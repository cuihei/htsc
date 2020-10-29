package com.ht.action.system.dictionaries;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.ht.action.base.BaseAction;
import com.ht.common.util.DataConverter;
import com.ht.common.util.Download;
import com.ht.common.util.FileUtil;
import com.ht.common.util.LogHelper;
import com.ht.common.util.LoginUtil;
import com.ht.front.pages.system.dictionaries.DictionariesPage;
import com.ht.persistence.model.background.dicdata.basedata.BaseData;
import com.ht.persistence.model.background.dicdata.type.Type;
import com.ht.service.inter.background.application.ApplicationService;
import com.ht.service.inter.background.dicdata.basedata.BaseDataService;
import com.ht.service.inter.background.dicdata.type.TypeService;

/**
 * 字典WEB层
 * @author dou
 *
 */
@SuppressWarnings("serial")
public class DictionariesAction extends BaseAction{
	//注入基础数据baseDataService
	@Resource(name="baseDataService")
	BaseDataService baseDataService;
	
	// 注入应用资源service
	@Resource(name="applicationService")
	private ApplicationService applicationService;
	
	@Resource(name="typeService")
	private TypeService typeService;
	
	/**
	 * 首页面
	 * @return 首页面
	 */
	public String dictionariesIndex(){
		String html = DictionariesPage.getListNode();
		request.setAttribute("html", html);
		return SUCCESS;
	}
	
	/**
	 * 编辑
	 * @return
	 * @throws Exception 
	 */
	public String initDicEdit () throws Exception{
		// 获取页面返回的参数，资源ID
		String id = getParam("id");
		// 获取应用资源对象的List集合
		List<Type> list = null;
		try {
			list = typeService.getType();
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 返回错误信息
			writeFailResult(e.getMessage());
		}
		//将获取的节点字符串返回到前台页面
		request.setAttribute("html",DictionariesPage.getEditNode(list, baseDataService, id));
		return SUCCESS;
	}
	
	/**
	 * 资料海区编辑页面
	 * @return
	 * @throws Exception 
	 */
	public String initBooksEdit () throws Exception{
		// 获取页面返回的参数，资源ID
		String id = getParam("id");
		String flag = getParam("flag");
		//将获取的节点字符串返回到前台页面
		request.setAttribute("html",DictionariesPage.getBooksEditNode(baseDataService, id,flag));
		return SUCCESS;
	}
	
	/**
	 * 初始化资料所属海区页面
	 * */
	public String booksInit() {
		DictionariesPage d = new DictionariesPage();
		String flag = getParam("flag");
		//将获取的列表页面返回到前台页面
		request.setAttribute("html", d.getBooksNode(flag));
		return SUCCESS;
	}
	
	
	/**
	 * 新增基础数据数据
	 */
	public void addBaseData(){
		try {
			//获取baseDataParam对象
			String baseData = getParam("dictionaries");
			//将baseDataParam对象转换为json
			String baseDataParam = DataConverter.convertObject2Json(baseData);
			//{"code":"1","value":"1","type":"1","creator":"1","creationDate":"2016/09/29","modifier":"1","modifyDate":"2016/10/19"}
			//获取当前用户
			String userName = LoginUtil.getInstance().getLoginNo(request);
			//执行新增方法
			baseDataService.addBaseData(baseData,userName);
			writeSuccessResult();
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			//返回错误信息
			writeFailResult(e.getMessage());
		}
	}
	
	/**
	 * 更新basedata数据
	 */
	public void modifyBaseData(){
		try {
			// 获取baseData
			String baseData = getParam("dictionaries");
			//将baseData对象转换为json
			String baseDataParam = DataConverter.convertObject2Json(baseData);
			//获取当前用户
			String userName = LoginUtil.getInstance().getLoginNo(request);
			//执行修改方法
			baseDataService.modifyBaseDataById(baseData,userName);
			writeSuccessResult();
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			//返回错误信息
			writeFailResult(e.getMessage());
		}
	}
	
	/**
	 * 删除BaseData数据
	 */
	public void removeBaseData(){
		try {
			// 获取基础数据标识
			String id = getParam("id");
			baseDataService.delBaseData(id);
			writeSuccessResult();
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			//返回错误信息
			writeFailResult(e.getMessage());
		}
	}
	
