package com.ht.service.impl.background.dicdata.defect;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;

import com.ht.common.util.DataConverter;
import com.ht.common.util.GenerateSequenceUtil;
import com.ht.common.util.LogHelper;
import com.ht.exception.DBException;
import com.ht.persistence.dao.inter.background.dicdata.defect.DefectDao;
import com.ht.persistence.model.background.dicdata.basedata.BaseData;
import com.ht.persistence.model.background.dicdata.defect.Defect;
import com.ht.persistence.model.background.dicdata.defect.ViewDefect;
import com.ht.persistence.model.background.dicdata.defectitem.DefectItem;
import com.ht.persistence.model.background.dicdata.defecttype.DefectType;
import com.ht.service.inter.background.dicdata.defect.DefectService;


/** 
* @ClassName: DefectServiceImpl 
* @Description: TODO(缺陷业务处理类) 
* @author penghao
* @date 2016年11月6日 下午2:36:22 
*  
*/
@SuppressWarnings("unchecked")
public class DefectServiceImpl implements DefectService {
	
	/**
	 * 注入缺陷数据处理Dao
	 */
	@Resource
	private DefectDao defectDao;

	/**
	 * 获取缺陷列表
	 */
	@Override
	public List<Defect> getDefectList(String param) {
		Defect defect = (Defect) DataConverter.convertJson2Object(param,Defect.class);
		return defectDao.getDefectList(defect);
	}
	
	/**
	 * 获取缺陷列表
	 */
	@Override
	public List<ViewDefect> getList() {
		return defectDao.getList();
	}

	/**
	 * 保存数据
	 */
	@Override
	public void add(String params) throws Exception {
		try {
		  Defect defect = (Defect) DataConverter.convertJson2Object(params, Defect.class);
		  List<Defect> list = defectDao.isExsit(defect);
		  // 如果id为空，添加
		  if(StringUtils.isBlank(defect.getId())){
			  // 如果存在
			  if(!CollectionUtils.isEmpty(list)){
				  throw new DBException("该缺陷已经存在！");
			  }
			  defect.setId(GenerateSequenceUtil.generateSequenceNo());
			  defectDao.add(defect);
		  }else{
			  if(!CollectionUtils.isEmpty(list)){
				  //更新之前
				  for (int i = 0; i < list.size(); i++) {
					  if(list.get(i).getId().equals(defect.getId())){
						  list.remove(i);
					  }
				  }
				  if(list.size()>0){
					  throw new DBException("该缺陷已经存在！");  
				  }
			  }
			  defectDao.modifyDefect(defect);
		  }
		} catch (Exception e) {
		  LogHelper.ERROR.log(e.getMessage());
		  throw e;
		}
		
	}

	/**
	 * 删除实体数据
	 */
	@Override
	public void delete(String ids) {
		try {
				// 将用户String类型转化成集合
				List<Defect> list = (List<Defect>) DataConverter.convertJson2List(ids,Defect.class);
				for (int i = 0; i < list.size(); i++) {
					//删除对象
					defectDao.delDefect(list.get(i));
				}
			} catch (Exception e) {
			  LogHelper.ERROR.log(e.getMessage());
			}
		}

	/**
	 * 根据id获取缺陷
	 */
	@Override
	public Defect getDefectById(String id) {
		Defect defect = new Defect();
		defect.setId(id);
		return defectDao.getDefectById(defect);
	}

	@Override
	public List<Defect> getDefectListBy3Id(String charttypeId,
			String defecttypeId, String defectitemId) {
		Defect defect = new Defect();
		BaseData data = new BaseData();
		data.setId(charttypeId);
		defect.setCharttype(data);
		
		DefectType type = new DefectType();
		type.setId(defecttypeId);
		defect.setType(type);
		
		DefectItem item = new DefectItem();
		item.setId(defectitemId);
		defect.setItem(item);
		List<Defect> list = defectDao.getDefectListBy3Id(defect);
		return list;
	}

}
