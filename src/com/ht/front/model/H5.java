package com.ht.front.model;

import java.util.ArrayList;
import java.util.List;

import com.ht.front.css.CssClass;

/**
 * I标签对象
 * @author 王有为
 * @date 2016/10/14
 */
public class H5 extends Base{

	/**
	 * 构造器 获得一个I标签实例
	 * @param cssClass 样式
	 */
	protected H5(CssClass cssClass) {
		super("h5", null, cssClass);
	}
	
	/**
	 * 获取I标签实例
	 * @param cssClass 样式
	 * @return I标签实例
	 */
	public static H5 getInstance(CssClass cssClass){
		return new H5(cssClass);
	}
	

	/**
	 * 获取I标签实例
	 * @param cssClass 样式
	 * @return I标签实例
	 */
	public static Base getH4(String content){
		return new Base("h4", null, null, content);
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
