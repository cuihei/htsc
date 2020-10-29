package com.ht.persistence.model.drawtask.plan;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ht.persistence.model.base.BaseModel;

/**
 * 计划文件类
 * @author yp
 */
@Entity
@Table(name = "BOOK_FILE")
public class PlanFile extends BaseModel {
	
	@Id
	private String id; //标识

	private String fileName; //文件名

	private String suffixName; //后缀名

	private String spaceSize; //空间大小

	private String filePath; //路径

	private Date planYear;//计划所属年份
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getSuffixName() {
		return suffixName;
	}

	public void setSuffixName(String suffixName) {
		this.suffixName = suffixName;
	}

	public String getSpaceSize() {
		return spaceSize;
	}

	public void setSpaceSize(String spaceSize) {
		this.spaceSize = spaceSize;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public Date getPlanYear() {
		return planYear;
	}

	public void setPlanYear(Date planYear) {
		this.planYear = planYear;
	}
	
	
}
