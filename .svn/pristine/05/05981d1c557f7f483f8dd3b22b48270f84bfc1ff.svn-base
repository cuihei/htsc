package com.ht.common.util;

import java.beans.BeanInfo;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 系统Web数据转换器类
 * @author wyw
 * @version 1.0 2015/07/05
 */
public class DataConverter {
	
	/**
	 * 将对象转换为JSON数据
	 * @param object 需要转换的对象
	 * @return JSON数据
	 */
	public static String convertObject2Json(Object object) {
		
		// 声明JSON数据
		String jsonData = null;
		
		try {
		
			// 创建对象映射器
			ObjectMapper objectMapper = new ObjectMapper();
			// 将数据对象转换为JSON字符串
			jsonData = objectMapper.writeValueAsString(object);
		}
		// 发生异常
		catch (Exception e) {
			
			// 输出错误日志
			LogHelper.ERROR.log(e.getMessage(), e);
		}
		
		// 返回JSON数据
		return jsonData;
	}
	
	/**
	 * 将JSON数据转换为对象
	 * @param jsonData JSON数据
	 * @param clazz 需要转换的对象类型
	 * @return 转换完成的对象
	 */
	public static Object convertJson2Object(String jsonData, Class<?> clazz) {
		
		// 声明对象
		Object object = null;
		
		// 如果JSON数据不为空
		if (StringUtils.isNotBlank(jsonData)) {
		
			try {
				
				// 创建象映射器
				ObjectMapper objectMapper = new ObjectMapper();
				// 读取JSON字符串
				object = objectMapper.readValue(jsonData, clazz);
			}
			// 发生异常
			catch (Exception e) {
				
				// 输出错误日志
				LogHelper.ERROR.log(e.getMessage(), e);
			}
		}
		
		// 返回对象
		return object;
	}
	
	/**
	 * 将JSON数据转换为对象
	 * @param jsonData JSON数据
	 * @param clazz 需要转换的对象类型
	 * @return 转换完成的对象
	 */
	public static Object convertJson2List(String jsonData, Class<?> clazz) {
		JavaType javaType = getCollectionType(ArrayList.class, clazz); 
		// 声明对象
		Object object = null;
		// 如果JSON数据不为空
		if (StringUtils.isNotBlank(jsonData)) {
			try {
				// 创建象映射器
				ObjectMapper objectMapper = new ObjectMapper();
				// 读取JSON字符串
				object = objectMapper.readValue(jsonData, javaType);
			}
			// 发生异常
			catch (Exception e) {
				// 输出错误日志
				e.printStackTrace();
			}
		}
		// 返回对象
		return object;
	}
	
	/**
	 * Map转为JavaBean
	 * @param map
	 * @param obj
	 */
    public static Object convertMap2Bean(Map<String, Object> map, Object obj) {  
        try { 
        	// 获得javabean操作类
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());  
            // 获得属性描述器
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();  
            // 循环javabean 所有属性
            for (PropertyDescriptor property : propertyDescriptors) {  
                // 获取当前属性值
            	String key = property.getName();  
            	//  如果map中存在当前属性
                if (map.containsKey(key)) {  
                    // 获取map value值
                	Object value = map.get(key); 
                	// 得到property对应的setter方法  
                    Method setter = property.getWriteMethod();  
                    // 设置javabean当前属性值
                    setter.invoke(obj, value);  
                }
            }
        } catch (Exception e) {  
        	// 打印错误日志
            LogHelper.FATAL.log(e.getMessage());
        }
        // 返回javabean
        return obj;
    }
	
	public static JavaType getCollectionType(Class<?> collectionClass,
			Class<?>... elementClasses) {
		// 创建象映射器
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.getTypeFactory().constructParametricType(collectionClass,
				elementClasses);
	}
	
	/**
	 * InputStream转String
	 * @param is
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public static String convertStream2String(InputStream is) throws UnsupportedEncodingException {

		BufferedReader reader = new BufferedReader(new InputStreamReader(is,"UTF-8"));

		StringBuilder sb = new StringBuilder();
		String line = null;

		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		return sb.toString();
	}  
	
	public static Map<String,Object> convertBean2Map(Object obj){
		Map<String, Object> map = new HashMap<String, Object>();  
        // System.out.println(obj.getClass());  
        // 获取f对象对应类中的所有属性域  
       Field[] fields = obj.getClass().getDeclaredFields();  
        for (int i = 0, len = fields.length; i < len; i++) {  
            String varName = fields[i].getName();  
            try {  
                // 获取原来的访问控制权限  
                boolean accessFlag = fields[i].isAccessible();  
                // 修改访问控制权限  
                fields[i].setAccessible(true);  
                // 获取在对象f中属性fields[i]对应的对象中的变量  
                Object o = fields[i].get(obj);  
               
                    map.put(varName, o);  
                // System.out.println("传入的对象中包含一个如下的变量：" + varName + " = " + o);  
                // 恢复访问控制权限  
                fields[i].setAccessible(accessFlag);  
            } catch (IllegalArgumentException ex) {  
                ex.printStackTrace();  
            } catch (IllegalAccessException ex) {  
                ex.printStackTrace();  
            }  
        }  
        return map;  
	}
}
