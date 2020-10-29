package com.ht.service.inter.datum.bookinfo;

import java.util.List;

import com.ht.persistence.model.datum.bookinfo.BookFile;

/**
 * 图书文件Service
 * @author 刘凯
 *
 */
public interface BookFileService {
	
	/**
	 * 获取一条附件
	 * @param bookFile
	 * @return BookFile附件
	 */
	public BookFile getFileById(String bookFileId) throws Exception;
	
	/**
	 * 获取图书的附件
	 * @param bookFile
	 * @return List<BookFile>附件集合
	 */
	public List<BookFile> getFileByBookId(String bookFile) throws Exception;
	
	/**
	 * 文件下载
	 * @param bookFileService 
	 * @param bookfileId
	 * @throws Exception 
	 */
	public void downloadFile(BookFileService bookFileService, String bookfileId) throws Exception;
	
	public void removeBookFile(String bookFileId) throws Exception;
	
}
