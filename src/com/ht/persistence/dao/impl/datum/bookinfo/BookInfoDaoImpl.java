package com.ht.persistence.dao.impl.datum.bookinfo;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SQLQuery;

import com.ht.common.util.GenerateSequenceUtil;
import com.ht.persistence.dao.impl.base.BaseDaoImpl;
import com.ht.persistence.dao.inter.datum.bookinfo.BookInfoDao;
import com.ht.persistence.dao.inter.datum.type.DatumCategoryDao;
import com.ht.persistence.model.datum.bookinfo.BookInfo;
import com.ht.persistence.model.datum.bookinfo.ViewBookInfo;
import com.ht.persistence.model.datum.books.BooksView;
import com.ht.persistence.model.datum.type.DatumCategory;

/**
 * BookInfo数据映射操作类
 * @author zyd
 *
 */
public class BookInfoDaoImpl extends BaseDaoImpl implements BookInfoDao {
	
	@Resource
	DatumCategoryDao datumCategoryDao;
	/**
	 * 图书资料添加
	 */
	@Override
	public void addBookInfo(BookInfo BookInfo) {
		this.save(BookInfo);
	}

	/**
	 * 图书资料更新
	 */
	@Override
	public void modifyBookInfo(BookInfo BookInfo) {
		this.update(BookInfo);
	}
	
	/**
	 * 图书资料更新，与update方法不同
	 */
	@Override
	public void mergeBookInfo(BookInfo BookInfo){
		super.merge(BookInfo);
	}

	/**
	 * 删除图书资料
	 */
	@Override
	public void deleteBookInfo(BookInfo BookInfo) {
		 this.delete(BookInfo);
	}
	
	/**
	 * 获取所有图书资料
	 */
	@Override
	public List<BookInfo> getBookInfo() {
		try {
			@SuppressWarnings("unchecked")
			// 执行查询
			List<BookInfo> result = this.findByNamedQuery("getBookInfo");
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
	public BookInfo getBookInfo(BookInfo BookInfo) {
		@SuppressWarnings("unchecked")
		List<BookInfo> result = this.findByNamedQueryAndNamedParam("getBookInfoById", "id", BookInfo.getId());
		if(result.size()>0){
			return result.get(0);
		}
		return null;
	}
	
	/**
	 * 获取一条图书资料
	 */
	@Override
	public ViewBookInfo getBookInfoBySql(String sql) {
		try {
			SQLQuery query = this.getSession().createSQLQuery(sql);
			query.addEntity(ViewBookInfo.class);
			List<ViewBookInfo> list = query.list();
			if(list.size()>0){
				return list.get(0);
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 根据条件模糊查询
	 */
	@Override
	public List<ViewBookInfo> getList(String hql) {
		try {
			SQLQuery query = this.getSession().createSQLQuery(hql);
			query.addEntity(ViewBookInfo.class);
			List<ViewBookInfo> list = query.list();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 校验编码是否存在
	 */
	@Override
	public Boolean isCodeExist(BookInfo bookInfo) {
		String [] values={"code","id"};
		String [] params={bookInfo.getCode(),bookInfo.getId()};
		@SuppressWarnings("unchecked")
		List<BooksView> result = this.findByNamedQueryAndNamedParam("isBookInfoCodeExist", values, params);
		if(result.size()>0){
			return true;
		}
		return false;
	}
	
	/**
	 * 检查一级二级子类是否存在，不存在添加关联
	 */
	@Override
	public String checkSubClass(DatumCategory datumCategory) {
		String[] paramNames={"parentId","categoryName"};
		String[] values={datumCategory.getParentId(),datumCategory.getCategoryName()};
		@SuppressWarnings("unchecked")
		List<DatumCategory> result = this.findByNamedQueryAndNamedParam("checkSubClass", paramNames, values);
		if(result.size()<=0){
			String id = GenerateSequenceUtil.generateSequenceNo();
			datumCategory.setId(id);
			datumCategoryDao.addDatumCategory(datumCategory);
			return id;
		}
		return result.get(0).getId();
	}

	@Override
	public BookInfo getBookInfoByCode(BookInfo BookInfo)
	{
		@SuppressWarnings("unchecked")
		List<BookInfo> result = this.findByNamedQueryAndNamedParam("getBookInfoByCode", "code", BookInfo.getCode());
		if(result.size()>0){
			return result.get(0);
		}
		return null;
	}
	
}
