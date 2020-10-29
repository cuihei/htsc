package com.ht.persistence.model.system.workflow.process;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ht.persistence.model.base.BaseModel;

@Entity
@Table(name = "PROCESS_LOG_DETAIL")
public class ProcessLogDetail extends BaseModel{
	
	@Id
	@Column(name = "ID")
	private String id;
	
	//工作流操作日志主键
	@Column(name = "PROCESS_LOG_ID")
	private String processLogId;
	
	//操作表
	@Column(name = "TABLE_NAME")
	private String tableName;
	
	//操作的记录主键
	@Column(name = "DETAIL_RECORD_ID")
	private String detailRecordId;
	
	//流程实例ID
	@Column(name = "PROCESS_INST_ID")
	private String processInstId;

	public ProcessLogDetail() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProcessLogId() {
		return processLogId;
	}

	public void setProcessLogId(String processLogId) {
		this.processLogId = processLogId;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getDetailRecordId() {
		return detailRecordId;
	}

	public void setDetailRecordId(String detailRecordId) {
		this.detailRecordId = detailRecordId;
	}

	public String getProcessInstId() {
		return processInstId;
	}

	public void setProcessInstId(String processInstId) {
		this.processInstId = processInstId;
	}
}
