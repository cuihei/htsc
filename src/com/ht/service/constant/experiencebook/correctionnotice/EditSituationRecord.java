package com.ht.service.constant.experiencebook.correctionnotice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ht.persistence.model.complication.formprop.FormValue;
import com.ht.service.constant.experiencebook.BaseExperience;

/**
 * 改正公告编辑经历簿 编辑情况记录表
 * @author huodesheng
 *
 */
public class EditSituationRecord extends BaseExperience {
	public Map<String, Object> map = new HashMap<String, Object>();
	/**
	 * 书签
	 */
	public final String MARKER = "table_bjqkjlb";
	
	/**
	 * 标题
	 */
	final String TITLE = "text_bjqkjlb_title";
	/**
	 * 表单ID
	 */
	public final String BJQKJLB_FORM_ID = "11051501533880022";
	/**
	 * 编辑
	 */
	final String EDITOR="mtext_bjqkjlb_editor";
	/**
	 * 编辑日期
	 */
	final String EDITOR_DATE="text_bjqkjlb_editor_date";
	
	public void setDataProps(List<FormValue> valueList){
		this.setProps(this.map, BJQKJLB_FORM_ID, valueList, "bjqkjlb");
	}
	public void setEDITOR(String editor){
		this.map.put(this.EDITOR,editor );
	}
	public void setEDITOR_DATE(String editor_date){
		this.map.put(this.EDITOR_DATE,editor_date );
	}
	public Map<String, Object> getMap() {
		return this.map;
	}
	public String getBJQKJLB_FORM_ID() {
		return BJQKJLB_FORM_ID;
	}
	public String getMARKER() {
		return MARKER;
	}
	
}
