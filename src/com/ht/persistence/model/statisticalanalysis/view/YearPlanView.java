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
public class YearPlanView  {

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
	
	// 编绘任务类别
	@Column(name = "type")
	private String type;

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
	private String lastTimeDate;

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

	// 工天
	@Column(name = "compilation_work_days")
	private String compilationWorkDays;

	// 编绘员
	@Column(name = "compilation_clerk")
	private String compilationClerk;

	// 开始时间
	@Column(name = "compilation_start_time")
	private Date compilationStartTime;

	// 完成时间
	@Column(name = "compilation_end_time")
	private Date compilationEndTime;

	// 工天
	@Column(name = "quality_work_days")
	private String qualityWorkDays;

	// 质检员
	@Column(name = "inspector")
	private String inspector;

	// 开始时间
	@Column(name = "quality_start_time")
	private Date qualityStartTime;

	// 完成时间
	@Column(name = "quality_end_time")
	private Date qualityEndTime;

	//  得分
	@Column(name = "quality_score")
	private String qualityScore;

	// 审定员
	@Column(name = "authorized_officer")
	private String authorizedOfficer;

	// 开始日期
	@Column(name = "authorized_start_time")
	private Date authorizedStartTime;

	// 完成日期
	@Column(name = "authorized_end_time")
	private Date authorizedEndTime;

	// 工天
	@Column(name = "authorized_work_days")
	private String authorizedWorkDays;

	// 质量评分
	@Column(name = "quality_achievement")
	private String qualityAchievement;
	
	@Column(name = "task_def_id")
	private String taskDefId;
	
	@Column(name = "start_time")
	private String startTime;
	
	@Column(name = "end_time")
	private String endTime;
	
	@Column(name = "assignee_name")
	private String assigneeName;
	
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

	/*public Date getDataExtractionTime() {
		return dataExtractionTime;
	}

	public void setDataExtractionTime(Date dataExtractionTime) {
		this.dataExtractionTime = dataExtractionTime;
	}*/

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

	public String getCompilationWorkDays() {
		return compilationWorkDays;
	}

	public void setCompilationWorkDays(String compilationWorkDays) {
		this.compilationWorkDays = compilationWorkDays;
	}

	public String getCompilationClerk() {
		return compilationClerk;
	}

	public void setCompilationClerk(String compilationClerk) {
		this.compilationClerk = compilationClerk;
	}

	public Date getCompilationStartTime() {
		return compilationStartTime;
	}

	public void setCompilationStartTime(Date compilationStartTime) {
		this.compilationStartTime = compilationStartTime;
	}

	public Date getCompilationEndTime() {
		return compilationEndTime;
	}

	public void setCompilationEndTime(Date compilationEndTime) {
		this.compilationEndTime = compilationEndTime;
	}

	public String getQualityWorkDays() {
		return qualityWorkDays;
	}

	public void setQualityWorkDays(String qualityWorkDays) {
		this.qualityWorkDays = qualityWorkDays;
	}

	public String getInspector() {
		return inspector;
	}

	public void setInspector(String inspector) {
		this.inspector = inspector;
	}

	public Date getQualityStartTime() {
		return qualityStartTime;
	}

	public void setQualityStartTime(Date qualityStartTime) {
		this.qualityStartTime = qualityStartTime;
	}

	public Date getQualityEndTime() {
		return qualityEndTime;
	}

	public void setQualityEndTime(Date qualityEndTime) {
		this.qualityEndTime = qualityEndTime;
	}

	public String getQualityScore() {
		return qualityScore;
	}

	public void setQualityScore(String qualityScore) {
		this.qualityScore = qualityScore;
	}

	public String getAuthorizedOfficer() {
		return authorizedOfficer;
	}

	public void setAuthorizedOfficer(String authorizedOfficer) {
		this.authorizedOfficer = authorizedOfficer;
	}

	public Date getAuthorizedStartTime() {
		return authorizedStartTime;
	}

	public void setAuthorizedStartTime(Date authorizedStartTime) {
		this.authorizedStartTime = authorizedStartTime;
	}

	public Date getAuthorizedEndTime() {
		return authorizedEndTime;
	}

	public void setAuthorizedEndTime(Date authorizedEndTime) {
		this.authorizedEndTime = authorizedEndTime;
	}

	public String getAuthorizedWorkDays() {
		return authorizedWorkDays;
	}

	public void setAuthorizedWorkDays(String authorizedWorkDays) {
		this.authorizedWorkDays = authorizedWorkDays;
	}

	public String getQualityAchievement() {
		return qualityAchievement;
	}

	public void setQualityAchievement(String qualityAchievement) {
		this.qualityAchievement = qualityAchievement;
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

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getAssigneeName() {
		return assigneeName;
	}

	public void setAssigneeName(String assigneeName) {
		this.assigneeName = assigneeName;
	}
}
