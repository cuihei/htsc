package com.ht.persistence.dao.inter.datum.datum;

import java.util.List;

import com.ht.persistence.model.datum.datum.VDatumFile;


/**
 * VDatumFileDao
 * @author zyd
 */
public interface VDatumFileDao {
	/**
	 * 获取所有VDatumFile
	 * @return DatumFile列表
	 */
	public List<VDatumFile> getDatumFile();
}
