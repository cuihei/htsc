package com.ht.persistence.dao.inter.datum.bookinfo;

import java.util.List;

import com.ht.persistence.model.datum.bookinfo.ReturnBook;
import com.ht.persistence.model.datum.datum.Borrowing;

/**
 * 图书归还Dao
 * @author zyd
 *
 */
public interface ReturnBookDao {
	
	/**
	 * 增加归还记录
	 * @param ReturnBook ReturnBook实体
	 */
	public void addReturnBook(ReturnBook borrowing);
	
	/**
	 * 增加归还记录
	 * @param ReturnBook ReturnBook实体
	 */
	public void modify(ReturnBook borrowing);
	
	/**
	 * 获取所有归还记录
	 * @return ReturnBook列表
	 */
	public List<ReturnBook> getReturnBook();
	
	/**
	 * 获取所有归还记录
	 * @return ReturnBook列表
	 */
	public ReturnBook getReturnBook(ReturnBook borrowing);

	public void delete(ReturnBook borrowing);

	public int getReturnByBorrowId(ReturnBook borrowing);
	
}
