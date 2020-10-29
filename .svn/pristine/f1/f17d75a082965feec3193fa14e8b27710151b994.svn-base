package com.ht.persistence.model.background.authority.role;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ht.persistence.model.base.BaseModel;

/**
 * Role 角色类
 * @author 侯晨
 */
@Entity
@Table(name = "ROLE")
public class Role extends BaseModel{
	@Id
	@Column(name = "id", length = 64)
	private String id;
	
	//角色编号
	@Column(name = "role_no", length = 100)
	private String roleNo;
	
	//角色名称
	@Column(name = "role_name", length = 100, nullable = false)
	private String roleName;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRoleNo() {
		return roleNo;
	}

	public void setRoleNo(String roleNo) {
		this.roleNo = roleNo;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

}
