package com.ht.service.impl.datum.bookinfo;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.ht.common.util.DataConverter;
import com.ht.common.util.FileUtil;
import com.ht.common.util.LogHelper;
import com.ht.persistence.dao.inter.datum.bookinfo.BookFileDao;
import com.ht.persistence.model.datum.bookinfo.BookFile;
import com.ht.service.inter.datum.bookinfo.BookFileService;

/**
 * 图书文件Service实现类
 * @author 刘凯
 */
public class BookFileServiceImpl implements BookFileService {
	
	/**
	 * 注入BookFileDao
	 */
	@Resource (name = "bookFileDao")
	private BookFileDao bookFileDao;

	/**
	 * 获取一条附件
	 * @param bookFile
	 * @return List<BookFile>附件集合
	 */
	@Override
	public BookFile getFileById(String bookFileId) throws Exception {
		try {
			// 获取部分资料
			return bookFileDao.getFileById(bookFileId);
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
	public List<BookFile> getFileByBookId(String bookFileParam) throws Exception {
		try {
			BookFile bookFile = (BookFile) DataConverter.convertJson2Object(bookFileParam,
					BookFile.class);
			// 获取部分资料
			return bookFileDao.getFileByBookId(bookFile);
		} catch (Exception e) {
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
	public void downloadFile(BookFileService bookFileService,String bookfileId) throws Exception {
		try {
			BookFile bf = bookFileService.getFileById(bookfileId);
			// 获取项目所在服务器路径，将\\替换为/
			String serverPath = ("D:\\").replaceAll("\\\\", "/");
			// 获取文件路径
			String filePath = (bf.getFilePath()).replaceAll("\\\\", "/");
			// 获取文件名称
			String fileName = bf.getFileName();

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

	@Override
	public void removeBookFile(String bookFileId) throws Exception {
		BookFile bf = new BookFile();
		bf.setId(bookFileId);
		bookFileDao.removeBookFile(bf);
	}
}