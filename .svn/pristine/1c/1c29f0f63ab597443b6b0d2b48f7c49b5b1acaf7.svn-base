package com.ht.persistence.model.drawtask.taskbook.create;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.ht.common.export.ExcelAttribute;
import com.ht.persistence.model.base.BaseModel;

/**
 * 编绘任务书model
 * @author penghao
 */
@Entity
@Table(name = "V_COMPILATION_CHILD_TASK")
public class ViewCreateTask extends BaseModel
{

	private String id; // 主键
	private String parentTaskbookId; // 任务书id
	private String taskbookName; // 任务书name
	@ExcelAttribute(name = "任务类型", isMark = true)
	private String taskType;
	@ExcelAttribute(name = "子类型", isMark = true)
	private String childTaskType; // 子任务类型
	private String childTaskTypeName; // 子任务类型
	@ExcelAttribute(name = "任务名称", isMark = true)
	private String taskName; // 任务名称
	@ExcelAttribute(name = "图号")
	private String mapNo; // 图号
	@ExcelAttribute(name = "比例尺")
	private String scale; // 比例尺
	@ExcelAttribute(name = "版次")
	private String revision; // 版次
	@ExcelAttribute(name = "年")
	private String year; // 年
	@ExcelAttribute(name = "月")
	private String month; // 月
	private String yearMonth; // 年月
	@ExcelAttribute(name = "状态")
	private String status;
	@ExcelAttribute(name = "发布次数")
	private Integer publishTimes;
	public String getTaskType()
	{
		return taskType;
	}

	public void setTaskType(String taskType)
	{
		this.taskType = taskType;
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getParentTaskbookId()
	{
		return parentTaskbookId;
	}

	public void setParentTaskbookId(String parentTaskbookId)
	{
		this.parentTaskbookId = parentTaskbookId;
	}

	public String getTaskName()
	{
		return taskName;
	}

	public void setTaskName(String taskName)
	{
		this.taskName = taskName;
	}

	public String getMapNo()
	{
		return mapNo;
	}

	public void setMapNo(String mapNo)
	{
		this.mapNo = mapNo;
	}

	public String getScale()
	{
		return scale;
	}

	public void setScale(String scale)
	{
		this.scale = scale;
	}

	public String getRevision()
	{
		return revision;
	}

	public void setRevision(String revision)
	{
		this.revision = revision;
	}

	public String getYear()
	{
		return year;
	}

	public void setYear(String year)
	{
		this.year = year;
	}

	public String getMonth()
	{
		return month;
	}

	public void setMonth(String month)
	{
		this.month = month;
	}

	public String getYearMonth()
	{
		return yearMonth;
	}

	public void setYearMonth(String yearMonth)
	{
		this.yearMonth = yearMonth;
	}

	public String getChildTaskType()
	{
		return childTaskType;
	}

	public void setChildTaskType(String childTaskType)
	{
		this.childTaskType = childTaskType;
	}

	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}

	public String getChildTaskTypeName()
	{
		return childTaskTypeName;
	}

	public void setChildTaskTypeName(String childTaskTypeName)
	{
		this.childTaskTypeName = childTaskTypeName;
	}

	public String getTaskbookName()
	{
		return taskbookName;
	}

	public void setTaskbookName(String taskbookName)
	{
		this.taskbookName = taskbookName;
	}

	public Integer getPublishTimes()
	{
		return publishTimes;
	}

	public void setPublishTimes(Integer publishTimes)
	{
		this.publishTimes = publishTimes;
	}
}
