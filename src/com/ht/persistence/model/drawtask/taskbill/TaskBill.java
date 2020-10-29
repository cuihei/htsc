package com.ht.persistence.model.drawtask.taskbill;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ht.persistence.model.base.BaseModel;
import com.ht.persistence.model.drawtask.plan.Plan;
import com.ht.persistence.model.drawtask.taskbook.book.TaskBook;
/**
 * TaskBill 编绘清单model
 * @author yaoping
 */
@Entity
@Table(name = "TASKBOOK_PLAN_RELATION")
public class TaskBill extends BaseModel{
 
		@Id	
		@Column(name = "id", length = 64)
		private String id;                  //主键
		private String taskBookId;  		//任务书ID
		private String planId;				//编绘计划ID
		private Plan plan;					//计划
		private TaskBook taskBook;			//编绘计划
		private String flag;				//标记
		private String revision;			//版次
	
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getTaskBookId() {
			return taskBookId;
		}
		public void setTaskBookId(String taskBookId) {
			this.taskBookId = taskBookId;
		}
		public String getPlanId() {
			return planId;
		}
		public void setPlanId(String planId) {
			this.planId = planId;
		}
		public Plan getPlan() {
			return plan;
		}
		public void setPlan(Plan plan) {
			this.plan = plan;
		}
		public TaskBook getTaskBook() {
			return taskBook;
		}
		public void setTaskBook(TaskBook taskBook) {
			this.taskBook = taskBook;
		}
		public String getFlag() {
			return flag;
		}
		public void setFlag(String flag) {
			this.flag = flag;
		}
		public String getRevision() {
			return revision;
		}
		public void setRevision(String revision) {
			this.revision = revision;
		}
}
