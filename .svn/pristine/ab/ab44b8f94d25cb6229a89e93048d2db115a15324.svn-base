package com.ht.persistence.dao.impl.system.assign;

import java.util.List;

import com.ht.persistence.dao.impl.base.BaseDaoImpl;
import com.ht.persistence.dao.inter.system.assign.AssignUserRecordDao;
import com.ht.persistence.model.system.workflow.assign.AssignUserRecord;
/**
 *  用户指派记录DaoImpl
 * @author huodesheng
 *
 */
public class AssignUserRecordDaoImpl extends BaseDaoImpl implements AssignUserRecordDao {

	@Override
	public void addAssignRecord(AssignUserRecord assignUserRecord) throws Exception {
		// TODO Auto-generated method stub
		this.save(assignUserRecord);
	}

	@Override
	public void updateAssignRecord(AssignUserRecord assignUserRecord) throws Exception{
		// TODO Auto-generated method stub
		this.update(assignUserRecord);
	}

	@Override
	public void deleteAssignRecord(AssignUserRecord assignUserRecord) throws Exception{
		// TODO Auto-generated method stub
		this.delete(assignUserRecord);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AssignUserRecord> getAll() throws Exception{
		List<AssignUserRecord> list = this.findByNamedQuery("getAll");
		return list;
	}

	@Override
	public List<AssignUserRecord> getRecordByProcessInstIdAndHisUser(
			String processInstId, String hisUser) throws Exception{
		String[] paramName={"processInstId","hisUser"};
		String[] value={processInstId,hisUser};
		List<AssignUserRecord> list = this.findByNamedQueryAndNamedParam("getRecordByProcessInstIdAndHisUser", paramName, value);
		return list;
	}

	@Override
	public List<AssignUserRecord> getRecordByProcessInstIdAndType(
			String processInstId, String type) throws Exception{
		String[] paramName={"processInstId","type"};
		String[] value={processInstId,type};
		List<AssignUserRecord> list = this.findByNamedQueryAndNamedParam("getRecordByProcessInstIdAndType", paramName, value);
		return list;
	}

	@Override
	public List<AssignUserRecord> getRecordByProcessInstIdAndAssignUser(
			String processInstId, String assignUser) throws Exception {
		String[] paramName={"processInstId","assignUser"};
		String[] value={processInstId,assignUser};
		List<AssignUserRecord> list = this.findByNamedQueryAndNamedParam("getRecordByProcessInstIdAndAssignUser", paramName, value);
		return list;
	}

}
