package com.ht.service.impl.background.organization.employee;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.ht.common.exception.CommonException;
import com.ht.common.util.ConvertUtil;
import com.ht.common.util.DataConverter;
import com.ht.common.util.FileUtil;
import com.ht.common.util.GenerateSequenceUtil;
import com.ht.common.util.LogHelper;
import com.ht.common.util.Utilmd5;
import com.ht.exception.DBException;
import com.ht.persistence.dao.inter.background.authority.role.RoleDao;
import com.ht.persistence.dao.inter.background.authority.role.RoleUsersDao;
import com.ht.persistence.dao.inter.background.organization.employee.UserDao;
import com.ht.persistence.dao.inter.background.organization.organization.OrganizationUsersRelationDao;
import com.ht.persistence.dao.inter.system.workflow.process.ProcessUserDao;
import com.ht.persistence.model.background.authority.role.Role;
import com.ht.persistence.model.background.authority.role.RoleUsers;
import com.ht.persistence.model.background.organization.employee.User;
import com.ht.persistence.model.background.organization.employee.UserOrgRole;
import com.ht.persistence.model.background.organization.employee.UserOrgView;
import com.ht.persistence.model.background.organization.organization.OrganizationUsersRelation;
import com.ht.persistence.model.system.workflow.process.ProcessUser;
import com.ht.service.inter.background.organization.employee.UserService;
import com.ht.workflow.service.IWorkflowService;

/**
 * 人员信息业务处理实现类
 * @author liukai
 */
public class UserServiceImpl implements UserService
{

	/**
	 * 注入人员信息Dao
	 */
	@Resource(name = "userDao")
	private UserDao userDao;

	/**
	 * 注入组织机构人员organizationDao
	 */
	@Resource(name = "organizationUsersRelationDao")
	private OrganizationUsersRelationDao organizationUsersRelationDao;

	/**
	 * 注入流程用户Dao
	 */
	@Resource(name = "processUserDao")
	private ProcessUserDao processUserDao;

	/**
	 * 注入roleUsersDao
	 */
	@Resource(name = "roleUsersDao")
	private RoleUsersDao roleUsersDao;

	/**
	 * 注入service
	 */
	@Resource(name = "service")
	private IWorkflowService service;

	/**
	 * 注入service
	 */
	@Resource(name = "roleDao")
	private RoleDao roleDao;

