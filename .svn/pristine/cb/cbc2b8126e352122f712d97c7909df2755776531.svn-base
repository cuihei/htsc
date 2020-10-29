package com.ht.service.constant.experiencebook.paper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ht.persistence.model.complication.formprop.FormValue;
import com.ht.service.constant.experiencebook.BaseExperience;

/**
 * 纸海图编绘经历簿导出标题，潮信表，信标参数表常量类
 * @author yp
 * 2016/12/7
 */
public class BeaconParameter extends BaseExperience
{
	Map<String,Object> map = new HashMap<String, Object>();
	/**
	 * 表单id
	 */
	public final String BEACON_PARAMETER_FORM_ID = "11051550389970090";
	
	/**
	 * 书签
	 */
	public final String MARKER = "table_btcxxbcsb";
	
	/*// 标题
	final String BT = "img_btcxxbcsb_bt";

	// 潮信表
	final String CXB = "img_btcxxbcsb_cxb";
	
	// 信标参数表
	final String XBCS = "img_btcxxbcsb_xbcs";*/
	
	public void setDataProps(List<FormValue> valueList){
		this.setProps(this.map, BEACON_PARAMETER_FORM_ID, valueList, "btcxxbcsb");
	}
	/*public void setBT(String bt)
	{
		this.map.put(this.BT, bt);
	}
	public void setCXB(String cxb)
	{
		this.map.put(this.CXB, cxb);
	}
	public void setXBCS(String xbcs)
	{
		this.map.put(this.XBCS, xbcs);
	}*/
	public Map<String,Object> getBeaconParameterMap(){
		return this.map;
	}
}
