package com.ht.action.statisticalanalysis;

import java.util.List;

import javax.annotation.Resource;

import com.ht.action.base.BaseAction;
import com.ht.common.util.LogHelper;
import com.ht.front.pages.statisticalanalysis.MonthlyPlanPage;
import com.ht.persistence.model.statisticalanalysis.view.CompilationMonthPlanView;
import com.ht.service.inter.statisticalanalysis.CompilationMonthPlanService;

/**
 * 港口航道图月度编绘计划action
 * @author zyd
 *
 */
@SuppressWarnings("serial")
public class MonthPlanAction extends BaseAction {
	
	/**
	 * 注入CompilationMonthPlanService
	 */
	@Resource(name = "compilationMonthPlanService")
	CompilationMonthPlanService compilationMonthPlanService;
	
	/**
	 * 初始化月度编绘计划数据页面，返回成功列表页面
	 * */
	public String initMonthlyPlan() {
		MonthlyPlanPage mpp = new MonthlyPlanPage();
		//将获取的节点字符串返回到前台页面
		request.setAttribute("html", mpp.getListPage());
		return SUCCESS;
	}
	
	/**
	 * 获取所有港口航道图月度编绘计划数据
	 */
	public void getCompilationMonthPlan(){
		String startTime = getParam("startTime");
		String endTime = getParam("endTime");
		String year = getParam("year");
		try {
			// 按照所选择的年月，执行查询操作
			List<CompilationMonthPlanView> list = compilationMonthPlanService.getCompilationMonthPlan(startTime,endTime,year);
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
	 * 导出
	 */
	public void export(){
		try {
			String year = getParam("year");
			String monthPlans = getParam("monthPlans");
			List<CompilationMonthPlanView> compilationMonthPlan = compilationMonthPlanService.getCompilationMonthPlan(null,null,year);
			compilationMonthPlanService.export(compilationMonthPlan,monthPlans,respose);
		} catch (Exception e) {
			// 写入错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 返回客户端错误消息
			writeFailResult(e.getMessage());
		}
	}
}
