package com.ht.workflow.constants;

/**
 * 工作流常量类
 * @author wyw
 * @version 1.0 2014/09/11
 */
public class WorkflowConstants {
	
	/** 工作流配置文件名称: */
	public static final String WORKFLOW_CONFIG_FILE = "/com/ht/workflow/config/workflow-configuration.cfg.xml";
	public static final String WORKFLOW_SESSION_CONFIG_FILE = "/com/ht/spring/config/beans-common-application-context.xml";
	/** 工作流实现类属性名称: "class" */
	public static final String ATTR_NAME_CLASS = "class";
	
	/** 工作流引擎类型XPATH: "configuration/engine-type" */
	public static final String XPATH_ENGINE_TYPE = "configuration/engine-type";
	/** 工作流引擎实现类XPATH: "configuration/engines/engine[@type='{0}']" */
	public static final String XPATH_WORKFLOW_ENGINE = "configuration/engines/engine[@type='{0}']";
	/** 工作流模板XPATH: "configuration/engines/engine[@type='{0}']/templates/template" */
	public static final String XPATH_WORKFLOW_TEMPLATE = "configuration/engines/engine[@type='{0}']/templates/template";
	/** 工作流任务处理模块类: "configuration/engines/engine[@type='{0}']/task-mapper/process[@id='{1}']/task[id='{2}']" */
	public static final String XPATH_TASK = "configuration/engines/engine[@type='{0}']/task-mapper/process[@id='{1}']/task[@id='{2}']";
	
	/** 方法名称: "init" 工作流自动任务处理初始化方法 */
	public static final String METHOD_NAME_INIT = "init";
	/** 方法名称: "execute" 工作流自动任务处理主方法 */
	public static final String METHOD_NAME_EXECUTE = "execute";
	/** 方法名称: "destroy" 工作流自动任务处理销毁方法 */
	public static final String METHOD_NAME_DESTROY = "destroy";
	
	/** 工作流任务代理状态: "pending" 挂起 */
	public static final String TASK_DELEGATION_STATE_PENDING = "pending";
	/** 工作流任务代理状态: "resolved" 解决 */
	public static final String TASK_DELEGATION_STATE_RESOLVED = "resolved";
	
	/** 工作流任务领取状态: "1" 工作流任务领取成功 */
	public static final int TASK_CLAIM_STATUS_TASK_CLAIM_SUCCESSFULLY = 1;
	/** 工作流任务领取状态: "2" 工作流任务无法找到 */
	public static final int TASK_CLAIM_STATUS_TASK_NOT_FOUND = 2;
	/** 工作流任务领取状态: "3" 工作流任务已经被领取 */
	public static final int TASK_CLAIM_STATUS_TASK_ALREADY_CLAIMED = 3;
}
