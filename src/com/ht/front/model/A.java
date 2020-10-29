package com.ht.front.model;


public class A extends Base{
	/**
	 * 构造器 引用父类方法 初始化按钮
	 * @param content 内容
	 * @param href 连接地址
	 */
	protected A(String href,String content) {
		super(href,content);
	}
	
	/**
	 * 构建A
	 * @param href a 连接地址
	 * @param content a 内容
	 * @return A
	 */
	public static A getInstance(String href,String content){
		return new A(href,content);
	}
}
