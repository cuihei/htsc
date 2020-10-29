package com.ht.persistence.model.background.dicdata.scalecontrol;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ht.common.export.ExcelAttribute;
import com.ht.persistence.model.base.BaseModel;

/**
 * ScaleControl 标准比例尺管理
 * @author 侯晨
 */
@Entity
@Table(name = "SCALE_CONTROL")
public class ScaleControl extends BaseModel{
	@Id
	@Column(name = "id", length = 64)
	private String id;
	
	//原比例尺
	@Column(name = "old_scale", length =64)
	@ExcelAttribute(name = "原比例尺") 
	private String oldScale;
	
	//标准比例尺
	@Column(name = "standard_scale", length =64)
	@ExcelAttribute(name = "标准比例尺") 
	private String standardScale;
	@ExcelAttribute(name = "创建者") 
	private String creator;// 创建者（导出用）
	@ExcelAttribute(name = "创建时间") 
	private Date creationDate;// 创建时间（导出用）
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOldScale() {
		return oldScale;
	}

	public void setOldScale(String oldScale) {
		this.oldScale = oldScale;
	}

	public String getStandardScale() {
		return standardScale;
	}

	public void setStandardScale(String standardScale) {
		this.standardScale = standardScale;
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
	
}
