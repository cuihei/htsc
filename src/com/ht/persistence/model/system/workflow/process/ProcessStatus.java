package com.ht.persistence.model.system.workflow.process;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ht.persistence.model.base.BaseModel;

@Entity
@Table(name = "PROCESS_STATUS")
public class ProcessStatus extends BaseModel{
	
	@Id
	@Column(name = "ID")
	private String id;
	
	//表单ID
	@Column(name = "FROM_ID")
	private String formId;
	
	//流程定义ID
	@Column(name = "PROCESS_DEF_ID")
	private String processDefId;
	
	//任务定义ID
	@Column(name = "TASK_DEF_ID")
	private String taskDefId;
	
	//状态
	@Column(name = "STATUS")
	private String status;
	
	public ProcessStatus() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFormId() {
		return formId;
	}

	public void setFormId(String formId) {
		this.formId = formId;
	}

	public String getProcessDefId() {
		return processDefId;
	}

	public void setProcessDefId(String processDefId) {
		this.processDefId = processDefId;
	}

	public String getTaskDefId() {
		return taskDefId;
	}

	public void setTaskDefId(String taskDefId) {
		this.taskDefId = taskDefId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}	
}
