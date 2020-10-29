package com.ht.service.inter.system.workflow.process;

import java.util.List;

import com.ht.persistence.model.system.workflow.process.ProcessLog;
import com.ht.persistence.model.system.workflow.process.ProcessLogDetail;

public interface ProcessLogDetailService {
	
	/**
	 * 查找实体对象
	 * @param id 
	 * @return 实体对象，若不存在则返回null
	 */
	ProcessLogDetail getProcessLogDetailById(String id) throws Exception;
	
	/**
	 * 查找所有实体对象集合 
	 * @return 所有实体对象集合
	 */
	List<ProcessLogDetail> getProcessLogDetailList() throws Exception;
	

	/**
	 * 新增或更新实体对象
	 * @param String
	 * @return 
	 */
	void addOrUpdateProcessLogDetail(String processLogDetail) throws Exception;

	/**
	 * 删除实体对象
	 * @param id 目录ID
	 */
	void delProcessLogDetail(String ids) throws Exception;
	
    /**
     * 添加
     * @param processLogId 工作流操作日志主键
     * @param tableName 操作表
     * @param detailRecordId 操作的记录主键
     * @param processInstId 流程实例ID
     * @throws Exception
     */
	void addProcessLogDetail(String processLogId,String tableName,
			String detailRecordId,String processInstId) throws Exception;	
}
