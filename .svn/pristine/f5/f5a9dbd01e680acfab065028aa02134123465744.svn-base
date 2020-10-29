package com.ht.service.impl.drawtask.taskbook.relation;

import java.util.List;

import javax.annotation.Resource;

import com.ht.persistence.dao.inter.drawtask.taskbook.relation.TaskBookPlanRelationDao;
import com.ht.persistence.model.drawtask.taskbook.relation.TaskBookPlanRelation;
import com.ht.service.inter.drawtask.taskbook.relation.TaskBookPlanRelationService;

public class TaskBookPlanRelationServiceImpl implements TaskBookPlanRelationService {
	@Resource(name = "taskBookPlanRelationDao") 
	private TaskBookPlanRelationDao taskBookPlanRelationDao;
	
	@Override
	public List<TaskBookPlanRelation> findList() throws Exception {
		// TODO Auto-generated method stub
		return taskBookPlanRelationDao.findList();
	}

	@Override
	public List<TaskBookPlanRelation> findListByBookId(String taskBookId)
			throws Exception {
		// TODO Auto-generated method stub
		return taskBookPlanRelationDao.findListByBookId(taskBookId);
	}

	@Override
	public List<TaskBookPlanRelation> findListByPlanId(String PlanId)
			throws Exception {
		// TODO Auto-generated method stub
		return taskBookPlanRelationDao.findListByPlanId(PlanId);
	}

	@Override
	public void addRelation(TaskBookPlanRelation taskBookPlanRelation)
			throws Exception {
		// TODO Auto-generated method stub
		taskBookPlanRelationDao.addRelation(taskBookPlanRelation);
	}

	@Override
	public void updateRelation(TaskBookPlanRelation taskBookPlanRelation)
			throws Exception {
		// TODO Auto-generated method stub
		taskBookPlanRelationDao.updateRelation(taskBookPlanRelation);
	}

	@Override
	public void delRelationByBookId(String taskBookId) throws Exception {
		// TODO Auto-generated method stub
		taskBookPlanRelationDao.delRelationByBookId(taskBookId);
	}

	@Override
	public void delRelationByPlanId(String planId) throws Exception {
		// TODO Auto-generated method stub
		taskBookPlanRelationDao.delRelationByPlanId(planId);
	}

}
