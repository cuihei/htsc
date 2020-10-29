package com.ht.service.constant.experiencebook.sourcedatacorrection;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ht.persistence.model.complication.formprop.FormValue;
import com.ht.service.constant.experiencebook.BaseExperience;
/**
 * 源数据小改正经历簿  源数据小改正资料登记表
 * @author huodesheng
 *
 */
public class DataRegister extends BaseExperience {
	Map<String, Object> map = new HashMap<String, Object>();
	/**
	 * 书签
	 */
	public final String MARKER = "table_ygzzldjb";
	
	/**
	 * 标题
	 */
	final String TITLE = "text_ygzzldjb_title";
	/**
	 * 表单ID
	 */
	public final String YGZZLDJB_FORM_ID = "11031931352070000";
	
	
	public void setDataProps(List<FormValue> valueList){
		this.setProps(this.map, YGZZLDJB_FORM_ID, valueList, "ygzzldjb");
	}
	
	public void setTITLE(String title){
		this.map.put(this.TITLE,title);
	}
	
	public String getYGZZLDJB_FORM_ID() {
		return YGZZLDJB_FORM_ID;
	}

	public Map<String, Object> getMap() {
		return this.map;
	}

	public String getMARKER() {
		return MARKER;
	}
	
}
