package com.ht.common.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;

/**
 * 利用开源组件POI3.0.2动态导出EXCEL文档
 * 
 * @author zyd
 * @version v1.0
 * @param <t>
 *            应用泛型，代表任意一个符合javabean风格的类
 *            注意这里为了简单起见，boolean型的属性xxx的get器方式为getXxx(),而不是isXxx()
 */
//T这里代表一个不确定是实体类，即参数实体
public class ExportExcel<t> {
 
    /**
     * 这是一个通用的方法，利用了JAVA的反射机制，可以将放置在JAVA集合中并且符号一定条件的数据以EXCEL 的形式输出
     * 
     * @param title
     *            表格标题名
     * @param headersName
     *            表格属性列名数组
     * @param headersId
     *            表格属性列名对应的字段
     * @param dataset
     *            需要显示的数据集合,集合中一定要放置符合javabean风格的类的对象
     * @param out
     *            与输出设备关联的流对象，可以将EXCEL文档导出到本地文件或者网络中
     */
    public boolean exportExcel(String title, String[] headersName,String[] headersId,
            List<t> dtoList,String path) {
    	
        //表头
        Map<Integer, String> map = new HashMap<Integer, String>();
        int key=0;
        for (int i = 0; i < headersName.length; i++) {
            if (!headersName[i].equals(null)) {
                map.put(key, headersName[i]);
                key++;
            }
        }
        //字段
        Map<Integer, String> zdMap = new HashMap<Integer, String>();
        int value = 0;
        for (int i = 0; i < headersId.length; i++) {
            if (!headersId[i].equals(null)) {
                zdMap.put(value, headersId[i]);
                value++;
            }
        }
        // 声明一个工作薄
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet(title);
        sheet.setColumnWidth((short) 0, (short) 9000);
        sheet.setColumnWidth((short) 1, (short) 6000);
        sheet.setColumnWidth((short) 2, (short) 6000);
        sheet.setColumnWidth((short) 3, (short) 6000);
        sheet.setColumnWidth((short) 4, (short) 6000);
        sheet.setColumnWidth((short) 5, (short) 6000);
        sheet.setColumnWidth((short) 6, (short) 6000);
        sheet.setColumnWidth((short) 7, (short) 6000);
        sheet.setColumnWidth((short) 8, (short) 6000);
        sheet.setColumnWidth((short) 9, (short) 6000);
        sheet.setColumnWidth((short) 10, (short) 6000);
        sheet.setColumnWidth((short) 11, (short) 6000);
        sheet.setColumnWidth((short) 12, (short) 6000);
        sheet.setColumnWidth((short) 13, (short) 6000);
        sheet.setColumnWidth((short) 14, (short) 6000);
        sheet.setColumnWidth((short) 15, (short) 6000);
        sheet.setColumnWidth((short) 16, (short) 6000);
        sheet.setColumnWidth((short) 17, (short) 6000);
        sheet.setColumnWidth((short) 18, (short) 6000);
        sheet.setColumnWidth((short) 19, (short) 6000);
        sheet.setColumnWidth((short) 20, (short) 6000);
        sheet.setColumnWidth((short) 21, (short) 6000);
        sheet.setColumnWidth((short) 22, (short) 6000);
        sheet.setColumnWidth((short) 23, (short) 6000);
        sheet.setColumnWidth((short) 24, (short) 6000);
        sheet.setColumnWidth((short) 25, (short) 6000);
        sheet.setColumnWidth((short) 26, (short) 6000);
        sheet.setColumnWidth((short) 27, (short) 6000);
        sheet.setColumnWidth((short) 28, (short) 6000);
        sheet.setColumnWidth((short) 29, (short) 6000);
        sheet.setColumnWidth((short) 30, (short) 6000);
        sheet.setColumnWidth((short) 31, (short) 6000);
        sheet.setColumnWidth((short) 32, (short) 6000);
        sheet.setColumnWidth((short) 33, (short) 6000);
        sheet.setColumnWidth((short) 34, (short) 6000);
        sheet.setColumnWidth((short) 35, (short) 6000);
        sheet.setColumnWidth((short) 36, (short) 6000);
        sheet.setColumnWidth((short) 37, (short) 6000);
        sheet.setColumnWidth((short) 38, (short) 6000);
        sheet.setColumnWidth((short) 39, (short) 6000);
        sheet.setColumnWidth((short) 40, (short) 6000);
        sheet.setColumnWidth((short) 41, (short) 6000);
        sheet.setColumnWidth((short) 42, (short) 6000);
        sheet.setColumnWidth((short) 43, (short) 6000);
        sheet.setColumnWidth((short) 44, (short) 6000);
        sheet.setColumnWidth((short) 45, (short) 6000);
        sheet.setColumnWidth((short) 46, (short) 6000);
        // 生成一个样式  
        HSSFCellStyle titlestyle = wb.createCellStyle();
        
        // 设置单元格边框样式
        titlestyle.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框 细边线
        titlestyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);// 下边框 细边线
        titlestyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框 细边线
        titlestyle.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框 细边线
        //设置单元格背景样式
        titlestyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
        titlestyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        // 设置单元格对齐方式
        titlestyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 水平居中
        titlestyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 垂直居中
        // 设置字体样式
        Font titleFont = wb.createFont();
        titleFont.setFontHeightInPoints((short) 14); // 字体高度
        titleFont.setFontName("华文仿宋"); // 字体样式
        titlestyle.setFont(titleFont);
        HSSFRow row = sheet.createRow(0);
        HSSFCell cell;
        HSSFCell celldata;
        Collection c = map.values();
        Iterator<String> it = c.iterator();
        //产生表格标题行
        HSSFRow row1 = sheet.createRow(0);
        for (int i = 0; i < headersId.length; i++) {
           HSSFCell cell1 = row1.createCell(i);
           cell1.setCellStyle(titlestyle);
           HSSFRichTextString text = new HSSFRichTextString(headersId[i]);
           cell1.setCellValue(text);
        }
        //根据选择的字段生成表头
        short size = 0;
        while (it.hasNext()) {
                cell = row1.createCell(size);
            cell.setCellValue(it.next().toString());
            cell.setCellStyle(titlestyle);
            size++;
        }
        // 字段        
        Collection zdC = zdMap.values();
        Iterator<t> labIt = (Iterator<t>) dtoList.iterator();
        
