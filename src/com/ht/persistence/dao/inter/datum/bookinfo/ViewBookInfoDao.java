package com.ht.persistence.dao.inter.datum.bookinfo;

import java.util.List;

import com.ht.persistence.model.datum.bookinfo.BookInfo;
import com.ht.persistence.model.datum.bookinfo.ViewBookInfo;

/**
 * 图书资料视图Dao
 * @author zyd
 *
 */
public interface ViewBookInfoDao {
	
	/**
	 * 获取所有图书资料
	 * @return BookInfo列表
	 */
	public List<ViewBookInfo> getBookInfo();
	
	/**
	 * 获取一条图书资料
	 * @param id BookInfo主键
	 * @return BookInfo实体
	 */
	public ViewBookInfo getBookInfo(ViewBookInfo BookInfo);

	public List<ViewBookInfo> getBookinfoByStatus();
	
}
