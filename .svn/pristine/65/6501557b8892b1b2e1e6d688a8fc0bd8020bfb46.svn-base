package com.ht.persistence.model.datum.productupdsourcedata;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ht.persistence.model.base.BaseModel;
/**
 * 产品修改源数据实体类
 * @author Mr_zyd
 * @date 2017/12/12
 */
@Entity
@Table(name = "Product_Upd_Source_Data")
public class ProductUpdSourceData extends BaseModel{
	@Id
	@Column(name = "id", length = 64)
	private String id;// 标识
	
	@Column(name = "draw", length = 64)
	private String draw;// 图号
	
	@Column(name = "figure_caption", length = 64)
	private String figure_caption;// 图名

	@Column(name = "compiler", length = 64)
	private String compiler;// 编绘员
	
	@Column(name = "quality_inspector", length = 64)
	private String quality_inspector;// 质检员
	
	@Column(name = "authorized_member", length = 64)
	private String authorized_member;// 审定员
	
	@Column(name = "sourcedata_problem", length = 64)
	private String sourcedata_problem;// 源数据问题

	@Column(name = "status", length = 20)
	private String status;// 状态（0-待确认，1-编绘员确认，2质检员确认，3-审定员确认）

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDraw() {
		return draw;
	}

	public void setDraw(String draw) {
		this.draw = draw;
	}

	public String getFigure_caption() {
		return figure_caption;
	}

	public void setFigure_caption(String figure_caption) {
		this.figure_caption = figure_caption;
	}

	public String getCompiler() {
		return compiler;
	}

	public void setCompiler(String compiler) {
		this.compiler = compiler;
	}

	public String getQuality_inspector() {
		return quality_inspector;
	}

	public void setQuality_inspector(String quality_inspector) {
		this.quality_inspector = quality_inspector;
	}

	public String getAuthorized_member() {
		return authorized_member;
	}

	public void setAuthorized_member(String authorized_member) {
		this.authorized_member = authorized_member;
	}

	public String getSourcedata_problem() {
		return sourcedata_problem;
	}

	public void setSourcedata_problem(String sourcedata_problem) {
		this.sourcedata_problem = sourcedata_problem;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
