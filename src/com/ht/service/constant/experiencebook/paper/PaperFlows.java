package com.ht.service.constant.experiencebook.paper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ht.persistence.model.complication.formprop.FormValue;
import com.ht.service.constant.experiencebook.BaseExperience;

/**
 * 纸海图编绘经历簿工序流程表
 * @author yp
 */
public class PaperFlows extends BaseExperience
{
	Map<String, Object> map = new HashMap<String, Object>();
	/**
	 * 书签
	 */
	public final String MARKER = "table_gx";
	
	/**
	 * 表单id
	 */
	public final String FLOWS_FORM_ID = "11051539395420057";
	
	/**
	 * 标题
	 */
	final String TITLE = "text_gx_title";
	
	/**
	 * 工序流程
	 */
	final String ORDER =  "text_gx_order";
	
	/**
	 * 作业人员
	 */
	final String PERFOERMER = "text_gx_performer";
	
	/**
	 * 作业时间（开始--结束）
	 */
	final String DATES = "text_gx_dates";
	
	/**
	 * 备注
	 */
	final String REMARK = "text_gx_remark";
	
	public void setDataProps(List<FormValue> valueList){
		this.setProps(this.map, FLOWS_FORM_ID, valueList, "gx");
	}

	public void setTITLE(String title){
		this.map.put(this.TITLE, title);
	}
	
	public void setORDER(String order){
		this.map.put(this.ORDER, order);
	}
	
	public void setPERFOERMER(String perfoermer){
		this.map.put(this.PERFOERMER, perfoermer);
	}

	public void setDATES(String dates){
		this.map.put(this.DATES, dates);
	}
	
	public void setREMARK(String remark){
		this.map.put(this.REMARK, remark);
	}
	
	public Map<String,Object> getFlowsMap(){
		return this.map;
	}
}
