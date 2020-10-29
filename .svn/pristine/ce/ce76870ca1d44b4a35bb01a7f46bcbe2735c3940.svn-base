package com.ht.action.system.workflow.task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.activiti.engine.identity.Group;

import com.ht.action.base.BaseAction;
import com.ht.common.util.LogHelper;
import com.ht.common.util.LoginUtil;
import com.ht.common.util.ProblemDateComparator;
import com.ht.exception.DBException;
import com.ht.front.pages.system.workflow.task.ProblemPage;
import com.ht.persistence.dao.inter.background.organization.employee.UserDao;
import com.ht.persistence.dao.inter.system.workflow.process.ProcessFlowDao;
import com.ht.persistence.model.background.organization.employee.User;
import com.ht.persistence.model.complication.formprop.FormProp;
import com.ht.persistence.model.complication.formprop.FormValue;
import com.ht.persistence.model.system.workflow.process.ProcessFlow;
import com.ht.persistence.model.system.workflow.task.Flows;
import com.ht.service.inter.background.organization.employee.UserService;
import com.ht.service.inter.complication.formprop.FormPropService;
import com.ht.service.inter.complication.formprop.FormValueService;
import com.ht.service.inter.system.workflow.task.TaskService;
import com.ht.workflow.service.IWorkflowService;

@SuppressWarnings("serial")
public class ProblemAction extends BaseAction {
	/**
	 * 注入service
	 * */
	@Resource
	IWorkflowService service;
	
	@Resource
	ProcessFlowDao processFlowDao;
	
	@Resource
	UserDao userDao;
	
	@Resource
	TaskService taskService;
	
	// 注入表单值Service
	@Resource
	private FormValueService formValueService;

	// 注入表单属性Service
	@Resource
	private FormPropService formPropService;
	/**
	 * 注入用户service
	 * */
	@Resource(name = "userService")
	UserService userService;

	public String init() throws DBException {
		ProblemPage p = new ProblemPage();
		List<org.activiti.engine.identity.User> qualityUsers = service.findUsersByGroupId("11051509218230003");
		List<org.activiti.engine.identity.User> approvalUsers = service.findUsersByGroupId("11051509305680004");
		
		List<User> qUsers = new ArrayList<User>();
		List<User> aUsers = new ArrayList<User>();
		for (int i = 0; i < qualityUsers.size(); i++)
		{
			User u =userService.getUser(qualityUsers.get(i).getId());
			qUsers.add(u);
		}
		for (int i = 0; i < approvalUsers.size(); i++)
		{
			User u =userService.getUser(approvalUsers.get(i).getId());
			aUsers.add(u);
		}
		String userNo = LoginUtil.getInstance().getLoginNo(request);
		List<User> users = userService.getUserByNo(userNo);
		String userId = null;
		if (users != null)
		{
			userId = users.get(0).getId();
		}
		List<Group> groups = service.getGroupsByUser(userId);
		boolean flag= false;
		if(groups != null){
			if(groups.size()>0){
				for (int i = 0; i < groups.size(); i++) {
					if(groups.get(i).getId().equals("11041451286720004")||groups.get(i).getId().equals("11211642408650154")){
						flag = true;
					}
				}
			}
		}
		//将获取的列表页面返回到前台页面
		request.setAttribute("html", p.getListPage(qUsers,aUsers,flag));
		return SUCCESS;
	}
	
	/**
	 * 初始化显示页面
	 * @throws Exception
	 */
	@SuppressWarnings(
	{ "unchecked", "rawtypes" })
	public void FormValueList() throws Exception
	{
		String formId = "01061813526950035";
		// 当前用户
		List<FormValue> list = new ArrayList<FormValue>();
		try
		{
			// 当前表单属性个数
			List<FormProp> formProp = formPropService.getFormPropByFormId(formId);
			int propNum = formProp.size();
			// 数据
			list = formValueService.getFormValueByFromId(formId);
			// 查询的数据个数
			List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
			if(list.size()>0){
				int length = list.size();
				// 当前循环到第几个
				int index = 0;
				
				Map map = new HashMap();
				for (int i = 0; i < length / propNum; i++)
				{
					index = i + 1;
					String rowFlag = list.get(index * propNum - 1).getRowFlag();
					String processInstId = list.get(index * propNum - 1).getProcessInstId();
					String jsr = list.get(index * propNum - 1).getCreator();
					String jsrq = list.get(index * propNum - 1).getCreationDate().toString();
					jsr = getUserId(jsr);
					List<ProcessFlow>  pfList = processFlowDao.getProcessFlowByProcessInstId(processInstId);
					if(pfList!=null){
						if(pfList.size()>0){
							for (int j = 0; j < pfList.size(); j++) {
								if(pfList.get(j).getProcessInstId().equals(processInstId)){
									if(pfList.get(j).getTaskResult().equals("开启流程")){
										map.put("FKRQ", pfList.get(j).getCreationDate().toString());
									}
								}
							}
						}
					}
					String fkr = (String)service.getHistProcessArgs(processInstId, "user_bhy");
					User user = userService.getUser(fkr);
					if(user!=null){
						map.put("FKR", user.getUserName());
					}
					fkr = (String)service.getHistProcessArgs(processInstId, "user_sdy");
					user = userService.getUser(fkr);
					if(user!=null){
						map.put("SDY", user.getUserName());
					}
					fkr = (String)service.getHistProcessArgs(processInstId, "user_zjy");
					user = userService.getUser(fkr);
					if(user!=null){
						map.put("ZJY", user.getUserName());
					}
					user = userService.getUser(jsr);
					if(user!=null){
						map.put("JSR", user.getUserName());
					}
					map.put("JSRQ", jsrq);
					map.put("rowFlag", rowFlag);
					for (int j = 0; j < list.size(); j++)
					{
						if (list.get(j).getRowFlag().equals(rowFlag))
						{
							// 取值
							map.put(list.get(j).getPropKey(), list.get(j).getPropValue());
						}
					}
					mapList.add(map);
					map = new HashMap();
				}
			}
			if(mapList.size()>0){
				Collections.sort(mapList, new ProblemDateComparator());
			}
			writeSuccessResult(mapList);
		}
		catch (Exception e)
		{
			LogHelper.ERROR.log(e.getMessage(), e);
			// 抛出异常
			throw e;
		}
	}
	
	/** 获取用户ID
	 * @param loginNo 工号
	 * @return 取用户ID
	 */
	private String getUserId(String loginNo)
	{
		// 获取用户ID
		String userId = null;
		User user = new User();
		user.setUserNo(loginNo);
		// 根据userNo获取User
		List<User> users = userDao.getUserByNo(user);
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
