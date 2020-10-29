package com.ht.service.inter.system.notice;

import java.util.List;

import com.ht.persistence.model.system.notice.Notice;

	/**
	 * 通知service
	 * @author 平子金
	 * */
public interface NoticeService {
	
	/**
	 *保存通知
	 * @param Notice Notice实体
	 * @return 
	 */
  public String addNotice(String notice) throws Exception;
  
  	/**
	 *修改通知
	 * @param Notice Notice实体
	 */
  public void modifyNotice(String notice) throws Exception;
  
  	/**
	 *删除通知
	 * @param Notice Notice实体
	 */
  public void delNotice(String id) throws Exception;
  
  	/**
	 *查询所有通知
	 * @param Notice Notice实体
	 */
  public List<Notice> getNotice() throws Exception;
  
  	/**
	 *查询一条通知
	 * @param Notice Notice实体
	 */
  public Notice getNotice(String id) throws Exception;
  
	/**
	 *查询所有通知
	 * @param Notice Notice实体
	 */
  public List<Notice> getNoticeViewByUser(String creator) throws Exception;
  
}
