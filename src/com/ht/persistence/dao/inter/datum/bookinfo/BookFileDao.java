package com.ht.persistence.dao.inter.datum.bookinfo;

import java.util.List;

import com.ht.persistence.model.datum.bookinfo.BookFile;
import com.ht.persistence.model.datum.fileddata.FiledData;


/**
 * BookFileDao
 * @author liukai
 */
public interface BookFileDao {
	
	/**
	 * 获取一条附件
	 * @param bookFile
	 * @return BookFile
	 */
	public BookFile getFileById(String bookFileId);
	
	/**
	 * 获取图书的附件
	 * @param bookFile
	 * @return List<BookFile>附件集合
	 */
	public List<BookFile> getFileByBookId(BookFile bookFile);
	
	/**
	 * 添加图书附件
	 * @param bookFile
	 */
	public void addBookFile(BookFile bookFile);
	
	public void removeBookFile(BookFile bookFile);
	
		
}
