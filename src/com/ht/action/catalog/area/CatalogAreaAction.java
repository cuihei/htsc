package com.ht.action.catalog.area;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.ht.action.base.BaseAction;
import com.ht.common.util.DataConverter;
import com.ht.common.util.ExcelFileUtil;
import com.ht.common.util.ExportExcel;
import com.ht.common.util.FileUtil;
import com.ht.common.util.GenerateSequenceUtil;
import com.ht.common.util.LogHelper;
import com.ht.front.pages.catalog.area.CatalogAreaPage;
import com.ht.persistence.model.background.organization.employee.User;
import com.ht.persistence.model.catalog.area.CatalogArea;
import com.ht.persistence.model.datum.ExportModel;
import com.ht.persistence.model.datum.bookinfo.ViewBookInfo;
import com.ht.service.inter.background.dicdata.basedata.BaseDataService;
import com.ht.service.inter.background.organization.employee.UserService;
import com.ht.service.inter.catalog.area.CatalogAreaService;


/**
 * CatalogArea 区域管理类
 * @author yp
 */
@SuppressWarnings("serial")
public class CatalogAreaAction extends BaseAction{
	/**
	 * 注入用户service
	 * */
	@Resource(name = "userService")
	UserService userService;
	//文件上传
	private File myfiles;
	//文件上传名称
	private String myfilesFileName;
	//文件上传类型
	private String myfilesContentType;
	
	public File getMyfiles() {
		return myfiles;
	}

	public void setMyfiles(File myfiles) {
		this.myfiles = myfiles;
	}

	public String getMyfilesFileName() {
		return myfilesFileName;
	}

	public void setMyfilesFileName(String myfilesFileName) {
		this.myfilesFileName = myfilesFileName;
	}

	public String getMyfilesContentType() {
		return myfilesContentType;
	}

	public void setMyfilesContentType(String myfilesContentType) {
		this.myfilesContentType = myfilesContentType;
	}

	/**
	 * 区域管理service
	 * */
	@Resource
	private CatalogAreaService catalogAreaService;
	
	/**
	 * 区域管理service
	 * */
	@Resource
	private BaseDataService baseDataService;

	/**
	 * 初始化数据页面，返回成功列表页面
	 * */
	public String initCatalog(){
		CatalogAreaPage cap = new CatalogAreaPage();
		//将获取的节点字符串返回到前台页面
		request.setAttribute("html",cap.getListNode());
		return SUCCESS;
	}

	/**
	 * 初始化编辑目录区域管理页面，返回成功列表页面
	 * @throws Exception 
	 * */
	public String initEdit() throws Exception{
		String catalogAreaId = getParam("id");
		CatalogAreaPage cap = new CatalogAreaPage();
		//将获取的节点字符串返回到前台页面
		request.setAttribute("html",cap.getEditNode(catalogAreaService,baseDataService,catalogAreaId));
		return SUCCESS;
	}
	
	/**
	 * 获取区域管理list
	 */
	public void findList(){
		try {
			List<CatalogArea> list = catalogAreaService.getCatalogAreaList();
			writeSuccessResult(list);
		} catch (Exception e) {
			// 写入错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			//返回客户端错误消息
			writeFailResult(e.getMessage());
		}
	}
	
	/**
	 * 删除区域管理数据
	 */
	public void delete(){
		//获取删除id
		String catalogArea = getParam("catalogArea");
		try {
			catalogAreaService.del(catalogArea);
			writeSuccessResult();
		} catch (Exception e) {
			// 写入错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			writeFailResult(e.getMessage());
		}
	}
	
	
	/**
	 * 导出
	 * @throws Exception 
	 */
	public void export() throws Exception{
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		// 获取路径
		String folderPath = FileUtil.ROOT_PATH + "dzy\\export\\" + "catalogarea";
		// 判断文件夹是否存在，不存在则创建
		if(!FileUtil.exists(folderPath)){
			FileUtil.CreateFolder(folderPath);
		}
		String path = folderPath + "\\" + ("目录区域") + ".xls";
		// 数组的形式创建表格标题行
		String[] col = {"目录类别名称","目录区域名称","创建人","创建时间"};
		// 数组的形式创建表格值（对应实体类的字段）
		String[] zd = {"fild1","fild2","fild3","fild4"};
		
		List<CatalogArea> catalogAreaList = catalogAreaService.getCatalogAreaList();
		List<ExportModel> exportModelList =new ArrayList<ExportModel>();
		for (CatalogArea catalogArea : catalogAreaList) {
			if(catalogArea.getBaseData()!=null){
				catalogArea.setCategoryId(catalogArea.getBaseData().getValue());
			}else{
				catalogArea.setCategoryId("");
			}
			if(catalogArea.getCreator()!=null){
				List<User> userByNo = userService.getUserByNo(catalogArea.getCreator());
				if(userByNo.size()>0){
					catalogArea.setCreator(userByNo.get(0).getUserName());
				}
			}
			ExportModel exportModel=new ExportModel();
			exportModel.setFild1(catalogArea.getCategoryId());
			exportModel.setFild2(catalogArea.getAreaName());
			exportModel.setFild3(catalogArea.getCreator());
			if(catalogArea.getCreationDate()!=null){
				exportModel.setFild4(sdf.format(catalogArea.getCreationDate()));
			}else{
				exportModel.setFild4("");
			}
			exportModelList.add(exportModel);
		}
		
		if(exportModelList.size() > 0){
			ExportExcel<ExportModel> ee = new ExportExcel<ExportModel>();
			boolean exportResult = ee.exportExcel("目录区域", col, zd, exportModelList,path);
			if(exportResult){
				ExcelFileUtil.download(path, respose); 
			}
		}
	
	}
	

	/**
	 * 更新区域管理数据
	 * @throws Exception 
	 */
	public void update() throws Exception{
		try{
			String categoryId = getParam("catalogArea");
			catalogAreaService.modify(categoryId,myfiles,myfilesFileName);
			writeSuccessResult();
		} catch (Exception e) {
			// 写入错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			writeFailResult(e.getMessage());
		}
	}
}
