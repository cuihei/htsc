package com.ht.service.constant.experiencebook.sourcedata;

import java.util.HashMap;
import java.util.Map;

/**
 * 源数据编绘经历簿导出封皮常量类
 * @author wangyouwei
 * 2016/12/4
 */
public class Cover
{
	Map<String,Object> map = new HashMap<String, Object>();
	
	/**
	 * 书签
	 */
	public final String MARKER = "table_cover";
	
	/**
	 * 封皮表单ID
	 */
	public final String COVER_FORM_ID = "11031838218790020";
	
	/**
	 * logo图片
	 */
	final String IMG_LOGO = "img_logo";
	
	/**
	 * 工程名称
	 */
	final String PROJECT_NAME = "text_cover_project_name";
	
	/**
	 * 生产类型(版本)
	 */
	final String REVISION = "text_cover_revision";
	
	/**
	 * 涉及图层
	 */
	final String LAYER = "text_cover_layer";
	
	/**
	 * 比例尺
	 */
	final String SCALE = "text_cover_scale";
	
	/**
	 * 涉及图名
	 */
	final String MAP_NAME = "text_cover_map_name";
	
	/**
	 * 涉及图号
	 */
	final String MAP_NO = "text_cover_map_no";
	
	/**
	 * 实施部门
	 */
	final String DEPARTMENT = "text_cover_department";
	
	/**
	 * 实施日期
	 */
	final String DATE = "text_cover_date";

	public void setIMG_LOGO(String imgLogo)
	{
		this.map.put(this.IMG_LOGO, imgLogo);
	}

	public void setPROJECT_NAME(String projectName)
	{
		this.map.put(this.PROJECT_NAME, projectName);
	}

	public void setREVISION(String revision)
	{
		this.map.put(this.REVISION, revision);
	}

	public void setLAYER(String layer)
	{
		this.map.put(this.LAYER, layer);
	}

	public void setSCALE(String scale)
	{
		this.map.put(this.SCALE, scale);
	}

	public void setMAP_NAME(String mapName)
	{
		this.map.put(this.MAP_NAME, mapName);
	}

	public void setMAP_NO(String mapNo)
	{
		this.map.put(this.MAP_NO, mapNo);
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
