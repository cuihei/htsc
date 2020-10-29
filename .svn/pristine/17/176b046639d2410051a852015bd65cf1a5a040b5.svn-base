package com.ht.front.model;

import com.ht.front.css.CssClass;

/**
 * form表单对象
 * @author penghao
 * @date 2016/10/18
 */
public class Form extends Base{
	
	/**
	 * 构造器 引用父类方法 初始化表单
	 * @param nodeType 节点类型
	 * @param nodeId 节点ID
	 * @param cssClass css样式类型
	 * @param 
	 */
	protected Form(String nodeType, String nodeId, CssClass cssClass,String value) {
		super(nodeType, nodeId, cssClass,value);
	}
	
	/**
	 * 构建一个表单
	 * @param nodeId 表单编号
	 * @param cssClass css样式
	 * @param value 显示值
	 * @return 表单实例
	 */
	public static Form getInstance(String nodeId,CssClass cssClass,String value){
		return new Form("form",nodeId,cssClass,value);
	}
	
	/**
	 * 获取默认表单
	 * @param 
	 * @return 表单
	 */
	public static Form getDefault(){
		CssClass css = new CssClass("form-search");
		return new Form("from",null,css,null);
	}
	
	
}
