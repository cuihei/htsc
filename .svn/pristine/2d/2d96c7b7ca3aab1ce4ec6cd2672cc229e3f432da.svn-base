package com.ht.common.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @ClassName: BuildExcelTemplate
 * @Description: 导出Excel模板
 * @author penghao
 * @date 2016年10月17日 下午12:51:31
 * 
 */
public class BuildExcelTemplate {
	/**
	 * 导出下载模板
	 * 
	 * @param String sheetName sheet名称
	 * @param String[] title 第一行标题
	 * @return
	 * @throws IOException 
	 */
	public static InputStream getTemplate(String sheetName, String[] title) throws IOException {
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
			sheet.setColumnWidth(i, title[i].getBytes().length * 2 * 256);
			cell.setCellStyle(style);
		}
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		workbook.write(outputStream);
		outputStream.flush();
		byte[] byteArray = outputStream.toByteArray();
		InputStream stream = new ByteArrayInputStream(byteArray, 0, byteArray.length);
		outputStream.close();
		return stream;
	}
}
