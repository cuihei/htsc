package com.ht.persistence.model.drawtask.taskbook.relation;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ht.persistence.model.base.BaseModel;

/**
 * 任务书和计划关联model
 * @author PeNgHaO
 */
@Entity
@Table(name = "TASKBOOK_PLAN_RELATION")
public class TaskBookPlanRelation extends BaseModel{
 
		@Id
		@Column(name = "id", length = 64)
		private String id;                  //主键
		@Column(name = "taskbook_id", length = 100, nullable = false)
		private String taskbookId;          //任务书id
		@Column(name = "plan_id", length = 64, nullable = false)
		private String planId;			    //计划id
		@Column(name = "revision", length = 64)
		private String revision;			 //版次
		@Column(name = "year", length = 64)
		private String year;			 //年份
		@Column(name = "month", length = 64)
		private String month;			 //月份
		public TaskBookPlanRelation(String taskbookId){
			super();
			this.taskbookId = taskbookId;
		}
		public TaskBookPlanRelation(){
			super();
		} 
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
		public String getPlanId() {
			return planId;
		}
		public void setPlanId(String planId) {
			this.planId = planId;
		}
		public String getRevision() {
			return revision;
		}
		public void setRevision(String revision) {
			this.revision = revision;
		}
		public String getYear() {
			return year;
		}
		public void setYear(String year) {
			this.year = year;
		}
		public String getMonth() {
			return month;
		}
		public void setMonth(String month) {
			this.month = month;
		}
		
}
