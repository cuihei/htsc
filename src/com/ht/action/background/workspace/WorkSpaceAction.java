package com.ht.action.background.workspace;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.ht.action.base.BaseAction;
import com.ht.common.util.DateComparator;
import com.ht.common.util.LogHelper;
import com.ht.common.util.LoginUtil;
import com.ht.front.pages.background.workspace.SeeMorePage;
import com.ht.front.pages.background.workspace.WorkSpacePage;
import com.ht.persistence.model.background.authority.role.Role;
import com.ht.persistence.model.background.authority.role.RoleUsers;
import com.ht.persistence.model.background.organization.employee.User;
import com.ht.persistence.model.datum.productupdsourcedata.ProductUpdSourceData;
import com.ht.persistence.model.drawtask.taskbook.book.TaskBookList;
import com.ht.persistence.model.system.notice.UserNoticeView;
import com.ht.persistence.model.system.workflow.process.ProcessTypeCount;
import com.ht.service.impl.system.workflow.task.ProcessTypeEnum;
import com.ht.service.inter.background.authority.role.RoleService;
import com.ht.service.inter.background.authority.role.RoleUsersService;
import com.ht.service.inter.background.organization.employee.UserService;
import com.ht.service.inter.datum.productupdsourcedata.ProductUpdSourceDataService;
import com.ht.service.inter.drawtask.taskbook.book.TaskBookService;
import com.ht.service.inter.system.notice.UserNoticeService;
import com.ht.service.inter.system.notice.UserNoticeViewService;
import com.ht.service.inter.system.workflow.task.TaskService;

/**
 * 个人工作台action
 * @author zyd
 */
@SuppressWarnings("serial")
public class WorkSpaceAction extends BaseAction
{

	/**
	 * 注入人员通知关系service
	 */
	@Resource(name = "usernoticeService")
	UserNoticeService usernoticeService;

	/**
	 * 注入人员通知关系viewService
	 */
	@Resource(name = "usernoticeviewService")
	UserNoticeViewService usernoticeviewService;

	/**
	 * 注入人员service
	 */
	@Resource(name = "userService")
	UserService userService;

	/**
	 * 注入流程任务service
	 */
	@Resource(name = "taskService")
	TaskService taskService;
	
	/**
	 * 注入roleUsersService
	 */
	@Resource(name = "roleUsersService")
	RoleUsersService roleUsersService;

	/**
	 * 编绘任务书service
	 */
	@Resource(name = "taskBookService")
	private TaskBookService taskBookService;
	
	/**
	 * 注入产品修改源数据service
	 * */
	@Resource(name="productUpdSourceDataService")
	ProductUpdSourceDataService pusdService;
	
	/**
	 * 注入roleService
	 */
	@Resource(name = "roleService")
	RoleService roleService;

	/**
	 * 初始化个人工作台页面，返回成功列表页面
	 * @return
	 * @throws Exception
	 */

	public String init() throws Exception
	{
		List<TaskBookList> booklist = new ArrayList<TaskBookList>();
		try
		{
			// 获取用户No
			String LoginUser = LoginUtil.getInstance().getLoginNo(request);
			// 获取 用户
			List<User> userList = userService.getUserByNo(LoginUser);
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy");
			// 创建待办任务 Map
			Map<String, ProcessTypeCount> ruListMap = new HashMap<String, ProcessTypeCount>();
			if (userList != null)
			{
				if (userList.size() > 0)
				{
					// 获取用户Id
					String userId = userList.get(0).getId();
					List<ProcessTypeCount> list = taskService.getRuTaskCountGroupByKey(userId);
					if(list!= null&&list.size()>0){
						for (int i = 0; i < list.size(); i++) {
							for (ProcessTypeEnum processType : ProcessTypeEnum.values())
							{
								if(list.get(i).getProcessDefKey().equals(processType.name())){
									ruListMap.put(processType.name(), list.get(i));
								}
								
							}
						}
					}
					// 遍历所有流程类型
					/*for (ProcessTypeEnum processType : ProcessTypeEnum.values())
					{
						List<Map<String, Object>> ruTaskList = taskService.getRuTask(userId, LoginUser, processType);
						ruListMap.put(processType.name(), ruTaskList);
					}*/
					booklist = taskBookService.findListIncludeTypeName(sdf.format(new Date()));
				}
			}
			// 创建个人工作台页面初始化类
			WorkSpacePage ddata = new WorkSpacePage();
			List<Map<String,Object>> taskCompletetion = taskService.getRuTask1();
			if(taskCompletetion.size()>0){
				Collections.sort(taskCompletetion, new DateComparator());
			}
			// 将获取的节点字符串返回给前台页面
			int cnt  = taskService.getRuTaskCnt();
			
			// 获取产品修改源数据的任务数
			List<ProductUpdSourceData> pusdList = new ArrayList<ProductUpdSourceData>();
			// 获取角色
			String role = getRole();
			String status = "";
			
			if(role.equals("bhy")){
				status = "1";
			}else if(role.equals("zjy")){
				status = "2";
			}else if(role.equals("sdy")){
				status = "3";
			}
			// 根据不同状态获取不同数据列表
			pusdList = pusdService.getProductUpdSourceDataByStatus(status,userList.get(0).getUserName());
			
			request.setAttribute("html", ddata.getListNode(booklist, ruListMap,taskCompletetion,cnt,pusdList));
			return SUCCESS;
		}
		catch (Exception e)
		{
			return SUCCESS;
		}
	}
	
	
	
