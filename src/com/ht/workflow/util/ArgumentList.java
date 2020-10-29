package com.ht.workflow.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 工作流参数列表类
 * @author wyw
 * @version 1.0 2014/09/19
 */
public class ArgumentList {
	
	/** 工作流变量散列集 */
	private Map<String, Argument> argumentsMap = new HashMap<String, Argument>();
	
	/**
	 * 工作流参数列表类构造方法
	 */
	public ArgumentList() {
		
	}
	
	/**
	 * 添加参数
	 * @param name 参数名称
	 * @param value 参数值
	 */
	public void add(String name, Object value) {
		
		// 创建工作流参数实例
		Argument argument = new Argument(name, value);		
		// 添加参数
		argumentsMap.put(name, argument);
	}
	
	/**
	 * 取得工作流参数实例
	 * @param key 工作流参数名称
	 * @return 工作流参数值
	 */
	public Object getValue(String name) {
		
		// 声明工作流参数值
		Object value = null;
		
		// 取得工作流参数实例
		Argument argument = argumentsMap.get(name);
		
		// 如果工作流参数实例不为空
		if (argument != null) {
			
			// 设置工作流参数值
			value = argument.getValue();
		}
		
		// 返回工作流参数值
		return value;
	}
	
	/**
	 * 取得所有的参数名称
	 * @return
	 */
	public Iterator<String> getAllArgumentNames() {
		
		// 返回所有的参数名称
		return argumentsMap.keySet().iterator();
	}
	
	/**
	 * 判断工作流参数列表是否为空
	 * @return 判断结果（TRUE：工作流参数列表为空；FALSE：工作流参数列表不为空）
	 */
	public boolean isEmpty() {
		
		// 返回判断结果
		return argumentsMap.isEmpty();
	}
}
