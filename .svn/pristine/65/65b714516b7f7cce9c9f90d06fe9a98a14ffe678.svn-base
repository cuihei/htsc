package com.ht.service.inter.drawtask.tasksplit;

import java.util.List;

import com.ht.persistence.model.drawtask.tasksplit.TaskSplit;

public interface TaskSplitService
{

	/**
	 * 编绘管理各子查询
	 * @return 所有实体对象集合
	 */
	List<TaskSplit> findCompilationList(String flag) throws Exception;

	/**
	 * 编绘任务进行拆分
	 * @return 所有实体对象集合
	 */
	void doSplit(String split) throws Exception;

	/**
	 * 改正通告拆分
	 * @return 所有实体对象集合
	 */
	void corNoticeSplit(String split) throws Exception;

	void doSplit(String split, String processInstId, String userNo, String taskId) throws Exception;

	List<TaskSplit> findCompilationListByTaskBookType(String taskBookType) throws Exception;

	List<TaskSplit> findCompilationListByTaskBookIdAndType(String taskBookType, String taskBookId) throws Exception;

	TaskSplit findCompilationListById(String id) throws Exception;
}
