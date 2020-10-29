package com.ht.persistence.dao.impl.background.monitor.operationlog;

import java.util.List;

import com.ht.persistence.dao.impl.base.BaseDaoImpl;

import com.ht.persistence.dao.inter.background.monitor.operationlog.SyslogOperationDao;
import com.ht.persistence.model.background.monitor.operationlog.SyslogOperation;

/**
 * SyslogOperation 数据映射操作类
 * @author liukai 
 *
 */
public class SyslogOperationDaoImpl extends BaseDaoImpl implements SyslogOperationDao{

	/**
	 * 增加一个SyslogOperation
	 * @param SyslogOperation 访问日志实体
	 */
	@Override
	public void addSyslogOperation(SyslogOperation syslogOperation) {
		//执行保存操作
		this.save(syslogOperation);
	}

	/**
	 * 更新一个SyslogOperation
	 * @param SyslogOperation 访问日志实体
	 */
	@Override
	public void modifySyslogOperation(SyslogOperation syslogOperation) {
		//执行修改操作
		this.update(syslogOperation);
	}

	/**
	 * 删除SyslogOperation
	 * @param syslogOperation 访问日志实体
	 */
	@Override
	public void delSyslogOperation(SyslogOperation syslogOperation) {
		//执行删除操作
		this.delete(syslogOperation);
	}

	/**
	 * 获取所有SyslogOperation
	 * @return SyslogOperation列表
	 */
	@Override
	public List<SyslogOperation> getSyslogOperation() {
		try {
			@SuppressWarnings("unchecked")
			//执行查询操作
			List<SyslogOperation> result = this.findByNamedQuery("getSyslogOperation");
			return result;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	/**
	 * 获取一条SyslogOperation
	 * @param SyslogOperation 访问日志实体
	 * @return SyslogOperation实体
	 */
	@Override
	public SyslogOperation getSyslogOperation(SyslogOperation syslogOperation) {
		@SuppressWarnings("unchecked")
		//执行查询操作
		List<SyslogOperation> result = this.findByNamedQueryAndNamedParam("getSyslogOperationById", "id", syslogOperation.getId());
		if(result.size()>0){
			return result.get(0);
		}
		return null;
	}
   
}
