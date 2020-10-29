package com.ht.persistence.dao.impl.background.monitor.accesslog;

import java.util.List;

import com.ht.persistence.dao.impl.base.BaseDaoImpl;

import com.ht.persistence.dao.inter.background.monitor.accesslog.SyslogDao;
import com.ht.persistence.model.background.monitor.accesslog.Syslog;

/**
 * Syslog 接口的实现类
 * @author 侯晨
 *
 */
public class SyslogDaoImpl extends BaseDaoImpl implements SyslogDao{

	/**
	 * 增加一个Syslog
	 * @param syslog Syslog实体
	 */
	@Override
	public void addSyslog(Syslog syslog) {
		this.save(syslog);
	}

	/**
	 * 更新一个Syslog
	 * @param syslog Syslog实体
	 */
	@Override
	public void modifySyslog(Syslog syslog) {
		this.update(syslog);
	}

	/**
	 * 删除Syslog 相关
	 * @param Syslog Syslog对象
	 */
	@Override
	public void delSyslog(Syslog syslog) {
		this.delete(syslog);
	}

	/**
	 * 获取所有Syslog
	 * @return Syslog列表
	 */
	@Override
	public List<Syslog> getSyslog() {
		try {
			@SuppressWarnings("unchecked")
			List<Syslog> result = this.findByNamedQuery("getSyslog");
			return result;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	/**
	 * 获取一条Syslog
	 * @param Syslog Syslog对象
	 * @return Syslog实体
	 */
	@Override
	public Syslog getSyslog(Syslog syslog) {
		@SuppressWarnings("unchecked")
		List<Syslog> result = this.findByNamedQueryAndNamedParam("getSyslogById", "id", syslog.getId());
		if(result.size()>0){
			return result.get(0);
		}
		return null;
	}
   
}
