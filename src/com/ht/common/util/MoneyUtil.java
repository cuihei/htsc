package com.ht.common.util;

public class MoneyUtil {
	
		/**
		 * 是否为金额格式字符串
		 * @param str
		 * @return
		 */
	   private static boolean isNumber(String str)   
	   {   
	       java.util.regex.Pattern pattern=java.util.regex.Pattern.compile("^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){0,2})?$"); // 判断小数点后2位的数字的正则表达式  
	       java.util.regex.Matcher match=pattern.matcher(str);   
	       if(match.matches()==false)   
	       {   
	          return false;   
	       }   
	       else   
	       {   
	          return true;   
	       }   
	   }  
	   
	   /**
	    * 转化为金额格式
	    * @param moneyStr 金额字符串
	    * @return 金额格式字符串
	    */
	   public static String convertToMoney(String moneyStr){
		   java.text.DecimalFormat nf = new java.text.DecimalFormat("###,###,###,###,###.##");
		   if (moneyStr!=null) {
			   if (isNumber(moneyStr)) {
				   return nf.format(Float.parseFloat(moneyStr));
			   }
			   else{
				   return moneyStr;
			   }
		   }
		   return null;
	   }

	   /**
	    * 金额格式转换为金额
	    * @param moneyStr
	    * @return
	    */
	   public static String convertToSimpleMoney(String moneyStr){
		if (moneyStr != null) {
			if (!isNumber(moneyStr)) {
				return moneyStr.replace(",", "");
			}
		}

		return moneyStr;
	}
}
