package com.ht.persistence.model.system.notice;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ht.persistence.model.base.BaseModel;
/**
 * Notice 通知类
 * @author 平子金
 */
@Entity
@Table(name = "NOTICE")
public class Notice extends BaseModel{
	@Id
	@Column(name = "id", length = 64)
	private String id;// 标识
	
	@Column(name = "title", length = 100)
	private String title;// 标题
	
	@Column(name = "description", length = 64)
	private String description;// 描述
	
	@Column(name = "notice_Type", length = 64)
	private String notice_Type;// 通知类型
	
	@Column(name = "user_id")
	private String user_id;//人员id
	
	@Column(name = "user_name")
	private String user_name;//人员名称

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

	public String getNotice_Type() {
		return notice_Type;
	}

	public void setNotice_Type(String notice_Type) {
		this.notice_Type = notice_Type;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
}
