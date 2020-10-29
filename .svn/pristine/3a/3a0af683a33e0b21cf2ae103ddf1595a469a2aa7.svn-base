package com.ht.persistence.model.system.document.template;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ht.persistence.model.base.BaseModel;

/**
 * Model 模板类
 * @author 平子金
 * */
@Entity
@Table(name="MODEL")
public class Model extends BaseModel{
	@Id
	@Column(name = "id", length = 64)
	private String id;// 标识
	@Column(name = "code", length = 100)
	private String code;// 编码
	@Column(name = "content")
	private String content;// 内容
	@Column(name = "model_type_id",length = 64)
	private String model_type_id;//模板类别id
	@Column(name = "model_type_name",length = 64)
	private String model_type_name;//模板类别名称
	@Column(name = "path",length = 64)
	private String path;//模板路径
	@Column(name = "model_item_id",length = 64)
	private String model_item_id;//模板项
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getModel_type_id() {
		return model_type_id;
	}
	public void setModel_type_id(String model_type_id) {
		this.model_type_id = model_type_id;
	}
	public String getModel_type_name() {
		return model_type_name;
	}
	public void setModel_type_name(String model_type_name) {
		this.model_type_name = model_type_name;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getModel_item_id() {
		return model_item_id;
	}
	public void setModel_item_id(String model_item_id) {
		this.model_item_id = model_item_id;
	}
}
