package com.ht.persistence.model.statisticalanalysis;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ht.persistence.model.base.BaseModel;

/**
 * 额定工天实体类
 * 
 * @author zqy
 */
@Entity
@Table(name = "COMPILATION_WORKDAYS")
public class CompilationWorkDays extends BaseModel {

	@Id
	@Column(name = "id", length = 32)
	private String id;

	// 图号
	@Column(name = "MAP_NO", length = 100)
	private String mapNo;

	// 图名
	@Column(name = "MAP_NAME", length = 100)
	private String mapName;
	
	// 比例尺
	@Column(name = "SCALE", length = 100)
	private String scale;
		
	// 0首版1再版
	@Column(name = "REVISION", length = 100)
	private String revision;

	// 源数据，纸海图，电子海图
	@Column(name = "CONTENT")
	private String content;

	// 编绘工天
	@Column(name = "COMPILATION_WORKDAYS")
	private String compilationWorkDays;
		
	//质检工天
	@Column(name = "CHECK_WORKDAYS")
	private String checkWorkDays;
		
	//审定工天
	@Column(name = "EXAMINE_WORKDAYS")
	private String examineWorkDays;
	
	//额定或者实际工天flag
	//额定:planWorkDays 实际:realWorkDays
	private String flag;
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

	public String getRevision() {
		return revision;
	}

	public void setRevision(String revision) {
		this.revision = revision;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCompilationWorkDays() {
		return compilationWorkDays;
	}

	public void setCompilationWorkDays(String compilationWorkDays) {
		this.compilationWorkDays = compilationWorkDays;
	}

	public String getCheckWorkDays() {
		return checkWorkDays;
	}

	public void setCheckWorkDays(String checkWorkDays) {
		this.checkWorkDays = checkWorkDays;
	}

	public String getExamineWorkDays() {
		return examineWorkDays;
	}

	public void setExamineWorkDays(String examineWorkDays) {
		this.examineWorkDays = examineWorkDays;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	
}
