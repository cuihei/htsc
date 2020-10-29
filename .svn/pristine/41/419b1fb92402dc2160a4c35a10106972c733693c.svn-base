package com.ht.service.inter.system.workflow.process;

import java.util.List;

import org.activiti.engine.repository.ProcessDefinition;

import com.ht.persistence.model.system.workflow.process.ProcessLogDetail;
import com.ht.persistence.model.system.workflow.process.ProcessUser;

public interface ProcessUserService {
	/**
	 * 查找实体对象
	 * @param id 
	 * @return 实体对象，若不存在则返回null
	 */
	ProcessUser getProcessUserById(String id) throws Exception;
	
	/**
	 * 查找所有实体对象集合 
	 * @return 所有实体对象集合
	 */
	List<ProcessUser> getProcessUserList() throws Exception;
	

	/**
	 * 新增或更新实体对象
	 * @param String
	 * @return 
	 */
	void addOrUodateProcessUser(String processUser) throws Exception;

	/**
	 * 删除实体对象
	 * @param id 目录ID
	 */
	void delProcessUser(String ids) throws Exception;
	/**
	 * 返回绑定用户
	 * @param flowId
	 * @return
	 * @throws Exception
	 */
	public List<ProcessUser> getProcessUserByProcessId(String flowId) throws Exception;

	public void addUser(List<ProcessDefinition> infos, String users) throws Exception;
}
