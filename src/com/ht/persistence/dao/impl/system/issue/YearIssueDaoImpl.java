package com.ht.persistence.dao.impl.system.issue;

import java.util.Date;
import java.util.List;

import com.ht.persistence.dao.impl.base.BaseDaoImpl;
import com.ht.persistence.dao.inter.system.issue.YearIssueDao;
import com.ht.persistence.model.system.issue.YearIssue;

public class YearIssueDaoImpl extends BaseDaoImpl implements YearIssueDao {

	@Override
	public void addYearIssue(YearIssue yearIssue) throws Exception {
		this.save(yearIssue);
	}

	@Override
	public void updateYearIssue(YearIssue yearIssue) throws Exception {
		this.update(yearIssue);
	}

	@Override
	public void deleteYearIssue(YearIssue yearIssue) throws Exception {
		this.delete(yearIssue);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<YearIssue> getAll() throws Exception {
		List<YearIssue> result = this.findByNamedQuery("getAllYearIssues");
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public YearIssue getYearIssueById(String id) throws Exception {
		List<YearIssue> list = this.findByNamedQueryAndNamedParam("getYearIssueById","id", id);
		YearIssue result=null;
		if(list!=null&&list.size()>0){
			result=list.get(0);
		}
		return result;
	}

	@Override
	public List<YearIssue> getYearIssueByIssue(String issue) throws Exception {
		@SuppressWarnings("unchecked")
		List<YearIssue> result = this.findByNamedQueryAndNamedParam("getYearIssueByIssue","issue", issue);
		return result;
	}

	@Override
	public List<YearIssue> getYearIssueByDate(Date beginDate, Date endDate)
			throws Exception {
		Date[] dates={beginDate,endDate};
		String[] paramNames={"beginDate","endDate"};
		List<YearIssue> result = this.findByNamedQueryAndNamedParam("getYearIssueByDate",paramNames, dates);
		return result;
	}

}
