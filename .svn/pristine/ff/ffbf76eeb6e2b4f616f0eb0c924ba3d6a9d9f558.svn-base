package com.ht.persistence.dao.impl.system.workflow.process;

import java.util.List;

import com.ht.persistence.dao.impl.base.BaseDaoImpl;
import org.springframework.util.CollectionUtils;

import com.ht.persistence.dao.inter.system.workflow.process.ProcessLogDao;
import com.ht.persistence.model.system.workflow.process.ProcessLog;

public class ProcessLogDaoImpl extends BaseDaoImpl implements ProcessLogDao{

	@Override
	public ProcessLog getProcessLog(ProcessLog processLog) throws Exception {
		List<ProcessLog> result = null;
		try {
			//获取
			result = this.findByNamedQueryAndNamedParam("findProcessLogById", "id", processLog.getId());
			if (CollectionUtils.isEmpty(result)) {
				return null;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
		return result.get(0);
	}

	@Override
	public List<ProcessLog> getProcessLogList() throws Exception {
		List<ProcessLog> list = null;
		try {
			//获取目录明细列表
			list = this.findByNamedQuery("findProcessLogList");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
		return list;
	}

	@Override
	public void addProcessLog(ProcessLog processLog) throws Exception {
		this.save(processLog);
	}

	@Override
	public void modifyProcessLog(ProcessLog processLog) throws Exception {
		this.update(processLog);
	}

	@Override
	public void delProcessLog(ProcessLog processLog) throws Exception {
		this.delete(processLog);
	}

}
