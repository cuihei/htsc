package com.ht.front.model;

import java.util.ArrayList;
import java.util.List;

import com.ht.front.css.CssClass;

/**
 * font标签对象
 * @author 王有为
 * @date 2016/10/14
 */
public class Font extends Base{

	/**
	 * 构造器 获得一个font标签实例
	 * @param cssClass 样式
	 */
	protected Font(CssClass cssClass) {
		super("font", null, cssClass);
	}
	
	/**
	 * 获取font标签实例
	 * @param cssClass 样式
	 * @return font标签实例
	 */
	public static Font getInstance(CssClass cssClass){
		return new Font(cssClass);
	}
	
	/**
	 * 子节点集合
	 */
	List<String> childNodes = new ArrayList<String>();
	
	/**
	 * 增加子节点
	 * @param node
	 */
	public void addChildNode(String node){
		if (node != null) {
			this.childNodes.add(node);
		}
	}
	
	
}
