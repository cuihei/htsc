package com.ht.persistence.model.background.organization.organization;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ht.persistence.model.base.BaseModel;

/**
 * 组织机构表
 * @author 侯晨
 */
@Entity
@Table(name = "ORGANIZATION")
public class Organization extends BaseModel{
	@Id
	@Column(name = "id", length = 64)
	private String id;
	
	//组织机构名称
	@Column(name = "org_name", length = 100, nullable = false)
	private String orgName;
	
	//组织机构编号
	@Column(name = "org_no", length = 64)
	private String orgNo;
	
	//机构父节点
	@Column(name = "parent_id", length = 64)
	private String parentId;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getOrgNo() {
		return orgNo;
	}
	public void setOrgNo(String orgNo) {
		this.orgNo = orgNo;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	@Override
	public String toString() {
		return "Organization [id=" + id + ", orgName=" + orgName + ", orgNo="
				+ orgNo + ", parentId=" + parentId + "]";
	}
	
}
