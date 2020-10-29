package com.ht.persistence.dao.inter.background.dicdata.defect;

import java.util.List;

import com.ht.persistence.model.background.dicdata.defect.Defect;
import com.ht.persistence.model.background.dicdata.defect.ViewDefect;

/** 
* @ClassName: DefectDao 
* @Description: TODO(缺陷项目数据操作接口) 
* @author penghao
* @date 2016年11月6日 下午2:41:05 
*  
*/
public interface DefectDao {
	
	/**
	 * 获取缺陷集合
	 * @param defect 
	 * @param
	 * @return
	 */
	List<Defect> getDefectList(Defect defect);
	
	/**
	 * 保存数据
	 * @param params json字符串
	 * @return
	 */
	void add(Defect defect);
	
	/**
	 * 修改数据
	 * @param params json字符串
	 * @return
	 */
	void modifyDefect(Defect defect);
	
	/**
	 * 删除数据
	 * @param ids
	 * @return
	 */
	void delDefect(Defect defect);
	
	/**
	 * 根据id获取缺陷
	 * @param defect实体
	 * @return
	 */
	Defect getDefectById(Defect defect);

	/**
	 * 根据海图类型id、类别、项目和缺陷判断是否存在
	 * @param defect
	 * @return
	 */
	List<Defect> isExsit(Defect defect);
	
	/**
	 * 根据项目id获取缺陷列表
	 * @param defectItem
	 * @return
	 */
	List<Defect> getDefectByDefectItemId(Defect defect);

	/**
	 * 根据3个id获取缺陷列表
	 * @param item
	 * @return
	 */
	List<Defect> getDefectListBy3Id(Defect defect);

	List<ViewDefect> getList();

}
