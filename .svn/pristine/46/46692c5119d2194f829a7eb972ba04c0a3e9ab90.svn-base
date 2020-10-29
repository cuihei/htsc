package com.ht.persistence.model.base;

import java.util.Date;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonFormat;

public class BaseModel {
	@Column(name = "creator", length = 64)
	private String creator;// 创建者
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8") 
	@Column(name = "creation_date")
	private Date creationDate;// 创建时间
	@Column(name = "modifier", length = 64)
	private String modifier;// 更新者
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8") 
	@Column(name = "modify_date")
	private Date modifyDate;// 更新时间
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public String getModifier() {
		return modifier;
	}
	public void setModifier(String modifier) {
		this.modifier = modifier;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
}
