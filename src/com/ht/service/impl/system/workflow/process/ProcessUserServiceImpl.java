package com.ht.service.impl.system.workflow.process;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.Resource;

import org.activiti.engine.repository.ProcessDefinition;
import org.apache.commons.lang3.StringUtils;

import com.ht.common.util.DataConverter;
import com.ht.common.util.GenerateSequenceUtil;
import com.ht.common.util.LogHelper;
import com.ht.exception.DBException;
import com.ht.persistence.dao.inter.background.organization.employee.UserDao;
import com.ht.persistence.dao.inter.system.workflow.process.ProcessUserDao;
import com.ht.persistence.model.background.organization.employee.User;
import com.ht.persistence.model.system.workflow.process.ProcessUser;
import com.ht.service.inter.system.workflow.process.ProcessUserService;

public class ProcessUserServiceImpl implements ProcessUserService{


	
	@Resource(name="processUserDao")
	private ProcessUserDao processUserDao;
	
	@Resource(name="userDao")
    private UserDao userDao;
	
	
	@Override
	public ProcessUser getProcessUserById(String id) throws Exception {
		ProcessUser processUser = new ProcessUser();
		processUser.setId(id);
		return processUserDao.getProcessUser(processUser);
	}
	
	@Override
	public List<ProcessUser> getProcessUserByProcessId(String flowId) throws Exception {
		ProcessUser processUser = new ProcessUser();
		processUser.setProcessDefId(flowId);
		return processUserDao.findProcessUserByProcessId(processUser);
	}

	@Override
	public List<ProcessUser> getProcessUserList() throws Exception {
		try {
			// 获取所有列表
			List<ProcessUser> list = processUserDao.getProcessUserList();
			return list; 
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}

	@Override
	public void addOrUodateProcessUser(String processUser) throws Exception {
		ProcessUser pUser = (ProcessUser) DataConverter.convertJson2Object(processUser,
				ProcessUser.class);
		if(StringUtils.isBlank(pUser.getId())){
			pUser.setId(GenerateSequenceUtil.generateSequenceNo());
			// 执行添加操作
			processUserDao.addProcessUser(pUser);
		}else{
			// 执行保存操作
			processUserDao.modifyProcessUser(pUser);
		}
	}

	@Override
	public void delProcessUser(String ids) throws Exception {
		// 将用户String类型转成ProcessFlow对象
		List<ProcessUser> list = (List<ProcessUser>) DataConverter.convertJson2List(ids,ProcessUser.class);
		for (int i = 0; i < list.size(); i++) {
			//删除对象
			processUserDao.delProcessUser(list.get(i));
		}
	}

	/**
     * 匹配用户
     */
	@Override
	public void addUser(List<ProcessDefinition> infos,String users) throws Exception {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<User> userList = null;
		try {
			for (int i = 0; i < infos.size(); i++) {
				ProcessDefinition info = infos.get(i);
				// 首先刪除当前流程定义绑定的用户
				ProcessUser paramProcessId = new ProcessUser();
				paramProcessId.setProcessDefId(info.getId());
				List<ProcessUser> processUserHas = processUserDao.findProcessUserByProcessId(paramProcessId);
				if (processUserHas != null) {
					processUserDao.delProcessUser(processUserHas);
				}
				// 然后新增关系记录
				userList = (List<User>) DataConverter.convertJson2List(users,User.class);
				for(User user:userList){
			    	User currentUser=userDao.getUser(user);
			    	ProcessUser processUser=new ProcessUser();
			    	String id = GenerateSequenceUtil.generateSequenceNo();
			    	processUser.setId(id);
			    	processUser.setProcessDefId(info.getId());
			    	processUser.setProcessDefVersion(Float.parseFloat(String.valueOf(info.getVersion())));
			    	processUser.setUserNo(currentUser.getId());
			    	processUser.setProcessDefName(info.getName());
			    	processUser.setProcessDefKey(info.getKey());
			    	processUser.setUserName(currentUser.getUserName());
			    	processUserDao.addProcessUser(processUser);
				}
			}
		} catch (DBException e) {
			LogHelper.ERROR.log(e.getMessage(),e);
			throw new DBException("数据库操作错误");
		}
	}

}
