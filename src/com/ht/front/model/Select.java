package com.ht.front.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.ht.front.css.CssClass;
import com.ht.front.css.Prop;
import com.ht.common.util.BeanUtil;
import com.ht.persistence.model.base.BaseModel;

/**
 * 选择框对象
 * @author 王有为
 * @date 2016/10/14
 */
public class Select extends Base{

	/**
	 * 构造器
	 * @param nodeType 节点类型
	 * @param nodeId 节点ID
	 * @param cssClass 节点样式
	 */
	protected Select(String nodeType, String nodeId, CssClass cssClass) {
		super(nodeType, nodeId, cssClass);
	}
	
	/**
	 * 获取选择框实例
	 * @param nodeId 选择框ID
	 * @param css 选择框样式
	 * @return 选择框实例
	 */
	public static Select getInstance(String nodeId,CssClass css){
		return new Select("select",nodeId,css);
	}
	
	/**
	 * 获取默认选择框
	 * @param nodeId 选择框ID
	 * @return 选择框实例
	 */
	public static Select getDefault(String nodeId){
		CssClass css = new CssClass("form-control");
		return getInstance(nodeId,css);
	}
	
	/**
	 * 获取默认选择框
	 * @param nodeId 选择框ID
	 * @param modelList 实体集合
	 * @param value 选择框绑定key的属性
	 * @param content 选择框显示值的属性
	 * @return 选择框实例
	 */
	public static Select getDefaultWithOption(String nodeId,List<? extends BaseModel> modelList,String value,String content){
		CssClass css = new CssClass("form-control");
		Select select = getInstance(nodeId,css);
		BeanUtil util = BeanUtil.getInstance();
		Base option = new Base("option",null,null,null,"--请选择--");
		select.addChildNode(option);
		if (modelList != null) {
			for (int i = 0; i < modelList.size(); i++) {
				Map<String,Object> object = util.getProperty(modelList.get(i),value);
				String optionValue = null;
				if (object.get(value) != null) {
					optionValue = object.get(value).toString();
				}
				object = util.getProperty(modelList.get(i),content);
				String optionContent = null;
				if (object.get(content) != null) {
					optionContent = object.get(content).toString();
				}
				option = new Base("option",null,null,optionValue,optionContent);
				select.addChildNode(option);
			}
		}
		return select;
	}
	
	/**
	 * 获取默认选择框
	 * @param nodeId 选择框ID
	 * @param modelList 实体集合
	 * @param value 选择框绑定key的属性
	 * @param content 选择框显示值的属性
	 * @param selectedValue 当前绑定key属性值
	 * @return 选择框实例
	 */
	public static Select getDefaultWithOption(String nodeId,List<? extends BaseModel> modelList,String value,String content,String selectedValue){
		CssClass css = new CssClass("form-control");
		Select select = getInstance(nodeId,css);
		BeanUtil util = BeanUtil.getInstance();
		List<Base> options = new ArrayList<Base>();
		Base first = null;
		if(null==selectedValue){
			Base fistoption = new Base("option",null,null,null,"--请选择--");
			select.addChildNode(fistoption);
		}
		if (modelList != null) {
			for (int i = 0; i < modelList.size(); i++) {
				Map<String,Object> object = util.getProperty(modelList.get(i),value);
				String optionValue = null;
				if (object.get(value) != null) {
					optionValue = object.get(value).toString();
				}
				object = util.getProperty(modelList.get(i),content);
				String optionContent = null;
				if (object.get(content) != null) {
					optionContent = object.get(content).toString();
				}
				if (selectedValue!=null && selectedValue.equals(optionValue)) {
					Base option = new Base("option",null,null,optionValue,optionContent);
					first = option;
				}
				else{
					Base option = new Base("option",null,null,optionValue,optionContent);
					options.add(option);
				}
			}
		}
		select.addChildNode(first);
		for (int i = 0; i < options.size(); i++) {
			select.addChildNode(options.get(i));
		}
		return select;
	}
	
