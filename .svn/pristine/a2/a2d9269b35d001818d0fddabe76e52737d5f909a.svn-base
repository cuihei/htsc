package com.ht.service.constant.experiencebook.paper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ht.persistence.model.complication.formprop.FormValue;
import com.ht.service.constant.experiencebook.BaseExperience;

/**
 * 纸海图编绘经历簿导出封皮常量类
 * @author yp
 * 2016/12/4
 */
public class PaperCover extends BaseExperience
{
	Map<String,Object> map = new HashMap<String, Object>();
	
	/**
	 * 书签
	 */
	public final String MARKER = "table_cover";
	
	/**
	 * 封皮表单ID
	 */
	public final String COVER_FORM_ID = "";
	
	/**
	 * logo图片
	 */
	final String IMG_LOGO = "img_logo";
	
	/**
	 * 图号
	 */
	final String MAP_NO = "text_cover_map_no";
	
	/**
	 * 图名
	 */
	final String MAP_NAME = "text_cover_map_name";
	
	/**
	 * 生产类型(版本)
	 */
	final String REVISION = "text_cover_revision";
	
	/**
	 * 比例尺
	 */
	final String SCALE = "text_cover_scale";
	
	/**
	 * 实施部门
	 */
	final String DEPARTMENT = "text_cover_department";
	
	/**
	 * 实施日期
	 */
	final String DATE = "text_cover_date";
	
	public void setDataProps(List<FormValue> valueList){
		this.setProps(this.map, COVER_FORM_ID, valueList, "cover");
	}

	public void setIMG_LOGO(String imgLogo)
	{
		this.map.put(this.IMG_LOGO, imgLogo);
	}

	public void setMAP_NAME(String mapName)
	{
		this.map.put(this.MAP_NAME, mapName);
	}

	public void setMAP_NO(String mapNo)
	{
		this.map.put(this.MAP_NO, mapNo);
	}
	public void setREVISION(String revision)
	{
		this.map.put(this.REVISION, revision);
	}

	public void setSCALE(String scale)
	{
		this.map.put(this.SCALE, scale);
	}

	public void setDEPARTMENT(String department)
	{
		this.map.put(this.DEPARTMENT, department);
	}

	public void setDATE(String date)
	{
		this.map.put(this.DATE, date);
	}
	
	public Map<String,Object> getCoverMap(){
		return this.map;
	}
}
