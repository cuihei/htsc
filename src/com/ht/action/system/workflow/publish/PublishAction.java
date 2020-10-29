package com.ht.action.system.workflow.publish;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;

import com.ht.action.base.BaseAction;
import com.ht.common.util.DataConverter;
import com.ht.common.util.LoginUtil;
import com.ht.persistence.model.background.organization.employee.User;
import com.ht.service.impl.system.workflow.task.ProcessTypeEnum;
import com.ht.service.inter.background.organization.employee.UserService;
import com.ht.service.inter.drawtask.taskbook.book.TaskBookService;
import com.ht.service.inter.system.workflow.publish.PublishService;
import com.ht.service.inter.system.workflow.task.TaskService;

/**
 * 流程发布访问操作类
 * @author 王有为
 * @date 2016/10/30
 */
public class PublishAction extends BaseAction
{

	@Resource
	PublishService publishService;
	
	@Resource
	TaskService taskService;
	
	@Resource
	UserService userService;

	@Resource(name = "taskBookService")
	private TaskBookService taskBookService;

	/**
	 * 发布流程
	 */
	public void publishProcess()
	{
		// 流程key
		String processDefKey = getParam("key");
		// 当前用户
		String LoginUser = LoginUtil.getInstance().getLoginNo(request);
		// 启动流程
		try
		{
			// 发布流程
			publishService.publishProcess(LoginUser, processDefKey);
			// 返回成功结果
			writeSuccessResult();
		}
		catch (Exception e)
		{
			// 返回失败结果
			writeFailResult(e.getMessage());
		}
	}
	
	/**
	 * 发布流程
	 */
	public void publishProblemProcess()
	{
		// 流程key
		String processDefKey = getParam("key");
		String quality = getParam("quality");
		String approval = getParam("approval");
		// 当前用户
		String LoginUser = LoginUtil.getInstance().getLoginNo(request);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		String year = sdf.format(new Date());
		// 启动流程
		try
		{
			// 发布流程
			String processInstId = publishService.publishProblemProcess(LoginUser, processDefKey,quality,approval);
			List<User> user = userService.getUserByNo(LoginUser);
			List<Map<String, Object>> ruTaskList = null;
			if (user.size() > 0){
				ruTaskList = taskService.getRuTask(user.get(0).getId(), LoginUser, ProcessTypeEnum.PROBLEM_SUBMIT,year);
			}
			Map<String, String> map = new HashMap<String, String>();
			if(ruTaskList != null){
				if(ruTaskList.size()>0){
					for (int i = 0; i < ruTaskList.size(); i++)
					{
						Map<String, Object> task = ruTaskList.get(i);
						if (processInstId.equals((String) task.get("processInstId")))
						{
							map.put("processInstId", processInstId);
							map.put("taskInstId", (String) task.get("taskInstId"));
							map.put("processDefId", (String) task.get("processDefId"));
							map.put("taskDefId", (String) task.get("taskDefId"));
						}
					}
				}
			}
			// 返回成功结果
			writeSuccessResult(map);
		}
		catch (Exception e)
		{
			// 返回失败结果
			writeFailResult(e.getMessage());
		}
	}

	/**
	 * 发布改正通告任务书流程
	 */
	public void publishCorrectionNoticeProcess()
	{
		// 当前用户
//		String LoginUser = LoginUtil.getInstance().getLoginNo(request);
//		// 流程key,默认为改正通告任务书类型
//		String processDefKey = ProcessTypeEnum.TASK_BOOK_CORRECTION_NOTICE.name();
//		// 通过年度和key在taskbook表中查出改正通告任务书数据，如果数据库没有数据，则直接发布流程，有则插入相同数据，期号加1
//		Calendar a = Calendar.getInstance();
//		String year = String.valueOf(a.get(Calendar.YEAR));
//		List<TaskBook> booklist = new ArrayList<TaskBook>();
//		try
//		{
//			booklist = taskBookService.findListCorrectionNoticeByYear(year);
//		}
//		catch (Exception e)
//		{
//			writeFailResult(e.getMessage());
//			return;
//		}
//		try
//		{
//			if (booklist != null)
//			{
//				if (booklist.size() > 0)
//				{
//					// 当前年度有改正通告任务书，当前年度最新期号任务书
//					TaskBook taskbook = booklist.get(0);
//					// 新增一条记录，数据相同，改正通告期号自动加一
//					taskBookService.modifyCNTaskBook(taskbook,LoginUser);
//					writeSuccessResult();
//				}
//				else
//				{
//					// 发布流程
//					publishService.publishProcess(LoginUser, processDefKey);
//					// 返回成功结果
//					writeSuccessResult();
//				}
//			}
//		}
//		catch (Exception e)
//		{
//			// 返回成功结果
//			writeFailResult(e.getMessage());
//		}
	}
	
