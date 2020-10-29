package com.ht.persistence.dao.impl.datum.correctionnoticebook;

import java.util.Date;
import java.util.List;

import com.ht.persistence.dao.impl.base.BaseDaoImpl;
import com.ht.persistence.dao.inter.datum.correctionnoticebook.CorrectionNoticeBookDao;
import com.ht.persistence.model.datum.correctionnoticebook.CorrectionNoticeBook;
import com.ht.persistence.model.datum.correctionnoticebook.CorrectionNoticeBookView;
import com.ht.persistence.model.system.issue.YearIssue;

/**
 * 改正通告编辑资料数据映射操作类
 * @author 刘凯
 *
 */
public class CorrectionNoticeBookDaoImpl extends BaseDaoImpl implements CorrectionNoticeBookDao {

	@Override
	public void addCorrectionNoticeBook(CorrectionNoticeBook c) {
		this.save(c);
	}

	@Override
	public void modifyCorrectionNoticeBook(CorrectionNoticeBook c) {
		this.update(c);
	}

	@Override
	public void mergeCorrectionNoticeBook(CorrectionNoticeBook c) {
		this.merge(c);
		
	}

	@Override
	public void deleteCorrectionNoticeBook(CorrectionNoticeBook c) {
		 this.delete(c);
	}

	@Override
	public List<CorrectionNoticeBookView> getCorrectionNoticeBooks() {
		try {
			@SuppressWarnings("unchecked")
			// 执行查询
			List<CorrectionNoticeBookView> result = this.find("from CorrectionNoticeBookView");
			return result;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public CorrectionNoticeBook getCorrectionNoticeBook(CorrectionNoticeBook c) {
		@SuppressWarnings("unchecked")
		List<CorrectionNoticeBook> result = this.findByNamedQueryAndNamedParam("getCorrectionNoticeById", "id", c.getId());
		if(result.size()>0){
			return result.get(0);
		}
		return null;
	}

	@Override
	public List<CorrectionNoticeBookView> getCorrectionNoticeBooksByCreateDate(Date beginDate, Date endDate)
			throws Exception {
		Date[] dates={beginDate,endDate};
		String[] paramNames={"beginDate","endDate"};
		List<CorrectionNoticeBookView> result = this.findByNamedQueryAndNamedParam("getCorrectionNoticeBooksByCreateDate",paramNames, dates);
		return result;
	}

	@Override
	public List<CorrectionNoticeBookView> getCorrectionNoticeBooksByCreateDateAndState(Date beginDate, Date endDate,String state)
			throws Exception {
		Object[] dates={beginDate,endDate,state};
		String[] paramNames={"beginDate","endDate","state"};
		List<CorrectionNoticeBookView> result = this.findByNamedQueryAndNamedParam("getCorrectionNoticeBooksByCreateDateAndState",paramNames, dates);
		return result;
	}
}
