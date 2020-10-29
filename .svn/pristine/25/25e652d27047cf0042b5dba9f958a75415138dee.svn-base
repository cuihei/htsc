package com.ht.common.exception;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

/**
 * 系统共通异常类
 * @author 王有为
 * @version 1.0 2015/01/05
 */
public class CommonException extends Exception {

	/** Serial Version Unique ID */
	private static final long serialVersionUID = 6776209617856722691L;
	
	/** 异常消息编号 */
	private String messageCode = null;
	/** 异常详细消息编号 */
	private String detailMessageCode = null;
	/** 异常消息参数 */
	private String[] arguments = null;
	/** 错误项目列表 */
	private List<String> errorItems = new ArrayList<String>();
	
	/**
	 * 共通异常类构造方法
	 */
	public CommonException() {
		
	}
	
	/**
	 * 共通异常类构造方法
	 * @param throwable 需要引用的异常
	 */
	public CommonException(Throwable throwable) {
		
		// 调用父类构造方法
		super(throwable);
	}
	
	/**
	 * 共通异常类构造方法
	 * @param messageCode 异常消息编号
	 */
	public CommonException(String messageCode) {
		
		// 设置异常消息编号
		this.messageCode = messageCode;
	}
	
	/**
	 * 共通异常类构造方法
	 * @param messageCode 异常消息编号
	 * @param arguments
	 */
	public CommonException(String messageCode, String[] arguments) {
		
		// 设置异常消息编号
		this.messageCode = messageCode;
		// 设置异常消息参数
		this.arguments = arguments;
	}
	
	/**
	 * 共通异常类构造方法
	 * @param messageCode 异常消息编号
	 * @param detailMessageCode 异常详细消息编号
	 */
	public CommonException(String messageCode, String detailMessageCode) {
		
		// 设置异常消息编号
		this.messageCode = messageCode;
		// 设置异常详细消息编号
		this.detailMessageCode = detailMessageCode;
	}
	
	/**
	 * 共通异常类构造方法
	 * @param messageCode 异常消息编号
	 * @param detailMessageCode 异常详细消息编号
	 * @param arguments
	 */
	public CommonException(String messageCode, String detailMessageCode, String[] arguments) {
		
		// 设置异常消息编号
		this.messageCode = messageCode;
		// 设置异常详细消息编号
		this.detailMessageCode = detailMessageCode;
		// 设置异常消息参数
		this.arguments = arguments;
	}
	
	/**
	 * 取得异常信息
	 * @return 异常信息
	 */
	@Override
	public String getMessage() {
		
		// 设置异常信息
		String message = null;
		
		// 如果异常消息编号不为空
		if (StringUtils.isNotBlank(messageCode)) {
			
			// 设置异常信息
			message = getMessageCode();
		}
		// 如果异常消息编号为空
		else {
			
			// 设置异常信息
			message = super.getMessage();
		}
		
		// 返回异常信息
		return message;
	}
	
	/**
	 * 取得本地化异常信息
	 * @return 本地化异常信息
	 */
	@Override
	public String getLocalizedMessage() {
		
		// 返回本地化异常信息
		return getMessage();
	}
	
	/**
	 * 取得异常消息编号
	 * @return 异常消息编号
	 */
	public String getMessageCode() {
		
		// 返回异常消息编号
		return messageCode;
	}

	/**
	 * 取得异常详细消息编号
	 * @return 异常详细消息编号
	 */
	public String getDetailMessageCode() {
		
		// 返回异常详细消息编号
		return detailMessageCode;
	}

	/**
	 * 返回异常消息参数
	 * @return 异常消息参数
	 */
	public String[] getArguments() {
		
		// 返回异常消息参数
		return arguments;
	}
	
	/**
	 * 添加错误项目
	 * @param errorItem 错误项目
	 * @return 当前异常实例
	 */
	public CommonException addErrorItem(String errorItem) {
		
		// 如果错误项目列表为空
		if (errorItems == null) {
			
			// 创建错误项目列表
			errorItems = new ArrayList<String>();
		}
		
		// 添加错误项目
		errorItems.add(errorItem);
		
		// 返回当前实例
		return this;
	}
	
	/**
	 * 添加错误项目列表
	 * @param errorItems 错误项目列表
	 * @return 当前异常实例
	 */
	public CommonException addErrorItems(List<String> errorItems) {
		
		// 如果错误项目列表为空
		if (errorItems == null) {
			
			// 创建错误项目列表
			errorItems = new ArrayList<String>();
		}
		
		// 添加错误项目
		this.errorItems.addAll(errorItems);
		
		// 返回当前实例
		return this;
	}
	
	/**
	 * 取得错误项目列表
	 * @return 错误项目列表
	 */
	public List<String> getErrorItems() {
		
		// 返回错误项目列表
		return errorItems;
	}
}
