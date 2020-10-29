package com.ht.service.impl.background.authority.auth;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.ht.common.util.DataConverter;
import com.ht.common.util.GenerateSequenceUtil;
import com.ht.common.util.LogHelper;
import com.ht.exception.DBException;
import com.ht.persistence.dao.inter.background.application.ApplicationDao;
import com.ht.persistence.dao.inter.background.authority.auth.RoleAppDao;
import com.ht.persistence.model.background.application.Application;
import com.ht.persistence.model.background.authority.auth.RoleApp;
import com.ht.persistence.model.background.authority.role.RoleApplicationRel;
import com.ht.service.inter.background.authority.auth.RoleAppService;

/**
 * RoleAuthService接口的实现类
 * @author 侯晨
 */
public class RoleAppServiceImpl implements RoleAppService {
    /**
     * 注入roleAuthDao
     */
	@Resource(name = "roleAppDao") 
	private RoleAppDao roleAppDao;
	
	 /**
     * 注入应用资源DAO ApplicationDao
     */
	@Resource(name = "applicationDao")
	private ApplicationDao applicationDao;
	
	/**
	 * 新增RoleAuth
	 * @param roleAuthParam 
	 * @throws Exception
	 */
	@Override
	public void addRoleAuth(String roleAuthParam) throws Exception {
		try {
			//将用户String类型转成Role类型
			RoleApp RoleApp = (RoleApp) DataConverter.convertJson2Object(roleAuthParam, RoleApp.class);
			roleAppDao.addRoleAuth(RoleApp);
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}

	/**
	 * 更新RoleAuth
	 * @param roleAuthParam 
	 * @throws Exception
	 */
	@Override
	public void modifyRoleAuth(String roleAuthParam) throws Exception {
		try {
			//将用户String类型转成Role类型
			RoleApp RoleApp = (RoleApp) DataConverter.convertJson2Object(roleAuthParam, RoleApp.class);
			roleAppDao.modifyRoleAuth(RoleApp);
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}

	/**
	 * 删除RoleAuth
	 * @param id 
	 * @throws Exception
	 */
	@Override
	public void delRoleAuth(String id) throws Exception {
		try {
			RoleApp RoleApp = new RoleApp();
			RoleApp.setAppId(id);
			roleAppDao.delRoleAuth(RoleApp);
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}
	
	/**
	 * 获取所有RoleAuth
	 * @param organizationParm 
	 * @throws Exception
	 */
	@Override
	public List<RoleApp> getRoleAuth() throws Exception {
		try {
			// 获取所有
			return roleAppDao.getRoleAuth();
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}
	
	/**
	 * 根据roleId获取RoleAuth
	 * @param roleId 
	 * @throws Exception
	 */
	@Override
	public List<RoleApp> getRoleAuth(String roleId) throws Exception {
		try {
			// 根据roleId获取RoleAuth
			RoleApp RoleApp = new RoleApp();
			RoleApp.setRoleId(roleId);
			return roleAppDao.getRoleAuth(RoleApp);
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}

	/**
	  * 插入角色与资源之间的关系
	  * @param roleId 角色ID
	  * @param apps 选中的资源id
	  * @throws Exception
	  */
	@Override
	public void addRoleApps(String roleId, String apps) throws Exception {
		try {
			// 将前台传回来的用户标识转化成list
			List<Application> appList = (List<Application>) DataConverter.convertJson2List(apps, Application.class);
			RoleApp ra = new RoleApp();
			ra.setRoleId(roleId);
			// 根据roleId取得所有该角色下的资源
			List<RoleApp> list = roleAppDao.getRoleAuth(ra);
			roleAppDao.delAllRoleAuth(list);
			// 再根据选中的人员重新插入新的角色和资源的关系
			for (int i = 0; i < appList.size(); i++) {
				Application app = appList.get(i);
				// 插入新的关系数据
				RoleApp roleApp = new RoleApp();
				roleApp.setId(GenerateSequenceUtil.generateSequenceNo());
				roleApp.setAppId(app.getId());
				roleApp.setRoleId(roleId);
				roleApp.setWrite(app.getWrite());
				roleApp.setCreationDate(new Date());
				roleApp.setModifyDate(new Date());
				roleAppDao.addRoleAuth(roleApp);
			}
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw new DBException("数据库操作错误");
		}
		
	}

	/**
	 * 根据roleid查询角色权限关系
	 * @param roleId 角色ID
	 * @throws Exception
	 */
	@Override
	public List<RoleApplicationRel> getRoleAppsByRoleId(String roleappsParam)
			throws Exception {
		try {
			List<RoleApplicationRel> result = new ArrayList<RoleApplicationRel>();
			RoleApp roleApps = (RoleApp) DataConverter.convertJson2Object(roleappsParam, RoleApp.class);
			
			// 首先查询资源表所有数据
			List<Application>  appsList = applicationDao.getApplication();
			// 查出当前角色下的资源
			List<RoleApp> list = roleAppDao.getRoleAuth(roleApps);
			for (int i = 0; i < appsList.size(); i++) {
				RoleApplicationRel ru = new RoleApplicationRel();
				Application a = appsList.get(i);
				ru.setId(a.getId());
				ru.setAppCode(a.getAppCode());
				ru.setAppContent(a.getAppContent());
				ru.setAppName(a.getAppName());
				ru.setAppParentId(a.getAppParentId());
				ru.setAppType(a.getAppType());
				ru.setAppUrl(a.getAppUrl());
				for (int j = 0; j < list.size(); j++) {
					if(list.get(j).getAppId().equals(a.getId())){
						ru.setRoleId(list.get(j).getRoleId());
						ru.setWrite(list.get(j).getWrite());
					}
				}
				result.add(ru);
			}
			return result;
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}
	
	/**
	 * 根据roleid查询角色权限关系
	 * @param roleId 角色ID
	 * @throws Exception
	 */
	@Override
	public List<RoleApplicationRel> getRoleAppRelByRoleId(String roleId)
			throws Exception {
		try {
			List<RoleApplicationRel> result = new ArrayList<RoleApplicationRel>();
			RoleApp roleApps = new RoleApp();
			roleApps.setRoleId(roleId);
			
			// 首先查询资源表所有数据
			List<Application>  appsList = applicationDao.getApplication();
			// 查出当前角色下的资源
			List<RoleApp> list = roleAppDao.getRoleAuth(roleApps);
			for (int i = 0; i < appsList.size(); i++) {
				RoleApplicationRel ru = new RoleApplicationRel();
				Application a = appsList.get(i);
				ru.setId(a.getId());
				ru.setAppCode(a.getAppCode());
				ru.setAppContent(a.getAppContent());
				ru.setAppName(a.getAppName());
				ru.setAppParentId(a.getAppParentId());
				ru.setAppType(a.getAppType());
				ru.setAppUrl(a.getAppUrl());
				for (int j = 0; j < list.size(); j++) {
					if(list.get(j).getAppId().equals(a.getId())){
						ru.setRoleId(list.get(j).getRoleId());
					}
				}
				result.add(ru);
			}
			return result;
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}

	/**
	 * 通过角色Id获取资源详细信息
	 * @param roleId 角色id
	 * @return List<RoleApplicationRel> 返回资源详细信息结果集
	 * @exception
	 * */
	@Override
	public List<RoleApplicationRel> getRoleAppByRoleId(String roleId)
			throws Exception {
		try {
			// 返回资源详细信息结果集
			List<RoleApplicationRel> result = new ArrayList<RoleApplicationRel>();
			
			RoleApp roleApps = new RoleApp();
			roleApps.setRoleId(roleId);
			
			// 通过角色Id获取资源Id,查出当前角色下的资源
			List<RoleApp> list = roleAppDao.getRoleAuth(roleApps);
			for (int i = 0; i < list.size(); i++) {
				// 通过资源ID获取到资源详情
				Application a = new Application();
				a.setId(list.get(i).getAppId());
				a = applicationDao.getApplication(a);
				// 为了避免显示index页面出错，如果得到为空则不加入返回结果集
				if(a!=null){
					RoleApplicationRel ru = new RoleApplicationRel();
					ru.setId(a.getId());
					ru.setAppCode(a.getAppCode());
					ru.setAppContent(a.getAppContent());
					ru.setAppName(a.getAppName());
					ru.setAppParentId(a.getAppParentId());
					ru.setAppType(a.getAppType());
					ru.setAppUrl(a.getAppUrl());
					ru.setRoleId(roleId);
					ru.setWrite(list.get(i).getWrite());
					ru.setAppImg(a.getAppImg());
					result.add(ru);
				}
			}
			return result;
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}

	/**
	 *根据appId查询角色权限关系
	 *@param appId 资源ID
	 *@return List<RoleApplicationRel> 角色权限关系对象集合
	 *@throws Exception
	 */
	public List<RoleApp> getRoleAppByAppId(String appId)
			throws Exception {
		try{
			RoleApp roleApps = new RoleApp();
			roleApps.setAppId(appId);
			List<RoleApp> rarList = roleAppDao.getRoleAuth(roleApps);
			return rarList;
		}catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}
}