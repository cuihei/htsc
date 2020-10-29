package com.ht.persistence.model.system.workflow.process;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ht.persistence.model.base.BaseModel;

@Entity
@Table(name = "PROCESS_USER")
public class ProcessUser extends BaseModel{
	@Id
	@Column(name = "ID")
	private String id;
	
	@Column(name = "PROCESS_DEF_ID")
	private String processDefId;
	
	@Column(name = "PROCESS_DEF_VERSION")
	private Float processDefVersion;
	
	@Column(name = "USER_NO")
	private String userNo;

	@Column(name = "PROCESS_DEF_NAME")
	private String processDefName;
	
	@Column(name = "PROCESS_DEF_KEY")
	private String processDefKey;
	
	@Column(name = "USER_NAME")
	private String userName;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProcessDefId() {
		return processDefId;
	}

	public void setProcessDefId(String processDefId) {
		this.processDefId = processDefId;
	}

	public String getUserNo() {
		return userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	public String getProcessDefName() {
		return processDefName;
	}

	public void setProcessDefName(String processDefName) {
		this.processDefName = processDefName;
	}

	public String getProcessDefKey() {
		return processDefKey;
	}

	public void setProcessDefKey(String processDefKey) {
		this.processDefKey = processDefKey;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Float getProcessDefVersion() {
		return processDefVersion;
	}

	public void setProcessDefVersion(Float processDefVersion) {
		this.processDefVersion = processDefVersion;
	}
}
