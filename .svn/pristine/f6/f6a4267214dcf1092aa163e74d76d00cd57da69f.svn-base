package com.ht.service.inter.background.dicdata.defect;

import java.util.List;

import com.ht.persistence.model.background.dicdata.defect.Defect;
import com.ht.persistence.model.background.dicdata.defect.ViewDefect;

/** 
* @ClassName: DefectService 
* @Description: TODO(缺陷处理的接口) 
* @author penghao
* @date 2016年11月6日 下午2:34:38 
*  
*/
public interface DefectService {
	
	/**
	 * 获取缺陷集合
	 * @param discription 
	 * @param
	 * @return
	 */
	List<Defect> getDefectList(String param);
	
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
	void delete(String ids);
	
	/**
	 * 根据id获取缺陷
	 * @param id
	 * @return
	 */
	Defect getDefectById(String id);

	/**
	 * 根据3个id获取缺陷列表
	 * @param charttypeId 海图类型id
	 * @param defecttypeId 类别id
	 * @param defectitemId 项目id
	 * @return
	 */
	List<Defect> getDefectListBy3Id(String charttypeId, String defecttypeId,
			String defectitemId);

	List<ViewDefect> getList();
	
}
