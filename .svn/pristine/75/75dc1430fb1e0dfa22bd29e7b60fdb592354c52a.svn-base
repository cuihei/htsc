package com.ht.service.inter.datum.fileddata;

import java.io.File;
import java.util.List;

import com.ht.persistence.model.datum.bookinfo.BookFile;
import com.ht.persistence.model.datum.books.Books;
import com.ht.persistence.model.datum.fileddata.FiledData;
import com.ht.persistence.model.datum.fileddata.ViewFiledData;

/**
 * 外业汇交Service
 * @author zyd
 *
 */
public interface FiledDataService {
	
	/**
	 * 保存外业汇交
	 * @param datumFile 
	 * @param FiledData
	 * @throws Exception
	 */
	public void addFiledData(String FiledData,String LoginUser) throws Exception;
	
	/**
	 * 修改外业汇交
	 * @param FiledData
	 * @throws Exception
	 */
	public void modifyFiledData(String FiledData) throws Exception;
	
	/**
	 * 删除外业汇交
	 * @param id
	 * @throws Exception
	 */
	public String deleteFiledData(String FiledData) throws Exception;
	
	/**
	 * 获取所有外业汇交
	 * @return
	 * @throws Exception
	 */
	public List<FiledData> getFiledData() throws Exception;
	
	/**
	 * 获取一条外业汇交
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public FiledData getFiledData(String id) throws Exception;
	
	/**
	 * 附件上传
	 * @param FiledDataId
	 * @param upload
	 * @param uploadFileName
	 * @throws Exception 
	 */
	public void uploadFile(String FiledDataId, File upload, String uploadFileName) throws Exception;
	
	/**
	 * 新增借阅记录
	 * @param borrowing
	 * @param datumFile
	 * @param surplus
	 */
	public void addBorrowing(String borrowing, FiledData FiledData, String surplus);
	
	/**
	 * 外业汇交归还
	 * @param borrowing
	 * @param FiledData 
	 * @param returnNo 
	 */
	public void returnBook(String borrowing, FiledData FiledData, String returnNo);
	
	/**
	 * 从视图根据Id获取一条数据
	 * @param id
	 * @return
	 */
	public ViewFiledData getViewFiledData(String id);
	
	/**
	 * 根据图号获取一条数据
	 * @param picNo
	 * @return
	 */
	public FiledData getFiledDataByPicNo(String picNo);
	
	/**
	 * 获取附件
	 * @param bookFile
	 * @return
	 */
	public List<BookFile> getFileByBookId(String bookFile);
	
	/**
	 * 根据条件模糊查询
	 * @param fileds
	 * @param concurrentTimeTwo
	 * @return
	 */
	public List<FiledData> getList(String fileds, String concurrentTimeTwo) throws Exception;

	/**
	 * 获取借阅列表
	 * @param ids
	 * @return
	 */
	public List<ViewFiledData> getFiledDataList(String ids) throws Exception;

	public List<FiledData> getFiledDataByStatus() throws Exception;

	public List<FiledData> getOtherList(String fileds, String concurrentTimeTwo);
	
	public FiledData getFiledDataByCode(String picNo);
}
