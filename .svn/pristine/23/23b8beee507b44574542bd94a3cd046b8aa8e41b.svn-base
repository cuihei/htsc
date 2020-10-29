package com.ht.service.impl.complication.correctionnotice.sourcedatasmallcorrection;

import java.util.List;

import javax.annotation.Resource;

import com.ht.common.util.LogHelper;
import com.ht.persistence.dao.inter.complication.correctionnotice.sourcedatasmallcorrection.SourSmallCorDao;
import com.ht.persistence.model.complication.correctionnotice.CorNotice;
import com.ht.service.inter.complication.correctionnotice.sourcedatasmallcorrection.SourSmallCorService;

public class SourSmallCorServiceImpl implements SourSmallCorService {

	@Resource
	private SourSmallCorDao sourSmallCorDao;

	/**
	 * 查询
	 * 
	 * @return  改正通告list
	 * @throws Exception
	 */
	@Override
	public List<CorNotice> findSourSmallCorList() throws Exception {
		try {
			// 获取所有CorNotice
			return sourSmallCorDao.findSourSmallCorList();
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(), e);
			// 抛出异常
			throw e;
		}
	}
	
}
