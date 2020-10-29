package com.ht.persistence.model.complication.correctionnotice;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ht.common.export.ExcelAttribute;
import com.ht.persistence.model.background.dicdata.basedata.BaseData;
import com.ht.persistence.model.base.BaseModel;

/**
 * SeaMap 海图编绘类
 * @author YP
 */
@Entity
@Table(name = "CATALOG_AREA")
public class CorNotice extends BaseModel{

	@Id
	@Column(name = "id", length = 64)
	private String id;
	
	//目录区域名称
	@Column(name = "area_name", length = 100, nullable = false)
	@ExcelAttribute(name = "区域目录名") 
	private String areaName ;
	
	//类别名称
	@Column(name = "category_id", length = 100, nullable = false)
	@ExcelAttribute(name = "类别")  
	private String categ;
	
	private BaseData baseData;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public BaseData getBaseData() {
		return baseData;
	}

	public void setBaseData(BaseData baseData) {
		this.baseData = baseData;
	}

	public String getCategoryId() {
		return categ;
	}

	public void setCategoryId(String categoryId) {
		this.categ = categoryId;
	}
	
}
