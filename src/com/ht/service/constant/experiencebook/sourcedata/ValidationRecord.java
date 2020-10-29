package com.ht.service.constant.experiencebook.sourcedata;

import java.util.HashMap;
import java.util.Map;

/**
 * 源数据审定记录表
 * @author zqy
 * @date 2016/12/7
 */
public class ValidationRecord {
	Map<String, Object> map = new HashMap<String, Object>();

	/**
	 * 书签
	 */
	public final String MARKER = "table_sdjlb";

	/**
	 * 标题
	 */
	final String TITLE = "text_sdjlb_title";
	
	/**
	 * 通告改正至
	 */
	final String NOTICE_TO_CORRECTION = "text_sdjlb_notice_to_correction";
	
	/**
	 * 审定
	 */
	final String VALIDATION = "mtext_sdjlb_validation";
	/**
	 * 审定时间
	 */
	final String VALIDATION_DATE = "text_sdjlb_validation_date";

	/**
	 * 科长
	 */
	final String CHIEF = "mtext_sdjlb_chief";
	
	/**
	 * 科长时间
	 */
	final String CHIEF_DATE = "text_sdjlb_chief_date";

	// 涉及图层
	final String SDJLB_LAYER = "text_sdjlb_layer";

	// 存在问题（缺陷项目+缺陷内容）
	final String SDJLB_PROBLEM = "text_sdjlb_problem";
	
	// 处理意见
	final String SDJLB_OPINION = "text_sdjlb_opinion";
	
	// 缺陷类别
	final String SDJLB_DEEP = "text_sdjlb_deep";
	
	// 缺陷个数
	final String SDJLB_NUM = "text_sdjlb_num";
	
	// 缺陷扣分
	final String SDJLB_SCORE = "text_sdjlb_score";
	
	// 备注
	final String SDJLB_REMARKS = "text_sdjlb_remarks";
	
	// 缺陷累计扣分
	final String SDJLB_TOTAL = "text_sdjlb_total";
	
	// 调整系数
	final String SDJLB_COEFFICIENT = "text_sdjlb_coefficient";
	
	// 实际扣分
	final String SDJLB_ACTUAL = "text_sdjlb_actual";
	
	// 质量评分
	final String SDJLB_GRADING = "text_sdjlb_grading";
	
	public void setTITLE(String title) {
		this.map.put(this.TITLE, title);
	}

	public void setNOTICE_TO_CORRECTION(String notice_to_correction) {
		this.map.put(this.NOTICE_TO_CORRECTION, notice_to_correction);
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

	public void setSDJLB_LAYER(String layer)
	{
		this.map.put(this.SDJLB_LAYER, layer);
	}

	public void setSDJLB_PROBLEM(String problem)
	{
		this.map.put(this.SDJLB_PROBLEM, problem);
	}

	public void setSDJLB_OPINION(String opinion)
	{
		this.map.put(this.SDJLB_OPINION, opinion);
	}

	public void setSDJLB_DEEP(String deep)
	{
		this.map.put(this.SDJLB_DEEP, deep);
	}

	public void setSDJLB_NUM(String num)
	{
		this.map.put(this.SDJLB_NUM, num);
	}

	public void setSDJLB_SCORE(String score)
	{
		this.map.put(this.SDJLB_SCORE, score);
	}

	public void setSDJLB_REMARKS(String remarks)
	{
		this.map.put(this.SDJLB_REMARKS, remarks);
	}

	public void setSDJLB_TOTAL(String total)
	{
		this.map.put(this.SDJLB_TOTAL, total);
	}

	public void setSDJLB_COEFFICIENT(String coefficient)
	{
		this.map.put(this.SDJLB_COEFFICIENT, coefficient);
	}

	public void setSDJLB_ACTUAL(String actual)
	{
		this.map.put(this.SDJLB_ACTUAL, actual);
	}

	public void setSDJLB_GRADING(String grading)
	{
		this.map.put(this.SDJLB_GRADING, grading);
	}
	
	public Map<String, Object> getValidationRecordMap() {
		return this.map;
	}
}
