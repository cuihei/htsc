package com.ht.service.constant.experiencebook.sourcedata;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ht.persistence.model.complication.formprop.FormValue;
import com.ht.service.constant.experiencebook.BaseExperience;

/**
 * 源数据质检结论记录表类
 * @author zqy
 * @date 2016/12/7
 */
public class QualityInspectionConclusionRecord extends BaseExperience
{

	Map<String, Object> map = new HashMap<String, Object>();

	public final String QUALITY_INSPECTION_FORM_ID = "01091219246290517";
	
	public final String QUALITY_PF_INSPECTION_FORM_ID = "11031848032190034";
	/**
	 * 书签
	 */
	public final String MARKER = "table_zjjljlb";

	/**
	 * 标题
	 */
	final String TITLE = "text_zjjljlb_title";

	/**
	 * 质量评分
	 */
	final String ZLJL = "text_zjjljlb_zjjl";
	
	/**
	 * 评分等级
	 */
	final String PFDJ = "text_zjjljlb_pfdj";
	
	/**
	 * 质检
	 */
	final String QUALITY = "mtext_zjjljlb_quality";
	/**
	 * 质检系统时间
	 */
	final String QUALITY_SYSTEM_TIME = "text_zjjljlb_quality_system_time";
	/**
	 * 科长意见
	 */
	final String SECTION_CHIEF = "text_zjjljlb_section_chief";
	/**
	 * 科长
	 */
	final String CHIEF = "mtext_zjjljlb_chief";
	/**
	 * 科长意见系统时间
	 */
	final String SECTION_CHIEF_SYSTEM_TIME = "text_zjjljlb_section_chief_system_time";
	/**
	 * 通告改正至
	 */
	final String NOTICE_TO_CORRECTION = "text_zjjljlb_notice_to_correction";
	/**
	 * 编绘
	 */
	final String COMPILATION = "mtext_zjjljlb_compilation";
	/**
	 * 编绘系统时间
	 */
	final String COMPILATION_SYSTEM_TIME = "text_zjjljlb_compilation_system_time";

	public void setDataProps(List<FormValue> valueList){
		this.setProps(map, QUALITY_INSPECTION_FORM_ID, valueList, "zjjljlb");
	}
	
	public void setPfDataProps(List<FormValue> valueList){
		this.setProps(map, QUALITY_PF_INSPECTION_FORM_ID, valueList, "zjjljlb");
	}
	
	public void setTITLE(String title)
	{
		this.map.put(this.TITLE, title);
	}
	
	public void setZLJL(String zljl)
	{
		this.map.put(this.ZLJL, zljl);
	}
	
	public void setPFDJ(String pfdj)
	{
		this.map.put(this.PFDJ, pfdj);
	}

	public void setQUALITY(String quality)
	{
		this.map.put(this.QUALITY, quality);
	}

	public void setQUALITY_SYSTEM_TIME(String quality_system_time)
	{
		this.map.put(this.QUALITY_SYSTEM_TIME, quality_system_time);
	}

	public void setSECTION_CHIEF(String section_chief)
	{
		this.map.put(this.SECTION_CHIEF, section_chief);
	}

	public void setSECTION_CHIEF_SYSTEM_TIME(String section_chief_system_time)
	{
		this.map.put(this.SECTION_CHIEF_SYSTEM_TIME, section_chief_system_time);
	}

	public void setNOTICE_TO_CORRECTION(String notice_to_correction)
	{
		this.map.put(this.NOTICE_TO_CORRECTION, notice_to_correction);
	}

	public void setCOMPILATION(String compilation)
	{
		this.map.put(this.COMPILATION, compilation);
	}
	public void setCHIEF(String chief)
	{
		this.map.put(this.CHIEF, chief);
	}

	public void setCOMPILATION_SYSTEM_TIME(String compilation_system_time)
	{
		this.map.put(this.COMPILATION_SYSTEM_TIME, compilation_system_time);
	}

	public Map<String, Object> getQualityInspectionConclusionRecordMap()
	{
		return this.map;
	}
}
