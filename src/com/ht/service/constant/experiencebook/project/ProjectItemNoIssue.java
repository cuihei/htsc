package com.ht.service.constant.experiencebook.project;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ht.persistence.model.complication.formprop.FormValue;
import com.ht.service.constant.experiencebook.BaseExperience;

/**
 * 工程&专题图 海图用途、项目名称、任务书编号
 * @author huodesheng
 *
 */
public class ProjectItemNoIssue extends BaseExperience{
	Map<String, Object> map = new HashMap<String, Object>();
	/**
	 * 书签
	 */
	public final String MARKER = "table_projectitemnoissue";
	
	/**
	 * 工程&专题图  纸海图表单id
	 */
	public final String ProjectItemNoIssue_Paper_FORM_ID="01161051206710111";
	
	/**
	 * 工程&专题图 电子海图表单id
	 */
	public final String ProjectItemNoIssue_Electron_FORM_ID="01161051363580118";
	
	// 海图用途
	final String HTYT = "text_projectitemnoissue_htyt";

	// 项目名称
	final String XMMC = "text_projectitemnoissue_xmmc";
	
	// 测量日期
	final String CLRQ= "text_projectitemnoissue_clrq";
	
	// 任务书编号
	final String RWSBH = "text_projectitemnoissue_rwsbh";

	public void setHTYT(String htyt)
	{
		this.map.put(this.HTYT, htyt);
	}
	public void setXMMC(String xmmc)
	{
		this.map.put(this.XMMC, xmmc);
	}
	public void setCLRQ(String clrq)
	{
		this.map.put(this.CLRQ, clrq);
	}
	public void setRWSBH(String rwsbh)
	{
		this.map.put(this.RWSBH, rwsbh);
	}
	public String getHTYT()
	{
		return (String) this.map.get(this.HTYT);
	}
	public String getXMMC()
	{
		return (String) this.map.get(this.XMMC);
	}
	public String getCLRQ()
	{
		return (String) this.map.get(this.CLRQ);
	}
	public String getRWSBH()
	{
		return (String) this.map.get(this.RWSBH);
	}
	// 设置纸海图数据
	public void setPaperDataProps(List<FormValue> valueList)
	{
		this.setProps(map, ProjectItemNoIssue_Paper_FORM_ID, valueList, "projectitemnoissue");
	}
	// 设置电子海图数据
	public void setElectronDataProps(List<FormValue> valueList)
	{
		this.setProps(map, ProjectItemNoIssue_Electron_FORM_ID, valueList, "projectitemnoissue");
	}
	
	//获取map
	public Map<String, Object> getMap(){
		return this.map;
	}
	
}
