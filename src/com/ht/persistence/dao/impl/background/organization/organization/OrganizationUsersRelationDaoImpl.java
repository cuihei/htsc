package com.ht.persistence.dao.impl.background.organization.organization;

import java.util.List;

import org.hibernate.SQLQuery;
import com.ht.persistence.dao.impl.base.BaseDaoImpl;

import com.ht.persistence.dao.inter.background.organization.organization.OrganizationUsersRelationDao;
import com.ht.persistence.model.background.organization.organization.OrganizationUsersRelation;

/**
 * OrganizationUsersRelation 接口的实现类
 * @author 侯晨
 *
 */
public class OrganizationUsersRelationDaoImpl extends BaseDaoImpl implements OrganizationUsersRelationDao{

	/**
	 * 增加一个OrganizationUsersRelation
	 * @param organizationUsersRelation OrganizationUsersRelation实体
	 */
	@Override
	public void addOrganizationUsersRelation(OrganizationUsersRelation organizationUsersRelation) {
		this.save(organizationUsersRelation);
	}

	/**
	 * 更新一个OrganizationUsersRelation
	 * @param OrganizationUsersRelation organizationUsersRelation实体
	 */
	@Override
	public void modifyOrganizationUsersRelation(OrganizationUsersRelation organizationUsersRelation) {
		this.update(organizationUsersRelation);
	}

	/**
	 * 删除OrganizationUsersRelation 相关
	 * @param OrganizationUsersRelation organizationUsersRelation对象
	 */
	@Override
	public void delOrganizationUsersRelation(OrganizationUsersRelation organizationUsersRelation) {
		this.delete(organizationUsersRelation);
	}

	/**
	 * 获取所有OrganizationUsersRelation
	 * @return List<OrganizationUsersRelation>列表
	 */
	@Override
	public List<OrganizationUsersRelation> getOrganizationUsersRelation() {
		try {

			@SuppressWarnings("unchecked")
			List<OrganizationUsersRelation> result = this.findByNamedQuery("getOrganizationUsersRelation");
			return result;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	/**
	 * 获取一条OrganizationUsersRelation
	 * @param OrganizationUsersRelation organizationUsersRelation对象
	 * @return OrganizationUsersRelation对象
	 */
	@Override
	public OrganizationUsersRelation getOrganizationUsersRelation(OrganizationUsersRelation organizationUsersRelation) {
		@SuppressWarnings("unchecked")
		List<OrganizationUsersRelation> result = this.findByNamedQueryAndNamedParam("getOrganizationUsersRelationById", "id", organizationUsersRelation.getId());
		if(result.size()>0){
			return result.get(0);
		}
		return null;
	}

	/**
	 * 获取某组织机构下人员信息列表
	 * @param organizationUsersRelation organizationUsersRelation实体
	 * @return organizationUsersRelation实体
	 */
	@Override
	public List<OrganizationUsersRelation> getUsersByOrgId(OrganizationUsersRelation organizationUsersRelation) {
		@SuppressWarnings("unchecked")
		List<OrganizationUsersRelation> result = this
				.findByNamedQueryAndNamedParam("getUsersByOrgId", "orgId",
						organizationUsersRelation.getOrgId());
		return result;
	}

	/**
	 * 获取某组织机构下人员以及公共部门的人员信息列表
	 * @param organizationUsersRelation organizationUsersRelation实体
	 * @return organizationUsersRelation实体
	 */
	@Override
	public List<OrganizationUsersRelation> getOrgUsers(
			OrganizationUsersRelation organizationUsersRelation) {
		SQLQuery query = this.getSession().createSQLQuery("select * from ORGANIZATION_USERS_RELATION t where t.org_id = ? or t.org_id ='10191641226710006'");
		query.setString(0,organizationUsersRelation.getOrgId());
		query.addEntity(OrganizationUsersRelation.class);
		@SuppressWarnings("unchecked")
		List<OrganizationUsersRelation> orgUsersList =  query.list();
		return orgUsersList;
	}

	/**
	 * 根据人员ID获取关系信息
	 * @param organizationUsersRelation organizationUsersRelation实体
	 * @return organizationUsersRelation实体
	 */
	@Override
	public OrganizationUsersRelation getUsersByUserId(OrganizationUsersRelation organizationUsersRelation) {
		@SuppressWarnings("unchecked")
		List<OrganizationUsersRelation> result = this.findByNamedQueryAndNamedParam("getUsersByUserId", "userId", organizationUsersRelation.getUserId());
		if(result.size()>0){
			return result.get(0);
		}
		return null;
	}

	/**
	 * 根据人员ID获取该员工是否被使用
	 * @param organizationUsersRelation organizationUsersRelation实体
	 * @return organizationUsersRelation实体
	 */
	@Override
	public List<OrganizationUsersRelation> getUserByUserId(
			OrganizationUsersRelation organizationUsersRelation) {
		SQLQuery query = this.getSession().createSQLQuery("select * from ORGANIZATION_USERS_RELATION t where t.user_id = ? and t.org_id  <> '10191641226710006'");
		query.setString(0,organizationUsersRelation.getUserId());
		query.addEntity(OrganizationUsersRelation.class);
		@SuppressWarnings("unchecked")
		List<OrganizationUsersRelation> orgUsersList =  query.list();
		if(orgUsersList != null){
			if(orgUsersList.size()>0){
				for (int i = 0; i < orgUsersList.size(); i++)
				{
					if(orgUsersList.get(i).getOrgId().equals("--请选择--")){
						orgUsersList.remove(i);
					}
				}
			}
		}
		return orgUsersList;
	}
	
}
