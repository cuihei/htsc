package com.ht.persistence.model.system.document.template;

import java.io.Serializable;
import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Id;

import com.ht.persistence.model.base.BaseModel;

public class FormDocument extends BaseModel implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "id", length = 64)
	private String id;// 标识
	@Column(name = "name", length = 64)
	private String name;// 模板项名称
	@Column(name = "form_id")
	private String form_id;// 模板关联表单ID
	@Column(name = "file_id")
	private String file_id;//文件ID
	@Column(name = "files")
	private Blob files;//文件
	
	
	
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
	public String getForm_id() {
		return form_id;
	}
	public void setForm_id(String form_id) {
		this.form_id = form_id;
	}
	public String getFile_id() {
		return file_id;
	}
	public void setFile_id(String file_id) {
		this.file_id = file_id;
	}
	public Blob getFiles() {
		return files;
	}
	public void setFiles(Blob files) {
		this.files = files;
	}
}
