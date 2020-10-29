package com.ht.action.system.workflow.task;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.activiti.engine.identity.Group;

import com.ht.action.base.BaseAction;
import com.ht.common.util.DataConverter;
import com.ht.common.util.DateComparator;
import com.ht.common.util.LoginUtil;
import com.ht.front.pages.system.workflow.task.RuTaskPage;
import com.ht.persistence.model.background.authority.role.RoleUsers;
import com.ht.persistence.model.background.organization.employee.User;
import com.ht.persistence.model.system.workflow.task.HiTask;
import com.ht.service.impl.system.workflow.task.ProcessTypeEnum;
import com.ht.service.impl.system.workflow.util.BusinessUtil;
import com.ht.service.inter.background.authority.role.RoleUsersService;
import com.ht.service.inter.background.organization.employee.UserService;
import com.ht.service.inter.system.workflow.process.ProcessFlowService;
import com.ht.service.inter.system.workflow.task.TaskService;
import com.ht.workflow.service.IWorkflowService;

/**
 * 经办任务访问类
 * @author 王有为
 */
public class HiTaskAction extends BaseAction{
	
	@Resource
	IWorkflowService service;
	
	/**
	 * 注入roleUsersService
	 */
	@Resource(name = "roleUsersService")
	RoleUsersService roleUsersService;

	
	@Resource
	UserService userService;
	
	@Resource
	TaskService taskService;
	
	@Resource
	ProcessFlowService processFlowService;
	
	/**
	 * 页面初始化
	 */
	public String init()
	{
		//科长角色集合
		List<String> roleList = new ArrayList<String>();
		roleList.add("11031901469110099");
		roleList.add("1103190213720100");
		roleList.add("11031904004130102");
		roleList.add("11031905468290105");	
		
		String processDefKey = getParam("processDefKey");
		String processInstId = getParam("processInstId");
		String parentProcessInstId = getParam("parentProcessInstId");
		RuTaskPage page = new RuTaskPage();
		// 当前登录用户
		String LoginUser = LoginUtil.getInstance().getLoginNo(request);
		List<User> loginUsers;
		try
		{
			loginUsers = userService.getUserByNo(LoginUser);
			// 定义用户集合
			List<User> users = new ArrayList<User>();
			String userId = null;
			if (loginUsers != null)
			{
				if (loginUsers.size() > 0)
				{
					userId = loginUsers.get(0).getId();
				}
			}
			// 查询当前用户的所属流程组
			List<Group> groups = service.getGroupsByUser(userId);
			if (groups != null)
			{
				for (int i = 0; i < groups.size(); i++)
				{
					// 获取当前组成员
					List<org.activiti.engine.identity.User> processUsers = service.getUsersByGroup(groups.get(i).getId());
					if (processUsers != null)
					{
						for (int j = 0; j < processUsers.size(); j++)
						{
							if (!processUsers.get(j).getId().equals(userId))
							{
								User user = new User();
								user.setId(processUsers.get(j).getId());
								user.setUserName(processUsers.get(j).getFirstName());
								users.add(user);
							}
						}
					}
				}
			}
			// 判断用户是否拥有科长角色
			boolean flag = false;
			List<RoleUsers> role = roleUsersService.getRoleUsersByUserId(userId);
			if (role != null)
			{
				if (role.size() > 0)
				{
					for (int i = 0; i < role.size(); i++) {
						String roleId = role.get(i).getRoleId();
						if(roleList.contains(roleId)){
							flag = true;
						}
					}
				}
				
			}
			
			// 获取配置显示列
			List<Map<String, String>> columns = BusinessUtil.getInstance().getBusinessColumns(processDefKey);
			
			request.setAttribute("html", page.getRuTaskPage(processDefKey, users, flag, false,false));
			request.setAttribute("processDefKey", processDefKey);
			request.setAttribute("processInstId", processInstId);
			request.setAttribute("parentProcessInstId", parentProcessInstId);
			request.setAttribute("from", 0);
			request.setAttribute("columns", DataConverter.convertObject2Json(columns));
			return SUCCESS;
		}
		catch (Exception e)
		{
			return ERROR;
		}
	}
	
