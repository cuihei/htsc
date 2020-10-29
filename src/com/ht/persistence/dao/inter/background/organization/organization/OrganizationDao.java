package com.ht.persistence.dao.inter.background.organization.organization;

import java.util.List;

import com.ht.persistence.model.background.organization.organization.Organization;

/**
 * OrganizationDao接口
 * @author zqy
 */
public interface OrganizationDao {
	/**
	 * 增加一个组织机构数据
	 * @param Organization organization实体
	 */
	public void addOrganization(Organization organization);

	/**
	 * 更新一个组织机构数据
	 * @param Organization organization实体
	 */
	public void modifyOrganization(Organization organization);

	/**
	 * 删除Organization 相关
	 * @param Organization organization实体
	 */
	public void delOrganization(Organization organization);
	
	/**
	 * 获取所有Organization
	 * @return List<Organization> 返回组织机构列表
	 */
	public List<Organization> getOrganization();

	/**
	 * 获取一条Organization
	 * @param Organization organization实体类
	 * @return 返回一条组织机构数
	 */
	public Organization getOrganization(Organization organization);
	
	/**
	 * 获取某组织机构对象节点下的所有子节点，形成树结构
	 * @param Organization Organization对象
	 * @return List<Organization> Organization对象的集合
	 */
	public List<Organization> getOrganizationChilds(Organization organization);
	
	/**
	 * 获取组织机构对象父节点下的所有子节点，形成树结构
	 * @return List<Organization> Organization对象的集合
	 */
	public List<Organization> getOrganizationTree();
}
