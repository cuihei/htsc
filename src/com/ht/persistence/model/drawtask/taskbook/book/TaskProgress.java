package com.ht.persistence.model.drawtask.taskbook.book;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.ht.persistence.model.base.BaseModel;

/**
 * 编绘任务书model
 * @author Weihua
 */
@Entity
@Table(name = "TASK_PROGRESS")
public class TaskProgress extends BaseModel
{
	private String ID;// 
	private String TASKBOOKNO;// 任务书编号
	private String MAPNO;// 图号
	private String MAPNAME;// 图名
	private String SCALE;// 比例尺
	private String PROCESSDEFNAME;// 任务类型
	private String NODE;// 当前节点
	private String CURRUSER;// 当前操作人
	private String TASKBOOKTIME;// 任务书创建时间
	private String PROCESSINSTID;// 流程ID
	private String SUPERID;// 父ID
	private String TASKDEFKEY;// TASKDEFKEY
	private String PROCDEFID;// PROCDEFID
	private String CREATETIME;// 创建日期
	private String COMPILATIONGROUP;// 编绘组
	private String COMPILATIONPERSON;// 编绘员
	private String COMPILATIONWORKDAYS;// 编绘工天
	private String COMPILATIONSTARTTIME;// 编绘开始时间
	private String COMPILATIONENDTIME;// 编绘结束时间
	private String QUALITYPERSON;// 质检人
	private String CHECKWORKDAYS;// 质检工天
	private String QUALITYSTARTTIME;// 质检开始时间
	private String QUALITYENDTIME;// 质检结束时间
	private String QUALITYSCORE;// 质检评分
	private String ZTTIME;// 制图事业部科长审核时间
	private String APPRVOEPERSON;// 审定人
	private String EXAMINEWORKDAYS;// 审定工天
	private String APPRVOESTARTTIME;// 审定开始时间
	private String APPRVOEENDTIME;// 审定结束时间
	private String SCORE;// 审定评分
	private String ZLTIME;// 质量检验科科长审核时间
	private String STATE;// 状态 0未完成 1已完成
	private String MODIFY_DATE;// 修改日期

