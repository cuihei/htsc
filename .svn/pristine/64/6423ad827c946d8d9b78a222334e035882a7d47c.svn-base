package com.ht.persistence.dao.impl.drawtask.taskbook.history;

import java.util.List;

import com.ht.persistence.dao.impl.base.BaseDaoImpl;

import com.ht.persistence.dao.inter.drawtask.taskbook.history.HistoryTaskBookDao;
import com.ht.persistence.model.drawtask.taskbook.history.HistoryTaskBook;

public class HistoryTaskBookDaoImpl extends BaseDaoImpl implements HistoryTaskBookDao {

	/**
	  * 查询历史计划书头部
	  * @return 历史计划书头部list
	  * @throws Exception
	  */
	@Override
	public List<HistoryTaskBook> findList() throws Exception {
		try{
		@SuppressWarnings("unchecked")
		List<HistoryTaskBook> list = this.findByNamedQuery("findHTaskBookList");
		return list;
		}catch(Exception e){
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	 /**
	  * 查询一条历史计划书详细
	  * @return 历史计划书详细list
	  * @throws Exception
	  */
	@Override
	public List<HistoryTaskBook> findDetailList(HistoryTaskBook historyTaskBook) throws Exception {
		try{
		@SuppressWarnings("unchecked")
		List<HistoryTaskBook> list = this.findByNamedQueryAndNamedParam("findHTaskBookById", "id", historyTaskBook.getId());
		return list;
		}catch(Exception e){
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	/**
	 * 删除一条历史计划书
	 * @param HistoryTaskBook historyTaskBook实体
	 * @throws Exception
	 */
	@Override
	public void delete(HistoryTaskBook historyTaskBook) throws Exception {
		super.delete(historyTaskBook);
	}

	@Override
	public List<HistoryTaskBook> findListByTaskBookId(String taskBookId)
			throws Exception {
		try{
			@SuppressWarnings("unchecked")
			List<HistoryTaskBook> list = this.findByNamedQueryAndNamedParam("findHTaskBookByTaskBookId", "id", taskBookId);
			return list;
			}catch(Exception e){
				System.out.println(e.getMessage());
				return null;
			}
	}

	@Override
	public void addHistoryTaskBook(HistoryTaskBook historyTaskBook)
			throws Exception {
		// TODO Auto-generated method stub
		this.save(historyTaskBook);
	}

}
