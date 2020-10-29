package com.ht.action.background.application;

import java.util.List;

import javax.annotation.Resource;

import com.ht.action.base.BaseAction;
import com.ht.common.util.DataConverter;
import com.ht.common.util.LogHelper;
import com.ht.front.pages.background.application.ApplicationPage;
import com.ht.persistence.model.background.application.Application;
import com.ht.persistence.model.background.application.ViewApplication;
import com.ht.persistence.model.background.dicdata.basedata.BaseData;
import com.ht.service.impl.background.dicdata.constants.BaseDataConstants;
import com.ht.service.inter.background.application.ApplicationService;
import com.ht.service.inter.background.application.ViewApplicationService;
import com.ht.service.inter.background.authority.auth.RoleAppService;
import com.ht.service.inter.background.dicdata.basedata.BaseDataService;

/**
 * application 应用资源类控制器
 * @author zhongquanyou
 */
@SuppressWarnings("serial")
public class ApplicationAction extends BaseAction{
	
	// 注入应用资源service
	@Resource(name="applicationService")
	private ApplicationService applicationService;

	// 注入应用资源service
	@Resource(name="viewApplicationService")
	private ViewApplicationService viewApplicationService;
	
	// 注入应用资源service
	@Resource(name="baseDataService")
	private BaseDataService baseDataService;
	
	// 注入权限关系service
	@Resource
	private RoleAppService roleAppService;
	
	/**
	 * 初始化应用资源页面
	 * @return 应用资源信息页面
	 */
	public String init(){
		ApplicationPage application = new ApplicationPage();
		request.setAttribute("html",application.getListNode());
		return SUCCESS;
	}
	
	/**
	 * 初始化编辑应用资源页面，返回成功的编辑页面
	 */
	public String initAppEdit(){
		
		// 获取页面返回的参数，资源ID
		String appIds = getParam("id");
		// 创建应用资源界面初始化类ApplicationView
		ApplicationPage appV = new ApplicationPage();
		// 获取基础类对象的List集合
		List<BaseData> baseDataList = null;
		// 获取节点字符串返回前台界面
		String appPage = null;
		try {
			baseDataList = baseDataService.getBaseDataByType(BaseDataConstants.CAIDAN_TYPE_NAME);
			appPage = appV.getEditNode(applicationService, baseDataService, baseDataList, appIds);
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 返回错误信息
			writeFailResult(e.getMessage());
		}
		
		//将获取的节点字符串返回到前台页面
		request.setAttribute("html",appPage);
		return SUCCESS;
	}
	
	/**
	 * 更新应用资源数据
	 */
	public void modifyApplication(){
		try {
			// 获取application对象
			String application = getParam("application");
			// 执行修改方法
			applicationService.modifyApplication(application);
			writeSuccessResult(application);
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 返回错误信息
			writeFailResult(e.getMessage());
		}
	}
	
	/**
	 * 删除应用资源数据
	 */
	public void removeApplication(){
		// 获取应用资源标识
		String id = getParam("application");
		try {
			// 执行删除方法
			String flag = applicationService.delApplication(id);
			writeSuccessResult(flag);
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 返回错误信息
			writeFailResult(e.getMessage());
		}
	}
	
	/**
	 * 获取应用资源列表
	 */
	public void getApplication(){
		try {
			List<ViewApplication> appList = viewApplicationService.getApplication();
			respose.getWriter().write(DataConverter.convertObject2Json(appList));
			//writeSuccessResult(appList);
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 返回错误信息
			writeFailResult(e.getMessage());
		}
	}
	
	/**
	 * 根据id获取application
	 */
	public void getApplicationByKey(){
		try {
			String id = getParam("application");
			Application application = applicationService.getApplication(id);
			writeSuccessResult(application);
		} catch (Exception e) {
			// 写入错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 写失败信息到客户端
			writeFailResult(e.getMessage());
		}
	}

	/**
	 * 导出Excel
	 */
	public void exportExcel() throws Exception{
		try{
			// 执行导出操作
			applicationService.exportExcel();
		}catch(Exception e){
			// 写入错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			//将失败信息输出到客户端
			writeSuccessResult();
		}
	}
	
	/**
	 * 获所有子节点的集合，形成书图
	 */
	public void getApplicationTree(){
		// 获取页面返回的参数，资源ID
		String appPid = getParam("id");
		try {
			String treeJson = applicationService.getApplicationTree(appPid);
			writeSuccessResult(treeJson);
		} catch (Exception e) {
			// 写入错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 写失败信息到客户端
			writeFailResult(e.getMessage());
		}
	}
	
	/**
	 * 获所有子节点的集合，形成书图
	 */
	public void getApplicationChilds(){
		try {
			String treeJson = applicationService.getApplicationChilds();
			writeSuccessResult(treeJson);
		} catch (Exception e) {
			// 写入错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 写失败信息到客户端
			writeFailResult(e.getMessage());
		}
	}
}
