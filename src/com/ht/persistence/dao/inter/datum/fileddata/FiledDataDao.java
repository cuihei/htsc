package com.ht.persistence.dao.inter.datum.fileddata;

import java.util.List;

import com.ht.persistence.model.datum.fileddata.FiledData;
import com.ht.persistence.model.datum.fileddata.ViewFiledData;

/**
 * 外业汇交Dao
 * @author zyd
 *
 */
public interface FiledDataDao {
	
	/**
	 * 增加外业汇交
	 * @param FiledData FiledData实体
	 */
	public void addFiledData(FiledData FiledData);
	
	/**
	 * 更新外业汇交
	 * @param FiledData FiledData实体
	 */
	public void modifyFiledData(FiledData FiledData);
	
	/**
	 * 删除外业汇交
	 * @param FiledData FiledData实体
	 */
	public void deleteFiledData(FiledData FiledData);
	
	/**
	 * 获取所有外业汇交
	 * @return FiledData列表
	 */
	public List<FiledData> getFiledData();
	
	/**
	 * 获取一条外业汇交
	 * @param id FiledData主键
	 * @return FiledData实体
	 */
	public FiledData getFiledData(FiledData FiledData);
	
	/**
	 * 从外业汇交视图根据Id获取一条数据
	 * @param vFiledData
	 * @return
	 */
	public ViewFiledData getViewFiledData(ViewFiledData vFiledData);
	
	/**
	 * 根据图号获取一条数据
	 * @param filedData
	 * @return
	 */
	public FiledData getFiledDataByPicNo(FiledData filedData);
	
	/**
	 * 根据图号和汇交时间获取一条数据
	 * @param filedData
	 * @return
	 */
	public FiledData getFiledDataByPicNoAndConcurrentTime(FiledData filedData);
	
	/**
	 * 根据条件模糊查询
	 * @param hql
	 * @return
	 */
	public List<FiledData> getList(String hql);

	/**
	 * 获取外业汇交信息
	 * @param sql
	 * @return
	 */
	public ViewFiledData getFiledDataBySql(String sql);

	public List<FiledData> getFiledDataByStatus();
	
}
