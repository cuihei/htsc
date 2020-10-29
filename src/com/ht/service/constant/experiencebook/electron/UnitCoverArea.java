package com.ht.service.constant.experiencebook.electron;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ht.persistence.model.complication.formprop.FormValue;
import com.ht.service.constant.experiencebook.BaseExperience;

/**
 * 电子海图编绘经历簿导出 单元覆盖范围 常量类
 * @author yp
 * 2016/12/7
 */
public class UnitCoverArea extends BaseExperience
{
	Map<String,Object> map = new HashMap<String, Object>();
	/**
	 * 表单id
	 */
	public final String UNIT_COVER_AREA_FORM_ID = "11230037081980574";
	
	/**
	 * 书签
	 */
	public final String MARKER = "table_dyfgfw";
	
	public void setDataProps(List<FormValue> valueList){
		this.setProps(this.map, UNIT_COVER_AREA_FORM_ID, valueList, "dyfgfw");
	}

	public Map<String,Object> getMapIndexesMap(){
		return this.map;
	}
}
