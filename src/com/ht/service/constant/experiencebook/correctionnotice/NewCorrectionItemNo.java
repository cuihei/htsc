package com.ht.service.constant.experiencebook.correctionnotice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ht.persistence.model.complication.formprop.FormValue;
import com.ht.service.constant.experiencebook.BaseExperience;
/**
 * 改正公告编辑经历簿 最新改正项号记录表
 * @author huodesheng
 *
 */
public class NewCorrectionItemNo extends BaseExperience{
	Map<String, Object> map = new HashMap<String, Object>();
	/**
	 * 书签
	 */
	public final String MARKER = "table_zxgzxhjlb";
	
	/**
	 * 标题
	 */
	final String TITLE = "text_zxgzxhjlb_title";
	/**
	 * 表单ID
	 */
	public final String ZXGZXHJLB_FORM_ID = "11302146064890732";
	/**
	 * 编辑
	 */
	final String EDITOR="mtext_zxgzxhjlb_editor";
	/**
	 * 编辑日期
	 */
	final String EDITOR_DATE="text_zxgzxhjlb_editor_date";
	/**
	 * 质检
	 */
	final String QUALITY="mtext_zxgzxhjlb_quality";
	/**
	 * 质检日期
	 */
	final String QUALITY_DATE="text_zxgzxhjlb_quality_date";
	public void setDataProps(List<FormValue> valueList){
		this.setProps(this.map, ZXGZXHJLB_FORM_ID, valueList, "zxgzxhjlb");
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
	public Map<String, Object> getMap() {
		return this.map;
	}

	public String getZXGZXHJLB_FORM_ID() {
		return ZXGZXHJLB_FORM_ID;
	}

	public String getMARKER() {
		return MARKER;
	}

	
}
