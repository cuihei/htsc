package com.ht.persistence.dao.inter.background.dicdata.defectitem;

import java.util.List;

import com.ht.persistence.model.background.dicdata.defectitem.DefectItem;
import com.ht.persistence.model.background.dicdata.defecttype.DefectType;

/** 
* @ClassName: DefectDao 
* @Description: TODO(缺陷数据操作接口) 
* @author penghao
* @date 2016年11月6日 下午2:41:05 
*  
*/
public interface DefectItemDao {
	
	/**
	 * 获取缺陷集合
	 * @param item 
	 * @param
	 * @return
	 */
	List<DefectItem> getDefectItemList(DefectItem item);
	
	/**
	 * 保存数据
	 * @param params json字符串
	 * @return
	 */
	void add(DefectItem item);
	
	/**
	 * 修改数据
	 * @param params json字符串
	 * @return
	 */
	void modifyDefectItem(DefectItem item);
	
	/**
	 * 删除数据
	 * @param ids
	 * @return
	 */
	void delDefectItem(DefectItem item);
	
	/**
	 * 根据id获取缺陷
	 * @param defect实体
	 * @return
	 */
	DefectItem getDefectItemById(DefectItem item);

	/**
	 * 根据缺陷类别id获取项目列表
	 * @param
	 * @return
	 */
	List<DefectItem> getDefectItemListByDefectTypeId(DefectItem item);
	
	/**
	 * 根据海图类型id，类别id和项目判断数据是否存在
	 * @param item
	 * @return
	 */
	List<DefectItem> isExsit(DefectItem item);

	/**
	 * 根据类别id获取项目的集合
	 * @param item
	 * @return
	 */
	List<DefectItem> getDefectItemByDefectTypeId(DefectItem item);
	
}
