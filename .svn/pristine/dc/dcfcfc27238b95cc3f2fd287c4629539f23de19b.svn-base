package com.ht.service.inter.datum.correctionnoticebook;

import java.io.File;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import com.ht.exception.DBException;
import com.ht.persistence.model.datum.correctionnoticebook.CorrectionNoticeBook;
import com.ht.persistence.model.datum.correctionnoticebook.CorrectionNoticeBookView;


/**
 * 改正通告编辑资料Service
 * @author 刘凯
 *
 */
public interface CorrectionNoticeBookService {
	
	public void addCorrectionNotice(String c) throws Exception;
	
	public void modifyCorrectionNotice(CorrectionNoticeBook correctionNoticeBook) throws Exception;
	
	public void deleteCorrectionNotice(String c) throws Exception;
	
	public List<CorrectionNoticeBookView> getCorrectionNoticeBooks() throws Exception;
	
	public CorrectionNoticeBook getCorrectionNoticeBook(String id) throws Exception;
	
	public InputStream exportExcel() throws DBException;
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
	 * 附件上传
	 * @param booksId
	 * @param upload
	 * @param uploadFileName
	 */
	public void uploadFile(String booksId, File upload, String uploadFileName)throws Exception;
	/**
	 * 根据创建时间段获取改正通告原始资料
	 */
	public List<CorrectionNoticeBookView> getCorrectionNoticeBooksByCreateDate(Date beginDate, Date endDate) throws Exception;
	/**
	 * 根据创建时间段和资料采用状态获取改正通告原始资料
	 */
	public List<CorrectionNoticeBookView> getCorrectionNoticeBooksByCreateDateAndState(Date beginDate, Date endDate,String State) throws Exception;

	void changeState(String c) throws Exception;
}
