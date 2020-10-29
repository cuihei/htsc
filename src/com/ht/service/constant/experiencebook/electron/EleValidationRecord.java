package com.ht.service.constant.experiencebook.electron;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ht.persistence.model.complication.formprop.FormValue;
import com.ht.service.constant.experiencebook.BaseExperience;

/**
 * 电子海图审定记录表
 * @author yp
 * @date 2016/12/7
 */
public class EleValidationRecord extends BaseExperience{
	Map<String, Object> map = new HashMap<String, Object>();

	/**
	 * 书签
	 */
	public final String MARKER = "table_dzhtsdjlb";
	public final String VALIDATION_RECORD_FROM_ID = "11051534397290042";
	// 存在问题
	final String DISCRIPTION = "text_dzhtsdjlb_discription";
	
	// 处理意见
	final String OPINION = "text_dzhtsdjlb_opinion";
	
	// 缺陷类别
	final String DEFECT_TYPE = "text_dzhtsdjlb_defect_type";
	
	// 缺陷扣分
	final String SCORE = "text_dzhtsdjlb_score";
		
	// 缺陷个数
	final String NUM = "text_dzhtsdjlb_num";
	
	//  通告改正至
	final String NOTICE_TO_CORRECTION = "text_dzhtsdjlb_notice_to_correction";
	
	// 累计缺陷扣分
	final String TOTAL = "text_dzhtsdjlb_total";
		
	// 调整系数
	final String COEFFICIENT = "text_dzhtsdjlb_coefficient";
			
	// 实际扣分
	final String ACTUAL = "text_dzhtsdjlb_actual";	
	
	// 质量评分
	final String GRADING = "text_dzhtsdjlb_grading";

	//  审定
	final String VALIDATION = "mtext_dzhtsdjlb_validation";
	
	// 审定时间
	final String VALIDATION_DATE = "text_dzhtsdjlb_validation_date";

	// 科长
	final String CHIEF = "mtext_dzhtsdjlb_chief";
	
	// 科长时间
	final String CHIEF_DATE = "text_dzhtsdjlb_chief_date";
	
	public void setDataProps(List<FormValue> valueList)
	{
		this.setProps(map, VALIDATION_RECORD_FROM_ID, valueList, "dzhtsdjlb");
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
	
	public void setNUM(String num)
	{
		this.map.put(this.NUM, num);
	}
	
	public void setDEFECT_TYPE(String defect_type)
	{
		this.map.put(this.DEFECT_TYPE, defect_type);
	}
	public Map<String, Object> getValidationRecordMap() {
		return this.map;
	}
}
