package com.ht.service.inter.system.workflow.process;

import java.util.List;

import com.ht.persistence.model.system.workflow.process.ProcessFlow;
import com.ht.persistence.model.system.workflow.process.ProcessLog;

public interface ProcessLogService {
	/**
	 * 查找实体对象
	 * @param id 
	 * @return 实体对象，若不存在则返回null
	 */
	ProcessLog getProcessLogById(String id) throws Exception;
	
	/**
	 * 查找所有实体对象集合 
	 * @return 所有实体对象集合
	 */
	List<ProcessLog> getProcessLogList() throws Exception;
	

	/**
	 * 新增或更新实体对象
	 * @param String
	 * @return 
	 */
	void addOrUodateProcessLog(String processLog) throws Exception;

	/**
	 * 删除实体对象
	 * @param id 目录ID
	 */
	void delProcessLog(String ids) throws Exception;
	
	/**
	 * 添加
	 * @param userNo 操作人
	 * @param processInstId 流程实例ID
	 * @param tableName 操作表名称
	 * @param operationType 操作类型
	 * @param tableStatusName 操作表状态字段名称
	 * @param tableKeyName 操作表主键字段名称
	 * @throws Exception
	 */
	String addProcessLog(String userNo,String processInstId,String tableName,
			String operationType,String tableStatusName,String tableKeyName) throws Exception;
}
