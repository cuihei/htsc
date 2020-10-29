package com.ht.service.impl.catalog.history;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;

import com.ht.common.util.DataConverter;
import com.ht.common.util.GenerateSequenceUtil;
import com.ht.common.util.LogHelper;
import com.ht.persistence.dao.inter.catalog.history.HistoryCatalogDetailDao;
import com.ht.persistence.model.catalog.history.HistoryCatalogDetail;
import com.ht.persistence.model.system.workflow.process.ProcessFlow;
import com.ht.service.inter.catalog.history.HistoryCatalogDetailService;


/** 
* @ClassName: HistoryCatalogDetailServiceImpl 
* @Description: 历史目录的业务处理类 
* @author yp
* @date 2016年10月17日 上午9:32:20 
*  
*/
public class HistoryCatalogDetailServiceImpl implements HistoryCatalogDetailService {
	

	/**
	 * 注入历史目录数据操作类
	 */
	@Resource(name = "historyCatalogDetailDao") 
	private HistoryCatalogDetailDao historyCatalogDetailDao;
	
	
	@Override
	public void addOrUodateHistoryCatalogDetail(String historyCatalogDetail)
			throws Exception {
		HistoryCatalogDetail hcd = (HistoryCatalogDetail) DataConverter.
				convertJson2Object(historyCatalogDetail,HistoryCatalogDetail.class);
		if(StringUtils.isBlank(hcd.getId())){
			hcd.setId(GenerateSequenceUtil.generateSequenceNo());
			// 执行添加操作
			historyCatalogDetailDao.addHistoryCatalogDetail(hcd);
		}else{
			// 执行保存操作
			historyCatalogDetailDao.modifyHistoryCatalogDetail(hcd);
		}
	}
	
	/*
	 * (非 Javadoc) 
	* Title: getHistoryDetailList
	* Description: 获取历史目录列表
	* @return
	* @throws Exception 
	* @see com.ht.service.inter.catalog.history.HistoryCatalogDetailService#getHistoryDetailList()
	 */
	@Override
	public List<HistoryCatalogDetail> getHistoryDetailList() throws Exception {
		try {
			// 获取所有的历史目录
			return historyCatalogDetailDao.getHistoryDetailList();
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}
	
	
	/*
	 * (非 Javadoc) 
	* Title: getHistoryDetailList
	* Description: 根据目录类型获取历史目录列表
	* @return
	* @throws Exception 
	* @see com.ht.service.inter.catalog.history.HistoryCatalogDetailService#getHistoryDetailList()
	 */
	@Override
	public List<HistoryCatalogDetail> getHistoryListByCategoryId(String categoryId) throws Exception {
		try {
			return historyCatalogDetailDao.getHistoryListByCategoryId(categoryId);
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}
}
