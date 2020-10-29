package com.ht.action.system.workflow.task;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.activiti.engine.identity.Group;
import org.activiti.engine.repository.ProcessDefinition;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Entity;

import com.ht.action.base.BaseAction;
import com.ht.common.util.DataConverter;
import com.ht.common.util.DateComparator;
import com.ht.common.util.FileUtil;
import com.ht.common.util.LogHelper;
import com.ht.common.util.LoginUtil;
import com.ht.common.util.MSWordTool;
import com.ht.exception.DBException;
import com.ht.front.pages.system.workflow.process.FlowChartPage;
import com.ht.front.pages.system.workflow.task.RuTaskPage;
import com.ht.persistence.model.background.authority.role.RoleUsers;
import com.ht.persistence.model.background.organization.employee.User;
import com.ht.persistence.model.complication.form.Form;
import com.ht.persistence.model.drawtask.taskbook.create.CreateTask;
import com.ht.persistence.model.system.notice.Notice;
import com.ht.persistence.model.system.workflow.process.ProcessFlow;
import com.ht.persistence.model.system.workflow.process.ProcessForm;
import com.ht.persistence.model.system.workflow.publish.VProcessDetail;
import com.ht.persistence.model.system.workflow.task.Flows;
import com.ht.persistence.model.system.workflow.task.HiTask;
import com.ht.persistence.model.system.workflow.task.RuTask;
import com.ht.service.impl.experiencebook.Base;
import com.ht.service.impl.system.workflow.task.ProcessTypeEnum;
import com.ht.service.impl.system.workflow.util.BusinessUtil;
import com.ht.service.inter.background.authority.role.RoleUsersService;
import com.ht.service.inter.background.dicdata.defectform.DefectFormService;
import com.ht.service.inter.background.organization.employee.UserService;
import com.ht.service.inter.complication.form.FormService;
import com.ht.service.inter.complication.formprop.FormValueService;
import com.ht.service.inter.drawtask.taskbook.create.CreateTaskService;
import com.ht.service.inter.experiencebook.CorrectionNoticeService;
import com.ht.service.inter.experiencebook.MinorCorrectionService;
import com.ht.service.inter.experiencebook.PaperMapOtherService;
import com.ht.service.inter.experiencebook.ProjectMapService;
import com.ht.service.inter.experiencebook.SeaMapService;
import com.ht.service.inter.paper.PaperChartService;
import com.ht.service.inter.paper.PaperPanelService;
import com.ht.service.inter.paper.PaperSheetService;
import com.ht.service.inter.system.notice.NoticeService;
import com.ht.service.inter.system.notice.UserNoticeService;
import com.ht.service.inter.system.workflow.process.ProcessFlowService;
import com.ht.service.inter.system.workflow.process.ProcessFormService;
import com.ht.service.inter.system.workflow.publish.VProcessDetailService;
import com.ht.service.inter.system.workflow.task.FlowsService;
import com.ht.service.inter.system.workflow.task.TaskFormService;
import com.ht.service.inter.system.workflow.task.TaskService;
import com.ht.workflow.common.ITaskInfo;
import com.ht.workflow.common.IWorkflowEngine;
import com.ht.workflow.service.IWorkflowService;
import com.ht.workflow.service.WorkflowService;

/**
 * 待办外部访问操作类
 * @author 王有为
 * @date 2016/10/31
 */
public class RuTaskAction extends BaseAction
{

	@Resource
	TaskService taskService;

	@Resource
	UserService userService;
	
	@Resource
	WorkflowService workflowService ;


	/**
	 * 注入roleUsersService
	 */
	@Resource(name = "roleUsersService")
	RoleUsersService roleUsersService;
	
	@Resource(name = "defectFormService")
	DefectFormService defectFormService;
	@Resource
	FormValueService formValueService;

	@Resource
	ProcessFormService processFormService;

	@Resource
	FormService formService;
	
	@Resource
	TaskFormService taskFormService;


	@Resource
	VProcessDetailService vProcessDetailService;

	@Resource
	IWorkflowService service;
	@Resource
	PaperChartService paperChartService;
	@Resource
	PaperPanelService paperPanelService;
	@Resource
	PaperSheetService paperSheetService;
	@Resource
	ProcessFlowService processFlowService;

	@Resource
	private CreateTaskService createTaskService;
	/**
	 * 类编码
	 */
	private static final long serialVersionUID = 1L;
	


