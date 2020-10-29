package com.ht.exception;

import com.ht.common.exception.CommonException;

/**
 * 数据库异常
 * @author 王有为
 * @date 2016/10/15
 */
public class DBException extends CommonException{
	
	/** Serial Version Unique ID */
	private static final long serialVersionUID = 1L;

	public DBException(){
		// 设置当前异常编码为数据库异常
		super(Constants.EXCEPTION_DB_ERROR_CODE);
	}
	
	/**
	 * 构造器
	 * @param messageCode 异常信息编码
	 */
	public DBException(String messageCode){
		// 设置当前异常编码为数据库异常
		super(messageCode);
	}
	
	/**
	 * throwable 需要引用的异常
	 * @param throwable
	 */
	public DBException(Throwable throwable){
		super(throwable);
	}
	
	/**
	 * 
	 * @param messageCode 异常消息编号
	 * @param arguments
	 */
	public DBException(String messageCode, String[] arguments){
		super(messageCode,arguments);
	}
	
	/**
	 * 持久层异常类构造方法
	 * @param messageCode 异常消息编号
	 * @param detailMessageCode 异常详细消息编号
	 */
	public DBException(String messageCode, String detailMessageCode) {
		
		// 调用父类构造方法
		super(messageCode, detailMessageCode);
	}
	
	/**
	 * 持久层异常类构造方法
	 * @param messageCode 异常消息编号
	 * @param detailMessageCode 异常详细消息编号
	 * @param arguments
	 */
	public DBException(String messageCode, String detailMessageCode, String[] arguments) {
		
		// 调用父类构造方法
		super(messageCode, detailMessageCode, arguments);
	}
}
