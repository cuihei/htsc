package com.ht.workflow.util;

/**
 * BizGo系统工作流参数类
 * @author wyw
 * @version 1.0 2014/09/19
 */
public class Argument {
	
	/** 工作流参数名称 */
	private String name = null;
	/** 工作流参数值 */
	private Object value = null;
	
	/**
	 * 工作流参数类构造方法
	 */
	public Argument() {
		
	}
	
	/**
	 * 工作流参数类构造方法
	 * @param name 工作流参数名称
	 * @param value 工作流参数值
	 */
	public Argument(String name, Object value) {
		
		// 设置工作流参数名称
		this.name = name;
		// 设置工作流参数值
		this.value = value;
	}
	
	/**
	 * 取得工作流参数名称
	 * @return 工作流参数名称
	 */
	public String getName() {
		
		// 返回工作流参数名称
		return name;
	}
	
	/**
	 * 设置工作流参数名称
	 * @param name 工作流参数名称
	 */
	public void setName(String name) {
		
		// 设置工作流参数名称
		this.name = name;
	}
	
	/**
	 * 取得工作流参数值
	 * @return 工作流参数值
	 */
	public Object getValue() {
		
		// 返回工作流参数值
		return value;
	}
	
	/**
	 * 设置工作流参数值
	 * @param value 工作流参数值
	 */
	public void setValue(Object value) {
		
		// 设置工作流参数值
		this.value = value;
	}
}
