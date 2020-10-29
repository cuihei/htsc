package com.ht.action.drawtask.taskbook.history;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import com.ht.action.base.BaseAction;
import com.ht.common.util.LogHelper;
import com.ht.front.pages.drawtask.taskbook.HistoryTaskBookPage;
import com.ht.persistence.model.drawtask.taskbook.book.TaskBook;
import com.ht.persistence.model.drawtask.taskbook.history.HistoryTaskBook;
import com.ht.service.inter.background.organization.organization.OrganizationService;
import com.ht.service.inter.drawtask.taskbook.history.HistoryTaskBookService;
import com.ht.service.inter.drawtask.taskbook.relation.TaskBookPlanRelationService;

/**
 * 历史计划书Action类
 * 
 * @author yaoping
 */

@SuppressWarnings("serial")
public class HistoryTaskBookAction extends BaseAction {

	@Resource(name = "historyTaskBookService")
	private HistoryTaskBookService historyTaskBookService;
	@Resource(name = "organizationService")
	private OrganizationService organizationService;
	@Resource(name = "taskBookPlanRelationService")
	private TaskBookPlanRelationService taskBookPlanRelationService;

	public String index() {
		HistoryTaskBookPage page = new HistoryTaskBookPage();
		String taskBookId = getParam("taskBookId");
		String taskBookType = getParam("taskBookType");
		String html = page.getListPage(historyTaskBookService, taskBookId,
				taskBookType);
		request.setAttribute("html", html);
		return SUCCESS;
	}

	/**
	 * 获取历史计划书head
	 */
	public void findList() {
		try {
			List<HistoryTaskBook> list = historyTaskBookService.findList();
			writeSuccessResult(list);
		} catch (Exception e) {
			// 写入错误日志
			e.printStackTrace();
			LogHelper.ERROR.log(e.getMessage(), e);
			writeFailResult(e.getMessage());
		}
	}

	/**
	 * 获取历史计划书head
	 */
	public void findListByTaskBookId() {
		try {
			String taskBookId = getParam("taskBookId");
			List<HistoryTaskBook> list = historyTaskBookService
					.findListByTaskBookId(taskBookId);
			if(list!=null&&list.size()>0){
				for (HistoryTaskBook historyTaskBook : list) {
					String revision = historyTaskBook.getRevision();
					if(revision!=null&&revision!=""){
						historyTaskBook.setRevision("第"+revision+"版");
					}
				}
			}
			writeSuccessResult(list);
		} catch (Exception e) {
			// 写入错误日志
			e.printStackTrace();
			LogHelper.ERROR.log(e.getMessage(), e);
			writeFailResult(e.getMessage());
		}
	}

	/**
	 * 获取历史计划书detail
	 * 
	 * @throws Exception
	 */
	public String findDetailList() throws Exception {
		HistoryTaskBookPage page = new HistoryTaskBookPage();
		String id = getParam("id");
		List<HistoryTaskBook> list = historyTaskBookService.findDetailList(id);
		HistoryTaskBook taskbook = null;
		if (list != null) {
			taskbook = list.get(0);
		}
		String html = page.getDetailPage(historyTaskBookService,
				organizationService, taskbook, taskBookPlanRelationService);
		request.setAttribute("html", html);
		return SUCCESS;
	}

	public void delete() throws Exception {
		try {
			String id = getParam("id");
			historyTaskBookService.delete(id);
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(), e);
			// 抛出异常
			throw e;
		}
	}

	/**
	 * 下载
	 */
	public String downLoad() throws Exception {
		try {
			String id = this.getParam("id");
			List<HistoryTaskBook> list = historyTaskBookService
					.findDetailList(id);
			HistoryTaskBook taskbook = null;
			taskbook = list.get(0);
			String enclosure = taskbook.getEnclosure();
			String[] split = enclosure.split("\\\\");
			String fileName = "";
			fileName = split[split.length - 1];
			HttpServletResponse response = this.respose;
			// response.setCharacterEncoding("utf-8");
			response.setContentType("multipart/form-data");
			response.setHeader("Content-Disposition", "attachment;fileName="
					+ java.net.URLEncoder.encode(fileName, "UTF-8"));
			// 获取项目在服务器的路径
			String serverPath = request.getServletContext().getRealPath("/");
			// 获取相应文件的流
			File file = new File(serverPath + taskbook.getEnclosure());
			// 设置文件长度
			response.setHeader("Content-Length", (int) file.length() + "");
			// IO流复制
			InputStream inputStream = new FileInputStream(file);
			OutputStream os = response.getOutputStream();
			int length;
			while ((length = inputStream.read()) != -1) {
				os.write(length);
			}
			// 释放资源
			inputStream.close();
			os.flush();
			os.close();
			return SUCCESS;
		} catch (Exception e) {
			// 写入错误日志
			LogHelper.ERROR.log(e.getMessage(), e);
			writeFailResult(e.getMessage());
			return "loadError";
		}

	}
}
