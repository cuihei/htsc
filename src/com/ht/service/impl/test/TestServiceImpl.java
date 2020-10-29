package com.ht.service.impl.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.ServletActionContext;

import com.ht.common.export.ExcelUtil;
import com.ht.common.util.CellValueUtil;
import com.ht.common.util.ConvertUtil;
import com.ht.common.util.DataConverter;
import com.ht.common.util.FileUtil;
import com.ht.common.util.GenerateSequenceUtil;
import com.ht.common.util.LogHelper;
import com.ht.persistence.dao.inter.datum.bookinfo.BookFileDao;
import com.ht.persistence.dao.inter.datum.bookinfo.ReturnBookDao;
import com.ht.persistence.dao.inter.datum.books.BooksDao;
import com.ht.persistence.dao.inter.datum.datum.BorrowingDao;
import com.ht.persistence.dao.inter.test.TestDao;
import com.ht.persistence.model.background.dicdata.basedata.BaseData;
import com.ht.persistence.model.datum.bookinfo.BookFile;
import com.ht.persistence.model.datum.bookinfo.ReturnBook;
import com.ht.persistence.model.datum.books.Books;
import com.ht.persistence.model.datum.books.BooksView;
import com.ht.persistence.model.datum.datum.Borrowing;
import com.ht.service.inter.datum.bookinfo.BookInfoService;
import com.ht.service.inter.datum.books.BooksService;
import com.ht.service.inter.test.TestService;

/**
 * TestService实现类
 * @author houchen
 *
 */
@SuppressWarnings("unchecked")
public class TestServiceImpl implements TestService {
	
	/**
	 * 注入Dao
	 */
	@Resource(name="testDao")
	TestDao testDao;
	
	/**
	 * 根据sql查询
	 * @param sql
	 * @return Object List
	 */
	@Override
	public List<Object> search(String sql){
		try {
			// 获取所有海图
			return testDao.search(sql);
		}catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}
}
