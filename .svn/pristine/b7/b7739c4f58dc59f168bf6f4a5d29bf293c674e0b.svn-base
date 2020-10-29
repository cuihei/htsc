package com.ht.persistence.dao.impl.system.notice;

import java.util.List;

import com.ht.persistence.dao.impl.base.BaseDaoImpl;
import com.ht.persistence.dao.inter.system.notice.NoticeDao;
import com.ht.persistence.model.system.notice.Notice;

/**
 * Notice 数据映射操作类
 * @author 平子金
 *
 */
public class NoticeDaoImpl extends BaseDaoImpl implements NoticeDao{

	/**
	 * 增加一个Notice
	 * @param notice notice 实体
	 */
	@Override
	public void addNotice(Notice notice) {
		this.save(notice);
	}

	/**
	 * 更新一个Notice
	 * @param notice notice实体
	 */
	@Override
	public void modifyNotice(Notice notice) {
		this.update(notice);
	}

	/**
	 * 删除Notice 相关
	 * @param id Notice主键
	 */
	@Override
	public void delNotice(Notice notice ) {
		this.delete(notice);
	}

	/**
	 * 获取所有Notice
	 * @return Notice列表
	 */
	@Override
	public List<Notice> getNotice() {
		try {

			@SuppressWarnings("unchecked")
			List<Notice> result = this.findByNamedQuery("getNoticeView");
			return result;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	/**
	 * 获取一条Notice
	 * @param id Notice主键
	 * @return Notice实体
	 */
	@Override
	public Notice getNotice(Notice notice) {
		@SuppressWarnings("unchecked")
		List<Notice> result = this.findByNamedQueryAndNamedParam("getNoticeById", "id", notice.getId());
		if(result.size()>0){
			return result.get(0);
		}
		return null;
	}

	@Override
	public List<Notice> getNoticeViewByUser(Notice notice) {
		String[] names = {"creator","notice_Type"};
		String[] params = {notice.getCreator(),notice.getNotice_Type()};
		@SuppressWarnings("unchecked")
		List<Notice> result = this.findByNamedQueryAndNamedParam("getNoticeViewByUser", names, params);
		if(result.size()>0){
			return result;
		}
		return null;
	}
   
}
