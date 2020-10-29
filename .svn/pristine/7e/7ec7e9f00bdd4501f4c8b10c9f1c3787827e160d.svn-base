package com.ht.persistence.model.system.workflow.process;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ht.persistence.model.base.BaseModel;

/**
 * 工作流表单详情
 * @author dou
 */
@Entity
@Table(name = "PROCESS_FORM_PROP")
public class ProcessFormProp extends BaseModel{
	
	@Id
	@Column(name = "ID")
	private String id;
	
	// 流程表单关系ID
	@Column(name = "PROCESS_FORM_ID")
	private String processFormId;
	
	// 流程表单属性ID
	@Column(name = "form_prop_id")
	private String formPropId;
	
	// 流程表单属性key
	@Column(name = "prop_key")
	private String propKey;
	
	// 流程表单属性名称
	@Column(name = "prop_name")
	private String propName;
	
	// 流程表单属性类型
	@Column(name = "prop_type")
	private String propType;
	
	// 筛选框数据
	@Column(name = "select_data")
	private String selectData;
	
	// 流程表单属性是否可编辑
	@Column(name = "EDITABLE")
	private String editAble;
	
	// 流程表单属性默认值
	@Column(name = "DEFAULT_VALUE")
	private String defaultValue;
	
	// 流程表单属性默认值委托类
	@Column(name = "DEFAULT_DELEGATE")
	private String defaultDelegate;
	
	// 流程表单属性是否必填
	@Column(name = "REQUIRED")
	private String required;
	
	// 流程表单属性是否必填
	@Column(name = "NUM")
	private String num;
	
	//下拉菜单的值,获取根据字典类型获取字典数据
	@Column(name = "selectDic")
	private String selectDic;

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getProcessFormId()
	{
		return processFormId;
	}

	public void setProcessFormId(String processFormId)
	{
		this.processFormId = processFormId;
	}

	public String getFormPropId()
	{
		return formPropId;
	}

	public void setFormPropId(String formPropId)
	{
		this.formPropId = formPropId;
	}

	public String getEditAble()
	{
		return editAble;
	}

	public void setEditAble(String editable)
	{
		this.editAble = editable;
	}

	public String getDefaultValue()
	{
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue)
	{
		this.defaultValue = defaultValue;
	}

	public String getDefaultDelegate()
	{
		return defaultDelegate;
	}

	public void setDefaultDelegate(String defaultDelegate)
	{
		this.defaultDelegate = defaultDelegate;
	}

	public String getRequired()
	{
		return required;
	}

	public void setRequired(String required)
	{
		this.required = required;
	}

	public String getPropKey()
	{
		return propKey;
	}

	public void setPropKey(String propKey)
	{
		this.propKey = propKey;
	}

	public String getPropName()
	{
		return propName;
	}

	public void setPropName(String propName)
	{
		this.propName = propName;
	}

	public String getPropType()
	{
		return propType;
	}

	public void setPropType(String propType)
	{
		this.propType = propType;
	}

	public String getSelectData()
	{
		return selectData;
	}

	public void setSelectData(String selectData)
	{
		this.selectData = selectData;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getSelectDic() {
		return selectDic;
	}

	public void setSelectDic(String selectDic) {
		this.selectDic = selectDic;
	}
	
}
