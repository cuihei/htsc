package com.ht.action.drawtask.taskbook.create;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.ht.action.base.BaseAction;
import com.ht.common.export.ExcelUtil;
import com.ht.common.util.LogHelper;
import com.ht.common.util.LoginUtil;
import com.ht.front.pages.drawtask.taskbook.CreateTaskPage;
import com.ht.persistence.model.background.authority.role.RoleUsers;
import com.ht.persistence.model.background.dicdata.basedata.BaseData;
import com.ht.persistence.model.background.organization.employee.User;
import com.ht.persistence.model.drawtask.taskbook.book.TaskBookList;
import com.ht.persistence.model.drawtask.taskbook.book.VTaskBook;
import com.ht.persistence.model.drawtask.taskbook.create.CreateTask;
import com.ht.persistence.model.drawtask.taskbook.create.ViewCreateTask;
import com.ht.service.impl.background.dicdata.constants.BaseDataConstants;
import com.ht.service.impl.system.workflow.util.BusinessUtil;
import com.ht.service.inter.background.authority.role.RoleUsersService;
import com.ht.service.inter.background.dicdata.basedata.BaseDataService;
import com.ht.service.inter.background.organization.employee.UserService;
import com.ht.service.inter.drawtask.taskbook.book.TaskBookService;
import com.ht.service.inter.drawtask.taskbook.create.CreateTaskService;

/**
 * 编绘任务Action类
 * @author PeNgHaO
 */

@SuppressWarnings("serial")
public class CreateTaskAction extends BaseAction
{

	@Resource
	private CreateTaskService createTaskService;

	@Resource
	private TaskBookService taskBookService;

	@Resource
	private BaseDataService baseDataService;
	
	/**
	 * 注入用户Service类
	 */
	@Resource(name = "userService")
	private UserService userService;
	
	/**
	 * 注入RoleUserService类
	 */
	@Resource(name = "roleUsersService")
	private RoleUsersService roleUsersService;
	
	// 必须的参数，参数名与表单名相同
	private File upload;
	// 必须的参数，格式：表单名+FileName，表示上传的文件名
	private String uploadFileName;
	// 必须的参数，格式：表单名+ContentType，表示上传文件类型
	private String uploadContentType;

	public File getUpload()
	{
		return upload;
	}

	public void setUpload(File upload)
	{
		this.upload = upload;
	}

	public String getUploadFileName()
	{
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName)
	{
		this.uploadFileName = uploadFileName;
	}

	public String getUploadContentType()
	{
		return uploadContentType;
	}

	public void setUploadContentType(String uploadContentType)
	{
		this.uploadContentType = uploadContentType;
	}

	/**
	 * 编绘任务首页
	 * @return
	 */
	public String index() throws Exception
	{
		CreateTaskPage ctp = new CreateTaskPage();
		String userNo = LoginUtil.getInstance().getLoginNo(request);
		List<User> users = userService.getUserByNo(userNo);
		String userId = null;
		if (users != null)
		{
			userId = users.get(0).getId();
		}
		// 根据当前登录人获取拥有的角色列表
		List<RoleUsers> roleList = roleUsersService.getRoleUsersByUserId(userId);
		List<String> jurisList=new ArrayList<String>(); 
		jurisList.add("11031905468290105");
		boolean jurisdiction = this.getUserJurisdiction(roleList, jurisList);
		// 将获取的节点字符串返回到前台页面
		request.setAttribute("html", ctp.getListNode(jurisdiction));
		return SUCCESS;
	}

	/**
	 * 获取任务书列表
	 */
	public void getTaskList()
	{
		String year = getParam("year");
		if (year == null)
		{
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String date = format.format(new Date());
			year = date.substring(0, 4);
		}
		try
		{
			List<VTaskBook> booklist = taskBookService.findCreateTaskListByYearAndState(year, "已下发");
			writeSuccessResult(booklist);
		}
		catch (Exception e)
		{
			// 写入错误日志
			e.printStackTrace();
			LogHelper.ERROR.log(e.getMessage(), e);
			writeFailResult(e.getMessage());
		}
	}

	/**
	 * 编绘任务编辑页面
	 * @return
	 */
	public String initChildTaskCreate() throws Exception
	{
		String id = this.getParam("id");
		String taskBookName = this.getParam("taskBookName");
		taskBookName = new String(taskBookName.getBytes("ISO8859-1"), BaseDataConstants.BASE_CODEING);
		CreateTaskPage ctp = new CreateTaskPage();
		// 将获取的节点字符串返回到前台页面
		request.setAttribute("html", ctp.getChildTaskCreatePage(id, taskBookName,baseDataService));
		return SUCCESS;
	}

