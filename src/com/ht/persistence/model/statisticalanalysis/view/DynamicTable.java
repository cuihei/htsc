package com.ht.persistence.model.statisticalanalysis.view;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;

/**
 * 编绘进度实体类
 */
public class DynamicTable {

	@Id
	@Column(name = "id", length = 64)
	private String id;

	// 图号
	@Column(name = "map_no", length = 64)
	private String mapNo;
	
	// 图名
	@Column(name = "map_name", length = 64)
	private String mapName;
	
	@Column(name = "area", length = 64)
	private String area;

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
	
	@Column(name = "remarks")
	private String remarks;
	
	
	//任务书编号
		@Column(name = "task_book_no")
		private String taskBookNo;
		
		
		//类型
		@Column(name = "process_def_name")
		private String processDefName;

		
		//完成状态
		@Column(name = "status")
		private String status;
	
	

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

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getTaskBookNo() {
		return taskBookNo;
	}

	public void setTaskBookNo(String taskBookNo) {
		this.taskBookNo = taskBookNo;
	}

	public String getProcessDefName() {
		return processDefName;
	}

	public void setProcessDefName(String processDefName) {
		this.processDefName = processDefName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
