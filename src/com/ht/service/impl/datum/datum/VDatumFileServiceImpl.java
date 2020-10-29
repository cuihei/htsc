package com.ht.service.impl.datum.datum;

import java.util.List;

import javax.annotation.Resource;

import com.ht.common.util.LogHelper;
import com.ht.persistence.dao.inter.datum.datum.VDatumFileDao;
import com.ht.persistence.model.datum.datum.VDatumFile;
import com.ht.service.inter.datum.datum.VDatumFileService;

/**
 * 资料文件Service实现类
 * @author zyd
 *
 */
public class VDatumFileServiceImpl implements VDatumFileService {
	
	/**
	 * 注入DatumFileDao
	 */
	@Resource (name = "vdatumFileDao")
	private VDatumFileDao vdatumFileDao;
	
	/**
	 * 获取资料维护树的数据
	 * @return List<DatumFile>资料维护列表
	 */
	@Override
	public List<VDatumFile> getDatumFile() throws Exception {
		try {
			return vdatumFileDao.getDatumFile();
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}
}