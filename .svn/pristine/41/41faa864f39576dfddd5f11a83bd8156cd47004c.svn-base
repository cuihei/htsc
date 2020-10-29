package com.ht.service.inter.background.authority.role;

/**
 * RoleUsersService接口
 * @author 侯晨
 */
import java.util.List;

import com.ht.persistence.model.background.authority.role.RoleUsers;
import com.ht.persistence.model.background.authority.role.RoleUsersRel;

public interface RoleUsersService {
	/**
	  * 保存角色用户关系
	  * @param roleUsers
	  * @throws Exception
	  */
	public void addRoleUsers(String roleUsers) throws Exception;
	/**
	  * 修改角色用户关系
	  * @param id
	  * @throws Exception
	  */
	public void modifyRoleUsers(String roleUsers) throws Exception;
	/**
	  * 删除角色用户关系
	  * @param id
	  * @throws Exception
	  */
	public void delRoleUsers(String id) throws Exception;
	/**
	  * 查询所有角色用户关系
	  * @throws Exception
	  */
	public List<RoleUsers> getRoleUsers() throws Exception;
	/**
	  * 根据roleid查询角色用户关系
	  * @param roleId
	  * @throws Exception
	  */
	public List<RoleUsers> getRoleUsers(String roleId) throws Exception;
	/**
	  * 根据roleid查询角色用户关系视图
	  * @param roleusersParam 角色人员关系
	  * @throws Exception
	  */
	public List<RoleUsersRel> getRoleUsersByRoleId(String roleusersParam) throws Exception;
	/**
	  * 插入角色与用户之间的关系
	  * @param roleId 角色ID
	  * @param users 选中的人员id
	  * @throws Exception
	  */
	public void addRoleUsers(String roleId, String users) throws Exception;
	/**
	  * 根据UserId查询角色用户关系视图
	  * @param roleusersParam 角色人员关系
	  * @throws Exception
	  */
	public List<RoleUsers> getRoleUsersByUserId(String roleusersParam) throws Exception;
}
