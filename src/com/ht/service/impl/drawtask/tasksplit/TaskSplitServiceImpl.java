package com.ht.service.impl.drawtask.tasksplit;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.activiti.engine.identity.User;
import org.apache.commons.lang3.StringUtils;

import com.ht.common.exception.CommonException;
import com.ht.common.util.DataConverter;
import com.ht.common.util.GenerateSequenceUtil;
import com.ht.common.util.LogHelper;
import com.ht.exception.DBException;
import com.ht.persistence.dao.inter.drawtask.tasksplit.TaskSplitDao;
import com.ht.persistence.dao.inter.system.notice.NoticeDao;
import com.ht.persistence.dao.inter.system.notice.UserNoticeDao;
import com.ht.persistence.dao.inter.system.workflow.process.ProcessFlowDao;
import com.ht.persistence.dao.inter.system.workflow.process.ProcessLogDao;
import com.ht.persistence.dao.inter.system.workflow.process.ProcessLogDetailDao;
import com.ht.persistence.dao.inter.system.workflow.publish.VProcessDetailDao;
import com.ht.persistence.dao.inter.system.workflow.task.TaskDao;
import com.ht.persistence.model.drawtask.taskbill.TaskBill;
import com.ht.persistence.model.drawtask.tasksplit.TaskSplit;
import com.ht.persistence.model.system.notice.Notice;
import com.ht.persistence.model.system.notice.UserNotice;
import com.ht.persistence.model.system.workflow.process.ProcessFlow;
import com.ht.persistence.model.system.workflow.process.ProcessLog;
import com.ht.persistence.model.system.workflow.process.ProcessLogDetail;
import com.ht.persistence.model.system.workflow.task.RuTask;
import com.ht.service.impl.system.workflow.task.ProcessTypeEnum;
import com.ht.service.impl.system.workflow.util.BusinessUtil;
import com.ht.service.inter.background.organization.employee.UserService;
import com.ht.service.inter.drawtask.tasksplit.TaskSplitService;
import com.ht.workflow.service.IWorkflowService;

public class TaskSplitServiceImpl implements TaskSplitService
{

	@Resource
	private TaskSplitDao taskSplitDao;

	@Resource
	VProcessDetailDao vProcessDetailDao;

	@Resource
	ProcessFlowDao processFlowDao;

	@Resource
	ProcessLogDao processLogDao;

	@Resource
	ProcessLogDetailDao processLogDetailDao;

	@Resource
	IWorkflowService service;

