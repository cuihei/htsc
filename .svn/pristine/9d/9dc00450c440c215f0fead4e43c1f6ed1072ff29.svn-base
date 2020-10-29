package com.ht.persistence.model.statisticalanalysis.view;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ht.persistence.model.base.BaseModel;

/**
 * 月度编绘计划实体类
 * 
 * @author zqy
 */
@Entity
@Table(name = "V_COMPILATION_MONTH_PLAN")
public class CompilationMonthPlanView  {

	@Id
	@Column(name = "id", length = 64)
	private String id;

	// 图号
	@Column(name = "sea_area", length = 64)
	private String seaArea;
	
	// 图号
	@Column(name = "map_no", length = 64)
	private String mapNo;
	
	
	
	// 图名
	@Column(name = "map_name", length = 64)
	private String mapName;

	// 比例尺
	@Column(name = "scale", length = 64)
	private String scale;
	
	// 性质
	@Column(name = "property", length = 100)
	private String property;
	
	// 比例尺
	@Column(name = "plan_exchange_time", length = 64)
	private Date planExchangeTime;
	
	
	@Column(name = "year", length = 4)
	private String year;
	
	

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

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public Date getPlanExchangeTime() {
		return planExchangeTime;
	}

	public void setPlanExchangeTime(Date planExchangeTime) {
		this.planExchangeTime = planExchangeTime;
	}

	public String getSeaArea() {
		return seaArea;
	}

	public void setSeaArea(String seaArea) {
		this.seaArea = seaArea;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	@Override
	public String toString() {
		return "CompilationMonthPlanView [id=" + id + ", mapNo=" + mapNo
				+ ", seaArea=" + seaArea + ", mapName=" + mapName + ", scale="
				+ scale + ", planExchangeTime=" + planExchangeTime
				+ ", property=" + property + ", year=" + year + "]";
	}
	
}
