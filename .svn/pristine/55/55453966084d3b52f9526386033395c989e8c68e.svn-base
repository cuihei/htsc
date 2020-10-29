package com.ht.action.index;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import com.ht.action.base.BaseAction;
import com.ht.common.util.DataConverter;
import com.ht.common.util.DzyComparator;
import com.ht.common.util.LoginUtil;
import com.ht.common.util.Tree;
import com.ht.common.util.TreeNode;
import com.ht.common.util.Utilmd5;
import com.ht.exception.DBException;
import com.ht.listener.SessionListener;
import com.ht.persistence.model.background.authority.role.Role;
import com.ht.persistence.model.background.authority.role.RoleApplicationRel;
import com.ht.persistence.model.background.authority.role.RoleUsers;
import com.ht.persistence.model.background.organization.employee.User;
import com.ht.service.inter.background.authority.auth.RoleAppService;
import com.ht.service.inter.background.authority.role.RoleService;
import com.ht.service.inter.background.authority.role.RoleUsersService;
import com.ht.service.inter.background.organization.employee.UserService;
import com.ht.service.inter.system.notice.UserNoticeViewService;

/**
 * 首页外部访问操作类
 * @author 王有为
 * @date 2016/10/12
 */
public class IndexAction extends BaseAction
{

	@Resource
	UserService userService;

	/**
	 * 注入roleUsersService
	 */
	@Resource(name = "roleUsersService")
	RoleUsersService roleUsersService;

	/**
	 * 注入roleAppService
	 */
	@Resource(name = "roleAppService")
	RoleAppService roleAppService;

	/**
	 * 注入roleService
	 */
	@Resource(name = "roleService")
	RoleService roleService;
	
	/**
	 * 注入人员通知关系viewService
	 */
	@Resource(name = "usernoticeviewService")
	UserNoticeViewService usernoticeviewService;

	/**
	 * 类身份认证
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 返回到首页，动态生成菜单栏
	 * @return
	 * @throws Exception
	 */
	public String init() throws Exception
	{
		String userNo = LoginUtil.getInstance().getLoginNo(request);

		request.setAttribute("userNo", userNo);

		List<User> users = null;
		try
		{
			users = userService.getUserByNo(userNo);
		}
		catch (DBException e)
		{
			return ERROR;
		}
		if (users != null)
		{
			if (users.size() > 0)
			{
				request.setAttribute("userName", users.get(0).getUserName());
				String password = users.get(0).getPassword();
				password = Utilmd5.New().convertMD5(password);
				request.setAttribute("password", password);
				//得到当前登录人的所有未知通知条数
				// 获取系统通知
				int count1 = usernoticeviewService.getUserNoticeCountByUserId(users.get(0).getId(), "否", "10301416596850001");
				// 获取其他通知
				int count2 = usernoticeviewService.getUserNoticeCountByUserId(users.get(0).getId(), "否", "10301417286570002");
				request.setAttribute("count", count1+count2);
			}
		}
		

		// 根据当前登录人获取拥有的角色列表
		String userId = users.get(0).getId();
		List<RoleUsers> roleList = roleUsersService.getRoleUsersByUserId(userId);
		
		
		
		if (roleList != null)
		{
			if (roleList.size() > 0)
			{
				String roleId = roleList.get(0).getRoleId();
				Role r = roleService.getRole(roleId);
				request.setAttribute("roleId",roleId);
				
				
				if (r != null)
				{
					request.setAttribute("roleName", r.getRoleName());
				}
				else
				{
					request.setAttribute("roleName", "");
				}
			}

		}
		// if (LoginUtil.getInstance().getUserApplication(userNo) == null)
		// {
		// 资源列表集合
		List<RoleApplicationRel> navResult = new ArrayList<RoleApplicationRel>();
		Map<String, RoleApplicationRel> mapNav = new HashMap<String, RoleApplicationRel>();
		String writeStr = "";
		// 循环角色列表获取对应的资源
		if (roleList != null)
		{
			if (roleList.size() > 0)
			{
				for (int i = 0; i < roleList.size(); i++)
				{
					String roleId = roleList.get(i).getRoleId();
					// 通过角色id获取拥有的资源列表
					List<RoleApplicationRel> appList = roleAppService.getRoleAppByRoleId(roleId);
					// 得到最终需要的角色拥有的资源集合
					for (int j = 0; j < appList.size(); j++)
					{
						// 排除根目录
						if (!appList.get(j).getAppCode().equals("root"))
						{
							// 排除重复的资源id
							if (!mapNav.containsKey(appList.get(j).getId()))
							{
								mapNav.put(appList.get(j).getId(), appList.get(j));
							}
						}
					}
				}
			}
		}
		// 将map转成list
		Iterator<Entry<String, RoleApplicationRel>> iterator = mapNav.entrySet().iterator();
		while (iterator.hasNext())
		{
			Entry<String, RoleApplicationRel> entry = iterator.next();
			navResult.add(entry.getValue());
		}
		// 进行排序
		Collections.sort(navResult, new DzyComparator());
		// 返回的树列表，包含资源id,资源名称，资源url以及资源父节点
		Tree tree = new Tree();
		for (int i = 0; i < navResult.size(); i++)
		{
			String url = "*";
			if (navResult.get(i).getAppUrl() != null)
			{
				url = navResult.get(i).getAppUrl();
				if (url.indexOf("?") > 0)
				{
					url += "&write=" + navResult.get(i).getWrite();
				}
				else
				{
					url += "?write=" + navResult.get(i).getWrite();
				}
			}
			String appImg = "*";
			if (navResult.get(i).getAppImg() != null)
			{
				appImg = navResult.get(i).getAppImg();
			}
			TreeNode node = new TreeNode(navResult.get(i).getId(), navResult.get(i).getAppName() + "," + url + "," + appImg, navResult.get(i)
					.getAppParentId());
			tree.addNode(node);
		}
		request.setAttribute("navResult", tree);
		LoginUtil.getInstance().resetUserApplication(userNo, tree);

		// }
		// else
		// {
		// request.setAttribute("navResult", LoginUtil.getInstance().getUserApplication(userNo));
		// }
		return SUCCESS;
	}

