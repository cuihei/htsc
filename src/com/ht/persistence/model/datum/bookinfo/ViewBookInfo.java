package com.ht.persistence.model.datum.bookinfo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ht.persistence.model.base.BaseModel;

/**
 * 图书资料视图实体类
 * @author zyd
 *
 */
@Entity
@Table(name = "V_BOOK_INFO")
public class ViewBookInfo extends BaseModel {
	
	private String code;	// 图书编号
	@Id
	@Column(name = "id", length = 64)
	private String id;	// id标识
	
	@Column(name = "book_name", length = 100)
	private String bookName;	// 图书名称
	
	@Column(name = "one_subclass", length = 64)
	private String oneSubClass;	// 一级子类
	
	@Column(name = "two_subclass", length = 64 )
	private String twoSubClass;	// 二级子类

	@Column(name = "inventory_num", length = 32 )
	private String inventoryNum;	// 在库数量
	
	@Column(name = "total", length = 32 )
	private String total;	// 库存总数
	
	@Column(name = "can_borrowing", length = 32 )
	private String canBorrowing;	// 库存总数
	
	@Column(name = "publish_date")
	private Date publishDate;	// 出版年月

	@Column(name = "version", length = 10 )
	private String version;	// 版本号
	
	@Column(name = "save_place", length = 100 )
	private String savePlace;	// 存储位置

	@Column(name = "publish_unit", length = 100 )
	private String publishUnit;	// 出版单位

	@Column(name = "state", length = 8 )
	private String state;	// 状态

	@Column(name = "entry", length = 32 )
	private String entry;	// 录入者

	@Column(name = "reviewers", length = 32 )
	private String reviewers;	// 审核者
	

	
	@Column(name = "status", length = 32 )
	private String status;	// 审核状态

	@Column(name = "remarks", length = 32 )
	private String remarks;	// 备注
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getOneSubClass() {
		return oneSubClass;
	}

	public void setOneSubClass(String oneSubClass) {
		this.oneSubClass = oneSubClass;
	}

	public String getTwoSubClass() {
		return twoSubClass;
	}

	public void setTwoSubClass(String twoSubClass) {
		this.twoSubClass = twoSubClass;
	}

	public String getInventoryNum() {
		return inventoryNum;
	}

	public void setInventoryNum(String inventoryNum) {
		this.inventoryNum = inventoryNum;
	}

	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}


	public String getSavePlace() {
		return savePlace;
	}

	public void setSavePlace(String savePlace) {
		this.savePlace = savePlace;
	}

	public String getPublishUnit() {
		return publishUnit;
	}

	public void setPublishUnit(String publishUnit) {
		this.publishUnit = publishUnit;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getEntry() {
		return entry;
	}

	public void setEntry(String entry) {
		this.entry = entry;
	}

	public String getReviewers() {
		return reviewers;
	}

	public void setReviewers(String reviewers) {
		this.reviewers = reviewers;
	}

	public String getTotal()
	{
		return total;
	}

	public String getCanBorrowing()
	{
		return canBorrowing;
	}

	public void setTotal(String total)
	{
		this.total = total;
	}

	public void setCanBorrowing(String canBorrowing)
	{
		this.canBorrowing = canBorrowing;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
}
