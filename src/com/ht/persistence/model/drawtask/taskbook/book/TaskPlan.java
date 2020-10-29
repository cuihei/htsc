package com.ht.persistence.model.drawtask.taskbook.book;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.ht.persistence.model.base.BaseModel;

/**
 * 编绘任务书计划model
 * @author Weihua
 */
@Entity
@Table(name = "V_ACT_HI_PLAN")
public class TaskPlan extends BaseModel
{private String id;
private String taskbookNo;    //任务书编号
private String mapNo;    //图号
private String mapName;    //图名
private String scale;    //比例尺 
private String processDefName;    //任务类型
private String status;    //状态
private String taskName;    //状态
private String currUser;    //当前操作人
private String taskBookTime;    //任务书创建时间 
private String processDefId;	
private String taskDefId;
private String processInstId;	
private String parentProcessInstId;	//PROCDEFID
private String compilationGroup;    //编绘组
private String compilationPerson;    //编绘员
private String compilationExpectedTime;    //编绘预期时间
private String compilationWorkdays;    //编绘工天
private String compilationStartTime;    //编绘开始时间
private String compilationEndTime;    //编绘结束时间
private String qualityPerson;    //质检人
private String qualityExpectedTime;    //质检预期时间
private String qualityStartTime;    //质检开始时间
private String qualityEndTime;    //质检结束时间
private String qualityScore;    //质检评分
private String checkWorkdays;    //质检工天
private String ztTime;    //制图事业部科长审核时间
private String apprvoePerson;    //审定人
private String apprvoeExpectedTime;    //审定预期时间
private String apprvoeStartTime;    //审定开始时间
private String apprvoeEndTime;    //审定结束时间
private String examineWorkdays;    //审定工天
private String score;    //审定评分
private String zlTime;    //质量检验科科长审核时间
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getTaskbookNo() {
	return taskbookNo;
}
public void setTaskbookNo(String taskbookNo) {
	this.taskbookNo = taskbookNo;
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
public String getTaskName() {
	return taskName;
}
public void setTaskName(String taskName) {
	this.taskName = taskName;
}
public String getCurrUser() {
	return currUser;
}
public void setCurrUser(String currUser) {
	this.currUser = currUser;
}
public String getTaskBookTime() {
	return taskBookTime;
}
public void setTaskBookTime(String taskBookTime) {
	this.taskBookTime = taskBookTime;
}
public String getProcessDefId() {
	return processDefId;
}
public void setProcessDefId(String processDefId) {
	this.processDefId = processDefId;
}
public String getTaskDefId() {
	return taskDefId;
}
public void setTaskDefId(String taskDefId) {
	this.taskDefId = taskDefId;
}
public String getProcessInstId() {
	return processInstId;
}
public void setProcessInstId(String processInstId) {
	this.processInstId = processInstId;
}
public String getParentProcessInstId() {
	return parentProcessInstId;
}
public void setParentProcessInstId(String parentProcessInstId) {
	this.parentProcessInstId = parentProcessInstId;
}
public String getCompilationGroup() {
	return compilationGroup;
}
public void setCompilationGroup(String compilationGroup) {
	this.compilationGroup = compilationGroup;
}
public String getCompilationPerson() {
	return compilationPerson;
}
public void setCompilationPerson(String compilationPerson) {
	this.compilationPerson = compilationPerson;
}
public String getCompilationExpectedTime() {
	return compilationExpectedTime;
}
public void setCompilationExpectedTime(String compilationExpectedTime) {
	this.compilationExpectedTime = compilationExpectedTime;
}
public String getCompilationWorkdays() {
	return compilationWorkdays;
}
public void setCompilationWorkdays(String compilationWorkdays) {
	this.compilationWorkdays = compilationWorkdays;
}
public String getCompilationStartTime() {
	return compilationStartTime;
}
public void setCompilationStartTime(String compilationStartTime) {
	this.compilationStartTime = compilationStartTime;
}
public String getCompilationEndTime() {
	return compilationEndTime;
}
public void setCompilationEndTime(String compilationEndTime) {
	this.compilationEndTime = compilationEndTime;
}
public String getQualityPerson() {
	return qualityPerson;
}
public void setQualityPerson(String qualityPerson) {
	this.qualityPerson = qualityPerson;
}
public String getQualityExpectedTime() {
	return qualityExpectedTime;
}
public void setQualityExpectedTime(String qualityExpectedTime) {
	this.qualityExpectedTime = qualityExpectedTime;
}
public String getQualityStartTime() {
	return qualityStartTime;
}
public void setQualityStartTime(String qualityStartTime) {
	this.qualityStartTime = qualityStartTime;
}
public String getQualityEndTime() {
	return qualityEndTime;
}
public void setQualityEndTime(String qualityEndTime) {
	this.qualityEndTime = qualityEndTime;
}
public String getQualityScore() {
	return qualityScore;
}
public void setQualityScore(String qualityScore) {
	this.qualityScore = qualityScore;
}
public String getCheckWorkdays() {
	return checkWorkdays;
}
public void setCheckWorkdays(String checkWorkdays) {
	this.checkWorkdays = checkWorkdays;
}
public String getZtTime() {
	return ztTime;
}
public void setZtTime(String ztTime) {
	this.ztTime = ztTime;
}
public String getApprvoePerson() {
	return apprvoePerson;
}
public void setApprvoePerson(String apprvoePerson) {
	this.apprvoePerson = apprvoePerson;
}
public String getApprvoeExpectedTime() {
	return apprvoeExpectedTime;
}
public void setApprvoeExpectedTime(String apprvoeExpectedTime) {
	this.apprvoeExpectedTime = apprvoeExpectedTime;
}
public String getApprvoeStartTime() {
	return apprvoeStartTime;
}
public void setApprvoeStartTime(String apprvoeStartTime) {
	this.apprvoeStartTime = apprvoeStartTime;
}
public String getApprvoeEndTime() {
	return apprvoeEndTime;
}
public void setApprvoeEndTime(String apprvoeEndTime) {
	this.apprvoeEndTime = apprvoeEndTime;
}
public String getExamineWorkdays() {
	return examineWorkdays;
}
public void setExamineWorkdays(String examineWorkdays) {
	this.examineWorkdays = examineWorkdays;
}
public String getScore() {
	return score;
}
public void setScore(String score) {
	this.score = score;
}
public String getZlTime() {
	return zlTime;
}
public void setZlTime(String zlTime) {
	this.zlTime = zlTime;
}

	
}