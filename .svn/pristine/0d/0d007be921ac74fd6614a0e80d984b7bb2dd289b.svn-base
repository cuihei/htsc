package com.ht.service.inter.drawtask.taskbook.create;

import java.io.File;
import java.util.List;

import com.ht.persistence.model.background.dicdata.basedata.BaseData;
import com.ht.persistence.model.drawtask.taskbook.create.CreateTask;
import com.ht.persistence.model.drawtask.taskbook.create.ViewCreateTask;

public interface CreateTaskService
{

	/**
	 * 查找实体对象
	 * @return 实体对象，若不存在则返回null
	 */
	List<ViewCreateTask> getTaskList(String parentTaskBookId) throws Exception;
	
	/**
	 * 获取一条编绘任务
	 * @return
	 * @throws Exception
	 */
	CreateTask getTask(String id) throws Exception;
	
	
	/**
	 * 编绘任务联动
	 * 
	 * @return
	 */
	List<BaseData> getChildTaskType(String taskType) throws Exception;
	/**
	 * 编绘任务新建保存
	 * 
	 * @return
	 */
	void modify(String taskParam) throws Exception;
	/**
	 * 编绘任务导入
	 * @throws Exception 
	 */
	String uploadFile(String parentTaskId,File file, String fileName) throws Exception;
	/**
	 * 删除编绘任务
	 * @param id
	 * @return
	 * @throws Exception
	 */
	String delete(String id) throws Exception;
	/**
	 * 编辑编绘任务
	 * @param id
	 * @return
	 * @throws Exception
	 */
	String edit(String taskParam) throws Exception;
}
