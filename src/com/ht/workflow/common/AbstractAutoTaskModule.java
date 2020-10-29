package com.ht.workflow.common;

import com.ht.workflow.exception.WorkflowException;
import com.ht.workflow.util.ArgumentList;

/**
 * BizGo系统工作流自动任务处理模块抽象类
 * @author wyw
 * @version 1.0 2014/09/19
 */
public abstract class AbstractAutoTaskModule {

	/**
	 * 工作流自动任务处理初始化方法
	 * @param arguments 参数列表
	 * @throws WorkflowException 发生工作流异常
	 */
	public abstract void init(ArgumentList arguments) throws WorkflowException;
	
	/**
	 * 工作流自动任务处理主方法
	 * @param arguments 参数列表
	 * @throws WorkflowException 发生工作流异常
	 */
	public abstract void execute(ArgumentList arguments) throws WorkflowException;
	
	/**
	 * 工作流自动任务处理销毁方法
	 * @param arguments 参数列表
	 * @throws WorkflowException 发生工作流异常
	 */
	public abstract void destroy(ArgumentList arguments) throws WorkflowException;
}
