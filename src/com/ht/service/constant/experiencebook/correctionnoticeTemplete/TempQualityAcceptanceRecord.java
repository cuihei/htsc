package com.ht.service.constant.experiencebook.correctionnoticeTemplete;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ht.persistence.model.complication.formprop.FormValue;
import com.ht.service.constant.experiencebook.BaseExperience;

/**
 * 总工程师质量验收记录表类
 * 
 * @author huodesheng
 */
public class TempQualityAcceptanceRecord extends BaseExperience{
	Map<String, Object> map = new HashMap<String, Object>();

	/**
	 * 书签
	 */
	public final String MARKER = "table_zgcszlysb";

	public final String ZGCSZLYSB_FORM_ID = "11051451215550006";
	
	/**
	 * 标题
	 */
	final String TITLE = "text_zgcszlysb_title";

	/**
	 * 验收结论
	 */
	final String YSJL = "text_zgcszlysb_ysjl";
	
	/**
	 * 评分等级
	 */
	final String PFDJ = "text_zgcszlysb_pfdj";
	
	
	/**
	 * 总工程师批示
	 */
	final String CHIEF_ENGINEER = "text_zgcszlysb_chief_engineer";
	
	/**
	 * 总工程师
	 */
	final String CHIEF = "mtext_zgcszlysb_chief";
	
	/**
	 * 总工程师批示批示日期
	 */
	final String CHIEF_ENGINEER_DATE = "text_zgcszlysb_date";
	
	/**
	 * 签章
	 */
	final String SIGNATURE = "img_zgcszlysb_signature";
	
	public void setDataProps(List<FormValue> valueList) {
		this.setProps(map, ZGCSZLYSB_FORM_ID, valueList, "zgcszlysb");
	}
	
	public void setYSJL(String ysjl) {
		this.map.put(this.YSJL, ysjl);
	}
	
	public void setPFDJ(String pfdj) {
		this.map.put(this.PFDJ, pfdj);
	}

	public void setTITLE(String title) {
		this.map.put(this.TITLE, title);
	}
	
	public void setCHIEF(String chief) {
		this.map.put(this.CHIEF, chief);
	}

	public void setCHIEF_ENGINEER(String chief_engineer) {
		this.map.put(this.CHIEF_ENGINEER, chief_engineer);
	}
	
	public void setCHIEF_ENGINEER_DATE(String chief_engineer_date) {
		this.map.put(this.CHIEF_ENGINEER_DATE, chief_engineer_date);
	}

	public void setSIGNATURE(String signature) {
		this.map.put(this.SIGNATURE, signature);
	}
	
	public Map<String, Object> getQualityAcceptanceRecordMap() {
		return this.map;
	}

	public String getZGCSZLYSB_FORM_ID() {
		return ZGCSZLYSB_FORM_ID;
	}

	public String getMARKER() {
		return MARKER;
	}
	
}
