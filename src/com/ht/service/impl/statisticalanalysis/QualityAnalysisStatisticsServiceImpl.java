package com.ht.service.impl.statisticalanalysis;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.activiti.engine.identity.Group;
import org.activiti.engine.repository.ProcessDefinition;
import org.apache.commons.lang3.StringUtils;

import com.ht.common.exception.CommonException;
import com.ht.common.util.BeanUtil;
import com.ht.common.util.DataConverter;
import com.ht.common.util.GenerateSequenceUtil;
import com.ht.common.util.LogHelper;
import com.ht.exception.DBException;
import com.ht.persistence.dao.inter.background.authority.role.RoleUsersDao;
import com.ht.persistence.dao.inter.background.dicdata.basedata.BaseDataDao;
import com.ht.persistence.dao.inter.background.dicdata.coefficient.CoefficientDao;
import com.ht.persistence.dao.inter.background.dicdata.defectform.DefectFormDao;
import com.ht.persistence.dao.inter.background.organization.employee.UserDao;
import com.ht.persistence.dao.inter.catalog.detail.CatalogDetailDao;
import com.ht.persistence.dao.inter.datum.bookinfo.BookInfoDao;
import com.ht.persistence.dao.inter.datum.bookinfo.ReturnBookDao;
import com.ht.persistence.dao.inter.datum.books.BooksDao;
import com.ht.persistence.dao.inter.datum.datum.BorrowingDao;
import com.ht.persistence.dao.inter.datum.fileddata.FiledDataDao;
import com.ht.persistence.dao.inter.drawtask.taskbill.TaskBillDao;
import com.ht.persistence.dao.inter.drawtask.taskbook.book.TaskBookDao;
import com.ht.persistence.dao.inter.drawtask.taskbook.create.CreateTaskDao;
import com.ht.persistence.dao.inter.drawtask.tasksplit.TaskSplitDao;
import com.ht.persistence.dao.inter.statisticalanalysis.CompilationCompleteStatusDao;
import com.ht.persistence.dao.inter.statisticalanalysis.CompilationResultSummaryDao;
import com.ht.persistence.dao.inter.statisticalanalysis.CompilationWorkDaysDao;
import com.ht.persistence.dao.inter.statisticalanalysis.CompilationYearPlanDao;
import com.ht.persistence.dao.inter.statisticalanalysis.DynamicTableDao;
import com.ht.persistence.dao.inter.system.assign.AssignUserRecordDao;
import com.ht.persistence.dao.inter.system.notice.NoticeDao;
import com.ht.persistence.dao.inter.system.notice.UserNoticeDao;
import com.ht.persistence.dao.inter.system.workflow.process.ProcessDelegateDao;
import com.ht.persistence.dao.inter.system.workflow.process.ProcessFlowDao;
import com.ht.persistence.dao.inter.system.workflow.process.ProcessLogDao;
import com.ht.persistence.dao.inter.system.workflow.process.ProcessLogDetailDao;
import com.ht.persistence.dao.inter.system.workflow.process.ProcessStatusDao;
import com.ht.persistence.dao.inter.system.workflow.publish.VProcessDetailDao;
import com.ht.persistence.dao.inter.system.workflow.task.FlowsDao;
import com.ht.persistence.dao.inter.system.workflow.task.TaskDao;
import com.ht.persistence.model.background.authority.role.RoleUsers;
import com.ht.persistence.model.background.dicdata.basedata.BaseData;
import com.ht.persistence.model.background.dicdata.defectform.DefectForm;
import com.ht.persistence.model.background.organization.employee.User;
import com.ht.persistence.model.catalog.detail.CatalogDetail;
import com.ht.persistence.model.datum.bookinfo.BookInfo;
import com.ht.persistence.model.datum.bookinfo.ReturnBook;
import com.ht.persistence.model.datum.books.Books;
import com.ht.persistence.model.datum.datum.Borrowing;
import com.ht.persistence.model.datum.fileddata.FiledData;
import com.ht.persistence.model.drawtask.plan.Plan;
import com.ht.persistence.model.drawtask.taskbill.TaskBill;
import com.ht.persistence.model.drawtask.taskbook.book.TaskBook;
import com.ht.persistence.model.drawtask.taskbook.create.CreateTask;
import com.ht.persistence.model.drawtask.tasksplit.TaskSplit;
import com.ht.persistence.model.statisticalanalysis.CompilationRealWorkDays;
import com.ht.persistence.model.statisticalanalysis.view.CompilationCompleteStatusView;
import com.ht.persistence.model.system.notice.Notice;
import com.ht.persistence.model.system.notice.UserNotice;
import com.ht.persistence.model.system.workflow.assign.AssignUserRecord;
import com.ht.persistence.model.system.workflow.process.ProcessDelegate;
import com.ht.persistence.model.system.workflow.process.ProcessFlow;
import com.ht.persistence.model.system.workflow.process.ProcessStatus;
import com.ht.persistence.model.system.workflow.process.ProcessTypeCount;
import com.ht.persistence.model.system.workflow.publish.VProcessDetail;
import com.ht.persistence.model.system.workflow.task.HiTask;
import com.ht.persistence.model.system.workflow.task.RuTask;
import com.ht.service.constant.ComplicationTaskProcessArgs;
import com.ht.service.constant.experiencebook.ChartStatusConstants;
import com.ht.service.constant.model.ComlicationTaskPersonAndDate;
import com.ht.service.impl.background.dicdata.constants.BaseDataConstants;
import com.ht.service.impl.system.workflow.task.ProcessTypeEnum;
import com.ht.service.impl.system.workflow.task.SeaMapEnum;
import com.ht.service.impl.system.workflow.util.BusinessUtil;
import com.ht.service.inter.statisticalanalysis.QualityAnalysisStatisticsService;
import com.ht.workflow.service.IWorkflowService;
import com.ht.workflow.service.WsResult;

