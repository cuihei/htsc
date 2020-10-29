package com.ht.persistence.dao.inter.background.application;

import java.util.List;

import com.ht.persistence.model.background.application.Application;

/**
 * ApplicationDao 应用资源接口DAO
 * @author zhongquanyou
 */
public interface ApplicationDao {
	/**
	 * 增加一个Application
	 * @param RoleApplicationRel Application对象
	 */
	public void addApplication(Application app);

	/**
	 * 更新一个Application
	 * @param RoleApplicationRel Application对象
	 */
	public void modifyApplication(Application app);

	/**
	 * 删除app
	 * @param RoleApplicationRel Application对象
	 */
	public void delApplication(Application app);
	
	/**
	 * 获取所有Application
	 * @return List<Application> Application对象集合
	 */
	public List<Application> getApplication();

	/**
	 * 获取一条Application
	 * @param RoleApplicationRel Application对象
	 * @return Application对象
	 */
	public Application getApplication(Application app);
	
	/**
	 * 获取Application对象父节点下的所有子节点，形成树结构
	 * @param RoleApplicationRel Application对象
	 * @return List<Application> Application对象的集合
	 */
	public List<Application> getApplicationTree(Application application);
	
	/**
	 * 获取Application对象父节点下的所有子节点，形成树结构
	 * @param RoleApplicationRel Application对象
	 * @return List<Application> Application对象的集合
	 */
	public List<Application> getApplicationChilds();
}
