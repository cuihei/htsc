package com.ht.persistence.model.datum.correctionnoticebook;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ht.common.export.ExcelAttribute;
import com.ht.persistence.model.base.BaseModel;

/**
 * 改正通告编辑资料实体类
 * @author 刘凯
 *
 */
@Entity
@Table(name = "CORRECTION_NOTICE")
public class CorrectionNoticeBook extends BaseModel {
	@Id
	@Column(name = "id", length = 64)
	private String id;	// id标识
	
	@Column(name = "source", length = 64)
	@ExcelAttribute(name = "资料来源",isMark=true) 
	private String source;			
	
	@Column(name = "titanic", length = 64)
	@ExcelAttribute(name = "文号",isMark=true) 
	private String titanic;	
	
	@Column(name = "receive_date", length = 64)
	@ExcelAttribute(name = "收到日期",isMark=true) 
	private Date receiveDate;	// 图名
	
	@Column(name = "content", length = 64 )
	@ExcelAttribute(name = "主要内容",isMark=false) 
	private String content;	// 一级子类
	
	@Column(name = "remark", length = 64 )
	@ExcelAttribute(name = "备注",isMark=false) 
	private String remark;	// 二级子类
	
	@Column(name = "state", length = 64 )
	private String state;	// 资料采用情况
	
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getTitanic() {
		return titanic;
	}

	public void setTitanic(String titanic) {
		this.titanic = titanic;
	}

	public Date getReceiveDate() {
		return receiveDate;
	}

	public void setReceiveDate(Date receiveDate) {
		this.receiveDate = receiveDate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
