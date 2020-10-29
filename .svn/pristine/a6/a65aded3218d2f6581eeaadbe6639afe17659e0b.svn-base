package com.ht.service.impl.datum.bookinfo;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.ht.common.util.DataConverter;
import com.ht.common.util.GenerateSequenceUtil;
import com.ht.common.util.LogHelper;
import com.ht.persistence.dao.inter.datum.bookinfo.ReturnBookDao;
import com.ht.persistence.dao.inter.datum.datum.BorrowingDao;
import com.ht.persistence.dao.inter.datum.datum.DatumFileDao;
import com.ht.persistence.model.datum.bookinfo.ReturnBook;
import com.ht.persistence.model.datum.datum.Borrowing;
import com.ht.persistence.model.datum.datum.DatumFile;
import com.ht.service.inter.datum.bookinfo.ReturnBookService;
import com.ht.service.inter.datum.datum.BorrowingService;

/**
 * 借阅记录Service实现类
 * @author zyd
 *
 */
public class ReturnBookServiceImpl implements ReturnBookService {
	
	/**
	 * 注入借阅记录Dao
	 */
	@Resource (name = "returnBookDao")
	private ReturnBookDao returnBookDao;

	
	/**
	 * 添加借阅记录
	 * @param BorrowingParam 借阅记录数据
	 */
	@Override
	public void addReturnBook(String borrowing, DatumFile datumFile, String returnNo) throws Exception {
		
	}
	
	
	/**
	 * 查询所有借阅记录
	 */
	@Override
	public List<ReturnBook> getReturnBook() throws Exception {
		try {
			// 获取所有Borrowing
			return returnBookDao.getReturnBook();
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}


	
	
}
