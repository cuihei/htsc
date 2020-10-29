package com.ht.persistence.dao.impl.system.workflow.process;

import java.util.List;

import org.hibernate.SQLQuery;

import com.ht.persistence.dao.impl.base.BaseDaoImpl;
import com.ht.persistence.dao.inter.system.workflow.process.ProcessTaskRelationDao;
import com.ht.persistence.model.background.organization.employee.User;
import com.ht.persistence.model.complication.formprop.FormValue;
import com.ht.persistence.model.system.workflow.process.ProcessTaskRelation;

public class ProcessTaskRelationDaoImpl extends BaseDaoImpl implements ProcessTaskRelationDao{


	@Override
	public void addProcessTaskRelation(ProcessTaskRelation processTaskRelation)
			throws Exception {
		this.save(processTaskRelation);
		
	}

	@Override
	public void modifyProcessTaskRelation(
			ProcessTaskRelation processTaskRelation) throws Exception {
		this.update(processTaskRelation);
		
	}

	@Override
	public void delProcessTaskRelation(ProcessTaskRelation processTaskRelation)
			throws Exception {
		this.delete(processTaskRelation);
	}

	/***
	 * 根据流程流程定义ID和任务定义ID查询关联数据
	 * @return 返回关联数据列表
	 */
	@Override
	public List<ProcessTaskRelation> findByTaskDefId(
			ProcessTaskRelation processTaskRelation) throws Exception {
		@SuppressWarnings("unchecked")
		//执行查询操作
		String[] params = {"taskDefId","processDefKey"};
		String[] values={processTaskRelation.getTaskDefId(),processTaskRelation.getProcessDefId().split(":")[0]};
		List<ProcessTaskRelation> result = this.findByNamedQueryAndNamedParam("findByTaskDefId", params, values);
		if(result.size()>0){
			return result;
		}
		return null;
	}

	/***
	 * 删除关联数据
	 * @return 删除条数
	 */
	@Override
	public int  delByTaskDefIdAndProcessDefId(String taskDefId,
			String processDefId) throws Exception {
		SQLQuery query = this.getSession().createSQLQuery("delete PROCESS_TASK_RELATION where TASK_DEF_ID = ? and PROCESS_DEF_ID = ? ");
		query.setString(0, taskDefId);
		query.setString(1, processDefId);
		query.addEntity(ProcessTaskRelation.class);
		int cnt = query.executeUpdate();
		return cnt;
	}

}
