package com.ht.persistence.dao.inter.datum.datum;

import java.util.List;

import com.ht.persistence.model.datum.datum.Borrowing;
import com.ht.persistence.model.datum.datum.BorrowingReturn;


/**
 * 资料借阅Dao
 * @author zyd
 *
 */
public interface BorrowingDao {
	
	/**
	 * 增加借阅记录
	 * @param Borrowing Borrowing实体
	 */
	public void addBorrowing(Borrowing borrowing);
	
	/**
	 * 更新借阅记录
	 * @param Borrowing Borrowing实体
	 */
	public void modifyBorrowing(Borrowing borrowing);
	
	/**
	 * 删除借阅记录
	 * @param Borrowing Borrowing实体
	 */
	public void deleteBorrowing(Borrowing borrowing);
	
	/**
	 * 获取所有借阅记录
	 * @return Borrowing列表
	 */
	public List<Borrowing> getBorrowing();
	
	/**
	 * 获取一条借阅记录
	 * @param id Borrowing主键
	 * @return Borrowing实体
	 */
	public Borrowing getBorrowing(Borrowing borrowing);
	
	/**
	 * 根据文件Id获取一条记录
	 * @param borrowing
	 * @return
	 */
	public Borrowing getBorrowingByFileId(Borrowing borrowing);
	
	/**
	 * 获取全部图书借阅归还信息
	 * return Borrowing列表
	 */
	public List<BorrowingReturn> getBorrowingReturn();

	public List<BorrowingReturn> getBorrowingReturnListByPerson(BorrowingReturn br);
	
}
