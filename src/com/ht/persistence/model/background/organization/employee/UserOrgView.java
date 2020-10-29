package com.ht.persistence.model.background.organization.employee;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ht.persistence.model.base.BaseModel;

/**
 * UserOrg 人员组织机构视图类
 * @author 刘凯
 */
@Entity
@Table(name = "V_USER_ORG")
public class UserOrgView extends BaseModel{
	@Id
	@Column(name = "id", length = 64)
	private String id;
	
	@Column(name = "user_name", length = 100)
	private String userName;
	
	@Column(name = "user_no", length = 64)
	private String userNo;
	
	@Column(name = "org_id", length = 64)
	private String orgId;
	
	@Column(name = "org_name", length = 64)
	private String orgName;
	
	@Column(name = "user_img", length = 100)
	private String userImg;
	
	private String roleId;

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserNo() {
		return userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}
	
	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getUserImg()
	{
		return userImg;
	}

	public void setUserImg(String userImg)
	{
		this.userImg = userImg;
	}
}
