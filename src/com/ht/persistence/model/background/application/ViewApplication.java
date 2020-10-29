package com.ht.persistence.model.background.application;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ht.common.export.ExcelAttribute;
import com.ht.persistence.model.base.BaseModel;

/**
 * Application 应用资源实体类
 * @author zqy
 */
@Entity
@Table(name = "V_APPLICATION_BASE_DATA")
public class ViewApplication extends BaseModel{
	
	//主键id
	@Id
	@Column(name = "id", length = 64)
	private String id;
	
	//资源编号
	@Column(name = "app_code", length = 100, nullable = false)
	@ExcelAttribute(name = "资源编号")
	private String appCode;
	
	//资源名称
	@Column(name = "app_name", length = 64, nullable = false)
	@ExcelAttribute(name = "资源名称")
	private String appName;
	
	//资源url
	@Column(name = "app_url", length = 64 )
	@ExcelAttribute(name = "资源路径")
	private String appUrl;
	
	//资源类型
	@Column(name = "app_type", length = 64 )
	@ExcelAttribute(name = "资源类型")
	private String appType;
	
	//资源内容
	@Column(name = "app_content", length = 100)
	@ExcelAttribute(name = "资源内容")
	private String appContent;
	
	//资源父节点
	@Column(name = "app_pid", length = 100)
	private String appParentId;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAppCode() {
		return appCode;
	}

	public void setAppCode(String appCode) {
		this.appCode = appCode;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getAppUrl() {
		return appUrl;
	}

	public void setAppUrl(String appUrl) {
		this.appUrl = appUrl;
	}

	public String getAppType() {
		return appType;
	}

	public void setAppType(String appType) {
		this.appType = appType;
	}

	public String getAppParentId() {
		return appParentId;
	}

	public void setAppParentId(String appParentId) {
		this.appParentId = appParentId;
	}

	public String getAppContent() {
		return appContent;
	}

	public void setAppContent(String appContent) {
		this.appContent = appContent;
	}

}
