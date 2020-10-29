package com.ht.service.impl.complication.project.electron;

import java.util.List;

import javax.annotation.Resource;

import com.ht.common.util.LogHelper;
import com.ht.persistence.dao.inter.complication.project.electron.ProElectronDao;
import com.ht.persistence.model.complication.project.electron.Project;
import com.ht.service.inter.complication.project.electron.ProElectronService;

public class ProElectronServiceImpl implements ProElectronService {

	@Resource
	private ProElectronDao proElectronDao;
	
	/**
	 * 查询
	 * 
	 * @return 电子海图list
	 * @throws Exception
	 */
	@Override
	public List<Project> findProEleList() throws Exception {
		try {
			// 获取所有CatalogArea
			return proElectronDao.findProEleList();
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(), e);
			// 抛出异常
			throw e;
		}
	}

}
