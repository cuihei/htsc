package com.ht.service.impl.system.assign;

import java.util.List;

import javax.annotation.Resource;

import com.ht.persistence.dao.inter.system.assign.AssignUserRecordDao;
import com.ht.persistence.model.system.workflow.assign.AssignUserRecord;
import com.ht.service.inter.system.workflow.assign.AssignUserRecordService;
/**
 * 用户指派记录ServiceImpl
 * @author huodesheng
 *
 */
public class AssignUserRecordServiceImpl implements AssignUserRecordService {

	@Resource
	AssignUserRecordDao assignUserRecordDao;
	
	@Override
	public void addAssignRecord(AssignUserRecord assignUserRecord)
			throws Exception {
		// TODO Auto-generated method stub
		assignUserRecordDao.addAssignRecord(assignUserRecord);
	}

	@Override
	public void updateAssignRecord(AssignUserRecord assignUserRecord)
			throws Exception {
		// TODO Auto-generated method stub
		assignUserRecordDao.updateAssignRecord(assignUserRecord);
	}

	@Override
	public void deleteAssignRecord(AssignUserRecord assignUserRecord)
			throws Exception {
		assignUserRecordDao.deleteAssignRecord(assignUserRecord);
	}

	@Override
	public List<AssignUserRecord> getAll() throws Exception {
		// TODO Auto-generated method stub
		return assignUserRecordDao.getAll();
	}

	@Override
	public List<AssignUserRecord> getRecordByProcessInstIdAndHisUser(
			String processInstId, String hisUser) throws Exception {
		// TODO Auto-generated method stub
		return assignUserRecordDao.getRecordByProcessInstIdAndHisUser(processInstId, hisUser);
	}

	@Override
	public List<AssignUserRecord> getRecordByProcessInstIdAndType(
			String processInstId, String type) throws Exception {
		// TODO Auto-generated method stub
		return assignUserRecordDao.getRecordByProcessInstIdAndType(processInstId, type);
	}

}