	/**
	 * 获取经办
	 */
	public void getHiTask(){
		String processDefKey = getParam("processDefKey");// 当前登录用户
		String processInstId = getParam("processInstId");// 流程Id
		String parentProcessInstId = getParam("parentProcessInstId");// 父流程ID
		System.out.println(parentProcessInstId);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		String year = sdf.format(new Date());
		//根据父流程ID获取流程ID
		List<HiTask> taskList = taskService.getTaskByParentProcessInstId(parentProcessInstId);
		List<String> ids = new ArrayList<>();
		if(taskList != null){
			if(taskList.size()>0){
				for (int i = 0; i < taskList.size(); i++) {
					String pInstId = taskList.get(i).getProcessInstId();
					if(!ids.contains(pInstId)){
						ids.add(pInstId);
					}
				}
			}
		}
		String LoginUser = LoginUtil.getInstance().getLoginNo(request);
		List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> listData = new ArrayList<Map<String,Object>>();
		try {
			switch (processDefKey)
			{
			case "SEA_MAP_COMPILATION_SOURCE_DATA":
				if(ids != null){
					if(ids.size()>0){
						for (int i = 0; i < ids.size(); i++) {
							listData = taskService.getHiTask(LoginUser, ProcessTypeEnum.valueOf(processDefKey),ids.get(i),year);
							if (listData != null)
							{
								result.addAll(listData);
							}
							listData = taskService.getHiTask(LoginUser, ProcessTypeEnum.SEA_MAP_COMPILATION_SOURCE_DATA_PLAN,ids.get(i),year);
							if (listData != null)
							{
								result.addAll(listData);
							}
						}
					}
				}
				break;
			case "SEA_MAP_COMPILATION_PAPER":
				if(ids != null){
					if(ids.size()>0){
						for (int i = 0; i < ids.size(); i++) {
							listData = taskService.getHiTask(LoginUser, ProcessTypeEnum.valueOf(processDefKey),ids.get(i),year);
							if (listData != null)
							{
								result.addAll(listData);
							}
							listData = taskService.getHiTask(LoginUser, ProcessTypeEnum.SEA_MAP_COMPILATION_PAPER_PLAN,ids.get(i),year);
							if (listData != null)
							{
								result.addAll(listData);
							}
						}
					}
				}
				break;
			case "SEA_MAP_COMPILATION_ELECTRONIC":
				if(ids != null){
					if(ids.size()>0){
						for (int i = 0; i < ids.size(); i++) {
							listData = taskService.getHiTask(LoginUser, ProcessTypeEnum.valueOf(processDefKey),ids.get(i),year);
							if (listData != null)
							{
								result.addAll(listData);
							}
							listData = taskService.getHiTask(LoginUser, ProcessTypeEnum.SEA_MAP_COMPILATION_ELECTRONIC_PLAN,ids.get(i),year);
							if (listData != null)
							{
								result.addAll(listData);
							}
						}
					}
				}
				break;
			default:
				result = taskService.getHiTask(LoginUser, ProcessTypeEnum.valueOf(processDefKey),processInstId,year);
				break;
			}
			if(result.size()>0){
				Collections.sort(result, new DateComparator());
			}
			writeSuccessResult(result);
		} catch (Exception e) {
			writeFailResult(e.getMessage());
		}
	}
	/**
	 * 获取经办
	 */
	public void getHiTaskGroupByProcessInstId(){
		String processDefKey = getParam("processDefKey");// 当前登录用户
		String year = getParam("year");
		String LoginUser = getParam("userNo") == null?LoginUtil.getInstance().getLoginNo(request): getParam("userNo");
		List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
		try {
			switch (processDefKey)
			{
			case "SEA_MAP_COMPILATION_SOURCE_DATA":
				List<Map<String,Object>> listData = taskService.getHiTaskGroupByProcessInstId(LoginUser, ProcessTypeEnum.valueOf(processDefKey),year);
				if (listData != null)
				{
					result.addAll(listData);
				}
				listData = taskService.getHiTaskGroupByProcessInstId(LoginUser, ProcessTypeEnum.SEA_MAP_COMPILATION_SOURCE_DATA_PLAN,year);
				if (listData != null)
				{
					result.addAll(listData);
				}
				if(result!= null){
					if(result.size()>0){
						// 去重
						for (int i = 0; i < result.size(); i++) {
							  Map<String,Object> m1 = result.get(i);
						      for (int j = i+1; j < result.size(); j++) {
						    	  String m2 = result.get(j).get("parentProcessInstId").toString();
						    	  if(m1.get("parentProcessInstId").toString().equals(m2)){
						    		  result.remove(j);
						    		  continue;
						    	  }
						      }
						}
					}
				}
				break;
			case "SEA_MAP_COMPILATION_PAPER":
				listData = new ArrayList<Map<String,Object>>();
				listData = taskService.getHiTaskGroupByProcessInstId(LoginUser, ProcessTypeEnum.valueOf(processDefKey),year);
				if (listData != null)
				{
					result.addAll(listData);
				}
				listData = taskService.getHiTaskGroupByProcessInstId(LoginUser, ProcessTypeEnum.SEA_MAP_COMPILATION_PAPER_PLAN,year);
				if (listData != null)
				{
					result.addAll(listData);
				}
				if(result!= null){
					if(result.size()>0){
						// 去重
						for (int i = 0; i < result.size(); i++) {
							  Map<String,Object> m1 = result.get(i);
						      for (int j = i+1; j < result.size(); j++) {
						    	  String m2 = result.get(j).get("parentProcessInstId").toString();
						    	  if(m1.get("parentProcessInstId").toString().equals(m2)){
						    		  result.remove(j);
						    		  continue;
						    	  }
						      }
						}
					}
				}
				break;
			case "SEA_MAP_COMPILATION_ELECTRONIC":
				listData = new ArrayList<Map<String,Object>>();
				listData = taskService.getHiTaskGroupByProcessInstId(LoginUser, ProcessTypeEnum.valueOf(processDefKey),year);
				if (listData != null)
				{
					result.addAll(listData);
				}
				listData = taskService.getHiTaskGroupByProcessInstId(LoginUser, ProcessTypeEnum.SEA_MAP_COMPILATION_ELECTRONIC_PLAN,year);
				if (listData != null)
				{
					result.addAll(listData);
				}
				if(result!= null){
					if(result.size()>0){
						// 去重
						for (int i = 0; i < result.size(); i++) {
							  Map<String,Object> m1 = result.get(i);
						      for (int j = i+1; j < result.size(); j++) {
						    	  String m2 = result.get(j).get("parentProcessInstId").toString();
						    	  if(m1.get("parentProcessInstId").toString().equals(m2)){
						    		  result.remove(j);
						    		  continue;
						    	  }
						      }
						}
					}
				}
				break;
			default:
				result = taskService.getHiTaskGroupByProcessInstId(LoginUser, ProcessTypeEnum.valueOf(processDefKey),year);
				break;
			}
			if(result.size()>0){
				Collections.sort(result, new DateComparator());
				
				// 查询年份
				Iterator<Map<String,Object>> iter = result.iterator();
		        while (iter.hasNext()) {
		        	Map<String,Object> item = iter.next();
		        	String taskbookNo = item.get("taskbookNo").toString();
		            if (!taskbookNo.contains(year)) {
		                iter.remove();
		            }
		        }
			}
			writeSuccessResult(result);
		} catch (Exception e) {
			writeFailResult(e.getMessage());
		}
	}
}
