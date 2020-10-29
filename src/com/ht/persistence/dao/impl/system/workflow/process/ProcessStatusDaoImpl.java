package com.ht.persistence.dao.impl.system.workflow.process;

import java.util.List;

import org.springframework.util.CollectionUtils;

import com.ht.persistence.dao.impl.base.BaseDaoImpl;
import com.ht.persistence.dao.inter.system.workflow.process.ProcessStatusDao;
import com.ht.persistence.model.system.workflow.process.ProcessStatus;

public class ProcessStatusDaoImpl extends BaseDaoImpl implements ProcessStatusDao
{

	@Override
	public ProcessStatus getProcessStatus(ProcessStatus processStatus) throws Exception
	{
		List<ProcessStatus> result = null;
		try
		{
			// 获取
			result = this.findByNamedQueryAndNamedParam("findProcessStatusById", "id", processStatus.getId());
			if (CollectionUtils.isEmpty(result)) { return null; }
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			return null;
		}
		return result.get(0);
	}

	@Override
	public ProcessStatus getProcessStatusByProAndTask(ProcessStatus processStatus) throws Exception
	{
		List<ProcessStatus> result = null;
		try
		{
			// 获取
			result = this.findByNamedQueryAndNamedParam("findProcessStatusByProAndTask", new String[]
			{ "processDefId", "taskDefId" }, new Object[]
			{ processStatus.getProcessDefId().split(":")[0], processStatus.getTaskDefId() });
			if (CollectionUtils.isEmpty(result)) { return null; }
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			return null;
		}
		return result.get(0);
	}

	@Override
	public List<ProcessStatus> getProcessStatusList() throws Exception
	{
		List<ProcessStatus> list = null;
		try
		{
			list = this.findByNamedQuery("findProcessStatusList");
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			return null;
		}
		return list;
	}

	@Override
	public void addProcessStatus(ProcessStatus processStatus) throws Exception
	{
		this.save(processStatus);
	}

	@Override
	public void modifyProcessStatus(ProcessStatus processStatus) throws Exception
	{
		this.update(processStatus);
	}

	@Override
	public void delProcessStatus(ProcessStatus processStatus) throws Exception
	{
		this.delete(processStatus);
	}

}
