package com.ht.persistence.model.statisticalanalysis.view;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ht.persistence.model.base.BaseModel;

/**
 * 任务书视图实体类
 * 
 * @author 刘凯
 */
@Entity
@Table(name = "V_COMPILATION_CHILD_TASK")
public class CompilationChildTaskView extends BaseModel{

	@Id
	@Column(name = "id", length = 64)
	private String id;

	@Column(name = "parent_taskbook_id", length = 64)
	private String parentTaskbookId;
	
	@Column(name = "task_name", length = 64)
	private String taskName;
	
	@Column(name = "map_no", length = 64)
	private String mapNo;

	// 比例尺
	@Column(name = "scale", length = 64)
	private String scale;

	@Column(name = "revision", length = 200)
	private String revision;
	
	@Column(name = "year", length = 200)
	private String year;
	
	@Column(name = "month", length = 200)
	private String month;
	
	@Column(name = "task_type", length = 200)
	private String taskType;
	
	@Column(name = "child_task_type", length = 200)
	private String childTaskType;
	
	@Column(name = "status", length = 200)
	private String status;
	
	@Column(name = "publish_times", length = 200)
	private String publishTimes;
	
	@Column(name = "taskbook_name", length = 200)
	private String taskbookName;
	
	@Column(name = "taskbook_no", length = 200)
	private String taskbookNo; // 任务书序号
	
	/** 工作流任务创建时间 */


	@Column(name = "ISSUE_TIME", length = 200)
	private Date issueTime;
	

		public Date getIssueTime() {
		return issueTime;
	}

	public void setIssueTime(Date issueTime) {
		this.issueTime = issueTime;
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

	public String getTaskType()
	{
		return taskType;
	}

	public void setTaskType(String taskType)
	{
		this.taskType = taskType;
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

	public String getPublishTimes()
	{
		return publishTimes;
	}

	public void setPublishTimes(String publishTimes)
	{
		this.publishTimes = publishTimes;
	}

	public String getTaskbookName()
	{
		return taskbookName;
	}

	public void setTaskbookName(String taskbookName)
	{
		this.taskbookName = taskbookName;
	}

	public String getTaskbookNo()
	{
		return taskbookNo;
	}

	public void setTaskbookNo(String taskbookNo)
	{
		this.taskbookNo = taskbookNo;
	}
}
