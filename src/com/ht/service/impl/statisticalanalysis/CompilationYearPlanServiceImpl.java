package com.ht.service.impl.statisticalanalysis;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFDataFormat;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.ht.common.util.ConvertUtil;
import com.ht.common.util.DataConverter;
import com.ht.common.util.ExcelFileUtil;
import com.ht.common.util.ExportExcel;
import com.ht.common.util.FileUtil;
import com.ht.common.util.GenerateSequenceUtil;
import com.ht.common.util.LogHelper;
import com.ht.persistence.dao.inter.background.dicdata.coefficient.CoefficientDao;
import com.ht.persistence.dao.inter.background.dicdata.defectform.DefectFormDao;
import com.ht.persistence.dao.inter.catalog.area.CatalogAreaDao;
import com.ht.persistence.dao.inter.catalog.detail.CatalogDetailDao;
import com.ht.persistence.dao.inter.datum.bookinfo.BookFileDao;
import com.ht.persistence.dao.inter.datum.bookinfo.BookInfoDao;
import com.ht.persistence.dao.inter.datum.bookinfo.ReturnBookDao;
import com.ht.persistence.dao.inter.datum.datum.BorrowingDao;
import com.ht.persistence.dao.inter.datum.datum.DatumFileDao;
import com.ht.persistence.dao.inter.drawtask.taskbook.book.TaskBookDao;
import com.ht.persistence.dao.inter.drawtask.taskbook.relation.TaskBookPlanRelationDao;
import com.ht.persistence.dao.inter.statisticalanalysis.CompilationWorkDaysDao;
import com.ht.persistence.dao.inter.statisticalanalysis.CompilationYearPlanDao;
import com.ht.persistence.dao.inter.system.workflow.publish.VProcessDetailDao;
import com.ht.persistence.dao.inter.system.workflow.task.FlowsDao;
import com.ht.persistence.model.background.dicdata.basedata.BaseData;
import com.ht.persistence.model.background.dicdata.coefficient.Coefficient;
import com.ht.persistence.model.background.dicdata.defectform.DefectForm;
import com.ht.persistence.model.background.organization.employee.UserOrgView;
import com.ht.persistence.model.catalog.area.CatalogArea;
import com.ht.persistence.model.catalog.detail.CatalogDetail;
import com.ht.persistence.model.datum.bookinfo.BookFile;
import com.ht.persistence.model.datum.bookinfo.BookInfo;
import com.ht.persistence.model.datum.bookinfo.ReturnBook;
import com.ht.persistence.model.datum.datum.Borrowing;
import com.ht.persistence.model.datum.datum.DatumFile;
import com.ht.persistence.model.drawtask.plan.Plan;
import com.ht.persistence.model.drawtask.taskbook.book.TaskBook;
import com.ht.persistence.model.drawtask.taskbook.relation.TaskBookPlanRelation;
import com.ht.persistence.model.statisticalanalysis.CompilationWorkDays;
import com.ht.persistence.model.statisticalanalysis.view.CompilationCompleteStatusView;
import com.ht.persistence.model.statisticalanalysis.view.CompilationYearPlanView;
import com.ht.persistence.model.statisticalanalysis.view.CompilationYearPlanView;
import com.ht.persistence.model.statisticalanalysis.view.YearPlanView;
import com.ht.persistence.model.system.notice.Notice;
import com.ht.persistence.model.system.workflow.publish.VProcessDetail;
import com.ht.persistence.model.system.workflow.task.Flows;
import com.ht.service.inter.datum.bookinfo.BookInfoService;
import com.ht.service.inter.datum.datum.BorrowingService;
import com.ht.service.inter.drawtask.plan.PlanService;
import com.ht.service.inter.statisticalanalysis.CompilationYearPlanService;

/**
 * 统计分析——编绘计划Service实现类
 * 
 * @author zyd
 *
 */
