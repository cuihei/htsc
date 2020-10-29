package com.ht.action.datum.productupdsorcedata;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.ht.action.base.BaseAction;
import com.ht.common.util.DataConverter;
import com.ht.common.util.LogHelper;
import com.ht.common.util.LoginUtil;
import com.ht.front.pages.datum.productupdsorcedata.ProductUpdSourceDatas;
import com.ht.persistence.model.background.authority.role.Role;
import com.ht.persistence.model.background.authority.role.RoleUsers;
import com.ht.persistence.model.background.organization.employee.User;
import com.ht.persistence.model.datum.productupdsourcedata.ProductUpdSourceData;
import com.ht.persistence.model.drawtask.taskbook.create.CreateTask;
import com.ht.service.inter.background.authority.role.RoleService;
import com.ht.service.inter.background.authority.role.RoleUsersService;
import com.ht.service.inter.background.organization.employee.UserService;
import com.ht.service.inter.datum.productupdsourcedata.ProductUpdSourceDataService;
import com.ht.service.inter.system.workflow.task.TaskService;

/**
 * 产品修改源数据action
 * @author Mr_zyd
 * @date 2017/12/12
 */
@SuppressWarnings("serial")
public class ProductUpdSourceDataAction extends BaseAction{

	/**
	 * 注入产品修改源数据service
	 * */
	@Resource(name="productUpdSourceDataService")
	ProductUpdSourceDataService productUpdSourceDataService;

	/**
	 * 注入TaskService
	 */
	@Resource(name="taskService")
	TaskService taskService;
	
	/**
	 * 注入用户service
	 * */
	@Resource(name = "userService")
	UserService userService;
	
	/**
	 * 注入roleUsersService
	 */
	@Resource(name = "roleUsersService")
	RoleUsersService roleUsersService;
	
	/**
	 * 注入roleService
	 */
	@Resource(name = "roleService")
	RoleService roleService;
	
	/**
	 * 初始化ProductUpdSourceData数据页面，返回成功列表页面
	 * @throws Exception 
	 * */
	public String init() throws Exception{
		
		// 获取用户No
		String LoginUser = LoginUtil.getInstance().getLoginNo(request);
		// 获取 用户
		List<User> userList = userService.getUserByNo(LoginUser);
		
		String userName = "";
		
		if(userList.size() > 0){
			userName = userList.get(0).getUserName();
		}
		
		// 实例化页面对象
		ProductUpdSourceDatas page = ProductUpdSourceDatas.getInstance();
		String role = getRole();
		String status = "";
		if(role.equals("bhy")){
			status = "1";
		}else if(role.equals("zjy")){
			status = "2";
		}else if(role.equals("sdy")){
			status = "3";
		}
		
		// 获取页面节点字符串
		String listnode = page.getListNode(status,userName);
		// 将获取的节点字符串返回到前台页面
		request.setAttribute("html", listnode);
		return SUCCESS;
	}
	
	/**
	 * 初始化编辑页面，返回成功页面
	 * @throws Exception 
	 * */
	public String editInit() throws Exception {
		ProductUpdSourceDatas pusds = new ProductUpdSourceDatas();
		List<ProductUpdSourceData> pusdList = new ArrayList<ProductUpdSourceData>();
		
		// 获取源数据审定完成的图号
		String status = "审定完成";
		String type = "SEA_MAP_COMPILATION_SOURCE_DATA_ALL";
		
		// 根据状态和类型获取任务
		List<CreateTask> ctList = productUpdSourceDataService.getTaskListByStatusAndType(status,type);
		
		for (int i = 0; i < ctList.size(); i++) {
			ProductUpdSourceData pusd = new ProductUpdSourceData();
			if(ctList.get(i).getMapNo() != null){
				pusd.setDraw(ctList.get(i).getMapNo());
				pusdList.add(pusd);
			}
		}
		
		//将获取的节点字符串返回到前台页面
		request.setAttribute("html", pusds.getEditNode(pusdList));
		return SUCCESS;
	}
	
	/**
	 * 新增ProductUpdSourceData
	 */
	public void addProductUpdSourceData(){
		try {
			String type = "SEA_MAP_COMPILATION_PAPER_ALL";
			// 获取前台传入参数
			String productupdsourcedata = getParam("productupdsourcedata");
			String draw = getParam("draw");
			// 通过图号和类型获取数据
			List<CreateTask> ctList = productUpdSourceDataService.getTaskListByDrawAndType(draw,type);
			// 执行保存操作
			String flag = productUpdSourceDataService.addProductUpdSourceData(productupdsourcedata,draw);
			// 挂起流程
			if(flag.equals("success")){
				if(ctList.size() > 0){
					productUpdSourceDataService.suspended(draw,"suspended",type);
				}
				writeSuccessResult(flag);
			}
			
		} catch (Exception e) {
			writeFailResult(e.getMessage());
		}
	}
	
	/**
	 * 获取ProductUpdSourceData列表
	 */
	public void getProductUpdSourceData(){
		try{
			List<ProductUpdSourceData> pusdList = new ArrayList<ProductUpdSourceData>();
			// 根据不同角色获取不同数据列表
			pusdList = productUpdSourceDataService.getProductUpdSourceData();
			writeSuccessResult(pusdList);
		} catch (Exception e) {
			// 写入错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			writeFailResult(e.getMessage());
		}
	}
	
	/**
	 * 通过图号获取任务
	 * @throws Exception
	 */
	public void getPersonByMapNo() throws Exception{
		String mapNo = getParam("draw");
		String childTskType = "SEA_MAP_COMPILATION_SOURCE_DATA_ALL";
		String status = "审定完成";
		
		Map<String, String> map = productUpdSourceDataService.getPersonByMapNo(mapNo,childTskType,status);
		ProductUpdSourceData pusd = new ProductUpdSourceData();
		pusd.setFigure_caption(map.get("taskName"));				// 图名
		pusd.setCompiler(map.get("compilationPerson"));				// 编绘员
		pusd.setQuality_inspector(map.get("qualityPerson"));		// 质检员
		pusd.setAuthorized_member(map.get("apprvoePerson"));		// 审定员
		String json = DataConverter.convertObject2Json(pusd);
		respose.getWriter().write(json);
	}
	
	/**
	 * 修改pusd状态
	 * @throws Exception
	 */
	public void updataPusdStatus() throws Exception{
		String type = "SEA_MAP_COMPILATION_PAPER_ALL";
		String id = getParam("id");
		ProductUpdSourceData pusd = productUpdSourceDataService.getProductUpdSourceDataById(id);
		// 获取角色
		String role = getRole();
		String status = "";
		
		if(role.equals("bhy")){
			status = "2";
		}else if(role.equals("zjy")){
			status = "3";
		}else if(role.equals("sdy")){
			status = "4";
		}
		pusd.setStatus(status);
		String flag = productUpdSourceDataService.modifyPusd(pusd);
		// 如果最后确定人角色是审定员，则启动流程
		if(role.equals("sdy")){
			productUpdSourceDataService.suspended(pusd.getDraw(),"activate",type);
		}
		writeSuccessResult(flag);
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