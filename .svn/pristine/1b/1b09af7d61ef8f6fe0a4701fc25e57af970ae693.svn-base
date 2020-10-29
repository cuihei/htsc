package com.ht.service.impl.datum.correctionnoticebook;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFDataFormat;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.ht.common.export.ExcelUtil;
import com.ht.common.util.CellValueUtil;
import com.ht.common.util.ConvertUtil;
import com.ht.common.util.DataConverter;
import com.ht.common.util.FileUtil;
import com.ht.common.util.GenerateSequenceUtil;
import com.ht.common.util.LogHelper;
import com.ht.exception.DBException;
import com.ht.persistence.dao.inter.background.dicdata.basedata.BaseDataDao;
import com.ht.persistence.dao.inter.datum.bookinfo.BookFileDao;
import com.ht.persistence.dao.inter.datum.correctionnoticebook.CorrectionNoticeBookDao;
import com.ht.persistence.model.background.authority.role.Role;
import com.ht.persistence.model.background.authority.role.RoleUsers;
import com.ht.persistence.model.background.dicdata.basedata.BaseData;
import com.ht.persistence.model.background.organization.employee.UserOrgView;
import com.ht.persistence.model.datum.bookinfo.BookFile;
import com.ht.persistence.model.datum.bookinfo.BookInfo;
import com.ht.persistence.model.datum.books.Books;
import com.ht.persistence.model.datum.correctionnoticebook.CorrectionNoticeBook;
import com.ht.persistence.model.datum.correctionnoticebook.CorrectionNoticeBookView;
import com.ht.service.inter.datum.correctionnoticebook.CorrectionNoticeBookService;

/**
 * 海图Service实现类
 * @author houchen
 *
 */
@SuppressWarnings("unchecked")
public class CorrectionNoticeBookServiceImpl implements CorrectionNoticeBookService {
	
	@Resource(name="correctionNoticeBookDao")
	CorrectionNoticeBookDao correctionNoticeBookDao;
	
	@Resource(name="baseDataDao")
	BaseDataDao baseDataDao;
	
	/**
	 * 注入BookFileDao
	 */
	@Resource (name = "bookFileDao")
	private BookFileDao bookFileDao;
	@Override
	public void addCorrectionNotice(String c) throws Exception {
		try {
			// 将JSON转为对象
			CorrectionNoticeBook cNotice = (CorrectionNoticeBook) DataConverter.convertJson2Object(c, CorrectionNoticeBook.class);
			// 如果Id不为空，则进行更新
			if(cNotice.getId() != null) {
				correctionNoticeBookDao.modifyCorrectionNoticeBook(cNotice);
			}else {
				// 如果Id为空，则进行添加
				cNotice.setId(GenerateSequenceUtil.generateSequenceNo());
				cNotice.setState("暂未采用");
				correctionNoticeBookDao.addCorrectionNoticeBook(cNotice);
			}
		} catch (Exception e) {
			LogHelper.ERROR.log(e.getMessage(),e);
			throw e;
		}
		
	}
	@Override
	public void changeState(String c) throws Exception {
		try {
			// 将JSON转为对象
			CorrectionNoticeBook cNotice = (CorrectionNoticeBook) DataConverter.convertJson2Object(c, CorrectionNoticeBook.class);
			// 如果Id不为空，则进行更新
			if(cNotice.getId() != null) {
				CorrectionNoticeBook correctionNoticeBook = correctionNoticeBookDao.getCorrectionNoticeBook(cNotice);
				correctionNoticeBook.setState(cNotice.getState());
				correctionNoticeBook.setCreationDate(correctionNoticeBook.getCreationDate());
				correctionNoticeBookDao.modifyCorrectionNoticeBook(correctionNoticeBook);
			}
		} catch (Exception e) {
			LogHelper.ERROR.log(e.getMessage(),e);
			throw e;
		}
		
	}
	
