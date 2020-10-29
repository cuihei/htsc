package com.ht.persistence.dao.inter.complication.correctionnotice.templete;

import java.util.List;

import com.ht.persistence.model.complication.correctionnotice.CorNotice;
import com.ht.persistence.model.complication.seamap.source.TransferStatus;

/**
 * catalogAreaDao
 * @author yp
 */
public interface TempleteDao {
	
	/**
	 * 获取list
	 * @return CorNotice列表
	 */
	public List<CorNotice> findTempleteList();
	
	/**
	 * 源数据一条流转状态
	 * @return TransferStatus列表
	 */
	public List<TransferStatus> findTempleteStatus(TransferStatus transferStatus);
	
	
}
