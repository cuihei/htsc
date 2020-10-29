package com.ht.service.constant.experiencebook.paper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ht.persistence.model.complication.formprop.FormValue;
import com.ht.service.constant.experiencebook.BaseExperience;

/**
 * 纸海图审定记录表
 * @author yp
 * @date 2016/12/7
 */
public class PaperValidationRecord extends BaseExperience{
	Map<String, Object> map = new HashMap<String, Object>();

	/**
	 * 书签
	 */
	public final String MARKER = "table_zhtsdjlb";
	public final String VALIDATION_RECORD_FROM_ID = "11051600081420117";
	// 存在问题
	final String DISCRIPTION = "text_zhtsdjlb_discription";
	
	// 处理意见
	final String OPINION = "text_zhtsdjlb_opinion";
	
	// 质量扣分
	final String SCORE = "text_zhtsdjlb_score";
	
	//  通告改正至
	final String NOTICE_TO_CORRECTION = "text_zhtsdjlb_notice_to_correction";
	
	// 质量累计扣分
	final String TOTAL = "text_zhtsdjlb_total";
		
	// 调整系数
	final String COEFFICIENT = "text_zhtsdjlb_coefficient";
			
	// 实际扣分
	final String ACTUAL = "text_zhtsdjlb_actual";	
	
	// 质量评分
	final String GRADING = "text_zhtsdjlb_grading";

	//  审定
	final String VALIDATION = "mtext_zhtsdjlb_validation";
	
	// 审定时间
	final String VALIDATION_DATE = "text_zhtsdjlb_validation_date";

	// 科长
	final String CHIEF = "mtext_zhtsdjlb_chief";
	
	// 科长时间
	final String CHIEF_DATE = "text_zhtsdjlb_chief_date";
	
	public void setDataProps(List<FormValue> valueList)
	{
		this.setProps(map, VALIDATION_RECORD_FROM_ID, valueList, "zhtsdjlb");
	}
	
	public void setDISCRIPTION(String discription) {
		this.map.put(this.DISCRIPTION, discription);
	}

	public void setOPINION(String opinion) {
		this.map.put(this.OPINION, opinion);
	}

	public void setSCORE(String score) {
		this.map.put(this.SCORE, score);
	}

	public void setNOTICE_TO_CORRECTION(String notice_to_correction) {
		this.map.put(this.NOTICE_TO_CORRECTION, notice_to_correction);
	}

	public void setTOTAL(String total) {
		this.map.put(this.TOTAL, total);
	}

	public void setCOEFFICIENT(String coefficient) {
		this.map.put(this.COEFFICIENT, coefficient);
	}

	public void setACTUAL(String actual)
	{
		this.map.put(this.ACTUAL, actual);
	}

	public void setGRADING(String grading)
	{
		this.map.put(this.GRADING, grading);
	}

	public void setVALIDATION(String validation)
	{
		this.map.put(this.VALIDATION, validation);
	}

	public void setVALIDATION_DATE(String validation_date)
	{
		this.map.put(this.VALIDATION_DATE, validation_date);
	}

	public void setCHIEF(String chief)
	{
		this.map.put(this.CHIEF, chief);
	}

	public void setCHIEF_DATE(String chief_date)
	{
		this.map.put(this.CHIEF_DATE, chief_date);
	}
	
	public Map<String, Object> getValidationRecordMap() {
		return this.map;
	}
}
