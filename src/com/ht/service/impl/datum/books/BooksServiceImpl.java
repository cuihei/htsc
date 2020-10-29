package com.ht.service.impl.datum.books;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
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
import com.ht.persistence.dao.inter.datum.bookinfo.BookFileDao;
import com.ht.persistence.dao.inter.datum.bookinfo.ReturnBookDao;
import com.ht.persistence.dao.inter.datum.books.BooksDao;
import com.ht.persistence.dao.inter.datum.datum.BorrowingDao;
import com.ht.persistence.model.background.dicdata.basedata.BaseData;
import com.ht.persistence.model.datum.bookinfo.BookFile;
import com.ht.persistence.model.datum.bookinfo.ReturnBook;
import com.ht.persistence.model.datum.books.Books;
import com.ht.persistence.model.datum.books.BooksView;
import com.ht.persistence.model.datum.datum.Borrowing;
import com.ht.service.impl.background.dicdata.constants.BaseDataConstants;
import com.ht.service.inter.datum.bookinfo.BookInfoService;
import com.ht.service.inter.datum.books.BooksService;

/**
 * 海图Service实现类
 * @author houchen
 *
 */
@SuppressWarnings("unchecked")
public class BooksServiceImpl implements BooksService {
	
	/**
	 * 注入海图Dao
	 */
	@Resource(name="booksDao")
	BooksDao booksDao;
	
	/**
	 * 注入BookFileDao
	 */
	@Resource (name = "bookFileDao")
	private BookFileDao bookFileDao;
	
	/**
	 * 注入借阅记录Dao
	 */
	@Resource (name = "BorrowingDao")
	private BorrowingDao BorrowingDao;
	
	/**
	 * 注入归还记录Dao
	 */
	@Resource (name = "returnBookDao")
	private ReturnBookDao returnBookDao;
	
	@Resource
	BookInfoService bookInfoService;
	/**
	 * 添加，更新海图
	 */
	@Override
	public void addBooks(String booksParam,String LoginUser) throws Exception {
		try {
			// 将JSON转为对象
			Books books = (Books) DataConverter.convertJson2Object(booksParam, Books.class);
			// 如果Id不为空，则进行更新
			if(books.getId() != null) {
				// 得到改数据的审核状态
				Books bs = new Books();
				bs.setId(books.getId());
				bs = booksDao.getBooks(books);
				//设置状态
				if (books.getStockNo().equals("0")) {
					books.setState("110319542370421");
				}else{
					books.setState("11031954066330412");
				}
				// 执行更新
				books.setEntry(LoginUser);
				books.setCreationDate(new Date());
				books.setCreator(LoginUser);
				books.setModifyDate(new Date());
				books.setModifier(LoginUser);
				if(bs != null){
					books.setStatus(bs.getStatus()==null?"":bs.getStatus());
					books.setReviewers(bs.getReviewers()==null?"":bs.getReviewers());
				}
				booksDao.mergeBooks(books);
			}else {
				//只校验编码是否重复
				/*if(isCodeExist(books.getCode(),"0")){
					  Exception e = new Exception();
					  throw e;
	            }*/
				// 如果Id为空，则进行添加
				books.setId(GenerateSequenceUtil.generateSequenceNo());
				//设置状态
				if (books.getStockNo().equals("0")) {
					books.setState("110319542370421");
				}else{
					books.setState("11031954066330412");
				}
				// 执行添加
				books.setEntry(LoginUser);
				books.setStatus(BaseDataConstants.CATALOG_STATUS_INIT);
				booksDao.addBooks(books);
			}
		} catch (Exception e) {
			LogHelper.ERROR.log(e.getMessage(),e);
			throw e;
		}
		
	}
	
	/**
	 * 更新海图
	 */
	public void modifyBooks(String Books) throws Exception{
	}
	
	/**
	 * 删除海图
	 */
	@Override
	public String deleteBooks(String books) throws Exception {
		try {
			List<Books> list = (List<Books>) DataConverter.convertJson2List(books,Books.class);
			for (int i = 0; i < list.size(); i++) {

				Books data = booksDao.getBooks(list.get(i));
				if(data!=null){
					 if(data.getStockNo().equals(data.getTotal())&&data.getStockNo().equals(data.getCanBorrowing())){
						// 删除海图
						booksDao.deleteBooks(data);
						BookFile bookFile =new BookFile();
						bookFile.setBookId(data.getId());
						//获取图书附件列表
						List<BookFile> filelist = bookFileDao.getFileByBookId(bookFile);
						for (BookFile bookFile2 : filelist) {
							//删除附件
							bookFileDao.removeBookFile(bookFile2);
						}
					
					}else{
						return "删除失败，该资料外借中";
					}
				}else{
					return "删除失败,该资料不存在";
				}
				
			}
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			return "系统异常请联系管理员";
		}
		return "删除成功";
	}
	
