package com.ht.workflow.common;

import java.util.Date;
import java.util.Map;

/**
 * 工作流任务信息接口
 * @author wyw
 * @version 1.0 2015/06/27
 */
public interface ITaskInfo {
	
	/**
	 * 取得工作流任务执行者名称
	 * @return 工作流任务执行者名称
	 */
	public String getPerformer();
	
	/**
	 * 设置工作流任务执行者名称
	 * @param assignee 工作流任务执行者名称
	 */
	public void setPerformer(String performer);
	
	/**
	 * 取得工作流任务创建时间
	 * @return 工作流任务创建时间
	 */
	public Date getCreateTime();
	
	/**
	 * 设置工作流任务创建时间
	 * @param createTime 工作流任务创建时间
	 */
	public void setCreateTime(Date createTime);
	
	/**
	 * 取得工作流任务代理状态
	 * @return 工作流任务代理状态
	 */
	public String getDelegationState();
	
	/**
	 * 设置工作流任务代理状态
	 * @param delegationState 工作流任务代理状态
	 */
	public void setDelegationState(String delegationState);
	
	/**
	 * 取得工作流任务描述
	 * @return 工作流任务描述
	 */
	public String getDescription();
	
	/**
	 * 设置工作流任务描述
	 * @param description 工作流任务描述
	 */
	public void setDescription(String description);
	
	/**
	 * 取得工作流任务处理日
	 * @return 工作流任务处理日
	 */
	public Date getDueDate();
	
	/**
	 * 设置工作流任务处理日
	 * @param dueDate 工作流任务处理日
	 */
	public void setDueDate(Date dueDate);
	
	/**
	 * 取得工作流任务表单主键
	 * @return 工作流任务表单主键
	 */
	public String getFormKey();
	
	/**
	 * 设置工作流任务表单主键
	 * @param formKey 工作流任务表单主键
	 */
	public void setFormKey(String formKey);
	
	/**
	 * 取得工作流任务ID
	 * @return 工作流任务ID
	 */
	public String getId();
	
	/**
	 * 设置工作流任务ID
	 * @param id 工作流任务ID
	 */
	public void setId(String id);
	
	/**
	 * 取得工作流任务名称
	 * @return 工作流任务名称
	 */
	public String getName();
	
	/**
	 * 设置工作流任务名称
	 * @param name 工作流任务名称
	 */
	public void setName(String name);
	
	/**
	 * 取得工作流任务所有者
	 * @return 工作流任务所有者
	 */
	public String getOwner();
	
	/**
	 * 设置工作流任务所有者
	 * @param owner 工作流任务所有者
	 */
	public void setOwner(String owner);
	
	/**
	 * 取得工作流任务参数列表
	 * @return 工作流任务参数列表
	 */
	public Map<String, Object> getTaskArgumentList();
	
	/**
	 * 设置工作流任务参数列表
	 * @param taskArgumentList 工作流任务参数列表
	 */
	public void setTaskArgumentList(Map<String, Object> taskArgumentList);
	
	/**
	 * 取得工作流参数列表
	 * @return 工作流参数列表
	 */
	public Map<String, Object> getProcessArgumentList();
	
	/**
	 * 设置工作流参数列表
	 * @param processArgumentList 工作流参数列表
	 */
	public void setProcessArgumentList(Map<String, Object> processArgumentList);
	
	/**
	 * 取得工作流定义名称
	 * @return 工作流定义名称
	 */
	public String getProcessDefInitName();

	/**
	 * 设置工作流定义名称
	 * @param processDefInitName 工作流定义名称
	 */
	public void setProcessDefInitName(String processDefInitName);
	
	/**
	 * 取得工作流任务定义ID
	 * @return 工作流任务定义ID
	 */
	public String getTaskDefinitionKey();
	
	/**
	 * 设置工作流任务定义ID
	 * @param taskDefinitionKey 工作流任务定义ID
	 */
	public void setTaskDefinitionKey(String taskDefinitionKey);
	
	/**
	 * 获取父流程ID
	 * @return 父流程ID
	 */
	public String getSuperProcessInstanceId();

	/**
	 * 设置父流程ID
	 * @param superProcessInstanceId 父流程ID
	 */
	public void setSuperProcessInstanceId(String superProcessInstanceId);
}
