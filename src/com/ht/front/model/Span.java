package com.ht.front.model;

import com.ht.front.css.CssClass;

/**
 * Span对象
 * @author 王有为
 * @date 2016/10/13
 */
public class Span extends Base{

	protected Span(String nodeType, String nodeId, CssClass cssClass,String content) {
		super(nodeType, nodeId, cssClass,content);
	}

	public static Span getInstance(CssClass cssClass,String content){
		return new Span("span",null,cssClass,content);
	}
	
	public static Span getInstance(CssClass cssClass,String content,String spanId){
		return new Span("span",spanId,cssClass,content);
	}
	
	public static Span getDefault(String content){
		CssClass css = new CssClass("input-group-addon");
		return getInstance(css, content);
	};
	
	public static Span getSpanWithI(String content,I i,String spanId){
		CssClass css = new CssClass("input-group-addon");
		Span span = getInstance(css, content,spanId);
		span.addChildNode(i);
		return span;
	};
	
	public static Span getDefault(){
		return new Span("span",null,null,null);
	};
}
