package com.ht.action.complication.seamap.paper;

import java.util.List;

import javax.annotation.Resource;

import com.ht.action.base.BaseAction;
import com.ht.common.util.LogHelper;
import com.ht.front.pages.complication.seamap.paper.PaperPage;
import com.ht.persistence.model.complication.seamap.source.SeaMap;
import com.ht.persistence.model.complication.seamap.source.SourceStatus;
import com.ht.service.inter.complication.formprop.FormFormValueService;
import com.ht.service.inter.complication.seamap.paper.PaperService;
import com.ht.service.inter.complication.seamap.source.SourceService;


/**
 * SeaMap 海图编绘类
 * @author yp
 */
@SuppressWarnings("serial")
public class PaperAction extends BaseAction{
	
	/**
	 * 海图编绘service
	 * 
	 */
	@Resource
	private PaperService paperService;
	
	/**
	 * 源数据编绘service
	 */
	@Resource
	private SourceService sourceService;
	
	/**
	 * 表单service
	 * 
	 */
	@Resource
	private FormFormValueService formFormValueService;
	/**
	 * 初始化数据页面，返回成功列表页面
	 * */
	public String initSeaMap(){
		PaperPage cap = new PaperPage();
		//将获取的节点字符串返回到前台页面
		request.setAttribute("html",cap.getListNode());
		return SUCCESS;
	}

	/**
	 * 初始化流转状态页面，返回成功列表页面
	 * */
	public String initStatus(){
		//传递流程定义id
		String process_Inst_Id = getParam("process_Inst_Id");
		process_Inst_Id="1"; //暂时写死,需要修改
		//传递任务id
		String task_Id=getParam("task_Id");
		task_Id="1";   //暂时写死,需要修改
		PaperPage cap = new PaperPage();
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
		String taskInstId = getParam("taskInstId");
		String processInstId = getParam("processInstId");
		PaperPage cap = new PaperPage();
		//将获取的节点字符串返回到前台页面
		request.setAttribute("html",cap.getDetailsPage(formFormValueService,taskInstId,processInstId));
		return SUCCESS;
	}
	
	/**
	 * 获取纸海图list
	 */
	public void findPaperList(){
		try {
			List<SeaMap> list = paperService.findPaperList();
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
	public void findPaperStatus(){
		try {
			//流程实例ID
			String process_Inst_Id = getParam("process_Inst_Id");
			//任务ID
			String task_Id = getParam("task_Id");
			List<SourceStatus> list = sourceService.findSourceStatus(process_Inst_Id,task_Id);
			//List<TransferStatus> list = paperService.findPaperStatus(id);
			writeSuccessResult(list);
		} catch (Exception e) {
			// 写入错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			//返回客户端错误消息
			writeFailResult(e.getMessage());
		}
	}
}
