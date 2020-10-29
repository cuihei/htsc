package com.ht.action.catalog.history;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.ht.action.base.BaseAction;
import com.ht.common.util.Download;
import com.ht.common.util.ExportUtil;
import com.ht.common.util.LogHelper;
import com.ht.front.pages.catalog.history.HistoryCatalogDetailPage;
import com.ht.persistence.model.catalog.history.HistoryCatalogDetail;
import com.ht.service.inter.background.dicdata.basedata.BaseDataService;
import com.ht.service.inter.catalog.history.HistoryCatalogDetailService;


/**
 * CatalogArea 区域管理类
 * @author yp
 */
@SuppressWarnings("serial")
public class HistoryCatalogDetailAction extends BaseAction{
	
	@Resource(name="historyCatalogDetailService")
	HistoryCatalogDetailService historyCatalogDetailService;
	
	@Resource(name="baseDataService")
	BaseDataService baseDataService;
	
	//规划目录图明细
	public String planIndex(){
		String type = "1";
		String listNode = HistoryCatalogDetailPage.getListNode(type);
		request.setAttribute("html", listNode);
		return "success";
	}
	
	//海军目录图明细 
	public String navyIndex(){
		String type = "2";
		String listNode = HistoryCatalogDetailPage.getListNode(type);
		request.setAttribute("html", listNode);
		return "success";
	}
	
	//港口航道目录图明细 
	public String portChannelIndex(){
		String type = "3";
		String listNode = HistoryCatalogDetailPage.getListNode(type);
		request.setAttribute("html", listNode);
		return "success";
	}
	
	
	
	/**
	 * 获取历史目录list
	 */
	public void list(){
		String type = request.getParameter("type");
		try {
			//List<HistoryCatalogDetail> list = historyCatalogDetailService.getHistoryListByCategoryId("1");
			//List<HistoryCatalogDetail> list = historyCatalogDetailService.getHistoryDetailList();
			List<HistoryCatalogDetail> list = new ArrayList<HistoryCatalogDetail>() ;
			if ("1".equals(type)) {
				list = historyCatalogDetailService.
						getHistoryListByCategoryId(HistoryCatalogDetailUtil.PLAN_ID);
			}
			if ("2".equals(type)) {
				list = historyCatalogDetailService.
						getHistoryListByCategoryId(HistoryCatalogDetailUtil.NAVY_ID);
			}
			if ("3".equals(type)) {
				list = historyCatalogDetailService.
						getHistoryListByCategoryId(HistoryCatalogDetailUtil.PORTCHANNEL_ID);
			}
			writeSuccessResult(list);
		} catch (Exception e) {
			// 写入错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			writeFailResult(e.getMessage());
		}
	}
	

	
	/**
	 * 导出
	 */
	public void export(){
		String type = request.getParameter("type");
		String name = null;
		try {
			List<HistoryCatalogDetail> list = new ArrayList<HistoryCatalogDetail>() ;
			if ("1".equals(type)) {
				list = historyCatalogDetailService.
						getHistoryListByCategoryId(HistoryCatalogDetailUtil.PLAN_ID);
				name = "规划图目录"; 
			}
			if ("2".equals(type)) {
				list = historyCatalogDetailService.
						getHistoryListByCategoryId(HistoryCatalogDetailUtil.NAVY_ID);
				name = "海军图目录";
			}
			if ("3".equals(type)) {
				list = historyCatalogDetailService.
						getHistoryListByCategoryId(HistoryCatalogDetailUtil.PORTCHANNEL_ID);
				name = "港口航道图目录";
			}
			//文件名
			SimpleDateFormat sd = new SimpleDateFormat("yyyyMMdd");
			String str = sd.format(new Date());
			String fileName = name + str;
			ExportUtil.template("历史目录", ExportUtil.titles,Download.PATH,fileName, list) ;
            Download.download(respose, fileName);
		} catch (Exception e) {
			// 写入错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			writeFailResult(e.getMessage());
		}
	}
}
