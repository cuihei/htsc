package com.ht.service.constant.experiencebook.electronicchartsmallcorrection;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ht.persistence.model.complication.formprop.FormValue;
import com.ht.service.constant.experiencebook.BaseExperience;

/**
 * 电子海图小改正编绘记录表常量类
 * @author houchen 
 * 2016/12/20
 */
public class EleCompilationRecord extends BaseExperience
{

	Map<String, Object> map = new HashMap<String, Object>();
	/**
	 * 书签
	 */
	public final String MARKER = "table_dzhtxgzbhjlb";
	
	//表单id
	public final String COMPILATION_ID = "11230203408120296";
	
	// 标题
	final String DZHTXGZBHJLB_TITLE = "text_dzhtxgzbhjlb_title";
		
	// 编绘员
	final String DZHTXGZBHJLB_COMPILATION_PERSON = "mtext_dzhtxgzbhjlb_compilation_person";

	// 编绘时间
	final String DZHTXGZBHJLB_COMPILATION_DATE = "text_dzhtxgzbhjlb_compilation_date";

	// 质检员
	final String DZHTXGZBHJLB_QUALITY_PERSON = "mtext_dzhtxgzbhjlb_quality_person";

	// 质检时间
	final String DZHTXGZBHJLB_QUALITY_DATE = "text_dzhtxgzbhjlb_quality_date";

	public void setDataProps(List<FormValue> valueList)
	{
		this.setProps(map, COMPILATION_ID, valueList, "dzhtxgzbhjlb");
	}

	public void setDZHTXGZBHJLB_TITLE(String title)
	{
		this.map.put(this.DZHTXGZBHJLB_TITLE, title);
	}

	public void setDZHTXGZBHJLB_COMPILATION_PERSON(String person)
	{
		this.map.put(this.DZHTXGZBHJLB_COMPILATION_PERSON, person);
	}

	public void setDZHTXGZBHJLB_COMPILATION_DATE(String date)
	{
		this.map.put(this.DZHTXGZBHJLB_COMPILATION_DATE, date);
	}

	public void setDZHTXGZBHJLB_QUALITY_PERSON(String person)
	{
		this.map.put(this.DZHTXGZBHJLB_QUALITY_PERSON, person);
	}

	public void setDZHTXGZBHJLB_QUALITY_DATE(String date)
	{
		this.map.put(this.DZHTXGZBHJLB_QUALITY_DATE, date);
	}

	public Map<String, Object> getCompilationRecoredMap()
	{
		return this.map;
	}
}
