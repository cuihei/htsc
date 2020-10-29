package com.ht.service.impl.background.dicdata.defectform;

public class DefectFormHelper
{
	/**
	 * 获取质量等级
	 * @param grade
	 * @return
	 */
	public static String getZldj(String gradeStr){
		Double grade = Double.valueOf(gradeStr);
		if (grade>=90)
		{
			return "优";
		}
		else if(grade>=75&&grade<90){
			return "良";
		}
		else if(grade>=60&&grade<75){
			return "及格";
		}
		else{
			return "不及格";
		}
	}
}
