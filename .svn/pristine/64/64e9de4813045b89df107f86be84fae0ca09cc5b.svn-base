package com.ht.persistence.dao.inter.drawtask.taskbook.create;

import java.util.List;

import com.ht.persistence.model.background.dicdata.basedata.BaseData;
import com.ht.persistence.model.drawtask.taskbook.create.CreateTask;
import com.ht.persistence.model.drawtask.taskbook.create.ViewCreateTask;

/**
 * CreateTaskDao
 * @author yp
 *
 */
public interface CreateTaskDao {


	CreateTask getTaskById(String id);
	
	/**
	 * 查找实体对象
	 * 
	 * @return 实体对象，若不存在则返回null
	 */
	List<ViewCreateTask> getTaskList(String parentTaskBookId) throws Exception;

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
	void modify(CreateTask task) throws Exception;
	
	/**
	 * 编绘任务编辑
	 * 
	 * @return
	 */
	void edit(CreateTask task) throws Exception;
	
	/**
	 * 删除任务
	 */
	void delete(CreateTask task) throws Exception;

	/**
	 * 通过图号获取任务
	 * @param mapNo
	 * @param status 
	 * @param childTskType 
	 * @return
	 */
	CreateTask getTaskByMapNo(String mapNo, String childTskType, String status) throws Exception;

	/**
	 * 根据状态和类型获取任务
	 * @param status
	 * @param type
	 * @return
	 */
	List<CreateTask> getTaskListByStatusAndType(String status, String type);

	/**
	 * 通过图号和类型获取数据
	 * @param draw
	 * @param type
	 * @return
	 */
	List<CreateTask> getTaskListByDrawAndType(String draw, String type);
	
	
}
