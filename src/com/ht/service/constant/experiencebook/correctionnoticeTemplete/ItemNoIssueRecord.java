package com.ht.service.constant.experiencebook.correctionnoticeTemplete;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ht.persistence.model.complication.formprop.FormValue;
import com.ht.service.constant.experiencebook.BaseExperience;
/**
 * 改正通告模版编辑	通告期号、本期项号
 * @author huodesheng
 *
 */
public class ItemNoIssueRecord  extends BaseExperience{
	Map<String, Object> map = new HashMap<String, Object>();

	/**
	 * 书签
	 */
	public final String MARKER = "table_itemnoissue";
	/**
	 * 源数据小改正内容记录表表单id
	 */
	public final String ItemNoIssueRecord_FORM_ID="01161050123140100";
	/**
	 *	通告期号
	 */
	public final String TGQH="text_itemnoissue_tgqh";
	/**
	 * 本期项号
	 */
	public final String BQXH="text_itemnoissue_bqxh";
	
	public void setDataProps(List<FormValue> valueList){
		this.setProps(this.map, ItemNoIssueRecord_FORM_ID, valueList, "itemnoissue");
	}
	
	public void setTGQH(String tgqh){
		this.map.put(this.TGQH, tgqh);
	}
	
	public void setBQXH(String bqxh){
		this.map.put(this.BQXH, bqxh);
	}
	
	public Map<String,Object> getMap(){
		return this.map;
	}
	
}
