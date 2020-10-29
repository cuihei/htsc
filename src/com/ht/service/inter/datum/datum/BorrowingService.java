package com.ht.service.inter.datum.datum;

import java.util.List;

import com.ht.persistence.model.datum.datum.Borrowing;
import com.ht.persistence.model.datum.datum.BorrowingReturn;
import com.ht.persistence.model.datum.datum.DatumFile;
import com.ht.persistence.model.datum.datum.VBorrowing;
import com.ht.persistence.model.datum.datum.VBorrowingReturn;

/**
 * 借阅记录Service
 * @author zyd
 *
 */
public interface BorrowingService {
	
	/**
	 * 保存借阅记录
	 * @param datumFile 
	 * @param Borrowing
	 * @throws Exception
	 */
	public void addBorrowing(String borrowing, DatumFile datumFile, String returnNo) throws Exception;
	
	/**
	 * 修改借阅记录
	 * @param Borrowing
	 * @throws Exception
	 */
	public void modifyBorrowing(String borrowing) throws Exception;
	
	/**
	 * 删除借阅记录
	 * @param id
	 * @throws Exception
	 */
	public void deleteBorrowing(String id) throws Exception;
	
	/**
	 * 获取所有借阅记录
	 * @return
	 * @throws Exception
	 */
	public List<Borrowing> getBorrowing() throws Exception;
	
	/**
	 * 获取一条借阅记录
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Borrowing getBorrowing(String id) throws Exception;
	
	/**
	 * 获取多条借阅记录
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List<VBorrowing> getReturnList(String ids) throws Exception;

	/**
	 * 根据文件Id获取一条借阅记录
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Borrowing getBorrowingByFileId(String fileId) throws Exception;

	public List<BorrowingReturn> getBorrowingReturn() throws Exception;

	public List<VBorrowingReturn> getBorrowingReturnListByPerson(String userName)
			throws Exception;
}
