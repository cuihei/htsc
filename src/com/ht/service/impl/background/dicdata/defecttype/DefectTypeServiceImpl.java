package com.ht.service.impl.background.dicdata.defecttype;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import com.ht.common.util.DataConverter;
import com.ht.common.util.GenerateSequenceUtil;
import com.ht.common.util.LogHelper;
import com.ht.exception.DBException;
import com.ht.persistence.dao.inter.background.dicdata.defectitem.DefectItemDao;
import com.ht.persistence.dao.inter.background.dicdata.defecttype.DefectTypeDao;
import com.ht.persistence.model.background.dicdata.basedata.BaseData;
import com.ht.persistence.model.background.dicdata.defectitem.DefectItem;
import com.ht.persistence.model.background.dicdata.defecttype.DefectType;
import com.ht.service.inter.background.dicdata.defecttype.DefectTypeService;


/** 
* @ClassName: DefectTypeServiceImpl 
* @Description: TODO(缺陷类别业务处理类) 
* @author penghao
* @date 2016年11月6日 下午2:36:22 
*  
*/
@SuppressWarnings("unchecked")
public class DefectTypeServiceImpl implements DefectTypeService {
	
	/**
	 * 注入缺陷数据处理Dao
	 */
	@Resource
	private DefectTypeDao defectTypeDao;
	
	/**
	 * 注入项目处理Dao
	 */
	@Resource
	private DefectItemDao defectItemDao;

	/**
	 * 获取缺陷类别列表
	 */
	@Override
	public List<DefectType> getDefectTypeList() {
		
		return defectTypeDao.getDefectTypeList();
	}

	/**
	 * 保存实体
	 */
	@Override
	public void add(String params) throws Exception {
		try {
		  DefectType type = (DefectType) DataConverter.convertJson2Object(params, DefectType.class);
		  //保存之前判断是否已经存在
		  List<DefectType> list = defectTypeDao.isExsit(type);
		  //如果id不为空
		  if(StringUtils.isBlank(type.getId())){
			  // 如果不为空
			  if(!CollectionUtils.isEmpty(list)){
				 throw new DBException("该海图类型下类别已经存在！"); 
			  }
			  type.setId(GenerateSequenceUtil.generateSequenceNo());
			  //添加实体
			  defectTypeDao.add(type);
		  }else{
			  if(!CollectionUtils.isEmpty(list)){
				  //更新之前
				  for (int i = 0; i < list.size(); i++) {
					  if(list.get(i).getId().equals(type.getId())){
						  list.remove(i);
					  }
				  }
				  if(list.size()>0){
					  throw new DBException("该海图类型下类别已经存在");  
				  }
			  }
			  //更新实体
			  defectTypeDao.modifyDefectType(type);
		  }
		} catch (Exception e) {
		  LogHelper.ERROR.log(e.getMessage());
		  throw e;
		}
	}

	/**
	 * 删除实体
	 * @throws DBException 
	 */
	@Override
	public String delete(String ids) throws Exception {
		StringBuffer failmsg = new StringBuffer();
		try {
			// 将用户String类型转化成集合
			List<DefectType> list = (List<DefectType>) DataConverter.convertJson2List(ids,DefectType.class);
			for (int i = 0; i < list.size(); i++) {
				//根据id获取类别信息
				DefectType type = defectTypeDao.getDefectTypeById(list.get(i));
				String value = "";
				if(type.getCharttype() != null && StringUtils.isNotBlank(type.getCharttype().getValue())){
					value = type.getCharttype().getValue();
				}
				// 删除之前判断类别是否被项目应用
				DefectItem item = new DefectItem();
				item.setType(list.get(i));
				List<DefectItem> itemList = defectItemDao.getDefectItemByDefectTypeId(item);
				if(!CollectionUtils.isEmpty(itemList)){
					failmsg.append(value+"类别"+type.getTypeName()+"被项目数据使用，请先删除项目！");
					continue;
				}
				// 删除对象
				defectTypeDao.delDefectType(list.get(i));
			}
		} catch (Exception e) {
		  LogHelper.ERROR.log(e.getMessage());
		  throw new DBException();
		}
		return failmsg.toString();
		
	}

	/**
	 * 根据id获取缺陷类别
	 */
	@Override
	public DefectType getDefectTypeById(String id) {
		DefectType type = new DefectType();
		type.setId(id);
		return defectTypeDao.getDefectTypeById(type);
	}

	/**
	 * 根据海图类型id获取缺陷类别列表
	 */
	@Override
	public List<DefectType> getDefectTypeListByCharttypeId(String charttypeId) {
		List<DefectType> list = null;
		try {
			DefectType type =  new DefectType();
			BaseData data = new BaseData();
			data.setId(charttypeId);
			type.setCharttype(data);
			list = defectTypeDao.getDefectTypeListByCharttypeId(type);
		} catch (Exception e) {
			LogHelper.ERROR.log(e.getMessage());
			throw e;
		}
		return list;
		
	}

	@Override
	public List<DefectType> isExsit(DefectType d) {
		List<DefectType> list = null;
		try {
			list = defectTypeDao.isExsit(d);
		} catch (Exception e) {
			LogHelper.ERROR.log(e.getMessage());
			throw e;
		}
		return list;
	}

}
