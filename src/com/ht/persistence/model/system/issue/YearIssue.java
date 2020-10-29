package com.ht.persistence.model.system.issue;

import java.util.Date;

import javax.persistence.Table;

import org.hibernate.annotations.Entity;

import com.ht.persistence.model.base.BaseModel;
/**
 * 改正通告期号时段 基础数据
 * @author huodesheng
 *
 */
@Entity
@Table(name = "YEAR_ISSUE")
public class YearIssue extends BaseModel{
	//id
	private String id;
	//改正期号
	private String issue;
	//年份
	private String year;
	//开始时间
	private Date beginDate;
	//结束时间
	private Date endDate;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIssue() {
		return issue;
	}
	public void setIssue(String issue) {
		this.issue = issue;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public Date getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	@Override
	public String toString() {
		return "YearIssue [id=" + id + ", issue=" + issue + ", year=" + year
				+ ", beginDate=" + beginDate + ", endDate=" + endDate + "]";
	}
	
	
}
