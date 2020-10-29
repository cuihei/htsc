package com.ht.action.system.workflow.process;

import javax.annotation.Resource;

import com.ht.action.base.BaseAction;
import com.ht.service.inter.system.workflow.process.ProcessUserService;
import com.ht.workflow.service.IWorkflowService;

/**
 * 流程受理组/人操作类
 * 
 * @author 王有为
 * @date 2016/10/19
 */
public class AssineeAction extends BaseAction {

	@Resource
	IWorkflowService service;

	@Resource
	private ProcessUserService processUserService;


	/**
	 * 增加审批组
	 */
	public void addGroup() {
		// 获取组名称
		String groupName = getParam("groupName");
		try {
			// 创建用户组
			service.createGroup(groupName);
			// 写成功结果
			writeSuccessResult();
		} catch (Exception e) {
			// 写失败结果
			writeFailResult("新建审批组失败,原因：" + e.getMessage());
		}
	}

	/**
	 * 删除用户组
	 */
	public void removeGroup() {
		// 获取组名称
		String groupId = getParam("groupId");
		try {
			// 删除用户组
			service.deleteGroup(groupId);
			// 写成功结果
			writeSuccessResult();
		} catch (Exception e) {
			// 写失败结果
			writeFailResult("删除审批组失败,原因：" + e.getCause().getMessage());
		}
	}
}
