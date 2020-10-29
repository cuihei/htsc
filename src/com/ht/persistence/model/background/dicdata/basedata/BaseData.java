package com.ht.persistence.model.background.dicdata.basedata;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ht.common.export.ExcelAttribute;
import com.ht.persistence.model.base.BaseModel;

/**
 * basedata 基础数据实体类
 * @author 刘凯
 */
@Entity
@Table(name = "BASE_DATA")
public class BaseData extends BaseModel{
	
	//主键id
	@Id
	@Column(name = "id", length = 64)
	private String id;
	
	//基础数据编码
	@Column(name = "code", length = 100, nullable = false)
	private String code;
	
	//基础数据名称
	@Column(name = "value", length = 64, nullable = false)
	@ExcelAttribute(name = "类别名称") 
	private String value;
	
	//类别
	@Column(name = "type_id", length = 64, nullable = false)
	private String typeId;
	
	//类别名称
	@Column(name = "type_name", length = 64, nullable = false)
	private String typeName;
	
	//排序
	@Column(name = "num", length = 64)
	private String num;
	
	@Column(name = "creator", length = 64)
	@ExcelAttribute(name = "创建人") 
	private String creator;// 创建者
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8") 
	@Column(name = "creation_date")
	@ExcelAttribute(name = "创建时间") 
	private Date creationDate;// 创建时间
	@Column(name = "modifier", length = 64)
	private String modifier;// 更新者
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8") 
	@Column(name = "modify_date")
	private Date modifyDate;// 更新时间
	
	public BaseData(String id, String value) {  
       super();  
       this.id = id;  
       this.value = value;  
   }  
	public BaseData(){
		 super();  
	}
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

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	
}
