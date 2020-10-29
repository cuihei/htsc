package com.ht.action.datum.his;

import java.util.List;

import javax.annotation.Resource;

import com.ht.action.base.BaseAction;
import com.ht.common.util.LogHelper;
import com.ht.front.pages.datum.his.ReturnHisPage;
import com.ht.persistence.model.datum.bookinfo.ReturnBook;
import com.ht.service.inter.datum.bookinfo.ReturnBookService;

/**
 * 归还记录action
 * @author zyd
 *
 */
@SuppressWarnings("serial")
public class ReturnAction extends BaseAction {
	
	/**
	 * 注入borrowingService
	 */
	@Resource(name="returnBookService")
	ReturnBookService returnBookService;
	
	
	/**
	 * 初始化归还历史记录页面，返回成功列表页面
	 * @return
	 */
	public String init(){
		// 创建图书资料页面初始化类
		ReturnHisPage rhPage = new ReturnHisPage();
		// 将获取的节点字符串返回给前台页面
		request.setAttribute("html", rhPage.getListNode());
		return SUCCESS;
	}
	
	
	/**
	 * 获取归还记录列表
	 */
	public void getReturnBook(){
		try {
			List<ReturnBook> returnList = returnBookService.getReturnBook();
			writeSuccessResult(returnList);
		} catch (Exception e) {
			LogHelper.ERROR.log(e.getMessage());
			writeFailResult(e.getMessage());
		}
	}
	
	
      
}
