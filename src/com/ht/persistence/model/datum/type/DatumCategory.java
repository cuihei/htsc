package com.ht.persistence.model.datum.type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ht.persistence.model.base.BaseModel;

/**
 *  DatumCategory资料类别类
 * @author 平子金
 */
@Entity
@Table(name = "DATUM_CATEGORY")
public class DatumCategory extends BaseModel {
	
	@Id
	@Column(name = "id", length = 64)
	private String id;//标识ID

	@Column(name = "category_name", length = 32)
	private String categoryName;//类别名称

	@Column(name = "parent_id", length = 64)
	private String parentId;//父ID

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	
	
}
