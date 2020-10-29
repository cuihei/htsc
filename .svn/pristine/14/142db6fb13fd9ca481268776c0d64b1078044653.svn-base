package com.ht.service.inter.system.workflow.process;

import java.sql.SQLException;
import java.util.List;

import org.activiti.engine.repository.ProcessDefinition;

import com.ht.exception.DBException;
import com.ht.persistence.model.catalog.detail.CatalogDetail;
import com.ht.persistence.model.system.workflow.process.ProcessFlow;

public interface ProcessFlowService {
	
	/**
	 * 查找实体对象
	 * @param id 
	 * @return 实体对象，若不存在则返回null
	 */
	ProcessFlow getProcessFlowById(String id) throws Exception;
	
	/**
	 * 查找所有实体对象集合 
	 * @return 所有实体对象集合
	 */
	List<ProcessFlow> getProcessFlowList() throws Exception;
	

	/**
	 * 新增或更新实体对象
	 * @param String
	 * @return 
	 */
	void addOrUpdateProcessFlow(String processFlow) throws Exception;

	/**
	 * 删除实体对象
	 * @param id 目录ID
	 */
	void delProcessFlow(String ids) throws Exception;

	ProcessFlow getProcessFlowByAdvice(String advice,String processInstId) throws DBException;

	ProcessFlow getProcessFlowByAdvice1(String advice, String processInstId) throws DBException;
}
