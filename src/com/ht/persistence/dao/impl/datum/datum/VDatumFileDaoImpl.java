package com.ht.persistence.dao.impl.datum.datum;

import java.util.List;

import org.hibernate.SQLQuery;
import com.ht.persistence.dao.impl.base.BaseDaoImpl;

import com.ht.persistence.dao.inter.datum.datum.VDatumFileDao;
import com.ht.persistence.model.background.application.Application;
import com.ht.persistence.model.datum.datum.VDatumFile;


/**
 * DatumFile数据映射操作类
 * @author zyd
 *
 */
public class VDatumFileDaoImpl extends BaseDaoImpl implements VDatumFileDao {
	
	/**
	 * 获取全部资料文件
	 * return VDatumFile列表
	 */
	@Override
	public List<VDatumFile> getDatumFile() {
		SQLQuery query = this.getSession().createSQLQuery("select * from v_datum_file t   start with t.id= '10271749038240008'  connect by t.category_id = prior t.id");
		query.addEntity(VDatumFile.class);
		
		@SuppressWarnings("unchecked")
		List<VDatumFile> appList =  query.list();
		return appList;
	}
}
