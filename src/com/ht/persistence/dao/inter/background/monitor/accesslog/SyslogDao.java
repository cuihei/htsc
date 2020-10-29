package com.ht.persistence.dao.inter.background.monitor.accesslog;

import java.util.List;

import com.ht.persistence.model.background.monitor.accesslog.Syslog;

/**
 * SyslogDao接口
 * @author zqy
 */
public interface SyslogDao {
	/**
	 * 增加一个Syslog
	 * @param Syslog Syslog实体
	 */
	public void addSyslog(Syslog syslog);

	/**
	 * 更新一个Syslog
	 * @param Syslog Syslog实体
	 */
	public void modifySyslog(Syslog syslog);

	/**
	 * 删除Syslog 相关
	 * @param Syslog Syslog对象
	 */
	public void delSyslog(Syslog syslog);
	
	/**
	 * 获取所有Syslog对象集合
	 * @return List<Syslog>列表
	 */
	public List<Syslog> getSyslog();

	/**
	 * 获取一条Syslog
	 * @param Syslog Syslog对象
	 * @return Syslog实体
	 */
	public Syslog getSyslog(Syslog syslog);
}
