package com.ht.service.constant.experiencebook.paper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ht.persistence.model.complication.formprop.FormValue;
import com.ht.service.constant.experiencebook.BaseExperience;



/**
 * 纸海图编绘经历簿导出 纸海图质检记录表、港口航道图内业编绘质量评分标准 常量类
 * @author yp
 * 2016/12/7
 */
public class PaperQualityRecored  extends BaseExperience
{
	
	Map<String,Object> map = new HashMap<String, Object>();
	
	/**
	 * 书签
	 */
	public final String MARKER = "table_zjjlbzlpf";
	
	public final String QUALITY_RECORED_FORM_ID = "1105155751870109";
	// 存在问题
	final String DISCRIPTION = "text_zjjlbzlpf_discription";
	
	// 处理意见
	final String OPINION = "text_zjjlbzlpf_opinion";
	
	// 备注
	final String REMARKS = "text_zjjlbzlpf_remarks";
	
	// 质量扣分
	final String SCORE = "text_zjjlbzlpf_score";
			
	// 通告改正至
	final String NOTICE_TO_CORRECTION = "text_zjjlbzlpf_notice_to_correction";
	
	// 质量累计扣分
	final String TOTAL = "text_zjjlbzlpf_total";
	
	// 调整系数
	final String COEFFICIENT = "text_zjjlbzlpf_coefficient";
	
	// 实际扣分
	final String ACTUAL = "text_zjjlbzlpf_actual";
	
	// 质量评分
	final String GRADING = "text_zjjlbzlpf_grading";
		
	// 编绘员
	final String COMPILATION_PERSON = "mtext_zjjlbzlpf_compilation_person";
	
	// 编绘时间
	final String COMPILATION_DATE = "text_zjjlbzlpf_compilation_date";
	
	// 质检员
	final String QUALITY_PERSON = "mtext_zjjlbzlpf_quality_person";
	
	// 质检时间
	final String QUALITY_DATE = "text_zjjlbzlpf_quality_date";
	
	
	public void setDataProps(List<FormValue> valueList){
		this.setProps(map, QUALITY_RECORED_FORM_ID, valueList, "qznrt");
	}
	
	public void setDISCRIPTION(String discription)
	{
		this.map.put(this.DISCRIPTION, discription);
	}
	
	public void setOPINION(String opinion)
	{
		this.map.put(this.OPINION, opinion);
	}
	
	public void setREMARKS(String remarks)
	{
		this.map.put(this.REMARKS, remarks);
	}
	
	public void setSCORE(String score)
	{
		this.map.put(this.SCORE, score);
	}
	
	public void setNOTICE_TO_CORRECTION(String notice_to_correction)
	{
		this.map.put(this.NOTICE_TO_CORRECTION, notice_to_correction);
	}
	
	public void setTOTAL(String total)
	{
		this.map.put(this.TOTAL, total);
	}
	
	public void setCOEFFICIENT(String coefficient)
	{
		this.map.put(this.COEFFICIENT, coefficient);
	}

	public void setACTUAL(String actual)
	{
		this.map.put(this.ACTUAL, actual);
	}

	public void setGRADING(String grading)
	{
		this.map.put(this.GRADING, grading);
	}

	public void setCOMPILATION_PERSON(String compilation_person)
	{
		this.map.put(this.COMPILATION_PERSON, compilation_person);
	}

	public void setCOMPILATION_DATE(String compilation_date)
	{
		this.map.put(this.COMPILATION_DATE, compilation_date);
	}

	public void setQUALITY_PERSON(String quality_person)
	{
		this.map.put(this.QUALITY_PERSON, quality_person);
	}

	public void setQUALITY_DATE(String quality_date)
	{
		this.map.put(this.QUALITY_DATE, quality_date);
	}
	public Map<String,Object> getQualityRecoredMap(){
		return this.map;
	}
}
