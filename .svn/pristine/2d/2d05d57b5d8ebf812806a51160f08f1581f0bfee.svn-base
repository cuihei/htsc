package com.ht.persistence.dao.inter.statisticalanalysis;

import java.util.Date;
import java.util.List;

import com.ht.persistence.model.statisticalanalysis.CompilationMonthPlan;
import com.ht.persistence.model.statisticalanalysis.view.CompilationCompleteStatusView;
import com.ht.persistence.model.statisticalanalysis.view.CompilationMonthPlanView;
import com.ht.persistence.model.statisticalanalysis.view.DynamicTableView;

/**
 * 港口航道图月度编绘计划Dao
 * @author zyd
 *
 */
public interface CompilationMonthPlanDao {
	
	/**
	 * 获取所有港口航道图月度编绘计划
	 * @return BookInfo列表
	 */
	public List<CompilationMonthPlanView> getCompilationMonthPlan(Date date1,Date date2);
	
	/**
	 * 获取一条港口航道图月度编绘计划
	 * @param cmp
	 * @return
	 */
	public CompilationMonthPlanView getCompilationMonthPlanById(CompilationMonthPlanView cmp);
	
	public List<CompilationMonthPlanView> getCompilationMonthPlanByYear(CompilationMonthPlanView cmp);
	
	/**
	 * 新增一条月度编绘计划
	 * @param c 月度编绘计划
	 * @throws Exception
	 */
	public void addCompilationMonthPlan(CompilationMonthPlan c);
	
}
