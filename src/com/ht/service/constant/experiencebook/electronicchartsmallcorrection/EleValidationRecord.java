package com.ht.service.constant.experiencebook.electronicchartsmallcorrection;

import java.util.HashMap;
import java.util.Map;



/**
 * 电子海图小改正审定记录表常量类
 * @author houchen
 * 2016/12/20
 */
public class EleValidationRecord
{
	
	Map<String,Object> map = new HashMap<String, Object>();
	
	/**
	 * 书签
	 */
	public final String MARKER = "table_dzhtxgzsdjlb";
	
	// 标题
	final String DZHTXGZSDJLB_TITLE = "text_dzhtxgzsdjlb_title";
	
	// 审定员
	final String DZHTXGZSDJLB_VALIDATION_PERSON = "mtext_dzhtxgzsdjlb_validation_person";
	
	// 审定时间
	final String DZHTXGZSDJLB_VALIDATION_DATE = "text_dzhtxgzsdjlb_validation_date";
	
	// 科长意见
	final String DZHTXGZSDJLB_CHIEF_OPINION = "text_dzhtxgzsdjlb_chief_opinion";
	
	// 科长
	final String DZHTXGZSDJLB_CHIEF_PERSON = "mtext_dzhtxgzsdjlb_chief_person";
	
	// 科长意见时间
	final String DZHTXGZSDJLB_CHIEF_OPINION_DATE = "text_dzhtxgzsdjlb_chief_opinion_date";
	
	// 涉及图层
	final String DZHTXGZSDJLB_SJTC = "text_dzhtxgzsdjlb_layer_name";

	// 存在问题（缺陷项目+缺陷内容）
	final String DZHTXGZSDJLB_CZWT = "text_dzhtxgzsdjlb_discription";
	
	// 处理意见
	final String DZHTXGZSDJLB_CLYJ = "text_dzhtxgzsdjlb_opinion";
	
	// 缺陷类别
	final String DZHTXGZSDJLB_QXLB = "text_dzhtxgzsdjlb_defect_type";
	
	// 缺陷扣分
	final String DZHTXGZSDJLB_QXKF = "text_dzhtxgzsdjlb_score";
	
	// 处理结果
	final String DZHTXGZSDJLB_CLJG = "text_dzhtxgzsdjlb_cljg";
	
	// 调整系数
	final String DZHTXGZSDJLB_COEFFICIENT = "text_dzhtxgzsdjlb_coefficient";
	
	// 质量评分
	final String DZHTXGZSDJLB_GRADING = "text_dzhtxgzsdjlb_grading";
	// 质量评分
	final String DZHTXGZSDJLB_DEFTVALUE = "text_dzhtxgzsdjlb_deftvalue";
	
	public void setDZHTXGZSDJLB_TITLE(String title)
	{
		this.map.put(this.DZHTXGZSDJLB_TITLE, title);
	}
	
	public void setDZHTXGZSDJLB_VALIDATION_PERSON(String person)
	{
		this.map.put(this.DZHTXGZSDJLB_VALIDATION_PERSON, person);
	}
	
	public void setDZHTXGZSDJLB_VALIDATION_DATE(String date)
	{
		this.map.put(this.DZHTXGZSDJLB_VALIDATION_DATE, date);
	}
	
	public void setDZHTXGZSDJLB_CHIEF_OPINION(String opinion)
	{
		this.map.put(this.DZHTXGZSDJLB_CHIEF_OPINION, opinion);
	}
	
	public void setDZHTXGZSDJLB_CHIEF_PERSON(String person)
	{
		this.map.put(this.DZHTXGZSDJLB_CHIEF_PERSON, person);
	}
	
	public void setDZHTXGZSDJLB_CHIEF_OPINION_DATE(String date)
	{
		this.map.put(this.DZHTXGZSDJLB_CHIEF_OPINION_DATE, date);
	}
	
	public void setDZHTXGZSDJLB_SJTC(String layer)
	{
		this.map.put(this.DZHTXGZSDJLB_SJTC, layer);
	}

	public void setDZHTXGZSDJLB_CZWT(String problem)
	{
		this.map.put(this.DZHTXGZSDJLB_CZWT, problem);
	}

	public void setDZHTXGZSDJLB_CLYJ(String opinion)
	{
		this.map.put(this.DZHTXGZSDJLB_CLYJ, opinion);
	}

	public void setDZHTXGZSDJLB_QXLB(String deep)
	{
		this.map.put(this.DZHTXGZSDJLB_QXLB, deep);
	}

	public void setDZHTXGZSDJLB_QXKF(String qxkf)
	{
		this.map.put(this.DZHTXGZSDJLB_QXKF, qxkf);
	}

	public void setDZHTXGZSDJLB_CLJG(String cljg)
	{
		this.map.put(this.DZHTXGZSDJLB_CLJG, cljg);
	}

	public void setDZHTXGZSDJLB_COEFFICIENT(String coefficient)
	{
		this.map.put(this.DZHTXGZSDJLB_COEFFICIENT, coefficient);
	}

	public void setDZHTXGZSDJLB_GRADING(String grading)
	{
		this.map.put(this.DZHTXGZSDJLB_GRADING, grading);
	}

	public Map<String,Object> getValidationRecordMap(){
		return this.map;
	}

	public void setDZHTXGZSDJLB_DEFTVALUE(String deftvalue) {
		this.map.put(this.DZHTXGZSDJLB_DEFTVALUE, deftvalue);
	}
	
}
