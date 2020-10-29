package com.ht.persistence.dao.inter.correctionnoticesearch;

import java.util.List;

import com.ht.persistence.model.correctionnotice.CorrectionNotice;
import com.ht.persistence.model.correctionnotice.NoticeBaseData;
import com.ht.persistence.model.correctionnotice.NoticeYear;

/**
 *	改正通告查询Dao CorrectionNoticeDao 
 * @author huodesheng
 * @date 2016/10/18
 */
public interface CorrectionNoticeDao {
	/**
	 * 根据sql查询数据 
	 * @param sql 		查询语句
	 * @return
	 */
	public List<CorrectionNotice> queryByCondition(final String sql);
	/**
	 * 根据id查询数据
	 * @param id 
	 * @return
	 */
	public NoticeBaseData queryById(String id);
	/**
	 * 获取通告类型列表
	 * @return
	 */
	public List<NoticeBaseData> getDdlTypeList();
	/**
	 * 获取改正行为列表
	 * @return
	 */
	public List<NoticeBaseData> getDdlActList();

	
	/**
	 * 获取年份列表
	 * @return
	 */
	public List<NoticeYear> getYearList();
	
	
	
}
