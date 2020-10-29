package com.ht.front.model;

import com.ht.front.css.CssClass;
import com.ht.front.css.Prop;

/**
 * 多行文本对象
 * @author 王有为
 * @date 2016/10/14
 */
public class TextArea extends Base{

	/**
	 * 构造器
	 * @param nodeId 多行文本对象ID
	 * @param cssClass 样式
	 */
	protected TextArea(String nodeId, CssClass cssClass,String content) {
		super("textarea", nodeId, cssClass,content);
	}

	/**
	 * 获取多行文本对象实例
	 * @param nodeId 对象ID
	 * @param cssClass 样式
	 * @param rowNum 行数
	 * @param content 文本
	 * @return 多行文本对象实例
	 */
	public static TextArea getInstance(String nodeId,CssClass cssClass,String rowNum,String content){
		TextArea ta = new TextArea(nodeId,cssClass,content);
		Prop prop = new Prop();
		prop.setPropKey("rows");
		prop.setPropValue(rowNum);
		ta.addProp(prop);
		return ta;
	}
	
	/**
	 * 获取默认的TextArea对象
	 * @param nodeId 对象ID
	 * @param rowNum 行数
	 * @return 多行文本对象实例
	 */
	public static TextArea getDefault(String nodeId,String rowNum){
		CssClass css = new CssClass("form-control");
		return getInstance(nodeId,css,rowNum,null);
	}
}
