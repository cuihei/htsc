package com.ht.workflow.common;

import java.util.List;

import com.ht.persistence.model.base.BaseModel;

/**
 * 流程任务定义
 * @author 王有为
 * @date 2016/10/26
 */
public class TaskDef extends BaseModel{
	/**
	 * 任务定义ID
	 */
	String taskDefId = null;
	/**
	 * 任务定义名称
	 */
	String taskDefName = null;
	/**
	 * 流程定义ID
	 */
	String processDefId = null;
	String processDefKey = null;
	/**
	 * 流程定义名称
	 */
	String processDefName = null;
	/**
	 * 任务选择审批组
	 */
	List<String> group = null;
	
	public String getTaskDefId() {
		return taskDefId;
	}
	public void setTaskDefId(String taskDefId) {
		this.taskDefId = taskDefId;
	}
	public String getTaskDefName() {
		return taskDefName;
	}
	public void setTaskDefName(String taskDefName) {
		this.taskDefName = taskDefName;
	}
	public String getProcessDefId() {
		return processDefId;
	}
	public void setProcessDefId(String processDefId) {
		this.processDefId = processDefId;
	}
	public String getProcessDefName() {
		return processDefName;
	}
	public void setProcessDefName(String processDefName) {
		this.processDefName = processDefName;
	}
	public List<String> getGroup() {
		return group;
	}
	public void setGroup(List<String> group) {
		this.group = group;
	}
	public String getProcessDefKey()
	{
		return processDefKey;
	}
	public void setProcessDefKey(String processDefKey)
	{
		this.processDefKey = processDefKey;
	}
}
