package com.ht.persistence.model.system.workflow.process;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ht.persistence.model.base.BaseModel;

/**
 * 工作流表单详情
 * @author dou
 */
@Entity
@Table(name = "PROCESS_FORM")
public class ProcessForm extends BaseModel
{

	@Id
	@Column(name = "ID")
	private String id;

	// 表单ID
	@Column(name = "FORM_ID")
	private String formId;

	// 流程定义ID
	@Column(name = "PROCESS_DEF_ID")
	private String processDefId;

	// 任务定义ID
	@Column(name = "TASK_DEF_ID")
	private String taskDefId;

	// 排序号
	@Column(name = "ORDER_NO")
	private String orderNo;

	// 数据委托
	@Column(name = "DELEGATE")
	private String delegate;

	public ProcessForm()
	{
		super();
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getFormId()
	{
		return formId;
	}

	public void setFormId(String formId)
	{
		this.formId = formId;
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

	public String getOrderNo()
	{
		return orderNo;
	}

	public void setOrderNo(String orderNo)
	{
		this.orderNo = orderNo;
	}

	public String getDelegate()
	{
		return delegate;
	}

	public void setDelegate(String delegate)
	{
		this.delegate = delegate;
	}
}
