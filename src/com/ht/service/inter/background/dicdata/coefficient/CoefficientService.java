package com.ht.service.inter.background.dicdata.coefficient;

import java.util.List;

import com.ht.persistence.model.background.dicdata.coefficient.Coefficient;

/** 
* @ClassName: CoefficientService 
* @Description: TODO(调整系数的接口) 
* @author penghao
* @date 2016年11月22日 下午7:31:38 
*  
*/
public interface CoefficientService {
	
	/**
	 * 获取难度系数集合
	 * @param discription 
	 * @param
	 * @return
	 */
	List<Coefficient> getList();
	
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
	 * 根据id获取难度系数
	 * @param id
	 * @return
	 */
	Coefficient getCoefficientById(String id);


	/**
	 * 根据mapNo获取难度系数
	 * @param mapNo
	 * @return
	 */
	List<Coefficient> getCoefficientBymapNo(String mapNo);
	
	/**
	 * 获取调整系数
	 * @param mapNo 图号
	 * @param typeId 类型
	 * @return
	 */
	Coefficient getCoefficient(String mapNo,String typeId);

	int mapcount(String mapno, String typeid);
	List<String> mapNoexi(String mapno);

}
