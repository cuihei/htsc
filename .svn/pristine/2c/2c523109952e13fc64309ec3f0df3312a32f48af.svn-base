package com.ht.persistence.model.complication.form;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ht.persistence.model.base.BaseModel;

/**
 * FormBaseDataView 表单类型下的列表类视图
 * @author houchen
 */
@Entity
@Table(name = "V_FORM_BASE_DATA")
public class FormBaseDataView extends BaseModel{
	
	// 标识
	@Id
	@Column(name = "id", length = 64)
	private String id;
	
	// 基础类的类型value
	@Column(name = "value")
	private String value;
	
	// 名称
	@Column(name = "name" , length = 256)
	private String name;
	
	// 表单地址
	@Column(name = "url" , length = 256)
	private String url;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
}
