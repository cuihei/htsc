package com.ht.persistence.model.system.document.type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ht.persistence.model.base.BaseModel;

/**
 * ModelType 模板类型类
 * @author 平子金
 * */
@Entity
@Table(name="MODEL_TYPE")
public class ModelType extends BaseModel{
	@Id
	@Column(name = "id", length = 64)
	private String id;// 标识
	@Column(name = "name", length = 64)
	private String name;// 模板类型名称
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
