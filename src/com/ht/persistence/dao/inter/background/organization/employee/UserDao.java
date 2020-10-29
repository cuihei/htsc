package com.ht.persistence.dao.inter.background.organization.employee;

import java.util.List;

import com.ht.persistence.model.background.organization.employee.User;
import com.ht.persistence.model.background.organization.employee.UserOrgView;

/**
 * userDao 人员信息Dao
 * @author 刘凯
 */
public interface UserDao {
	
	/**
	 * 增加一个User
	 * @param user实体
	 */
	public void addUser(User user);

	/**
	 * 更新一个User
	 * @param user实体
	 */
	public void modifyUser(User user);
	
	/**
	 * 更新一个User
	 * @param user实体
	 */
	public void modifyPassword(User user);

	/**
	 * 删除User
	 * @param user实体
	 */
	public void delUser(User user);
	
	/**
	 * 获取所有User
	 * @return 员工列表
	 */
	public List<User> getUserList();

	public List<User> getUserByUserIds(List<String> ids);
	
	/**
	 * 获取一条User
	 * @param user实体 
	 * @return user实体
	 */
	public User getUser(User user);
	
	/**
	 * 通过userNo登陆账号获取用户信息
	 * @param user实体 
	 * @return 员工列表
	 */
	public List<User> getUserByNo(User user);
	
	/**
	 * 通过userNo登陆账号和密码获取用户信息
	 * @param user实体 
	 * @return 员工列表
	 */
	public List<User> getUserByNoAndPassword(User user);
	
	/**
	 * 获取所有UserOrgView
	 * @return 员工列表
	 */
	public List<UserOrgView> getUserOrgViewList();
	
	/**
	 * 根据orgId查询用户
	 * @param user String类型的员工对象
	 * @return List<UserOrgView> 用户数据列表
	 */
	public List<UserOrgView> getUserOrgViewByOrgId(UserOrgView user);
}
