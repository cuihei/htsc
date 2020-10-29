package com.ht.service.impl.background.application;

import java.util.List;

import javax.annotation.Resource;

import com.ht.common.export.ExcelUtil;
import com.ht.common.util.DataConverter;
import com.ht.common.util.GenerateSequenceUtil;
import com.ht.common.util.LogHelper;
import com.ht.common.util.Tree;
import com.ht.common.util.TreeNode;
import com.ht.exception.DBException;
import com.ht.persistence.dao.inter.background.application.ApplicationDao;
import com.ht.persistence.dao.inter.background.authority.auth.RoleAppDao;
import com.ht.persistence.model.background.application.Application;
import com.ht.persistence.model.background.authority.auth.RoleApp;
import com.ht.service.inter.background.application.ApplicationService;

/**
 * 应用资源ApplicationService接口的实现类
 * @author zhongquanyou
 */
public class ApplicationServiceImpl implements ApplicationService {

	// 注入应用资源DAO ApplicationDao
	@Resource(name = "applicationDao")
	private ApplicationDao applicationDao;

	// 注入应用资源DAO RoleAppDao
	@Resource
	private RoleAppDao roleAppDao;
	/**
	 * 新增应用资源
	 * 
	 * @param appParam json对象
	 * @throws Exception
	 */
	public void addApplication(String appParam) throws Exception {
		try {
			// 将String转换为Application对象
			Application app = (Application) DataConverter.convertJson2Object(
					appParam, Application.class);
			// Application对象的AppId设置值
			app.setId(GenerateSequenceUtil.generateSequenceNo());
			//获取Application对象的appParentId属性
			
			// 执行保存操作
			applicationDao.addApplication(app);
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(), e);
			// 抛出异常
			throw e;
		}
	}

	/**
	 * 修改应用资源
	 * @param appParam json对象
	 * @throws Exception
	 */
	public void modifyApplication(String appParam) throws Exception {
		try {
			// 将String转换为Application对象
			Application app = (Application) DataConverter.convertJson2Object(
					appParam, Application.class);
			// 获取Application对象的AppId属性
			String appId = app.getId();
			if(appId != null){
				// 更新Application
				applicationDao.modifyApplication(app);
			}else{
				// Application对象的AppId设置值
				app.setId(GenerateSequenceUtil.generateSequenceNo());
				applicationDao.addApplication(app);
			}
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(), e);
			// 抛出异常
			throw e;
		}
	}

	/**
	 * 删除应用资源
	 * @param appParam json对象
	 * @throws Exception
	 */
	public String delApplication(String appParam) throws Exception {
		try {
			// 将用户String类型转成Appliaction对象
			Application app = (Application) DataConverter.convertJson2Object(appParam,Application.class);
			
			//
			RoleApp ra = new RoleApp();
			ra.setAppId(app.getId());
			List<RoleApp> roleApp = roleAppDao.getAppsByAppId(ra);
			if(roleApp.size() > 0){
				return "del";
			}
			List<Application> appList = applicationDao.getApplicationTree(app);
			for (int i = 0; i < appList.size(); i++) {
					// 删除User
					applicationDao.delApplication(appList.get(i));
				}
						
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(), e);
			// 抛出异常
			throw e;
		}
		return "success";
	}

	/**
	 * 查询所有应用资源
	 * @return List<Application> Application对象集合
	 * @throws Exception
	 */
	public List<Application> getApplication() throws Exception {
		try {
			// 获取所有Application对象
			return applicationDao.getApplication();
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(), e);
			// 抛出异常
			throw e;
		}
	}

	/**
	 * 查询一条应用资源
	 * 
	 * @param appId 应用资源ID 
	 * @return Application Application对象
	 * @throws Exception
	 */
	public Application getApplication(String appParam) throws DBException {
		try {
			// 创建Application对象
			Application app = new Application();
			app.setId(appParam);
			// 根据appId获取Application
			return applicationDao.getApplication(app);
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(), e);
			// 抛出异常
			throw new DBException("数据库操作错误");
		}
	}

	/**
	 * 获取Application对象父节点下的所有子节点，形成树结构
	 * @param RoleApplicationRel Application对象
	 * @return String Application对象的集合
	 */
	public String getApplicationTree(String app)
			throws Exception {
		try {
			Application appParam = (Application)DataConverter.convertJson2Object(app, Application.class);
			List<Application> result = applicationDao.getApplicationTree(appParam);
			Tree tree = new Tree();
			TreeNode root = null;
			for (int i = 0; i < result.size(); i++) {
				TreeNode node = new TreeNode(result.get(i).getId(), result.get(i).getAppName(), result.get(i).getAppParentId());
				if (result.get(i).getAppParentId() == null) {
					root = node;
				}
				else{
					tree.addNode(node);
				}
			}
			return tree.getTreeJson(tree, root);
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(), e);
			// 抛出异常
			throw e;
		}
	}

	/**
	 * 获取Application对象父节点下的所有子节点，形成树结构
	 * @return List<Application> Application对象的集合
	 */
	@Override
	public String getApplicationChilds() {
		try {
			List<Application> result = applicationDao.getApplicationChilds();
			Tree tree = new Tree();
			TreeNode root = null;
			for (int i = 0; i < result.size(); i++) {
				TreeNode node = new TreeNode(result.get(i).getId(), result.get(i).getAppName(), result.get(i).getAppParentId());
				if (result.get(i).getAppParentId() == null) {
					root = node;
				}
				else{
					tree.addNode(node);
				}
			}
			return tree.getTreeJson(tree, root);
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(), e);
			// 抛出异常
			throw e;
		}
	}
	
	/**
	 * 导出Excel文档
	 * @throws Exception 
	 */
	public void exportExcel() throws Exception {
		// 获取应用资源对象集合
		List<Application> appList = applicationDao.getApplication();
		// 将资源类型type转成name
		for (int i = 0; i < appList.size(); i++) {
			String type = appList.get(i).getAppType()==null?"":appList.get(i).getAppType();
			if(type.equals("10241537286430045")){
				appList.get(i).setAppType("菜单项");
			}else if(type.equals("10241538529740071")){
				appList.get(i).setAppType("输入项");
			}else if(type.equals("1024153811640058")){
				appList.get(i).setAppType("页面");
			}else{
				appList.get(i).setAppType("");
			}
		}
		// 创建导出工具类
		ExcelUtil<Application> utilApp = new ExcelUtil<Application>(Application.class);  
		utilApp.getListToExcel(appList, "资源信息","资源信息导出");  
	}
}
