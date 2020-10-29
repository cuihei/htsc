package com.ht.persistence.dao.inter.system.workflow.process;

import java.util.List;

import com.ht.persistence.model.system.workflow.process.ProcessTaskRelation;


public interface ProcessTaskRelationDao{
	/**

	
	/**
	* 新增
	 */
	void addProcessTaskRelation(ProcessTaskRelation processTaskRelation) throws Exception;

	/**
	 * 更新实体对象
	 * @param entity 实体对象
	 * @return void    返回类型 
	 */
	void modifyProcessTaskRelation(ProcessTaskRelation processTaskRelation) throws Exception;
	
	/**
	 * 删除实体对象
	 * @param entity 实体对象
	 * @return void    返回类型 
	 */
	void delProcessTaskRelation(ProcessTaskRelation processTaskRelation) throws Exception;
	
	int delByTaskDefIdAndProcessDefId(String taskDefId,String processDefId) throws Exception;
	
	List<ProcessTaskRelation> findByTaskDefId(ProcessTaskRelation processTaskRelation) throws Exception;
}
