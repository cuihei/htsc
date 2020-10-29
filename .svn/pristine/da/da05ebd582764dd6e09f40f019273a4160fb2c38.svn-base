package com.ht.action.system.workflow.process;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.activiti.bpmn.model.Gateway;
import org.activiti.bpmn.model.SequenceFlow;
import org.activiti.bpmn.model.UserTask;
import org.activiti.engine.repository.ProcessDefinition;
import org.apache.commons.lang3.StringUtils;

import com.ht.action.base.BaseAction;
import com.ht.common.util.DataConverter;
import com.ht.common.util.LogHelper;
import com.ht.front.pages.background.organization.employee.EmployeePage;
import com.ht.front.pages.system.workflow.process.FlowChartPage;
import com.ht.persistence.model.background.organization.employee.User;
import com.ht.persistence.model.system.workflow.process.ProcessTaskRelation;
import com.ht.persistence.model.system.workflow.process.ProcessUser;
import com.ht.service.impl.system.workflow.util.BusinessUtil;
import com.ht.service.inter.background.organization.employee.UserService;
import com.ht.service.inter.system.workflow.process.ProcessTaskRelationService;
import com.ht.service.inter.system.workflow.process.ProcessUserService;
import com.ht.service.inter.system.workflow.task.TaskService;
import com.ht.workflow.common.ProcessDefinitionInfo;
import com.ht.workflow.service.IWorkflowService;

public class ProcessAction extends BaseAction {

	@Resource
	IWorkflowService service;

	@Resource
	private ProcessUserService processUserService;

	@Resource(name = "userService")
	UserService userService;
	
	@Resource
	TaskService taskService;
	
	public String init() {
//		List<UserTask> taskList = new ArrayList<UserTask>();
//		List<String> assineeList = new ArrayList<String>();
//		assineeList.add("10231748336720076");
//		UserTask task = service.createUserTask("u_task_01", "任务1", assineeList);
//		taskList.add(task);
//		task = service.createUserTask("u_task_02", "任务2", assineeList);
//		taskList.add(task);
//		task = service.createUserTask("u_task_03", "任务3", assineeList);
//		taskList.add(task);
//		List<SequenceFlow> slist = new ArrayList<SequenceFlow>();
//		SequenceFlow s = service.createSequenceFlow("start", "u_task_01", null);
//		slist.add(s);
//		SequenceFlow s1 = service.createSequenceFlow("u_task_01", "child", null);
//		slist.add(s1);
//		SequenceFlow s2 = service.createSequenceFlow("child", "u_task_02", null);
//		slist.add(s2);
//		SequenceFlow s4 = service.createSequenceFlow("u_task_02", "u_task_03", null);
//		slist.add(s4);
//		SequenceFlow s3 = service.createSequenceFlow("u_task_03", "end", null);
//		slist.add(s3);
//		List<SequenceFlow> slistIn = new ArrayList<SequenceFlow>();
//		slistIn.add(s1);
//		List<SequenceFlow> slistOut = new ArrayList<SequenceFlow>();
//		slistOut.add(s2);
//		ArgumentList inParams = new ArgumentList();
//		inParams.add(CallActivityConstants.SOURCE_PARAM_KEY, "main");
//		inParams.add(CallActivityConstants.TARGET_PARAM_KEY, "in");
//		List<CallActivity> caList = new ArrayList<CallActivity>();
//		CallActivity ca = service.createChildProcess("child", "test2", slistIn, slistOut, inParams,null);
//		caList.add(ca);
//		service.createProcess("test3", "测试流程3带子流程", taskList, slist, null,caList);
//		FrontUtil util = FrontUtil.getInstance();
//		Base root = util.createRoot();
//		Base row = util.createRow(root);
//		util.createGrid(row, "tree");
//		request.setAttribute("html", root.getNode());
//		service.perform("27506", null);

		//待办数据
		String[] ps = {"47829","47836"};
		
		for (int i = 0; i < ps.length; i++)
		{
			service.deleteProcessInst(ps[i]);
		}
		
		//已办数据
		/*String[] hs = {"32406","32414"};
		
		for (int i = 0; i < hs.length; i++)
		{
			service.deleteHiProcessInst(hs[i]);
		}*/
//		List<ProcessDefinitionInfo> list = service.getProcessDefinition();
//		for (int i = 0; i < list.size(); i++) {
//			service.removeProcess(list.get(i).getDeploymentId());
//		}
		// service.start("p11011745264830001", "liukai", null);
//		service.removeProcess("9");
//		service.deploy();
		return SUCCESS;
	}


	/**	
	 * 初始化工作流列表页面
	 * 
	 * @return 应用资源信息页面
	 */
	public String initFlowList() {
		FlowChartPage flowChart = new FlowChartPage();
		request.setAttribute("html", flowChart.getListNode());
		return SUCCESS;
	}

	/**
	 * 获取工作流的列表
	 */
	public void getFlowChart() {
		try {
			List<ProcessDefinitionInfo> result = service.getProcessDefinition();
			writeSuccessResult(result);
		} catch (Exception e) {
			// 写入错误日志
			LogHelper.ERROR.log(e.getMessage(), e);
			// 写失败信息到客户端
			writeFailResult(e.getMessage());
		}
	}
	
	/**
	 * 初始化用户列表页面
	 * 
	 * @return 用户列表信息
	 */
	@SuppressWarnings("unchecked")
	public String distributeUserList() {
		// 获取应用资源标识
		String flowIds = getParam("flowchart");
		// 流程ID
		EmployeePage employee = new EmployeePage();
		// 将获取的列表页面返回到前台页面
		request.setAttribute("html", employee.getFlowListPage());
		request.setAttribute("flowIds", flowIds);
		return SUCCESS;
	}

