package com.ht.service.impl.system.workflow.process;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;

import com.ht.common.util.DataConverter;
import com.ht.common.util.GenerateSequenceUtil;
import com.ht.common.util.LogHelper;
import com.ht.persistence.dao.inter.system.workflow.process.ProcessFlowDao;
import com.ht.persistence.dao.inter.system.workflow.process.ProcessLogDao;
import com.ht.persistence.model.system.workflow.process.ProcessFlow;
import com.ht.persistence.model.system.workflow.process.ProcessLog;
import com.ht.service.inter.system.workflow.process.ProcessLogService;

public class ProcessLogServiceImpl implements ProcessLogService{
	
	/**
	 * 注入目录Dao
	 */
	@Resource(name="processLogDao")
	private ProcessLogDao processLogDao;
	
	@Override
	public ProcessLog getProcessLogById(String id) throws Exception {
		ProcessLog processLog = new ProcessLog();
		processLog.setId(id);
		return processLogDao.getProcessLog(processLog);
	}

	@Override
	public List<ProcessLog> getProcessLogList() throws Exception {
		try {
			// 获取所有列表
			List<ProcessLog> list = processLogDao.getProcessLogList();
			return list; 
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}

	@Override
	public void addOrUodateProcessLog(String processLog) throws Exception {
		ProcessLog pLog = (ProcessLog) DataConverter.convertJson2Object(processLog,
				ProcessLog.class);
		if(StringUtils.isBlank(pLog.getId())){
			pLog.setId(GenerateSequenceUtil.generateSequenceNo());
			// 执行添加操作
			processLogDao.addProcessLog(pLog);
		}else{
			// 执行保存操作
			processLogDao.modifyProcessLog(pLog);
		}
	}

	@Override
	public void delProcessLog(String ids) throws Exception {
		// 将用户String类型转成ProcessFlow对象
		List<ProcessLog> list = (List<ProcessLog>) DataConverter.convertJson2List(ids,ProcessLog.class);
		for (int i = 0; i < list.size(); i++) {
			//删除对象
			processLogDao.delProcessLog(list.get(i));
		}
	}

	/**
	 * 添加
	 * @param userNo 操作人
	 * @param processInstId 流程实例ID
	 * @param tableName 操作表名称
	 * @param operationType 操作类型
	 * @param tableStatusName 操作表状态字段名称
	 * @param tableKeyName 操作表主键字段名称
	 * @throws Exception
	 */
	@Override
	public String addProcessLog(String userNo,String processInstId,String tableName,
			String operationType,String tableStatusName,String tableKeyName) throws Exception {
		ProcessLog processLog = new ProcessLog();
		String id = GenerateSequenceUtil.generateSequenceNo();
		processLog.setId(id);
		processLog.setUserNo(userNo);
		processLog.setProcessInstId(processInstId);
		processLog.setTableName(tableName);
		processLog.setOperationType(operationType);
		processLog.setTableStatusName(tableStatusName);
		processLog.setTableKeyName(tableKeyName);
		processLogDao.addProcessLog(processLog);
		return id;
	}
}
