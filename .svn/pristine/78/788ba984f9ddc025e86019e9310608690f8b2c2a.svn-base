package com.ht.common.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import com.ht.common.constant.EnvironmentConstants;

/**
 * 系统配置查询助手类
 * @author wyw
 * @version 1.0 2014/08/31
 */
public class ConfigLookupHelper {
	
	/** 配置查询助手类实例 */
	private static HashMap<URL, ConfigLookupHelper> helpers = new HashMap<URL, ConfigLookupHelper>();
	/** XML文档读取器 */
	private SAXReader reader = null;
	/** XML文档实例 */
	private Document document = null;
	
	/**
	 * 配置查询助手类构造方法
	 * @param url 配置文件URL
	 */
	private ConfigLookupHelper(URL url) {
		
		try {
			// 创建XML文档读取器
			reader = new SAXReader();
			// 设置编码
			reader.setEncoding(EnvironmentConstants.DEFAULT_CHAR_CODEC);
			// 加载服务器配置文件
			document = reader.read(url);
		}
		// 发生异常
		catch (DocumentException e) {
			
			// 打印错误日志
			LogHelper.ERROR.log(e.getMessage(), e);
		}
	}
	
	/**
	 * 配置查询助手类构造方法
	 * @param url 配置文件URL
	 */
	private ConfigLookupHelper(String xml) {
		try {
			// 创建XML文档读取器
			reader = new SAXReader();
			// 设置编码
			reader.setEncoding(EnvironmentConstants.DEFAULT_CHAR_CODEC);
			// 加载服务器配置文件
			document = reader.read(new StringReader(xml));
		}
		// 发生异常
		catch (DocumentException e) {
			
			// 打印错误日志
			LogHelper.ERROR.log(e.getMessage(), e);
		}
	}
	
	/**
	 * 取得配置查询助手类实例
	 *  @param url 配置文件URL
	 * @return 配置查询助手类实例
	 */
	public static ConfigLookupHelper getInstance(URL url) {
		
		// 取得配置查询助手类
		ConfigLookupHelper helper = helpers.get(url);
		
		// 如果配置查询助手类实例为空
		if (helper == null) {
			
			// 创建配置查询助手类实例
			helper = new ConfigLookupHelper(url);
			// 将配置查询助手类添加到散列表中
			helpers.put(url, helper);
		}
		
		// 返回配置查询助手类实例
		return helper;
	}
	
	/**
	 * 取得配置查询助手类实例
	 *  @param url 配置文件URL
	 * @return 配置查询助手类实例
	 */
	public static ConfigLookupHelper getInstance(String xml) {
	
		// 创建配置查询助手类实例
		ConfigLookupHelper helper = new ConfigLookupHelper(xml);
		
		// 返回配置查询助手类实例
		return helper;
	}
	
	/**
	 * 根据XPATH取得配置文件值的列表
	 * @param xPath DOM文件XPATH
	 * @return 配置文件值的列表
	 */
	public List<String> getValues(String xPath) {
		
		// 声明配置文件值的列表
		List<String> values = new ArrayList<String>();
		
		// 取得所有的节点列表
		@SuppressWarnings("rawtypes")
		List nodes = document.selectNodes(xPath);
		
		// 如果节点列表不为空
		if (nodes != null && !nodes.isEmpty()) {
			
			// 循环处理所有节点
			for (int i = 0, len = nodes.size(); i < len; i++) {
				
				// 添加节点值到值列表中
				values.add(((Element) nodes.get(i)).getStringValue());
			}
		}
		
		// 返回配置文件值的列表
		return values;
	}
	
	/**
	 * 根据XPATH取得配置文件的值
	 * @param xPath DOM文件XPATH
	 * @return 配置文件的值
	 */
	public String getValue(String xPath) {
		
		// 声明配置文件的值
		String value = null;
		
		// 取得节点
		Node node = document.selectSingleNode(xPath);
		
		// 如果节点不为空
		if (node != null) {
			
			// 设置配置文件的值
			value = ((Element) node).getStringValue();
		}
		
		// 返回配置文件的值
		return value;
	}
	
	/**
	 * 根据XPATH取得配置文件属性值的列表
	 * @param xPath DOM文件XPATH
	 * @param attributeName 属性名称
	 * @return 配置文件属性值的列表
	 */
	@SuppressWarnings("rawtypes")
	public List<String> getAttributeValues(String xPath, String attributeName) {
		
		// 声明配置文件值的列表
		List<String> values = new ArrayList<String>();
		
		// 取得所有的节点列表
		List nodes = document.selectNodes(xPath);
		
		// 如果节点列表不为空
		if (nodes != null && !nodes.isEmpty()) {
			
			// 循环处理所有节点
			for (int i = 0, len = nodes.size(); i < len; i++) {
				
				// 添加节点值到值列表中
				values.add(((Element) nodes.get(i)).attributeValue(attributeName));
			}
		}
		
		// 返回配置文件属性值的列表
		return values;
	}
	
