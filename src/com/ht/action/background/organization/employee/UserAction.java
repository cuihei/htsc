package com.ht.action.background.organization.employee;

import java.io.File;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.ht.action.base.BaseAction;
import com.ht.common.util.LogHelper;
import com.ht.exception.DBException;
import com.ht.front.pages.background.organization.employee.EmployeePage;
import com.ht.persistence.model.background.authority.role.Role;
import com.ht.persistence.model.background.organization.employee.User;
import com.ht.persistence.model.background.organization.employee.UserOrgView;
import com.ht.persistence.model.background.organization.organization.Organization;
import com.ht.service.impl.background.dicdata.constants.BaseDataConstants;
import com.ht.service.inter.background.authority.role.RoleService;
import com.ht.service.inter.background.authority.role.RoleUsersService;
import com.ht.service.inter.background.organization.employee.UserService;
import com.ht.service.inter.background.organization.organization.OrganizationService;
import com.ht.service.inter.background.organization.organization.OrganizationUsersRelationService;

/**
 * 员工访问操作action
 * @author liukai
 */
@SuppressWarnings("serial")
public class UserAction extends BaseAction {

	/**
	 * 注入用户service
	 * */
	@Resource(name = "userService")
	UserService userService;
	
	/**
	 * 注入组织机构service
	 * */
	@Resource(name = "organizationService")
	OrganizationService orgService;
	
	/**
	 * 注入角色service
	 * */
	@Resource(name = "roleService")
	RoleService roleService;
	
	/**
	 * 注入角色-用户service
	 * */
	@Resource(name = "roleUsersService")
	RoleUsersService ruService;
	
	/**
	 * 注入组织机构-用户service
	 * */
	@Resource(name = "organizationUsersRelationService")
	OrganizationUsersRelationService ourService;
	
	//文件下载
	private InputStream downloadStream;
	//文件下载名称
	private String fileName;

	public InputStream getDownloadStream() {
		return downloadStream;
	}

	public void setDownloadStream(InputStream downloadStream) {
		this.downloadStream = downloadStream;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) throws Exception {
		this.fileName = new String(fileName.getBytes("ISO8859-1"), BaseDataConstants.BASE_CODEING);
	}
	
	//文件上传
	private File myfiles;
	//文件上传名称
	private String myfilesFileName;
	//文件上传类型
	private String myfilesContentType;
	
	public File getMyfiles() {
		return myfiles;
	}

	public void setMyfiles(File myfiles) {
		this.myfiles = myfiles;
	}

	public String getMyfilesFileName() {
		return myfilesFileName;
	}

	public void setMyfilesFileName(String myfilesFileName) {
		this.myfilesFileName = myfilesFileName;
	}

	public String getMyfilesContentType() {
		return myfilesContentType;
	}

	public void setMyfilesContentType(String myfilesContentType) {
		this.myfilesContentType = myfilesContentType;
	}
	
	/**
	 * 初始化人员数据页面，返回成功列表页面
	 * */
	public String init() {
		EmployeePage employee = new EmployeePage();
		//将获取的列表页面返回到前台页面
		request.setAttribute("html", employee.getListPage());
		return SUCCESS;
	}

	/**
	 * 初始化新增和编辑人员数据页面，返回成功页面
	 * @throws Exception 
	 * */
	public String editInit() throws Exception {
		// 接收要修改的人员编号
		String id = getParam("id");
		EmployeePage employee = new EmployeePage();
		// 获取组织所有组织机构
		List<Organization> orgList = new ArrayList<Organization>();
		// 获取所有角色
		List<Role> roleList = new ArrayList<Role>();
		try {
			// 组织机构
			orgList = orgService.getOrganization();
			// 角色
			roleList = roleService.getRole();
			//将获取的新增、编辑页面返回到前台页面
			

		} catch (Exception e) {
			// 写入错误日志
			LogHelper.ERROR.log(e.getMessage(), e);
			// 返回客户端消息
			writeFailResult(e.getMessage());
		}
		String userPage = employee.getEditPage(userService, id, orgService, orgList, roleService, roleList, ruService,
				ourService);
		request.setAttribute("html", userPage);
		return SUCCESS;
	}
	
	/**
	 * 初始化签章页面，返回成功页面
	 * @throws DBException 
	 * */
	public String signatureInit() throws DBException {
		// 接收图片链接地址
		String id = getParam("id");
		User user = userService.getUser(id);
		String userImg = user.getUserImg();
		EmployeePage employee = new EmployeePage();
		//初始化签章页面返回到前台页面
		request.setAttribute("html", employee.getViewPage(userImg));
		return SUCCESS;
	}

