package com.ht.service.impl.datum.datum;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.ht.common.util.ConvertUtil;
import com.ht.common.util.DataConverter;
import com.ht.common.util.FileUtil;
import com.ht.common.util.GenerateSequenceUtil;
import com.ht.common.util.LogHelper;
import com.ht.persistence.dao.inter.datum.datum.DatumFileDao;
import com.ht.persistence.model.datum.datum.DatumFile;
import com.ht.persistence.model.datum.type.DatumCategory;
import com.ht.service.inter.datum.datum.DatumFileService;

/**
 * 资料文件Service实现类
 * @author zyd
 *
 */
public class DatumFileServiceImpl implements DatumFileService {
	
	/**
	 * 注入DatumFileDao
	 */
	@Resource (name = "DatumFileDao")
	private DatumFileDao datumFileDao;
	
	/**
	 * 添加资料文件
	 * @param datumFileParam 资料文件数据
	 */
	@Override
	public void addDatumFile(DatumFile datumFileParam) throws Exception {
		try {
			datumFileDao.addDatumFile(datumFileParam);
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}
	
	/**
	 * 更新资料文件
	 * @param datumFileParam 资料文件数据
	 */
	@Override
	public void modifyDatumFile(String datumFileParam) throws Exception {
		try {
			// 将JSON转成对象
			DatumFile datumdata = (DatumFile) DataConverter.convertJson2Object(datumFileParam, DatumFile.class);
			// 更新DatumFile
			datumFileDao.modifyDatumFile(datumdata);
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}
	
	/**
	 * 删除资料文件
	 * @param id 资料文件标识
	 */
	@Override
	public void deleteDatumFile(String id) throws Exception {
		try {
			DatumFile datumfile = new DatumFile();
			datumfile.setId(id);
			// 获取一条文件
			datumfile = datumFileDao.getDatumFile(datumfile);
			// 判断是否是电子文件
			if(datumfile.getFileType().equals("电子文档")){
				// 获取项目所在服务器路径
				String serverPath = FileUtil.ROOT_PATH;
				// 获取文件路径
				String filePath = datumfile.getFilePath();
				// 获取文件名称
				String fileName = datumfile.getFileName();
				String serveFile = serverPath+filePath+"\\"+fileName;
				File file = new File(serveFile);
				if(file.exists()){
					file.delete();
				}
				// 删除DatumFile
				datumFileDao.deleteDatumFile(datumfile);
			}else {
				// 删除DatumFile
				datumFileDao.deleteDatumFile(datumfile);
			}
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}
	
	/**
	 * 查询所有资料文件
	 */
	@Override
	public List<DatumFile> getDatumFile() throws Exception {
		try {
			// 获取所有DatumFile
			return datumFileDao.getDatumFile();
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}
	
	/**
	 * 查询一条资料文件
	 * @param id 资料文件标识
	 */
	@Override
	public DatumFile getDatumFile(String id) throws Exception {
		try {
			DatumFile datumfile = new DatumFile();
			datumfile.setId(id);
			// 根据id获取DatumFile
			return datumFileDao.getDatumFile(datumfile);
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}
	
	/**
	 * 获取部分资料
	 * @throws Exception 
	 */
	@Override
	public List<DatumFile> getIsFile(String datumCategoryId) throws Exception {
		try {
			// 获取部分资料
			return datumFileDao.getIsFile(datumCategoryId);
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}
	
	/**
	 * 校验目录是否使用
	 * @throws Exception 
	 */
	@Override
	public int checkIfExist(String datumCategoryId,String datumCategoryParentId) throws Exception {
		try {
			// 获取部分资料
			DatumCategory dc = new DatumCategory();
			dc.setId(datumCategoryId);
			dc.setParentId(datumCategoryParentId);
			return datumFileDao.checkIfExist(dc);
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}
	
	/**
	 * 新增资料
	 */
	@Override
	public void addDatumFile(String datumFileParam, File upload, String uploadFileName) throws IOException {
		DatumFile datumFile = (DatumFile) DataConverter.convertJson2Object(datumFileParam, DatumFile.class);
		datumFile.setId(GenerateSequenceUtil.generateSequenceNo());
		datumFile.setCreator("");
		datumFile.setCreationDate(new Date());
		datumFile.setModifier("");
		datumFile.setModifyDate(new Date());
		try {
			if(uploadFileName!=null){
				// 获取项目在服务器的路径
		 		String serverPath = FileUtil.ROOT_PATH;
		 		// 新建一个路径，在最后以当前年月日新建一个文件夹
		 		String path = "\\upload\\datumFile\\"+ ConvertUtil.convertDateToString("yyyyMMdd",new Date());
		 		// 创建文件夹
		 		FileUtil.CreateFolder(serverPath+path);
		        InputStream is = new FileInputStream(upload);
		        OutputStream os = new FileOutputStream(new File(serverPath+path, uploadFileName));
		        // 截取文件后缀名
		        String suffixName = uploadFileName.substring(uploadFileName.lastIndexOf("."));
		        // 获取文件大小
		        int size = is.available();
		        String spaceSize = null;
		        if(size > 0){
		        	// 大小除以1024，计算出等于多少kb
		        	int spacesize = size / 1024;
		        	spaceSize = Integer.toString(spacesize)+"kb";
		        }else {
		        	spaceSize = "";
		        }
		        byte[] buffer = new byte[500];
		        int length = 0;
		        while(-1 != (length = is.read(buffer, 0, buffer.length)))
		        {
		            os.write(buffer);
		        }
		        os.close();
		        is.close();
		        // 设置文件路径
		        datumFile.setFilePath(path);
		        datumFile.setSuffixName(suffixName);
		        datumFile.setSpaceSize(spaceSize);
		        datumFile.setFileName(uploadFileName);
			}
	        // 添加文件数据
	        datumFileDao.addDatumFile(datumFile);
		}catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}
	
	/**
	 * 文件下载
	 * @throws Exception 
	 */
	@Override
	public void downloadFile(DatumFileService datumFileService,String datumfileId) throws Exception {
		try {
			DatumFile df = datumFileService.getDatumFile(datumfileId);
			// 获取项目所在服务器路径，将\\替换为/
			String serverPath = (FileUtil.ROOT_PATH).replaceAll("\\\\", "/");
			// 获取文件路径
			String filePath = (df.getFilePath()).replaceAll("\\\\", "/");
			// 获取文件名称
			String fileName = df.getFileName();

			InputStream is = new FileInputStream(serverPath+filePath+"/"+fileName);
			//下载到哪里？客户端
			HttpServletResponse response = ServletActionContext.getResponse();
			OutputStream os = response.getOutputStream();
			//弹出下载的框filename:提示用户下载的文件名，解决中文乱码
			response.addHeader("content-disposition", "attachment;fileName="+new String(fileName.getBytes("utf-8"),"iso-8859-1"));
			byte[] b = new byte[1024];
			int size = is.read(b);
			while(size>0){
				os.write(b,0,size);
				size = is.read(b);
			}
			os.flush();
			is.close();
			os.close();
		}catch (Exception e){
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}
	
	/**
	 * 文件上传
	 * @throws Exception 
	 */
	@Override
	public void uploadFile(String categoryId, File upload, String uploadFileName) throws Exception {
		try {
			// 获取项目在服务器的路径
	 		String serverPath = FileUtil.ROOT_PATH;
	 		// 新建一个路径，在最后以当前年月日新建一个文件夹
	 		String path = "\\upload\\datumFile\\"+ ConvertUtil.convertDateToString("yyyyMMdd",new Date());
	 		// 创建文件夹
	 		FileUtil.CreateFolder(serverPath+path);
	        InputStream is = new FileInputStream(upload);
	        OutputStream os = new FileOutputStream(new File(serverPath+path, uploadFileName));
	        // 截取文件后缀名
	        String suffixName = uploadFileName.substring(uploadFileName.lastIndexOf("."));
	        // 获取文件大小
	        int size = is.available();
	        String spaceSize = null;
	        if(size > 0){
	        	// 大小除以1024，计算出等于多少kb
	        	int spacesize = size / 1024;
	        	spaceSize = Integer.toString(spacesize)+"kb";
	        }else {
	        	spaceSize = "";
	        }
	        byte[] buffer = new byte[500];
	        int length = 0;
	        while(-1 != (length = is.read(buffer, 0, buffer.length)))
	        {
	            os.write(buffer);
	        }
	        os.close();
	        is.close();
	        DatumFile datumFile = new DatumFile();
	        // 设置文件路径
	        datumFile.setId(GenerateSequenceUtil.generateSequenceNo());
	        datumFile.setFileType("电子文档");
	        datumFile.setCategoryId(categoryId);
	        datumFile.setFileName(uploadFileName);
	        datumFile.setFilePath(path);
	        datumFile.setSuffixName(suffixName);
	        datumFile.setSpaceSize(spaceSize);
	        datumFile.setCreator("");
 			datumFile.setCreationDate(new Date());
 			datumFile.setModifier("");
 			datumFile.setModifyDate(new Date());
	        // 添加文件数据
	        datumFileDao.addDatumFile(datumFile);
		}catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}

	/**
	 * 获取资料维护树的数据
	 * @return List<DatumFile>资料维护列表
	 */
	@Override
	public List<DatumFile> getDatumFileTree() throws Exception {
		try {
			return datumFileDao.getDatumFileTree();
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}
}