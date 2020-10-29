package com.ht.service.inter.system.workflow.process;

import java.util.List;

import com.ht.persistence.model.system.workflow.process.ProcessDelegate;

public interface ProcessDelegateService
{
	/**
	 * 增加
	 * @param object
	 */
	public void add(String groupId,String userId);
	
	/**
	 * 增加
	 * @param object
	 */
	public void addAll(String groupId, List<String> userIds) throws Exception;
	
	/**
	 * 删除
	 * @param object
	 */
	public void deleteAll(List<ProcessDelegate> object);
	
	/**
	 * 根据组ID获取
	 * @return
	 */
	public List<ProcessDelegate> getListByGroupId(String groupId);
}
