package com.ht.persistence.model.drawtask.plan;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ht.persistence.model.background.dicdata.basedata.BaseData;
import com.ht.persistence.model.base.BaseModel;
import com.ht.persistence.model.catalog.area.CatalogArea;

/**
 * plan 月度计划model
 * @author penghao
 */
@Entity
@Table(name = "PLAN")
public class Plan extends BaseModel{
 
		@Id
		@Column(name = "id", length = 64)
		private String id;               //主键
		private BaseData category;  		//计划类别
		private String seaArea;			//海区
		private String revision;			//版次
		private String printQuantity;		//打印数量
		private String mapNo;				//图号
		private String mapName;				//图名
		private String scale;				//比例尺
		private String type;		        //类型（基测、检测等）
		private String workload;		   	//测绘工作量
		private Date deliverTime;			//资料汇交时间
		private String measurementPeriod;	//基测周期
		private String testIng;	//基测周期
		private String year;				//年份
		private String planMonth;			//所属月份
		private String discription;			//说明
		private String relationId;			//月度计划关联Id
		private String taskSource;			//任务来源
		private String nature;			    //上次测量/编绘性质
		private String taskType;			//任务类型
		private String lastYear;			//上次测量年份
		
		

		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public BaseData getCategory() {
			return category;
		}
		public void setCategory(BaseData category) {
			this.category = category;
		}
		public String getRevision() {
			return revision;
		}
		public String getSeaArea() {
			return seaArea;
		}
		public void setSeaArea(String seaArea) {
			this.seaArea = seaArea;
		}
		public void setRevision(String revision) {
			this.revision = revision;
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
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public String getWorkload() {
			return workload;
		}
		public void setWorkload(String workload) {
			this.workload = workload;
		}
		public Date getDeliverTime() {
			return deliverTime;
		}
		public void setDeliverTime(Date deliverTime) {
			this.deliverTime = deliverTime;
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
		public String getDiscription() {
			return discription;
		}
		public void setDiscription(String discription) {
			this.discription = discription;
		}
		public String getPlanMonth() {
			return planMonth;
		}
		public void setPlanMonth(String planMonth) {
			this.planMonth = planMonth;
		}
		public String getPrintQuantity() {
			return printQuantity;
		}
		public void setPrintQuantity(String printQuantity) {
			this.printQuantity = printQuantity;
		}
		public String getRelationId() {
			return relationId;
		}
		public void setRelationId(String relationId) {
			this.relationId = relationId;
		}
		public String getYear() {
			return year;
		}
		public void setYear(String year) {
			this.year = year;
		}
		public String getTaskSource() {
			return taskSource;
		}
		public void setTaskSource(String taskSource) {
			this.taskSource = taskSource;
		}
		public String getNature() {
			return nature;
		}
		public void setNature(String nature) {
			this.nature = nature;
		}
		public String getTaskType() {
			return taskType;
		}
		public void setTaskType(String taskType) {
			this.taskType = taskType;
		}
		public String getLastYear() {
			return lastYear;
		}
		public void setLastYear(String lastYear) {
			this.lastYear = lastYear;
		}
}
