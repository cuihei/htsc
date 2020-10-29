package com.ht.common.util;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Bean工具类 只针对于String 和 Boolean
 * @date 2015/11/06
 * @author wangyouwei
 * @version 1.0
 */
public class BeanUtil {
	
	/** 静态实例*/
	public static BeanUtil _beanUtil = null;
	
	/** 构造器*/
	public BeanUtil()
	{
		
	}
	
	/** 获得实例*/
	public static BeanUtil getInstance()
	{
		if (_beanUtil==null) {
			_beanUtil = new BeanUtil();
		}
		return _beanUtil;
	}
	
	/**
	 * 获取类属性集合
	 * @param <T>
	 * @param clazz 类型
	 * @return 类属性集合
	 */
	public <T> Map<String,Object> getProperty(T clazz,Boolean containNullValue)
	{
		// 声明属性描述器
		PropertyDescriptor[] props = null;
        try {
        	// 实例属性描述器
            props = Introspector.getBeanInfo(clazz.getClass(), Object.class).getPropertyDescriptors();
        } 
        catch (IntrospectionException e) 
        {
        	e.printStackTrace();
        }
        // 声明属性结果集合
        Map<String,Object> result = null;
		if (props != null) {
			result = new HashMap<String, Object>();
			for (int i = 0; i < props.length; i++) {
				try{
					// 获取类中的属性
					String proName = props[i].getName();
					// 当前属性方法
					Method method = props[i].getReadMethod();
					// 执行当前属性的关联方法获取值
					if (method!=null) {
						Object proValue = method.invoke(clazz, new Object[]{});
						if (containNullValue)
						{
							result.put(proName, proValue);
						}
						else{
							if(proValue!=null)
							{
								result.put(proName, proValue);
							}
						}
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}
	
	/**
	 * 获取类属性集合
	 * @param <T>
	 * @param clazz 类型
	 * @return 类属性集合
	 */
	public <T> Map<String,Object> getProperty(T clazz,String prop)
	{
		// 声明属性描述器
		PropertyDescriptor[] props = null;
        try {
        	// 实例属性描述器
            props = Introspector.getBeanInfo(clazz.getClass(), Object.class).getPropertyDescriptors();
        } 
        catch (IntrospectionException e) 
        {
        	e.printStackTrace();
        }
        // 声明属性结果集合
        Map<String,Object> result = null;
		if (props != null) {
			result = new HashMap<String, Object>();
			for (int i = 0; i < props.length; i++) {
				try{
					// 获取类中的属性
					String proName = props[i].getName();
					if (proName.equals(prop)) {
						// 当前属性方法
						Method method = props[i].getReadMethod();
						// 执行当前属性的关联方法获取值
						if (method!=null) {
							Object proValue = method.invoke(clazz, new Object[]{});
							if(proValue!=null)
							{
								result.put(proName, proValue);
							}
						}
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}
	
	/**
	 * 给类属性设置值 只针对于String 和 Boolean
	 * @param attrMap 属性列表
	 * @param clazz 类
	 * @return
	 */
	public <T> T setProperty(Map<String,Object> attrMap,T clazz)
	{
		// 声明属性描述器
		PropertyDescriptor[] props = null;
		try {
			// 实例属性描述器
			props = Introspector.getBeanInfo(clazz.getClass(), Object.class)
					.getPropertyDescriptors();
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
		if (props != null) {
			for (int i = 0; i < props.length; i++) {
				try {
					// 获取类中的属性
					String proName = props[i].getName();
					Class<?> proType = props[i].getPropertyType();
					// 过滤掉除String 和 Boolean 的其他类型
					if (proType.getSimpleName().equals("String")||proType.getSimpleName().equals("Boolean")) {
						Object obj = null;
						if (proType.getSimpleName().equals("Boolean")) {
							obj = Boolean.parseBoolean((String) attrMap.get(proName));
						}
						else {
							obj = attrMap.get(proName);
						}
						// 当前属性方法
						Method method = props[i].getWriteMethod();
						// 执行当前属性的关联方法设置值
						if (method!=null) {
							method.invoke(clazz,new Object[] {obj});
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return clazz;
	}
}
