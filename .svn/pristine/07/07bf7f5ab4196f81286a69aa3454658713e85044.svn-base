package com.ht.common.util;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.apache.commons.lang3.StringUtils;

import com.ht.common.constant.CommonConstants;

/**
 * 系统文字资源绑定工具
 * @author wyw
 * @version 1.0 2014/08/30
 */
public class NlsResourceBundle {
	
	/** 文字资源绑定工具 */
	private ResourceBundle bundle = null;
	/** 父系统文字资源绑定工具 */
	private List<NlsResourceBundle> parents = null;
	
	/**
	 * 系统文字资源绑定工具构造方法
	 * @param resource 字符资源
	 * @param locale 所在区域
	 */
	private NlsResourceBundle(String resource, Locale locale) {
		
		try {
		
			// 取得文字资源绑定工具
			bundle = ResourceBundle.getBundle(resource, locale);
		}
		// 发生无法找到资源文件异常
		catch (MissingResourceException e) {
			
			// 打印错误日志
			LogHelper.ERROR.log(e.getMessage(), e);
		}
	}
	
	/**
	 * 取得文字资源绑定工具
	 * @param resource 资源包名
	 * @param locale 所在区域
	 * @return 文字资源绑定工具
	 */
	public static NlsResourceBundle getBundle(String resource, Locale locale) {
		
		// 取得文字资源绑定工具实例
		NlsResourceBundle bundle = new NlsResourceBundle(resource, locale);
		
		// 取得所有父字符资源对象
		String[] bundles = NlsResourceBundle.getParentNlsResourceBundle(bundle.getString(CommonConstants.PARENT_NLS_KEY));
		
		// 如果父字符资源对象存在
		if (bundles != null && bundles.length != 0) {
			
			// 声明字符资源绑定器数组
			List<NlsResourceBundle> parents = new ArrayList<NlsResourceBundle>();
			
			// 循环处理所有父字符资源
			for (int index = 0, nLength = bundles.length; index < nLength; index++) {
				
				// 设置父字符资源
				parents.add(new NlsResourceBundle(bundles[index], locale));
			}
			
			// 设置父字符资源
			bundle.setParents(parents);
		}
		
		// 返回文字资源绑定工具实例
		return bundle;
	}
	
	/**
	 * 取得文字资源绑定工具
	 * @param resource 资源包名
	 * @return 文字资源绑定工具
	 */
	public static NlsResourceBundle getBundle(String resource) {
		
		// 返回文字资源绑定工具
		return NlsResourceBundle.getBundle(resource, Locale.getDefault());
	}
	
	/**
	 * 取得字符资源
	 * @param key 字符资源主键
	 * @return 字符资源
	 */
	public String getString(String key) {
		
		// 声明字符资源
		String content = null;
		
		try {
			
			// 取得字符资源
			content = bundle.getString(key);
		}
		// 发生丢失资源异常
		catch (MissingResourceException e) {
			
			// 如果存在父字符资源对象
			if (parents != null && !parents.isEmpty()) {
				
				// 循环处理父资源对象
				for (NlsResourceBundle parent: parents) {
					
					try {
						
						// 从父字符资源中取得字符资源
						content = parent.getString(key);
						
						// 如果字符资源不为空
						if (StringUtils.isNotBlank(content)) {
							
							// 跳出循环
							break;
						}
					}
					// 发生丢失资源异常
					catch (MissingResourceException ex) {
						
						// 进入下一次循环
						continue;
					}
				}
			}
		}
		
		// 返回字符资源
		return content;
	}
	
	/**
	 * 取得字符资源
	 * @param key 字符资源主键
	 * @param args 格式化字符参数
	 * @return 字符资源
	 */
	public String getString(String key, Object... args) {
		// 返回字符资源
		return MessageFormat.format(getString(key), args);
	}
	
	/**
	 * 设置父系统文字资源绑定工具
	 * @param parents 父系统文字资源绑定工具
	 */
	public void setParents(List<NlsResourceBundle> parents) {
		
		// 设置父系统文字资源绑定工具
		this.parents = parents;
	}
	
	/**
	 * 取得所有字符资源
	 * @param bundles 字符资源字符串
	 * @return 字符资源数组
	 */
	private static String[] getParentNlsResourceBundle(String bundles) {
		
		// 用分号拆分所有字符资源
		return StringUtils.split(bundles, ";");
	}
	
	/**
	 * 取得所有字符资源
	 * @return 字符资源散列表
	 */
	public Map<String, String> getAllResources() {
		
		// 声明字符资源散列表
		Map<String, String> resources = new HashMap<String, String>();
		
		// 声明主键
		String key = null;
		
		// 循环处理所有主键
		for (Enumeration<String> enumeration = bundle.getKeys(); enumeration.hasMoreElements();) {
			
			// 取得主键
			key = enumeration.nextElement();
			// 添加字符资源
			resources.put(key, getString(key));
		}
		
		// 返回字符资源散列表
		return resources;
	}
	
	/**
	 * 返回区域信息
	 * @return 区域信息
	 */
	public Locale getLocale() {
		
		// 返回区域信息
		return bundle.getLocale();
	}
}
