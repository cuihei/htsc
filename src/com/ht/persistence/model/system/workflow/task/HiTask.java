package com.ht.persistence.model.system.workflow.task;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 经办任务实体
 */
@Entity
@Table(name = "V_HI_TASK")
public class HiTask
{
	/**
	 * 任务ID
	 */
	private String taskInstId = null;
	
	/**
	 * 流程定义ID
	 */
	private String processDefId = null;
	
	/**
	 * 任务定义Key
	 */
	private String taskDefId = null;
	
	/**
	 * 流程定义名称
	 */
	private String processDefName = null;
	
	/**
	 * 流程定义KEY
	 */
	private String processDefKey = null;
	
	/**
	 * 流程实例ID
	 */
	private String processInstId = null;
	
	/**
	 * 任务执行ID
	 */
	private String executionId = null;
	
	/**
	 * 任务名称
	 */
	private String taskName = null;
	
	/**
	 * 任务受理人
	 */
	private String assignee = null;
	
	/**
	 * 任务受理人名称
	 */
	private String assigneeName = null;
	
	/**
	 * 任务受理人ID
	 */
	private String assigneeId = null;
	
	/**
	 * 任务开始时间
	 */
	private Date startTime = null;
	
	/**
	 * 任务结束时间
	 */
	private Date endTime = null;
	
	/**
	 * 任务领取时间
	 */
	private Date claimTime = null;
	
	/**
	 * 任务删除原因（任务完成等）
	 */
	private String deleteReason = null;
	
	/**
	 * 父流程ID
	 */
	private String parentProcessInstId = null;
	
	/**
	 * 任务受理组ID
	 */
	private String groupId = null;
	
	/**
	 * 任务受理组名称
	 */
	private String groupName = null;
	
	/**
	 * 流程结束时间
	 */
	private Date processEndTime = null;

	/**
	 * 流程的业务记录ID
	 */
	private String recordId = null;

	public String getTaskInstId() {
		return taskInstId;
	}

	public void setTaskInstId(String taskInstId) {
		this.taskInstId = taskInstId;
	}

	public String getProcessDefId()
	{
		return processDefId;
	}

	public void setProcessDefId(String processDefId)
	{
		this.processDefId = processDefId;
	}
	
	public String getTaskDefId()
	{
		return taskDefId;
	}

	public void setTaskDefId(String taskDefId)
	{
		this.taskDefId = taskDefId;
	}

	public String getProcessDefName()
	{
		return processDefName;
	}

	public void setProcessDefName(String processDefName)
	{
		this.processDefName = processDefName;
	}

	public String getProcessDefKey()
	{
		return processDefKey;
	}

	public void setProcessDefKey(String processDefKey)
	{
		this.processDefKey = processDefKey;
	}

	public String getProcessInstId()
	{
		return processInstId;
	}

	public void setProcessInstId(String processInstId)
	{
		this.processInstId = processInstId;
	}

	public String getExecutionId()
	{
		return executionId;
	}

	public void setExecutionId(String executionId)
	{
		this.executionId = executionId;
	}

	public String getTaskName()
	{
		return taskName;
	}

	public void setTaskName(String taskName)
	{
		this.taskName = taskName;
	}

	public String getAssignee()
	{
		return assignee;
	}

	public void setAssignee(String assignee)
	{
		this.assignee = assignee;
	}

	public String getAssigneeName()
	{
		return assigneeName;
	}

	public void setAssigneeName(String assigneeName)
	{
		this.assigneeName = assigneeName;
	}

	
	
	public String getAssigneeId() {
		return assigneeId;
	}

	public void setAssigneeId(String assigneeId) {
		this.assigneeId = assigneeId;
	}

	public Date getStartTime()
	{
		return startTime;
	}

	public void setStartTime(Date startTime)
	{
		this.startTime = startTime;
	}

	public Date getEndTime()
	{
		return endTime;
	}

	public void setEndTime(Date endTime)
	{
		this.endTime = endTime;
	}

	public Date getClaimTime()
	{
		return claimTime;
	}

	public void setClaimTime(Date claimTime)
	{
		this.claimTime = claimTime;
	}

	public String getDeleteReason()
	{
		return deleteReason;
	}

	public void setDeleteReason(String deleteReason)
	{
		this.deleteReason = deleteReason;
	}

	public String getParentProcessInstId()
	{
		return parentProcessInstId;
	}

	public void setParentProcessInstId(String parentProcessInstId)
	{
		this.parentProcessInstId = parentProcessInstId;
	}

	public String getGroupId()
	{
		return groupId;
	}

	public void setGroupId(String groupId)
	{
		this.groupId = groupId;
	}

	public String getGroupName()
	{
		return groupName;
	}

	public void setGroupName(String groupName)
	{
		this.groupName = groupName;
	}

	public Date getProcessEndTime()
	{
		return processEndTime;
	}

	public void setProcessEndTime(Date processEndTime)
	{
		this.processEndTime = processEndTime;
	}

	public String getRecordId()
	{
		return recordId;
	}

	public void setRecordId(String recordId)
	{
		this.recordId = recordId;
	}
}
