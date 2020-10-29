package com.ht.persistence.model.system.notice;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ht.persistence.model.base.BaseModel;

/**
 *  UserNoticeView人员通知关系视图类
 * 
 * @author 平子金
 */
@Entity
@Table(name = "V_USER_NOTICE")
public class UserNoticeView extends BaseModel{
	@Id
	@Column(name = "id", length = 64)
	private String id;//标识
	
	@Column(name = "notice_id", length = 64)
	private String noticeId;
	
	@Column(name = "title", length = 64)
	private String title;
	
	@Column(name = "description", length = 64)
	private String description;
	
	@Column(name = "user_id", length = 64)
	private String userId;
	
	@Column(name = "user_no", length = 64)
	private String user_no;
	
	@Column(name = "user_name", length = 64)
	private String userName;
	
	@Column(name = "release_time", length = 64)
	private String releaseTime;
	
	@Column(name = "is_read", length = 64)
	private String isRead;
	
	@Column(name = "creator", length = 64)
	private String creator;
	
	@Column(name = "publish_user", length = 64)
	private String publishUser;
	
	@Column(name = "notice_type", length = 64)
	private String noticeType;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getReleaseTime() {
		return releaseTime;
	}

	public void setReleaseTime(String releaseTime) {
		this.releaseTime = releaseTime;
	}

	public String getIsRead() {
		return isRead;
	}

	public void setIsRead(String isRead) {
		this.isRead = isRead;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUser_no() {
		return user_no;
	}

	public void setUser_no(String user_no) {
		this.user_no = user_no;
	}

	public String getPublishUser() {
		return publishUser;
	}

	public void setPublishUser(String publishUser) {
		this.publishUser = publishUser;
	}

	public String getNoticeId() {
		return noticeId;
	}

	public void setNoticeId(String noticeId) {
		this.noticeId = noticeId;
	}

	public String getNoticeType() {
		return noticeType;
	}

	public void setNoticeType(String noticeType) {
		this.noticeType = noticeType;
	}
	
	
}
