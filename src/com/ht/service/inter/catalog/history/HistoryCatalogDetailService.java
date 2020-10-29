package com.ht.service.inter.catalog.history;

import java.util.List;

import com.ht.persistence.model.catalog.history.HistoryCatalogDetail;


/** 
* @ClassName: HistoryCatalogDetailService 
* @Description: 历史目录接口 
* @author yp
* @date 2016年10月17日 上午9:28:40 
*  
*/
public interface HistoryCatalogDetailService {
	
  /**
   * 添加或更新	
   * @param historyCatalogDetail
   * @throws Exception
   */
  public void addOrUodateHistoryCatalogDetail(String historyCatalogDetail) throws Exception;
	
  /**
   * 历史目录列表
   * @param
   * @return
   */
  public List<HistoryCatalogDetail> getHistoryDetailList() throws Exception;
  
  /**
   * 根据目录类型获取
   * 历史目录列表
   * @param
   * @return
   */
  public List<HistoryCatalogDetail> getHistoryListByCategoryId(String categoryId) throws Exception;
  
}
