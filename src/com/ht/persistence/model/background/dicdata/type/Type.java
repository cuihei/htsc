package com.ht.persistence.model.background.dicdata.type;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ht.persistence.model.base.BaseModel;

/**
 * type 类别数据类
 * @author 刘凯
 */
@Entity
@Table(name = "BASE_DATA_TYPE")
public class Type extends BaseModel{
	@Id
	@Column(name = "id", length = 64)
	private String id;
	
	@Column(name = "name", length = 100, nullable = false)
	private String name;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
