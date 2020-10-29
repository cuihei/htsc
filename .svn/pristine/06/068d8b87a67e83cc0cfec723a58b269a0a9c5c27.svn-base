package com.ht.persistence.dao.inter.background.organization.organization;

import java.util.List;

import com.ht.persistence.model.background.organization.organization.OrganizationUsersRelation;

/**
 * OrganizationUsersRelationDao接口
 * @author zqy
 */
public interface OrganizationUsersRelationDao {
	/**
	 * 增加一个OrganizationUsersRelation对象
	 * @param OrganizationUsersRelation organizationUsersRelation实体
	 */
	public void addOrganizationUsersRelation(OrganizationUsersRelation organizationUsersRelation);

	/**
	 * 更新一个OrganizationUsersRelation
	 * @param OrganizationUsersRelation organizationUsersRelation实体
	 */
	public void modifyOrganizationUsersRelation(OrganizationUsersRelation organizationUsersRelation);

	/**
	 * 删除OrganizationUsersRelation 相关
	 * @param OrganizationUsersRelation organizationUsersRelation实体
	 */
	public void delOrganizationUsersRelation(OrganizationUsersRelation organizationUsersRelation);
	
	/**
	 * 获取所有OrganizationUsersRelation
	 * @return List<OrganizationUsersRelation>列表
	 */
	public List<OrganizationUsersRelation> getOrganizationUsersRelation();

	/**
	 * 获取一条OrganizationUsersRelation
	 * @param organizationUsersRelation organizationUsersRelation实体
	 * @return OrganizationUsersRelation实体
	 */
	public OrganizationUsersRelation getOrganizationUsersRelation(OrganizationUsersRelation organizationUsersRelation);

	/**
	 * 获取某组织机构下人员信息列表
	 * @param organizationUsersRelation organizationUsersRelation实体
	 * @return organizationUsersRelation实体
	 */
	public List<OrganizationUsersRelation> getUsersByOrgId(OrganizationUsersRelation organizationUsersRelation);
	
	/**
	 * 根据人员ID获取关系信息
	 * @param organizationUsersRelation organizationUsersRelation实体
	 * @return organizationUsersRelation实体
	 */
	public OrganizationUsersRelation getUsersByUserId(OrganizationUsersRelation organizationUsersRelation);

	/**
	 * 获取某组织机构下人员以及公共部门的人员信息列表
	 * @param organizationUsersRelation organizationUsersRelation实体
	 * @return organizationUsersRelation实体
	 */
	public List<OrganizationUsersRelation> getOrgUsers(OrganizationUsersRelation organizationUsersRelation);

	/**
	 * 根据人员ID获取该员工是否被使用
	 * @param organizationUsersRelation organizationUsersRelation实体
	 * @return organizationUsersRelation实体
	 */
	public List<OrganizationUsersRelation> getUserByUserId(OrganizationUsersRelation organizationUsersRelation);

}
