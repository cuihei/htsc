package com.ht.service.impl.complication.correctionnotice.templete;

import java.util.List;

import javax.annotation.Resource;

import com.ht.common.util.LogHelper;
import com.ht.persistence.dao.inter.complication.correctionnotice.templete.TempleteDao;
import com.ht.persistence.model.complication.correctionnotice.CorNotice;
import com.ht.persistence.model.complication.seamap.source.TransferStatus;
import com.ht.service.inter.complication.correctionnotice.templete.TempleteService;

public class TempleteServiceImpl implements TempleteService {

	@Resource
	private TempleteDao templeteDao;

	/**
	 * 查询
	 * 
	 * @return  改正通告模版list
	 * @throws Exception
	 */
	@Override
	public List<CorNotice> findTempleteList() throws Exception {
		try {
			// 获取所有CorNotice
			return templeteDao.findTempleteList();
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(), e);
			// 抛出异常
			throw e;
		}
	}
	
	/**
	 * 查询
	 * 
	 * @return  改正通告模版一条流转状态
	 * @throws Exception
	 */
	@Override
	public List<TransferStatus> findStatus(String id) throws Exception {
		try {
			TransferStatus tf = new TransferStatus();
			tf.setId(id);
			// 获取所有数据
			return templeteDao.findTempleteStatus(tf);
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(), e);
			// 抛出异常
			throw e;
		}
	}
}
