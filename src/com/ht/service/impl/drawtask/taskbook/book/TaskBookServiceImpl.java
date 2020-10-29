package com.ht.service.impl.drawtask.taskbook.book;

import com.ht.common.exception.CommonException;
import com.ht.common.util.DataConverter;
import com.ht.common.util.FileUtil;
import com.ht.common.util.GenerateSequenceUtil;
import com.ht.common.util.LogHelper;
import com.ht.exception.DBException;
import com.ht.persistence.dao.inter.drawtask.taskbill.TaskBillDao;
import com.ht.persistence.dao.inter.drawtask.taskbook.book.TaskBookDao;
import com.ht.persistence.dao.inter.drawtask.taskbook.book.TaskBookEditHisDao;
import com.ht.persistence.dao.inter.drawtask.taskbook.history.HistoryTaskBookDao;
import com.ht.persistence.dao.inter.drawtask.taskbook.relation.TaskBookPlanRelationDao;
import com.ht.persistence.dao.inter.statisticalanalysis.CompilationMonthPlanDao;
import com.ht.persistence.dao.inter.statisticalanalysis.DynamicTableDao;
import com.ht.persistence.dao.inter.system.workflow.process.ProcessFlowDao;
import com.ht.persistence.dao.inter.system.workflow.process.ProcessLogDao;
import com.ht.persistence.dao.inter.system.workflow.process.ProcessLogDetailDao;
import com.ht.persistence.dao.inter.system.workflow.task.TaskDao;
import com.ht.persistence.model.background.dicdata.basedata.BaseData;
import com.ht.persistence.model.drawtask.taskbook.book.TaskBook;
import com.ht.persistence.model.drawtask.taskbook.book.TaskBookEditHis;
import com.ht.persistence.model.drawtask.taskbook.book.TaskBookList;
import com.ht.persistence.model.drawtask.taskbook.book.VTaskBook;
import com.ht.persistence.model.drawtask.taskbook.history.HistoryTaskBook;
import com.ht.persistence.model.drawtask.taskbook.relation.TaskBookPlanRelation;
import com.ht.persistence.model.system.workflow.process.ProcessFlow;
import com.ht.persistence.model.system.workflow.process.ProcessLog;
import com.ht.persistence.model.system.workflow.process.ProcessLogDetail;
import com.ht.service.inter.drawtask.plan.PlanService;
import com.ht.service.inter.drawtask.taskbook.book.TaskBookService;
import com.ht.workflow.service.IWorkflowService;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.apache.commons.lang3.StringUtils;

public class TaskBookServiceImpl  implements TaskBookService
{
	@Resource(name = "taskBookDao")
	private TaskBookDao taskBookDao;

	@Resource(name = "historyTaskBookDao")
	private HistoryTaskBookDao historyTaskBookDao;
	
	@Resource(name = "taskBookEditHisDao")
	private TaskBookEditHisDao taskBookEditHisDao;


	@Resource(name = "taskBookPlanRelationDao")
	private TaskBookPlanRelationDao taskBookPlanRelationDao;

	@Resource(name = "compilationMonthPlanDao")
	CompilationMonthPlanDao compilationMonthPlanDao;

	@Resource
	ProcessFlowDao processFlowDao;

	@Resource
	ProcessLogDao processLogDao;
	
	@Resource
	ProcessLogDetailDao processLogDetailDao;

	@Resource(name = "dynamicTableDao")
	DynamicTableDao dynamicTableDao;

	@Resource(name = "planService")
	private PlanService planService;

	@Override
	public TaskBook findById(String id) throws Exception
	{
		return taskBookDao.findById(id);
	}

	@Override
	public TaskBookList findBookListByid(String id) throws Exception
	{
		return taskBookDao.findBookListByid(id);
	}

	@Override
	public List<TaskBook> findList() throws Exception
	{
		return taskBookDao.findList();
	}

