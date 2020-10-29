package com.ht.service.inter.system.workDays;

import java.util.List;

import com.ht.persistence.model.statisticalanalysis.CompilationRealWorkDays;
import com.ht.persistence.model.statisticalanalysis.CompilationWorkDays;


/**
 * 工天service
 * @author yp
 * */
public interface CompilationWorkDaysService {
  
	/**
	 *查询所有额定工天
	 * @param CompilationWorkDays CompilationWorkDays实体
	 */
	public List<CompilationWorkDays> getWorkDaysList();
	
	/**
	 *查询所有实际工天
	 * @param CompilationRealWorkDays CompilationRealWorkDays实体
	 */
	public List<CompilationRealWorkDays> getRealWorkDaysList();
	
	/**
	 *保存
	 * @throws Exception 
	 */
	public void modify(String workDays,String flag) throws Exception;
	
	/**
	 *删除
	 */
	public void delete(String workDays,String flag);
	
	/**
	 *查询实际工天by id
	 * @param CompilationRealWorkDays CompilationRealWorkDays实体
	 */
	public CompilationRealWorkDays getRealWorkDaysListById(String id);
	
	/**
	 *查询额定工天by id
	 * @param CompilationWorkDays CompilationWorkDays实体
	 */
	public CompilationWorkDays getWorkDaysListById(String id);

	/**
	 *校验额定工天图号是否已经存在
	 * */
	public Boolean ifExistWorkDays(String flag, String mapNo);

	/**
	 *校验实际工天图号是否已经存在
	 * */
	public Boolean ifExistRealWorkDays(String flag, String mapNo);
}
