package com.ht.persistence.dao.impl.complication.correctionnotice.templete;

import java.util.List;

import com.ht.persistence.dao.impl.base.BaseDaoImpl;

import com.ht.persistence.dao.inter.complication.correctionnotice.templete.TempleteDao;
import com.ht.persistence.model.complication.correctionnotice.CorNotice;
import com.ht.persistence.model.complication.seamap.source.TransferStatus;


/**
 *  目录区域数据映射操作类
 * @author yaoping 
 *
 */
public class TempleteDaoImpl extends BaseDaoImpl implements  TempleteDao{

	/**
	 * 查询
	 * 获取所有 改正通告数据
	 * @return  改正通告list
	 */
	@Override
	public List<CorNotice> findTempleteList() {
		try {
			@SuppressWarnings("unchecked")
			List<CorNotice> result = this.findByNamedQuery("getAreaList");
			return result;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}


	/**
	 * 查询
	 * 源数据一条流转状态
	 * @return 源数据流转状态list
	 */
	@Override
	public List<TransferStatus> findTempleteStatus(TransferStatus transferStatus) {
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
