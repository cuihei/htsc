package com.ht.service.impl.complication.correctionnotice.correctionnotice;

import java.util.List;

import javax.annotation.Resource;

import com.ht.common.util.LogHelper;
import com.ht.persistence.dao.inter.complication.correctionnotice.correctionnotice.CorNoticeDao;
import com.ht.persistence.model.complication.correctionnotice.CorNotice;
import com.ht.service.inter.complication.correctionnotice.correctionnotice.CorNoticeService;

public class CorNoticeServiceImpl implements CorNoticeService {

	@Resource
	private CorNoticeDao corNoticeDao;

	/**
	 * 查询
	 * 
	 * @return  改正通告list
	 * @throws Exception
	 */
	@Override
	public List<CorNotice> findCorNoticeList() throws Exception {
		try {
			// 获取所有CorNotice
			return corNoticeDao.findCorNoticeList();
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(), e);
			// 抛出异常
			throw e;
		}
	}
	
}
