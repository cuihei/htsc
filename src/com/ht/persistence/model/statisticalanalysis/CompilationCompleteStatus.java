package com.ht.persistence.model.statisticalanalysis;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ht.persistence.model.base.BaseModel;

/**
 * 编绘进度实体类
 * 
 * @author zqy
 */
@Entity
@Table(name = "COMPILATION_COMPLETE_STATUS")
public class CompilationCompleteStatus extends BaseModel {

	@Id
	@Column(name = "id", length = 64)
	private String id;

	// 计划ID
	@Column(name = "plan_id", length = 64)
	private String planId;

	// 编绘内容
	@Column(name = "content", length = 200)
	private String content;

	// 完成日期（审定）
	@Column(name = "complete_date")
	private Date completeDate;

	// 完成数量 月/累计（海图数量）
	@Column(name = "complete_number", length = 100)
	private String completeNumber;

	// 备注
	@Column(name = "remarks", length = 200)
	private String remarks;

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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCompleteDate() {
		return completeDate;
	}

	public void setCompleteDate(Date completeDate) {
		this.completeDate = completeDate;
	}

	public String getCompleteNumber() {
		return completeNumber;
	}

	public void setCompleteNumber(String completeNumber) {
		this.completeNumber = completeNumber;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}
