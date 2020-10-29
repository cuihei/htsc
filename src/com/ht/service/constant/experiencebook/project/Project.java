package com.ht.service.constant.experiencebook.project;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ht.persistence.model.complication.formprop.FormValue;
import com.ht.service.constant.experiencebook.BaseExperience;

/**
 * 工程专题图制图经历表说明常量类
 * @author penghao 2016/12/15
 */
public class Project extends BaseExperience
{

	Map<String, Object> map = new HashMap<String, Object>();
	/**
	 * 书签工程专题图制图经历薄
	 */
	public final String MARKER = "table_gczttztjlb";
	
	/**
	 * 书签工程专题图制图经历薄
	 */
	public final String BHJL_MARKER = "table_gczttztjlb";
	
	// 纸海图编绘记录表
	public final String COMPLICATION_PAPER_FORM_ID = "12201507444330280";
	
	// 纸海图编绘记录表
	public final String COMPLICATION_ELECTRONIC_FORM_ID = "12201540356800330";
	
	// 质检&审定记录表
	public final String QT_VALIDATION_FORM_ID = "1";
	
	// 标题
	final String GCZTTZTJLB_TITLE = "text_gczttztjlb_title";

	// 海图类型
	final String GCZTTZTJLB_CHART_TYPE = "text_gczttztjlb_chart_type";

	// 海图用途
	final String GCZTTZTJLB_USE = "text_gczttztjlb_use";

	// 项目名称
	final String GCZTTZTJLB_PROJECT_NAME = "text_gczttztjlb_project_name";

	// 任务书编号
	final String GCZTTZTJLB_TASKBOOK_NO = "text_gczttztjlb_taskbook_no";

	// 图名
	final String GCZTTZTJLB_MAP_NAME = "text_gczttztjlb_map_name";
	
	// 版次
	final String GCZTTZTJLB_REVISION = "text_gczttztjlb_revision";
	
	// 比例尺类型
	final String GCZTTZTJLB_SCALE_TYPE = "text_gczttztjlb_scale_type";
	
	// 比例尺
	final String GCZTTZTJLB_SCALE = "text_gczttztjlb_scale";
	
	// 是否需要经过印制中心质量审核
	final String GCZTTZTJLB_NEED_TO_PRINT_CENTER_QUALITY_AUDIT = "text_yes_or_no";
	
	// 测量日期
	final String GCZTTZTJLB_MEASURE_DATE= "text_gczttztjlb_measure_date";
	
	// 编绘计划和疑难问题
	final String GCZTTZTJLB_PLAN_PROBLEM = "text_gczttztjlb_plan_problem";

	// 编绘员
	final String GCZTTZTJLB_COMPILATION_PERSON = "mtext_gczttztjlb_compilation_person";
	
	// 编绘时间
	final String GCZTTZTJLB_COMPILATION_DATE = "text_gczttztjlb_compilation_date";

	// 质检存在问题（缺陷项目+缺陷内容）和处理结果
	final String GCZTTZTJLB_QUALITY_PROBLEM = "text_gczttztjlb_quality_problem_result";
	
	// 质检员
	final String GCZTTZTJLB_QUALITY_PERSON = "mtext_gczttztjlb_quality_person";
	
	// 质检时间
	final String GCZTTZTJLB_QUALITY_DATE = "text_gczttztjlb_quality_date";
	
	// 制图事业部结论
	final String GCZTTZTJLB_BD_CONCLUSION = "text_gczttztjlb_bd_conclusion";
	
	// 制图事业部主任
	final String GCZTTZTJLB_BD_DIRECTOR= "mtext_gczttztjlb_bd_director";
	
	// 制图事业部主任日期
	final String GCZTTZTJLB_BD_DIRECTOR_DATE= "text_gczttztjlb_bd_director_date";
	
	// 审定存在问题（缺陷项目+缺陷内容）
	final String GCZTTZTJLB_VALIDATION_PROBLEM = "text_gczttztjlb_validation_problem_result";
	
	// 审定员
	final String GCZTTZTJLB_VALIDATION_PERSON = "mtext_gczttztjlb_validation_person";
	
	// 审定日期
	final String GCZTTZTJLB_VALIDATION_DATE = "text_gczttztjlb_validation_date";
	
	// 质量管理部门结论
	final String GCZTTZTJLB_QD_CONCLUSION = "text_gczttztjlb_qd_conclusion";
	
	// 质量管理部门科长
	final String GCZTTZTJLB_QD_CHIEF= "mtext_gczttztjlb_qd_chief";
	
	// 质量管理部门科长日期
	final String GCZTTZTJLB_QD_CHIEF_DATE= "text_gczttztjlb_qd_chief_date";
	
	// 附件说明
	final String GCZTTZTJLB_ATTACHMENT_ILLUSTRATE= "text_gczttztjlb_attachment_illustrate";
	
	public void setWTCLJLB_TITLE(String title)
	{
		this.map.put(this.GCZTTZTJLB_TITLE, title);
	}

	public void setGCZTTZTJLB_CHART_TYPE(String type)
	{
		this.map.put(this.GCZTTZTJLB_CHART_TYPE, type);
	}

	public void setGCZTTZTJLB_USE(String use)
	{
		this.map.put(this.GCZTTZTJLB_USE, use);
	}

