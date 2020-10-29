package com.ht.persistence.model.background.organization.organization;


import javax.persistence.Column;
import javax.persistence.Id;

import com.ht.persistence.model.base.BaseModel;

/**
 * 组织机构人员信息类
 * @author 刘凯
 */
public class OrgUsers extends BaseModel{
	@Id
	@Column(name = "id", length = 64)
	private String id;
	
	@Column(name = "user_name", length = 100, nullable = false)
	private String userName;
	
	@Column(name = "user_no", length = 64, nullable = false)
	private String userNo;
	
	@Column(name = "mail", length = 64 )
	private String mail;
	
	@Column(name = "phone", length = 64 )
	private String phone;
	
	@Column(name = "org_id", length = 100)
	private String orgId;

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

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

}
