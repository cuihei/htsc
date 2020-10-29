package com.ht.persistence.model.background.monitor.operationlog;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ht.persistence.model.base.BaseModel;

/**
 * SyslogOperation 系统操作日志类
 * @author 刘凯
 */
@Entity
@Table(name = "SYSLOG_OPERATION")
public class SyslogOperation extends BaseModel{
	@Id
	@Column(name = "id", length = 64)
	private String id;
	
	@Column(name = "operator_id", length = 100, nullable = false)
	private String operatorId;
	
	@Column(name = "operator_ip", length = 64, nullable = false)
	private String operatorIp;
	
	@Column(name = "operation_behavior", length = 256 )
	private String operationBehavior;
	
	@Column(name = "operation_result", length = 100 )
	private String operationResult;
	
	@Column(name = "operation_object", length = 100)
	private String operationObject;

	@Column(name = "operation_time", length = 64)
	private String operationTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}

	public String getOperatorIp() {
		return operatorIp;
	}

	public void setOperatorIp(String operatorIp) {
		this.operatorIp = operatorIp;
	}

	public String getOperationBehavior() {
		return operationBehavior;
	}

	public void setOperationBehavior(String operationBehavior) {
		this.operationBehavior = operationBehavior;
	}

	public String getOperationResult() {
		return operationResult;
	}

	public void setOperationResult(String operationResult) {
		this.operationResult = operationResult;
	}

	public String getOperationObject() {
		return operationObject;
	}

	public void setOperationObject(String operationObject) {
		this.operationObject = operationObject;
	}

	public String getOperationTime() {
		return operationTime;
	}

	public void setOperationTime(String operationTime) {
		this.operationTime = operationTime;
	}
	
}
