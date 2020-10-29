package com.ht.persistence.dao.impl.datum.fileddata;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.SQLQuery;

import com.ht.persistence.dao.impl.base.BaseDaoImpl;
import com.ht.persistence.dao.inter.datum.fileddata.FiledDataDao;
import com.ht.persistence.model.datum.bookinfo.BookInfo;
import com.ht.persistence.model.datum.bookinfo.ViewBookInfo;
import com.ht.persistence.model.datum.fileddata.FiledData;
import com.ht.persistence.model.datum.fileddata.ViewFiledData;
import com.ht.persistence.model.system.notice.Notice;

/**
 * FiledData数据映射操作类
 * @author zyd
 *
 */
public class FiledDataDaoImpl extends BaseDaoImpl implements FiledDataDao {
	
	/**
	 * 外业汇交添加 
	 */
	@Override
	public void addFiledData(FiledData FiledData) {
		this.save(FiledData);
	}

	/**
	 * 外业汇交更新
	 */
	@Override
	public void modifyFiledData(FiledData FiledData) {	
		this.getSession().merge(FiledData);
		this.update(FiledData);
	}

	/**
	 * 删除外业汇交
	 */
	@Override
	public void deleteFiledData(FiledData FiledData) {
		 this.delete(FiledData);
	}
	
	/**
	 * 获取所有外业汇交
	 */
	@Override
	public List<FiledData> getFiledData() {
		
		try {
			@SuppressWarnings("unchecked")
			// 执行查询
			List<FiledData> result = this.findByNamedQuery("getViewFiledData");
			if(result.size() > 0){
				return result;
			}
			return null;
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * 获取一条外业汇交
	 */
	@Override
	public FiledData getFiledData(FiledData FiledData) {
		@SuppressWarnings("unchecked")
		List<FiledData> result = this.findByNamedQueryAndNamedParam("getFiledDataById", "id", FiledData.getId());
		if(result.size()>0){
			return result.get(0);
		}
		return null;
	}
	
	/**
	 * 从外业汇交视图获取一条数据
	 */
	@Override
	public ViewFiledData getViewFiledData(ViewFiledData vFiledData) {
		@SuppressWarnings("unchecked")
		List<ViewFiledData> result = this.findByNamedQueryAndNamedParam("getViewFiledDataById", "id", vFiledData.getId());
		if(result.size()>0){
			return result.get(0);
		}
		return null;
	}
	
	/**
	 * 根据图号获取一条数据
	 */
	@Override
	public FiledData getFiledDataByPicNo(FiledData filedData) {
		@SuppressWarnings("unchecked")
		List<FiledData> result = this.findByNamedQueryAndNamedParam("getFiledDataByPicNo", "picNo", filedData.getPicNo());
		if(result.size()>0){
			return result.get(0);
		}
		return null;
	}
	
	@Override
	public FiledData getFiledDataByPicNoAndConcurrentTime(FiledData filedData) {
		
		String hql="select * from FILED_DATA f  where   f.PIC_NO like '" + filedData.getPicNo() + "'";
		
		
		// 获取汇交时间
		Date concurrentTime = filedData.getConcurrentTime();
		// 判断日期是否为空
		if(concurrentTime != null){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String date = sdf.format(concurrentTime);
			if(date != null && date != ""){
				hql += " and f.CONCURRENT_TIME = to_date (' "+ date+" ','yyyy-mm-dd')";
			}
		}
		
		try {
			SQLQuery query = this.getSession().createSQLQuery(hql);
			query.addEntity(FiledData.class);
			List<FiledData> list = query.list();
			return list.get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	
	/**
	 * 根据条件模糊查询
	 */
	@Override
	public List<FiledData> getList(String hql) {
		try {
			SQLQuery query = this.getSession().createSQLQuery(hql);
			query.addEntity(FiledData.class);
			List<FiledData> list = query.list();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取一条图书资料
	 */
	@Override
	public ViewFiledData getFiledDataBySql(String sql) {
		try {
			SQLQuery query = this.getSession().createSQLQuery(sql);
			query.addEntity(ViewFiledData.class);
			List<ViewFiledData> list = query.list();
			if(list.size()>0){
				return list.get(0);
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	@Override
	public List<FiledData> getFiledDataByStatus() {
		
		SQLQuery query = this.getSession().createSQLQuery("select * from V_FILED_DATA_USERS t where t.status = '审核通过' order by t.MODIFY_DATE desc");
		query.addEntity(FiledData.class);
		
		@SuppressWarnings("unchecked")
		List<FiledData> appList =  query.list();
		if(appList.size()>0){
			return appList;
		}
		return null;
	}

	
	
}
