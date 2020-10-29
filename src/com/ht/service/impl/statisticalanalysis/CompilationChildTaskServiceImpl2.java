package com.ht.service.impl.statisticalanalysis;

import java.util.List;

import javax.annotation.Resource;

import com.ht.common.util.LogHelper;
import com.ht.persistence.dao.inter.statisticalanalysis.CompilationChildTaskDao;
import com.ht.service.inter.statisticalanalysis.CompilationChildTaskService;

public class CompilationChildTaskServiceImpl2 implements CompilationChildTaskService {
	
	@Resource(name="compilationChildTaskDao")
	CompilationChildTaskDao compilationChildTaskDao;

	
	@Override
	public List<String> getYearList() throws Exception {
		try {
			return  compilationChildTaskDao.getYearList();
		}catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}

	
}
