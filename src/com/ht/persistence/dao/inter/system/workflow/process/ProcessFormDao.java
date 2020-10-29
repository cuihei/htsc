package com.ht.persistence.dao.inter.system.workflow.process;

import java.util.List;

import com.ht.persistence.model.system.workflow.process.ProcessFlow;
import com.ht.persistence.model.system.workflow.process.ProcessForm;
import com.ht.persistence.model.system.workflow.process.ProcessFormProp;

public interface ProcessFormDao {
	/**
	 * 查找实体对象
	 * @param id ID
	 * @return 实体对象，若不存在则返回null
	 */
	ProcessForm getProcessForm(ProcessForm processForm) throws Exception;
	
	ProcessForm getProcessFormByProAndTask(ProcessForm processForm) throws Exception;
	
	/**
	 * 查找所有实体对象集合
	 * @return 所有实体对象集合
	 */
	List<ProcessForm> getProcessFormList() throws Exception;

	/**
	* 新增
	 */
	void addProcessForm(ProcessForm processForm) throws Exception;

	/**
	 * 更新实体对象
	 * @param entity 实体对象
	 * @return void    返回类型 
	 */
	void modifyProcessForm(ProcessForm processForm) throws Exception;
	
	/**
	 * 删除实体对象
	 * @param entity 实体对象
	 * @return void    返回类型 
	 */
	void delProcessForm(ProcessForm processForm) throws Exception;

	List<ProcessForm> getProcessFormByProcessDefId(ProcessForm processForm) throws Exception;
	
	List<ProcessFormProp> getProcessFormProp(String processDefId, String taskDefId) throws Exception;

	void saveProcessFormProp(ProcessFormProp entity) throws Exception;

	List<ProcessFormProp> getProcessFormProp(String formId) throws Exception;

	void deleteProcessFormProp(List<ProcessFormProp> entity) throws Exception;
	
	void insertProcessFormProp(List<ProcessFormProp> list) throws Exception;
}
