package com.ht.persistence.model.background.monitor.accesslog;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ht.persistence.model.base.BaseModel;

/**
 * Syslog 系统访问日志类
 * @author 侯晨
 */
@Entity
@Table(name = "SYSLOG")
public class Syslog extends BaseModel{
	@Id
	@Column(name = "id", length = 64)
	private String id;
	
	//访问者ID
	@Column(name = "handle_id", length = 100)
	private String handleId;
	
	//访问者IP
	@Column(name = "handle_ip", length = 64)
	private String handleIp;
	
	//操作行为
	@Column(name = "handle_behavior", length = 256)
	private String handleBehavior;
	
	//操作结果
	@Column(name = "handle_result", length = 100)
	private String handleResult;
	
	//访问时间
	@Column(name = "handle_time", length = 64) 
	private String handleTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getHandleId() {
		return handleId;
	}

	public void setHandleId(String handleId) {
		this.handleId = handleId;
	}

	public String getHandleIp() {
		return handleIp;
	}

	public void setHandleIp(String handleIp) {
		this.handleIp = handleIp;
	}

	public String getHandleBehavior() {
		return handleBehavior;
	}

	public void setHandleBehavior(String handleBehavior) {
		this.handleBehavior = handleBehavior;
	}

	public String getHandleResult() {
		return handleResult;
	}

	public void setHandleResult(String handleResult) {
		this.handleResult = handleResult;
	}

	public String getHandleTime() {
		return handleTime;
	}

	public void setHandleTime(String handleTime) {
		this.handleTime = handleTime;
	}
	
	
}
