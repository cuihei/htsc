package com.ht.action.drawtask.taskbill;

import java.util.List;

import javax.annotation.Resource;

import com.ht.action.base.BaseAction;
import com.ht.common.util.LogHelper;
import com.ht.front.pages.drawtask.taskbill.TaskBillPage;
import com.ht.persistence.model.drawtask.taskbill.TaskBill;
import com.ht.service.inter.background.organization.organization.OrganizationService;
import com.ht.service.inter.drawtask.taskbill.TaskBillService;
import com.ht.service.inter.drawtask.taskbook.book.TaskBookService;


/**
 * 编绘任务清单Action类 
 * @author yaoping
 */

@SuppressWarnings("serial")
public class TaskBillAction extends BaseAction{
	
	/**
	 * 编绘任务清单service
	 * */
	@Resource
	private TaskBillService taskBillService;
	
	/**
	 * 编绘任务书service
	 * */
	@Resource
	private TaskBookService taskBookService;
	/**
	 * 注入organizationService
	 */
	@Resource(name="organizationService")
	OrganizationService organizationService;
	
	/**
	 * 初始化数据，返回成功列表页面
	 * */
	public String initTaskBill(){
		String flag = this.getParam("flag");
		TaskBillPage tbp = new TaskBillPage();
		//将获取的节点字符串返回到前台页面
		request.setAttribute("html",tbp.getListNode(flag));
		return SUCCESS;
	}
	
	/**
	 * 获取编绘任务清单list
	 */
	public void findList()throws Exception{
		try {
			String flag = this.getParam("flag");
			List<TaskBill> list = taskBillService.getList(flag);
			writeSuccessResult(list);
		} catch (Exception e) {
			// 写入错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			//返回客户端错误消息
			writeFailResult(e.getMessage());
		}
	}
	
	/**
	 * 初始化查看页面数据
	 * */
	public String seeTaskBill(){
		String id = this.getParam("id");
		TaskBillPage tbp = new TaskBillPage();
		//将获取的节点字符串返回到前台页面
		request.setAttribute("html",tbp.getSeeNode(id, taskBookService,organizationService));
		return SUCCESS;
	}
}
