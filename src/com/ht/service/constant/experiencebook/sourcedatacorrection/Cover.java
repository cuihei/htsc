package com.ht.service.constant.experiencebook.sourcedatacorrection;

import java.util.HashMap;
import java.util.Map;

/**
 * 源数据小改正经历簿 封皮
 * @author huodesheng
 * 
 */
public class Cover {
	Map<String, Object> map = new HashMap<String, Object>();

	/**
	 * 书签
	 */
	public final String MARKER = "table_cover";

	/**
	 * 封皮表单ID
	 */
	public final String COVER_FORM_ID = "";
	/**
	 * logo
	 */
	public final String IMG_COVER_LOGO = "IMG_COVER_LOGO";

//	/**
//	 * 改正期号
//	 */
//	public final String TEXT_COVER_CORRECT_NO = "text_cover_correct_no";
//	/**
//	 * 工程名称
//	 */
//	public final String TEXT_COVER_PROJECT_NAME = "text_cover_project_name";
	/**
	 * 实施部门
	 */
	public final String TEXT_COVER_DEPARTMENT = "text_cover_department";
	/**
	 * 实施日期
	 */
	public final String TEXT_COVER_DATE = "text_cover_date";

	public void setIMG_COVER_LOGO(String img_cover_logo) {
		this.map.put(this.IMG_COVER_LOGO, img_cover_logo);
	}

//	public void setTEXT_COVER_CORRECT_NO(String text_cover_correct_no) {
//		this.map.put(this.TEXT_COVER_CORRECT_NO, text_cover_correct_no);
//	}
//
//	public void setTEXT_COVER_PROJECT_NAME(String text_cover_project_name) {
//		this.map.put(this.TEXT_COVER_PROJECT_NAME, text_cover_project_name);
//	}

	public void setTEXT_COVER_DEPARTMENT(String text_cover_department) {
		this.map.put(this.TEXT_COVER_DEPARTMENT, text_cover_department);
	}

	public void setTEXT_COVER_DATE(String text_cover_date) {
		this.map.put(this.TEXT_COVER_DATE, text_cover_date);
	}

	public Map<String, Object> getCoverMap() {
		return this.map;
	}

}