	/**
	 * 获取流程图
	 */
	@SuppressWarnings("unchecked")
	public String flowLayout() {
		// 获取页面返回的参数
		String path = getParam("path");
		FlowChartPage flowChart = new FlowChartPage();
		// 获取节点字符串返回前台界面
		String appPage = null;
		try {
			appPage = flowChart.getImageEditPage(path);
			// 将获取的节点字符串返回到前台页面
			request.setAttribute("html", appPage);
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(), e);
			// 返回错误信息
			writeFailResult(e.getMessage());
		}
		return SUCCESS;
	}
	
	/**
	 * 获取流程图
	 */
	@SuppressWarnings("unchecked")
	public String flowImage() {
		// 获取页面返回的参数
		String flowId = getParam("id");
		FlowChartPage flowChart = new FlowChartPage();
		// 获取节点字符串返回前台界面
		String appPage = null;
		try {
			service.getDiagramPng(flowId);
			appPage = flowChart.getEditNode(flowId);
			// 将获取的节点字符串返回到前台页面
			request.setAttribute("html", appPage);
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(), e);
			// 返回错误信息
			writeFailResult(e.getMessage());
		}
		return SUCCESS;
	}
	
	
	/**
	 * 任务匹配系统用户
	 */
	public void addUser() {
		// 获取流程定义ID
		String flowIds = getParam("flowIds");
		// 获取选择用户
		String users = getParam("user");
		try {
			List<ProcessDefinition> infos = new ArrayList<ProcessDefinition>();
			// 流程定义专为对象
			List<Map<String,String>> processDef = (List<Map<String,String>>) DataConverter.convertJson2List(flowIds, Map.class);
			for (int i = 0; i < processDef.size(); i++) {
				infos.add(service.getProcessDefinitionId(processDef.get(i).get("id")));
			}
			// 执行插入
			processUserService.addUser(infos, users);
			writeSuccessResult();
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(), e);
			// 返回错误信息
			writeFailResult(e.getMessage());
		}
	}

	/**
	 * 任务匹配系统用户
	 */
	public void selectUser() {
		// 获取任务id
		String flowIds = getParam("flowIds");
		Set<String> result = new HashSet<String>();
		try {
			// 流程定义专为对象
			List<Map<String,String>> processDef = (List<Map<String,String>>) DataConverter.convertJson2List(flowIds, Map.class);
			for (Map<String,String> flowInfo : processDef) {
				if (flowInfo != null && StringUtils.isNotBlank(flowInfo.get("id"))) {
					List<ProcessUser> processUsers = processUserService.getProcessUserByProcessId(flowInfo.get("id"));
					if (processUsers != null && processUsers.size() > 0) {
						for (ProcessUser processUser : processUsers) {
							User user = userService.getUser(processUser.getUserNo());
							result.add(user.getId());
						}
					}
				}
			}
			writeSuccessResult(result);
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(), e);
			// 返回错误信息
			writeFailResult(e.getMessage());
		}
	}
	
	@Resource
	ProcessTaskRelationService processTaskRelationService;
	
	/**
	 * 获取下一个任务定义的流向数量
	 */
	public void getTaskOutGoingFlowCount(){
		String processDefId = getParam("processDefId");
		String taskDefId = getParam("taskDefId");
		try
		{
			// 是否有通告改正至
			String hasNotice = BusinessUtil.getInstance().getProcessTaskNotice(processDefId.split(":")[0], taskDefId);
			Map<String,Object> result = new HashMap<String, Object>();
			result.put("notice", hasNotice);
			// 下一个任务是否为当前的下一步
			List<ProcessTaskRelation> ptList = processTaskRelationService.findByTaskDefId(taskDefId, processDefId);
			// 是否有退回按钮
			String targetId = null;
			List<UserTask> userTasks = service.getUserTasks(processDefId);
			if (userTasks != null)
			{
				for (int i = 0; i < userTasks.size(); i++) {
					UserTask userTask = userTasks.get(i);
					if (userTask.getId().equals(taskDefId)) {
						targetId = userTask.getOutgoingFlows().get(0).getTargetRef();
						break;
					}
				}
				for (int i = 0; i < userTasks.size(); i++)
				{
					UserTask userTask = userTasks.get(i);
					// 当下一步是任务时，表示只有一条线
					if (userTask.getId().equals(targetId))
					{
						result.put("buttonCount", 1);
						// 判断下一任务是否是当前任务的下一步
						if (ptList != null)
						{
							for (int j = 0; j < ptList.size(); j++)
							{
								if (ptList.get(j).getRelationId().equals(targetId))
								{
									result.put("buttonValue", "下一步");
								}
							}
						}
						writeSuccessResult(result);
						return;
					}
				}
			}
			// 当流向线上没有agree参数时 返回1
			Gateway g = service.getGateway(processDefId, targetId);
			List<SequenceFlow> ss = g.getOutgoingFlows();
			if (ss != null)
			{
				for (int i = 0; i < ss.size(); i++)
				{
					SequenceFlow s = ss.get(i);
					String express = s.getConditionExpression();
					if (StringUtils.isNotEmpty(express))
					{
						if (express.contains("agree==false"))
						{
							result.put("buttonCount",2);
							writeSuccessResult(result);
							return;
						}
					}
				}
				result.put("buttonCount", 1);
				writeSuccessResult(result);
				return;
			}
			result.put("buttonCount",2);
			writeSuccessResult(result);
		}
		catch (Exception e)
		{
			writeFailResult(e.getMessage());
		}
	}
	
}