	/**
	 * 获取默认选择框
	 * @param nodeId 选择框ID
	 * @param modelList 实体集合
	 * @param value 选择框绑定key的属性
	 * @param content 选择框显示值的属性
	 * @param selectedValue 当前绑定key属性值
	 * @param isBlank 是否有初始值
	 * @return 选择框实例
	 */
	public static Select getDefaultWithOption(String nodeId,List<? extends BaseModel> modelList,String value,String content,String selectedValue,Boolean isBlank){
		CssClass css = new CssClass("form-control");
		Select select = getInstance(nodeId,css);
		BeanUtil util = BeanUtil.getInstance();
		List<Base> options = new ArrayList<Base>();
		Base first = null;
		if (modelList != null) {
			for (int i = 0; i < modelList.size(); i++) {
				Map<String,Object> object = util.getProperty(modelList.get(i),value);
				String optionValue = null;
				if (object.get(value) != null) {
					optionValue = object.get(value).toString();
				}
				object = util.getProperty(modelList.get(i),content);
				String optionContent = null;
				if (object.get(content) != null) {
					optionContent = object.get(content).toString();
				}
				if (selectedValue!=null && selectedValue.equals(optionValue)) {
					Base option = new Base("option",null,null,optionValue,optionContent);
					first = option;
				}
				else{
					Base option = new Base("option",null,null,optionValue,optionContent);
					options.add(option);
				}
			}
		}
		select.addChildNode(first);
		if(!isBlank){
			Base fistoption = new Base("option",null,null,null,"--请选择--");
			select.addChildNode(fistoption);
		}
		for (int i = 0; i < options.size(); i++) {
			select.addChildNode(options.get(i));
		}
		return select;
	}
	
	/**
	 * 获取默认选择框
	 * @param nodeId 选择框ID
	 * @param modelList 实体集合
	 * @param value 选择框绑定key的属性
	 * @param content 选择框显示值的属性
	 * @param isBlank 初始化是否为空
	 * @return 选择框实例
	 */
	public static Select getDefaultWithOption(String nodeId,List<? extends BaseModel> modelList,String value,String content,Boolean isBlank){
		CssClass css = new CssClass("form-control");
		Select select = getInstance(nodeId,css);
		BeanUtil util = BeanUtil.getInstance();
		if (!isBlank) {
			Base option = new Base("option",null,null,null,"--请选择--");
			select.addChildNode(option);
		}
		if (modelList != null) {
			for (int i = 0; i < modelList.size(); i++) {
				Map<String,Object> object = util.getProperty(modelList.get(i),value);
				String optionValue = null;
				if (object.get(value) != null) {
					optionValue = object.get(value).toString();
				}
				object = util.getProperty(modelList.get(i),content);
				String optionContent = null;
				if (object.get(content) != null) {
					optionContent = object.get(content).toString();
				}
				Base option = new Base("option",null,null,optionValue,optionContent);
				select.addChildNode(option);
			}
		}
		return select;
	}
	
	/**
	 * 获取带默认值的默认选择框
	 * @param nodeId 选择框ID
	 * @param dataList 数据集合
	 * @param value 选择框绑定key的属性
	 * @param content 选择框显示值的属性
	 * @param isBlank 初始化是否为空
	 * @return 选择框实例
	 */
	public static Select getSelectWithDefaultOption(String nodeId,List<Map<String,String>> dataList,String value,String content,Boolean isBlank){
		CssClass css = new CssClass("form-control");
		Select select = getInstance(nodeId,css);
		BeanUtil util = BeanUtil.getInstance();
		if (!isBlank) {
			Base option = new Base("option",null,null,null,"--请选择--");
			Prop prop = new Prop();
			prop.setPropKey("value");
			prop.setPropValue("");
			option.addProp(prop);
			select.addChildNode(option);
		}
		if (dataList != null) {
			for (Iterator iterator = dataList.iterator(); iterator.hasNext();) {
				Map<String,String> map = (Map<String,String>) iterator.next();
				String optionValue = map.get(value).toString();
				String optionContent = map.get(content).toString();;
				Base option = new Base("option",null,null,optionValue,optionContent);
				select.addChildNode(option);
			}
		}
		return select;
	}
}
