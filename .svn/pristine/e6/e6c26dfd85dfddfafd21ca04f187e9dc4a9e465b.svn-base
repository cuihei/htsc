package com.ht.persistence.dao.inter.statisticalanalysis;

import java.util.List;

import com.ht.persistence.model.statisticalanalysis.CompilationRealWorkDays;
import com.ht.persistence.model.statisticalanalysis.CompilationWorkDays;

/**
 * 工天Dao
 * @author yp
 *
 */
public interface CompilationWorkDaysDao {
	
	/**
	 * @return 工天总数
	 */
	public int getCompilationWorkDaysSum(CompilationWorkDays cw);

	/**
	 * @return 工天列表
	 */
	public List<CompilationWorkDays>  getCompilationWorkDaysList(CompilationWorkDays cw);
	
	/**
	 * 额定工天list
	 * @return  CompilationWorkDays
	 */
	public List<CompilationWorkDays>  getWorkDaysList();
	
	/**
	 * 实际工天list
	 * @return  CompilationRealWorkDays
	 */
	public List<CompilationRealWorkDays>  getRealWorkDaysList();
	
	/**
	 * 保存额定工天
	 */
	public void modifyWorkDays(CompilationWorkDays compilationWorkDays);
	
	/**
	 * 保存实际工天
	 */
	public void  modifyRealWorkDays(CompilationRealWorkDays compilationRealWorkDays);
	
	/**
	 * 删除实际工天
	 */
	public void  delRealWorkDays(CompilationRealWorkDays compilationRealWorkDays);
	
	/**
	 * 删除额定工天
	 */
	public void  delWorkDays(CompilationWorkDays CompilationWorkDays);
	
	/**
	 * 额定工天by id
	 */
	public CompilationWorkDays getWorkDaysListById(CompilationWorkDays compilationWorkDays);
	
	/**
	 * 实际工天by id
	 * @return 
	 */
	public CompilationRealWorkDays  getRealWorkDaysListById(CompilationRealWorkDays compilationRealWorkDays);
	
	/**
	 * 更新额定工天
	 */
	public void updateWorkDays(CompilationWorkDays compilationWorkDays);
	
	/**
	 * 更新实际工天
	 */
	public void  updateRealWorkDays(CompilationRealWorkDays compilationRealWorkDays);
	
	/**
	 * 校验额定工天图号是否已经存在
	 */
	public Boolean  ifExistWorkDays(CompilationWorkDays compilationWorkDays);
	
	/**
	 * 校验实际工天图号是否已经存在
	 */
	public Boolean  ifExistRealWorkDays(CompilationRealWorkDays compilationRealWorkDays);
	
	public CompilationRealWorkDays getRealWorkDaysListByMapNo(CompilationRealWorkDays compilationRealWorkDays);
	
	public CompilationWorkDays getWorkDaysListByMapNo(CompilationWorkDays c);
	
}
