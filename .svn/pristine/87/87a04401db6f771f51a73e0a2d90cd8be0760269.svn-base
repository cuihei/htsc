package com.ht.persistence.dao.impl.system.workflow.process;

import java.util.List;

import com.ht.persistence.dao.impl.base.BaseDaoImpl;
import com.ht.persistence.dao.inter.system.workflow.process.ProcessDelegateDao;
import com.ht.persistence.model.system.workflow.process.ProcessDelegate;

public class ProcessDelegateDaoImpl extends BaseDaoImpl implements ProcessDelegateDao
{

	@Override
	public void add(ProcessDelegate object)
	{
		this.save(object);
	}

	@Override
	public void addAll(List<ProcessDelegate> object)
	{
		this.saveOrUpdateAll(object);
	}

	@Override
	public void deleteAll(List<ProcessDelegate> object)
	{
		super.deleteAll(object);
	}

	@Override
	public List<ProcessDelegate> getListByGroupId(String groupId)
	{
		List<ProcessDelegate> list = null;
		try
		{
			// 获取目录明细列表
			list = this.findByNamedQueryAndNamedParam("findListByGroupId", "groupId", groupId);
		}
		catch (Exception e)
		{
			return null;
		}
		return list;
	}
}
