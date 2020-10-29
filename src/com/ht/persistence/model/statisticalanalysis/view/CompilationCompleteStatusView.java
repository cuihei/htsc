package com.ht.persistence.model.statisticalanalysis.view;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ht.persistence.model.base.BaseModel;

/**
 * 编绘进度视图实体类
 * 
 * @author zqy
 */
@Entity
@Table(name = "V_COMPILATION_COMPLATE_STATUS")
public class CompilationCompleteStatusView  {

	@Id
	@Column(name = "id", length = 64)
	private String id;

	// 图号
	@Column(name = "map_no", length = 64)
	private String mapNo;
	
	// 图名
	@Column(name = "map_name", length = 64)
	private String mapName;

	// 比例尺
	@Column(name = "scale", length = 64)
	private String scale;

	// 编绘内容
	@Column(name = "content", length = 200)
	private String content;

	// 完成日期（审定）
	@Column(name = "complete_date")
	private Date completeDate;
	
	@Column(name = "create_year", length = 4)
	private String createYear;
	@Column(name = "STATUS", length = 100)
	private String status;


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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCompleteDate() {
		return completeDate;
	}

	public void setCompleteDate(Date completeDate) {
		this.completeDate = completeDate;
	} 

	public String getCreateYear() {
		return createYear;
	}

	public void setCreateYear(String createYear) {
		this.createYear = createYear;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
	

}
