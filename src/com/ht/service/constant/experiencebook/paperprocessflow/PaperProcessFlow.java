package com.ht.service.constant.experiencebook.paperprocessflow;

import java.util.HashMap;
import java.util.Map;

/**
 * 纸海图制作工序流程表
 * @author huodesheng
 *
 */
public class PaperProcessFlow {
	Map<String, Object> map = new HashMap<String, Object>();
	/**
	 * 书签
	 */
	public final String MARKER = "table_flow";
	
	//项目名称
	public final String XMMC = "text_flow_xmmc";
	//任务书编号
	public final String TASK_NO = "text_flow_task_no";
	//图    名
	public final String MAP_NAME = "text_flow_map_name";
	//图      号
	public final String MAP_NO = "text_flow_map_no";
	//比例尺
	public final String SCALE = "text_flow_scale";
	//产品编号
	public final String PRODUCT_NO = "text_flow_product_no";
	//版次       小改证编号
	public final String REVISION = "text_flow_ revision";
	//工程编号
	public final String PROJECT_NO = "text_flow_project_no";
	//文件路径1
	public final String  ROUTE_1 = "text_flow_ route_1";
	//文件路径2
	public final String  ROUTE_2 = "text_flow_ route_2";
	//纸海图
	public final String  PAPER = "text_flow_paper";
	//纸海图小改正1
	public final String  PAPER_MIN_1 = "text_flow_paper_min_1";
	//纸海图小改正2
	public final String  PAPER_MIN_2 = "text_flow_paper_min_2";
	//图幅变更信息
	public final String  CHANGE_INFORMATION= "text_flow_change_information";
	//流程
	public final String  PROCESS= "text_flow_process";
	//日期
	public final String  DATE= "text_flow_date";
	//备注
	public final String  REMARKS= "text_flow_remarks";
	//签名1
	public final String  AUTOGRAPH_1= "text_flow_autograph_1";
	//签名2
	public final String  AUTOGRAPH_2= "text_flow_autograph_2";
	//类型
	public final String  TYPE= "text_flow_type";
	
	public void setXMMC(String xmmc){
		this.map.put(this.XMMC, xmmc);
	}
	public void setTYPE(String type){
		this.map.put(this.TYPE, type);
	}
	
	public void setTASK_NO(String task_no){
		this.map.put(this.TASK_NO, task_no);
	}
	public void setMAP_NAME(String map_name){
		this.map.put(this.MAP_NAME, map_name);
	}
	public void setMAP_NO(String map_no){
		this.map.put(this.MAP_NO, map_no);
	}
	public void setSCALE(String scale){
		this.map.put(this.SCALE, scale);
	}
	public void setPRODUCT_NO(String product_no){
		this.map.put(this.PRODUCT_NO, product_no);
	}
	public void setROUTE_1(String route_1){
		this.map.put(this.ROUTE_1,route_1);
	}
	public void setROUTE_2(String route_2){
		this.map.put(this.ROUTE_2,route_2);
	}
	public void setPAPER_MIN_1(String paper_min_1){
		this.map.put(this.PAPER_MIN_1, paper_min_1);
	}
	public void setPAPER_MIN_2(String paper_min_2){
		this.map.put(this.PAPER_MIN_2, paper_min_2);
	}
	
	public void setCHANGE_INFORMATION(String change_information){
		this.map.put(this.CHANGE_INFORMATION, change_information);
	}
	
	public void setPROCESS(String process){
		this.map.put(this.PROCESS, process);
	}
	
	public void setDATE(String date){
		this.map.put(this.DATE, date);
	}
	
	public void setREMARKS(String remarks){
		this.map.put(this.REMARKS, remarks);
	}
	
	public void setAUTOGRAPH_1(String autograph_1){
		this.map.put(this.AUTOGRAPH_1, autograph_1);
	}
	public void setAUTOGRAPH_2(String autograph_2){
		this.map.put(this.AUTOGRAPH_2, autograph_2);
	}
	public void setREVISION(String revision){
		this.map.put(this.REVISION, revision);
	}
	public void setPROJECT_NO(String project_no){
		this.map.put(this.PROJECT_NO, project_no);
	}
	public void setPAPER(String paper){
		this.map.put(this.PAPER, paper);
	}
	
	public Map<String, Object> getMap(){
		return map;
	}
}
