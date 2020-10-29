package com.ht.front.model;

import com.ht.front.css.CssClass;

/**
 * Hr
 * @author Administrator
 * @date 2016/10/15
 */
public class Hr extends Base{
	/**
	 * 构造器
	 * @param class class属性
	 */
	protected Hr(CssClass cssClass) {
		super(cssClass);
	}
	
	/**
	 * 获取一个Hr
	 * @param class class
	 */
	public static Hr getInstance(CssClass cssClass){
		return new Hr(cssClass);
	}
}
