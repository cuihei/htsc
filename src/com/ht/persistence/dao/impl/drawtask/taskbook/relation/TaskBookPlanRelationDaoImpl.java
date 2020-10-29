package com.ht.persistence.dao.impl.drawtask.taskbook.relation;

import java.util.List;

import com.ht.persistence.dao.impl.base.BaseDaoImpl;

import com.ht.persistence.dao.inter.drawtask.taskbook.relation.TaskBookPlanRelationDao;
import com.ht.persistence.model.background.dicdata.defectform.DefectForm;
import com.ht.persistence.model.drawtask.taskbook.relation.TaskBookPlanRelation;

public class TaskBookPlanRelationDaoImpl extends BaseDaoImpl implements TaskBookPlanRelationDao {

	@Override
	public List<TaskBookPlanRelation> findList() throws Exception {
		try {
			@SuppressWarnings("unchecked")
			List<TaskBookPlanRelation> list = this.findByNamedQuery("findTaskBookPlanReleations");
			return list;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	@Override
	public List<TaskBookPlanRelation> findListByBookId(String taskBookId)
			throws Exception {
		try {
			@SuppressWarnings("unchecked")
			List<TaskBookPlanRelation> result = this.getSession().createQuery(" from TaskBookPlanRelation where taskbookId= :taskbookId").setParameter("taskbookId", taskBookId).list();
			return result;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TaskBookPlanRelation> findListByPlanId(String PlanId)
			throws Exception {
		try {
			List<TaskBookPlanRelation>	list = this.getSession().createQuery(" from TaskBookPlanRelation where planId= :planId").setParameter("planId", PlanId).list();
			return list;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	@Override
	public void addRelation(TaskBookPlanRelation taskBookPlanRelation)
			throws Exception {
		this.save(taskBookPlanRelation);
	}

	@Override
	public void updateRelation(TaskBookPlanRelation taskBookPlanRelation)
			throws Exception {
		taskBookPlanRelation = (TaskBookPlanRelation) this.merge(taskBookPlanRelation);
		this.update(taskBookPlanRelation);
	}

	@Override
	public void delRelationByBookId(String taskBookId) throws Exception {
		// TODO Auto-generated method stub
		List<TaskBookPlanRelation> list = this.findListByBookId(taskBookId);
		for (TaskBookPlanRelation taskBookPlanRelation : list) {
			this.delete(taskBookPlanRelation);
		}
	}

	@Override
	public void delRelationByPlanId(String planId) throws Exception {
		// TODO Auto-generated method stub
		List<TaskBookPlanRelation> list = this.findListByPlanId(planId);
		for (TaskBookPlanRelation taskBookPlanRelation : list) {
			this.delete(taskBookPlanRelation);
		}
	}

}
