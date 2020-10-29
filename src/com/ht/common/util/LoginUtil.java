package com.ht.common.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录工具
 * @author 王有为
 * @date 2016/10/24
 */
public class LoginUtil {

	/**
	 * 声明登录号key值
	 */
	private static final String LOGIN_NO_KEY = "LOGIN_NO_KEY";
	
	/**
	 * 登录者栈对象
	 */
	public static Stack<String> stackLogin = new Stack<String>();
	
	/**
	 * 声明登录工具静态实例
	 */
	private static LoginUtil util  = null;
	
	/**
	 * 存放用户角色资源信息的map
	 * 
	 */ 
	public static Map<String,Tree> userApplications = new HashMap<String,Tree>();
	
	/**
	 * 构造方法
	 * 设置登录栈长度
	 */
	public LoginUtil(){
		stackLogin.setSize(1);
	}
	
	/**
	 * 获取登录工具实例
	 * @return 登录工具实例
	 */
	public static LoginUtil getInstance(){
		if (util == null) {
			util = new LoginUtil();
		}
		return util;
	}
	
	/**
	 * 保持登录号
	 * @resp http响应对象
	 */
	public void keepLoginNo(HttpServletResponse resp,String loginNo){
		CookieUtil.setCookie(resp,LoginUtil.LOGIN_NO_KEY, loginNo);
	}
	
	/**
	 * 保持页面写数据
	 * @resp http响应对象
	 */
	public void keepWrite(HttpServletResponse resp,String write){
		CookieUtil.setCookie(resp,"WRITE", write);
	}
	
	/**
	 * 获取登录号
	 * @return
	 */
	public String getLoginNo(HttpServletRequest req){
		return CookieUtil.getCookieValue(req, LoginUtil.LOGIN_NO_KEY);
	}
	
	

	
	/**
	 * 清除cookie
	 */
	public void clearLoginNo(HttpServletRequest req,HttpServletResponse resp){
		CookieUtil.removeCookie(resp, CookieUtil.getCookie(req, LoginUtil.LOGIN_NO_KEY));
	}
	
	/**
	 * 设置登录用户
	 */
	public void setLoginUser(String userNo){
		stackLogin.push(userNo);
	}
	
	/**
	 * 获取服务器当前用户
	 * @return 用户账号
	 */
	public String getCurrentUser(){
		return stackLogin.peek();
	}
	
	/**
	 * 重置角色资源
	 */
	public void resetUserApplication(String userNo,Tree application){
		if (userApplications.containsKey(userNo))
		{
			userApplications.remove(userNo);
		}
		userApplications.put(userNo, application);
	}
	
	/**
	 * 清空角色资源
	 */
	public void clearUserApplication(){
		userApplications.clear();
	}
	
	/**
	 * 移除角色资源
	 */
	public void removeUserApplication(String userNo){
		if (userApplications.containsKey(userNo))
		{
			userApplications.remove(userNo);
		}
	}
	
	/**
	 * 获取角色资源
	 * @return
	 */
	public Tree getUserApplication(String userNo){
		if (userApplications.containsKey(userNo))
		{
			return LoginUtil.userApplications.get(userNo);
		}
		return null;
	}
}
