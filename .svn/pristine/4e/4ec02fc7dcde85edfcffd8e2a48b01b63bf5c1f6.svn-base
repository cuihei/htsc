package com.ht.persistence.dao.inter.complication.seamap.source;

import java.util.List;

import com.ht.persistence.model.complication.seamap.source.SourceStatus;
import com.ht.persistence.model.complication.seamap.source.TransferStatus;
import com.ht.persistence.model.drawtask.tasksplit.TaskSplit;

/**
 * catalogAreaDao
 * @author yp
 */
public interface SourceDao {
	
	/**
	 * 源数据list
	 * @return SeaMap列表
	 */
	public List<TaskSplit> findSourceList();
	
	/**
	 * 源数据一条流转状态
	 * @return SourceStatus列表
	 */
	public List<SourceStatus> findSourceStatus(SourceStatus sourceStatus);
	
	/**
	 * 源数据采用登记列表
	 * @return TransferStatus列表
	 */
	public List<TransferStatus> findSourceAdopt(TransferStatus transferStatus);
	
	/**
	 * 源数据幅索引图数据
	 * @return TransferStatus列表
	 */
	public TransferStatus findSourceIndexMap(TransferStatus transferStatus);

}
