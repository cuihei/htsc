package com.ht.persistence.dao.inter.system.workflow.task;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ht.persistence.model.drawtask.taskbook.book.StatisticsPlan;
import com.ht.persistence.model.drawtask.taskbook.book.StatisticsTask;
import com.ht.persistence.model.drawtask.taskbook.book.TaskAnalysis;
import com.ht.persistence.model.drawtask.taskbook.book.TaskBookUpTime;
import com.ht.persistence.model.drawtask.taskbook.book.TaskPlan;
import com.ht.persistence.model.drawtask.taskbook.book.Taskinfo;
import com.ht.persistence.model.statisticalanalysis.view.CompilationResultSummaryView;
import com.ht.persistence.model.system.workflow.process.ProcessTypeCount;
import com.ht.persistence.model.system.workflow.task.HiTask;
import com.ht.persistence.model.system.workflow.task.RuTask;

/**
 * 流程任务DAO
 * @author 王有为
 * @date 2016/11/1
 */
public interface TaskDao {
	
	/**
	 * 根据任务受理组取得任务
	 * @param groupId 受理组ID
	 * @return 任务列表
	 */
	public List<RuTask> getRuTaskByGroupId(RuTask ruTask);
	
	/**
	 * 根据任务受理组取得任务
	 * @param ProcessInstId 父流程实例ID
	 * @return 任务列表
	 */
	public List<RuTask> getRuTaskByParentProcessInstId(RuTask ruTask);
	
	/**
	 * 根据任务受理组取得任务
	 * @param groupId 受理组ID
	 * @param ProcessDefId 流程定义ID
	 * @return 任务列表
	 */
	public List<RuTask> getRuTaskByGroupIdAndProcessDefId(RuTask ruTask);

	/**
	 * 根据流程实例ID取得任务
	 * @param ruTask
	 * @return
	 */
	public List<RuTask> getRuTaskByProcessInstId(RuTask ruTask);

	/**
	 * 修改业务明细
	 * @param tableName 业务表名
	 * @param id 业务表主键名称
	 * @param key 业务主键
	 * @param tableStatusName 业务表状态名称
	 * @param status 状态
	 * @return 修改数量
	 */
	public int updateTable(String tableName, String id, String key, String tableStatusName, String status);

	/**
	 * 获取所有待办
	 * @return
	 */
	public List<RuTask> getAllRuTask();

	
	
	/**
	 * 根据父流程ID和流程定义Key取得历史任务
	 * @param parentProcessId 父流程ID
	 * @param processDefKey 流程定义KEY
	 * @return 历史任务列表
	 */
	public List<HiTask> getHiTaskByParentProcessIdAndProcessDefKey(String parentProcessId,String processDefKey);

	/**
	 * 根据受理人获取经办任务
	 * @param assignee
	 * @return 历史任务列表
	 */
	public List<HiTask> getHiTaskByAssignee(String assignee);
	
	/**
	 * 根据受理人和流程key获取经办任务
	 * @param assignee 受理人
	 * @param processDefKey 流程key
	 * @return 历史任务列表
	 */
	public List<HiTask> getHiTaskByAssigneeAndProcessDefKey(String assignee,String processDefKey);

	/**
	 * 根据流程实例ID获取经办任务
	 * @param processInstId 流程实例ID
	 * @return 历史任务列表
	 */
	public List<HiTask> getHiTaskByProcessId(String processInstId);

	/**
	 * 根据流程定义KEY获取历史任务
	 * @param processDefKeys 流程定义KEY
	 * @return 历史任务列表
	 */
	public List<HiTask> getHiTaskByProcessDefKey(List<String> processDefKeys);

	/**
	 * 获取完成的任务
	 * @param processDefKeys
	 * @return
	 */
	public List<HiTask> getCompleteTask(List<String> processDefKeys,String page,String pageSize);
	
	
	public List<HiTask> getCompleteTaskByYear(List<String> processDefKeys,String page,String pageSize,String year);

	List<HiTask> getHiTaskByProcessInstIdAndTaskDefId(String processInstId, String taskDefId);

	List<HiTask> getHiTaskByGroupAndProcessDefKey(String group, String processDefKey);

	List<RuTask> getRuTaskByAssigneeList(List<String> assigneeList);

	List<HiTask> getHiTaskNotInProcessInstId(String processInstId, String parentProcessInstId);

