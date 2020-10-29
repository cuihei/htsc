package com.ht.service.impl.system.issue;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.ht.common.util.DataConverter;
import com.ht.common.util.GenerateSequenceUtil;
import com.ht.persistence.dao.inter.system.issue.YearIssueDao;
import com.ht.persistence.model.datum.books.Books;
import com.ht.persistence.model.system.issue.YearIssue;
import com.ht.service.inter.system.issue.YearIssueService;
/**
 * 改正通告期号时段 ServiceImpl
 * @author huodesheng
 *
 */
public class YearIssueServiceImpl implements YearIssueService {
	@Resource
	YearIssueDao yearIssueDao;
	@Override
	public String addYearIssue(String yearIssueParam,String LoginUser) throws Exception {
		try {
			YearIssue yearIssue = (YearIssue) DataConverter.convertJson2Object(yearIssueParam, YearIssue.class);
			//1.期号不能重复。
			List<YearIssue> validateIssue = yearIssueDao.getYearIssueByIssue(yearIssue.getIssue());
			if(validateIssue==null||validateIssue.size()!=0){
				return "期号不能重复";
			}
			//2.时间段不能交叉
			List<YearIssue> validateTime = yearIssueDao.getYearIssueByDate(yearIssue.getBeginDate(), yearIssue.getEndDate());
			if(validateTime==null||validateTime.size()!=0){
				return "改正通告期号时段冲突";
			}
			yearIssue.setId(GenerateSequenceUtil.generateSequenceNo());
			yearIssue.setCreator(LoginUser);
			yearIssue.setCreationDate(new Date());
			yearIssueDao.addYearIssue(yearIssue);
		} catch (Exception e) {
			throw e;
		}
		return "添加成功";
	}

	@Override
	public String updateYearIssue(String yearIssueParam,String LoginUser) throws Exception {
		try {
			YearIssue yearIssue = (YearIssue) DataConverter.convertJson2Object(yearIssueParam, YearIssue.class);
			//1.期号不能重复。
			List<YearIssue> validateIssue = yearIssueDao.getYearIssueByIssue(yearIssue.getIssue());
			if(validateIssue==null||validateIssue.size()!=0){
				if(!validateIssue.get(0).getId().equals(yearIssue.getId())){
					return "期号不能重复";
				}
			}
			//2.时间段不能交叉
			List<YearIssue> validateTime = yearIssueDao.getYearIssueByDate(yearIssue.getBeginDate(), yearIssue.getEndDate());
			if(validateTime==null||validateTime.size()!=0){
				if(!validateTime.get(0).getId().equals(yearIssue.getId())){
					return "改正通告期号时段冲突";
				}
			}
			YearIssue oldYearIssue = yearIssueDao.getYearIssueById(yearIssue.getId());
			oldYearIssue.setBeginDate(yearIssue.getBeginDate());
			oldYearIssue.setEndDate(yearIssue.getEndDate());
			oldYearIssue.setIssue(yearIssue.getIssue());
			oldYearIssue.setYear(yearIssue.getYear());
			oldYearIssue.setModifyDate(new Date());
			oldYearIssue.setModifier(LoginUser);
			yearIssueDao.updateYearIssue(oldYearIssue);
		} catch (Exception e) {
			throw e;
		}
		return "修改成功";
	}

	@Override
	public void deleteYearIssue(String yearIssueParam) throws Exception {
		List<YearIssue> yearIssues = (List<YearIssue>) DataConverter.convertJson2List(yearIssueParam, YearIssue.class);
		for (YearIssue yearIssue2 : yearIssues) {
			yearIssueDao.deleteYearIssue(yearIssue2);
		}
	}

	@Override
	public List<YearIssue> getAll() throws Exception {
		// TODO Auto-generated method stub
		return yearIssueDao.getAll();
	}

	@Override
	public YearIssue getYearIssueById(String id) throws Exception {
		// TODO Auto-generated method stub
		return yearIssueDao.getYearIssueById(id);
	}

	@Override
	public List<YearIssue> getYearIssueByIssue(String issue) throws Exception {
		// TODO Auto-generated method stub
		return yearIssueDao.getYearIssueByIssue(issue);
	}

	@Override
	public List<YearIssue> getYearIssueByDate(Date beginDate, Date endDate)
			throws Exception {
		// TODO Auto-generated method stub
		return yearIssueDao.getYearIssueByDate(beginDate, endDate);
	}

}
