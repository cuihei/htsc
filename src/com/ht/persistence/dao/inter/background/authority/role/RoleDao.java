package com.ht.persistence.dao.inter.background.authority.role;

import java.util.List;

import com.ht.persistence.model.background.authority.role.Role;

/**
 * RoleDao接口
 * @author zqy
 */
public interface RoleDao {
	/**
	 * 增加一个Role对象
	 * @param Role Role实体
	 */
	public void addRole(Role role);

	/**
	 * 更新一个Role
	 * @param Role role实体
	 */
	public void modifyRole(Role role);

	/**
	 * 删除Role 相关
	 * @param Role role对象
	 */
	public void delRole(Role role);
	
	/**
	 * 获取所有Role
	 * @return List<Role>列表
	 */
	public List<Role> getRole();

	/**
	 * 获取一条Role
	 * @param Role role对象
	 * @return Role对象
	 */
	public Role getRole(Role role);
}
