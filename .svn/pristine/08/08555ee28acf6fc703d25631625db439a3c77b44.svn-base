package com.ht.service.constant.experiencebook.electron;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ht.persistence.model.complication.formprop.FormValue;
import com.ht.service.constant.experiencebook.BaseExperience;

/**
 * 电子海图编绘经历簿首页常量
 * @author yp
 */
public class HomePage extends BaseExperience
{	
	Map<String,Object> map = new HashMap<String, Object>();
	
	/**
	 * 书签
	 */
	public final String MARKER = "table_descript";
	
	/**
	 * 表单id
	 */
	public final String HOME_PAGE_FORM_ID = "";
	
	/**
	 * 标题
	 */
	final String TITLE = "text_title";
	
	/**
	 * 说明标题
	 */
	final String INSTRUCTION_TITLE  = "text_instruction_title";
	
	/**
	 * 说明内容
	 */
	final String INSTRUTION_CONTENT = "text_instrution_content";

	public void setDataProps(List<FormValue> valueList){
		this.setProps(this.map, HOME_PAGE_FORM_ID, valueList, "descript");
	}
	
	public void setTITLE(String title)
	{
		this.map.put(this.TITLE, title);
	}

	public void setINSTRUCTION_TITLE(String instructionTitle)
	{
		this.map.put(this.INSTRUCTION_TITLE, instructionTitle);
	}

	public void setINSTRUTION_CONTENT(String instructionContent)
	{
		this.map.put(this.INSTRUTION_CONTENT, instructionContent);
	}
	
	public Map<String,Object> getHomePageMap(){
		return this.map;
	}
}
