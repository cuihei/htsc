package com.ht.action.complication.correctionnotice.sourcedatasmallcorrection;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import com.ht.action.base.BaseAction;
import com.ht.common.util.LogHelper;
import com.ht.front.pages.complication.correctionnotice.sourcedatasmallcorrection.SourSmallCorPage;
import com.ht.persistence.model.complication.correctionnotice.CorNotice;
import com.ht.persistence.model.complication.seamap.source.SourceStatus;
import com.ht.persistence.model.complication.seamap.source.TransferStatus;
import com.ht.service.inter.complication.correctionnotice.sourcedatasmallcorrection.SourSmallCorService;
import com.ht.service.inter.complication.correctionnotice.templete.TempleteService;
import com.ht.service.inter.complication.seamap.source.SourceService;


/**
 * CorNotice 改正通告类
 * @author yp
 */
@SuppressWarnings("serial")
public class SourSmallCorAction extends BaseAction{
	
	/**
	 * 源数据小改正service
	 * */
	@Resource
	private SourSmallCorService sourSmallCorService;
	
	/**
	 * 海图编绘service
	 * */
	@Resource
	private SourceService sourceService;

	/**
	 * 改正通告模板service
	 * */
	@Resource
	private TempleteService templeteService;
	
	/**
	 * 初始化数据页面，返回成功列表页面
	 * @throws IOException 
	 * */
	public String initCorNotice() throws IOException{
		SourSmallCorPage scp = new SourSmallCorPage();
		//将获取的节点字符串返回到前台页面
		request.setAttribute("html",scp.getListNode());
		return SUCCESS;
	}

	/**
	 * 初始化流转状态页面，返回成功列表页面
	 * */
	public String initStatus(){
		//流程实例ID
		String process_Inst_Id = getParam("process_Inst_Id");
		//任务ID
		String task_Id = getParam("task_Id");
		SourSmallCorPage scp = new SourSmallCorPage();
		//将获取的节点字符串返回到前台页面
		request.setAttribute("html",scp.getStatusPage());
		request.setAttribute("process_Inst_Id", process_Inst_Id);
		request.setAttribute("task_Id", task_Id);
		return SUCCESS;
	}
	
	/**
	 * 初始化详情页面，返回成功列表页面
	 * */
	public String initDetails(){
		String id = getParam("id");
		SourSmallCorPage scp = new SourSmallCorPage();
		//将获取的节点字符串返回到前台页面
		request.setAttribute("html",scp.getDetailsPage(id));
		return SUCCESS;
	}
	
	/**
	 * 获取源数据小改正list
	 */
	public void findSourSmallCorList(){
		try {
			List<CorNotice> list = sourSmallCorService.findSourSmallCorList();
			writeSuccessResult(list);
		} catch (Exception e) {
			// 写入错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			//返回客户端错误消息
			writeFailResult(e.getMessage());
		}
	}
	
	/**
	 * 获取源数据小改正一条流转状态
	 */
	public void findSourSmallCorStatus(){
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
