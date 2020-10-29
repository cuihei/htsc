package com.ht.service.inter.experiencebook;

import java.util.Map;

/**
 * 改正通过编辑和改正通告模版编辑经历簿
 * @author huodesheng
 *
 */
public interface CorrectionNoticeService {
	/**
	 * 改正通告编辑经历簿
	 * @param processInstId
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> NoticeMap(String processInstId) throws Exception;
	/**
	 * 改正通告模版编辑经历簿
	 * @param processInstId
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> NoticeTempleteMap(String processInstId) throws Exception;
	/**
	 * 改正通告模版编辑工序流程经历簿
	 * @param processInstId
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> NoticeTempleteFlowMap(String processInstId) throws Exception;
	/**
	 * 改正通告编辑工序流程经历簿
	 * @param processInstId
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> NoticeFlowMap(String processInstId) throws Exception;
	
	/**
	 * 改正通告质量自评表
	 * @author huodesheng
	 * @date 2017-10-9
	 * @company DaoEasy
	 * @param processInstId
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> NoticeSelfQualityEvaluateMap(String processInstId) throws Exception;
	/**
	 * 改正通告质量评定表
	 * @author huodesheng
	 * @date 2017-10-9
	 * @company DaoEasy
	 * @param processInstId
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> NoticeQualityEvaluateMap(String processInstId) throws Exception;
	/**
	 * 改正通告质量检查验收报告
	 * @author huodesheng
	 * @date 2017-10-9
	 * @company DaoEasy
	 * @param processInstId
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> NoticeQualityInspectMap(String processInstId)  throws Exception;
}
