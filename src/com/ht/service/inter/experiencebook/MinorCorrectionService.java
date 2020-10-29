package com.ht.service.inter.experiencebook;

import java.util.Map;

/**
 * 小改正经历簿导出类
 * @author houchen
 *
 */
public interface MinorCorrectionService
{
	/**
	 * 源数据小改正编绘经历簿导出
	 * @param processInstId
	 * @return 
	 * @throws Exception 
	 */
	public Map<String, Object> sourceDataMap(String processInstId) throws Exception;
	
	/**
	 * 纸海图小改正经历簿导出
	 * @param processInstId
	 * @return 
	 */
	public Map<String, Object> paperMap(String processInstId) throws Exception;
	
	/**
	 * 电子海图小改正经历簿导出
	 * @param processInstId
	 */
	public Map<String, Object> electricMap(String processInstId) throws Exception;
	/**
	 * 纸海图小改正工序流程表 
	 * @author huodesheng
	 * @date 2017-10-10
	 * @company DaoEasy
	 * @return
	 */
	public Map<String,Object> paperCorrenctionMap(String processInstId)throws Exception;
	/**
	 * 电子海图小改正工序流程表 
	 * @param processInstId
	 */
	public Map<String, Object> electricCorrenctionMap(String processInstId) throws Exception;
	
}
