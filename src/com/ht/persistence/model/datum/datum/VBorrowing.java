package com.ht.persistence.model.datum.datum;

import java.util.Date;

import javax.persistence.Id;

import com.ht.persistence.model.base.BaseModel;

/**
 * 资料借阅实体类
 * @author zyd
 *
 */
public class VBorrowing extends BaseModel {
	
	@Id
	private String id; //标识
	
	private String folderId; //文件夹Id
	
	private String borrowCode; //图书编号

	private String borrowBookName; //图书名称

	private String borrowPerson; //借阅人

	private Date borrowDate; //借阅时间

	private String borrowNo; //借阅数量
	
	private String shouldReturn; //借阅数量
	
	private String status; //借阅状态
	
	private String reNew; //是否续借

	private String renewDays; //续借天数
	
	private String type; //资料类型
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFolderId() {
		return folderId;
	}

	public void setFolderId(String folderId) {
		this.folderId = folderId;
	}

	public String getBorrowCode()
	{
		return borrowCode;
	}

	public void setBorrowCode(String borrowCode)
	{
		this.borrowCode = borrowCode;
	}

	public String getBorrowBookName() {
		return borrowBookName;
	}

	public void setBorrowBookName(String borrowBookName) {
		this.borrowBookName = borrowBookName;
	}

	public String getBorrowPerson() {
		return borrowPerson;
	}

	public void setBorrowPerson(String borrowPerson) {
		this.borrowPerson = borrowPerson;
	}

	public Date getBorrowDate() {
		return borrowDate;
	}

	public void setBorrowDate(Date borrowDate) {
		this.borrowDate = borrowDate;
	}

	public String getBorrowNo() {
		return borrowNo;
	}

	public void setBorrowNo(String borrowNo) {
		this.borrowNo = borrowNo;
	}

	public String getReNew() {
		return reNew;
	}

	public void setReNew(String reNew) {
		this.reNew = reNew;
	}

	public String getRenewDays() {
		return renewDays;
	}

	public void setRenewDays(String renewDays) {
		this.renewDays = renewDays;
	}

	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}

	public String getShouldReturn() {
		return shouldReturn;
	}

	public void setShouldReturn(String shouldReturn) {
		this.shouldReturn = shouldReturn;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}
