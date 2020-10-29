package com.ht.service.impl.system.notice;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.util.CollectionUtils;

import com.ht.common.util.DataConverter;
import com.ht.common.util.GenerateSequenceUtil;
import com.ht.common.util.LogHelper;
import com.ht.persistence.dao.inter.background.organization.organization.OrganizationUsersRelationDao;
import com.ht.persistence.dao.inter.system.notice.NoticeDao;
import com.ht.persistence.dao.inter.system.notice.UserNoticeDao;
import com.ht.persistence.model.background.organization.employee.User;
import com.ht.persistence.model.background.organization.organization.OrganizationUsersRelation;
import com.ht.persistence.model.system.notice.Notice;
import com.ht.persistence.model.system.notice.UserNotice;
import com.ht.persistence.model.system.notice.UserNoticeView;
import com.ht.service.inter.system.notice.UserNoticeService;
public class UserNoticeServiceImpl implements UserNoticeService {
    
	
	/**
	 * 注入组织机构人员organizationDao
	 * */
	@Resource(name = "organizationUsersRelationDao") 
	private OrganizationUsersRelationDao organizationUsersRelationDao;
	
	/**
	 * 注入用户通知关系Dao
	 * */
	@Resource(name = "usernoticeDao") 
	private UserNoticeDao usernoticeDao;
	
	/**
	 * 注入通知service
	 * */
	@Resource
	private NoticeDao noticeDao;
	
	/**
	 * 新增用户通知
	 * @param usernoticeParam String类型的用户通知关系对象
	 * @throws Exception
	 */
	@Override
	public void addUserNotices(String usernoticeParam,String userparam,String orgparam) throws Exception {
		try {
			List<UserNotice> list =(List<UserNotice>)DataConverter.convertJson2List(usernoticeParam, UserNotice.class);
			List<String> userList = new ArrayList<String>();
			if(userparam != null){
				if(userparam.split(",").length>0){
					for (int i = 0; i < userparam.split(",").length; i++) {
						userList.add(userparam.split(",")[i]);
					}
				}
			}
			
			if(orgparam != null){
				if(orgparam.split(",").length>0){
					for (int j = 0; j < orgparam.split(",").length; j++) {
						//得到组织机构用户
						OrganizationUsersRelation o= new OrganizationUsersRelation();
						o.setOrgId(orgparam.split(",")[j]);
						List<OrganizationUsersRelation>  orgList = organizationUsersRelationDao.getUsersByOrgId(o);
						if(orgList != null){
							if(orgList.size()>0){
								for (int i = 0; i < orgList.size(); i++) {
									String userId = orgList.get(i).getUserId();
									if(!userList.contains(userId)){
										userList.add(userId);
									}
								}
							}
						}
					}
				}
			}
			
			if(!CollectionUtils.isEmpty(list)){
				for (int i = 0; i < list.size(); i++) {
					if(userList != null){
						if(userList.size()>0){
							for (int j = 0; j < userList.size(); j++) {
								UserNotice usernotice = new UserNotice();
								usernotice.setId(GenerateSequenceUtil.generateSequenceNo());
								usernotice.setNotice_id(list.get(i).getId());
								usernotice.setUser_id(userList.get(j));
								usernotice.setIs_read("否");
								// new日期对象
								Date date = new Date();
								// 转换提日期输出格式
								SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
								// 设置发布时间
								usernotice.setRelease_time(dateFormat.format(date));
								usernoticeDao.addUserNotice(usernotice);
							}
						}
					}
				}
			}
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}
	
	/**
	 * 新增用户通知
	 * @param usernoticeParam String类型的用户通知关系对象
	 * @throws Exception
	 */
	@Override
	public void addUserNotice(String noticeId,String userId,String isRead) throws Exception {
		try {
			UserNotice usernotice = new UserNotice();
			usernotice.setId(GenerateSequenceUtil.generateSequenceNo());
			usernotice.setNotice_id(noticeId);
			usernotice.setUser_id(userId);
			usernotice.setIs_read("否");
			// new日期对象
			Date date = new Date();
			// 转换提日期输出格式
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			// 设置发布时间
			usernotice.setRelease_time(dateFormat.format(date));
			usernoticeDao.addUserNotice(usernotice);
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}
	
	/**
	 * 获取用户通知列表
	 * @throws Exception
	 */
	@Override
	public List<UserNotice> getUserNotice() throws Exception {
		try {
			// 获取所有UserNotice
			return usernoticeDao.getUserNotice();
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}
	
	
	/**
	 * 根据用户通知关系编号获取用户通知关系信息
	 * @param id 用户通知关系编号
	 * @throws Exception
	 */
	@Override
	public UserNotice getUserNotice(String id) throws Exception {
		try {
			UserNotice usernotice = new UserNotice();
			usernotice.setId(id);
			// 根据id获取UserNotice
			return usernoticeDao.getUserNotice(usernotice);
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}
	
	/**
	 * 更新用户通知表
	 */
	@Override
	public void modifyUserNotice(String userNoticeId) throws Exception {
		try {
			List<UserNoticeView> list = (List<UserNoticeView>) DataConverter.convertJson2List(userNoticeId,UserNoticeView.class);
			for(int i = 0; i < list.size(); i++){
				UserNotice usernotice = new UserNotice();
				usernotice.setId(list.get(i).getId());
				usernotice = usernoticeDao.getUserNotice(usernotice);
				// 设置是否已读为：是
				usernotice.setIs_read("是");
				// 执行更新
				usernoticeDao.modifyUserNotice(usernotice);
			}
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}
	
	/**
	 * 删除用户通知
	 */
	@Override
	public void delUserNotice(String id) throws Exception {
		
	}
	
	/**
	 * 根据通知Id，用户Id获取一条数据
	 */
	@Override
	public UserNotice getUserNotice(String noticeId, String userId) {
		try{
			UserNotice userNotice = new UserNotice();
			userNotice.setNotice_id(noticeId);
			userNotice.setUser_id(userId);
			return usernoticeDao.getUserNoticeByUserIdAndNoticeId(userNotice);
		}catch(Exception e) {
			LogHelper.ERROR.log(e.getMessage(),e);
			throw e;
		}
	}
	
	/**
	 * 根据通知Id获取数据
	 */
	@Override
	public List<UserNotice> getUserNoticeByNoticeId(String noticeId) {
		try {
			return usernoticeDao.getUserNoticeByNoticeId(noticeId);
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}
	
	/**
	 * 创建并且发布一个通知
	 * @param noticeType 通知类型
	 * @param title 标题
	 * @param noticeDescription 内容
	 * @param users 通知人列表
	 */
	@Override
	public void publishNotice(String noticeType,String title,String noticeDescription,List<User> users){
		// 创建新通知
		Notice notice = new Notice();
		notice.setNotice_Type(noticeType);
		notice.setTitle(title);
		notice.setDescription(noticeDescription);
		String noticeId = GenerateSequenceUtil.generateSequenceNo();
		notice.setId(noticeId);
		noticeDao.addNotice(notice);
		// 发送通知
		for (int i = 0; i < users.size(); i++)
		{
			UserNotice usernotice = new UserNotice();
			usernotice.setId(GenerateSequenceUtil.generateSequenceNo());
			usernotice.setNotice_id(noticeId);
			usernotice.setUser_id(users.get(i).getId());
			usernotice.setIs_read("否");
			// 设置发布时间
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = new Date();
			usernotice.setRelease_time(dateFormat.format(date));
			usernoticeDao.addUserNotice(usernotice);
		}
	}
}
