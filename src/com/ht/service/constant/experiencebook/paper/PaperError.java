/*package com.ht.service.constant.experiencebook.paper;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ht.persistence.model.complication.formprop.FormValue;
import com.ht.persistence.model.system.workflow.process.ProcessFlow;
import com.ht.service.constant.experiencebook.BaseExperience2;

*//**
 * 导出错误 说明 图文的常量
 * @author weihua
 * 2018/11/20
 *//*
public class PaperError extends BaseExperience2
{
	Map<String,Object> map = new HashMap<String, Object>();
	*//**
	 * 错误说明的条件
	 *//*
	public final String TASK_RESULT = "提交";
	*//**
	 * 书签
	 *//*
	public final String MARKER = "text_err";

	public final String TYPE = "text_err_type";

	public final String CREATIONDATE = "text_err_creation_date";
// 提交人
	public final String CREATOR = "text_err_creator";
// 提交内容一
	public final String ERRTXT1 = "text_err_errtxt1";
// 提交内容二
	public final String ERRTXT2 = "text_err_errtxt2";
// 提交内容三
	public final String ERRTXT3 = "text_err_errtxt3";
// 提交图片一
	public final String ERRJPG1 = "img_err_errjpg1";
// 提交图片二
	public final String ERRJPG2 = "img_err_errjpg2";
// 提交图片三
	public final String ERRJPG3 = "img_err_errjpg3";
//  节点名称
	public final String TASKNAME = "text_err_taskname";

	
	public void setErrorDataProps(List<ProcessFlow> valueList){
		this.setProps(this.map, TASK_RESULT, valueList, "err");
	}
	

	public void setTYPE(String type) {this.map.put(this.TYPE,type);};
	public void setCREATIONDATE(String creationdate) {this.map.put(this.CREATIONDATE,creationdate);};
	public void setCREATOR(String creator) {this.map.put(this.CREATOR,creator);};
	public void setERRTXT1(String errtxt1) {this.map.put(this.ERRTXT1,errtxt1);};
	public void setERRTXT2(String errtxt2) {this.map.put(this.ERRTXT2,errtxt2);};
	public void setERRTXT3(String errtxt3) {this.map.put(this.ERRTXT3,errtxt3);};
	public void setERRJPG1(String errjpg1) {this.map.put(this.ERRJPG1,errjpg1);};
	public void setERRJPG2(String errjpg2) {this.map.put(this.ERRJPG2,errjpg2);};
	public void setERRJPG3(String errjpg3) {this.map.put(this.ERRJPG2,errjpg3);};
	public void setTASKNAME(String taskname) {this.map.put(this.TASKNAME,taskname);};

	public Map<String,Object> getErrorNotesMap(){
		return this.map;
	}
}
*/