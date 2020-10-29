package com.ht.service.constant.experiencebook.sourcedata;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ht.persistence.model.complication.formprop.FormValue;
import com.ht.service.constant.experiencebook.BaseExperience;

/**
 * 源数据经历薄资料采用情况表
 * @author wangyouwei
 */
public class DatumUsage extends BaseExperience
{
	Map<String, Object> map = new HashMap<String, Object>();
	/**
	 * 书签
	 */
	public final String MARKER = "table_datum_usage";
	
	/**
	 * 表单ID
	 */
	public final String DATUM_USAGE_FORM_ID = "11031809162880000";
	
	/**
	 *  标题
	 */
	final String DATUM_USAGE_TITLE = "table_datum_usage_title";
		
	public void setDataProps(List<FormValue> valueList){
		this.setProps(this.map, DATUM_USAGE_FORM_ID, valueList, "datum_usage");
	}
	
	public void setDATUM_USAGE_TITLE(String title){
		this.map.put(this.DATUM_USAGE_TITLE, title);
	}
	
	public Map<String,Object> getDatumUsageMap(){
		return this.map;
	}
}
