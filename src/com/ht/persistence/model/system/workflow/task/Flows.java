package com.ht.persistence.model.system.workflow.task;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ht.persistence.model.base.BaseModel;

@Entity
@Table(name = "V_PROCESS_FLOW")
public class Flows extends BaseModel
{
	@Id
	@Column(name = "id", length = 64)
	private String id;
	// 流程实例ID
	@Column(name = "process_inst_id", length = 64, nullable = false)
	private String processInstId;
	// 流程定义ID
	@Column(name = "process_def_id", length = 64, nullable = false)
	private String processDefId;
	// 流程名称
	@Column(name = "process_name", length = 255, nullable = false)
	private String processName;
	// 任务ID
	@Column(name = "task_id", length = 64, nullable = false)
	private String taskId;
	// 任务定义ID
	@Column(name = "task_def_id", length = 64, nullable = false)
	private String taskDefId;
	// 任务名称
	@Column(name = "task_name", length = 255, nullable = false)
	private String taskName;
	// 任务结果
	@Column(name = "task_result", length = 64, nullable = false)
	private String taskResult;
	// 类型
	@Column(name = "type", length = 64, nullable = false)
	private String type;
	// 用户ID
	@Column(name = "user_no", length = 64, nullable = false)
	private String userNo;
	// 执行人
	@Column(name = "user_name", length = 100, nullable = false)
	private String userName;
	// 开始时间
	@Column(name = "start_time", nullable = false)
	private Date startTime;
	// 结束时间
	@Column(name = "end_time", nullable = false)
	private Date endTime;
	// 建议
	@Column(name = "advice", length = 500, nullable = false)
	private String advice;
	
	// 备注
	@Column(name = "remark", length = 500, nullable = false)
	private String remark;

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getProcessInstId()
	{
		return processInstId;
	}

	public void setProcessInstId(String processInstId)
	{
		this.processInstId = processInstId;
	}

	public String getProcessDefId()
	{
		return processDefId;
	}

	public void setProcessDefId(String processDefId)
	{
		this.processDefId = processDefId;
	}

	public String getProcessName()
	{
		return processName;
	}

	public void setProcessName(String processName)
	{
		this.processName = processName;
	}

	public String getTaskId()
	{
		return taskId;
	}

	public void setTaskId(String taskId)
	{
		this.taskId = taskId;
	}

	public String getTaskName()
	{
		return taskName;
	}

	public void setTaskName(String taskName)
	{
		this.taskName = taskName;
	}

	public String getTaskResult()
	{
		return taskResult;
	}

	public void setTaskResult(String taskResult)
	{
		this.taskResult = taskResult;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public String getUserNo()
	{
		return userNo;
	}

	public void setUserNo(String userNo)
	{
		this.userNo = userNo;
	}

	public String getUserName()
	{
		return userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
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

	public String getAdvice()
	{
		return advice;
	}

	public void setAdvice(String advice)
	{
		this.advice = advice;
	}

	public String getTaskDefId()
	{
		return taskDefId;
	}

	public void setTaskDefId(String taskDefId)
	{
		this.taskDefId = taskDefId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