	/**
	 * 获取所有海图
	 */
	@Override
	public List<Books> getBooks() throws Exception {
		try {
			// 获取所有海图
			return booksDao.getBooks();
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
	public Books getBooks(String id) throws Exception {
		try {
			Books books = new Books();
			books.setId(id);
			// 根据id获取图书资料
			return booksDao.getBooks(books);
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}
	
	/**
	 * 获取所有海图视图
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<BooksView> getBooksView() {
		try {
			// 获取所有海图
			List<BooksView> list = booksDao.getBooksView();
			return list;
		}catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}
	
	@Override
	public List<BooksView> getBooksByStatus() {
		try {
			// 获取所有海图
			List<BooksView> list = booksDao.getBooksByStatus();
			return list;
		}catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}
	
	/**
	 * 获取多条图书资料
	 */
	@Override
	public List<BooksView> getBooksViewList(String ids) throws Exception {
		List<BooksView> booklist = new ArrayList<BooksView>();
		try {
			if (StringUtils.isNotEmpty(ids)) {
				// 将用户String类型转成Plan对象
				String[] idsArray = ids.split(",");
				for (int i = 0; i < idsArray.length; i++) {
					String sql = "select * from V_Books where id = "+idsArray[i]; 
					booklist.add(booksDao.getBooksBySql(sql));
				}
			}
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
		return booklist;
	}
	
	/**
	 * 附件上传
	 * @throws Exception 
	 */
	@Override
	public void uploadFile(String booksId, File upload, String uploadFileName) throws Exception {
		try {
			// 获取项目在服务器的路径
	 		String serverPath ="D:\\";
	 		// 新建一个路径，在最后以当前年月日新建一个文件夹
	 		String path = "\\upload\\books\\"+ ConvertUtil.convertDateToString("yyyyMMdd",new Date());
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
	        Books books = new Books();
	        books.setId(booksId);
			// 根据id获取图书资料
	        books = booksDao.getBooks(books);
			String bookName = books.getChartName();
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
	
	/**
	 * 新增借阅记录
	 */
	@Override
	public void addBorrowing(String BorrowingParam, Books books, String surplus) {
		try {
			// 将JSON转成对象
			Borrowing borrowing = (Borrowing) DataConverter.convertJson2Object(BorrowingParam, Borrowing.class);
			// 添加Borrowing
			borrowing.setId(GenerateSequenceUtil.generateSequenceNo());
			borrowing.setCreator("");
			borrowing.setCreationDate(new Date());
			borrowing.setModifier("");
			borrowing.setModifyDate(new Date());
			// 设置库存数量
			books.setStockNo(surplus);
			//设置状态
			if (surplus.equals("0")) {
				books.setState("110319542370421");
			}else{
				books.setState("11031954066330412");
			}
			// 执行借阅记录的添加
			BorrowingDao.addBorrowing(borrowing);
			// 更新海图表
			booksDao.modifyBooks(books);
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}
	
	/**
	 * 图书归还
	 */
	@Override
	public void returnBook(String returnBookParam, Books books, String returnNo) {
		try {
			// 将JSON转成对象
			ReturnBook returnBook = (ReturnBook) DataConverter.convertJson2Object(returnBookParam, ReturnBook.class);
			// 添加Borrowing
			returnBook.setId(GenerateSequenceUtil.generateSequenceNo());
			returnBook.setCreator("");
			returnBook.setCreationDate(new Date());
			returnBook.setModifier("");
			returnBook.setModifyDate(new Date());
			// 执行归还记录的添加
			returnBookDao.addReturnBook(returnBook);
			
			// 获取图书剩余数量
			String stockNo = books.getStockNo();
			int num = Integer.parseInt(stockNo);
			int returnNum = Integer.parseInt(returnNo);
			// 计算归还后的总数
			int sum = num+returnNum;
			String stockNoSum = String.valueOf(sum);
			//设置库存
			books.setStockNo(stockNoSum);
			//设置状态
			if (stockNoSum.equals("0")) {
				books.setState("110319542370421");
			}else{
				books.setState("11031954066330412");
			}
			booksDao.modifyBooks(books);
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}
	
	/**
	 * 根据ChartNo获取一条海图
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@Override
	public Books getBooksByChartNo(String chartNo) {
		try {
			Books books = new Books();
			books.setChartNo(chartNo);
			// 根据id获取图书资料
			return booksDao.getBooksByChartNo(books);
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}
	
	/**
	 * 根据条件模糊查询
	 * @return 
	 */
	@Override
	public List<BooksView> getList(String books, String stockNoTwo,
			String publicationDateTwo, String scaleTwo) {
		try {
			// 将Json转成对象
			BooksView b = (BooksView) DataConverter.convertJson2Object(books, BooksView.class);
			// 获取图号
			String chartNo = null;
			//获取图名
			String chartName = null;
			//获取录入人
			// 获取版本号
			String version = null;
			// 获取一级子类
			String oneSubClass =null;
			// 获取二级子类
			String twoSubClass =null;
			// 获取状态
			String state = null;
			// 获取港口/地区
			String port =null;
			// 获取比例尺
			String scale = null;
			//获取库存
			String stockNo =null;
			// 获取开始发布日期
			Date date=null;
			if(b!=null){
				// 获取图号
				 chartNo = b.getChartNo();
				//获取图名
				 chartName = b.getChartName();
				//获取录入人
				// 获取版本号
				 version = b.getVersion();
				// 获取一级子类
				 oneSubClass = b.getOneSubClass();
				// 获取二级子类
				 twoSubClass = b.getTwoSubClass();
				// 获取状态
				 state = b.getState();
				// 获取港口/地区
				 port = b.getPort();
				// 获取比例尺
				 scale = b.getScale();
				//获取库存
				 stockNo = b.getStockNo();
				// 获取开始发布日期
				date = b.getPublicationDate();
			}
			// 拼接sql
			String hql = "select * from V_Books b where 1=1";
			if(chartNo != null && chartNo != ""){
				hql += " and b.chart_no like '%" + chartNo + "%'";
			}
			if(chartName != null && chartName != ""){
				hql += " and b.chart_name like '%" + chartName + "%'";
			}
			if(version != null && version != ""){
				hql += " and b.version like '%" + version + "%'";
			}
			if(state != null && state != ""){
				hql += " and b.state like '%" + state + "%'";
			}
			if(port != null && port != ""){
				hql += " and b.port like '%" + port + "%'";
			}
			if(oneSubClass != null && oneSubClass != ""){
				hql += " and b.one_Sub_Class like '%" + oneSubClass + "%'";
			}
			if(twoSubClass != null && twoSubClass != ""){
				hql += " and b.two_Sub_Class like '%" + twoSubClass + "%'";
			}
			if(stockNo != null && stockNo != ""){
				hql += " and b.stock_no >= to_number(' "+ stockNo+" ')";
			}
			if(stockNoTwo != null && stockNoTwo != ""){
				hql += " and b.stock_no <= to_number(' "+ stockNoTwo+" ')";
			}
			
			// 判断日期是否为空
			if(date != null){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String publicationDate = sdf.format(date);
				if(publicationDate != null && publicationDate != ""){
					hql += " and b.publication_Date >= to_date (' "+ publicationDate+" ','yyyy-mm-dd')";
				}
			}
			if(publicationDateTwo != null && publicationDateTwo != ""){
				hql += " and b.publication_Date <= to_date (' "+ publicationDateTwo+" ','yyyy-mm-dd')";
			}
			if(scale != null && scale != ""){
				hql += " and b.scale+0 >= " + "'"+scale+"'";
			}
			if(scaleTwo != null && scaleTwo != ""){
				hql += " and b.scale+0 <= " + "'"+scaleTwo+"'";
			}
			return booksDao.getList(hql);
		} catch (Exception e) {
			LogHelper.ERROR.log(e.getMessage(),e);
			throw e;
		}
	}

	@Override
	public BooksView getBooksView(String id) throws Exception {
		try {
			BooksView books = new BooksView();
			books.setId(id);
			// 根据id获取图书资料
			return booksDao.getBooksView(books);
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}
	
	/**
	 * 导出模版
	 * 
	 * @throws Exception
	 */
	@Override
	public void exportBookTemplate() throws Exception {
	    ExcelUtil<Books> util1 = new ExcelUtil<Books>(Books.class);  
	    util1.getListToExcel(null, "海图资料","海图资料导入模板");  
	   }
	
//	/**
//	 * 海图导入
//	 * @throws Exception 
//	 */
//	@Override
//	public String uploadBookFile(File file,String fileName,String LoginUser) throws Exception
//	{
//		StringBuffer failMsg = new StringBuffer();
//		try
//		{
//			
//			// 创建文件流
//			FileInputStream is = new FileInputStream(file);
//			// 加载文件流
//			XSSFWorkbook wbs = new XSSFWorkbook(is);
//			// 读取第一个Sheet
//			XSSFSheet firstSheet = wbs.getSheetAt(0);
//			int rows = firstSheet.getLastRowNum();//总行数
//			int clos = firstSheet.getRow(0).getPhysicalNumberOfCells();//总列数
//			XSSFCell cell;// 产生单元格  
//			cell = firstSheet.getRow(0).createCell(clos);
//        	cell.setCellValue("导入结果");  
//        	// 遍历行
//			Map<String, String> map = new HashMap<String, String>();
//			int failNum = 0;
//			int count = 0;
//			for (int i = 1; i < rows+1; i++)
//			{
//				XSSFRow row = firstSheet.getRow(i);
//				BookFile files = new BookFile();
//				int j = 0;
//				//id
//				String id=row.getCell(j++)==null?"":CellValueUtil.removePoint(row.getCell(j-1)).trim();
//				files.setId(id);
//				//图书id
//				String bookId=row.getCell(j++)==null?"":CellValueUtil.removePoint(row.getCell(j-1)).trim();
//				files.setBookId(bookId);
//				//文件名
//				String name=row.getCell(j++)==null?"":CellValueUtil.removePoint(row.getCell(j-1)).trim();
//				if(name!=null){
//					String[] names = name.split("/");
//					name=names[names.length-1];
//				}
//				files.setFileName(name);
//				//后缀名
//				String suffix=row.getCell(j++)==null?"":CellValueUtil.removePoint(row.getCell(j-1)).trim();
//				if(suffix!=null){
//					String[] suffixs = suffix.split("\\.");
//					suffix=suffixs[suffixs.length-1];
//				}
//				files.setSuffixName(suffix);
//				//空间大小
//				String spaceSize=row.getCell(j++)==null?"":CellValueUtil.removePoint(row.getCell(j-1)).trim();
//				files.setSpaceSize(spaceSize);
//				//路径
//				String filePath=row.getCell(j++)==null?"":CellValueUtil.removePoint(row.getCell(j-1)).trim();
//				if(filePath!=null){
//					filePath = filePath.substring(0,filePath.lastIndexOf("/"));
//				}
//				files.setFilePath(filePath);
//				files.setCreationDate(new Date());
//				files.setCreator("admin");
//				files.setModifier("admin");
//				files.setModifyDate(new Date());
//				bookFileDao.addBookFile(files);
//			}
//			failMsg.append("<p>共成功导入" + count + "条数据，失败" + failNum + "条！</p>");
//		}
//		catch (Exception e)
//		{
//			// 写错误日志
//			LogHelper.ERROR.log(e.getMessage(), e);
//			throw e;
//		}
//		return failMsg.toString();
//	}
//	/**
//	 * 海图导入
//	 * @throws Exception 
//	 */
//	@Override
//	public String uploadBookFile(File file,String fileName,String LoginUser) throws Exception
//	{
//		StringBuffer failMsg = new StringBuffer();
//		try
//		{
//			
//			// 创建文件流
//			FileInputStream is = new FileInputStream(file);
//			// 加载文件流
//			XSSFWorkbook wbs = new XSSFWorkbook(is);
//			// 读取第一个Sheet
//			XSSFSheet firstSheet = wbs.getSheetAt(0);
//			int rows = firstSheet.getLastRowNum();//总行数
//			int clos = firstSheet.getRow(0).getPhysicalNumberOfCells();//总列数
//			XSSFCell cell;// 产生单元格  
//			cell = firstSheet.getRow(0).createCell(clos);
//			cell.setCellValue("导入结果");  
//			// 遍历行
//			Map<String, String> map = new HashMap<String, String>();
//			int failNum = 0;
//			int count = 0;
//			for (int i = 1; i < rows+1; i++)
//			{
//				XSSFRow row = firstSheet.getRow(i);
//				Books books = new Books();
//				int j = 0;
//				//id
//				String id=row.getCell(j++)==null?"":CellValueUtil.removePoint(row.getCell(j-1)).trim();
//				books.setId(id);
//				//编码
//				String code=row.getCell(j++)==null?"":CellValueUtil.removePoint(row.getCell(j-1)).trim();
//				if (code == null || code.isEmpty())
//				{
//					failMsg.append("<p>第" + (i + 1)
//							+ "行导入失败，失败原因为：编码不能为空！</p>");
//					failNum = failNum + 1;
//					continue;
//				}
//				books.setCode(code);
//				//图号
//				String chartNo=row.getCell(j++)==null?"":CellValueUtil.removePoint(row.getCell(j-1)).trim();
//				if (chartNo == null || chartNo.isEmpty())
//				{
//					failMsg.append("<p>第" + (i + 1)
//							+ "行导入失败，失败原因为：图号不能为空！</p>");
//					failNum = failNum + 1;
//					continue;
//				}
//				books.setChartNo(chartNo);
//				//图名
//				String chartName=row.getCell(j++)==null?"":CellValueUtil.removePoint(row.getCell(j-1)).trim();
//				if (chartName == null || chartName.isEmpty())
//				{
//					failMsg.append("<p>第" + (i + 1)
//							+ "行导入失败，失败原因为：图名不能为空！</p>");
//					failNum = failNum + 1;
//					continue;
//				}
//				books.setChartName(chartName);
//				
//				//一级子类
//				String oneSubClass=row.getCell(j++)==null?"":CellValueUtil.removePoint(row.getCell(j-1)).trim();
//				books.setOneSubClass(oneSubClass);
//				//二级子类
//				String twoSubClass=row.getCell(j++)==null?"":CellValueUtil.removePoint(row.getCell(j-1)).trim();
//				books.setTwoSubClass(twoSubClass);
//				//港口地区
//				String port=CellValueUtil.getCellValue(row.getCell(j++));
//				if(port!=null){
//					String[] split = port.split("\\.");
//					port=split[0];
//				}
//				books.setPort(port);
//				//比例尺
//				String scale=CellValueUtil.removePoint(row.getCell(j++));
//				books.setScale(scale);
//				//出版年月
//				String datePattern = "\\d{4}-\\d{2}";
//				String publicationDate=CellValueUtil.removePoint(row.getCell(j++));
//				if(publicationDate!=null){
//					Date date = new SimpleDateFormat("yyyy-MM").parse(publicationDate);
//					books.setPublicationDate(date);
//				}else{
//					books.setPublicationDate(null);
//				}
//				//版本号
//				String version=CellValueUtil.removePoint(row.getCell(j++));
//				books.setVersion(version);
//				//存储位置
//				String savePlace=CellValueUtil.getCellValue(row.getCell(j++));
//				books.setSavePlace(savePlace);
//				//库存数量
//				String stockNo=CellValueUtil.removePoint(row.getCell(j++));
//				if (stockNo == null || stockNo.isEmpty())
//				{
//					failMsg.append("<p>第" + (i + 1)
//							+ "行导入失败，失败原因为：库存数量不能为空！</p>");
//					failNum = failNum + 1;
//					continue;
//				}
//				books.setStockNo(stockNo);
//				books.setTotal(stockNo);
//				books.setCanBorrowing(stockNo);
//				//设置状态	
//				String state=CellValueUtil.getCellValue(row.getCell(j++));
//				if (state!=null) {
//					books.setState("11031954066330412");
//				}else{
//					books.setState("110319542370421");
//				}
//				//首版年月
//				String firstVersionDate=CellValueUtil.getCellValue(row.getCell(j++));
//				if(firstVersionDate!=null){
//					Date date = new SimpleDateFormat("yyyy-MM").parse(firstVersionDate);
//					books.setFirstVersionDate(date);
//				}else{
//					books.setFirstVersionDate(null);
//				}
//				//改正项号
//				String correctNo=CellValueUtil.removePoint(row.getCell(j++));
//				books.setCorrectNo(correctNo);
//				//印刷年月
//				String printDate=CellValueUtil.getCellValue(row.getCell(j++));
//				if(printDate!=null){
//					Date date = new SimpleDateFormat("yyyy-MM").parse(printDate);
//					books.setPrintDate(date);
//				}else{
//					books.setPrintDate(null);
//				}
//				//出版单位
//				String publicationCompany=CellValueUtil.getCellValue(row.getCell(j++));
//				books.setPublicationCompany(publicationCompany);
//				//图幅范围（东经）
//				String tufufanwei=CellValueUtil.removePoint(row.getCell(j++));
//				if(tufufanwei!=""){
//					try {
//						String[] split = tufufanwei.split("\\;");
//						String start=split[0];
//						String end=split[2];
//						String[] starts = start.split(",");
//						String[] ends = end.split(",");
//						books.setLongitudeFrom(getDegree(starts[0],180));
//						books.setLongitudeTo(getDegree(ends[0],180));
//						books.setLatitudeFrom(getDegree(starts[1],90));
//						books.setLatitudeTo(getDegree(ends[1],90));
//					} catch (Exception e) {
//						e.printStackTrace();
//					}
//					
//				}else{
//					books.setLongitudeFrom(null);
//					books.setLongitudeTo(null);
//					books.setLatitudeFrom(null);
//					books.setLatitudeTo(null);
//				}
//				
//				//审核者
//				String reviewers=CellValueUtil.getCellValue(row.getCell(j++));
//				books.setReviewers(reviewers);
//				//电子数据下载权限
//				String downPermission=CellValueUtil.getCellValue(row.getCell(j++));
//				books.setDownPermission(downPermission);
//				//海图状态
//				String seaMapStatus=CellValueUtil.getCellValue(row.getCell(j++));
//				books.setSeaMapStatus(seaMapStatus);
//				//备注
//				String remarks=CellValueUtil.getCellValue(row.getCell(j++));
//				books.setRemarks(remarks);
//				//流转意见
//				String flowSuggestion=CellValueUtil.getCellValue(row.getCell(j++));
//				books.setFlowSuggestion(flowSuggestion);
//				//录入者
//				String entry=CellValueUtil.getCellValue(row.getCell(j++));
//				books.setEntry(entry);
//				
//				books.setCreationDate(new Date());
//				books.setCreator(LoginUser);
//				books.setModifyDate(new Date());
//				books.setModifier(LoginUser);
//				//设置审核状态
//				books.setStatus(BaseDataConstants.CATALOG_STATUS_PASS);
//				booksDao.addBooks(books);
//				count++;
//			}
//			failMsg.append("<p>共成功导入" + count + "条数据，失败" + failNum + "条！</p>");
//		}
//		catch (Exception e)
//		{
//			// 写错误日志
//			LogHelper.ERROR.log(e.getMessage(), e);
//			throw e;
//		}
//		return failMsg.toString();
//	}
	/**
	 * 海图导入
	 * @throws Exception 
	 */
	@Override
	public String uploadBookFile(File file,String fileName,String LoginUser) throws Exception
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
			Map<String, String> map = new HashMap<String, String>();
			int failNum = 0;
			int count = 0;
			for (int i = 1; i < rows+1; i++)
			{
				XSSFRow row = firstSheet.getRow(i);
				Books books = new Books();
				int j = 0;
				//编码
				String code=row.getCell(j++)==null?"":CellValueUtil.removePoint(row.getCell(j-1)).trim();
				if (code == null || code.isEmpty())
				{
					failMsg.append("<p>第" + (i + 1)
							+ "行导入失败，失败原因为：编码不能为空！</p>");
					failNum = failNum + 1;
					continue;
				}
				books.setCode(code);
				//图号
				String chartNo=row.getCell(j++)==null?"":CellValueUtil.removePoint(row.getCell(j-1)).trim();
				if (chartNo == null || chartNo.isEmpty())
				{
					failMsg.append("<p>第" + (i + 1)
							+ "行导入失败，失败原因为：图号不能为空！</p>");
					failNum = failNum + 1;
					continue;
				}
				books.setChartNo(chartNo);
				//图名
				String chartName=row.getCell(j++)==null?"":CellValueUtil.removePoint(row.getCell(j-1)).trim();
				if (chartName == null || chartName.isEmpty())
				{
					failMsg.append("<p>第" + (i + 1)
							+ "行导入失败，失败原因为：图名不能为空！</p>");
					failNum = failNum + 1;
					continue;
				}
				books.setChartName(chartName);
				
				//一级子类
				String oneSubClass=row.getCell(j++)==null?"":CellValueUtil.removePoint(row.getCell(j-1)).trim();
				if (oneSubClass == null || oneSubClass.isEmpty())
				{
					failMsg.append("<p>第" + (i + 1)
							+ "行导入失败，失败原因为：一级子类不能为空！</p>");
					failNum = failNum + 1;
					continue;
				}
				String oneSubClassIId = bookInfoService.checkSubClass(oneSubClass,"201610301150");
				books.setOneSubClass(oneSubClassIId);
				//二级子类
				String twoSubClass=row.getCell(j++)==null?"":CellValueUtil.removePoint(row.getCell(j-1)).trim();
				if (twoSubClass == null || twoSubClass.isEmpty())
				{
					failMsg.append("<p>第" + (i + 1)
							+ "行导入失败，失败原因为：二级子类不能为空！</p>");
					failNum = failNum + 1;
					continue;
				}
				String twoSubClassId = bookInfoService.checkSubClass(twoSubClass,oneSubClassIId);
				books.setTwoSubClass(twoSubClassId);
				//港口地区
				String port=CellValueUtil.getCellValue(row.getCell(j++));
				String portId = checkPortIfExist(port);
				if(portId.isEmpty()){
					failMsg.append("<p>第" + (i + 1)
							+ "行导入失败，失败原因为：港口地区不存在！</p>");
					failNum = failNum + 1;
					continue;
				}
				books.setPort(portId);
				//比例尺
				String scale=CellValueUtil.removePoint(row.getCell(j++));
				books.setScale(scale);
				//出版年月
				String datePattern = "\\d{4}-\\d{1,2}";
				String publicationDate=CellValueUtil.removePoint(row.getCell(j++));
				if(publicationDate!=null){
					if(!publicationDate.matches(datePattern)){
						failMsg.append("<p>第" + (i + 1)
								+ "行导入失败，失败原因为：出版年月日期格式不正确（例：1900-01）！</p>");
						failNum = failNum + 1;
						continue;
					}
					Date date = new SimpleDateFormat("yyyy-MM").parse(publicationDate);
					books.setPublicationDate(date);
				}else{
					books.setPublicationDate(null);
				}
				//版本号
				String version=CellValueUtil.removePoint(row.getCell(j++));
				books.setVersion(version);
				//存储位置
				String savePlace=CellValueUtil.getCellValue(row.getCell(j++));
				books.setSavePlace(savePlace);
				//库存数量
				String stockNo=CellValueUtil.removePoint(row.getCell(j++));
				if (stockNo == null || stockNo.isEmpty())
				{
					failMsg.append("<p>第" + (i + 1)
							+ "行导入失败，失败原因为：库存数量不能为空！</p>");
					failNum = failNum + 1;
					continue;
				}
				books.setStockNo(stockNo);
				books.setTotal(stockNo);
				books.setCanBorrowing(stockNo);
				//设置状态
				if (stockNo.equals("0")) {
					books.setState("110319542370421");
				}else{
					books.setState("11031954066330412");
				}
				//首版年月
				String firstVersionDate=CellValueUtil.getCellValue(row.getCell(j++));
				if(firstVersionDate!=null){
					if(!firstVersionDate.matches(datePattern)){
						failMsg.append("<p>第" + (i + 1)
								+ "行导入失败，失败原因为：首版年月日期格式不正确（例：1900-01）！</p>");
						failNum = failNum + 1;
						continue;
					}
					Date date = new SimpleDateFormat("yyyy-MM").parse(firstVersionDate);
					books.setFirstVersionDate(date);
				}else{
					books.setFirstVersionDate(null);
				}
				//改正项号
				String correctNo=CellValueUtil.removePoint(row.getCell(j++));
				books.setCorrectNo(correctNo);
				//印刷年月
				String printDate=CellValueUtil.getCellValue(row.getCell(j++));
				if(printDate!=null){
					if(!printDate.matches(datePattern)){
						failMsg.append("<p>第" + (i + 1)
								+ "行导入失败，失败原因为：印刷年月日期格式不正确（例：1900-01）！</p>");
						failNum = failNum + 1;
						continue;
					}
					Date date = new SimpleDateFormat("yyyy-MM").parse(printDate);
					books.setPrintDate(date);
				}else{
					books.setPrintDate(null);
				}
				//出版单位
				String publicationCompany=CellValueUtil.getCellValue(row.getCell(j++));
				books.setPublicationCompany(publicationCompany);
				//图幅范围（东经）
				String longitudeFrom=CellValueUtil.removePoint(row.getCell(j++));
				
				//图幅范围（西经）
				String longitudeTo=CellValueUtil.removePoint(row.getCell(j++));
				
				//图幅范围（北纬）
				String latitudeFrom=CellValueUtil.removePoint(row.getCell(j++));
				
				//图幅范围（南纬）
				String latitudeTo=CellValueUtil.removePoint(row.getCell(j++));
				Map<String, String> checkMap = checkLatitudeAndLongitude(longitudeFrom, longitudeTo, latitudeFrom, latitudeTo);
				if(checkMap.get("flag").equals("false")){
					failMsg.append("<p>第" + (i + 1)
							+ checkMap.get("msg").toString()+"</p>");
					failNum = failNum + 1;
					continue;
				}
				books.setLongitudeFrom((String) checkMap.get("starLongitude"));
				books.setLongitudeTo((String) checkMap.get("endLongitude"));
				books.setLatitudeFrom((String) checkMap.get("starLatitude"));
				books.setLatitudeTo((String) checkMap.get("endLatitude"));
				//电子数据下载权限
				String downPermission=CellValueUtil.getCellValue(row.getCell(j++));
				books.setDownPermission(downPermission);
				//海图状态
				String seaMapStatus=CellValueUtil.getCellValue(row.getCell(j++));
				books.setSeaMapStatus(seaMapStatus);
				//备注
				String remarks=CellValueUtil.getCellValue(row.getCell(j++));
				books.setRemarks(remarks);
				//流转意见
				String flowSuggestion=CellValueUtil.getCellValue(row.getCell(j++));
				books.setFlowSuggestion(flowSuggestion);
				books.setId(GenerateSequenceUtil.generateSequenceNo());
				//设置审核状态
				books.setStatus(BaseDataConstants.CATALOG_STATUS_INIT);
				
				books.setEntry(LoginUser);
				books.setCreationDate(new Date());
				books.setCreator(LoginUser);
				books.setModifyDate(new Date());
				books.setModifier(LoginUser);
				booksDao.addBooks(books);
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
	
	/**
	 * 校验编码是否存在
	 */
	public Boolean isCodeExist(String code,String id){
		Books book  = new Books();
		book.setCode(code);
		book.setId(id);
		return booksDao.isCodeExist(book);
	}
	
	/**
	 * 校验port是否存在
	 */
	public String checkPortIfExist(String port){
		BaseData bd  = new BaseData();
		bd.setTypeId("02281125502140137");
		bd.setValue(port);
		bd = booksDao.checkPortIfExist(bd);
		return bd==null?"":bd.getId();
	}

	@Override
	public Books getBooksByCode(String chartNo) {
		Books b = new Books();
		b.setChartNo(chartNo);
		return booksDao.getBooksByChartNo(b);
	}
	/**
	 * 验证经纬度 是否正确
	 * @param starLongitude 开始经度
	 * @param endLongitude	终止经度
	 * @param starLatitude	开始纬度
	 * @param endLatitude	终止纬度
	 * @return
	 */
	public Map<String,String> checkLatitudeAndLongitude(String starLongitude,String endLongitude,String starLatitude,String endLatitude){
		String regex1 = "[0-9]|E|W";
		String regex2 = "[0-9]|N|S";
		// 匹配纬度度
		String regex3 = "^(\\d*)?([N]|[S])?$";
		// 匹配纬度度分
		String regex4 ="^(\\d*)?((-(\\d*){1}))([N]|[S]){0,1}$";
		// 匹配纬度度分秒
		String regex5 = "^(\\d*)?((-(\\d*){1}))((-(\\d*){1}))([N]|[S]){0,1}$";
		// 匹配经度度
		String regex6 = "^(\\d*)?([E]|[W])?$";
		// 匹配经度度分
		String regex7 = "^(\\d*)?((-(\\d*){1}))([E]|[W]){0,1}$";
		// 匹配经度度度分秒
		String regex8 = "^(\\d*)?((-(\\d*){1}))((-(\\d*){1}))([E]|[W]){0,1}$";
		
		Map<String,String> map=new HashMap<String,String>();
		String flag="true";
		//获取东经
		if(starLongitude != null && starLongitude != ""){
			if(!Pattern.matches(regex6, starLongitude)){
				if(!Pattern.matches(regex7, starLongitude)){
					if(!Pattern.matches(regex8, starLongitude)){
						map.put("flag","false");
						map.put("msg","您输入的起始经度数据有误,请改正！");
						return map;
					}
				}
			}
			String lastword3 = starLongitude.substring(starLongitude.length()-1,starLongitude.length());
//			if(Pattern.matches(regex1, lastword3)){
//				map.put("flag","false");
//				map.put("msg","起始经度单位错误，经度不填单位表示东经！");
//				return map;
//			}
			String star2 = starLongitude.substring(0,starLongitude.length()-1);
			String[] array3;
			if(lastword3.equalsIgnoreCase("E")){
				array3 = star2.split("-");
				if(array3.length==1){
					star2 = (Long.parseLong(array3[0])+180)+"°";
				}else if(array3.length==2){
					star2 = (Long.parseLong(array3[0])+180)+"°"+array3[1]+"′";
				}else{
					star2 = (Long.parseLong(array3[0])+180)+"°"+array3[1]+"′"+array3[2]+"″";
				}
			}else if(lastword3.equalsIgnoreCase("W")){
				array3 = star2.split("-");
				if(array3.length==1){
					star2 = (-(Long.parseLong(array3[0]))+180)+"°";
				}else if(array3.length==2){
					star2 = (-(Long.parseLong(array3[0]))+180)+"°"+array3[1]+"′";
				}else{
					star2 = (-(Long.parseLong(array3[0]))+180)+"°"+array3[1]+"′"+array3[2]+"″";
				}
			}else{
				array3 = starLongitude.split("-");
				if(array3.length==1){
					starLongitude = (Long.parseLong(array3[0])+180)+"°";
				}else if(array3.length==2){
					starLongitude = (Long.parseLong(array3[0])+180)+"°"+array3[1]+"′";
				}else{
					starLongitude = (Long.parseLong(array3[0])+180)+"°"+array3[1]+"′"+array3[2]+"″";
				}
				star2 = starLongitude;
			}
			if(array3.length==1){
				if(Long.parseLong(array3[0])>180){
					map.put("flag","false");
					map.put("msg","经度不能大于180°");
					return map;
				}
			}else if(array3.length==2){
				if(Long.parseLong(array3[0])==180&&Long.parseLong(array3[1])>0){
					map.put("flag","false");
					map.put("msg","经度不能大于180°");
					return map;
				}
				if(Long.parseLong(array3[1])>60){
					map.put("flag","false");
					map.put("msg","经度的秒不能大于60′");
					return map;
				}
			}else{
				if(Long.parseLong(array3[0])== 180 && Long.parseLong(array3[1])+Long.parseLong(array3[2])>0){
					map.put("flag","false");
					map.put("msg","经度不能大于90°");
					return map;
				}
				if(Long.parseLong(array3[1])>60){
					map.put("flag","false");
					map.put("msg","经度的分不能大于60′");
					return map;
				}
				if(Long.parseLong(array3[2])>60){
					map.put("flag","false");
					map.put("msg","经度的秒不能大于60″");
					return map;
					
				}
			}
			map.put("starLongitude",star2);
		}
		if(endLongitude != null && endLongitude != ""){
			if(!Pattern.matches(regex6, endLongitude)){
				if(!Pattern.matches(regex7, endLongitude)){
					if(!Pattern.matches(regex8, endLongitude)){
						map.put("flag","false");
						map.put("msg","您输入的终止经度数据有误,请改正！");
						return map;
					}
				}
			}
			String lastword4 = endLongitude.substring(endLongitude.length()-1,endLongitude.length());
//			if(!Pattern.matches(regex1, lastword4)){
//				map.put("flag","false");
//				map.put("msg","终止经度单位错误，经度不填单位表示东经！");
//				return map;
//			}
			String end2 = endLongitude.substring(0,endLongitude.length()-1);
			String[] array4;
			if(lastword4.equalsIgnoreCase("E")){
				array4 = end2.split("-");
				if(array4.length==1){
					end2 = (Long.parseLong(array4[0])+180)+"°";
				}else if(array4.length==2){
					end2 = (Long.parseLong(array4[0])+180)+"°"+array4[1]+"′";
				}else{
					end2 = (Long.parseLong(array4[0])+180)+"°"+array4[1]+"′"+array4[2]+"″";
				}
			}else if(lastword4.equalsIgnoreCase("W")){
				array4 = end2.split("-");
				if(array4.length==1){
					end2 = (-(Long.parseLong(array4[0]))+180)+"°";
				}else if(array4.length==2){
					end2 = (-(Long.parseLong(array4[0]))+180)+"°"+array4[1]+"′";
				}else{
					end2 = (-(Long.parseLong(array4[0]))+180)+"°"+array4[1]+"′"+array4[2]+"″";
				}
			}else{
				array4 = endLongitude.split("-");
				if(array4.length==1){
					endLongitude = (Long.parseLong(array4[0])+180)+"°";
				}else if(array4.length==2){
					endLongitude = (Long.parseLong(array4[0])+180)+"°"+array4[1]+"′";
				}else{
					endLongitude = (Long.parseLong(array4[0])+180)+"°"+array4[1]+"′"+array4[2]+"″";
				}
				end2 = endLongitude;
			}
			if(array4.length==1){
				if(Long.parseLong(array4[0])>180){
					map.put("flag","false");
					map.put("msg","经度不能大于180°");
					return map;
				}
			}else if(array4.length==2){
				if(Long.parseLong(array4[0])==180 && Long.parseLong(array4[1])>0){
					map.put("flag","false");
					map.put("msg","经度不能大于180°");
					return map;
				}
				if(Long.parseLong(array4[1])>60){
					map.put("flag","false");
					map.put("msg","经度的秒不能大于60′");
					return map;
				}
			}else{
				if(Long.parseLong(array4[0])== 180 && Long.parseLong(array4[1])+Long.parseLong(array4[2])>0){
					map.put("flag","false");
					map.put("msg","经度不能大于90");
					return map;
				}
				if(Long.parseLong(array4[1])>60){
					map.put("flag","false");
					map.put("msg","经度的秒不能大于60′");
					return map;
				}
				if(Long.parseLong(array4[2])>60){
					map.put("flag","false");
					map.put("msg","经度的秒不能大于60′");
					return map;
				}
			}
			map.put("endLongitude",end2);
		}
		//起始纬度(北纬)
		if(starLatitude != null && starLatitude != ""){
			if(!Pattern.matches(regex3, starLatitude)){
				if(!Pattern.matches(regex4, starLatitude)){
					if(!Pattern.matches(regex5, starLatitude)){
						map.put("flag","false");
						map.put("msg","您输入的起始纬度数据有误,请改正！");
						return map;
					}
				}
			}
			String lastword1  = starLatitude.substring(starLatitude.length()-1,starLatitude.length());
//			if(!Pattern.matches(regex2, lastword1)){
//				map.put("flag","false");
//				map.put("msg","起始纬度单位错误，纬度不填单位表示北纬！");
//				return map;
//			}
			String star1 = starLatitude.substring(0,starLatitude.length()-1);
			String[] array;
			if(lastword1.equalsIgnoreCase("N")){
				array = star1.split("-");
				if(array.length==1){
					star1 = (Long.parseLong(array[0])+90)+"°";
				}else if(array.length==2){
					star1 = (Long.parseLong(array[0])+90)+"°"+array[1]+"′";
				}else{
					star1 = (Long.parseLong(array[0])+90)+"°"+array[1]+"′"+array[2]+"″";
				}
			}else if(lastword1.equalsIgnoreCase("S")){
				array = star1.split("-");
				if(array.length==1){
					star1 = (-(Long.parseLong(array[0]))+90)+"°";
				}else if(array.length==2){
					star1 = (-(Long.parseLong(array[0]))+90)+"°"+array[1]+"′";
				}else{
					star1 = (-(Long.parseLong(array[0]))+90)+"°"+array[1]+"′"+array[2]+"″";
				}
			}else{
				array = starLatitude.split("-");
				if(array.length==1){
					starLatitude = (Long.parseLong(array[0])+90)+"°";
				}else if(array.length==2){
					starLatitude = (Long.parseLong(array[0])+90)+"°"+array[1]+"′";
				}else{
					starLatitude = (Long.parseLong(array[0])+90)+"°"+array[1]+"′"+array[2]+"″";
				}
				star1 = starLatitude;
			}
			if(array.length==1){
				if(Long.parseLong(array[0])>90){
					map.put("flag","false");
					map.put("msg","纬度不能大于90°");
					return map;
				}
			}else if(array.length==2){
				if(Long.parseLong(array[0])==90 && Long.parseLong(array[1])>0){
					map.put("flag","false");
					map.put("msg","纬度不能大于90°");
					return map;
				}
				if(Long.parseLong(array[1])>60){
					map.put("flag","false");
					map.put("msg","纬度的秒不能大于60′");
					return map;
				}
			}else{
				if(Long.parseLong(array[0])== 90 && Long.parseLong(array[1])+Long.parseLong(array[2])>0){
					map.put("flag","false");
					map.put("msg","纬度不能大于90°");
					return map;
				}
				if(Long.parseLong(array[1])>60){
					map.put("flag","false");
					map.put("msg","纬度的分不能大于60′");
					return map;
				}
				if(Long.parseLong(array[2])>60){
					map.put("flag","false");
					map.put("msg","纬度的秒不能大于60″");
					return map;
				}
			}
			map.put("starLatitude", star1);
		}
		//获取南纬
		if(endLatitude != null && endLatitude != ""){
			if(!Pattern.matches(regex3, endLatitude)){
				if(!Pattern.matches(regex4, endLatitude)){
					if(!Pattern.matches(regex5, endLatitude)){
						map.put("flag","false");
						map.put("msg","您输入的终止纬度数据有误,请改正！");
						return map;
					}
				}
			}
			String lastword2 = endLatitude.substring(endLatitude.length()-1,endLatitude.length());
//			if(!Pattern.matches(regex2, lastword2)){
//				map.put("flag","false");
//				map.put("msg","终止纬度单位错误，纬度不填单位表示北纬！");
//				return map;
//			}
			String end = endLatitude.substring(0,endLatitude.length()-1);
			String[] array2;
			if(lastword2.equalsIgnoreCase("N")){
				array2 = end.split("-");
				if(array2.length==1){
					end = (Long.parseLong(array2[0])+90)+"°";
				}else if(array2.length==2){
					end = (Long.parseLong(array2[0])+90)+"°"+array2[1]+"′";
				}else{
					end = (Long.parseLong(array2[0])+90)+"°"+array2[1]+"′"+array2[2]+"″";
				}
			}else if(lastword2.equalsIgnoreCase("S")){
				array2 = end.split("-");
				if(array2.length==1){
					end = (-(Long.parseLong(array2[0]))+90)+"°";
				}else if(array2.length==2){
					end = (-(Long.parseLong(array2[0]))+90)+"°"+array2[1]+"′";
				}else{
					end = (-(Long.parseLong(array2[0]))+90)+"°"+array2[1]+"′"+array2[2]+"″";
				}
			}else{
				array2 = endLatitude.split("-");
				if(array2.length==1){
					endLatitude = (Long.parseLong(array2[0])+90)+"°";
				}else if(array2.length==2){
					endLatitude = (Long.parseLong(array2[0])+90)+"°"+array2[1]+"′";
				}else{
					endLatitude = (Long.parseLong(array2[0])+90)+"°"+array2[1]+"′"+array2[2]+"″";
				}
				end = endLatitude;
			}
			if(array2.length==1){
				if(Long.parseLong(array2[0])>90){
					map.put("flag","false");
					map.put("msg","纬度不能大于90°");
					return map;
				}
			}else if(array2.length==2){
				if(Long.parseLong(array2[0])==90 && Long.parseLong(array2[1])>0){
					map.put("flag","false");
					map.put("msg","纬度不能大于90°");
					return map;
				}
				if(Long.parseLong(array2[1])>60){
					map.put("flag","false");
					map.put("msg","纬度的秒不能大于60′");
					return map;
				}
			}else{
				if(Long.parseLong(array2[0])== 90 && Long.parseLong(array2[1])+Long.parseLong(array2[2])>0){
					map.put("flag","false");
					map.put("msg","纬度不能大于90°");
					return map;
				}
				if(Long.parseLong(array2[1])>60){
					map.put("flag","false");
					map.put("msg","纬度的分不能大于60′");
					return map;
				}
				if(Long.parseLong(array2[2])>60){
					map.put("flag","false");
					map.put("msg","纬度的秒不能大于60″");
					return map;
				}
			}
			map.put("endLatitude",end);
		}
		map.put("flag", flag);
		return map;
	}
	/*
	 *情况：
	 *1.不含小数点：说明是整度数。
	 *2.含有小数点，但小数点后位数小于3位。2位或者1位：说明是度分类型。秒为0
	 *3.含有小数点，小数点后位数大大于3为。说明含有度，分，秒；
	 * 
	 */
	public static String getDegree(String degree,int num){
		//判断是否有分和秒
		String[] splits = degree.split("\\.");
		//获取度
		String du = splits[0];
		String fen="";
		String miao="";
		//增加度数，方便计算。
		Integer parseInt = Integer.parseInt(du)+num;
		du=parseInt.toString()+"°";
		if(splits.length>1){
			//获取分和秒
			String fenmiao = splits[1];
			Long f = Long.parseLong(fenmiao);
			if(fenmiao.length()>1){
				f=f*60;
				fen = f.toString();	
				miao=fen.substring(2,fen.length()-1);
				fen = fen.substring(0,2)+"′";
				if(miao!=""&&!"0".equals(miao)){
					Long m = Long.parseLong(miao);
					m=m*60;
					miao=m.toString();
					if(miao.length()>0){
						if(miao.length()>1){
							miao=miao.substring(0,2)+"″";
						}else{
							miao=miao.substring(0,miao.length()-1)+"″";
						}
					}
					
				}
			
			}else{
				fen=f.toString()+"′";
			}
		}
		return du+fen+miao;
	}
	
}
