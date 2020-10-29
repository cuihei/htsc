package com.ht.persistence.model.system.workflow.process;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ht.persistence.model.base.BaseModel;

@Entity
@Table(name = "PROCESS_LOG")
public class ProcessLog  extends BaseModel{
	
	@Id
	@Column(name = "ID")
	private String id;
	
	//操作人
	@Column(name = "USER_NO")
	private String userNo;
	
	//流程实例ID
	@Column(name = "PROCESS_INST_ID")
	private String processInstId;
	
	//操作表名称
	@Column(name = "TABLE_NAME")
	private String tableName;
	
	//操作类型
	@Column(name = "OPERATION_TYPE")
	private String operationType;
	
	//操作表状态字段名称
	@Column(name = "TABLE_STATUS_NAME")
	private String tableStatusName;
	
	//操作表主键字段名称
	@Column(name = "TABLE_KEY_NAME")
	private String tableKeyName;

	public ProcessLog() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserNo() {
		return userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	public String getProcessInstId() {
		return processInstId;
	}

	public void setProcessInstId(String processInstId) {
		this.processInstId = processInstId;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getOperationType() {
		return operationType;
	}

	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}

	public String getTableStatusName() {
		return tableStatusName;
	}

	public void setTableStatusName(String tableStatusName) {
		this.tableStatusName = tableStatusName;
	}

	public String getTableKeyName() {
		return tableKeyName;
	}

	public void setTableKeyName(String tableKeyName) {
		this.tableKeyName = tableKeyName;
	}
}
