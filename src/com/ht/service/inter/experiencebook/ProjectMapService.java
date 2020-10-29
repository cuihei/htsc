package com.ht.service.inter.experiencebook;

import java.util.Map;

/**
 * 工程转图图经历簿导出类
 * @author wangyouwei
 *
 */
public interface ProjectMapService
{
	/**
	 * 纸海图经历簿导出
	 * @param processInstId
	 */
	public Map<String, Object> projectPaperMap(String processInstId) throws Exception;
	
	/**
	 * 电子海图经历簿导出
	 * @param processInstId
	 */
	public Map<String, Object> projectElectricMap(String processInstId) throws Exception;
}
