package com.ht.common.util;

import java.net.URL;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import com.ht.common.constant.CommonConstants;
import com.ht.common.constant.LogConstants;

/**
 * 系统日志输出器
 * @author wyw
 * @version 1.0 2014/08/30
 */
public class LogHelper {
	
	/** 系统日志实例 */
	public static final LogHelper SYSTEM = new LogHelper(LogConstants.SYSTEM_LOGGER);
	/** 错误日志实例 */
	public static final LogHelper ERROR = new LogHelper(LogConstants.ERROR_LOGGER);
	/** 异常日志实例 */
	public static final LogHelper FATAL = new LogHelper(LogConstants.FATAL_LOGGER);
	
	/** 日志输出器 */
	private Logger logger = null;
	
	/**
	 * 系统日志输出器构造方法
	 * @param loggerName 日志输出器名称
	 */
	private LogHelper(String loggerName) {
		
		// 取得日志配置文件的绝对路径
		URL configFileUrl = EnvironmentUtil.getResourceUrl(LogHelper.class, CommonConstants.LOG_CONFIG_FILE);
		// 加载配置文件
		DOMConfigurator.configure(configFileUrl);
		// 创建日志输出器
		logger = Logger.getLogger(loggerName);
	}
	
	/**
	 * 输出日志
	 * @param message 日志内容
	 */
	public void log(String message) {
		
		// 输出日志
		log(LogConstants.LOG_LEVEL_INFO, message);
	}
	
	/**
	 * 输出日志
	 * @param level 日志级别
	 * @param message 日志内容
	 */
	public void log(String level, String message) {
		
		// 如果是信息级日志
		if (StringUtils.equals(level, LogConstants.LOG_LEVEL_INFO)) {
			
			// 输出日志
			logger.info(message);
		}
		// 如果是警告级日志
		else if (StringUtils.equals(level, LogConstants.LOG_LEVEL_WARN)) {
			
			// 输出日志
			logger.warn(message);
		}
		// 如果是错误级日志
		else if (StringUtils.equals(level, LogConstants.LOG_LEVEL_ERROR)) {
			
			// 输出日志
			logger.error(message);
		}
		// 如果是致命错误级日志
		else if (StringUtils.equals(level, LogConstants.LOG_LEVEL_FATAL)) {
			
			// 输出日志
			logger.fatal(message);
		}
		// 如果是调试级日志
		else if (StringUtils.equals(level, LogConstants.LOG_LEVEL_DEBUG)) {
			
			// 输出日志
			logger.debug(message);
		}
	}
	
	/**
	 * 输出日志
	 * @param message 日志内容
	 * @param throwable 异常信息
	 */
	public void log(String message, Throwable throwable) {
		
		// 输出日志
		log(LogConstants.LOG_LEVEL_ERROR, message, throwable);
	}
	
	/**
	 * 输出日志
	 * @param level 日志级别
	 * @param message 日志内容
	 * @param throwable 异常信息
	 */
	public void log(String level, String message, Throwable throwable) {
		
		// 如果是信息级日志
		if (StringUtils.equals(level, LogConstants.LOG_LEVEL_INFO)) {
			
			// 输出日志
			logger.info(message, throwable);
		}
		// 如果是警告级日志
		else if (StringUtils.equals(level, LogConstants.LOG_LEVEL_WARN)) {
			
			// 输出日志
			logger.warn(message, throwable);
		}
		// 如果是错误级日志
		else if (StringUtils.equals(level, LogConstants.LOG_LEVEL_ERROR)) {
			
			// 输出日志
			logger.error(message, throwable);
		}
		// 如果是致命错误级日志
		else if (StringUtils.equals(level, LogConstants.LOG_LEVEL_FATAL)) {
			
			// 输出日志
			logger.fatal(message, throwable);
		}
		// 如果是调试级日志
		else if (StringUtils.equals(level, LogConstants.LOG_LEVEL_DEBUG)) {
			
			// 输出日志
			logger.debug(message, throwable);
		}
	}
}
