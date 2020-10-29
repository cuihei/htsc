package com.ht.action.drawtask.tasksplit;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;

import com.ht.action.base.BaseAction;
import com.ht.common.util.LogHelper;
import com.ht.common.util.LoginUtil;
import com.ht.front.pages.drawtask.tasksplit.TaskSplitPage;
import com.ht.persistence.model.drawtask.plan.Plan;
import com.ht.persistence.model.drawtask.taskbill.TaskBill;
import com.ht.persistence.model.system.workflow.publish.VProcessDetail;
import com.ht.service.inter.background.dicdata.basedata.BaseDataService;
import com.ht.service.inter.drawtask.taskbill.TaskBillService;
import com.ht.service.inter.drawtask.tasksplit.TaskSplitService;
import com.ht.service.inter.system.workflow.publish.VProcessDetailService;


/**
 * 编绘任务拆分Action类 
 * @author yaoping
 */

@SuppressWarnings("serial")
public class TaskSplitAction extends BaseAction{
	
	/**
	 * 编绘任务拆分service
	 * */
	@Resource
	private TaskSplitService taskSplitService;
	
	@Resource
	private TaskBillService taskBillService;
	
	@Resource
	private BaseDataService baseDataService ;
	
	@Resource
	VProcessDetailService vProcessDetailService;
	
	/**
	 * 初始化数据，返回成功列表页面
	 * */
	public String initTaskSplit(){
		String flag = this.getParam("flag");
		TaskSplitPage tsp = new TaskSplitPage();
		//  计划ID列表
		String planIds = getParam("planIds");
		// 流程实例ID
		String processInstId = getParam("processInstId");
		// 流程任務ID
		String taskId = getParam("taskId");
		// 流程实例ID
		String processDefId = getParam("processDefId");
		// 流程任務ID
		String taskDefId = getParam("taskDefId");
		if (StringUtils.isEmpty(flag)) {
			if (StringUtils.isNotEmpty(processDefId)) {
				if (processDefId.split(":") != null) {
					flag = processDefId.split(":")[0];
				}
			}
		}
		VProcessDetail vProcessDetail;
		//将获取的节点字符串返回到前台页面
		request.setAttribute("html",tsp.getListPage(flag,baseDataService));
		request.setAttribute("planIds",planIds);
		request.setAttribute("processInstId",processInstId);
		request.setAttribute("taskId",taskId);
		try {
			vProcessDetail = vProcessDetailService.getProcessDetailByProcessInstIdAndTaskId(processInstId, taskId);
			String taskBookId =	vProcessDetail.getDetailRecordId();
			request.setAttribute("taskBookId", taskBookId);
		} catch (Exception e) {
			return SUCCESS;
		}
		return SUCCESS;
	}

	
	/**
	 * 获取编绘任务拆分list
	 */
	public void findList(){
		try {
			List<TaskBill> list = null;
			String taskBookId = getParam("taskBookId");
			String flag = getParam("flag");
			if (StringUtils.isEmpty(taskBookId)) {
				list = taskBillService.getList(flag);
			}
			else{
				list = taskBillService.getListByPlanIdsAndFlag(flag, taskBookId);
			}
			if (list != null)
			{
				// 如果没有任务书计划 则放一个空计划进去
				for (int i = 0; i < list.size(); i++)
				{
					Plan plan = list.get(i).getPlan();
					if (plan == null)
					{
						Plan planFront = new Plan();
						planFront.setMapNo("无");
						list.get(i).setPlan(planFront);
					}
				}
			}
			writeSuccessResult(list);
		} catch (Exception e) {
			// 写入错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			//返回客户端错误消息
			writeFailResult(e.getMessage());
		}
	}
	
	/**
	 * 编绘任务拆分操作
	 */
	public void doSplit(){
		try {
			String processInstId = this.getParam("processInstId");
			String taskId = this.getParam("taskId");
			String userNo = LoginUtil.getInstance().getLoginNo(request);
			String split = this.getParam("split");
			taskSplitService.doSplit(split,processInstId,userNo,taskId);
			writeSuccessResult();
		} catch (Exception e) {
			// 写入错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			//返回客户端错误消息
			writeFailResult(e.getMessage());
		}
	}
}
