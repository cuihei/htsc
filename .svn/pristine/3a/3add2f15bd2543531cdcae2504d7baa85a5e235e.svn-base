package com.ht.service.inter.drawtask.taskbook.history;

import java.util.List;

import com.ht.persistence.model.drawtask.taskbook.history.HistoryTaskBook;

public interface HistoryTaskBookService{

	 /**
	  * 查询历史计划书头部
	  * @return 历史计划书头部list
	  * @throws Exception
	  */
	List<HistoryTaskBook> findList() throws Exception;
	
	/**
	 * 根据计划书id查询历史计划书列表
	 * @return HistoryTaskBook列表
	 */
	List<HistoryTaskBook> findListByTaskBookId(String taskBookId) throws Exception;
	
	 /**
	  * 查询一条历史计划书详细
	  * @return 历史计划书详细list
	  * @throws Exception
	  */
	List<HistoryTaskBook> findDetailList(String id) throws Exception;
	
	/**
	 * 删除一条历史计划书
	 * @param id 历史计划书id
	 * @throws Exception
	 */
	void delete(String id) throws Exception;
}
