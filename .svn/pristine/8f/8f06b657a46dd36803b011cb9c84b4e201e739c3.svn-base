package com.ht.service.constant.experiencebook.electron;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ht.persistence.model.complication.formprop.FormValue;
import com.ht.service.constant.experiencebook.BaseExperience;

/**
 * 电子海图编绘经历薄签字内容
 * @author yp
 */
public class SignatureContent extends BaseExperience
{
	Map<String, Object> map = new HashMap<String, Object>();
	
	/**
	 * 书签
	 */
	public final String MARKER = "table_qznrt";
	
	public final String SIGNATURE_CONTENT_FORM_ID = "";
	
	/**
	 * 编绘
	 */
	final String COMPILATION_PERSON = "mtext_qznrt_compilation_person";
	
	/**
	 * 编绘时间
	 */
	final String COMPILATION_DATE = "text_qznrt_compilation_date";
	
	/**
	 * 质检
	 */
	final String QUALITY_PERSON = "mtext_qznrt_quality_person";
	
	/**
	 * 质检时间
	 */
	final String QUALITY_DATE = "text_qznrt_quality_date";
	
	/**
	 * 生产部门意见
	 */
	final String PD_OPINION = "text_qznrt_pd_opinion";
	
	/**
	 * 生产部门意见时间
	 */
	final String PD_OPINION_DATE = "text_qznrt_pd_opinion_date";
	
	/**
	 * 主管部门意见
	 */
	final String AD_OPINION = "text_qznrt_ad_opinion";
	
	/**
	 * 主管部门意见时间
	 */
	final String AD_OPINION_DATE = "text_qznrt_ad_opinion_date";
	
	public void setDataProps(List<FormValue> valueList){
		this.setProps(map, SIGNATURE_CONTENT_FORM_ID, valueList, "qznrt");
	}
	
	public void setCOMPILATION_PERSON(String compilation_person){
		this.map.put(this.COMPILATION_PERSON, compilation_person);
	}
	
	public void setCOMPILATION_DATE(String compilation_date){
		this.map.put(this.COMPILATION_DATE, compilation_date);
	}
	
	public void setQUALITY_PERSON(String quality_person){
		this.map.put(this.QUALITY_PERSON, quality_person);
	}
	
	public void setQUALITY_DATE(String quality_date){
		this.map.put(this.QUALITY_DATE, quality_date);
	}
	public void setPD_OPINION(String pd_opinion){
		this.map.put(this.PD_OPINION, pd_opinion);
	}
	
	public void setPD_OPINION_DATE(String pd_opinion_date){
		this.map.put(this.PD_OPINION_DATE, pd_opinion_date);
	}
	public void setAD_OPINION(String ad_opinion){
		this.map.put(this.AD_OPINION, ad_opinion);
	}
	
	public void setAD_OPINION_DATE(String ad_opinion_date){
		this.map.put(this.AD_OPINION_DATE, ad_opinion_date);
	}
	
	public Map<String,Object> getWorkPlanMap(){
		return this.map;
	}
}
