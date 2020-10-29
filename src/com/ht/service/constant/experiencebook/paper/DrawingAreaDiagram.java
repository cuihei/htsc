package com.ht.service.constant.experiencebook.paper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ht.persistence.model.complication.formprop.FormValue;
import com.ht.service.constant.experiencebook.BaseExperience;

/**
 * 纸海图编绘经历簿导出 制图区域示意图 常量类
 * @author yp
 * 2016/12/7
 */
public class DrawingAreaDiagram extends BaseExperience
{
	Map<String,Object> map = new HashMap<String, Object>();
	/**
	 * 表单id
	 */
	public final String DRAWING_AREA_DIAGRAM_FORM_ID = "11051556056170104";
	
	/**
	 * 书签
	 */
	public final String MARKER = "table_ztqusyt";
	
	public void setDataProps(List<FormValue> valueList){
		this.setProps(this.map, DRAWING_AREA_DIAGRAM_FORM_ID, valueList, "ztqusyt");
	}

	public Map<String,Object> getDrawingAreaDiagramMap(){
		return this.map;
	}
}
