package com.ht.persistence.dao.impl.drawtask.tasksplit;

import java.util.List;

import com.ht.common.util.LogHelper;
import com.ht.persistence.dao.impl.base.BaseDaoImpl;
import com.ht.persistence.dao.inter.drawtask.tasksplit.TaskSplitDao;
import com.ht.persistence.model.drawtask.tasksplit.TaskSplit;

public class TaskSplitDaoImpl extends BaseDaoImpl implements TaskSplitDao
{

	/**
	 * 拆分
	 * @return 编绘任务拆分
	 * @throws Exception
	 */
	@Override
	public void doSplit(TaskSplit taskSplit) throws Exception
	{
		try
		{
			this.save(taskSplit);
		}
		catch (Exception e)
		{
			LogHelper.ERROR.log(e.getMessage());
		}

	}

	/**
	 * 拆分校验
	 * @return 数据是否存在
	 * @throws Exception
	 */
	@Override
	public Boolean ifExist(TaskSplit taskSplit) throws Exception
	{
		try
		{
			String[] paramNames =
			{ "taskBookPlanId", "taskType" };
			String[] values =
			{ taskSplit.getTaskBill().getId(), taskSplit.getTaskType() };
			@SuppressWarnings("unchecked")
			List<TaskSplit> list = this.findByNamedQueryAndNamedParam("CheckSplitIfExist", paramNames, values);
			if (list.size() > 0) { return true; }
			return false;
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			return null;
		}
	}

	/**
	 * 编绘管理各子查询
	 * @return 所有实体对象集合
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<TaskSplit> findCompilationList(TaskSplit taskSplit) throws Exception
	{
		String[] params =
		{ "flag" };
		String[] values =
		{ taskSplit.getFlag() };
		List<TaskSplit> list = this.findByNamedQueryAndNamedParam("findCompilationList", params, values);
		return list;
	}

	/**
	 * 编绘管理各子查询
	 * @return 所有实体对象集合
	 */
	@SuppressWarnings("unchecked")
	@Override
	public TaskSplit findCompilationListById(TaskSplit taskSplit) throws Exception
	{
		String[] params =
		{ "id" };
		String[] values =
		{ taskSplit.getId() };
		List<TaskSplit> list = this.findByNamedQueryAndNamedParam("findCompilationListById", params, values);
		if (list != null)
		{
			if (list.size() > 0) { return list.get(0); }
		}
		return null;
	}

	/**
	 * 根据任务书类型获取拆分
	 * @param taskSplit 拆分实体
	 * @return 拆分列表
	 * @throws Exception
	 */
	@Override
	public List<TaskSplit> findCompilationListByTaskBookType(TaskSplit taskSplit) throws Exception
	{
		String[] params =
		{ "taskBookType" };
		String[] values =
		{ taskSplit.getTaskBill().getFlag() };
		List<TaskSplit> list = this.findByNamedQueryAndNamedParam("findCompilationListByTaskBookType", params, values);
		return list;
	}

	@Override
	public List<TaskSplit> findCompilationListByTaskBookIdAndType(TaskSplit taskSplit) throws Exception
	{
		String[] params =
		{ "taskBookType", "taskBookId" };
		String[] values =
		{ taskSplit.getTaskBill().getFlag(), taskSplit.getTaskBill().getTaskBookId() };
		List<TaskSplit> list = this.findByNamedQueryAndNamedParam("findCompilationListByTaskBookIdAndType", params, values);
		return list;
	}
}
