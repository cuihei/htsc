package com.ht.persistence.model.statisticalanalysis;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ht.persistence.model.base.BaseModel;

/**
 * 编绘动态汇总实体类
 * 
 * @author zqy
 */
@Entity
@Table(name = "COMPILATION_DYNAMIC_SUMMARY")
public class CompilationDynamicSummary extends BaseModel {

	@Id
	@Column(name = "id", length = 64)
	private String id;
	
	// 图号
	@Column(name = "map_no", length = 64)
	private String mapNo;
	
	// 图名
	@Column(name = "map_name", length = 64)
	private String mapName;

	// 比例尺
	@Column(name = "scale", length = 64)
	private String scale;

	// 性质	
	@Column(name = "property", length = 100)
	private String property;

	// 计划汇交时间
	@Column(name = "plan_exchange_time")
	private Date planExchangeTime;

	// 实际汇交时间
	@Column(name = "actual_exchange_time")
	private Date actualExchangeTime;

	// 任务下达时间
	@Column(name = "task_release_time")
	private Date taskReleaseTime;

	// 计划完成时间
	@Column(name = "plan_complete_time")
	private Date planCompleteTime;

	// 备注
	@Column(name = "remarks", length = 200)
	private String remarks;
	
	// 任务书id
	@Column(name = "task_book_id", length = 200)
	private String taskBookId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMapNo() {
		return mapNo;
	}

	public void setMapNo(String mapNo) {
		this.mapNo = mapNo;
	}

	public String getMapName() {
		return mapName;
	}

	public void setMapName(String mapName) {
		this.mapName = mapName;
	}

	public String getScale() {
		return scale;
	}

	public void setScale(String scale) {
		this.scale = scale;
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

	public Date getTaskReleaseTime() {
		return taskReleaseTime;
	}

	public void setTaskReleaseTime(Date taskReleaseTime) {
		this.taskReleaseTime = taskReleaseTime;
	}

	public Date getPlanCompleteTime() {
		return planCompleteTime;
	}

	public void setPlanCompleteTime(Date planCompleteTime) {
		this.planCompleteTime = planCompleteTime;
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

}
