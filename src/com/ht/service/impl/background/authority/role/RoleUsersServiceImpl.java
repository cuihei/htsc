package com.ht.service.impl.background.authority.role;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.ht.common.util.DataConverter;
import com.ht.common.util.GenerateSequenceUtil;
import com.ht.common.util.LogHelper;
import com.ht.persistence.dao.inter.background.authority.role.RoleUsersDao;
import com.ht.persistence.dao.inter.background.organization.employee.UserDao;
import com.ht.persistence.model.background.authority.role.RoleUsers;
import com.ht.persistence.model.background.authority.role.RoleUsersRel;
import com.ht.persistence.model.background.organization.employee.User;
import com.ht.service.inter.background.authority.role.RoleUsersService;

/**
 * RoleUsersService接口的实现类
 * @author zqy
 */
public class RoleUsersServiceImpl implements RoleUsersService {
	/**
     * 注入roleUsersDao
     */
	@Resource(name = "roleUsersDao") 
	private RoleUsersDao roleUsersDao;
	
	/**
	 * 注入userDao
	 */
	@Resource(name = "userDao") 
	private UserDao userDao;
	
	/**
	 * 新增RoleUsers
	 * @param roleUsersParam 
	 * @throws Exception
	 */
	@Override
	public void addRoleUsers(String roleUsersParam) throws Exception {
		try {
			//将用户String类型转成Role类型
			RoleUsers roleUsers = (RoleUsers) DataConverter.convertJson2Object(roleUsersParam, RoleUsers.class);
			roleUsersDao.addRoleUsers(roleUsers);
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
		
	}

	/**
	 * 更新RoleUsers
	 * @param roleUsersParam 
	 * @throws Exception
	 */
	@Override
	public void modifyRoleUsers(String roleUsersParam) throws Exception {
		try {
			//将用户String类型转成Role类型
			RoleUsers roleUsers = (RoleUsers) DataConverter.convertJson2Object(roleUsersParam, RoleUsers.class);
			roleUsersDao.modifyRoleUsers(roleUsers);
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}

	/**
	 * 删除RoleUsers
	 * @param id 
	 * @throws Exception
	 */
	@Override
	public void delRoleUsers(String id) throws Exception {
		try {
			RoleUsers roleUsers = new RoleUsers();
			roleUsers.setId(id);
			roleUsersDao.delRoleUsers(roleUsers);
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}
	
	/**
	 * 获取所有RoleUsers
	 * @throws Exception
	 */
	@Override
	public List<RoleUsers> getRoleUsers() throws Exception {
		try {
			// 获取所有User
			return roleUsersDao.getRoleUsers();
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}
	
	/**
	 *  根据roleId获取RoleUsers
	 * @param roleId 
	 * @throws Exception
	 */
	@Override
	public List<RoleUsers> getRoleUsers(String roleId) throws Exception {
		try {
			RoleUsers roleUsers = new RoleUsers();
			roleUsers.setRoleId(roleId);
			return roleUsersDao.getRoleUsers(roleUsers);
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}

	/**
	  * 根据roleid查询角色用户关系视图
	  * @param roleId
	  * @throws Exception
	  */
	@Override
	public List<RoleUsersRel> getRoleUsersByRoleId(String roleusersParam)
			throws Exception {
		try {
			List<RoleUsersRel> result = new ArrayList<RoleUsersRel>();
			RoleUsers roleUsers = (RoleUsers) DataConverter.convertJson2Object(roleusersParam, RoleUsers.class);
			
			// 首先查询用户表所有数据
			List<User>  usersList = userDao.getUserList();
			// 查出当前角色下的人员
			List<RoleUsers> list = roleUsersDao.getRoleUsers(roleUsers);
			for (int i = 0; i < usersList.size(); i++) {
				RoleUsersRel ru = new RoleUsersRel();
				User u = usersList.get(i);
				ru.setId(u.getId());
				ru.setUserNo(u.getUserNo());
				ru.setUserName(u.getUserName());
				ru.setMail(u.getMail());
				ru.setPhone(u.getPhone());
				for (int j = 0; j < list.size(); j++) {
					if(list.get(j).getUserId().equals(u.getId())){
						ru.setRoleId(list.get(j).getRoleId());
					}
				}
				result.add(ru);
			}
			return result;
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}

	/**
	  * 插入角色与用户之间的关系
	  * @param roleId 角色ID
	  * @param users 选中的人员id
	  * @throws Exception
	  */
	@Override
	public void addRoleUsers(String roleId, String users) throws Exception {
		// 将前台传回来的用户标识转化成list
		List<User> userList = (List<User>) DataConverter.convertJson2List(users, User.class);
		RoleUsers ru = new RoleUsers();
		ru.setRoleId(roleId);
		// 根据roleId取得所有该角色下的人员
		List<RoleUsers> list = roleUsersDao.getRoleUsers(ru);
		// 先将该角色下的人员关系全部删除
		if(list.size()>0){
			for (int j = 0; j < list.size(); j++) {
				roleUsersDao.delRoleUsers(list.get(j));
			}
		}
		// 再根据选中的人员重新插入新的角色和人员的关系
		for (int i = 0; i < userList.size(); i++) {
			User user = userList.get(i);
			// 插入新的关系数据
			RoleUsers roleUsers = new RoleUsers();
			roleUsers.setId(GenerateSequenceUtil.generateSequenceNo());
			roleUsers.setUserId(user.getId());
			roleUsers.setRoleId(roleId);
			roleUsers.setCreator("");
			roleUsers.setCreationDate(new Date());
			roleUsers.setModifier("");
			roleUsers.setModifyDate(new Date());
			roleUsersDao.addRoleUsers(roleUsers);
		}
	}

	/**
	 * 根据userId获取RoleUsers列表
	 * @param RoleUsers roleUsers对象
	 * @return RoleUsers实体
	 */
	@Override
	public List<RoleUsers> getRoleUsersByUserId(String userId)
			throws Exception {
		try {
			RoleUsers roleUsers = new RoleUsers();
			roleUsers.setUserId(userId);
			return roleUsersDao.getRoleUsersByUserId(roleUsers);
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}
}
