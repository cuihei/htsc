package com.ht.common.util;

import java.util.Comparator;

import com.ht.persistence.model.background.authority.role.RoleApplicationRel;


public class DzyComparator implements Comparator<RoleApplicationRel>{

	@Override
	public int compare(RoleApplicationRel o1, RoleApplicationRel o2) {
		
		Integer navNo1 = Integer.parseInt(o1.getAppCode());
		Integer navNo2 = Integer.parseInt(o2.getAppCode());
		if (navNo1>navNo2) {
			return 1;
		}
		else
			if(navNo1<navNo2){
				return -1;
			}
			else{
				return 0;
			}
	}
	
}
