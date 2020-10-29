package com.ht.persistence.dao.impl.complication.smallcorrection.paper;

import java.util.List;

import com.ht.persistence.dao.impl.base.BaseDaoImpl;

import com.ht.persistence.dao.inter.complication.smallcorrection.paper.PaperSmallCorDao;
import com.ht.persistence.model.complication.correctionnotice.CorNotice;
import com.ht.persistence.model.complication.smallcorrection.electron.SmallCor;


/**
 *  目录区域数据映射操作类
 * @author yaoping 
 *
 */
public class PaperSmallCorDaoImpl extends BaseDaoImpl implements PaperSmallCorDao{

	
	/**
	 * 查询
	 * 获取所有 改正通告数据
	 * @return  改正通告list
	 */
	@Override
	public List<SmallCor> findPaperSmallCorList() {
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
