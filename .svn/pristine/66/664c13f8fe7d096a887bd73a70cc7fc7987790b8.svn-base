package com.ht.persistence.dao.impl.background.authority.role;

import java.util.List;

import com.ht.persistence.dao.impl.base.BaseDaoImpl;

import com.ht.persistence.dao.inter.background.authority.role.RoleUsersDao;
import com.ht.persistence.model.background.authority.role.RoleUsers;

/**
 * RoleUsers 接口的实现类
 * @author liukai 
 *
 */
public class RoleUsersDaoImpl extends BaseDaoImpl implements RoleUsersDao{

	/**
	 * 增加一个RoleUsers
	 * @param RoleApp RoleUsers实体
	 */
	@Override
	public void addRoleUsers(RoleUsers roleUsers) {
		this.save(roleUsers);
	}

	/**
	 * 更新一个RoleUsers
	 * @param RoleApp RoleUsers实体
	 */
	@Override
	public void modifyRoleUsers(RoleUsers roleUsers) {
		this.update(roleUsers);
	}

	/**
	 * 删除RoleUsers
	 * @param RoleUsers RoleUsers对象
	 */
	@Override
	public void delRoleUsers(RoleUsers roleUsers) {
		this.delete(roleUsers);
	}

	/**
	 * 获取所有RoleUsers
	 * @return List<RoleUsers>列表
	 */
	@Override
	public List<RoleUsers> getRoleUsers() {
		try {
			@SuppressWarnings("unchecked")
			List<RoleUsers> result = this.findByNamedQuery("getRoleUsers");
			return result;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	/**
	 * 获取RoleUsers
	 * @param RoleUsers RoleUsers对象
	 * @return User实体
	 */
	@Override
	public List<RoleUsers> getRoleUsers(RoleUsers roleUsers) {
		try {
			@SuppressWarnings("unchecked")
			List<RoleUsers> result = this.findByNamedQueryAndNamedParam("getUsersByRoleId", "roleId", roleUsers.getRoleId());
			return result;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	/**
	 * 获取RoleUsers列表
	 * @param RoleUsers RoleUsers对象
	 * @return User实体
	 */
	@Override
	public List<RoleUsers> getRoleUsersByUserId(RoleUsers roleUsers) {
		try {
			@SuppressWarnings("unchecked")
			List<RoleUsers> result = this.findByNamedQueryAndNamedParam("getRoleUsersByUserId", "userId", roleUsers.getUserId());
			return result;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
}
