package com.ht.service.inter.statisticalanalysis;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.ht.persistence.model.statisticalanalysis.view.CompilationCompleteStatusView;


/**
 * 港口航道图完成情况Service
 * @author zyd
 *
 */
public interface CompilationCompleteStatusService {
	
	/**
	 * 获取所有港口航道图完成情况
	 * @return
	 * @throws Exception
	 */
	public List<CompilationCompleteStatusView> getCompilationCompleteStatus(String startTime,String endTime,String year) throws Exception;
	
	
	/**
	 * 根据Id获取一条数据
	 */
	public CompilationCompleteStatusView getCompilationCompleteStatusById(String id); 
	
	/**导出
	 * 
	 * @param completionStatus
	 * @param respose
	 */
	public void export(String completionStatus, HttpServletResponse respose);
	
	
	

}
