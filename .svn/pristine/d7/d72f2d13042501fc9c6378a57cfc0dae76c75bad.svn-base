package com.ht.front.model;

import com.ht.front.css.CssClass;

/**
 * 按钮对象
 * @author 王有为
 * @date 2016/10/13
 */
public class Button extends Base{
	
	/**
	 * 构造器 引用父类方法 初始化按钮
	 * @param nodeType 节点类型
	 * @param nodeId 节点ID
	 * @param cssClass css样式类型
	 * @param name 按钮值
	 */
	protected Button(String nodeType, String nodeId, CssClass cssClass,String value) {
		super(nodeType, nodeId, cssClass,value);
	}
	
	/**
	 * 构建一个按钮
	 * @param nodeId 按钮编号
	 * @param cssClass css样式
	 * @param value 显示值
	 * @return 按钮实例
	 */
	public static Button getInstance(String nodeId,CssClass cssClass,String value){
		return new Button("button",nodeId,cssClass,value);
	}
	
	/**
	 * 获取默认按钮
	 * @param value 按钮显示值
	 * @return 按钮实例
	 */
	public static Button getDefault(String value){
		CssClass css = new CssClass("btn btn-default");
		return new Button("button",null,css,value);
	}
	
	/**
	 * 获取一个带图标的按钮
	 * @param nodeId 按钮ID
	 * @param cssClass 按钮样式
	 * @param value 按钮值
	 * @param i 按钮图标对象
	 * @return 带图标的按钮
	 */
	public static Button getButtonWithIcon(String nodeId,CssClass cssClass,String value,I i){
		Button button = getInstance(nodeId, cssClass, value);
		button.addChildNode(i);
		return button;
	}
}
