package com.ht.action.background.organization.organization;

import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;

import com.ht.action.base.BaseAction;
import com.ht.common.util.DataConverter;
import com.ht.common.util.LogHelper;
import com.ht.front.pages.background.organization.organization.OrganizationPage;
import com.ht.persistence.model.background.organization.employee.UserOrgView;
import com.ht.persistence.model.background.organization.organization.Organization;
import com.ht.service.inter.background.organization.employee.UserService;
import com.ht.service.inter.background.organization.organization.OrganizationService;
import com.ht.service.inter.background.organization.organization.OrganizationUsersRelationService;
import com.sun.star.resource.OfficeResourceLoader;

/**
 * 组织机构类action
 * @author 侯晨
 */
@SuppressWarnings("serial")
public class OrganizationAction extends BaseAction{
	/**
	 * 注入organizationService
	 */
	@Resource(name="organizationService")
	OrganizationService organizationService;
	
	/**
	 * 注入organizationUsersRelationService
	 */
	@Resource(name="organizationUsersRelationService")
	OrganizationUsersRelationService organizationUsersRelationService;
	
	/**
	 * 注入用户service
	 * */
	@Resource(name="userService")
	UserService userService;
	
	/**
	 * 初始化组织机构数据页面，返回成功列表页面
	 * @throws Exception 
	 * */
	public String index() throws Exception{
		OrganizationPage organization = OrganizationPage.getInstance();
		//将获取的组织机构列表页面返回到前台页面
		request.setAttribute("html", organization.getListPage());
		return SUCCESS;
	}
	
	/**
	 * 初始化编辑组织机构数据页面，返回成功页面
	 * @param id 组织机构的id
	 * @throws Exception 
	 * */
	public String editIndex() throws Exception{
		//接收要修改的组织机构标示
		String id = getParam("id");
		OrganizationPage organization = new OrganizationPage();
		//将获取的节点字符串返回到前台页面
		request.setAttribute("html", organization.getEditNode(organizationService,id));
		return SUCCESS;
	}
	
	/**
	 * 初始化组织机构人员数据页面，返回成功页面
	 * @throws Exception 
	 * */
	public String allocUserIndex() throws Exception{
		// 接收要分配人员的组织机构orgId
		String id = getParam("id");
		Organization o = organizationService.getOrganization(id);
		String orgName = o.getOrgName();
		OrganizationPage organization = new OrganizationPage();
		//将获取的分配人员的页面返回到前台页面
		request.setAttribute("html", organization.getAllocUserPage(id,orgName));
		return SUCCESS;
	}
	
	/**
	 * 新增Organization数据
	 * @param org 新增的组织机构json数据
	 */
	public void addOrganization(){
		try {
			// 获取前台传入参数
			String organization = getParam("org");
			// 执行保存操作
			organizationService.addOrganization(organization);
			// 返回客户端消息
			writeSuccessResult(organization);
		} catch (Exception e) {
			// 写入错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 返回客户端错误消息
			writeFailResult(e.getMessage());
		}
	}
	
	/**
	 * 更新Organization数据
	 * @param org 修改的组织机构json数据
	 */
	public void modifyOrganization(){
		// 获取Organization修改数据
		String organization = getParam("organization");
		try {
			//执行修改操作
			organizationService.modifyOrganization(organization);
			//返回客户端消息
			writeSuccessResult(organization);
		} catch (Exception e) {
			// 写入错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			//返回客户端消息
			writeFailResult(e.getMessage());
		}
	}
	
	/**
	 * 删除Organization数据以及关系表
	 * @param 修改的组织机构json数据
	 */
	public void removeOrganization(){
		// 获取Organization标识
		String org = getParam("org");
		try {
			//执行删除操作
			organizationService.delOrganization(org);
			//返回客户端消息
			writeSuccessResult();
		} catch (Exception e) {
			// 写入错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			//返回客户端错误消息
			writeFailResult(e.getMessage());
		}
	}
	
	/**
	 * 获取Organization列表
	 */
	public void getOrganization(){
		try {
			//执行查询操作
			List<Organization> organizationList = organizationService.getOrganization();
			respose.getWriter().write(DataConverter.convertObject2Json(organizationList));
		} catch (Exception e) {
			// 写入错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			//返回客户端错误消息
			writeFailResult(e.getMessage());
		}
	}
	
	/**
	 * 根据id获取Organization组织机构信息
	 * @param id 组织机构标识
	 */
	public void getOrganizationByKey(){
		// 获取Organization标识
		String id = getParam("id");
		try {
			//执行查询操作
			Organization organization = organizationService.getOrganization(id);
			//返回客户端消息
			writeSuccessResult(organization);
		} catch (Exception e) {
			// 写入错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 写失败信息到客户端
			writeFailResult(e.getMessage());
		}
	}
	
	/**
	 * 提交某组织机构下人员关系
	 * @param orgId 组织机构标识
	 * @param users 用户标识集合
	 * @throws Exception 
	 */
	public void addOrgUsers(){
		// 获取Organization标识
		String orgId = getParam("orgId");
		//获取选中的用户标示
		String users = getParam("users");
		try {
			//插入组织机构与人员关系数据
			organizationUsersRelationService.addOrgUsers(orgId, users);
			//返回客户端消息
			writeSuccessResult();
		} catch (Exception e) {
			// 写入错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 写失败信息到客户端
			writeFailResult(e.getMessage());
		}
	}
	
	/**
	 * 获取某组织机构下人员及公共部门里的人员列表
	 * @param 组织人员关系数据
	 * @throws Exception 
	 */
	public void getOrgUsers(){
		String orgId = getParam("org");
		try {
			List<UserOrgView> userList = userService.getUserOrgViewByOrgId(orgId);
			//获取某组织机构下人员及公共部门里的人员列表
			List<UserOrgView> list = organizationUsersRelationService.getOrgUsers(orgId,userList);
			//返回客户端消息
			if(list != null){
				if(list.size()>0){
					Iterator<UserOrgView> it = list.iterator();
					while(it.hasNext()){
						UserOrgView x = it.next();
						if(x == null){
							it.remove();
						}
					}
				}
			}
			writeSuccessResult(list);
		} catch (Exception e) {
			// 写入错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 写失败信息到客户端
			writeFailResult(e.getMessage());
		}
	}
	
	/**
	 * 获所有组织机构子节点的集合，形成数机构
	 * @throws Exception
	 */
	public void getOrganizationTree(){
		try {
			String treeJson = organizationService.getOrganizationTree();
			writeSuccessResult(treeJson);
		} catch (Exception e) {
			// 写入错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 写失败信息到客户端
			writeFailResult(e.getMessage());
		}
	}
}
