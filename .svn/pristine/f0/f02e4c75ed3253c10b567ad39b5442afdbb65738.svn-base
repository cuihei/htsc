package com.ht.persistence.dao.impl.catalog.history;

import java.util.List;

import com.ht.persistence.dao.impl.base.BaseDaoImpl;

import com.ht.persistence.dao.inter.catalog.history.HistoryCatalogDetailDao;
import com.ht.persistence.model.catalog.history.HistoryCatalogDetail;

/**
 * CatalogHistoryDetail 数据映射操作类
 * @author yaoping 
 *
 */
public class HistoryCatalogDetailDaoImpl extends BaseDaoImpl implements HistoryCatalogDetailDao{

	
	/**
	 * 获取所有CatalogHistoryDetail
	 * @return CatalogHistoryDetail列表
	 */
	@Override
	public List<HistoryCatalogDetail> getHistoryDetailList() {
		try {
			//获取历史目录列表
			@SuppressWarnings("unchecked")
			List<HistoryCatalogDetail> result = this.findByNamedQuery("getHistoryList");
			return result;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	
	
	@Override
	public List<HistoryCatalogDetail> getHistoryListByCategoryId(String categoryId) {
		try {
			//获取历史目录列表
			@SuppressWarnings("unchecked")
			List<HistoryCatalogDetail> result = this.findByNamedQueryAndNamedParam("getHistoryListByCategoryId","categoryId",categoryId);
			return result;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}



	@Override
	public void addHistoryCatalogDetail(
			HistoryCatalogDetail historyCatalogDetail) {
		this.save(historyCatalogDetail);
	}

	@Override
	public void modifyHistoryCatalogDetail(
			HistoryCatalogDetail historyCatalogDetail) {
		this.update(historyCatalogDetail);
	}
}
