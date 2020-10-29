package com.ht.front.model;

import com.ht.front.css.Prop;

/**
 * 列模板
 * @author Administrator
 * @date 2016/10/15
 */
public class Script extends Base{

	/**
	 * 构造器
	 * @param nodeType
	 * @param nodeId
	 * @param cssClass
	 */
	protected Script(String nodeId) {
		super("script", nodeId, null);
	}
	/**
	 * 构造器
	 * @param nodeType
	 * @param nodeId
	 * @param cssClass
	 */
	protected Script(String nodeId,String content) {
		super("script",nodeId,null,content);
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	public static Script getInstance(String id){
		Script script =  new Script(id);
		Prop prop = new Prop();
		prop.setPropKey("type");
		prop.setPropValue("text/x-kendo-template");		
		script.addProp(prop);
		return script;
	}
	/**
	 * 
	 * @param id
	 * @return
	 */
	public static Script getInstance(String id,String content){
		Script script =  new Script(id,content);
		return script;
	}
}
