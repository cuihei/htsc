package com.ht.service.inter.background.authority.role;

import java.util.List;

import com.ht.persistence.model.background.authority.role.Role;

/**
 * RoleService接口
 * @author 侯晨
 */
public interface RoleService {
	/**
	  * 保存权限
	  * @param role
	  * @throws Exception
	  */
	public void addRole(String role) throws Exception;
	/**
	  * 修改权限
	  * @param role
	  * @throws Exception
	  */
	public void modifyRole(String role) throws Exception;
	/**
	  * 删除权限
	  * @param id
	  * @throws Exception
	  */
	public void delRole(String id) throws Exception;
	/**
	  * 查询所有权限
	  * @throws Exception
	  */
	public List<Role> getRole() throws Exception;
	/**
	  * 查询一条权限
	  * @param id
	  * @throws Exception
	  */
	public Role getRole(String id) throws Exception;
}
