package com.ht.service.impl.datum.bookinfo;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.ht.common.util.DataConverter;
import com.ht.common.util.GenerateSequenceUtil;
import com.ht.common.util.LogHelper;
import com.ht.persistence.dao.inter.datum.bookinfo.BookInfoDao;
import com.ht.persistence.dao.inter.datum.bookinfo.ViewBookInfoDao;
import com.ht.persistence.dao.inter.datum.datum.BorrowingDao;
import com.ht.persistence.dao.inter.datum.datum.DatumFileDao;
import com.ht.persistence.model.datum.bookinfo.BookInfo;
import com.ht.persistence.model.datum.bookinfo.ViewBookInfo;
import com.ht.persistence.model.datum.datum.Borrowing;
import com.ht.persistence.model.datum.datum.DatumFile;
import com.ht.persistence.model.system.notice.Notice;
import com.ht.service.inter.datum.bookinfo.BookInfoService;
import com.ht.service.inter.datum.bookinfo.ViewBookInfoService;
import com.ht.service.inter.datum.datum.BorrowingService;

/**
 * 图书资料视图Service实现类
 * @author zyd
 *
 */
public class ViewBookInfoServiceImpl implements ViewBookInfoService {
	
	/**
	 * 注入图书资料视图Dao
	 */
	@Resource(name="viewBookInfoDao")
	ViewBookInfoDao viewBookInfoDao;
	
	
	/**
	 * 获取所有图书资料
	 */
	@Override
	public List<ViewBookInfo> getBookInfo() throws Exception {
		try {
			// 获取所有图书资料
			return viewBookInfoDao.getBookInfo();
		}catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}
	
	/**
	 * 获取所有图书资料
	 */
	@Override
	public List<ViewBookInfo> getBookInfoByStatus() throws Exception {
		try {
			// 获取所有图书资料
			return viewBookInfoDao.getBookinfoByStatus();
		}catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}
	
	/**
	 * 获取一条图书资料
	 */
	@Override
	public ViewBookInfo getBookInfo(String id) throws Exception {
		try {
			ViewBookInfo bookInfo = new ViewBookInfo();
			bookInfo.setId(id);
			// 根据id获取图书资料
			return viewBookInfoDao.getBookInfo(bookInfo);
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}
	
	
	
}
