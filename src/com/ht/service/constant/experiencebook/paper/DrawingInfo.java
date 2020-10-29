package com.ht.service.constant.experiencebook.paper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ht.persistence.model.complication.formprop.FormValue;
import com.ht.service.constant.experiencebook.BaseExperience;

/**
 * 纸海图编绘经历簿导出制图基本信息表常量类
 * @author yp
 * 2016/12/7
 */
public class DrawingInfo extends BaseExperience
{
	Map<String,Object> map = new HashMap<String, Object>();
	/**
	 * 表单id
	 */
	public final String DRAWING_INFO_FORM_ID = "11051544503280072";
	
	/**
	 * 书签
	 */
	public final String MARKER = "table_zhtztjbxib";
	
	public void setDataProps(List<FormValue> valueList){
		this.setProps(this.map, DRAWING_INFO_FORM_ID, valueList, "zhtztjbxib");
	}

	public Map<String,Object> getDrawingInfoMap(){
		return this.map;
	}
}