	/**
	 * 返回到登录页
	 * @return
	 */
	public String loginInit()
	{
		return SUCCESS;
	}
	
	/**
	 * 返回到登录页
	 * @return
	 */
	public String loginOut()
	{
		//清除cookie
		LoginUtil.getInstance().clearLoginNo(request, respose);
		HttpSession session = request.getSession();
		SessionListener.delSession(session);
		@SuppressWarnings("rawtypes")
		Enumeration e = session.getAttributeNames();  
        while(e.hasMoreElements()){  
            String sessionName = (String) e.nextElement();  
            session.removeAttribute(sessionName);  
        }  
        session.invalidate(); 
		return SUCCESS;
	}

	/**
	 * 登录验证
	 */
	public void login(){
		HttpSession session = request.getSession();
		try{
			String userNo = getParam("userNo");
			String password = getParam("password");
			Utilmd5 util = Utilmd5.New();
			password = util.convertMD5(password);
			List<User> user = userService.getUserByNo(userNo);
			if (user == null){
				writeFailResult("用户不存在！");
			}
			else{
				if (user.size() > 0){
					Date date = new Date();
					long time = date.getTime();
					User u = user.get(0);
					Long lastErrorTime = u.getLastErrorTime();
					if (lastErrorTime!=null) {
						long interval = time - lastErrorTime.longValue();
						//超过30分钟
						if (interval>=1800000) {
							u.setErrorCount(0);
							String userJson = DataConverter.convertObject2Json(u);
							userService.modifyUser(userJson);
							u = userService.getUserByNo(userNo).get(0);
						}
					}
					if (u.getErrorCount()>=5) {
						writeFailResult("该账号已被锁定！请30分钟后再尝试登陆.");
					}else{
						if (!u.getPassword().equals(password))
						{
							u.setErrorCount(u.getErrorCount()+1);
							u.setLastErrorTime(time);
							if (u.getErrorCount()>=5){
								String userJson = DataConverter.convertObject2Json(u);
								userService.modifyUser(userJson);
								writeFailResult("密码错误,当前已错误5次,账号被锁定！");
							}else{
								String userJson = DataConverter.convertObject2Json(u);
								userService.modifyUser(userJson);
								writeFailResult("密码错误,错误5次账号将被锁定！当前已错误"+u.getErrorCount()+"次");
							}
						}
						else{
							if (SessionListener.sessionMap.size() >= 100) {
								writeFailResult("当前登录人数已满,请稍后尝试！");
							}else{
								//该账号已经被登陆 
								/*if(null != SessionListener.sessionMap.get(u.getUserNo())){  
									writeFailResult("该账号已经在别处登录！");
								}  
								//该账号未被登陆 
								else{  
									
								}  */
								LoginUtil.getInstance().keepLoginNo(respose, userNo);
								SessionListener.sessionMap.put(u.getUserNo(), session);  
								u.setErrorCount(0);
								String userJson = DataConverter.convertObject2Json(u);
								userService.modifyUser(userJson);
								session.setAttribute("users", u);
								writeSuccessResult();
							}
						}
					}
				}
				else{
					writeFailResult("用户不存在！");
				}
			}
		}
		catch (Exception e){
			writeFailResult("系统错误,原因为：" + e.getMessage());
		}
	}

	/**
	 * 修改密码
	 * @return
	 */
	public void passwordEdit()
	{
		String userParam = getParam("user");
		User user = (User) DataConverter.convertJson2Object(userParam, User.class);
		user.setUserNo(LoginUtil.getInstance().getLoginNo(request));
		try
		{
			List<User> userList = userService.getUserByNo(user.getUserNo());
			String[] password = user.getPassword().split(",");
			Utilmd5 util = Utilmd5.New();
			String oldPassword = util.convertMD5(password[0]);
			if (userList.size() > 0)
			{
				if (!userList.get(0).getPassword().equals(oldPassword))
				{
					writeFailResult("原密码错误！");
				}
				else if (password[0].equals(password[1]))
				{
					writeFailResult("新旧密码一致，无需更改！");
				}
				else
				{
					userService.modifyPassword(userList.get(0).getId(), password[1]);
					writeSuccessResult("修改成功");
				}
			}
		}
		catch (Exception e)
		{
			writeFailResult("系统错误,原因为：" + e.getMessage());
		}
	}
}
