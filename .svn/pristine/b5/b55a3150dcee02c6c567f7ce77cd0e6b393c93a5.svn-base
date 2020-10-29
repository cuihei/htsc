package com.ht.service.impl.datum.bookinfo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import com.ht.persistence.dao.inter.datum.bookinfo.BookInfoDao;
import com.ht.persistence.dao.inter.datum.bookinfo.ReturnBookDao;
import com.ht.persistence.dao.inter.datum.datum.BorrowingDao;
import com.ht.persistence.model.datum.bookinfo.BookFile;
import com.ht.persistence.model.datum.bookinfo.BookInfo;
import com.ht.persistence.model.datum.bookinfo.ReturnBook;
import com.ht.persistence.model.datum.bookinfo.ViewBookInfo;
import com.ht.persistence.model.datum.datum.Borrowing;
import com.ht.persistence.model.datum.type.DatumCategory;
import com.ht.service.impl.background.dicdata.constants.BaseDataConstants;
import com.ht.service.inter.datum.bookinfo.BookInfoService;

/**
 * 图书资料Service实现类
 * 
 * @author zyd
 *
 */
@SuppressWarnings("unchecked")
public class BookInfoServiceImpl implements BookInfoService {

	/**
	 * 注入图书资料Dao
	 */
	@Resource(name = "bookInfoDao")
	BookInfoDao bookInfoDao;

	/**
	 * 注入BookFileDao
	 */
	@Resource(name = "bookFileDao")
	private BookFileDao bookFileDao;

	/**
	 * 注入借阅记录Dao
	 */
	@Resource(name = "BorrowingDao")
	private BorrowingDao BorrowingDao;

	/**
	 * 注入归还记录Dao
	 */
	@Resource(name = "returnBookDao")
	private ReturnBookDao returnBookDao;

	/**
	 * 添加，更新图书资料
	 */
	@Override
	public void addBookInfo(String bookInfoParam, String LoginUser)
			throws Exception {
		try {
			// 将JSON转为对象
			BookInfo bookInfo = (BookInfo) DataConverter.convertJson2Object(
					bookInfoParam, BookInfo.class);
			// 如果Id不为空，则进行更新
			if (bookInfo.getId() != null) {
				// 得到改数据的审核状态
				BookInfo bi = new BookInfo();
				bi.setId(bookInfo.getId());
				bi = bookInfoDao.getBookInfo(bi);

				// 设置状态
				if (bookInfo.getInventoryNum().equals("0")) {
					bookInfo.setState("110319542370421");
				} else {
					bookInfo.setState("11031954066330412");
				}
				// 执行更新
				bookInfo.setEntry(LoginUser);
				bookInfo.setCreationDate(new Date());
				bookInfo.setCreator(LoginUser);
				bookInfo.setModifyDate(new Date());
				bookInfo.setModifier(LoginUser);
				if (bi != null) {
					bookInfo.setStatus(bi.getStatus() == null ? "" : bi
							.getStatus());
					bookInfo.setReviewers(bi.getReviewers() == null ? "" : bi
							.getReviewers());
				}
				bookInfoDao.mergeBookInfo(bookInfo);
			} else {
				// 只校验编码是否重复
				/*
				 * if(isCodeExist(bookInfo.getCode(),"0")){ Exception e = new
				 * Exception(); throw e; }
				 */
				// 如果Id为空，则进行添加
				bookInfo.setId(GenerateSequenceUtil.generateSequenceNo());
				// 设置状态
				if (bookInfo.getInventoryNum().equals("0")) {
					bookInfo.setState("110319542370421");
				} else {
					bookInfo.setState("11031954066330412");
				}
				// 执行添加
				bookInfo.setEntry(LoginUser);
				bookInfo.setStatus(BaseDataConstants.CATALOG_STATUS_INIT);
				bookInfoDao.addBookInfo(bookInfo);
			}
		} catch (Exception e) {
			LogHelper.ERROR.log(e.getMessage(), e);
			throw e;
		}

	}

