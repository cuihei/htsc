package com.ht.persistence.dao.inter.drawtask.taskbook.history;

import java.util.List;

import com.ht.persistence.model.drawtask.taskbook.book.TaskBook;
import com.ht.persistence.model.drawtask.taskbook.history.HistoryTaskBook;

/**
 * MonthPlanDao
 * @author yaoping
 *
 */
public interface HistoryTaskBookDao {
	
	/**
	 * 查找所有实体对象集合
	 * @return HistoryTaskBook列表
	 */
	List<HistoryTaskBook> findList() throws Exception;
	
	/**
	 * 根据计划书id查询历史计划书列表
	 * @return HistoryTaskBook列表
	 */
	List<HistoryTaskBook> findListByTaskBookId(String taskBookId) throws Exception;
	
	/**
	 * 查找一条历史计划书详情
	 * @return HistoryTaskBook列表
	 */
	List<HistoryTaskBook> findDetailList(HistoryTaskBook historyTaskBook) throws Exception;
	
	/**
	 * 删除一条历史计划书
	 * @param HistoryTaskBook historyTaskBook实体
	 */
	void delete(HistoryTaskBook historyTaskBook) throws Exception;
	
	/**
	 * 保存实体对象
	 * 
	 * @param entity
	 *            实体对象
	 * @throws Exception 
	 */
	void addHistoryTaskBook(HistoryTaskBook historyTaskBook) throws Exception;
}
