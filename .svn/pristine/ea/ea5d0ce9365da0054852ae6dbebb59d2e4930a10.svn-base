package com.ht.persistence.dao.impl.system.notice;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;

import com.ht.persistence.dao.impl.base.BaseDaoImpl;
import com.ht.persistence.dao.inter.system.notice.UserNoticeViewDao;
import com.ht.persistence.model.system.notice.UserNoticeView;

/**
 * UserNoticeView 人员通知视图操作类
 * @author 平子金
 *
 */
public class UserNoticeViewDaoImpl extends BaseDaoImpl implements UserNoticeViewDao{

	@Override
	public void addUserNoticeView(UserNoticeViewDao usernoticeview) {
		this.save(usernoticeview);
	}
	
	/**
	 * 获取全部人员通知
	 */
	@Override
	public List<UserNoticeView> getUserNoticeView() {
		try {
			@SuppressWarnings("unchecked")
			List<UserNoticeView> result = this.findByNamedQuery("getUserNoticeView");
			return result;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	/**
	 * 根据人员Id获取通知
	 */
	@Override
	public List<UserNoticeView> getUserNoticeByUser(String userNo) {
		try {
			String hql = "from UserNoticeView where creator= ? and noticeType = '10301417286570002' order by releaseTime desc ";
			Query query = this.getSession().createQuery(hql);
			query.setParameter(0, userNo);
			List<UserNoticeView> result = query.list();
			return result;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	/**
	 * 根据人员Id获取通知
	 */
	@Override
	public List<UserNoticeView> getUserNoticeByUserId(String userId, String read,String noticeType) {
		try {
			String hql = "from UserNoticeView where userId= ? ";
			if(StringUtils.isNotBlank(read)){
				hql += " and isRead = ?";
			}
			hql += "and noticeType = ? order by releaseTime desc";
			Query query = this.getSession().createQuery(hql);
			query.setParameter(0, userId);
			if(StringUtils.isNotBlank(read)){
				query.setParameter(1, read);
			}
			query.setParameter(2, noticeType);
			List<UserNoticeView> result = query.list();
			return result;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	
	/**
	 * 根据人员Id获取通知
	 */
	@Override
	public int getUserNoticeCountByUserId(String userId, String read,String noticeType) {
		try {
			String hql = "select count(*) from UserNoticeView where userId= ? ";
			if(StringUtils.isNotBlank(read)){
				hql += " and isRead = ? ";
			}
			hql += "and noticeType = ? order by releaseTime desc";
			Query query = this.getSession().createQuery(hql);
			query.setParameter(0, userId);
			if(StringUtils.isNotBlank(read)){
				query.setParameter(1, read);
			}
			query.setParameter(2, noticeType);
			return Integer.valueOf(query.uniqueResult().toString());
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return 0;
		}
	}
	
	/**
	 * 根据用户工号查询用户通知
	 * @param userNo
	 */
	@Override
	public List<UserNoticeView> getUserNoticeByUserNo(String userNo) {
		try {
			String[] paramNames = {"user_no", "read"};
			String[] values = {userNo, "否"};
			
			@SuppressWarnings("unchecked")
			List<UserNoticeView> result = this.findByNamedQueryAndNamedParam("getUserNoticeByUserNo", paramNames, values);
			return result;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
}
