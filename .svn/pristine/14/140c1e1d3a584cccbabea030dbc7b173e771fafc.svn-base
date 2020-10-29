package com.ht.persistence.model.background.authority.auth;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ht.persistence.model.base.BaseModel;

/**
 * 角色资源关系类
 * @author 刘凯
 */
@Entity
@Table(name = "ROLE_APPLICATION_RELATION")
public class RoleApp extends BaseModel{
	@Id
	@Column(name = "id", length = 64)
	private String id;
	
	//角色主键ID
	@Column(name = "role_id", length = 64, nullable = false)
	private String roleId;
	
	//权限主键ID
	@Column(name = "app_id", length = 64, nullable = false)
	private String appId;

	//写
	@Column(name = "write")
	private Integer write;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public Integer getWrite() {
		return write;
	}

	public void setWrite(Integer write) {
		this.write = write;
	}
}
