package com.ht.persistence.model.drawtask.taskbook.book;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.ht.persistence.model.base.BaseModel;

/**
 * 编绘任务书修正记录model
 * @author Weihua
 */
@Entity
@Table(name = "COMPILATION_TASK_BOOK_EIDT_HIS")
public class TaskBookEditHis extends BaseModel
{

	private String id; // 主键
	private String taskbookId; // 任务书ID
	private String taskbookNo; // 任务书编号
	private String editContent; // 修改内容
	private Number revision; // 版次
	private String creator; // 创建人
	private Date creatorDate; // 创建时间
	
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTaskbookId() {
		return taskbookId;
	}
	public void setTaskbookId(String taskbookId) {
		this.taskbookId = taskbookId;
	}
	public String getTaskbookNo() {
		return taskbookNo;
	}
	public void setTaskbookNo(String taskbookNo) {
		this.taskbookNo = taskbookNo;
	}
	public String getEditContent() {
		return editContent;
	}
	public void setEditContent(String editContent) {
		this.editContent = editContent;
	}
	public Number getRevision() {
		return revision;
	}
	public void setRevision(Number revision) {
		this.revision = revision;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public Date getCreatorDate() {
		return creatorDate;
	}
	public void setCreatorDate(Date creatorDate) {
		this.creatorDate = creatorDate;
	}

}
