package com.ht.service.inter.system.notice;

import java.util.List;

import com.ht.persistence.model.background.organization.employee.User;
import com.ht.persistence.model.system.notice.UserNotice;

/**
 * 用户通知关系service
 * @author 平子金
 */
public interface UserNoticeService
{

	/**
	 * 保存用户通知关系
	 * @param UserNotices UserNotice实体
	 */
	public void addUserNotices(String Usernotice, String user,String org) throws Exception;

	/**
	 * 修改用户通知关系
	 * @param userNoticeId UserNotice实体
	 */
	public void modifyUserNotice(String userNoticeId) throws Exception;

	/**
	 * 删除用户通知关系
	 * @param UserNotices UserNotice实体
	 */
	public void delUserNotice(String id) throws Exception;

	/**
	 * 查询所有用户通知关系
	 * @param UserNotices UserNotice实体
	 */
	public List<UserNotice> getUserNotice() throws Exception;

	/**
	 * 查询一条用户通知关系
	 * @param UserNotices UserNotice实体
	 */
	public UserNotice getUserNotice(String id) throws Exception;

	/**
	 * 根据通知Id, 用户Id获取一条数据
	 * @param noticeId
	 * @param userId
	 * @return
	 */
	public UserNotice getUserNotice(String noticeId, String userId);

	/**
	 * 根据通知Id获取数据
	 */
	public List<UserNotice> getUserNoticeByNoticeId(String noticeId);

	public void addUserNotice(String noticeId, String userId, String isRead) throws Exception;

	/**
	 * 创建并且发布一个通知
	 * @param noticeType 通知类型
	 * @param title 标题
	 * @param noticeDescription 内容
	 * @param users 通知人列表
	 */
	public void publishNotice(String noticeType, String title, String noticeDescription, List<User> users);
}
