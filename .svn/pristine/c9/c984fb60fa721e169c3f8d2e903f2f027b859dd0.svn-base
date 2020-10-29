package com.ht.service.constant.experiencebook.electron;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ht.persistence.model.complication.formprop.FormValue;
import com.ht.service.constant.experiencebook.BaseExperience;

/**
 * 电子海图编绘经历簿导出电子海图编绘问题处理记录表常量类
 * @author yp 2016/12/7
 */
public class EleProblemRecored extends BaseExperience
{

	Map<String, Object> map = new HashMap<String, Object>();
	/**
	 * 书签
	 */
	public final String MARKER = "table_dzhtbhwtcljlb";

	public final String PROBLEM_RECORED_FORM_ID = "11051530097200028";
	
	// 编绘疑难问题
	final String YNWT = "text_dzhtbhwtcljlb_ynwt";

	// 处理意见
	final String CLYJ = "text_dzhtbhwtcljlb_clyj";
	
	// 通告改正至
	final String NOTICE_TO_CORRECTION = "text_dzhtbhwtcljlb_notice_to_correction";
	
	// 编绘员
	final String COMPILATION_PERSON = "mtext_dzhtbhwtcljlb_compilation_person";

	// 编绘时间
	final String COMPILATION_DATE = "text_dzhtbhwtcljlb_compilation_date";

	// 质检员
	final String QUALITY_PERSON = "mtext_dzhtbhwtcljlb_quality_person";

	// 质检时间
	final String QUALITY_DATE = "text_dzhtbhwtcljlb_quality_date";

	public void setDataProps(List<FormValue> valueList)
	{
		this.setProps(map, PROBLEM_RECORED_FORM_ID, valueList, "dzhtbhwtcljlb");
	}

	public void setYNWT(String ynwt)
	{
		this.map.put(this.YNWT, ynwt);
	}

	public void setCLYJ(String clyj)
	{
		this.map.put(this.CLYJ, clyj);
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

	public void setQUALITY_PERSON(String quality_person)
	{
		this.map.put(this.QUALITY_PERSON, quality_person);
	}

	public void setQUALITY_DATE(String quality_date)
	{
		this.map.put(this.QUALITY_DATE, quality_date);
	}

	public Map<String, Object> getProblemRecoredMap()
	{
		return this.map;
	}
}
