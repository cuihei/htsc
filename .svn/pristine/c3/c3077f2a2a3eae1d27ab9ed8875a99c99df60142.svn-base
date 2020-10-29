package com.ht.service.inter.background.application;

import java.util.List;

import com.ht.exception.DBException;
import com.ht.persistence.model.background.application.Application;

/**
 * 应用资源接口ApplicationService
 * @author zhongquanyou
 */
public interface ApplicationService {

	/**
	 * 新增应用资源
	 * @param RoleApplicationRel String类型的应用资源对象
	 * @throws Exception
	 */
	public void addApplication(String appParam) throws Exception;

	/**
	 * 修改应用资源
	 * @param RoleApplicationRel String类型的应用资源对象
	 * @throws Exception
	 */
	public void modifyApplication(String appParam) throws Exception;

	/**
	 * 删除应用资源
	 * @param appId 应用资源ID
	 * @return 
	 * @throws Exception
	 */
	public String delApplication(String appId) throws Exception;

	/**
	 * 查询所有应用资源
	 * @return List<Application> Application对象集合
	 * @throws Exception
	 */
	public List<Application> getApplication() throws Exception;

	/**
	 * 查询一条应用资源
	 * @param appId 应用资源ID
	 * @return Application Application对象
	 * @throws Exception
	 */
	public Application getApplication(String appId) throws DBException;

	/**
	 * 获取Application对象父节点下的所有子节点，形成树结构
	 * @param RoleApplicationRel Application对象
	 * @return List<Application> Application对象的集合
	 */
	public String getApplicationTree(String appParam) throws Exception;
	
	/**
	 * 获取Application对象父节点下的所有子节点，形成树结构
	 * @return List<Application> Application对象的集合
	 */
	public String getApplicationChilds() throws Exception; 
	
	/**
	 * 导出Excel文件方法
	 */
	public void exportExcel() throws Exception;

}
