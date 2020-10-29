package com.ht.persistence.dao.inter.background.dicdata.coefficient;

import java.util.List;

import com.ht.persistence.model.background.dicdata.coefficient.Coefficient;

/** 
* @ClassName: CoefficientDao 
* @Description: TODO(调整系数数据操作接口) 
* @author penghao
* @date 2016年11月22日 下午7:30:05 
*  
*/
public interface CoefficientDao {
	/**
	 * 获取调整系数集合
	 * @param defect 
	 * @param
	 * @return
	 */
	List<Coefficient> getList();
	
	/**
	 * 保存数据
	 * @param coefficient
	 * @return
	 */
	void add(Coefficient coefficient);
	
	/**
	 * 修改数据
	 * @param coefficient
	 * @return
	 */
	void modifyCoefficient(Coefficient coefficient);
	
	/**
	 * 删除数据
	 * @param ids
	 * @return
	 */
	void delCoefficient(Coefficient coefficient);
	
	/**
	 * 根据id获取调整系数
	 * @param coefficient实体
	 * @return
	 */
	Coefficient getCoefficientById(Coefficient coefficient);
	
	
	/**
	 * 根据mapNo获取调整系数
	 * @param coefficient实体
	 * @return
	 */
	 List<Coefficient> getCoefficientByMapNo(Coefficient coefficient);

	/**
	 * 根据类型id和图号判断是否存在
	 * @param coefficient
	 * @return
	 */
	List<Coefficient> isExsit(Coefficient coefficient);

	/**
	 * 获取难度系数首版
	 * @param coefficient
	 * @return
	 */
	Coefficient getCoefficient(Coefficient coefficient);

	int mapcount(String mapno, String typeid);
	List<String> mapNoexi(String mapno);
	
}
