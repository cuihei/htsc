package com.ht.service.constant.experiencebook.paper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ht.persistence.model.complication.formprop.FormValue;
import com.ht.service.constant.experiencebook.BaseExperience;

/**
 * 纸海图编绘经历簿导出图面配置示意图、资料采用略图、图幅索引图、及磁偏差计算表常量类
 * @author yp
 * 2016/12/7
 */
public class MapConfigureDiagram extends BaseExperience
{
	Map<String,Object> map = new HashMap<String, Object>();
	/**
	 * 表单id
	 */
	public final String MAP_CONFIGURE_DIAGRAM_FORM_ID = "11051552334010094";
	
	public final String DIAGRAM_FORM_ID = "11051554099400098";
	/**
	 * 书签
	 */
	public final String MARKER = "table_tmpz";
	public final String IMAGEMARKER = "table_tmpz_image";
	
	public void setDataProps(List<FormValue> valueList){
		this.setProps(this.map, MAP_CONFIGURE_DIAGRAM_FORM_ID, valueList, "tmpz");
	}
	public void setDIAGRAMDataProps(List<FormValue> valueList){
		this.setProps(this.map, DIAGRAM_FORM_ID, valueList, "tmpz");
	}

	public Map<String,Object> getMapConfigureDiagramMap(){
		return this.map;
	}
}
