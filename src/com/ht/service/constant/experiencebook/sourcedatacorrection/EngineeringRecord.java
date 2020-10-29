package com.ht.service.constant.experiencebook.sourcedatacorrection;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ht.persistence.model.complication.formprop.FormValue;
import com.ht.service.constant.experiencebook.BaseExperience;
/**
 * 改正期号&工程名称
 * @author huodesheng
 *
 */
public class EngineeringRecord extends BaseExperience{
	Map<String, Object> map = new HashMap<String, Object>();

	/**
	 * 书签
	 */
	public final String MARKER = "table_Engineering";
	/**
	 * 源数据小改正内容记录表表单id
	 */
	public final String EngineeringRecord_FORM_ID="01161032213290058";
	/**
	 *改正期号
	 */
	public final String GZQH="text_engineering_gzqh";
	/**
	 * 工程名称
	 */
	public final String GCMC="text_engineering_gcmc";
	
	public void setDataProps(List<FormValue> valueList){
		this.setProps(this.map, EngineeringRecord_FORM_ID, valueList, "engineering");
	}
	
	public void setGZQH(String gzqh){
		this.map.put(this.GZQH, gzqh);
	}
	
	public void setGCMC(String gcmc){
		this.map.put(this.GCMC, gcmc);
	}
	
	public Map<String,Object> getMap(){
		return this.map;
	}
}
