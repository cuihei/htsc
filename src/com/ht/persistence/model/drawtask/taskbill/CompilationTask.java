package com.ht.persistence.model.drawtask.taskbill;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ht.persistence.model.base.BaseModel;
/**
 * TaskbookPlanRelation 
 * @author yaoping
 */
@Entity
@Table(name = "COMPILATION_TASK")
public class CompilationTask extends BaseModel{
 
		@Id
		@Column(name = "id", length = 64)
		private String id;                  //主键
		private String taskBookPlanId;  	
		private String taskType;
		
		public String getTaskType() {
			return taskType;
		}
		public void setTaskType(String taskType) {
			this.taskType = taskType;
		}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getTaskBookPlanId() {
			return taskBookPlanId;
		}
		public void setTaskBookPlanId(String taskBookPlanId) {
			this.taskBookPlanId = taskBookPlanId;
		}
	
}
