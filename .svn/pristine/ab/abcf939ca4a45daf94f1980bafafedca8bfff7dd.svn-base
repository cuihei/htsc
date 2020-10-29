package com.ht.persistence.dao.impl.system.workflow.process;

import java.util.List;

import org.hibernate.SQLQuery;
import com.ht.persistence.dao.impl.base.BaseDaoImpl;
import org.springframework.util.CollectionUtils;

import com.ht.persistence.dao.inter.system.workflow.process.ProcessUserDao;
import com.ht.persistence.model.system.workflow.process.ProcessUser;

public class ProcessUserDaoImpl extends BaseDaoImpl implements ProcessUserDao {

	@Override
	public ProcessUser getProcessUser(ProcessUser processUser) throws Exception {
		List<ProcessUser> result = null;
		try {
			//获取
			result = this.findByNamedQueryAndNamedParam("findProcessUserById", "id", processUser.getId());
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
	public List<ProcessUser> findProcessUserByProcessId(ProcessUser processUser) throws Exception {
		List<ProcessUser> result = null;
		try {
			//获取
			result = this.findByNamedQueryAndNamedParam("findProcessUserByProcessId", "processDefId", processUser.getProcessDefId());
			if (CollectionUtils.isEmpty(result)) {
				return null;
			}
		} catch (Exception e) {
			return null;
		}
		return result;
	}

	@Override
	public List<ProcessUser> getProcessUserList() throws Exception {
		List<ProcessUser> list = null;
		try {
			//获取目录明细列表
			list = this.findByNamedQuery("findProcessUserList");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
		return list;
	}

	@Override
	public void addProcessUser(ProcessUser processUser) throws Exception {
		this.save(processUser);
	}

	@Override
	public void modifyProcessUser(ProcessUser processUser) throws Exception {
		this.update(processUser);
	}

	@Override
	public void delProcessUser(ProcessUser processUser) throws Exception {
		this.delete(processUser);
	}

	/**
	 * 根据用户Id获取关联用户 
	 * @param processUser
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<ProcessUser> findProcessUserByUserId(ProcessUser processUser)
			throws Exception {
		SQLQuery query = this.getSession().createSQLQuery("select * from PROCESS_USER t where t.user_no = ? ");
		query.setString(0,processUser.getUserNo());
		query.addEntity(ProcessUser.class);
		@SuppressWarnings("unchecked")
		List<ProcessUser> list =  query.list();
		return list;
	}

	@Override
	public void delProcessUser(List<ProcessUser> processUsers) throws Exception {
		this.deleteAll(processUsers);
	}
}
