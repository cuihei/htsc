package com.ht.persistence.util;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.hibernate.SessionFactory;

import com.ht.common.util.LogHelper;
import com.ht.persistence.dao.inter.base.BaseDao;

public class SessionFactoryHelper implements ISessionFactoryHelper{

	@Resource
	BaseDao baseDao;
	
	private static final long serialVersionUID = 1L;
	public DataSource getDataSource() {
		try {
			DataSource dataSource = baseDao.getDataSource();
			return dataSource;
		} catch (Exception e) {
			LogHelper.ERROR.log(e.getMessage(),e);
		}
		return null;
	}
	
	public SessionFactory getFactory(){
		SessionFactory factory = baseDao.getFactory();
		return factory;
	}
}
