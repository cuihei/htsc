package com.ht.persistence.dao.inter.background.monitor.operationlog;

import java.util.List;

import com.ht.persistence.model.background.monitor.operationlog.SyslogOperation;

/**
 * SyslogOperationDao 系统操作日志Dao
 * @author 刘凯
 */
public interface SyslogOperationDao {
	
	/**
	 * 增加一个SyslogOperation
	 * @param SyslogOperation 操作日志实体
	 */
	public void addSyslogOperation(SyslogOperation syslogOperation);

	/**
	 * 更新一个SyslogOperation
	 * @param SyslogOperation 操作日志实体
	 */
	public void modifySyslogOperation(SyslogOperation syslogOperation);

	/**
	 * 删除SyslogOperation
	 * @param syslogOperation 操作日志实体
	 */
	public void delSyslogOperation(SyslogOperation syslogOperation);
	
	/**
	 * 获取所有SyslogOperation
	 * @return SyslogOperation列表
	 */
	public List<SyslogOperation> getSyslogOperation();

	/**
	 * 获取一条SyslogOperation
	 * @param syslogOperation 操作日志实体
	 * @return SyslogOperation实体
	 */
	public SyslogOperation getSyslogOperation(SyslogOperation syslogOperation);
}
