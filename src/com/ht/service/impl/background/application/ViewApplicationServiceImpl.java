package com.ht.service.impl.background.application;

import java.util.List;

import javax.annotation.Resource;

import com.ht.common.util.LogHelper;
import com.ht.persistence.dao.inter.background.application.ViewApplicationDao;
import com.ht.persistence.model.background.application.ViewApplication;
import com.ht.service.inter.background.application.ViewApplicationService;

/**
 * 应用资源ApplicationService接口的实现类
 * @author zhongquanyou
 */
public class ViewApplicationServiceImpl implements ViewApplicationService {

	// 注入应用资源DAO ApplicationDao
	@Resource(name = "viewApplicationDao")
	private ViewApplicationDao viewApplicationDao;

	/**
	 * 查询所有应用资源
	 * @return List<ViewApplication> ViewApplication对象集合
	 * @throws Exception
	 */
	public List<ViewApplication> getApplication() throws Exception {
		try {
			// 获取所有Application对象
			return viewApplicationDao.getApplication();
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(), e);
			// 抛出异常
			throw e;
		}
	}



}
