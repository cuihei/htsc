package com.ht.persistence.model.background.organization.organization;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ht.persistence.model.base.BaseModel;

/**
 * 组织机构人员关系类
 * @author 侯晨
 *
 */
@Entity
@Table(name = "ORGANIZATION_USERS_RELATION")
public class OrganizationUsersRelation extends BaseModel{
	@Id
	@Column(name = "id", length = 64)
	private String id;
	
	//组织机构主键
	@Column(name = "org_id", length = 64, nullable = false)
	private String orgId;
	
	//人员编号
	@Column(name = "user_id", length = 64, nullable = false)
	private String userId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
