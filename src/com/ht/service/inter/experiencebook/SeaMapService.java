package com.ht.service.inter.experiencebook;

import java.util.Map;

/**
 * 海图编绘经历簿导出类
 * @author wangyouwei
 *
 */
public interface SeaMapService
{
	/**
	 * 源数据编绘经历簿导出
	 * @param processInstId
	 * @return 
	 * @throws Exception 
	 */
	public Map<String, Object> sourceDataMap(String processInstId) throws Exception;
	
	/**
	 * 纸海图经历簿导出
	 * @param processInstId
	 * @return 
	 */
	public Map<String, Object> paperMap(String processInstId,String pro_InstId) throws Exception;
	
	/**
	 * 电子海图经历簿导出
	 * @param processInstId
	 */
	public Map<String, Object> electricMap(String processInstId) throws Exception;
}
