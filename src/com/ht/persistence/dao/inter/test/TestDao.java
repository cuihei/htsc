package com.ht.persistence.dao.inter.test;

import java.util.List;

import com.ht.persistence.model.background.dicdata.basedata.BaseData;
import com.ht.persistence.model.datum.books.Books;
import com.ht.persistence.model.datum.books.BooksView;

/**
 * TestDao
 * @author houchen
 *
 */
public interface TestDao {
	
	/**
	 * 根据sql查询
	 * @param sql
	 * @return Object List
	 */
	public List<Object> search(String sql);
	
}
