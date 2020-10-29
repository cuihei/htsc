package com.ht.workflow.form.delegate;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.ht.common.util.ConfigLookupHelper;
import com.ht.common.util.EnvironmentUtil;

/**
 * 流程任务表单属性默认值委托类
 * @author wangyouwei
 * @2017/1/7
 */
public class FormDefaultValueDelegate
{
	/**
	 * 参数
	 */
	Map<String, Object> params = new HashMap<String, Object>();

	String delegateName = null;

	/**
	 * @param delegateName
	 * @return
	 */
	public FormDefaultValueDelegate(String delegateName)
	{
		this.delegateName = delegateName;
		// 读取xml文件
		String path = "/com/ht/workflow/config/form-delegate-configuration.cfg.xml";
		ConfigLookupHelper helper = ConfigLookupHelper.getInstance(EnvironmentUtil.getResourceUrl(path));
		String pathTable = "configuration/delegates/delegate/functions/function[@name='" + delegateName + "']/params/param";
		List<Map<String, String>> list = (List<Map<String, String>>) helper.getAttributesValue(pathTable);
		if (list != null)
		{
			for (int i = 0; i < list.size(); i++)
			{
				Map<String, String> param = list.get(i);
				String key = param.get("key");
				String value = param.get("value");
				if (StringUtils.isNotEmpty(value))
				{
					this.params.put(key, value);
				}
			}
		}
	}

	/**
	 * 获取参数
	 * @return
	 */
	public Map<String, Object> getParams()
	{
		return this.params;
	}

	/**
	 * 设置参数
	 * @return
	 */
	public void setParams(Map<String, Object> param)
	{
		this.params = param;
	}

	/**
	 * 执行委托
	 * @return
	 * @throws ClassNotFoundException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 */
	public Object excute() throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException,
			IllegalArgumentException, InvocationTargetException
	{
		String className = "com.ht.workflow.form.delegate.Delegates";
		Class<?> clazz = Class.forName(className);
		Object object = clazz.newInstance();
		Class[] classes = new Class[this.params.size()];
		Object[] paramValues = new Object[this.params.size()];
		int index = -1;
		for (String key : this.params.keySet())
		{
			index = index + 1;
			classes[index] = key.getClass();
			paramValues[index] = this.params.get(key);
		}
		Method method = clazz.getMethod(this.delegateName, classes);
		return method.invoke(object, paramValues);
	}
}