	/**
	 * 新增User数据
	 * @param user String类型的员工对象
	 * @throws Exception
	 */
	public void addUser() {
		// 获取前台传入参数
		String user = getParam("user");
		String roleId = getParam("roleId");
		// 执行保存操作
		try {
			String flag = userService.addUser(user,myfiles,myfilesFileName,roleId);
			writeSuccessResult(flag);
		} catch (DBException e) {
			// 写入错误日志
			LogHelper.ERROR.log(e.getMessage(), e);
			// 返回客户端消息
			writeFailResult(e.getMessage());
		}
	}

	/**
	 * 更新User数据
	 * @param user String类型的员工对象
	 * @throws Exception
	 */
	public void modifyUser() {
		// 获取User修改数据
		String user = getParam("user");
		try {
			// 执行修改操作
			userService.modifyUser(user);
			// 返回客户端消息
			writeSuccessResult(user);
		} catch (Exception e) {
			// 写入错误日志
			LogHelper.ERROR.log(e.getMessage(), e);
			// 返回客户端消息
			writeFailResult(e.getMessage());
		}
	}

	/**
	 * 删除User数据 如果已经被组织或者流程使用，则不能被删除
	 * @param user 包含员工标识的user对象
	 * @throws Exception
	 */
	public void removeUser() {
		// 获取User标识
		String user = getParam("user");
		try {
			// 执行删除操作,返回前台判断标识
			String flag = userService.delUser(user);
			// 返回客户端消息
			writeSuccessResult(flag);
		} catch (Exception e) {
			// 写入错误日志
			LogHelper.ERROR.log(e.getMessage(), e);
			// 返回客户端错误消息
			writeFailResult(e.getMessage());
		}
	}

	/**
	 * 获取User列表数据
	 * @throws Exception
	 */
	public void getUserList() {
		try {
			// 执行查询操作
			List<UserOrgView> userList = userService.getUserOrgViewList();
			// 返回客户端消息
			writeSuccessResult(userList);
		} catch (Exception e) {
			// 写入错误日志
			LogHelper.ERROR.log(e.getMessage(), e);
			// 返回客户端错误消息
			writeFailResult(e.getMessage());
		}
	}

	/**
	 * 根据userId获取user
	 * @param id 员工标识
	 * @throws Exception
	 */
	public void getUserById() {
		// 获取User标识
		String id = getParam("id");
		try {
			// 执行查询操作
			User user = userService.getUser(id);
			// 返回客户端消息
			writeSuccessResult(user);
		} catch (Exception e) {
			// 写入错误日志
			LogHelper.ERROR.log(e.getMessage(), e);
			// 写失败信息到客户端
			writeFailResult(e.getMessage());
		}
	}
	
	/**
	 * 导出Excel
	 * @return 返回下载流成功
	 * @throws UnsupportedEncodingException 
	 * @throws Exception
	 */
	public String export() throws UnsupportedEncodingException{
		//表格名称
		SimpleDateFormat sd = new SimpleDateFormat("yyyyMMdd");
		String str = sd.format(new Date());
		this.fileName = "用户数据"+str+".xls";
		fileName = new String(fileName.getBytes(), "ISO8859-1");
		try {
			// 执行导出操作
			downloadStream = userService.exportExcel();;
		} catch (Exception e) {
			// 写入错误日志
			LogHelper.ERROR.log(e.getMessage(), e);
			// 写失败信息到客户端
			writeFailResult(e.getMessage());
		}
		return SUCCESS;
	}
	
	/**
	 * 根据userNo获取user
	 * @param UserNo 员工编号
	 * @throws Exception
	 */
	public void getUserByUserNo() {
		// 获取User标识
		String userNo = getParam("userNo");
		try {
			// 执行查询操作
			List<User> user = userService.getUserByNo(userNo);
			if (user!=null) {
				if (user.size() > 0) {
					// 返回客户端消息
					writeSuccessResult(user.get(0));
					return;
				}
			}
			// 返回客户端消息
			writeSuccessResult();
		} catch (Exception e) {
			// 写入错误日志
			LogHelper.ERROR.log(e.getMessage(), e);
			// 写失败信息到客户端
			writeFailResult(e.getMessage());
		}
	}
}