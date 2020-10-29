package com.ht.persistence.dao.inter.background.dicdata.defecttype;

import java.util.List;

import com.ht.persistence.model.background.dicdata.defecttype.DefectType;

/** 
* @ClassName: DefectTypeDao 
* @Description: TODO(缺陷项目数据操作接口) 
* @author penghao
* @date 2016年11月6日 下午2:41:05 
*  
*/
public interface DefectTypeDao {
	
	/**
	 * 获取缺陷集合
	 * @param
	 * @return
	 */
	List<DefectType> getDefectTypeList();
	
	/**
	 * 保存数据
	 * @param params json字符串
	 * @return
	 */
	void add(DefectType defectType);
	
	/**
	 * 修改数据
	 * @param params json字符串
	 * @return
	 */
	void modifyDefectType(DefectType defectType);
	
	/**
	 * 删除数据
	 * @param ids
	 * @return
	 */
	void delDefectType(DefectType defectType);
	
	/**
	 * 根据id获取缺陷
	 * @param DefectType实体
	 * @return
	 */
	DefectType getDefectTypeById(DefectType defectType);
	
	/**
	 * 根据海图类型id获取类别集合
	 * @param
	 * @return
	 */
	List<DefectType> getDefectTypeListByCharttypeId(DefectType type);
	
	/**
	 * 根据海图类型id和类别判断是否已经存在
	 * @param type
	 */
	List<DefectType> isExsit(DefectType type);

}
