package com.ht.persistence.dao.inter.system.workflow.process;

import java.util.List;

import com.ht.persistence.model.system.workflow.process.ProcessLogDetail;

public interface ProcessLogDetailDao {
	/**
	 * 查找实体对象
	 * @param id ID
	 * @return 实体对象，若不存在则返回null
	 */
	ProcessLogDetail getProcessLogDetail(ProcessLogDetail processLogDetail) throws Exception;
	
	/**
	 * 查找所有实体对象集合
	 * @return 所有实体对象集合
	 */
	List<ProcessLogDetail> getProcessLogDetailList() throws Exception;

	
	/**
	* 新增
	 */
	void addProcessLogDetail(ProcessLogDetail processLogDetail) throws Exception;

	/**
	 * 更新实体对象
	 * @param entity 实体对象
	 * @return void    返回类型 
	 */
	void modifyProcessLogDetail(ProcessLogDetail processLogDetail) throws Exception;
	
	/**
	 * 删除实体对象
	 * @param entity 实体对象
	 * @return void    返回类型 
	 */
	void delProcessLogDetail(ProcessLogDetail processLogDetail) throws Exception;

	List<ProcessLogDetail> getProcessLogDetailListByProcessInstId(
			String processInstId) throws Exception;

	List<ProcessLogDetail> getProcessLogDetailByProcessLogId(
			ProcessLogDetail processLogDetail) throws Exception;
}