	/**
	 * 新增人员
	 * @param userParam String类型的员工对象
	 * @throws Exception
	 */
	@Override
	public String addUser(String userParam, File myfiles, String myfilesFileName, String roleId) throws DBException
	{
		try
		{
			// 将用户String类型转成User对象
			UserOrgRole uor = (UserOrgRole) DataConverter.convertJson2Object(userParam, UserOrgRole.class);
			if(uor.getOrgId().equals("--请选择--")){
				uor.setOrgId("10191641226710006");
			}
			
			if(uor.getUserNo().length()>64){
				return "userNo";
			}
			if(uor.getUserName().length()>64){
				return "userName";
			}

			String[] roleIds = null;
			if (StringUtils.isNotBlank(roleId))
			{
				roleIds = roleId.split(",");
			}
			// 创建用户对象
			User user = new User();
			user.setId(uor.getId());
			user.setPassword(uor.getPassword());
			user.setUserName(uor.getUserName());
			user.setUserImg(uor.getUserImg());
			// 去掉登录账号的前后空格
			String userNo = uor.getUserNo().trim();
			user.setUserNo(userNo);

			// 用户-组织机构关系对象
			OrganizationUsersRelation our = new OrganizationUsersRelation();
			our.setOrgId(uor.getOrgId());

			// 如果id存在，则执行修改操作
			if (StringUtils.isNotEmpty(user.getId()))
			{
				// 判断登陆账号如果存在则不能修改（排除自己）
				List<User> list = userDao.getUserList();
				// 排除自己后的用户列表
				for (int i = 0; i < list.size(); i++)
				{
					if (list.get(i).getId().equals(user.getId()))
					{// 排除自己
						list.remove(i);
					}
				}
				for (int i = 0; i < list.size(); i++)
				{
					if (list.get(i).getUserNo().equals(user.getUserNo()))
					{// 如果登陆账号已存在
						return "exit";
					}
				}
				String password = user.getPassword();
				// MD5加密
				String newpassword = Utilmd5.New().convertMD5(password);
				user.setPassword(newpassword);
				if (myfilesFileName != null)
				{
					// 编辑时没上传图片
					String url = "";
					if (myfiles != null)
					{
						url = uploadFile(myfiles, myfilesFileName);
					}
					user.setUserImg(url);
				}
				else
				{
					User u = new User();
					u.setId(user.getId());
					u = userDao.getUser(u);
					user.setUserImg(u.getUserImg());
				}
				userDao.modifyUser(user);
				our.setUserId(uor.getId());
				OrganizationUsersRelation orgUser = organizationUsersRelationDao.getUsersByUserId(our);
				organizationUsersRelationDao.delOrganizationUsersRelation(orgUser);
				our.setId(GenerateSequenceUtil.generateSequenceNo());
				our.setUserId(uor.getId());
				organizationUsersRelationDao.addOrganizationUsersRelation(our);

				// 用户-角色的处理
				RoleUsers rur = new RoleUsers();
				rur.setUserId(user.getId());
				List<RoleUsers> rurList = roleUsersDao.getRoleUsersByUserId(rur);
				for (int i = 0; i < rurList.size(); i++)
				{
					roleUsersDao.delRoleUsers(rurList.get(i));
				}
				for (int j = 0; j < roleIds.length; j++)
				{
					if(!roleIds[j].contains("请选择")){
						RoleUsers ru = new RoleUsers();
						ru.setId(GenerateSequenceUtil.generateSequenceNo());
						ru.setUserId(user.getId());
						ru.setRoleId(roleIds[j]);
						roleUsersDao.addRoleUsers(ru);
					}
				}
			}
			else
			{
				// 如果id不存在，则执行新增操作
				// 判断登陆账号如果存在则不能插入新数据
				List<User> list = userDao.getUserList();
				for (int i = 0; i < list.size(); i++)
				{
					if (list.get(i).getUserNo().equals(user.getUserNo()))
					{
						return "exit";
					}
				}
				// 插入用户数据
				String id = GenerateSequenceUtil.generateSequenceNo();
				user.setId(id);
				String password = user.getPassword();
				// MD5加密
				String newpassword = Utilmd5.New().convertMD5(password);
				user.setPassword(newpassword);
				String url = "";
				if (myfiles != null)
				{
					url = uploadFile(myfiles, myfilesFileName);
				}
				user.setUserImg(url);
				userDao.addUser(user);
				// 插入公共组织和人员关系表

				our.setId(GenerateSequenceUtil.generateSequenceNo());
				our.setUserId(id);
				organizationUsersRelationDao.addOrganizationUsersRelation(our);
				// 创建角色对象

				if(roleIds!=null){
					if(roleIds.length>0){
						for (int j = 0; j < roleIds.length; j++)
						{
							if(!roleIds[j].contains("请选择")){
								RoleUsers ru = new RoleUsers();
								ru.setId(GenerateSequenceUtil.generateSequenceNo());
								ru.setUserId(user.getId());
								ru.setRoleId(roleIds[j]);
								roleUsersDao.addRoleUsers(ru);
							}
						}
					}
				}
			}
		}
		catch (Exception e)
		{
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(), e);
		}
		return "true";
	}

	/**
	 * 修改密码
	 */
	public void modifyPassword(String id, String password) throws Exception
	{
		try
		{
			// MD5加密
			String newpassword = Utilmd5.New().convertMD5(password.trim());///先去除一下空格2018.4.2
			User user = new User();
			user.setPassword(newpassword);
			user.setId(id);
			userDao.modifyPassword(user);
		}
		catch (Exception e)
		{
			throw new CommonException();
		}

	}

	/**
	 * 修改人员
	 * @param userParam String类型的员工对象
	 * @throws Exception
	 */
	@Override
	public void modifyUser(String userParam) throws Exception
	{
		try
		{
			// 将用户String类型转成User对象
			User user = (User) DataConverter.convertJson2Object(userParam, User.class);
			user.setCreator("");
			user.setModifier("");
			user.setModifyDate(new Date());
			// 更新User
			userDao.modifyUser(user);
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
	 * 删除多条人员信息
	 * @param userParam String类型的员工对象
	 * @return 返回是否删除成功字符串
	 * @throws Exception
	 */
	@Override
	public String delUser(String userParam) throws Exception
	{
		try
		{
			// 将用户String类型转成User对象
			List<User> list = (List<User>) DataConverter.convertJson2List(userParam, User.class);
			for (int i = 0; i < list.size(); i++)
			{
				// 首先判断该用户是否被组织使用
				OrganizationUsersRelation our = new OrganizationUsersRelation();
				our.setUserId(list.get(i).getId());
				List<OrganizationUsersRelation> ourList = organizationUsersRelationDao.getUserByUserId(our);
				if (ourList.size() > 0)
				{// 如果已经被组织使用，则返回前台判断标识
					return "org";
				}
				// 判断该用户是否被角色使用
				RoleUsers ru = new RoleUsers();
				ru.setUserId(list.get(i).getId());
				List<RoleUsers> ruList = roleUsersDao.getRoleUsersByUserId(ru);
				if (ruList.size() > 0)
				{// 如果已经被流程使用，则返回前台判断标识
					return "role";
				}
				// 判断该用户是否被使用
				org.activiti.engine.identity.User u = service.getUserById(list.get(i).getId());
				if (u != null)
				{// 查出数据则说明不能删除！
					String userName = u.getFirstName();
					service.deleteUser(userName);
				}
				// 删除User
				userDao.delUser(list.get(i));
				// 删除组织人员关系
				our = organizationUsersRelationDao.getUsersByUserId(our);
				organizationUsersRelationDao.delOrganizationUsersRelation(our);
			}
		}
		catch (Exception e)
		{
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(), e);
			// 抛出异常
			throw e;
		}
		return "success";
	}

	/**
	 * 获取人员列表
	 * @return List<User> 人员信息
	 * @throws Exception
	 */
	@Override
	public List<User> getUserList() throws Exception
	{
		try
		{
			// 获取所有User列表
			return userDao.getUserList();
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
	public List<User> getUserByUserIds(List<String> ids) throws DBException
	{
		try
		{
			if (ids.size()>0)
			{
				// 获取所有User列表
				return userDao.getUserByUserIds(ids);
			}
			return null;
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
	 * 根据人员编号获取人员信息
	 * @param id User主键
	 * @return User实体对象
	 * @throws Exception
	 */
	@Override
	public User getUser(String id) throws DBException
	{
		try
		{
			User user = new User();
			user.setId(id);
			// 根据userId获取User
			user = userDao.getUser(user);
			return user;
		}
		catch (Exception e)
		{
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(), e);
			// 抛出异常
			throw new DBException("数据库操作错误");
		}
	}

	/**
	 * 导出人员信息
	 * @return InputStream 返回流
	 */
	@Override
	public InputStream exportExcel()
	{
		InputStream stream = null;
		String[] title =
		{ "登陆账号", "用户名称", "所属组织机构", "所属角色" };
		String sheetName = "人员信息";
		List<UserOrgView> userList = userDao.getUserOrgViewList();
		for (int i = 0; i < userList.size(); i++)
		{
			UserOrgView uov = userList.get(i);
			RoleUsers ru = new RoleUsers();
			ru.setUserId(uov.getId());
			List<RoleUsers> ruList = roleUsersDao.getRoleUsersByUserId(ru);
			String roleName = "";
			for (int j = 0; j < ruList.size(); j++)
			{
				Role role = new Role();
				role.setId(ruList.get(j).getRoleId());
				role = roleDao.getRole(role);
				if (j == ruList.size() - 1)
				{
					roleName += role.getRoleName();
				}
				else
				{
					roleName += role.getRoleName() + ",";
				}
			}
			uov.setRoleId(roleName);
		}
		try
		{
			// 创建excel工作簿
			XSSFWorkbook workbook = new XSSFWorkbook();
			// 创建一个工作表
			XSSFSheet sheet = workbook.createSheet(sheetName);
			// 给一个工作表名称一个长度
			sheet.setDefaultColumnWidth(15);
			// 生成一个样式
			XSSFCellStyle style = workbook.createCellStyle();
			// 创建第一行
			XSSFRow row = sheet.createRow(0);
			// 设置样式居中
			style.setAlignment(XSSFCellStyle.ALIGN_CENTER);
			// 创建字体对象
			Font font = workbook.createFont();
			// 字体加粗
			font.setBoldweight(Font.BOLDWEIGHT_BOLD);
			// 添加字体样式
			style.setFont(font);
			// 设置行高
			row.setHeightInPoints(20);
			// 设置第一行标题
			XSSFCell cell = null;
			for (int i = 0; i < title.length; i++)
			{
				cell = row.createCell(i);
				cell.setCellValue(title[i]);
				sheet.setColumnWidth(i, title[i].getBytes().length * 1 * 256);
				cell.setCellStyle(style);
			}
			// 创建记录
			int size = userList.size();
			if (size > 0)
			{
				for (int i = 1; i <= size; i++)
				{
					XSSFRow xssfRow = sheet.createRow(i);
					XSSFCell xssfCell = null;
					UserOrgView u = userList.get(i - 1);
					for (int j = 0; j < title.length; j++)
					{

						if (j == 0)
						{
							xssfCell = xssfRow.createCell(j);
							xssfCell.setCellValue(u.getUserNo());
						}

						if (j == 1)
						{
							xssfCell = xssfRow.createCell(j);
							xssfCell.setCellValue(u.getUserName());
						}

						if (j == 2)
						{
							xssfCell = xssfRow.createCell(j);
							xssfCell.setCellValue(u.getOrgName());
						}
						if (j == 3)
						{
							xssfCell = xssfRow.createCell(j);
							xssfCell.setCellValue(u.getRoleId());
						}
						xssfCell.setCellStyle(style);
					}
				}
			}
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			workbook.write(outputStream);
			outputStream.flush();
			byte[] byteArray = outputStream.toByteArray();
			stream = new ByteArrayInputStream(byteArray, 0, byteArray.length);
			outputStream.close();
		}
		catch (Exception e)
		{
			e.getMessage();
		}
		return stream;

	}

	/**
	 * 根据userNo查询用户
	 * @param user String类型的员工对象
	 * @return List<User> 用户数据列表
	 * @throws Exception
	 */
	@Override
	public List<User> getUserByNo(String userParam) throws DBException
	{
		try
		{
			User user = new User();
			user.setUserNo(userParam);
			// 根据userNo获取User
			return userDao.getUserByNo(user);
		}
		catch (Exception e)
		{
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(), e);
			// 抛出异常
			throw new DBException("数据库操作错误");
		}
	}

	/**
	 * 通过userNo登陆账号和密码获取用户信息
	 * @param user实体
	 * @return 员工列表
	 * @throws Exception
	 */
	@Override
	public List<User> getUserByNoAndPassword(String userParam) throws DBException
	{
		try
		{
			User user = (User) DataConverter.convertJson2Object(userParam, User.class);
			// 根据userNo和password获取User
			return userDao.getUserByNoAndPassword(user);
		}
		catch (Exception e)
		{
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(), e);
			// 抛出异常
			throw new DBException("数据库操作错误");
		}
	}

	/**
	 * 上传文件
	 */
	@Override
	public String uploadFile(File upload, String uploadFileName) throws IOException
	{
		// 获取项目在服务器的路径
		String serverPath = FileUtil.ROOT_PATH;
		// 新建一个路径，在最后以当前年月日新建一个文件夹
		String path = "\\upload\\userImage\\" + ConvertUtil.convertDateToString("yyyyMMdd", new Date());
		// 创建文件夹
		// 设置文件名称
		String imgName = ConvertUtil.convertDateToString("yyyyMMddHHmmss", new Date()) + ".jpg";
		FileUtil.CreateFolder(serverPath + path);
		InputStream is = new FileInputStream(upload);
		OutputStream os = new FileOutputStream(new File(serverPath + path, imgName));
		System.out.println("fileFileName: " + imgName);

		// 因为file是存放在临时文件夹的文件，我们可以将其文件名和文件路径打印出来，看和之前的fileFileName是否相同
		System.out.println("file: " + upload.getName());
		System.out.println("file: " + upload.getPath());

		byte[] buffer = new byte[500];
		int length = 0;

		while (-1 != (length = is.read(buffer, 0, buffer.length)))
		{
			os.write(buffer);
		}

		os.close();
		is.close();
		return "\\ht" + path + "\\" + imgName;
	}

	/**
	 * 获取人员列表
	 * @return List<UserOrgView> 人员信息
	 * @throws Exception
	 */
	@Override
	public List<UserOrgView> getUserOrgViewList()
	{
		try
		{
			// 获取所有User列表
			List<UserOrgView> urvList = userDao.getUserOrgViewList();
			for (int i = 0; i < urvList.size(); i++)
			{
				UserOrgView uov = urvList.get(i);
				if(uov!= null){
					RoleUsers ru = new RoleUsers();
					ru.setUserId(uov.getId());
					List<RoleUsers> ruList = roleUsersDao.getRoleUsersByUserId(ru);
					String roleName = "";
					for (int j = 0; j < ruList.size(); j++)
					{
						Role role = new Role();
						role.setId(ruList.get(j).getRoleId());
						role = roleDao.getRole(role);
						if (j == ruList.size() - 1)
						{
							roleName += role.getRoleName();
						}
						else
						{
							roleName += role.getRoleName() + ",";
						}
					}
					uov.setRoleId(roleName);
				}
			}
			return urvList;
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
	 * 根据orgId查询用户
	 * @param user String类型的员工对象
	 * @return List<User> 用户数据列表
	 * @throws Exception
	 */
	@Override
	public List<UserOrgView> getUserOrgViewByOrgId(String orgId) throws DBException
	{
		try
		{
			UserOrgView user = new UserOrgView();
			user.setOrgId(orgId);
			// 根据userNo获取User
			return userDao.getUserOrgViewByOrgId(user);
		}
		catch (Exception e)
		{
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(), e);
			// 抛出异常
			throw new DBException("数据库操作错误");
		}
	}
}
