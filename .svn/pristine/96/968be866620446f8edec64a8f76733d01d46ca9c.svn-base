package com.ht.service.constant.experiencebook.correctionnotice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ht.persistence.model.complication.formprop.FormValue;
import com.ht.service.constant.experiencebook.BaseExperience;
/**
 * 改正公告编辑经历簿  编辑资料登记表
 * @author huodesheng
 *
 */
public class EditDataRegister extends BaseExperience {
	Map<String, Object> map = new HashMap<String, Object>();
	/**
	 * 书签
	 */
	public final String MARKER = "table_bjzldjb";
	
	/**
	 * 标题
	 */
	final String TITLE = "text_bjzldjb_title";
	/**
	 * 表单ID
	 */
	public final String BJZLDJB_FORM_ID = "11051458281760015";
	
	/**
	 * 编辑
	 */
	final String EDITOR="mtext_bjzldjb_editor";
	/**
	 * 编辑日期
	 */
	final String EDITOR_DATE="text_bjzldjb_editor_date";
	/**
	 * 质检
	 */
	final String QUALITY="mtext_bjzldjb_quality";
	/**
	 * 质检日期
	 */
	final String QUALITY_DATE="text_bjzldjb_quality_date";
	
	public void setDataProps(List<FormValue> valueList){
		this.setProps(this.map, BJZLDJB_FORM_ID, valueList, "bjzldjb");
	}
	
	public void setEDITOR(String editor){
		this.map.put(this.EDITOR,editor );
	}
	public void setEDITOR_DATE(String editor_date){
		this.map.put(this.EDITOR_DATE,editor_date );
	}
	public void setQUALITY(String quality){
		this.map.put(this.QUALITY,quality);
	}
	public void setQUALITY_DATE(String quality_date){
		this.map.put(this.QUALITY_DATE,quality_date );
	}
	public void setTITLE(String title){
		this.map.put(this.TITLE,title);
	}
	
	public String getBJZLDJB_FORM_ID() {
		return BJZLDJB_FORM_ID;
	}

	public Map<String, Object> getMap() {
		return this.map;
	}

	public String getMARKER() {
		return MARKER;
	}
	
}
