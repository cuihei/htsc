package com.ht.persistence.dao.impl.background.authority.auth;

import java.util.List;

import com.ht.persistence.dao.impl.base.BaseDaoImpl;

import com.ht.persistence.dao.inter.background.authority.auth.AuthDao;
import com.ht.persistence.model.background.authority.auth.Auth;

/**
 * Auth 数据映射操作类
 * @author 侯晨
 *
 */
public class AuthDaoImpl extends BaseDaoImpl implements AuthDao{

	/**
	 * 增加一个Auth
	 * @param auth Auth实体
	 */
	@Override
	public void addAuth(Auth auth) {
		//执行保存方法
		this.save(auth);
	}

	/**
	 * 更新一个Auth
	 * @param Auth auth实体
	 */
	@Override
	public void modifyAuth(Auth auth) {
		//执行更新方法
		this.update(auth);
	}

	/**
	 * 删除Auth 相关
	 * @param id Auth主键
	 */
	@Override
	public void delAuth(Auth auth) {
		//执行删除方法
		this.delete(auth);
	}

	/**
	 * 获取所有Auth
	 * @return Auth列表
	 */
	@Override
	public List<Auth> getAuth() {
		//执行查询方法
		@SuppressWarnings("unchecked")
		List<Auth> result = this.findByNamedQuery("getAuth");
		return result;
	}

	/**
	 * 获取一条Auth
	 * @param id Auth主键
	 * @return Auth实体
	 */
	@Override
	public Auth getAuth(Auth auth) {
		//执行查询方法
		@SuppressWarnings("unchecked")
		List<Auth> result = this.findByNamedQueryAndNamedParam("getAuthById", "id", auth.getId());
		if(result.size()>0){
			//获取第一条
			return result.get(0);
		}
		return null;
	}
   
}
