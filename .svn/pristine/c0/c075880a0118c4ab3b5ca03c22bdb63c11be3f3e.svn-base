package com.ht.service.impl.complication.seamap.electron;

import java.util.List;

import javax.annotation.Resource;

import com.ht.common.util.LogHelper;
import com.ht.persistence.dao.inter.complication.seamap.electron.ElectronDao;
import com.ht.persistence.model.complication.seamap.source.SeaMap;
import com.ht.persistence.model.complication.seamap.source.TransferStatus;
import com.ht.service.inter.complication.seamap.electron.ElectronService;

public class ElectronServiceImpl implements ElectronService {

	@Resource
	private ElectronDao electronDao;
	
	/**
	 * 查询
	 * 
	 * @return 电子海图list
	 * @throws Exception
	 */
	@Override
	public List<SeaMap> findEleList() throws Exception {
		try {
			// 获取所有CatalogArea
			return electronDao.findEleList();
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
	 * @return 电子海图一条流转状态list
	 * @throws Exception
	 */
	@Override
	public List<TransferStatus> findEleStatus(String id) throws Exception {
		try {
			TransferStatus transferStatus = new TransferStatus();
			transferStatus.setId(id);
			// 获取所有CatalogArea
			return electronDao.findEleStatus(transferStatus);
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(), e);
			// 抛出异常
			throw e;
		}
	}

}
