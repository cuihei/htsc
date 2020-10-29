package com.ht.service.constant.experiencebook.sourcedatacorrection;

import java.util.HashMap;
import java.util.Map;

import com.ht.service.constant.experiencebook.BaseExperience;
/**
 * 源数据小改正质检记录表
 * @author huodesheng
 *
 */
public class SDCQualityRecord extends BaseExperience{
	Map<String, Object> map = new HashMap<String, Object>();
	/**
	 * 书签
	 */
	public final String MARKER = "table_ygzzjjlb";
	/**
	 * 涉及图层
	 */
	final String LAYER_NAME="text_ygzzjjlb_layer_name";
	/**
	 * 存在问题
	 */
	final String DISCRIPTION="text_ygzzjjlb_discription";
	/**
	 * 处理意见
	 */
	final String OPINION="text_ygzzjjlb_opinion";
	/**
	 * 缺陷类别
	 */
	final String DEFECT_TYPE="text_ygzzjjlb_defect_type";
	/**
	 * 缺陷扣分
	 */
	final String SCORE="text_ygzzjjlb_score";
	/**
	 * 处理结果
	 */
	final String CLJG="text_ygzzjjlb_cljg";
	/**
	 * 缺陷累计扣分
	 */
	final String TOTAL="text_ygzzjjlb_total";
	/**
	 * 质量评分
	 */
	final String GRADING="text_ygzzjjlb_grading";
	/**
	 * 质检
	 */
	final String QUALITY_PERSON="mtext_ygzzjjlb_quality_person";
	/**
	 * 质检时间
	 */
	final String QUALITY_DATE="text_ygzzjjlb_quality_date";
	/**
	 *  科长
	 */
	final String CHIEF="mtext_ygzzjjlb_chief";
	/**
	 *科长时间
	 */
	final String CHIEF_DATE="text_ygzzjjlb_chief_date";
	
	public void setLAYER_NAME(String layer_name){
		this.map.put(this.LAYER_NAME,layer_name);
	}
	public void setDISCRIPTION(String discription){
		this.map.put(this.DISCRIPTION,discription);
	}
	public void setOPINION(String opinion){
		this.map.put(this.OPINION,opinion);
	}
	public void setDEFECT_TYPE(String defect_type){
		this.map.put(this.DEFECT_TYPE,defect_type);
	}
	public void setSCORE(String score){
		this.map.put(this.SCORE,score);
	}
	public void setCLJG(String cljg){
		this.map.put(this.CLJG,cljg);
	}
	public void setTOTAL(String total){
		this.map.put(this.TOTAL,total);
	}
	public void setGRADING(String grading){
		this.map.put(this.GRADING,grading);
	}
	public void setQUALITY_PERSON(String quality_person){
		this.map.put(this.QUALITY_PERSON,quality_person);
	}
	public void setQUALITY_DATE(String quality_date){
		this.map.put(this.QUALITY_DATE,quality_date);
	}
	public void setCHIEF(String chief){
		this.map.put(this.CHIEF,chief);
	}
	public void setCHIEF_DATE(String chief_date){
		this.map.put(this.CHIEF_DATE,chief_date);
	}

	public Map<String, Object> getMap() {
		return this.map;
	}

	public String getMARKER() {
		return MARKER;
	}
}
