package com.ht.persistence.model.drawtask.taskbook.book;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.ht.persistence.model.base.BaseModel;

/**
 * 编绘任务书model
 * @author Weihua
 */
@Entity/*
@Table(name = "COMPILATION_TASK_ANALYSIS")
*/
@Table(name = "ACT_HI_TASKINST")
public class Taskinfo extends BaseModel
{	

	
	private String	ID;
	private String	PROCDEFID;
	private String	TASKDEFKEY;
	private String	PROCINSTID;
	private String	EXECUTIONID;
	private String	PARENTTASKID;
	private String	NAME;
	private String	DESCRIPTION;
	private String	OWNER;
	private String	ASSIGNEE;
	private Date	STARTTIME;
	private Date	CLAIMTIME;
	private Date	ENDTIME;
	private Double	DURATION;
	private String	DELETEREASON;
	private Double	PRIORITY;
	private Date	DUEDATE;
	private String	FORMKEY;
	private String	CATEGORY;
	private String	TENANTID;
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getPROCDEFID() {
		return PROCDEFID;
	}
	public void setPROCDEFID(String pROCDEFID) {
		PROCDEFID = pROCDEFID;
	}
	public String getTASKDEFKEY() {
		return TASKDEFKEY;
	}
	public void setTASKDEFKEY(String tASKDEFKEY) {
		TASKDEFKEY = tASKDEFKEY;
	}
	public String getPROCINSTID() {
		return PROCINSTID;
	}
	public void setPROCINSTID(String pROCINSTID) {
		PROCINSTID = pROCINSTID;
	}
	public String getEXECUTIONID() {
		return EXECUTIONID;
	}
	public void setEXECUTIONID(String eXECUTIONID) {
		EXECUTIONID = eXECUTIONID;
	}
	public String getPARENTTASKID() {
		return PARENTTASKID;
	}
	public void setPARENTTASKID(String pARENTTASKID) {
		PARENTTASKID = pARENTTASKID;
	}
	public String getNAME() {
		return NAME;
	}
	public void setNAME(String nAME) {
		NAME = nAME;
	}
	public String getDESCRIPTION() {
		return DESCRIPTION;
	}
	public void setDESCRIPTION(String dESCRIPTION) {
		DESCRIPTION = dESCRIPTION;
	}
	public String getOWNER() {
		return OWNER;
	}
	public void setOWNER(String oWNER) {
		OWNER = oWNER;
	}
	public String getASSIGNEE() {
		return ASSIGNEE;
	}
	public void setASSIGNEE(String aSSIGNEE) {
		ASSIGNEE = aSSIGNEE;
	}
	public Date getSTARTTIME() {
		return STARTTIME;
	}
	public void setSTARTTIME(Date sTARTTIME) {
		STARTTIME = sTARTTIME;
	}
	public Date getCLAIMTIME() {
		return CLAIMTIME;
	}
	public void setCLAIMTIME(Date cLAIMTIME) {
		CLAIMTIME = cLAIMTIME;
	}
	public Date getENDTIME() {
		return ENDTIME;
	}
	public void setENDTIME(Date eNDTIME) {
		ENDTIME = eNDTIME;
	}
	public Double getDURATION() {
		return DURATION;
	}
	public void setDURATION(Double dURATION) {
		DURATION = dURATION;
	}
	public String getDELETEREASON() {
		return DELETEREASON;
	}
	public void setDELETEREASON(String dELETEREASON) {
		DELETEREASON = dELETEREASON;
	}
	public Double getPRIORITY() {
		return PRIORITY;
	}
	public void setPRIORITY(Double pRIORITY) {
		PRIORITY = pRIORITY;
	}
	public Date getDUEDATE() {
		return DUEDATE;
	}
	public void setDUEDATE(Date dUEDATE) {
		DUEDATE = dUEDATE;
	}
	public String getFORMKEY() {
		return FORMKEY;
	}
	public void setFORMKEY(String fORMKEY) {
		FORMKEY = fORMKEY;
	}
	public String getCATEGORY() {
		return CATEGORY;
	}
	public void setCATEGORY(String cATEGORY) {
		CATEGORY = cATEGORY;
	}
	public String getTENANTID() {
		return TENANTID;
	}
	public void setTENANTID(String tENANTID) {
		TENANTID = tENANTID;
	}
	
	
	
	
	
}