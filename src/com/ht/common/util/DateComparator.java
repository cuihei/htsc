package com.ht.common.util;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ht.persistence.model.background.authority.role.RoleApplicationRel;


public class DateComparator implements Comparator<Map<String,Object>>{

	@Override
	public int compare(Map<String,Object> map1,Map<String,Object> map2) {
		Date date1=(Date)map1.get("createTime");
		Date date2=(Date)map2.get("createTime");
		int n = date2.compareTo(date1);
		return n;
	}

	
}
