package com.ht.persistence.model.system.workflow.process;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ht.persistence.model.base.BaseModel;

/**
 * 流程角色委托人实体类
 * @author 王有为
 */
@Entity
@Table(name = "PROCESS_DELEGATE")
public class ProcessDelegate extends BaseModel
{
	@Id
	@Column(name = "ID")
	private String id;

	// 组ID
	@Column(name = "ACT_GROUP_ID")
	private String groupId;
	
	// 组ID
	@Column(name = "ACT_USER_ID")
	private String userId;

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getGroupId()
	{
		return groupId;
	}

	public void setGroupId(String groupId)
	{
		this.groupId = groupId;
	}

	public String getUserId()
	{
		return userId;
	}

	public void setUserId(String userId)
	{
		this.userId = userId;
	}
}
