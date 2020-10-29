package com.ht.action.statisticalanalysis;

import java.io.File;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.ht.action.base.BaseAction;
import com.ht.common.util.DataConverter;
import com.ht.common.util.ExcelFileUtil;
import com.ht.common.util.ExportExcel;
import com.ht.common.util.FileUtil;
import com.ht.common.util.GenerateSequenceUtil;
import com.ht.common.util.LogHelper;
import com.ht.exception.DBException;
import com.ht.front.pages.datum.bookinfo.BookInfoPage;
import com.ht.front.pages.datum.file.DatumFilePage;
import com.ht.front.pages.statisticalanalysis.YearPlanPage;
import com.ht.persistence.model.datum.bookinfo.BookFile;
import com.ht.persistence.model.datum.bookinfo.BookInfo;
import com.ht.persistence.model.datum.bookinfo.ViewBookInfo;
import com.ht.persistence.model.datum.datum.Borrowing;
import com.ht.persistence.model.datum.datum.DatumFile;
import com.ht.persistence.model.datum.type.DatumCategory;
import com.ht.persistence.model.statisticalanalysis.view.CompilationYearPlanView;
import com.ht.persistence.model.statisticalanalysis.view.YearPlanView;
import com.ht.service.impl.background.dicdata.constants.BaseDataConstants;
import com.ht.service.inter.background.dicdata.basedata.BaseDataService;
import com.ht.service.inter.background.organization.employee.UserService;
import com.ht.service.inter.datum.bookinfo.BookFileService;
import com.ht.service.inter.datum.bookinfo.BookInfoService;
import com.ht.service.inter.datum.bookinfo.ViewBookInfoService;
import com.ht.service.inter.datum.type.DatumCategoryService;
import com.ht.service.inter.statisticalanalysis.CompilationYearPlanService;

/**
 * 海图资料action
 * @author zyd
 *
 */
@SuppressWarnings("serial")
public class CompilationYearPlanAction extends BaseAction {
	//文件下载
	private InputStream downloadStream;
	//文件下载名称
	private String fileName;

	public InputStream getDownloadStream() {
		return downloadStream;
	}

	public void setDownloadStream(InputStream downloadStream) {
		this.downloadStream = downloadStream;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) throws Exception {
		this.fileName = new String(fileName.getBytes("ISO8859-1"), BaseDataConstants.BASE_CODEING);
	}
	
	/**
	 * 注入统计分析——编绘计划service
	 */
	@Resource(name="compilationYearPlanService")
	CompilationYearPlanService compilationYearPlanService;
	
	/**
	 * 初始化年测量编绘计划数据页面，返回成功列表页面
	 * */
	public String initYearPlan() {
		YearPlanPage ypp = new YearPlanPage();
		//将获取的节点字符串返回到前台页面
		request.setAttribute("html", ypp.getListPage());
		return SUCCESS;
	}
	
	/**
	 * 获取编绘计划（生产管理）数据
	 */
	public void getCompilationYearPlan(){
		try {
			// 执行查询操作
			List<YearPlanView> list = compilationYearPlanService.getCompilationYearPlan();
			// 返回客户端消息
			writeSuccessResult(list);
		} catch (Exception e) {
			// 写入错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 返回客户端错误消息
			writeFailResult(e.getMessage());
		}
	}
	
	/**
	 * 导出
	 */
	public String export(){
		SimpleDateFormat sd = new SimpleDateFormat("yyyyMMdd");
		String str = sd.format(new Date());
		this.fileName = "编绘计划（生产管理）"+str+".xls";
		try {
			// 获取参数
			fileName = new String(fileName.getBytes(), "ISO8859-1");
			downloadStream = compilationYearPlanService.export();
		} catch (Exception e) {
			// 写入错误日志
			LogHelper.ERROR.log(e.getMessage(), e);
			// 写失败信息到客户端
			writeFailResult(e.getMessage());
		}
		return SUCCESS;
	}
}
