package com.ht.persistence.dao.inter.system.notice;

import java.util.List;

import com.ht.persistence.model.system.notice.UserNotice;

/**
 * UserNoticeDao
 * @author 平子金
*/
public interface UserNoticeDao {
	 
	/**
	 * 增加一个 UserNotice
	 * @param UserNotices UserNotice实体
	 */
	public void addUserNotice( UserNotice  usernotice);

	/**
	 * 更新一个 UserNotice
	 * @param UserNotices UserNotice实体
	 */
	public void modifyUserNotice( UserNotice  usernotice);

	/**
	 * 删除 UserNotice 相关
	 *@param UserNotices UserNotice实体
	 */
	public void delUserNotice(UserNotice usernotice);
	
	/**
	 * 获取所有 UserNotice
	 * @param UserNotices UserNotice实体
	 */
	public List< UserNotice> getUserNotice();

	/**
	 * 获取一条UserNotice
	 * @param id UserNotice主键
	 * @param UserNotices UserNotice实体
	 * 
	 */
	public  UserNotice getUserNotice(UserNotice usernotice);
	
	/**
	 * 根据用户Id，通知Id获取一条数据
	 * @param noticeId
	 * @param userId
	 * @return
	 */
	public UserNotice getUserNoticeByUserIdAndNoticeId(UserNotice userNotice);
	
	/**
	 * 根据通知Id获取数据
	 * @param noticeId
	 * @return
	 */
	public List<UserNotice> getUserNoticeByNoticeId(String noticeId);
}
