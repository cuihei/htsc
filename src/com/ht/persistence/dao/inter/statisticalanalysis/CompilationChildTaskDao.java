package com.ht.persistence.dao.inter.statisticalanalysis;

import java.util.List;

import com.ht.persistence.model.statisticalanalysis.view.CompilationChildTaskView;

/**
 * 港口航道图完成情况Dao
 * @author zyd
 *
 */
public interface CompilationChildTaskDao {
	///按年份查询
	public List<CompilationChildTaskView> getCompilationChildTask(CompilationChildTaskView c,String userId);
	//所有年份
	//public List<CompilationChildTaskView> getCompilationChildTaskAllYear(CompilationChildTaskView c);

	public List<String> getYearList();

	List<CompilationChildTaskView> getCompilationChildTask_old(CompilationChildTaskView c);

	public CompilationChildTaskView getVTaskById(String recordId);
	
}
