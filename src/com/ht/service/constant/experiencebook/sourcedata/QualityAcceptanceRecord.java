package com.ht.service.constant.experiencebook.sourcedata;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ht.persistence.model.complication.formprop.FormValue;
import com.ht.service.constant.experiencebook.BaseExperience;

/**
 * 总工程师质量验收记录表类
 * 
 * @author zqy
 * @date 2016/12/7
 */
public class QualityAcceptanceRecord extends BaseExperience{
	Map<String, Object> map = new HashMap<String, Object>();

	/**
	 * 书签
	 */
	public final String MARKER = "table_zgcszlysb";

	public final String QUALITY_ACCEPTANCE_FORM_ID = "11031856168990051";
	
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
	 * 通告改正至
	 */
	final String NOTICE_TO_CORRECTION = "text_zgcszlysb_notice_to_correction";
	
	/**
	 * 总工程师批示
	 */
	final String CHIEF_ENGINEER = "text_zgcszlysb_chief_engineer";
	
	/**
	 * 总工程师批示
	 */
	final String CHIEF_ENGINEER_DATE = "text_zgcszlysb_chief_engineer_date";
	
	/**
	 * 总工程师
	 */
	final String CHIEF= "mtext_zgcszlysb_chief";
	
	/**
	 * 签章
	 */
	final String SIGNATURE = "img_zgcszlysb_signature";


	public void setDataProps(List<FormValue> valueList) {
		this.setProps(map, QUALITY_ACCEPTANCE_FORM_ID, valueList, "zgcszlysb");
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

	public void setNOTICE_TO_CORRECTION(String notice_to_correcction) {
		this.map.put(this.NOTICE_TO_CORRECTION, notice_to_correcction);
	}

	public void setCHIEF_ENGINEER(String chief_engineer) {
		this.map.put(this.CHIEF_ENGINEER, chief_engineer);
	}
	
	public void setCHIEF_ENGINEER_DATE(String chief_engineer_date) {
		this.map.put(this.CHIEF_ENGINEER_DATE, chief_engineer_date);
	}

	public void setCHIEF(String chief) {
		this.map.put(this.CHIEF, chief);
	}
	public void setSIGNATURE(String signature) {
		this.map.put(this.SIGNATURE, signature);
	}
	
	public Map<String, Object> getQualityAcceptanceRecordMap() {
		return this.map;
	}
}
