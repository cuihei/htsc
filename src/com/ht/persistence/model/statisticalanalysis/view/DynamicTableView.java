package com.ht.persistence.model.statisticalanalysis.view;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 编绘进度视图实体类
 * 
 * @author zqy
 */
@Entity
@Table(name = "V_COMPILATION_DYNAMIC_SUMMARY")
public class DynamicTableView {

	@Id
	@Column(name = "id", length = 64)
	private String id;

	// 图号
	@Column(name = "map_no", length = 64)
	private String mapNo;
	
	@Column(name = "area", length = 64)
	private String area;
	
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
	
	@Column(name = "process_inst_id")
	private String processInstId;
	
	@Column(name = "task_def_id")
	private String taskDefId;
	
/*	@Column(name = "year")
	private String year;*/
	
	
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

	public String getProcessInstId() {
		return processInstId;
	}

	public void setProcessInstId(String processInstId) {
		this.processInstId = processInstId;
	}

	public String getTaskDefId() {
		return taskDefId;
	}

	public void setTaskDefId(String taskDefId) {
		this.taskDefId = taskDefId;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

/*	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}
*/
	public String getProcessDefName() {
		return processDefName;
	}

	public void setProcessDefName(String processDefName) {
		this.processDefName = processDefName;
	}

	public String getTaskBookNo() {
		return taskBookNo;
	}

	public void setTaskBookNo(String taskBookNo) {
		this.taskBookNo = taskBookNo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
