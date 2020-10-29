package com.ht.service.constant.experiencebook.electronicchartsmallcorrection;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ht.persistence.model.complication.formprop.FormValue;
import com.ht.service.constant.experiencebook.BaseExperience;
/**
 * 电子海图小改正 改正期号
 * @author huodesheng
 *
 */
public class EleCorrectionNoRecord   extends BaseExperience{
	Map<String, Object> map = new HashMap<String, Object>();
	/**
	 * 书签
	 */
	public final String MARKER = "table_dzhtxgzjlb";
	/**
	 * 电子海图小改正 改正期号id
	 */
	public final String EleCorrectionNoRecord_FORM_ID="0116103614260078";
	/**
	 *	改正期号
	 */
	public final String CORRECT_NO="text_dzhtxgzjlb_correct_no";
	
	public void setDataProps(List<FormValue> valueList){
		this.setProps(this.map, EleCorrectionNoRecord_FORM_ID, valueList, "dzhtxgzjlb");
	}
	
	public void setCORRECT_NO(String correct_no){
		this.map.put(this.CORRECT_NO, correct_no);
	}
	
	public Map<String,Object> getMap(){
		return this.map;
	}
	
}
