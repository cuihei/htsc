package com.ht.action.statisticalanalysis;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.ht.action.base.BaseAction;
import com.ht.common.util.LogHelper;
import com.ht.front.pages.statisticalanalysis.StatisticAlanalysisPage;
import com.ht.persistence.model.statisticalanalysis.view.CompilationCompleteStatusView;
import com.ht.service.inter.statisticalanalysis.CompilationCompleteStatusService;

/**
 * 港口航道图完成情况Action
 * @author zhongquanyou
 * 
 */
@SuppressWarnings("serial")
public class StatisticAlanalysisAction extends BaseAction {
	
	@Resource(name = "compilationCompleteStatusService")
	CompilationCompleteStatusService compilationCompleteStatusService;
	
	/**
	 * 初始化港口航道图完成情况数据页面，返回成功列表页面
	 * */
	public String initCompletionStatus() {
		StatisticAlanalysisPage sap = new StatisticAlanalysisPage();
		//将获取的节点字符串返回到前台页面
		request.setAttribute("html", sap.getListPage());
		return SUCCESS;
	}
	
	/**
	 * 获取所有港口航道图完成情况数据
	 */
	public void getCompletionStatus(){
		String startTime = getParam("startTime");
		String endTime = getParam("endTime");
		String year = getParam("year");
		try {
			// 根据选择的年月，执行查询操作
			List<CompilationCompleteStatusView> list = compilationCompleteStatusService.getCompilationCompleteStatus(startTime,endTime,year);
			
			if(list != null){
				if(list.size()>0){
					Iterator<CompilationCompleteStatusView> it = list.iterator();
					while(it.hasNext()){
						CompilationCompleteStatusView x = it.next();
					    if(x.getCompleteDate() == null){
					        it.remove();
					    }
					}
				}
			}
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
			// 获取参数
			String completionStatus = getParam("completionStatus");
			compilationCompleteStatusService.export(completionStatus,respose);
		} catch (Exception e) {
			LogHelper.ERROR.log(e.getMessage(),e);
			writeFailResult(e.getMessage());
		}
	}
	
	
	public static void main(String[] args) {
		try {
			Socket s = new Socket("127.0.0.1", 8088);
			InetAddress i = s.getLocalAddress();
			System.out.println(i.getCanonicalHostName());
			s.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
