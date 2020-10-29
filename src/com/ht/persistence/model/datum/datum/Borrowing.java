package com.ht.persistence.model.datum.datum;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ht.persistence.model.base.BaseModel;

/**
 * 资料借阅实体类
 * @author zyd
 *
 */
@Entity
@Table(name = "BORROWING")
public class Borrowing extends BaseModel {
	
	@Id
	@Column(name = "id", length = 64)
	private String id; //标识
	
	@Column(name = "folder_id", length = 64, nullable = false)
	private String folderId; //文件夹Id
	
	@Column(name = "borrow_code", length = 100, nullable = false)
	private String borrowCode; //图书编号

	@Column(name = "borrow_book_name", length = 64, nullable = false)
	private String borrowBookName; //图书名称

	@Column(name = "borrow_person", length = 100, nullable = false)
	private String borrowPerson; //借阅人
	
	@Column(name = "type", length = 20, nullable = false)
	private String type; //资料类型

	@Column(name = "borrow_date", length = 254, nullable = false)
	private Date borrowDate; //借阅时间

	@Column(name = "borrow_no", length = 254, nullable = false)
	private String borrowNo; //借阅数量
	
	@Column(name = "status", length = 254, nullable = false)
	private String status; //借阅状态
	
	@Column(name = "renew", length = 254, nullable = false)
	private String reNew; //是否续借

	@Column(name = "renew_days", length = 254, nullable = false)
	private String renewDays; //续借天数
	
	@Column(name = "secret_involved", length = 20, nullable = false)
	private String secretInvolved; //是否涉密
	
//	20200817
	
	@Column(name = "concurrent_time", length = 32)
	private Date concurrentTime;	// 汇交时间
	
	public Date getConcurrentTime() {
		return concurrentTime;
	}

	public void setConcurrentTime(Date concurrentTime) {
		this.concurrentTime = concurrentTime;
	}
	
	
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSecretInvolved()
	{
		return secretInvolved;
	}

	public void setSecretInvolved(String secretInvolved)
	{
		this.secretInvolved = secretInvolved;
	}
}
