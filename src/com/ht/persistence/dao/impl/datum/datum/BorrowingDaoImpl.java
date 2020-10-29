package com.ht.persistence.dao.impl.datum.datum;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;

import com.ht.persistence.dao.impl.base.BaseDaoImpl;
import com.ht.persistence.dao.inter.datum.datum.BorrowingDao;
import com.ht.persistence.model.complication.formprop.FormValue;
import com.ht.persistence.model.datum.bookinfo.ReturnBook;
import com.ht.persistence.model.datum.datum.Borrowing;
import com.ht.persistence.model.datum.datum.BorrowingReturn;

/**
 * Borrowing数据映射操作类
 * @author zyd
 *
 */
public class BorrowingDaoImpl extends BaseDaoImpl implements BorrowingDao {
	
	/**
	 * 增加Borrowing
	 * @param Borrowing borrowing实体
	 */
	@Override
	public void addBorrowing(Borrowing borrowing) {
		this.save(borrowing);

	}
	
	/**
	 * 更新Borrowing
	 * @param Borrowing borrowing实体
	 */
	@Override
	public void modifyBorrowing(Borrowing borrowing) {
		this.update(borrowing);
	}
	
	/**
	 * 删除Borrowing
	 * @param id Borrowing主键
	 */
	@Override
	public void deleteBorrowing(Borrowing borrowing) {
		this.delete(borrowing);
	}
	
	/**
	 * 获取全部Borrowing
	 * return Borrowing列表
	 */
	@Override
	public List<Borrowing> getBorrowing() {
		try{
			@SuppressWarnings("unchecked")
			List<Borrowing> result = this.findByNamedQuery("getBorrowing");
			return result;
		} catch(Exception e){
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	/**
	 * 获取一条Borrowing
	 * @param id Borrowing主键
	 * return Borrowing实体
	 */
	@Override
	public Borrowing getBorrowing(Borrowing borrowing) {
		@SuppressWarnings("unchecked")
		List<Borrowing> result = this.findByNamedQueryAndNamedParam("getBorrowingById", "id", borrowing.getId());
		
		if(result.size() > 0){
			return result.get(0);
		}
		return null;
	}
	
	/**
	 * 根据文件Id获取一条记录
	 */
	@Override
	public Borrowing getBorrowingByFileId(Borrowing borrowing) {
		@SuppressWarnings("unchecked")
		List<Borrowing> result = this.findByNamedQueryAndNamedParam("getBorrowingByFileId", "fileId", borrowing.getBorrowCode());
		
		if(result.size() > 0){
			return result.get(0);
		}
		return null;
	}

	/**
	 * 获取全部图书借阅归还信息
	 * return Borrowing列表
	 */
	@Override
	public List<BorrowingReturn> getBorrowingReturn() {
		@SuppressWarnings("unchecked")
		List<BorrowingReturn> list = this.findByNamedQuery("getBorrowingReturnList");
		return list;
	}
	
	/**
	 * 获取登陆人全部图书借阅归还信息
	 * return Borrowing列表
	 */
	@Override
	public List<BorrowingReturn> getBorrowingReturnListByPerson(BorrowingReturn br) {
		@SuppressWarnings("unchecked")
		List<BorrowingReturn> list = this.findByNamedQueryAndNamedParam("getBorrowingReturnListByPerson","borrowPerson",br.getBorrowPerson());
		return list;
	}

}
