package com.ht.action.system.workflow.process;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;

import com.ht.action.base.BaseAction;
import com.ht.common.util.DataConverter;
import com.ht.common.util.LogHelper;
import com.ht.front.pages.system.workflow.process.AuditRolePage;
import com.ht.persistence.model.system.workflow.process.ProcessDelegate;
import com.ht.service.impl.background.dicdata.constants.BaseDataConstants;
import com.ht.service.inter.background.organization.employee.UserService;
import com.ht.service.inter.system.workflow.process.ProcessDelegateService;
import com.ht.workflow.service.IWorkflowService;

/**
 * 审批角色配置操作类
 * @author yx
 * @date 2016-10-22 12:50:58
 */
@SuppressWarnings("serial")
public class AuditRoleAction extends BaseAction
{
	/**
	 * 注入service
	 */
	@Resource
	IWorkflowService service;

	@Resource
	UserService userService;

	/**
	 * 初始化审批角色配置
	 * @return
	 */
	public String init()
	{
		AuditRolePage application = new AuditRolePage();
		request.setAttribute("html", application.getListNode());
		return SUCCESS;
	}

	/**
	 * 获取列表数据
	 */
	public void getList()
	{
		try
		{
			// 执行查询操作
			List<Group> userList = service.findGroups();
			// 返回客户端消息
			writeSuccessResult(userList);
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
	 * 初始化编辑数据页面，返回成功页面
	 */
	public String editInit()
	{
		// 接受要修改的人员编号
		String id = getParam("id");
		AuditRolePage application = new AuditRolePage();
		// 将获取的新增、编辑页面返回到前台页面
		request.setAttribute("html", application.getEditPage(service, id));
		return SUCCESS;
	}

	/**
	 * 初始化分配用户数据页面，返回成功页面
	 */
	public String allotAudit()
	{
		// 接受要修改的人员编号
		String id = getParam("id");
		String groupName = "";
		List<Group> groups = service.getGroupById(id);
		if (groups.size() >= 1)
		{
			Group group = groups.get(0);
			groupName = group.getName();
		}
		AuditRolePage application = new AuditRolePage();
		// 将获取的新增、编辑页面返回到前台页面
		request.setAttribute("html", application.getUserListNode(id, groupName));
		return SUCCESS;
	}

	/**
	 * 获取列表数据
	 */
	public void getUserList()
	{
		try
		{
			// 接受要修改的人员编号
			String id = getParam("GroupId");
			// 同步创建用户
			List<com.ht.persistence.model.background.organization.employee.User> userLists = userService.getUserList();
			for (int i = 0; i < userLists.size(); i++)
			{
				String uid = userLists.get(i).getId();
				String username = userLists.get(i).getUserName();
				service.createUser(uid, username);
			}
			// 执行查询操作 - 得到当前用户
			List<User> alluserList = service.findAllUser();
			// 执行查询操作 - 得到当前组内用户
			List<User> userList = service.findUsersByGroupId(id);
			// 合并
			Map<String, Object> map = new HashMap<String, Object>();
			if(alluserList != null){
				if(alluserList.size()>0){
					for (Iterator it = alluserList.iterator(); it.hasNext();) {
						User u = (User)it.next();
						String userId = u.getId();
						com.ht.persistence.model.background.organization.employee.User user = userService.getUser(userId);
						if(user != null){
							u.setFirstName(user.getUserName());
							u.setEmail(user.getUserNo());
						}else{
							it.remove();
						}
					}
					
				}
			}
			if(userList != null){
				if(userList.size()>0){
					for (int i = 0; i < userList.size(); i++) {
						User u = userList.get(i);
						String userId = u.getId();
						com.ht.persistence.model.background.organization.employee.User user = userService.getUser(userId);
						if(user != null){
							u.setEmail(user.getUserNo());
							u.setFirstName(user.getUserName());
						}
					}
				}
			}
			map.put("all", alluserList);
			map.put("select", userList);
			
			// 返回客户端消息
			writeSuccessResult(map);
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
	 * 新增User数据
	 * @param user String类型的员工对象
	 * @throws Exception
	 */
	public void addUser()
	{
		// 获取前台传入参数
		String user = getParam("name");
		service.createGroup(user);
		// 返回客户端消息
		writeSuccessResult(user);
	}

	/**
	 * 删除数据
	 */
	public void removeBaseData()
	{
		try
		{
			// 获取基础数据标识
			String groupId = getParam("auditrole");
			List<Map<String, String>> list = service.deleteGroup(groupId);
			writeSuccessResult(list);
		}
		catch (Exception e)
		{
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(), e);
			// 返回错误信息
			writeFailResult(e.getMessage());
		}
	}

	/**
	 * 分配用户
	 */
	public void BindUser()
	{
		try
		{
			String str = getParam("grouanduser");
			List<String> uid = new ArrayList<String>(); // 删除用存放UID
			List<String> saveUid = new ArrayList<String>();// 新增用存放UID
			String groupId = "";
			List<Map<String, String>> groupList = (List<Map<String, String>>) DataConverter.convertJson2List(str, Map.class);

			for (int i = 0; i < groupList.size(); i++)
			{
				groupId = groupList.get(i).get("groupid");
				if (i == 0)
				{
					// 第一次循环重置当前组
					List<User> list = service.findUsersByGroupId(groupId);
					for (int j = 0; j < list.size(); j++)
					{
						uid.add(list.get(j).getId());
					}
					service.deleteUsersFromGroup(groupId, uid);
				}
				saveUid.add(groupList.get(i).get("uid"));
			}

			if (!groupId.equals(""))
			{
				service.addUsersToGroup(groupId, saveUid);
			}

			writeSuccessResult();
		}
		catch (Exception e)
		{
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(), e);
			// 返回错误信息
			writeFailResult(e.getMessage());
		}
	}
	
	@Resource
	ProcessDelegateService processDelegateService;
	
	/**
	 * 分配委托人页面
	 * @return
	 */
	public String delegateUserInit() {
		String groupId = getParam("groupId");
		String groupName = getParam("groupName");
		try
		{
			groupName = new String(groupName.getBytes("ISO8859-1"), BaseDataConstants.BASE_CODEING);
		}
		catch (UnsupportedEncodingException e)
		{
			return SUCCESS;
		}
		AuditRolePage page = new AuditRolePage();
		String html = page.getDelegateUserPage(groupId,groupName);
		request.setAttribute("html", html);
		return SUCCESS;
	}
	
	/**
	 * 获取分配委托人数据
	 */
	public void getDelegateUserList() {
		try {
			// 组编号
			String groupId = getParam("groupId");
			// 执行查询操作 - 得到所有用户
			List<User> alluserList = service.findAllUser();
			// 执行查询操作 - 得到当前组内用户
			List<ProcessDelegate> userList = processDelegateService.getListByGroupId(groupId);
			// 合并
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("all", alluserList);
			map.put("select", userList);
			// 返回客户端消息
		    writeSuccessResult(map);
		} catch (Exception e) {
			// 写入错误日志
			LogHelper.ERROR.log(e.getMessage(), e);
			// 返回客户端错误消息
			writeFailResult(e.getMessage());
		}
	}
	
	/**
	 * 分配委托用户
	 */
	public void allotDelegateUser() {
		// 组ID
		String groupId = getParam("groupId");
		// 分配用户集合
		String userIds = getParam("userIds");
		List<String> userIdList = (List<String>) DataConverter.convertJson2List(userIds, String.class);
		try
		{
			processDelegateService.addAll(groupId,userIdList);
			writeSuccessResult();
		}
		catch (Exception e)
		{
			writeFailResult(e.getMessage());
		}
	}
}
