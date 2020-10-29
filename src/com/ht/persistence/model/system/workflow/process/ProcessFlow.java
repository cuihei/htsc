package com.ht.persistence.model.system.workflow.process;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ht.persistence.model.base.BaseModel;

@Entity
@Table(name = "PROCESS_FLOW")
public class ProcessFlow  extends BaseModel{
	
	@Id
	@Column(name = "ID")
	private String id;

	//流程实例ID
	@Column(name = "PROCESS_INST_ID")
	private String processInstId;
	
	//操作人编号
	@Column(name = "USER_NO")
	private String userNo;
	
	//操作类型    审批  开启  结束
	@Column(name = "TYPE")
	private String type;
	
	//流程任务ID
	@Column(name = "TASK_ID")
	private String taskId;
	
	//任务处理结果   通过 不通过
	@Column(name = "TASK_RESULT")
	private String taskResult;
	
	//  创建人
	@Column(name = "CREATOR")
	private String creator;
	//  创建日期
	@Column(name = "CREATION_DATE")
	private Date creationDate;
	//  更新人
	@Column(name = "MODIFIER")
	private String modifier;
	//  更新日期
	@Column(name = "MODIFY_DATE")
	private Date modifyDate;
	
	//任务父节点ID
	@Column(name = "PARENT_TASK_ID")
	private String parentTaskId;
	
	//任务处理意见
	@Column(name = "ADVICE")
	private String advice;
	
	//任务处理意见
	@Column(name = "ADVICE1")
	private String advice1;
	
	//备注
	@Column(name = "REMARK")
	private String remark;
	
	//错误图片1
	@Column(name = "ERRJPG1")
	private String errjpg1;
	
	//错误说明文本1
	@Column(name = "ERRTXT1")
	private String errtxt1;	
	
	//错误图片2
	@Column(name = "ERRJPG2")
	private String errjpg2;
	
	//错误说明文本2
	@Column(name = "ERRTXT2")
	private String errtxt2;	
	
	//错误图片3
	@Column(name = "ERRJPG3")
	private String errjpg3;
	
	//错误说明文本3
	@Column(name = "ERRTXT3")
	private String errtxt3;
	//  节点名称
	@Column(name = "TASKNAME")
	private String taskname;


	
	
	public ProcessFlow() {
		super();
	}

	/**
	 * @return id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id 要设置的 id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return processInstId
	 */
	public String getProcessInstId() {
		return processInstId;
	}

	/**
	 * @param processInstId 要设置的 processInstId
	 */
	public void setProcessInstId(String processInstId) {
		this.processInstId = processInstId;
	}

	/**
	 * @return userNo
	 */
	public String getUserNo() {
		return userNo;
	}

	/**
	 * @param userNo 要设置的 userNo
	 */
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	/**
	 * @return type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type 要设置的 type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return taskId
	 */
	public String getTaskId() {
		return taskId;
	}

	/**
	 * @param taskId 要设置的 taskId
	 */
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	/**
	 * @return taskResult
	 */
	public String getTaskResult() {
		return taskResult;
	}

	/**
	 * @param taskResult 要设置的 taskResult
	 */
	public void setTaskResult(String taskResult) {
		this.taskResult = taskResult;
	}

	/**
	 * @return parentTaskId
	 */
	public String getParentTaskId() {
		return parentTaskId;
	}

	/**
	 * @param parentTaskId 要设置的 parentTaskId
	 */
	public void setParentTaskId(String parentTaskId) {
		this.parentTaskId = parentTaskId;
	}

	/**
	 * @return advice
	 */
	public String getAdvice() {
		return advice;
	}

	/**
	 * @param advice 要设置的 advice
	 */
	public void setAdvice(String advice) {
		this.advice = advice;
	}

	/**
	 * @return advice1
	 */
	public String getAdvice1() {
		return advice1;
	}

	/**
	 * @param advice1 要设置的 advice1
	 */
	public void setAdvice1(String advice1) {
		this.advice1 = advice1;
	}

	/**
	 * @return remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @param remark 要设置的 remark
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * @return errjpg1
	 */
	public String getErrjpg1() {
		return errjpg1;
	}

	/**
	 * @param errjpg1 要设置的 errjpg1
	 */
	public void setErrjpg1(String errjpg1) {
		this.errjpg1 = errjpg1;
	}

	/**
	 * @return errtxt1
	 */
	public String getErrtxt1() {
		return errtxt1;
	}

	/**
	 * @param errtxt1 要设置的 errtxt1
	 */
	public void setErrtxt1(String errtxt1) {
		this.errtxt1 = errtxt1;
	}

	/**
	 * @return errjpg2
	 */
	public String getErrjpg2() {
		return errjpg2;
	}

	/**
	 * @param errjpg2 要设置的 errjpg2
	 */
	public void setErrjpg2(String errjpg2) {
		this.errjpg2 = errjpg2;
	}

	/**
	 * @return errtxt2
	 */
	public String getErrtxt2() {
		return errtxt2;
	}

	/**
	 * @param errtxt2 要设置的 errtxt2
	 */
	public void setErrtxt2(String errtxt2) {
		this.errtxt2 = errtxt2;
	}

	/**
	 * @return errjpg3
	 */
	public String getErrjpg3() {
		return errjpg3;
	}

	/**
	 * @param errjpg3 要设置的 errjpg3
	 */
	public void setErrjpg3(String errjpg3) {
		this.errjpg3 = errjpg3;
	}

	/**
	 * @return errtxt3
	 */
	public String getErrtxt3() {
		return errtxt3;
	}

	/**
	 * @param errtxt3 要设置的 errtxt3
	 */
	public void setErrtxt3(String errtxt3) {
		this.errtxt3 = errtxt3;
	}

	/**
	 * @return taskname
	 */
	public String getTaskname() {
		return taskname;
	}

	/**
	 * @param taskname 要设置的 taskname
	 */
	public void setTaskname(String taskname) {
		this.taskname = taskname;
	}

	/**
	 * @return creator
	 */
	public String getCreator() {
		return creator;
	}

	/**
	 * @param creator 要设置的 creator
	 */
	public void setCreator(String creator) {
		this.creator = creator;
	}

	/**
	 * @return creationDate
	 */
	public Date getCreationDate() {
		return creationDate;
	}

	/**
	 * @param creationDate 要设置的 creationDate
	 */
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	/**
	 * @return modifier
	 */
	public String getModifier() {
		return modifier;
	}

	/**
	 * @param modifier 要设置的 modifier
	 */
	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	/**
	 * @return modifyDate
	 */
	public Date getModifyDate() {
		return modifyDate;
	}

	/**
	 * @param modifyDate 要设置的 modifyDate
	 */
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	
	
}
