package com.ht.persistence.model.drawtask.tasksplit;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ht.persistence.model.base.BaseModel;
import com.ht.persistence.model.drawtask.taskbill.TaskBill;
import com.ht.persistence.model.drawtask.taskbook.book.TaskBook;
import com.ht.persistence.model.system.workflow.publish.VProcessDetail;

/**
 * TaskBill 编绘清单model
 * @author yaoping
 */
@Entity
@Table(name = "COMPILATION_TASK")
public class TaskSplit extends BaseModel
{

	@Id
	@Column(name = "id", length = 64)
	private String id; // 主键
	@Column(name = "taskbook_plan_id", length = 100, nullable = false)
	private String taskBookPlanId; // 任务书ID
	private String taskType; // 编绘任务类型
	@Column(name = "status", length = 100, nullable = true)
	private String status; // 编绘任务类型
	private TaskBill taskBill; // 编绘计划
	private String flag; // 标记
	private String typeValue; // 拆分名称
	private String typeId; // 拆分类型
	private TaskBook taskBook; // 编绘计划
	private Set<VProcessDetail> vProcessDetail;// 流程视图

	public Set<VProcessDetail> getvProcessDetail()
	{
		return vProcessDetail;
	}

	public void setvProcessDetail(Set<VProcessDetail> vProcessDetail)
	{
		this.vProcessDetail = vProcessDetail;
	}

	public TaskBook getTaskBook()
	{
		return taskBook;
	}

	public void setTaskBook(TaskBook taskBook)
	{
		this.taskBook = taskBook;
	}

	public String getTypeValue()
	{
		return typeValue;
	}

	public void setTypeValue(String typeValue)
	{
		this.typeValue = typeValue;
	}

	public String getTypeId()
	{
		return typeId;
	}

	public void setTypeId(String typeId)
	{
		this.typeId = typeId;
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getTaskBookPlanId()
	{
		return taskBookPlanId;
	}

	public void setTaskBookPlanId(String taskBookPlanId)
	{
		this.taskBookPlanId = taskBookPlanId;
	}

	public String getTaskType()
	{
		return taskType;
	}

	public void setTaskType(String taskType)
	{
		this.taskType = taskType;
	}

	public TaskBill getTaskBill()
	{
		return taskBill;
	}

	public void setTaskBill(TaskBill taskBill)
	{
		this.taskBill = taskBill;
	}

	public String getFlag()
	{
		return flag;
	}

	public void setFlag(String flag)
	{
		this.flag = flag;
	}

	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}
}
