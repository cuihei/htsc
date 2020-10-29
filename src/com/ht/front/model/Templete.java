package com.ht.front.model;

import com.ht.front.css.CssClass;

/**
 * H标签对象
 * @author yaoping
 * @date 2016/10/22
 */
public class Templete extends Base{

	/**
	 * 构造器 获得一个H标签实例
	 * @param cssClass 样式
	 */
	protected Templete(String nodeType,CssClass cssClass) {
		super(nodeType, null, cssClass);
	}
	
	/**
	 * 获取I标签实例
	 * @param cssClass 样式
	 * @return I标签实例
	 */
	public static Templete getInstance(String nodeType,CssClass cssClass){
		return new Templete(nodeType,cssClass);
	}
}
