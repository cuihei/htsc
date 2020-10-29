package com.ht.service.impl.system.workflow.process;

import java.util.List;

import javax.annotation.Resource;

import com.ht.common.exception.CommonException;
import com.ht.common.util.GenerateSequenceUtil;
import com.ht.persistence.dao.inter.system.workflow.process.ProcessDelegateDao;
import com.ht.persistence.model.system.workflow.process.ProcessDelegate;
import com.ht.service.inter.system.workflow.process.ProcessDelegateService;

public class ProcessDelegateServiceImpl implements ProcessDelegateService
{
	
	@Resource
	ProcessDelegateDao processDelegateDao;
	
	@Override
	public void add(String groupId, String userId)
	{
		ProcessDelegate object = new ProcessDelegate();
		object.setId(GenerateSequenceUtil.generateSequenceNo());
		object.setGroupId(groupId);
		object.setUserId(userId);
		processDelegateDao.add(object);
	}

	@Override
	public void addAll(String groupId,List<String> userIds) throws Exception
	{
		try
		{
			List<ProcessDelegate> list = processDelegateDao.getListByGroupId(groupId);
			processDelegateDao.deleteAll(list);
			for (int i = 0; i < userIds.size(); i++)
			{
				String userId = userIds.get(i);
				ProcessDelegate object = new ProcessDelegate();
				object.setId(GenerateSequenceUtil.generateSequenceNo());
				object.setGroupId(groupId);
				object.setUserId(userId);
				processDelegateDao.add(object);
			}
		}
		catch (Exception e)
		{
			throw new CommonException("数据库操作错误，插入失败");
		}
		
	}

	@Override
	public void deleteAll(List<ProcessDelegate> object)
	{
		processDelegateDao.deleteAll(object);
	}

	@Override
	public List<ProcessDelegate> getListByGroupId(String groupId)
	{
		return processDelegateDao.getListByGroupId(groupId);
	}

}
