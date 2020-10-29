package com.ht.action.correctionnoticesearch;

import java.util.List;

import javax.annotation.Resource;

import com.ht.action.base.BaseAction;
import com.ht.common.util.LogHelper;
import com.ht.front.pages.correctionnoticesearch.CorrectionNoticeItemNoPage;
import com.ht.front.pages.correctionnoticesearch.CorrectionNoticePage;
import com.ht.persistence.model.correctionnotice.CorrectionNotice;
import com.ht.persistence.model.correctionnotice.NoticeBaseData;
import com.ht.service.inter.correctionnoticesearch.CorrectionNoticeService;
/**
 * 查询改正通告Action
 * @author huodesheng	
 * 
 */
@SuppressWarnings("serial")
public class CorrectionNoticeAction extends BaseAction{
	/**
	 * 类标识
	 */
	private static final long serialVersionUID = 1L;
	// 改正通告查询Service CorrectionNoticeDao 
	@Resource(name = "correctionNoticeService")
	private CorrectionNoticeService correctionNoticeService;
	/**
	 * 进入改正通告列表页面
	 * @return
	 */
	public String noticePage(){
		CorrectionNoticePage page = new CorrectionNoticePage();
		String html = page.getPage(correctionNoticeService,"notice");
		request.setAttribute("html", html);
		return SUCCESS;
	}
	/**
	 * 进入改正通告项号查询列表页面
	 * @return
	 */
	public String noticeItemNoPage(){
		CorrectionNoticeItemNoPage page = new CorrectionNoticeItemNoPage();
		String html = page.getPage(correctionNoticeService,"notice");
		request.setAttribute("html", html);
		return SUCCESS;
	}
	/**
	 * 进入最新通告列表页面
	 * @return
	 */
	public String newestNoticePage(){
		CorrectionNoticePage page = new CorrectionNoticePage();
		String html = page.getPage(correctionNoticeService,"newestNotice");
		request.setAttribute("html", html);
		return SUCCESS;
	}
	/**
	 * 获取数据。如果传入查询条件就是根据条件查询，如果未传入条件，就是查询所有。
	 * @return 
	 */
	public void getNoticeList() {
		try {
			/*
			 * 获取参数
			 */
			String ChartNo = getParam("ChartNo");//图号
			String key = getParam("key");//关键字
			String ddlType = getParam("ddlType");//通告类型
			String startTime = getParam("startTime")==null?"":getParam("startTime");//开始时间
			String endTime = getParam("endTime");//结束时间
			String ddlAct =getParam("ddlAct");//改正行为
			String itemNo1 =getParam("itemNo1");//开始时间项号
			String itemNo2 =getParam("itemNo2");//结束时间项号
			if(ddlType==null||ddlType.contains("请选择")){
				ddlType="";
			}
			if(ddlAct==null||ddlAct.contains("请选择")){
				ddlAct="";
			}
			if(startTime==null||startTime.contains("请选择")){
				startTime="";
			}
			if(endTime==null||endTime.contains("请选择")){
				endTime="";
			}
			List<CorrectionNotice> queryByCondition = correctionNoticeService.queryNoticeByCondition(ChartNo, key, ddlType, startTime, endTime, ddlAct,itemNo1,itemNo2);
			//返回客户端数据
			writeSuccessResult(queryByCondition);
		} catch (Exception e) {
			// 写入错误日志
			LogHelper.ERROR.log(e.getMessage(), e);
			// 返回客户端错误消息
			writeFailResult(e.getMessage());
		}
	}
	/**
	 * 获取数据。如果传入查询条件就是根据条件查询，如果未传入条件，就是查询所有。
	 * @return 
	 */
	public void getNoticeItemNoList() {
		try {
			/*
			 * 获取参数
			 */
			String itemNo = getParam("itemNo");//图号
			String key = getParam("key");//关键字
			String ddlType = getParam("ddlType");//通告类型
			String startTime = getParam("startTime");//开始时间
			if(startTime==null||startTime.contains("请选择")){
				startTime="";
			}
			String ddlAct =getParam("ddlAct");//改正行为
			if(ddlType==null||ddlType.contains("请选择")){
				ddlType="";
			}
			if(ddlAct==null||ddlAct.contains("请选择")){
				ddlAct="";
			}
			List<CorrectionNotice> queryByCondition = correctionNoticeService.queryNoticeByItemNoCondition(itemNo, key, ddlType, startTime, ddlAct);
			//返回客户端数据
			writeSuccessResult(queryByCondition);
		} catch (Exception e) {
			// 写入错误日志
			LogHelper.ERROR.log(e.getMessage(), e);
			// 返回客户端错误消息
			writeFailResult(e.getMessage());
		}
	}
	/**
	 * 查询数据
	 */
	public void getNewestList() {
		try {
			/*
			 * 获取参数
			 */
			String IssueID = getParam("IssueID");//期号id
			List<CorrectionNotice> queryByCondition = correctionNoticeService.queryNewestByCondition(IssueID);
			//返回客户端数据
			writeSuccessResult(queryByCondition);
		} catch (Exception e) {
			// 写入错误日志
			LogHelper.ERROR.log(e.getMessage(), e);
			// 返回客户端错误消息
			writeFailResult(e.getMessage());
		}
	}
	/**
	 * 查询项号数据
	 */
	public void getItem() {
		try {
			/*
			 * 获取参数
			 */
			String id = getParam("id");//期号id
			 NoticeBaseData item = correctionNoticeService.queryById(id);
			//返回客户端数据
			writeSuccessResult(item);
		} catch (Exception e) {
			// 写入错误日志
			LogHelper.ERROR.log(e.getMessage(), e);
			// 返回客户端错误消息
			writeFailResult(e.getMessage());
		}
	}
	
}
