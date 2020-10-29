package com.ht.persistence.dao.inter.background.application;

import java.util.List;

import com.ht.persistence.model.background.application.Application;
import com.ht.persistence.model.background.application.ViewApplication;

/**
 * ApplicationDao 应用资源接口DAO
 * @author zhongquanyou
 */
public interface ViewApplicationDao {
	
	/**
	 * 获取所有Application
	 * @param app 
	 * @return List<Application> Application对象集合
	 */
	public List<ViewApplication> getApplication();

	
}
