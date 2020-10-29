package com.ht.action.system.document.type;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.ServletActionContext;

import com.ht.action.base.BaseAction;
import com.ht.common.util.DataConverter;
import com.ht.common.util.ExcelFileUtil;
import com.ht.common.util.ExportExcel;
import com.ht.common.util.FileUtil;
import com.ht.common.util.GenerateSequenceUtil;
import com.ht.common.util.LogHelper;
import com.ht.front.pages.background.organization.employee.EmployeePage;
import com.ht.front.pages.system.document.type.ModelTypes;
import com.ht.persistence.model.background.organization.employee.User;
import com.ht.persistence.model.system.document.type.ModelType;
import com.ht.service.inter.system.document.type.ModelTypeService;

/**
 * ModelType模板类型类
 * @author 平子金
 */
@SuppressWarnings("serial")
public class ModelTypeAction extends BaseAction{
	/**
	 * 注入模板类型service
	 * */
	@Resource(name="modeltypeService")
	ModelTypeService modeltypeService;

	/**
	 * 初始化模板类型数据页面，返回成功列表页面
	 * */
	public String init(){
		ModelTypes modeltypes = new ModelTypes();
		//将获取的节点字符串返回到前台页面
		request.setAttribute("html", modeltypes.getListNode());
		return SUCCESS;
	}
	/**
	 * 初始化编辑模板数据页面，返回成功页面
	 * */
	public String editInit() {
		// 接受要修改的模板类型编号
		String id = getParam("id");
		ModelTypes modeltypes = new ModelTypes();
		//将获取的节点字符串返回到前台页面
		request.setAttribute("html", modeltypes.getEditNode(modeltypeService,id));
		return SUCCESS;
	}

	
	/**
	 * 新增ModelType数据
	 */
	public void addModelType(){
		try {
			// 获取前台传入参数
			String modeltype = getParam("modeltype");
			// 执行保存操作
			modeltypeService.addModelType(modeltype);
			writeSuccessResult(modeltype);
		} catch (Exception e) {
			writeFailResult(e.getMessage());
		}
	}
	
	/**
	 * 更新ModelType数据
	 */
	public void modifyModelType(){
		// 获取前台传入数据
		String modeltype = getParam("modeltype");
		try {
			modeltypeService.modifyModelType(modeltype);
			writeSuccessResult(modeltype);
		} catch (Exception e) {
			// 写入错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			writeFailResult(e.getMessage());
		}
	}
	
	/**
	 * 删除ModelType数据
	 */
	public void removeModelType(){
		// 获取ModelType标识
		String modeltype = getParam("modeltype");
		try {
			modeltypeService.delModelType(modeltype);
			writeSuccessResult();
		} catch (Exception e) {
			// 写入错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			writeFailResult(e.getMessage());
		}
	}
	
	/**
	 * 获取ModelType列表
	 */
	public void getModelType(){
		try {
			List<ModelType> modeltypeList = modeltypeService.getModelType();
			writeSuccessResult(modeltypeList);
		} catch (Exception e) {
			// 写入错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			writeFailResult(e.getMessage());
		}
	}
	
	/**
	 * 根据id获取ModelType
	 */
	public void getModelTypeByKey(){
		try {
			String id = getParam("id");
			ModelType modeltype = modeltypeService.getModelType(id);
			writeSuccessResult(modeltype);
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
		
		HttpServletRequest req = ServletActionContext.getRequest();
		HttpServletResponse resp = ServletActionContext.getResponse();
		// 获取ModelType标识
		String modeltype = getParam("modeltype");
		// 获取路径
		String folderPath = FileUtil.ROOT_PATH + "dzy\\export\\" + "modeltype";
		// 判断文件夹是否存在，不存在则创建
		if(!FileUtil.exists(folderPath)){
			FileUtil.CreateFolder(folderPath);
		}
		String path = folderPath+"\\"+("文档模板类型")+GenerateSequenceUtil.generateSequenceNo()+".xls";
		List<ModelType> list = (List<ModelType>) DataConverter.convertJson2List(modeltype, ModelType.class);
		
		String[] col = {"模板类型编号","模板类型名称"};
		String[] zd = {"id","name"};
		
		List<ModelType> modeltypeList = new ArrayList<ModelType>();
		for (int i = 0; i < list.size(); i++) {
			ModelType m = modeltypeService.getModelType(list.get(i).getId());
			modeltypeList.add(m);
		}
		if(modeltypeList.size() > 0){
			ExportExcel ee = new ExportExcel();
			boolean exportResult = ee.exportExcel("文档模板类型导出", col, zd, modeltypeList,path);
			if(exportResult){
				ExcelFileUtil.download(path, resp); 
			}
		}
	}
	
}