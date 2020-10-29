package com.ht.action.statisticalanalysis;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.ht.action.base.BaseAction;
import com.ht.common.util.DateComparator;
import com.ht.common.util.LogHelper;
import com.ht.front.pages.statisticalanalysis.ChartCompletionPage;
import com.ht.persistence.model.drawtask.taskbook.book.TaskBookUpTime;
import com.ht.persistence.util.PageObject;
import com.ht.service.inter.statisticalanalysis.CompilationChildTaskService;
import com.ht.service.inter.system.workflow.task.TaskService;
//import com.sun.org.apache.bcel.internal.generic.NEW;

/**
 * 海图完成情况action
 * 
 * @author 侯晨
 *
 */
@SuppressWarnings("serial")
public class ChartCompletionAction extends BaseAction {

	/**
	 * 注入TaskService
	 */
	@Resource
	TaskService taskService;
	@Resource
	CompilationChildTaskService compilationChildTaskService;

	/**
	 * 初始化海图完成情况页面，返回成功列表页面
	 */
	public String init() {
		String key = this.getParam("processDefKey");
		String isComplete = getParam("isComplete");
		String type = getParam("type");
		ChartCompletionPage ccp = new ChartCompletionPage();
		// 将获取的节点字符串返回到前台页面
		request.setAttribute("isComplete", isComplete);
		request.setAttribute("type", type);
		//获取更新状态 1更新中 0未更新
		List<String> list = null;
		try {
			list = compilationChildTaskService.getYearList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("html", ccp.getListPage(key, list));
		return SUCCESS;
	}

	/**
	 * 获取所有海图完成情况
	 */
	public void getChartCompletion() {
		String page = getParam("page");
		String pageSize = getParam("pageSize");
		PageObject p = new PageObject();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		String year = sdf.format(new Date());
		try {
			String isComplete = getParam("isComplete");
			String key = this.getParam("processDefKey");
			List<Map<String, Object>> list = null;
			int total = 0;
			if (StringUtils.isNotEmpty(key)) {
				if (isComplete.equals("true")) {
					list = taskService.getCompleteTaskByKey(key, page, pageSize, year);
					if (list.size() > 0) {
						Collections.sort(list, new DateComparator());
					}
					total = taskService.getCompleteTaskByKeyCount(key);
				} else {
					list = taskService.getRuTaskByKey(key, page, pageSize, year);
					if (list.size() > 0) {
						Collections.sort(list, new DateComparator());
					}
					total = taskService.getRuTaskByKeyCount(key);
				}
			} else {
				if (isComplete.equals("true")) {
					list = taskService.getCompleteTask(page, pageSize, year);
					total = taskService.getCompleteTaskCount();
					if (list.size() > 0) {
						Collections.sort(list, new DateComparator());
					}
				} else {
					list = taskService.getRuTaskAll(page, pageSize, year);
					total = taskService.getRuTaskAllCount();
					if (list.size() > 0) {
					//	Collections.sort(list, new DateComparator());
											
						
					}
				}
			}
			// 返回客户端消息
			p.setList(list);
			p.setTotal(total);
			
			
		
			



			        //排序前 
		

	
	
			
			
			
			
			
			writeSuccessResult(p);
		} catch (Exception e) {
			// 写入错误日志
			LogHelper.ERROR.log(e.getMessage(), e);
			// 返回客户端错误消息
			writeFailResult(e.getMessage());
		}
	}
	
	
	
	

	/**
	 * 获取所有海图完成情况
	 */
	public void getAllChartCompletion() {
		String page = getParam("page");
		String pageSize = getParam("pageSize");
		String compType = getParam("compType");
		PageObject p = new PageObject();
		String plan = getParam("type");
		String year = getParam("year");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {// 编绘计划完成情况
			if ((plan != null) && (plan.equals("plan"))) {
				//全部计划
						if (compType.equals("0")) {
							List<Map<String, Object>> listAll = this.taskService.getTaskAllPlan(year, compType);
							p.setList(listAll);
							p.setTotal(Integer.valueOf(listAll.size()));
				//已完成的计划
						} else if (compType.equals("1")) {
							List<Map<String, Object>> listAll = this.taskService.getTaskAllPlan(year, compType);
							p.setList(listAll);
							p.setTotal(Integer.valueOf(listAll.size()));
				//未完成的计划
						} else if (compType.equals("2")) {
							List<Map<String, Object>> listAll = this.taskService.getTaskAllPlan(year, compType);
							p.setList(listAll);
							p.setTotal(Integer.valueOf(listAll.size()));
						}
			} else if ((plan != null) && (plan.equals("distribution"))) {
				//任务分配情况
						List<Map<String, Object>> list1 = this.taskService.getRuTaskAllDis(year);
						p.setList(list1);
						p.setTotal(Integer.valueOf(list1.size()));
				//编绘任务完成情况
			} else if (compType.equals("0")) {//全部任务
			
				List<Map<String, Object>> listAll = this.taskService.getTaskAll(year, compType);
				p.setList(listAll);
				p.setTotal(Integer.valueOf(listAll.size()));
			} else if (compType.equals("1")) {//已完成任务
				List<Map<String, Object>> listAll = this.taskService.getTaskAll(year, compType);
				p.setList(listAll);
				p.setTotal(Integer.valueOf(listAll.size()));
			} else if (compType.equals("2")) {//未完成任务
				List<Map<String, Object>> listAll = this.taskService.getTaskAll(year, compType);
				p.setList(listAll);
				p.setTotal(Integer.valueOf(listAll.size()));
				p.getmsg("1");
				
			}
			if (p.getTotal().intValue() == 0) {
				writeSuccessResult("数据库文件为空");
			} else {
				writeSuccessResult(p);
			}
		} catch (Exception e) {
			LogHelper.ERROR.log(e.getMessage(), e);

			writeFailResult(e.getMessage());
		}
	}



	
	/**
	 * 根据key获取海图完成情况
	 */
	public void getChartCompletionByKey() {
		String page = getParam("page");
		String pageSize = getParam("pageSize");
		PageObject p = new PageObject();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		String year = sdf.format(new Date());
		try {
			String key = this.getParam("processDefKey");
			String isComplete = getParam("isComplete");
			List<Map<String, Object>> list = null;
			int count = 0;
			if (isComplete.equals("true")) {
				list = taskService.getCompleteTaskByKey(key, page, pageSize, year);
				count = taskService.getCompleteTaskByKeyCount(key);
			} else {
				list = taskService.getRuTaskByKey(key, page, pageSize, year);
				count = taskService.getRuTaskByKeyCount(key);
			}
			p.setList(list);
			p.setTotal(count);
			// 返回客户端消息
			writeSuccessResult(p);
		} catch (Exception e) {
			// 写入错误日志
			LogHelper.ERROR.log(e.getMessage(), e);
			// 返回客户端错误消息
			writeFailResult(e.getMessage());
		}
	}

	
	///页面过来的方法 人工点击时 更新状态信息。
	
/*	public void updateUpTab() { 
		String type = getParam("type");
		String sta = getParam("state");
		taskService.updateTaskUpTime(type, sta);
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		writeSuccessResult(sdf.format(date));
		}*/
	///从本面方法过来的  更新状态信息，以防定时任务运行时表中的状态没改变。
/*	public void updateUpTab(String type,String sta) { 
		taskService.updateTaskUpTime(type, sta);
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		writeSuccessResult(sdf.format(date));
		}*/
	
	///进入页面时获取更新状态
/*	public void upstate() { String type = getParam("type"); List<String> p=taskService.taskupTimeStr(type); writeSuccessResult(p);}*/
	
/*	
	public void taskrun() throws Exception {
		updateUpTab("task","1");
				try {
			String page = "1";
			String pageSize = "-1";
			List<String> yearAll = new ArrayList<String>();
			List<Map<String,Object>> listall=new ArrayList<Map<String,Object>>();
			yearAll = this.taskService.getAllTaskYear();
			String year = null;
			if (yearAll.size() > 0) {
				for (int x = 0; x < yearAll.size(); x++) {
					year = (String) yearAll.get(x);
					List<Map<String, Object>> list0 = this.taskService.getRuTaskAllByYear(page, pageSize, year);
			    	List<Map<String, Object>> list1 = this.taskService.getCompleteTaskByYear(page, pageSize, year);
					 listall.addAll(list1);
					 listall.addAll(list0);
				}
				this.taskService.delAllTempTask();
				this.taskService.addTaslAllList(listall);
				writeSuccessResult("更新结束");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void planrun() throws Exception {
		updateUpTab("plan","1");
		try {
			String page = "1";
			String pageSize = "-1";
			List<String> yearAll = new ArrayList<String>();
			yearAll = this.taskService.getAllTaskYear();
			List<Map<String,Object>> listall=new ArrayList<Map<String,Object>>();
			String year = null;
			if (yearAll.size() > 0) {
				for (int x = 0; x < yearAll.size(); x++) {
					year = (String) yearAll.get(x);
				   List<Map<String, Object>> list0 = this.taskService.getPlanCompleteTask(page, pageSize, year);
			    	List<Map<String, Object>> list1 = this.taskService.getPlanRuTask(page, pageSize, year);
			        listall.addAll(list1);
			        listall.addAll(list0);
				}
				this.taskService.delAllTempTaskPlan();
				this.taskService.addTaslAllListPlan(listall);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

}