	/**
	 * 获取基础数据列表
	 */
	public void getBaseData(){
		try {
			//执行查询方法
			List<BaseData> baseDataList = baseDataService.getBaseData();
			writeSuccessResult(baseDataList);
		} catch (Exception e) {
			e.printStackTrace();
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			//返回错误信息
			writeFailResult(e.getMessage());
		}
	}
	
	/**
	 * 根据id获取基础数据
	 */
	public void getBaseDataByKey(){
		try {
			String id = getParam("id");
			BaseData baseData = baseDataService.getBaseData(id);
			writeSuccessResult(baseData);
		} catch (Exception e) {
			// 写入错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 写失败信息到客户端
			writeFailResult(e.getMessage());
		}
	}
	
	
	/**
	 * 根据字典类型获取海区数据
	 */
	public void getBooksArea(){
		String flag = getParam("flag");
		List<BaseData> baseData = new ArrayList<BaseData>();
		try {
			if(flag.equals("1")){
				baseData = baseDataService.getBaseDataByTypeId("02281125502140137");
			}else{
				baseData = baseDataService.getBaseDataByTypeId("02281555137100012");
			}
			writeSuccessResult(baseData);
		} catch (Exception e) {
			// 写入错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 写失败信息到客户端
			writeFailResult(e.getMessage());
		}
	}
	
	
	/**
	 * 导出Excel
	 */
	public void exportExcel() throws Exception{
		List<BaseData> baseDatas = baseDataService.getBaseData();
		String[] col = {"基础数据编码","基础数据名称","类别名称"};
		String name = "字典管理";
		String fileName = name + System.currentTimeMillis();
		template("字典", col, Download.PATH, fileName, baseDatas);
		Download.download(respose,fileName);
	}
	
	/**
	 * 导出Excel
	 */
	public void exportBooksAreaExcel() throws Exception{
		String flag = getParam("flag");
		List<BaseData> baseDatas = new ArrayList<BaseData>();
		String name = null;
		String fileName = null;
		String[] col = {"海区编码","海区名称"};
		if(flag.equals("1")){
			baseDatas = baseDataService.getBaseDataByTypeId("02281125502140137");
			name = "海图资料所属海区管理";
			fileName = name + System.currentTimeMillis();
			template("海图资料所属海区", col, Download.PATH, fileName, baseDatas);
		}else{
			baseDatas = baseDataService.getBaseDataByTypeId("02281555137100012");
			name = "外业汇交资料所属海区管理";
			fileName = name + System.currentTimeMillis();
			template("外业汇交资料所属海区", col, Download.PATH, fileName, baseDatas);
		}
		
		
	
		
		Download.download(respose,fileName);
	}
	
	
	/**
	 * 导出人员信息模板
	 * 
	 * @param String sheetName sheet名称
	 * @param String[] title 第一行标题
	 * @param String url 下载后存放的位置
	 * @param String excelName excel的名称
	 * @return
	 */
	public static void template(String sheetName, String[] title, String url, String excelName,
			List<BaseData> lists) {
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
		//字体加粗
		font.setBoldweight(Font.BOLDWEIGHT_BOLD);
		//添加字体样式
		style.setFont(font);
		// 设置行高
		row.setHeightInPoints(20);
		// 设置第一行标题
		XSSFCell cell = null;
		for (int i = 0; i < title.length; i++) {
			cell = row.createCell(i);
			cell.setCellValue(title[i]);
			sheet.setColumnWidth(i, title[i].getBytes().length * 1 * 256);
			cell.setCellStyle(style);
		}
		
		
		//创建记录
		int size = lists.size();
		if (size>0) {
			for (int i = 1; i <= size; i++) {
				XSSFRow xssfRow = sheet.createRow(i);
				XSSFCell xssfCell = null;
				BaseData n = lists.get(i-1);
				for (int j = 0; j < title.length; j++) {
					
					if (j==0) {
						xssfCell = xssfRow.createCell(j);
						xssfCell.setCellValue(n.getCode());
					}
					
					if (j==1) {
						xssfCell = xssfRow.createCell(j);
						xssfCell.setCellValue(n.getValue());
					}
					
					if (j==2) {
						xssfCell = xssfRow.createCell(j);
						xssfCell.setCellValue(n.getTypeName());
					}
					xssfCell.setCellStyle(style);
				}
			}
		}
		// 创建文件夹
 		FileUtil.CreateFolder(url);
		// 创建一个文件
		File file = new File(url + excelName+".xls");
		try {
			file.createNewFile();
			FileOutputStream stream = new FileOutputStream(file);
			workbook.write(stream);
			stream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
