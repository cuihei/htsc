package com.ht.service.impl.complication.project.paper;

import java.util.List;

import javax.annotation.Resource;

import com.ht.common.util.LogHelper;
import com.ht.persistence.dao.inter.complication.project.paper.ProPaperDao;
import com.ht.persistence.model.complication.project.electron.Project;
import com.ht.service.inter.complication.project.paper.ProPaperService;

public class ProPaperServiceImpl implements ProPaperService {

	@Resource
	private ProPaperDao proPaperDao;
	
	/**
	 * 查询
	 * 
	 * @return 电子海图list
	 * @throws Exception
	 */
	@Override
	public List<Project> findProPaperList() throws Exception {
		try {
			// 获取所有CatalogArea
			return proPaperDao.findProPaperList();
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(), e);
			// 抛出异常
			throw e;
		}
	}

}