	/**
	 * 拆分
	 * @throws Exception
	 */
	@Override
	public void doSplit(String split, String processInstId, String userNo, String taskId) throws Exception
	{
		try
		{
			// 任务拆分对象
			Map<String, String> taskSplit = (Map<String, String>) DataConverter.convertJson2Object(split, Map.class);
			// 任务类型
			String[] type = taskSplit.get("taskType").split(",");
			// 任务类型值
			String[] value = taskSplit.get("typeValue").split(",");
			// 定义拆分主键集合
			Map<String, String> splitMap = new HashMap<String, String>();
			// 循环拆分类型进行拆分
			for (int i = 0; i < type.length; i++)
			{
				TaskSplit task = new TaskSplit();
				TaskBill taskBill = new TaskBill();
				task.setTaskType(type[i]);
				taskBill.setId(taskSplit.get("taskBookPlanId"));
				task.setTaskBill(taskBill);
				boolean result = ifExist(task);
				if (result)
				{
					DBException e = new DBException("此任务的：" + value[i] + "拆分类型已经存在，不能重复拆分！");
					throw e;
				}
				// 拆分主键
				String id = GenerateSequenceUtil.generateSequenceNo();
				task.setId(id);
				taskSplitDao.doSplit(task);
				String key = type[i];
				splitMap.put(key, id);
				// 如果是源数据 纸海图 电子海图 则分为计划和作业 计划的key值用作业的key
				if (ProcessTypeEnum.SEA_MAP_COMPILATION_SOURCE_DATA.toString().equals(key))
				{
					key = ProcessTypeEnum.SEA_MAP_COMPILATION_SOURCE_DATA_PLAN.toString();
					splitMap.put(key, id);
				}
				if (ProcessTypeEnum.SEA_MAP_COMPILATION_ELECTRONIC.toString().equals(key))
				{
					key = ProcessTypeEnum.SEA_MAP_COMPILATION_ELECTRONIC_PLAN.toString();
					splitMap.put(key, id);
				}
				if (ProcessTypeEnum.SEA_MAP_COMPILATION_PAPER.toString().equals(key))
				{
					key = ProcessTypeEnum.SEA_MAP_COMPILATION_PAPER_PLAN.toString();
					splitMap.put(key, id);
				}
			}
			// 提交任务到子流程
			setProcessLog(processInstId, taskId, "COMPILATION_TASK_BOOK", taskSplit.get("taskBookId"), userNo, splitMap);
		}
		catch (Exception e)
		{
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(), e);
			// 抛出异常
			throw e;
		}
	}

	@Override
	public void doSplit(String split) throws Exception
	{
		try
		{
			TaskSplit taskSplit = (TaskSplit) DataConverter.convertJson2Object(split, TaskSplit.class);
			String[] type = taskSplit.getTaskType().split(",");
			String[] value = taskSplit.getTypeValue().split(",");
			for (int i = 0; i < type.length; i++)
			{
				TaskSplit task = new TaskSplit();
				TaskBill taskBill = new TaskBill();
				task.setTaskType(type[i]);
				taskBill.setId(taskSplit.getTaskBookPlanId());
				task.setTaskBill(taskBill);
				boolean result = ifExist(task);
				if (result)
				{
					DBException e = new DBException("此任务的：" + value[i] + "拆分类型已经存在，不能重复拆分！");
					throw e;
				}
				String id = GenerateSequenceUtil.generateSequenceNo();
				task.setId(id);
				taskSplitDao.doSplit(task);
			}
		}
		catch (Exception e)
		{
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(), e);
			// 抛出异常
			throw e;
		}
	}

	/**
	 * 改正通告拆分
	 * @throws Exception
	 */
	@Override
	public void corNoticeSplit(String split) throws Exception
	{
		TaskSplit taskSplit = (TaskSplit) DataConverter.convertJson2Object(split, TaskSplit.class);
		taskSplit.setTaskType("CORRECTION_NOTICE_TEMPLATE");
		boolean result = ifExist(taskSplit);
		if (result) { throw new CommonException("当前任务已经拆分无需再次拆分！"); }
		String[] type =
		{ "CORRECTION_NOTICE_TEMPLATE", "CORRECTION_NOTICE_TEMPLATE_EDIT", "CORRECTION_NOTICE_SOURCE_DATA_SMALL_CORRECTION" };
		// 定义拆分主键集合
		for (String i : type)
		{
			TaskSplit task = new TaskSplit();
			TaskBill taskBill = new TaskBill();
			task.setTaskType(i);
			taskBill.setId(taskSplit.getTaskBookPlanId());
			task.setTaskBill(taskBill);
			String id = GenerateSequenceUtil.generateSequenceNo();
			task.setId(id);
			taskSplitDao.doSplit(task);
		}
	}

	/**
	 * 拆分校验
	 * @throws Exception
	 */
	public Boolean ifExist(TaskSplit taskSplit) throws Exception
	{
		boolean result;
		try
		{
			result = taskSplitDao.ifExist(taskSplit);
		}
		catch (Exception e)
		{
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(), e);
			// 抛出异常
			throw e;
		}
		return result;
	}

	/**
	 * 编绘管理各子查询
	 * @return 所有实体对象集合
	 */
	@Override
	public List<TaskSplit> findCompilationList(String flag) throws Exception
	{
		try
		{
			TaskSplit taskSplit = new TaskSplit();
			taskSplit.setFlag(flag);
			List<TaskSplit> list = taskSplitDao.findCompilationList(taskSplit);
			return list;
		}
		catch (Exception e)
		{
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(), e);
			// 抛出异常
			throw e;
		}
	}
	
	/**
	 * 编绘管理各子查询
	 * @return 所有实体对象集合
	 */
	@Override
	public TaskSplit findCompilationListById(String id) throws Exception
	{
		try
		{
			TaskSplit taskSplit = new TaskSplit();
			taskSplit.setId(id);
			TaskSplit split = taskSplitDao.findCompilationListById(taskSplit);
			return split;
		}
		catch (Exception e)
		{
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(), e);
			// 抛出异常
			throw e;
		}
	}

	/**
	 * 编绘管理各子查询
	 * @return 所有实体对象集合
	 */
	@Override
	public List<TaskSplit> findCompilationListByTaskBookType(String taskBookType) throws Exception
	{
		try
		{
			TaskSplit taskSplit = new TaskSplit();
			TaskBill bill = new TaskBill();
			bill.setFlag(taskBookType);
			taskSplit.setTaskBill(bill);
			List<TaskSplit> list = taskSplitDao.findCompilationListByTaskBookType(taskSplit);
			return list;
		}
		catch (Exception e)
		{
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(), e);
			// 抛出异常
			throw e;
		}
	}

	/**
	 * 编绘管理各子查询
	 * @return 所有实体对象集合
	 */
	@Override
	public List<TaskSplit> findCompilationListByTaskBookIdAndType(String taskBookType, String taskBookId) throws Exception
	{
		try
		{
			TaskSplit taskSplit = new TaskSplit();
			TaskBill bill = new TaskBill();
			bill.setFlag(taskBookType);
			bill.setTaskBookId(taskBookId);
			taskSplit.setTaskBill(bill);
			List<TaskSplit> list = taskSplitDao.findCompilationListByTaskBookIdAndType(taskSplit);
			return list;
		}
		catch (Exception e)
		{
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(), e);
			// 抛出异常
			throw e;
		}
	}

	@Resource
	TaskDao taskDao;

	@Resource
	UserService userService;

	@Resource
	NoticeDao noticeDao;

	@Resource
	UserNoticeDao usernoticeDao;

	private void setProcessLog(String processInstId, String taskId, String tableName, String recordId, String userNo, Map<String, String> splitMap)
			throws Exception
	{
		Boolean agree = true;
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("agree", agree);
		// 子流程来源参数（来自主流程
		dataMap.put("index0", false);
		dataMap.put("index1", false);
		dataMap.put("index2", false);
		dataMap.put("index3", false);
		dataMap.put("index4", false);
		dataMap.put("index5", false);
		for (String dataKey : splitMap.keySet())
		{
			String index = BusinessUtil.getInstance().getIndexByProcess(dataKey);
			dataMap.put("index" + index, true);
		}
		service.perform(taskId, DataConverter.convertObject2Json(dataMap));
		// 提交任务后进行业务操作
		String taskResult = "拆分成功";
		String type = "编绘任务拆分";
		ProcessFlow processFlow = new ProcessFlow();
		processFlow.setUserNo(userNo);
		processFlow.setProcessInstId(processInstId);
		processFlow.setTaskId(taskId);
		processFlow.setId(GenerateSequenceUtil.generateSequenceNo());
		processFlow.setTaskResult(taskResult);
		processFlow.setType(type);
		processFlow.setCreator(userNo);
		processFlow.setCreationDate(new Date());
		processFlow.setModifyDate(new Date());
		processFlow.setModifier(userNo);
		processFlowDao.addProcessFlow(processFlow);
		// 进行流程日志的操作
		RuTask paramRuTask = new RuTask();
		// 根据当前流程ID获取子流程的列表
		paramRuTask.setParentProcessInstId(processInstId.trim());
		List<RuTask> ruTaskList = taskDao.getRuTaskByParentProcessInstId(paramRuTask);
		if (ruTaskList != null)
		{
			if (ruTaskList.size() > 0)
			{
				for (int i = 0; i < ruTaskList.size(); i++)
				{
					RuTask ruTask = ruTaskList.get(i);
					// 取得用户
					String assignee = ruTask.getAssignee();
					if (StringUtils.isNotEmpty(assignee))
					{
						com.ht.persistence.model.background.organization.employee.User user = userService.getUser(assignee);
						ProcessLog log = new ProcessLog();
						String logId = GenerateSequenceUtil.generateSequenceNo();
						log.setId(logId);
						log.setOperationType("操作");
						log.setProcessInstId(ruTask.getProcessInstId());
						log.setTableKeyName("id");
						log.setTableName(BusinessUtil.getInstance().getTableNameByProcess(ruTask.getProcessDefId().split(":")[0]));
						log.setTableStatusName("status");
						log.setUserNo(user.getUserNo());
						processLogDao.addProcessLog(log);
						ProcessLogDetail processLogDetail = new ProcessLogDetail();
						processLogDetail.setId(GenerateSequenceUtil.generateSequenceNo());
						processLogDetail.setDetailRecordId(splitMap.get(ruTask.getProcessDefId().split(":")[0]));
						processLogDetail.setProcessInstId(ruTask.getProcessInstId());
						processLogDetail.setProcessLogId(logId);
						processLogDetail.setTableName(BusinessUtil.getInstance().getTableNameByProcess(ruTask.getProcessDefId().split(":")[0]));
						processLogDetailDao.addProcessLogDetail(processLogDetail);
						// 发送通知
						Notice notice = new Notice();
						notice.setNotice_Type("10301416596850001");
						notice.setTitle("系统通知");
						// new日期对象
						Date date = new Date();
						// 转换提日期输出格式
						SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						notice.setDescription("您有一条" + ruTask.getProcessDefName() + "的任务，发布时间为：" + dateFormat.format(date));
						String noticeId = GenerateSequenceUtil.generateSequenceNo();
						notice.setId(noticeId);
						noticeDao.addNotice(notice);
						UserNotice usernotice = new UserNotice();
						usernotice.setId(GenerateSequenceUtil.generateSequenceNo());
						usernotice.setNotice_id(noticeId);
						usernotice.setUser_id(assignee);
						usernotice.setIs_read("否");
						// 设置发布时间
						usernotice.setRelease_time(dateFormat.format(date));
						usernoticeDao.addUserNotice(usernotice);
					}
					else
					{
						// 根据组取得用户
						String groupId = ruTask.getGroupId();
						if (StringUtils.isNotEmpty(groupId))
						{
							List<User> userList = service.getUsersByGroup(groupId);
							for (int j = 0; j < userList.size(); j++)
							{
								com.ht.persistence.model.background.organization.employee.User user = userService.getUser(userList.get(j).getId());
								ProcessLog log = new ProcessLog();
								String logId = GenerateSequenceUtil.generateSequenceNo();
								log.setId(logId);
								log.setOperationType("操作");
								log.setProcessInstId(ruTask.getProcessInstId());
								log.setTableKeyName("id");
								log.setTableName(BusinessUtil.getInstance().getTableNameByProcess(ruTask.getProcessDefId().split(":")[0]));
								log.setTableStatusName("status");
								log.setUserNo(user.getUserNo());
								processLogDao.addProcessLog(log);
								ProcessLogDetail processLogDetail = new ProcessLogDetail();
								processLogDetail.setId(GenerateSequenceUtil.generateSequenceNo());
								processLogDetail.setDetailRecordId(splitMap.get(ruTask.getProcessDefId().split(":")[0]));
								processLogDetail.setProcessInstId(ruTask.getProcessInstId());
								processLogDetail.setProcessLogId(logId);
								processLogDetail.setTableName(BusinessUtil.getInstance()
										.getTableNameByProcess(ruTask.getProcessDefId().split(":")[0]));
								processLogDetailDao.addProcessLogDetail(processLogDetail);
								// 发送通知
								Notice notice = new Notice();
								notice.setNotice_Type("10301416596850001");
								notice.setTitle("系统通知");
								// new日期对象
								Date date = new Date();
								// 转换提日期输出格式
								SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
								notice.setDescription("您有一条" + ruTask.getProcessDefName() + "的任务，发布时间为：" + dateFormat.format(date));
								String noticeId = GenerateSequenceUtil.generateSequenceNo();
								notice.setId(noticeId);
								noticeDao.addNotice(notice);
								UserNotice usernotice = new UserNotice();
								usernotice.setId(GenerateSequenceUtil.generateSequenceNo());
								usernotice.setNotice_id(noticeId);
								usernotice.setUser_id(userList.get(j).getId());
								usernotice.setIs_read("否");
								// 设置发布时间
								usernotice.setRelease_time(dateFormat.format(date));
								usernoticeDao.addUserNotice(usernotice);
							}
						}
						else
						{
							ProcessLog log = new ProcessLog();
							String logId = GenerateSequenceUtil.generateSequenceNo();
							log.setId(logId);
							log.setOperationType("操作");
							log.setProcessInstId(ruTask.getProcessInstId());
							log.setTableKeyName("id");
							log.setTableName(BusinessUtil.getInstance().getTableNameByProcess(ruTask.getProcessDefId().split(":")[0]));
							log.setTableStatusName("status");
							processLogDao.addProcessLog(log);
							ProcessLogDetail processLogDetail = new ProcessLogDetail();
							processLogDetail.setId(GenerateSequenceUtil.generateSequenceNo());
							processLogDetail.setDetailRecordId(splitMap.get(ruTask.getProcessDefId().split(":")[0]));
							processLogDetail.setProcessInstId(ruTask.getProcessInstId());
							processLogDetail.setProcessLogId(logId);
							processLogDetail.setTableName(BusinessUtil.getInstance().getTableNameByProcess(ruTask.getProcessDefId().split(":")[0]));
							processLogDetailDao.addProcessLogDetail(processLogDetail);
						}
					}
				}
			}
		}
	}
}