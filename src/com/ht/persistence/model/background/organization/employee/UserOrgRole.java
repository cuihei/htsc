package com.ht.persistence.model.background.organization.employee;

/**
 * 用户-组织机构-角色关系临时类
 * @author zqy
 * @date 2016/12/6
 */
public class UserOrgRole {

	private String id;				//用户id
	private String userName;		//用户名称
	private String userNo;			//用户编号
	private String password;		//登陆密码
	private String userImg;			//图片
	private String orgId;			//组织机构ID
	private String roleId;			// 角色ID
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
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	
}
