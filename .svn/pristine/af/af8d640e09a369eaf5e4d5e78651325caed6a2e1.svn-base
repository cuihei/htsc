package com.ht.service.impl.complication.seamap.paper;

import java.util.List;

import javax.annotation.Resource;

import com.ht.common.util.LogHelper;
import com.ht.persistence.dao.inter.complication.seamap.paper.PaperDao;
import com.ht.persistence.model.complication.seamap.source.SeaMap;
import com.ht.persistence.model.complication.seamap.source.TransferStatus;
import com.ht.service.inter.complication.seamap.paper.PaperService;

public class PaperServiceImpl implements PaperService {

	@Resource
	private PaperDao paperDao;

	/**
	 * 查询
	 * 
	 * @return 纸海图list
	 * @throws Exception
	 */
	@Override
	public List<SeaMap> findPaperList() throws Exception {
		try {
			// 获取所有CatalogArea
			return paperDao.findPaperList();
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
	 * @return 纸海图一条流转状态list
	 * @throws Exception
	 */
	@Override
	public List<TransferStatus> findPaperStatus(String id) throws Exception {
		try {
			TransferStatus tf = new TransferStatus();
			tf.setId(id);
			// 获取一条流转状态list
			return paperDao.findSourceStatus(tf);
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(), e);
			// 抛出异常
			throw e;
		}
	}
}
