package com.ht.filter;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.ht.common.util.Cundate;
import com.ht.common.util.GenerateSequenceUtil;
import com.ht.common.util.GetIPUtil;
import com.ht.common.util.LogHelper;
import com.ht.common.util.LoginUtil;
import com.ht.common.util.MatchingUtil;
import com.ht.exception.DBException;
import com.ht.listener.SessionListener;
import com.ht.persistence.model.background.organization.employee.User;
import com.ht.service.inter.background.monitor.accesslog.SyslogService;
import com.ht.service.inter.background.monitor.operationlog.SyslogOperationService;
import com.ht.service.inter.background.organization.employee.UserService;

public class MyFilter implements Filter {
	/**
	 * 注入syslogService(系统操作日志)
	 */
	@Resource
	private SyslogService syslogService;
	
	/**
	 * 注入syslogOperationService(系统访问日志)
	 */
	@Resource
	private SyslogOperationService syslogOperationService;
	
	@Resource
	private UserService userService;
	
	//过滤器配置
	protected FilterConfig config;
	private ServletContext context;
	private String filterName;
	//获取当前日期的类
	private Cundate cd=new Cundate();
	
	static Logger logger = Logger.getLogger(MyFilter.class.getName());
		
	@Override
	public void destroy() {
		System.out.println("---------MyFilter.destroy()-------------");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		String write = req.getParameter("write");
		HttpServletResponse resp = (HttpServletResponse) response;
		if(write!=null&&!write.equals("null")){
			LoginUtil.getInstance().keepWrite(resp, write);
		}
		String userNo = LoginUtil.getInstance().getLoginNo(req);
		HttpSession session = req.getSession(false);
		//保持登录
		if (userNo != null) {
			if (SessionListener.sessionMap.get(userNo) != null) {
				LoginUtil.getInstance().keepLoginNo(resp, userNo);
				List<User> users = null;
				try {
					users = userService.getUserByNo(userNo);
					session.setAttribute("users", users.get(0));
				} catch (DBException e) {
					request.setAttribute("userNo", null);
					resp.sendRedirect("/ht/index/login");
					return;
				}
			}else{
				userNo = null;
			}
		}else{
			SessionListener.delSession(session);
		}
		Boolean action = MatchingUtil.matchingAction(req.getRequestURI());
		// 客户端访问放行
		String client = req.getParameter("client");
		if (StringUtils.isNotEmpty(client))
		{
			if (client.equals("1"))
			{
				chain.doFilter(req, resp);
				return;
			}
		}
		if (StringUtils.isEmpty(userNo)) {
			if (action) {
				if (req.getRequestURI().toString().equals("/ht/index/login")) {
					chain.doFilter(req, resp);
				}
				else if(req.getRequestURI().toString().equals("/ht/index/validation")){
					chain.doFilter(req, resp);
				}
				else if(req.getRequestURI().toString().contains("/ht/docTempleteAction")){
					chain.doFilter(req, resp);
				}
				else{
					request.setAttribute("userNo", null);
					resp.sendRedirect("/ht/index/login");
					return;
				}
			}
			else{
				chain.doFilter(req, resp);
			}
		}
		else{
			request.setAttribute("userNo", userNo);
			request.setAttribute("write", write);
			chain.doFilter(request, response);
		}
		//如果是action保存日志
		if (MatchingUtil.matchingAction(req.getRequestURI())) {
			//获取id
			String slid = GenerateSequenceUtil.generateSequenceNo();
			String soid = GenerateSequenceUtil.generateSequenceNo();
			//获取请求用户id
			String userId = LoginUtil.getInstance().getLoginNo(req);
			//获取请求IP地址
			String userIp = GetIPUtil.getIpAddr(req);
			//获取请求操作  "访问了" + req.getRequestURI()
			String handleBehavior = "登录了管理系统";
			//获取操作内容
			String operationBehavior = MatchingUtil.getBehavior(req.getRequestURI());
			//获取操作对象
			String operationObject = MatchingUtil.getBehaviorObject(req.getRequestURI());
			//获取请求结果
			String Result = "成功";
			//访问日志json
			String syslog = "{\"id\":\"" + slid + "\",\"handleId\":\""+ userId + "\",\"handleIp\":\""+ userIp +
					"\",\"handleBehavior\":\""+ handleBehavior + "\",\"handleResult\":\""+ Result + "\"}";
			//操作日志json
			String syslogOperation = "{\"id\":\"" + soid + "\",\"operatorId\":\""+ userId + "\",\"operatorIp\":\""+ userIp +
					"\",\"operationBehavior\":\""+ operationBehavior + "\",\"operationResult\":\""+ Result +
					"\",\"operationObject\":\""+ operationObject + "\"}";
			try {
				//如果登录失败保存登录失败日志
				if (req.getRequestURI().toString().equals("/ht/index/validation")) {
					boolean b = syslogService.validation(req);
					if (!b) {
						String user = req.getParameter("userNo");
						syslog = "{\"id\":\"" + slid + "\",\"handleId\":\""+ user + "\",\"handleIp\":\""+ userIp +
								"\",\"handleBehavior\":\""+ "尝试登录管理系统" + "\",\"handleResult\":\""+ "失败" + "\"}";
						syslogService.addSyslog(syslog);
					}
				}
				//如果是访问行为保存访问日志
				if (MatchingUtil.matchingVisit(req.getRequestURI())) {
					syslogService.addSyslog(syslog);
				}
				//如果是操作行为保存操作日志
				if (MatchingUtil.matchingBehavior(req.getRequestURI())) {
					syslogOperationService.addSyslogOperation(syslogOperation);
				}
			} catch (Exception e) {
				// 写入错误日志
				LogHelper.ERROR.log(e.getMessage(),e);
			}
		}
	}
	
	@Override
	public void init(FilterConfig config) throws ServletException {
		this.config = config; 
		context = config.getServletContext();
		filterName = config.getFilterName();
	}
}
