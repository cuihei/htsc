package com.ht.service.constant.experiencebook.paperchartsmallcorrection;

import java.util.HashMap;
import java.util.Map;

/**
 * 纸海图小改正经历簿头部常量类
 * @author houchen
 * 2016/12/16
 */
public class Head
{
	Map<String,Object> map = new HashMap<String, Object>();
	
	/**
	 * 书签
	 */
	public final String MARKER = "table_zhtxgzjlb";
	
	/**
	 * 标题
	 */
	final String TITLE = "text_zhtxgzjlb_title";
	
	/**
	 * 涉及图名
	 */
	final String MAP_NAME = "text_zhtxgzjlb_map_name";
	
	/**
	 * 涉及图号
	 */
	final String MAP_NO = "text_zhtxgzjlb_map_no";
	
	/**
	 * 版次
	 */
	final String REVISION = "text_zhtxgzjlb_revision";
	
	/**
	 * 比例尺
	 */
	final String SCALE = "text_zhtxgzjlb_scale";
	
//	/**
//	 * 改正通告期号
//	 */
//	final String NOTICENO = "text_zhtxgzjlb_gztgqh";
//	
//	/**
//	 * 改正项号
//	 */
//	final String ITEMNO = "text_zhtxgzjlb_gzxh";

	public void setTITLE(String title)
	{
		this.map.put(this.TITLE, title);
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

//	public void setNOTICENO(String noticeno)
//	{
//		this.map.put(this.NOTICENO, noticeno);
//	}
//
//	public void setITEMNO(String itemno)
//	{
//		this.map.put(this.ITEMNO, itemno);
//	}
	
	public Map<String,Object> getHeadMap(){
		return this.map;
	}
}
