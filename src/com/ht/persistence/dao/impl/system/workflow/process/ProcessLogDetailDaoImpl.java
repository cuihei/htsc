package com.ht.persistence.dao.impl.system.workflow.process;

import java.util.List;

import org.springframework.util.CollectionUtils;

import com.ht.persistence.dao.impl.base.BaseDaoImpl;
import com.ht.persistence.dao.inter.system.workflow.process.ProcessLogDetailDao;
import com.ht.persistence.model.system.workflow.process.ProcessLogDetail;

public class ProcessLogDetailDaoImpl extends BaseDaoImpl implements ProcessLogDetailDao{

	@Override
	public ProcessLogDetail getProcessLogDetail(ProcessLogDetail processLogDetail)
			throws Exception {
		List<ProcessLogDetail> result = null;
		try {
			//获取
			result = this.findByNamedQueryAndNamedParam("findProcessLogDetailById", "id", processLogDetail.getId());
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
	public List<ProcessLogDetail> getProcessLogDetailByProcessLogId(ProcessLogDetail processLogDetail)
			throws Exception {
		List<ProcessLogDetail> result = null;
		try {
			//获取
			result = this.findByNamedQueryAndNamedParam("findProcessLogDetailByLogId", "processLogId", processLogDetail.getProcessLogId());
			if (CollectionUtils.isEmpty(result)) {
				return null;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
		return result;
	}

	@Override
	public List<ProcessLogDetail> getProcessLogDetailList() throws Exception {
		List<ProcessLogDetail> list = null;
		try {
			//获取目录明细列表
			list = this.findByNamedQuery("findProcessLogDetailList");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
		return list;
	}
	
	@Override
	public List<ProcessLogDetail> getProcessLogDetailListByProcessInstId(String processInstId) throws Exception {
		List<ProcessLogDetail> list = null;
		try {
			//获取目录明细列表
			list = this.findByNamedQueryAndNamedParam("getProcessLogDetailListByProcessInstId","processInstId",processInstId);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
		return list;
	}

	@Override
	public void addProcessLogDetail(ProcessLogDetail processLogDetail)
			throws Exception {
		List<ProcessLogDetail> list = this.findByNamedQueryAndNamedParam("getProcessLogDetailListByProcessInstId","processInstId",processLogDetail.getProcessInstId());
		this.deleteAll(list);
		this.save(processLogDetail);
	}

	@Override
	public void modifyProcessLogDetail(ProcessLogDetail processLogDetail)
			throws Exception {
		this.update(processLogDetail);
	}

	@Override
	public void delProcessLogDetail(ProcessLogDetail processLogDetail)
			throws Exception {
		this.delete(processLogDetail);
	}

}
