package com.ht.persistence.dao.inter.background.authority.auth;

import java.util.List;

import com.ht.persistence.model.background.authority.auth.Auth;

/**
 * 权限类Dao
 * @author 侯晨
 *
 */
public interface AuthDao {
	/**
	 * 增加一个Auth
	 * @param Auth Auth实体
	 */
	public void addAuth(Auth auth);

	/**
	 * 更新一个Auth
	 * @param Auth Auth实体
	 */
	public void modifyAuth(Auth auth);

	/**
	 * 删除Auth 相关
	 * @param auth Auth实体
	 */
	public void delAuth(Auth auth);
	
	/**
	 * 获取所有Auth
	 * @return Auth列表
	 */
	public List<Auth> getAuth();

	/**
	 * 获取一条Auth
	 * @param auth Auth实体
	 * @return Auth实体
	 */
	public Auth getAuth(Auth auth);
}
