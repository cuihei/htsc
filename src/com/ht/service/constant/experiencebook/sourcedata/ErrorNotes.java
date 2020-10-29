/*package com.ht.service.constant.experiencebook.sourcedata;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ht.common.util.SpringUtil;
import com.ht.persistence.dao.inter.complication.formprop.FormPropDao;
import com.ht.persistence.model.complication.formprop.FormPropFormView;
import com.ht.persistence.model.complication.formprop.FormValue;


*//**
 * 导出错误 说明 图文的常量
 * @author weihua
 * 2018/11/20
 *//*
public class ErrorNotes extends BaseExperience2
{
	Map<String,Object> map = new HashMap<String, Object>();
	*//**
	 * 错误说明的条件
	 *//*
	public final String Err_Notes_ID = "提交";
	*//**
	 * 书签
	 *//*
	public final String MARKER = "table_err";

final String TYPE = "text_err_type";
// 节点名称
final String NAME = "text_err_name";
//提交日期
final String DATE = "text_err_date";
// 提交人
final String SUBMIT = "text_err_submit";
// 提交内容一
final String ERRTXT1 = "text_err_errtxt1";
// 提交内容二
final String ERRTXT2 = "text_err_errtxt2";
// 提交内容三
final String ERRTXT3 = "text_err_errtxt3";
// 提交图片一
final String ERRJPG1 = "text_err_errjpg1";
// 提交图片二
final String ERRJPG2 = "text_err_errjpg2";
// 提交图片三
final String ERRJPG3 = "text_err_errjpg3";

final String TaskName = "text_err_taskname";


	public void setErrProps(List<ProcessFlow> valueList){
		this.setProps(this.map, Err_Notes_ID, valueList, "err");
	}
	
	public void setErrorDataProps(List<ProcessFlow> valueList){
		this.setProps(this.map, Err_Notes_ID, valueList, "err");
	}
	public void setTYPE(String type) {this.map.put(this.TYPE,type);};
	public void setDATE(String date) {this.map.put(this.DATE,date);};
	public void setNAME(String name) {this.map.put(this.DATE,name);};
	public void setSUBMIT(String submit) {this.map.put(this.SUBMIT,submit);};
	public void setERRTXT1(String errtxt1) {this.map.put(this.ERRTXT1,errtxt1);};
	public void setERRTXT2(String errtxt2) {this.map.put(this.ERRTXT2,errtxt2);};
	public void setERRTXT3(String errtxt3) {this.map.put(this.ERRTXT3,errtxt3);};
	public void setERRJPG1(String errjpg1) {this.map.put(this.ERRJPG1,errjpg1);};
	public void setERRJPG2(String errjpg2) {this.map.put(this.ERRJPG2,errjpg2);};
	public void setERRJPG3(String errjpg3) {this.map.put(this.ERRJPG2,errjpg3);};
	public void setTaskName(String taskname) {this.map.put(this.TaskName,taskname);};

	public Map<String,Object> getErrorNotesMap(){
		return this.map;
	}
}
*/