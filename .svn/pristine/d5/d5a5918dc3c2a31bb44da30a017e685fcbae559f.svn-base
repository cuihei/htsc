package com.ht.persistence.model.statisticalanalysis;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ht.persistence.model.base.BaseModel;

/**
 * 编绘成果汇总实体类
 * 
 * @author zqy
 */
@Entity
@Table(name = "COMPILATION_RESULTS_SUMMARY")
public class CompilationResultsSummary extends BaseModel {

	@Id
	@Column(name = "id", length = 64)
	private String id;

	// 计划ID
	@Column(name = "plan_id", length = 64)
	private String planId;

	// 性质
	@Column(name = "property", length = 100)
	private String property;

	// 计划汇交时间
	@Column(name = "plan_exchange_time")
	private Date planExchangeTime;

	// 实际汇交时间
	@Column(name = "actual_exchange_time")
	private Date actualExchangeTime;

	// 汇交数量 月/累计（幅）
	@Column(name = "summary_number")
	private String summaryNumber;
	
	// 任务书id
	@Column(name = "task_book_id")
	private String taskBookId;
	
	// 年份
	@Column(name = "year")
	private String year;
		
	// 月份
	@Column(name = "month")
	private String month;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPlanId() {
		return planId;
	}

	public void setPlanId(String planId) {
		this.planId = planId;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public Date getPlanExchangeTime() {
		return planExchangeTime;
	}

	public void setPlanExchangeTime(Date planExchangeTime) {
		this.planExchangeTime = planExchangeTime;
	}

	public Date getActualExchangeTime() {
		return actualExchangeTime;
	}

	public void setActualExchangeTime(Date actualExchangeTime) {
		this.actualExchangeTime = actualExchangeTime;
	}

	public String getSummaryNumber() {
		return summaryNumber;
	}

	public void setSummaryNumber(String summaryNumber) {
		this.summaryNumber = summaryNumber;
	}

	public String getTaskBookId() {
		return taskBookId;
	}

	public void setTaskBookId(String taskBookId) {
		this.taskBookId = taskBookId;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}
	
}
