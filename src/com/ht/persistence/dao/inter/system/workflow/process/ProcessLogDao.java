package com.ht.persistence.dao.inter.system.workflow.process;

import java.util.List;

import com.ht.persistence.model.base.BaseModel;
import com.ht.persistence.model.system.workflow.process.ProcessLog;


public interface ProcessLogDao{
	/**
	 * 查找实体对象
	 * @param id ID
	 * @return 实体对象，若不存在则返回null
	 */
	ProcessLog getProcessLog(ProcessLog processLog) throws Exception;
	
	/**
	 * 查找所有实体对象集合
	 * @return 所有实体对象集合
	 */
	List<ProcessLog> getProcessLogList() throws Exception;

	
	/**
	* 新增
	 */
	void addProcessLog(ProcessLog processLog) throws Exception;

	/**
	 * 更新实体对象
	 * @param entity 实体对象
	 * @return void    返回类型 
	 */
	void modifyProcessLog(ProcessLog processLog) throws Exception;
	
	/**
	 * 删除实体对象
	 * @param entity 实体对象
	 * @return void    返回类型 
	 */
	void delProcessLog(ProcessLog processLog) throws Exception;
}
