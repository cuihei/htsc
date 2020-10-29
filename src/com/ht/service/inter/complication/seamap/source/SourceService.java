package com.ht.service.inter.complication.seamap.source;

import java.util.List;

import com.ht.persistence.model.complication.seamap.source.SourceStatus;
import com.ht.persistence.model.complication.seamap.source.TransferStatus;
import com.ht.persistence.model.drawtask.tasksplit.TaskSplit;

public interface SourceService {
  /**
   * 查询
   * @return 源数据list
   * @throws Exception
   */
  public List<TaskSplit> findSourceList() throws Exception;
  
  /**
   * 查询
   * @return 源数据一条流转状态
   * @throws Exception
   */
  public List<SourceStatus> findSourceStatus(String process_inst_id,String task_id) throws Exception;
  
  /**
   * 查询
   * @return 获取源数据采用登记列表list
   * @throws Exception
   */
  public List<TransferStatus> findSourceAdopt(String id) throws Exception;
  
  /**
   * 查询
   * @return 获取源数据幅索引图数据
   * @throws Exception
   */
  public TransferStatus findSourceIndexMap(String id) throws Exception;
}
