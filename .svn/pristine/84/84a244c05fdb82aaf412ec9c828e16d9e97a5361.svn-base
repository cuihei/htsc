package com.ht.service.impl.background.authority.role;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.ht.common.util.DataConverter;
import com.ht.common.util.GenerateSequenceUtil;
import com.ht.common.util.LogHelper;
import com.ht.persistence.dao.inter.background.authority.role.RoleDao;
import com.ht.persistence.dao.inter.background.authority.role.RoleUsersDao;
import com.ht.persistence.model.background.authority.role.Role;
import com.ht.persistence.model.background.authority.role.RoleUsers;
import com.ht.service.inter.background.authority.role.RoleService;

/**
 * RoleService接口的实现类
 * @author zqy
 */
public class RoleServiceImpl implements RoleService {
    /**
     * 注入roleDao
     */
	@Resource(name = "roleDao") 
	private RoleDao roleDao;
	
	/**
	 * 注入roleDao
	 */
	@Resource(name = "roleUsersDao") 
	private RoleUsersDao roleUsersDao;
	
	/**
	 * 新增Role
	 * @param roleParam 
	 * @throws Exception
	 */
	@Override
	public void addRole(String roleParam) throws Exception {
		try {
			//将用户String类型转成Role类型
			Role role = (Role) DataConverter.convertJson2Object(roleParam, Role.class);
			//如果id存在，则执行修改操作
			if(role.getId()!=null){
				roleDao.modifyRole(role);
			}else{// 如果id不存在，则执行新增操作
				// 插入角色数据
				String id = GenerateSequenceUtil.generateSequenceNo();
				role.setId(id);
				roleDao.addRole(role);
			}
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
		
	}

	/**
	 * 更新Role
	 * @param roleParam 
	 * @throws Exception
	 */
	@Override
	public void modifyRole(String roleParam) throws Exception {
		try {
			//将用户String类型转成Role类型
			Role role = (Role) DataConverter.convertJson2Object(roleParam, Role.class);
			roleDao.modifyRole(role);
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}
	
	/**
	 * 删除Role
	 * @param id 角色id集合
	 * @throws Exception
	 */
	@Override
	public void delRole(String roleParam) throws Exception {
		try {
			// 创建Role对象
			List<Role> list = (List<Role>) DataConverter.convertJson2List(roleParam,Role.class);
			for (int i = 0; i < list.size(); i++) {
				Role role = list.get(i);
				// 通过roleId获取角色用户关系
				RoleUsers roleUsers = new RoleUsers();
				roleUsers.setRoleId(role.getId());
				List<RoleUsers> ruList = roleUsersDao.getRoleUsers(roleUsers);
				if(ruList.size()>0){
					for (int j = 0; j < ruList.size(); j++) {
						RoleUsers ru = ruList.get(j);
						roleUsersDao.delRoleUsers(ru);
					}
				}
				roleDao.delRole(role);
			}
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}
	
	/**
	 * 获取所有Role
	 * @throws Exception
	 */
	@Override
	public List<Role> getRole() throws Exception {
		try {
			// 获取所有Role
			return roleDao.getRole();
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}
	
	/**
	 * 根据id获取Role
	 * @param id 
	 * @throws Exception
	 */
	@Override
	public Role getRole(String id) throws Exception {
		try {
			//创建Role对象
			Role role = new Role();
			role.setId(id);
			return roleDao.getRole(role);
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}
}