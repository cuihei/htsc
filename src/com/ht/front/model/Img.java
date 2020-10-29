package com.ht.front.model;

import com.ht.front.css.CssClass;

public class Img extends Base{

	protected Img(String nodeId, String nodeName,CssClass cssClass) {
		super("img", nodeId, cssClass);
	}

	public static Img getInstance(String nodeId, String nodeName,CssClass cssClass){
		return new Img(nodeId,nodeName,cssClass);
	}
}
