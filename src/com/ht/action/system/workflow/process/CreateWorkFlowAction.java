package com.ht.action.system.workflow.process;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.activiti.bpmn.model.CallActivity;
import org.activiti.bpmn.model.ExclusiveGateway;
import org.activiti.bpmn.model.Gateway;
import org.activiti.bpmn.model.InclusiveGateway;
import org.activiti.bpmn.model.SequenceFlow;
import org.activiti.bpmn.model.UserTask;

import com.ht.action.base.BaseAction;
import com.ht.common.util.DataConverter;
import com.ht.common.util.GenerateSequenceUtil;
import com.ht.front.pages.system.workflow.process.CreateWorkFlowPage;
import com.ht.persistence.model.background.dicdata.basedata.BaseData;
import com.ht.service.impl.background.dicdata.constants.BaseDataConstants;
import com.ht.service.inter.background.dicdata.basedata.BaseDataService;
import com.ht.workflow.common.ProcessDefinitionInfo;
import com.ht.workflow.constants.CallActivityConstants;
import com.ht.workflow.service.IWorkflowService;
import com.ht.workflow.util.ArgumentList;

/**
 * 工作流创建操作类
 * 
 * @author 王有为
 * @date 2016/10/19
 */
public class CreateWorkFlowAction extends BaseAction {

	@Resource
	IWorkflowService service;
	
	@Resource
	BaseDataService baseDataService;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 初始化流程数据页面，返回成功列表页面
	 * */
	public String init() {
		CreateWorkFlowPage createWFPage = new CreateWorkFlowPage();
		// 将获取的列表页面返回到前台页面
		List<ProcessDefinitionInfo> workflowList = service.getProcessDefinition();
		List<BaseData> processTypeList = baseDataService.getBaseDataByTypeId(BaseDataConstants.PROCESS_TYPE);
		request.setAttribute("html", createWFPage.getWorkFlowPage(workflowList,processTypeList));
		return SUCCESS;
	}