	/**
	 * 页面初始化
	 */
	public String init()
	{
		// 科长角色集合
		List<String> roleList = new ArrayList<String>();
		roleList.add("11031901469110099");
		roleList.add("1103190213720100");
		roleList.add("11031904004130102");
		roleList.add("11031905468290105");
		// 资料管理员角色
		roleList.add("11031915039750121");
		roleList.add("11031912049230118");

		String processDefKey = getParam("processDefKey");
		String year = getParam("year");
		processDefKey = getProcessKeyArg(processDefKey);
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
			//定义  总工程师 质检组长 质检工作组组长 质检员 审定组长 审定员 制图事业部科长 用于流程提交对话框的 错误说明的判断依据 2018.11.9
			List<String> curroleList = new ArrayList<String>();
			curroleList.add("11031901469110099");  //总工程师
			curroleList.add("11031911238610116");  // 质检组长
			curroleList.add("11031904004130102");  //质检工作组组长
			curroleList.add("11031911426730117");  // 质检员
			curroleList.add("11031904283690103");  //审定组长
			curroleList.add("11031904444950104");  //审定员
			curroleList.add("11031905468290105");  //制图事业部科长
			//获取当前角色ID是否为编绘员 是为false 否为false curRoleId
			boolean curRoleId=false; 
			
			// 判断用户是否拥有科长角色
			boolean flag = false;
			List<RoleUsers> role = roleUsersService.getRoleUsersByUserId(userId);
			if (role != null)
			{
				if (role.size() > 0)
				{
					for (int i = 0; i < role.size(); i++)
					{
						String roleId = role.get(i).getRoleId();
						if (roleList.contains(roleId)) {
							flag = true;
						}
						// 判断当前角色ID是否为编绘员 用于流程对话框 错误说明显隐的判断
						if (curroleList.contains(roleId)) {
							curRoleId = true;
						}
					}
				}

			}
			
			// 获取配置显示列
			List<Map<String, String>> columns = BusinessUtil.getInstance().getBusinessColumns(processDefKey);
			
			request.setAttribute("html", page.getRuTaskPage(processDefKey, users, flag, true,curRoleId));
			request.setAttribute("curRoleId", curRoleId);///给页面赋值 是否为编绘员、资料管理员 的角色 TRUE FALSE
			request.setAttribute("processDefKey", processDefKey);
			ArrayList<String> processSubmitApprove = new ArrayList<String>();
			List<ProcessForm> processSubmitApproveList = processFormService.getProcessFormByProcessDefId(processDefKey);
			if (processSubmitApproveList != null && processSubmitApproveList.size() > 0)//获取流程节点列表 2020.4.7
			{
				for (ProcessForm processForm : processSubmitApproveList)
				{
					processSubmitApprove.add(processForm.getTaskDefId());
				}
			}
			
			
			
			if(processDefKey.indexOf("NOTICE_")>0) {
							
				processSubmitApproveList = processFormService.getProcessFormByProcessDefId(processDefKey);
				if (processSubmitApproveList != null && processSubmitApproveList.size() > 0)
				{
					for (ProcessForm processForm : processSubmitApproveList)
					{
						processSubmitApprove.add(processForm.getTaskDefId());
					}
				}
						
				
			}
			
			
			
			
			
			processSubmitApproveList = processFormService.getProcessFormByProcessDefId(processDefKey + "_PLAN");
			if (processSubmitApproveList != null && processSubmitApproveList.size() > 0)
			{
				for (ProcessForm processForm : processSubmitApproveList)
				{
					processSubmitApprove.add(processForm.getTaskDefId());
				}
			}
			
	
			
			
			
			
			processSubmitApproveList = processFormService.getProcessFormByProcessDefId(processDefKey + "_ALL");
			if (processSubmitApproveList != null && processSubmitApproveList.size() > 0)
			{
				for (ProcessForm processForm : processSubmitApproveList)
				{
					processSubmitApprove.add(processForm.getTaskDefId());
				}
			}
			request.setAttribute("processSubmitApprove", processSubmitApprove);
			request.setAttribute("from", "1");
			request.setAttribute("columns", DataConverter.convertObject2Json(columns));
			return SUCCESS;
		}
		catch (Exception e)
		{
			return ERROR;
		}
	}

	private String getProcessKeyArg(String processDefKey)
	{
		String result = null;
		if (processDefKey.equals(ProcessTypeEnum.SEA_MAP_COMPILATION_ELECTRONIC_PLAN.name()))
		{
			result = ProcessTypeEnum.SEA_MAP_COMPILATION_ELECTRONIC.name();
		}
		if (processDefKey.equals(ProcessTypeEnum.SEA_MAP_COMPILATION_ELECTRONIC_ALL.name()))
		{
			result = ProcessTypeEnum.SEA_MAP_COMPILATION_ELECTRONIC.name();
		}
		if (processDefKey.equals(ProcessTypeEnum.SEA_MAP_COMPILATION_PAPER_ALL.name()))
		{
			result = ProcessTypeEnum.SEA_MAP_COMPILATION_PAPER.name();
		}
		if (processDefKey.equals(ProcessTypeEnum.SEA_MAP_COMPILATION_PAPER_PLAN.name()))
		{
			result = ProcessTypeEnum.SEA_MAP_COMPILATION_PAPER.name();
		}
		if (processDefKey.equals(ProcessTypeEnum.SEA_MAP_COMPILATION_SOURCE_DATA_ALL.name()))
		{
			result = ProcessTypeEnum.SEA_MAP_COMPILATION_SOURCE_DATA.name();
		}
		if (processDefKey.equals(ProcessTypeEnum.SEA_MAP_COMPILATION_SOURCE_DATA_PLAN.name()))
		{
			result = ProcessTypeEnum.SEA_MAP_COMPILATION_SOURCE_DATA.name();
		}
		else
		{
			result = processDefKey;
		}
		return result;
	}

	/**
	 * 获取待办任务
	 */
	public void getRuTask()
	{
		// 流程定义KEY key值需配置在流程业务配置文件内
		String processDefKey = getParam("processDefKey");
		String year = getParam("year");
	 
		
		String LoginUser = getParam("userNo");
		List<Map<String, Object>> ruTaskList = null;
		try
		{

			// 当前登录用户
			if (StringUtils.isEmpty(LoginUser))
			{
				LoginUser = LoginUtil.getInstance().getLoginNo(request);
			}
			// 取得用户
			List<User> user = userService.getUserByNo(LoginUser);
			if (user.size() > 0)
			{
				switch (processDefKey)
				{
				// 任务书
				case "TASK_BOOK":
					ruTaskList = taskService.getRuTask(user.get(0).getId(), LoginUser, ProcessTypeEnum.TASK_BOOK,year);
					break;
				// 海图编绘源数据编绘
				case "SEA_MAP_COMPILATION_SOURCE_DATA":
					ruTaskList = new ArrayList<Map<String, Object>>();
					List<Map<String, Object>> ruTaskListSeaMap = new ArrayList<Map<String, Object>>();
					ruTaskListSeaMap = taskService.getRuTask(user.get(0).getId(), LoginUser, ProcessTypeEnum.SEA_MAP_COMPILATION_SOURCE_DATA,year);
					if (ruTaskListSeaMap != null)
					{
						ruTaskList.addAll(ruTaskListSeaMap);
					}
					ruTaskListSeaMap = taskService.getRuTask(user.get(0).getId(), LoginUser, ProcessTypeEnum.SEA_MAP_COMPILATION_SOURCE_DATA_PLAN,year);
					if (ruTaskListSeaMap != null)
					{
						ruTaskList.addAll(ruTaskListSeaMap);
					}
					ruTaskListSeaMap = taskService.getRuTask(user.get(0).getId(), LoginUser, ProcessTypeEnum.SEA_MAP_COMPILATION_SOURCE_DATA_ALL,year);
					if (ruTaskListSeaMap != null)
					{
						ruTaskList.addAll(ruTaskListSeaMap);
					}
					break;
				// 海图编绘纸海图编绘
				case "SEA_MAP_COMPILATION_PAPER":
					ruTaskList = new ArrayList<Map<String, Object>>();
					ruTaskListSeaMap = new ArrayList<Map<String, Object>>();
					ruTaskListSeaMap = taskService.getRuTask(user.get(0).getId(), LoginUser, ProcessTypeEnum.SEA_MAP_COMPILATION_PAPER,year);
					if (ruTaskListSeaMap != null)
					{
						ruTaskList.addAll(ruTaskListSeaMap);
					}
					ruTaskListSeaMap = taskService.getRuTask(user.get(0).getId(), LoginUser, ProcessTypeEnum.SEA_MAP_COMPILATION_PAPER_PLAN,year);
					if (ruTaskListSeaMap != null)
					{
						ruTaskList.addAll(ruTaskListSeaMap);
					}
					ruTaskListSeaMap = taskService.getRuTask(user.get(0).getId(), LoginUser, ProcessTypeEnum.SEA_MAP_COMPILATION_PAPER_ALL,year);
					if (ruTaskListSeaMap != null)
					{
						ruTaskList.addAll(ruTaskListSeaMap);
					}
					break;
				// 海图编绘电子海图编绘
				case "SEA_MAP_COMPILATION_ELECTRONIC":
					ruTaskList = new ArrayList<Map<String, Object>>();
					ruTaskListSeaMap = new ArrayList<Map<String, Object>>();
					ruTaskListSeaMap = taskService.getRuTask(user.get(0).getId(), LoginUser, ProcessTypeEnum.SEA_MAP_COMPILATION_ELECTRONIC,year);
					if (ruTaskListSeaMap != null)
					{
						ruTaskList.addAll(ruTaskListSeaMap);
					}
					ruTaskListSeaMap = taskService.getRuTask(user.get(0).getId(), LoginUser, ProcessTypeEnum.SEA_MAP_COMPILATION_ELECTRONIC_PLAN,year);
					if (ruTaskListSeaMap != null)
					{
						ruTaskList.addAll(ruTaskListSeaMap);
					}
					ruTaskListSeaMap = taskService.getRuTask(user.get(0).getId(), LoginUser, ProcessTypeEnum.SEA_MAP_COMPILATION_ELECTRONIC_ALL,year);
					if (ruTaskListSeaMap != null)
					{
						ruTaskList.addAll(ruTaskListSeaMap);
					}
					break;
				// 改正通告 源数据小改正
				case "CORRECTION_NOTICE_SOURCE_DATA_SMALL_CORRECTION":
					ruTaskList = taskService
							.getRuTask(user.get(0).getId(), LoginUser, ProcessTypeEnum.CORRECTION_NOTICE_SOURCE_DATA_SMALL_CORRECTION,year);
					break;
				// 改正通告模版
				case "CORRECTION_NOTICE_TEMPLATE":
					ruTaskList = taskService.getRuTask(user.get(0).getId(), LoginUser, ProcessTypeEnum.CORRECTION_NOTICE_TEMPLATE,year);
					break;
				// 改正通告 模版编辑
				case "CORRECTION_NOTICE_TEMPLATE_EDIT":
					ruTaskList = taskService.getRuTask(user.get(0).getId(), LoginUser, ProcessTypeEnum.CORRECTION_NOTICE_TEMPLATE_EDIT,year);
					break;
				// 纸海图小改正
				case "SMALL_CORRECTION_PAPER":
					ruTaskList = taskService.getRuTask(user.get(0).getId(), LoginUser, ProcessTypeEnum.SMALL_CORRECTION_PAPER,year);
					break;
				// 电子海图小改正
				case "SMALL_CORRECTION_ELECTRONIC":
					ruTaskList = taskService.getRuTask(user.get(0).getId(), LoginUser, ProcessTypeEnum.SMALL_CORRECTION_ELECTRONIC,year);
					break;
				// 工程&专题图纸海图
				case "PROJECT_SPECIAL_PAPER":
					ruTaskList = taskService.getRuTask(user.get(0).getId(), LoginUser, ProcessTypeEnum.PROJECT_SPECIAL_PAPER,year);
					break;
				// 工程&专题图电子海图
				case "PROJECT_SPECIAL_ELECTRONIC":
					ruTaskList = taskService.getRuTask(user.get(0).getId(), LoginUser, ProcessTypeEnum.PROJECT_SPECIAL_ELECTRONIC,year);
					break;
				// 工程&专题图电子海图
				case "OTHER_NAVIGATIONAL":
					ruTaskList = taskService.getRuTask(user.get(0).getId(), LoginUser, ProcessTypeEnum.OTHER_NAVIGATIONAL,year);
					break;
				// 目录管理
				case "CATALOG_MANAGEMENT":
					ruTaskList = taskService.getRuTask(user.get(0).getId(), LoginUser, ProcessTypeEnum.CATALOG_MANAGEMENT,year);
					break;
				// 资料借阅
				case "DATA_BORROWING":
					ruTaskList = taskService.getRuTask(user.get(0).getId(), LoginUser, ProcessTypeEnum.DATA_BORROWING,year);
					break;
				// 资料归还
				case "DATA_RETURN":
					ruTaskList = taskService.getRuTask(user.get(0).getId(), LoginUser, ProcessTypeEnum.DATA_RETURN,year);
					break;
				// 资料录入
				case "DATA_INPUT":
					ruTaskList = taskService.getRuTask(user.get(0).getId(), LoginUser, ProcessTypeEnum.DATA_INPUT,year);
					break;
				// 疑难问题
				case "PROBLEM_SUBMIT":
					ruTaskList = taskService.getRuTask(user.get(0).getId(), LoginUser, ProcessTypeEnum.PROBLEM_SUBMIT,year);
					break;
				default:
					break;
				}
				// 任务数据写回前端
				if(!processDefKey.equals("TASK_BOOK")&&!processDefKey.equals("CATALOG_MANAGEMENT")&&!processDefKey.equals("DATA_BORROWING")
						&&!processDefKey.equals("PROBLEM_SUBMIT")&&!processDefKey.equals("DATA_INPUT")&&!processDefKey.equals("DATA_RETURN")) {
					if(ruTaskList.size()>0){
						// 查询年份
						Iterator<Map<String,Object>> iter = ruTaskList.iterator();
				        while (iter.hasNext()) {
				        	Map<String,Object> item = iter.next();
				          	String taskbookNo = item.get("taskbookNo").toString();
				          	
						//	System.out.println(taskbookNo);
        	
				        	
				            if (!taskbookNo.contains(year)) {
				                iter.remove();
				            }
				        }
					}
				}
				Collections.sort(ruTaskList, new DateComparator());
				writeSuccessResult(ruTaskList);
			}
			else
			{
				// 提示当前用户未注册
				writeFailResult("当前用户未注册");
			}
		}
		catch (Exception e)
		{
			writeFailResult(e.getMessage());
		}
	}

	/**
	 * 初始化流转状态页面，返回成功列表页面
	 */
	public String initFlows()
	{
		// 任务实例ID
		String taskInstId = getParam("taskInstId");
		// 传递流程实例ID
		String processInstId = getParam("processInstId");
		RuTaskPage page = new RuTaskPage();
		// 将获取的节点字符串返回到前台页面
		request.setAttribute("html", page.getFlowsPage(taskInstId, processInstId));
		return SUCCESS;
	}

	@Resource
	FlowsService flowsService;

	/**
	 * 获取流转状态
	 */
	public void getFlow()
	{
		// 流程实例ID
		String processInstId = getParam("processInstId");
		// 获取流转列表
		try
		{
			List<Flows> flows = flowsService.getFlows(processInstId);
			writeSuccessResult(flows);
		}
		catch (Exception e)
		{
			writeFailResult(e.getMessage());
		}
	}

	@Resource
	UserNoticeService usernoticeService;

	@Resource
	NoticeService noticeService;
	
	//改正项号比较
	public void taskNotice() {
		// 流程实例ID
		String processInstId = getParam("processInstId");
		// 通告year
		String year1 = getParam("year1");
		// 通告项号
		String opinion1 = getParam("opinion1");
		
		try {
			// 改正通告项号比较
			if(StringUtils.isNotEmpty(opinion1)){// 不为空，比较本流程的项号大小
				// 查找本流程的最新一条改正通告记录
				ProcessFlow flow = processFlowService.getProcessFlowByAdvice("改正通告",processInstId);
				if(flow != null){
					String lastAdvice = flow.getAdvice();
					String[]  yearAndOpinion = lastAdvice.substring(4).split("-");
					String lastYear = yearAndOpinion[0];
					String lastOpinion = yearAndOpinion[1];
					if(StringUtils.isEmpty(lastOpinion)){
						lastOpinion = "0";
					}
					if(Integer.valueOf(opinion1)<Integer.valueOf(lastOpinion)){
						if(lastYear.equals(year1)){
							//writeFailResult("提示：改正通告项号填写错误，<br/>点击确认则继续提交；<br/>点击返回则取消提交；<br/>先前项号："+lastOpinion);
							writeFailResult("提示：当前改正通告项号小于历史项号，<br/>确认则继续提交；<br/>返回则重新修改；<br/>先前项号："+lastOpinion);
						}
					}
				}else{
					String lastOpinion = "0";
					if(Integer.valueOf(opinion1)<Integer.valueOf(lastOpinion)){
					//writeFailResult("提示：改正通告项号填写错误，<br/>点击确认则继续提交；<br/>点击返回则取消提交；<br/>先前项号："+lastOpinion);
						writeFailResult("提示：改正通告项号填写错误，<br/>点击确认则继续提交；<br/>点击返回则取消提交；<br/>先前项号："+lastOpinion);
					}else {
						writeSuccessResult("提示：当前改正通告项号小于历史项号，<br/>确认则继续提交；<br/>返回则重新修改；<br/>先前项号："+lastOpinion);
						
						
					}
				}
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}

	
	}

	/**
	 * 提交任务  agreeValue 0为退回  agreeValue 1为提交
	 */
	public void performTask()
	{
		// 当前登录用户
		String loginUser = LoginUtil.getInstance().getLoginNo(request);
		// 任务实例ID 417341
		String taskId = getParam("taskId");
		// 流程实例ID 378199
		String processInstId = getParam("processInstId");
		// 流程父实例ID 	377771
		String parentProcessInstId = getParam("parentProcessInstId"); 
		// 是否同意 同意1 不同意0
		String agreeValue = getParam("agreeValue");
		// 备注信息
		String remark = getParam("remark");
		// 审批意见
		//String advice = getParam("advice");
		// 通告year
		String year1 = getParam("year1");
		// 通告项号
		String opinion1 = getParam("opinion1");
		// 航海year
		String year2 = getParam("year2");
		// 航海项号
		String opinion2 = getParam("opinion2");
		// 流程定义ID
		String processDefId = getParam("processDefId");
		// 流程定义ID
		String taskDefId = getParam("taskDefId");
		// 图名
		String mapName = getParam("mapName");
		
				

		//版本号
		String  FORM_BB=null;
		String th=getParam("tuihui");
		String myfiles1=getParam("myfiles1");
		String myfiles2=getParam("myfiles2");
		String myfiles3=getParam("myfiles3");
		String errtxt1=getParam("errtxt1");
		String errtxt2=getParam("errtxt2");
		String errtxt3=getParam("errtxt3");	
		String taskName=getParam("taskName");	
		String advice = null;// 改正通告至
		String advice1 = null;// 航海通告至
		/**
		 * 验证项号0926 liukai
		 */
		try
		{		
		     ////改正项号判空处理
					if(    taskDefId.contains("u_task_wtcljlb")||/*纸海图   编绘问题处理记录表*/
							taskDefId.contains("u_task_work")||/*填写源数据编绘问题处理记录表*   *填写电子海图编绘问题处理记录表*/
							taskDefId.contains("u_task_zhijian_zjjilub")||/*源数据    质检员填写质检记录表*  * 电子海图 质检员填写质检记录表*  * 纸海图 质检员填写质检记录表*/
							taskDefId.contains("u_task_bianhui_tgclqk")||/*纸海图  编绘员填写处理情况*  *电子海图  编绘员填写处理情况*     *源数据  编绘员填写处理情况*/
							taskDefId.contains("u_task_shending_sdjilub")||/*纸海图  审定员填写审定记录表*  *源数据  审定员填写审定记录表*  *电子海图  审定员填写审定记录表*/
							taskDefId.contains("u_task_zhijian_tgclqk")||/*纸海图  质检员填写处理情况*/
							taskDefId.contains("u_task_zj_tgclqk")||/*电子海图  质检员填写处理情况*/
							taskDefId.contains("u_task_tgzjclqk")/*源数据  质检员填写处理情况*/
				)
					{
						if(opinion1.length()<1 && processDefId.indexOf("CORRECTION_NOTICE_SOURCE_DATA_SMALL_CORRECTION")<0  ) {
				
						writeFailResult("提示：改正通告项号不可为空！");
						return;
						}
					}
					// 改正通告项号比较
					if(StringUtils.isNotEmpty(opinion1)){// 不为空，比较本流程的项号大小
						// 查找本流程的最新一条改正通告记录
						ProcessFlow flow = processFlowService.getProcessFlowByAdvice("改正通告",processInstId);
						if(flow != null){
							String lastAdvice = flow.getAdvice();
							String[]  yearAndOpinion = lastAdvice.substring(4).split("-");
							String lastYear = yearAndOpinion[0];
						    String lastOpinion = yearAndOpinion[1];
						  //有可能lastOpinion的值会是       982；航海通告2017            这样的形式 所以 需要重新判断取值一次
						    if(lastOpinion.indexOf("；")!=-1||lastOpinion.indexOf(";")!=-1) {
						      String[] strs = lastOpinion.split(";");
								 	lastOpinion=strs[0];    
						    }
						 if(StringUtils.isEmpty(lastOpinion)){
								lastOpinion = "0";
							}
							if(Integer.valueOf(opinion1)<Integer.valueOf(lastOpinion)){
						if(lastYear.equals(year1)){
							writeFailResult("改正通告先前项号："+lastOpinion);
							return ;
								}
							}
						}else{
					String lastOpinion = "0";
							if(Integer.valueOf(opinion1)<Integer.valueOf(lastOpinion)){
								writeFailResult("改正通告先前项号："+lastOpinion);
								return ;
							}
						}
						advice = "改正通告"+year1+"-"+opinion1;
					}else{// 没填项号，advice为空
						advice = "";
					}
					
									
					
							// 航海通告项号比较
						if(StringUtils.isNotEmpty(opinion2)){
								// 查找本流程的最新一条航海通告的记录
								ProcessFlow flow = processFlowService.getProcessFlowByAdvice1("航海通告",processInstId);
								if(flow != null){
									String lastAdvice = flow.getAdvice1();
									String[]  yearAndOpinion = lastAdvice.substring(4).split("-");
									String lastYear = yearAndOpinion[0];
									String lastOpinion = yearAndOpinion[1];
									if(StringUtils.isEmpty(lastOpinion)){
										lastOpinion = "0";
									}
									if(Integer.valueOf(opinion2)<Integer.valueOf(lastOpinion)){
										//if(lastYear.equals(year2)){
										//	writeFailResult("航海通告项号必须大于或者等于"+lastOpinion);
										//	return;
									//	}
									}
								}else{
									String lastOpinion = "0";
									//if(Integer.valueOf(opinion2)<Integer.valueOf(lastOpinion)){
									//	writeFailResult("航海通告项号必须大于或者等于"+lastOpinion);
									//	return;
									//}
								}
								advice1 = "航海通告"+year2+"-"+opinion2;
							}else{// 没填项号，advice1为空
								advice1 = "";
							}		
						}catch (Exception e)
						{
							writeFailResult(e.getMessage());
							return;
					}
					
							
			
	
			//质量 审定  总工程师 提交时没填写内容则退回 2018.11.29 魏华
			// 质检记录表  审定记录表    写入 FORM_DEFECTIVE
			if(taskName.indexOf("审定记录表")>-1||taskName.indexOf("质检记录表")>-1) {
				
				
				//判断是否存在评分记录
				Integer dfNum = defectFormService.getDefectFormNum(taskId,processInstId);
					if(dfNum<1) {
					writeFailResult("提示：当前节点  "+taskName+"    未发现内容，请修正后在提交；<br/>注：质检、审定 提交“结论表” “审定表” 必须有评分！");
				     return;
					}
				}
			 //质检结论表   审定结论表   写入 FORM_VALUE PROP_KEY=zlpf  总工程师 
             // agreeValue 0为退回  1为提交   
			if((taskName.indexOf("审定结论表")>-1||taskName.indexOf("质检结论表")>0||taskName.indexOf("工程师审核")>0 )&&agreeValue.equals("1")) {
				//判断是否存在评分记录
				Integer dfNum = formService.getFormValueNum(taskId,processInstId,taskName);
					if(dfNum<1) {
						writeFailResult("提示：当前节点  "+taskName+"    未发现内容，请修正后在提交；<br/>注：质检、审定 提交“结论表” “审定表” 必须有评分！");
					return;
					}
				}
			
			
		
		/**
		 * 验证当前节点为质检时计划是否通过
		 */
		String valid = BusinessUtil.getInstance().getProcessTaskValid(processDefId.split(":")[0], taskDefId);
		if (StringUtils.isNotEmpty(valid))
		{
			
			List<HiTask>  planFinish = taskService.validQuality(processInstId, parentProcessInstId);
					if(planFinish!=null&&planFinish.size()>0) {
					    String plname=planFinish.get(0).getTaskName();
						String usname=planFinish.get(0).getGroupName()!=null? planFinish.get(0).getGroupName():planFinish.get(0).getAssigneeName();
						if(plname.indexOf("计划审定")<0) {
							writeFailResult("提	    	示：当前该任务计划未完成，无法提交。<br/>计划节点："+plname+"  \n操 作 人："+usname);
							return;
						}
					}
		}
		// 验证表单是否已填写
		try
		{
			// 查询当前任务所属表单
			ProcessForm pfs = processFormService.getProcessFormByProAndTask(processDefId, taskDefId);
			if (pfs != null)
			{
				String formId = pfs.getFormId();
				// 有跳转URL 校验在流程日志中是否已经有数据
				Form form = formService.getForm(formId);
				String url = form.getUrl();
				// if (StringUtils.isNotEmpty(url))
				// {
				// VProcessDetail process = vProcessDetailService.getProcessDetailByProcessInstIdAndTaskId(processInstId, taskId);
				// if (process == null)
				// {
				// writeFailResult("当前任务有未填写的表单！请完成后再进行提交！");
				// return;
				// }
				// }
				// else
				// {
				// if (StringUtils.isNotEmpty(formId))
				// {
				// // 查询当前流程实例是否有填写的表单
				// List<FormValue> formValueList = formValueService.getFormValueByPt(processInstId, taskId, formId);
				// // 如果当前表单内容为空 那么不允许提交
				// if (formValueList.size() == 0)
				// {
				// writeFailResult("当前任务有未填写的表单！请完成后再进行提交！");
				// return;
				// }
				// }
				// }
			}
		}
		catch (Exception e)
		{
			writeFailResult(e.getMessage());
			return;
		}
		// 提交任务
		try
		{  taskService.performTask_a(loginUser, processInstId, parentProcessInstId, taskId, agreeValue, advice, advice1 , remark,processDefId, taskDefId,mapName,myfiles1,errtxt1,myfiles2,errtxt2,myfiles3,errtxt3,taskName);
		  //th为退回标记  如果是th的话则为退回，从退回按钮带来的字符，此时记录当前节点，2018.9.10
			
		///为每一个任务记录到或更新task_process这个表中 2019.1.11 agreeValue 1为同意 0为退回
		 //如果是制图科长分配任务给编绘组长，则跳出。
		if(taskDefId.indexOf("u_task_distribution_group")<0) {
			taskFormService.updateTaskProgress( taskId,processInstId, processDefId, taskDefId, loginUser, parentProcessInstId,agreeValue,taskName);
		};
		
	switch(agreeValue){

/*	    case "0":  //	//// 如果点击退回按钮则执行 写入版本事件
	    	  taskService.addProcessBackHisByTaskId(taskId);
	     	  break;*/
	    case "0":///如果是提交 则向FORM_VALUE中添加TASKID
	    	formValueService.addFormValueByTaskId(processInstId,loginUser,taskId,taskDefId);
	           break;
	    default: 
	           break;
	}
		//// 如果点击退回按钮则执行 写入版本事件

			
//	  	formValueService.addFormValueByTaskId(processInstId,loginUser,taskId);
		
			// 发送通知
			ProcessDefinition info = service.getProcessDefinitionId(processDefId);
			String processName = info.getName();
			Notice notice = new Notice();
			notice.setNotice_Type("10301416596850001");
			notice.setTitle("系统通知");
			// new日期对象
			Date date = new Date();
			// 转换提日期输出格式
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			VProcessDetail detail = null;
			if (StringUtils.isNotEmpty(parentProcessInstId))
			{
				detail = vProcessDetailService.getProcessDetailByProcessInstId(parentProcessInstId);
			}
			else
			{
				detail = vProcessDetailService.getProcessDetailByProcessInstId(processInstId);
			}
			if(detail!=null){
				String detailRecordId = detail.getDetailRecordId();
				if(detailRecordId!=null){
					CreateTask task = createTaskService.getTask(detailRecordId);
					if(task!=null){
						processName=processName+":"+task.getTaskName();;
					}
				}
			}
			notice.setDescription("您有一条" + processName + "的任务，发布时间为：" + dateFormat.format(date));
			String noticeId = noticeService.addNotice(DataConverter.convertObject2Json(notice));
			List<RuTask> ruList = taskService.getRuTaskByProcessInstId(processInstId);
			for (int i = 0; i < ruList.size(); i++)
			{
				RuTask task = ruList.get(i);
				String userId = task.getAssignee();
				if (StringUtils.isNotEmpty(userId))
				{
					usernoticeService.addUserNotice(noticeId, userId, null);
				}
				else
				{
					String groupId = task.getGroupId();
					List<org.activiti.engine.identity.User> users = service.getUsersByGroup(groupId);
					if (users != null)
					{
						for (int j = 0; j < users.size(); j++)
						{
							usernoticeService.addUserNotice(noticeId, users.get(j).getId(), null);
						}
					}
				}
			}
			writeSuccessResult();
		}
		catch (Exception e)
		{
			writeFailResult(e.getMessage());
		}finally {
			//纸海图、电子海图
			if(("u_task_ztkz_shenhe".equals(taskDefId) && "SEA_MAP_COMPILATION_ELECTRONIC:1:20".contains(processDefId)) || 
				("u_task_zhijian_shenhe".equals(taskDefId) && "SEA_MAP_COMPILATION_PAPER:1:32".contains(processDefId))	) {

			
			//获取任务分配人员
			taskDefId="u_task_distribution_shending";
			String userNo = "028346"; //陈欣
	
	
			
		    String planuser = this.taskFormService.getplanName(taskId, processInstId, processDefId, taskDefId, userNo, parentProcessInstId);

			
		
			
			ITaskInfo  iTaskInfo= 	workflowService.getOneTaskListByProcessInstId(processInstId);

			
			try {
				taskFormService.assignedUser(iTaskInfo.getId(), processInstId, processDefId, taskDefId, planuser, parentProcessInstId);
			} catch (DBException e) {
		     //	System.out.println(e.getMessage());
			}
			}
		}
			
		
	}

	/**
	 * 批量提交任务
	 */
	public void batchPerformTask()
	{
		// 当前登录用户
		String loginUser = LoginUtil.getInstance().getLoginNo(request);
		// 任务实例ID
		String taskIds = getParam("taskId");
		// 流程实例ID
		String processInstIds = getParam("processInstId");
		// 流程父实例ID
		String parentProcessInstIds = getParam("parentProcessInstId");
		// 是否同意 同意1 不同意0
		String agreeValue = getParam("agreeValue");
		// 审批意见
		String advice = getParam("advice");
		// 流程定义ID
		String processDefIds = getParam("processDefId");
		// 流程定义ID
		String taskDefIds = getParam("taskDefId");
		
		System.out.println("制图事业部审核"+taskDefIds);
		
		// 图名
		String mapNames = getParam("mapName");
		if (taskIds != null)
		{
			String[] taskId = taskIds.split(",");
			String[] processInstId = processInstIds.split(",");
			String[] parentProcessInstId = parentProcessInstIds.split(",");
			String[] processDefId = processDefIds.split(",");
			String[] taskDefId = taskDefIds.split(",");
			String[] mapName = mapNames.split(",");
			if (taskId != null)
			{
				if (taskId.length > 0)
				{
					for (int k = 0; k < taskId.length; k++)
					{
						// 提交任务
						try
						{
							if (parentProcessInstId[k].equals("undefined")||parentProcessInstId[k].equals("null"))
							{
								parentProcessInstId[k] = "";
							}
							taskService.performTask(loginUser, processInstId[k], parentProcessInstId[k], taskId[k], agreeValue, advice,"","",
									processDefId[k], taskDefId[k], mapName[k]);
							// 发送通知
							ProcessDefinition info = service.getProcessDefinitionId(processDefId[k]);
							String processName = info.getName();
							Notice notice = new Notice();
							notice.setNotice_Type("10301416596850001");
							notice.setTitle("系统通知");
							// new日期对象
							Date date = new Date();
							// 转换提日期输出格式
							SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							VProcessDetail detail = null;
							if (StringUtils.isNotEmpty(parentProcessInstId[k]))
							{
								detail = vProcessDetailService.getProcessDetailByProcessInstId(parentProcessInstId[k]);
							}
							else
							{
								detail = vProcessDetailService.getProcessDetailByProcessInstId(processInstId[k]);
							}
							if(detail!=null){
								String detailRecordId = detail.getDetailRecordId();
								if(detailRecordId!=null){
									CreateTask task = createTaskService.getTask(detailRecordId);
									if(task!=null){
										processName=processName+":"+task.getTaskName();;
									}
								}
							}
							notice.setDescription("您有一条" + processName + "的任务，发布时间为：" + dateFormat.format(date));
							String noticeId = noticeService.addNotice(DataConverter.convertObject2Json(notice));
							List<RuTask> ruList = taskService.getRuTaskByProcessInstId(processInstId[k]);
							for (int i = 0; i < ruList.size(); i++)
							{
								RuTask task = ruList.get(i);
								String userId = task.getAssignee();
								if (StringUtils.isNotEmpty(userId))
								{
									usernoticeService.addUserNotice(noticeId, userId, null);
								}
								else
								{
									String groupId = task.getGroupId();
									List<org.activiti.engine.identity.User> users = service.getUsersByGroup(groupId);
									if (users != null)
									{
										for (int j = 0; j < users.size(); j++)
										{
											usernoticeService.addUserNotice(noticeId, users.get(j).getId(), null);
										}
									}
								}
							}
						}
						catch (Exception e)
						{
							writeFailResult(e.getMessage());
						}
					}
					writeSuccessResult();
				}
			}
		}
		
		

		
		
	}

	/**
	 * 挂起流程
	 */
	public void suspendProcessInstance()
	{
		// 流程实例ID
		String processInstId = getParam("processInstId");
		// 原因
		String advice = getParam("advice");
		// 当前登录用户
		String loginUser = LoginUtil.getInstance().getLoginNo(request);
		try
		{
			taskService.suspendProcessInstance(processInstId, loginUser, advice);
			writeSuccessResult();
		}
		catch (Exception e)
		{
			writeFailResult(e.getMessage());
		}
	}

	/**
	 * 激活流程
	 */
	public void activateProcessInstance()
	{
		// 流程实例ID
		String processInstId = getParam("processInstId");
		// 原因
		String advice = getParam("advice");
		// 当前登录用户
		String loginUser = LoginUtil.getInstance().getLoginNo(request);
		try
		{
			taskService.activateProcessInstance(processInstId, loginUser, advice);
			writeSuccessResult();
		}
		catch (Exception e)
		{
			writeFailResult(e.getMessage());
		}
	}

	/**
	 * 委托受理人
	 */
	public void delegateTask()
	{
		// 流程实例ID
		String processInstId = getParam("processInstId");
		// 任务ID
		String taskId = getParam("taskId");
		// taskDefId
		String taskDefId = getParam("taskDefId");
		//processDefKey
		// 被委托人
		String userId = getParam("userId");
		// 查看被委托人是否存在
		org.activiti.engine.identity.User isExsit = service.getUserById(userId);
		if (isExsit == null)
		{
			writeFailResult("请选择存在的用户！");
			return;
		}
		
		// 当前登录用户
		String loginUser = LoginUtil.getInstance().getLoginNo(request);
		try
		{
			taskService.delegateTask(processInstId, taskId, loginUser, userId,taskDefId);
			//刷新一次 TASK_PROGRESS表中当前任务的状态
			taskFormService.updateTaskProgress(taskId, processInstId, null, taskDefId, null, null, null, null);
		
			writeSuccessResult();
		}
		catch (Exception e)
		{
			writeFailResult(e.getMessage());
		}
	}

	/**
	 * 获取流程图
	 */
	@SuppressWarnings("unchecked")
	public String flowImage()
	{
		// 获取页面返回的参数
		String flowId = getParam("flowId");
		String excutionId = getParam("excutionId");
		FlowChartPage flowChart = new FlowChartPage();
		// 获取节点字符串返回前台界面
		String appPage = null;
		try
		{
			service.getDiagramActPng(flowId, excutionId);
			appPage = flowChart.getActPngNode(flowId, excutionId);
			// 将获取的节点字符串返回到前台页面
			request.setAttribute("html", appPage);
		}
		catch (Exception e)
		{
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(), e);
			// 返回错误信息
			writeFailResult(e.getMessage());
		}
		return SUCCESS;
	}

	@Resource
	SeaMapService seaMapService;
	@Resource
	CorrectionNoticeService correctionService;
	@Resource
	MinorCorrectionService minorCorrectionService;
	@Resource
	ProjectMapService projectMapService;
	@Resource
	PaperMapOtherService paperMapOtherService;

	/**
	 * 导出经历簿
	 */
	public void ExportBook()
	{
		// 父流程实例ID
		String parentProcessInstId = getParam("parentProcessInstId");
		// 流程实例ID
		String processInstId = getParam("processInstId");
		// 任务类型
		String processDefKey = getParam("processDefKey");
		String fileName = "经历簿";
		try
		{
			// 定义导出数据
			Map<String, Object> data = null;
			/**
			 * 根据流程类型不同 调用不同的导出数据方法
			 */
			// 获取项目在服务器的路径
			String serverPath = request.getServletContext().getRealPath("/");
			File templete = null;

			
			// 源数据
			if (processDefKey.equals(ProcessTypeEnum.SEA_MAP_COMPILATION_SOURCE_DATA_PLAN.name())
					|| processDefKey.equals(ProcessTypeEnum.SEA_MAP_COMPILATION_SOURCE_DATA.name()))
			{
				// 设置返回头
				fileName=getExceptFileName(parentProcessInstId, processDefKey,"1");
				if(fileName==null){
					fileName = "源数据编绘经历簿";
				}else{
					fileName += "源数据编绘经历簿";
				}
				respose.setContentType("multipart/form-data");
				respose.setContentType("application/vnd.ms-excel;charset=uft-8");
				respose.setHeader("Content-Disposition", "attachment;fileName=" + new String(fileName.getBytes("utf-8"), "ISO8859-1") + ".zip");
				// 文件夹路径
				String temp = serverPath + "/experience";
				// 删除文件夹
				FileUtil.DeletFile(new File(temp));
				// 创建文件夹
				FileUtil.CreateFolder(temp);

				// 获取数据
				data = seaMapService.sourceDataMap(parentProcessInstId);
				// 文件输出流
				FileOutputStream fop = new FileOutputStream(new File(temp +  "/"+fileName+".doc"));
				// 文件保存
				replaceTemplete(data, fop, serverPath + "/ht/templete/ysjbhjlbTemplete.docx");

				// 获取数据
				data = paperMapOtherService.PaperQualityEvaluateMap(parentProcessInstId, "SOURCE");
				// 文件输出流
				fop = new FileOutputStream(new File(temp + "/源数据质量评定表.doc"));
				// 文件保存
				replaceTemplete(data, fop, serverPath + "/ht/templete/source/SourceQualityEvaluateTemplate.docx");

				// 转换成压缩包，并输出到页面
				FileUtil.zip(temp, respose.getOutputStream(), false);
				// 删除文件夹
				FileUtil.DeletFile(new File(temp));
			}
			else if (processDefKey.equals(ProcessTypeEnum.SEA_MAP_COMPILATION_PAPER_PLAN.name())
					|| processDefKey.equals(ProcessTypeEnum.SEA_MAP_COMPILATION_PAPER.name()))
			{// 纸海图
				fileName=getExceptFileName(parentProcessInstId, processDefKey,"1");
				if(fileName==null){
					fileName = "纸海图编绘经历簿";
				}else{
					fileName += "纸海图编绘经历簿";
				}
				respose.setContentType("multipart/form-data");
				respose.setContentType("application/vnd.ms-excel;charset=uft-8");
				respose.setHeader("Content-Disposition", "attachment;fileName=" + new String(fileName.getBytes("utf-8"), "ISO8859-1") + ".zip");
				// 文件夹路径
				String temp = serverPath + "/experience";
				// 删除文件夹
				FileUtil.DeletFile(new File(temp));
				// 创建文件夹
				FileUtil.CreateFolder(temp);

				// 获取数据
				//data = seaMapService.paperMap(parentProcessInstId);
				
				// 获取数据
				data = seaMapService.paperMap(parentProcessInstId,processInstId);
				// 文件输出流
				FileOutputStream fop = new FileOutputStream(new File(temp +  "/"+fileName+".doc"));
				// 文件保存
				replaceTemplete(data, fop, serverPath + "/ht/templete/zhtbhjlbTemplete.docx");

				// 获取数据
				data = paperMapOtherService.PaperProcessflow(parentProcessInstId, "PAPER");
				// 文件输出流
				fop = new FileOutputStream(new File(temp + "/纸海图制作工序流程表.doc"));
				// 文件保存
				replaceTemplete(data, fop, serverPath + "/ht/templete/paper/PaperProcessflowTemplete.docx");

				// 获取数据
				data = paperMapOtherService.PaperQualityEvaluateMap(parentProcessInstId, "PAPER");
				// 文件输出流
				fop = new FileOutputStream(new File(temp + "/纸海图质量评定表.doc"));
				// 文件保存
				replaceTemplete(data, fop, serverPath + "/ht/templete/other/PaperQualityEvaluateTemplete.docx");
				
				// 获取数据
				data = paperMapOtherService.PaperSelfQualityEvaluateMap(parentProcessInstId, "PAPER");
				// 文件输出流
				fop = new FileOutputStream(new File(temp + "/纸海图质量自评表.doc"));
				// 文件保存
				replaceTemplete(data, fop, serverPath + "/ht/templete/paper/PaperSelfQualityEvaluateTemplete.docx");
				
				// 获取数据
				data = paperMapOtherService.PaperQualityInspectMap(parentProcessInstId, "PAPER");
				// 文件输出流
								
				///“过程检查意见”一栏  应填“经检查，质量符合要求，可以送中心审定” 部门负责人为“制图事业部科长”  2018.9.21 王娟
				//“最终检查意见”一栏  应填“经检查，质量符合要求，可以送印” 部门负责人为“审定组负责人”  2018.9.21 王娟
                 Map<String, Object> m=(Map<String, Object>) data.get("table_quality");
                
					
				 
				if(m.get("mtext_quality_operation_people")==null) {
					m.put("text_quality_process_opinion", "");
					data.put("table_quality", m);
				}else{
				    m.put("text_quality_process_opinion", "经检查，质量符合要求，可以送中心审定。");
					//m.put("text_quality_process_opinion", "");
					data.put("table_quality", m);
				};
				
				 if(m.get("mtext_quality_final_people")==null) {
						m.put("text_quality_final_opinion", "");
						data.put("table_quality", m);
					}else{
						m.put("text_quality_final_opinion", "经审查，质量已符合要求，可以送印。");
						//m.put("text_quality_process_opinion", "");
						data.put("table_quality", m);
					};
			
				fop = new FileOutputStream(new File(temp + "/纸海图质量检查验收报告.doc"));
				// 文件保存
				
				
				replaceTemplete(data, fop, serverPath + "/ht/templete/other/PaperQualityInspectTemplete.docx");

				// 转换成压缩包，并输出到页面
				FileUtil.zip(temp, respose.getOutputStream(), false);
				// 删除文件夹
				FileUtil.DeletFile(new File(temp));

			}
			else if (processDefKey.equals(ProcessTypeEnum.SEA_MAP_COMPILATION_ELECTRONIC_PLAN.name())
					|| processDefKey.equals(ProcessTypeEnum.SEA_MAP_COMPILATION_ELECTRONIC.name()))
			{// 电子海图
				fileName=getExceptFileName(parentProcessInstId, processDefKey,"1");
				if(fileName==null){
					fileName = "电子海图编绘经历簿";
				}else{
					fileName += "电子海图编绘经历簿";
				}
				respose.setContentType("multipart/form-data");
				respose.setContentType("application/vnd.ms-excel;charset=uft-8");
				respose.setHeader("Content-Disposition", "attachment;fileName=" + new String(fileName.getBytes("utf-8"), "ISO8859-1") + ".zip");
				// 文件夹路径
				String temp = serverPath + "/experience";
				// 删除文件夹
				FileUtil.DeletFile(new File(temp));
				// 创建文件夹
				FileUtil.CreateFolder(temp);

				// 获取数据
				data = seaMapService.electricMap(parentProcessInstId);
				// 文件输出流
				FileOutputStream fop = new FileOutputStream(new File(temp + "/"+fileName+".doc"));
				// 文件保存
				replaceTemplete(data, fop, serverPath + "/ht/templete/dzhtbhjlbTemplete.docx");

				// 获取数据
				data = paperMapOtherService.ElectronicProcessflow(parentProcessInstId, "ELECTRONIC");
				// 文件输出流
				fop = new FileOutputStream(new File(temp + "/电子海图制作工序流程表.doc"));
				// 文件保存
				replaceTemplete(data, fop, serverPath + "/ht/templete/electronic/ElectronicProcessflowTemplete.docx");

				// 获取数据
				data = paperMapOtherService.PaperQualityEvaluateMap(parentProcessInstId, "ELECTRONIC");
				// 文件输出流
				fop = new FileOutputStream(new File(temp + "/电子海图质量评定表.doc"));
				// 文件保存
				replaceTemplete(data, fop, serverPath + "/ht/templete/other/PaperQualityEvaluateTemplete.docx");
				
				// 获取数据
				data = paperMapOtherService.PaperSelfQualityEvaluateMap(parentProcessInstId, "ELECTRONIC");
				// 文件输出流
				fop = new FileOutputStream(new File(temp + "/电子海图质量自评表.doc"));
				// 文件保存
				replaceTemplete(data, fop, serverPath + "/ht/templete/electronic/ElectricSelfQualityEvaluateTemplete.docx");
				
				// 获取数据
				data = paperMapOtherService.PaperQualityInspectMap(parentProcessInstId, "ELECTRONIC");
				// 文件输出流

				// 文件保存
				
					///“过程检查意见”一栏  应填“经检查，质量符合要求，可以送中心审定” 部门负责人为“制图事业部科长”  2018.9.21 王娟
					//“最终检查意见”一栏  应填“经检查，质量符合要求，可以送印” 部门负责人为“审定组负责人”  2018.9.21 王娟
					 Map<String, Object> m=(Map<String, Object>) data.get("table_quality");
		
									 
						if(m.get("mtext_quality_operation_people")==null) {
							m.put("text_quality_process_opinion", "");
							data.put("table_quality", m);
						}else{
						    m.put("text_quality_process_opinion", "经检查，质量符合要求，可以送中心审定。");
							//m.put("text_quality_process_opinion", "");
							data.put("table_quality", m);
						};
						
						 if(m.get("mtext_quality_final_people")==null) {
								m.put("text_quality_final_opinion", "");
								data.put("table_quality", m);
							}else{
								m.put("text_quality_final_opinion", "经审查，质量已符合要求，可以送印。");
								//m.put("text_quality_process_opinion", "");
								data.put("table_quality", m);
							};
						
						
					/*if(m.get("text_quality_final_opinion").toString().equals("同意")&&m.get("text_quality_final_opinion").toString().equals(null)) {
						m.put("text_quality_final_opinion", "经检查，质量符合要求，可以送印");
						data.put("table_quality", m);
					}else{
						m.put("text_quality_final_opinion", "");
						data.put("table_quality", m);
					};
					if(m.get("text_quality_process_opinion").toString().equals("同意")&&m.get("text_quality_process_opinion").toString().equals(n)) {
						m.put("text_quality_process_opinion", "经检查，质量符合要求，可以送中心审定");
						data.put("table_quality", m);
					}else{
						m.put("text_quality_process_opinion", "");
						data.put("table_quality", m);	
					};*/
										
					fop = new FileOutputStream(new File(temp + "/电子海图质量检查验收报告.doc"));
					// 文件保存

								
				replaceTemplete(data, fop, serverPath + "/ht/templete/other/PaperQualityInspectTemplete.docx");

				// 转换成压缩包，并输出到页面
				FileUtil.zip(temp, respose.getOutputStream(), false);
				// 删除文件夹
				FileUtil.DeletFile(new File(temp));

			}else if (processDefKey.equals(ProcessTypeEnum.CORRECTION_NOTICE_TEMPLATE_EDIT.name()))
				{// 改正通告模版编辑
					data = correctionService.NoticeTempleteMap(processInstId);
					fileName=getExceptFileName(processInstId, processDefKey,"2");
					if(fileName==null){
						fileName = "改正通告模板编辑经历簿";
					}else{
						fileName += "改正通告模板编辑经历簿";
					}
					respose.setContentType("multipart/form-data");
					respose.setContentType("application/vnd.ms-excel;charset=uft-8");
					respose.setHeader("Content-Disposition", "attachment;fileName=" + new String(fileName.getBytes("utf-8"), "ISO8859-1") + ".zip");
					// 文件夹路径
					String temp = serverPath + "/experience";
					// 删除文件夹
					FileUtil.DeletFile(new File(temp));
					// 创建文件夹
					FileUtil.CreateFolder(temp);
					FileOutputStream fop = new FileOutputStream(new File(temp +"/"+fileName+".docx"));
					replaceTemplete(data, fop,serverPath +  "/ht/templete/gztgmbbjTemplete.docx");
					// 获取数据
					data = correctionService.NoticeTempleteFlowMap(processInstId);
					// 文件输出流
					 fop  = new FileOutputStream(new File(temp + "/改正通告模板工序流程表.doc"));
					// 文件保存
					replaceTemplete(data, fop, serverPath + "/ht/templete/correctionnotice/CorrectionNoticeTemplateFlow.docx");

					// 转换成压缩包，并输出到页面
					FileUtil.zip(temp, respose.getOutputStream(), false);
					// 删除文件夹
					FileUtil.DeletFile(new File(temp));
				}else if (processDefKey.equals(ProcessTypeEnum.CORRECTION_NOTICE_TEMPLATE.name()))
					{// 改正公告编辑
						data = correctionService.NoticeMap(processInstId);
						fileName=getExceptFileName(processInstId, processDefKey,"2");
						if(fileName==null){
							fileName = "改正通告编辑经历簿";
						}else{
							fileName += "改正通告编辑经历簿";
						}
						respose.setContentType("multipart/form-data");
						respose.setContentType("application/vnd.ms-excel;charset=uft-8");
						respose.setHeader("Content-Disposition", "attachment;fileName=" + new String(fileName.getBytes("utf-8"), "ISO8859-1") + ".zip");
						// 文件夹路径
						String temp = serverPath + "/experience";
						// 删除文件夹
						FileUtil.DeletFile(new File(temp));
						// 创建文件夹
						FileUtil.CreateFolder(temp);
						FileOutputStream fop = new FileOutputStream(new File(temp +"/"+fileName+".docx"));
						replaceTemplete(data, fop,serverPath +  "/ht/templete/gztgbjTemplete.docx");
						// 获取数据
						data = correctionService.NoticeFlowMap(processInstId);
						// 文件输出流
						fop  = new FileOutputStream(new File(temp + "/改正通告工序流程表.doc"));
						// 文件保存
						replaceTemplete(data, fop, serverPath + "/ht/templete/correctionnotice/CorrectionNoticeTemplateFlow.docx");
						// 获取数据
						data = correctionService.NoticeSelfQualityEvaluateMap(processInstId);
						// 文件输出流
						fop  = new FileOutputStream(new File(temp + "/改正通告质量自评表.doc"));
						// 文件保存
						replaceTemplete(data, fop, serverPath + "/ht/templete/correctionnotice/NoticeSelfQualityEvaluateTemplete.docx");
						// 获取数据
						data = correctionService.NoticeQualityEvaluateMap(processInstId);
						// 文件输出流
						fop  = new FileOutputStream(new File(temp + "/改正通告质量评定表.doc"));
						// 文件保存
						replaceTemplete(data, fop, serverPath + "/ht/templete/correctionnotice/NoticeQualityEvaluateTemplete.docx");
						// 获取数据
						data = correctionService.NoticeQualityInspectMap(processInstId);
						// 文件输出流
						fop  = new FileOutputStream(new File(temp + "/改正通告质量检查验收报告.doc"));
						// 文件保存
						replaceTemplete(data, fop, serverPath + "/ht/templete/correctionnotice/NoticeQualityInspectTemplete.docx");

						// 转换成压缩包，并输出到页面
						FileUtil.zip(temp, respose.getOutputStream(), false);
						// 删除文件夹
						FileUtil.DeletFile(new File(temp));
					}else if (processDefKey.equals(ProcessTypeEnum.CORRECTION_NOTICE_SOURCE_DATA_SMALL_CORRECTION.name()))
					{// 源数据小改正
						data = minorCorrectionService.sourceDataMap(processInstId);
						fileName=getExceptFileName(processInstId, processDefKey,"2");
						if(fileName==null){
							fileName = "源数据小改正经历簿";
						}else{
							fileName += "源数据小改正经历簿";
						}
						respose.setContentType("multipart/form-data");
						respose.setContentType("application/vnd.ms-excel;charset=uft-8");
						respose.setHeader("Content-Disposition", "attachment;fileName=" + new String(fileName.getBytes("utf-8"), "ISO8859-1") + ".zip");
						// 文件夹路径
						String temp = serverPath + "/experience";
						// 删除文件夹
						FileUtil.DeletFile(new File(temp));
						// 创建文件夹
						FileUtil.CreateFolder(temp);
						FileOutputStream fop = new FileOutputStream(new File(temp +"/"+fileName+".docx"));
						replaceTemplete(data, fop,serverPath +  "/ht/templete/ysjxgzTemplete.docx");
						// 获取数据
						data = paperMapOtherService.SourceProcessflow(processInstId);
						// 文件输出流
						 fop  = new FileOutputStream(new File(temp + "/源数据小改正工序流程表.doc"));
						// 文件保存
						replaceTemplete(data, fop, serverPath + "/ht/templete/source/SourceMinProcessflow.docx");

						// 转换成压缩包，并输出到页面
						FileUtil.zip(temp, respose.getOutputStream(), false);
						// 删除文件夹
						FileUtil.DeletFile(new File(temp));
					}else if (processDefKey.equals(ProcessTypeEnum.SMALL_CORRECTION_PAPER.name()))
					{// 纸海图小改正
						data = minorCorrectionService.paperMap(processInstId);
						fileName=getExceptFileName(processInstId, processDefKey,"2");
						if(fileName==null){
							fileName = "纸海图小改正经历簿";
						}else{
							fileName += "纸海图小改正经历簿";
						}
						respose.setContentType("multipart/form-data");
						respose.setContentType("application/vnd.ms-excel;charset=uft-8");
						respose.setHeader("Content-Disposition", "attachment;fileName=" + new String(fileName.getBytes("utf-8"), "ISO8859-1") + ".zip");
						// 文件夹路径
						String temp = serverPath + "/experience";
						// 删除文件夹
						FileUtil.DeletFile(new File(temp));
						// 创建文件夹
						FileUtil.CreateFolder(temp);
						FileOutputStream fop = new FileOutputStream(new File(temp +"/"+fileName+".docx"));
						replaceTemplete(data, fop,serverPath +   "/ht/templete/zhtxgzTemplete.docx");
						// 获取数据
						data = minorCorrectionService.paperCorrenctionMap(processInstId);
						// 文件输出流
						 fop  = new FileOutputStream(new File(temp + "/纸海图小改正工序流程表.doc"));
						// 文件保存
						replaceTemplete(data, fop, serverPath + "/ht/templete/paper/PaperCorrectionProcessflowTemplete.docx");

						// 转换成压缩包，并输出到页面
						FileUtil.zip(temp, respose.getOutputStream(), false);
						// 删除文件夹
						FileUtil.DeletFile(new File(temp));
					}else if (processDefKey.equals(ProcessTypeEnum.SMALL_CORRECTION_ELECTRONIC.name()))
						{// 电子海图小改正
							data = minorCorrectionService.electricMap(processInstId);
							fileName=getExceptFileName(processInstId, processDefKey,"2");
							if(fileName==null){
							fileName = "电子海图小改正经历簿";
							}else{
								fileName += "电子海图小改正经历簿";
							}
							respose.setContentType("multipart/form-data");
							respose.setContentType("application/vnd.ms-excel;charset=uft-8");
							respose.setHeader("Content-Disposition", "attachment;fileName=" + new String(fileName.getBytes("utf-8"), "ISO8859-1") + ".zip");
							// 文件夹路径
							String temp = serverPath + "/experience";
							// 删除文件夹
							FileUtil.DeletFile(new File(temp));
							// 创建文件夹
							FileUtil.CreateFolder(temp);
							FileOutputStream fop = new FileOutputStream(new File(temp +"/"+fileName+".docx"));
							replaceTemplete(data, fop,serverPath +"/ht/templete/dzhtxgzTemplete.docx");
							// 获取数据
							data = minorCorrectionService.electricCorrenctionMap(processInstId);
							// 文件输出流
							 fop  = new FileOutputStream(new File(temp + "/电子海图小改正工序流程表.doc"));
							// 文件保存
							replaceTemplete(data, fop, serverPath + "/ht/templete/electronic/ElectriceCorrectionProcessflowTemplete.docx");

							// 转换成压缩包，并输出到页面
							FileUtil.zip(temp, respose.getOutputStream(), false);
							// 删除文件夹
							FileUtil.DeletFile(new File(temp));
						}
					else{
					// 工程专题图纸海图
					if (processDefKey.equals(ProcessTypeEnum.PROJECT_SPECIAL_PAPER.name()))
					{
						data = projectMapService.projectPaperMap(processInstId);
						templete = new File(serverPath + "/ht/templete/gczttztjlbTemplete.docx");
						fileName=getExceptFileName(processInstId, processDefKey,"2");
						if(fileName==null){
							fileName = "工程专题图纸海图经历簿";
						}else{
							fileName += "工程专题图纸海图经历簿";
						}
					}
					// 工程专题图电子海图
					if (processDefKey.equals(ProcessTypeEnum.PROJECT_SPECIAL_ELECTRONIC.name()))
					{
						data = projectMapService.projectElectricMap(processInstId);
						templete = new File(serverPath + "/ht/templete/gczttztjlbTemplete.docx");
						fileName=getExceptFileName(processInstId, processDefKey,"2");
						if(fileName==null){
							fileName = "工程专题图电子海图经历簿";
						}else{
							fileName += "工程专题图电子海图经历簿";
						}
						
					}
					// 纸海图小改正
					if (processDefKey.equals(ProcessTypeEnum.SMALL_CORRECTION_PAPER.name()))
					{
						data = minorCorrectionService.paperMap(processInstId);
						templete = new File(serverPath + "/ht/templete/zhtxgzTemplete.docx");
						fileName=getExceptFileName(processInstId, processDefKey,"2");
						if(fileName==null){
							fileName = "纸海图小改正经历簿";
						}else{
							fileName += "纸海图小改正经历簿";
						}
					}
		
					
					respose.setContentType("multipart/form-data");
					respose.setContentType("application/vnd.ms-excel;charset=uft-8");
					respose.setHeader("Content-Disposition", "attachment;fileName=" + new String(fileName.getBytes("utf-8"), "ISO8859-1") + ".doc");
		
					InputStream in = new FileInputStream(templete);
					// 初始化工具类
					MSWordTool changer = new MSWordTool();
					// 放入模板
					changer.setTemplate(in);
					List<String> bookMarksList = changer.getBookMarkStrings();
					if (bookMarksList.size() > 0)
					{
						changer.replaceTemplete(data, respose.getOutputStream());
					}
				
					respose.getOutputStream().flush();
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * 在hpd中获取数据
	 */
	@SuppressWarnings("unused")
	public void getHPD()
	{/*
		try
		{
			String cpid = getParam("cpid");
			 PaperChart paperChart = paperChartService.getPaperChart(cpid); 
			 PaperPanel paperPanel = paperPanelService.getPaperPanel(cpid);
			 PaperSheet paperSheet = paperSheetService.getPaperSheet(cpid); 
			Map<String, String> map = new HashMap<String, String>();
			if(paperChart!=null){
				map.put("chart", paperChart.toString());
			}
			if(paperPanel!=null){
				map.put("panel", paperPanel.toString());
			}
			if(paperSheet!=null){
				map.put("sheet", paperSheet.toString());
			}
			writeSuccessResult(map);
		}
		catch (Exception e)
		{
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(), e);
		}

	*/}
	/**
	 * 在hpd中获取数据
	 */
	@SuppressWarnings("unused")
	public void getElehpd()
	{/*
		try
		{
			String ecpid = getParam("ecpid");
			PaperChart paperChart = paperChartService.getPaperChart(ecpid); 
			Map<String, String> map = new HashMap<String, String>();
			if(paperChart!=null){
				map.put("eleattributes", paperChart.toString());
			}
			writeSuccessResult(map);
		}
		catch (Exception e)
		{
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(), e);
		}
		
	*/}

	/**
	 * 导出经历簿,替换模版数据并保存
	 * @param data 数据
	 * @param fop 输出流
	 * @param templetePath 模版地址
	 */
	public void replaceTemplete(Map<String, Object> data, OutputStream fop, String templetePath)
	{
		try
		{
			// 获取模版
			File templete = new File(templetePath);
			// 将模版读入流中
			InputStream in = new FileInputStream(templete);
			// 初始化工具类
			MSWordTool changer = new MSWordTool();
			// 放入模板
			changer.setTemplate(in);
			// 获取书签
			List<String> bookMarksList = changer.getBookMarkStrings();
			// 文件输出流
			if (bookMarksList.size() > 0)
			{ // 替换文本
				changer.replaceTemplete(data, fop);
			}
			// 保存文件
			fop.flush();
			fop.close();
		}
		catch (Exception e)
		{
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(), e);
		}

	}
	@SuppressWarnings("unused")
	private String getExceptFileName(String processInstId,String processDefKey,String type) throws Exception{
		VProcessDetail detail=null;
		Base base = new Base();
		if("1".equals(type)){
			// 获取当前流程实例操作的表数据
			HiTask task = base.getFirstHiTask(processInstId,processDefKey);
			// 编绘计划流程实例ID
			String planProcessInstId = task.getProcessInstId();
			// 拆分主键记录
			detail = base.getDetailRecordId(processInstId, processInstId);
		}else{
			// 拆分主键记录
			detail = base.getDetailRecordId(null, processInstId);
		}
		CreateTask complicationTask = createTaskService.getTask(detail.getDetailRecordId());
		String name=null;
		if(complicationTask!=null){
			name=complicationTask.getTaskName();
		}
		if(StringUtils.isNotBlank(name)){
			name=name.replaceAll("/", "-");
			name=name.replaceAll("\\.", "-");
		}
		return name;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void performTask1()
	{
		// 当前登录用户
		String loginUser = LoginUtil.getInstance().getLoginNo(request);
		// 任务实例ID 417341
		String taskId = getParam("taskId");
		// 流程实例ID 378199
		String processInstId = getParam("processInstId");
		// 流程父实例ID 	377771
		String parentProcessInstId = getParam("parentProcessInstId"); 
		// 是否同意 同意1 不同意0
		String agreeValue = getParam("agreeValue");
		// 备注信息
		String remark = getParam("remark");
		// 审批意见
		//String advice = getParam("advice");
		// 通告year
		String year1 = getParam("year1");
		// 通告项号
		String opinion1 = getParam("opinion1");
		// 航海year
		String year2 = getParam("year2");
		// 航海项号
		String opinion2 = getParam("opinion2");
		// 流程定义ID
		String processDefId = getParam("processDefId");
		// 流程定义ID
		String taskDefId = getParam("taskDefId");
		// 图名
		String mapName = getParam("mapName");
		
		String th=getParam("tuihui");
		String myfiles1=getParam("myfiles1");
		String myfiles2=getParam("myfiles2");
		String myfiles3=getParam("myfiles3");
		String errtxt1=getParam("errtxt1");
		String errtxt2=getParam("errtxt2");
		String errtxt3=getParam("errtxt3");	
		String taskName=getParam("taskName");	
		String advice = null;// 改正通告至
		String advice1 = null;// 航海通告至
				
		//质量 审定  总工程师 提交时没填写内容则退回 2018.11.29 魏华
		// 质检记录表  审定记录表    写入 FORM_DEFECTIVE
		if(taskName.indexOf("审定记录表")>-1||taskName.indexOf("质检记录表")>-1) {
		
			
			//判断是否存在评分记录
			Integer dfNum = defectFormService.getDefectFormNum(taskId,processInstId);
				if(dfNum<1) {
				writeFailResult("提示：当前节点  "+taskName+"    未发现内容，请修正后在提交；<br/>注：质检、审定 提交“结论表” “审定表” 必须有评分！");
			     return;
				}
			}
		 //质检结论表   审定结论表   写入 FORM_VALUE PROP_KEY=zlpf  总工程师 pfdj
		if(taskName.indexOf("审定结论表")>-1||taskName.indexOf("质检结论表")>0||taskName.indexOf("工程师审核")>0) {
			//判断是否存在评分记录
			Integer dfNum = formService.getFormValueNum(taskId,processInstId,taskName);
				if(dfNum<1) {
					writeFailResult("提示：当前节点  "+taskName+"    未发现内容，请修正后在提交；<br/>注：质检、审定 提交“结论表” “审定表” 必须有评分！");
				return;
				}
			}
		
				
		
		/**
		 * 验证当前节点为质检时计划是否通过
		 */
		String valid = BusinessUtil.getInstance().getProcessTaskValid(processDefId.split(":")[0], taskDefId);
		if (StringUtils.isNotEmpty(valid))
		{
			List<HiTask>  planFinish = taskService.validQuality(processInstId, parentProcessInstId);
					if(planFinish!=null&&planFinish.size()>0) {
						
						
						
					    String plname=planFinish.get(0).getTaskName();
						String usname=planFinish.get(0).getGroupName()!=null? planFinish.get(0).getGroupName():planFinish.get(0).getAssigneeName();
						if(plname.indexOf("计划审定")<0) {
							writeFailResult("提	    	示：当前该任务计划未完成，无法提交。<br/>计划节点："+plname+"  \n操 作 人："+usname);
							return;
						}
					}
		}
		// 验证表单是否已填写
		try
		{
			// 查询当前任务所属表单
			ProcessForm pfs = processFormService.getProcessFormByProAndTask(processDefId, taskDefId);
			if (pfs != null)
			{
				String formId = pfs.getFormId();
				// 有跳转URL 校验在流程日志中是否已经有数据
				Form form = formService.getForm(formId);
				String url = form.getUrl();


				
				
				
				
				
				
				
				
			}
		}
		catch (Exception e)
		{
			writeFailResult(e.getMessage());
			return;
		}
		// 提交任务
		try
		{  taskService.performTask_a(loginUser, processInstId, parentProcessInstId, taskId, agreeValue, advice, advice1 , remark,processDefId, taskDefId,mapName,myfiles1,errtxt1,myfiles2,errtxt2,myfiles3,errtxt3,taskName);
		  //th为退回标记  如果是th的话则为退回，从退回按钮带来的字符，此时记录当前节点，2018.9.10
			
		///为每一个任务记录到或更新task_process这个表中 2019.1.11 agreeValue 1为同意 0为退回
		 //如果是制图科长分配任务给编绘组长，则跳出。
		if(taskDefId.indexOf("u_task_distribution_group")<0) {
			taskFormService.updateTaskProgress( taskId,processInstId, processDefId, taskDefId, loginUser, parentProcessInstId,agreeValue,taskName);
		};
		
	switch(agreeValue){

/*	    case "0":  //	//// 如果点击退回按钮则执行 写入版本事件
	    	  taskService.addProcessBackHisByTaskId(taskId);
	     	  break;*/
	    case "0":///如果是提交 则向FORM_VALUE中添加TASKID
	    	formValueService.addFormValueByTaskId(processInstId,loginUser,taskId,taskDefId);
	           break;
	    default: 
	           break;
	}
		//// 如果点击退回按钮则执行 写入版本事件

			
//	  	formValueService.addFormValueByTaskId(processInstId,loginUser,taskId);
		
			// 发送通知
			ProcessDefinition info = service.getProcessDefinitionId(processDefId);
			String processName = info.getName();
			Notice notice = new Notice();
			notice.setNotice_Type("10301416596850001");
			notice.setTitle("系统通知");
			// new日期对象
			Date date = new Date();
			// 转换提日期输出格式
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			VProcessDetail detail = null;
			if (StringUtils.isNotEmpty(parentProcessInstId))
			{
				detail = vProcessDetailService.getProcessDetailByProcessInstId(parentProcessInstId);
			}
			else
			{
				detail = vProcessDetailService.getProcessDetailByProcessInstId(processInstId);
			}
			if(detail!=null){
				String detailRecordId = detail.getDetailRecordId();
				if(detailRecordId!=null){
					CreateTask task = createTaskService.getTask(detailRecordId);
					if(task!=null){
						processName=processName+":"+task.getTaskName();;
					}
				}
			}
			notice.setDescription("您有一条" + processName + "的任务，发布时间为：" + dateFormat.format(date));
			String noticeId = noticeService.addNotice(DataConverter.convertObject2Json(notice));
			List<RuTask> ruList = taskService.getRuTaskByProcessInstId(processInstId);
			for (int i = 0; i < ruList.size(); i++)
			{
				RuTask task = ruList.get(i);
				String userId = task.getAssignee();
				if (StringUtils.isNotEmpty(userId))
				{
					usernoticeService.addUserNotice(noticeId, userId, null);
				}
				else
				{
					String groupId = task.getGroupId();
					List<org.activiti.engine.identity.User> users = service.getUsersByGroup(groupId);
					if (users != null)
					{
						for (int j = 0; j < users.size(); j++)
						{
							usernoticeService.addUserNotice(noticeId, users.get(j).getId(), null);
						}
					}
				}
			}
			writeSuccessResult();
		}
		catch (Exception e)
		{
			writeFailResult(e.getMessage());
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
