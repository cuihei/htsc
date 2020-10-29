package com.ht.front.model;

import java.util.ArrayList;
import java.util.List;

import com.ht.front.css.CssClass;

/**
 * P标签对象
 * @author 王有为
 * @date 2016/10/14
 */
public class P extends Base{

	/**
	 * 构造器 获得一个P标签实例
	 * @param cssClass 样式
	 */
	protected P(CssClass cssClass) {
		super("p", null, cssClass);
	}
	
	/**
	 * 获取I标签实例
	 * @param cssClass 样式
	 * @return I标签实例
	 */
	public static P getInstance(CssClass cssClass){
		return new P(cssClass);
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