	List<ProcessTypeCount> getRuTaskCountGroupByKey(String userId,String groupId);
	/**
	 * 根据流程分组获取经办
	 * @param assignee
	 * @param processDefKey
	 * @return
	 */
	List<HiTask> getHiTaskGroupByProcessInstId(String assignee,String processDefKey);

	List<HiTask> getHiTaskByAssigneeAndProcessInstId(String assignee,
			String processDefKey, String ProcessInstId);

	List<HiTask> getHiTaskByGroupAndProcessInstId(String group,
			String processDefKey, String processInstId);
	/**
	 * 根据流程类型和开始结束时间获取经办
	 * @param processDefKeys
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	List<HiTask> getCompleteTask(List<String> processDefKeys, Date beginDate,
			Date endDate);

	public List<RuTask> getRuTaskInProcessDefKey(List<String> name);
	
	//根据父流程ID获取流程ID
	public List<HiTask> getTaskByParentProcessInstId(String parentProcessInstId);
 
	public int getCompleteTaskCount(List<String> processDefKeys);

	public List<RuTask> getRuTaskInProcessDefKey(List<String> name, String page, String pageSize);
	

	public List<RuTask> getRuTaskInProcessDefKeyByYear(List<String> name, String page, String pageSize,String year);

	public int getRuTaskInProcessDefKeyCount(List<String> name);

	/**
	 * 根据流程定义Key获取待办
	 * @param ruTask
	 * @return
	 */
	public List<RuTask> getRuTaskByProcessDefKey(RuTask ruTask, String page, String pageSize);

	public List<RuTask> getRuTaskByProcessDefKey(RuTask ruTask);

	public int getRuTaskByProcessDefKeyCount(RuTask ruTask);

	public List<HiTask> getCompleteTask(List<String> processDefKeys);

	public RuTask getRtByParentProcessInstId(String parentProcessInstId);
   /* 获取计划分配人。*/
	public String getplanName(String taskId, String processInstId, String processDefId, String taskDefId, String userId,String parentProcessInstId);

	public List<String> getAllTaskYear();

	void addTaslAllList(List<Map<String, Object>> listAll);
	void addTaslAllListPlan(List<Map<String, Object>> listAll);
	void delAllTempTask();
	void delAllTempTaskPlan();
	
	/*	获取所有任务  新任务完成情况	*/
	public  List<StatisticsPlan>  getTaskAllPlan(String year,String compType);
	/*	获取所有任务  新任务完成情况	*/
	public  List<StatisticsTask> getTaskAll(String year,String compType);
	/*	获取所有任务  中间表情况	*/
	public  List<TaskAnalysis> zjb_getTaskAll(String year,String compType);
	
	
	/*	获编印工作量完成情况  中间表情况	*/
	public  List<TaskAnalysis> getCompletionFinish(String  id,String  start,String end,String year,String type);
	/**
	 * 获取一条数据
	 * @param crs
	 * @return
	 */
	public TaskAnalysis getCompletionFinishById(TaskAnalysis crs);

	/*	获取所有任务  中间表完成情况	*/
	public List<TaskPlan> zjb_getTaskAllPlan(String year,String compType);
	 List<String> taskupTimeStr(String type);

	public int updateTaskUpTime(String type, String sta);
	//分配人员时更新COMPILATION_TASK_PLAN中的对应的人员
	//public void updateTaskplanUser(String taskId, String processInstId, String processDefId, String taskDefId, String userId, String parentProcessInstId,String agreeValue,String taskName);

	public int updateTaskProgress_A(String processInstId,String nowTime, String taskDefId);
	public List<Map<String, Object>> updateTaskProgress_B(String processInstId,String nowTime, String taskDefId);

	void updateTaskplanUser(String taskId, String processInstId, String processDefId, String taskDefId, String userId,
			String parentProcessInstId, String agreeValue, String taskName);

	public void updateTaskProgress_C(String processInstId,String nowTime);
	public void updateTaskProgress_E(String processInstId,String nowTime);
	public void updateTaskProgress_H(String processInstId,String nowTime);

	public List<Taskinfo> getinfo(String proc_inst_id);
	
	public List<Taskinfo> getInfos(String proc_inst_ids);

	public String getGroup(String userid);


	Map<String, Object> getscore(String proc_inst_id, String ry);


	public String talist1(String procinstid, String taskinstid);
	public String talist2(String procinstid, String taskinstid);

}
