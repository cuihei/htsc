package com.ht.persistence.dao.impl.complication.correctionnotice.correctionnotice;

import java.util.List;

import com.ht.persistence.dao.impl.base.BaseDaoImpl;

import com.ht.persistence.dao.inter.complication.correctionnotice.correctionnotice.CorNoticeDao;
import com.ht.persistence.model.complication.correctionnotice.CorNotice;


/**
 *  目录区域数据映射操作类
 * @author yaoping 
 *
 */
public class CorNoticeDaoImpl extends BaseDaoImpl implements CorNoticeDao{

	
	/**
	 * 查询
	 * 获取所有 改正通告数据
	 * @return  改正通告list
	 */
	@Override
	public List<CorNotice> findCorNoticeList() {
		try {
			@SuppressWarnings("unchecked")
			List<CorNotice> result = this.findByNamedQuery("getAreaList");
			return result;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

}
