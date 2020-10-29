package com.ht.service.inter.background.organization.organization;

import java.util.List;

import com.ht.persistence.model.background.organization.employee.UserOrgView;
import com.ht.persistence.model.background.organization.organization.OrganizationUsersRelation;

/**
 * 组织机构人员关系
 * @author 侯晨
 */
public interface OrganizationUsersRelationService {
	
	/**
	  * 保存组织机构人员关系
	  * @param organizationUsersRelation
	  * @throws Exception
	  */
	public void addOrganizationUsersRelation(String organizationUsersRelation) throws Exception;
	
	/**
	  * 修改组织机构人员关系
	  * @param organizationUsersRelation
	  * @throws Exception
	  */
	public void modifyOrganizationUsersRelation(String organizationUsersRelation) throws Exception;
	
	/**
	  * 删除组织机构人员关系
	  * @param id
	  * @throws Exception
	  */
	public void delOrganizationUsersRelation(String id) throws Exception;
	
	/**
	  * 查询所有
	  * @return List<OrganizationUsersRelation> 组织机构人员关系表
	  * @throws Exception
	  */
	public List<OrganizationUsersRelation> getOrganizationUsersRelation() throws Exception;
	
	/**
	  * 查询一条
	  * @param id
	  * @return OrganizationUsersRelation 组织机构人员关系实体
	  * @throws Exception
	  */	
	public OrganizationUsersRelation getOrganizationUsersRelation(String id) throws Exception;
	
	/**
	  * 获取某组织机构下人员信息列表
	  * @param organizationUsersRelation
	  * @return List<OrganizationUsersRelation> 组织机构人员关系表
	  * @throws Exception
	  */
	public List<OrganizationUsersRelation> getUsersByOrgId(String orgId) throws Exception;
	
	/**
	  * 提交某组织机构下人员关系
	  * @param orgId 组织机构Id users 人员标识list
	  * @throws Exception
	  */
	public void addOrgUsers(String orgId, String users) throws Exception;
	
	/**
	 * 获取某组织机构下人员以及公共部门的人员信息列表
	 * @param organizationUsersRelation 组织人员关系数据
	 * @param userList 某组织机构下人员
	 * @return List<UserOrgView> 组织人员关系列表
	 */
	public List<UserOrgView> getOrgUsers(String organizationUsersRelation, List<UserOrgView> userList) throws Exception;
	
	/**
	 * 根据人员ID获取关系信息
	 * @param userId 用户Id
	 * @return organizationUsersRelation实体
	 */
	public OrganizationUsersRelation getUsersByUserId(String userId) throws Exception;

}
