package com.ht.service.inter.system.workflow.process;

import java.util.List;

import com.ht.persistence.model.system.workflow.process.ProcessForm;
import com.ht.persistence.model.system.workflow.process.ProcessFormProp;

public interface ProcessFormService {
	
	
	/**
	 * 查找实体对象
	 * @param id 
	 * @return 实体对象，若不存在则返回null
	 */
	ProcessForm getProcessFormById(String id) throws Exception;
	
	/**
	 * 查找实体对象
	 * @param processDefId 流程定义id
	 * @param taskDefId 流程任务定义id
	 * @return 实体对象 ProcessForm
	 */
	ProcessForm getProcessFormByProAndTask(String processDefId,String taskDefId) 
			throws Exception;
	
	/**
	 * 查找所有实体对象集合 
	 * @return 所有实体对象集合
	 */
	List<ProcessForm> getProcessFormList() throws Exception;
	

	/**
	 * 新增或更新实体对象
	 * @param String
	 * @return 
	 */
	void addOrUodateProcessForm(String processForm) throws Exception;

	/**
	 * 删除实体对象
	 * @param id 目录ID
	 */
	void delProcessForm(String ids) throws Exception;

	List<ProcessForm> getProcessFormByProcessDefId(String processDefId) throws Exception;

	List<ProcessFormProp> getProcessFormProp(String processDefId, String taskDefId) throws Exception;
	
	void addProcessFormProp(String processFormProp) throws Exception;

	List<ProcessFormProp> getProcessFormProp(String formId) throws Exception;
}
