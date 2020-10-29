package com.ht.service.inter.background.application;

import java.util.List;

import com.ht.exception.DBException;
import com.ht.persistence.model.background.application.Application;
import com.ht.persistence.model.background.application.ViewApplication;

/**
 * 应用资源接口ApplicationService
 * @author zhongquanyou
 */
public interface ViewApplicationService {

	/**
	 * 查询所有应用资源
	 * @return List<Application> Application对象集合
	 * @throws Exception
	 */
	public List<ViewApplication> getApplication() throws Exception;

}
