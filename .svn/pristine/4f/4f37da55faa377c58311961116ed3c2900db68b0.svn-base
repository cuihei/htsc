package com.ht.persistence.dao.inter.drawtask.taskbill;

import java.util.List;

import com.ht.persistence.model.drawtask.taskbill.TaskBill;
import com.ht.persistence.model.drawtask.taskbook.relation.TaskBookPlanRelation;
import com.ht.persistence.model.drawtask.tasksplit.TaskSplit;


/**
 * TaskBillDao
 * @author yaoping
 *
 */
public interface TaskBillDao {
	
	/**
	 * 查找所有实体对象集合
	 * @return 所有实体对象集合
	 */
	List<TaskBill> getList(TaskBill taskBill) throws Exception;
	
	/**s
	  * 查询
	  * @return 编绘任务清单海图编绘list
	  * @throws Exception
	  */
	public List<TaskBill> getListByPlanIdAndType(TaskBill taskBill) throws Exception; 
	
	/**
	 * 根据任务查找计划
	 * @param entity 实体对象
	 * @return void    返回类型 
	 */
	public List<TaskBill> findPlanByTaskId(TaskBookPlanRelation	 tbpr) throws Exception;
	
	/**
	 * 根据任务查找计划类型
	 * @param entity 实体对象
	 * @return void    返回类型 
	 */
	public List<TaskSplit> findPlanTypeByTask(TaskSplit taskSplit) throws Exception;
	
	/**
	 * 根据计划查找任务书计划
	 * @param entity 实体对象
	 * @return void    返回类型 
	 */
	public List<TaskBill> findPlanByPlanId(TaskBill taskBill) throws Exception;
	
	/**
	 * 根据计划查找任务书计划
	 * @param entity 实体对象
	 * @return
	 */
	public List<TaskBill> findListByTaskBookId(TaskBill taskBill) throws Exception;
}