	@Override
	public List<TaskBookList> findListIncludeTypeName(String year) throws Exception
	{
		return taskBookDao.findListIncludeTypeName(year);
	}

	@Override
	public List<TaskBookList> findListByYearAndState(String year,String state) throws Exception
	{
		return taskBookDao.findListByYearAndState(year,state);
	}
	@Override
	public List<VTaskBook> findCreateTaskListByYearAndState(String year,String state) throws Exception
	{
		return taskBookDao.findCreateTaskListByYearAndState(year,state);
	}
	
	@Override
	public List<TaskBookList> findListCorrectionNotice() throws Exception
	{
		return taskBookDao.findListCorrectionNotice();
	}

	@Override
	public List<TaskBook> findFiveList() throws Exception
	{
		return taskBookDao.findFiveList();
	}

	@Override
	public long count() throws Exception
	{
		return 0;
	}

	@Override
	public boolean exists(String mapNo) throws Exception
	{
		return taskBookDao.exists(mapNo);
	}

	@Override
	public void addTaskBook(TaskBook TaskBook) throws Exception
	{
		taskBookDao.addTaskBook(TaskBook);
	}


  
	@Override
	public void modifyTaskBook(TaskBook taskBook, String[] planIds,String[] revisions) throws Exception
	{
		try
		{
			if (StringUtils.isBlank(taskBook.getId()))
			{
				if (planIds != null)
				{
					taskBook.setId(GenerateSequenceUtil.generateSequenceNo());
					taskBook.setState("创建");
					/* String revision = taskBook.getRevision(); if (revision == null || revision == "") { revision = "0"; } Integer intHao =
					 * Integer.parseInt(revision); intHao++; DecimalFormat df = new DecimalFormat("000"); String version = df.format(intHao);
					 * taskBook.setRevision(version); */
					// 执行添加操作
					taskBookDao.addTaskBook(taskBook);
					/** 添加关联关系 **/
					for (int i = 0 ; i < planIds.length;i++)
					{
						TaskBookPlanRelation tbpr = new TaskBookPlanRelation();
						tbpr.setTaskbookId(taskBook.getId());
						tbpr.setId(GenerateSequenceUtil.generateSequenceNo());
						tbpr.setPlanId(planIds[i]);
						tbpr.setRevision(revisions[i]);
						Calendar ca = Calendar.getInstance();
						Integer y = ca.get(Calendar.YEAR);
						Integer m = ca.get(Calendar.MONTH);
						m = m+1;
						String year = y.toString();
						String month = m.toString();
						tbpr.setYear(year);
						tbpr.setMonth(month);
						taskBookPlanRelationDao.addRelation(tbpr);
					}
				}
				else
				{
					throw new CommonException("任务不能为空");
				}
			}
			else
			{
				/** 将任务计划书变成历史计划书 **/
				TaskBook oldTaskBook = taskBookDao.findById(taskBook.getId());
				if("已下发".equals(oldTaskBook.getState())){
					HistoryTaskBook historyTaskBook = new HistoryTaskBook();
					historyTaskBook.setId(GenerateSequenceUtil.generateSequenceNo());
					historyTaskBook.setTaskbookId(oldTaskBook.getId());
					historyTaskBook.setTaskbookName(oldTaskBook.getTaskbookName());
					historyTaskBook.setEnclosure(oldTaskBook.getEnclosure());
					historyTaskBook.setTaskName(oldTaskBook.getTaskName());
					historyTaskBook.setTaskFrom(oldTaskBook.getTaskFrom());
					historyTaskBook.setExecuteDeptId(oldTaskBook.getExecuteDeptId());
					historyTaskBook.setExecuteTime(oldTaskBook.getExecuteTime());
					historyTaskBook.setTechnologyDemand(oldTaskBook.getTechnologyDemand());
					historyTaskBook.setTechnologyStandard(oldTaskBook.getTechnologyStandard());
					historyTaskBook.setOtherDemand(oldTaskBook.getOtherDemand());
					historyTaskBook.setTaskbookNo(oldTaskBook.getTaskbookNo());
					historyTaskBook.setRevision(oldTaskBook.getRevision());
					historyTaskBook.setAuthenticity(oldTaskBook.getAuthenticity());
					historyTaskBook.setTaskBookType(oldTaskBook.getTaskBookType());
					historyTaskBook.setProjectName(oldTaskBook.getProjectName());
					historyTaskBook.setNoticeNo(oldTaskBook.getNoticeNo());
					historyTaskBook.setCorrectNo(oldTaskBook.getCorrectNo());
					historyTaskBook.setCorrectNoticeNo(oldTaskBook.getCorrectNoticeNo());
					historyTaskBook.setItemName(oldTaskBook.getItemName());
					historyTaskBook.setIssueTime(oldTaskBook.getIssueTime());
					historyTaskBookDao.addHistoryTaskBook(historyTaskBook);
					/** 将原数据，的id改为历史记录id **/
					List<TaskBookPlanRelation> oldRelationList = taskBookPlanRelationDao.findListByBookId(taskBook.getId());
					for (TaskBookPlanRelation relation : oldRelationList)
					{
						relation.setTaskbookId(historyTaskBook.getId());
						taskBookPlanRelationDao.updateRelation(relation);
					}
				}
				/** 删除原数据 **/
				taskBookDao.delTaskBook(oldTaskBook);
				//taskBookPlanRelationDao.delRelationByBookId(taskBook.getId());
				/** 新增数据 **/
				/* String revision = oldTaskBook.getRevision(); if (revision == null || revision == "") { revision = "0"; } Integer intHao =
				 * Integer.parseInt(revision); intHao++; DecimalFormat df = new DecimalFormat("000"); String version = df.format(intHao);
				 * taskBook.setRevision(version); */
				// 执行添加操作
				taskBook.setState(oldTaskBook.getState());
				taskBook.setEnclosure(oldTaskBook.getEnclosure());
				if("已下发".equals(oldTaskBook.getState())){
					taskBook.setIssueTime(new Date());
				}
				taskBookDao.addTaskBook(taskBook);
				for (int i = 0 ; i < planIds.length;i++)
				{
					TaskBookPlanRelation tbpr = new TaskBookPlanRelation();
					tbpr.setTaskbookId(taskBook.getId());
					tbpr.setId(GenerateSequenceUtil.generateSequenceNo());
					tbpr.setPlanId(planIds[i]);
					tbpr.setRevision(revisions[i]);
					Calendar ca = Calendar.getInstance();
					Integer y = ca.get(Calendar.YEAR);
					Integer m = ca.get(Calendar.MONTH);
					m = m+1;
					String year = y.toString();
					String month = m.toString();
					tbpr.setYear(year);
					tbpr.setMonth(month);
					taskBookPlanRelationDao.addRelation(tbpr);
				}
			}
		}
		catch (Exception e)
		{
			LogHelper.ERROR.log(e.getMessage(), e);
			throw new CommonException(e.getMessage());
		}
	}
  
  
  
  
  

