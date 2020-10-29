package com.ht.service.impl.complication.seamap.source;

import java.util.List;

import javax.annotation.Resource;

import com.ht.common.util.LogHelper;
import com.ht.persistence.dao.inter.complication.seamap.source.SourceDao;
import com.ht.persistence.model.complication.seamap.source.SourceStatus;
import com.ht.persistence.model.complication.seamap.source.TransferStatus;
import com.ht.persistence.model.drawtask.tasksplit.TaskSplit;
import com.ht.service.inter.complication.seamap.source.SourceService;

public class SourceServiceImpl implements SourceService {

	@Resource
	private SourceDao sourceDao;

	/**
	 * 查询
	 * 
	 * @return 源数据list
	 * @throws Exception
	 */
	@Override
	public List<TaskSplit> findSourceList() throws Exception {
		try {
			// 获取所有数据
			return sourceDao.findSourceList();
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(), e);
			// 抛出异常
			throw e;
		}
	}
	
	/**
	 * 查询
	 * 
	 * @return 源数据一条流转状态
	 * @throws Exception
	 */
	@Override
	public List<SourceStatus> findSourceStatus(String process_inst_id,String task_id) throws Exception {
		try {
			//TransferStatus tf = new TransferStatus();
			SourceStatus st=new SourceStatus();
			st.setProcess_Inst_Id(process_inst_id);
			st.setTask_Id(task_id);
			//tf.setId(processid);
			// 获取所有数据
			return sourceDao.findSourceStatus(st);
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(), e);
			// 抛出异常
			throw e;
		}
	}

	/**
	 * 查询
	 * 
	 * @return 源数据采用登记列表
	 * @throws Exception
	 */
	@Override
	public List<TransferStatus> findSourceAdopt(String id) throws Exception {
		try {
			TransferStatus tf = new TransferStatus();
			tf.setId(id);
			// 获取所有数据
			return sourceDao.findSourceAdopt(tf);
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(), e);
			// 抛出异常
			throw e;
		}
	}
	
	/**
	 * 查询
	 * 
	 * @return 源数据幅索引图数据
	 * @throws Exception
	 */
	@Override
	public TransferStatus findSourceIndexMap(String id) throws Exception {
		try {
			TransferStatus tf = new TransferStatus();
			tf.setId(id);
			// 获取所有数据
			return sourceDao.findSourceIndexMap(tf);
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(), e);
			// 抛出异常
			throw e;
		}
	}
}
