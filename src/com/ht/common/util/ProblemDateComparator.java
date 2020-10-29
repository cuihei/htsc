package com.ht.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ht.persistence.model.background.authority.role.RoleApplicationRel;


public class ProblemDateComparator implements Comparator<Map<String,Object>>{

	@Override
	public int compare(Map<String,Object> map1,Map<String,Object> map2) {
		String s1=(String)map1.get("FKRQ");
		String s2=(String)map2.get("FKRQ");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date1 = new Date();
		Date date2 = new Date();
		try {
			date1 = sdf.parse(s1);
			date2 = sdf.parse(s2);
		} catch (ParseException e) {
			e.printStackTrace();
		}
//		int n = date2.compareTo(date1);
		return date2.compareTo(date1);
		/*if (n>1) {
			return 1;
		}
		else
			if(n<0){
				return -1;
			}
			else{
				return 0;
			}*/
	}

	
}
