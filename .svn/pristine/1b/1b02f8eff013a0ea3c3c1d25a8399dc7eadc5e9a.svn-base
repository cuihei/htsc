package com.ht.service.impl.drawtask.taskbook.history;

import java.util.List;

import javax.annotation.Resource;

import com.ht.common.util.LogHelper;
import com.ht.persistence.dao.inter.drawtask.taskbook.history.HistoryTaskBookDao;
import com.ht.persistence.model.drawtask.taskbook.history.HistoryTaskBook;
import com.ht.service.inter.drawtask.taskbook.history.HistoryTaskBookService;


public class HistoryTaskBookServiceImpl implements HistoryTaskBookService {


	@Resource(name = "historyTaskBookDao") 
	private HistoryTaskBookDao historyTaskBookDao;
	
	 /**
	  * 查询历史计划书头部
	  * @return 历史计划书list
	  * @throws Exception
	  */
	@Override
	public List<HistoryTaskBook> findList() throws Exception {
		try{
			// 获取所有monthPlan
			return historyTaskBookDao.findList();
		}catch(Exception e){
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}
	
	/**
	  * 查询一条历史计划书详情
	  * @return 历史计划书详情list
	  * @throws Exception
	  */
	@Override
	public List<HistoryTaskBook> findDetailList(String id) throws Exception {
		try{
			HistoryTaskBook historyTaskBook = new HistoryTaskBook();
			historyTaskBook.setId(id);
			// 获取一条monthPlan
			return historyTaskBookDao.findDetailList(historyTaskBook);
		}catch(Exception e){
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}
	
	 /**
	   * 删除所选
	   * @param id 一条历史计划书
	   * @throws Exception
	   */
	@Override
	public void delete(String id) throws Exception{
		try {
			HistoryTaskBook historyTaskBook = new HistoryTaskBook();
			historyTaskBook.setId(id);
			historyTaskBookDao.delete(historyTaskBook);
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}

	@Override
	public List<HistoryTaskBook> findListByTaskBookId(String taskBookId)
			throws Exception {
		try{
			//根据任务计划书id获取历史任务计划书
			return historyTaskBookDao.findListByTaskBookId(taskBookId);
		}catch(Exception e){
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}
	
}
