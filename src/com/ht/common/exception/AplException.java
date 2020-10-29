package com.ht.common.exception;

/**
 * 系统应用程序异常类
 * @author 王有为
 * @version 1.0 2015/01/05
 */
public class AplException extends CommonException {

	/** Serial Version Unique ID */
	private static final long serialVersionUID = 3924392216478962312L;
	
	/**
	 * 应用程序异常类构造方法
	 */
	public AplException() {
		
	}
	
	/**
	 * 应用程序异常类构造方法
	 * @param throwable 需要引用的异常
	 */
	public AplException(Throwable throwable) {
		
		// 调用父类构造方法
		super(throwable);
	}
	
	/**
	 * 应用程序异常类构造方法
	 * @param messageCode 异常消息编号
	 */
	public AplException(String messageCode) {
		
		// 调用父类构造方法
		super(messageCode);
	}
	
	/**
	 * 应用程序异常类构造方法
	 * @param messageCode 异常消息编号
	 * @param arguments
	 */
	public AplException(String messageCode, String[] arguments) {
		
		// 调用父类构造方法
		super(messageCode, arguments);
	}
	
	/**
	 * 应用程序异常类构造方法
	 * @param messageCode 异常消息编号
	 * @param detailMessageCode 异常详细消息编号
	 */
	public AplException(String messageCode, String detailMessageCode) {
		
		// 调用父类构造方法
		super(messageCode, detailMessageCode);
	}
	
	/**
	 * 应用程序异常类构造方法
	 * @param messageCode 异常消息编号
	 * @param detailMessageCode 异常详细消息编号
	 * @param arguments 参数集合
	 */
	public AplException(String messageCode, String detailMessageCode, String[] arguments) {
		
		// 调用父类构造方法
		super(messageCode, detailMessageCode, arguments);
	}
}