	/**
	 * 编绘任务联动
	 * @return
	 */
	public void changeTask() throws Exception
	{
		String taskType = this.getParam("taskType");
		try
		{
			taskType = taskType.replace("TASK_BOOK_", "");
			List<BaseData> list = createTaskService.getChildTaskType(taskType);
			writeSuccessResult(list);
		}
		catch (Exception e)
		{
			// 写入错误日志
			e.printStackTrace();
			LogHelper.ERROR.log(e.getMessage(), e);
			writeFailResult(e.getMessage());
		}
	}

	/**
	 * 新增编绘任务
	 * @return
	 */
	public void add() throws Exception
	{
		String task = this.getParam("task");
		try
		{
			createTaskService.modify(task);
			writeSuccessResult();
		}
		catch (Exception e)
		{
			// 写入错误日志
			e.printStackTrace();
			LogHelper.ERROR.log(e.getMessage(), e);
			writeFailResult(e.getMessage());
		}
	}
	/**
	 * 修改编绘任务
	 * @return
	 */
	public void edit() throws Exception
	{
		String task = this.getParam("task");
		try
		{
			createTaskService.edit(task);
			writeSuccessResult();
		}
		catch (Exception e)
		{
			// 写入错误日志
			e.printStackTrace();
			LogHelper.ERROR.log(e.getMessage(), e);
			writeFailResult(e.getMessage());
		}
	}
	/**
	 * 删除编绘任务
	 * @return
	 */
	public void delete() throws Exception
	{
		String ids = this.getParam("id");
		try
		{
			boolean flag=true;
			String msg=null;
			String[] idArray = ids.split(",");
			for (String id : idArray) {
				String delete = createTaskService.delete(id.replace("\"", ""));
				if(delete!=null){
					flag=true;
					msg=delete;
				}
			}
			if(flag){
				writeSuccessResult();
			}else{
				writeSuccessResult(msg);
			}
			
		}
		catch (Exception e)
		{
			// 写入错误日志
			e.printStackTrace();
			LogHelper.ERROR.log(e.getMessage(), e);
			writeFailResult(e.getMessage());
		}
	}
	/**
	 * 下载模板
	 * @return
	 */
	public void downTemplate() throws Exception
	{
		ExcelUtil<CreateTask> util1 = new ExcelUtil<CreateTask>(CreateTask.class);
		util1.getListToExcel(null, "编绘任务", "编绘任务导入模板");
	}

	/**
	 * 导入
	 */
	public String uploadFile() throws Exception
	{
		try
		{
			String parentTaskId = this.getParam("id");
			String msg = createTaskService.uploadFile(parentTaskId, upload, uploadFileName);
			writeSuccessResult(msg);
		}
		catch (Exception e)
		{
			LogHelper.ERROR.log(e.getMessage());
			return ERROR;
		}
		return null;
	}

	/**
	 * 编绘任务页面
	 * @return
	 */
	public String initChildTask()
	{
		try {
			CreateTaskPage ctp = new CreateTaskPage();
			String userNo = LoginUtil.getInstance().getLoginNo(request);
			List<User> users = userService.getUserByNo(userNo);
			String userId = null;
			if (users != null)
			{
				userId = users.get(0).getId();
			}
			// 根据当前登录人获取拥有的角色列表
			List<RoleUsers> roleList = roleUsersService.getRoleUsersByUserId(userId);
			List<String> jurisList=new ArrayList<String>(); 
			jurisList.add("11031905468290105");
			boolean jurisdiction = this.getUserJurisdiction(roleList, jurisList);
			// 将获取的节点字符串返回到前台页面
			request.setAttribute("html", ctp.getChildListPage(jurisdiction));
			return SUCCESS;
		} catch (Exception e) {
			LogHelper.ERROR.log(e.getMessage());
			return ERROR;
		}
		
	}
	/**
	 * 编绘任务页面
	 * @return
	 * @throws Exception 
	 */
	public String editChildTask() throws Exception
	{
		String id = this.getParam("id");
		CreateTaskPage ctp = new CreateTaskPage();
		// 将获取的节点字符串返回到前台页面
		request.setAttribute("html", ctp.getChildTaskEditPage(id, "", baseDataService,createTaskService));
		return SUCCESS;
	}

	/**
	 * 获取编绘任务列表
	 */
	public void getChildTaskList()
	{
		String parentTaskBookId = getParam("parentTaskBookId");
		try
		{
			List<ViewCreateTask> result = createTaskService.getTaskList(parentTaskBookId);
			for (int i = 0; i < result.size(); i++)
			{
				ViewCreateTask createTask = result.get(i);
				createTask.setChildTaskTypeName(BusinessUtil.getInstance().getDecriptionNameByProcess(createTask.getChildTaskType()));
			}
			writeSuccessResult(result);
		}
		catch (Exception e)
		{
			writeFailResult(e.getMessage());
		}
	}
}
