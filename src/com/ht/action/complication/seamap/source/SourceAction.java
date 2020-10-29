package com.ht.action.complication.seamap.source;

import java.util.List;

import javax.annotation.Resource;

import com.ht.action.base.BaseAction;
import com.ht.common.util.LogHelper;
import com.ht.front.pages.complication.seamap.source.SourcePage;
import com.ht.persistence.model.complication.seamap.source.SourceStatus;
import com.ht.persistence.model.complication.seamap.source.TransferStatus;
import com.ht.service.inter.complication.formprop.FormFormValueService;
import com.ht.persistence.model.drawtask.tasksplit.TaskSplit;
import com.ht.service.inter.complication.seamap.source.SourceService;
import com.ht.service.inter.drawtask.tasksplit.TaskSplitService;


/**
 * SeaMap 海图编绘类
 * @author yp
 */
@SuppressWarnings("serial")
public class SourceAction extends BaseAction{
	
	/**
	 * 海图编绘service
	 * */
	@Resource
	private SourceService sourceService;
	
	/**
	 * 表单service
	 * */
	@Resource
	private FormFormValueService formFormValueService;

	/**
	 * 编绘任务拆分service
	 * */
	@Resource
	private TaskSplitService taskSplitService;
	
	/**
	 * 初始化源数据页面，返回成功列表页面
	 * */
	public String initSeaMap(){
		SourcePage cap = new SourcePage();
		//将获取的节点字符串返回到前台页面
		request.setAttribute("html",cap.getListNode());
		return SUCCESS;
	}

	/**
	 * 初始化流转状态页面，返回成功列表页面
	 * */
	public String initStatus(){
		//String id = getParam("id");
		//传递流程定义id
		String process_Inst_Id = getParam("process_Inst_Id");
		process_Inst_Id="1"; //暂时写死,需要修改
		//传递任务id
		String task_Id=getParam("task_Id");
		task_Id="1";   //暂时写死,需要修改
		SourcePage cap = new SourcePage();
		//将获取的节点字符串返回到前台页面
		request.setAttribute("html",cap.getStatusPage());
		request.setAttribute("process_Inst_Id", process_Inst_Id);
		request.setAttribute("task_Id", task_Id);
		return SUCCESS;
	}
	
	/**
	 * 初始化详情页面，返回成功列表页面
	 * */
	public String initDetails(){
		/*String id = getParam("id");*/
		//流程任务实例id
		String taskInstId = getParam("taskInstId");
		//流程实例id
		String processInstId = getParam("processInstId");
		SourcePage cap = new SourcePage();
		//将获取的节点字符串返回到前台页面
		request.setAttribute("html",cap.getDetailsPage(formFormValueService,taskInstId,processInstId));
		return SUCCESS;
	}
	
	/**
	 * 初始化源数据采用登记列表页面，返回成功列表页面
	 * */
	public String initAdoptPage(){
		String id = getParam("id");
		SourcePage cap = new SourcePage();
		//将获取的节点字符串返回到前台页面
		request.setAttribute("html",cap.getAdoptPage(id));
		return SUCCESS;
	}
	
	/**
	 * 初始化源数据幅索引图页面，返回成功页面
	 * */
	public String initIndexMapPage(){
		String id = getParam("id");
		SourcePage cap = new SourcePage();
		//将获取的节点字符串返回到前台页面
		request.setAttribute("html",cap.getIndexMapPage(id));
		return SUCCESS;
	}
	
	/**
	 * 获取源数据list
	 */
	public void findSourceList(){
		try {
			String flag = getParam("flag");
			List<TaskSplit> list = taskSplitService.findCompilationList(flag);
			writeSuccessResult(list);
		} catch (Exception e) {
			// 写入错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			//返回客户端错误消息
			writeFailResult(e.getMessage());
		}
	}
	
	/**
	 * 获取源数据一条流转状态
	 */
	public void findSourceStatus(){
		try {
			//流程实例ID
			String process_Inst_Id = getParam("process_Inst_Id");
			//任务ID
			String task_Id = getParam("task_Id");
			//String id = getParam("id");
			//List<TransferStatus> list = sourceService.findSourceStatus(process_inst_id,task_id);
			List<SourceStatus> list = sourceService.findSourceStatus(process_Inst_Id,task_Id);
			writeSuccessResult(list);
		} catch (Exception e) {
			// 写入错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			//返回客户端错误消息
			writeFailResult(e.getMessage());
		}
	}
	

	/**
	 * 获取源数据采用登记列表
	 */
	public void findSourceAdopt(){
		try {
			String id = getParam("id");
			List<TransferStatus> list = sourceService.findSourceAdopt(id);
			writeSuccessResult(list);
		} catch (Exception e) {
			// 写入错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			//返回客户端错误消息
			writeFailResult(e.getMessage());
		}
	}
	
	/**
	 * 获取源数据幅索引图数据
	 * 
	 */
	public void findSourceIndexMap(){
		try {
			String id = getParam("id");
			TransferStatus info = sourceService.findSourceIndexMap(id);
			writeSuccessResult(info);
		} catch (Exception e) {
			// 写入错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			//返回客户端错误消息
			writeFailResult(e.getMessage());
		}
	}
}
