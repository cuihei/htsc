package com.ht.front.css;

/**
 * css样式类型
 * @author 王有为
 * @date 2016/10/13
 */
public class CssClass {
	/**
	 * css类型名称
	 */
	StringBuffer name = null;
	
	/**
	 * 最终形成的css类型
	 */
	StringBuffer cssClass = null;
	
	/**
	 * 构造css类型
	 * @param name css类型名称
	 */
	public CssClass(String name){
		this.name = new StringBuffer();
		this.name.append(name);
		this.cssClass = new StringBuffer();
		this.cssClass.append(" class='"+this.name+"'");
	}
	
	/**
	 * 增加css类型
	 * @param name css类型名称
	 */
	public void addCssClass(String name){
		if (this.name != null) {
			if (this.name.length()>0) {
				name = " " + name;
			}
		}
		this.name.append(name);
		this.cssClass = new StringBuffer();
		this.cssClass.append(" class='"+this.name+"'");
	}
	
	/**
	 * 返回css类型字符串
	 * @return css类型字符串
	 */
	public String getCssClass(){
		return this.cssClass.toString();
	}
}
