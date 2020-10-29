package com.ht.service.constant.experiencebook.paperchartsmallcorrection;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ht.persistence.model.complication.formprop.FormValue;
import com.ht.service.constant.experiencebook.BaseExperience;
/**
 * 纸海图小改正  改正通告期号
 * @author huodesheng
 *
 */
public class PaperItemNoIssue  extends BaseExperience {
	Map<String, Object> map = new HashMap<String, Object>();
	
	/**
	 * 书签
	 */
	public final String MARKER = "table_zhtxgzjlb_xh_qh";
	
	/**
	 * 纸海图小改正  改正通告期号id
	 */
	public final String PaperItemNoIssue_FORM_ID="01161032483760065";
	/**
	 *	通告期号
	 */
	public final String GZTGQH="text_zhtxgzjlb_gztgqh";
	/**
	 * 本期项号
	 */
	public final String GZXH="text_zhtxgzjlb_gzxh";
	
	public void setDataProps(List<FormValue> valueList){
		this.setProps(this.map, PaperItemNoIssue_FORM_ID, valueList, "zhtxgzjlb");
	}
	
	public void setGZTGQH(String gztgqh){
		this.map.put(this.GZTGQH, gztgqh);
	}
	
	public void setGZXH(String gzxh){
		this.map.put(this.GZXH, gzxh);
	}
	
	public Map<String,Object> getMap(){
		return this.map;
	}
	
	
}
