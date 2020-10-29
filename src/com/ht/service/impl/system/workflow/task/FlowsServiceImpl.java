package com.ht.service.impl.system.workflow.task;

import java.util.List;

import javax.annotation.Resource;

import com.ht.exception.DBException;
import com.ht.persistence.dao.inter.system.workflow.task.FlowsDao;
import com.ht.persistence.model.system.workflow.task.Flows;
import com.ht.service.inter.system.workflow.task.FlowsService;

public class FlowsServiceImpl implements FlowsService{

	@Resource
	FlowsDao flowsDao;
	
	@Override
	public List<Flows> getFlows(String processInstId) throws Exception{
		try {
			Flows flows = new Flows();
			flows.setProcessInstId(processInstId);
			return flowsDao.getProcessFlows(flows);
		} catch (Exception e) {
			throw new DBException("数据库操作错误");
		}
	}

}
