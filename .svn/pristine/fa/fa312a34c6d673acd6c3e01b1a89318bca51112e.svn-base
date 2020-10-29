package com.ht.service.impl.background.dicdata.defectitem;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;

import com.ht.common.util.DataConverter;
import com.ht.common.util.GenerateSequenceUtil;
import com.ht.common.util.LogHelper;
import com.ht.exception.DBException;
import com.ht.persistence.dao.inter.background.dicdata.defect.DefectDao;
import com.ht.persistence.dao.inter.background.dicdata.defectitem.DefectItemDao;
import com.ht.persistence.model.background.dicdata.defect.Defect;
import com.ht.persistence.model.background.dicdata.defectitem.DefectItem;
import com.ht.persistence.model.background.dicdata.defecttype.DefectType;
import com.ht.service.inter.background.dicdata.defectitem.DefectItemService;


/** 
* @ClassName: DefectServiceImpl 
* @Description: TODO(缺陷项目业务处理类) 
* @author penghao
* @date 2016年11月6日 下午2:36:22 
*  
*/
@SuppressWarnings("unchecked")
public class DefectItemServiceImpl implements DefectItemService {
	
	/**
	 * 注入缺陷项目数据处理Dao
	 */
	@Resource
	private DefectItemDao defectItemDao;
	
	/**
	 * 注入缺陷处理Dao
	 */
	@Resource
	private DefectDao defectDao;

	@Override
	public List<DefectItem> getDefectItemList(String item) {
		DefectItem di = (DefectItem) DataConverter.convertJson2Object(item,DefectItem.class);
		return defectItemDao.getDefectItemList(di);
	}

	@Override
	public void add(String params) throws Exception {
		try {
		  DefectItem item = (DefectItem) DataConverter.convertJson2Object(params, DefectItem.class);
		  //保存之前判断是否已经存在
		  List<DefectItem> list = defectItemDao.isExsit(item);
		  // 如果id为空
		  if(StringUtils.isBlank(item.getId())){
			  // 如果已经存在
			  if(!CollectionUtils.isEmpty(list)){
				 throw new DBException("该海图类型、类别下项目已经存在！"); 
			  }
			  item.setId(GenerateSequenceUtil.generateSequenceNo());
			  defectItemDao.add(item);
		  }else{
			  //更新之前
			  if(!CollectionUtils.isEmpty(list)){
				  for (int i = 0; i < list.size(); i++) {
					  if(list.get(i).getId().equals(item.getId())){
						  list.remove(i);
					  }
				  }
				  if(list.size()>0){
					  throw new DBException("该海图类型、类别下项目已经存在！");  
				  }
			  }
			  defectItemDao.modifyDefectItem(item);
		  }
		} catch (Exception e) {
		  LogHelper.ERROR.log(e.getMessage());
		  throw e;
		}
		
	}

	/**
	 * 根据id删除数据
	 */
	@Override
	public String delete(String ids) throws Exception{
		StringBuffer failmsg = new StringBuffer();
		try {
			// 将用户String类型转化成集合
			List<DefectItem> list = (List<DefectItem>) DataConverter.convertJson2List(ids,DefectItem.class);
			for (int i = 0; i < list.size(); i++) {
				//根据id获取缺陷类容
				DefectItem item = defectItemDao.getDefectItemById(list.get(i));
				String chartName = "";
				if(item.getCharttype()!=null&&StringUtils.isNotBlank(item.getCharttype().getValue())){
					chartName = item.getCharttype().getValue();
				}
				String typeName = "";
				if(item.getType()!=null&&StringUtils.isNotBlank(item.getType().getTypeName())){
					typeName = item.getType().getTypeName();
				}
				//删除之前判断是否被缺陷使用
				Defect defect = new Defect();
				defect.setItem(list.get(i));
				List<Defect> defectList = defectDao.getDefectByDefectItemId(defect);
				if(!CollectionUtils.isEmpty(defectList)){
					failmsg.append(chartName+" "+typeName+" "+item.getItem()+"被缺陷使用，请先删除缺陷！");
					continue;
				}
				//删除对象
				defectItemDao.delDefectItem(list.get(i));
			}
			} catch (Exception e) {
			  LogHelper.ERROR.log(e.getMessage());
			  throw new DBException();
			}
		return failmsg.toString();
		
	}

	@Override
	public DefectItem getDefectItemById(String id) {
		DefectItem item = new DefectItem();
		item.setId(id);
		return defectItemDao.getDefectItemById(item);
	}

	/**
	 * 根据缺陷类别获取项目列表
	 */
	@Override
	public List<DefectItem> getDefectItemListByDefectTypeId(String typeId) {
		List<DefectItem> list = null;
		try {
			DefectItem item = new DefectItem();
			DefectType type = new DefectType();
			type.setId(typeId);
			item.setType(type);
			list = defectItemDao.getDefectItemListByDefectTypeId(item);
		} catch (Exception e) {
			LogHelper.ERROR.log(e.getMessage());
		}
		return list;
	}

	@Override
	public List<DefectItem> isExsit(DefectItem defectItem) {
		List<DefectItem> list = null;
		try {
			list = defectItemDao.isExsit(defectItem);
		} catch (Exception e) {
			LogHelper.ERROR.log(e.getMessage());
		}
		return list;
	}

}
