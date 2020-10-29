package com.ht.service.impl.system.workflow.process;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;

import com.ht.common.util.DataConverter;
import com.ht.common.util.GenerateSequenceUtil;
import com.ht.common.util.LogHelper;
import com.ht.exception.DBException;
import com.ht.persistence.dao.inter.system.workflow.process.ProcessFlowDao;
import com.ht.persistence.model.system.workflow.process.ProcessFlow;
import com.ht.service.inter.system.workflow.process.ProcessFlowService;

public class ProcessFlowServiceImpl implements ProcessFlowService{
	
	/**
	 * 注入目录Dao
	 */
	@Resource(name="processFlowDao")
	private ProcessFlowDao processFlowDao;

	@Override
	public ProcessFlow getProcessFlowById(String id) throws Exception {
		ProcessFlow processFlow = new ProcessFlow();
		processFlow.setId(id);
		return processFlowDao.getProcessFlow(processFlow);
	}

	@Override
	public List<ProcessFlow> getProcessFlowList() throws Exception {
		try {
			// 获取所有列表
			List<ProcessFlow> list = processFlowDao.getProcessFlowList();
			return list; 
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}

	@Override
	public void addOrUpdateProcessFlow(String processFlow) throws Exception {
		ProcessFlow pFlow = (ProcessFlow) DataConverter.convertJson2Object(processFlow,
				ProcessFlow.class);
		if(StringUtils.isBlank(pFlow.getId())){
			pFlow.setId(GenerateSequenceUtil.generateSequenceNo());
			// 执行添加操作
			processFlowDao.addProcessFlow(pFlow);
		}else{
			// 执行保存操作
			processFlowDao.modifyProcessFlow(pFlow);
		}
	}

	@Override
	public void delProcessFlow(String ids) throws Exception {
		// 将用户String类型转成ProcessFlow对象
		List<ProcessFlow> list = (List<ProcessFlow>) DataConverter.convertJson2List(ids,ProcessFlow.class);
		for (int i = 0; i < list.size(); i++) {
			//删除对象
			processFlowDao.delProcessFlow(list.get(i));
		}
	}
	
	@Override
	public ProcessFlow getProcessFlowByAdvice(String advice,String processInstId) throws DBException {
		try {
			return processFlowDao.getProcessFlowByAdvice(advice,processInstId);
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(), e);
			// 抛出异常
			throw new DBException("数据库操作错误");
		}
	}
	
	@Override
	public ProcessFlow getProcessFlowByAdvice1(String advice,String processInstId) throws DBException {
		try {
			return processFlowDao.getProcessFlowByAdvice1(advice,processInstId);
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(), e);
			// 抛出异常
			throw new DBException("数据库操作错误");
		}
	}
}
