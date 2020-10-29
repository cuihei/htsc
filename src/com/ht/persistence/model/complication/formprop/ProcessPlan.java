package com.ht.persistence.model.complication.formprop;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ht.persistence.model.base.BaseModel;

/**
 * 编绘计划实体
 */
@Entity
@Table(name = "PROCESS_PLAN")
public class ProcessPlan extends BaseModel
{
	@Id
	@Column(name = "id", length = 64)
	private String id;
	// 流程实例ID
	@Column(name = "process_inst_id", length = 64, nullable = false)
	private String processInstId;
	// 任务ID
	@Column(name = "task_id", length = 64, nullable = false)
	private String taskId;
	// 任务定义ID
	@Column(name = "task_def_id", length = 64, nullable = false)
	private String taskDefId;
	// 流程实例父ID
	@Column(name = "parent_process_inst_id", length = 64, nullable = false)
	private String parentProcessInstId;
	
	@Column(name = "process_bb", length = 20, nullable = false)
	private String processBb;
	// 备注
	@Column(name = "remark", length = 500, nullable = false)
	private String remark;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProcessInstId() {
		return processInstId;
	}

	public void setProcessInstId(String processInstId) {
		this.processInstId = processInstId;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getTaskDefId() {
		return taskDefId;
	}

	public void setTaskDefId(String taskDefId) {
		this.taskDefId = taskDefId;
	}

	public String getParentProcessInstId() {
		return parentProcessInstId;
	}

	public void setParentProcessInstId(String parentProcessInstId) {
		this.parentProcessInstId = parentProcessInstId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getProcessBb() {
		return processBb;
	}

	public void setProcessBb(String processBb) {
		this.processBb = processBb;
	}

	
	
}
