package com.ht.service.inter.datum.bookinfo;

import java.io.File;
import java.util.List;

import com.ht.persistence.model.datum.bookinfo.BookInfo;
import com.ht.persistence.model.datum.bookinfo.ViewBookInfo;


/**
 * 图书资料Service
 * @author zyd
 *
 */
public interface BookInfoService {
	
	/**
	 * 保存图书资料
	 * @param datumFile 
	 * @param BookInfo
	 * @throws Exception
	 */
	public void addBookInfo(String BookInfo,String LoginUser) throws Exception;
	
	/**
	 * 修改图书资料
	 * @param BookInfo
	 * @throws Exception
	 */
	public void modifyBookInfo(String BookInfo) throws Exception;
	
	/**
	 * 删除图书资料
	 * @param id
	 * @return 
	 * @throws Exception
	 */
	public String deleteBookInfo(String BookInfo) throws Exception;
	
	/**
	 * 获取所有图书资料
	 * @return
	 * @throws Exception
	 */
	public List<BookInfo> getBookInfo() throws Exception;
	
	/**
	 * 获取一条图书资料
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public BookInfo getBookInfo(String id) throws Exception;
		
	/**
	 * 获取多条图书资料
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List<ViewBookInfo> getBookInfoList(String ids) throws Exception;
	
	/**
	 * 附件上传
	 * @param bookInfoId
	 * @param upload
	 * @param uploadFileName
	 * @throws Exception 
	 */
	public void uploadFile(String bookInfoId, File upload, String uploadFileName) throws Exception;
	
	/**
	 * 新增借阅记录
	 * @param borrowing
	 * @param datumFile
	 * @param surplus
	 */
	public void addBorrowing(String borrowing, BookInfo bookInfo, String surplus);
	
	/**
	 * 图书归还
	 * @param borrowing
	 * @param bookInfo 
	 * @param returnNo 
	 */
	public void returnBook(String borrowing, BookInfo bookInfo, String returnNo);
	
	/**
	 * 根据条件模糊查询
	 * @param bookInfo
	 * @param publishDateTwo 
	 * @return
	 */
	public List<ViewBookInfo> getList(String bookInfo, String publishDateTwo);

	/**
	 * 导出模版
	 * 
	 * @throws Exception
	 */
	void exportInfoTemplate() throws Exception;

	/**
	 * 图书导入
	 */
	String uploadInfoFile(File file, String fileName,String LoginUser) throws Exception;

	/**
	 * 检查一级二级子类是否存在，不存在添加关联
	 */
	String checkSubClass(String subClassName, String parentId);
	
	public BookInfo getBookInfoByCode(String code);
}
