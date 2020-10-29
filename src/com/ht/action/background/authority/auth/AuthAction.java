package com.ht.action.background.authority.auth;

import java.util.List;

import javax.annotation.Resource;

import com.ht.action.base.BaseAction;
import com.ht.common.util.DataConverter;
import com.ht.common.util.LogHelper;
import com.ht.persistence.model.background.authority.auth.Auth;
import com.ht.service.inter.background.authority.auth.AuthService;


/**
 * 权限类控制器
 * @author 侯晨
 */
@SuppressWarnings("serial")
public class AuthAction extends BaseAction{
	
	//注入权限authService
	@Resource(name="authService")
	AuthService authService;
	
	/**
	 * 返回页面
	 * @return 页面
	 */
	public String index(){
		return "success";
	}
	
	/**
	 * 新增Auth数据
	 */
	public void addAuth(){
		try {
			//获取auth对象
			String auth = getParam("auth");
			//将auth对象转换为json
			String authParam = DataConverter.convertObject2Json(auth);
			//执行新增方法
			authService.addAuth(authParam);
			writeSuccessResult();
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			//返回错误信息
			writeFailResult(e.getMessage());
		}
	}
	
	/**
	 * 更新Auth数据
	 */
	public void modifyAuth(){
		try {
			// 获取Auth
			String auth = getParam("auth");
			//将auth对象转换为json
			String authParam = DataConverter.convertObject2Json(auth);
			//执行修改方法
			authService.modifyAuth(authParam);
			writeSuccessResult();
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			//返回错误信息
			writeFailResult(e.getMessage());
		}
	}
	
	/**
	 * 删除Auth数据
	 */
	public void removeAuth(){
		try {
			// 获取User标识
			String id = getParam("id");
			// 创建auth对象
			Auth auth = new Auth(); 
			auth.setId(id);
			//将auth对象转换为json
			String authParam = DataConverter.convertObject2Json(auth);
			//执行删除方法
			authService.delAuth(authParam);
			writeSuccessResult();
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			//返回错误信息
			writeFailResult(e.getMessage());
		}
	}
	
	/**
	 * 获取Auth列表
	 */
	public void getAuth(){
		try {
			// 创建auth对象
			Auth auth = new Auth(); 
			//将auth对象转换为json
			String authParam = DataConverter.convertObject2Json(auth);
			//执行查询方法
			List<Auth> authList = authService.getAuth();
			writeSuccessResult(authList);
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			//返回错误信息
			writeFailResult(e.getMessage());
		}
	}
	
	/**
	 * 根据id获取Auth
	 */
	public void getAuthByKey(){
		try {
			String id = getParam("id");
			// 创建auth对象
			Auth a = new Auth(); 
			a.setId(id);
			//将auth对象转换为json
			String authParam = DataConverter.convertObject2Json(a);
			//执行查询方法
			Auth auth = authService.getAuth(authParam);
			writeSuccessResult(auth);
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			//返回错误信息
			writeFailResult(e.getMessage());
		}
	}
	
}
