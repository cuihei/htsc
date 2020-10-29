package com.ht.persistence.model.catalog.area;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ht.common.export.ExcelAttribute;
import com.ht.persistence.model.background.dicdata.basedata.BaseData;
import com.ht.persistence.model.base.BaseModel;

/**
 * CatalogArea 区域管理类
 * @author YP
 */
@Entity
@Table(name = "CATALOG_AREA")
public class CatalogArea extends BaseModel{

	@Id
	@Column(name = "id", length = 64)
	private String id;
	//类别名称
	@ExcelAttribute(name = "所属目录类型") 
	private BaseData baseData;
	//目录类别名称
	@Column(name = "category_id", length = 100, nullable = false)
	private String categoryId;
	//目录区域名称
	@Column(name = "area_name", length = 100, nullable = false)
	@ExcelAttribute(name = "目录区域名称") 
	private String areaName ;

	
	
	//图片url地址
	@Column(name = "area_img", length = 100, nullable = false)
	private String areaImg;
	
	
	
	public CatalogArea(String areaName,String id){
		super();
		this.areaName = areaName;
		this.id = id;
	}
	public CatalogArea(){
		super();
	}
	private String imgType;//图片类别
	
	public String getImgType() {
		return imgType;
	}

	public void setImgType(String imgType) {
		this.imgType = imgType;
	}

	
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
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getAreaImg() {
		return areaImg;
	}

	public void setAreaImg(String areaImg) {
		this.areaImg = areaImg;
	}
	public CatalogArea(String id, String areaName, String imgType) {
		super();
		this.id = id;
		this.areaName = areaName;
		this.imgType = imgType;
	}

	
}
