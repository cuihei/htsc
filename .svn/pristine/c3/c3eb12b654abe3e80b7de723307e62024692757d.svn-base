package com.ht.service.constant.experiencebook.paper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ht.persistence.model.complication.formprop.FormValue;
import com.ht.service.constant.experiencebook.BaseExperience;

/**
 * 纸海图经历薄资料采用情况表
 * @author yp
 */
public class PaperDatumUsage extends BaseExperience
{
	Map<String, Object> map = new HashMap<String, Object>();
	/**
	 * 书签
	 */
	public final String MARKER = "table_zlcydjb";
	
	/**
	 * 表单ID
	 */
	public final String DATUM_USAGE_FORM_ID = "11051543039820064";
	
	/**
	 *  标题
	 */
	final String TITLE = "text_zlcydjb_title";
		
	public void setDataProps(List<FormValue> valueList){
		this.setProps(this.map, DATUM_USAGE_FORM_ID, valueList, "zlcydjb");
	}
	
	public void setTITLE(String title){
		this.map.put(this.TITLE, title);
	}
	
	public Map<String,Object> getDatumUsageMap(){
		return this.map;
	}
}
