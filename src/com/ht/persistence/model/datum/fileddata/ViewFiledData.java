package com.ht.persistence.model.datum.fileddata;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ht.persistence.model.base.BaseModel;

/**
 * 外业数据实体类
 * @author zyd
 *
 */
@Entity
@Table(name = "V_FILED_DATA_USERS")
public class ViewFiledData extends BaseModel {
	
	@Id
	@Column(name = "id", length = 64)
	private String id;	// id标识
	
	@Column(name = "pic_no", length = 64)
	private String picNo;	// 图号

	@Column(name = "sea_area", length = 64)
	private String seaArea;	// 图号

	@Column(name = "scale", length = 32)
	private String scale;	// 比例尺

	@Column(name = "project_name", length = 100)
	private String projectName;	// 项目名称
	
	@Column(name = "measure_cycle", length = 10)
	private String measureCycle;	// 测量周期
	
	@Column(name = "concurrent_time", length = 32)
	private String concurrentTime;	// 汇交时间
	
	@Column(name = "data_name", length = 32)
	private String dataName;	// 数据名称
	
	@Column(name = "data_form", length = 32)
	private String dataForm;	// 数据形式
	
	@Column(name = "original_file_name", length = 32)
	private String originalFileName;	// 原始文件名称
	
	@Column(name = "copies", length = 10)
	private String copies;	// 份数
	
	@Column(name = "total", length = 10)
	private String total;	// 份数
	
	@Column(name = "can_borrowing", length = 10)
	private String canBorrowing;	// 份数

	@Column(name = "state", length = 10)
	private String state;	// 状态

	@Column(name = "recipient", length = 32)
	private String recipient;	// 接收人
	
	@Column(name = "entry", length = 32 )
	private String entry;	// 录入者

	@Column(name = "reviewers", length = 32 )
	private String reviewers;	// 审核者
	
	@Column(name = "status", length = 32 )
	private String status;	// 审核状态

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPicNo() {
		return picNo;
	}

	public void setPicNo(String picNo) {
		this.picNo = picNo;
	}

	public String getScale() {
		return scale;
	}

	public void setScale(String scale) {
		this.scale = scale;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getMeasureCycle() {
		return measureCycle;
	}

	public void setMeasureCycle(String measureCycle) {
		this.measureCycle = measureCycle;
	}

	public String getConcurrentTime() {
		return concurrentTime;
	}

	public void setConcurrentTime(String concurrentTime) {
		this.concurrentTime = concurrentTime;
	}

	public String getDataName() {
		return dataName;
	}

	public void setDataName(String dataName) {
		this.dataName = dataName;
	}

	public String getDataForm() {
		return dataForm;
	}

	public void setDataForm(String dataForm) {
		this.dataForm = dataForm;
	}

	public String getOriginalFileName() {
		return originalFileName;
	}

	public void setOriginalFileName(String originalFileName) {
		this.originalFileName = originalFileName;
	}

	public String getCopies() {
		return copies;
	}

	public void setCopies(String copies) {
		this.copies = copies;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
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

	public String getEntry()
	{
		return entry;
	}

	public void setEntry(String entry)
	{
		this.entry = entry;
	}

	public String getReviewers()
	{
		return reviewers;
	}

	public void setReviewers(String reviewers)
	{
		this.reviewers = reviewers;
	}

	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}

	public String getSeaArea() {
		return seaArea;
	}

	public void setSeaArea(String seaArea) {
		this.seaArea = seaArea;
	}
	
}
