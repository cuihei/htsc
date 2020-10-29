package com.ht.persistence.dao.impl.datum.type;

import java.util.List;

import org.hibernate.SQLQuery;
import com.ht.persistence.dao.impl.base.BaseDaoImpl;

import com.ht.persistence.dao.inter.datum.type.DatumCategoryDao;
import com.ht.persistence.model.datum.type.DatumCategory;

/**
 * 资料类别 数据映射操作类
 * @author 平子金
 *
 */
public class DatumCategoryDaoImpl extends BaseDaoImpl implements DatumCategoryDao{

	/**
	 * 增加一个资料类别
	 * @param datumcategory
	 */
	@Override
	public void addDatumCategory(DatumCategory datumcategory) {
		this.save(datumcategory);
	}

	/**
	 * 更新一个资料类别
	 * @param datumcategory
	 */
	@Override
	public void modifyDatumCategory(DatumCategory datumcategory) {
		this.update(datumcategory);
	}

	/**
	 * 删除资料类别
	 * @param datumcategory
	 */
	@Override
	public void delDatumCategory(DatumCategory datumcategory) {
		this.delete(datumcategory);
	}

	/**
	 * 获取所有资料类别
	 * 
	 */
	@Override
	public List<DatumCategory> getDatumCategory() {
		try {
			@SuppressWarnings("unchecked")
			List<DatumCategory> result = this.findByNamedQuery("getDatumCategory");
			return result;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	/**
	 * 获取一条资料类别
	 * @param datumcategory
	 * @return DatumCategory实体
	 */
	@Override
	public DatumCategory getDatumCategory(DatumCategory datumcategory) {
		@SuppressWarnings("unchecked")
		List<DatumCategory> result = this.findByNamedQueryAndNamedParam("getDatumCategoryById", "id", datumcategory.getId());
		if(result.size()>0){
			return result.get(0);
		}
		return null;
	}

	/**
	 * 获取DatumCategory对象父节点下的所有子节点，形成树结构
	 * @param DatumCategory DatumCategory对象
	 * @return List<DatumCategory> DatumCategory对象的集合
	 */
	@Override
	public List<DatumCategory> getDatumCategoryTree() {
		
		SQLQuery query = this.getSession().createSQLQuery("select * from Datum_Category t start with t.id='201610301150' connect by t.parent_id = prior t.id");
		query.addEntity(DatumCategory.class);
		
		@SuppressWarnings("unchecked")
		List<DatumCategory> appList =  query.list();
		return appList;
	}
	
	/**
	 * 根据父Id获取部分资料类别
	 */
	@Override
	public List<DatumCategory> getDatumCategoryByParentId(DatumCategory dc) {
		@SuppressWarnings("unchecked")
		List<DatumCategory> result = this.findByNamedQueryAndNamedParam("getDatumCategoryByParentId", "parentId", dc.getParentId());
		if(result.size()>0){
			return result;
		}
		return null;
	}
   
}
