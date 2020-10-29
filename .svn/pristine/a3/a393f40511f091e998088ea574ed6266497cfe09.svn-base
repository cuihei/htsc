package com.ht.service.constant.experiencebook.paper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ht.persistence.model.complication.formprop.FormValue;
import com.ht.service.constant.experiencebook.BaseExperience;

/**
 * 纸海图审定结论记录表类
 * @author yp
 * @date 2016/12/7
 */
public class PaperValidationConclusionRecord extends BaseExperience
{

	Map<String, Object> map = new HashMap<String, Object>();

	/**
	 * 书签
	 */
	public final String MARKER = "table_sdjljlb";

	public final String VALIDATION_CONCLUSION_FROM_ID = "11051600577250120";
	
	public final String VALIDATION_CLQK_FROM_ID = "01091220264600538";
	/**
	 * 审定结论
	 */
	final String SDJL = "text_sdjljlb_sdjl";
	/**
	 * 质量评分及等级
	 */
	final String PFDJ = "text_sdjljlb_pfdj";
	/**
	 * 审定
	 */
	final String VALIDATION = "mtext_sdjljlb_validation";
	/**
	 * 审定时间
	 */
	final String VALIDATION_DATE = "text_sdjljlb_validation_date";

	/**
	 * 通告改正至
	 */
	final String NOTICE_TO_CORRECTION = "text_sdjljlb_notice_to_correction";
	/**
	 * 质检
	 */
	final String QUALITY_PERSON = "mtext_sdjljlb_quality_person";
	/**
	 * 质检时间
	 */
	final String QUALITY_DATE = "text_sdjljlb_quality_date";
	/**
	 * 科长意见
	 */
	final String CHIEF_OPINION = "text_sdjljlb_chief_opinion";
	/**
	 * 科长意见时间
	 */
	final String CHIEF_OPINION_DATE = "text_sdjljlb_chief_opinion_date";
	/**
	 * 科长
	 */
	final String CHIEF = "mtext_sdjljlb_chief";
	/**
	 * 科长时间
	 */
	final String CHIEF_DATE = "text_sdjljlb_chief_date";
	/**
	 * 质量检验科科长签名：
	 */
	final String CHIEF_SIGNATURE = "mtext_sdjljlb_chief_signature";

	public void setDataProps(List<FormValue> valueList)
	{
		this.setProps(map, VALIDATION_CONCLUSION_FROM_ID, valueList, "sdjljlb");
	}
	public void setClqkDataProps(List<FormValue> valueList)
	{
		this.setProps(map, VALIDATION_CLQK_FROM_ID, valueList, "sdjljlb");
	}
	
	public void setVALIDATION(String validation)
	{
		this.map.put(this.VALIDATION, validation);
	}
	
	public void setVALIDATION_DATE(String validation_date)
	{
		this.map.put(this.VALIDATION_DATE, validation_date);
	}

	public void setNOTICE_TO_CORRECTION(String notice_to_correction)
	{
		this.map.put(this.NOTICE_TO_CORRECTION, notice_to_correction);
	}

	public void setQUALITY_PERSON(String quality_person)
	{
		this.map.put(this.QUALITY_PERSON, quality_person);
	}

	public void setQUALITY_DATE(String quality_date)
	{
		this.map.put(this.QUALITY_DATE, quality_date);
	}

	public void setCHIEF_OPINION(String chief_opinion)
	{
		this.map.put(this.CHIEF_OPINION, chief_opinion);
	}

	public void setCHIEF_OPINION_DATE(String chief_opinion_date)
	{
		this.map.put(this.CHIEF_OPINION_DATE, chief_opinion_date);
	}

	public void setCHIEF(String chief)
	{
		this.map.put(this.CHIEF, chief);
	}

	public void setCHIEF_DATE(String chief_date)
	{
		this.map.put(this.CHIEF_DATE, chief_date);
	}
	
	public void setCHIEF_SIGNATURE(String chief_signature)
	{
		this.map.put(this.CHIEF_SIGNATURE, chief_signature);
	}
	public void setSDJL(String sdjl)
	{
		this.map.put(this.SDJL, sdjl);
	}

	public void setPFDJ(String pfdj)
	{
		this.map.put(this.PFDJ, pfdj);
	}
	public Map<String, Object> getPaperValidationConclusionRecordMap()
	{
		return this.map;
	}
}
