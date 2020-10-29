package com.ht.persistence.dao.impl.background.application;

import java.util.List;

import org.hibernate.SQLQuery;

import com.ht.persistence.dao.impl.base.BaseDaoImpl;
import com.ht.persistence.dao.inter.background.application.ApplicationDao;
import com.ht.persistence.model.background.application.Application;

/**
 * Application 数据映射操作类
 * @author zhongquanyou 
 *
 */
public class ApplicationDaoImpl extends BaseDaoImpl implements ApplicationDao{

	/**
	 * 增加一个Application
	 * @param RoleApplicationRel Application对象
	 */
	@Override
	public void addApplication(Application app) {
		//执行保存方法
		this.save(app);
	}

	/**
	 * 更新一个Application
	 * @param RoleApplicationRel Application对象
	 */
	@Override
	public void modifyApplication(Application app) {
		//执行更新方法
		this.update(app);
	}

	/**
	 * 删除Application
	 * @param RoleApplicationRel Application对象
	 */
	@Override
	public void delApplication(Application app) {
		//执行删除方法
		this.delete(app);
	}

	/**
	 * 获取所有Application
	 * @return List<Application> Application对象列表
	 */
	@Override
	public List<Application> getApplication() {
		//执行查询方法
		@SuppressWarnings("unchecked")
		List<Application> result = this.findByNamedQuery("getApplication");
		return result;
	}

	/**
	 * 获取一条app
	 * @param RoleApplicationRel Application对象
	 * @return Application对象
	 */
	@Override
	public Application getApplication(Application app) {
		//执行查询方法
		@SuppressWarnings("unchecked")
		List<Application> result = this.findByNamedQueryAndNamedParam("getApplicationById", "id", app.getId());
		if(result.size()>0){
			//获取第一条
			return result.get(0);
		}
		return null;
	}

	/**
	 * 获取Application对象父节点下的所有子节点，形成树结构
	 * @param RoleApplicationRel Application对象
	 * @return List<Application> Application对象的集合
	 */
	public List<Application> getApplicationTree(Application app) {
		
		SQLQuery query = this.getSession().createSQLQuery("select * from application t   start with t.id= ?  connect by t.app_pid = prior t.id");
		query.setString(0,app.getId() );
		query.addEntity(Application.class);
		
		@SuppressWarnings("unchecked")
		List<Application> appList =  query.list();
		return appList;
	}

	/**
	 * 获取Application对象父节点下的所有子节点，形成树结构
	 * @return List<Application> Application对象的集合
	 */
	@Override
	public List<Application> getApplicationChilds() {
		SQLQuery query = this.getSession().createSQLQuery("select * from application t   start with t.id= '10181145576270000'  connect by t.app_pid = prior t.id");
		query.addEntity(Application.class);
		
		@SuppressWarnings("unchecked")
		List<Application> appList =  query.list();
		return appList;
	}
   
}
