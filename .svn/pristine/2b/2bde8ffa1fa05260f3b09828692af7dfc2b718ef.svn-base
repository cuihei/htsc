package com.ht.service.impl.background.monitor.accesslog;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.ht.common.util.DataConverter;
import com.ht.common.util.LogHelper;
import com.ht.common.util.Utilmd5;
import com.ht.persistence.dao.inter.background.monitor.accesslog.SyslogDao;
import com.ht.persistence.dao.inter.background.organization.employee.UserDao;
import com.ht.persistence.model.background.monitor.accesslog.Syslog;
import com.ht.persistence.model.background.organization.employee.User;
import com.ht.service.inter.background.monitor.accesslog.SyslogService;

/**
 * SyslogService的实现类
 * @author 侯晨
 */
public class SyslogServiceImpl implements SyslogService {
	/**
	 * 注入syslogDao
	 * */
	@Resource(name = "syslogDao") 
	private SyslogDao syslogDao;
	
	/**
	 * 注入userDao
	 * */
	@Resource(name = "userDao") 
	private UserDao userDao;
	
	/**
	 * 新增系统访问日志
	 * @param syslogParam 
	 * @throws Exception
	 */
	@Override
	public void addSyslog(String syslogParam) throws Exception {
		try {
			//将用户String类型转成Syslog类型
			Syslog syslog = (Syslog) DataConverter.convertJson2Object(syslogParam, Syslog.class);
			//设置操作时间
			Date date = new Date();   
		    DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    String dateStr = sdf.format(date);
			syslog.setHandleTime(dateStr);
			//执行保存操作
			syslogDao.addSyslog(syslog);
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}
	
	/**
	 * 更新系统访问日志
	 * @param syslogParam 
	 * @throws Exception
	 */
	@Override
	public void modifySyslog(String syslogParam) throws Exception {
		try {
			//将用户String类型转成Syslog类型
			Syslog syslog = (Syslog) DataConverter.convertJson2Object(syslogParam, Syslog.class);
			syslogDao.modifySyslog(syslog);
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}

	/**
	 * 删除系统访问日志
	 * @param id 
	 * @throws Exception
	 */
	@Override
	public void delSyslog(String syslog) throws Exception {
		try {
			// 将日志String类型转成Syslog对象
			List<Syslog> list = (List<Syslog>) DataConverter.convertJson2List(syslog,Syslog.class);
			for (int i = 0; i < list.size(); i++) {
				// 删除Syslog
				syslogDao.delSyslog(list.get(i));
			}
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}
	
	/**
	 * 获取所有Syslog
	 * @throws Exception
	 */
	@Override
	public List<Syslog> getSyslog() throws Exception {
		try {
			// 获取所有Syslog
			return syslogDao.getSyslog();
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}
	
	/**
	 * 根据id获取Syslog
	 * @param id 
	 * @throws Exception
	 */
	@Override
	public Syslog getSyslog(String id) throws Exception {
		try {
			//创建Syslog对象
			Syslog syslog = new Syslog();
			syslog.setId(id);
			return syslogDao.getSyslog(syslog);
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}

	/**
	 * 验证登陆是否失败
	 * @param req
	 * @return true 成功  false 失败
	 */
	@Override
	public boolean validation(HttpServletRequest req) {
		String userNo = req.getParameter("userNo");
		String password = req.getParameter("password");
		Utilmd5 util = Utilmd5.New();
		password = util.convertMD5(password);
		User user = new User();
		user.setUserNo(userNo);
		List<User> users = userDao.getUserByNo(user);
		if (users.size()!=0) {
			user = users.get(0);
			if (user == null) {
				return false;
			}else{
				if (!user.getPassword().equals(password)) {
					return false;
				}
				else{
					return true;
				}
			}
		}else{
			return false;
		}
	}
}
