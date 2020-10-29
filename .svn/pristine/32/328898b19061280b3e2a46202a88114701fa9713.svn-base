package com.ht.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.ht.common.exception.CommonException;

/**
 * 系统转换工具类
 * @author wyw
 * @version 1.0 2015/04/22
 */
public class ConvertUtil {
	
	/**
	 * 将日期对象转换为字符表达式
	 * @param pattern 日期格式
	 * @param dateValue 日期对象
	 * @return 字符表达式
	 */
	public static final String convertDateToString(String pattern, Date dateValue) {
		
		// 声明字符表达式
		String expression = null;
		
		// 如果日期对象不为空
		if (dateValue != null) {
			
			// 将日期对象转换为字符表达式
			expression = new SimpleDateFormat(pattern).format(dateValue);
		}
		
		// 返回字符表达式
		return expression;
	}
	
	/**
	 * 将字符表达式转换为日期对象
	 * @param pattern 日期格式
	 * @param expression 字符表达式
	 * @return 日期对象
	 */
	public static Date convertStringToDate(String pattern, String expression) {
		
		// 声明日期对象
		Date dateValue = null;
		
		// 如果字符表达式不为空
		if (StringUtils.isNotBlank(expression)) {
			
			try {
				
				// 将字符表达式转换为日期对象
				dateValue = new SimpleDateFormat(pattern).parse(expression);
			} 
			// 发生分析异常
			catch (ParseException e) {
				
			}
		}
		
		// 返回日期对象
		return dateValue;
	}
	
	/**
	 * 将纬度转换为数学格式
	 * @param latitude 纬度
	 * @return String
	 * @throws CommonException 
	 */
	public static String convertLatitudeToPlusMinus(String latitude) throws CommonException {
		latitude=latitude.trim();
		String s = latitude.substring(latitude.length()-1,latitude.length());
		String regx = "^\\d+$";
		Pattern pattern = Pattern.compile(regx);
		Matcher m = pattern.matcher(s);
		String resutl = latitude.substring(0,latitude.length()-1);
		if(m.matches()){
			if(latitude.contains("-")){
				String[] latArray = latitude.split("-");
				if(latArray.length == 1){
					latitude = (Long.valueOf(latArray[0])+90)+"°";
				}else if(latArray.length == 2){
					latitude = (Long.valueOf(latArray[0])+90)+"°" + latArray[1] +"'";
				}else{
					latitude = (Long.valueOf(latArray[0])+90)+"°" + latArray[1] +"'" + latArray[2] +"\"";
				}
			}
			else{
				String[] latArray1 = latitude.split("°");
				latitude = (Long.valueOf(latArray1[0])+90)+"°"+latArray1[1];
			}
			return latitude;
		}else if(s.equals("N")){
			if(resutl.contains("-")){
				String[] latArray = resutl.split("-");
				if(latArray.length == 1){
					resutl = (Long.valueOf(latArray[0])+90)+"°";
				}else if(latArray.length == 2){
					resutl = (Long.valueOf(latArray[0])+90)+"°" + latArray[1] +"'";
				}else{
					resutl = (Long.valueOf(latArray[0])+90)+"°" + latArray[1] +"'" + latArray[2] +"\"";
				}
			}
			else{
				String[] latArray1 = resutl.split("°");
				resutl = (Long.valueOf(latArray1[0])+90)+"°"+latArray1[1];
			}
			return resutl;
		}else if(s.equals("S")){
			if(resutl.contains("-")){
				String[] latArray = resutl.split("-");
				if(latArray.length == 1){
					resutl = (-(Long.valueOf(latArray[0]))+90)+"°";
				}else if(latArray.length == 2){
					resutl = (-(Long.valueOf(latArray[0]))+90)+"°" + latArray[1] +"'";
				}else{
					resutl = (-(Long.valueOf(latArray[0]))+90)+"°" + latArray[1] +"'" + latArray[2] +"\"";
				}
			}
			else{
				String[] latArray1 = resutl.split("°");
				resutl = (-(Long.valueOf(latArray1[0]))+90)+"°"+latArray1[1];
			}
			return resutl;
		}else if(s.equals("′")||s.equals("°")||s.equals("″")||s.equals("'")||s.equals("\"")){
			String[] degree = resutl.split("°");
			if(degree[1]!=null&&degree[1]!=""){
				resutl = (Long.valueOf(degree[0])+90)+"°"+degree[1];
			}else{
				resutl = (Long.valueOf(degree[0])+90)+"°";
			}
			return resutl;
		}else{
			return "error";
		}
	}
	
