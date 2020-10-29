package com.ht.persistence.dao.impl.system.notice;

import java.util.List;

import com.ht.persistence.dao.impl.base.BaseDaoImpl;

import com.ht.persistence.dao.inter.system.notice.UserNoticeDao;
import com.ht.persistence.model.system.notice.UserNotice;

/**
 * UserNotice 数据映射操作类
 * @author 平子金
 *
 */
public class UserNoticeDaoImpl extends BaseDaoImpl implements UserNoticeDao{

	/**
	 * 增加一个UserNotice
	 * @param UserNotices UserNotice实体
	 */
	@Override
	public void addUserNotice(UserNotice usernotice) {
		this.save(usernotice);
	}

	/**
	 * 更新一个UserNotice
	 * @param UserNotices UserNotice实体
	 */
	@Override
	public void modifyUserNotice(UserNotice usernotice) {
		this.update(usernotice);
	}

	/**
	 * 删除UserNotice相关
	 * @param id UserNotice实体
	 */
	@Override
	public void delUserNotice(UserNotice usernotice) {
		this.delete(usernotice);
	}

	/**
	 * 获取所有UserNotice
	 * return UserNotice
	 */
	@Override
	public List<UserNotice> getUserNotice() {
		try {

			@SuppressWarnings("unchecked")
			List<UserNotice> result = this.findByNamedQuery("getUserNotice");
			return result;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	/**
	 * 获取一条UserNotice
	 * @param id UserNotice主键
	 * @return UserNotice实体
	 */
	@Override
	public UserNotice getUserNotice(UserNotice usernotice) {
		@SuppressWarnings("unchecked")
		List<UserNotice> result = this.findByNamedQueryAndNamedParam("getUserNoticeById", "id", usernotice.getId());
		if(result.size()>0){
			return result.get(0);
		}
		return null;
	}
	
	/**
	 * 根据用户Id，通知Id获取一条数据
	 */
	@Override
	public UserNotice getUserNoticeByUserIdAndNoticeId(UserNotice userNotice) {
		try {
			// 获取通知Id
			String noticeId = userNotice.getNotice_id();
			// 获取用户Id
			String userId = userNotice.getUser_id();
			String[] paramNames = {"noticeId","userId"};
			String[] values = {noticeId,userId};
			
			@SuppressWarnings("unchecked")
			List<UserNotice> result = this.findByNamedQueryAndNamedParam("getUserNoticeByUserIdAndNoticeId", paramNames, values);
			if(result.size() >0 ){
				return result.get(0);
			}
			return null;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	/**
	 * 根据通知Id获取数据
	 */
	@Override
	public List<UserNotice> getUserNoticeByNoticeId(String noticeId) {
		try {
			@SuppressWarnings("unchecked")
			List<UserNotice> result = this.findByNamedQueryAndNamedParam("getUserNoticeByNoticeId", "noticeId", noticeId);
			if(result.size() > 0 ){
				return result;
			}
			return null;
		} catch (Exception e) {
			return null;
		}
	}
   
}
