package com.ht.persistence.dao.inter.statisticalanalysis;

import java.util.Date;
import java.util.List;

import com.ht.persistence.model.statisticalanalysis.CompilationCompleteStatus;
import com.ht.persistence.model.statisticalanalysis.view.CompilationCompleteStatusView;

/**
 * 港口航道图完成情况Dao
 * @author zyd
 *
 */
public interface CompilationCompleteStatusDao {
	
	/**
	 * 获取所有港口航道图完成情况
	 * @return BookInfo列表
	 */
	public List<CompilationCompleteStatusView> getCompilationCompleteStatus(Date date1,Date date2);
	
	
	
	/**
	 * 根据Id获取一条数据
	 * @param bookInfo
	 * @return
	 */
	public CompilationCompleteStatusView getCompilationCompleteStatusById(CompilationCompleteStatusView compilationCompleteStatus);
	
	/**
	 * 保存一条数据
	 * @param  CatalogArea实体
	 */
	public void add(CompilationCompleteStatus compilationCompleteStatus);



	public List<CompilationCompleteStatusView> getCompilationCompleteStatusByYear(
			CompilationCompleteStatusView ccs);
}
