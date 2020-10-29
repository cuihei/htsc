package com.ht.service.inter.background.dicdata.defecttype;

import java.util.List;

import com.ht.persistence.model.background.dicdata.defecttype.DefectType;

/** 
* @ClassName: DefectService 
* @Description: TODO(缺陷类别的接口) 
* @author penghao
* @date 2016年11月6日 下午2:34:38 
*  
*/
public interface DefectTypeService {
	
	/**
	 * 获取缺陷类别集合
	 * @param
	 * @return
	 */
	List<DefectType> getDefectTypeList();
	
	/**
	 * 保存数据
	 * @param params json字符串
	 * @return
	 */
	void add(String params) throws Exception;
	
	/**
	 * 删除数据
	 * @param ids
	 * @return
	 */
	String delete(String ids) throws Exception;
	
	/**
	 * 根据id获取缺陷
	 * @param id
	 * @return
	 */
	DefectType getDefectTypeById(String id);
	
	/**
	 * 根据海图类型id获取类别的list
	 * @param charttypeId 海图类型id
	 * @return
	 */
	List<DefectType> getDefectTypeListByCharttypeId(String charttypeId);

	List<DefectType> isExsit(DefectType d);
	
}
