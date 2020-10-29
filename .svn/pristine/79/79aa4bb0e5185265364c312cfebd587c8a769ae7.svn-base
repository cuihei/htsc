package com.ht.service.constant.experiencebook.sourcedata;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ht.persistence.model.complication.formprop.FormValue;
import com.ht.service.constant.experiencebook.BaseExperience;

/**
 * 源数据编绘经历薄编绘作业计划
 * @author wangyouwei
 */
public class WorkPlan extends BaseExperience
{
	Map<String, Object> map = new HashMap<String, Object>();
	
	/**
	 * 书签
	 */
	public final String MARKER = "table_work_plan";
	
	public final String WORK_PLAN_FORM_ID = "11031831406120018";
	
	/**
	 * 标题
	 */
	final String WORK_PLAN_TITLE = "text_work_plan_title";
	
	/**
	 * 内容
	 */
	final String WORK_PLAN_CONTENT = "text_work_plan_ysjzyjh";
	
	/**
	 * 工程名称
	 */
	final String WORK_PLAN_GCMC = "text_work_plan_gcmc";
	
	public void setWORK_PLAN_TITLE(String title){
		this.map.put(this.WORK_PLAN_TITLE, title);
	}
	
	public void setWORK_PLAN_CONTENT(String content){
		this.map.put(this.WORK_PLAN_CONTENT, content);
	}
	
	public void setWORK_PLAN_GCMC(String gcmc){
		this.map.put(this.WORK_PLAN_GCMC, gcmc);
	}
	
	
	public void setDataProps(List<FormValue> valueList){
		this.setProps(this.map, WORK_PLAN_FORM_ID, valueList, "work_plan");
	}
	
	public Map<String,Object> getWorkPlanMap(){
		return this.map;
	}
}
