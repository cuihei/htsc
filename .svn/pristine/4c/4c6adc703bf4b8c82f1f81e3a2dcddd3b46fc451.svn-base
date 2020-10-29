package com.ht.action.statisticalanalysis;

import java.util.List;

import javax.annotation.Resource;

import com.ht.action.base.BaseAction;
import com.ht.common.util.LogHelper;
import com.ht.front.pages.statisticalanalysis.SubmissionSummaryPage;
import com.ht.persistence.model.statisticalanalysis.view.CompilationResultSummaryView;
import com.ht.service.inter.statisticalanalysis.CompilationResultSummaryService;

/**
 * 港口航道图月度汇交action
 * @author zyd
 *
 */
@SuppressWarnings("serial")
public class SubmissionSummaryAction extends BaseAction {
	
	/**
	 * 注入港口航道图月度汇交
	 */
	@Resource(name = "compilationResultSummaryService")
	CompilationResultSummaryService compilationResultSummaryService;
	
	/**
	 * 初始化港口航道图月度汇交数据页面，返回成功列表页面
	 * */
	public String initSubmissionSummary() {
		SubmissionSummaryPage ssp = new SubmissionSummaryPage();
		//将获取的节点字符串返回到前台页面
		request.setAttribute("html", ssp.getListPage());
		return SUCCESS;
	}
	
	/**
	 * 获取所有月度汇交
	 */
	public void getCompilationResultSummary(){
		String startTime = getParam("startTime");
		String endTime = getParam("endTime");
		String year = getParam("year");
		try{
			List<CompilationResultSummaryView> list = compilationResultSummaryService.getCompilationResultSummary(startTime,endTime,year);
			writeSuccessResult(list);
		}catch (Exception e){
			LogHelper.ERROR.log(e.getMessage(),e);
			writeFailResult(e.getMessage());
		}
	}
	
	/**
	 * 导出
	 */
	public void export(){
		try {
			// 获取参数
			String submissionSummarys = getParam("submissionSummarys");
			compilationResultSummaryService.export(submissionSummarys,respose);
		} catch (Exception e) {
			LogHelper.ERROR.log(e.getMessage(),e);
			writeFailResult(e.getMessage());
		}
	}
}
