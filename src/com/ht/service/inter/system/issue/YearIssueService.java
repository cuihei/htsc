package com.ht.service.inter.system.issue;

import java.util.Date;
import java.util.List;

import com.ht.persistence.model.system.issue.YearIssue;
/**
 * 改正通告期号时段 Service
 * @author huodesheng
 *
 */
public interface YearIssueService {
	/**
	 * 新增 改正通告期号时段
	 * @param yearIssue
	 * @return 
	 * @throws Exception
	 */
	public String addYearIssue(String yearIssueParam,String LoginUser) throws Exception;
	/**
	 * 修改 改正通告期号时段
	 * @param yearIssue
	 * @return 
	 * @throws Exception
	 */
	public String updateYearIssue(String yearIssueParam,String LoginUser) throws Exception;
	/**
	 * 删除 改正通告期号时段
	 * @param yearIssue
	 * @throws Exception
	 */
	public void deleteYearIssue(String yearIssueParam) throws Exception;
	/**
	 * 查询所有 改正通告期号时段
	 * @return
	 * @throws Exception
	 */
	public List<YearIssue> getAll() throws Exception;
	/**
	 * 根据ID获取 改正通告期号时段列表
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public YearIssue getYearIssueById(String id) throws Exception;
	
	/**
	 * 根据期号获取 改正通告期号时段列表
	 * @param issue
	 * @return
	 * @throws Exception
	 */
	public List<YearIssue> getYearIssueByIssue(String issue) throws Exception;
	
	/**
	 * 根据时间段获取 改正通告期号时段列表
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	public List<YearIssue> getYearIssueByDate(Date beginDate,Date endDate) throws Exception;
}