	/**
	 * 将是否已读更新为：是
	 */
	public void modifyRead()
	{
		try
		{
			// 获取用户通知关系Id
			String userNoticeId = getParam("userNoticeId");
			usernoticeService.modifyUserNotice(userNoticeId);
			writeSuccessResult(userNoticeId);
		}
		catch (Exception e)
		{
			writeFailResult(e.getMessage());
		}
	}

	/**
	 * 初始化查看更多通知页面，返回成功列表页面
	 */
	public String sysNoticeSeeMore()
	{
		// 获取通知标识
		String flag = getParam("flag");
		// 实例化页面对象
		SeeMorePage page = SeeMorePage.getInstance();
		// 获取页面节点字符串
		String listnode = page.getListNode(flag);
		// 将获取的节点字符串返回到前台页面
		request.setAttribute("html", listnode);
		return SUCCESS;
	}

	/**
	 * 查看更多获取通知
	 */
	public void getUserNoticeView()
	{
		List<UserNoticeView> sysNoticeList = new ArrayList<UserNoticeView>();
		List<UserNoticeView> otherNoticeList = new ArrayList<UserNoticeView>();
		try
		{
			// 获取用户No
			String LoginUser = LoginUtil.getInstance().getLoginNo(request);
			String read = "";
			// 获取 用户
			List<User> userList = userService.getUserByNo(LoginUser);
			// 获取系统通知标识
			String flag = getParam("flag");
			// 获取系统通知初始化数据
			String isRead = getParam("isRead");
			if (isRead.equals("0"))
			{
				read = "否";
			}
			else if (isRead.equals("1"))
			{
				read = "是";
			}
			if (userList != null)
			{
				if (userList.size() > 0)
				{
					// 获取用户Id
					String userId = userList.get(0).getId();
					// 获取系统通知
					sysNoticeList = usernoticeviewService.getUserNoticeByUserId(userId, read, "10301416596850001");
					// 获取其他通知
					otherNoticeList = usernoticeviewService.getUserNoticeByUserId(userId, read, "10301417286570002");
				}
			}
			if (flag.equals("sysNotice"))
			{
				writeSuccessResult(sysNoticeList);
			}
			else if(flag.equals("otherNotice"))
			{
				writeSuccessResult(otherNoticeList);
			}
			else{
				List<UserNoticeView> result = new ArrayList<UserNoticeView>();
				result.addAll(sysNoticeList);
				result.addAll(otherNoticeList);
				writeSuccessResult(result);
			}
		}
		catch (Exception e)
		{
			// 写入错误日志
			LogHelper.ERROR.log(e.getMessage(), e);
			writeFailResult(e.getMessage());
		}
	}
	
	/**
	 * 获取角色
	 * @return
	 * @throws Exception
	 */
	public String getRole() throws Exception{
		String role = "";
		// 获取当前登录人
		String userNo = LoginUtil.getInstance().getLoginNo(request);
		request.setAttribute("userNo", userNo);
		List<User> users = null;
		// 获取用户
		users = userService.getUserByNo(userNo);
		if(users != null && users.size() > 0){
			String userId = users.get(0).getId();
			List<RoleUsers> roleList = roleUsersService.getRoleUsersByUserId(userId);
			if (roleList != null && roleList.size() > 0){
				String roleId = roleList.get(0).getRoleId();
				Role r = roleService.getRole(roleId);
				if (r != null){
					String roleNo = r.getRoleNo();
					String roleName = r.getRoleName();
					
					String bhyNo = "009,010,011,012,013,014,015,016";
					String bhyName = "编绘一组组长,编绘员一组,编绘二组组长,编绘员二组,编绘三组组长,编绘员三组,编绘四组组长,编绘员四组";
					
					String zjyNo = "019,020";
					String zjyName = "质检组组长,质检员";
					
					String sdyNo = "006,007";
					String sdyName = "审定组组长,审定员";
					
					if(bhyNo.indexOf(roleNo) != -1 || bhyName.indexOf(roleName) != -1){
						role = "bhy";
					}else if(zjyNo.indexOf(roleNo) != -1 || zjyName.indexOf(roleName) != -1){
						role = "zjy";
					}else if(sdyNo.indexOf(roleNo) != -1 || sdyName.indexOf(roleName) != -1 ){
						role = "sdy";
					}
				}
			}
		}
		return role;
	}
}
