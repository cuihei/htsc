package com.ht.front.model;

import java.util.List;
import java.util.Map;

import com.ht.front.css.CssClass;

/**
 * 表格对象
 * @author 王有为
 * @date 2016/10/13
 */
public class Table extends Base{

	/**
	 * 构造器
	 * @param id id属性
	 * @param type type属性
	 * @param name name属性
	 * @param value value属性
	 * @param css class属性
	 * @param placeholder placeholder属性
	 */
	public Table(String nodeType,String nodeId,CssClass cssClass) {
		super(nodeType, nodeId, cssClass);
	}

	/**
	 * 构建文本框
	 * @param id id属性
	 * @param Type type属性
	 * @param name name属性
	 * @param value value属性
	 * @param css class属性
	 * @param placeholder placeholder属性
	 * @return 文本框实例
	 */
	public static Table getInstance(String id,CssClass css){
		return new Table("table", id, css);
	}
	
	/**
	 * 获取一个默认表格
	 */
	public static Table getDefault(String id){
		CssClass css = new CssClass("table  table-striped");
		return new Table("table", id, css);
	}
	
	/**
	 * 创建表头
	 * @param table
	 * @param trList
	 * @return
	 */
	public static Base creatThead(Base table,List<String> trList){
		Base thead = new Base("thead", null, null); 
		table.addChildNode(thead);
		Base tr = new Base("tr", null, null); 
		thead.addChildNode(tr);
		for (int i = 0; i < trList.size(); i++)
		{
			Base th = new Base("th", null, null,trList.get(i)); 
			tr.addChildNode(th);
		}
		return table;
	}
	
	/**
	 * 创建表体
	 * @param table
	 * @param trList
	 * @return
	 */
	public static Base creatTBody(Base table,List<Map<String,Object>> list){
		Base thead = new Base("tbody", null, null); 
		table.addChildNode(thead);
		for (int i = 0; i < list.size(); i++)
		{
			Map<String,Object> mapTd = list.get(i);
			Base tr = new Base("tr", null, null); 
			thead.addChildNode(tr);
			for (Map.Entry<String,Object> entry : mapTd.entrySet()) {  
				Base th = new Base("td", null, null,entry.getValue() == null?"":entry.getValue().toString()); 
				tr.addChildNode(th);
		    }  
		}
		return table;
	}
}
