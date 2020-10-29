package com.ht.persistence.model.complication.formprop;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ht.persistence.model.base.BaseModel;

/**
 * Form 表单类型下的列表类
 * @author zhongquanyou
 */
@Entity
@Table(name = "V_FORM_FORM_VALUE")
public class FormFormValue extends BaseModel{
	
	// 标识
	@Id
	@Column(name = "id", length = 64)
	private String id;
	
	// 名称
	@Column(name = "name" , length = 256)
	private String name;
	
	//流程任务实例id
	@Column(name = "task_inst_id" , length = 64)
	private String taskInstId;
	
	//流程任务实例id
	@Column(name = "process_inst_id" , length = 64)
	private String processInstId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTaskInstId() {
		return taskInstId;
	}

	public void setTaskInstId(String taskInstId) {
		this.taskInstId = taskInstId;
	}

	public String getProcessInstId() {
		return processInstId;
	}

	public void setProcessInstId(String processInstId) {
		this.processInstId = processInstId;
	}
	
}
