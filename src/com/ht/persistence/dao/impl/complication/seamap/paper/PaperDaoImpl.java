package com.ht.persistence.dao.impl.complication.seamap.paper;

import java.util.List;

import com.ht.persistence.dao.impl.base.BaseDaoImpl;

import com.ht.persistence.dao.inter.complication.seamap.paper.PaperDao;
import com.ht.persistence.model.complication.seamap.source.SeaMap;
import com.ht.persistence.model.complication.seamap.source.TransferStatus;


/**
 *  目录区域数据映射操作类
 * @author yaoping 
 *
 */
public class PaperDaoImpl extends BaseDaoImpl implements PaperDao{

	/**
	 * 查询
	 * 获取所有纸海图数据
	 * @return 纸海图list
	 */
	@Override
	public List<SeaMap> findPaperList() {
		try {
			@SuppressWarnings("unchecked")
			List<SeaMap> result = this.findByNamedQuery("getAreaList");
			return result;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	/**
	 * 查询
	 * @return 纸海图一条流转状态list
	 */
	@Override
	public List<TransferStatus> findSourceStatus(TransferStatus transferStatus) {
		try {
			@SuppressWarnings("unchecked")
			List<TransferStatus> result =this.findByNamedQueryAndNamedParam("getCatalogAreaById","id",transferStatus.getId());
			return result;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
}
