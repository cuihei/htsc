package com.ht.service.constant.experiencebook.electron;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ht.persistence.model.complication.formprop.FormValue;
import com.ht.service.constant.experiencebook.BaseExperience;

/**
 * 电子海图编绘经历簿导出 单元区域索引图 常量类
 * @author yp
 * 2016/12/7
 */
public class UnitAreaIndex extends BaseExperience
{
	Map<String,Object> map = new HashMap<String, Object>();
	/**
	 * 表单id
	 */
	public final String UNIT_AREA_INDEX_FORM_ID = "11051529221760026";
	
	/**
	 * 书签
	 */
	public final String MARKER = "table_dyqusyt";
	
	/**
	 * 单元区域索引图
	 */
	public final String SYT = "img_dyqusyt_syt";
	
	public void setDataProps(List<FormValue> valueList){
		this.setProps(this.map, UNIT_AREA_INDEX_FORM_ID, valueList, "dyqusyt");
	}

	public void setSYT(String syt){
		this.map.put(this.SYT, syt);
	}
	
	public Map<String,Object> getMapIndexesMap(){
		return this.map;
	}
}
