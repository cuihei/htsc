package com.ht.persistence.dao.inter.statisticalanalysis;

import java.util.Date;
import java.util.List;

import com.ht.persistence.model.background.monitor.accesslog.Syslog;
import com.ht.persistence.model.statisticalanalysis.CompilationDynamicSummary;
import com.ht.persistence.model.statisticalanalysis.view.CompilationCompleteStatusView;
import com.ht.persistence.model.statisticalanalysis.view.DynamicTableView;

/**
 * 港口航道图月度在编动态Dao
 * @author zyd
 *
 */
public interface DynamicTableDao {
	
	/**
	 * 获取一条月度在编动态
	 * @param bookInfo
	 * @return
	 */
	public DynamicTableView getDynamicTableById(DynamicTableView bookInfo);

	/**
	 * 新增一条
	 * @param c
	 * @throws Exception
	 */
	public void addDynamicTable(CompilationDynamicSummary d);
	
	/**
	 * 根据任务书id获取月度在编动态
	 * @param bookInfo
	 * @return
	 */
	
	/**
	 * 修改
	 * @param cds
	 */
	public void modifyCompilationDynamicSummary(CompilationDynamicSummary cds);

	public List<DynamicTableView> getDynamicTableView(Date date1,Date date2);

	public List<DynamicTableView> getDynamicTableByYear(DynamicTableView dynamicTable);

}
