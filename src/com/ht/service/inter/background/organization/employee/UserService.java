package com.ht.service.inter.background.organization.employee;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.ht.exception.DBException;
import com.ht.persistence.model.background.organization.employee.User;
import com.ht.persistence.model.background.organization.employee.UserOrgView;

/**
 * 人员信息接口
 * @author liukai
 * */
public interface UserService {
	
  /**
   * 保存用户
   * @param user String类型的员工对象
   * @throws Exception
   */
  public String addUser(String user,File myfiles,String myfilesFileName,String roleId) throws DBException;
  
  /**
   * 修改密码
   * @param user String类型的员工对象
   * @throws Exception
   */
  public void modifyPassword(String id,String password) throws Exception;
  
  /**
   * 修改用户
   * @param user String类型的员工对象
   * @throws Exception
   */
  public void modifyUser(String user) throws Exception;
  
  /**
   * 用户用户
   * @param user String类型的员工对象
   * @throws Exception
   */
  public String delUser(String user) throws Exception;
  
  /**
   * 查询用户列表
   * @return List<User> 用户数据列表
   * @throws Exception
   */
  public List<User> getUserList() throws Exception;
  
  /**
   * 查询一条用户
   * @param id 员工标识
   * @return User 一条user实体
   * @throws Exception
   */
  public User getUser(String id) throws DBException;
  
  /**
   * 导出人员信息
   * @throws Exception
   */
  public InputStream exportExcel() throws DBException;
  
  /**
   * 根据userNo查询用户
   * @param user String类型的员工对象
   * @return List<User> 用户数据列表
   * @throws Exception
   */
  public List<User> getUserByNo(String user) throws DBException;
  
  /**
   * 通过userNo登陆账号和密码获取用户信息
   * @param user实体 
   * @return 员工列表
   * @throws Exception
   */
  public List<User> getUserByNoAndPassword(String user) throws DBException;
  
  
  public List<User> getUserByUserIds(List<String> ids) throws DBException;
  
  /**
   * 上传图片
   * @param File upload 上传图片
   * @param String uploadFileName 图片名称
   * @return 返回上传图片的路径
   */
  public String uploadFile(File upload, String uploadFileName) throws IOException;
  
  /**
   * 查询用户列表
   * @return List<UserOrgView> 用户数据列表
   * @throws Exception
   */
  public List<UserOrgView> getUserOrgViewList();
  
  /**
   * 根据orgId查询用户
   * @param orgId org组织机构id
   * @return List<UserOrgView> 用户数据列表
   * @throws Exception
   */
  public List<UserOrgView> getUserOrgViewByOrgId(String orgId) throws DBException;
}
