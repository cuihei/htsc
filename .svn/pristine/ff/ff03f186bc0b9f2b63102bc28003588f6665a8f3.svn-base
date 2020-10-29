package com.ht.persistence.dao.inter.datum.datum;

import java.util.List;

import com.ht.persistence.model.datum.datum.DatumFile;
import com.ht.persistence.model.datum.type.DatumCategory;


/**
 * DatumFileDao
 * @author zyd
 */
public interface DatumFileDao {
	
	/**
	 * 增加Datumfile
	 * @param BookFile DatumFile实体
	 */
	public void addDatumFile(DatumFile datumFile);
	
	/**
	 * 更新datumfile
	 * @param BookFile DatumFile实体
	 */
	public void modifyDatumFile(DatumFile datumFile);
	
	/**
	 * 删除datumFile
	 * @param BookFile DatumFile实体
	 */
	public void deleteDatumFile(DatumFile datumFile);
	
	/**
	 * 获取所有DatumFile
	 * @return DatumFile列表
	 */
	public List<DatumFile> getDatumFile();
	
	/**
	 * 获取一条DatumFile
	 * @param id DatumFile主键
	 * @return DatumFile实体
	 */
	public DatumFile getDatumFile(DatumFile datumFile);
	
	/**
	 * 获取部分资料
	 * @param datumCategoryId
	 * @return
	 */
	public List<DatumFile> getIsFile(String datumCategoryId);
	
	/**
	 * 获取资料维护树的数据
	 * @return List<DatumFile>资料维护列表
	 */
	public List<DatumFile> getDatumFileTree();

	/**
	 * 校验目录是否使用
	 * @throws Exception 
	 */
	public int checkIfExist(DatumCategory datumCategory);
	
}
