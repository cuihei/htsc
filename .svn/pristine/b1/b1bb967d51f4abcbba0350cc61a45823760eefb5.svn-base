package com.ht.persistence.dao.inter.system.workflow.process;

import java.util.List;

import com.ht.persistence.model.system.workflow.process.ProcessStatus;

public interface ProcessStatusDao {
	/**
	 * 查找实体对象
	 * @param id ID
	 * @return 实体对象，若不存在则返回null
	 */
	ProcessStatus getProcessStatus(ProcessStatus processStatus) throws Exception;
	
	ProcessStatus getProcessStatusByProAndTask(ProcessStatus processStatus) throws Exception;
	
	/**
	 * 查找所有实体对象集合
	 * @return 所有实体对象集合
	 */
	List<ProcessStatus> getProcessStatusList() throws Exception;

	/**
	* 新增
	 */
	void addProcessStatus(ProcessStatus processStatus) throws Exception;

	/**
	 * 更新实体对象
	 * @param entity 实体对象
	 * @return void    返回类型 
	 */
	void modifyProcessStatus(ProcessStatus processStatus) throws Exception;
	
	/**
	 * 删除实体对象
	 * @param entity 实体对象
	 * @return void    返回类型 
	 */
	void delProcessStatus(ProcessStatus processStatus) throws Exception;
}
