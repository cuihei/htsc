package com.ht.persistence.model.system.workflow.process;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ht.persistence.model.base.BaseModel;

/**
 * 任务名称关联表
 * @author dou
 */
@Entity
@Table(name = "PROCESS_TASK_RELATION")
public class ProcessTaskRelation extends BaseModel{
	
	@Id
	@Column(name = "ID")
	private String id;
	
	//流程定义ID
	@Column(name = "PROCESS_DEF_ID")
	private String processDefId;
	
	//流程定义key
	@Column(name = "PROCESS_DEF_KEY")
	private String processDefKey;
	

	//任务定义ID
	@Column(name = "TASK_DEF_ID")
	private String taskDefId;
	
	//任务定义ID
	@Column(name = "RELATION_ID")
	private String relationId;
	
	public ProcessTaskRelation() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProcessDefId() {
		return processDefId;
	}

	public void setProcessDefId(String processDefId) {
		this.processDefId = processDefId;
	}

	public String getProcessDefKey() {
		return processDefKey;
	}

	public void setProcessDefKey(String processDefKey) {
		this.processDefKey = processDefKey;
	}

	public String getTaskDefId() {
		return taskDefId;
	}

	public void setTaskDefId(String taskDefId) {
		this.taskDefId = taskDefId;
	}

	public String getRelationId() {
		return relationId;
	}

	public void setRelationId(String relationId) {
		this.relationId = relationId;
	}
}
