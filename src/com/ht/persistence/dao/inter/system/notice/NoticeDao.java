package com.ht.persistence.dao.inter.system.notice;

import java.util.List;
import com.ht.persistence.model.system.notice.Notice;

/**
 * noticeDao
 * @author 平子金
*/
public interface NoticeDao {
		
	/**
	 * 增加一个Notice
	 * @param Notice Notice实体
	 */
	public void addNotice(Notice notice);

	/**
	 * 更新一个Notice
	 * @param Notice Notice实体
	 */
	public void modifyNotice(Notice notice);

	/**
	 * 删除Notice 相关
	 *@param Notice Notice实体
	 */
	public void delNotice(Notice notice);
	
	/**
	 * 获取所有Notice
	 * @param Notice Notice实体
	 */
	public List<Notice> getNotice();

	/**
	 * 获取一条Notice
	 * @param id Notice主键
	 * @param Notice Notice实体
	 * 
	 */
	public Notice getNotice(Notice notice);
	
	/**
	 * 获取所有Notice
	 * @param Notice Notice实体
	 */
	public List<Notice> getNoticeViewByUser(Notice notice);
}