	/**
	 * 删除海图
	 */
	@Override
	public void deleteCorrectionNotice(String c) throws Exception {
		try {
			List<CorrectionNoticeBook> list = (List<CorrectionNoticeBook>) DataConverter.convertJson2List(c,CorrectionNoticeBook.class);
			for (int i = 0; i < list.size(); i++) {
				correctionNoticeBookDao.deleteCorrectionNoticeBook(list.get(i));
			}
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}
	
	/**
	 * 获取所有海图
	 */
	@Override
	public List<CorrectionNoticeBookView> getCorrectionNoticeBooks() throws Exception {
		try {
			// 获取所有海图
			return correctionNoticeBookDao.getCorrectionNoticeBooks();
		}catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}
	
	/**
	 * 获取一条海图
	 */
	@Override
	public CorrectionNoticeBook getCorrectionNoticeBook(String id) throws Exception {
		try {
			CorrectionNoticeBook c = new CorrectionNoticeBook();
			c.setId(id);
			// 根据id获取图书资料
			return correctionNoticeBookDao.getCorrectionNoticeBook(c);
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}

	@Override
	public InputStream exportExcel()
	{
		InputStream stream = null;
		String[] title =
		{ "资料来源", "文号", "收到日期", "主要内容", "备注" };
		String sheetName = "改正通告编辑资料";
		List<CorrectionNoticeBookView> cList = correctionNoticeBookDao.getCorrectionNoticeBooks();
		try
		{
			// 创建excel工作簿
			XSSFWorkbook workbook = new XSSFWorkbook();
			// 创建一个工作表
			XSSFSheet sheet = workbook.createSheet(sheetName);
			// 给一个工作表名称一个长度
			sheet.setDefaultColumnWidth(15);
			// 生成一个样式
			XSSFCellStyle style = workbook.createCellStyle();
			// 创建第一行
			XSSFRow row = sheet.createRow(0);
			// 设置样式居中
			style.setAlignment(XSSFCellStyle.ALIGN_CENTER);
			// 创建字体对象
			Font font = workbook.createFont();
			// 字体加粗
			font.setBoldweight(Font.BOLDWEIGHT_BOLD);
			// 添加字体样式
			style.setFont(font);
			// 设置行高
			row.setHeightInPoints(20);
			// 设置第一行标题
			XSSFCell cell = null;
			for (int i = 0; i < title.length; i++)
			{
				cell = row.createCell(i);
				cell.setCellValue(title[i]);
				sheet.setColumnWidth(i, title[i].getBytes().length * 1 * 256);
				cell.setCellStyle(style);
			}
			// 创建记录
			int size = 0;
			if(cList != null){
				if(cList.size()>0){
					size = cList.size();
				}
			}
			if (size > 0)
			{
				for (int i = 1; i <= size; i++)
				{
					XSSFRow xssfRow = sheet.createRow(i);
					XSSFCell xssfCell = null;
					CorrectionNoticeBookView u = cList.get(i - 1);
					for (int j = 0; j < title.length; j++)
					{

						if (j == 0)
						{
							xssfCell = xssfRow.createCell(j);
							xssfCell.setCellValue(u.getSource());
						}

						if (j == 1)
						{
							xssfCell = xssfRow.createCell(j);
							xssfCell.setCellValue(u.getTitanic());
						}

						
						if (j == 2)
						{
							xssfCell = xssfRow.createCell(j);
							/*Date date = u.getReceiveDate();
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
							sdf.format(date);*/
							xssfCell.setCellValue(u.getReceiveDate()+"");
						}
						if (j == 3)
						{
							xssfCell = xssfRow.createCell(j);
							xssfCell.setCellValue(u.getContent());
						}
						if (j == 4)
						{
							xssfCell = xssfRow.createCell(j);
							xssfCell.setCellValue(u.getRemark());
						}
						xssfCell.setCellStyle(style);
					}
				}
			}
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			workbook.write(outputStream);
			outputStream.flush();
			byte[] byteArray = outputStream.toByteArray();
			stream = new ByteArrayInputStream(byteArray, 0, byteArray.length);
			outputStream.close();
		}
		catch (Exception e)
		{
			e.getMessage();
		}
		return stream;

	}
	/**
	 * 导出模版
	 * 
	 * @throws Exception
	 */
	@Override
	public void exportInfoTemplate() throws Exception {
	    ExcelUtil<CorrectionNoticeBook> util1 = new ExcelUtil<CorrectionNoticeBook>(CorrectionNoticeBook.class);  
	    util1.getListToExcel(null, "改正通告原始资料","改正通告原始资料导入模板");  
	   }
	/**
	 *	改正通告原始资料导入
	 * @throws Exception 
	 */
	@Override
	public String uploadInfoFile(File file,String fileName,String LoginUser) throws Exception
	{
		StringBuffer failMsg = new StringBuffer();
		try
		{
			// 创建文件流
			FileInputStream is = new FileInputStream(file);
			// 加载文件流
			XSSFWorkbook wbs = new XSSFWorkbook(is);
			// 读取第一个Sheet
			XSSFSheet firstSheet = wbs.getSheetAt(0);
			int rows = firstSheet.getLastRowNum();//总行数
			int clos = firstSheet.getRow(0).getPhysicalNumberOfCells();//总列数
			XSSFCell cell;// 产生单元格  
			cell = firstSheet.getRow(0).createCell(clos);
        	cell.setCellValue("导入结果");  
        	// 遍历行
			int failNum = 0;
			int count = 0;
			for (int i = 1; i < rows+1; i++)
			{
				XSSFRow row = firstSheet.getRow(i);
				CorrectionNoticeBook correctionNoticeBook = new CorrectionNoticeBook();
				int j = 0;
			 	//资料来源
                String source=row.getCell(j++)==null?"":CellValueUtil.getCellValue(row.getCell(j-1)).trim();
                if (source == null || source.isEmpty())
				{
                	failMsg.append("<p>第" + (i + 1)
							+ "行导入失败，失败原因为：资料来源为空！</p>");
					failNum = failNum + 1;
					continue;
				}
                BaseData bd=new BaseData();
                bd.setValue(source);
                bd.setTypeId("04171410193260026");
                BaseData sourceData = baseDataDao.getDataByValue(bd);
                if (sourceData == null)
				{
                	failMsg.append("<p>第" + (i + 1)
							+ "行导入失败，失败原因为：资料来源不存在！</p>");
					failNum = failNum + 1;
					continue;
				}
              
                correctionNoticeBook.setSource(source);
              // 文号
                String titanic=row.getCell(j++)==null?"":CellValueUtil.getCellValue(row.getCell(j-1)).trim();
                if (titanic == null || titanic.isEmpty())
				{
                	failMsg.append("<p>第" + (i + 1)
							+ "行导入失败，失败原因为：文号不能为空！</p>");
					failNum = failNum + 1;
					continue;
				}
                correctionNoticeBook.setTitanic(titanic);
                //收到日期
                String datePattern = "\\d{4}-\\d{1,2}-\\d{1,2}";
                String receiveDate=CellValueUtil.getCellValue(row.getCell(j++));
                if(receiveDate!=null){
                	if(!receiveDate.matches(datePattern)){
                		failMsg.append("<p>第" + (i + 1)
    							+ "行导入失败，失败原因为：收到日期格式不正确（例：1900-01-01）！</p>");
    					failNum = failNum + 1;
						continue;
                	}
                	Date date = new SimpleDateFormat("yyyy-MM-dd").parse(receiveDate);
                	correctionNoticeBook.setReceiveDate(date);
                }else{
                	correctionNoticeBook.setReceiveDate(null);
                }
                //主要内容
                String content=CellValueUtil.getCellValue(row.getCell(j++));
                correctionNoticeBook.setContent(content);
                //备注
                String remark=CellValueUtil.getCellValue(row.getCell(j++));
                correctionNoticeBook.setRemark(remark);
                correctionNoticeBook.setId(GenerateSequenceUtil.generateSequenceNo());
                // 执行更新
                correctionNoticeBook.setCreationDate(new Date());
                correctionNoticeBook.setCreator(LoginUser);
                correctionNoticeBook.setModifyDate(new Date());
                correctionNoticeBook.setModifier(LoginUser);
                
                correctionNoticeBookDao.addCorrectionNoticeBook(correctionNoticeBook);
                count++;
			}
			failMsg.append("<p>共成功导入" + count + "条数据，失败" + failNum + "条！</p>");
		}
		catch (Exception e)
		{
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(), e);
			throw e;
		}
		 return failMsg.toString();

		}

	@Override
	public void uploadFile(String booksId, File upload, String uploadFileName) throws Exception{
		try {
			//根据图书id获取附件，如果附件存在则先删除原附件，再添加新附件。
			BookFile oldFile=new BookFile();
			oldFile.setBookId(booksId);
			List<BookFile> oldFileList = bookFileDao.getFileByBookId(oldFile);
			if(oldFileList!=null&&oldFileList.size()>0){
				for (BookFile bookFile : oldFileList) {
					bookFileDao.removeBookFile(bookFile);
				}
			}
			// 获取项目在服务器的路径
	 		String serverPath ="D:\\";
	 		// 新建一个路径，在最后以当前年月日新建一个文件夹
	 		String path = "\\upload\\correction\\"+ ConvertUtil.convertDateToString("yyyyMMdd",new Date());
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
	        CorrectionNoticeBook c = new CorrectionNoticeBook();
	        c.setId(booksId);
			// 根据id获取图书资料
	        c = correctionNoticeBookDao.getCorrectionNoticeBook(c);
			String bookName = c.getTitanic();
	        BookFile bookFile = new BookFile();
	        bookFile.setId(GenerateSequenceUtil.generateSequenceNo());
	        bookFile.setBookId(booksId);
	        bookFile.setBookName(bookName);
	        bookFile.setFileName(uploadFileName);
	        bookFile.setFilePath(path);
	        bookFile.setSpaceSize(spaceSize);
	        bookFile.setSuffixName(suffixName);
	        bookFile.setCreator("");
	        bookFile.setCreationDate(new Date());
	        bookFile.setModifier("");
	        bookFile.setModifyDate(new Date());
	        // 添加文件数据
	        bookFileDao.addBookFile(bookFile);
		}catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}

	@Override
	public void modifyCorrectionNotice(CorrectionNoticeBook correctionNoticeBook)
			throws Exception {
		if(correctionNoticeBook!=null&&StringUtils.isNotEmpty(correctionNoticeBook.getId())){
			correctionNoticeBookDao.modifyCorrectionNoticeBook(correctionNoticeBook);
		}
		
	}

	@Override
	public List<CorrectionNoticeBookView> getCorrectionNoticeBooksByCreateDate(Date beginDate, Date endDate)
			throws Exception {
		return correctionNoticeBookDao.getCorrectionNoticeBooksByCreateDate(beginDate, endDate);
	}

	@Override
	public List<CorrectionNoticeBookView> getCorrectionNoticeBooksByCreateDateAndState(Date beginDate, Date endDate,String state)
			throws Exception {
		return correctionNoticeBookDao.getCorrectionNoticeBooksByCreateDateAndState(beginDate, endDate, state);
	}
}
