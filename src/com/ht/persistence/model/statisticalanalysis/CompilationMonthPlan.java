package com.ht.persistence.model.statisticalanalysis;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ht.persistence.model.base.BaseModel;

/**
 * 月度编绘计划实体类
 * 
 * @author zqy
 */
@Entity
@Table(name = "COMPILATION_MONTH_PLAN")
public class CompilationMonthPlan extends BaseModel {

	@Id
	@Column(name = "id", length = 64)
	private String id;

	// 计划ID
	@Column(name = "plan_id", length = 64)
	private String planId;

	// 性质
	@Column(name = "property", length = 100)
	private String property;

	// 备注
	@Column(name = "remarks", length = 200)
	private String remarks;
	
	// 任务书id
	@Column(name = "task_book_id", length = 64)
	private String taskBookId;
	
	// 年份
	@Column(name = "year", length = 64)
	private String year;
	
	//月份
	@Column(name = "month", length = 64)
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

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
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
