package com.ht.service.impl.complication.smallcorrection.electron;

import java.util.List;

import javax.annotation.Resource;

import com.ht.common.util.LogHelper;
import com.ht.persistence.dao.inter.complication.smallcorrection.electron.EleSmallCorDao;
import com.ht.persistence.model.complication.smallcorrection.electron.SmallCor;
import com.ht.service.inter.complication.smallcorrection.electron.EleSmallCorService;

public class EleSmallCorServiceImpl implements EleSmallCorService {

	@Resource
	private EleSmallCorDao eleSmallCorDao;

	/**
	 * 查询
	 * 
	 * @return  改正通告list
	 * @throws Exception
	 */
	@Override
	public List<SmallCor> findEleSmallCorList() throws Exception {
		try {
			// 获取所有CorNotice
			return eleSmallCorDao.findEleSmallCorList();
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(), e);
			// 抛出异常
			throw e;
		}
	}
	
}
