package com.ht.service.inter.statisticalanalysis;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.ht.persistence.model.datum.bookinfo.BookInfo;
import com.ht.persistence.model.datum.bookinfo.ViewBookInfo;
import com.ht.persistence.model.statisticalanalysis.CompilationDynamicSummary;
import com.ht.persistence.model.statisticalanalysis.CompilationMonthPlan;
import com.ht.persistence.model.statisticalanalysis.view.CompilationCompleteStatusView;
import com.ht.persistence.model.statisticalanalysis.view.CompilationYearPlanView;
import com.ht.persistence.model.statisticalanalysis.view.DynamicTable;
import com.ht.persistence.model.statisticalanalysis.view.DynamicTableView;


/**
 * 港口航道图月度在编动态Service
 * @author zyd
 *
 */
public interface DynamicTableService {
	
	/**
	 * 获取所有港口航道图月度在编动态
	 * @return
	 * @throws Exception
	 */
	public List<DynamicTable> getDynamicTable(String datestr1,String datestr2,String year) throws Exception;
	
	/**
	 * 获取一条月度在编动态
	 * @param id
	 * @return
	 */
	public DynamicTableView getDynamicTableById(String id) throws Exception;
	
	/**
	 * 导出
	 * @param dynamicTables 
	 * @param respose 
	 */
	public void export(String dynamicTables,HttpServletResponse respose) throws Exception;
	
	/**
	 * 新增一条
	 * @param c
	 * @throws Exception
	 */
	public void addDynamicTable(CompilationDynamicSummary d) throws Exception;
	

}
