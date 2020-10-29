package com.ht.service.inter.statisticalanalysis;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.ht.persistence.model.datum.bookinfo.BookInfo;
import com.ht.persistence.model.datum.bookinfo.ViewBookInfo;
import com.ht.persistence.model.statisticalanalysis.CompilationMonthPlan;
import com.ht.persistence.model.statisticalanalysis.view.CompilationCompleteStatusView;
import com.ht.persistence.model.statisticalanalysis.view.CompilationMonthPlanView;
import com.ht.persistence.model.statisticalanalysis.view.CompilationYearPlanView;
import com.ht.persistence.model.statisticalanalysis.view.DynamicTableView;


/**
 * 港口航道图月度编绘计划Service
 * @author zyd
 *
 */
public interface CompilationMonthPlanService {
	
	/**
	 * 获取所有港口航道图月度编绘计划
	 * @return
	 * @throws Exception
	 */
	public List<CompilationMonthPlanView> getCompilationMonthPlan(String startTime,String endTime,String year) throws Exception;
	
	/**
	 * 获取一条编绘计划
	 */
	public CompilationMonthPlanView getCompilationMonthPlanById(String id);
	
	/**
	 * 导出
	 * @param monthPlans
	 * @param respose
	 */
	public void export(List<CompilationMonthPlanView> dynamicTableList,String monthPlans, HttpServletResponse respose);
	
	/**
	 * 新增一条
	 * @param c
	 * @throws Exception
	 */
	public void addCompilationMonthPlan(CompilationMonthPlan compilationMonthPlan) throws Exception;
	
}
