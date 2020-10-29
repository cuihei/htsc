package com.ht.persistence.model.system.notice;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ht.persistence.model.base.BaseModel;

/**
 *  UserNotice人员通知关系类
 * 
 * @author 平子金
 */
@Entity
@Table(name = "USER_NOTICE")
public class UserNotice extends BaseModel {
	@Id
	@Column(name = "id", length = 64)
	private String id;		//标识

	@Column(name = "user_id", length = 100)
	private String user_id;		//人员ID

	@Column(name = "notice_id", length = 64)
	private String notice_id;	//通知ID

	@Column(name = "is_read", length = 64)
	private String is_read;		//是否已读

	@Column(name = "release_time", length = 64)
	private String release_time;		//发布时间

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getNotice_id() {
		return notice_id;
	}

	public void setNotice_id(String notice_id) {
		this.notice_id = notice_id;
	}

	public String getIs_read() {
		return is_read;
	}

	public void setIs_read(String is_read) {
		this.is_read = is_read;
	}

	public String getRelease_time() {
		return release_time;
	}

	public void setRelease_time(String release_time) {
		this.release_time = release_time;
	}
}
