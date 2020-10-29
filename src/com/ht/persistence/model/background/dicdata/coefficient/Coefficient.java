package com.ht.persistence.model.background.dicdata.coefficient;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ht.persistence.model.background.dicdata.basedata.BaseData;
import com.ht.persistence.model.base.BaseModel;


/**
 * 调整系数的实体类
 *
 */
@Entity
@Table(name = "COEFFICIENT")
public class Coefficient extends BaseModel{
	
	@Id
	@Column(name = "id", length = 64)
	private String id;				//主键id
	private String mapNo;			//图号
	private String mapName;			//图名
	private String scale;			//比例尺
	private BaseData type;			//调整系数类型
	private String firstEdition;		//首版
	private String reprint;			//再版
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
	public String getMapName() {
		return mapName;
	}
	public void setMapName(String mapName) {
		this.mapName = mapName;
	}
	public String getScale() {
		return scale;
	}
	public void setScale(String scale) {
		this.scale = scale;
	}
	public BaseData getType() {
		return type;
	}
	public void setType(BaseData type) {
		this.type = type;
	}
	public String getFirstEdition() {
		return firstEdition;
	}
	public void setFirstEdition(String firstEdition) {
		this.firstEdition = firstEdition;
	}
	public String getReprint() {
		return reprint;
	}
	public void setReprint(String reprint) {
		this.reprint = reprint;
	}
	
}
