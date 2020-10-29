package com.ht.persistence.model.catalog.detail;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ht.persistence.model.background.dicdata.basedata.BaseData;
import com.ht.persistence.model.base.BaseModel;
import com.ht.persistence.model.catalog.area.CatalogArea;

/**
 * catalog_detail 目录明细model
 * @author penghao
 */
/**
 * @author Administrator
 *
 */
@Entity
@Table(name = "catalog_detail")
public class CatalogDetail extends BaseModel{
 
		@Id
		@Column(name = "id", length = 64)
		private String id;                  //主键
		private BaseData type;				//目录类型
		private CatalogArea area;			//海区
		private String mapNo;				//图号
		private String mapName;				//图名
		private String scale;				//比例尺
		private String starLongitude;		//图幅范围经度（前）
		private String endLongitude;		//图幅范围经度（后）
		private String starLatitude;		//图幅范围纬度（前）
		private String endLatitude;			//图幅范围纬度（后）
		private String nature;				//性质
		private String measurementPeriod;	//基测
		private String testIng;	      //检测
		private String publicationYear;		//出版年份
		private String datumLatitude;		//基准纬度
		private String mapProportion;		//图积
		private String adjustmentProperty;	//调整性质
		private Date publicationDate;		//出版日期
		private String printNum;			//印次
		private String remarks;				//备注
		private Date creationDate;		//创建日期
		private String creator;			//创建人
		private String status;				//状态
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}	
		public BaseData getType() {
			return type;
		}
		public void setType(BaseData type) {
			this.type = type;
		}
		public CatalogArea getArea() {
			return area;
		}
		public void setArea(CatalogArea area) {
			this.area = area;
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
		public void setStarLongitude(String starLongitude) {
			this.starLongitude = starLongitude;
		}
		public String getEndLongitude() {
			return endLongitude;
		}
		public void setEndLongitude(String endLongitude) {
			this.endLongitude = endLongitude;
		}
		public String getStarLatitude() {
			return starLatitude;
		}
		public void setStarLatitude(String starLatitude) {
			this.starLatitude = starLatitude;
		}
		public String getEndLatitude() {
			return endLatitude;
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
		public String getPrintNum() {
			return printNum;
		}
		public void setPrintNum(String printNum) {
			this.printNum = printNum;
		}
		
		
		
		public Date getCreationDate() {
			return creationDate;
		}
		public void setCreationDate(Date creationDate) {
			this.creationDate = creationDate;
		}
		public String getCreator() {
			return creator;
		}
		public void setCreator(String creator) {
			this.creator = creator;
		}
		public String getRemarks() {
			return remarks;
		}
		public void setRemarks(String remarks) {
			this.remarks = remarks;
		}
		public String getStatus()
		{
			return status;
		}
		public void setStatus(String status)
		{
			this.status = status;
		}
		public CatalogDetail(String id, BaseData type, CatalogArea area,
				String mapNo, String mapName, String scale,
				String starLongitude, String endLongitude, String starLatitude,
				String endLatitude, String nature, String measurementPeriod,String testIng,
				String publicationYear, String datumLatitude,
				String mapProportion, String adjustmentProperty,
				Date publicationDate, String printNum, String remarks,
				String status) {
			super();
			this.id = id;
			this.type = type;
			this.area = area;
			this.mapNo = mapNo;
			this.mapName = mapName;
			this.scale = scale;
			this.starLongitude = starLongitude;
			this.endLongitude = endLongitude;
			this.starLatitude = starLatitude;
			this.endLatitude = endLatitude;
			this.nature = nature;
			this.measurementPeriod = measurementPeriod;
			this.testIng = testIng;
			this.publicationYear = publicationYear;
			this.datumLatitude = datumLatitude;
			this.mapProportion = mapProportion;
			this.adjustmentProperty = adjustmentProperty;
			this.publicationDate = publicationDate;
			this.printNum = printNum;
			this.remarks = remarks;
			this.status = status;
		}
		public CatalogDetail() {
			super();
			// TODO Auto-generated constructor stub
		}
		
}
