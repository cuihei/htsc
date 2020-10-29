package com.ht.persistence.model.statisticalanalysis.view;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 编绘年计划实体类
 * 
 * @author zqy
 */
@Entity
@Table(name = "V_COMPILATION_YEAR_PLAN")
public class CompilationYearPlanView  {

	@Id
	@Column(name = "id", length = 64)
	private String id;
	
	/*//计划id
	@Column(name = "plan_id", length = 64)
	private String planId;
	
	//工作流转日志Id
	@Column(name = "process_inst_id", length = 64)
	private String processInstId;
		*/
	
	// 图号
	@Column(name = "map_no", length = 64)
	private String mapNo;
	
	// 图名
	@Column(name = "map_name", length = 64)
	private String mapName;

	// 比例尺
	@Column(name = "scale", length = 64)
	private String scale;


	// 测量周期（基测）
	@Column(name = "frederick_cycle")
	private String frederickCycle;

	/*// 测量周期（检测）
	@Column(name = "testing_cycle")
	private String testingCycle;*/

	// 上次测量/编绘性质
	@Column(name = "last_time_property")
	private String lastTimeProperty;

	// 上次测量（年份）
	@Column(name = "last_time_date")
	private String  lastTimeDate;

	// 计划类别
	@Column(name = "plan_type")
	private String planType;
	
	// 计划汇交时间
	@Column(name = "plan_exchange_time")
	private Date planExchangeTime;

	// 实际汇交时间
	@Column(name = "actual_exchange_time")
	private Date actualExchangeTime;
	
	// 计划编绘时间
	@Column(name = "task_release_time")
	private Date taskReleaseTime;

	// 计划完成时间
	@Column(name = "plan_complete_time")
	private Date planCompleteTime;


	// 编绘性质
	@Column(name = "compilation_property")
	private String compilationProperty;
	
	// 编绘内容
	@Column(name = "compilation_content")
	private String compilationContent;

	// 调整系数
	@Column(name = "adjustment_coefficient")
	private String adjustmentCoefficient;
	
	// 编绘任务类别
	@Column(name = "type")
	private String type;
	
	@Column(name = "task_def_id")
	private String taskDefId;
	
	@Column(name = "start_time")
	private Date startTime;
	
	@Column(name = "end_time")
	private Date endTime;
	
	@Column(name = "assignee_name")
	private String assigneeName;
	
	@Column(name = "process_inst_id")
	private String processInstId;
	
	@Column(name = "process_def_key")
	private String processDefKey;
	
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


	public String getFrederickCycle() {
		return frederickCycle;
	}

	public void setFrederickCycle(String frederickCycle) {
		this.frederickCycle = frederickCycle;
	}
/*
	public String getTestingCycle() {
		return testingCycle;
	}

	public void setTestingCycle(String testingCycle) {
		this.testingCycle = testingCycle;
	}*/

	public String getLastTimeProperty() {
		return lastTimeProperty;
	}

	public void setLastTimeProperty(String lastTimeProperty) {
		this.lastTimeProperty = lastTimeProperty;
	}

	public String getLastTimeDate() {
		return lastTimeDate;
	}

	public void setLastTimeDate(String lastTimeDate) {
		this.lastTimeDate = lastTimeDate;
	}

	public String getPlanType() {
		return planType;
	}

	public void setPlanType(String planType) {
		this.planType = planType;
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



	public String getCompilationProperty() {
		return compilationProperty;
	}

	public void setCompilationProperty(String compilationProperty) {
		this.compilationProperty = compilationProperty;
	}

	public String getCompilationContent() {
		return compilationContent;
	}

	public void setCompilationContent(String compilationContent) {
		this.compilationContent = compilationContent;
	}

	public String getAdjustmentCoefficient() {
		return adjustmentCoefficient;
	}

	public void setAdjustmentCoefficient(String adjustmentCoefficient) {
		this.adjustmentCoefficient = adjustmentCoefficient;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTaskDefId() {
		return taskDefId;
	}

	public void setTaskDefId(String taskDefId) {
		this.taskDefId = taskDefId;
	}

	
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getAssigneeName() {
		return assigneeName;
	}

	public void setAssigneeName(String assigneeName) {
		this.assigneeName = assigneeName;
	}

	public String getProcessInstId() {
		return processInstId;
	}

	public void setProcessInstId(String processInstId) {
		this.processInstId = processInstId;
	}

	public String getProcessDefKey() {
		return processDefKey;
	}

	public void setProcessDefKey(String processDefKey) {
		this.processDefKey = processDefKey;
	}

}
