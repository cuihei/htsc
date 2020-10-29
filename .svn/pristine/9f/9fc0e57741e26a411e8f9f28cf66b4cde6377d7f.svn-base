package com.ht.common.export;


import java.io.OutputStream;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.ServletActionContext;
 
/** 
* excel导出工具类 
*/  
public class ExcelUtil<T> implements Serializable {  
 
   private static final long serialVersionUID = 551970754610248636L;  
 
   private Class<T> clazz;  
 
   public ExcelUtil(Class<T> clazz) {  
       this.clazz = clazz;  
   }  
 
   /** 
    * 将list数据源的数据导入到excel表单 
    *  
    * @param list 
    *            数据源 
    * @param sheetName 
    *            工作表的名称 
    * @param output 
    *            java输出流 
    */  
   public boolean getListToExcel(List<T> list, String sheetName,String fileName) throws Exception {  
	   Date date = new Date();
		SimpleDateFormat sd = new SimpleDateFormat("yyyyMMddHHmmss");
		String str = sd.format(date);
		fileName = fileName+"_"+str+".xlsx";
		HttpServletResponse response = ServletActionContext.getResponse();
		
		response.setContentType("application/msexcel");
		response.addHeader("Content-Disposition", "attachment; filename=\"" + new String(fileName.getBytes("gb2312"), "ISO8859-1") + "\";");
	   try {  
           // excel中每个sheet中最多有65536行  
           int sheetSize = 65536;  
           // 得到所有定义字段  
           Field[] allFields = clazz.getDeclaredFields();  
           List<Field> fields = new ArrayList<Field>(); 
           String packageUrl = null;
           // 得到所有field并存放到一个list中  
           for (Field field : allFields) {  
               if (field.isAnnotationPresent(ExcelAttribute.class)) {  
            	   packageUrl = field.getType().getName();
            	   //如果是自定义包，循环下一层实体
            	   if(packageUrl.startsWith("com.ht")) {
	            	   Field[] allDetailFields = field.getType().getDeclaredFields();
	            	   for (Field detailField : allDetailFields) {  
	                       if (detailField.isAnnotationPresent(ExcelAttribute.class)) {  
	                    	   fields.add(detailField); 
	                       }
	                    }
            	   }else{
            		   fields.add(field); 
            	   }
               }  
           }
           // 产生工作薄对象  
           XSSFWorkbook workbook = new XSSFWorkbook();  
           // 取出一共有多少个sheet  
           int listSize = 0;  
           if (list != null && list.size() >= 0) {  
               listSize = list.size();  
           }  
           double sheetNo = Math.ceil(listSize / sheetSize);  
           for (int index = 0; index <= sheetNo; index++) {  
               // 产生工作表对象  
               XSSFSheet sheet = workbook.createSheet();  
               // 设置工作表的名称.  
               workbook.setSheetName(index, sheetName + index);  
               XSSFRow row;  
               XSSFCell cell;// 产生单元格  
               row = sheet.createRow(0);// 产生一行  
               /* *********普通列样式********* */  
               XSSFFont font = workbook.createFont();  
               XSSFCellStyle cellStyle = workbook.createCellStyle();  
               font.setFontName("Arail narrow"); // 字体  
               font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD); // 字体宽度  
               /* *********标红列样式********* */  
               XSSFFont newFont = workbook.createFont();  
               XSSFCellStyle newCellStyle = workbook.createCellStyle(); 
               newFont.setFontName("Arail narrow"); // 字体  
               newFont.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD); // 字体宽度  
               /* *************创建列头名称*************** */  
               for (int i = 0; i < fields.size(); i++) {  
                   Field field = fields.get(i);  
                   ExcelAttribute attr = field.getAnnotation(ExcelAttribute.class);  
                   String name=attr.name();
                   int col = i;  
                   // 根据指定的顺序获得列号  
                   if (!attr.column().isEmpty()) {  
                       col = getExcelCol(attr.column());  
                   }  
                   // 创建列  
                   cell = row.createCell(col);  
                   //设置列为文本格式
                   CellStyle css = workbook.createCellStyle();
   				   DataFormat  format = workbook.createDataFormat();
   				   css.setDataFormat(format.getFormat("@"));
   				   sheet.setDefaultColumnStyle(col,css);
                   if (attr.isMark()) {  
                	 //  newCellStyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);//背景色
                	  // newCellStyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());//背景色
                	   newFont.setColor(XSSFFont.COLOR_RED); // 字体颜色  
                	  // newFont.setColor(XSSFFont.COLOR_NORMAL); // 字体颜色  
                	   name="*"+name;
                       newCellStyle.setFont(newFont);  
                       cell.setCellStyle(newCellStyle);   
                       
                   } else {  
                       font.setColor(XSSFFont.COLOR_NORMAL); // 字体颜色  
                       cellStyle.setFont(font);  
                       cell.setCellStyle(cellStyle);  
                   }  
                   sheet.setColumnWidth(i, (int) ((attr.name().getBytes().length <= 4 ? 6 : attr.name().getBytes().length) * 1.5 * 256));  
                   // 设置列中写入内容为String类型  
                   cell.setCellType(XSSFCell.CELL_TYPE_STRING);  
                   // 写入列名 
                   if(!attr.name().equals("状态")&&!attr.name().equals("发布次数")&&!attr.name().equals("年")
                		   &&!attr.name().equals("月")&&!attr.name().equals("资料状态")&&!attr.name().equals("资料总数")&&!attr.name().equals("可借数量")){
                	   cell.setCellValue(name);
                   }
               }  
               /* *************创建内容列*************** */  
               font = workbook.createFont();  
               cellStyle = workbook.createCellStyle();  
               int startNo = index * sheetSize;  
               int endNo = Math.min(startNo + sheetSize, listSize);  
               // 写入各条记录,每条记录对应excel表中的一行  
               for (int i = startNo; i < endNo; i++) {  
                   row = sheet.createRow(i + 1 - startNo);  
                   T vo = (T) list.get(i); // 得到导出对象.
                   
                   for (int j = 0; j < fields.size(); j++) {  
                       // 获得field  
                       Field field = fields.get(j);
                       // 设置实体类私有属性可访问  
                       field.setAccessible(true);  
                       ExcelAttribute attr = field.getAnnotation(ExcelAttribute.class);  
                       int col = j;  
                       // 根据指定的顺序获得列号  
                       if (!attr.column().isEmpty()) {  
                           col = getExcelCol(attr.column());  
                       }  
                       // 根据ExcelVOAttribute中设置情况决定是否导出,有些情况需要保持为空,希望用户填写这一列.  
                       if (attr.isExport()) {  
                           // 创建cell  
                           cell = row.createCell(col);  
                           if (attr.isMark()) {  
                               newFont.setColor(XSSFFont.COLOR_RED); // 字体颜色  
                               newCellStyle.setFont(newFont);  
                               cell.setCellStyle(newCellStyle);  
                           } else {  
                               font.setColor(XSSFFont.COLOR_NORMAL); // 字体颜色  
                               cellStyle.setFont(font);  
                               cell.setCellStyle(cellStyle);  
                           }  
                           // 如果数据存在就填入,不存在填入空格  
                           Class<?> classType = (Class<?>) field.getType();  
                           String value = null; 
                           if(packageUrl.equals(field.getDeclaringClass().getName())){
                        	   //找出实体中的实体类参数
                        	   int packageNameBegin = packageUrl.lastIndexOf(".");
                        	   String packageName = packageUrl.substring(packageNameBegin+1, packageUrl.length());
                        	   String temp = packageName.substring(0, 1);
                        	   //获取实体包名
                        	   packageName = packageName.replaceFirst(temp,temp.toLowerCase());
                        	   Field childBeanField = clazz.getDeclaredField(packageName);
                        	   childBeanField.setAccessible(true);
                        	   //得到子实体内容	
                        	   Object object = childBeanField.get(vo);
                        	   if (field.get(object) != null && classType.isAssignableFrom(Date.class)) {  
	                               SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
	                               value = sdf.format(field.get(object));
	                           }  
	                          cell.setCellValue(field.get(object) == null ? "" : value == null ? String.valueOf(field.get(object)) : value);
                           }else{
	                           if (field.get(vo) != null && classType.isAssignableFrom(Date.class)) {  
	                               SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
	                               value = sdf.format(field.get(vo));
	                           }  
	                           cell.setCellValue(field.get(vo) == null ? "" : value == null ? String.valueOf(field.get(vo)) : value); 
                           }
                       }  
                   }  
               }  
           }
           OutputStream out = response.getOutputStream();   
           workbook.write(out);  
           out.flush();
           out.close();
           return Boolean.TRUE;  
       } catch (Exception e) {  
           throw new Exception("将list数据源的数据导入到excel表单异常!", e);  
       }  
 
   }  
 
   /** 
    * 将EXCEL中A,B,C,D,E列映射成0,1,2,3 
    *  
    * @param col 
    */  
   public static int getExcelCol(String col) {  
       col = col.toUpperCase();  
       // 从-1开始计算,字母重1开始运算。这种总数下来算数正好相同。  
       int count = -1;  
       char[] cs = col.toCharArray();  
       for (int i = 0; i < cs.length; i++) {  
           count += (cs[i] - 64) * Math.pow(26, cs.length - 1 - i);  
       }  
       return count;  
   }  
 
}  