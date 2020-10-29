package com.ht.persistence.dao.inter.datum.type;

import java.util.List;

import com.ht.persistence.model.datum.type.DatumCategory;

/**
 * 资料类别Dao
 * @author 平子金
 */
public interface DatumCategoryDao {
	
	/**
	 * 增加资料类别
	 * @param datumcategory 资料类别实体
	 * @return 
	 */
	public void addDatumCategory(DatumCategory datumcategory);

	/**
	 * 更新资料类别
	 * @param datumcategory 资料类别实体
	 */
	public void modifyDatumCategory(DatumCategory datumcategory);

	/**
	 * 删除资料类别
	 * @param datumcategory 资料类别实体
	 */
	public void delDatumCategory(DatumCategory datumcategory);
	
	/**
	 * 获取所有资料类别
	 */
	public List<DatumCategory> getDatumCategory();

	/**
	 * 获取一条资料类别
	 * @param datumcategory 资料类别实体
	 */
	public DatumCategory getDatumCategory(DatumCategory datumcategory);
	
	/**
	 * 获取DatumCategory对象父节点下的所有子节点，形成树结构
	 * @param DatumCategory DatumCategory对象
	 * @return List<DatumCategory> DatumCategory对象的集合
	 */
	public List<DatumCategory> getDatumCategoryTree();
	
	/**
	 * 根据父Id获取部分资料类别
	 * @param dc
	 * @return
	 */
	public List<DatumCategory> getDatumCategoryByParentId(DatumCategory dc);
}
