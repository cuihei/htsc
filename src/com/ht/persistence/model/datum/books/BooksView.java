package com.ht.persistence.model.datum.books;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ht.common.export.ExcelAttribute;
import com.ht.persistence.model.base.BaseModel;

/**
 * 海图视图 实体类
 * @author houchen
 *
 */
@Entity
@Table(name = "V_BOOKS")
public class BooksView extends BaseModel {
	
	private String code;	// 编码
	@Id
	@Column(name = "id", length = 64)
	private String id;	// id标识
	
	@Column(name = "chart_no", length = 64)
	private String chartNo;	// 图号
	
	@Column(name = "chart_name", length = 64)
	private String chartName;	// 图名
	
	@Column(name = "one_sub_class", length = 64 )
	private String oneSubClass;	// 一级子类
	
	@Column(name = "two_sub_class", length = 64 )
	private String twoSubClass;	// 二级子类

	@Column(name = "port", length = 64 )
	private String port;	// 港口地区

	@Column(name = "scale", length = 64)
	private String scale;	// 比例尺

	@Column(name = "publication_date")
	private Date publicationDate;	// 出版年月
	
	@Column(name = "version", length = 64 )
	private String version;	// 版本号

	@Column(name = "save_place", length = 100 )
	private String savePlace;	// 存储位置

	@Column(name = "stock_no", length = 64 )
	private String stockNo;	// 库存数量
	
	@Column(name = "entry", length = 32 )
	private String entry;	// 录入者

	@Column(name = "reviewers", length = 32 )
	private String reviewers;	// 审核者

	@Column(name = "state", length = 32 )
	private String state;	// 状态


	
	@ExcelAttribute(name = "资料总数")
	private String total;		    // 资料总数
	
	@ExcelAttribute(name = "可借数量")
	private String canBorrowing;	// 可借数量
	@Column(name = "status")
	private String status;	// 审核状态
	@Column(name = "remarks")
	private String remarks;	// 备注
	
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getChartNo() {
		return chartNo;
	}

	public void setChartNo(String chartNo) {
		this.chartNo = chartNo;
	}

	public String getChartName() {
		return chartName;
	}

	public void setChartName(String chartName) {
		this.chartName = chartName;
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

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getScale() {
		return scale;
	}

	public void setScale(String scale) {
		this.scale = scale;
	}

	public Date getPublicationDate() {
		return publicationDate;
	}

	public void setPublicationDate(Date publicationDate) {
		this.publicationDate = publicationDate;
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

	public String getStockNo() {
		return stockNo;
	}

	public void setStockNo(String stockNo) {
		this.stockNo = stockNo;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
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

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
}
