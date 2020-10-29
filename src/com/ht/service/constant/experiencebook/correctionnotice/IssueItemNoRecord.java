package com.ht.service.constant.experiencebook.correctionnotice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ht.persistence.model.complication.formprop.FormValue;
import com.ht.service.constant.experiencebook.BaseExperience;
/**
 * 改正通告编辑		通告期号、本期项号
 * @author huodesheng
 *
 */
public class IssueItemNoRecord  extends BaseExperience{
	Map<String, Object> map = new HashMap<String, Object>();

	/**
	 * 书签
	 */
	public final String MARKER = "table_issueitemno";
	/**
	 * 源数据小改正内容记录表表单id
	 */
	public final String IssueItemNoRecord_FORM_ID="0116104143110089";
	/**
	 *	通告期号
	 */
	public final String TGQH="text_issueitemno_tgqh";
	/**
	 * 本期项号
	 */
	public final String BQXH="text_issueitemno_bqxh";
	
	public void setDataProps(List<FormValue> valueList){
		this.setProps(this.map, IssueItemNoRecord_FORM_ID, valueList, "issueitemno");
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
