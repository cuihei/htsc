package com.ht.service.impl.system.notice;

import java.util.List;

import javax.annotation.Resource;

import com.ht.common.util.DataConverter;
import com.ht.common.util.GenerateSequenceUtil;
import com.ht.common.util.LogHelper;
import com.ht.persistence.dao.inter.system.notice.NoticeDao;
import com.ht.persistence.model.system.notice.Notice;
import com.ht.service.inter.system.notice.NoticeService;
public class NoticeServiceImpl implements NoticeService {
    
	/**
	 * 注入通知Dao
	 * */
	@Resource(name = "noticeDao") 
	private NoticeDao noticeDao;
	
	@Override
	public String addNotice(String noticeParam) throws Exception {
			// 增加notice
		try {
			Notice notice =(Notice)DataConverter.convertJson2Object(noticeParam, Notice.class);
			if(notice.getId()!=null){
				// new日期对象
				// 转换提日期输出格式
				noticeDao.modifyNotice(notice);
				return notice.getId();
			}else{
				String noticeId = GenerateSequenceUtil.generateSequenceNo();
				notice.setId(noticeId);
				noticeDao.addNotice(notice);
				return noticeId;
			}
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
		
	}

	/**
	 * 更新通知
	 */
	@Override
	public void modifyNotice(String noticeParam) throws Exception {
		try {
			Notice notice = (Notice) DataConverter.convertJson2Object(noticeParam, Notice.class);
			// 更新Notice
			noticeDao.modifyNotice(notice);
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}
	
	/**
	 * 删除通知
	 */
	@Override
	public void delNotice(String notice) throws Exception {
		try {
			List<Notice> list = (List<Notice>) DataConverter.convertJson2List(notice,Notice.class);
			for (int i = 0; i < list.size(); i++) {
				// 删除Notice
				noticeDao.delNotice(list.get(i));
			}
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}
	
	/**
	 * 获取所有通知
	 */
	@Override
	public List<Notice> getNotice() throws Exception {
		try {
			// 获取所有Notice
			return noticeDao.getNotice();
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}
	
	/**
	 * 根据id获取一条通知
	 */
	@Override
	public Notice getNotice(String id) throws Exception {
		try {
			Notice notice = new Notice();
			notice.setId(id);
			// 根据id获取Notice
			return noticeDao.getNotice(notice);
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}

	@Override
	public List<Notice> getNoticeViewByUser(String creator) throws Exception {
		try {
			Notice notice = new Notice();
			notice.setCreator(creator);
			notice.setNotice_Type("其他通知");
			// 根据id获取Notice
			return noticeDao.getNoticeViewByUser(notice);
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}
}
