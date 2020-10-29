package com.ht.persistence.dao.inter.datum.correctionnoticebook;

import java.util.Date;
import java.util.List;

import com.ht.persistence.model.background.dicdata.basedata.BaseData;
import com.ht.persistence.model.datum.books.Books;
import com.ht.persistence.model.datum.books.BooksView;
import com.ht.persistence.model.datum.correctionnoticebook.CorrectionNoticeBook;
import com.ht.persistence.model.datum.correctionnoticebook.CorrectionNoticeBookView;

/**
 * 改正通告编辑资料Dao
 * @author 刘凯
 *
 */
public interface CorrectionNoticeBookDao {
	
	public void addCorrectionNoticeBook(CorrectionNoticeBook c);
	
	public void modifyCorrectionNoticeBook(CorrectionNoticeBook c);
	
	public void mergeCorrectionNoticeBook(CorrectionNoticeBook c);
	
	public void deleteCorrectionNoticeBook(CorrectionNoticeBook c);
	
	public List<CorrectionNoticeBookView> getCorrectionNoticeBooks();
	
	public CorrectionNoticeBook getCorrectionNoticeBook(CorrectionNoticeBook c);
	/**
	 * 根据创建时间段获取改正通告原始资料
	 */
	public List<CorrectionNoticeBookView> getCorrectionNoticeBooksByCreateDate(Date beginDate, Date endDate) throws Exception;
	/**
	 * 根据创建时间段和资料采用状态获取改正通告原始资料
	 */
	public List<CorrectionNoticeBookView> getCorrectionNoticeBooksByCreateDateAndState(Date beginDate, Date endDate,String state) throws Exception;
}
