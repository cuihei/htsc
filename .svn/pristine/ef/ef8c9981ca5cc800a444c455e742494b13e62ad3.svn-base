package com.ht.persistence.dao.impl.complication.seamap.electron;

import java.util.List;

import com.ht.persistence.dao.impl.base.BaseDaoImpl;

import com.ht.persistence.dao.inter.complication.seamap.electron.ElectronDao;
import com.ht.persistence.model.complication.seamap.source.SeaMap;
import com.ht.persistence.model.complication.seamap.source.TransferStatus;


/**
 *  目录区域数据映射操作类
 * @author yaoping 
 *
 */
public class ElectronDaoImpl extends BaseDaoImpl implements ElectronDao{

	/**
	 * 查询
	 * 获取所有电子海图数据
	 * @return 电子海图list
	 */
	@Override
	public List<SeaMap> findEleList() {
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
	 * 获取电子海图一条流转状态数据
	 * @return 电子海图一条流转状态list
	 */
	@Override
	public List<TransferStatus> findEleStatus(TransferStatus transferStatus) {
		try {
			@SuppressWarnings("unchecked")
			List<TransferStatus> result = this.findByNamedQueryAndNamedParam("getCatalogAreaById","id",transferStatus.getId());
			return result;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
}
