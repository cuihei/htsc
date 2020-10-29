package com.ht.persistence.model.system.maps;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ht.persistence.model.base.BaseModel;
/**
 * Maps 图幅管理类
 * @author yp
 */
@Entity
@Table(name = "MAPS")
public class Maps extends BaseModel{
	private String id;// 标识
	private String mapNo;// 图号
	private String picture;// 图片
	private String pictureType;//图片类型
	
	public String getPictureType() {
		return pictureType;
	}

	public void setPictureType(String pictureType) {
		this.pictureType = pictureType;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMapNo() {
		return mapNo;
	}

	public void setMapNo(String mapNo) {
		this.mapNo = mapNo;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public Maps(String id, String mapNo) {
		super();
		this.id = id;
		this.mapNo = mapNo;
	}

	public Maps() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