	@Override
	public void modifyTaskBook(TaskBook taskBook, String[] planIds, String[] revisions, String processInstId, String taskId, String userNo)
			throws Exception
	{
		try
		{
			if (StringUtils.isBlank(taskBook.getId()))
			{
				taskBook.setId(GenerateSequenceUtil.generateSequenceNo());
				// 执行添加操作
				taskBookDao.addTaskBook(taskBook);
				// 如果计划为空那么要增加一条空计划拆分数据
				if (planIds != null)
				{
					/** 添加关联关系 **/
					for (int i = 0; i < planIds.length; i++)
					{
						TaskBookPlanRelation tbpr = new TaskBookPlanRelation();
						tbpr.setTaskbookId(taskBook.getId());
						tbpr.setId(GenerateSequenceUtil.generateSequenceNo());
						tbpr.setPlanId(planIds[i]);
						tbpr.setRevision(revisions[i]);
						Calendar ca = Calendar.getInstance();
						Integer y = ca.get(Calendar.YEAR);
						Integer m = ca.get(Calendar.MONTH);
						m = m+1;
						String year = y.toString();
						String month = m.toString();
						tbpr.setYear(year);
						tbpr.setMonth(month);
						taskBookPlanRelationDao.addRelation(tbpr);
					}
				}
				else
				{
					TaskBookPlanRelation tbpr = new TaskBookPlanRelation();
					tbpr.setTaskbookId(taskBook.getId());
					tbpr.setId(GenerateSequenceUtil.generateSequenceNo());
					Calendar ca = Calendar.getInstance();
					Integer y = ca.get(Calendar.YEAR);
					Integer m = ca.get(Calendar.MONTH);
					m = m+1;
					String year = y.toString();
					String month = m.toString();
					tbpr.setYear(year);
					tbpr.setMonth(month);
					taskBookPlanRelationDao.addRelation(tbpr);
				}
				setProcess(processInstId, taskId, userNo, taskBook.getId());
			}
			else
			{
				/** 将任务计划书变成历史计划书 **/
				TaskBook oldTaskBook = taskBookDao.findById(taskBook.getId());
				HistoryTaskBook historyTaskBook = new HistoryTaskBook();
				historyTaskBook.setId(GenerateSequenceUtil.generateSequenceNo());
				historyTaskBook.setTaskbookId(oldTaskBook.getId());
				historyTaskBook.setTaskbookName(oldTaskBook.getTaskbookName());
				historyTaskBook.setEnclosure(oldTaskBook.getEnclosure());
				historyTaskBook.setTaskName(oldTaskBook.getTaskName());
				historyTaskBook.setTaskFrom(oldTaskBook.getTaskFrom());
				historyTaskBook.setExecuteDeptId(oldTaskBook.getExecuteDeptId());
				historyTaskBook.setExecuteTime(oldTaskBook.getExecuteTime());
				historyTaskBook.setTechnologyDemand(oldTaskBook.getTechnologyDemand());
				historyTaskBook.setTechnologyStandard(oldTaskBook.getTechnologyStandard());
				historyTaskBook.setOtherDemand(oldTaskBook.getOtherDemand());
				historyTaskBook.setTaskbookNo(oldTaskBook.getTaskbookNo());
				historyTaskBook.setRevision(oldTaskBook.getRevision());
				historyTaskBook.setAuthenticity(oldTaskBook.getAuthenticity());
				historyTaskBook.setTaskBookType(oldTaskBook.getTaskBookType());
				historyTaskBook.setProjectName(oldTaskBook.getProjectName());
				historyTaskBook.setNoticeNo(oldTaskBook.getNoticeNo());
				historyTaskBook.setCorrectNo(oldTaskBook.getCorrectNo());
				historyTaskBook.setCorrectNoticeNo(oldTaskBook.getCorrectNoticeNo());
				historyTaskBook.setItemName(oldTaskBook.getItemName());
				historyTaskBookDao.addHistoryTaskBook(historyTaskBook);
				/** 删除原数据 **/
				taskBookDao.delTaskBook(oldTaskBook);
				/** 将原数据，的id改为历史记录id **/
				List<TaskBookPlanRelation> oldRelationList = taskBookPlanRelationDao.findListByBookId(taskBook.getId());
				for (TaskBookPlanRelation relation : oldRelationList)
				{
					relation.setTaskbookId(historyTaskBook.getId());
					taskBookPlanRelationDao.updateRelation(relation);
				}
				/** 新增数据 **/
				taskBook.setTaskBookType(oldTaskBook.getTaskBookType());
				taskBook.setEnclosure(oldTaskBook.getEnclosure());
				// 执行添加操作
				taskBookDao.addTaskBook(taskBook);
				/** 添加关联关系 **/
				if (planIds != null)
				{
					for (int i = 0; i < planIds.length; i++)
					{
						TaskBookPlanRelation tbpr = new TaskBookPlanRelation();
						tbpr.setTaskbookId(taskBook.getId());
						tbpr.setId(GenerateSequenceUtil.generateSequenceNo());
						tbpr.setPlanId(planIds[i]);
						tbpr.setRevision(revisions[i]);
						Calendar ca = Calendar.getInstance();
						Integer y = ca.get(Calendar.YEAR);
						Integer m = ca.get(Calendar.MONTH);
						m = m+1;
						String year = y.toString();
						String month = m.toString();
						tbpr.setYear(year);
						tbpr.setMonth(month);
						taskBookPlanRelationDao.addRelation(tbpr);
					}
				}
				else
				{
					TaskBookPlanRelation tbpr = new TaskBookPlanRelation();
					tbpr.setTaskbookId(taskBook.getId());
					tbpr.setId(GenerateSequenceUtil.generateSequenceNo());
					Calendar ca = Calendar.getInstance();
					Integer y = ca.get(Calendar.YEAR);
					Integer m = ca.get(Calendar.MONTH);
					m = m+1;
					String year = y.toString();
					String month = m.toString();
					tbpr.setYear(year);
					tbpr.setMonth(month);
					taskBookPlanRelationDao.addRelation(tbpr);
				}
				setProcess(processInstId, taskId, userNo, taskBook.getId());
			}
		}
		catch (Exception e)
		{
			LogHelper.ERROR.log(e.getMessage(), e);
			throw new DBException("数据库操作错误：" + e.getMessage());
		}
	}
  
  
	@Override
	public void modifyCNTaskBook(TaskBook taskBook,String userNo) throws Exception
	{
//		try
//		{
//			// 定义流程变量
//			Map<String,Object> v = new HashMap<String, Object>();
//			// 为流程设置用户变量
//			String flowId = service.getProcessDefinitionIdByKey(ProcessTypeEnum.TASK_BOOK_CORRECTION_NOTICE.name()).getId();
//			List<UserTask> userTasks = service.getUserTasks(flowId);
//			for (int i = 0; i < userTasks.size(); i++) {
//				v.put(userTasks.get(i).getId(), null);
//			}
//			String startResult = service.start(ProcessTypeEnum.TASK_BOOK_CORRECTION_NOTICE.name(),userNo, DataConverter.convertObject2Json(v));
//			WsResult<Map<String, String>> wsResult = new WsResult<Map<String, String>>();
//			wsResult = (WsResult<Map<String, String>>) DataConverter.convertJson2Object(startResult, wsResult.getClass());
//			String success = wsResult.getResult();
//			Map processData = wsResult.getData();
//			// 如果流程启动成功则进行业务操作
//			if (success.equals("success")) {
//				String processInstId = (String) processData.get("processInstanceId");
//				RuTask ruTask = new RuTask();
//				ruTask.setProcessInstId(processInstId);
//				List<RuTask>  ruList = taskDao.getRuTaskByProcessInstId(ruTask);
//				String taskId = null;
//				if (ruList != null)
//				{
//					if (ruList.size()>0)
//					{
//						for (int i = 0; i < ruList.size(); i++)
//						{
//							taskId = ruList.get(i).getTaskInstId();
//							String processDefId = ruList.get(i).getProcessDefId();
//							Map<String, Object> dataMap = new HashMap<String, Object>();
//							// 为流程设置用户变量
//							List<UserTask> userTasks1 = service.getUserTasks(processDefId);
//							for (int j = 0; j < userTasks1.size(); j++)
//							{
//								Object arg = service.getProcessArgs(processInstId, userTasks1.get(i).getId());
//								if (arg == null)
//								{
//									dataMap.put(userTasks1.get(i).getId(), null);
//								}
//							}
//							dataMap.put("agree", true);
//							service.perform(taskId, DataConverter.convertObject2Json(dataMap));
//							break;
//						}
//					}
//				}
//				// 创建新的改正通告任务书
//				// 当前年度有改正通告任务书，当前年度最新期号任务书
//				// 当前通告期号
//				String correctNoticeNoLast = taskBook.getNoticeNo();
//				String correctNoticeNo = null;
//				if (StringUtils.isNotEmpty(correctNoticeNoLast))
//				{
//					Integer noticeNo = Integer.valueOf(correctNoticeNoLast) + 1;
//					try
//					{
//						correctNoticeNo = noticeNo.toString();
//					}
//					catch (Exception e)
//					{
//						correctNoticeNo = "1";
//					}
//				}
//				else
//				{
//					correctNoticeNo = "1";
//				}
//				String taskBookNewId = GenerateSequenceUtil.generateSequenceNo();
//				TaskBook taskBookNew = new TaskBook();
//				taskBookNew.setAuthenticity(taskBook.getAuthenticity());
//				taskBookNew.setCorrectItemNo(taskBook.getCorrectItemNo());
//				taskBookNew.setCorrectNo(taskBook.getCorrectNo());
//				taskBookNew.setCorrectNoticeNo(taskBook.getCorrectItemNo());
//				taskBookNew.setEnclosure(taskBook.getEnclosure());
//				taskBookNew.setEndTime(taskBook.getEndTime());
//				taskBookNew.setExecuteDeptId(taskBook.getExecuteDeptId());
//				taskBookNew.setExecuteTime(taskBook.getExecuteTime());
//				taskBookNew.setItemName(taskBook.getItemName());
//				taskBookNew.setNoticeNo(correctNoticeNo);
//				taskBookNew.setOtherDemand(taskBook.getOtherDemand());
//				taskBookNew.setProjectName(taskBook.getProjectName());
//				taskBookNew.setRevision(taskBook.getRevision());
//				taskBookNew.setTaskbookName(taskBook.getTaskbookName());
//				taskBookNew.setTaskbookNo(taskBook.getTaskbookNo());
//				taskBookNew.setTaskBookType(taskBook.getTaskBookType());
//				taskBookNew.setTaskFrom(taskBook.getTaskFrom());
//				taskBookNew.setTaskName(taskBook.getTaskName());
//				taskBookNew.setTechnologyDemand(taskBook.getTechnologyDemand());
//				taskBookNew.setTechnologyStandard(taskBook.getTechnologyStandard());
//				taskBookNew.setId(taskBookNewId);
//				// 执行添加操作
//				taskBookDao.addTaskBook(taskBookNew);
//				//
//				TaskBill taskBill = new TaskBill();
//				taskBill.setTaskBookId(taskBook.getId());
//				List<TaskBill> billList = taskBillDao.findListByTaskBookId(taskBill);
//				/** 添加关联关系 **/
//				if (billList != null)
//				{
//					if (billList.size() > 0)
//					{
//						for (int i = 0; i < billList.size(); i++)
//						{
//							Plan plan = billList.get(i).getPlan();
//							if (plan != null)
//							{
//								TaskBookPlanRelation tbpr = new TaskBookPlanRelation();
//								tbpr.setTaskbookId(taskBookNewId);
//								tbpr.setId(GenerateSequenceUtil.generateSequenceNo());
//								tbpr.setPlanId(plan.getId());
//								tbpr.setRevision(billList.get(i).getRevision());
//								taskBookPlanRelationDao.addRelation(tbpr);
//							}
//						}
//					}
//				}
//				else
//				{
//					TaskBookPlanRelation tbpr = new TaskBookPlanRelation();
//					tbpr.setTaskbookId(taskBookNewId);
//					tbpr.setId(GenerateSequenceUtil.generateSequenceNo());
//					taskBookPlanRelationDao.addRelation(tbpr);
//				}
//				setProcess(processInstId, taskId, userNo, taskBookNewId);
//			}
//		}
//		catch (Exception e)
//		{
//			LogHelper.ERROR.log(e.getMessage(), e);
//			throw new DBException("数据库操作错误：" + e.getMessage());
//		}
	}
  
	
	
	
	
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public void delTaskBook(String id) throws Exception
	{
		List<TaskBook> taskBookList = (List<TaskBook>) DataConverter.convertJson2List(id, TaskBook.class);
		if(null!=taskBookList&&taskBookList.size()>0){
			for (int i = 0; i < taskBookList.size(); i++) {
			TaskBook taskbook = taskBookList.get(i);
			//删除历史任务书
			List<HistoryTaskBook> historyList = historyTaskBookDao.findListByTaskBookId(taskbook.getId());
			if(null!=historyList&&historyList.size()>0){
				for (HistoryTaskBook historyTaskBook : historyList) {
					historyTaskBookDao.delete(historyTaskBook);
				}
			}
			//删除关联计划
			List<TaskBookPlanRelation> planList = taskBookPlanRelationDao.findListByBookId(taskbook.getId());
			if(null!=planList&&planList.size()>0){
				taskBookPlanRelationDao.delRelationByBookId(id);
			}
				taskBookDao.delTaskBook(taskbook);
			}
		}
		
	}

