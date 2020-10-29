package com.ht.service.inter.background.dicdata.defectitem;

import java.util.List;

import com.ht.persistence.model.background.dicdata.defectitem.DefectItem;

/** 
* @ClassName: DefectService 
* @Description: TODO(缺陷项目处理的接口) 
* @author penghao
* @date 2016年11月6日 下午2:34:38 
*  
*/
public interface DefectItemService {
	
	/**
	 * 获取缺陷集合
	 * @param item 
	 * @param
	 * @return
	 */
	List<DefectItem> getDefectItemList(String item);
	
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
	DefectItem getDefectItemById(String id);
	
	/**
	 * 根据类别id获取项目列表
	 * @param
	 * @return
	 */
	List<DefectItem> getDefectItemListByDefectTypeId(String typeId);

	List<DefectItem> isExsit(DefectItem defectItem);

}