public class CompilationYearPlanServiceImpl implements
		CompilationYearPlanService {

	/**
	 * 注入图书资料Dao
	 */
	@Resource(name = "compilationYearPlanDao")
	CompilationYearPlanDao compilationYearPlanDao;

	@Resource(name = "planService")
	PlanService planService;

	@Resource(name = "catalogDetailDao")
	CatalogDetailDao catalogDetailDao;

	@Resource(name = "compilationWorkDaysDao")
	CompilationWorkDaysDao compilationWorkDaysDao;

	@Resource(name = "coefficientDao")
	CoefficientDao coefficientDao;

	@Resource(name = "flowsDao")
	FlowsDao flowsDao;

	@Resource(name = "vProcessDetailDao")
	VProcessDetailDao vProcessDetailDao;

	@Resource(name = "defectFormDao")
	DefectFormDao defectFormDao;

	@Resource(name = "catalogAreaDao")
	CatalogAreaDao catalogAreaDao;

	@Resource(name = "taskBookPlanRelationDao")
	TaskBookPlanRelationDao taskBookPlanRelationDao;

	@Resource(name = "taskBookDao")
	TaskBookDao taskBookDao;

	/**
	 * 获取所有编绘计划
	 */
	@Override
	public List<YearPlanView> getCompilationYearPlan()
			throws Exception {
		try {
			List<YearPlanView> list = compilationYearPlanDao.getCompilationYearPlan();
			
			return list;
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(), e);
			// 抛出异常
			throw e;
		}
	}

	/**
	 * 获取一条编绘计划
	 */
	@Override
	public CompilationYearPlanView getCompilationYearPlanById(String id) {
		try {
			CompilationYearPlanView cyp = new CompilationYearPlanView();
			cyp.setId(id);
			return compilationYearPlanDao.getCompilationYearPlanById(cyp);
		} catch (Exception e) {// 写错误日志
			LogHelper.ERROR.log(e.getMessage(), e);
			// 抛出异常
			throw e;
		}
	}

	/**
	 * 导出
	 */
	@Override
	public InputStream export() {
		InputStream stream = null;
		String sheetName = "编绘计划（生产管理）";
		
		// 数组的形式创建表格标题行
		String[] title ={  "图号", "图名", "比例尺(1:)", "本年度测量性质", "测量周期（基）",
						 "上次测量/编绘性质", "上次测量年份", "任务类别", "计划汇交时间", "实际汇交时间",
						"计划编绘时间", "计划完成时间", "编绘性质", "编绘内容", "调整系数", 
						"工天", "编绘员", "开始时间", "完成时间", "工天", 
						"质检员", "开始时间", "完成时间", "得分","审定员", 
						"开始日期", "完成日期", "工天", "质量评分" };
		List<YearPlanView> yearPlanList;
		try
		{
			yearPlanList = this.getCompilationYearPlan();
			// 创建excel工作簿
			XSSFWorkbook workbook = new XSSFWorkbook();
			// 创建一个工作表
			XSSFSheet sheet = workbook.createSheet(sheetName);
			// 给一个工作表名称一个长度
			sheet.setDefaultColumnWidth(15);
			// 生成一个样式
			XSSFCellStyle style = workbook.createCellStyle();
			// 创建第一行
			XSSFRow row = sheet.createRow(0);
			// 设置样式居中
			style.setAlignment(XSSFCellStyle.ALIGN_CENTER);
			// 创建字体对象
			Font font = workbook.createFont();
			// 字体加粗
			font.setBoldweight(Font.BOLDWEIGHT_BOLD);
			// 添加字体样式
			style.setFont(font);
			// 设置行高
			row.setHeightInPoints(20);
			// 设置第一行标题
			XSSFCell cell = null;
			for (int i = 0; i < title.length; i++)
			{
				cell = row.createCell(i);
				cell.setCellValue(title[i]);
				sheet.setColumnWidth(i, title[i].getBytes().length * 1 * 512);
				cell.setCellStyle(style);
			}
			// 创建记录
			int size = yearPlanList.size();
			if (size > 0)
			{
				for (int i = 1; i <= size; i++)
				{
					XSSFRow xssfRow = sheet.createRow(i);
					XSSFCell xssfCell = null;
					YearPlanView y = yearPlanList.get(i - 1);
					for (int j = 0; j < title.length; j++)
					{

						if (j == 0)
						{
							xssfCell = xssfRow.createCell(j);
							xssfCell.setCellValue(y.getMapNo());
							xssfCell.setCellStyle(style);
						}

						if (j == 1)
						{
							xssfCell = xssfRow.createCell(j);
							xssfCell.setCellValue(y.getMapName());
							xssfCell.setCellStyle(style);
						}

						if (j == 2)
						{
							xssfCell = xssfRow.createCell(j);
							xssfCell.setCellValue(y.getScale());
							xssfCell.setCellStyle(style);
						}
						
						if (j == 3)
						{
							xssfCell = xssfRow.createCell(j);
							xssfCell.setCellValue(y.getType());
							xssfCell.setCellStyle(style);
						}
						
						if (j == 4)
						{
							xssfCell = xssfRow.createCell(j);
							xssfCell.setCellValue(y.getFrederickCycle());
							xssfCell.setCellStyle(style);
						}
						
						if (j == 5)
						{
							xssfCell = xssfRow.createCell(j);
							xssfCell.setCellValue(y.getLastTimeProperty());
							xssfCell.setCellStyle(style);
						}
						
						if (j == 6)
						{
							xssfCell = xssfRow.createCell(j);
							xssfCell.setCellValue(y.getLastTimeDate());
							xssfCell.setCellStyle(style);
						}
						
						if (j == 7)
						{
							xssfCell = xssfRow.createCell(j);
							xssfCell.setCellValue(y.getPlanType());
							xssfCell.setCellStyle(style);
						}
						
						if (j == 8)
						{
							xssfCell = xssfRow.createCell(j);
							if(y.getPlanExchangeTime()!=null){
								xssfCell.setCellValue(y.getPlanExchangeTime());
							}else{
								xssfCell.setCellValue("");
							}
							XSSFCellStyle cellStyle = workbook.createCellStyle();
							cellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
					        XSSFDataFormat format= workbook.createDataFormat();
					        cellStyle.setDataFormat(format.getFormat("yyyy-MM-dd"));
					        xssfCell.setCellStyle(cellStyle);
						}
						
						if (j == 9)
						{
							xssfCell = xssfRow.createCell(j);
							if(y.getActualExchangeTime()!=null){
								xssfCell.setCellValue(y.getActualExchangeTime());
							}else{
								xssfCell.setCellValue("");
							}
							XSSFCellStyle cellStyle = workbook.createCellStyle();
							cellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
					        XSSFDataFormat format= workbook.createDataFormat();
					        cellStyle.setDataFormat(format.getFormat("yyyy-MM-dd"));
					        xssfCell.setCellStyle(cellStyle);
						}
						
						if (j == 10)
						{
							xssfCell = xssfRow.createCell(j);
							if(y.getTaskReleaseTime()!=null){
								xssfCell.setCellValue(y.getTaskReleaseTime());
							}else{
								xssfCell.setCellValue("");
							}
							XSSFCellStyle cellStyle = workbook.createCellStyle();
							cellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
					        XSSFDataFormat format= workbook.createDataFormat();
					        cellStyle.setDataFormat(format.getFormat("yyyy-MM-dd"));
					        xssfCell.setCellStyle(cellStyle);
						}
						
						if (j == 11)
						{
							xssfCell = xssfRow.createCell(j);
							if(y.getPlanCompleteTime()!=null){
								xssfCell.setCellValue(y.getPlanCompleteTime());
							}else{
								xssfCell.setCellValue("");
							}
							XSSFCellStyle cellStyle = workbook.createCellStyle();
							cellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
					        XSSFDataFormat format= workbook.createDataFormat();
					        cellStyle.setDataFormat(format.getFormat("yyyy-MM-dd"));
					        xssfCell.setCellStyle(cellStyle);
						}
						
						if (j == 12)
						{
							xssfCell = xssfRow.createCell(j);
							xssfCell.setCellValue(y.getCompilationProperty());
							xssfCell.setCellStyle(style);
						}
						
						if (j == 13)
						{
							xssfCell = xssfRow.createCell(j);
							xssfCell.setCellValue(y.getCompilationContent());
							xssfCell.setCellStyle(style);
						}
						
						if (j == 14)
						{
							xssfCell = xssfRow.createCell(j);
							xssfCell.setCellValue(y.getAdjustmentCoefficient());
							xssfCell.setCellStyle(style);
						}
						
						if (j == 15)
						{
							xssfCell = xssfRow.createCell(j);
							xssfCell.setCellValue(y.getCompilationWorkDays());
							xssfCell.setCellStyle(style);
						}
						
						if (j == 16)
						{
							xssfCell = xssfRow.createCell(j);
							xssfCell.setCellValue(y.getCompilationClerk());
							xssfCell.setCellStyle(style);
						}
						
						if (j == 17)
						{
							xssfCell = xssfRow.createCell(j);
							if(y.getCompilationStartTime()!=null){
								xssfCell.setCellValue(y.getCompilationStartTime());
							}else{
								xssfCell.setCellValue("");
							}
							XSSFCellStyle cellStyle = workbook.createCellStyle();
							cellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
					        XSSFDataFormat format= workbook.createDataFormat();
					        cellStyle.setDataFormat(format.getFormat("yyyy-MM-dd"));
					        xssfCell.setCellStyle(cellStyle);
						}
						
						if (j == 18)
						{
							xssfCell = xssfRow.createCell(j);
							if(y.getCompilationEndTime()!=null){
								xssfCell.setCellValue(y.getCompilationEndTime());
							}else{
								xssfCell.setCellValue("");
							}
							XSSFCellStyle cellStyle = workbook.createCellStyle();
							cellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
					        XSSFDataFormat format= workbook.createDataFormat();
					        cellStyle.setDataFormat(format.getFormat("yyyy-MM-dd"));
					        xssfCell.setCellStyle(cellStyle);
						}
						
						if (j == 19)
						{
							xssfCell = xssfRow.createCell(j);
							xssfCell.setCellValue(y.getQualityWorkDays());
							xssfCell.setCellStyle(style);
						}
						
						if (j == 20)
						{
							xssfCell = xssfRow.createCell(j);
							xssfCell.setCellValue(y.getInspector());
							xssfCell.setCellStyle(style);
						}
						
						if (j == 21)
						{
							xssfCell = xssfRow.createCell(j);
							if(y.getQualityStartTime()!=null){
								xssfCell.setCellValue(y.getQualityStartTime());
							}else{
								xssfCell.setCellValue("");
							}
							XSSFCellStyle cellStyle = workbook.createCellStyle();
							cellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
					        XSSFDataFormat format= workbook.createDataFormat();
					        cellStyle.setDataFormat(format.getFormat("yyyy-MM-dd"));
					        xssfCell.setCellStyle(cellStyle);
						}
						
						if (j == 22)
						{
							xssfCell = xssfRow.createCell(j);
							if(y.getQualityEndTime()!=null){
								xssfCell.setCellValue(y.getQualityEndTime());
							}else{
								xssfCell.setCellValue("");
							}
							XSSFCellStyle cellStyle = workbook.createCellStyle();
							cellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
					        XSSFDataFormat format= workbook.createDataFormat();
					        cellStyle.setDataFormat(format.getFormat("yyyy-MM-dd"));
					        xssfCell.setCellStyle(cellStyle);
						}
						
						if (j == 23)
						{
							xssfCell = xssfRow.createCell(j);
							xssfCell.setCellValue(y.getQualityScore());
							xssfCell.setCellStyle(style);
						}
						
						if (j == 24)
						{
							xssfCell = xssfRow.createCell(j);
							xssfCell.setCellValue(y.getAuthorizedOfficer());
							xssfCell.setCellStyle(style);
						}
						
						if (j == 25)
						{
							xssfCell = xssfRow.createCell(j);
							if(y.getAuthorizedStartTime()!=null){
								xssfCell.setCellValue(y.getAuthorizedStartTime());
							}else{
								xssfCell.setCellValue("");
							}
							XSSFCellStyle cellStyle = workbook.createCellStyle();
							cellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
					        XSSFDataFormat format= workbook.createDataFormat();
					        cellStyle.setDataFormat(format.getFormat("yyyy-MM-dd"));
					        xssfCell.setCellStyle(cellStyle);
						}
						
						if (j == 26)
						{
							xssfCell = xssfRow.createCell(j);
							if(y.getAuthorizedEndTime()!=null){
								xssfCell.setCellValue(y.getAuthorizedEndTime());
							}else{
								xssfCell.setCellValue("");
							}
							XSSFCellStyle cellStyle = workbook.createCellStyle();
							cellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
					        XSSFDataFormat format= workbook.createDataFormat();
					        cellStyle.setDataFormat(format.getFormat("yyyy-MM-dd"));
					        xssfCell.setCellStyle(cellStyle);
						}
						
						if (j == 27)
						{
							xssfCell = xssfRow.createCell(j);
							xssfCell.setCellValue(y.getAuthorizedWorkDays());
							xssfCell.setCellStyle(style);
						}
						
						if (j == 28)
						{
							xssfCell = xssfRow.createCell(j);
							xssfCell.setCellValue(y.getQualityAchievement());
							xssfCell.setCellStyle(style);
						}
					}
				}
			}
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			workbook.write(outputStream);
			outputStream.flush();
			byte[] byteArray = outputStream.toByteArray();
			stream = new ByteArrayInputStream(byteArray, 0, byteArray.length);
			outputStream.close();
		}
		catch (Exception e)
		{
			e.getMessage();
		}
		return stream;
	}

}
