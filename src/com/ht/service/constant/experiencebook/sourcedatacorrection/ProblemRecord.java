package com.ht.service.constant.experiencebook.sourcedatacorrection;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ht.persistence.model.complication.formprop.FormValue;
import com.ht.service.constant.experiencebook.BaseExperience;
/**
 * 源数据小改正经历簿  源数据小改正问题处理记录表
 * @author huodesheng
 *
 */
public class ProblemRecord extends BaseExperience  {
	Map<String, Object> map = new HashMap<String, Object>();
	/**
	 * 书签
	 */
	public final String MARKER = "table_yxgzwtclb";
	
	/**
	 * 标题
	 */
	final String TITLE = "text_yxgzwtclb_title";
	/**
	 * 表单ID
	 */
	public final String YXGZWTCLB_FORM_ID = "11031937246480014";
	/**
	 * 编绘
	 */
	final String COMPILATION_PERSON="mtext_yxgzwtclb_compilation_person";
	/**
	 * 编绘时间
	 */
	final String COMPILATION_DATE="text_yxgzwtclb_compilation_date";
	/**
	 * 质检
	 */
	final String QUALITY_PERSON="mtext_yxgzwtclb_quality_person";
	/**
	 * 质检时间
	 */
	final String QUALITY_DATE="text_yxgzwtclb_quality_date";
	
	
	public void setDataProps(List<FormValue> valueList){
		this.setProps(this.map, YXGZWTCLB_FORM_ID, valueList, "yxgzwtclb");
	}
	
	public void setTITLE(String title){
		this.map.put(this.TITLE,title);
	}
	
	public void setCOMPILATION_PERSON(String compilation_person){
		this.map.put(this.COMPILATION_PERSON,compilation_person);
	}
	public void setCOMPILATION_DATE(String compilation_date){
		this.map.put(this.COMPILATION_DATE,compilation_date);
	}
	public void setQUALITY_PERSON(String quality_person){
		this.map.put(this.QUALITY_PERSON,quality_person);
	}
	public void setQUALITY_DATE(String quality_date){
		this.map.put(this.QUALITY_DATE,quality_date);
	}
	
	public String getYXGZWTCLB_FORM_ID() {
		return YXGZWTCLB_FORM_ID;
	}

	public Map<String, Object> getMap() {
		return this.map;
	}

	public String getMARKER() {
		return MARKER;
	}
	
}
