package com.ht.service.constant.experiencebook.paper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ht.persistence.model.complication.formprop.FormValue;
import com.ht.service.constant.experiencebook.BaseExperience;

/**
 * 纸海图质检结论记录表类
 * @author yp
 * @date 2016/12/7
 */
public class PaperQualityInspectionConclusionRecord extends BaseExperience
{

	Map<String, Object> map = new HashMap<String, Object>();

	public final String QUALITY_INSPECTION_FORM_ID = "11051558547330113";
	
	public final String QUALITY_CLQK_FORM_ID = "0109122009740531";
	
	/**
	 * 书签
	 */
	public final String MARKER = "table_zhtzjjlb";
	
	/**
	 * 纸海图质检结论
	 */
	final String JL = "text_zhtzjjlb_jl";

	/**
	 * 质量评分及等级
	 */
	final String PFDJ = "text_zhtzjjlb_pfdj";
	
	/**
	 * 质检
	 */
	final String QUALITY_PERSON = "mtext_zhtzjjlb_quality_person";
	/**
	 * 质检时间
	 */
	final String QUALITY_DATE = "text_zhtzjjlb_quality_date";
	/**
	 * 通告改正至
	 */
	final String NOTICE_TO_CORRECTION = "text_zhtzjjlb_notice_to_correction";
	/**
	 * 编绘
	 */
	final String COMPILATION_PERSON = "mtext_zhtzjjlb_compilation_person";
	/**
	 * 编绘系统时间
	 */
	final String COMPILATION_DATE = "text_zhtzjjlb_compilation_date";

	/**
	 * 科长意见
	 */
	final String CHIEF_OPINION = "text_zhtzjjlb_chief_opinion";
	/**
	 * 科长
	 */
	final String CHIEF = "mtext_zhtzjjlb_chief";
	/**
	 * 科长意见系统时间
	 */
	final String CHIEF_OPINION_DATE = "text_zhtzjjlb_chief_opinion_date";

	public void setDataProps(List<FormValue> valueList){
		this.setProps(map, QUALITY_INSPECTION_FORM_ID, valueList, "zhtzjjlb");
	}
	public void setClqkDataProps(List<FormValue> valueList){
		this.setProps(map, QUALITY_CLQK_FORM_ID, valueList, "zhtzjjlb");
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

	public void setCHIEF_OPINION_DATE(String chief_opinion_date)
	{
		this.map.put(this.CHIEF_OPINION_DATE, chief_opinion_date);
	}

	public void setJL(String jl)
	{
		this.map.put(this.JL, jl);
	}
	
	public void setCHIEF(String chief)
	{
		this.map.put(this.CHIEF, chief);
	}
	public void setPFDJ(String pfdj)
	{
		this.map.put(this.PFDJ, pfdj);
	}
	
	public Map<String, Object> getPaperQualityInspectionConclusionRecordMap()
	{
		return this.map;
	}
}
