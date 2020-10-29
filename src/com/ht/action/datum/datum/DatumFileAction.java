package com.ht.action.datum.datum;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import com.ht.action.base.BaseAction;
import com.ht.common.util.DataConverter;
import com.ht.common.util.LogHelper;
import com.ht.common.util.LoginUtil;
import com.ht.exception.DBException;
import com.ht.front.css.CssClass;
import com.ht.front.model.I;
import com.ht.front.pages.datum.file.DatumFilePage;
import com.ht.persistence.model.datum.datum.Borrowing;
import com.ht.persistence.model.datum.datum.DatumFile;
import com.ht.persistence.model.datum.datum.VDatumFile;
import com.ht.persistence.model.datum.type.DatumCategory;
import com.ht.service.impl.system.workflow.task.ProcessTypeEnum;
import com.ht.service.inter.datum.datum.BorrowingService;
import com.ht.service.inter.datum.datum.DatumFileService;
import com.ht.service.inter.datum.datum.VDatumFileService;
import com.ht.service.inter.datum.type.DatumCategoryService;
import com.ht.service.inter.system.workflow.publish.PublishService;

/**
 * 资料文件action
 * @author zyd
 *
 */
@SuppressWarnings("serial")
public class DatumFileAction extends BaseAction {
	
	/**
	 * 注入DatumFileService
	 */
	@Resource(name="datumFileService")
	DatumFileService datumFileService;

	/**
	 * 注入DatumCatagoryService
	 */
	@Resource(name="datumcategoryService")
	DatumCategoryService datumcategoryService;
	
	/**
	 * 注入BorrowingService
	 */
	@Resource(name="borrowingService")
	BorrowingService BorrowingService;
	
	/**
	 * 注入BorrowingService
	 */
	@Resource(name="vdatumFileService")
	VDatumFileService vdatumFileService;
	
	/**
	 * 流程发布serivce
	 */
	@Resource
	PublishService publishService;

	
	//必须的参数，参数名与表单名相同
    private File upload;
    //必须的参数，格式：表单名+FileName，表示上传的文件名  
    private String uploadFileName;
    //必须的参数，格式：表单名+ContentType，表示上传文件类型
    private String uploadContentType;

    
	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	/**
	 * 初始化资料文件页面，返回成功列表页面
	 * @return
	 */
	public String init(){
		// 创建资料维护页面初始化类
		DatumFilePage ddata = new DatumFilePage();
		// 将获取的节点字符串返回给前台页面
		request.setAttribute("html", ddata.getListNode());
		return SUCCESS;
	}
	
	/**
	 * 初始化编辑资料文件数据页面，返回成功页面
	 * @throws Exception 
	 * */
	public String editInit() throws Exception {
		List<DatumFile> datumFileList = datumFileService.getDatumFile();
		List<DatumCategory> datumCatagoryList = datumcategoryService.getDatumCategory();
		
		// 接收前台传的Id
		String id = getParam("id");
		// 接收前台传的修改标识
		String flag = getParam("flag");
		DatumFilePage datumFilePage = new DatumFilePage();
		//将获取的节点字符串返回到前台页面
		request.setAttribute("html", datumFilePage.getEditNode(flag,datumFileService, datumCatagoryList, id, datumFileList));
		return SUCCESS;
	}
	
	/**
	 * 初始化借阅资料页面
	 * @throws Exception 
	 */
	public String borrowingInit() throws Exception{
		// 获取文件Id
		String fileId = getParam("id");
		// 获取文件夹Id
		String datumCategoryId = getParam("datumCategoryId");
		// 获取归还标识
		String flag = getParam("flag");
		DatumFilePage datumFilePage = new DatumFilePage();
		Borrowing borr = BorrowingService.getBorrowingByFileId(fileId);
		
		DatumFile df = datumFileService.getDatumFile(fileId);
		String borrowBookName = df.getEntityFileName();
		//将获取的节点字符串返回到前台页面
		request.setAttribute("html", datumFilePage.getBorrowingNode(datumFileService, fileId, borrowBookName,datumCategoryId,flag,borr));
		return SUCCESS;
	}
	
	/**
	 * 新增借阅
	 * @throws Exception
	 */
	public void addBorrowing() throws Exception {
		// 获取前台传入参数
		String borrowing = getParam("borrowing");
		// 获取图书编号
		String borrowBookNo = getParam("borrowBookNo");
		// 获取归还数量
		String returnNo = getParam("returnNo");
		// 获取当前图书数量
		DatumFile datumFile = datumFileService.getDatumFile(borrowBookNo);
		// 流程key
		String processDefKey = ProcessTypeEnum.DATA_BORROWING.toString();
		// 当前用户
		String LoginUser = LoginUtil.getInstance().getLoginNo(request);
		try {
			//发布借阅流程
			publishService.publishProcess(LoginUser, processDefKey);
			BorrowingService.addBorrowing(borrowing,datumFile,returnNo);
		} catch (DBException e) {
			e.printStackTrace();
		}
		// 返回客户端消息
		writeSuccessResult(borrowing);
	}
	
