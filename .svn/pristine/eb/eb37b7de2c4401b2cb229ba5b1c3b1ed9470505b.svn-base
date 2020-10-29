package com.ht.persistence.dao.impl.system.workflow.publish;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;

import com.ht.common.util.LogHelper;
import com.ht.persistence.dao.impl.base.BaseDaoImpl;
import com.ht.persistence.dao.inter.system.workflow.publish.VProcessDetailDao;
import com.ht.persistence.model.drawtask.taskbook.relation.TaskBookPlanRelation;
import com.ht.persistence.model.system.workflow.publish.VProcessDetail;

/**
 * 工作流转日志视图DaoImpl
 * 
 * @author huodesheng
 * @date 2016/10/28
 */
public class VProcessDetailDaoImpl extends BaseDaoImpl implements
		VProcessDetailDao {

	public List<VProcessDetail> findListByUserNo(String userNo)
			throws Exception {
		try {
			String sql = "SELECT * FROM V_PROCESS_DETAIL where USERNO = :id";
			@SuppressWarnings("unchecked")
			List<VProcessDetail> list = this.getSession().createSQLQuery(sql)
					.addEntity(VProcessDetail.class)
					.setParameter("id", "liukai").list();
			return list;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			// 写入错误日志
			LogHelper.ERROR.log(e.getMessage(), e);
		}
		return null;
	}

	@Override
	public List<Object> findListByTable(String TableName, String KeyName,
			String KeyValue) throws Exception {
		String sql = "SELECT * FROM " + TableName + " where 1=1 AND " + KeyName
				+ " = " + " :keyName";
		@SuppressWarnings("unchecked")
		List<Object> list = this.getSession().createSQLQuery(sql)
				.setParameter("keyName", KeyValue).list();
		return list;
	}

	@Override
	public List<VProcessDetail> findTableNameByUserNo(String userNo)
			throws Exception {
		String sql = "select v.table_name from V_PROCESS_DETAIL as v where v.USERNO =  :userNo  group by v.table_name";
		@SuppressWarnings("unchecked")
		List<VProcessDetail> list = this.getSession().createSQLQuery(sql)
				.addEntity(VProcessDetail.class).setParameter("userNo", userNo)
				.list();
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public VProcessDetail getProcessDetailByProcessInstIdAndTaskId(
			String userNo, String tableName, String processInstId,
			String taskInstId) {
		String sql = "select * from V_PROCESS_DETAIL t where t.USERNO =  :userNo  and table_name = :tableName and process_inst_id = :processInstId and task_id= :taskId";
		SQLQuery query = this.getSession().createSQLQuery(sql);
		query.addEntity(VProcessDetail.class);
		query.setParameter("userNo", userNo);
		query.setParameter("tableName", tableName);
		query.setParameter("processInstId", processInstId);
		query.setParameter("taskId", taskInstId);
		List<VProcessDetail> list = query.list();
		if (list != null) {
			if (list.size()>0) {
				return list.get(0);
			}
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public VProcessDetail getProcessDetailByProcessInstIdAndTaskId(String processInstId,String taskInstId) {
		String sql = "select * from V_PROCESS_DETAIL t where  process_inst_id = :processInstId and task_id= :taskId";
		SQLQuery query = this.getSession().createSQLQuery(sql);
		query.addEntity(VProcessDetail.class);
		query.setParameter("processInstId", processInstId);
		query.setParameter("taskId", taskInstId);
		List<VProcessDetail> list = query.list();
		if (list != null) {
			if (list.size()>0) {
				return list.get(0);
			}
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public VProcessDetail getProcessDetailByProcessInstId(String tableName, String processInstId) {
		String sql = "select * from V_PROCESS_DETAIL t where table_name = :tableName and process_inst_id = :processInstId";
		SQLQuery query = this.getSession().createSQLQuery(sql);
		query.addEntity(VProcessDetail.class);
		query.setParameter("tableName", tableName);
		query.setParameter("processInstId", processInstId);
		List<VProcessDetail> list = query.list();
		if (list != null) {
			if (list.size()>0) {
				return list.get(0);
			}
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public VProcessDetail getProcessDetailByProcessInstId(String processInstId) {
		String sql = "select * from V_PROCESS_DETAIL t where process_inst_id = :processInstId";
		SQLQuery query = this.getSession().createSQLQuery(sql);
		query.addEntity(VProcessDetail.class);
		query.setParameter("processInstId", processInstId);
		List<VProcessDetail> list = query.list();
		if (list != null) {
			if (list.size()>0) {
				return list.get(0);
			}
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public VProcessDetail getProcessDetailByProcessInstIdAndDefKey(String processInstId,String processDefKey) {
		String sql = "select * from V_PROCESS_DETAIL t where process_inst_id = :processInstId and process_def_key = :processDefKey";
		SQLQuery query = this.getSession().createSQLQuery(sql);
		query.addEntity(VProcessDetail.class);
		query.setParameter("processInstId", processInstId);
		query.setParameter("processDefKey", processDefKey);
		List<VProcessDetail> list = query.list();
		if (list != null) {
			if (list.size()>0) {
				return list.get(0);
			}
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public VProcessDetail getProcessDetailByParentProcessInstIdAndDefKey(String parentProcessInstId,String processDefKey) {
		String sql = "select * from V_PROCESS_DETAIL t where parent_process_inst_id = :parentProcessInstId and process_def_key = :processDefKey";
		SQLQuery query = this.getSession().createSQLQuery(sql);
		query.addEntity(VProcessDetail.class);
		query.setParameter("parentProcessInstId", parentProcessInstId);
		query.setParameter("processDefKey", processDefKey);
		List<VProcessDetail> list = query.list();
		if (list != null) {
			if (list.size()>0) {
				return list.get(0);
			}
		}
		return null;
	}

	@Override
	public VProcessDetail getProcessDetailByProcessIdAndId(
			String processInstId, String taskInstId, String processDefId,
			String taskDefId) {
		String sql = "select * from V_PROCESS_DETAIL t where process_inst_id = :processInstId and process_def_id= :processDefId and task_id= :taskInstId and task_def_id= :taskDefId";
		SQLQuery query = this.getSession().createSQLQuery(sql);
		query.addEntity(VProcessDetail.class);
		query.setParameter("processInstId", processInstId);
		query.setParameter("taskInstId", taskInstId);
		query.setParameter("processDefId", processDefId);
		query.setParameter("taskDefId", taskDefId);
		List<VProcessDetail> list = query.list();
		if (list != null) {
			if (list.size()>0) {
				return list.get(0);
			}
		}
		return null;
	}
	
	@Override
	public List<VProcessDetail> getProcessDetailsByProcessIdAndId(
			String processInstId, String taskInstId, String processDefId,
			String taskDefId) {
		String sql = "select * from V_PROCESS_DETAIL t where process_inst_id = :processInstId and process_def_id= :processDefId and task_id= :taskInstId and task_def_id= :taskDefId";
		SQLQuery query = this.getSession().createSQLQuery(sql);
		query.addEntity(VProcessDetail.class);
		query.setParameter("processInstId", processInstId);
		query.setParameter("taskInstId", taskInstId);
		query.setParameter("processDefId", processDefId);
		query.setParameter("taskDefId", taskDefId);
		List<VProcessDetail> list = query.list();
		if (list != null) {
			if (list.size()>0) {
				return list;
			}
		}
		return null;
	}
	
	@Override
	public List<TaskBookPlanRelation> findTaskIdByProcessInstId(VProcessDetail vProcessDetail)
			throws Exception {
		String sql = "select new TaskBookPlanRelation(tpr.taskbookId) from TaskBookPlanRelation tpr "
				+",CompilationTask ct on tpr.id = ct.taskBookPlanId "
				+",VProcessDetail vpd on  vpd.detailRecordId = ct.id"
				+"where vpd.processInstId =  :processInstId group by  tpr.taskbookId";
		@SuppressWarnings("unchecked")
		Query query=this.getSession().createQuery(sql).setParameter("processInstId",vProcessDetail.getProcessInstId());
		List<TaskBookPlanRelation> list = query.list();
		return list;
	}

	@Override
	public VProcessDetail getByDetailRecordId(String recordId) {
		List<VProcessDetail> result = this.findByNamedQueryAndNamedParam("getByDetailRecordId", "detailRecordId", recordId);
		if(result.size() > 0){
			return result.get(0);
		}
		return null;
	}
}
