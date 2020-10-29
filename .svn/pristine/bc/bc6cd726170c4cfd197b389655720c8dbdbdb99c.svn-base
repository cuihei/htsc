package com.ht.service.inter.background.monitor.accesslog;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ht.persistence.model.background.monitor.accesslog.Syslog;

/**
 * SyslogService接口
 * @author 侯晨
 */
public interface SyslogService {
	/**
	  * 保存系统访问日志
	  * @param syslog
	  * @throws Exception
	  */
	public void addSyslog(String syslog) throws Exception;
	
	/**
	  * 修改系统访问日志
	  * @param syslog
	  * @throws Exception
	  */
	public void modifySyslog(String syslog) throws Exception;
	
	/**
	  * 删除系统访问日志
	  * @param id
	  * @throws Exception
	  */
	public void delSyslog(String syslog) throws Exception;
	
	/**
	  * 查询所有系统访问日志
	  * @throws Exception
	  */
	public List<Syslog> getSyslog() throws Exception;
	
	/**
	  * 查询一条系统访问日志
	  * @param id
	  * @throws Exception
	  */
	public Syslog getSyslog(String id) throws Exception;
	
	/**
	 * 验证登陆是否失败
	 * @param req
	 * @return true 成功  false 失败
	 */
	public boolean validation(HttpServletRequest req);
}