	/**
	 * 编辑流程图布局
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public void ProcessLayout() {
		// 流程任务列表
		List<UserTask> tasks = new ArrayList<UserTask>();
		// 流程流向列表
		List<SequenceFlow> flowList = new ArrayList<SequenceFlow>();
		// 网关列表
		List<Gateway> gatewayList = new ArrayList<Gateway>();
		// 流程名称
		String processName = getParam("processName");
		// 流程ID
		String processKey = getParam("processKey");
		// 子节点
		List<CallActivity> caList = new ArrayList<CallActivity>();
		// 任务节点数据
		String taskList = getParam("taskList");
		try {
			
			this.buildParam(taskList, tasks, flowList, gatewayList, caList);
			// 流程ID
			String path = service.getLayoutXml(processKey, processName,tasks, flowList, gatewayList);
			writeSuccessResult(path);
		} catch (Exception e) {
			writeFailResult("创建出现错误，原因为："+e.getMessage());
		}
	}
	
	/**
	 * 创建流程
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public void createProcess() {
		// 流程任务列表
		List<UserTask> tasks = new ArrayList<UserTask>();
		// 流程流向列表
		List<SequenceFlow> flowList = new ArrayList<SequenceFlow>();
		// 网关列表
		List<Gateway> gatewayList = new ArrayList<Gateway>();
		// 流程名称
		String processName = getParam("processName");
		// 流程ID
		String processKey = getParam("processKey");
		// 子节点
		List<CallActivity> caList = new ArrayList<CallActivity>();
		// 任务节点数据
		String taskList = getParam("taskList");
		// 布局xml
		String layoutXml = getParam("xml");
		try {
			
			this.buildParam(taskList, tasks, flowList, gatewayList, caList);
			// 流程ID
			service.createProcess(processKey, processName, tasks, flowList,
					gatewayList,caList,layoutXml);
			writeSuccessResult();
		} catch (Exception e) {
			writeFailResult("创建出现错误，原因为："+e.getMessage());
		}
	}
	
	private void buildParam(String taskList,List<UserTask> tasks,List<SequenceFlow> flowList,List<Gateway> gatewayList,List<CallActivity> caList){
		// 开始遍历任务列表
		List<Map<String, Object>> taskDataList = (List<Map<String, Object>>) DataConverter
				.convertJson2List(taskList, Map.class);
		for (Map<String, Object> task : taskDataList) {
			/**
			 * 声明任务属性
			 */
			// 任务ID
			String taskId = null;
			// 任务类型
			String taskType = null;
			// 任务名称
			String taskName = null;
			// 任务审批组
			List<String> assineeList = null;
			// 同意流向
			List<String> agreeFlowList = null;
			// 退回流向
			List<String> backFlowList = null;
			// 任务ID
			if (task.get("id") != null) {
				taskId = task.get("id").toString();
			}
			// 任务类型
			if (task.get("type") != null) {
				taskType = task.get("type").toString();
			}
			// 任务名称
			if (task.get("name") != null) {
				taskName = task.get("name").toString();
			}
			// 任务审批组
			if (task.get("groupNo") != null) {
				assineeList = (List<String>) task.get("groupNo");
			}
			// 同意流向
			if (task.get("sourceNo") != null) {
				agreeFlowList = (List<String>) task.get("sourceNo");
			}
			// 退回流向
			if (task.get("targetNo") != null) {
				backFlowList = (List<String>) task.get("targetNo");
			}
			// 校验
			/**
			 * 创建任务节点
			 */
			// 任务为普通任务
			if (taskType.equals("0")) {
				// 创建普通任务
				if (!taskId.equals("ru_task_start") && !taskId.equals("ru_task_end")) {
					UserTask userTask = service.createUserTask(taskId, taskName, assineeList,taskId);
					tasks.add(userTask);
				}
				this.createSeqAndGateway(taskId, agreeFlowList,backFlowList, flowList, gatewayList);
			}
		}
		
		for (Map<String, Object> task : taskDataList) {
			/**
			 * 声明任务属性
			 */
			// 任务ID
			String taskId = null;
			// 任务类型
			String taskType = null;
			// 任务名称
			String taskName = null;
			// 被调用的子流程key
			String caKey = null;
			// 任务审批组
			List<String> assineeList = null;
			// 同意流向
			List<String> agreeFlowList = null;
			// 退回流向
			List<String> backFlowList = null;
			// 任务ID
			if (task.get("id") != null) {
				taskId = task.get("id").toString();
			}
			// 任务类型
			if (task.get("type") != null) {
				taskType = task.get("type").toString();
			}
			// 任务名称
			if (task.get("name") != null) {
				taskName = task.get("name").toString();
			}
			// 任务名称
			if (task.get("caKey") != null) {
				caKey = task.get("caKey").toString();
			}
			// 任务审批组
			if (task.get("groupNo") != null) {
				assineeList = (List<String>) task.get("groupNo");
			}
			// 同意流向
			if (task.get("sourceNo") != null) {
				agreeFlowList = (List<String>) task.get("sourceNo");
			}
			// 退回流向
			if (task.get("targetNo") != null) {
				backFlowList = (List<String>) task.get("targetNo");
			}
			// 校验
			/**
			 * 创建任务节点
			 */
			// 任务为子流程
			if (taskType.equals("1")) {
				// 创建子流程
				CallActivity ca = this.createChildProcess(taskId, caKey, flowList, gatewayList, agreeFlowList, backFlowList);
				caList.add(ca);
			}
		}
	}
	
	/**
	 * 创建子流程
	 * @param id
	 * @param caKey
	 * @param taskList
	 */
	private CallActivity createChildProcess(String id,String caKey, List<SequenceFlow> flowList,List<Gateway> gatewayList,List<String> agreeFlowList,List<String> backFlowList){
		// 来源流向集合
		List<SequenceFlow> caInComingFlows = new ArrayList<SequenceFlow>();
		// 寻找来源流向集合
		for (int i = 0; i < flowList.size(); i++) {
			// 每一个流向
			SequenceFlow outGoingFlow = flowList.get(i);
			// 流向的指向
			String target = outGoingFlow.getTargetRef();
			// 如果指向了当前子流程 加入集合
			if (target.equals(id)) {
				caInComingFlows.add(outGoingFlow);
			}
		}
		List<SequenceFlow> caOutGoingFlows = this.createSeqAndGateway(id, agreeFlowList,backFlowList, flowList, gatewayList);
		ArgumentList inParams = new ArgumentList();
		inParams.add(CallActivityConstants.SOURCE_PARAM_KEY, "source");
		inParams.add(CallActivityConstants.TARGET_PARAM_KEY, "target");
		CallActivity ca = service.createChildProcess(id, caKey, caInComingFlows, caOutGoingFlows, inParams, null);
		return ca;
	}
	
	private List<SequenceFlow> createSeqAndGateway(String taskId,List<String> agreeFlowList,List<String> backFlowList,List<SequenceFlow> flowList,List<Gateway> gatewayList){
		// 当前任务的最后流向列表
		List<SequenceFlow> detailOutFlows = new ArrayList<SequenceFlow>();
		if (backFlowList != null) {
			if (backFlowList.size()>0) {
				// 网关ID
				String gateWayId = "gw_"+GenerateSequenceUtil.generateSequenceNo();
				// 来源流向列表
				List<SequenceFlow> incomingFlows = new ArrayList<SequenceFlow>();
				// 创建来源流向
				SequenceFlow flow = service.createSequenceFlow(taskId, gateWayId, null);
				detailOutFlows.add(flow);
				incomingFlows.add(flow);
				flowList.add(flow);
				// 创建去向流向
				List<SequenceFlow> outgoingFlows = new ArrayList<SequenceFlow>();
				// 同意情况下 如果同意流向大于1 需要再次创建网关
				if (agreeFlowList.size()>1) {
					// 同意流向的网关ID
					String agreeGateWayId = "gw_"+GenerateSequenceUtil.generateSequenceNo();
					// 创建同意来源流向（就是第一个网关的去向流向列表）
					SequenceFlow agreeInComingFlow = service.createSequenceFlow(gateWayId,agreeGateWayId, "${agree==true}");
					List<SequenceFlow> agreeInComingFlows = new ArrayList<SequenceFlow>();
					agreeInComingFlows.add(agreeInComingFlow);
					outgoingFlows.add(agreeInComingFlow);
					flowList.add(agreeInComingFlow);
					// 创建同意去向流向列表
					List<SequenceFlow> agreeOutGoingFlows = new ArrayList<SequenceFlow>();
					for (int i = 0; i < agreeFlowList.size(); i++) {
						// 创建同意去向流向
						String agreeTaskId = agreeFlowList.get(i);
						// 需要参数
						SequenceFlow agreeOutGoingFlow = service.createSequenceFlow(agreeGateWayId,agreeTaskId, "${index"+i+"==true}");
						agreeOutGoingFlows.add(agreeOutGoingFlow);
						flowList.add(agreeOutGoingFlow);
					}
					
					Gateway gateway =  new InclusiveGateway();
					gateway = service.createGateway(agreeGateWayId, gateway,agreeInComingFlows, agreeOutGoingFlows);
					gatewayList.add(gateway);
				}
				// 同意情况下 如果同意流向为1 直接创建流向
				else{
					SequenceFlow agreeFlow = service.createSequenceFlow(gateWayId,agreeFlowList.get(0), "${agree==true}");
					outgoingFlows.add(agreeFlow);
					flowList.add(agreeFlow);
				}
				// 退回情况下 如果退回流向大于1 需要再次创建网关
				if (backFlowList.size()>1) {
					// 退回流向的网关ID
					String backGateWayId = "gw_"+GenerateSequenceUtil.generateSequenceNo();
					// 创建退回来源流向（就是第一个网关的去向流向列表）
					SequenceFlow backInComingFlow = service.createSequenceFlow(gateWayId,backGateWayId, "${agree==true}");
					List<SequenceFlow> backInComingFlows = new ArrayList<SequenceFlow>();
					backInComingFlows.add(backInComingFlow);
					outgoingFlows.add(backInComingFlow);
					flowList.add(backInComingFlow);
					// 创建退回去向流向列表
					List<SequenceFlow> backOutGoingFlows = new ArrayList<SequenceFlow>();
					for (int i = 0; i < backFlowList.size(); i++) {
						// 创建退回去向流向
						String backTaskId = backFlowList.get(i);
						SequenceFlow backOutGoingFlow = service.createSequenceFlow(backGateWayId,backTaskId, "${index"+i+"==true}");
						backOutGoingFlows.add(backOutGoingFlow);
						flowList.add(backOutGoingFlow);
					}
					Gateway gateway =  new InclusiveGateway();
					gateway = service.createGateway(backGateWayId,gateway,backInComingFlows, backOutGoingFlows);
					gatewayList.add(gateway);
				}
				// 退回情况下 如果退回流向为1 直接创建流向
				else{
					SequenceFlow backFlow = service.createSequenceFlow(gateWayId,backFlowList.get(0), "${agree==false}");
					flowList.add(backFlow);
					outgoingFlows.add(backFlow);
				}
				// 创建网关
				ExclusiveGateway gateway =  new ExclusiveGateway();
				gateway =  (ExclusiveGateway) service.createGateway(gateWayId,gateway,incomingFlows, outgoingFlows);
				gatewayList.add(gateway);
			}
			// 2、退回流向为空情况 直接创建流向
			else{
				// 同意情况下 如果同意流向大于1 需要创建网关
				if (agreeFlowList.size()>1) {
					// 同意流向的网关ID
					String agreeGateWayId = "gw_"+GenerateSequenceUtil.generateSequenceNo();
					// 创建同意来源流向（就是第一个网关的去向流向列表）
					SequenceFlow agreeInComingFlow = service.createSequenceFlow(taskId,agreeGateWayId, null);
					List<SequenceFlow> agreeInComingFlows = new ArrayList<SequenceFlow>();
					agreeInComingFlows.add(agreeInComingFlow);
					detailOutFlows.add(agreeInComingFlow);
					flowList.add(agreeInComingFlow);
					// 创建同意去向流向列表
					List<SequenceFlow> agreeOutGoingFlows = new ArrayList<SequenceFlow>();
					for (int i = 0; i < agreeFlowList.size(); i++) {
						// 创建同意去向流向
						String agreeTaskId = agreeFlowList.get(i);
						// 需要参数
						SequenceFlow agreeOutGoingFlow = service.createSequenceFlow(agreeGateWayId,agreeTaskId, "${index"+i+"==true}");
						agreeOutGoingFlows.add(agreeOutGoingFlow);
						flowList.add(agreeOutGoingFlow);
					}
					Gateway gateway =  new InclusiveGateway();
					gateway =  service.createGateway(agreeGateWayId,gateway,agreeInComingFlows, agreeOutGoingFlows);
					gatewayList.add(gateway);
				}
				// 同意情况下 如果同意流向为1 直接创建流向
				else{
					SequenceFlow agreeFlow = service.createSequenceFlow(taskId,agreeFlowList.get(0), null);
					flowList.add(agreeFlow);
					detailOutFlows.add(agreeFlow);
				}
			}
		}
		return detailOutFlows;
	}
}
