package com.ht.persistence.dao.inter.statisticalanalysis;

import java.util.Date;
import java.util.List;

import com.ht.persistence.model.statisticalanalysis.CompilationResultsSummary;
import com.ht.persistence.model.statisticalanalysis.view.CompilationCompleteStatusView;
import com.ht.persistence.model.statisticalanalysis.view.CompilationResultSummaryView;
import com.ht.persistence.model.statisticalanalysis.view.DynamicTableView;

/**
 * 港口航道图成果汇交Dao
 * @author zyd
 *
 */
public interface CompilationResultSummaryDao {
	
	/**
	 * 获取所有港口航道图成果汇交
	 * @return BookInfo列表
	 */
	public List<CompilationResultSummaryView> getCompilationResultSummary(Date date1,Date date2);
	
	/**
	 * 获取一条数据
	 * @param crs
	 * @return
	 */
	public CompilationResultSummaryView getSubmissionSummaryById(CompilationResultSummaryView crs);
	
	/**
	 * 新增一条
	 * @param c 月度编绘计划
	 * @throws Exception
	 */
	public void addCompilationResultSummary(CompilationResultsSummary c);

	public List<CompilationResultSummaryView> getSubmissionSummaryByYear(
			CompilationResultSummaryView crs);
	
}
