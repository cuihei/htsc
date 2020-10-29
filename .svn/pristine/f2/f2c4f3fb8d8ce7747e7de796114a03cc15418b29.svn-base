package com.ht.persistence.model.complication.form;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ht.persistence.model.base.BaseModel;

/**
 * 表单内容 类
 * @author zhongquanyou
 */
@Entity
@Table(name = "FORM_CONTENT")
public class FormContent extends BaseModel{
	// 标识
	@Id
	@Column(name = "id" , length = 64)
	private String id;
	
	// 表单表的ID
	@Column(name = "form_id" ,length = 64)
	private String formId;
	
	// 由表单属性表生成
	@Column(name = "content" ,length = 256)
	private String content;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFormId() {
		return formId;
	}

	public void setFormId(String formId) {
		this.formId = formId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
}