	public void setGCZTTZTJLB_PROJECT_NAME(String projectName)
	{
		this.map.put(this.GCZTTZTJLB_PROJECT_NAME, projectName);
	}

	public void setGCZTTZTJLB_TASKBOOK_NO(String no)
	{
		this.map.put(this.GCZTTZTJLB_TASKBOOK_NO, no);
	}

	public void setGCZTTZTJLB_MAP_NAME(String mapName)
	{
		this.map.put(this.GCZTTZTJLB_MAP_NAME, mapName);
	}
	
	public void setGCZTTZTJLB_REVISION(String revision)
	{
		this.map.put(this.GCZTTZTJLB_REVISION, revision);
	}
	
	public void setGCZTTZTJLB_SCALE_TYPE(String scaleType)
	{
		this.map.put(this.GCZTTZTJLB_SCALE_TYPE, scaleType);
	}
	
	public void setGCZTTZTJLB_SCALE(String scale)
	{
		this.map.put(this.GCZTTZTJLB_SCALE, scale);
	}
	
	public void setGCZTTZTJLB_NEED_TO_PRINT_CENTER_QUALITY_AUDIT(String needAudit)
	{
		this.map.put(this.GCZTTZTJLB_NEED_TO_PRINT_CENTER_QUALITY_AUDIT, needAudit);
	}
	
	public void setGCZTTZTJLB_MEASURE_DATE(String date)
	{
		this.map.put(this.GCZTTZTJLB_MEASURE_DATE, date);
	}
	
	// 编绘记录表
	public void setComplicationDataProps(List<FormValue> valueList,String formId)
	{
		this.setProps(map, formId, valueList, "gczttztjlb");
	}
	
	// 编绘记录表
	public void setQTAndValidationDataProps(List<FormValue> valueList)
	{
		this.setProps(map, QT_VALIDATION_FORM_ID, valueList, "gczttztjlb");
	}
	
	public void setGCZTTZTJLB_COMPLICATION_PLAN(String plan)
	{
		this.map.put(this.GCZTTZTJLB_MAP_NAME, plan);
	}
	
	public void setGCZTTZTJLB_PLAN_PROBLEM(String problem)
	{
		this.map.put(this.GCZTTZTJLB_PLAN_PROBLEM, problem);
	}
	
	public void setGCZTTZTJLB_COMPILATION_PERSON(String person)
	{
		this.map.put(this.GCZTTZTJLB_COMPILATION_PERSON, person);
	}
	
	public void setGCZTTZTJLB_COMPILATION_DATE(String date)
	{
		this.map.put(this.GCZTTZTJLB_COMPILATION_DATE, date);
	}
	
	public void setGCZTTZTJLB_QUALITY_PROBLEM(String problem)
	{
		this.map.put(this.GCZTTZTJLB_QUALITY_PROBLEM, problem);
	}
	
	public void setGCZTTZTJLB_QUALITY_PERSON(String person)
	{
		this.map.put(this.GCZTTZTJLB_QUALITY_PERSON, person);
	}
	
	public void setGCZTTZTJLB_QUALITY_DATE(String date)
	{
		this.map.put(this.GCZTTZTJLB_QUALITY_DATE, date);
	}
	
	public void setGCZTTZTJLB_BD_CONCLUSION(String conclusion)
	{
		this.map.put(this.GCZTTZTJLB_BD_CONCLUSION, conclusion);
	}
	
	public void setGCZTTZTJLB_BD_DIRECTOR(String director)
	{
		this.map.put(this.GCZTTZTJLB_BD_DIRECTOR, director);
	}
	
	public void setGCZTTZTJLB_BD_DIRECTOR_DATE(String date)
	{
		this.map.put(this.GCZTTZTJLB_BD_DIRECTOR_DATE, date);
	}
	
	public void setGCZTTZTJLB_VALIDATION_PROBLEM(String problem)
	{
		this.map.put(this.GCZTTZTJLB_VALIDATION_PROBLEM, problem);
	}
	
	public void setGCZTTZTJLB_VALIDATION_PERSON(String person)
	{
		this.map.put(this.GCZTTZTJLB_VALIDATION_PERSON, person);
	}
	
	public void setGCZTTZTJLB_VALIDATION_DATE(String date)
	{
		this.map.put(this.GCZTTZTJLB_VALIDATION_DATE, date);
	}
	
	public void setGCZTTZTJLB_QD_CONCLUSION(String conclusion)
	{
		this.map.put(this.GCZTTZTJLB_QD_CONCLUSION, conclusion);
	}
	
	public void setGCZTTZTJLB_QD_CHIEF(String chief)
	{
		this.map.put(this.GCZTTZTJLB_QD_CHIEF, chief);
	}
	
	public void setGCZTTZTJLB_QD_CHIEF_DATE(String date)
	{
		this.map.put(this.GCZTTZTJLB_QD_CHIEF_DATE, date);
	}
	
	public void setGCZTTZTJLB_ATTACHMENT_ILLUSTRATE(String illustrate)
	{
		this.map.put(this.GCZTTZTJLB_ATTACHMENT_ILLUSTRATE, illustrate);
	}

	public Map<String, Object> getProjectMap()
	{
		return this.map;
	}
}
