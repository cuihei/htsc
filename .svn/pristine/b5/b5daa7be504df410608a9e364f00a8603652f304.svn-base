package com.ht.persistence.dao.inter.drawtask.taskbook.relation;

import java.util.List;

import com.ht.persistence.model.drawtask.taskbook.relation.TaskBookPlanRelation;

public interface TaskBookPlanRelationDao {
	/**
	 * 所有关联关系
	 * @return
	 * @throws Exception
	 */
	public List<TaskBookPlanRelation> findList() throws Exception;
	
	/**
	 * 根据任务书id获取关系列表
	 * @return
	 * @throws Exception
	 */
	public List<TaskBookPlanRelation> findListByBookId(String taskBookId) throws Exception;
	/**
	 * 根据任务id获取关系列表
	 * @return
	 * @throws Exception
	 */
	public List<TaskBookPlanRelation> findListByPlanId(String PlanId) throws Exception;
	/**
	 * 添加关系
	 * @throws Exception
	 */
	public void addRelation(TaskBookPlanRelation taskBookPlanRelation) throws Exception;
	/**
	 * 修改关系
	 * @throws Exception
	 */
	public void updateRelation(TaskBookPlanRelation taskBookPlanRelation) throws Exception;
	
	/**
	 * 根据任务书id删除关系
	 * @throws Exception
	 */
	public void delRelationByBookId(String taskBookId) throws Exception;
	/**
	 * 根据计划id删除关系
	 * @throws Exception
	 */
	public void delRelationByPlanId(String planId) throws Exception;
}
