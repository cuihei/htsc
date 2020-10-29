package com.ht.service.constant.experiencebook.correctionnotice;

import java.util.HashMap;
import java.util.Map;
/**
 * 改正通告编辑  中心质量检查记录表
 * @author huodesheng
 *
 */
public class CentreQualityInspect {
	Map<String, Object> map = new HashMap<String, Object>();

	/**
	 * 书签
	 */
	public final String MARKER = "table_zxzljcjlb";

	/**
	 * 标题
	 */
	final String TITLE = "text_zjjlb_title";

	/**
	 * 存在问题
	 */
	final String DISCRIPTION = "text_zxzljcjlb_discription";

	/**
	 * 处理意见
	 */
	final String OPINION = "text_zxzljcjlb_opinion";

	/**
	 * 缺陷类别
	 */
	final String DEFECT_TYPE = "text_zxzljcjlb_defect_type";

	/**
	 * 缺陷个数
	 */
	final String NUM = "text_zxzljcjlb_num";

	/**
	 * 缺陷扣分
	 */
	final String SCORE = "text_zxzljcjlb_score";

	/**
	 * 处理结果
	 */
	final String CLJG = "text_zxzljcjlb_cljg";

	/**
	 * 缺陷累计扣分
	 */
	final String TOTAL = "text_zxzljcjlb_total";

	/**
	 * 质量评分
	 */
	final String GRADING = "text_zxzljcjlb_grading";

	/**
	 * 质量等级
	 */
	final String ZLDJ = "text_zxzljcjlb_zldj";

	/**
	 * 编辑
	 */
	final String QUALITY = "mtext_zxzljcjlb_quality_person";

	/**
	 * 编辑日期
	 */
	final String QUALITY_DATE = "text_zxzljcjlb_quality_date";

	/**
	 * 审定
	 */
	final String VALIDATION = "mtext_zxzljcjlb_validation";

	/**
	 * 审定日期
	 */
	final String VALIDATION_DATE = "text_zxzljcjlb_validation_date";

	/**
	 * 科长
	 */
	final String CHIEF = "mtext_zxzljcjlb_chief";

	/**
	 * 科长时间
	 */
	final String CHIEF_DATE = "text_zxzljcjlb_chief_date";

	public void setDISCRIPTION(String discription) {
		this.map.put(this.DISCRIPTION, discription);
	}

	public void setOPINION(String opinion) {
		this.map.put(this.OPINION, opinion);
	}

	public void setDEFECT_TYPE(String defect_type) {
		this.map.put(this.DEFECT_TYPE, defect_type);
	}

	public void setNUM(String num) {
		this.map.put(this.NUM, num);
	}

	public void setSCORE(String score) {
		this.map.put(this.SCORE, score);
	}

	public void setCLJG(String cljg) {
		this.map.put(this.CLJG, cljg);
	}

	public void setTOTAL(String total) {
		this.map.put(this.TOTAL, total);
	}

	public void setGRADING(String grading) {
		this.map.put(this.GRADING, grading);
	}

	public void setZLDJ(String zldj) {
		this.map.put(this.ZLDJ, zldj);
	}

	public void setQUALITY(String quality) {
		this.map.put(this.QUALITY, quality);
	}

	public void setQUALITY_DATE(String quality_date) {
		this.map.put(this.QUALITY_DATE, quality_date);
	}

	public void setVALIDATION(String validation) {
		this.map.put(this.VALIDATION, validation);
	}

	public void setVALIDATION_DATE(String validation_date) {
		this.map.put(this.VALIDATION_DATE, validation_date);
	}

	public void setCHIEF(String chief) {
		this.map.put(this.CHIEF, chief);
	}

	public void setCHIEF_DATE(String chief_date) {
		this.map.put(this.CHIEF_DATE, chief_date);
	}

	public String getMARKER() {
		return MARKER;
	}
	public Map<String, Object> getMap() {
		return this.map;
	}
}
