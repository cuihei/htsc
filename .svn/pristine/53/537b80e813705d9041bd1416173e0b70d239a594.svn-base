package com.ht.action.system.notice;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.ht.action.base.BaseAction;
import com.ht.common.util.LogHelper;
import com.ht.common.util.LoginUtil;
import com.ht.front.pages.system.notice.UserNoticeViews;
import com.ht.persistence.model.background.organization.employee.User;
import com.ht.persistence.model.system.notice.UserNotice;
import com.ht.persistence.model.system.notice.UserNoticeView;
import com.ht.service.inter.background.organization.employee.UserService;
import com.ht.service.inter.system.notice.UserNoticeViewService;

public class UserNoticeViewAction extends BaseAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 注入模板类型service
	 * */
	@Resource(name="usernoticeviewService")
	UserNoticeViewService usernoticeviewService;
	
	@Resource
	UserService userService;

	/**
	 * 初始化模板类型数据页面，返回成功列表页面
	 * */
	public String init(){
		UserNoticeViews usernoticeviews = new UserNoticeViews();
		String flag = getParam("flag");
		//将获取的节点字符串返回到前台页面
		request.setAttribute("html", usernoticeviews.getListNode(flag));
		return SUCCESS;
	}
	
	/**
	 * 获取UserNoticeView列表
	 */
	public void getUserNoticeView(){
		String flag = getParam("flag");
		String LoginUser = LoginUtil.getInstance().getLoginNo(request);
		List<UserNoticeView> usernoticeviewList = new ArrayList<UserNoticeView>();
		try {
			if(flag!=""){
				usernoticeviewList = usernoticeviewService.getUserNoticeByUser(LoginUser);
			}else{
				usernoticeviewList= usernoticeviewService.getUserNoticeView();
			}
			writeSuccessResult(usernoticeviewList);
		} catch (Exception e) {
			// 写入错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			writeFailResult(e.getMessage());
		}
	}
	
	/**
	 * 根据用户工号查询用户通知
	 */
	public void getUserNoticeByUserNo(){
		try {
			String userNo = getParam("userNo");
			List<UserNoticeView> usernoticeviewList = usernoticeviewService.getUserNoticeByUserNo(userNo);
			writeSuccessResult(usernoticeviewList);
		} catch (Exception e) {
			// 写入错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			writeFailResult(e.getMessage());
		}
	}
}
