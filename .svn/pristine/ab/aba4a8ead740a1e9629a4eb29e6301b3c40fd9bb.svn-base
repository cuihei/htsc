package com.ht.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;

/**
 * @ClassName: BuildExcelTemplate
 * @Description: 导出Excel模板
 * @author penghao
 * @date 2016年10月17日 下午12:51:31
 * 
 */
public class CellValueUtil {
	
	/**
	 * 获取单元格的值
	 * @param cell
	 * @return
	 */
	public static String getCellValue(Cell cell) throws Exception {
		String cellValue = null;
		if (null != cell) {   
           if(cell.getCellType() != XSSFCell.CELL_TYPE_BLANK){
        	   cellValue = cell.toString();
           }
		}
		return cellValue;   
	}
	
	public static String removePoint(Cell cell){
		String value = null;
		if(cell != null){
			if(cell.getCellType() != XSSFCell.CELL_TYPE_BLANK){
				value = cell.toString();
				if(value.indexOf(".") > 0){
					//正则表达
					value = value.replaceAll("0+?$", "");//去掉后面无用的零
					value = value.replaceAll("[.]$", "");//如小数点后面全是零则去掉小数点
				}
			}
			if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){  
			    short format = cell.getCellStyle().getDataFormat();  
			    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
			    if(format == 14 || format == 31 || format == 57 || format == 58){  
			        //日期  
			        sdf = new SimpleDateFormat("yyyy-MM-dd");  
			        
			        double result = cell.getNumericCellValue();  
				    Date date = org.apache.poi.ss.usermodel.DateUtil.getJavaDate(result);  
				    value = sdf.format(date);  
			    }
			}  
		}
		return value;
		
	}
	public static String getdate(Cell cell,String formats){
		String  result="";
		if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){  
		    short format = cell.getCellStyle().getDataFormat();  
		    SimpleDateFormat sdf = new SimpleDateFormat(formats);  
		    if(format == 14 || format == 31 || format == 57 || format == 58){  
		        //日期  
		        sdf = new SimpleDateFormat(formats);  
		    }else if (format == 20 || format == 32) {  
		        //时间  
		        sdf = new SimpleDateFormat("HH:mm");  
		    }  
		    double value = cell.getNumericCellValue();  
		    Date date = org.apache.poi.ss.usermodel.DateUtil.getJavaDate(value);  
		     result = sdf.format(date);  
		}  
		return result;
		
	}
		
}
