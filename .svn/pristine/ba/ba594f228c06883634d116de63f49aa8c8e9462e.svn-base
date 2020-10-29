package com.ht.persistence.dao.impl.drawtask.taskbill;

import java.util.List;

import com.ht.persistence.dao.impl.base.BaseDaoImpl;
import com.ht.persistence.dao.inter.drawtask.taskbill.TaskBillDao;
import com.ht.persistence.model.drawtask.taskbill.TaskBill;
import com.ht.persistence.model.drawtask.taskbook.relation.TaskBookPlanRelation;
import com.ht.persistence.model.drawtask.tasksplit.TaskSplit;

public class TaskBillDaoImpl extends BaseDaoImpl implements TaskBillDao {

	/**s
	  * 查询
	  * @return 编绘任务清单海图编绘list
	  * @throws Exception
	  */
		@Override
	public List<TaskBill> getList(TaskBill taskBill) throws Exception {
		try{
			@SuppressWarnings("unchecked")
			List<TaskBill> list = this.findByNamedQueryAndNamedParam("findTaskBillList","flag",taskBill.getFlag());
			return list;
		}catch(Exception e){
			System.out.println(e.getMessage());
			return null;
		}
	}

	@Override
	public List<TaskBill> getListByPlanIdAndType(TaskBill taskBill)
			throws Exception {
		String[] names = {"flag","taskBookId"};
		String[] params = {taskBill.getFlag(),taskBill.getTaskBookId().trim()};
		try{
			@SuppressWarnings("unchecked")
			List<TaskBill> list = this.findByNamedQueryAndNamedParam("findListByPlanIdAndFlag",names,params);
			return list;
		}catch(Exception e){
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	@Override
	public List<TaskBill> findPlanByTaskId(TaskBookPlanRelation tbpr) throws Exception {
		List<TaskBill> list = this.findByNamedQueryAndNamedParam("findPlanByTaskId","taskId",tbpr.getTaskbookId());
		return list;
	}
	
	@Override
	public List<TaskSplit> findPlanTypeByTask(TaskSplit taskSplit) throws Exception{
		List<TaskSplit> taskSplitList = this.findByNamedQueryAndNamedParam("findPlanTypeByTask","taskPlanId",taskSplit.getTaskBookPlanId());
		return taskSplitList;
	}

	@Override
	public List<TaskBill> findPlanByPlanId(TaskBill taskBill) throws Exception {
		List<TaskBill> list = this.findByNamedQueryAndNamedParam("findPlanByPlanId","planId",taskBill.getPlanId());
		return list;
	}

	@Override
	public List<TaskBill> findListByTaskBookId(TaskBill taskBill)
			throws Exception {
		List<TaskBill> list = this.findByNamedQueryAndNamedParam("findListByTaskBookId","taskBookId",taskBill.getTaskBookId());
		return list;
	}
}
