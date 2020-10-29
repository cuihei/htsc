package com.ht.action.system.notice;

import java.util.List;

import javax.annotation.Resource;

import com.ht.action.base.BaseAction;
import com.ht.common.util.DataConverter;
import com.ht.common.util.LogHelper;
import com.ht.front.pages.system.document.type.ModelTypes;
import com.ht.front.pages.system.notice.UserNoticeViews;
import com.ht.front.pages.system.notice.UserNotices;
import com.ht.persistence.model.system.notice.UserNotice;
import com.ht.service.inter.system.notice.UserNoticeService;

/**
 * UserNotice 用户通知关系类
 * @author 平子金
 */
@SuppressWarnings("serial")
public class UserNoticeAction extends BaseAction{
	
	/**
	 * 注入用户通知关系service
	 * */
	@Resource(name="usernoticeService")
	UserNoticeService usernoticeService;
	
	/**
	 * 初始化用户通知关系数据页面，返回成功列表页面
	 * */
	public String init(){
		UserNotices usernotices = new UserNotices();
		//将获取的节点字符串返回到前台页面
		request.setAttribute("html", usernotices.getListNode());
		return SUCCESS;
	}
	
	/**
	 * 新增用户通知数据
	 */
	public void addUserNotice(){
		try {
			// 获取前台传入参数
			String usernotice = getParam("notice");
			String user = getParam("user");
			String org = getParam("org");
			// 执行保存操作
			usernoticeService.addUserNotices(usernotice,user,org);
			writeSuccessResult();
		} catch (Exception e) {
			writeFailResult(e.getMessage());
		}
	}
	
	/**
	 * 删除UserNotice数据
	 */
	public void removeUserNotice(){
		// 获取UserNotice标识
		String id = getParam("id");
		try {
			usernoticeService.delUserNotice(id);
			writeSuccessResult();
		} catch (Exception e) {
			// 写入错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			writeFailResult(e.getMessage());
		}
	}
	
	/**
	 * 获取UserNotice列表
	 */
	public void getUserNotice(){
		try {
			List<UserNotice> usernoticeList = usernoticeService.getUserNotice();
			writeSuccessResult(usernoticeList);
		} catch (Exception e) {
			// 写入错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			writeFailResult(e.getMessage());
		}
	}
	
	/**
	 * 根据id获取UserNotice
	 */
	public void getUserNoticeByKey(){
		try {
			String id = getParam("id");
			UserNotice usernotice = usernoticeService.getUserNotice(id);
			writeSuccessResult(usernotice);
		} catch (Exception e) {
			// 写入错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 写失败信息到客户端
			writeFailResult(e.getMessage());
		}
	}
	
}
