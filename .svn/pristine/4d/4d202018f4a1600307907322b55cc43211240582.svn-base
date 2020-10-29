package com.ht.service.inter.statisticalanalysis;

import java.io.File;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.ht.persistence.model.datum.bookinfo.BookInfo;
import com.ht.persistence.model.datum.bookinfo.ViewBookInfo;
import com.ht.persistence.model.statisticalanalysis.view.CompilationYearPlanView;
import com.ht.persistence.model.statisticalanalysis.view.YearPlanView;


/**
 * 统计分析——编绘计划Service
 * @author zyd
 *
 */
public interface CompilationYearPlanService {
	
	/**
	 * 获取所有编绘计划
	 * @return
	 * @throws Exception
	 */
	public List<YearPlanView> getCompilationYearPlan() throws Exception;
	
	/**
	 * 获取一条编绘计划
	 */
	public CompilationYearPlanView getCompilationYearPlanById(String id);
	
	/**
	 * 导出
	 * @param yearPlans
	 * @param respose
	 */
	public InputStream export();
	

}
