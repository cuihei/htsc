package com.ht.service.impl.background.monitor.operationlog;

import java.io.File;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.ht.common.util.DataConverter;
import com.ht.common.util.LogHelper;
import com.ht.persistence.dao.inter.background.monitor.operationlog.SyslogOperationDao;
import com.ht.persistence.model.background.monitor.accesslog.Syslog;
import com.ht.persistence.model.background.monitor.operationlog.SyslogOperation;
import com.ht.service.inter.background.monitor.operationlog.SyslogOperationService;

/**
 * 系统操作日志实现类
 * @author liukai
 */
public class SyslogOperationServiceImpl implements SyslogOperationService {
	
	/**
	 * 注入系统操作日志Dao
	 * */
	@Resource(name = "syslogOperationDao") 
	private SyslogOperationDao syslogOperationDao;
	
	/**
	 * 新增系统操作日志
	 * @param soParam 操作日志数据
	 */
	@Override
	public void addSyslogOperation(String soParam) throws Exception {
		try {
			//将操作日志String类型转成SyslogOperation类型
			SyslogOperation syslogOperation = (SyslogOperation) DataConverter.convertJson2Object(soParam, SyslogOperation.class);
			//设置操作时间
			Date date = new Date();   
		    DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    String dateStr = sdf.format(date);
		    syslogOperation.setOperationTime(dateStr);
			//执行保存操作
			syslogOperationDao.addSyslogOperation(syslogOperation);
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}

	/**
	 * 修改系统操作日志
	 * @param soParam 操作日志数据
	 */
	@Override
	public void modifySyslogOperation(String soParam) throws Exception {
		try {
			//将操作日志String类型转成SyslogOperation类型
			SyslogOperation syslogOperation = (SyslogOperation) DataConverter.convertJson2Object(soParam, SyslogOperation.class);
			// 更新syslogOperation
			syslogOperationDao.modifySyslogOperation(syslogOperation);
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}

	/**
	 * 删除系统操作日志
	 * @param id 操作日志主键id
	 */
	@Override
	public void delSyslogOperation(String syslogOperation) throws Exception {
		try {
			// 将日志String类型转成Syslog对象
			List<SyslogOperation> list = (List<SyslogOperation>) DataConverter.convertJson2List(syslogOperation,SyslogOperation.class);
			for (int i = 0; i < list.size(); i++) {
				// 删除SyslogOperation
				syslogOperationDao.delSyslogOperation(list.get(i));
			}
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}
	
	/**
	 * 获取系统操作日志列表
	 */
	@Override
	public List<SyslogOperation> getSyslogOperation() throws Exception {
		try {
			// 获取所有SyslogOperation
			return syslogOperationDao.getSyslogOperation();
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}
	
	/**
	 * 根据id系统操作日志数据
	 * @param id 操作日志主键id
	 */
	@Override
	public SyslogOperation getSyslogOperation(String id) throws Exception {
		try {
			SyslogOperation syslogOperation = new SyslogOperation();
			syslogOperation.setId(id);
			// 根据id获取SyslogOperation
			return syslogOperationDao.getSyslogOperation(syslogOperation);
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}
	
	/**
	 * 导出下载
	 * @param String sheetName sheet名称
	 * @param String[] title 第一行标题
	 * @param String url 下载后存放的位置
	 * @param String excelName excel的名称
	 * @param List<SyslogOperation> soList 对象list
	 * @return
	 */
	@Override
	public void exportExcel(String sheetName, String[] title, String url, String excelName,  List<SyslogOperation> soList) {
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
		//字体加粗
		font.setBoldweight(Font.BOLDWEIGHT_BOLD);
		//添加字体样式
		style.setFont(font);
		// 设置行高
		row.setHeightInPoints(20);
		// 设置第一行标题
		XSSFCell cell = null;
		for (int i = 0; i < title.length; i++) {
			cell = row.createCell(i);
			cell.setCellValue(title[i]);
			sheet.setColumnWidth(i, title[i].getBytes().length * 1 * 256);
			cell.setCellStyle(style);
		}
		//创建记录
		int size = soList.size();
		if (size>0) {
			for (int i = 1; i <= size; i++) {
				XSSFRow xssfRow = sheet.createRow(i);
				XSSFCell xssfCell = null;
				SyslogOperation s = soList.get(i-1);
				for (int j = 0; j < title.length; j++) {
					if (j==0) {
						xssfCell = xssfRow.createCell(j);
						xssfCell.setCellValue(s.getId());
						//sheet.setColumnWidth(i, hcd.getMapNo().getBytes().length * 1 * 256);
					}
					if (j==1) {
						xssfCell = xssfRow.createCell(j);
						xssfCell.setCellValue(s.getOperatorId());
						//sheet.setColumnWidth(i, hcd.getMapName().getBytes().length * 1 * 256);
					}
					if (j==2) {
						xssfCell = xssfRow.createCell(j);
						xssfCell.setCellValue(s.getOperatorIp());
						//sheet.setColumnWidth(i, hcd.getScale().getBytes().length * 1 * 256);
					}
					if (j==3) {
						xssfCell = xssfRow.createCell(j);
						xssfCell.setCellValue(s.getOperationBehavior());
						//sheet.setColumnWidth(i, hcd.getEastLongitude().getBytes().length * 1 * 256);
					}
					if (j==4) {
						xssfCell = xssfRow.createCell(j);
						xssfCell.setCellValue(s.getOperationResult());
						//sheet.setColumnWidth(i, hcd.getEastLongitude().getBytes().length * 1 * 256);
					}
					if (j==5) {
						xssfCell = xssfRow.createCell(j);
						xssfCell.setCellValue(s.getOperationObject());
						//sheet.setColumnWidth(i, hcd.getEastLongitude().getBytes().length * 1 * 256);
					}
					if (j==6) {
						if(s.getOperationTime()!=null){
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
							String time = sdf.format(s.getOperationTime());
							xssfCell = xssfRow.createCell(j);
							xssfCell.setCellValue(time);
							//sheet.setColumnWidth(i, hcd.getEastLongitude().getBytes().length * 1 * 256);
						}else{
							xssfCell = xssfRow.createCell(j);
							xssfCell.setCellValue("");
						}
					}
					xssfCell.setCellStyle(style);
				}
			}
		}
		// 创建一个文件
		File file = new File(url + excelName+".xls");
		try {
			file.createNewFile();
			FileOutputStream stream = new FileOutputStream(file);
			workbook.write(stream);
			stream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
