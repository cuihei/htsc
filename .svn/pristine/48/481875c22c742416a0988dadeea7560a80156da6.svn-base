package com.ht.action.system.dictionariesType;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.ht.action.base.BaseAction;
import com.ht.common.util.Download;
import com.ht.common.util.FileUtil;
import com.ht.common.util.LogHelper;
import com.ht.front.pages.system.dictionariesType.DictionariesTypePage;
import com.ht.persistence.model.background.dicdata.type.Type;
import com.ht.service.inter.background.application.ApplicationService;
import com.ht.service.inter.background.dicdata.basedata.BaseDataService;
import com.ht.service.inter.background.dicdata.type.TypeService;

/**
 * 字典类型WEB层
 * @author dou
 *
 */
@SuppressWarnings("serial")
public class DictionariesTypeAction extends BaseAction{
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
	public String dictionariesTypeIndex(){
		String html = DictionariesTypePage.getListNode();
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
		//将获取的节点字符串返回到前台页面
		request.setAttribute("html",DictionariesTypePage.getEditNode(typeService, id));
		return SUCCESS;
	}
	
	
	/**
	 * 新增基础数据数据
	 */
	public void addBaseData(){
		try {
			//获取baseDataParam对象
			String data = getParam("dictionariesType");
			//执行新增方法
			Map<String, Object> result = typeService.addType(data);
			writeSuccessResult(result);
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
			String data = getParam("dictionariesType");
			//执行修改方法
			Map<String, Object> result = typeService.modifyTypeById(data);
			writeSuccessResult(result);
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
			String id = getParam("ids");
			typeService.delTypes(id);
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
			List<Type> types = typeService.getType();
			writeSuccessResult(types);
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
			Type type = typeService.getType(id);
			writeSuccessResult(type);
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
		List<Type> baseDatas = typeService.getType();
		String[] col = {"类别名称","创建者","创建时间","更新者","更新时间"};
		String name = "类别管理";
		String fileName = name + System.currentTimeMillis();
		template("字典类型", col, Download.PATH, fileName, baseDatas);
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
			List<Type> lists) {
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
				Type n = lists.get(i-1);
				for (int j = 0; j < title.length; j++) {
					
					if (j==0) {
						xssfCell = xssfRow.createCell(j);
						xssfCell.setCellValue(n.getName());
					}
					if (j==1) {
						xssfCell = xssfRow.createCell(j);
						xssfCell.setCellValue(n.getCreator());
					}
					if (j==2) {
						xssfCell = xssfRow.createCell(j);
						Date creationDate = n.getCreationDate();
						if (creationDate!=null) {
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
							xssfCell.setCellValue(sdf.format(creationDate));
						}else{
							xssfCell.setCellValue("");
						}
					}
					if (j==3) {
						xssfCell = xssfRow.createCell(j);
						xssfCell.setCellValue(n.getModifier());
					}
					if (j==4) {
						xssfCell = xssfRow.createCell(j);
						Date modifyDate = n.getModifyDate();
						if (modifyDate!=null) {
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
							xssfCell.setCellValue(sdf.format(modifyDate));
						}else {
							xssfCell.setCellValue("");
						}
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
