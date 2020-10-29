package com.ht.front.model;

import com.ht.front.css.CssClass;

/**
 * 文本框对象
 * @author 王有为
 * @date 2016/10/13
 */
public class TextBox extends Base{

	/**
	 * 构造器
	 * @param id id属性
	 * @param type type属性
	 * @param name name属性
	 * @param value value属性
	 * @param css class属性
	 * @param placeholder placeholder属性
	 */
	public TextBox(String id, String type, String name, String value,
			CssClass css,String placeholder) {
		super(type, id, name,value,css,placeholder);
	}

	/**
	 * 构建文本框
	 * @param id id属性
	 * @param Type type属性
	 * @param name name属性
	 * @param value value属性
	 * @param css class属性
	 * @param placeholder placeholder属性
	 * @return 文本框实例
	 */
	public static TextBox getInstance(String id,String name, String value,
			CssClass css,String placeholder){
		return new TextBox(id, "text", name, value, css,placeholder);
	}
	
	/**
	 * 获取一个默认文本框
	 * @param id id属性
	 * @param value value属性
	 * @param placeholder placeholder属性
	 * @return 文本框实例
	 */
	public static TextBox getDefault(String id,String value,String placeholder){
		CssClass css = new CssClass("form-control");
		return new TextBox(id, "text", null, value, css,placeholder);
	}
	
	/**
	 * 获取一个密码文本框
	 * @param id id属性
	 * @param value value属性
	 * @param placeholder placeholder属性
	 * @return 密码文本框实例
	 */
	public static TextBox getPassWordDefault(String id,String value,String placeholder){
		CssClass css = new CssClass("form-control");
		return new TextBox(id, "password", null, value, css,placeholder);
	}
}
