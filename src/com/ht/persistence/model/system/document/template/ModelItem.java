package com.ht.persistence.model.system.document.template;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ht.persistence.model.base.BaseModel;

/**
 * ModelItem 模板项类
 * @author 平子金
 * */
@Entity
@Table(name="MODEL_ITEM")
public class ModelItem extends BaseModel{
	@Id
	@Column(name = "id", length = 64)
	private String id;// 标识
	@Column(name = "name", length = 64)
	private String name;// 模板项名称
	@Column(name = "bidding_name")
	private String bidding_name;// 
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
	public String getBidding_name() {
		return bidding_name;
	}
	public void setBidding_name(String bidding_name) {
		this.bidding_name = bidding_name;
	}
}