        // 数据样式 因为标题和数据样式不同 需要分开设置 不然会覆盖
        HSSFCellStyle dataStyle = wb.createCellStyle();
        // 设置边框样式
        dataStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框 细边线
        dataStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);// 下边框 细边线
        dataStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框 细边线
        dataStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框 细边线
        // 设置居中样式
        dataStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 水平居中
        dataStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 垂直居中
        int zdRow =0;
        while (labIt.hasNext()) {
            int zdCell = 0;
            zdRow++;
            row1 = sheet.createRow(zdRow);
            t l = (t) labIt.next();
            // 利用反射，根据javabean属性的先后顺序，动态调用getXxx()方法得到属性值
            Field[] fields1 = l.getClass().getDeclaredFields();
            Field[] declaredFields = l.getClass().getSuperclass().getDeclaredFields();
            Field[] fields = ArrayUtils.addAll(fields1, declaredFields);
            for (short i = 0; i < fields.length; i++) {
                Field field = fields[i];
                String fieldName = field.getName();
//              System.out.println(fieldName);
                Iterator<String> zdIt = zdC.iterator();
                while (zdIt.hasNext()) {
                    if (zdIt.next().equals(fieldName)) {
                        String getMethodName = "get"
                            + fieldName.substring(0, 1).toUpperCase()
                            + fieldName.substring(1);
                        Class tCls = l.getClass();
                        try {
                            Method getMethod = tCls.getMethod(getMethodName,
                                    new Class[] {});
                            Object val = getMethod.invoke(l, new Object[] {});
//                          System.out.println(fields[i].getName());
//                          System.out.println(val);
                            String textVal = null;
                            if (val!= null) {
                                textVal = val.toString();
                            }else{
                                textVal = null;
                            }
                            celldata = row1.createCell((short) zdCell);
                            celldata.setCellValue(textVal);
                            celldata.setCellStyle(dataStyle);
                            zdCell++;
                        } catch (SecurityException e) {
                            e.printStackTrace();
                        } catch (IllegalArgumentException e) {
                            e.printStackTrace();
                        } catch (NoSuchMethodException e) {
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }finally {
                            //清理资源
                        }
                    }
                }
                 
            }
        }
        try {
            
        	FileOutputStream xls = new FileOutputStream(path);
            wb.write(xls); 
            xls.close();
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    
    
    
    
    public boolean exportExcelCompilationFinish(String title, String[] headersName,String[] headersId,
            List<t> dtoList,List<t> dtoList1,String path) {
    	
    	//标题
    	HSSFWorkbook wb = new HSSFWorkbook();
    	   HSSFSheet sheet = wb.createSheet(title);
    	
    	
    	
    	HSSFRow firstHeaderRow=sheet.createRow(0);// 第一道行
    	sheet.addMergedRegion(new CellRangeAddress(0,0,0,9));
    	HSSFCell titleCell=firstHeaderRow.createCell(0);
    	titleCell.setCellValue("沿海港口航道图编印工作量完成情况统计表");
    	  // 设置标题字体样式
    	 HSSFCellStyle titlestyle1 = wb.createCellStyle();
    	 titlestyle1.setAlignment(HSSFCellStyle.ALIGN_CENTER);//水平居中
        Font headFont = wb.createFont();
        headFont.setFontHeightInPoints((short) 24); // 字体高度
        headFont.setFontName("黑体"); // 字体样式
      
        titlestyle1.setFont(headFont);
        titleCell.setCellStyle(titlestyle1);
        
        
        
        //单位
    	HSSFRow companyRow=sheet.createRow(1);// 第一道行
    	sheet.addMergedRegion(new CellRangeAddress(0,0,0,9));
      	HSSFCell companyCell=companyRow.createCell(0);
      	companyCell.setCellValue("填报单位（盖章）：上海海图中心");
      	//设置格式
      	 HSSFCellStyle companytyle1 = wb.createCellStyle();
        Font companyFont = wb.createFont();
        companyFont.setFontHeightInPoints((short) 10); // 字体高度
        companyFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//粗体显示
        companyFont.setFontName("黑体"); // 字体样式
      
        companytyle1.setFont(companyFont);
        companyCell.setCellStyle(companytyle1);
        
 
        
        //表头
        Map<Integer, String> map = new HashMap<Integer, String>();
        int key=1;
        for (int i = 0; i < headersName.length; i++) {
            if (!headersName[i].equals(null)) {
                map.put(key, headersName[i]);
                key++;
            }
        }
        //字段
        Map<Integer, String> zdMap = new HashMap<Integer, String>();
        int value = 0;
        for (int i = 0; i < headersId.length; i++) {
            if (!headersId[i].equals(null)) {
                zdMap.put(value, headersId[i]);
                value++;
            }
        }
        // 声明一个工作薄
    //    HSSFWorkbook wb = new HSSFWorkbook();
 //       HSSFSheet sheet = wb.createSheet(title);
        sheet.setColumnWidth((short) 0, (short) 9000);
        sheet.setColumnWidth((short) 1, (short) 6000);
        sheet.setColumnWidth((short) 2, (short) 6000);
        sheet.setColumnWidth((short) 3, (short) 6000);
        sheet.setColumnWidth((short) 4, (short) 6000);
        sheet.setColumnWidth((short) 5, (short) 6000);
        sheet.setColumnWidth((short) 6, (short) 6000);
        sheet.setColumnWidth((short) 7, (short) 6000);
        sheet.setColumnWidth((short) 8, (short) 6000);
        sheet.setColumnWidth((short) 9, (short) 6000);
        sheet.setColumnWidth((short) 10, (short) 6000);
        sheet.setColumnWidth((short) 11, (short) 6000);
        sheet.setColumnWidth((short) 12, (short) 6000);
        sheet.setColumnWidth((short) 13, (short) 6000);
        sheet.setColumnWidth((short) 14, (short) 6000);
        sheet.setColumnWidth((short) 15, (short) 6000);
        sheet.setColumnWidth((short) 16, (short) 6000);
        sheet.setColumnWidth((short) 17, (short) 6000);
        sheet.setColumnWidth((short) 18, (short) 6000);
        sheet.setColumnWidth((short) 19, (short) 6000);
        sheet.setColumnWidth((short) 20, (short) 6000);
        sheet.setColumnWidth((short) 21, (short) 6000);
        sheet.setColumnWidth((short) 22, (short) 6000);
        sheet.setColumnWidth((short) 23, (short) 6000);
        sheet.setColumnWidth((short) 24, (short) 6000);
        sheet.setColumnWidth((short) 25, (short) 6000);
        sheet.setColumnWidth((short) 26, (short) 6000);
        sheet.setColumnWidth((short) 27, (short) 6000);
        sheet.setColumnWidth((short) 28, (short) 6000);
        sheet.setColumnWidth((short) 29, (short) 6000);
        sheet.setColumnWidth((short) 30, (short) 6000);
        sheet.setColumnWidth((short) 31, (short) 6000);
        sheet.setColumnWidth((short) 32, (short) 6000);
        sheet.setColumnWidth((short) 33, (short) 6000);
        sheet.setColumnWidth((short) 34, (short) 6000);
        sheet.setColumnWidth((short) 35, (short) 6000);
        sheet.setColumnWidth((short) 36, (short) 6000);
        sheet.setColumnWidth((short) 37, (short) 6000);
        sheet.setColumnWidth((short) 38, (short) 6000);
        sheet.setColumnWidth((short) 39, (short) 6000);
        sheet.setColumnWidth((short) 40, (short) 6000);
        sheet.setColumnWidth((short) 41, (short) 6000);
        sheet.setColumnWidth((short) 42, (short) 6000);
        sheet.setColumnWidth((short) 43, (short) 6000);
        sheet.setColumnWidth((short) 44, (short) 6000);
        sheet.setColumnWidth((short) 45, (short) 6000);
        sheet.setColumnWidth((short) 46, (short) 6000);
        // 生成一个样式  
        HSSFCellStyle titlestyle = wb.createCellStyle();
        
        // 设置单元格边框样式
        titlestyle.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框 细边线
        titlestyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);// 下边框 细边线
        titlestyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框 细边线
        titlestyle.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框 细边线
        //设置单元格背景样式
        titlestyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
        titlestyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        // 设置单元格对齐方式
        titlestyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 水平居中
        titlestyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 垂直居中
        // 设置字体样式
        Font titleFont = wb.createFont();
        titleFont.setFontHeightInPoints((short) 14); // 字体高度
        titleFont.setFontName("华文仿宋"); // 字体样式
        titlestyle.setFont(titleFont);
        HSSFRow row = sheet.createRow(2);
        HSSFCell cell;
        HSSFCell celldata;
        Collection c = map.values();
        Iterator<String> it = c.iterator();
        //产生表格标题行
        HSSFRow row1 = sheet.createRow(2);
        for (int i = 0; i < headersId.length-1; i++) {
           HSSFCell cell1 = row1.createCell(i+1);
           cell1.setCellStyle(titlestyle);
           HSSFRichTextString text = new HSSFRichTextString(headersId[i]);
           cell1.setCellValue(text);
        }
        //根据选择的字段生成表头
        short size = 0;
        while (it.hasNext()) {
                cell = row1.createCell(size);
            cell.setCellValue(it.next().toString());
            cell.setCellStyle(titlestyle);
            size++;
        }
        
        
        HSSFRow zhiRow=sheet.createRow(3);// 第一道行
    	sheet.addMergedRegion(new CellRangeAddress(0,0,0,9));
      	HSSFCell zhiCell=zhiRow.createCell(0);
      	zhiCell.setCellValue("一、纸海图");
      	//设置格式
      	 HSSFCellStyle zhityle1 = wb.createCellStyle();
        Font zhiyFont = wb.createFont();
        zhiyFont.setFontHeightInPoints((short) 10); // 字体高度      
        zhiyFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//粗体显示
        zhiyFont.setFontName("黑体"); // 字体样式
      
        zhityle1.setFont(zhiyFont);
        zhiCell.setCellStyle(zhityle1);
        
        
        
        
        // 字段        
        Collection zdC = zdMap.values();
        Iterator<t> labIt = (Iterator<t>) dtoList.iterator();
        
        Iterator<t> labIt1 = (Iterator<t>) dtoList1.iterator();
        
        // 数据样式 因为标题和数据样式不同 需要分开设置 不然会覆盖
        HSSFCellStyle dataStyle = wb.createCellStyle();
        // 设置边框样式
        dataStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框 细边线
        dataStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);// 下边框 细边线
        dataStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框 细边线
        dataStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框 细边线
        // 设置居中样式
        dataStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 水平居中
        dataStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 垂直居中
        int zdRow =3;
        while (labIt.hasNext()) {
            int zdCell = 0;
            zdRow++;
            row1 = sheet.createRow(zdRow);
            t l = (t) labIt.next();
            // 利用反射，根据javabean属性的先后顺序，动态调用getXxx()方法得到属性值
            Field[] fields1 = l.getClass().getDeclaredFields();
            Field[] declaredFields = l.getClass().getSuperclass().getDeclaredFields();
            Field[] fields = ArrayUtils.addAll(fields1, declaredFields);
            for (short i = 0; i < fields.length; i++) {
                Field field = fields[i];
                String fieldName = field.getName();
//              System.out.println(fieldName);
                Iterator<String> zdIt = zdC.iterator();
                while (zdIt.hasNext()) {
                    if (zdIt.next().equals(fieldName)) {
                        String getMethodName = "get"
                            + fieldName.substring(0, 1).toUpperCase()
                            + fieldName.substring(1);
                        Class tCls = l.getClass();
                        try {
                            Method getMethod = tCls.getMethod(getMethodName,
                                    new Class[] {});
                            Object val = getMethod.invoke(l, new Object[] {});
//                          System.out.println(fields[i].getName());
//                          System.out.println(val);
                            String textVal = null;
                            if (val!= null) {
                                textVal = val.toString();
                            }else{
                                textVal = null;
                            }
                            celldata = row1.createCell((short) zdCell);
                            celldata.setCellValue(textVal);
                            celldata.setCellStyle(dataStyle);
                            zdCell++;
                        } catch (SecurityException e) {
                            e.printStackTrace();
                        } catch (IllegalArgumentException e) {
                            e.printStackTrace();
                        } catch (NoSuchMethodException e) {
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }finally {
                            //清理资源
                        }
                    }
                }
                 
            }
        }
        
     int roww=   zdRow++;
     
     //累计
     HSSFRow leiRow=sheet.createRow(roww+1);// 第一道行
 	sheet.addMergedRegion(new CellRangeAddress(0,0,0,3));
   	HSSFCell lei1Cell=leiRow.createCell(2);
   	lei1Cell.setCellValue("完成小改正累计（幅）：");
  	HSSFCell lei2Cell=leiRow.createCell(5);
  	lei2Cell.setCellValue("完成上年度累计（幅）:");
  	HSSFCell lei3Cell=leiRow.createCell(8);
  	lei3Cell.setCellValue("完成本年度累计（幅）:");
	HSSFCell lei4Cell=leiRow.createCell(11);
  	lei4Cell.setCellValue("全年累计完成合计(幅）:");
  	
   	//设置格式
   	 HSSFCellStyle leityle1 = wb.createCellStyle();
     Font leiFont = wb.createFont();
     leiFont.setFontHeightInPoints((short) 10); // 字体高度      
     leiFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//粗体显示
     leiFont.setFontName("黑体"); // 字体样式
   
     leityle1.setFont(leiFont);
     lei1Cell.setCellStyle(leityle1);
     lei2Cell.setCellStyle(leityle1);
     lei3Cell.setCellStyle(leityle1);
     lei4Cell.setCellStyle(leityle1);
     
     int roww1=   zdRow++;
     
     //电子海图
     HSSFRow dianRow=sheet.createRow(roww1+1);// 第一道行
 	sheet.addMergedRegion(new CellRangeAddress(0,0,0,9));
   	HSSFCell dianCell=dianRow.createCell(0);
   	dianCell.setCellValue("二、电子海图");
   	//设置格式
   	 HSSFCellStyle diantyle1 = wb.createCellStyle();
     Font dianFont = wb.createFont();
     dianFont.setFontHeightInPoints((short) 10); // 字体高度      
     dianFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//粗体显示
     dianFont.setFontName("黑体"); // 字体样式
   
     diantyle1.setFont(dianFont);
     dianCell.setCellStyle(diantyle1);
     
        
        while (labIt1.hasNext()) {
            int zdCell = 0;
            zdRow++;
            row1 = sheet.createRow(zdRow);
            t l = (t) labIt1.next();
            // 利用反射，根据javabean属性的先后顺序，动态调用getXxx()方法得到属性值
            Field[] fields1 = l.getClass().getDeclaredFields();
            Field[] declaredFields = l.getClass().getSuperclass().getDeclaredFields();
            Field[] fields = ArrayUtils.addAll(fields1, declaredFields);
            for (short i = 0; i < fields.length; i++) {
                Field field = fields[i];
                String fieldName = field.getName();
//              System.out.println(fieldName);
                Iterator<String> zdIt = zdC.iterator();
                while (zdIt.hasNext()) {
                    if (zdIt.next().equals(fieldName)) {
                        String getMethodName = "get"
                            + fieldName.substring(0, 1).toUpperCase()
                            + fieldName.substring(1);
                        Class tCls = l.getClass();
                        try {
                            Method getMethod = tCls.getMethod(getMethodName,
                                    new Class[] {});
                            Object val = getMethod.invoke(l, new Object[] {});
//                          System.out.println(fields[i].getName());
//                          System.out.println(val);
                            String textVal = null;
                            if (val!= null) {
                                textVal = val.toString();
                            }else{
                                textVal = null;
                            }
                            celldata = row1.createCell((short) zdCell);
                            celldata.setCellValue(textVal);
                            celldata.setCellStyle(dataStyle);
                            zdCell++;
                        } catch (SecurityException e) {
                            e.printStackTrace();
                        } catch (IllegalArgumentException e) {
                            e.printStackTrace();
                        } catch (NoSuchMethodException e) {
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }finally {
                            //清理资源
                        }
                    }
                }
                 
            }
        }
        
        
        int rowr=   zdRow++;
        
        //累计
        HSSFRow leidianRow=sheet.createRow(rowr+1);// 第一道行
    	sheet.addMergedRegion(new CellRangeAddress(0,0,0,3));
      
     	HSSFCell leidian2Cell=leidianRow.createCell(5);
     	leidian2Cell.setCellValue("完成上年度累计（幅）:");
     	HSSFCell leidian3Cell=leidianRow.createCell(8);
     	leidian3Cell.setCellValue("完成本年度累计（幅）:");
    	HSSFCell leidian4Cell=leidianRow.createCell(11);
    	leidian4Cell.setCellValue("全年累计完成合计(幅）:");
     	
      	//设置格式
      	 HSSFCellStyle leidiantyle1 = wb.createCellStyle();
        Font leidianFont = wb.createFont();
        leidianFont.setFontHeightInPoints((short) 10); // 字体高度      
        leidianFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//粗体显示
        leidianFont.setFontName("黑体"); // 字体样式
      
        leidiantyle1.setFont(leidianFont);
        leidian2Cell.setCellStyle(leidiantyle1);
        leidian3Cell.setCellStyle(leidiantyle1);
        leidian4Cell.setCellStyle(leidiantyle1);
        
        
        
        
 int rowrr=   zdRow++;
        
        //
        HSSFRow luoRow=sheet.createRow(rowrr+1);// 第一道行
    	sheet.addMergedRegion(new CellRangeAddress(0,0,0,2));
      
     	HSSFCell luo1Cell=luoRow.createCell(0);
     	luo1Cell.setCellValue("单位负责人：");
     	HSSFCell luo2Cell=luoRow.createCell(2);
     	luo2Cell.setCellValue("统计负责人：");
    	HSSFCell luo3Cell=luoRow.createCell(4);
    	luo3Cell.setCellValue("填表人：");
       	HSSFCell luo4Cell=luoRow.createCell(6);
    	luo4Cell.setCellValue("联系电话：");
    	HSSFCell luo5Cell=luoRow.createCell(8);
    	luo5Cell.setCellValue("报出日期：");
     	
      	//设置格式
      	 HSSFCellStyle luotyle1 = wb.createCellStyle();
        Font luoFont = wb.createFont();
        luoFont.setFontHeightInPoints((short) 10); // 字体高度      
      //  luoFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//粗体显示
        luoFont.setFontName("黑体"); // 字体样式
      
        luotyle1.setFont(luoFont);
        luo1Cell.setCellStyle(luotyle1);
        luo2Cell.setCellStyle(luotyle1);
        luo3Cell.setCellStyle(luotyle1);
        luo4Cell.setCellStyle(luotyle1);
        luo5Cell.setCellStyle(luotyle1);
        
        
        try {
            
        	FileOutputStream xls = new FileOutputStream(path);
            wb.write(xls); 
            xls.close();
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    
    public boolean exportExcelCompilationSum(String title,int zhi,int dian ,String path) {
    	
    	//标题
    	HSSFWorkbook wb = new HSSFWorkbook();
    	   HSSFSheet sheet = wb.createSheet(title);
    	
    	
    	
    	HSSFRow firstHeaderRow=sheet.createRow(0);// 第一道行
    	sheet.addMergedRegion(new CellRangeAddress(0,0,0,6));
    	HSSFCell titleCell=firstHeaderRow.createCell(0);
    	titleCell.setCellValue(title);
    	  // 设置标题字体样式
    	 HSSFCellStyle titlestyle1 = wb.createCellStyle();
    	 titlestyle1.setAlignment(HSSFCellStyle.ALIGN_CENTER);//水平居中
        Font headFont = wb.createFont();
        headFont.setFontHeightInPoints((short) 18); // 字体高度
        headFont.setFontName("黑体"); // 字体样式
      
        titlestyle1.setFont(headFont);
        titleCell.setCellStyle(titlestyle1);
    	
    	//表号
        HSSFRow companyRow=sheet.createRow(1);// 第一道行
    	sheet.addMergedRegion(new CellRangeAddress(1,1,0,6));
      	HSSFCell companyCell=companyRow.createCell(0);
      	companyCell.setCellValue("表号：沪海计23表");
      	//设置格式
      	 HSSFCellStyle companytyle1 = wb.createCellStyle();
      	companytyle1.setAlignment(companytyle1.ALIGN_RIGHT );//水平居右
        Font companyFont = wb.createFont();
        companyFont.setFontHeightInPoints((short) 12); // 字体高度
     //   companyFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//粗体显示
        companyFont.setFontName("宋体"); // 字体样式
      
        companytyle1.setFont(companyFont);
        companyCell.setCellStyle(companytyle1);
        
        
        
        
    	//单位
        HSSFRow zhiRow=sheet.createRow(2);// 第一道行
    	sheet.addMergedRegion(new CellRangeAddress(2,2,0,5));
      	HSSFCell zhiCell=zhiRow.createCell(0);
      	zhiCell.setCellValue("填报单位:上海海图中心");
      	//设置格式
      	 HSSFCellStyle zhityle1 = wb.createCellStyle();
      	companytyle1.setAlignment(HSSFCellStyle.ALIGN_LEFT );//水平居左
        Font zhiyFont = wb.createFont();
        zhiyFont.setFontHeightInPoints((short) 12); // 字体高度      
     //   zhiyFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//粗体显示
        zhiyFont.setFontName("宋体"); // 字体样式
      
        zhityle1.setFont(zhiyFont);
        zhiCell.setCellStyle(zhityle1);
        
        //指标名称
        HSSFRow leidianRow=sheet.createRow(3);// 第一道行
    	sheet.addMergedRegion(new CellRangeAddress(0,0,0,1));
      
     	HSSFCell leidian2Cell=leidianRow.createCell(0);
     	leidian2Cell.setCellValue("指标名称");
     	HSSFCell leidian3Cell=leidianRow.createCell(2);
     	leidian3Cell.setCellValue("计算单位");
    	HSSFCell leidian4Cell=leidianRow.createCell(3);
    	leidian4Cell.setCellValue("序号");
     	HSSFCell leidian5Cell=leidianRow.createCell(4);
     	leidian5Cell.setCellValue("本月");
     	HSSFCell leidian6Cell=leidianRow.createCell(5);
     	leidian6Cell.setCellValue("年内累计");
     	
      	//设置格式
      	 HSSFCellStyle leidiantyle1 = wb.createCellStyle();
        Font leidianFont = wb.createFont();
        leidianFont.setFontHeightInPoints((short) 12); // 字体高度      
      //  leidianFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//粗体显示
        leidianFont.setFontName("宋体"); // 字体样式
      
        leidiantyle1.setFont(leidianFont);
        leidian2Cell.setCellStyle(leidiantyle1);
        leidian3Cell.setCellStyle(leidiantyle1);
        leidian4Cell.setCellStyle(leidiantyle1);
        leidian5Cell.setCellStyle(leidiantyle1);
        leidian6Cell.setCellStyle(leidiantyle1);
        
        
        
        
        
        //甲
        HSSFRow jiaRow=sheet.createRow(4);// 第一道行
    	sheet.addMergedRegion(new CellRangeAddress(0,0,0,1));
      
     	HSSFCell jia1Cell=jiaRow.createCell(0);
     	jia1Cell.setCellValue("甲");
     	HSSFCell jia2Cell=jiaRow.createCell(2);
     	jia2Cell.setCellValue("乙");
    	HSSFCell jia3Cell=jiaRow.createCell(3);
    	jia3Cell.setCellValue("丙");
     	HSSFCell jia4Cell=jiaRow.createCell(4);
     	jia4Cell.setCellValue("1");
     	HSSFCell jia5Cell=jiaRow.createCell(5);
     	jia5Cell.setCellValue("2");
     	
      	//设置格式
      	 HSSFCellStyle jiatyle1 = wb.createCellStyle();
        Font jiaFont = wb.createFont();
        jiaFont.setFontHeightInPoints((short) 12); // 字体高度      
      //  leidianFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//粗体显示
        jiaFont.setFontName("宋体"); // 字体样式
      
        jiatyle1.setFont(jiaFont);
        jia1Cell.setCellStyle(jiatyle1);
        jia2Cell.setCellStyle(jiatyle1);
        jia3Cell.setCellStyle(jiatyle1);
        jia4Cell.setCellStyle(jiatyle1);
        jia5Cell.setCellStyle(jiatyle1);
        
        //海图
        
      	HSSFRow haiRow=sheet.createRow(5);// 第一道行
    	sheet.addMergedRegion(new CellRangeAddress(5,10,0,0));
    	HSSFCell haituCell=haiRow.createCell(0);
    	haituCell.setCellValue("海图编绘");
    
    	
    	  // 设置标题字体样式
    	 HSSFCellStyle haitustyle1 = wb.createCellStyle();
    	 haitustyle1.setAlignment(HSSFCellStyle.ALIGN_CENTER);//水平居中
    	 haitustyle1.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中 
        Font haituFont = wb.createFont();
        haituFont.setFontHeightInPoints((short) 18); // 字体高度
        haituFont.setFontName("黑体"); // 字体样式
      
        haitustyle1.setFont(haituFont);
        haituCell.setCellStyle(haitustyle1);
        
    
    	
    	HSSFCell haitu1Cell=haiRow.createCell(1);
    	haitu1Cell.setCellValue("纸海图");
    	HSSFCell haitu2Cell=haiRow.createCell(2);
    	haitu2Cell.setCellValue("幅");
    	HSSFCell haitu3Cell=haiRow.createCell(3);
    	haitu3Cell.setCellValue("1");
    	HSSFCell haitu4Cell=haiRow.createCell(5);
    	haitu4Cell.setCellValue(zhi);
        
    	haitustyle1.setFont(jiaFont);
    	  haitu1Cell.setCellStyle(haitustyle1);
    	  haitu2Cell.setCellStyle(haitustyle1);
    	  haitu3Cell.setCellStyle(haitustyle1);
    	  haitu4Cell.setCellStyle(haitustyle1);
    	  
    	  
    	 	HSSFRow youRow=sheet.createRow(6);// 第一道行
    	  HSSFCell youCell=youRow.createCell(1);
    	  youCell.setCellValue("其中：优良");
      	HSSFCell you1Cell=youRow.createCell(2);
      	you1Cell.setCellValue("幅");
      	HSSFCell you2Cell=youRow.createCell(3);
      	you2Cell.setCellValue("2");
      	HSSFCell you3Cell=youRow.createCell(5);
      	you3Cell.setCellValue(zhi);
          
      	haitustyle1.setFont(jiaFont);
      	youCell.setCellStyle(haitustyle1);
      	you1Cell.setCellStyle(haitustyle1);
      	you2Cell.setCellStyle(haitustyle1);
      	you3Cell.setCellStyle(haitustyle1);
      	
      	HSSFRow dianRow=sheet.createRow(7);// 第一道行
     	HSSFCell dian1Cell=dianRow.createCell(1);
     	dian1Cell.setCellValue("电子海图");
    	HSSFCell dian2Cell=dianRow.createCell(2);
    	dian2Cell.setCellValue("幅");
    	HSSFCell dian3Cell=dianRow.createCell(3);
    	dian3Cell.setCellValue("3");
    	HSSFCell dian4Cell=dianRow.createCell(5);
    	dian4Cell.setCellValue(dian);
        
    	haitustyle1.setFont(jiaFont);
    	dian1Cell.setCellStyle(haitustyle1);
    	dian2Cell.setCellStyle(haitustyle1);
    	dian3Cell.setCellStyle(haitustyle1);
    	dian4Cell.setCellStyle(haitustyle1);
    	  
    	
 	 	HSSFRow qiRow=sheet.createRow(8);// 第一道行
  	  HSSFCell qiCell=qiRow.createCell(1);
  	qiCell.setCellValue("其中：优良");
    	HSSFCell qi1Cell=qiRow.createCell(2);
    	qi1Cell.setCellValue("幅");
    	HSSFCell qi2Cell=qiRow.createCell(3);
    	qi2Cell.setCellValue("4");
    	HSSFCell qi3Cell=qiRow.createCell(5);
    	qi3Cell.setCellValue(dian);
        
    	haitustyle1.setFont(jiaFont);
    	qiCell.setCellStyle(haitustyle1);
    	qi1Cell.setCellStyle(haitustyle1);
    	qi2Cell.setCellStyle(haitustyle1);
    	qi3Cell.setCellStyle(haitustyle1);
        
    	
    	HSSFRow zhuanRow=sheet.createRow(9);// 第一道行
     	HSSFCell zhuan1Cell=zhuanRow.createCell(1);
     	zhuan1Cell.setCellValue("专题图");
    	HSSFCell zhuan2Cell=zhuanRow.createCell(2);
    	zhuan2Cell.setCellValue("幅");
    	HSSFCell zhuan3Cell=zhuanRow.createCell(3);
    	zhuan3Cell.setCellValue("5");
    
    	haitustyle1.setFont(jiaFont);
    	zhuan1Cell.setCellStyle(haitustyle1);
    	zhuan2Cell.setCellStyle(haitustyle1);
    	zhuan3Cell.setCellStyle(haitustyle1);
    	  
    	
 	 	HSSFRow zhongRow=sheet.createRow(10);// 第一道行
  	  HSSFCell zhong1Cell=zhongRow.createCell(1);
  	zhong1Cell.setCellValue("其中：优良");
    	HSSFCell zhong2Cell=zhongRow.createCell(2);
    	zhong2Cell.setCellValue("幅");
    	HSSFCell zhong3Cell=zhongRow.createCell(3);
    	zhong3Cell.setCellValue("6");
  
    	haitustyle1.setFont(jiaFont);
    	zhong1Cell.setCellStyle(haitustyle1);
    	zhong2Cell.setCellStyle(haitustyle1);
    	zhong3Cell.setCellStyle(haitustyle1);
    	
    	//海图发行量
     	HSSFRow faRow=sheet.createRow(11);// 第一道行
    	sheet.addMergedRegion(new CellRangeAddress(11,12,0,0));
    	HSSFCell faCell=faRow.createCell(0);
    	faCell.setCellValue("海图发行量");
        haitustyle1.setFont(haituFont);
        faCell.setCellStyle(haitustyle1);
        
        HSSFCell haitufa1Cell=faRow.createCell(1);
        haitufa1Cell.setCellValue("纸海图");
    	HSSFCell haitufa2Cell=faRow.createCell(2);
    	haitufa2Cell.setCellValue("幅");
    	HSSFCell haitufa3Cell=faRow.createCell(3);
    	haitufa3Cell.setCellValue("7");
    	
        
    	haitustyle1.setFont(jiaFont);
    	haitufa1Cell.setCellStyle(haitustyle1);
    	haitufa2Cell.setCellStyle(haitustyle1);
    	haitufa3Cell.setCellStyle(haitustyle1);
    	
    	
      	HSSFRow dianfaRow=sheet.createRow(12);// 第一道行
     	HSSFCell dianfa1Cell=dianfaRow.createCell(1);
     	dianfa1Cell.setCellValue("电子海图");
    	HSSFCell dianfa2Cell=dianfaRow.createCell(2);
    	dianfa2Cell.setCellValue("幅次");
    	HSSFCell dianfa3Cell=dianfaRow.createCell(3);
    	dianfa3Cell.setCellValue("8");
    
    	haitustyle1.setFont(jiaFont);
    	dianfa1Cell.setCellStyle(haitustyle1);
    	dianfa2Cell.setCellStyle(haitustyle1);
    	dianfa3Cell	.setCellStyle(haitustyle1);
    	
    	
    	//制印
        
    	HSSFRow yinRow=sheet.createRow(13);// 第一道行
    	sheet.addMergedRegion(new CellRangeAddress(13,18,0,0));
    	HSSFCell yinCell=yinRow.createCell(0);
    	yinCell.setCellValue("制印");
        haitustyle1.setFont(haituFont);
        faCell.setCellStyle(haitustyle1);
        
        HSSFCell yin3Cell=yinRow.createCell(1);
        yin3Cell.setCellValue("制印");
    	HSSFCell yin1Cell=yinRow.createCell(2);
    	yin1Cell.setCellValue("幅");
    	HSSFCell yin2Cell=yinRow.createCell(3);
    	yin2Cell.setCellValue("9");
    	
        
    	haitustyle1.setFont(jiaFont);
    	yin3Cell.setCellStyle(haitustyle1);
    	yin1Cell.setCellStyle(haitustyle1);
    	yin2Cell.setCellStyle(haitustyle1);
    	
    	HSSFRow yin1Row=sheet.createRow(14);// 第一道行
    	 HSSFCell yin4Cell=yin1Row.createCell(1);
    	 yin4Cell.setCellValue("海图印刷");
     	HSSFCell yin5Cell=yin1Row.createCell(2);
     	yin5Cell.setCellValue("印张");
     	HSSFCell yin6Cell=yin1Row.createCell(3);
     	yin6Cell.setCellValue("10");
     	
         
     	haitustyle1.setFont(jiaFont);
     	yin4Cell.setCellStyle(haitustyle1);
     	yin5Cell.setCellStyle(haitustyle1);
     	yin6Cell.setCellStyle(haitustyle1);
     	
     	HSSFRow yin2Row=sheet.createRow(15);// 第一道行
   	 HSSFCell yin7Cell=yin2Row.createCell(1);
   	yin7Cell.setCellValue("改正通告制版");
 	HSSFCell yin8Cell=yin2Row.createCell(2);
 	yin8Cell.setCellValue("期");
 	HSSFCell yin9Cell=yin2Row.createCell(3);
 	yin9Cell.setCellValue("11");
 	
     
 	haitustyle1.setFont(jiaFont);
 	yin7Cell.setCellStyle(haitustyle1);
 	yin8Cell.setCellStyle(haitustyle1);
 	yin9Cell.setCellStyle(haitustyle1);
    	
 	HSSFRow yin3Row=sheet.createRow(16);// 第一道行      
  	 HSSFCell yin10Cell=yin3Row.createCell(1);
  	yin10Cell.setCellValue("改正通告印刷");
	HSSFCell yin11Cell=yin3Row.createCell(2);
	yin11Cell.setCellValue("册");
	HSSFCell yin12Cell=yin3Row.createCell(3);
	yin12Cell.setCellValue("12");
	
    
	haitustyle1.setFont(jiaFont);
	yin10Cell.setCellStyle(haitustyle1);
	yin11Cell.setCellStyle(haitustyle1);
	yin12Cell.setCellStyle(haitustyle1);
   	
 	HSSFRow yin4Row=sheet.createRow(17);// 第一道行   
	 HSSFCell yin13Cell=yin4Row.createCell(1);
	 yin13Cell.setCellValue("其它制版");
		HSSFCell yin14Cell=yin4Row.createCell(2);
		yin14Cell.setCellValue("幅");
		HSSFCell yin15Cell=yin4Row.createCell(3);
		yin15Cell.setCellValue("13");
		
	    
		haitustyle1.setFont(jiaFont);
		yin13Cell.setCellStyle(haitustyle1);
		yin14Cell.setCellStyle(haitustyle1);
		yin15Cell.setCellStyle(haitustyle1);
	   	
		HSSFRow yin5Row=sheet.createRow(18);// 第一道行   
		 HSSFCell yin16Cell=yin5Row.createCell(1);
		 yin16Cell.setCellValue("其它印刷");
			HSSFCell yin17Cell=yin5Row.createCell(2);
			yin17Cell.setCellValue("印张");
			HSSFCell yin18Cell=yin5Row.createCell(3);
			yin18Cell.setCellValue("14");
			
		    
			haitustyle1.setFont(jiaFont);
			yin16Cell.setCellStyle(haitustyle1);
			yin17Cell.setCellStyle(haitustyle1);
			yin18Cell.setCellStyle(haitustyle1);
		   	
		
   try {
            
        	FileOutputStream xls = new FileOutputStream(path);
            wb.write(xls); 
            xls.close();
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    	
    	
    }
    
    
    
    public boolean exportExcelprocessing(String title, String[] headersName,String[] headersId,
            List<t> dtoList,String path) {
    	
    	//标题
    	HSSFWorkbook wb = new HSSFWorkbook();
    	   HSSFSheet sheet = wb.createSheet(title);
    	
    	
    	
    	HSSFRow firstHeaderRow=sheet.createRow(0);// 第一道行
    	sheet.addMergedRegion(new CellRangeAddress(0,0,0,9));
    	HSSFCell titleCell=firstHeaderRow.createCell(0);
    	titleCell.setCellValue("沿海港口航道图在编月度统计表");
    	  // 设置标题字体样式
    	 HSSFCellStyle titlestyle1 = wb.createCellStyle();
    	 titlestyle1.setAlignment(HSSFCellStyle.ALIGN_CENTER);//水平居中
        Font headFont = wb.createFont();
        headFont.setFontHeightInPoints((short) 24); // 字体高度
        headFont.setFontName("黑体"); // 字体样式
      
        titlestyle1.setFont(headFont);
        titleCell.setCellStyle(titlestyle1);
        
        
        
        //单位
    	HSSFRow companyRow=sheet.createRow(1);// 第一道行
    	sheet.addMergedRegion(new CellRangeAddress(0,0,0,9));
      	HSSFCell companyCell=companyRow.createCell(0);
      	companyCell.setCellValue("填报单位（盖章）：上海海图中心");
      	//设置格式
      	 HSSFCellStyle companytyle1 = wb.createCellStyle();
        Font companyFont = wb.createFont();
        companyFont.setFontHeightInPoints((short) 10); // 字体高度
        companyFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//粗体显示
        companyFont.setFontName("黑体"); // 字体样式
      
        companytyle1.setFont(companyFont);
        companyCell.setCellStyle(companytyle1);
        
 
        
        //表头
        Map<Integer, String> map = new HashMap<Integer, String>();
        int key=1;
        for (int i = 0; i < headersName.length; i++) {
            if (!headersName[i].equals(null)) {
                map.put(key, headersName[i]);
                key++;
            }
        }
        //字段
        Map<Integer, String> zdMap = new HashMap<Integer, String>();
        int value = 0;
        for (int i = 0; i < headersId.length; i++) {
            if (!headersId[i].equals(null)) {
                zdMap.put(value, headersId[i]);
                value++;
            }
        }
        // 声明一个工作薄
    //    HSSFWorkbook wb = new HSSFWorkbook();
 //       HSSFSheet sheet = wb.createSheet(title);
        sheet.setColumnWidth((short) 0, (short) 9000);
        sheet.setColumnWidth((short) 1, (short) 6000);
        sheet.setColumnWidth((short) 2, (short) 6000);
        sheet.setColumnWidth((short) 3, (short) 6000);
        sheet.setColumnWidth((short) 4, (short) 6000);
        sheet.setColumnWidth((short) 5, (short) 6000);
        sheet.setColumnWidth((short) 6, (short) 6000);
        sheet.setColumnWidth((short) 7, (short) 6000);
        sheet.setColumnWidth((short) 8, (short) 6000);
        sheet.setColumnWidth((short) 9, (short) 6000);
        sheet.setColumnWidth((short) 10, (short) 6000);
        sheet.setColumnWidth((short) 11, (short) 6000);
        sheet.setColumnWidth((short) 12, (short) 6000);
        sheet.setColumnWidth((short) 13, (short) 6000);
        sheet.setColumnWidth((short) 14, (short) 6000);
        sheet.setColumnWidth((short) 15, (short) 6000);
        sheet.setColumnWidth((short) 16, (short) 6000);
        sheet.setColumnWidth((short) 17, (short) 6000);
        sheet.setColumnWidth((short) 18, (short) 6000);
        sheet.setColumnWidth((short) 19, (short) 6000);
        sheet.setColumnWidth((short) 20, (short) 6000);
        sheet.setColumnWidth((short) 21, (short) 6000);
        sheet.setColumnWidth((short) 22, (short) 6000);
        sheet.setColumnWidth((short) 23, (short) 6000);
        sheet.setColumnWidth((short) 24, (short) 6000);
        sheet.setColumnWidth((short) 25, (short) 6000);
        sheet.setColumnWidth((short) 26, (short) 6000);
        sheet.setColumnWidth((short) 27, (short) 6000);
        sheet.setColumnWidth((short) 28, (short) 6000);
        sheet.setColumnWidth((short) 29, (short) 6000);
        sheet.setColumnWidth((short) 30, (short) 6000);
        sheet.setColumnWidth((short) 31, (short) 6000);
        sheet.setColumnWidth((short) 32, (short) 6000);
        sheet.setColumnWidth((short) 33, (short) 6000);
        sheet.setColumnWidth((short) 34, (short) 6000);
        sheet.setColumnWidth((short) 35, (short) 6000);
        sheet.setColumnWidth((short) 36, (short) 6000);
        sheet.setColumnWidth((short) 37, (short) 6000);
        sheet.setColumnWidth((short) 38, (short) 6000);
        sheet.setColumnWidth((short) 39, (short) 6000);
        sheet.setColumnWidth((short) 40, (short) 6000);
        sheet.setColumnWidth((short) 41, (short) 6000);
        sheet.setColumnWidth((short) 42, (short) 6000);
        sheet.setColumnWidth((short) 43, (short) 6000);
        sheet.setColumnWidth((short) 44, (short) 6000);
        sheet.setColumnWidth((short) 45, (short) 6000);
        sheet.setColumnWidth((short) 46, (short) 6000);
        // 生成一个样式  
        HSSFCellStyle titlestyle = wb.createCellStyle();
        
        // 设置单元格边框样式
        titlestyle.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框 细边线
        titlestyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);// 下边框 细边线
        titlestyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框 细边线
        titlestyle.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框 细边线
        //设置单元格背景样式
        titlestyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
        titlestyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        // 设置单元格对齐方式
        titlestyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 水平居中
        titlestyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 垂直居中
        // 设置字体样式
        Font titleFont = wb.createFont();
        titleFont.setFontHeightInPoints((short) 14); // 字体高度
        titleFont.setFontName("华文仿宋"); // 字体样式
        titlestyle.setFont(titleFont);
        HSSFRow row = sheet.createRow(2);
        HSSFCell cell;
        HSSFCell celldata;
        Collection c = map.values();
        Iterator<String> it = c.iterator();
        //产生表格标题行
        HSSFRow row1 = sheet.createRow(2);
        for (int i = 0; i < headersId.length-1; i++) {
           HSSFCell cell1 = row1.createCell(i+1);
           cell1.setCellStyle(titlestyle);
           HSSFRichTextString text = new HSSFRichTextString(headersId[i]);
           cell1.setCellValue(text);
        }
        //根据选择的字段生成表头
        short size = 0;
        while (it.hasNext()) {
                cell = row1.createCell(size);
            cell.setCellValue(it.next().toString());
            cell.setCellStyle(titlestyle);
            size++;
        }
        
        
    /*    HSSFRow zhiRow=sheet.createRow(3);// 第一道行
    	sheet.addMergedRegion(new CellRangeAddress(0,0,0,9));
      	HSSFCell zhiCell=zhiRow.createCell(0);
      	zhiCell.setCellValue("一、纸海图");
      	//设置格式
      	 HSSFCellStyle zhityle1 = wb.createCellStyle();
        Font zhiyFont = wb.createFont();
        zhiyFont.setFontHeightInPoints((short) 10); // 字体高度      
        zhiyFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//粗体显示
        zhiyFont.setFontName("黑体"); // 字体样式
      
        zhityle1.setFont(zhiyFont);
        zhiCell.setCellStyle(zhityle1);
        */
        
        
        
        // 字段        
        Collection zdC = zdMap.values();
        Iterator<t> labIt = (Iterator<t>) dtoList.iterator();
        
   
        // 数据样式 因为标题和数据样式不同 需要分开设置 不然会覆盖
        HSSFCellStyle dataStyle = wb.createCellStyle();
        // 设置边框样式
        dataStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框 细边线
        dataStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);// 下边框 细边线
        dataStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框 细边线
        dataStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框 细边线
        // 设置居中样式
        dataStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 水平居中
        dataStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 垂直居中
        int zdRow =3;
        while (labIt.hasNext()) {
            int zdCell = 0;
            zdRow++;
            row1 = sheet.createRow(zdRow);
            t l = (t) labIt.next();
            // 利用反射，根据javabean属性的先后顺序，动态调用getXxx()方法得到属性值
            Field[] fields1 = l.getClass().getDeclaredFields();
            Field[] declaredFields = l.getClass().getSuperclass().getDeclaredFields();
            Field[] fields = ArrayUtils.addAll(fields1, declaredFields);
            for (short i = 0; i < fields.length; i++) {
                Field field = fields[i];
                String fieldName = field.getName();
//              System.out.println(fieldName);
                Iterator<String> zdIt = zdC.iterator();
                while (zdIt.hasNext()) {
                    if (zdIt.next().equals(fieldName)) {
                        String getMethodName = "get"
                            + fieldName.substring(0, 1).toUpperCase()
                            + fieldName.substring(1);
                        Class tCls = l.getClass();
                        try {
                            Method getMethod = tCls.getMethod(getMethodName,
                                    new Class[] {});
                            Object val = getMethod.invoke(l, new Object[] {});
//                          System.out.println(fields[i].getName());
//                          System.out.println(val);
                            String textVal = null;
                            if (val!= null) {
                                textVal = val.toString();
                            }else{
                                textVal = null;
                            }
                            celldata = row1.createCell((short) zdCell);
                            celldata.setCellValue(textVal);
                            celldata.setCellStyle(dataStyle);
                            zdCell++;
                        } catch (SecurityException e) {
                            e.printStackTrace();
                        } catch (IllegalArgumentException e) {
                            e.printStackTrace();
                        } catch (NoSuchMethodException e) {
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }finally {
                            //清理资源
                        }
                    }
                }
                 
            }
        }
        

       
        
        
        try {
            
        	FileOutputStream xls = new FileOutputStream(path);
            wb.write(xls); 
            xls.close();
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    
    
    
    
    
    
    public boolean exportSubmissionExcel(String title, String[] headersName,String[] headersId,
            List<t> dtoList,String path) {
    	
    	//标题
    	HSSFWorkbook wb = new HSSFWorkbook();
    	   HSSFSheet sheet = wb.createSheet(title);
    	
    	
    	
    	HSSFRow firstHeaderRow=sheet.createRow(0);// 第一道行
    	sheet.addMergedRegion(new CellRangeAddress(0,0,0,9));
    	HSSFCell titleCell=firstHeaderRow.createCell(0);
    	titleCell.setCellValue("沿海港口航道图测量成果数据汇交情况统计表");
    	  // 设置标题字体样式
    	 HSSFCellStyle titlestyle1 = wb.createCellStyle();
    	 titlestyle1.setAlignment(HSSFCellStyle.ALIGN_CENTER);//水平居中
        Font headFont = wb.createFont();
        headFont.setFontHeightInPoints((short) 24); // 字体高度
        headFont.setFontName("黑体"); // 字体样式
      
        titlestyle1.setFont(headFont);
        titleCell.setCellStyle(titlestyle1);
        
        
        
        //单位
    	HSSFRow companyRow=sheet.createRow(1);// 第一道行
    	sheet.addMergedRegion(new CellRangeAddress(0,0,0,9));
      	HSSFCell companyCell=companyRow.createCell(0);
      	companyCell.setCellValue("填报单位（盖章）：上海海图中心");
      	//设置格式
      	 HSSFCellStyle companytyle1 = wb.createCellStyle();
        Font companyFont = wb.createFont();
        companyFont.setFontHeightInPoints((short) 10); // 字体高度
        companyFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//粗体显示
        companyFont.setFontName("黑体"); // 字体样式
      
        companytyle1.setFont(companyFont);
        companyCell.setCellStyle(companytyle1);
        
 
        
        //表头
        Map<Integer, String> map = new HashMap<Integer, String>();
        int key=1;
        for (int i = 0; i < headersName.length; i++) {
            if (!headersName[i].equals(null)) {
                map.put(key, headersName[i]);
                key++;
            }
        }
        //字段
        Map<Integer, String> zdMap = new HashMap<Integer, String>();
        int value = 0;
        for (int i = 0; i < headersId.length; i++) {
            if (!headersId[i].equals(null)) {
                zdMap.put(value, headersId[i]);
                value++;
            }
        }
        // 声明一个工作薄
    //    HSSFWorkbook wb = new HSSFWorkbook();
 //       HSSFSheet sheet = wb.createSheet(title);
        sheet.setColumnWidth((short) 0, (short) 9000);
        sheet.setColumnWidth((short) 1, (short) 6000);
        sheet.setColumnWidth((short) 2, (short) 6000);
        sheet.setColumnWidth((short) 3, (short) 6000);
        sheet.setColumnWidth((short) 4, (short) 6000);
        sheet.setColumnWidth((short) 5, (short) 6000);
        sheet.setColumnWidth((short) 6, (short) 6000);
        sheet.setColumnWidth((short) 7, (short) 6000);
        sheet.setColumnWidth((short) 8, (short) 6000);
        sheet.setColumnWidth((short) 9, (short) 6000);
        sheet.setColumnWidth((short) 10, (short) 6000);
        sheet.setColumnWidth((short) 11, (short) 6000);
        sheet.setColumnWidth((short) 12, (short) 6000);
        sheet.setColumnWidth((short) 13, (short) 6000);
        sheet.setColumnWidth((short) 14, (short) 6000);
        sheet.setColumnWidth((short) 15, (short) 6000);
        sheet.setColumnWidth((short) 16, (short) 6000);
        sheet.setColumnWidth((short) 17, (short) 6000);
        sheet.setColumnWidth((short) 18, (short) 6000);
        sheet.setColumnWidth((short) 19, (short) 6000);
        sheet.setColumnWidth((short) 20, (short) 6000);
        sheet.setColumnWidth((short) 21, (short) 6000);
        sheet.setColumnWidth((short) 22, (short) 6000);
        sheet.setColumnWidth((short) 23, (short) 6000);
        sheet.setColumnWidth((short) 24, (short) 6000);
        sheet.setColumnWidth((short) 25, (short) 6000);
        sheet.setColumnWidth((short) 26, (short) 6000);
        sheet.setColumnWidth((short) 27, (short) 6000);
        sheet.setColumnWidth((short) 28, (short) 6000);
        sheet.setColumnWidth((short) 29, (short) 6000);
        sheet.setColumnWidth((short) 30, (short) 6000);
        sheet.setColumnWidth((short) 31, (short) 6000);
        sheet.setColumnWidth((short) 32, (short) 6000);
        sheet.setColumnWidth((short) 33, (short) 6000);
        sheet.setColumnWidth((short) 34, (short) 6000);
        sheet.setColumnWidth((short) 35, (short) 6000);
        sheet.setColumnWidth((short) 36, (short) 6000);
        sheet.setColumnWidth((short) 37, (short) 6000);
        sheet.setColumnWidth((short) 38, (short) 6000);
        sheet.setColumnWidth((short) 39, (short) 6000);
        sheet.setColumnWidth((short) 40, (short) 6000);
        sheet.setColumnWidth((short) 41, (short) 6000);
        sheet.setColumnWidth((short) 42, (short) 6000);
        sheet.setColumnWidth((short) 43, (short) 6000);
        sheet.setColumnWidth((short) 44, (short) 6000);
        sheet.setColumnWidth((short) 45, (short) 6000);
        sheet.setColumnWidth((short) 46, (short) 6000);
        // 生成一个样式  
        HSSFCellStyle titlestyle = wb.createCellStyle();
        
        // 设置单元格边框样式
        titlestyle.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框 细边线
        titlestyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);// 下边框 细边线
        titlestyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框 细边线
        titlestyle.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框 细边线
        //设置单元格背景样式
        titlestyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
        titlestyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        // 设置单元格对齐方式
        titlestyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 水平居中
        titlestyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 垂直居中
        // 设置字体样式
        Font titleFont = wb.createFont();
        titleFont.setFontHeightInPoints((short) 14); // 字体高度
        titleFont.setFontName("华文仿宋"); // 字体样式
        titlestyle.setFont(titleFont);
        HSSFRow row = sheet.createRow(2);
        HSSFCell cell;
        HSSFCell celldata;
        Collection c = map.values();
        Iterator<String> it = c.iterator();
        //产生表格标题行
        HSSFRow row1 = sheet.createRow(2);
        for (int i = 0; i < headersId.length-1; i++) {
           HSSFCell cell1 = row1.createCell(i+1);
           cell1.setCellStyle(titlestyle);
           HSSFRichTextString text = new HSSFRichTextString(headersId[i]);
           cell1.setCellValue(text);
        }
        //根据选择的字段生成表头
        short size = 0;
        while (it.hasNext()) {
                cell = row1.createCell(size);
            cell.setCellValue(it.next().toString());
            cell.setCellStyle(titlestyle);
            size++;
        }
        
        
    /*    HSSFRow zhiRow=sheet.createRow(3);// 第一道行
    	sheet.addMergedRegion(new CellRangeAddress(0,0,0,9));
      	HSSFCell zhiCell=zhiRow.createCell(0);
      	zhiCell.setCellValue("一、纸海图");
      	//设置格式
      	 HSSFCellStyle zhityle1 = wb.createCellStyle();
        Font zhiyFont = wb.createFont();
        zhiyFont.setFontHeightInPoints((short) 10); // 字体高度      
        zhiyFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//粗体显示
        zhiyFont.setFontName("黑体"); // 字体样式
      
        zhityle1.setFont(zhiyFont);
        zhiCell.setCellStyle(zhityle1);
        */
        
        
        
        // 字段        
        Collection zdC = zdMap.values();
        Iterator<t> labIt = (Iterator<t>) dtoList.iterator();
        
   
        // 数据样式 因为标题和数据样式不同 需要分开设置 不然会覆盖
        HSSFCellStyle dataStyle = wb.createCellStyle();
        // 设置边框样式
        dataStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框 细边线
        dataStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);// 下边框 细边线
        dataStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框 细边线
        dataStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框 细边线
        // 设置居中样式
        dataStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 水平居中
        dataStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 垂直居中
        int zdRow =2;
        while (labIt.hasNext()) {
            int zdCell = 0;
            zdRow++;
            row1 = sheet.createRow(zdRow);
            t l = (t) labIt.next();
            // 利用反射，根据javabean属性的先后顺序，动态调用getXxx()方法得到属性值
            Field[] fields1 = l.getClass().getDeclaredFields();
            Field[] declaredFields = l.getClass().getSuperclass().getDeclaredFields();
            Field[] fields = ArrayUtils.addAll(fields1, declaredFields);
            for (short i = 0; i < fields.length; i++) {
                Field field = fields[i];
                String fieldName = field.getName();
//              System.out.println(fieldName);
                Iterator<String> zdIt = zdC.iterator();
                while (zdIt.hasNext()) {
                    if (zdIt.next().equals(fieldName)) {
                        String getMethodName = "get"
                            + fieldName.substring(0, 1).toUpperCase()
                            + fieldName.substring(1);
                        Class tCls = l.getClass();
                        try {
                            Method getMethod = tCls.getMethod(getMethodName,
                                    new Class[] {});
                            Object val = getMethod.invoke(l, new Object[] {});
//                          System.out.println(fields[i].getName());
//                          System.out.println(val);
                            String textVal = null;
                            if (val!= null) {
                                textVal = val.toString();
                            }else{
                                textVal = null;
                            }
                            celldata = row1.createCell((short) zdCell);
                            celldata.setCellValue(textVal);
                            celldata.setCellStyle(dataStyle);
                            zdCell++;
                        } catch (SecurityException e) {
                            e.printStackTrace();
                        } catch (IllegalArgumentException e) {
                            e.printStackTrace();
                        } catch (NoSuchMethodException e) {
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }finally {
                            //清理资源
                        }
                    }
                }
                 
            }
        }
        
        
           int rowr=   zdRow++;
        
        HSSFRow yueRow=sheet.createRow(rowr+1);// 第一道行
    	sheet.addMergedRegion(new CellRangeAddress(0,0,0,2));
      
     	HSSFCell yue1Cell=yueRow.createCell(1);
     	yue1Cell.setCellValue("东海海区汇交（或退还）本月合计：");
     	HSSFCell yue2Cell=yueRow.createCell(3);
     	yue2Cell.setCellValue("南海海区汇交（或退还）本月合计：");
    	HSSFCell yue3Cell=yueRow.createCell(5);
    	yue3Cell.setCellValue("北海海区汇交（或退还）本月合计：");
    
     	
      	//设置格式
      	 HSSFCellStyle luotyle1 = wb.createCellStyle();
        Font luoFont = wb.createFont();
        luoFont.setFontHeightInPoints((short) 10); // 字体高度      
      //  luoFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//粗体显示
        luoFont.setFontName("宋体"); // 字体样式
      
        luotyle1.setFont(luoFont);
        yue1Cell.setCellStyle(luotyle1);
        yue2Cell.setCellStyle(luotyle1);
        yue3Cell.setCellStyle(luotyle1);
   
        
          int roww=   zdRow++;
        
        
        HSSFRow nianRow=sheet.createRow(roww+1);// 第一道行
    	sheet.addMergedRegion(new CellRangeAddress(0,0,0,2));
      
     	HSSFCell nian1Cell=nianRow.createCell(1);
     	nian1Cell.setCellValue("东海海区汇交（或退还）年度累计：");
     	HSSFCell nian2Cell=nianRow.createCell(3);
     	nian2Cell.setCellValue("南海海区汇交（或退还）年度累计：");
    	HSSFCell nian3Cell=nianRow.createCell(5);
    	nian3Cell.setCellValue("北海海区汇交（或退还）年度累计：");
    
     	
   
        luotyle1.setFont(luoFont);
        nian1Cell.setCellStyle(luotyle1);
        nian2Cell.setCellStyle(luotyle1);
        nian3Cell.setCellStyle(luotyle1);
        
        
        int row0=   zdRow++;      
     HSSFRow nianduRow=sheet.createRow(row0+1);// 第一道行
 	sheet.addMergedRegion(new CellRangeAddress(0,0,4,6));
  	HSSFCell niandu1Cell=nianduRow.createCell(4);
  	niandu1Cell.setCellValue("年度汇交总计：    幅");
  	
    int row2=   zdRow++;
    HSSFRow niandu1Row=sheet.createRow(row2+1);// 第一道行
 	sheet.addMergedRegion(new CellRangeAddress(0,0,0,5));
  	HSSFCell niandu2Cell=niandu1Row.createCell(4);
  	niandu2Cell.setCellValue("年度汇交（或退还）累计：");
  	
    int row3=   zdRow++;
    HSSFRow niandu2Row=sheet.createRow(row3+1);// 第一道行
 	sheet.addMergedRegion(new CellRangeAddress(0,0,4,6));
  	HSSFCell niandu3Cell=niandu2Row.createCell(4);
  	niandu3Cell.setCellValue("年度累计完成比例：");
    
    
  	
   	//设置格式
   	 HSSFCellStyle zutyle1 = wb.createCellStyle();
     Font zuFont = wb.createFont();
     zuFont.setFontHeightInPoints((short) 10); // 字体高度      
     zuFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//粗体显示
     zuFont.setFontName("黑体"); // 字体样式
   
     zutyle1.setFont(zuFont);
     niandu1Cell.setCellStyle(zutyle1);
     niandu2Cell.setCellStyle(zutyle1);
     niandu3Cell.setCellStyle(zutyle1);
        
        
   
       
    int rowrr=   zdRow++;
        
        //
        HSSFRow luoRow=sheet.createRow(rowrr+1);// 第一道行
    	sheet.addMergedRegion(new CellRangeAddress(0,0,0,2));
      
     	HSSFCell luo1Cell=luoRow.createCell(0);
     	luo1Cell.setCellValue("单位负责人：");
     	HSSFCell luo2Cell=luoRow.createCell(2);
     	luo2Cell.setCellValue("统计负责人：");
    	HSSFCell luo3Cell=luoRow.createCell(4);
    	luo3Cell.setCellValue("填表人：");
       	HSSFCell luo4Cell=luoRow.createCell(6);
    	luo4Cell.setCellValue("联系电话：");
    	HSSFCell luo5Cell=luoRow.createCell(8);
    	luo5Cell.setCellValue("报出日期：");
     	
    
        luotyle1.setFont(luoFont);
        luo1Cell.setCellStyle(luotyle1);
        luo2Cell.setCellStyle(luotyle1);
        luo3Cell.setCellStyle(luotyle1);
        luo4Cell.setCellStyle(luotyle1);
        luo5Cell.setCellStyle(luotyle1);
        
        try {
            
        	FileOutputStream xls = new FileOutputStream(path);
            wb.write(xls); 
            xls.close();
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    
    
}