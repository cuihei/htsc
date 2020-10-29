package com.ht.front.css;

/**
 * Html对象的属性对象
 * @author 王有为
 * @date 2014/10/14
 */
public class Prop {
	
	public Prop(){
		
	}
	
	public Prop(String propKey,String propValue){
		this.propKey = propKey;
		this.propValue = propValue;
	}
	
	/**
	 * 属性键
	 */
	String propKey = null;
	
	/**
	 * 属性值
	 */
	String propValue =null;

	public String getPropKey() {
		return propKey;
	}

	public void setPropKey(String propKey) {
		this.propKey = propKey;
	}

	public String getPropValue() {
		return propValue;
	}

	public void setPropValue(String propValue) {
		this.propValue = propValue;
	}
}
