package com.ht.persistence.dao.inter.datum.bookinfo;

import java.util.List;

import com.ht.persistence.model.datum.bookinfo.BookInfo;
import com.ht.persistence.model.datum.bookinfo.ViewBookInfo;
import com.ht.persistence.model.datum.type.DatumCategory;

/**
 * 图书资料Dao
 * @author zyd
 *
 */
public interface BookInfoDao {
	
	/**
	 * 增加图书资料
	 * @param BookInfo BookInfo实体
	 */
	public void addBookInfo(BookInfo BookInfo);
	
	/**
	 * 更新图书资料
	 * @param BookInfo BookInfo实体
	 */
	public void modifyBookInfo(BookInfo BookInfo);

	/**
	 * 更新图书资料，与update方法不同
	 * @param BookInfo BookInfo实体
	 */
	public void mergeBookInfo(BookInfo BookInfo);
	
	/**
	 * 删除图书资料
	 * @param BookInfo BookInfo实体
	 */
	public void deleteBookInfo(BookInfo BookInfo);
	
	/**
	 * 获取所有图书资料
	 * @return BookInfo列表
	 */
	public List<BookInfo> getBookInfo();
	
	/**
	 * 获取一条图书资料
	 * @param id BookInfo主键
	 * @return BookInfo实体
	 */
	public BookInfo getBookInfo(BookInfo BookInfo);
	
	/**
	 * 获取一条图书资料
	 * @param id BookInfo主键
	 * @return BookInfo实体
	 */
	public ViewBookInfo getBookInfoBySql(String sql);
	
	/**
	 * 获取一条图书资料
	 * @param id BookInfo主键
	 * @return BookInfo实体
	 */
	public BookInfo getBookInfoByCode(BookInfo BookInfo);
	
	/**
	 * 根据条件模糊查询
	 * @param hql
	 * @return
	 */
	public List<ViewBookInfo> getList(String hql);

	/**
	 * 校验编码是否存在
	 */
	public Boolean isCodeExist(BookInfo bookInfo);

	/**
	 * 检查一级二级子类是否存在，不存在添加关联
	 */
	public String checkSubClass(DatumCategory datumCategory);
	
}
