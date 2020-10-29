package com.ht.action.system.issue;

import java.util.List;

import javax.annotation.Resource;

import com.ht.action.base.BaseAction;
import com.ht.common.util.DataConverter;
import com.ht.common.util.LogHelper;
import com.ht.common.util.LoginUtil;
import com.ht.front.pages.system.issue.YearIssuePage;
import com.ht.persistence.model.system.issue.YearIssue;
import com.ht.persistence.model.system.symbol.Symbol;
import com.ht.service.inter.system.issue.YearIssueService;
/**
 *  改正通告期号时段 action
 * @author huodesheng
 *
 */
@SuppressWarnings("serial")
public class YearIssueAction extends BaseAction{
	@Resource
	private YearIssueService yearIssueService;
	/**
	 * 初始化首页
	 * @return
	 */
	public String init(){
		YearIssuePage yi=new YearIssuePage();
		request.setAttribute("html", yi.getListNode());
		return SUCCESS;
	}
	/**
	 * 获取列表
	 * @return
	 */
	public void getYearIssueList(){
		try {
			//执行查询方法
			List<YearIssue> yearIssuelList = yearIssueService.getAll();
			writeSuccessResult(yearIssuelList);
		} catch (Exception e) {
			e.printStackTrace();
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			//返回错误信息
			writeFailResult(e.getMessage());
		}
	}
	/**
	 * 初始化新增/编辑 页面
	 * @return
	 */
	public String initEdit(){
		try {
			String id = getParam("id");
			YearIssuePage yi=new YearIssuePage();
			String html = yi.getEditNode(yearIssueService, id);
			request.setAttribute("html",html);
		} catch (Exception e) {
			LogHelper.ERROR.log(e.getMessage());
			writeFailResult(e.getMessage());
		}
		return SUCCESS;
	}
	/**
	 * 添加或修改
	 */
	public void editYearIssue(){
		try {
			String LoginUser = LoginUtil.getInstance().getLoginNo(request);
			// 获取参数
			String yearIssueParam = getParam("yearIssue");
			YearIssue yearIssue = (YearIssue) DataConverter.convertJson2Object(yearIssueParam, YearIssue.class);
			if(yearIssue!=null){
				if(yearIssue.getId()!=null){
					String  msg=yearIssueService.updateYearIssue(yearIssueParam, LoginUser);
					// 成功返回消息
					writeSuccessResult(msg);
				}else{
					// 执行添加
					String  msg= yearIssueService.addYearIssue(yearIssueParam, LoginUser);
					// 成功返回消息
					writeSuccessResult(msg);
				}
			}else{
				// 成功返回消息
				writeSuccessResult("添加失败");
			}
			
			
		} catch (Exception e) {
			LogHelper.ERROR.log(e.getMessage());
			writeFailResult(e.getMessage());
		}

	}
	/**
	 * 删除
	 */
	public void deleteYearIssue(){
		try {
			String yearIssueParam = getParam("yearIssue");
			yearIssueService.deleteYearIssue(yearIssueParam);
			writeSuccessResult("删除成功");
		} catch (Exception e) {
			LogHelper.ERROR.log(e.getMessage());
			writeFailResult(e.getMessage());
		}
	}
}
