package com.ht.persistence.dao.impl.background.authority.auth;

import java.util.List;

import com.ht.persistence.dao.impl.base.BaseDaoImpl;

import com.ht.persistence.dao.inter.background.authority.auth.RoleAppDao;
import com.ht.persistence.model.background.authority.auth.RoleApp;

/**
 * RoleApp 数据映射操作类
 * @author zqy 
 *
 */
public class RoleAppDaoImpl extends BaseDaoImpl implements RoleAppDao{

	/**
	 * 增加一个RoleAuth
	 * @param RoleApp RoleAuth实体
	 */
	@Override
	public void addRoleAuth(RoleApp RoleApp) {
		this.save(RoleApp);
	}

	@Override
	public void saveOrUpdateAll(List<RoleApp> roleAppList) {
		this.saveOrUpdateAll(roleAppList);
	}
	
	/**
	 * 更新一个RoleUsers
	 * @param RoleApp RoleAuth实体
	 */
	@Override
	public void modifyRoleAuth(RoleApp RoleApp) {
		this.update(RoleApp);
	}

	/**
	 * 删除RoleUsers
	 * @param RoleApp RoleAuth对象
	 */
	@Override
	public void delRoleAuth(RoleApp RoleApp) {
		this.delete(RoleApp);
	}

	/**
	 * 删除RoleUsers
	 * @param RoleApp RoleAuth对象
	 */
	@Override
	public void delAllRoleAuth(List<RoleApp> roleAppList) {
		this.deleteAll(roleAppList);
	}
	
	/**
	 * 获取所有RoleAuth
	 * @return List<RoleApp>对象列表
	 */
	@Override
	public List<RoleApp> getRoleAuth() {
		try {
			@SuppressWarnings("unchecked")
			List<RoleApp> result = this.findByNamedQuery("getRoleAuth");
			return result;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	/**
	 * 获取RoleAuth
	 * @param RoleApp RoleAuth对象
	 * @return List<RoleApp>对象列表
	 */
	@Override
	public List<RoleApp> getRoleAuth(RoleApp RoleApp) {
		try {
			@SuppressWarnings("unchecked")
			List<RoleApp> result = this.findByNamedQueryAndNamedParam("getAppsByRoleId", "roleId", RoleApp.getRoleId());
			return result;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	/**
	 * 获取多个RoleApp对象
	 * @param RoleApp roleApp实体
	 * @return List<RoleApp>实体
	 */
	public List<RoleApp> getAppsByAppId(RoleApp RoleApp){
		try {
			@SuppressWarnings("unchecked")
			List<RoleApp> result = this.findByNamedQueryAndNamedParam("getAppsByAppId", "appId", RoleApp.getAppId());
			return result;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
}
