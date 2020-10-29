package com.ht.front.model;

import com.ht.front.css.CssClass;

/**
 * 复选框
 * @author Administrator
 * @date 2016/10/15
 */
public class Input extends Base{
	/**
	 * 构造器
	 * @param id id属性
	 * @param type type属性
	 * @param name name属性
	 * @param value value属性
	 * @param css class属性
	 * @param placeholder placeholder属性
	 */
	protected Input(String id, String name,String value,String content) {
		super("checkbox", id, name,value,null,null,content);
	}
	
	
	/**
	 * 构造器 引用父类方法 初始化按钮
	 * @param nodeType 节点类型
	 * @param nodeId 节点ID
	 * @param cssClass css样式类型
	 * @param name 按钮值
	 */
	protected Input(String type, String id, CssClass cssClass,String value,Object obj) {
		super(type, id, cssClass,value,obj);
	}
	
	/**
	 * 构建一个按钮
	 * @param nodeId 按钮编号
	 * @param cssClass css样式
	 * @param value 显示值
	 * @return 按钮实例
	 */
	public static Input getInput(String type, String id, CssClass cssClass,String value,Object obj){
		return new Input(type,id,cssClass,value,obj);
	}
	/**
	 * 获取一个复选框
	 * @param id 复选框ID
	 * @param value 复选框value
	 * @return 复选框
	 */
	public static Input getInstance(String id, String name,String value,String content){
		return new Input(id,name,value,content);
	}
}