	/**
	 * @return iD
	 */
	public String getID() {
		return ID;
	}
	/**
	 * @param iD 要设置的 iD
	 */
	public void setID(String iD) {
		ID = iD;
	}
	/**
	 * @return tASKBOOKNO
	 */
	public String getTASKBOOKNO() {
		return TASKBOOKNO;
	}
	/**
	 * @param tASKBOOKNO 要设置的 tASKBOOKNO
	 */
	public void setTASKBOOKNO(String tASKBOOKNO) {
		TASKBOOKNO = tASKBOOKNO;
	}
	/**
	 * @return mAPNO
	 */
	public String getMAPNO() {
		return MAPNO;
	}
	/**
	 * @param mAPNO 要设置的 mAPNO
	 */
	public void setMAPNO(String mAPNO) {
		MAPNO = mAPNO;
	}
	/**
	 * @return mAPNAME
	 */
	public String getMAPNAME() {
		return MAPNAME;
	}
	/**
	 * @param mAPNAME 要设置的 mAPNAME
	 */
	public void setMAPNAME(String mAPNAME) {
		MAPNAME = mAPNAME;
	}
	/**
	 * @return sCALE
	 */
	public String getSCALE() {
		return SCALE;
	}
	/**
	 * @param sCALE 要设置的 sCALE
	 */
	public void setSCALE(String sCALE) {
		SCALE = sCALE;
	}
	/**
	 * @return pROCESSDEFNAME
	 */
	public String getPROCESSDEFNAME() {
		return PROCESSDEFNAME;
	}
	/**
	 * @param pROCESSDEFNAME 要设置的 pROCESSDEFNAME
	 */
	public void setPROCESSDEFNAME(String pROCESSDEFNAME) {
		PROCESSDEFNAME = pROCESSDEFNAME;
	}
	/**
	 * @return nODE
	 */
	public String getNODE() {
		return NODE;
	}
	/**
	 * @param nODE 要设置的 nODE
	 */
	public void setNODE(String nODE) {
		NODE = nODE;
	}
	/**
	 * @return cURRUSER
	 */
	public String getCURRUSER() {
		return CURRUSER;
	}
	/**
	 * @param cURRUSER 要设置的 cURRUSER
	 */
	public void setCURRUSER(String cURRUSER) {
		CURRUSER = cURRUSER;
	}
	/**
	 * @return tASKBOOKTIME
	 */
	public String getTASKBOOKTIME() {
		return TASKBOOKTIME;
	}
	/**
	 * @param tASKBOOKTIME 要设置的 tASKBOOKTIME
	 */
	public void setTASKBOOKTIME(String tASKBOOKTIME) {
		TASKBOOKTIME = tASKBOOKTIME;
	}
	/**
	 * @return pROCESSINSTID
	 */
	public String getPROCESSINSTID() {
		return PROCESSINSTID;
	}
	/**
	 * @param pROCESSINSTID 要设置的 pROCESSINSTID
	 */
	public void setPROCESSINSTID(String pROCESSINSTID) {
		PROCESSINSTID = pROCESSINSTID;
	}
	/**
	 * @return sUPERID
	 */
	public String getSUPERID() {
		return SUPERID;
	}
	/**
	 * @param sUPERID 要设置的 sUPERID
	 */
	public void setSUPERID(String sUPERID) {
		SUPERID = sUPERID;
	}
	/**
	 * @return tASKDEFKEY
	 */
	public String getTASKDEFKEY() {
		return TASKDEFKEY;
	}
	/**
	 * @param tASKDEFKEY 要设置的 tASKDEFKEY
	 */
	public void setTASKDEFKEY(String tASKDEFKEY) {
		TASKDEFKEY = tASKDEFKEY;
	}
	/**
	 * @return pROCDEFID
	 */
	public String getPROCDEFID() {
		return PROCDEFID;
	}
	/**
	 * @param pROCDEFID 要设置的 pROCDEFID
	 */
	public void setPROCDEFID(String pROCDEFID) {
		PROCDEFID = pROCDEFID;
	}
	/**
	 * @return cREATETIME
	 */
	public String getCREATETIME() {
		return CREATETIME;
	}
	/**
	 * @param cREATETIME 要设置的 cREATETIME
	 */
	public void setCREATETIME(String cREATETIME) {
		CREATETIME = cREATETIME;
	}
	/**
	 * @return cOMPILATIONGROUP
	 */
	public String getCOMPILATIONGROUP() {
		return COMPILATIONGROUP;
	}
	/**
	 * @param cOMPILATIONGROUP 要设置的 cOMPILATIONGROUP
	 */
	public void setCOMPILATIONGROUP(String cOMPILATIONGROUP) {
		COMPILATIONGROUP = cOMPILATIONGROUP;
	}
	/**
	 * @return cOMPILATIONPERSON
	 */
	public String getCOMPILATIONPERSON() {
		return COMPILATIONPERSON;
	}
	/**
	 * @param cOMPILATIONPERSON 要设置的 cOMPILATIONPERSON
	 */
	public void setCOMPILATIONPERSON(String cOMPILATIONPERSON) {
		COMPILATIONPERSON = cOMPILATIONPERSON;
	}
	/**
	 * @return cOMPILATIONWORKDAYS
	 */
	public String getCOMPILATIONWORKDAYS() {
		return COMPILATIONWORKDAYS;
	}
	/**
	 * @param cOMPILATIONWORKDAYS 要设置的 cOMPILATIONWORKDAYS
	 */
	public void setCOMPILATIONWORKDAYS(String cOMPILATIONWORKDAYS) {
		COMPILATIONWORKDAYS = cOMPILATIONWORKDAYS;
	}
	/**
	 * @return cOMPILATIONSTARTTIME
	 */
	public String getCOMPILATIONSTARTTIME() {
		return COMPILATIONSTARTTIME;
	}
	/**
	 * @param cOMPILATIONSTARTTIME 要设置的 cOMPILATIONSTARTTIME
	 */
	public void setCOMPILATIONSTARTTIME(String cOMPILATIONSTARTTIME) {
		COMPILATIONSTARTTIME = cOMPILATIONSTARTTIME;
	}
	/**
	 * @return cOMPILATIONENDTIME
	 */
	public String getCOMPILATIONENDTIME() {
		return COMPILATIONENDTIME;
	}
	/**
	 * @param cOMPILATIONENDTIME 要设置的 cOMPILATIONENDTIME
	 */
	public void setCOMPILATIONENDTIME(String cOMPILATIONENDTIME) {
		COMPILATIONENDTIME = cOMPILATIONENDTIME;
	}
	/**
	 * @return qUALITYPERSON
	 */
	public String getQUALITYPERSON() {
		return QUALITYPERSON;
	}
	/**
	 * @param qUALITYPERSON 要设置的 qUALITYPERSON
	 */
	public void setQUALITYPERSON(String qUALITYPERSON) {
		QUALITYPERSON = qUALITYPERSON;
	}
	/**
	 * @return cHECKWORKDAYS
	 */
	public String getCHECKWORKDAYS() {
		return CHECKWORKDAYS;
	}
	/**
	 * @param cHECKWORKDAYS 要设置的 cHECKWORKDAYS
	 */
	public void setCHECKWORKDAYS(String cHECKWORKDAYS) {
		CHECKWORKDAYS = cHECKWORKDAYS;
	}
	/**
	 * @return qUALITYSTARTTIME
	 */
	public String getQUALITYSTARTTIME() {
		return QUALITYSTARTTIME;
	}
	/**
	 * @param qUALITYSTARTTIME 要设置的 qUALITYSTARTTIME
	 */
	public void setQUALITYSTARTTIME(String qUALITYSTARTTIME) {
		QUALITYSTARTTIME = qUALITYSTARTTIME;
	}
	/**
	 * @return qUALITYENDTIME
	 */
	public String getQUALITYENDTIME() {
		return QUALITYENDTIME;
	}
	/**
	 * @param qUALITYENDTIME 要设置的 qUALITYENDTIME
	 */
	public void setQUALITYENDTIME(String qUALITYENDTIME) {
		QUALITYENDTIME = qUALITYENDTIME;
	}
	/**
	 * @return qUALITYSCORE
	 */
	public String getQUALITYSCORE() {
		return QUALITYSCORE;
	}
	/**
	 * @param qUALITYSCORE 要设置的 qUALITYSCORE
	 */
	public void setQUALITYSCORE(String qUALITYSCORE) {
		QUALITYSCORE = qUALITYSCORE;
	}
	/**
	 * @return zTTIME
	 */
	public String getZTTIME() {
		return ZTTIME;
	}
	/**
	 * @param zTTIME 要设置的 zTTIME
	 */
	public void setZTTIME(String zTTIME) {
		ZTTIME = zTTIME;
	}
	/**
	 * @return aPPRVOEPERSON
	 */
	public String getAPPRVOEPERSON() {
		return APPRVOEPERSON;
	}
	/**
	 * @param aPPRVOEPERSON 要设置的 aPPRVOEPERSON
	 */
	public void setAPPRVOEPERSON(String aPPRVOEPERSON) {
		APPRVOEPERSON = aPPRVOEPERSON;
	}
	/**
	 * @return eXAMINEWORKDAYS
	 */
	public String getEXAMINEWORKDAYS() {
		return EXAMINEWORKDAYS;
	}
	/**
	 * @param eXAMINEWORKDAYS 要设置的 eXAMINEWORKDAYS
	 */
	public void setEXAMINEWORKDAYS(String eXAMINEWORKDAYS) {
		EXAMINEWORKDAYS = eXAMINEWORKDAYS;
	}
	/**
	 * @return aPPRVOESTARTTIME
	 */
	public String getAPPRVOESTARTTIME() {
		return APPRVOESTARTTIME;
	}
	/**
	 * @param aPPRVOESTARTTIME 要设置的 aPPRVOESTARTTIME
	 */
	public void setAPPRVOESTARTTIME(String aPPRVOESTARTTIME) {
		APPRVOESTARTTIME = aPPRVOESTARTTIME;
	}
	/**
	 * @return aPPRVOEENDTIME
	 */
	public String getAPPRVOEENDTIME() {
		return APPRVOEENDTIME;
	}
	/**
	 * @param aPPRVOEENDTIME 要设置的 aPPRVOEENDTIME
	 */
	public void setAPPRVOEENDTIME(String aPPRVOEENDTIME) {
		APPRVOEENDTIME = aPPRVOEENDTIME;
	}
	/**
	 * @return sCORE
	 */
	public String getSCORE() {
		return SCORE;
	}
	/**
	 * @param sCORE 要设置的 sCORE
	 */
	public void setSCORE(String sCORE) {
		SCORE = sCORE;
	}
	/**
	 * @return zLTIME
	 */
	public String getZLTIME() {
		return ZLTIME;
	}
	/**
	 * @param zLTIME 要设置的 zLTIME
	 */
	public void setZLTIME(String zLTIME) {
		ZLTIME = zLTIME;
	}
	/**
	 * @return sTATE
	 */
	public String getSTATE() {
		return STATE;
	}
	/**
	 * @param sTATE 要设置的 sTATE
	 */
	public void setSTATE(String sTATE) {
		STATE = sTATE;
	}
	/**
	 * @return mODIFY_DATE
	 */
	public String getMODIFY_DATE() {
		return MODIFY_DATE;
	}
	/**
	 * @param mODIFY_DATE 要设置的 mODIFY_DATE
	 */
	public void setMODIFY_DATE(String mODIFY_DATE) {
		MODIFY_DATE = mODIFY_DATE;
	}
	
	
	
}