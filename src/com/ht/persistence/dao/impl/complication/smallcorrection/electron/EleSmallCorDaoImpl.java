package com.ht.persistence.dao.impl.complication.smallcorrection.electron;

import java.util.List;

import com.ht.persistence.dao.impl.base.BaseDaoImpl;

import com.ht.persistence.dao.inter.complication.smallcorrection.electron.EleSmallCorDao;
import com.ht.persistence.model.complication.smallcorrection.electron.SmallCor;


/**
 *  目录区域数据映射操作类
 * @author yaoping 
 *
 */
public class EleSmallCorDaoImpl extends BaseDaoImpl implements EleSmallCorDao{

	
	/**
	 * 查询
	 * 获取所有 改正通告数据
	 * @return  改正通告list
	 */
	@Override
	public List<SmallCor> findEleSmallCorList() {
		try {
			@SuppressWarnings("unchecked")
			List<SmallCor> result = this.findByNamedQuery("getCorNoticeList");
			return result;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

}
