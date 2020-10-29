package com.ht.service.impl.drawtask.taskbill;

import java.util.List;

import javax.annotation.Resource;

import com.ht.common.util.LogHelper;
import com.ht.exception.DBException;
import com.ht.persistence.dao.inter.drawtask.taskbill.TaskBillDao;
import com.ht.persistence.model.drawtask.taskbill.TaskBill;
import com.ht.service.inter.drawtask.taskbill.TaskBillService;

public class TaskBillServiceImpl implements TaskBillService {


	@Resource
	private TaskBillDao taskBillDao;
	
	/**
	  * 查询
	  * @return 编绘任务清单list
	  * @throws Exception
	  */
	@Override
	public List<TaskBill> getList(String flag) throws Exception {
		try{
			TaskBill taskBill = new TaskBill();
			taskBill.setFlag(flag);
			// 获取所有编绘清单
			List<TaskBill> list = taskBillDao.getList(taskBill);
			for(TaskBill tb:list){
				if(tb.getPlan()==null){
					throw new DBException("任务书："+tb.getTaskBook().getTaskbookName()+"无计划！");
				}
			}
			return list;
		}catch(Exception e){
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}
	
	/**
	  * 查询
	  * @return 编绘任务清单list
	  * @throws Exception
	  */
	@Override
	public List<TaskBill> getListByPlanIdsAndFlag(String flag,String taskBookId) throws Exception {
		try{
			TaskBill taskBill = new TaskBill();
			taskBill.setFlag(flag);
			taskBill.setTaskBookId(taskBookId);
			List<TaskBill> tList = taskBillDao.getListByPlanIdAndType(taskBill);
			return tList;
		}catch(Exception e){
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}

	@Override
	public List<TaskBill> findPlanByPlanId(String planId) throws Exception {
		try{
			TaskBill taskBill = new TaskBill();
			taskBill.setPlanId(planId);
			List<TaskBill> tList = taskBillDao.findPlanByPlanId(taskBill);
			return tList;
		}catch(Exception e){
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}

	@Override
	public List<TaskBill> findListByTaskBookId(String taskBookId) throws Exception {
		try{
			TaskBill taskBill = new TaskBill();
			taskBill.setTaskBookId(taskBookId);
			List<TaskBill> tList = taskBillDao.findListByTaskBookId(taskBill);
			return tList;
		}catch(Exception e){
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}
}