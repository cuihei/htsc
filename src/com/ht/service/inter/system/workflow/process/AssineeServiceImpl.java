package com.ht.service.inter.system.workflow.process;

import java.util.List;

import com.ht.persistence.model.system.workflow.process.HtTask;

/**
 * 流程受理对象（审批组）业务处理类
 * @author 王有为
 * @date 2016/10/17
 */
public interface AssineeServiceImpl {
	
	/**
	 * 增加组
	 * @param groupName 组名称
	 */
	public void addGroup(String groupName);
	
	/**
	 * 移除组
	 * @param groupId 组ID
	 */
	public void removeGroup(String groupId);
	
	/**
	 * 在组内增加用户
	 * @param groupId 组ID
	 * @param userId 人员ID
	 */
	public void allocUserToGroup(String groupId,String userId);
	
	/**
	 * 从组内移除用户
	 * @param groupId 组ID
	 * @param userId 人员ID
	 */
	public void removeUserFromGroup(String groupId,String userId);
	
	/**
	 * 在组内增加用户
	 * @param groupId 组ID
	 * @param userIds 人员ID
	 */
	public void allocUserToGroup(String groupId,List<String> userIds);
	
	/**
	 * 从组内移除用户
	 * @param groupId 组ID
	 * @param userIds 人员ID
	 */
	public void removeUserFromGroup(String groupId,List<String> userIds);
	
	/**
	 * 获取是用指定组的流程任务
	 * @param groupId
	 */
	public List<HtTask> getProcessDefByGroup(String groupId);
}
