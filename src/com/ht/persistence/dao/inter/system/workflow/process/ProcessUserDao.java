package com.ht.persistence.dao.inter.system.workflow.process;

import java.util.List;

import com.ht.persistence.model.system.workflow.process.ProcessUser;

public interface ProcessUserDao {
	/**
	 * 查找实体对象
	 * @param id ID
	 * @return 实体对象，若不存在则返回null
	 */
	ProcessUser getProcessUser(ProcessUser processUser) throws Exception;
	
	/**
	 * 查找所有实体对象集合
	 * @return 所有实体对象集合
	 */
	List<ProcessUser> getProcessUserList() throws Exception;

	/**
	* 新增
	 */
	void addProcessUser(ProcessUser processUser) throws Exception;

	/**
	 * 更新实体对象
	 * @param entity 实体对象
	 * @return void    返回类型 
	 */
	void modifyProcessUser(ProcessUser processUser) throws Exception;
	
	/**
	 * 删除实体对象
	 * @param entity 实体对象
	 * @return void    返回类型 
	 */
	void delProcessUser(ProcessUser processUser) throws Exception;
	/**
	 * 返回关联用户
	 * @param processUser
	 * @return
	 * @throws Exception
	 */
	List<ProcessUser> findProcessUserByProcessId(ProcessUser processUser) throws Exception;

	/**
	 * 根据用户Id获取关联用户 
	 * @param processUser
	 * @return
	 * @throws Exception
	 */
	List<ProcessUser> findProcessUserByUserId(ProcessUser processUser) throws Exception;

	void delProcessUser(List<ProcessUser> processUsers) throws Exception;
}
