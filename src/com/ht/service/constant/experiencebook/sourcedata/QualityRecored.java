package com.ht.service.constant.experiencebook.sourcedata;

import java.util.HashMap;
import java.util.Map;



/**
 * 源数据编绘经历簿导出源数据质检记录表常量类
 * @author penghao
 * 2016/12/7
 */
public class QualityRecored
{
	
	Map<String,Object> map = new HashMap<String, Object>();
	
	/**
	 * 书签
	 */
	public final String MARKER = "table_zjjlb";
	
	// 标题
	final String ZJJLB_TITLE = "text_zjjlb_title";
	
	// 通告改正至
	final String ZJJLB_NOTICE = "text_zjjlb_notice";
	
	// 编绘员
	final String ZJJLB_COMPILATION_PERSON = "mtext_zjjlb_compilation_person";
	
	// 编绘时间
	final String ZJJLB_COMPILATION_DATE = "text_zjjlb_compilation_date";
	
	// 质检员
	final String ZJJLB_QUALITY_PERSON = "mtext_zjjlb_quality_person";
	
	// 质检时间
	final String ZJJLB_QUALITY_DATE = "text_zjjlb_quality_date";
	
	// 涉及图层
	final String ZJJLB_LAYER = "text_zjjlb_layer";

	// 存在问题（缺陷项目+缺陷内容）
	final String ZJJLB_PROBLEM = "text_zjjlb_problem";
	
	// 处理意见
	final String ZJJLB_OPINION = "text_zjjlb_opinion";
	
	// 缺陷类别
	final String ZJJLB_DEEP = "text_zjjlb_deep";
	
	// 缺陷个数
	final String ZJJLB_NUM = "text_zjjlb_num";
	
	// 缺陷扣分
	final String ZJJLB_SCORE = "text_zjjlb_score";
	
	// 备注
	final String ZJJLB_REMARKS = "text_zjjlb_remarks";
	
	// 缺陷累计扣分
	final String ZJJLB_TOTAL = "text_zjjlb_total";
	
	// 调整系数
	final String ZJJLB_COEFFICIENT = "text_zjjlb_coefficient";
	
	// 实际扣分
	final String ZJJLB_ACTUAL = "text_zjjlb_actual";
	
	// 质量评分
	final String ZJJLB_GRADING = "text_zjjlb_grading";
	
	public void setZJJLB_TITLE(String title)
	{
		this.map.put(this.ZJJLB_TITLE, title);
	}
	
	public void setZJJLB_NOTICE(String notice)
	{
		this.map.put(this.ZJJLB_NOTICE, notice);
	}
	
	public void setZJJLB_COMPILATION_PERSON(String person)
	{
		this.map.put(this.ZJJLB_COMPILATION_PERSON, person);
	}
	
	public void setZJJLB_COMPILATION_DATE(String date)
	{
		this.map.put(this.ZJJLB_COMPILATION_DATE, date);
	}
	
	public void setZJJLB_QUALITY_PERSON(String person)
	{
		this.map.put(this.ZJJLB_QUALITY_PERSON, person);
	}
	
	public void setZJJLB_QUALITY_DATE(String date)
	{
		this.map.put(this.ZJJLB_QUALITY_DATE, date);
	}
	
	public void setZJJLB_LAYER(String layer)
	{
		this.map.put(this.ZJJLB_LAYER, layer);
	}

	public void setZJJLB_PROBLEM(String problem)
	{
		this.map.put(this.ZJJLB_PROBLEM, problem);
	}

	public void setZJJLB_OPINION(String opinion)
	{
		this.map.put(this.ZJJLB_OPINION, opinion);
	}

	public void setZJJLB_DEEP(String deep)
	{
		this.map.put(this.ZJJLB_DEEP, deep);
	}

	public void setZJJLB_NUM(String num)
	{
		this.map.put(this.ZJJLB_NUM, num);
	}

	public void setZJJLB_SCORE(String score)
	{
		this.map.put(this.ZJJLB_SCORE, score);
	}

	public void setZJJLB_REMARKS(String remarks)
	{
		this.map.put(this.ZJJLB_REMARKS, remarks);
	}

	public void setZJJLB_TOTAL(String total)
	{
		this.map.put(this.ZJJLB_TOTAL, total);
	}

	public void setZJJLB_COEFFICIENT(String coefficient)
	{
		this.map.put(this.ZJJLB_COEFFICIENT, coefficient);
	}

	public void setZJJLB_ACTUAL(String actual)
	{
		this.map.put(this.ZJJLB_ACTUAL, actual);
	}

	public void setZJJLB_GRADING(String grading)
	{
		this.map.put(this.ZJJLB_GRADING, grading);
	}

	public Map<String,Object> getQualityRecoredMap(){
		return this.map;
	}
}
