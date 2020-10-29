package com.ht.service.inter.system.notice;

import java.util.List;

import com.ht.persistence.model.system.notice.UserNoticeView;

public interface UserNoticeViewService {
	/**
	 *保存人员通知关系
	 * @param UserNoticeView UserNoticeView实体
	 */
	public void addUserNoticeView(String usernoticeview) throws Exception;

	/**
	 *查询所有人员通知关系
	 * @param UserNoticeView UserNoticeView实体
	 */
	public List<UserNoticeView> getUserNoticeView() throws Exception;

	/**
	 * 根据登陆账号获取通知
	 * @param userNo
	 * @param read 
	 * @return
	 */
	public List<UserNoticeView> getUserNoticeByUserId(String userId,String read, String noticeType);
	
	/**
	 * 根据用户工号查询用户通知
	 * @param userNo
	 */
	public List<UserNoticeView> getUserNoticeByUserNo(String userNo);

	public List<UserNoticeView> getUserNoticeByUser(String userId);

	public int getUserNoticeCountByUserId(String userId, String read, String noticeType);
	

}
