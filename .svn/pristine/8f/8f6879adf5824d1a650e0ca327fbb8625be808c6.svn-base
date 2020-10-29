package com.ht.persistence.model.background.authority.auth;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ht.persistence.model.base.BaseModel;

/**
 * Auth 权限实体类
 * @author 侯晨
 */
@Entity
@Table(name = "AUTH")
public class Auth extends BaseModel{
	
	//主键id
	@Id
	@Column(name = "id", length = 64)
	private String id;
	
	//权限编号
	@Column(name = "auth_no", length = 100)
	private String authNo;
	
	//权限名称
	@Column(name = "auth_name", length = 64, nullable = false)
	private String authName;
	
	//权限地址
	@Column(name = "auth_url", length = 256 )
	private String authUrl;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAuthNo() {
		return authNo;
	}

	public void setAuthNo(String authNo) {
		this.authNo = authNo;
	}

	public String getAuthName() {
		return authName;
	}

	public void setAuthName(String authName) {
		this.authName = authName;
	}

	public String getAuthUrl() {
		return authUrl;
	}

	public void setAuthUrl(String authUrl) {
		this.authUrl = authUrl;
	}

	@Override
	public String toString() {
		return "Auth [id=" + id + ", authNo=" + authNo + ", authName="
				+ authName + ", authUrl=" + authUrl + "]";
	}
	
	
}
