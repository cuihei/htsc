package com.ht.service.inter.background.monitor.operationlog;

import java.util.List;

import com.ht.persistence.model.background.monitor.operationlog.SyslogOperation;

/**
 * 系统操作日志接口
 * @author liukai
 */
public interface SyslogOperationService {
	
  /**
   * 保存操作日志数据
   * @param syslogOperation
   * @throws Exception
   * */
  public void addSyslogOperation(String syslogOperation) throws Exception;
  
  /**
   * 修改操作日志数据
   * @param syslogOperation
   * @throws Exception
   * */
  public void modifySyslogOperation(String syslogOperation) throws Exception;
  
  /**
   * 删除操作日志数据
   * @param id
   * @throws Exception
   * */
  public void delSyslogOperation(String syslogOperation) throws Exception;
  
  /**
   * 查询操作日志列表
   * @throws Exception
   * */
  public List<SyslogOperation> getSyslogOperation() throws Exception;
  
  /**
   * 查询一条操作日志
   * @param id
   * @throws Exception
   * */
  public SyslogOperation getSyslogOperation(String id) throws Exception;
  
  /**
   * 导出Excel
   * @param syslogList 
   * @param String sheetName sheet名称
   * @param String[] title 第一行标题
   * @param String url 下载后存放的位置
   * @param String excelName excel的名称
   * @param List<SyslogOperation> soList 对象集合
   * @return
   */
  public void exportExcel(String string, String[] title, String string2,
		String name, List<SyslogOperation> soList);
}
