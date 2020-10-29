package com.ht.service.inter.background.authority.auth;

import java.util.List;

import com.ht.persistence.model.background.authority.auth.RoleApp;
import com.ht.persistence.model.background.authority.role.RoleApplicationRel;

/**
 * RoleAuthService接口
 * @author 侯晨
 */
public interface RoleAppService {
	/**
	  * 保存角色权限关系
	  * @param RoleApp
	  * @throws Exception
	  */
	public void addRoleAuth(String RoleApp) throws Exception;
	/**
	  * 修改角色权限关系
	  * @param RoleApp
	  * @throws Exception
	  */
	public void modifyRoleAuth(String RoleApp) throws Exception;
	/**
	  * 删除角色权限关系
	  * @param Organization
	  * @throws Exception
	  */
	public void delRoleAuth(String id) throws Exception;
	/**
	  * 查询所有角色权限关系
	  * @param Organization
	  * @throws Exception
	  */
	public List<RoleApp> getRoleAuth() throws Exception;
	/**
	  * 根据roleid查询角色权限关系
	  * @param roleId
	  * @throws Exception
	  */
	public List<RoleApp> getRoleAuth(String roleId) throws Exception;
	/**
	  * 插入角色与资源之间的关系
	  * @param roleId 角色ID
	  * @param apps 选中的资源id
	  * @throws Exception
	  */
	public void addRoleApps(String roleId, String apps) throws Exception;
	/**
	 * 根据roleid查询角色权限关系
	 * @param roleId 角色ID
	 * @throws Exception
	 */
	public List<RoleApplicationRel> getRoleAppsByRoleId(String roleId) throws Exception;
	/**
	 * 根据roleid查询角色权限关系
	 * @param roleId 角色ID
	 * @throws Exception
	 */
	public List<RoleApplicationRel> getRoleAppRelByRoleId(String roleId) throws Exception;
	/**
	 * 根据roleid查询角色权限关系
	 * @param roleId 角色ID
	 * @throws Exception
	 */
	public List<RoleApplicationRel> getRoleAppByRoleId(String roleId) throws Exception;
	
	/**
	 *根据appId查询角色权限关系
	 *@param appId 资源ID
	 *@return List<RoleApplicationRel> 角色权限关系对象集合
	 *@throws Exception
	 */
	public List<RoleApp> getRoleAppByAppId(String appId) throws Exception;
}
