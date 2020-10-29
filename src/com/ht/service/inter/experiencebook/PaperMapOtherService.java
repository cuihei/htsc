package com.ht.service.inter.experiencebook;

import java.util.Map;

/**
 * 纸海图制作工序流程表，
 * 纸海图质量检查验收报告，
 * 纸海图质量评定表
 * @author huodesheng
 *
 */
public interface PaperMapOtherService {
	/**
	 * 纸海图制作工序流程表
	 * @param processInstId
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> PaperProcessflow(String processInstId,String type) throws Exception;
	/**
	 * 纸海图质量检查验收报告
	 * @param processInstId
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> PaperQualityInspectMap(String processInstId,String type) throws Exception;
	/**
	 * 纸海图质量评定表            PaperQualityEvaluateMap
	 * @param processInstId
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> PaperQualityEvaluateMap(String processInstId,String type) throws Exception;
	
	/**
	 * 纸海图质量自评表
	 * @param processInstId
	 * @return
	 * @throws Exception          PaperQualityEvaluateMap
	 */
	public Map<String,Object> PaperSelfQualityEvaluateMap(String processInstId,String type) throws Exception;
	
	/**
	 * 电子海图制作工序流程表
	 * @param processInstId
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> ElectronicProcessflow(String processInstId,String type) throws Exception;
	
	/**
	 * 纸海图小改正工序流程表
	 * @author huodesheng
	 * @date 2017-9-28
	 * @company DaoEasy
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> PaperCorrectionProcessflow(String processInstId) throws Exception;
	
	/**
	 * 源数据小改正工序流程表
	 * @author huodesheng
	 * @date 2017-9-28
	 * @company DaoEasy
	 * @param processInstId
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> SourceProcessflow(String processInstId) throws Exception;
	
}
