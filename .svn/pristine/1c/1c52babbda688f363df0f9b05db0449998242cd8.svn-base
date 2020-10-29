package com.ht.persistence.dao.inter.system.notice;

import java.util.List;

import com.ht.persistence.model.system.notice.Notice;
import com.ht.persistence.model.system.notice.UserNoticeView;

/**
 * usernoticeviewDao
 * @author 平子金
*/
public interface UserNoticeViewDao {
	
	/**
	 * 增加一个UserNoticeView
	 * @param UserNoticeViewService UserNoticeView实体
	 */
	public void addUserNoticeView(UserNoticeViewDao usernoticeview);
	
	/**
	 * 获取所有UserNoticeView
	 * @param UserNoticeViewService UserNoticeView实体
	 */
	public List<UserNoticeView> getUserNoticeView();
	
	/**
	 * 根据登陆账号获取通知
	 * @param userNo 
	 * @param read 
	 * @return
	 */
	public List<UserNoticeView> getUserNoticeByUserId(String userId, String read, String noticeType);
	
	/**
	 * 根据用户工号查询用户通知
	 * @param userNo
	 */
	public List<UserNoticeView> getUserNoticeByUserNo(String userNo);

	public List<UserNoticeView> getUserNoticeByUser(String userId);

	public int getUserNoticeCountByUserId(String userId, String read, String noticeType);
}
