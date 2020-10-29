package com.ht.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 得到当前日期的简单类
 * @author houchen
 *
 */
public class Cundate {
	
	 /**
	  * 得到当前日期
	  * @return
	  */
	 public String getNowTime(){
	    Date d = new Date();  //Get current date to d.
	    SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd"); //Set date format
	    String strDate = fm.format(d); //Get date to string strDate via format fm.
	    return strDate;
	  }
}
