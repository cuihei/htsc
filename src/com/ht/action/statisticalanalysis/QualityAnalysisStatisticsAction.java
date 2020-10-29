package com.ht.action.statisticalanalysis;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;

import com.ht.action.base.BaseAction;
import com.ht.common.util.DateComparator;
import com.ht.common.util.LogHelper;
import com.ht.front.pages.statisticalanalysis.ChartCompletionPage;
import com.ht.front.pages.statisticalanalysis.QualityAnalysisStatisticsPage;
import com.ht.service.inter.statisticalanalysis.QualityAnalysisStatisticsService;
import com.ht.service.inter.system.workflow.task.TaskService;

/**
 * 质量分析统计表action
 * @author huodesheng
 *
 */
@SuppressWarnings("serial")
public class QualityAnalysisStatisticsAction extends BaseAction {
	
	/**
	 * 注入TaskService
	 */
	@Resource
	QualityAnalysisStatisticsService qualityAnalysisStatisticsService;
	
	/**
	 * 初始化海图质量分析统计页面
	 * */
	public String init() {
		String key = this.getParam("processDefKey");
		QualityAnalysisStatisticsPage qas = new QualityAnalysisStatisticsPage();
		//将获取的节点字符串返回到前台页面
		request.setAttribute("html", qas.getListPage(key));
		return SUCCESS;
	}
	
	
	/**
	 * 获取所有海图完成情况
	 */
	public void getAllChartCompletion(){
		try {
			String key = this.getParam("processDefKey");
			List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
			List<Map<String, Object>> list1 = qualityAnalysisStatisticsService.getCompleteTaskByKey(key);
			List<Map<String, Object>> list2 = qualityAnalysisStatisticsService.getRuTaskByKey(key);
			list.addAll(list1);
			list.addAll(list2);
			if(list.size()>0){
				Collections.sort(list, new DateComparator());
			}
			// 返回客户端消息
			writeSuccessResult(list);
		} catch (Exception e) {
			// 写入错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 返回客户端错误消息
			writeFailResult(e.getMessage());
		}
	}
	
	/**
	 * 根据key获取海图完成情况
	 */
	public void getChartCompletionByDate(){
		try {
			String key = this.getParam("processDefKey");
			String startTime = getParam("startTime");
			String endTime = getParam("endTime");
			List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
			List<Map<String, Object>> list1 = qualityAnalysisStatisticsService.getCompleteTaskByDate(key,startTime,endTime);
			List<Map<String, Object>> list2 = qualityAnalysisStatisticsService.getRuTaskByDate(key,startTime,endTime);
			list.addAll(list1);
			list.addAll(list2);
			if(list.size()>0){
				Collections.sort(list, new DateComparator());
			}
			// 返回客户端消息
			writeSuccessResult(list);
		} catch (Exception e) {
			// 写入错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 返回客户端错误消息
			writeFailResult(e.getMessage());
		}
	}
	
}
