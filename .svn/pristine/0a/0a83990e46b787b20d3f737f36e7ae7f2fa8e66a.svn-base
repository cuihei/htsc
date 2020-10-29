package com.ht.persistence.model.background.authority.role;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ht.persistence.model.base.BaseModel;

/**
 * 角色人员类
 * @author 刘凯
 */
@Entity
@Table(name = "ROLE_USERS_RELATION")
public class RoleUsers extends BaseModel{
	@Id
	@Column(name = "id", length = 20)
	private String id;
	
	//角色主键ID
	@Column(name = "role_id", length = 20, nullable = false)
	private String roleId;
	
	//人员编号
	@Column(name = "user_id", length = 20, nullable = false)
	private String userId;

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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
