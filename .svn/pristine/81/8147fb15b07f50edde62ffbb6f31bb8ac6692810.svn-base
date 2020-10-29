package com.ht.service.constant.experiencebook.electron;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ht.persistence.model.complication.formprop.FormValue;
import com.ht.service.constant.experiencebook.BaseExperience;

/**
 * 电子海图质检结论记录表类
 * @author yp
 * @date 2016/12/7
 */
public class EleQualityInspectionConclusionRecord extends BaseExperience
{

	Map<String, Object> map = new HashMap<String, Object>();

	public final String QUALITY_INSPECTION_FORM_ID = "11051532589870038";
	public final String QUALITY_CLQK_FORM_ID = "0109122145420545";
	
	/**
	 * 书签
	 */
	public final String MARKER = "table_dzhtzjjlb";
	/**
	 * 纸海图质检结论
	 */
	final String JL = "text_dzhtzjjlb_jl";

	/**
	 * 质量评分及等级
	 */
	final String PFDJ = "text_dzhtzjjlb_pfdj";
	
	/**
	 * 质检
	 */
	final String QUALITY_PERSON = "mtext_dzhtzjjlb_quality_person";
	/**
	 * 质检时间
	 */
	final String QUALITY_DATE = "text_dzhtzjjlb_quality_date";
	/**
	 * 通告改正至
	 */
	final String NOTICE_TO_CORRECTION = "text_dzhtzjjlb_notice_to_correction";
	/**
	 * 编绘
	 */
	final String COMPILATION_PERSON = "mtext_dzhtzjjlb_compilation_person";
	/**
	 * 编绘系统时间
	 */
	final String COMPILATION_DATE = "text_dzhtzjjlb_compilation_date";

	/**
	 * 科长意见
	 */
	final String CHIEF_OPINION = "text_dzhtzjjlb_chief_opinion";
	/**
	 * 科长
	 */
	final String CHIEF = "mtext_dzhtzjjlb_chief";
	/**
	 * 科长意见系统时间
	 */
	final String CHIEF_OPINION_DATE = "text_dzhtzjjlb_chief_opinion_date";

	public void setDataProps(List<FormValue> valueList){
		this.setProps(map, QUALITY_INSPECTION_FORM_ID, valueList, "dzhtzjjlb");
	}
	public void setClqkDataProps(List<FormValue> valueList){
		this.setProps(map, QUALITY_CLQK_FORM_ID, valueList, "dzhtzjjlb");
	}
	
	public void setQUALITY_PERSON(String quality_person)
	{
		this.map.put(this.QUALITY_PERSON, quality_person);
	}
	
	public void setQUALITY_DATE(String quality_date)
	{
		this.map.put(this.QUALITY_DATE, quality_date);
	}
	
	public void setNOTICE_TO_CORRECTION(String notice_to_correction)
	{
		this.map.put(this.NOTICE_TO_CORRECTION, notice_to_correction);
	}

	public void setCOMPILATION_PERSON(String compilation_person)
	{
		this.map.put(this.COMPILATION_PERSON, compilation_person);
	}

	public void setCOMPILATION_DATE(String compilation_date)
	{
		this.map.put(this.COMPILATION_DATE, compilation_date);
	}

	public void setCHIEF_OPINION(String chief_opinion)
	{
		this.map.put(this.CHIEF_OPINION, chief_opinion);
	}
	
	public void setCHIEF(String chief)
	{
		this.map.put(this.CHIEF, chief);
	}

	public void setCHIEF_OPINION_DATE(String chief_opinion_date)
	{
		this.map.put(this.CHIEF_OPINION_DATE, chief_opinion_date);
	}
	
	public void setJL(String jl)
	{
		this.map.put(this.JL, jl);
	}
	
	public void setPFDJ(String pfdj)
	{
		this.map.put(this.PFDJ, pfdj);
	}
	public Map<String, Object> getQualityInspectionConclusionRecordMap()
	{
		return this.map;
	}
}
