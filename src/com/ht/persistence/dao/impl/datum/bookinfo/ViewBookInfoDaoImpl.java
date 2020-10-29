package com.ht.persistence.dao.impl.datum.bookinfo;

import java.util.List;

import org.hibernate.SQLQuery;

import com.ht.persistence.dao.impl.base.BaseDaoImpl;
import com.ht.persistence.dao.inter.datum.bookinfo.BookInfoDao;
import com.ht.persistence.dao.inter.datum.bookinfo.ViewBookInfoDao;
import com.ht.persistence.model.background.application.Application;
import com.ht.persistence.model.datum.bookinfo.BookInfo;
import com.ht.persistence.model.datum.bookinfo.ViewBookInfo;
import com.ht.persistence.model.system.notice.Notice;

/**
 * BookInfo视图数据映射操作类
 * @author zyd
 *
 */
public class ViewBookInfoDaoImpl extends BaseDaoImpl implements ViewBookInfoDao {
	
	/**
	 * 获取所有图书资料
	 */
	@Override
	public List<ViewBookInfo> getBookInfo() {
		try {
			@SuppressWarnings("unchecked")
			// 执行查询
			List<ViewBookInfo> result = this.findByNamedQuery("getAllBookInfo");
			if(result.size() > 0){
				return result;
			}
			return null;
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * 获取一条图书资料
	 */
	@Override
	public ViewBookInfo getBookInfo(ViewBookInfo BookInfo) {
		@SuppressWarnings("unchecked")
		List<ViewBookInfo> result = this.findByNamedQueryAndNamedParam("getViewBookInfoById", "id", BookInfo.getId());
		if(result.size()>0){
			return result.get(0);
		}
		return null;
	}
	
	@Override
	public List<ViewBookInfo> getBookinfoByStatus() {
		
		SQLQuery query = this.getSession().createSQLQuery("select * from V_book_info t where t.status = '审核通过' order by t.MODIFY_DATE desc");
		query.addEntity(ViewBookInfo.class);
		
		@SuppressWarnings("unchecked")
		List<ViewBookInfo> appList =  query.list();
		if(appList.size()>0){
			return appList;
		}
		return null;
	}
	
	
}