	/**
	 * 将经度转换为数学格式
	 * @param latitude 经度
	 * @return String
	 * @throws CommonException 
	 */
	public static String convertLongitudeToPlusMinus(String longitude) throws CommonException {
		longitude=longitude.trim();
		String s = longitude.substring(longitude.length()-1,longitude.length());
		String regx = "^\\d+$";
		Pattern pattern = Pattern.compile(regx);
		Matcher m = pattern.matcher(s);
		String resutl = longitude.substring(0,longitude.length()-1);
		if(m.matches()){
			if(longitude.contains("-")){
				String[] longArray = longitude.split("-");
				if(longArray.length == 1){
					longitude = (Long.valueOf(longArray[0])+180)+"°";
				}else if(longArray.length == 2){
					longitude = (Long.valueOf(longArray[0])+180)+"°" + longArray[1] +"'";
				}else{
					longitude = (Long.valueOf(longArray[0])+180)+"°" + longArray[1] +"'" + longArray[2] +"\"";
				}
			}
			else{
				String[] longArray1 = longitude.split("°");
				longitude = (Long.valueOf(longArray1[0])+180)+"°"+longArray1[1];
			}
			return longitude;
		}else if(s.equals("E")){
			if(resutl.contains("-")){
				String[] longArray = resutl.split("-");
				if(longArray.length == 1){
					resutl = (Long.valueOf(longArray[0])+180)+"°";
				}else if(longArray.length == 2){
					resutl = (Long.valueOf(longArray[0])+180)+"°" + longArray[1] +"'";
				}else{
					resutl = (Long.valueOf(longArray[0])+180)+"°" + longArray[1] +"'" + longArray[2] +"\"";
				}
			}
			else{
				String[] longArray1 = resutl.split("°");
				resutl = (Long.valueOf(longArray1[0])+180)+"°"+longArray1[1];
			}
			return resutl;
		}else if(s.equals("W")){
			if(resutl.contains("-")){
				String[] longArray = resutl.split("-");
				if(longArray.length == 1){
					resutl = (-(Long.valueOf(longArray[0]))+180)+"°";
				}else if(longArray.length == 2){
					resutl = (-(Long.valueOf(longArray[0]))+180)+"°" + longArray[1] +"′";
				}else{
					resutl = (-(Long.valueOf(longArray[0]))+180)+"°" + longArray[1] +"′" + longArray[2] +"″";
				}
			}
			else{
				String[] longArray1 = resutl.split("°");
				resutl = (-(Long.valueOf(longArray1[0]))+180)+"°"+longArray1[1];
			}
			return resutl;
		}else if(s.equals("′")||s.equals("°")||s.equals("″")||s.equals("'")||s.equals("\"")){
			String[] degree = resutl.split("°");
			if(degree[1]!=null&&degree[1]!=""){
				resutl = (Long.valueOf(degree[0])+180)+"°"+degree[1];
			}else{
				resutl = (Long.valueOf(degree[0])+180)+"°";
			}
			return resutl;
		}else{
			return "error";
		}
	}
	
	/**
	 * 将数学表达式转为经度
	 * @param latitude 经度
	 * @return String
	 * @throws CommonException 
	 */
	public static String convertPlusMinusToLongitude(String longitude){
		String[] longitudeArray = longitude.split("°");
		Long result = Long.valueOf(longitudeArray[0])-180;
		if(result>=0){
			if(longitudeArray.length == 1){
				longitude = result+"°"+"E";
			}else{
				longitude = result+"°"+longitudeArray[1]+"E";
			}
		}else{
			if(longitudeArray.length == 1){
				longitude = Math.abs(result)+"°"+"W";
			}else{
				longitude = Math.abs(result)+"°"+longitudeArray[1]+"W";
			}
		}
		return longitude;
	}
	
	/**
	 * 将数学表达式转为纬度
	 * @param latitude 纬度
	 * @return String
	 * @throws CommonException 
	 */
	public static String convertPlusMinusToLatitude(String latitude){
		String[] latitudeArray = latitude.split("°");
		Long result = Long.valueOf(latitudeArray[0])-90;
		if(result>=0){
			if(latitudeArray.length == 1){
				latitude = result+"°"+"N";
			}else{
				latitude = result+"°"+latitudeArray[1]+"N";
			}
		}else{
			if(latitudeArray.length == 1){
				latitude = Math.abs(result)+"°"+"S";
			}else{
				latitude = Math.abs(result)+"°"+latitudeArray[1]+"S";
			}
			
		}
		return latitude;
	}
}