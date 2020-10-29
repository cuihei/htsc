package com.ht.service.inter.complication.correctionnotice.templete;

import java.util.List;

import com.ht.persistence.model.complication.correctionnotice.CorNotice;
import com.ht.persistence.model.complication.seamap.source.TransferStatus;

public interface TempleteService {
	
  /**
   * 查询
   * @return 改正通告模版list
   * @throws Exception
   */
  public List<CorNotice> findTempleteList() throws Exception;
  
  /**
   * 查询
   * @return 改正通告模版一条流转状态
   * @throws Exception
   */
  public List<TransferStatus> findStatus(String id) throws Exception;
 
}
