package com.ht.persistence.dao.inter.system.workflow.publish;

import java.util.List;

import com.ht.persistence.model.drawtask.taskbook.relation.TaskBookPlanRelation;
import com.ht.persistence.model.system.workflow.publish.VProcessDetail;

/**
 * 工作流转日志视图Dao
 * 
 * @author huodesheng
 * @date 2016/10/28
 */
public interface VProcessDetailDao {
	/**
	 * 根据用户userNo获取数据
	 * 
	 * @param userNo
	 * @return 业务表的表名称， 业务表主键字段，业务表主键数据
	 * @throws Exception
	 */
	public List<VProcessDetail> findListByUserNo(String userNo)
			throws Exception;

	/**
	 * 根据用户userNo获取表名
	 * 
	 * @param userNo
	 * @return 业务表的表名称， 业务表主键字段，业务表主键数据
	 * @throws Exception
	 */
	public List<VProcessDetail> findTableNameByUserNo(String userNo)
			throws Exception;

	/**
	 * 
	 * @param TableName 表名
	 * @param KeyName 主键名
	 * @param KeyValue 主键值
	 * @return
	 * @throws Exception
	 */
	public List<Object> findListByTable(String TableName, String KeyName,
			String KeyValue) throws Exception;

	/**
	 * 获取流程日志
	 * @param userNo 用户登录号
	 * @param tableName 关联表名称
	 * @param processInstId 流程实例ID
	 * @param taskInstId 任务实例ID
	 * @return 流程日志集合
	 */
	public VProcessDetail getProcessDetailByProcessInstIdAndTaskId(
			String userNo, String tableName, String processInstId,
			String taskInstId);

	/**
	 * 获取流程日志
	 * @param tableName 关联表名称
	 * @param processInstId 流程实例ID
	 * @return 流程日志集合
	 */
	public VProcessDetail getProcessDetailByProcessInstId(String tableName,
			String processInstId);

	/**
	 * 获取流程日志
	 * @param processInstId 流程实例ID
	 * @return 流程日志集合
	 */
	public VProcessDetail getProcessDetailByProcessInstId(String processInstId);

	public VProcessDetail getProcessDetailByProcessInstIdAndTaskId(
			String processInstId, String taskInstId);

	public VProcessDetail getProcessDetailByProcessIdAndId(
			String processInstId, String taskInstId,String processDefId,String taskDefId);
	
	/**
	 * 根据流程id获取任务书id
	 * @param processInstId 流程实例ID
	 * @return 流程日志集合
	 */
	public List<TaskBookPlanRelation> findTaskIdByProcessInstId(VProcessDetail vProcessDetail)throws Exception;

	public List<VProcessDetail> getProcessDetailsByProcessIdAndId(String processInstId, String taskInstId, String processDefId, String taskDefId);

	public VProcessDetail getProcessDetailByProcessInstIdAndDefKey(String parentProcessInstId, String processDefKey);

	public VProcessDetail getProcessDetailByParentProcessInstIdAndDefKey(String parentProcessInstId, String processDefKey);

	/**
	 * 通过recordId获取数据
	 * @param recordId
	 * @return
	 */
	public VProcessDetail getByDetailRecordId(String recordId);
}
