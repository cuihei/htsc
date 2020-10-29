package com.ht.service.inter.statisticalanalysis;

import java.util.List;
import java.util.Map;

import com.ht.persistence.model.system.workflow.process.ProcessTypeCount;
import com.ht.persistence.model.system.workflow.task.HiTask;
import com.ht.persistence.model.system.workflow.task.RuTask;
import com.ht.service.impl.system.workflow.task.ProcessTypeEnum;
import com.ht.service.impl.system.workflow.task.SeaMapEnum;

public interface QualityAnalysisStatisticsService {

	/**
	 * 提交任务
	 * @param taskId 任务实例ID
	 * @param agreeValue 是否同意 同意1 退回0
	 */
	public void performTask(String userNo, String processInstId,String parentProcessInstId, String taskId,
			String agreeValue, String advice,String processDefId,String taskDefId) throws Exception;;
	
	/**
	 * 提交任务
	 * @param taskId 任务实例ID
	 * @param cliamUser 领取人
	 */
	public void performTaskWithcliamUser(String taskId,String cliamUser) throws Exception;
	
	/**
	 * 提交任务
	 * @param taskId 任务实例ID
	 * @param callActivityIds 子流程ID列表
	 */
	public void performTaskWithCallActivity(String taskId,List<String> callActivityIds) throws Exception;

	/**
	 * 获取待办
	 * @param userNo 用户
	 * @param processDefId 流程定义ID
	 * @throws Exception 
	 */
	public List<Map<String, Object>> getRuTask(String userNo,String loginNo,ProcessTypeEnum type) throws Exception;
	
	/**
	 * 获取所有待办
	 * @throws Exception 
	 */
	public List<Map<String, Object>> getRuTask() throws Exception;
	
	/**
	 * 获取目录待办
	 * @param userNo 用户
	 * @param processDefId 流程定义ID
	 * @throws Exception 
	 */
	public List<Map<String, Object>> getCatalogRuTask(String userNo,String loginNo,ProcessTypeEnum type) throws Exception;

	/**
	 * 获取编绘管理待办任务
	 * @param type
	 * @return 
	 * @throws Exception 
	 */
	List<Map<String, Object>> getDrawRuTask(String userId, ProcessTypeEnum type) throws Exception;

	/**
	 * 获取任务书待办任务
	 * @param userNo 用户登录号
	 * @param type 类型
	 * @return 任务书待办任务
	 * @throws Exception
	 */

	List<Map<String, Object>> getTaskBookRuTask(String userId, ProcessTypeEnum processDefKey) throws Exception;

	/**
	 * 根据流程实例获取流程任务
	 * @param processInstId 流程实例Id
	 * @return 任务集合
	 * @throws Exception
	 */
	public List<RuTask> getRuTaskByProcessInstId(String processInstId) throws Exception;
	
	/**
	 * 挂起流程实例
	 * @param processInstId 流程实例ID
	 * @param userNo 用户编号
	 * @param advice 意见
	 */
	public void suspendProcessInstance(String processInstId,String userNo,String advice) throws Exception;
	
	/**
	 * 激活流程实例
	 * @param processInstId 流程实例ID
	 * @param userNo 用户编号
	 * @param advice 意见
	 */
	public void activateProcessInstance(String processInstId,String userNo,String advice) throws Exception;
	
	/**
	 * 委托受理人
	 * @param processInstId 流程实例ID
	 * @param taskId 任务实例ID
	 * @param userNo 操作人
	 * @param delegateUserId 委托人ID
	 * @throws Exception
	 */
	public void delegateTask(String processInstId, String taskId, String userNo,
			String delegateUserId) throws Exception;
	
	/**
	 * 获取所有海图编绘待办
	 */
	public List<Map<String, Object>> getAllDrawRuTask(SeaMapEnum type)
			throws Exception;

	public List<Map<String, Object>> getBookInfoRuTask(String userNo, String loginNo, ProcessTypeEnum type) throws Exception;

	public List<Map<String, Object>> getReturnBookRuTask(String userNo,
			String loginNo, ProcessTypeEnum type) throws Exception;
	
	/**
	 * 获取经办
	 * @param loginNo 登录号 工号
	 * @param type 流程类型
	 * @return 经办列表
	 * @throws Exception 
	 */
	public List<Map<String, Object>> getHiTask(String loginNo,ProcessTypeEnum type,String processInstId) throws Exception;
	public List<Map<String, Object>> getHiTaskGroupByProcessInstId(String loginNo,ProcessTypeEnum type) throws Exception;

	public List<Map<String, Object>> getDataInputRuTask(String userNo, String loginNo, ProcessTypeEnum type) throws Exception;

	/**
	 * 根据key获取待办
	 */
	List<Map<String, Object>> getRuTaskByKey(String key) throws Exception;

	/**
	 * 获取所有经办
	 * @return
	 * @throws Exception
	 */
	List<Map<String, Object>> getCompleteTask() throws Exception;

	List<Map<String, Object>> getCompleteTaskByKey(String processDefKey) throws Exception;

	List<HiTask> getHiTaskByProcessInstIdAndTaskDefId(String processInstId, String taskDefId);

	List<Map<String, Object>> getProblemRuTask(String userNo, String loginNo, ProcessTypeEnum type) throws Exception;

	/**
	 * 获取用户可以进行委托的任务
	 * @param loginNo
	 * @return 
	 * @throws Exception
	 */
	List<Map<String, Object>> getDelegateTask(String loginNo) throws Exception;

	/**
	 * 计划流程是否走完
	 * @return
	 */
	Boolean validQuality(String processInstId, String parentProcessInstId);
	
	List<ProcessTypeCount> getRuTaskCountGroupByKey(String userId) throws Exception;

	List<Map<String, Object>> getHiTaskProcessInstId(String loginNo,
			ProcessTypeEnum type, String processInstId) throws Exception;

	List<Map<String, Object>> getCompleteTaskByDate(String processDefKey,
			String startTime, String endTime) throws Exception;

	List<Map<String, Object>> getAllDrawRuTaskByDate(SeaMapEnum type,
			String startTime, String endTime) throws Exception;

	List<Map<String, Object>> getRuTaskByDate(String key, String startTime,
			String endTime) throws Exception;
}