	@Override
	public void delTaskBook(TaskBook TaskBook) throws Exception
	{
		taskBookDao.delTaskBook(TaskBook);
	}

	@Override
	public List<TaskBook> countByNo(String noName) throws Exception
	{
		return taskBookDao.countByNo(noName);
	}
  
	/**
	 * 上传文件
	 * @throws Exception
	 */
	@Override
	public void uploadFile(File upload, String uploadFileName, String id) throws Exception
	{
		// 获取项目在服务器的路径
		String serverPath = FileUtil.ROOT_PATH;
		// 新建一个路径，在最后以当前年月日新建一个文件夹
		String path = "\\upload\\taskbook\\";
		// 创建文件夹
		FileUtil.CreateFolder(serverPath + path);
		InputStream is = new FileInputStream(upload);
		OutputStream os = new FileOutputStream(new File(serverPath + path, uploadFileName));

		byte[] buffer = new byte[500];
		int length = 0;

		while (-1 != (length = is.read(buffer, 0, buffer.length)))
		{
			os.write(buffer);
		}

		os.close();
		is.close();
		TaskBook taskbook = taskBookDao.findById(id);
		taskbook.setEnclosure(path  + uploadFileName);
		taskBookDao.modifyTaskBook(taskbook);
	}

	@Override
	public List<BaseData> getTechnologyDemand() throws Exception
	{
		return taskBookDao.getTechnologyDemand();
	}

