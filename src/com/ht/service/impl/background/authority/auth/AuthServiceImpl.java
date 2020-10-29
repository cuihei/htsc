package com.ht.service.impl.background.authority.auth;

import java.util.List;

import javax.annotation.Resource;

import com.ht.common.util.DataConverter;
import com.ht.common.util.LogHelper;
import com.ht.persistence.dao.inter.background.authority.auth.AuthDao;
import com.ht.persistence.model.background.authority.auth.Auth;
import com.ht.service.inter.background.authority.auth.AuthService;

/**
 * 权限类service实现
 * @author 侯晨
 *
 */
public class AuthServiceImpl implements AuthService {
    
	//注入authDao
	@Resource(name = "authDao") 
	private AuthDao authDao;
	
	/**
	 * 新增权限
	 * @param authParam json对象
	 * @throws Exception
	 */
	@Override
	public void addAuth(String authParam) throws Exception {
		try {
			//将json转换为auth对象
			Auth auth = (Auth) DataConverter.convertJson2Object(authParam, Auth.class);
			//添加Auth
			authDao.addAuth(auth);
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
		
	}

	/**
	 * 修改权限
	 * @param authParam json对象
	 * @throws Exception
	 */
	@Override
	public void modifyAuth(String authParam) throws Exception {
		try {
			//将json转换为auth对象
			Auth auth = (Auth) DataConverter.convertJson2Object(authParam, Auth.class);
			// 更新Auth
			authDao.modifyAuth(auth);
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}
	
	/**
	 * 删除权限
	 * @param authParam json对象
	 * @throws Exception
	 */
	@Override
	public void delAuth(String authParam) throws Exception {
		try {
			//将json转换为auth对象
			Auth auth = (Auth) DataConverter.convertJson2Object(authParam, Auth.class);
			// 删除Auth
			authDao.delAuth(auth);
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}
	
	/**
	 * 查询所有权限
	 * @throws Exception
	 */
	@Override
	public List<Auth> getAuth() throws Exception {
		try {
			// 获取所有Auth
			return authDao.getAuth();
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}
	
	/**
	 * 查询一条权限
	 * @param authParam json对象
	 * @throws Exception
	 */
	@Override
	public Auth getAuth(String authParam) throws Exception {
		try {
			//将json转换为auth对象
			Auth auth = (Auth) DataConverter.convertJson2Object(authParam, Auth.class);
			// 根据id获取Auth
			return authDao.getAuth(auth);
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}

	
}
