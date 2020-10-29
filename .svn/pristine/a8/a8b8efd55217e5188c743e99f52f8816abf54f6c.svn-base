package com.ht.service.constant.experiencebook.sourcedata;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ht.persistence.model.complication.formprop.FormValue;
import com.ht.service.constant.experiencebook.BaseExperience;

/**
 * 源数据审定结论记录表类
 * @author zqy
 * @date 2016/12/7
 */
public class ValidationConclusionRecord extends BaseExperience
{

	Map<String, Object> map = new HashMap<String, Object>();

	/**
	 * 书签
	 */
	public final String MARKER = "table_sdjljlb";

	public final String VALIDATION_CONCLUSION_FROM_ID = "01091219447470524";

	public final String VALIDATION_PF_CONCLUSION_FROM_ID = "11031853234420047";
	/**
	 * 标题
	 */
	final String TITLE = "text_sdjljlb_title";

	/**
	 * 质量评分
	 */
	final String SDJL = "text_sdjljlb_sdjl";
	
	/**
	 * 评分等级
	 */
	final String PFDJ = "text_sdjljlb_pfdj";
	
	/**
	 * 通告改正至
	 */
	final String NOTICE_TO_CORRECTION = "text_sdjljlb_notice_to_correction";
	/**
	 * 质检
	 */
	final String QUALITY = "mtext_sdjljlb_quality";
	/**
	 * 质检时间
	 */
	final String QUALITY_TIME = "text_sdjljlb_quality_time";
	/**
	 * 科长
	 */
	final String CHIEF = "mtext_sdjljlb_chief";
	/**
	 * 科长时间
	 */
	final String CHIEF_TIME = "text_sdjljlb_chief_time";
	/**
	 * 审定
	 */
	final String VALIDATION = "mtext_sdjljlb_validation";
	/**
	 * 审定时间
	 */
	final String VALIDATION_SYSTEM_DATE = "text_sdjljlb_validation_system_date";

	/**
	 * 科长意见
	 */
	final String SECTION_CHIEF = "text_sdjljlb_section_chief";
	/**
	 * 科长意见时间
	 */
	final String SECTION_CHIEF_TIME = "text_sdjljlb_section_chief_time";
	/**
	 * 质量检验科科长签名：
	 */
	final String CHIEF_SIGNATURE = "mtext_sdjljlb_chief_signature";

	public void setDataProps(List<FormValue> valueList)
	{
		this.setProps(map, VALIDATION_CONCLUSION_FROM_ID, valueList, "sdjljlb");
	}
	
	public void setTITLE(String title)
	{
		this.map.put(this.TITLE, title);
	}
	
	public void setSDJL(String sdjl)
	{
		this.map.put(this.SDJL, sdjl);
	}


	public void setPFDJ(String pfdj)
	{
		this.map.put(this.PFDJ, pfdj);
	}

	public void setNOTICE_TO_CORRECTION(String notice_to_correction)
	{
		this.map.put(this.NOTICE_TO_CORRECTION, notice_to_correction);
	}

	public void setQUALITY(String quality)
	{
		this.map.put(this.QUALITY, quality);
	}

	public void setQUALITY_TIME(String quality_time)
	{
		this.map.put(this.QUALITY_TIME, quality_time);
	}

	public void setCHIEF(String chief)
	{
		this.map.put(this.CHIEF, chief);
	}

	public void setCHIEF_TIME(String chief_time)
	{
		this.map.put(this.CHIEF_TIME, chief_time);
	}

	public void setVALIDATION(String validation)
	{
		this.map.put(this.VALIDATION, validation);
	}

	public void setVALIDATION_SYSTEM_DATE(String validation_system_date)
	{
		this.map.put(this.VALIDATION_SYSTEM_DATE, validation_system_date);
	}

	public void setSECTION_CHIEF(String section_chief)
	{
		this.map.put(this.SECTION_CHIEF, section_chief);
	}

	public void setSECTION_CHIEF_TIME(String section_chief_time)
	{
		this.map.put(this.SECTION_CHIEF_TIME, section_chief_time);
	}

	public void setCHIEF_SIGNATURE(String chief_signature)
	{
		this.map.put(this.CHIEF_SIGNATURE, chief_signature);
	}

	public Map<String, Object> getValidationConclusionRecordMap()
	{
		return this.map;
	}
}
