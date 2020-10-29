package com.ht.persistence.dao.impl.correctionnoticesearch;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SQLQuery;
import com.ht.persistence.dao.impl.base.BaseDaoImpl;

import com.ht.persistence.dao.inter.correctionnoticesearch.CorrectionNoticeDao;
import com.ht.persistence.model.correctionnotice.CorrectionNotice;
import com.ht.persistence.model.correctionnotice.NoticeBaseData;
import com.ht.persistence.model.correctionnotice.NoticeYear;
/**
 * CorrectionNoticeDaoImpl 改正通告查询Dao实现类
 * @author huodesheng
 * @date 2016/10/18
 */
public class CorrectionNoticeDaoImpl extends BaseDaoImpl  implements CorrectionNoticeDao {
	/**
	 * 根据sql查询数据 
	 * @param sql 		查询语句
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<CorrectionNotice> queryByCondition(final String sql) {
		try {
			SQLQuery query = this.getSession().createSQLQuery(sql);
			query.addEntity(CorrectionNotice.class);
			List<CorrectionNotice> list = query.list();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 根据id查询数据
	 * @param id 
	 * @return
	 */
	@Override
	public NoticeBaseData queryById(String id) {
		try {
			SQLQuery query = this.getSession().createSQLQuery("select id,CnDocumentContent AS name,SOURCE AS EnName from item where id=:id");
			query.setString("id", id);
			query.addEntity(NoticeBaseData.class);
			List<NoticeBaseData> list = query.list();
			if(list!=null && list.size()>0){
				return list.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 获取通告类型列表
	 * @return
	 */
	@Override
	public List<NoticeBaseData> getDdlTypeList() {
		try {
			SQLQuery query = this.getSession().createSQLQuery("select * from NOTICETYPE");///CN表中已添加视频 2018.7.4
			query.addEntity(NoticeBaseData.class);
			@SuppressWarnings("unchecked")
			List<NoticeBaseData> list = query.list();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 获取改正行为列表
	 * @return
	 */
	@Override
	public List<NoticeBaseData> getDdlActList() {
		try {
			this.getSession().clear();
			SQLQuery query = this.getSession().createSQLQuery("select * from ACT where id<10");
			query.addEntity(NoticeBaseData.class);
			@SuppressWarnings("unchecked")
			List<NoticeBaseData> list = query.list();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 获取改正行为列表
	 * @return
	 */
	@Override
	public List<NoticeYear> getYearList() {
		try {
			this.getSession().clear();
			SQLQuery query = this.getSession().createSQLQuery("SELECT * FROM YEAR ORDER BY NAME");
			query.addEntity(NoticeYear.class);
			List<NoticeYear> list = query.list();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
