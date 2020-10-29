package com.ht.persistence.dao.inter.complication.smallcorrection.electron;

import java.util.List;

import com.ht.persistence.model.complication.smallcorrection.electron.SmallCor;

/**
 * catalogAreaDao
 * @author yp
 */
public interface EleSmallCorDao {
	
	/**
	 * 获取list
	 * @return CorNotice列表
	 */
	public List<SmallCor> findEleSmallCorList();
	
	
}
