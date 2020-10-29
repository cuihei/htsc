package com.ht.service.constant.experiencebook.paperchartsmallcorrection;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ht.persistence.model.complication.formprop.FormValue;
import com.ht.service.constant.experiencebook.BaseExperience;

/**
 * 纸海图小改正总工程师质量验收记录表类
 * @author houchen
 * @date 2016/12/19
 */
public class QualityAcceptanceRecord extends BaseExperience{
	Map<String, Object> map = new HashMap<String, Object>();

	/**
	 * 书签
	 */
	public final String MARKER = "table_zhtxgzysjlb";

	public final String QUALITY_ACCEPTANCE_FORM_ID = "1123022327870498";
	
	/**
	 * 标题
	 */
	final String TITLE = "text_zgcszlysb_title";

	/**
	 * 验收意见
	 */
	final String YSYJ = "text_zhtxgzysjlb_ysyj";
	
	/**
	 * 验收结论
	 */
	final String YSJL = "text_zhtxgzysjlb_ysjl";
	
	/**
	 * 评分等级
	 */
	final String PFDJ = "text_zhtxgzysjlb_pfdj";
	
	/**
	 * 验收日期
	 */
	final String RQ = "text_zhtxgzysjlb_rq";
	
	/**
	 * 总工程师
	 */
	final String ZGCS = "mtext_zhtxgzysjlb_zgcs";
	
	/**
	 * 总工程师意见
	 */
	final String YJ = "text_zhtxgzysjlb_yj";
	

	public void setDataProps(List<FormValue> valueList) {
		this.setProps(map, QUALITY_ACCEPTANCE_FORM_ID, valueList, "zhtxgzysjlb");
	}

	public void setTITLE(String title) {
		this.map.put(this.TITLE, title);
	}
	
	public void setYSYJ(String ysyj) {
		this.map.put(this.YSYJ, ysyj);
	}
	
	public void setYSJL(String ysjl) {
		this.map.put(this.YSJL, ysjl);
	}
	
	public void setPFDJ(String pfdj) {
		this.map.put(this.PFDJ, pfdj);
	}

	public void setRQ(String rq) {
		this.map.put(this.RQ, rq);
	}

	public void setZGCS(String zgcs) {
		this.map.put(this.ZGCS, zgcs);
	}
	
	public void setYJ(String yj) {
		this.map.put(this.YJ, yj);
	}
	
	public Map<String, Object> getQualityAcceptanceRecordMap() {
		return this.map;
	}
}
