package com.ht.persistence.model.catalog.history;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ht.persistence.model.base.BaseModel;

/**
 * catalog_detail 目录明细model
 * @author yaoping
 */
@Entity
@Table(name = "history_catalog_detail")
public class HistoryCatalogDetail extends BaseModel{
 
		@Id
		@Column(name = "id", length = 64)
		private String id;                    	//主键
		private String detailId;				//目录明细ID
		@Column(name = "category_id", length = 64, nullable = false)
		private String categoryId;            	//目录类型ID
		@Column(name = "area_id", length = 64, nullable = false)
		private String areaId;					//目录区域ID
		private String mapNo;					//图号
		private String mapName;					//图名
		private String scale;					//比例尺
		private String starLongitude;		//图幅范围经度（前）
		private String endLongitude;		//图幅范围经度（后）
		private String starLatitude;		//图幅范围纬度（前）
		private String endLatitude;			//图幅范围纬度（后）
		private String nature;					//性质
		private String measurementPeriod;		//基测
		private String testIng;   	//检测
		private String publicationYear;			//出版年份
		private String datumLatitude;			//基准纬度
		private String mapProportion;			//图积
		private String adjustmentProperty;		//调整性质
		@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8") 
		private Date publicationDate;			//出版日期
		private String printNum;					//版次
		private String remarks;					//备注
		

		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getCategoryId() {
			return categoryId;
		}
		public void setCategoryId(String categoryId) {
			this.categoryId = categoryId;
		}
		public String getAreaId() {
			return areaId;
		}
		public void setAreaId(String areaId) {
			this.areaId = areaId;
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
		
		public String getStarLongitude() {
			return starLongitude;
		}
		public String getEndLongitude() {
			return endLongitude;
		}
		public String getStarLatitude() {
			return starLatitude;
		}
		public String getEndLatitude() {
			return endLatitude;
		}
		public void setStarLongitude(String starLongitude) {
			this.starLongitude = starLongitude;
		}
		public void setEndLongitude(String endLongitude) {
			this.endLongitude = endLongitude;
		}
		public void setStarLatitude(String starLatitude) {
			this.starLatitude = starLatitude;
		}
		public void setEndLatitude(String endLatitude) {
			this.endLatitude = endLatitude;
		}
		public String getNature() {
			return nature;
		}
		public void setNature(String nature) {
			this.nature = nature;
		}
		public String getMeasurementPeriod() {
			return measurementPeriod;
		}
		public void setMeasurementPeriod(String measurementPeriod) {
			this.measurementPeriod = measurementPeriod;
		}
		public String getTestIng() {
			return testIng;
		}
		public void setTestIng(String testIng) {
			this.testIng = testIng;
		}
		public String getPublicationYear() {
			return publicationYear;
		}
		public void setPublicationYear(String publicationYear) {
			this.publicationYear = publicationYear;
		}
		public String getDatumLatitude() {
			return datumLatitude;
		}
		public void setDatumLatitude(String datumLatitude) {
			this.datumLatitude = datumLatitude;
		}
		public String getMapProportion() {
			return mapProportion;
		}
		public void setMapProportion(String mapProportion) {
			this.mapProportion = mapProportion;
		}
		public String getAdjustmentProperty() {
			return adjustmentProperty;
		}
		public void setAdjustmentProperty(String adjustmentProperty) {
			this.adjustmentProperty = adjustmentProperty;
		}
		public Date getPublicationDate() {
			return publicationDate;
		}
		public void setPublicationDate(Date publicationDate) {
			this.publicationDate = publicationDate;
		}
		public String getRemarks() {
			return remarks;
		}
		public void setRemarks(String remarks) {
			this.remarks = remarks;
		}
		public String getDetailId() {
			return detailId;
		}
		public void setDetailId(String detailId) {
			this.detailId = detailId;
		}
		public String getPrintNum() {
			return printNum;
		}
		public void setPrintNum(String printNum) {
			this.printNum = printNum;
		}
}
