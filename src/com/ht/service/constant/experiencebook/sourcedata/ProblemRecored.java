package com.ht.service.constant.experiencebook.sourcedata;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ht.persistence.model.complication.formprop.FormValue;
import com.ht.service.constant.experiencebook.BaseExperience;

/**
 * 源数据编绘经历簿导出源数据编绘问题处理记录表常量类
 * @author penghao 2016/12/7
 */
public class ProblemRecored extends BaseExperience
{

	Map<String, Object> map = new HashMap<String, Object>();
	/**
	 * 书签
	 */
	public final String MARKER = "table_wtcljlb";

	public final String PROBLEM_ID = "11031838218790020";
	
	// 标题
	final String WTCLJLB_TITLE = "text_wtcljlb_title";

	// 通告改正至
	final String WTCLJLB_NOTICE = "text_wtcljlb_notice";

	// 编绘员
	final String WTCLJLB_COMPILATION_PERSON = "mtext_wtcljlb_compilation_person";

	// 编绘时间
	final String WTCLJLB_COMPILATION_DATE = "text_wtcljlb_compilation_date";

	// 质检员
	final String WTCLJLB_QUALITY_PERSON = "mtext_wtcljlb_quality_person";

	// 质检时间
	final String WTCLJLB_QUALITY_DATE = "text_wtcljlb_quality_date";

	public void setDataProps(List<FormValue> valueList)
	{
		this.setProps(map, PROBLEM_ID, valueList, "wtcljlb");
	}

	public void setWTCLJLB_TITLE(String title)
	{
		this.map.put(this.WTCLJLB_TITLE, title);
	}

	public void setWTCLJLB_COMPILATION_PERSON(String person)
	{
		this.map.put(this.WTCLJLB_COMPILATION_PERSON, person);
	}

	public void setWTCLJLB_COMPILATION_DATE(String date)
	{
		this.map.put(this.WTCLJLB_COMPILATION_DATE, date);
	}

	public void setWTCLJLB_QUALITY_PERSON(String person)
	{
		this.map.put(this.WTCLJLB_QUALITY_PERSON, person);
	}

	public void setWTCLJLB_QUALITY_DATE(String date)
	{
		this.map.put(this.WTCLJLB_QUALITY_DATE, date);
	}

	public void setWTCLJLB_NOTICE(String notice)
	{
		this.map.put(this.WTCLJLB_NOTICE, notice);
	}

	public Map<String, Object> getProblemRecoredMap()
	{
		return this.map;
	}
}
