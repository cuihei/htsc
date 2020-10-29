package com.ht.persistence.dao.inter.complication.seamap.paper;

import java.util.List;

import com.ht.persistence.model.complication.seamap.source.SeaMap;
import com.ht.persistence.model.complication.seamap.source.TransferStatus;

/**
 * catalogAreaDao
 * @author yp
 */
public interface PaperDao {
	
	/**
	 * 纸海图list
	 * @return SeaMap列表
	 */
	public List<SeaMap> findPaperList();

	/**
	 * 纸海图一条流转状态list
	 * @return TransferStatus列表
	 */
	public List<TransferStatus> findSourceStatus(TransferStatus transferStatus);
}