	/**
	 * 发布目录流程
	 */
	public void publishCatalogProcess()
	{
		// 流程key
		String processDefKey ="CATALOG_MANAGEMENT";
		// 获取要提交的目录id
		String ids = getParam("ids");
		// 当前用户
		String LoginUser = LoginUtil.getInstance().getLoginNo(request);
		// 启动流程
		try
		{
			// 发布流程
			publishService.publishCatalogProcess(LoginUser, processDefKey,ids);
			// 返回成功结果
			writeSuccessResult();
		}
		catch (Exception e)
		{
			// 返回失败结果
			writeFailResult(e.getMessage());
		}
	}
	
	
	/**
	 * 发布图书资料流程
	 */
	public void publishBooksProcess()
	{
		// 流程key
		String processDefKey = ProcessTypeEnum.DATA_BORROWING.toString();
		// 获取要提交的图书资料id
		String ids = getParam("ids");
		String borrowNos = getParam("borrowNos");
		String type = getParam("type");// 资料类型
		String secretInvolved = getParam("secretInvolved");
		// 当前用户
		//String LoginUser = LoginUtil.getInstance().getLoginNo(request);
		String LoginUser = getParam("userNo");
		
		// 启动流程
		try
		{
			// 发布流程
			publishService.publishBooksProcess(LoginUser, processDefKey,ids,borrowNos,type,secretInvolved);
			// 返回成功结果
			writeSuccessResult();
		}
		catch (Exception e)
		{
		
			// 返回失败结果
			writeFailResult(e.getMessage());
		}
	}
	
		
	
	/**
	 * 发布图书归还资料流程
	 */
	public void publishReturnBooksProcess()
	{
		// 流程key
		String processDefKey = ProcessTypeEnum.DATA_RETURN.toString();
		// 获取要提交的图书资料id
		String ids = getParam("ids");
		String returnNos = getParam("returnNos");
		// 当前用户
		String LoginUser = LoginUtil.getInstance().getLoginNo(request);
		// 启动流程
		try
		{
			// 发布流程
			publishService.publishReturnBooksProcess(LoginUser, processDefKey,ids,returnNos);
			// 返回成功结果
			writeSuccessResult();
		}
		catch (Exception e)
		{
			// 返回失败结果
			writeFailResult(e.getMessage());
		}
	}
	
	/**
	 * 发布资料录入审核流程
	 */
	public void publishDataInputProcess()
	{
		// 流程key
		String processDefKey ="DATA_INPUT";
		// 获取要提交的目录id
		String ids = getParam("ids");
		// 资料类型
		String bookType = getParam("type");
		// 当前用户
		String LoginUser = LoginUtil.getInstance().getLoginNo(request);
		// 启动流程
		try
		{
			// 发布流程
			publishService.publishDataInputProcess(LoginUser, processDefKey,ids,bookType);
			// 返回成功结果
			writeSuccessResult();
		}
		catch (Exception e)
		{
			// 返回失败结果
			writeFailResult(e.getMessage());
		}
	}
	
	/**
	 * 发布资料录入审核流程
	 */
	public void publishTaskBookProcess()
	{
		// 流程key
		String processDefKey = ProcessTypeEnum.TASK_BOOK.name();
		// 获取要提交的目录id
		String taskBookIds = getParam("taskBookIds");
		// 当前用户
		String LoginUser = LoginUtil.getInstance().getLoginNo(request);
		List<String> taskBookIdList = (List<String>) DataConverter.convertJson2List(taskBookIds, String.class);
		// 启动流程
		try
		{
			// 发布流程
			publishService.publishTaskBookProcess(LoginUser, processDefKey, taskBookIdList);
			// 返回成功结果
			writeSuccessResult();
		}
		catch (Exception e)
		{
			// 返回失败结果
			writeFailResult(e.getMessage());
		}
	}
	
	public void publishTaskProcess()
	{
		// 获取要提交的目录id
		String tasks = getParam("tasks");
		String zj = getParam("zj");
		String sd = getParam("sd");
		// 当前用户
		String LoginUser = LoginUtil.getInstance().getLoginNo(request);
		List<Map<String,String>> taskIdList = (List<Map<String, String>>) DataConverter.convertJson2List(tasks, Map.class);
		// 启动流程
		try
		{	
			if(StringUtils.isEmpty(zj)){
				zj="0";
			}
			if(StringUtils.isEmpty(sd)){
				sd="0";
			}
			// 发布流程
			publishService.publishTaskProcess(LoginUser,taskIdList,zj,sd);
			// 返回成功结果
			writeSuccessResult();
		}
		catch (Exception e)
		{
			// 返回失败结果
			writeFailResult(e.getMessage());
		}
	}
}