public class QualityAnalysisStatisticsServiceImpl implements
		QualityAnalysisStatisticsService {

	@Resource
	IWorkflowService service;

	@Resource
	ProcessFlowDao processFlowDao;

	@Resource(name = "taskBookDao")
	private TaskBookDao taskBookDao;

	@Resource
	TaskSplitDao taskSplitDao;

	// 注入baseDataDao
	@Resource(name = "baseDataDao")
	private BaseDataDao baseDataDao;

	@Resource(name = "processStatusDao")
	private ProcessStatusDao processStatusDao;

	@Resource
	VProcessDetailDao vProcessDetailDao;

	@Resource
	ProcessLogDao processLogDao;

	@Resource
	ProcessLogDetailDao processLogDetailDao;

	@Resource
	TaskDao taskDao;

	@Resource
	TaskBillDao taskBillDao;

	@Resource
	CompilationResultSummaryDao compilationResultSummaryDao;

	@Resource
	DynamicTableDao dynamicTableDao;

	@Resource(name = "vProcessDetailDao")
	private VProcessDetailDao VProcessDetailDao;

	@Resource
	FlowsDao flowsDao;

	@Resource
	CompilationCompleteStatusDao compilationCompleteStatusDao;

	@Resource
	CompilationWorkDaysDao compilationWorkDaysDao;

	@Resource
	CatalogDetailDao catalogDetailDao;

	@Resource
	CoefficientDao coefficientDao;

	@Resource
	CompilationYearPlanDao compilationYearPlanDao;

	@Resource
	DefectFormDao defectFormDao;

	/**
	 * 注入图书资料Dao
	 */
	@Resource(name = "bookInfoDao")
	BookInfoDao bookInfoDao;

	/**
	 * 注入借阅记录Dao
	 */
	@Resource(name = "returnBookDao")
	private ReturnBookDao returnBookDao;

	@Resource
	BorrowingDao borrowingDao;

	/**
	 * 注入海图Dao
	 */
	@Resource(name = "booksDao")
	BooksDao booksDao;

	/**
	 * 注入借阅记录Dao
	 */
	@Resource(name = "BorrowingDao")
	private BorrowingDao BorrowingDao;

	/**
	 * 注入外业汇交Dao
	 */
	@Resource(name = "filedDataDao")
	FiledDataDao filedDataDao;

	@Resource
	UserDao userDao;
	
	/**
     * 注入roleUsersDao
     */
	@Resource(name = "roleUsersDao") 
	private RoleUsersDao roleUsersDao;
	/**
	 * 注入roleUsersDao
	 */
	@Resource
	private AssignUserRecordDao assignUserRecordDao;
	@Override
	/**
	 * 获取待办
	 * @param userNo 用户
	 * @param processDefId 流程定义ID
	 */
	public List<Map<String, Object>> getRuTask(String userId, String loginNo, ProcessTypeEnum type) throws Exception
	{
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		// 判断类型
		if (type.name().contains("TASK_BOOK"))
		{
			result = getTaskBookRuTask(userId, type);
		}
		else if (type.equals(ProcessTypeEnum.CATALOG_MANAGEMENT))
		{
			result = getCatalogRuTask(userId, loginNo, type);
		}
		else if (type.equals(ProcessTypeEnum.DATA_BORROWING))
		{
			result = getBookInfoRuTask(userId, loginNo, type);
		}
		else if (type.equals(ProcessTypeEnum.DATA_RETURN))
		{
			result = getReturnBookRuTask(userId, loginNo, type);
		}
		else if (type.equals(ProcessTypeEnum.DATA_INPUT))
		{
			result = getDataInputRuTask(userId, loginNo, type);
		}
		else if (type.equals(ProcessTypeEnum.PROBLEM_SUBMIT))
		{
			result = getProblemRuTask(userId, loginNo, type);
		}
		else
		{
			result = getDrawRuTask(userId, type);
		}
		return result;
	}

	/**
	 * 
	 * 获取海图编绘待办
	 */
	@Override
	public List<Map<String, Object>> getProblemRuTask(String userNo, String loginNo, ProcessTypeEnum type) throws Exception
	{
		try
		{
			// 根据用户查询所属组
			List<Group> groups = service.getGroupsByUser(userNo);
			if (groups == null)
			{
				return null;
			}
			else
			{
				if (groups.size() == 0) { return null; }
			}
			// 定义返回结果对象
			// List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();

			List<Map<String, Object>> resultTask = new ArrayList<Map<String, Object>>();
			// 查询当前待办
			// 任务数据
			String tasks = null;
			// 首选搜索是否有用户变量的任务
			tasks = service.getTaskListByPeformerAndProcessKey(userNo, type.toString());
			List<Map<String, Object>> maps = readWsResultData(tasks);
			// 解析任务数据
			if (maps != null)
			{
				resultTask.addAll(maps);
			}
			// 查询当前组待办列表
			for (int i = 0; i < groups.size(); i++)
			{
				// 当前组
				Group group = groups.get(i);
				// 获取任务数据
				tasks = service.getTaskListByCandidateGroupAndProcessKey(group.getId(), type.toString());
				maps = readWsResultData(tasks);
				if (maps != null)
				{
					resultTask.addAll(maps);
				}
			}
			// 根据任务ID去重复
			Map<String, Map<String, Object>> taskResult = new HashMap<String, Map<String, Object>>();
			for (int i = 0; i < resultTask.size(); i++)
			{
				Map<String, Object> task = resultTask.get(i);
				String taskId = (String) task.get("taskInstId");
				if (!taskResult.containsKey(taskId))
				{
					taskResult.put(taskId, task);
				}
			}
			/* for (Entry<String, Map<String, Object>> entry : taskResult.entrySet()) { Map<String, Object> task = entry.getValue(); if
			 * (task.get("recordId") != null) { // 定义返回类型存放map String recordId = task.get("recordId").toString(); task = setDrawTaskInfo(task,
			 * recordId); result.add(task); } } */
			return resultTask;
		}
		catch (Exception e)
		{
			throw new DBException("数据库操作错误：" + e.getMessage());
		}
	}

	/**
	 * 获取海图编绘待办
	 */
	@Override
	public List<Map<String, Object>> getDataInputRuTask(String userNo, String loginNo, ProcessTypeEnum type) throws Exception
	{
		try
		{
			// 根据用户查询所属组
			List<Group> groups = service.getGroupsByUser(userNo);
			if (groups == null)
			{
				return null;
			}
			else
			{
				if (groups.size() == 0) { return null; }
			}

			// 定义返回结果对象
			List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();

			List<Map<String, Object>> resultTask = new ArrayList<Map<String, Object>>();
			// 查询当前待办
			// 任务数据
			String tasks = null;
			// 首选搜索是否有用户变量的任务
			tasks = service.getTaskListByPeformerAndProcessKey(userNo, type.toString());
			List<Map<String, Object>> maps = readCatalogResultData(tasks);
			// 解析任务数据
			if (maps != null)
			{
				resultTask.addAll(maps);
			}
			else
			{
				// 查询当前待办列表
				for (int i = 0; i < groups.size(); i++)
				{
					// 当前组
					Group group = groups.get(i);
					// 获取任务数据
					tasks = service.getTaskListByCandidateGroupAndProcessKey(group.getId(), type.toString());
					maps = readCatalogResultData(tasks);
					if (maps != null)
					{
						resultTask.addAll(maps);
					}
				}
			}
			if (resultTask != null)
			{
				// 循环任务 组织业务数据
				for (int i = 0; i < resultTask.size(); i++)
				{
					Map<String, Object> task = resultTask.get(i);
					if (task.get("recordId") != null)
					{
						// 定义返回类型存放map
						String recordId = task.get("recordId").toString();
						// 定义查询数据表对象
						BookInfo b = new BookInfo();
						// 设置类型
						b.setId(recordId);
						// 根据类型查询当前所有任务
						b = bookInfoDao.getBookInfo(b);

						Books bs = new Books();
						// 设置类型
						bs.setId(recordId);
						// 根据类型查询当前所有任务
						bs = booksDao.getBooks(bs);

						FiledData f = new FiledData();
						// 设置类型
						f.setId(recordId);
						// 根据类型查询当前所有任务
						f = filedDataDao.getFiledData(f);

						// 设置图书资料属性
						if (b != null)
						{
							task = setBookInfoDataInput(b, task);
						}
						else if (bs != null)
						{
							task = setBooksDataInput(bs, task);
						}
						else if (f != null)
						{
							task = setFiledDataInput(f, task);
						}
						result.add(task);
					}
				}
			}
			return result;
		}
		catch (Exception e)
		{
			throw new DBException("数据库操作错误：" + e.getMessage());
		}
	}

	/**
	 * 设置任务书和计划
	 * @param bill 任务书
	 * @param map 计划
	 * @return 结果
	 */
	private Map<String, Object> setBookInfoDataInput(BookInfo b, Map<String, Object> map)
	{
		if (b != null)
		{
			map.put("id", b.getId());
			// 图书名称
			map.put("bookName", b.getBookName());
			// 图书编号
			map.put("code", b.getCode());
			// 图书编号
			map.put("type", "图书资料");
		}
		return map;
	}

	private Map<String, Object> setBooksDataInput(Books b, Map<String, Object> map)
	{
		if (b != null)
		{
			map.put("id", b.getId());
			// 图书名称
			map.put("bookName", b.getChartName());
			// 图书编号
			map.put("code", b.getChartNo());
			// 图书编号
			map.put("type", "海图资料");
		}
		return map;
	}

	private Map<String, Object> setFiledDataInput(FiledData b, Map<String, Object> map)
	{
		if (b != null)
		{
			map.put("id", b.getId());
			// 图书名称
			map.put("bookName", b.getProjectName());
			// 图书编号
			map.put("code", b.getPicNo());
			// 图书编号
			map.put("type", "外业汇交资料");
		}
		return map;
	}

	/**
	 * 获取借阅待办
	 */
	@Override
	public List<Map<String, Object>> getBookInfoRuTask(String userNo, String loginNo, ProcessTypeEnum type) throws Exception
	{
		try
		{
			// 根据用户查询所属组
			List<Group> groups = service.getGroupsByUser(userNo);
			if (groups == null)
			{
				return null;
			}
			else
			{
				if (groups.size() == 0) { return null; }
			}

			// 定义返回结果对象
			List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();

			List<Map<String, Object>> resultTask = new ArrayList<Map<String, Object>>();
			// 查询当前待办
			// 任务数据
			String tasks = null;
			// 首选搜索是否有用户变量的任务
			tasks = service.getTaskListByPeformerAndProcessKey(userNo, type.toString());
			List<Map<String, Object>> maps = readCatalogResultData(tasks);
			// 解析任务数据
			if (maps != null)
			{
				resultTask.addAll(maps);
			}
			else
			{
				// 查询当前待办列表
				for (int i = 0; i < groups.size(); i++)
				{
					// 当前组
					Group group = groups.get(i);
					// 获取任务数据
					tasks = service.getTaskListByCandidateGroupAndProcessKey(group.getId(), type.toString());
					maps = readCatalogResultData(tasks);
					if (maps != null)
					{
						resultTask.addAll(maps);
					}
				}
			}
			if (resultTask != null)
			{
				// 循环任务 组织业务数据
				for (int i = 0; i < resultTask.size(); i++)
				{
					Map<String, Object> task = resultTask.get(i);
					if (task.get("recordId") != null)
					{
						// 定义返回类型存放map
						String recordId = task.get("recordId").toString();
						// 定义查询数据表对象
						Borrowing b = new Borrowing();
						// 设置类型
						b.setId(recordId);
						// 根据类型查询当前所有任务
						b = BorrowingDao.getBorrowing(b);
						// 设置图书资料属性
						task = setBookInfo(b, task);
						result.add(task);
					}
				}
			}
			return result;
		}
		catch (Exception e)
		{
			throw new DBException("数据库操作错误：" + e.getMessage());
		}
	}

	/**
	 * 获取海图编绘待办
	 */
	@Override
	public List<Map<String, Object>> getReturnBookRuTask(String userNo, String loginNo, ProcessTypeEnum type) throws Exception
	{
		try
		{
			// 根据用户查询所属组
			List<Group> groups = service.getGroupsByUser(userNo);
			if (groups == null)
			{
				return null;
			}
			else
			{
				if (groups.size() == 0) { return null; }
			}
			// 定义返回结果对象
			List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();

			List<Map<String, Object>> resultTask = new ArrayList<Map<String, Object>>();
			// 查询当前待办
			// 任务数据
			String tasks = null;
			// 首选搜索是否有用户变量的任务
			tasks = service.getTaskListByPeformerAndProcessKey(userNo, type.toString());
			List<Map<String, Object>> maps = readCatalogResultData(tasks);
			// 解析任务数据
			if (maps != null)
			{
				resultTask.addAll(maps);
			}
			else
			{
				// 查询当前待办列表
				for (int i = 0; i < groups.size(); i++)
				{
					// 当前组
					Group group = groups.get(i);
					// 获取任务数据
					tasks = service.getTaskListByCandidateGroupAndProcessKey(group.getId(), type.toString());
					maps = readCatalogResultData(tasks);
					if (maps != null)
					{
						resultTask.addAll(maps);
					}
				}
			}
			if (resultTask != null)
			{
				// 循环任务 组织业务数据
				for (int i = 0; i < resultTask.size(); i++)
				{
					Map<String, Object> task = resultTask.get(i);
					if (task.get("recordId") != null)
					{
						// 定义返回类型存放map
						String recordId = task.get("recordId").toString();
						// 定义查询数据表对象
						ReturnBook b = new ReturnBook();
						// 设置类型
						b.setId(recordId);
						// 根据类型查询当前所有任务
						b = returnBookDao.getReturnBook(b);
						// 设置归还资料的属性
						task = setReturnBookInfo(b, task);
						result.add(task);
					}
				}
			}
			return result;
		}
		catch (Exception e)
		{
			throw new DBException("数据库操作错误：" + e.getMessage());
		}
	}

	/**
	 * 设置任务书和计划
	 * @param bill 任务书
	 * @param map 计划
	 * @return 结果
	 */
	private Map<String, Object> setReturnBookInfo(ReturnBook b, Map<String, Object> map)
	{
		if (b != null)
		{
			// 图书名称
			map.put("bookName", b.getBookName());
			// 图书编号
			map.put("code", b.getBookNo());
			// 当前归还数量
			map.put("returnNo", b.getCurReturnNo());
			// 归还人
			map.put("returnPerson", b.getReturnPerson());
			String type = b.getType();
			if (type != null)
			{
				if (type.equals("bookinfo"))
				{
					map.put("type", "图书资料");
					BookInfo bi = new BookInfo();
					bi.setCode(b.getBookNo());
					bi = bookInfoDao.getBookInfoByCode(bi);
					if (bi != null)
					{
						map.put("id", bi.getId());
					}
				}
				else if (type.equals("books"))
				{
					map.put("type", "海图资料");
					Books bs = new Books();
					bs.setCode(b.getBookNo());
					bs = booksDao.getBooksByCode(bs);
					if (bs != null)
					{
						map.put("id", bs.getId());
					}
				}
				else if (type.equals("fileddata"))
				{
					map.put("type", "外业汇交资料");
					FiledData filedData = new FiledData();
					filedData.setPicNo(b.getBookNo());
					filedData = filedDataDao.getFiledDataByPicNo(filedData);
					if (filedData != null)
					{
						map.put("id", filedData.getId());
					}
				}
			}
		}
		return map;
	}

	/**
	 * 设置任务书和计划
	 * @param bill 任务书
	 * @param map 计划
	 * @return 结果
	 */
	private Map<String, Object> setBookInfo(Borrowing b, Map<String, Object> map)
	{
		if (b != null)
		{
			// 图书名称
			map.put("bookName", b.getBorrowBookName());
			// 图书编号
			map.put("code", b.getBorrowCode());
			// 借阅数量
			map.put("borrowNo", b.getBorrowNo());
			// 借阅人
			map.put("borrowPerson", b.getBorrowPerson());
			String type = b.getType();
			if (type != null)
			{
				if (type.equals("bookinfo"))
				{
					map.put("type", "图书资料");
					BookInfo bi = new BookInfo();
					bi.setCode(b.getBorrowCode());
					bi = bookInfoDao.getBookInfoByCode(bi);
					if (bi != null)
					{
						map.put("id", bi.getId());
					}
				}
				else if (type.equals("books"))
				{
					map.put("type", "海图资料");
					Books bs = new Books();
					bs.setCode(b.getBorrowCode());
					bs = booksDao.getBooksByCode(bs);
					if (bs != null)
					{
						map.put("id", bs.getId());
					}
				}
				else if (type.equals("fileddata"))
				{
					map.put("type", "外业汇交资料");
					FiledData filedData = new FiledData();
					filedData.setPicNo(b.getBorrowCode());
					filedData = filedDataDao.getFiledDataByPicNo(filedData);
					if (filedData != null)
					{
						map.put("id", filedData.getId());
					}
				}
			}
			// 是否涉密
			map.put("secretInvolved", b.getSecretInvolved());
		}
		return map;
	}

	/**
	 * 获取海图编绘待办
	 */
	@Override
	public List<Map<String, Object>> getCatalogRuTask(String userNo, String loginNo, ProcessTypeEnum type) throws Exception
	{
		try
		{
			// 根据用户查询所属组
			List<Group> groups = service.getGroupsByUser(userNo);
			if (groups == null)
			{
				return null;
			}
			else
			{
				if (groups.size() == 0) { return null; }
			}
			// 定义返回结果对象
			List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();

			List<Map<String, Object>> resultTask = new ArrayList<Map<String, Object>>();
			// 查询当前待办
			// 任务数据
			String tasks = null;
			// 首选搜索是否有用户变量的任务
			tasks = service.getTaskListByPeformerAndProcessKey(userNo, type.toString());
			List<Map<String, Object>> maps = readCatalogResultData(tasks);
			// 解析任务数据
			if (maps != null)
			{
				resultTask.addAll(maps);
			}
			else
			{
				// 查询当前待办列表
				for (int i = 0; i < groups.size(); i++)
				{
					// 当前组
					Group group = groups.get(i);
					// 获取任务数据
					tasks = service.getTaskListByCandidateGroupAndProcessKey(group.getId(), type.toString());
					maps = readCatalogResultData(tasks);
					if (maps != null)
					{
						resultTask.addAll(maps);
					}
				}
			}
			if (resultTask != null)
			{
				// 循环任务 组织业务数据
				for (int i = 0; i < resultTask.size(); i++)
				{
					Map<String, Object> task = resultTask.get(i);
					if (task.get("recordId") != null)
					{
						// 定义返回类型存放map
						String recordId = task.get("recordId").toString();
						// 定义查询数据表对象
						CatalogDetail catalogDetail = new CatalogDetail();
						// 设置类型
						catalogDetail.setId(recordId);
						// 根据类型查询当前所有任务
						catalogDetail = catalogDetailDao.getDetail(catalogDetail);
						// 设置任务书和计划属性
						task = setCatalogDetail(catalogDetail, task);
						result.add(task);
					}
				}
			}
			return result;
		}
		catch (Exception e)
		{
			throw new DBException("数据库操作错误：" + e.getMessage());
		}
	}

	/**
	 * 获取待办
	 */
	public List<Map<String, Object>> getRuTask() throws Exception
	{
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (SeaMapEnum processType : SeaMapEnum.values())
		{
			List<Map<String, Object>> result = getAllDrawRuTask(processType);
			list.addAll(result);
		}
		return list;
	}

	/**
	 * 根据key获取待办
	 */
	@Override
	public List<Map<String, Object>> getRuTaskByKey(String key) throws Exception
	{
		List<Map<String, Object>> result = getAllDrawRuTask(SeaMapEnum.valueOf(key));
		return result;
	}
	/**
	 * 根据key获取待办
	 */
	@Override
	public List<Map<String, Object>> getRuTaskByDate(String key,String startTime,String endTime) throws Exception
	{
		List<Map<String, Object>> result = getAllDrawRuTaskByDate(SeaMapEnum.valueOf(key), startTime, endTime);
		return result;
	}

	/**
	 * 设置任务书和计划
	 * @param bill 任务书
	 * @param map 计划
	 * @return 结果
	 */
	private Map<String, Object> setCatalogDetail(CatalogDetail catalogDetail, Map<String, Object> map)
	{
		if (catalogDetail != null)
		{
			// 目录id
			map.put("id", catalogDetail.getId());
			// 目录类型
			map.put("type", catalogDetail.getType());
			// 海区
			map.put("area", catalogDetail.getArea());
			// 图号
			map.put("mapNo", catalogDetail.getMapNo());
			// 图名
			map.put("mapName", catalogDetail.getMapName());
			// 比例尺
			map.put("scale", catalogDetail.getScale());
			// 图幅范围经度（前）
			map.put("starLongitude", catalogDetail.getStarLongitude());
			// 图幅范围经度（后
			map.put("endLongitude", catalogDetail.getEndLongitude());
			// 图幅范围纬度（前）
			map.put("starLatitude", catalogDetail.getStarLatitude());
			// 图幅范围纬度（后）
			map.put("endLatitude", catalogDetail.getEndLatitude());
			// 性质
			map.put("nature", catalogDetail.getNature());
			// 基测
			map.put("measurementPeriod", catalogDetail.getMeasurementPeriod());
			// 检测
			map.put("testIng", catalogDetail.getTestIng());
			// 出版年份
			map.put("publicationYear", catalogDetail.getPublicationYear());
			// 基准纬度
			map.put("datumLatitude", catalogDetail.getDatumLatitude());
			// 图积
			map.put("mapProportion", catalogDetail.getMapProportion());
			// 调整性质
			map.put("adjustmentProperty", catalogDetail.getAdjustmentProperty());
			// 出版日期
			map.put("publicationDate", catalogDetail.getPublicationDate());
			// 印次
			map.put("printNum", catalogDetail.getPrintNum());
			// 备注
			map.put("remarks", catalogDetail.getRemarks());
			// 状态
			map.put("status", catalogDetail.getStatus());
		}
		return map;
	}
	
	/**
	 * 获取待办信息map 键 任务ID 值 当前任务信息
	 * @return
	 * @throws Exception 
	 */
	private Map<String,Map<String,Object>> getRuTaskInfo(String userId,ProcessTypeEnum type) throws Exception{
		// 根据用户查询所属组
		List<Group> groups = service.getGroupsByUser(userId);
		if (groups == null)
		{
			return null;
		}
		else
		{
			if (groups.size() == 0) { return null; }
		}
		List<Map<String, Object>> resultTask = new ArrayList<Map<String, Object>>();
		// 查询当前待办
		// 任务数据
		String tasks = null;
		// 首选搜索是否有用户变量的任务
		tasks = service.getTaskListByPeformerAndProcessKey(userId, type.name());
		List<Map<String, Object>> maps = readWsResultData(tasks);
		// 解析任务数据
		if (maps != null)
		{
			resultTask.addAll(maps);
		}
		// 查询当前组待办列表
		for (int i = 0; i < groups.size(); i++)
		{
			// 当前组
			Group group = groups.get(i);
			// 获取任务数据
			tasks = service.getTaskListByCandidateGroupAndProcessKey(group.getId(), type.toString());
			maps = readWsResultData(tasks);
			if (maps != null)
			{
				resultTask.addAll(maps);
			}
		}
		// 根据任务ID去重复
		Map<String, Map<String, Object>> taskResult = new HashMap<String, Map<String, Object>>();
		for (int i = 0; i < resultTask.size(); i++)
		{
			Map<String, Object> task = resultTask.get(i);
			String taskId = (String) task.get("taskInstId");
			if (!taskResult.containsKey(taskId))
			{
				taskResult.put(taskId, task);
			}
		}
		return taskResult;
	}

	/**
	 * 获取海图编绘待办
	 */
	@Override
	public List<Map<String, Object>> getDrawRuTask(String userId, ProcessTypeEnum type) throws Exception
	{
		try
		{
			// 定义返回结果对象
			List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
			Map<String,Map<String,Object>> taskResult = getRuTaskInfo(userId, type);
			if (taskResult != null)
			{
				for (Entry<String, Map<String, Object>> entry : taskResult.entrySet())
				{
					Map<String, Object> task = entry.getValue();
					if (task.get("recordId") != null)
					{
						// 定义返回类型存放map
						String recordId = task.get("recordId").toString();
						task = setDrawTaskInfo(task, recordId);
						result.add(task);
					}
				}
			}
			return result;
		}
		catch (Exception e)
		{
			throw new DBException("数据库操作错误：" + e.getMessage());
		}
	}

	/**
	 * 获取所有海图编绘待办
	 */
	@Override
	public List<Map<String, Object>> getAllDrawRuTask(SeaMapEnum type) throws Exception
	{
		try
		{
			// 定义返回结果对象
			List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
			RuTask param = new RuTask();
			param.setProcessDefKey(type.name());
			List<RuTask> ruList = taskDao.getRuTaskByProcessDefKey(param);
			if (ruList != null)
			{
				for (int i = 0; i < ruList.size(); i++)
				{
					Map<String, Object> map = new HashMap<String, Object>();
					RuTask task = ruList.get(i);
					setProcessTask(task.getProcessDefId(), task.getProcessDefName(), task.getTaskDefId(), task.getProcessInstId(),
							task.getTaskInstId(), task.getTaskName(), task.getCreateTime(), task.getSuspendState().equals(1) ? false : true, map);
					map.put("performer", task.getAssigneeName());
					map.put("group", task.getGroupName());
					map.put("endTime", task.getEndTime());
					map.put("excutionId", task.getExcutionId());
					VProcessDetail detail = getDetailRecordId(task.getProcessDefKey(), task.getParentProcessInstId(), task.getProcessInstId());
					if (detail != null)
					{
						map.put("recordId", detail.getDetailRecordId());
					}
					if (map.get("recordId") != null)
					{
						// 定义返回类型存放map
						String recordId = map.get("recordId").toString();
						setDrawTaskInfo(map, recordId);
						Map<String, Object> compeletionMap = setTaskCompeletion(task.getProcessDefKey(),task.getProcessInstId(),task.getParentProcessInstId(),(String)map.get("mapNo"));
						map.putAll(compeletionMap);
						 List<DefectForm> qualitylist = getQualityAchievementScore(task.getProcessDefKey(), task.getProcessInstId());
						 if(qualitylist!=null&&qualitylist.size()>0){
							 for (DefectForm defectForm : qualitylist) {
								 Map<String,Object> qualityMap=new HashMap<String,Object>();
								 qualityMap.putAll(map);
								 qualityMap.put("dataFrom","质检记录表");
								 qualityMap.put("opinion", defectForm.getOpinion());//存在问题及处理意见
								 qualityMap.put("deep", defectForm.getDeep());//类别
								 qualityMap.put("defectitem",defectForm.getDefectitem());//检查项目
								 qualityMap.put("discription",defectForm.getDiscription());//缺陷内容
								 qualityMap.put("defecttype", defectForm.getDefecttype());//缺陷类别
								 qualityMap.put("number",defectForm.getNumber());// 缺陷个数
								 qualityMap.put("score", defectForm.getScore());//扣分
								 qualityMap.put("grading", defectForm.getGrading());//分数
								 result.add(qualityMap);
							}
						 }
						 List<DefectForm> validationlist = getQualityScore(task.getProcessDefKey(), task.getProcessInstId());
						 if(validationlist!=null&&validationlist.size()>0){
							 for (DefectForm defectForm : validationlist) {
								 Map<String,Object> validationMap=new HashMap<String,Object>();
								 validationMap.putAll(map);
								 validationMap.put("dataFrom","审定记录表");
								 validationMap.put("opinion", defectForm.getOpinion());//存在问题及处理意见
								 validationMap.put("deep", defectForm.getDeep());//类别
								 validationMap.put("defectitem",defectForm.getDefectitem());//检查项目
								 validationMap.put("discription",defectForm.getDiscription());//缺陷内容
								 validationMap.put("defecttype", defectForm.getDefecttype());//缺陷类别
								 validationMap.put("number",defectForm.getNumber());// 缺陷个数
								 validationMap.put("score", defectForm.getScore());//扣分
								 validationMap.put("grading", defectForm.getGrading());//分数
								 result.add(validationMap);
							}
						 }
					}
				}
			}
			return result;
		}
		catch (Exception e)
		{
			LogHelper.ERROR.log(e.getMessage(), e);
			throw new DBException("数据库操作错误：" + e.getMessage());
		}
	}
	/**
	 * 获取所有海图编绘待办
	 */
	@Override
	public List<Map<String, Object>> getAllDrawRuTaskByDate(SeaMapEnum type,String startTime,String endTime) throws Exception
	{
		try
		{
			// 定义返回结果对象
			List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			RuTask param = new RuTask();
			param.setProcessDefKey(type.name());
			List<RuTask> ruList = taskDao.getRuTaskByProcessDefKey(param);
			Date beginDate =null;
			Date endDate=null;
			if(StringUtils.isNotEmpty(startTime)&&StringUtils.isNotEmpty(endTime)){
				beginDate = sdf.parse(startTime);
				endDate = sdf.parse(endTime);
			}
			if (ruList != null)
			{
				for (int i = 0; i < ruList.size(); i++)
				{
					Map<String, Object> map = new HashMap<String, Object>();
					RuTask task = ruList.get(i);
					setProcessTask(task.getProcessDefId(), task.getProcessDefName(), task.getTaskDefId(), task.getProcessInstId(),
							task.getTaskInstId(), task.getTaskName(), task.getCreateTime(), task.getSuspendState().equals(1) ? false : true, map);
					map.put("performer", task.getAssigneeName());
					map.put("group", task.getGroupName());
					map.put("endTime", task.getEndTime());
					map.put("excutionId", task.getExcutionId());
					VProcessDetail detail = getDetailRecordId(task.getProcessDefKey(), task.getParentProcessInstId(), task.getProcessInstId());
					if (detail != null)
					{
						map.put("recordId", detail.getDetailRecordId());
					}
					if (map.get("recordId") != null)
					{
						// 定义返回类型存放map
						String recordId = map.get("recordId").toString();
						setDrawTaskInfo(map, recordId);
						Map<String, Object> compeletionMap = setTaskCompeletion(task.getProcessDefKey(),task.getProcessInstId(),task.getParentProcessInstId(),(String)map.get("mapNo"));
						map.putAll(compeletionMap);
						List<DefectForm> qualitylist = getQualityAchievementScore(task.getProcessDefKey(), task.getProcessInstId());
						if(qualitylist!=null&&qualitylist.size()>0){
							for (DefectForm defectForm : qualitylist) {
								Map<String,Object> qualityMap=new HashMap<String,Object>();
								qualityMap.putAll(map);
								qualityMap.put("dataFrom","质检记录表");
								qualityMap.put("opinion", defectForm.getOpinion());//存在问题及处理意见
								qualityMap.put("deep", defectForm.getDeep());//类别
								qualityMap.put("defectitem",defectForm.getDefectitem());//检查项目
								qualityMap.put("discription",defectForm.getDiscription());//缺陷内容
								qualityMap.put("defecttype", defectForm.getDefecttype());//缺陷类别
								qualityMap.put("number",defectForm.getNumber());// 缺陷个数
								qualityMap.put("score", defectForm.getScore());//扣分
								qualityMap.put("grading", defectForm.getGrading());//分数
								if("审定完成".equals(qualityMap.get("status"))){
									Date completeDate =(Date)qualityMap.get("completeDate");
									if(beginDate!=null&&endDate!=null&&completeDate!=null&&(completeDate.after(beginDate)||completeDate.equals(beginDate))&&(completeDate.before(endDate)||completeDate.equals(endDate))){
										result.add(qualityMap);
									}
								}
							}
						}
						List<DefectForm> validationlist = getQualityScore(task.getProcessDefKey(), task.getProcessInstId());
						if(validationlist!=null&&validationlist.size()>0){
							for (DefectForm defectForm : validationlist) {
								Map<String,Object> validationMap=new HashMap<String,Object>();
								validationMap.putAll(map);
								validationMap.put("dataFrom","审定记录表");
								validationMap.put("opinion", defectForm.getOpinion());//存在问题及处理意见
								validationMap.put("deep", defectForm.getDeep());//类别
								validationMap.put("defectitem",defectForm.getDefectitem());//检查项目
								validationMap.put("discription",defectForm.getDiscription());//缺陷内容
								validationMap.put("defecttype", defectForm.getDefecttype());//缺陷类别
								validationMap.put("number",defectForm.getNumber());// 缺陷个数
								validationMap.put("score", defectForm.getScore());//扣分
								validationMap.put("grading", defectForm.getGrading());//分数
								if("审定完成".equals(validationMap.get("status"))){
									Date completeDate =(Date)validationMap.get("completeDate");
									if(beginDate!=null&&endDate!=null&&completeDate!=null&&(completeDate.after(beginDate)||completeDate.equals(beginDate))&&(completeDate.before(endDate)||completeDate.equals(endDate))){
										result.add(validationMap);
									}
								}
							}
						}
					}
				}
			}
			return result;
		}
		catch (Exception e)
		{
			LogHelper.ERROR.log(e.getMessage(), e);
			throw new DBException("数据库操作错误：" + e.getMessage());
		}
	}

	@Override
	/**
	 * 获取任务书待办任务
	 * @param type 任务书类型
	 * @return 任务书任务列表
	 * @throws Exception
	 */
	public List<Map<String, Object>> getTaskBookRuTask(String userId, ProcessTypeEnum processDefKey) throws Exception
	{
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		try
		{
			Map<String,Map<String, Object>> taskResult = getRuTaskInfo(userId, processDefKey);
			if (taskResult != null)
			{
				for (Entry<String, Map<String, Object>> entry : taskResult.entrySet())
				{
					Map<String, Object> task = entry.getValue();
					if (task.get("recordId") != null)
					{
						// 定义返回类型存放map
						String recordId = task.get("recordId").toString();
						task = setTaskBookInfo(task,recordId);
						result.add(task);
					}
				}
			}
			return result;
		}
		catch (Exception e)
		{
			LogHelper.FATAL.log(e.getMessage(), e);
			throw e;
		}
	}

	/**
	 * 解析目录管理工作流任务信息
	 * @param tasks
	 */
	@SuppressWarnings("unused")
	private List<Map<String, Object>> readCatalogResultData(String tasks)
	{
		WsResult<List<Map<String, Object>>> result = new WsResult<List<Map<String, Object>>>();
		// 解析任务数据
		if (tasks == null) { return null; }
		result = (WsResult<List<Map<String, Object>>>) DataConverter.convertJson2Object(tasks, result.getClass());
		List<Map<String, Object>> resultMap = null;
		List<Map<String, Object>> data = (List<Map<String, Object>>) result.getData();
		if (data != null)
		{
			if (data.size() > 0)
			{
				resultMap = new ArrayList<Map<String, Object>>();
				for (Map<String, Object> datum : data)
				{
					Map<String, Object> map = new HashMap<String, Object>();
					String taskInstId = (String) datum.get("id");
					String processInstId = (String) datum.get("processInstanceId");
					String processDefId = (String) datum.get("processDefinitionId");
					String taskDefId = (String) datum.get("taskDefinitionKey");
					String taskName = (String) datum.get("name");
					String processDefName = (String) datum.get("processDefInitName");
					long createTimeLong = (long) datum.get("createTime");
					Boolean suspendState = (Boolean) datum.get("suspendState");
					Date createTime = new Date(createTimeLong);
					setProcessTask(processDefId, processDefName, taskDefId, processInstId, taskInstId, taskName, createTime, suspendState, map);
					List<VProcessDetail> detailList = vProcessDetailDao.getProcessDetailsByProcessIdAndId(processInstId, taskInstId, processDefId,
							taskDefId);
					if (detailList != null)
					{
						for (int i = 0; i < detailList.size(); i++)
						{
							map.put("recordId", detailList.get(i).getDetailRecordId());
							resultMap.add(map);
						}

					}

				}
			}
		}
		return resultMap;
	}

	/**
	 * 解析工作流任务信息
	 * @param tasks
	 * @throws Exception
	 */
	private List<Map<String, Object>> readWsResultData(String tasks) throws Exception
	{
		WsResult<List<Map<String, Object>>> result = new WsResult<List<Map<String, Object>>>();
		// 解析任务数据
		if (tasks == null) { return null; }
		result = (WsResult<List<Map<String, Object>>>) DataConverter.convertJson2Object(tasks, result.getClass());
		List<Map<String, Object>> resultMap = null;
		List<Map<String, Object>> data = (List<Map<String, Object>>) result.getData();
		if (data != null)
		{
			if (data.size() > 0)
			{
				resultMap = new ArrayList<Map<String, Object>>();
				for (Map<String, Object> datum : data)
				{
					Map<String, Object> map = new HashMap<String, Object>();
					String taskInstId = (String) datum.get("id");
					String processInstId = (String) datum.get("processInstanceId");
					String processDefId = (String) datum.get("processDefinitionId");
					String taskDefId = (String) datum.get("taskDefinitionKey");
					String taskName = (String) datum.get("name");
					String processDefName = (String) datum.get("processDefInitName");
					String parentProcessInstId = datum.get("superProcessInstanceId") == null ? null : datum.get("superProcessInstanceId").toString();
					long createTimeLong = (long) datum.get("createTime");
					Boolean suspendState = (Boolean) datum.get("suspendState");
					Date createTime = new Date(createTimeLong);
					setProcessTask(processDefId, processDefName, taskDefId, processInstId, taskInstId, taskName, createTime, suspendState, map);
					map.put("parentProcessInstId", parentProcessInstId);
					String processDefKey = processDefId.split(":")[0];
					// 只有源数据 纸海图 电子海图有父流程
					VProcessDetail detail = getDetailRecordId(processDefKey, parentProcessInstId, processInstId);
					if (detail != null)
					{
						map.put("recordId", detail.getDetailRecordId());
					}
					resultMap.add(map);
				}
			}
		}
		return resultMap;
	}

	@Override
	public void performTask(String userNo, String processInstId, String parentProcessInstId, String taskId, String agreeValue, String advice,
			String processDefId, String taskDefId) throws Exception
	{
		// 确定
		try
		{
			String processDefKey = processDefId.split(":")[0];
			String status = null;
			Boolean agree = agreeValue.equals("1") ? true : false;
			VProcessDetail detail = getDetailRecordId(processDefKey, parentProcessInstId, processInstId);
			if (detail != null)
			{
				// 修改借阅数量
				if (processDefKey.equals(ProcessTypeEnum.DATA_BORROWING.name()))
				{
					updateBookInfoTotal(detail, agree);
				}
				if (processDefKey.equals(ProcessTypeEnum.DATA_RETURN.name()))
				{
					updateReturnBookInfoTotal(detail, agree);
				}

				if (processDefKey.equals(ProcessTypeEnum.DATA_INPUT.name()))
				{
					status = updateDataInputStatus(userNo, detail, agree);
				}
				else
				{
					status = updateBusinessStatus(detail, processDefId, taskDefId, agree);
				}
			}
			// 领取任务
			String userId = getUserId(userNo);
			Map<String, Object> v = BusinessUtil.getInstance().getProcessNextTaskArgs(processDefKey, taskDefId);
			if (v == null)
			{
				v = new HashMap<String, Object>();
			}
			if (v.containsKey("sd_time")) {
				Object value = service.getProcessArgs(processInstId, "sd_time");
				if (value != null) {
					if (agree) {
						agree = null;
					}
				}
			}
			if (v.containsKey("zj_time")) {
				Object value = service.getProcessArgs(processInstId, "zj_time");
				if (value != null) {
					if (agree) {
						agree = null;
					}
				}
			}
			v.remove("zj_time");
			v.remove("sd_time");
			// 为流程设置变量
			v.put("agree", agree);
			int clalimResult = service.claim(taskId, userId);
			LogHelper.FATAL.log(DataConverter.convertObject2Json(v));
			service.perform(taskId, DataConverter.convertObject2Json(v));
			// 提交任务后进行业务操作
			String taskResult = null;
			if (agree != null)
			{
				taskResult = agree ? "提交" : "退回";
			}
			else{
				taskResult = "提交";
			}
			String type = StringUtils.isNotEmpty(status) ? status : "提交任务";
			ProcessFlow processFlow = new ProcessFlow();
			processFlow.setUserNo(userNo);
			processFlow.setProcessInstId(processInstId);
			processFlow.setTaskId(taskId);
			processFlow.setAdvice(advice);
			processFlow.setId(GenerateSequenceUtil.generateSequenceNo());
			processFlow.setTaskResult(taskResult);
			processFlow.setType(type);
			processFlowDao.addProcessFlow(processFlow);
			this.processEnd(processInstId, userNo, taskId, processDefId);
		}
		catch (Exception e)
		{
			LogHelper.ERROR.log(e.getMessage(), e);
			throw new DBException("数据库操作错误");
		}
	}
	
	/**
	 * 资料录入修改资料表的状态和审核人
	 */
	public String updateDataInputStatus(String userNo, VProcessDetail detail, Boolean agree) throws Exception
	{
		// 修改业务表的状态
		// 根据processDefId和taskDefId查询process_status的状态
		String status = null;
		if (detail != null)
		{
			if (detail.getDetailRecordId() != null)
			{
				String id = detail.getDetailRecordId();
				BookInfo bi = new BookInfo();
				bi.setId(id);
				bi = bookInfoDao.getBookInfo(bi);

				Books b = new Books();
				b.setId(id);
				b = booksDao.getBooks(b);

				FiledData f = new FiledData();
				f.setId(id);
				f = filedDataDao.getFiledData(f);

				if (bi != null)
				{// 图书资料
					bi.setReviewers(userNo);// 审核人
					if (agree)
					{
						status = BaseDataConstants.CATALOG_STATUS_PASS;
					}
					else
					{
						status = BaseDataConstants.CATALOG_STATUS_BACK;
					}
					bi.setStatus(status);
					bookInfoDao.modifyBookInfo(bi);
				}
				else if (b != null)
				{// 海图资料
					b.setReviewers(userNo);// 审核人
					if (agree)
					{
						status = BaseDataConstants.CATALOG_STATUS_PASS;
					}
					else
					{
						status = BaseDataConstants.CATALOG_STATUS_BACK;
					}
					b.setStatus(status);
					booksDao.modifyBooks(b);
				}
				else if (f != null)
				{// 外业汇交资料
					f.setReviewers(userNo);// 审核人
					if (agree)
					{
						status = BaseDataConstants.CATALOG_STATUS_PASS;
					}
					else
					{
						status = BaseDataConstants.CATALOG_STATUS_BACK;
					}
					f.setStatus(status);
					filedDataDao.modifyFiledData(f);
				}
				return status;
			}
		}
		return null;
	}

	public String updateBusinessStatus(VProcessDetail detail, String processDefId, String taskDefId, Boolean agree) throws Exception
	{
		// 修改业务表的状态
		// 根据processDefId和taskDefId查询process_status的状态
		String status = null;
		if (detail != null)
		{
			if (detail.getTableStatusName() != null)
			{
				ProcessStatus ps = new ProcessStatus();
				ps.setProcessDefId(processDefId);
				ps.setTaskDefId(taskDefId);
				ps = processStatusDao.getProcessStatusByProAndTask(ps);
				if (ps == null) { return null; }
				if (ps.getStatus() != null)
				{
					BaseData bd = new BaseData();
					bd.setId(ps.getStatus());
					List<BaseData> bdList = baseDataDao.getBaseDataById(bd);
					if (bdList != null)
					{
						if (bdList.size() > 0)
						{
							status = bdList.get(0).getValue();
							if (status != null)
							{
								String[] statusArray = status.split(" ");
								if (statusArray.length > 0)
								{
									if (agree)
									{
										status = statusArray[0];
									}
									else
									{
										if (statusArray.length > 1)
										{
											status = statusArray[1];
										}
									}
									// 修改的业务表名
									String tableName = detail.getTableName();
									// 修改表的主键id值
									String id = detail.getDetailRecordId();
									// 修改表的主键key
									String key = detail.getTableKeyName();
									// 要修改的字段名称
									String tableStatusName = detail.getTableStatusName();
									int count = taskDao.updateTable(tableName, id, key, tableStatusName, status);
									if (count > 0)
									{
										return status;
									}
									else
									{
										throw new CommonException("状态更新失败");
									}
								}
							}
						}
					}
				}
			}
		}
		return null;
	}

	/**
	 * 修改资料借阅的数量
	 */
	public void updateBookInfoTotal(VProcessDetail detail, Boolean agree) throws Exception
	{
		// 修改业务表的状态
		if (detail != null)
		{
			if (detail.getTableStatusName() != null)
			{
				// 归还表的主键id值
				String id = detail.getDetailRecordId();
				// 根据id得到归还的记录
				Borrowing b = new Borrowing();
				b.setId(id);
				b = borrowingDao.getBorrowing(b);
				if (b != null)
				{
					if (b.getBorrowCode() != null)
					{
						if (b.getType() != null)
						{
							if (b.getType().equals("bookinfo"))
							{
								BookInfo bi = new BookInfo();
								bi.setCode(b.getBorrowCode());
								bi = bookInfoDao.getBookInfoByCode(bi);
								if (bi != null)
								{
									if (agree)
									{
										// 同意，审核通过,库存减去借阅数量
										int inventoryNum = Integer.valueOf(bi.getInventoryNum()) - Integer.valueOf(b.getBorrowNo());
										if(inventoryNum<0){
											inventoryNum=0;
										}
										if(inventoryNum==0){
											bi.setState("110319542370421");
										}
										bi.setInventoryNum(String.valueOf(inventoryNum));
										bookInfoDao.modifyBookInfo(bi);
									}
									else
									{
										// 退回，审核不通过,可用数量加上借阅数量
										int canBorrowing = Integer.valueOf(bi.getCanBorrowing()) + Integer.valueOf(b.getBorrowNo());
										bi.setCanBorrowing(String.valueOf(canBorrowing));
										bookInfoDao.modifyBookInfo(bi);
									}
								}
							}
							else if (b.getType().equals("books"))
							{
								Books bs = new Books();
								bs.setCode(b.getBorrowCode());
								bs = booksDao.getBooksByCode(bs);
								if (bs != null)
								{
									if (agree)
									{
										// 同意，审核通过,库存减去借阅数量
										int stockNo = Integer.valueOf(bs.getStockNo()) - Integer.valueOf(b.getBorrowNo());
										if(stockNo<0){
											stockNo=0;
										}
										if(stockNo==0){
											bs.setState("110319542370421");
										}
										bs.setStockNo(String.valueOf(stockNo));
										booksDao.modifyBooks(bs);
									}
									else
									{
										// 退回，审核不通过,可用数量加上借阅数量
										int canBorrowing = Integer.valueOf(bs.getCanBorrowing()) + Integer.valueOf(b.getBorrowNo());
										bs.setCanBorrowing(String.valueOf(canBorrowing));
										booksDao.modifyBooks(bs);
									}
								}
							}
							else if (b.getType().equals("fileddata"))
							{
								FiledData fd = new FiledData();
								fd.setPicNo(b.getBorrowCode());
								fd = filedDataDao.getFiledDataByPicNo(fd);
								if (fd != null)
								{
									if (agree)
									{
										// 同意，审核通过,库存减去借阅数量
										int copies = Integer.valueOf(fd.getCopies()) - Integer.valueOf(b.getBorrowNo());
										if(copies<0){
											copies=0;
										}
										if(copies==0){
											fd.setState("110319542370421");
										}
										fd.setCopies(String.valueOf(copies));
										filedDataDao.modifyFiledData(fd);
									}
									else
									{
										// 退回，审核不通过,可用数量加上借阅数量
										int canBorrowing = Integer.valueOf(fd.getCanBorrowing()) + Integer.valueOf(b.getBorrowNo());
										fd.setCanBorrowing(String.valueOf(canBorrowing));
										filedDataDao.modifyFiledData(fd);
									}
								}
							}
						}

					}
				}
			}
		}
	}

	/**
	 * 修改资料借阅的数量
	 */
	public void updateReturnBookInfoTotal(VProcessDetail detail, Boolean agree) throws Exception
	{
		// 修改业务表的状态
		if (detail != null)
		{
			if (detail.getTableStatusName() != null)
			{
				// 归还表的主键id值
				String id = detail.getDetailRecordId();
				// 根据id得到归还的记录
				ReturnBook b = new ReturnBook();
				b.setId(id);
				b = returnBookDao.getReturnBook(b);
				if (b != null)
				{
					if (b.getBookNo() != null)
					{
						if (b.getType() != null)
						{
							if (b.getType().equals("bookinfo"))
							{// 图书资料
								BookInfo bi = new BookInfo();
								bi.setCode(b.getBookNo());
								bi = bookInfoDao.getBookInfoByCode(bi);
								if (bi != null)
								{
									if (agree)
									{
										// 同意，审核通过，归还成功,库存和可借数量都加上本次归还数量
										int inventoryNum = Integer.valueOf(bi.getInventoryNum()) + Integer.valueOf(b.getCurReturnNo());
										bi.setInventoryNum(String.valueOf(inventoryNum));
										int canBorrowing = Integer.valueOf(bi.getCanBorrowing()) + Integer.valueOf(b.getCurReturnNo());
										bi.setCanBorrowing(String.valueOf(canBorrowing));
										if(inventoryNum!=0){
											bi.setState("11031954066330412");
										}
										bookInfoDao.modifyBookInfo(bi);

									}
									else
									{
										// 退回，审核不通过，归还失败，库存和可借数量不变，归还数量减去本次归还数量，本次归还数量清零
										int returnNo = Integer.valueOf(b.getReturnNo()) - Integer.valueOf(b.getCurReturnNo());
										b.setReturnNo(String.valueOf(returnNo));
										returnBookDao.modify(b);
									}
								}
							}
							else if (b.getType().equals("books"))
							{// 海图资料
								Books bs = new Books();
								bs.setCode(b.getBookNo());
								bs = booksDao.getBooksByCode(bs);
								if (bs != null)
								{
									if (agree)
									{
										// 同意，审核通过，归还成功,库存和可借数量都加上本次归还数量
										int stockNo = Integer.valueOf(bs.getStockNo()) + Integer.valueOf(b.getCurReturnNo());
										bs.setStockNo(String.valueOf(stockNo));
										int canBorrowing = Integer.valueOf(bs.getCanBorrowing()) + Integer.valueOf(b.getCurReturnNo());
										bs.setCanBorrowing(String.valueOf(canBorrowing));
										if(stockNo!=0){
											bs.setState("11031954066330412");
										}
										booksDao.modifyBooks(bs);

									}
									else
									{
										// 退回，审核不通过，归还失败，库存和可借数量不变，归还数量减去本次归还数量，本次归还数量清零
										int returnNo = Integer.valueOf(b.getReturnNo()) - Integer.valueOf(b.getCurReturnNo());
										b.setReturnNo(String.valueOf(returnNo));
										returnBookDao.modify(b);
									}
								}
							}
							else if (b.getType().equals("fileddata"))
							{// 外业汇交资料
								FiledData fd = new FiledData();
								fd.setPicNo(b.getBookNo());
								fd = filedDataDao.getFiledDataByPicNo(fd);
								if (fd != null)
								{
									if (agree)
									{
										// 同意，审核通过，归还成功,库存和可借数量都加上本次归还数量
										int copies = Integer.valueOf(fd.getCopies()) + Integer.valueOf(b.getCurReturnNo());
										fd.setCopies(String.valueOf(copies));
										int canBorrowing = Integer.valueOf(fd.getCanBorrowing()) + Integer.valueOf(b.getCurReturnNo());
										fd.setCanBorrowing(String.valueOf(canBorrowing));
										if(copies!=0){
											fd.setState("11031954066330412");
										}
										filedDataDao.modifyFiledData(fd);

									}
									else
									{
										// 退回，审核不通过，归还失败，库存和可借数量不变，归还数量减去本次归还数量，本次归还数量清零
										int returnNo = Integer.valueOf(b.getReturnNo()) - Integer.valueOf(b.getCurReturnNo());
										b.setReturnNo(String.valueOf(returnNo));
										returnBookDao.modify(b);
									}
								}
							}
						}
					}
				}
			}
		}
	}

	@Override
	public void performTaskWithcliamUser(String taskId, String cliamUser) throws Exception
	{

	}

	@Override
	public void performTaskWithCallActivity(String taskId, List<String> callActivityIds) throws Exception
	{

	}

	/**
	 * 流程结束处理
	 * @param procInstId
	 * @throws Exception
	 */
	private void processEnd(String processInstId, String userNo, String taskId, String processDefId) throws Exception
	{
		String endTime = service.getProcessEndTime(processInstId);
		WsResult<Date> result = new WsResult<Date>();
		result = (WsResult<Date>) DataConverter.convertJson2Object(endTime, result.getClass());
		// 结束时间不为空代表流程结束
		if (result.getData() != null)
		{
			ProcessFlow processFlow = new ProcessFlow();
			processFlow.setUserNo(userNo);
			processFlow.setProcessInstId(processInstId);
			processFlow.setId(GenerateSequenceUtil.generateSequenceNo());
			processFlow.setTaskResult("流程结束");
			processFlow.setType("结束");
			processFlowDao.addProcessFlow(processFlow);
			// 流程结束后 发送通知到当前流程所有人员
			// 发送通知
			List<ProcessFlow> flows = processFlowDao.getProcessFlowByProcessInstId(processInstId);
			List<User> userList = new ArrayList<User>();
			if (flows != null)
			{
				if (flows.size() > 0)
				{
					for (int i = 0; i < flows.size(); i++)
					{
						String puiblishUserNo = flows.get(i).getUserNo();
						User user = new User();
						user.setUserNo(puiblishUserNo);
						List<User> puiblishUsers = userDao.getUserByNo(user);
						if (puiblishUsers != null)
						{
							if (puiblishUsers.size() > 0)
							{
								userList.addAll(puiblishUsers);
							}
						}
					}
				}
			}
			pulishNotice(processInstId, processDefId, userList);
		}
	}

	@Resource
	NoticeDao noticeDao;

	@Resource
	UserNoticeDao usernoticeDao;

	private void pulishNotice(String processInstId, String processDefId, List<User> userList)
	{
		Notice notice = new Notice();
		notice.setNotice_Type("10301416596850001");
		notice.setTitle("系统通知");
		ProcessDefinition info = service.getProcessDefinitionId(processDefId);
		// 转换提日期输出格式
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		notice.setDescription("流程" + info.getName() + "已经结束，结束时间为：" + dateFormat.format(date));
		String noticeId = GenerateSequenceUtil.generateSequenceNo();
		notice.setId(noticeId);
		noticeDao.addNotice(notice);
		for (int i = 0; i < userList.size(); i++)
		{
			UserNotice usernotice = new UserNotice();
			usernotice.setId(GenerateSequenceUtil.generateSequenceNo());
			usernotice.setNotice_id(noticeId);
			usernotice.setUser_id(userList.get(i).getId());
			usernotice.setIs_read("否");
			usernotice.setRelease_time(dateFormat.format(date));
			usernoticeDao.addUserNotice(usernotice);
		}
	}
	
	/**
	 * 设置任务属性
	 * @param task 任务
	 * @param map 属性集合
	 * @return 任务属性集合
	 */
	private Map<String, Object> setProcessTask(String processDefId, String processDefName, String taskDefId, String processInstId, String taskInstId,
			String taskName, Date createTime, Boolean suspendState, Map<String, Object> map)
	{
		// 流程定义ID
		map.put("processDefId", processDefId);
		// 流程名称
		map.put("processDefName", processDefName);
		// 任务定义ID
		map.put("taskDefId", taskDefId);
		// 流程实例ID
		map.put("processInstId", processInstId);
		// 任务实例ID
		map.put("taskInstId", taskInstId);
		// 任务名称
		map.put("taskName", taskName);
		// 挂起状态
		map.put("suspendState", suspendState);
		// 创建时间
		map.put("createTime", createTime);
		return map;
	}

	/**
	 * 设置任务书信息
	 * @param taskBookId
	 * @return
	 * @throws Exception
	 */
	private Map<String, Object> setTaskBookInfo(Map<String, Object> map,String taskBookId) throws Exception{
		TaskBook tb = taskBookDao.findById(taskBookId);
		if (tb != null)
		{
			map.put("taskbookName", tb.getTaskbookName());
			String filename = "";
			if (tb.getEnclosure() != null)
			{
				filename = tb.getEnclosure().split("\\\\")[tb.getEnclosure().split("\\\\").length - 1];
			}
			map.put("enclosure", tb.getEnclosure());
			map.put("filename", filename);
			map.put("id", tb.getId());
		}
		return map;
	}
	
	/**
	 * 设置任务书和计划
	 * @param bill 任务书
	 * @param map 计划
	 * @return 结果
	 */
	private Map<String, Object> setTaskBookAndPlan(TaskSplit split, Map<String, Object> map)
	{
		// 任务书计划关系
		TaskBill bill = split.getTaskBill();
		// 任务书
		TaskBook taskBook = bill.getTaskBook();
		if (taskBook != null)
		{
			// 通告期号
			map.put("noticeNo", taskBook.getNoticeNo());
			// 工程名称
			map.put("projectName", taskBook.getProjectName());
			// 改正期号
			map.put("corrcetNo", taskBook.getCorrectNo());
			// 改正通告期号
			map.put("corrcetNoticeNo", taskBook.getCorrectNoticeNo());
			// 项目名称
			map.put("itemName", taskBook.getItemName());
			// 版次
			map.put("revision", taskBook.getRevision());
		}
		if (bill != null)
		{
			// 计划
			Plan plan = bill.getPlan();
			if (plan != null)
			{
				// 图名
				map.put("mapName", plan.getMapName());
				// 图号
				map.put("mapNo", plan.getMapNo());
				// 比例尺
				map.put("scale", plan.getScale());
			}
		}
		map.put("status", split.getStatus());
		return map;
	}

	@Override
	public List<RuTask> getRuTaskByProcessInstId(String processInstId) throws Exception
	{
		try
		{
			RuTask ruTask = new RuTask();
			ruTask.setProcessInstId(processInstId);
			return taskDao.getRuTaskByProcessInstId(ruTask);
		}
		catch (Exception e)
		{
			LogHelper.FATAL.log(e.getMessage(), e);
			throw new CommonException("数据库出现错误,原因为：" + e.getMessage());
		}
	}

	@Override
	public void suspendProcessInstance(String processInstId, String userNo, String advice) throws Exception
	{
		try
		{
			service.suspendProcessInstanceById(processInstId);
			ProcessFlow processFlow = new ProcessFlow();
			processFlow.setUserNo(userNo);
			processFlow.setProcessInstId(processInstId);
			processFlow.setId(GenerateSequenceUtil.generateSequenceNo());
			processFlow.setTaskResult("挂起流程成功");
			processFlow.setType("挂起");
			processFlow.setAdvice(advice);
			processFlowDao.addProcessFlow(processFlow);
		}
		catch (Exception e)
		{
			throw new CommonException("系统错误，原因为：" + e.getMessage());
		}
	}

	@Override
	public void activateProcessInstance(String processInstId, String userNo, String advice) throws Exception
	{
		try
		{
			service.activateProcessInstanceById(processInstId);
			ProcessFlow processFlow = new ProcessFlow();
			processFlow.setUserNo(userNo);
			processFlow.setProcessInstId(processInstId);
			processFlow.setId(GenerateSequenceUtil.generateSequenceNo());
			processFlow.setTaskResult("激活流程成功");
			processFlow.setType("激活");
			processFlow.setAdvice(advice);
			processFlowDao.addProcessFlow(processFlow);
		}
		catch (Exception e)
		{
			throw new CommonException("系统错误，原因为：" + e.getMessage());
		}
	}

	@Override
	public void delegateTask(String processInstId, String taskId, String userNo, String delegateUserId) throws Exception
	{
		try
		{
			// 执行委托
			service.delegate(taskId, delegateUserId);
			// 更改 编绘人员参数为 编绘委托人（编绘员 质检员 审定员）
			Object bhy = service .getProcessArgs(processInstId, "user_bhy");
			//判断类型
			String userType = this.getUserType(delegateUserId);
			if ("user_bhy".equals(userType)) {
				//将之后的流程任务都指派给该人员
				service.setProcessArg(processInstId, "user_bhy", delegateUserId);
				//获取指派记录
				List<AssignUserRecord> recordList = assignUserRecordDao.getRecordByProcessInstIdAndAssignUser(processInstId, (String)bhy);
				//如果任务曾指派过，则至修改被指派人，否则新增指派记录
				if(recordList!=null&&recordList.size()>0){
					AssignUserRecord assignUserRecord = recordList.get(0);
					assignUserRecord.setAssignUser(delegateUserId);
					assignUserRecordDao.updateAssignRecord(assignUserRecord);
				}else{
					AssignUserRecord assignUserRecord=new AssignUserRecord();
					assignUserRecord.setId(GenerateSequenceUtil.generateSequenceNo());
					assignUserRecord.setAssignUser(delegateUserId);
					assignUserRecord.setHisUser((String)bhy);
					assignUserRecord.setProcessInstId(processInstId);
					assignUserRecord.setType("user_bhy");
					assignUserRecordDao.addAssignRecord(assignUserRecord);
				}
			}
			
			Object zjy = service.getProcessArgs(processInstId, "user_zjy");
			if ("user_zjy".equals(userType)) {
				//将之后的流程任务都指派给该人员
				service.setProcessArg(processInstId, "user_zjy", delegateUserId);
				//如果任务曾指派过，则至修改被指派人，否则新增指派记录
				List<AssignUserRecord> recordList = assignUserRecordDao.getRecordByProcessInstIdAndAssignUser(processInstId, (String)zjy);
				if(recordList!=null&&recordList.size()>0){
					AssignUserRecord assignUserRecord = recordList.get(0);
					assignUserRecord.setAssignUser(delegateUserId);
					assignUserRecordDao.updateAssignRecord(assignUserRecord);
				}else{
					AssignUserRecord assignUserRecord=new AssignUserRecord();
					assignUserRecord.setId(GenerateSequenceUtil.generateSequenceNo());
					assignUserRecord.setAssignUser(delegateUserId);
					assignUserRecord.setHisUser((String)zjy);
					assignUserRecord.setProcessInstId(processInstId);
					assignUserRecord.setType("user_zjy");
					assignUserRecordDao.addAssignRecord(assignUserRecord);
				}
			}
			
			Object sdy = service.getProcessArgs(processInstId, "user_sdy");
			if ("user_sdy".equals(userType)) {
				//将之后的流程任务都指派给该人员
				service.setProcessArg(processInstId, "user_sdy", delegateUserId);
				//如果任务曾指派过，则至修改被指派人，否则新增指派记录
				List<AssignUserRecord> recordList = assignUserRecordDao.getRecordByProcessInstIdAndAssignUser(processInstId, (String)sdy);
				if(recordList!=null&&recordList.size()>0){
					AssignUserRecord assignUserRecord = recordList.get(0);
					assignUserRecord.setAssignUser(delegateUserId);
					assignUserRecordDao.updateAssignRecord(assignUserRecord);
				}else{
					AssignUserRecord assignUserRecord=new AssignUserRecord();
					assignUserRecord.setId(GenerateSequenceUtil.generateSequenceNo());
					assignUserRecord.setAssignUser(delegateUserId);
					assignUserRecord.setHisUser((String)sdy);
					assignUserRecord.setProcessInstId(processInstId);
					assignUserRecord.setType("user_sdy");
					assignUserRecordDao.addAssignRecord(assignUserRecord);
				}
			}
			
			
			ProcessFlow processFlow = new ProcessFlow();
			processFlow.setUserNo(userNo);
			processFlow.setProcessInstId(processInstId);
			processFlow.setId(GenerateSequenceUtil.generateSequenceNo());
			User user = new User();
			user.setId(delegateUserId);
			User delegateUser = userDao.getUser(user);
			if (delegateUser != null)
			{
				processFlow.setTaskResult("委托任务成功,被委托人：" + delegateUser.getUserName());
			}
			processFlow.setType("委托");
			processFlowDao.addProcessFlow(processFlow);
		}
		catch (Exception e)
		{
			throw new CommonException("系统错误，原因为：" + e.getMessage());
		}
	}

	/**
	 * 获取所有经办
	 */
	@Override
	public List<Map<String, Object>> getCompleteTask() throws Exception
	{
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		List<String> param = new ArrayList<String>();
		for (SeaMapEnum processType : SeaMapEnum.values())
		{
			param.add(processType.name());
		}
		List<HiTask> hiTasks = taskDao.getCompleteTask(param);
		Map<String, Map<String, Object>> help = new HashMap<String, Map<String, Object>>();
		if (hiTasks != null)
		{
			for (int i = 0; i < hiTasks.size(); i++)
			{
				HiTask hiTask = hiTasks.get(i);
				if (!help.containsKey(hiTask.getProcessInstId()))
				{
					Map<String, Object> map = setHiTaskInfo(hiTask);
					Map<String, Object> compeletionMap = setTaskCompeletion(hiTask.getProcessDefKey(),hiTask.getProcessInstId(),hiTask.getParentProcessInstId(),(String)map.get("mapNo"));
					map.putAll(compeletionMap);
					help.put(hiTask.getProcessInstId(), map);
					 List<DefectForm> qualitylist = getQualityAchievementScore(hiTask.getProcessDefKey(),hiTask.getProcessInstId());
					 if(qualitylist!=null&&qualitylist.size()>0){
						 DefectForm defectForm = qualitylist.get(0);
						 map.put("dataFrom","质检记录表");
						 map.put("opinion", defectForm.getOpinion());//存在问题及处理意见
						 map.put("deep", defectForm.getDeep());//类别
						 map.put("defectitem",defectForm.getDefectitem());//检查项目
						 map.put("discription",defectForm.getDiscription());//缺陷内容
						 map.put("defecttype", defectForm.getDefecttype());//缺陷类别
						 map.put("number",defectForm.getNumber());// 缺陷个数
						 map.put("actualTotal", defectForm.getActualTotal());//扣分
						 map.put("grading", defectForm.getGrading());//分数
					 }
					 List<DefectForm> validationlist = getQualityScore(hiTask.getProcessDefKey(), hiTask.getProcessInstId());
					 if(validationlist!=null&&validationlist.size()>0){
						 DefectForm defectForm = validationlist.get(0);
						 map.put("dataFrom","审定记录表");
						 map.put("opinion", defectForm.getOpinion());//存在问题及处理意见
						 map.put("deep", defectForm.getDeep());//类别
						 map.put("defectitem",defectForm.getDefectitem());//检查项目
						 map.put("discription",defectForm.getDiscription());//缺陷内容
						 map.put("defecttype", defectForm.getDefecttype());//缺陷类别
						 map.put("number",defectForm.getNumber());// 缺陷个数
						 map.put("actualTotal", defectForm.getActualTotal());//扣分
						 map.put("grading", defectForm.getGrading());//分数
					 }
					result.add(map);
				}
			}
		}
		return result;
	}

	@Override
	public List<Map<String, Object>> getCompleteTaskByKey(String processDefKey) throws Exception
	{
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		List<String> param = new ArrayList<String>();
		param.add(processDefKey);
		List<HiTask> hiTasks = taskDao.getCompleteTask(param);
		Map<String, Map<String, Object>> help = new HashMap<String, Map<String, Object>>();
		if (hiTasks != null)
		{
			for (int i = 0; i < hiTasks.size(); i++)
			{
				HiTask hiTask = hiTasks.get(i);
				if (!help.containsKey(hiTask.getProcessInstId()))
				{
					Map<String, Object> map = setHiTaskInfo(hiTask);
					Map<String, Object> compeletionMap = setTaskCompeletion(hiTask.getProcessDefKey(),hiTask.getProcessInstId(),hiTask.getParentProcessInstId(),(String)map.get("mapNo"));
					map.putAll(compeletionMap);
					help.put(hiTask.getProcessInstId(), map);
					 List<DefectForm> qualitylist = getQualityAchievementScore(hiTask.getProcessDefKey(),hiTask.getProcessInstId());
					 if(qualitylist!=null&&qualitylist.size()>0){
						 for (DefectForm defectForm : qualitylist) {
							 Map<String,Object> qualityMap=new HashMap<String,Object>();
							 qualityMap.putAll(map);
							 qualityMap.put("dataFrom","质检记录表");
							 qualityMap.put("opinion", defectForm.getOpinion());//存在问题及处理意见
							 qualityMap.put("deep", defectForm.getDeep());//类别
							 qualityMap.put("defectitem",defectForm.getDefectitem());//检查项目
							 qualityMap.put("discription",defectForm.getDiscription());//缺陷内容
							 qualityMap.put("defecttype", defectForm.getDefecttype());//缺陷类别
							 qualityMap.put("number",defectForm.getNumber());// 缺陷个数
							 qualityMap.put("score", defectForm.getScore());//扣分
							 qualityMap.put("grading", defectForm.getGrading());//分数
							 result.add(qualityMap);
						}
						
					 }
					 List<DefectForm> validationlist = getQualityScore(hiTask.getProcessDefKey(), hiTask.getProcessInstId());
					 if(validationlist!=null&&validationlist.size()>0){
						 for (DefectForm defectForm : validationlist) {
							 Map<String,Object> validationMap=new HashMap<String,Object>();
							 validationMap.putAll(map);
							 validationMap.put("dataFrom","审定记录表");
							 validationMap.put("opinion", defectForm.getOpinion());//存在问题及处理意见
							 validationMap.put("deep", defectForm.getDeep());//类别
							 validationMap.put("defectitem",defectForm.getDefectitem());//检查项目
							 validationMap.put("discription",defectForm.getDiscription());//缺陷内容
							 validationMap.put("defecttype", defectForm.getDefecttype());//缺陷类别
							 validationMap.put("number",defectForm.getNumber());// 缺陷个数
							 validationMap.put("score", defectForm.getScore());//扣分
							 validationMap.put("grading", defectForm.getGrading());//分数
							 result.add(validationMap);
						}
						
					 }
				}
			}
		}
		return result;
	}
	@Override
	public List<Map<String, Object>> getCompleteTaskByDate(String processDefKey,String startTime,String endTime) throws Exception
	{
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		
		List<String> param = new ArrayList<String>();
		param.add(processDefKey);
		Date beginDate =null;
		Date endDate=null;
		if(StringUtils.isNotEmpty(startTime)&&StringUtils.isNotEmpty(endTime)){
			beginDate = sdf.parse(startTime);
			endDate = sdf.parse(endTime);
		}
		List<HiTask> hiTasks = taskDao.getCompleteTask(param);
		Map<String, Map<String, Object>> help = new HashMap<String, Map<String, Object>>();
		if (hiTasks != null)
		{
			for (int i = 0; i < hiTasks.size(); i++)
			{
				HiTask hiTask = hiTasks.get(i);
				if (!help.containsKey(hiTask.getProcessInstId()))
				{
					Map<String, Object> map = setHiTaskInfo(hiTask);
					Map<String, Object> compeletionMap = setTaskCompeletion(hiTask.getProcessDefKey(),hiTask.getProcessInstId(),hiTask.getParentProcessInstId(),(String)map.get("mapNo"));
					map.putAll(compeletionMap);
					help.put(hiTask.getProcessInstId(), map);
					List<DefectForm> qualitylist = getQualityAchievementScore(hiTask.getProcessDefKey(),hiTask.getProcessInstId());
					if(qualitylist!=null&&qualitylist.size()>0){
						for (DefectForm defectForm : qualitylist) {
							Map<String,Object> qualityMap=new HashMap<String,Object>();
							qualityMap.putAll(map);
							qualityMap.put("dataFrom","质检记录表");
							qualityMap.put("opinion", defectForm.getOpinion());//存在问题及处理意见
							qualityMap.put("deep", defectForm.getDeep());//类别
							qualityMap.put("defectitem",defectForm.getDefectitem());//检查项目
							qualityMap.put("discription",defectForm.getDiscription());//缺陷内容
							qualityMap.put("defecttype", defectForm.getDefecttype());//缺陷类别
							qualityMap.put("number",defectForm.getNumber());// 缺陷个数
							qualityMap.put("score", defectForm.getScore());//扣分
							qualityMap.put("grading", defectForm.getGrading());//分数
							if("审定完成".equals(qualityMap.get("status"))){
								Date completeDate =(Date)qualityMap.get("completeDate");
								if(beginDate!=null&&endDate!=null&&completeDate!=null&&(completeDate.after(beginDate)||completeDate.equals(beginDate))&&(completeDate.before(endDate)||completeDate.equals(endDate))){
									result.add(qualityMap);
								}
							}
						}
						
					}
					List<DefectForm> validationlist = getQualityScore(hiTask.getProcessDefKey(), hiTask.getProcessInstId());
					if(validationlist!=null&&validationlist.size()>0){
						for (DefectForm defectForm : validationlist) {
							Map<String,Object> validationMap=new HashMap<String,Object>();
							validationMap.putAll(map);
							validationMap.put("dataFrom","审定记录表");
							validationMap.put("opinion", defectForm.getOpinion());//存在问题及处理意见
							validationMap.put("deep", defectForm.getDeep());//类别
							validationMap.put("defectitem",defectForm.getDefectitem());//检查项目
							validationMap.put("discription",defectForm.getDiscription());//缺陷内容
							validationMap.put("defecttype", defectForm.getDefecttype());//缺陷类别
							validationMap.put("number",defectForm.getNumber());// 缺陷个数
							validationMap.put("score", defectForm.getScore());//扣分
							validationMap.put("grading", defectForm.getGrading());//分数
							if("审定完成".equals(validationMap.get("status"))){
								Date completeDate =(Date)validationMap.get("completeDate");
								if(beginDate!=null&&endDate!=null&&completeDate!=null&&(completeDate.after(beginDate)||completeDate.equals(beginDate))&&(completeDate.before(endDate)||completeDate.equals(endDate))){
									result.add(validationMap);
								}
							}
						}
						
					}
				}
			}
		}
		return result;
	}

	/**
	 * 设置经办任务信息
	 * @param hiTask
	 * @return
	 * @throws Exception
	 */
	private Map<String, Object> setHiTaskInfo(HiTask hiTask) throws Exception
	{
		Map<String, Object> map = new HashMap<String, Object>();
		this.setProcessTask(hiTask.getProcessDefId(), hiTask.getProcessDefName(), hiTask.getTaskDefId(), hiTask.getProcessInstId(),
				hiTask.getTaskInstId(), hiTask.getTaskName(), hiTask.getStartTime(), null, map);
		// 业务记录ID
		VProcessDetail detail = getDetailRecordId(hiTask.getProcessDefKey(), hiTask.getParentProcessInstId(), hiTask.getProcessInstId());
		if (detail != null)
		{
			String recordId = detail.getDetailRecordId();
			if (StringUtils.isNotEmpty(recordId))
			{
				map = setHiTaskRecordProps(map, ProcessTypeEnum.valueOf(hiTask.getProcessDefKey()), recordId);
				map.put("endTime", hiTask.getEndTime());
				map.put("group", hiTask.getGroupName());
				map.put("performer", hiTask.getAssigneeName());
				map.put("excutionId", hiTask.getExecutionId());
				map.put("processEndTime", hiTask.getProcessEndTime());
				map.put("parentProcessInstId", hiTask.getParentProcessInstId());
			}
		}
		return map;
	}

	@Override
	/**
	 * 获取经办
	 */
	public List<Map<String, Object>> getHiTask(String loginNo, ProcessTypeEnum type,String processInstId) throws Exception
	{
		try
		{
			String userId = getUserId(loginNo);
			List<Group> groups = service.getGroupsByUser(userId);
			// 首先根据受理人查询历史任务
			List<HiTask> hiTasks = taskDao.getHiTaskByAssigneeAndProcessInstId(userId, type.name(),processInstId);
			List<HiTask> hiTasksGroups = new ArrayList<HiTask>();
			if (groups != null)
			{
				for (int i = 0; i < groups.size(); i++)
				{
					Group group = groups.get(i);
					List<HiTask> hiTasksGroup = taskDao.getHiTaskByGroupAndProcessInstId(group.getId(), type.name(),processInstId);
					if (hiTasksGroup != null)
					{
						hiTasksGroups.addAll(hiTasksGroup);
					}
				}
			}
			Map<String,HiTask> hiTaskMap = new HashMap<String, HiTask>();
			if (hiTasks != null)
			{
				for (int i = 0; i < hiTasks.size(); i++)
				{
					HiTask hiTask = hiTasks.get(i);
					if (!hiTaskMap.containsKey(hiTask.getTaskInstId()))
					{
						hiTaskMap.put(hiTask.getTaskInstId(), hiTask);
					}
				}
			}
			if (hiTasksGroups != null)
			{
				for (int i = 0; i < hiTasksGroups.size(); i++)
				{
					HiTask hiTask = hiTasksGroups.get(i);
					if (!hiTaskMap.containsKey(hiTask.getTaskInstId()))
					{
						hiTaskMap.put(hiTask.getTaskInstId(), hiTask);
					}
				}
			}
			List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
			if (hiTaskMap != null)
			{
				for (Entry<String, HiTask> entry : hiTaskMap.entrySet())
				{
					HiTask hiTask = entry.getValue();
					Map<String, Object> map = setHiTaskInfo(hiTask);
					result.add(map);
				}
			}
			return result;
		}
		catch (Exception e)
		{
			throw new CommonException("请求发生错误:" + e.getMessage());
		}
	}

	/**
	 * 根据流程类型获取对应属性值集合
	 * @param processType 流程类型
	 * @return 数据集合
	 * @throws Exception
	 */
	private Map<String, Object> setHiTaskRecordProps(Map<String, Object> map, ProcessTypeEnum processType, String recordId) throws Exception
	{
		switch (processType)
		{
		// 目录管理
		case CATALOG_MANAGEMENT:
			// 定义返回类型存放map
			// 定义查询数据表对象
			CatalogDetail catalogDetail = new CatalogDetail();
			// 设置类型
			catalogDetail.setId(recordId);
			// 根据类型查询当前所有任务
			catalogDetail = catalogDetailDao.getDetail(catalogDetail);
			setCatalogDetail(catalogDetail, map);
			break;
		case DATA_BORROWING:
			// 定义返回类型存放map
			// 定义查询数据表对象
			Borrowing b = new Borrowing();
			// 设置类型
			b.setId(recordId);
			// 根据类型查询当前所有任务
			b = BorrowingDao.getBorrowing(b);
			setBookInfo(b, map);
			break;
		case DATA_RETURN:
			// 定义返回类型存放map
			// 定义查询数据表对象
			ReturnBook rb = new ReturnBook();
			// 设置类型
			rb.setId(recordId);
			// 根据类型查询当前所有任务
			rb = returnBookDao.getReturnBook(rb);
			setReturnBookInfo(rb, map);
			break;
		case DATA_INPUT:
			// 定义返回类型存放map
			// 定义查询数据表对象
			// 定义查询数据表对象
			BookInfo bookInfo = new BookInfo();
			// 设置类型
			bookInfo.setId(recordId);
			// 根据类型查询当前所有任务
			bookInfo = bookInfoDao.getBookInfo(bookInfo);
			Books bs = new Books();
			// 设置类型
			bs.setId(recordId);
			// 根据类型查询当前所有任务
			bs = booksDao.getBooks(bs);
			FiledData f = new FiledData();
			// 设置类型
			f.setId(recordId);
			// 根据类型查询当前所有任务
			f = filedDataDao.getFiledData(f);
			// 设置图书资料属性
			if (bookInfo != null)
			{
				map = setBookInfoDataInput(bookInfo, map);
			}
			else if (bs != null)
			{
				map = setBooksDataInput(bs, map);
			}
			else if (f != null)
			{
				map = setFiledDataInput(f, map);
			}
			break;
		case TASK_BOOK:
			setTaskBookInfo(map, recordId);
			break;
		default:
			setDrawTaskInfo(map, recordId);
			break;
		}
		return map;
	}

	@Resource
	CreateTaskDao createTaskDao;

	/**
	 * 设置编绘任务信息
	 * @param map
	 * @param recordId
	 * @throws Exception
	 */
	private Map<String, Object> setDrawTaskInfo(Map<String, Object> map, String recordId) throws Exception
	{
		CompilationCompleteStatusView compilationCompleteStatus=new CompilationCompleteStatusView();
		compilationCompleteStatus.setId(recordId);
		CompilationCompleteStatusView taskv = compilationCompleteStatusDao.getCompilationCompleteStatusById(compilationCompleteStatus);
		if(taskv!=null){
			map.put("mapName", taskv.getMapName());
			map.put("mapNo", taskv.getMapNo());
			map.put("scale", taskv.getScale());
			map.put("status", taskv.getStatus());
			map.put("completeDate", taskv.getCompleteDate());
		}else{
			CreateTask task = createTaskDao.getTaskById(recordId);
			map.put("mapName", task.getTaskName());
			map.put("mapNo", task.getMapNo());
			map.put("scale", task.getScale());
			map.put("status", task.getStatus());
		}
		return map;
	}

	/**
	 * 获取用户ID
	 * @param loginNo 工号
	 * @return 取用户ID
	 */
	private String getUserId(String loginNo)
	{
		// 获取用户ID
		String userId = null;
		User user = new User();
		user.setUserNo(loginNo);
		// 根据userNo获取User
		List<User> users = userDao.getUserByNo(user);
		if (users != null)
		{
			if (users.size() > 0)
			{
				userId = users.get(0).getId();
			}
		}
		return userId;
	}
	
	/**
	 * 获取用户ID
	 * @param loginNo 工号
	 * @return 取用户ID
	 */
	private String getUserName(String userId)
	{
		// 获取用户ID
		User userParam = new User();
		userParam.setId(userId);
		// 根据userNo获取User
		User user = userDao.getUser(userParam);
		if (user != null)
		{
			return user.getUserName();
		}
		return null;
	}
	
	/**
	 * 获得业务明细ID
	 * @param processDefKey
	 * @param parentProcessInstId
	 * @param processInstId
	 * @return
	 */
	private VProcessDetail getDetailRecordId(String processDefKey, String parentProcessInstId, String processInstId)
	{
		VProcessDetail detail = null;
		if (StringUtils.isNotEmpty(parentProcessInstId))
		{
			detail = vProcessDetailDao.getProcessDetailByProcessInstId(parentProcessInstId);
		}
		else
		{
			detail = vProcessDetailDao.getProcessDetailByProcessInstId(processInstId);
		}
		return detail;
	}

	/**
	 * 根据流程实例ID和任务定义ID获取历史任务
	 */
	@Override
	public List<HiTask> getHiTaskByProcessInstIdAndTaskDefId(String processInstId, String taskDefId)
	{
		return taskDao.getHiTaskByProcessInstIdAndTaskDefId(processInstId, taskDefId);
	}
	
	/**
	 * 注入工天管理Dao
	 * */
	@Resource
	private CompilationWorkDaysDao workDaysDao;

	/**
	 * 设置任务完成进度情况
	 * @return
	 */
	private Map<String, Object> setTaskCompeletion(String processDefKey,String processInstId,String parentProcessInstId,String mapNo)
	{
		ComlicationTaskPersonAndDate compeletion = new ComlicationTaskPersonAndDate();
		Object compilationGroup = null;
		CompilationRealWorkDays c = new CompilationRealWorkDays();
		String content = null;
		if(processDefKey != null){
	    	if(processDefKey.contains("SEA_MAP_COMPILATION_SOURCE")){
				content = "源数据";
			}else if(processDefKey.contains("SEA_MAP_COMPILATION_PAPER")){
				content = "纸海图";
			}else if(processDefKey.contains("SEA_MAP_COMPILATION_ELECTRONIC")){
				content = "电子海图";
			}
	    }else{
	    	content = null;
	    }
		if (mapNo != null){
			c.setMapNo(mapNo);
			c.setContent(content);
			c = workDaysDao.getRealWorkDaysListByMapNo(c);
		}
		if(c != null){
			compeletion.setCompilationWorkdays(c.getCompilationWorkDays());
			compeletion.setCheckWorkdays(c.getCheckWorkDays());
			compeletion.setExamineWorkdays(c.getExamineWorkDays());
		}
		if (StringUtils.isNotEmpty(parentProcessInstId))
		{
			compilationGroup = service.getHistProcessArgs(parentProcessInstId, ComplicationTaskProcessArgs.compilationGroup);
		}
		else{
			compilationGroup = service.getHistProcessArgs(processInstId, ComplicationTaskProcessArgs.compilationGroup);
		}
		// 设置编绘人
		Object compilationPerson = service.getHistProcessArgs(processInstId, ComplicationTaskProcessArgs.compilationPerson);
		if (compilationPerson != null)
		{
			String compilationPersonName = getUserName(compilationPerson.toString());
			try {
				List<AssignUserRecord>  recordList = assignUserRecordDao.getRecordByProcessInstIdAndType(processInstId, ComplicationTaskProcessArgs.compilationPerson);
				if(recordList!=null&&recordList.size()>0){
					compilationPersonName= getUserName(recordList.get(0).getAssignUser());
				}
			} catch (Exception e) {
				LogHelper.ERROR.log(e.getMessage(), e);
			}
			
			compeletion.setCompilationPerson(compilationPersonName);
		}
		// 设置质检员
		Object qualityPerson = service.getHistProcessArgs(processInstId, ComplicationTaskProcessArgs.qualityPerson);
		if (qualityPerson != null)
		{
			String qualityPersonName = getUserName(qualityPerson.toString());
			try {
				List<AssignUserRecord>  recordList = assignUserRecordDao.getRecordByProcessInstIdAndType(processInstId, ComplicationTaskProcessArgs.qualityPerson);
				if(recordList!=null&&recordList.size()>0){
					qualityPersonName= getUserName(recordList.get(0).getAssignUser());
				}
			} catch (Exception e) {
				LogHelper.ERROR.log(e.getMessage(), e);
			}
			compeletion.setQualityPerson(qualityPersonName);
		}
		//质检分数
//		String qualityScore = getQualityAchievementScore(processDefKey, processInstId);
//		if(qualityScore != null){
//			compeletion.setQualityScore(qualityScore);
//		}
		// 设置审定员
		Object apprvoePerson = service.getHistProcessArgs(processInstId, ComplicationTaskProcessArgs.apprvoePerson);
		if (apprvoePerson != null)
		{
			String apprvoePersonName = getUserName(apprvoePerson.toString());
			try {
				List<AssignUserRecord>  recordList = assignUserRecordDao.getRecordByProcessInstIdAndType(processInstId, ComplicationTaskProcessArgs.apprvoePerson);
				if(recordList!=null&&recordList.size()>0){
					apprvoePersonName= getUserName(recordList.get(0).getAssignUser());
				}
			} catch (Exception e) {
				LogHelper.ERROR.log(e.getMessage(), e);
			}
			compeletion.setApprvoePerson(apprvoePersonName.toString());
		}
		// 设置审定质量评分
		/*Delegates d = new Delegates();
		String score = d.getSdZlpf("u_task_shending_sdjilub,u_task_xgsdjilub", processInstId);*/
//		String score = getQualityScore(processDefKey, processInstId);
//		compeletion.setScore(score);
		Map<String, Object> mapCompeletion = BeanUtil.getInstance().getProperty(compeletion, true);
		return mapCompeletion;
	}
	
	/**
	 * 获取质检得分
	 * */
	public List<DefectForm> getQualityAchievementScore(String compilationContent,String processInstId){
		List<DefectForm> defectFormList = new ArrayList<DefectForm>();
		if(compilationContent.equals("CORRECTION_NOTICE_SOURCE_DATA_SMALL_CORRECTION")){
	    	defectFormList = defectFormDao.getDefectFormListByProcessInstId(processInstId, "zjjilub");
	    }else if(compilationContent.equals("CORRECTION_NOTICE_TEMPLATE")){
	    	defectFormList = defectFormDao.getDefectFormListByProcessInstId(processInstId, "bmzljcjl");
	    }else if(compilationContent.equals("CORRECTION_NOTICE_TEMPLATE_EDIT")||compilationContent.equals("SMALL_CORRECTION_PAPER")){
	    	defectFormList = defectFormDao.getDefectFormListByProcessInstId(processInstId, "zljcjlb");
	    }else{
	    	defectFormList = defectFormDao.getDefectFormListByProcessInstId(processInstId, ChartStatusConstants.QUALITY_LIKE);
	    }
	    if(defectFormList != null){
	    	if(defectFormList.size()>0){
	    		return defectFormList;
	    	}
	    }
		return null;
	}
	
	/**
	 * 获取审定质量评分
	 * */
	public List<DefectForm> getQualityScore(String compilationContent,String processInstId){
		List<DefectForm> defectFormList = new ArrayList<DefectForm>();
		if(compilationContent.equals("CORRECTION_NOTICE_SOURCE_DATA_SMALL_CORRECTION")){
	    	defectFormList = defectFormDao.getDefectFormListByProcessInstId(processInstId, "sdjilub");
	    }else if(compilationContent.equals("CORRECTION_NOTICE_TEMPLATE")){
	    	defectFormList = defectFormDao.getDefectFormListByProcessInstId(processInstId, "zxzljcjl");
	    }else if(compilationContent.equals("CORRECTION_NOTICE_TEMPLATE_EDIT")){
	    	defectFormList = defectFormDao.getDefectFormListByProcessInstId(processInstId, "zxzljcjlb");
	    }else if(compilationContent.equals("SMALL_CORRECTION_PAPER")){
	    	defectFormList = defectFormDao.getDefectFormListByProcessInstId(processInstId, "sdjlb");
	    }else{
	    	defectFormList = defectFormDao.getDefectFormListByProcessInstId(processInstId, ChartStatusConstants.APPROVE_LIKE);
	    }
	    if(defectFormList != null){
	    	if(defectFormList.size()>0){
	    		return defectFormList;
	    	}
	    }
		return null;
		
	}
	
	/**
	 * 获取操作时间
	 * @param processDefKey 流程定义Key
	 * @param processInstId 流程实例ID
	 * @param taskDefId 任务定义ID
	 * @return
	 */
	private Date getOperationTime(String processDefKey,String operationType,String processInstId){
		Map<String,String> map = BusinessUtil.getInstance().getProcessOperationTimes(processDefKey, operationType);
		if (map != null)
		{
			String taskDefId = map.get("taskDefId");
			String perform = map.get("perform");
			List<HiTask> hiTasks = taskDao.getHiTaskByProcessInstIdAndTaskDefId(processInstId, taskDefId);
			if (hiTasks != null)
			{
				if (hiTasks.size()>0)
				{
					for (int i = 0; i < hiTasks.size(); i++)
					{
						HiTask hiTask = hiTasks.get(i);
						if (perform.equals("true"))
						{
							return hiTask.getEndTime();
						}
						else{
							return hiTask.getStartTime();
						}
					}
				}
			}
		}
		return null;
	}
	
	/**
	 * 计划流程是否走完
	 * @return
	 */
	@Override
	public Boolean validQuality(String processInstId,String parentProcessInstId){
		List<HiTask> list = taskDao.getHiTaskNotInProcessInstId(processInstId, parentProcessInstId);
		if (list != null)
		{
			if (list.size()>0)
			{
				return true;
			}
		}
		return false;
	}
	
	/** 用户委托业务   */
	
	@Resource
	ProcessDelegateDao processDelegateDao;
	
	@Override
	public List<Map<String,Object>> getDelegateTask(String loginNo) throws Exception{
		try
		{
			String userId = getUserId(loginNo);
			// 获取所属组
			List<Group> groups = service.getGroupsByUser(userId);
			// 定义当前用户分配的委托人集合
			List<String> userIds = new ArrayList<String>();
			if (groups != null)
			{
				for (int i = 0; i < groups.size(); i++)
				{
					// 获取当前用户分配的委托人
					List<ProcessDelegate> delegateList = processDelegateDao.getListByGroupId(groups.get(i).getId());
					if (delegateList != null)
					{
						for (int j = 0; j < delegateList.size(); j++)
						{
							userIds.add(delegateList.get(j).getUserId());
						}
					}
				}
			}
			List<RuTask> list = taskDao.getRuTaskByAssigneeList(userIds);
			List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
			if (list != null)
			{
				for (int i = 0; i < list.size(); i++)
				{
					// 业务记录ID
					VProcessDetail detail = getDetailRecordId(list.get(i).getProcessDefKey(), list.get(i).getParentProcessInstId(), list.get(i).getProcessInstId());
					if (detail != null)
					{
						Map<String,Object> map = setRuTaskInfo(list.get(i),detail);
						setDrawTaskInfo(map,detail.getDetailRecordId());
						result.add(map);
					}
				}
			}
			return result;
		}
		catch (Exception e)
		{
			throw new CommonException("查询出现错误："+e.getMessage());
		}
	}
	
	/**
	 * 设置待办任务信息
	 * @param hiTask
	 * @return
	 * @throws Exception
	 */
	private Map<String, Object> setRuTaskInfo(RuTask ruTask,VProcessDetail detail) throws Exception
	{
		Map<String, Object> map = new HashMap<String, Object>();
		this.setProcessTask(ruTask.getProcessDefId(), ruTask.getProcessDefName(), ruTask.getTaskDefId(), ruTask.getProcessInstId(),
				ruTask.getTaskInstId(), ruTask.getTaskName(), ruTask.getCreateTime(), null, map);
		if (detail != null)
		{
			String recordId = detail.getDetailRecordId();
			if (StringUtils.isNotEmpty(recordId))
			{
				map = setHiTaskRecordProps(map, ProcessTypeEnum.valueOf(ruTask.getProcessDefKey()), recordId);
				map.put("endTime", ruTask.getEndTime());
				map.put("group", ruTask.getGroupName());
				map.put("performer", ruTask.getAssigneeName());
				map.put("excutionId", ruTask.getExcutionId());
				map.put("parentProcessInstId", ruTask.getParentProcessInstId());
			}
		}
		return map;
	}
	
	@Override
	public List<ProcessTypeCount> getRuTaskCountGroupByKey(String userId) throws Exception
	{
		List<Group> groups = service.getGroupsByUser(userId);
		String groupId = "";
		if(groups != null){
			if(groups.size()>0){
				for (int i = 0; i < groups.size(); i++) {
					groupId +=  "'"+groups.get(i).getId()+"',";
				}
				groupId = groupId.substring(0, groupId.length()-1);
			}
		}
		return taskDao.getRuTaskCountGroupByKey(userId, groupId);
	}

	@Override
	public List<Map<String, Object>> getHiTaskGroupByProcessInstId(
			String loginNo, ProcessTypeEnum type) throws Exception {
		try
		{
			String userId = getUserId(loginNo);
			List<Group> groups = service.getGroupsByUser(userId);
			// 首先根据受理人查询历史任务
			List<HiTask> hiTasks = taskDao.getHiTaskGroupByProcessInstId(userId, type.name());
			List<HiTask> hiTasksGroups = new ArrayList<HiTask>();
			if (groups != null)
			{
				for (int i = 0; i < groups.size(); i++)
				{
					Group group = groups.get(i);
					List<HiTask> hiTasksGroup = taskDao.getHiTaskByGroupAndProcessDefKey(group.getId(), type.name());
					if (hiTasksGroup != null)
					{
						hiTasksGroups.addAll(hiTasksGroup);
					}
				}
			}
			Map<String,HiTask> hiTaskMap = new HashMap<String, HiTask>();
			if (hiTasks != null)
			{
				for (int i = 0; i < hiTasks.size(); i++)
				{
					HiTask hiTask = hiTasks.get(i);
					if (!hiTaskMap.containsKey(hiTask.getTaskInstId()))
					{
						hiTaskMap.put(hiTask.getTaskInstId(), hiTask);
					}
				}
			}
			if (hiTasksGroups != null)
			{
				for (int i = 0; i < hiTasksGroups.size(); i++)
				{
					HiTask hiTask = hiTasksGroups.get(i);
					if (!hiTaskMap.containsKey(hiTask.getTaskInstId()))
					{
						hiTaskMap.put(hiTask.getTaskInstId(), hiTask);
					}
				}
			}
			List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
			if (hiTaskMap != null)
			{
				for (Entry<String, HiTask> entry : hiTaskMap.entrySet())
				{
					HiTask hiTask = entry.getValue();
					Map<String, Object> map = setHiTaskInfo(hiTask);
					result.add(map);
				}
			}
			return result;
		}
		catch (Exception e)
		{
			throw new CommonException("请求发生错误:" + e.getMessage());
		}
	
	}
	@Override
	public List<Map<String, Object>> getHiTaskProcessInstId(
			String loginNo, ProcessTypeEnum type,String processInstId) throws Exception {
		try
		{
			String userId = getUserId(loginNo);
			List<Group> groups = service.getGroupsByUser(userId);
			// 首先根据受理人查询历史任务
			List<HiTask> hiTasks = taskDao.getHiTaskByAssigneeAndProcessInstId(userId, type.name(),processInstId);
			List<HiTask> hiTasksGroups = new ArrayList<HiTask>();
			if (groups != null)
			{
				for (int i = 0; i < groups.size(); i++)
				{
					Group group = groups.get(i);
					List<HiTask> hiTasksGroup = taskDao.getHiTaskByGroupAndProcessDefKey(group.getId(), type.name());
					if (hiTasksGroup != null)
					{
						hiTasksGroups.addAll(hiTasksGroup);
					}
				}
			}
			Map<String,HiTask> hiTaskMap = new HashMap<String, HiTask>();
			if (hiTasks != null)
			{
				for (int i = 0; i < hiTasks.size(); i++)
				{
					HiTask hiTask = hiTasks.get(i);
					if (!hiTaskMap.containsKey(hiTask.getTaskInstId()))
					{
						hiTaskMap.put(hiTask.getTaskInstId(), hiTask);
					}
				}
			}
			if (hiTasksGroups != null)
			{
				for (int i = 0; i < hiTasksGroups.size(); i++)
				{
					HiTask hiTask = hiTasksGroups.get(i);
					if (!hiTaskMap.containsKey(hiTask.getTaskInstId()))
					{
						hiTaskMap.put(hiTask.getTaskInstId(), hiTask);
					}
				}
			}
			List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
			if (hiTaskMap != null)
			{
				for (Entry<String, HiTask> entry : hiTaskMap.entrySet())
				{
					HiTask hiTask = entry.getValue();
					Map<String, Object> map = setHiTaskInfo(hiTask);
					result.add(map);
				}
			}
			return result;
		}
		catch (Exception e)
		{
			throw new CommonException("请求发生错误:" + e.getMessage());
		}
		
	}
	/**
	 * 获取流程中的用户是编绘员，质检员，还是审定员。
	 * @return
	 */
	public String getUserType(String userId){
		String relust="";
		RoleUsers roleUsers = new RoleUsers();
		roleUsers.setUserId(userId);
		List<RoleUsers> roleList = roleUsersDao.getRoleUsersByUserId(roleUsers);
		//判断是否是编绘员
		List<String> jurisList=new ArrayList<String>(); 
		jurisList.add("11031906117740106");
		jurisList.add("11031906577250107");
		jurisList.add("110319071450108");
		jurisList.add("11031907312230109");
		jurisList.add("11031908142520110");
		jurisList.add("11031908299430111");
		jurisList.add("11031909081440112");
		jurisList.add("11031909343750113");
		jurisList.add("11031910116200114");
		jurisList.add("11031910485480115");
		if(roleList.size()>0){
			for (RoleUsers role : roleList) {
				if(jurisList.contains(role.getRoleId())){
					relust="user_bhy";
					break;
				}
			}
		}
		//判断是否为质检员
		jurisList=new ArrayList<String>(); 
		jurisList.add("11031911238610116");
		jurisList.add("11031911426730117");
		if(roleList.size()>0){
			for (RoleUsers role : roleList) {
				if(jurisList.contains(role.getRoleId())){
					relust="user_zjy";
					break;
				}
			}
		}
		//判断是否为审定员
		jurisList=new ArrayList<String>(); 
		jurisList.add("11031904283690103");
		jurisList.add("11031904444950104");
		if(roleList.size()>0){
			for (RoleUsers role : roleList) {
				if(jurisList.contains(role.getRoleId())){
					relust="user_sdy";
					break;
				}
			}
		}
		
		return relust;
	}
}
