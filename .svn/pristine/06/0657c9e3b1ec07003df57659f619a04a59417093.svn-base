package com.ht.service.inter.system.workflow.process;

import java.util.List;

import com.ht.persistence.model.system.workflow.process.ProcessStatus;

public interface ProcessStatusService {

	/**
	 * 查找实体对象
	 * @param id 
	 * @return 实体对象，若不存在则返回null
	 */
	ProcessStatus getProcessStatusById(String id) throws Exception;
	
	ProcessStatus getProcessStatusByProAndTask(String processDefId,String taskDefId) throws Exception;
	
	/**
	 * 查找所有实体对象集合 
	 * @return 所有实体对象集合
	 */
	List<ProcessStatus> getProcessStatusList() throws Exception;
	

	/**
	 * 新增或更新实体对象
	 * @param String
	 * @return 
	 */
	void addOrUodateProcessStatus(String processStatus) throws Exception;

	/**
	 * 删除实体对象
	 * @param id 目录ID
	 */
	void delProcessStatus(String ids) throws Exception;
}
