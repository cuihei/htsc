package com.ht.persistence.dao.impl.datum.bookinfo;

import java.util.List;

import com.ht.persistence.dao.impl.base.BaseDaoImpl;

import com.ht.persistence.dao.inter.datum.bookinfo.BookFileDao;
import com.ht.persistence.model.datum.bookinfo.BookFile;


/**
 * BookFile数据映射操作类
 * @author liukai
 *
 */
public class BookFileDaoImpl extends BaseDaoImpl implements BookFileDao {
	
	/**
	 * 获取图书的附件
	 * @param bookFile
	 * @return List<BookFile>附件集合
	 */
	@Override
	public List<BookFile> getFileByBookId(BookFile bookFile) {
		try{
			@SuppressWarnings("unchecked")
			List<BookFile> result = this.findByNamedQueryAndNamedParam("getFileByBookId","bookId",bookFile.getBookId());
			return result;
		} catch(Exception e){
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	/**
	 * 获取一条附件
	 * @param bookFile
	 * @return BookFile
	 */
	@Override
	public BookFile getFileById(String bookFileId) {
		try{
			@SuppressWarnings("unchecked")
			List<BookFile> result = this.findByNamedQueryAndNamedParam("getFileById","id",bookFileId);
			if(result.size() > 0){
				return result.get(0);
			}
		} catch(Exception e){
			System.out.println(e.getMessage());
			return null;
		}
		return null;
	}
	
	/**
	 * 添加图书附件
	 */
	@Override
	public void addBookFile(BookFile bookFile) {
		this.save(bookFile);
	}

	@Override
	public void removeBookFile(BookFile bookFile) {
		this.delete(bookFile);
	}
}
