package com.ht.action.background.authority.role;

import java.util.List;

import javax.annotation.Resource;

import com.ht.action.base.BaseAction;
import com.ht.common.util.DataConverter;
import com.ht.common.util.LogHelper;
import com.ht.common.util.LoginUtil;
import com.ht.exception.DBException;
import com.ht.front.pages.background.authority.role.RolePage;
import com.ht.persistence.model.background.authority.role.Role;
import com.ht.persistence.model.background.authority.role.RoleApplicationRel;
import com.ht.persistence.model.background.authority.role.RoleUsers;
import com.ht.persistence.model.background.authority.role.RoleUsersRel;
import com.ht.persistence.model.background.organization.employee.User;
import com.ht.service.inter.background.authority.auth.RoleAppService;
import com.ht.service.inter.background.authority.role.RoleService;
import com.ht.service.inter.background.authority.role.RoleUsersService;
import com.ht.service.inter.background.organization.employee.UserService;

/**
 * 角色类action
 * @author 侯晨
 */
@SuppressWarnings("serial")
public class RoleAction extends BaseAction
{
	/**
	 * 注入roleService
	 */
	@Resource(name = "roleService")
	RoleService roleService;

	/**
	 * 注入roleUsersService
	 */
	@Resource(name = "roleUsersService")
	RoleUsersService roleUsersService;

	/**
	 * 注入roleAuthService
	 */
	@Resource(name = "roleAppService")
	RoleAppService roleAppService;

	@Resource
	UserService userService;

	/**
	 * 初始化角色数据页面，返回成功列表页面
	 * @throws Exception
	 */
	public String init() throws Exception
	{
		RolePage role = RolePage.getInstance();
		// 将获取的列表页面返回到前台页面
		request.setAttribute("html", role.getListPage());
		return SUCCESS;
	}

	/**
	 * 初始化新增和编辑角色数据页面，返回成功页面
	 * @throws Exception
	 */
	public String editInit() throws Exception
	{
		// 接受要修改的人员编号
		String id = getParam("id");
		RolePage role = RolePage.getInstance();
		// 将获取的新增、编辑页面返回到前台页面
		request.setAttribute("html", role.getEditPage(roleService, id));
		return SUCCESS;
	}

	/**
	 * 初始化角色人员数据页面，返回成功页面
	 * @throws Exception
	 */
	public String allocUserIndex() throws Exception
	{
		// 接收要分配人员的组织机构orgId
		String id = getParam("id");
		Role r = roleService.getRole(id);
		String roleName = r.getRoleName();
		RolePage role = RolePage.getInstance();
		// 将获取的分配人员的页面返回到前台页面
		request.setAttribute("html", role.getAllocUserPage(id, roleName));
		return SUCCESS;
	}

	/**
	 * 初始化角色人员数据页面，返回成功页面
	 * @throws Exception
	 */
	public String allocAppIndex() throws Exception
	{
		// 接收要分配人员的组织机构orgId
		String id = getParam("id");
		Role r = roleService.getRole(id);
		String roleName = r.getRoleName();
		RolePage role = RolePage.getInstance();
		// 将获取的分配人员的页面返回到前台页面
		request.setAttribute("html", role.getAllocAppPage(id, roleName));
		return SUCCESS;
	}

	/**
	 * 新增Role数据
	 */
	public void addRole()
	{
		try
		{
			// 获取前台传入参数
			String role = getParam("role");
			// 执行保存操作
			roleService.addRole(role);
			// 返回客户端消息
			writeSuccessResult(role);
		}
		catch (Exception e)
		{
			// 写入错误日志
			LogHelper.ERROR.log(e.getMessage(), e);
			// 返回客户端错误消息
			writeFailResult(e.getMessage());
		}
	}

	/**
	 * 更新Role数据
	 */
	public void modifyRole()
	{
		// 获取Role修改数据
		String role = getParam("role");
	
		String roleParam = DataConverter.convertObject2Json(role);
		try
		{
			// 执行修改操作
			roleService.modifyRole(role);
			// 返回客户端消息
			writeSuccessResult(roleParam);
		}
		catch (Exception e)
		{
			// 写入错误日志
			LogHelper.ERROR.log(e.getMessage(), e);
			// 返回客户端错误消息
			writeFailResult(e.getMessage());
		}
	}

	/**
	 * 删除Role数据
	 */
	public void removeRole()
	{
		// 获取角色标识
		String role = getParam("role");
		try
		{
			// 执行删除操作
			roleService.delRole(role);
			// 返回客户端消息
			writeSuccessResult();
		}
		catch (Exception e)
		{
			// 写入错误日志
			LogHelper.ERROR.log(e.getMessage(), e);
			// 返回客户端错误消息
			writeFailResult(e.getMessage());
		}
	}