	@Override
	public void modifyBookInfo(String BookInfo) throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * 删除图书资料
	 */
	@Override
	public String deleteBookInfo(String bookinfo) throws Exception {
		try {
			List<BookInfo> list = (List<BookInfo>) DataConverter
					.convertJson2List(bookinfo, BookInfo.class);
			for (int i = 0; i < list.size(); i++) {

				BookInfo data = bookInfoDao.getBookInfo(list.get(i));
				if (data != null) {
					if (data.getInventoryNum().equals(data.getTotal())
							&& data.getInventoryNum().equals(
									data.getCanBorrowing())) {
						// 删除图书资料
						bookInfoDao.deleteBookInfo(data);
						BookFile bookFile = new BookFile();
						bookFile.setBookId(data.getId());
						// 获取图书附件列表
						List<BookFile> filelist = bookFileDao
								.getFileByBookId(bookFile);
						for (BookFile bookFile2 : filelist) {
							// 删除附件
							bookFileDao.removeBookFile(bookFile2);
						}

					} else {
						return "删除失败，该资料外借中";
					}
				} else {
					return "删除失败,该资料不存在";
				}
			}
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(), e);
			return "系统异常请联系管理员";
		}
		return "删除成功";
	}

	/**
	 * 获取所有图书资料
	 */
	@Override
	public List<BookInfo> getBookInfo() throws Exception {
		try {
			// 获取所有图书资料
			return bookInfoDao.getBookInfo();
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(), e);
			// 抛出异常
			throw e;
		}
	}

	/**
	 * 获取一条图书资料
	 */
	@Override
	public BookInfo getBookInfo(String id) throws Exception {
		try {
			BookInfo bookInfo = new BookInfo();
			bookInfo.setId(id);
			// 根据id获取图书资料
			return bookInfoDao.getBookInfo(bookInfo);
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(), e);
			// 抛出异常
			throw e;
		}
	}

	/**
	 * 获取多条图书资料
	 */
	@Override
	public List<ViewBookInfo> getBookInfoList(String ids) throws Exception {
		List<ViewBookInfo> booklist = new ArrayList<ViewBookInfo>();
		try {
			if (StringUtils.isNotEmpty(ids)) {
				// 将用户String类型转成Plan对象
				String[] idsArray = ids.split(",");
				for (int i = 0; i < idsArray.length; i++) {
					String sql = "select * from V_Book_Info where id = '"+ idsArray[i]+"'";
					booklist.add(bookInfoDao.getBookInfoBySql(sql));
				}
			}
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(), e);
			// 抛出异常
			throw e;
		}
		return booklist;
	}

	/**
	 * 附件上传
	 * 
	 * @throws Exception
	 */
	@Override
	public void uploadFile(String bookInfoId, File upload, String uploadFileName)
			throws Exception {
		try {
			// 获取项目在服务器的路径
			String serverPath = "D:\\";
			// 新建一个路径，在最后以当前年月日新建一个文件夹
			String path = "\\upload\\bookinfo\\"
					+ ConvertUtil.convertDateToString("yyyyMMdd", new Date());
			// 创建文件夹
			FileUtil.CreateFolder(serverPath + path);
			InputStream is = new FileInputStream(upload);
			OutputStream os = new FileOutputStream(new File(serverPath + path,
					uploadFileName));
			// 截取文件后缀名
			String suffixName = uploadFileName.substring(uploadFileName
					.lastIndexOf("."));
			// 获取文件大小
			int size = is.available();
			String spaceSize = null;
			if (size > 0) {
				// 大小除以1024，计算出等于多少kb
				int spacesize = size / 1024;
				spaceSize = Integer.toString(spacesize) + "kb";
			} else {
				spaceSize = "";
			}
			byte[] buffer = new byte[500];
			int length = 0;
			while (-1 != (length = is.read(buffer, 0, buffer.length))) {
				os.write(buffer);
			}
			os.close();
			is.close();
			BookInfo bookInfo = new BookInfo();
			bookInfo.setId(bookInfoId);
			// 根据id获取图书资料
			bookInfo = bookInfoDao.getBookInfo(bookInfo);
			String bookName = bookInfo.getBookName();
			BookFile bookFile = new BookFile();
			bookFile.setId(GenerateSequenceUtil.generateSequenceNo());
			bookFile.setBookId(bookInfoId);
			bookFile.setBookName(bookName);
			bookFile.setFileName(uploadFileName);
			bookFile.setFilePath(path);
			bookFile.setSpaceSize(spaceSize);
			bookFile.setSuffixName(suffixName);
			// 添加文件数据
			bookFileDao.addBookFile(bookFile);
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(), e);
			// 抛出异常
			throw e;
		}
	}

	/**
	 * 新增借阅记录
	 */
	@Override
	public void addBorrowing(String BorrowingParam, BookInfo bookInfo,
			String surplus) {
		try {
			// 将JSON转成对象
			Borrowing borrowing = (Borrowing) DataConverter.convertJson2Object(
					BorrowingParam, Borrowing.class);
			// 添加Borrowing
			borrowing.setId(GenerateSequenceUtil.generateSequenceNo());
			// 设置库存数量
			bookInfo.setInventoryNum(surplus);
			// 设置状态
			if (surplus.equals("0")) {
				bookInfo.setState("110319542370421");
			} else {
				bookInfo.setState("11031954066330412");
			}
			// 执行借阅记录的添加
			BorrowingDao.addBorrowing(borrowing);
			// 更新图书资料表
			bookInfoDao.modifyBookInfo(bookInfo);
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(), e);
			// 抛出异常
			throw e;
		}
	}

	/**
	 * 图书归还
	 */
	@Override
	public void returnBook(String returnBookParam, BookInfo bookInfo,
			String returnNo) {
		try {
			// 将JSON转成对象
			ReturnBook returnBook = (ReturnBook) DataConverter
					.convertJson2Object(returnBookParam, ReturnBook.class);
			// 添加Borrowing
			returnBook.setId(GenerateSequenceUtil.generateSequenceNo());
			// 执行归还记录的添加
			returnBookDao.addReturnBook(returnBook);

			// 获取图书剩余数量
			String inventoryNum = bookInfo.getInventoryNum();
			int num = Integer.parseInt(inventoryNum);
			int returnNum = Integer.parseInt(returnNo);
			// 计算归还后的总数
			int sum = num + returnNum;
			String inventorySum = String.valueOf(sum);
			// 设置状态
			if (inventorySum.equals("0")) {
				bookInfo.setState("110319542370421");
			} else {
				bookInfo.setState("11031954066330412");
			}
			bookInfo.setInventoryNum(inventorySum);
			bookInfoDao.modifyBookInfo(bookInfo);
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(), e);
			// 抛出异常
			throw e;
		}
	}

	/**
	 * 根据条件模糊查询
	 * 
	 * @return
	 */
	@Override
	public List<ViewBookInfo> getList(String bookInfo, String publishDateTwo) {
		try {
			// 将Json转成对象
			ViewBookInfo bi = (ViewBookInfo) DataConverter.convertJson2Object(
					bookInfo, ViewBookInfo.class);
			// 获取图书名称
			String bookName = bi.getBookName();
			// 获取版本号
			String version = bi.getVersion();
			// 获取发布单位
			String publishUnit = bi.getPublishUnit();
			// 获取状态
			String state = bi.getState();
			// 获取一级子类
			String oneSubClass = bi.getOneSubClass();
			// 获取二级子类
			String twoSubClass = bi.getTwoSubClass();
			// 拼接sql
			String hql = "select * from V_Book_Info bi where 1=1";

			if ((bookName != null && bookName != "")
					|| (version != null && version != "")
					|| (publishUnit != null && publishUnit != "")) {
				hql += " and (bi.book_Name like '%" + bookName + "%'"
						+ " or  bi.version like '%" + version + "%'"
						+ " or  bi.publish_Unit like '%" + publishUnit + "%')";
			}

			/*
			 * if(version != null && version != ""){ hql +=
			 * " and bi.version like '%" + version + "%'"; } if(publishUnit !=
			 * null && publishUnit != ""){ hql += " and bi.publish_Unit like '%"
			 * + publishUnit + "%'"; }
			 */
			if (state != null && state != "") {
				hql += " and bi.state like '%" + state + "%'";
			}
			if (oneSubClass != null && oneSubClass != "") {
				hql += " and bi.one_SubClass like '%" + oneSubClass + "%'";
			}
			if (twoSubClass != null && twoSubClass != "") {
				hql += " and bi.two_SubClass like '%" + twoSubClass + "%'";
			}
			// 获取开始发布日期
			Date date = bi.getPublishDate();
			// 判断日期是否为空
			if (date != null) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String publishDate = sdf.format(date);
				if (publishDate != null && publishDate != "") {
					hql += " and bi.publish_Date >= to_date (' " + publishDate
							+ " ','yyyy-mm-dd')";
				}
			}
			if (publishDateTwo != null && publishDateTwo != "") {
				hql += " and bi.publish_Date <= to_date (' " + publishDateTwo
						+ " ','yyyy-mm-dd')";
			}
			return bookInfoDao.getList(hql);
		} catch (Exception e) {
			LogHelper.ERROR.log(e.getMessage(), e);
			throw e;
		}
	}

	/**
	 * 导出模版
	 * 
	 * @throws Exception
	 */
	@Override
	public void exportInfoTemplate() throws Exception {
		ExcelUtil<BookInfo> util1 = new ExcelUtil<BookInfo>(BookInfo.class);
		util1.getListToExcel(null, "图书资料", "图书资料导入模板");
	}

	/**
	 * 图书导入
	 * 
	 * @throws Exception
	 */
	@Override
	public String uploadInfoFile(File file, String fileName, String LoginUser)
			throws Exception {
		StringBuffer failMsg = new StringBuffer();
		try {
			// 创建文件流
			FileInputStream is = new FileInputStream(file);
			// 加载文件流
			XSSFWorkbook wbs = new XSSFWorkbook(is);
			// 读取第一个Sheet
			XSSFSheet firstSheet = wbs.getSheetAt(0);
			int rows = firstSheet.getLastRowNum();// 总行数
			int clos = firstSheet.getRow(0).getPhysicalNumberOfCells();// 总列数
			XSSFCell cell;// 产生单元格
			cell = firstSheet.getRow(0).createCell(clos);
			cell.setCellValue("导入结果");
			// 遍历行
			int failNum = 0;
			int count = 0;
			for (int i = 1; i < rows + 1; i++) {
				XSSFRow row = firstSheet.getRow(i);
				BookInfo bookInfo = new BookInfo();
				int j = 0;
				// 编码
				String code = row.getCell(j++) == null ? "" : CellValueUtil
						.getCellValue(row.getCell(j - 1)).trim();
				if (code == null || code.isEmpty()) {
					failMsg.append("<p>第" + (i + 1) + "行导入失败，失败原因为：编码不能为空！</p>");
					failNum = failNum + 1;
					continue;
				}
				bookInfo.setCode(code);
				// 图书名称
				String bookName = row.getCell(j++) == null ? "" : CellValueUtil
						.getCellValue(row.getCell(j - 1)).trim();
				if (bookName == null || bookName.isEmpty()) {
					failMsg.append("<p>第" + (i + 1)
							+ "行导入失败，失败原因为：图书名称不能为空！</p>");
					failNum = failNum + 1;
					continue;
				}
				bookInfo.setBookName(bookName);

				// 一级子类
				String oneSubClass = row.getCell(j++) == null ? ""
						: CellValueUtil.getCellValue(row.getCell(j - 1)).trim();
				if (oneSubClass == null || oneSubClass.isEmpty()) {
					failMsg.append("<p>第" + (i + 1)
							+ "行导入失败，失败原因为：一级子类不能为空！</p>");
					failNum = failNum + 1;
					continue;
				}
				String oneSubClassIId = checkSubClass(oneSubClass,
						"201610301150");
				bookInfo.setOneSubClass(oneSubClassIId);
				// 二级子类
				String twoSubClass = row.getCell(j++) == null ? ""
						: CellValueUtil.getCellValue(row.getCell(j - 1)).trim();
				if (twoSubClass == null || twoSubClass.isEmpty()) {
					failMsg.append("<p>第" + (i + 1)
							+ "行导入失败，失败原因为：二级子类不能为空！</p>");
					failNum = failNum + 1;
					continue;
				}
				String twoSubClassId = checkSubClass(twoSubClass,
						oneSubClassIId);
				bookInfo.setTwoSubClass(twoSubClassId);
				// 库存数量
				String inventoryNum = CellValueUtil.getCellValue(row
						.getCell(j++));
				if (inventoryNum == null || inventoryNum.isEmpty()) {
					failMsg.append("<p>第" + (i + 1)
							+ "行导入失败，失败原因为：库存数量不能为空！</p>");
					failNum = failNum + 1;
					continue;
				}
				bookInfo.setInventoryNum(inventoryNum);
				bookInfo.setTotal(inventoryNum);
				bookInfo.setCanBorrowing(inventoryNum);
				// 设置状态
				if (inventoryNum.equals("0")) {
					bookInfo.setState("110319542370421");
				} else {
					bookInfo.setState("11031954066330412");
				}
				// 出版年月
				String datePattern = "\\d{4}-\\d{2}";
				String publishDate = CellValueUtil.getCellValue(row
						.getCell(j++));
				if (publishDate != null) {
					if (!publishDate.matches(datePattern)) {
						failMsg.append("<p>第" + (i + 1)
								+ "行导入失败，失败原因为：出版年月日期格式不正确（例：1900-01）！</p>");
						failNum = failNum + 1;
						continue;
					}
					Date date = new SimpleDateFormat("yyyy-MM")
							.parse(publishDate);
					bookInfo.setPublishDate(date);
				} else {
					bookInfo.setPublishDate(null);
				}
				// 版本号
				String version = CellValueUtil.getCellValue(row.getCell(j++));
				bookInfo.setVersion(version);
				// 出版单位
				String publishUnit = CellValueUtil.getCellValue(row
						.getCell(j++));
				bookInfo.setPublishUnit(publishUnit);
				// 作者
				String author = CellValueUtil.getCellValue(row.getCell(j++));
				bookInfo.setAuthor(author);
				// 印刷年月
				String printDate = CellValueUtil.getCellValue(row.getCell(j++));
				if (printDate != null) {
					if (!printDate.matches(datePattern)) {
						failMsg.append("<p>第" + (i + 1)
								+ "行导入失败，失败原因为：印刷年月日期格式不正确（例：1900-01）！</p>");
						failNum = failNum + 1;
						continue;
					}
					Date date = new SimpleDateFormat("yyyy-MM")
							.parse(printDate);
					bookInfo.setPrintDate(date);
				} else {
					bookInfo.setPrintDate(null);
				}
				// 书号
				String bookNo = CellValueUtil.getCellValue(row.getCell(j++));
				bookInfo.setBookNo(bookNo);
				// 定价
				String price = CellValueUtil.getCellValue(row.getCell(j++));
				bookInfo.setPrice(price);
				// 存储位置
				String savePlace = CellValueUtil.getCellValue(row.getCell(j++));
				bookInfo.setSavePlace(savePlace);
				// 资料状态
				String fileStatus = CellValueUtil
						.getCellValue(row.getCell(j++));
				bookInfo.setFileStatus(fileStatus);
				// 电子数据下载权限
				String downPermission = CellValueUtil.getCellValue(row
						.getCell(j++));
				bookInfo.setDownPermission(downPermission);
				// 备注
				String remarks = CellValueUtil.getCellValue(row.getCell(j++));
				bookInfo.setRemarks(remarks);
				// 流转意见
				String flowSuggestion = CellValueUtil.getCellValue(row
						.getCell(j++));
				bookInfo.setFlowSuggestion(flowSuggestion);
				bookInfo.setId(GenerateSequenceUtil.generateSequenceNo());
				// 设置审核状态
				bookInfo.setStatus(BaseDataConstants.CATALOG_STATUS_INIT);
				// 执行更新
				bookInfo.setEntry(LoginUser);
				bookInfo.setCreationDate(new Date());
				bookInfo.setCreator(LoginUser);
				bookInfo.setModifyDate(new Date());
				bookInfo.setModifier(LoginUser);

				bookInfoDao.addBookInfo(bookInfo);
				count++;
			}
			failMsg.append("<p>共成功导入" + count + "条数据，失败" + failNum + "条！</p>");
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(), e);
			throw e;
		}
		return failMsg.toString();
	}

	/**
	 * 校验编码是否存在
	 */
	public Boolean isCodeExist(String code, String id) {
		BookInfo bookInfo = new BookInfo();
		bookInfo.setCode(code);
		bookInfo.setId(id);
		return bookInfoDao.isCodeExist(bookInfo);
	}

	/**
	 * 检查一级二级子类是否存在，不存在添加关联
	 */
	@Override
	public String checkSubClass(String subClassName, String parentId) {
		DatumCategory dc = new DatumCategory();
		dc.setCategoryName(subClassName);
		dc.setParentId(parentId);
		String id = bookInfoDao.checkSubClass(dc);
		return id;
	}

	@Override
	public BookInfo getBookInfoByCode(String code) {
		BookInfo b = new BookInfo();
		b.setCode(code);
		return bookInfoDao.getBookInfoByCode(b);
	}
}
