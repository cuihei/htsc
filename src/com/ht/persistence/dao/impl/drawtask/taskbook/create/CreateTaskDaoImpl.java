package com.ht.persistence.dao.impl.drawtask.taskbook.create;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.ht.persistence.dao.impl.base.BaseDaoImpl;
import com.ht.persistence.dao.inter.drawtask.taskbook.create.CreateTaskDao;
import com.ht.persistence.model.background.dicdata.basedata.BaseData;
import com.ht.persistence.model.drawtask.taskbook.create.CreateTask;
import com.ht.persistence.model.drawtask.taskbook.create.ViewCreateTask;
import com.ht.persistence.model.system.notice.Notice;
import com.ht.persistence.model.system.workflow.publish.VProcessDetail;

/**
 * 编绘任务书
 * @author huodesheng
 */
public class CreateTaskDaoImpl extends BaseDaoImpl implements CreateTaskDao
{
	@Override
	public CreateTask getTaskById(String id)
	{
		List<CreateTask> result = null;
		if (StringUtils.isNotEmpty(id))
		{
			result = this.findByNamedQueryAndNamedParam("getTaskById", "id", id);
		}
		if (result != null)
		{
			if (result.size()>0)
			{
				return result.get(0);
			}
		}
		
	     /////////////////////////////////////////////////////////	

		
		
		
		
		
		/////////////////////////////////////////////////////////
		
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ViewCreateTask> getTaskList(String parentTaskBookId) throws Exception
	{
		List<ViewCreateTask> result = null;
		if (StringUtils.isNotEmpty(parentTaskBookId))
		{
			result = this.findByNamedQueryAndNamedParam("getTaskListByTaskBookId", "parentTaskBookId", parentTaskBookId);
		}
		else
		{
			result = this.findByNamedQuery("getViewTaskList");
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BaseData> getChildTaskType(String taskType) throws Exception
	{
		List<BaseData> result = null;
		try
		{
			String sql = "select * from BASE_DATA where type_id = 12281511512320034 and code like '" + taskType + "%' order by num";
			result = this.getSession().createSQLQuery(sql).addEntity(BaseData.class).list();
		}
		catch (Exception e)
		{
			return null;
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void modify(CreateTask task) throws Exception
	{
		this.save(task);
	}

	@Override
	public void delete(CreateTask task) throws Exception {
		// TODO Auto-generated method stub
		super.delete(task);
	}

	@Override
	public void edit(CreateTask task) throws Exception {
		// TODO Auto-generated method stub
		this.update(task);
	}

	/**
	 * 通过图号获取任务
	 */
	@SuppressWarnings("unchecked")
	@Override
	public CreateTask getTaskByMapNo(String mapNo,String childTskType, String status) {
		
		String[] names = {"mapNo","childTskType","status"};
		String[] params = {mapNo,childTskType,status};
		
		List<CreateTask> ctList = this.findByNamedQueryAndNamedParam("getTaskByMapNo", names, params);
		if(ctList.size() > 0){
			return ctList.get(0);
		}
		return null;
	}

	/**
	 * 根据状态和类型获取任务
	 */
	@Override
	public List<CreateTask> getTaskListByStatusAndType(String status, String type) {
		String[] names = {"status","type"};
		String[] params = {status,type};
		@SuppressWarnings("unchecked")
		List<CreateTask> ctList = this.findByNamedQueryAndNamedParam("getTaskListByStatusAndType", names, params);
		return ctList;
	}

	/**
	 * 通过图号和类型获取任务
	 */
	@Override
	public List<CreateTask> getTaskListByDrawAndType(String draw, String type) {
		String[] names = {"mapNo","type"};
		String[] params = {draw,type};
		@SuppressWarnings("unchecked")
		List<CreateTask> ctList = this.findByNamedQueryAndNamedParam("getTaskListByDrawAndType", names, params);
		return ctList;
	}

}
