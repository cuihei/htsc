package com.ht.front.model;

import com.ht.front.css.CssClass;

/**
 * 文件选择组件
 * @author 王有为
 * @date 2016/10/13
 */
public class File extends Base{

	protected File(String nodeType,String nodeId,CssClass cssClass) {
		super(nodeType,nodeId,null,null,cssClass,null);
	}
	
	/**
	 * 获取文件组件实例
	 * @param nodeId 组件ID
	 * @param cssClass 组件样式
	 */
	public static File getInstance(String nodeId,CssClass cssClass){
		return new File("file",nodeId,cssClass);
	}
	
	/**
	 * 获取默认文件组件实例
	 * @param nodeId 组件ID
	 * @return 文件组件
	 */
	public static File getDefault(String nodeId){
		CssClass cssClass = new CssClass("file");
		return new File("file",nodeId,cssClass);
	}
}
