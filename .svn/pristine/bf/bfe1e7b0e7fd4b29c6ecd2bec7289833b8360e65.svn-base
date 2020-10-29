package com.ht.front.model;

import com.ht.front.css.CssClass;

public class Div extends Base {
	/**
	 * 构造器 引用父类方法 初始化按钮
	 * 
	 * @param nodeType
	 *            节点类型
	 * @param nodeId
	 *            节点ID
	 * @param cssClass
	 *            css样式类型
	 * @param name
	 *            按钮值
	 */
	protected Div(String nodeType, String nodeId, CssClass cssClass, String name) {
		super(nodeType, nodeId, cssClass, name);
	}

	/**
	 * 构建DIV
	 * 
	 * @param nodeId
	 *            div ID属性
	 * @param cssClass
	 *            div class属性
	 * @param value
	 *            div InnerHtml
	 * @return DIV
	 */
	public static Div getInstance(String nodeId, CssClass cssClass, String value) {
		return new Div("div", nodeId, cssClass, value);
	}

	/**
	 * 创建一个空的Div
	 * 
	 * @return
	 */
	public static Div getBlankDiv(String id) {
		return getInstance(id, null, null);
	}
}
