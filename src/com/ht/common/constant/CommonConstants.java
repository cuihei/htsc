package com.ht.common.constant;

/**
 * 配置类型常量类
 * @author wyw
 * @version 1.0 2014/08/30
 */
public class CommonConstants {
	/** 日志配置文件名称*/
	public static final String LOG_CONFIG_FILE = "/com/ht/log/config/log.cfg.xml";
	
	/** 父资源文件主键: "NLS_INCLUDES" */
	public static final String PARENT_NLS_KEY = "NLS_INCLUDES";
	
	/** 默认日期时间格式 */
	public static final String FORMAT_PATTERN_DEFAULT_DATE_TIME = "yyyy/MM/dd HH:mm";	
	/** 默认日期格式 */
	public static final String FORMAT_PATTERN_DEFAULT_DATE = "yyyy/MM/dd";
	/** 默认时间格式 */
	public static final String FORMAT_PATTERN_DEFAULT_TIME = "HH:mm";
	
	/**经历簿导出模版中的文本类型**/
	public static final String[] TEMPLETE_TEXT_ARRAY={"text","select","textArea","kendoselect"};
}