	/**
	 * 获取Role列表
	 */
	public void getRole()
	{
		try
		{
			// 执行查询操作
			List<Role> roleList = roleService.getRole();
			// 返回客户端消息
			writeSuccessResult(roleList);
		}
		catch (Exception e)
		{
			// 写入错误日志
			LogHelper.ERROR.log(e.getMessage(), e);
			// 返回客户端错误消息
			writeFailResult(e.getMessage());
		}
	}

	/**
	 * 根据id获取Role
	 */
	public void getRoleByKey()
	{
		// 获取Role标识
		String id = getParam("id");
		try
		{
			// 执行查询操作
			Role role = roleService.getRole(id);
			// 返回客户端消息
			writeSuccessResult(role);
		}
		catch (Exception e)
		{
			// 写入错误日志
			LogHelper.ERROR.log(e.getMessage(), e);
			// 写失败信息到客户端
			writeFailResult(e.getMessage());
		}
	}

	/**
	 * 根据角色id获取角色里的用户
	 */
	public void getRoleUsersByroleId()
	{
		try
		{
			String roleId = getParam("roleId");
			List<RoleUsers> result = roleUsersService.getRoleUsers(roleId);
			writeSuccessResult(result);
		}
		catch (Exception e)
		{
			// 写入错误日志
			LogHelper.ERROR.log(e.getMessage(), e);
			// 写失败信息到客户端
			writeFailResult(e.getMessage());
		}
	}

	/**
	 * 根据角色id获取角色里的权限
	 */
	public void getRoleUsers()
	{
		// 获取Role标识
		String rul = getParam("role");
		try
		{
			// 获取某角色下人员及公共部门里的人员列表
			List<RoleUsersRel> list = roleUsersService.getRoleUsersByRoleId(rul);
			// 返回客户端消息
			writeSuccessResult(list);
		}
		catch (Exception e)
		{
			// 写入错误日志
			LogHelper.ERROR.log(e.getMessage(), e);
			// 写失败信息到客户端
			writeFailResult(e.getMessage());
		}
	}

	/**
	 * 提交某角色下人员关系
	 * @param orgId 组织机构标识
	 * @param users 用户标识集合
	 * @throws Exception
	 */
	public void addRoleUsers()
	{
		// 获取角色标识
		String roleId = getParam("roleId");
		// 获取选中的用户标示
		String users = getParam("users");
		try
		{
			// 插入角色与人员关系数据
			roleUsersService.addRoleUsers(roleId, users);
			// 返回客户端消息
			writeSuccessResult();
		}
		catch (Exception e)
		{
			// 写入错误日志
			LogHelper.ERROR.log(e.getMessage(), e);
			// 写失败信息到客户端
			writeFailResult(e.getMessage());
		}
	}

	/**
	 * 提交某角色下拥有的资源
	 * @param orgId 组织机构标识
	 * @param users 用户标识集合
	 * @throws Exception
	 */
	public void addRoleApps()
	{
		// 获取角色标识
		String roleId = getParam("roleId");
		// 获取选中的资源标示
		String apps = getParam("app");
		try
		{
			// 插入角色与资源关系数据
			roleAppService.addRoleApps(roleId, apps);
			// 更新indexAction里面的map
			// 得到roleId里面的所有人
			List<RoleUsers> users = roleUsersService.getRoleUsers(roleId);
			if(users!=null){
				if(users.size()>0){
					for (int i = 0; i < users.size(); i++)
					{
						String userId  = users.get(i).getUserId();
						User user = userService.getUser(userId);
						if(user!=null){
							// 根据用户id得到用户信息
							LoginUtil.getInstance().removeUserApplication(user.getUserNo());
						}
					}
				}
			}
			// 返回客户端消息
			writeSuccessResult();
		}
		catch (Exception e)
		{
			// 写失败信息到客户端
			writeFailResult(e.getMessage());
		}
	}

	/**
	 * 根据角色id获取角色里的资源
	 */
	public void getAppsByRoleId()
	{
		// 获取Role标识
		String role = getParam("role");
		try
		{
			// 执行查询操作
			List<RoleApplicationRel> result = roleAppService.getRoleAppsByRoleId(role);
			// 返回客户端消息
			writeSuccessResult(result);
		}
		catch (Exception e)
		{
			// 写入错误日志
			LogHelper.ERROR.log(e.getMessage(), e);
			// 写失败信息到客户端
			writeFailResult(e.getMessage());
		}
	}
}