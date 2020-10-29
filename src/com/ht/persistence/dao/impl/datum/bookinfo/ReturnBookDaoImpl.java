package com.ht.persistence.dao.impl.datum.bookinfo;

import java.util.List;

import com.ht.persistence.dao.impl.base.BaseDaoImpl;
import com.ht.persistence.dao.inter.datum.bookinfo.ReturnBookDao;
import com.ht.persistence.model.background.organization.employee.User;
import com.ht.persistence.model.datum.bookinfo.ReturnBook;

/**
 * returnBook数据映射操作类
 * @author zyd
 *
 */
public class ReturnBookDaoImpl extends BaseDaoImpl implements ReturnBookDao {
	
	/**
	 * 增加returnBook
	 * @param returnBook returnBook实体
	 */
	public void addReturnBook(ReturnBook returnBook) {
		this.save(returnBook);
	}
	
	/**
	 * 获取全部returnBook
	 * return returnBook列表
	 */
	public List<ReturnBook> getReturnBook() {
		try{
			@SuppressWarnings("unchecked")
			List<ReturnBook> result = this.findByNamedQuery("getReturnBook");
			return result;
		} catch(Exception e){
			System.out.println(e.getMessage());
			return null;
		}
	}

	@Override
	public ReturnBook getReturnBook(ReturnBook borrowing) {
		@SuppressWarnings("unchecked")
		//执行查询操作
		List<ReturnBook> result = this.findByNamedQueryAndNamedParam("getReturnById", "id", borrowing.getId());
		if(result.size()>0){
			return result.get(0);
		}
		return null;
	}
	
	@Override
	public int getReturnByBorrowId(ReturnBook borrowing) {
		@SuppressWarnings("unchecked")
		//执行查询操作
		List<ReturnBook> result = this.findByNamedQueryAndNamedParam("getReturnByBorrowId", "borrowId", borrowing.getBorrowId());
		int count = 0;
		if(result.size()>0){
			for (int i = 0; i < result.size(); i++)
			{
				String returnNo = result.get(i).getReturnNo();
				count += Integer.valueOf(returnNo);
			}
			return count;
		}
		return 0;
	}

	@Override
	public void modify(ReturnBook borrowing) {
		borrowing = (ReturnBook)this.merge(borrowing);
		this.update(borrowing);
	}
	
	@Override
	public void delete(ReturnBook borrowing) {
		this.delete(borrowing);
	}
	

}