	@Override
	public List<TaskBook> findByType(String taskBookType) throws Exception
	{
		return taskBookDao.findByType(taskBookType);
	}

	@Override
	public List<BaseData> getOtherDemand() throws Exception
	{
		return taskBookDao.getOtherDemand();
	}

	@Override
	public List<BaseData> getTechnologyStandard() throws Exception
	{
		return taskBookDao.getTechnologyStandard();
	}

	private void setProcess(String processInstId, String taskId, String userNo, String recordId) throws Exception
	{
		if (StringUtils.isNoneEmpty(processInstId))
		{
			String taskResult = "任务书编辑";
			String type = "任务";
			ProcessFlow processFlow = new ProcessFlow();
			processFlow.setCreator(userNo);
			processFlow.setProcessInstId(processInstId);
			processFlow.setTaskResult(taskResult);
			processFlow.setType(type);
			processFlow.setId(GenerateSequenceUtil.generateSequenceNo());
			processFlow.setModifyDate(new Date());
			processFlow.setModifier(userNo);
			processFlow.setUserNo(userNo);
			processFlow.setTaskId(taskId);
			processFlowDao.addProcessFlow(processFlow);
			// 更新流程日志
			ProcessLog processLog = new ProcessLog();
			String logId = GenerateSequenceUtil.generateSequenceNo();
			processLog.setId(logId);
			processLog.setProcessInstId(processInstId);
			processLog.setTableName("COMPILATION_TASK_BOOK");
			processLog.setTableKeyName("id");
			processLog.setUserNo(userNo);
			processLogDao.addProcessLog(processLog);
			ProcessLogDetail processLogDetail = new ProcessLogDetail();
			processLogDetail.setDetailRecordId(recordId);
			processLogDetail.setId(GenerateSequenceUtil.generateSequenceNo());
			processLogDetail.setProcessInstId(processInstId);
			processLogDetail.setProcessLogId(logId);
			processLogDetail.setTableName("COMPILATION_TASK_BOOK");
			processLogDetailDao.addProcessLogDetail(processLogDetail);
		}
	}
  
