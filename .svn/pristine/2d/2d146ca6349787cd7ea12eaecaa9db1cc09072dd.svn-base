package com.ht.persistence.dao.impl.base;

import java.io.Serializable;
import java.util.Date;

import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.SessionFactoryUtils;

import com.ht.common.util.LoginUtil;
import com.ht.persistence.dao.inter.base.BaseDao;
import com.ht.persistence.model.base.BaseModel;

public class BaseDaoImpl extends HibernateTemplate implements BaseDao{

	@Override
	public DataSource getDataSource() {
		DataSource dataSource = SessionFactoryUtils.getDataSource(this.getSessionFactory());
		return dataSource;
	}

	@Override
	public SessionFactory getFactory() {
		return this.getSessionFactory();
	}
	
	/**
	 * 重写父类sava方法
	 */
	public Serializable save(Object entity){
		BaseModel model = (BaseModel)entity;
		LoginUtil util = LoginUtil.getInstance();
		model.setCreationDate(new Date());
		model.setCreator(util.getCurrentUser());
		model.setModifier(util.getCurrentUser());
		model.setModifyDate(new Date());
		return super.save(model);
	}
	
	/**
	 * 重写父类update方法
	 */
	public void update(Object entity){
		BaseModel model = (BaseModel)entity;
		LoginUtil util = LoginUtil.getInstance();
		model.setModifier(util.getCurrentUser());
		model.setModifyDate(new Date());
		super.update(model);
	}

	@Override
	public Session getCustomSession() {
		boolean isOpen = this.getSessionFactory().getCurrentSession().isOpen();
		if (!isOpen) {
			return this.getSessionFactory().openSession();
		}
		return this.getSessionFactory().getCurrentSession();
	}
}
