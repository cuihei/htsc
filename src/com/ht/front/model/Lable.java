package com.ht.front.model;

import com.ht.front.css.CssClass;

public class Lable extends Base{
	/**
	 * 构造器 引用父类方法 初始化按钮
	 * @param nodeType 节点类型
	 * @param nodeId 节点ID
	 * @param cssClass css样式类型
	 * @param value 显示值
	 */
	protected Lable(String nodeType, String nodeId, CssClass cssClass,String value) {
		super(nodeType, nodeId, cssClass,value);
	}
	
	/**
	 * 获得一个lable实例
	 * @param nodeId 标签ID
	 * @param cssClass 样式
	 * @param value 显示值 
	 * @return 标签实例
	 */
	public static Lable getInstance(String nodeId,CssClass cssClass,String value){
		return new Lable("lable",nodeId,cssClass,value);
	}
	
	/**
	 * 获取一个默认的标签
	 * @param value 标签值
	 * @return 标签实例
	 */
	public static Lable getDefault(String value){
		CssClass css = new CssClass("control-label");
		return new Lable("lable",null,css,value);
	}
}
