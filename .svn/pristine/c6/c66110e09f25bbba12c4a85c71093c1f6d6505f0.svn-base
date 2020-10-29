package com.ht.service.inter.statisticalanalysis;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.ht.persistence.model.datum.bookinfo.BookInfo;
import com.ht.persistence.model.datum.bookinfo.ViewBookInfo;
import com.ht.persistence.model.statisticalanalysis.CompilationMonthPlan;
import com.ht.persistence.model.statisticalanalysis.CompilationResultsSummary;
import com.ht.persistence.model.statisticalanalysis.view.CompilationCompleteStatusView;
import com.ht.persistence.model.statisticalanalysis.view.CompilationResultSummaryView;
import com.ht.persistence.model.statisticalanalysis.view.CompilationYearPlanView;
import com.ht.persistence.model.statisticalanalysis.view.DynamicTableView;


/**
 * 港口航道图月度汇交Service
 * @author zyd
 *
 */
public interface CompilationResultSummaryService {
	
	/**
	 * 获取所有港口航道图月度汇交
	 * @return
	 * @throws Exception
	 */
	public List<CompilationResultSummaryView> getCompilationResultSummary(String startTime,String endTime,String year) throws Exception;
	
	/**
	 * 获取一条港口航道图月度汇交
	 */
	public CompilationResultSummaryView getSubmissionSummaryById(String id) throws Exception;
	
	/**
	 * 导出
	 * @param submissionSummarys
	 * @param respose
	 */
	public void export(String submissionSummarys, HttpServletResponse respose);
	
	/**
	 * 新增一条
	 * @param c
	 * @throws Exception
	 */
	public void addCompilationResultSummary(CompilationResultsSummary c) throws Exception;
	
	

}
