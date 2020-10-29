package com.ht.action.complication.correctionnotice.templete;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import com.ht.action.base.BaseAction;
import com.ht.common.util.LogHelper;
import com.ht.front.pages.complication.correctionnotice.templete.TempletePage;
import com.ht.persistence.model.complication.correctionnotice.CorNotice;
import com.ht.persistence.model.complication.seamap.source.SourceStatus;
import com.ht.persistence.model.complication.seamap.source.TransferStatus;
import com.ht.service.inter.complication.correctionnotice.templete.TempleteService;
import com.ht.service.inter.complication.seamap.source.SourceService;


/**
 * CorNotice 改正通告模板类
 * @author yp
 */
@SuppressWarnings("serial")
public class TempleteAction extends BaseAction{
	
	/**
	 * 改正通告模板service
	 * */
	@Resource
	private TempleteService templeteService;
	
	/**
	 * 海图编绘service
	 * */
	@Resource
	private SourceService sourceService;

	/**
	 * 初始化数据页面，返回成功列表页面
	 * @throws IOException 
	 * */
	public String initCorNotice() throws IOException{
		TempletePage tp = new TempletePage();
		//将获取的节点字符串返回到前台页面
		request.setAttribute("html",tp.getListNode());
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
		TempletePage tp = new TempletePage();
		//将获取的节点字符串返回到前台页面
		request.setAttribute("html",tp.getStatusPage());
		request.setAttribute("process_Inst_Id", process_Inst_Id);
		request.setAttribute("task_Id", task_Id);
		return SUCCESS;
	}
	
	/**
	 * 初始化详情页面，返回成功列表页面
	 * */
	public String initDetails(){
		String id = getParam("id");
		TempletePage tp = new TempletePage();
		//将获取的节点字符串返回到前台页面
		request.setAttribute("html",tp.getDetailsPage(id));
		return SUCCESS;
	}
	
	/**
	 * 获取改正通告模板list
	 */
	public void findTempleteList(){
		try {
			List<CorNotice> list = templeteService.findTempleteList();
			writeSuccessResult(list);
		} catch (Exception e) {
			// 写入错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			//返回客户端错误消息
			writeFailResult(e.getMessage());
		}
	}
	
	/**
	 * 获取改正通告模板一条流转状态
	 */
	public void findTempleteStatus(){
		try {
			//流程实例ID
			String process_Inst_Id = getParam("process_Inst_Id");
			//任务ID
			String task_Id = getParam("task_Id");
			List<SourceStatus> list = sourceService.findSourceStatus(process_Inst_Id,task_Id);
			//List<TransferStatus> list = templeteService.findStatus(id);
			writeSuccessResult(list);
		} catch (Exception e) {
			// 写入错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			//返回客户端错误消息
			writeFailResult(e.getMessage());
		}
	}
	
}
