package com.ht.persistence.dao.impl.background.application;

import java.util.List;

import org.hibernate.SQLQuery;
import com.ht.persistence.dao.impl.base.BaseDaoImpl;

import com.ht.persistence.dao.inter.background.application.ApplicationDao;
import com.ht.persistence.dao.inter.background.application.ViewApplicationDao;
import com.ht.persistence.model.background.application.Application;
import com.ht.persistence.model.background.application.ViewApplication;

/**
 * Application 数据映射操作类
 * @author zhongquanyou 
 *
 */
public class ViewApplicationDaoImpl extends BaseDaoImpl implements ViewApplicationDao{


	/**
	 * 获取所有Application
	 * @return List<Application> Application对象列表
	 */
	@Override
	public List<ViewApplication> getApplication() {
		//执行查询方法
		@SuppressWarnings("unchecked")
		List<ViewApplication> result = this.findByNamedQuery("getViewApplication");
		return result;
	}

}
