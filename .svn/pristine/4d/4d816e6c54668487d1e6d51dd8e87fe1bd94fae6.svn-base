package com.ht.persistence.dao.inter.background.authority.auth;

import java.util.List;

import com.ht.persistence.model.background.authority.auth.RoleApp;

/**
 * RoleAuthDao接口
 * @author zqy
 */
public interface RoleAppDao {
	/**
	 * 增加一个RoleAuth
	 * @param RoleApp RoleAuth实体
	 */
	public void addRoleAuth(RoleApp RoleApp);

	/**
	 * 更新一个RoleAuth
	 * @param RoleApp RoleAuth实体
	 */
	public void modifyRoleAuth(RoleApp RoleApp);

	/**
	 * 删除RoleAuth 相关
	 * @param RoleApp roleAuth实体
	 */
	public void delRoleAuth(RoleApp RoleApp);
	
	/**
	 * 获取所有RoleAuth
	 * @return List<RoleApp>列表
	 */
	public List<RoleApp> getRoleAuth();

	/**
	 * 获取多个RoleAuth对象
	 * @param RoleApp roleAuth实体
	 * @return List<RoleApp>实体
	 */
	public List<RoleApp> getRoleAuth(RoleApp RoleApp);
	
	/**
	 * 获取多个RoleApp对象
	 * @param RoleApp roleApp实体
	 * @return List<RoleApp>实体
	 */
	public List<RoleApp> getAppsByAppId(RoleApp RoleApp);

	/**
	 * 删除角色- 应有资源关系
	 * @param roleAppList 角色- 应有资源关系集合
	 */
	public void delAllRoleAuth(List<RoleApp> roleAppList);

	public void saveOrUpdateAll(List<RoleApp> roleAppList);
}
