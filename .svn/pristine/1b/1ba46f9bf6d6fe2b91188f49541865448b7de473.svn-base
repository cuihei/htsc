package com.ht.service.inter.datum.datum;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.ht.persistence.model.datum.datum.DatumFile;

/**
 * 资料文件Service
 * @author zyd
 *
 */
public interface DatumFileService {
	
	/**
	 * 保存资料文件
	 * @param datumFile
	 * @throws Exception
	 */
	public void addDatumFile(DatumFile datumFile) throws Exception;
	
	/**
	 * 修改资料文件
	 * @param datumFile
	 * @throws Exception
	 */
	public void modifyDatumFile(String datumFile) throws Exception;
	
	/**
	 * 删除资料文件
	 * @param id
	 * @throws Exception
	 */
	public void deleteDatumFile(String id) throws Exception;
	
	/**
	 * 获取所有资料文件
	 * @return
	 * @throws Exception
	 */
	public List<DatumFile> getDatumFile() throws Exception;
	
	/**
	 * 获取一条资料文件
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public DatumFile getDatumFile(String datumFile) throws Exception;
	
	/**
	 * 获取部分资料
	 * @param datumCategoryId
	 * @return
	 * @throws Exception 
	 */
	public List<DatumFile> getIsFile(String datumCategoryId) throws Exception;
	
	/**
	 * 新增资料
	 * @param datumFile 
	 * @param upload
	 * @param uploadFileName
	 * @throws IOException
	 */
	public void addDatumFile(String datumFile, File upload, String uploadFileName) throws IOException;
	
	/**
	 * 文件下载
	 * @param datumFileService 
	 * @param datumfileId
	 * @throws Exception 
	 */
	public void downloadFile(DatumFileService datumFileService, String datumfileId) throws Exception;
	
	/**
	 * 上传文件
	 * @param categoryId
	 * @param upload
	 * @param uploadFileName
	 */
	public void uploadFile(String categoryId, File upload, String uploadFileName) throws Exception;
	
	/**
	 * 获取资料维护树的数据
	 * @return List<DatumFile>资料维护列表
	 */
	public List<DatumFile> getDatumFileTree() throws Exception;
	
	/**
	 * 校验目录是否使用
	 * @return List<DatumFile>
	 */
	public int checkIfExist(String datumCategoryId,String datumCategoryParentId) throws Exception;
	
}
