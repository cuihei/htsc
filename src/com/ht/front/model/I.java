package com.ht.front.model;

import com.ht.front.css.CssClass;

/**
 * I标签对象
 * @author 王有为
 * @date 2016/10/14
 */
public class I extends Base{

	/**
	 * 构造器 获得一个I标签实例
	 * @param cssClass 样式
	 */
	protected I(CssClass cssClass) {
		super("i", null, cssClass);
	}
	
	/**
	 * 获取I标签实例
	 * @param cssClass 样式
	 * @return I标签实例
	 */
	public static I getInstance(CssClass cssClass){
		return new I(cssClass);
	}
}
