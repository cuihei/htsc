package com.ht.persistence.model.system.workflow.assign;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.ht.persistence.model.base.BaseModel;

@Entity
@Table(name = "AssignUserRecord")
public class AssignUserRecord extends BaseModel{
	private String id;
	private String hisUser;//历史负责人
	private String assignUser;//指派的人员
	private String processInstId;//流程实例ID
	private String type;//用户类型，user_bhy,user_zjy,user_sdy
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getHisUser() {
		return hisUser;
	}
	public void setHisUser(String hisUser) {
		this.hisUser = hisUser;
	}
	public String getAssignUser() {
		return assignUser;
	}
	public void setAssignUser(String assignUser) {
		this.assignUser = assignUser;
	}
	public String getProcessInstId() {
		return processInstId;
	}
	public void setProcessInstId(String processInstId) {
		this.processInstId = processInstId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "AssignUserRecord [id=" + id + ", hisUser=" + hisUser
				+ ", assignUser=" + assignUser + ", processInstId="
				+ processInstId + ", type=" + type + "]";
	}
}
