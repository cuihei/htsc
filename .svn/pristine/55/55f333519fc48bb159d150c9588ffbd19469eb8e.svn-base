package com.ht.service.impl.system.notice;

import java.util.List;

import javax.annotation.Resource;

import com.ht.common.util.DataConverter;
import com.ht.common.util.LogHelper;
import com.ht.persistence.dao.inter.system.notice.UserNoticeViewDao;
import com.ht.persistence.model.system.notice.UserNoticeView;
import com.ht.service.inter.system.notice.UserNoticeViewService;

public class UserNoticeViewServiceImpl implements UserNoticeViewService{
	/**
	 * 注入人员通知关系Dao
	 * */
	@Resource(name = "usernoticeviewDao") 
	private UserNoticeViewDao usernoticeviewDao;
	
	/**
	 * 增加人员通知关系ServiceImpl
	 * */
	@Override
	public void addUserNoticeView(String usernoticeviewParam) throws Exception {
		// 增加UserNoticeView
		try {
			UserNoticeView usernoticeview =(UserNoticeView)DataConverter.convertJson2Object(usernoticeviewParam,UserNoticeView.class);
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}

	}
	
	/**
	 * 查询人员通知关系所有 ServiceImpl
	 * */
	@Override
	public List<UserNoticeView> getUserNoticeView() throws Exception {
		try {
			// 获取所有UserNoticeView
			return usernoticeviewDao.getUserNoticeView();
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}
	
	/**
	 * 根据登陆账号获取通知
	 */
	@Override
	public List<UserNoticeView> getUserNoticeByUserId(String userId, String read,String noticeType) {
		try{
			return usernoticeviewDao.getUserNoticeByUserId(userId,read,noticeType);
		}catch(Exception e){
			LogHelper.ERROR.log(e.getMessage());
			throw e;
		}
	}
	
	/**
	 * 根据登陆账号获取通知
	 */
	@Override
	public int getUserNoticeCountByUserId(String userId, String read,String noticeType) {
		try{
			return usernoticeviewDao.getUserNoticeCountByUserId(userId,read,noticeType);
		}catch(Exception e){
			LogHelper.ERROR.log(e.getMessage());
			throw e;
		}
	}
	
	
	/**
	 * 根据登陆账号获取通知
	 */
	@Override
	public List<UserNoticeView> getUserNoticeByUser(String userNo) {
		try{
			return usernoticeviewDao.getUserNoticeByUser(userNo);
		}catch(Exception e){
			LogHelper.ERROR.log(e.getMessage());
			throw e;
		}
	}
	
	/**
	 * 根据用户工号查询用户通知
	 * @param userNo
	 */
	@Override
	public List<UserNoticeView> getUserNoticeByUserNo(String userNo) {
		try{
			return usernoticeviewDao.getUserNoticeByUserNo(userNo);
		}catch(Exception e){
			LogHelper.ERROR.log(e.getMessage());
			throw e;
		}
	}


}
