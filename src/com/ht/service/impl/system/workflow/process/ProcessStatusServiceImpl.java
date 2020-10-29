package com.ht.service.impl.system.workflow.process;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;

import com.ht.common.util.DataConverter;
import com.ht.common.util.GenerateSequenceUtil;
import com.ht.common.util.LogHelper;
import com.ht.common.util.LoginUtil;
import com.ht.persistence.dao.inter.system.workflow.process.ProcessStatusDao;
import com.ht.persistence.model.system.workflow.process.ProcessStatus;
import com.ht.service.inter.system.workflow.process.ProcessStatusService;

public class ProcessStatusServiceImpl implements ProcessStatusService {

	
	/**
	 * 注入目录Dao
	 */
	@Resource(name="processStatusDao")
	private ProcessStatusDao processStatusDao;

	@Override
	public ProcessStatus getProcessStatusById(String id) throws Exception {
		ProcessStatus processStatus = new ProcessStatus();
		processStatus.setId(id);
		return processStatusDao.getProcessStatus(processStatus);
	}
	
	@Override
	public ProcessStatus getProcessStatusByProAndTask(String processDefId,String taskDefId) 
			throws Exception{
		ProcessStatus processStatus = new ProcessStatus();
		processStatus.setProcessDefId(processDefId);
		processStatus.setTaskDefId(taskDefId);
		return processStatusDao.getProcessStatusByProAndTask(processStatus);
	}
	

	@Override
	public List<ProcessStatus> getProcessStatusList() throws Exception {
		try {
			// 获取所有列表
			List<ProcessStatus> list = processStatusDao.getProcessStatusList();
			return list; 
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}

	@Override
	public void addOrUodateProcessStatus(String processStatus) throws Exception {
		ProcessStatus pStatus = (ProcessStatus) DataConverter.convertJson2Object(processStatus,
				ProcessStatus.class);
		ProcessStatus proStatus = 
				this.getProcessStatusByProAndTask(pStatus.getProcessDefId(), pStatus.getTaskDefId());
		if (proStatus!=null) {
			processStatusDao.delProcessStatus(proStatus);
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if(StringUtils.isBlank(pStatus.getId())){
			pStatus.setId(GenerateSequenceUtil.generateSequenceNo());
			// 执行添加操作
			processStatusDao.addProcessStatus(pStatus);
		}else{
			// 执行保存操作
			processStatusDao.modifyProcessStatus(pStatus);
		}
	}

	@Override
	public void delProcessStatus(String ids) throws Exception {
		// 将用户String类型转成ProcessStatus对象
		List<ProcessStatus> list = (List<ProcessStatus>) 
				DataConverter.convertJson2List(ids,ProcessStatus.class);
		for (int i = 0; i < list.size(); i++) {
			//删除对象
			processStatusDao.delProcessStatus(list.get(i));
		}
	}
	
}