	/**
	 * 新增资料文件数据
	 * @throws IOException 
	 * @throws Exception 
	 */
	public String addDatumFile() throws IOException{
		try {
 			String datumFile = getParam("datum");
	        datumFileService.addDatumFile(datumFile,upload,uploadFileName);
	        // 返回客户端消息
			writeSuccessResult();
		} catch (Exception e) {
			writeFailResult(e.getMessage());
		}
		return SUCCESS;
	}
	
	/**
	 * 更新资料文件数据
	 */
	public void modifyDatumFile(){
		// 接收前台传入的参数
		String datumFile = getParam("datumfile");
		// 将对象转成JSON
		String datumFileParam = DataConverter.convertObject2Json(datumFile);
		try {
			datumFileService.modifyDatumFile(datumFileParam);
			writeSuccessResult();
		} catch (Exception e) {
			writeFailResult(e.getMessage());
		}
	}
	
	/**
	 * 删除资料文件数据
	 */
	public void removeDatumFile(){
		// 获取资料文件标识
		String datumfileId = getParam("datumfileId");
		try {
			datumFileService.deleteDatumFile(datumfileId);
			writeSuccessResult();
		} catch (Exception e) {
			LogHelper.ERROR.log(e.getMessage());
			writeFailResult(e.getMessage());
		}
	}
	
	/**
	 * 获取资料文件列表
	 */
	public void getDatumFile(){
		try {
			List<DatumFile> datumFileList = datumFileService.getDatumFile();
			respose.getWriter().write(DataConverter.convertObject2Json(datumFileList));
		} catch (Exception e) {
			LogHelper.ERROR.log(e.getMessage());
			writeFailResult(e.getMessage());
		}
	}
	
	/**
	 * 根据id获取资料文件数据
	 */
	public void getDatumFileByKey(){
		try {
			// 获取资料文件标识
			String id = getParam("id");
			DatumFile df = datumFileService.getDatumFile(id);
			writeSuccessResult(df);
		} catch (Exception e) {
			LogHelper.ERROR.log(e.getMessage());
			writeFailResult(e.getMessage());
		}
	}
	
	/**
	 * 文件下载
	 */
	public String execute() throws Exception{
		try{
			// 获取文件Id
			String datumfileId = getParam("datumfileId");
			datumFileService.downloadFile(datumFileService,datumfileId);
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 初始化文件夹页面，返回成功页面
	 * @throws Exception 
	 * */
	public String viewFolderInit() throws Exception {
		// 接收前台传的Id
		String id = getParam("id");
		DatumFilePage datumFilePage = new DatumFilePage();
		//将获取的节点字符串返回到前台页面
		request.setAttribute("html", datumFilePage.getViewNode(datumFileService, id));
		return SUCCESS;
	}
	
	/**
	 * 根据文件夹Id获取文件
	 */
	public void getFileByCategoryId(){
		try {
			// 获取文件夹标识
			String categoryId = getParam("categoryId");
			List<DatumFile> df = datumFileService.getIsFile(categoryId);
			writeSuccessResult(df);
		} catch (Exception e) {
			LogHelper.ERROR.log(e.getMessage());
			writeFailResult(e.getMessage());
		}
	}
	
	/**
	 * 初始化文件属性页面，返回成功页面
	 * @return
	 * @throws Exception 
	 */
	public String fileAttr() throws Exception {
		// 接收前台传的Id
		String id = getParam("datumfileId");
		DatumFilePage datumFilePage = new DatumFilePage();
		DatumFile datumFile = datumFileService.getDatumFile(id);
		//将获取的节点字符串返回到前台页面
		request.setAttribute("html", datumFilePage.getFileAttrNode(datumFileService, datumFile));
		return SUCCESS;
	}
	
	/**
	 * 资料上传
	 */
	public void uploadFile() throws Exception {
		try{
			// 获取文件夹Id
			String categoryId = getParam("categoryId");
			datumFileService.uploadFile(categoryId,upload,uploadFileName);
			writeSuccessResult();
		}catch (Exception e){
			LogHelper.ERROR.log(e.getMessage());
			writeFailResult(e.getMessage());
		}
	}
	
	/**
	 * 获取资料文件树
	 */
	public void getDatumFileTree(){
		try {
			List<VDatumFile> datumFileList = vdatumFileService.getDatumFile();
			for (int i = 0; i < datumFileList.size(); i++) {
				VDatumFile v = datumFileList.get(i);
				if(v.getFileType().equals("文件夹")){
					CssClass foldCss = new CssClass("fa fa-folder");
					I foldI = I.getInstance(foldCss);
					v.setFileName(foldI.getNode()+"&nbsp;"+v.getFileName());
				}else if(v.getFileType().equals("实体文档")){
					CssClass foldCss = new CssClass("fa fa-book");
					I foldI = I.getInstance(foldCss);
					v.setFileName(foldI.getNode()+"&nbsp;"+v.getFileName());
				}else if(v.getFileType().equals("电子文档")){
					CssClass foldCss = new CssClass("fa fa-file-text");
					I foldI = I.getInstance(foldCss);
					v.setFileName(foldI.getNode()+"&nbsp;"+v.getFileName());
				}
			}
			writeSuccessResult(datumFileList);
		} catch (Exception e) {
			LogHelper.ERROR.log(e.getMessage());
			writeFailResult(e.getMessage());
		}
	}
}
