package com.ht.persistence.model.background.organization.employee;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ht.persistence.model.base.BaseModel;

/**
 * User 人员信息类
 * @author 刘凯
 */
@Entity
@Table(name = "USERS")
public class User extends BaseModel{
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
	
	@Column(name = "password", length = 100)
	private String password;
	
	@Column(name = "user_img", length = 100)
	private String userImg;
	
	@Column(name = "error_count")
	private int errorCount;
	
	@Column(name = "last_error_time")
	private Long lastErrorTime;

//@Column(name = "renew") ///续借模式  0正常  1续借
//	private int renew;
	
	
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserImg() {
		return userImg;
	}

	public void setUserImg(String userImg) {
		this.userImg = userImg;
	}

	public int getErrorCount() {
		return errorCount;
	}

	public void setErrorCount(int errorCount) {
		this.errorCount = errorCount;
	}

	public Long getLastErrorTime() {
		return lastErrorTime;
	}

	public void setLastErrorTime(Long lastErrorTime) {
		this.lastErrorTime = lastErrorTime;
	}


	
}
