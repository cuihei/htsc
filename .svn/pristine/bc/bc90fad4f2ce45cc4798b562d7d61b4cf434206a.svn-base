package com.ht.service.impl.system.workflow.process;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;

import com.ht.common.util.DataConverter;
import com.ht.common.util.GenerateSequenceUtil;
import com.ht.common.util.LogHelper;
import com.ht.persistence.dao.inter.system.workflow.process.ProcessLogDao;
import com.ht.persistence.dao.inter.system.workflow.process.ProcessLogDetailDao;
import com.ht.persistence.model.system.workflow.process.ProcessLog;
import com.ht.persistence.model.system.workflow.process.ProcessLogDetail;
import com.ht.service.inter.system.workflow.process.ProcessLogDetailService;

public class ProcessLogDetailServiceImpl implements ProcessLogDetailService{

	
	/**
	 * 注入目录Dao
	 */
	@Resource(name="processLogDetailDao")
	private ProcessLogDetailDao processLogDetailDao;
	
	@Override
	public ProcessLogDetail getProcessLogDetailById(String id) throws Exception {
		ProcessLogDetail processLogDetail = new ProcessLogDetail();
		processLogDetail.setId(id);
		return processLogDetailDao.getProcessLogDetail(processLogDetail);
	}

	@Override
	public List<ProcessLogDetail> getProcessLogDetailList() throws Exception {
		try {
			// 获取所有列表
			List<ProcessLogDetail> list = processLogDetailDao.getProcessLogDetailList();
			return list; 
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}

	@Override
	public void addOrUpdateProcessLogDetail(String processLogDetail)
			throws Exception {
		ProcessLogDetail pLogDeatil = (ProcessLogDetail) DataConverter.convertJson2Object(processLogDetail,
				ProcessLogDetail.class);
		if(StringUtils.isBlank(pLogDeatil.getId())){
			pLogDeatil.setId(GenerateSequenceUtil.generateSequenceNo());
			// 执行添加操作
			processLogDetailDao.addProcessLogDetail(pLogDeatil);
		}else{
			// 执行保存操作
			processLogDetailDao.modifyProcessLogDetail(pLogDeatil);
		}
	}

	@Override
	public void delProcessLogDetail(String ids) throws Exception {
		// 将用户String类型转成ProcessFlow对象
		List<ProcessLogDetail> list = (List<ProcessLogDetail>) DataConverter.convertJson2List(ids,ProcessLogDetail.class);
		for (int i = 0; i < list.size(); i++) {
			//删除对象
			processLogDetailDao.delProcessLogDetail(list.get(i));
		}
	}
	
	/**
     * 添加
     * @param processLogId 工作流操作日志主键
     * @param tableName 操作表
     * @param detailRecordId 操作的记录主键
     * @param processInstId 流程实例ID
     * @throws Exception
     */
	@Override
	public void addProcessLogDetail(String processLogId,String tableName,
			String detailRecordId,String processInstId) throws Exception {
		ProcessLogDetail processLogDetail = new ProcessLogDetail();
		String id = GenerateSequenceUtil.generateSequenceNo();
		processLogDetail.setId(id);
		processLogDetail.setProcessLogId(processLogId);
		processLogDetail.setTableName(tableName);
		processLogDetail.setDetailRecordId(detailRecordId);
		processLogDetail.setProcessInstId(processInstId);
		processLogDetailDao.addProcessLogDetail(processLogDetail);
	}
}