	/**
	 * 获取版次内容
	 */
	@Override
	public List<BaseData> getVersion() throws Exception
	{
		return taskBookDao.getVersion();
	}

	@Override
	public List<TaskBook> findListCorrectionNoticeByYear(String year) throws Exception
	{
		return taskBookDao.findListCorrectionNoticeByYear(year);
	}
  
	/**
	 * 下发
	 */
	@Override
	public void issue(String ids) throws Exception
	{	
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		@SuppressWarnings("unchecked")
		List<TaskBook> tbs =(List<TaskBook>)DataConverter.convertJson2List(ids, TaskBook.class);
		for(TaskBook tb:tbs){
			TaskBook temp = taskBookDao.findById(tb.getId());
			temp.setState("已下发");
			Date issueTime = new Date();
			temp.setIssueTime(issueTime);
			String other = temp.getOtherDemand();
			other+="\n 任务书下发时间："+sdf.format(issueTime);
			temp.setOtherDemand(other);
			taskBookDao.issue(temp);
		}
	}
	@Override
	public List<TaskBookList> findList(String year,boolean jurisdiction,String booktype) throws Exception
	{
		return taskBookDao.findList(year,jurisdiction,booktype);
	}
	/**
	 * 上传文件
	 * @throws Exception
	 */
	@Override
	public void removeFile(String id,String url) throws Exception
	{
		// 获取项目在服务器的路径
		String serverPath = FileUtil.ROOT_PATH;
		File file = new File(serverPath+url);
		if(file.exists()){
			file.delete();
		}
		TaskBook t =taskBookDao.findById(id);
		t.setEnclosure("");
		taskBookDao.modifyTaskBook(t);
	}

	/**
	 * 获取修订记录列表
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<TaskBookEditHis> findEditListById(String taskbookId) throws Exception {
		return taskBookEditHisDao.findEditListById(taskbookId);
	}
	
	/**
	 * 获取修订记录条数
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@Override
	public int editCount(String taskbookId) throws Exception {
		return taskBookEditHisDao.editcount(taskbookId);
	}
	
	
	/**
	 * 获取修订记录列表
	 * @param id
	 * @return 
	 * @return 
	 * @return
	 * @throws Exception
	 */
	
	@Override
	public  String addTaskBookEdit(String taskbookid, String editcount, String creatro,String taskbookNo,String nowtime) {

		 taskBookEditHisDao.addTaskBookEdit(taskbookid,editcount,creatro,taskbookNo,nowtime);
		 return "1";
	}

	/**
	 * 获取任务书编号的最大值
	 * @param id
	 * @return
	 * @throws Exception
	 */
	
	@Override
	public String findTaskselect(String taskselect) throws Exception {
		return taskBookDao.findTaskselect(taskselect);
	}
}
