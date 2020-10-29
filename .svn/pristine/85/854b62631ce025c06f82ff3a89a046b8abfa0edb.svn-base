package com.ht.service.impl.system.workflow.process;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SQLQuery;

import com.ht.common.util.DataConverter;
import com.ht.common.util.GenerateSequenceUtil;
import com.ht.common.util.LogHelper;
import com.ht.exception.DBException;
import com.ht.persistence.dao.inter.system.workflow.process.ProcessTaskRelationDao;
import com.ht.persistence.model.background.organization.employee.UserOrgRole;
import com.ht.persistence.model.complication.formprop.FormValue;
import com.ht.persistence.model.system.workflow.process.ProcessTaskRelation;
import com.ht.service.inter.system.workflow.process.ProcessTaskRelationService;

public class ProcessTaskRelationServiceImpl implements ProcessTaskRelationService{
	
	/**
	 * 注入Dao
	 */
	@Resource(name="processTaskRelationDao")
	private ProcessTaskRelationDao processTaskRelationDao;

	@Override
	public void addProcessTaskRelation(String processTaskRelationParam)
			throws Exception {
		ProcessTaskRelation processTaskRelation = (ProcessTaskRelation) DataConverter.convertJson2Object(processTaskRelationParam, ProcessTaskRelation.class);
		String taskDefId = processTaskRelation.getTaskDefId();
		String processDefId = processTaskRelation.getProcessDefId();
		
		int count = processTaskRelationDao.delByTaskDefIdAndProcessDefId(taskDefId, processDefId);
		System.out.println(count);
		String relationId = processTaskRelation.getRelationId();
		if(relationId.length()>0){
			if(relationId != null){
				if(relationId.split(",").length>0){
					for (int i = 0; i < relationId.split(",").length; i++) {
						ProcessTaskRelation p =  new ProcessTaskRelation();
						p.setId(GenerateSequenceUtil.generateSequenceNo());
						p.setProcessDefId(processTaskRelation.getProcessDefId());
						p.setProcessDefKey(processTaskRelation.getProcessDefKey());
						p.setTaskDefId(processTaskRelation.getTaskDefId());
						p.setRelationId(relationId.split(",")[i]);
						processTaskRelationDao.addProcessTaskRelation(p);
					}
				}
			}
		}
	}

	@Override
	public void modifyProcessTaskRelation(String processTaskRelationParam)
			throws Exception {
		ProcessTaskRelation processTaskRelation = (ProcessTaskRelation) DataConverter.convertJson2Object(processTaskRelationParam, UserOrgRole.class);
		try {
			processTaskRelationDao.modifyProcessTaskRelation(processTaskRelation);
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(), e);
			// 抛出异常
			throw new DBException();
		}
	}

	@Override
	public void delProcessTaskRelation(String processTaskRelationParam)
			throws Exception {
		ProcessTaskRelation processTaskRelation = (ProcessTaskRelation) DataConverter.convertJson2Object(processTaskRelationParam, UserOrgRole.class);
		try {
			processTaskRelationDao.delProcessTaskRelation(processTaskRelation);
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(), e);
			// 抛出异常
			throw new DBException();
		}
	}

	@Override
	public List<ProcessTaskRelation> findByTaskDefId(String taskDefId,String processDefId)
			throws Exception {
		ProcessTaskRelation p = new ProcessTaskRelation();
		p.setTaskDefId(taskDefId);
		p.setProcessDefId(processDefId);
		return processTaskRelationDao.findByTaskDefId(p);
	}
}
