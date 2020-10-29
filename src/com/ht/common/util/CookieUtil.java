package com.ht.common.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Cookie工具类,封装Cookie常用操作
 * @author carrkevin
 * @date
 */
public class CookieUtil {

	/**
	 * 设置cookie有效期，根据需要自定义[本系统设置为50分钟] 
	 * //周工因为工作需要，所以设置时间长一点 50分钟 2019.9.17
	 */
	private final static int COOKIE_MAX_AGE = 60*50;

	/**
	 *
	 * @desc 删除指定Cookie
	 * @param response
	 * @param cookie
	 */
	public static void removeCookie(HttpServletResponse response, Cookie cookie) {
		if (cookie != null) {
			cookie.setPath("/");
			cookie.setValue("");
			cookie.setMaxAge(0);
			response.addCookie(cookie);
		}
	}

	/**
	 *
	 * @desc 删除指定Cookie
	 * @param response
	 * @param cookie
	 * @param domain
	 */
	public static void removeCookie(HttpServletResponse response,
			Cookie cookie, String domain) {
		if (cookie != null) {
			cookie.setPath("/");
			cookie.setValue("");
			cookie.setMaxAge(0);
			cookie.setDomain(domain);
			response.addCookie(cookie);
		}
	}

	/**
	 *
	 * @desc 根据Cookie名称得到Cookie的值，没有返回Null
	 * @param request
	 * @param name
	 * @return
	 */
	public static String getCookieValue(HttpServletRequest request, String name) {
		Cookie cookie = getCookie(request, name);
		if (cookie != null) {
			return cookie.getValue();
		} else {
			return null;
		}
	}

	/**
	 *
	 * @desc 根据Cookie名称得到Cookie对象，不存在该对象则返回Null
	 * @param request
	 * @param name
	 */
	public static Cookie getCookie(HttpServletRequest request, String name) {
		Cookie cookies[] = request.getCookies();
		if (cookies == null || name == null || name.length() == 0)
			return null;
		Cookie cookie = null;
		for (int i = 0; i < cookies.length; i++) {
			if (!cookies[i].getName().equals(name))
				continue;
			cookie = cookies[i];
			if (request.getServerName().equals(cookie.getDomain()))
				break;
		}
		return cookie;
	}

	/**
	 * @desc 添加一条新的Cookie信息，默认有效时间为一个月
	 * @param response
	 * @param name
	 * @param value
	 */
	public static void setCookie(HttpServletResponse response, String name,
			String value) {
		setCookie(response, name, value, COOKIE_MAX_AGE);
	}

	/**
	 *
	 * @desc 添加一条新的Cookie信息，可以设置其最长有效时间(单位：秒)
	 * @param response 响应
	 * @param name 名称
	 * @param value 值
	 * @param maxAge 失效时间
	 */
	public static void setCookie(HttpServletResponse response, String name,
			String value, int maxAge) {
		if (value == null)
			value = "";
		Cookie cookie = new Cookie(name, value);
		if (maxAge != 0) {
			cookie.setMaxAge(maxAge);
		} else {
			cookie.setMaxAge(COOKIE_MAX_AGE);
		}
		cookie.setPath("/");
		response.addCookie(cookie);
	}
}