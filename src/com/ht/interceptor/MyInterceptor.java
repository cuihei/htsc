package com.ht.interceptor;

import java.util.Map;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

/**
 * ������
 * @author liuzhengquan
 *
 */
public class MyInterceptor implements Interceptor {

	@Override
	public void destroy() {
		System.out.println("---------MyInterceptor.destory()-------------");
	}

	@Override
	public void init() {
		System.out.println("---------MyInterceptor.init()-------------");
	}

	/**
	 * ����ǰ���ȵ���intercept����
	 */
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
//		System.out.println("---------MyInterceptor.intercept()-------------");
//		String result=null;
//		
//		//�õ�session
//		Map<String, Object> sessionMap=invocation.getInvocationContext().getSession();
//		
//		String userName=(String)sessionMap.get("userName");
//		
//		System.out.println("userName="+userName);
//		
//		if (userName != null) {
//			result=invocation.invoke();
//		}else {
//			result="checkError";
//		}
//		return result;
		return "success";
	}

}
