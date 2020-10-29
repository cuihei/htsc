package com.ht.persistence.dao.impl.system.workflow.task;

import java.util.List;

import com.ht.persistence.dao.impl.base.BaseDaoImpl;
import com.ht.persistence.dao.inter.system.workflow.task.FlowsDao;
import com.ht.persistence.model.system.workflow.task.Flows;

public class FlowsDaoImpl extends BaseDaoImpl implements FlowsDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<Flows> getProcessFlows(Flows flows) {
		try {
			List<Flows> result = this.findByNamedQueryAndNamedParam("getProcessFlowByProcessId", "processInstId", flows.getProcessInstId());
			return result;
		} catch (Exception e) {
			return null;
		}
	}
	
	@Override
	public List<Flows> getProcessFlowsByProcessInstIdAndTypes(String processInstId,List<String> types) {
		try {
			String[] params = {"processInstId","types"};
			Object[] values = {processInstId,types};
			List<Flows> result = this.findByNamedQueryAndNamedParam("getProcessFlowsByProcessInstIdAndTypes", params, values);
			return result;
		} catch (Exception e) {
			return null;
		}
	}
	
	@Override
	public int countCompleteByMonth() throws Exception {
		List<Flows> list = null;
		list = this.findByNamedQuery("countCompleteByMonth");
		return list.size();
	}

	@Override
	public List<Flows> getProcessFlowsByProcessInstIdAndTaskDefId(
			String processInstId, List<String> taskDefId) {
		try {
			String[] params = {"processInstId","taskDefId"};
			Object[] values = {processInstId,taskDefId};
			List<Flows> result = this.findByNamedQueryAndNamedParam("getProcessFlowsByProcessInstIdAndTaskDefId", params, values);
			return result;
		} catch (Exception e) {
			return null;
		}
	}
}