	/**
	 * 根据XPATH取得配置文件的属性值
	 * @param xPath DOM文件XPATH
	 * @param attributeName 属性名称
	 * @return 配置文件的属性值
	 */
	public String getAttributeValue(String xPath, String attributeName) {
		
		// 声明配置文件的属性值
		String value = null;
		
		// 取得节点
		Node node = document.selectSingleNode(xPath);
		
		// 如果节点不为空
		if (node != null) {
			
			// 设置配置文件的值
			value = ((Element) node).attributeValue(attributeName);
		}
		
		// 返回配置文件的属性值
		return value;
	}
	
	/**
	 * 取得属性值列表
	 * @param xPath DMO文件XPATH
	 * @return 属性值列表
	 */
	@SuppressWarnings("rawtypes")
	public List<? extends Map<String, String>> getAttributesValue(String xPath) {
		
		// 声明属性值列表
		List<Map<String, String>> attributes = new ArrayList<Map<String,String>>();
		// 声明属性值
		Map<String, String> attribute = null;
		
		// 取得所有的节点列表
		List nodes = document.selectNodes(xPath);
		
		// 如果节点列表不为空
		if (nodes != null && !nodes.isEmpty()) {
			
			// 声明节点对象
			Element element = null;
			// 剩名属性对象
			Attribute _attribute = null;
			
			// 循环处理所有节点
			for (int i = 0, len = nodes.size(); i < len; i++) {
				
				// 创建属性值
				attribute = new HashMap<String, String>();
				
				// 取得节点对象
				element = (Element) nodes.get(i);
				
				// 循环处理所有属性
				for (int j = 0, count = element.attributeCount(); j < count; j++) {
					
					// 取得属性对象
					_attribute = element.attribute(j);
					// 降属性信息添加到散列表中
					attribute.put(_attribute.getName(), _attribute.getValue());
				}
				
				// 降属性值添加到列表中
				attributes.add(attribute);
			}
		}
		
		// 返回属性值列表
		return attributes;
	}
	
	/**
	 * 创建/查找根节点
	 * @param elementName 根节点名称
	 */
	public Element createRootElement(String rootElementName) {
		if (document.getRootElement()!=null) {
			return document.getRootElement();
		}
		return document.addElement(rootElementName);
	}
	
	/**
	 * 在指定节点下创建一个新的节点
	 * @param element 操作节点
	 * @param elementName 新节点名称
	 * @return 节点对象
	 */
	public Element createElement(Element element,String elementName)
	{
		return element.addElement(elementName);
	}
	
	/**
	 * 
	 * @param element 指定节点
	 * @param attrMap 属性列表
	 * @return
	 */
	public Element setAttributes(Element element,Map<String, Object> attrMap)
	{
		Iterator<?> it = attrMap.entrySet().iterator();
		while(it.hasNext()){
			@SuppressWarnings("unchecked")
			Entry<String, Object> entry = (Entry<String, Object>)it.next();
			element.addAttribute(entry.getKey(),entry.getValue().toString());
		}
		return element;
	}
	
	/**
	 * 
	 * @param element 指定节点
	 * @param attrName 属性名称
	 * @param attrValue 属性值
	 * @return
	 */
	public Element setAttribute(Element element,String attrName,String attrValue)
	{
		element.addAttribute(attrName,attrValue);
		return element;
	}
	
	/**
	 * 设置值
	 * @param element
	 * @param value
	 * @return
	 */
	public Element setValue(Element element,String value){
		if (value!=null) {
			return element.addText(value);
		}
		return element;
	}
	
	/**
	 * 获取根节点
	 * @return
	 */
	public Element getRootElement(){
		return document.getRootElement();
	}
	
	/**
	 * 保存xml
	 * @throws IOException
	 */
	public void save(String path,String EnCode) throws IOException
	{
		OutputFormat format = OutputFormat.createPrettyPrint();  
		// 根据需要设置编码  
        format.setEncoding(EnCode);
        XMLWriter writer = new XMLWriter(new FileWriter(new File(path)), format);    
        document.normalize();
        writer.write(document);    
        writer.close(); 
	}
	
	public Document getDocument(){
		return document;
	}
}
