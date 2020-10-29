package com.ht.action.system.workflow.task;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.activiti.engine.identity.Group;

import com.ht.action.base.BaseAction;
import com.ht.common.util.LoginUtil;
import com.ht.exception.DBException;
import com.ht.front.pages.system.workflow.task.RuTaskPage;
import com.ht.persistence.model.background.organization.employee.User;
import com.ht.persistence.model.system.workflow.process.ProcessDelegate;
import com.ht.persistence.model.system.workflow.task.RuTask;
import com.ht.service.inter.background.organization.employee.UserService;
import com.ht.service.inter.system.workflow.process.ProcessDelegateService;
import com.ht.service.inter.system.workflow.task.TaskService;
import com.ht.workflow.service.WorkflowService;

public class DelegateTaskAction extends BaseAction
{
	
	@Resource
	WorkflowService service;
	
	@Resource
	TaskService taskService;
	
	@Resource
	UserService userService;
	
	@Resource
	ProcessDelegateService processDelegateService;
	
	/**
	 * 页面初始化
	 */
	public String init()
	{
		try
		{
			String LoginUser = LoginUtil.getInstance().getLoginNo(request);
			String userId = getUserId(LoginUser);
			// 获取所属组
			List<Group> groups = service.getGroupsByUser(userId);
			// 定义当前用户分配的委托人集合
			List<String> userIds = new ArrayList<String>();
			if (groups != null)
			{
				for (int i = 0; i < groups.size(); i++)
				{
					// 获取当前用户分配的委托人
					List<ProcessDelegate> delegateList = processDelegateService.getListByGroupId(groups.get(i).getId());
					if (delegateList != null)
					{
						for (int j = 0; j < delegateList.size(); j++)
						{
							userIds.add(delegateList.get(j).getUserId());
						}
					}
				}
			}
			RuTaskPage page = new RuTaskPage();
			List<User> users = userService.getUserByUserIds(userIds);
			request.setAttribute("html", page.getDelegatePage(users));
			return SUCCESS;
		}
		catch (Exception e)
		{
			return SUCCESS;
		}
		
	}
	
	public void getTaskList(){
		// 当前登录用户
		String LoginUser = LoginUtil.getInstance().getLoginNo(request);
		try
		{
			List<Map<String, Object>> tasks = taskService.getDelegateTask(LoginUser);
			writeSuccessResult(tasks);
		}
		catch (Exception e)
		{
			writeFailResult(e.getMessage());
		}
	}
	
	/**
	 * 获取用户ID
	 * @param loginNo 工号
	 * @return 取用户ID
	 * @throws DBException 
	 */
	private String getUserId(String loginNo) throws DBException
	{
		// 获取用户ID
		String userId = null;
		// 根据userNo获取User
		List<User> users = userService.getUserByNo(loginNo);
		if (users != null)
		{
			if (users.size() > 0)
			{
				userId = users.get(0).getId();
			}
		}
		return userId;
	}
}
