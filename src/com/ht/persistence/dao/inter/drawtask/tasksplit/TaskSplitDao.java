package com.ht.persistence.dao.inter.drawtask.tasksplit;

import java.util.List;

import com.ht.persistence.model.drawtask.tasksplit.TaskSplit;

/**
 * TaskSplitDao
 * @author yaoping
 */
public interface TaskSplitDao
{

	/**
	 * 拆分
	 */
	void doSplit(TaskSplit taskSplit) throws Exception;

	/**
	 * 拆分校验
	 */
	Boolean ifExist(TaskSplit taskSplit) throws Exception;

	/**
	 * 编绘管理各子查询
	 * @return 所有实体对象集合
	 */
	List<TaskSplit> findCompilationList(TaskSplit taskSplit) throws Exception;

	/**
	 * 根据任务书类型获取拆分
	 * @param taskSplit 拆分实体
	 * @return 拆分列表
	 * @throws Exception
	 */
	List<TaskSplit> findCompilationListByTaskBookType(TaskSplit taskSplit) throws Exception;

	List<TaskSplit> findCompilationListByTaskBookIdAndType(TaskSplit taskSplit) throws Exception;

	TaskSplit findCompilationListById(TaskSplit taskSplit) throws Exception;
}
