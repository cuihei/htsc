package com.ht.action.complication.seamap.electron;

import java.util.List;

import javax.annotation.Resource;

import com.ht.action.base.BaseAction;
import com.ht.common.util.LogHelper;
import com.ht.front.pages.complication.seamap.electron.ElectronPage;
import com.ht.persistence.model.complication.seamap.source.SeaMap;
import com.ht.persistence.model.complication.seamap.source.SourceStatus;
import com.ht.persistence.model.complication.seamap.source.TransferStatus;
import com.ht.service.inter.complication.formprop.FormFormValueService;
import com.ht.service.inter.complication.seamap.electron.ElectronService;
import com.ht.service.inter.complication.seamap.source.SourceService;


/**
 * SeaMap 海图编绘类
 * @author yp
 */
@SuppressWarnings("serial")
public class ElectronAction extends BaseAction{
	
	/**
	 * 海图编绘service
	 * */
	@Resource
	private ElectronService electronService;
	
	/**
	 * 海图编绘service
	 * */
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
		ElectronPage cap = new ElectronPage();
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
		ElectronPage cap = new ElectronPage();
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
		String id = getParam("id");
		String taskInstId = getParam("taskInstId");
		String processInstId = getParam("processInstId");
		ElectronPage cap = new ElectronPage();
		//将获取的节点字符串返回到前台页面
		request.setAttribute("html",cap.getDetailsPage(formFormValueService,taskInstId,processInstId));
		return SUCCESS;
	}
	
	/**
	 * 获取电子海图list
	 */
	public void findElectronList(){
		try {
			List<SeaMap> list = electronService.findEleList();
			writeSuccessResult(list);
		} catch (Exception e) {
			// 写入错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			//返回客户端错误消息
			writeFailResult(e.getMessage());
		}
	}

	/**
	 * 获取电子海图一条流转状态
	 */
	public void findEleStatus(){
		try {
			//流程实例ID
			String process_Inst_Id = getParam("process_Inst_Id");
			//任务ID
			String task_Id = getParam("task_Id");
			//List<TransferStatus> list = electronService.findEleStatus(id);
			List<SourceStatus> list = sourceService.findSourceStatus(process_Inst_Id,task_Id);
			writeSuccessResult(list);
		} catch (Exception e) {
			// 写入错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			//返回客户端错误消息
			writeFailResult(e.getMessage());
		}
	}
	
}
