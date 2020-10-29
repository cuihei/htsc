package com.ht.service.constant.experiencebook.electron;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ht.persistence.model.complication.formprop.FormValue;
import com.ht.service.constant.experiencebook.BaseExperience;

/**
 * 电子海图总工程师质量验收记录表类
 * 
 * @author yp
 * @date 2016/12/7
 */
public class EleQualityAcceptanceRecord extends BaseExperience{
	Map<String, Object> map = new HashMap<String, Object>();

	/**
	 * 书签
	 */
	public final String MARKER = "table_zgcszlysb";

	public final String QUALITY_ACCEPTANCE_FORM_ID = "11051537527050053";
	/**
	 * 验收结论
	 */
	final String SDJL = "text_zgcszlysb_sdjl";
	
	/**
	 * 评分等级
	 */
	final String PFDJ = "text_zgcszlysb_pfdj";
	
	/**
	 * 通告改正至
	 */
	final String NOTICE_TO_CORRECTION = "text_zgcszlysb_notice_to_correction";
	
	/**
	 * 总工程师批示
	 */
	final String CHIEF_ENGINEER = "text_zgcszlysb_chief_engineer";
	
	/**
	 * 总工程师
	 */
	final String CHIEF= "mtext_zgcszlysb_chief";
	
	/**
	 * 时间
	 */
	final String DATE = "text_zgcszlysb_date";
	

	public void setDataProps(List<FormValue> valueList) {
		this.setProps(map, QUALITY_ACCEPTANCE_FORM_ID, valueList, "zgcszlysb");
	}
	
	public void setNOTICE_TO_CORRECTION(String notice_to_correction) {
		this.map.put(this.NOTICE_TO_CORRECTION, notice_to_correction);
	}
	
	public void setCHIEF_ENGINEER(String chief_engineer) {
		this.map.put(this.CHIEF_ENGINEER, chief_engineer);
	}

	public void setDATE(String date) {
		this.map.put(this.DATE, date);
	}
	public void setCHIEF(String chief) {
		this.map.put(this.CHIEF, chief);
	}

	public void setSDJL(String sdjl) {
		this.map.put(this.SDJL, sdjl);
	}
	
	public void setPFDJ(String pfdj) {
		this.map.put(this.PFDJ, pfdj);
	}
	
	public Map<String, Object> getQualityAcceptanceRecordMap() {
		return this.map;
	}
}
