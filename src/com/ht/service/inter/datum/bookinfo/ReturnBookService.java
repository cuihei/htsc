package com.ht.service.inter.datum.bookinfo;

import java.util.List;

import com.ht.persistence.model.datum.bookinfo.ReturnBook;
import com.ht.persistence.model.datum.datum.Borrowing;
import com.ht.persistence.model.datum.datum.DatumFile;

/**
 * 归还记录Service
 * @author zyd
 *
 */
public interface ReturnBookService {
	
	/**
	 * 保存归还记录
	 * @param datumFile 
	 * @param Borrowing
	 * @throws Exception
	 */
	public void addReturnBook(String borrowing, DatumFile datumFile, String returnNo) throws Exception;
	
	/**
	 * 获取所有归还记录
	 * @return
	 * @throws Exception
	 */
	public List<ReturnBook> getReturnBook() throws Exception;
	
}
