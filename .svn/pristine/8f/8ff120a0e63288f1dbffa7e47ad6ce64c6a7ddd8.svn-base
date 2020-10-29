package com.ht.service.constant.experiencebook.paperchartsmallcorrection;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ht.persistence.model.complication.formprop.FormValue;
import com.ht.service.constant.experiencebook.BaseExperience;

/**
 * 纸海图小改正编绘记录表常量类
 * @author houchen 2016/12/19
 */
public class CompilationRecord extends BaseExperience
{

	Map<String, Object> map = new HashMap<String, Object>();
	/**
	 * 书签
	 */
	public final String MARKER = "table_zhtxgzbhjlb";
	
	//表单id
	public final String COMPILATION_ID = "11051659299890019";
	
	// 标题
	final String ZHTXGZBHJLB_TITLE = "text_zhtxgzbhjlb_title";
		
	// 编绘员
	final String ZHTXGZBHJLB_COMPILATION_PERSON = "mtext_zhtxgzbhjlb_compilation_person";

	// 编绘时间
	final String ZHTXGZBHJLB_COMPILATION_DATE = "text_zhtxgzbhjlb_compilation_date";

	// 质检员
	final String ZHTXGZBHJLB_QUALITY_PERSON = "mtext_zhtxgzbhjlb_quality_person";

	// 质检时间
	final String ZHTXGZBHJLB_QUALITY_DATE = "text_zhtxgzbhjlb_quality_date";

	public void setDataProps(List<FormValue> valueList)
	{
		this.setProps(map, COMPILATION_ID, valueList, "zhtxgzbhjlb");
	}

	public void setZHTXGZBHJLB_TITLE(String title)
	{
		this.map.put(this.ZHTXGZBHJLB_TITLE, title);
	}

	public void setZHTXGZBHJLB_COMPILATION_PERSON(String person)
	{
		this.map.put(this.ZHTXGZBHJLB_COMPILATION_PERSON, person);
	}

	public void setZHTXGZBHJLB_COMPILATION_DATE(String date)
	{
		this.map.put(this.ZHTXGZBHJLB_COMPILATION_DATE, date);
	}

	public void setZHTXGZBHJLB_QUALITY_PERSON(String person)
	{
		this.map.put(this.ZHTXGZBHJLB_QUALITY_PERSON, person);
	}

	public void setZHTXGZBHJLB_QUALITY_DATE(String date)
	{
		this.map.put(this.ZHTXGZBHJLB_QUALITY_DATE, date);
	}

	public Map<String, Object> getCompilationRecoredMap()
	{
		return this.map;
	}
}
