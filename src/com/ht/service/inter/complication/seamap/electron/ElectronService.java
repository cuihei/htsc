package com.ht.service.inter.complication.seamap.electron;

import java.util.List;

import com.ht.persistence.model.complication.seamap.source.SeaMap;
import com.ht.persistence.model.complication.seamap.source.TransferStatus;

public interface ElectronService {
  /**
   * 查询
   * @return 电子海图list
   * @throws Exception
   */
  public List<SeaMap> findEleList() throws Exception;
  
  /**
   * 查询
   * @return 电子海图一条流转状态list
   * @throws Exception
   */
  public List<TransferStatus> findEleStatus(String id) throws Exception;
}
