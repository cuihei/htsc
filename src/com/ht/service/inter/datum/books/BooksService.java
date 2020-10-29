package com.ht.service.inter.datum.books;

import java.io.File;
import java.util.List;

import com.ht.persistence.model.datum.books.Books;
import com.ht.persistence.model.datum.books.BooksView;


/**
 * 海图Service
 * @author houchen
 *
 */
public interface BooksService {
	
	/**
	 * 保存海图
	 * @param Books
	 * @throws Exception
	 */
	public void addBooks(String Books,String LoginUser) throws Exception;
	
	/**
	 * 修改海图
	 * @param Books
	 * @throws Exception
	 */
	public void modifyBooks(String Books) throws Exception;
	
	/**
	 * 删除海图
	 * @param id
	 * @return 
	 * @throws Exception
	 */
	public String deleteBooks(String Books) throws Exception;
	
	/**
	 * 获取所有海图
	 * @return
	 * @throws Exception
	 */
	public List<Books> getBooks() throws Exception;
	
	/**
	 * 获取一条海图
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Books getBooks(String id) throws Exception;
	
	/**
	 * 获取一条海图
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public BooksView getBooksView(String id) throws Exception;
	/**
	 * 获取所有海图视图
	 * @return
	 * @throws Exception
	 */
	public List<BooksView> getBooksView();
	
	/**
	 * 获取所有海图视图
	 * @return
	 * @throws Exception
	 */
	public List<BooksView> getBooksViewList(String ids) throws Exception;
	
	/**
	 * 附件上传
	 * @param bookInfoId
	 * @param upload
	 * @param uploadFileName
	 * @throws Exception 
	 */
	public void uploadFile(String booksId, File upload, String uploadFileName) throws Exception;
	
	/**
	 * 新增借阅记录
	 * @param borrowing
	 * @param datumFile
	 * @param surplus
	 */
	public void addBorrowing(String borrowing, Books books, String surplus);
	
	/**
	 * 图书归还
	 * @param borrowing
	 * @param books 
	 * @param returnNo 
	 */
	public void returnBook(String borrowing, Books books, String returnNo);
	
	/**
	 * 根据ChartNo获取一条海图
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Books getBooksByChartNo(String chartNo);
	
	/**
	 * 根据条件模糊查询
	 * @param books
	 * @param stockNoTwo
	 * @param publishDateTwo
	 * @param scaleTwo
	 * @return
	 */
	public List<BooksView> getList(String books, String stockNoTwo,
			String publishDateTwo, String scaleTwo);

	/**
	 * 导出模版
	 * @return
	 */
	void exportBookTemplate() throws Exception;

	/**
	 * 海图导入
	 */
	String uploadBookFile(File file,String fileName,String LoginUser) throws Exception;

	public List<BooksView> getBooksByStatus();
	
	public Books getBooksByCode(String chartNo);
}
