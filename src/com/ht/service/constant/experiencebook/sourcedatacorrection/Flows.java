package com.ht.service.constant.experiencebook.sourcedatacorrection;

import java.util.HashMap;
import java.util.Map;

/**
 * 改正公告编辑经历簿 工序流程表
 * @author huodesheng
 */
public class Flows
{
	Map<String, Object> map = new HashMap<String, Object>();
	/**
	 * 书签
	 */
	public final String MARKER = "table_gx";
	
	/**
	 * 标题
	 */
	final String TITLE = "text_gx_title";
	
	/**
	 * 工序流程
	 */
	final String ORDER =  "text_gx_order";
	
	/**
	 * 作业人员
	 */
	final String PERFOERMER = "text_gx_performer";
	
	/**
	 * 作业时间（开始--结束）
	 */
	final String DATES = "text_gx_dates";
	
	
	public void setTITLE(String title){
		this.map.put(this.TITLE, title);
	}
	
	public void setORDER(String order){
		this.map.put(this.ORDER, order);
	}
	
	public void setPERFOERMER(String perfoermer){
		this.map.put(this.PERFOERMER, perfoermer);
	}

	public void setDATES(String dates){
		this.map.put(this.DATES, dates);
	}
	
	public Map<String,Object> getFlowsMap(){
		return this.map;
	}
}
