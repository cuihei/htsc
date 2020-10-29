package com.ht.persistence.dao.impl.background.organization.organization;

import java.util.List;

import org.hibernate.SQLQuery;
import com.ht.persistence.dao.impl.base.BaseDaoImpl;

import com.ht.persistence.dao.inter.background.organization.organization.OrganizationDao;
import com.ht.persistence.model.background.organization.organization.Organization;

/**
 * Organization 接口的实现类
 * @author zqy
 *
 */
public class OrganizationDaoImpl extends BaseDaoImpl implements OrganizationDao{

	/**
	 * 增加一个Organization
	 * @param organization Organization实体
	 */
	@Override
	public void addOrganization(Organization organization) {
		this.save(organization);
	}

	/**
	 * 更新一个Organization
	 * @param Organization organization实体
	 */
	@Override
	public void modifyOrganization(Organization organization) {
		this.update(organization);
	}

	/**
	 * 删除Organization 相关
	 * @param Organization organization对象
	 */
	@Override
	public void delOrganization(Organization organization) {
		this.delete(organization);
	}

	/**
	 * 获取所有Organization
	 * @return List<Organization> 组织机构对象列表
	 */
	@Override
	public List<Organization> getOrganization() {
		try {
			@SuppressWarnings("unchecked")
			List<Organization> result = this.findByNamedQuery("getOrganization");
			for (int i = 0; i < result.size(); i++) {
				Organization o = result.get(i);
				if (o.getId().equals("10191641226710006")) {
					result.remove(i);
				}
			}
			return result;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	/**
	 * 获取一条Organization
	 * @param Organization organization对象
	 * @return Organization实体
	 */
	@Override
	public Organization getOrganization(Organization organization) {
		@SuppressWarnings("unchecked")
		List<Organization> result = this.findByNamedQueryAndNamedParam("getOrganizationById", "id",organization.getId());
		if(result.size()>0){
			return result.get(0);
		}
		return null;
	}

	/**
	 * 获取组织机构对象父节点下的所有子节点，形成树结构
	 * @param Organization Organization对象
	 * @return List<Organization> Organization对象的集合
	 */
	@Override
	public List<Organization> getOrganizationChilds(Organization organization) {
		SQLQuery query = this.getSession().createSQLQuery("select * from ORGANIZATION t   start with t.id=?  connect by t.parent_id = prior t.id");
		query.setString(0,organization.getId());
		query.addEntity(Organization.class);
		@SuppressWarnings("unchecked")
		List<Organization> orgList =  query.list();
		return orgList;
	}

	/**
	 * 获取组织机构下的所有子节点，形成树结构
	 * @param Organization Organization对象
	 * @return List<Organization> Organization对象的集合
	 */
	@Override
	public List<Organization> getOrganizationTree() {
		// 查询根组织下的所有机构列表，用递归算法
		SQLQuery query = this.getSession().createSQLQuery("select * from ORGANIZATION t   start with t.id='1019142929270002'  connect by t.parent_id = prior t.id");
		query.addEntity(Organization.class);
		@SuppressWarnings("unchecked")
		List<Organization> orgList =  query.list();
		return orgList;
	}
}
