package com.ht.workflow.util;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.lang3.StringUtils;

import com.ht.common.util.ConfigLookupHelper;
import com.ht.common.util.EnvironmentUtil;
import com.ht.common.util.LogHelper;
import com.ht.workflow.common.IWorkflowEngine;
import com.ht.workflow.constants.WorkflowConstants;
import com.ht.workflow.exception.WorkflowException;

/**
 * BizGo系统工作流引擎工厂类
 * @author wyw
 * @version 1.0 2014/09/11
 */
public class WorkflowEngineFactory {
	
	/** 工作流引擎工厂实例 */
	private static WorkflowEngineFactory factory = null;
	/** 工作流引擎类型 */
	private String engineType = null;
	/** 工作流引擎 */
	private IWorkflowEngine engine = null;
	
	/**
	 * 工作流引擎工厂类构造方法
	 */
	private WorkflowEngineFactory() {
		
		// 创建配置查询助手类实例
		ConfigLookupHelper helper = ConfigLookupHelper.getInstance(EnvironmentUtil.getResourceUrl(WorkflowEngineFactory.class, WorkflowConstants.WORKFLOW_CONFIG_FILE));
		// 取得工作流引擎类型
		engineType = helper.getValue(WorkflowConstants.XPATH_ENGINE_TYPE);
		// 取得工作流引擎实现类名称
		String className = helper.getAttributeValue(StringUtils.replace(WorkflowConstants.XPATH_WORKFLOW_ENGINE, "{0}", engineType), WorkflowConstants.ATTR_NAME_CLASS);
		
		// 如果实现类名称不为空
		if (StringUtils.isNotBlank(className)) {
			try {
				// 注册工作流引擎实现类
				Class<?> clazz = Class.forName(className);
				// 创建会话管理器实例
				Object object = clazz.newInstance();
				// 设置绘画势力管理器实例
				engine = (IWorkflowEngine) object;
			}
			// 发生异常
			catch (Exception e) {
				
				// 如果异常是目标异常
				if (e instanceof InvocationTargetException) {
					
					// 将异常转换为目标异常
					InvocationTargetException ite = (InvocationTargetException) e;
					// 输出错误日志
					LogHelper.ERROR.log(ite.getMessage(), e);
				}
				// 如果异常是其他异常
				else {
				
					// 输出错误日志
					LogHelper.ERROR.log(e.getMessage(), e);
				}
			}
		}
	}
	
	/**
	 * 取得工作流引擎工厂类
	 */
	public static WorkflowEngineFactory getInstance() {
		
		// 如果工作流引擎工厂类实例为空
		if (factory == null) {
			
			// 创建工作流引擎工厂类实例
			factory = new WorkflowEngineFactory();
		}
		
		// 返回工作流引擎工厂类实例
		return factory;
	}
	
	/**
	 * 取得工作流引擎
	 * @return 工作流引擎
	 * @throws WorkflowException 工作流异常
	 */
	public IWorkflowEngine getWorkflowEngine() throws WorkflowException {
		
		// 如果工作流引擎为空
		if (engine == null) {
			
			// 抛出工作流异常
			throw new WorkflowException("ERR_CANNOT_CREATE_WORKFLOW_ENGINE");
		}
		
		// 返回工作流引擎
		return engine;
	}
	
	/**
	 * 取得工作流引擎类型
	 * @return 工作流引擎类型
	 */
	public String getEngineType() {
		
		// 返回工作流引擎类型
		return engineType;
	}
}
