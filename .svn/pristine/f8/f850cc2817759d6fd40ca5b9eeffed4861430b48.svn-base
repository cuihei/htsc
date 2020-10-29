package com.ht.persistence.model.complication.formprop;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ht.persistence.model.base.BaseModel;

/**
 * FormProp 表单属性表
 * @author houchen
 */
@Entity
@Table(name = "V_FORM_PROP_FORM")
public class FormPropFormView extends BaseModel{
	//标识
	@Id
	@Column(name = "id", length = 64)
	private String id;
	
	//formId
	@Column(name = "form_id", length = 64)
	private String formId ;
	
	//formName
	@Column(name = "form_name", length = 64)
	private String formName ;
	
	//属性键
	@Column(name = "prop_key", length = 64)
	private String propKey;
	
	//属性名称
	@Column(name = "prop_name", length = 64)
	private String propName;
	
	//属性类型
	@Column(name = "prop_type", length = 64)
	private String propType;
	
	//属性默认值
	@Column(name = "prop_default_value", length = 2000)
	private String propDefaultValue;
	
	// 是否必填项
	@Column(name = "REQUIRED", length = 2000)
	private String required;

	// 下拉菜单的值
	@Column(name = "select_data", length = 2000)
	private String selectData;
	
	// 属性默认值
	@Column(name = "num", length = 20)
	private String num;

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

	public String getFormName() {
		return formName;
	}

	public void setFormName(String formName) {
		this.formName = formName;
	}

	public String getPropKey() {
		return propKey;
	}

	public void setPropKey(String propKey) {
		this.propKey = propKey;
	}
	
	public String getPropName() {
		return propName;
	}

	public void setPropName(String propName) {
		this.propName = propName;
	}
	
	public String getPropType() {
		return propType;
	}

	public void setPropType(String propType) {
		this.propType = propType;
	}

	public String getPropDefaultValue() {
		return propDefaultValue;
	}

	public void setPropDefaultValue(String propDefaultValue) {
		this.propDefaultValue = propDefaultValue;
	}

	public String getRequired() {
		return required;
	}

	public void setRequired(String required) {
		this.required = required;
	}

	public String getSelectData() {
		return selectData;
	}

	public void setSelectData(String selectData) {
		this.selectData = selectData;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}
	
}
