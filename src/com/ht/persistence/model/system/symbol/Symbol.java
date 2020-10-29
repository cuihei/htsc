package com.ht.persistence.model.system.symbol;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.ht.persistence.model.base.BaseModel;

/**
 * 小改正符号管理
 * @author huodesheng
 *
 */
@Entity
@Table(name = "Symbol")
public class Symbol extends BaseModel{
	
	private String id;//id
	
	private String code;//特征码
	
	private String img;//图片
	
	private String pictureType;//图片类型
	
	private String imgUrl;//图片类型
	
	public Symbol() {
		super();
		// TODO Auto-generated constructor stub
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}


	public String getPictureType() {
		return pictureType;
	}


	public void setPictureType(String pictureType) {
		this.pictureType = pictureType;
	}


	public String getImgUrl() {
		return imgUrl;
	}


	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}


	@Override
	public String toString() {
		return "Symbol [id=" + id + ", code=" + code + ", img=" + img
				+ ", pictureType=" + pictureType + ", imgUrl=" + imgUrl + "]";
	}


	public Symbol(String id, String code) {
		super();
		this.id = id;
		this.code = code;
	}



}
