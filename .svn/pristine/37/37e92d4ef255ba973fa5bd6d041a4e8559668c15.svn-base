package com.ht.action.datum.correctionnoticebook;

import java.io.File;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import com.ht.action.base.BaseAction;
import com.ht.common.util.DataConverter;
import com.ht.common.util.LogHelper;
import com.ht.common.util.LoginUtil;
import com.ht.front.pages.datum.books.BooksPage;
import com.ht.front.pages.datum.correctionnoticebook.CorrectionNoticeBookPage;
import com.ht.persistence.model.background.authority.role.RoleUsers;
import com.ht.persistence.model.background.organization.employee.User;
import com.ht.persistence.model.complication.formprop.FormValue;
import com.ht.persistence.model.datum.correctionnoticebook.CorrectionNoticeBook;
import com.ht.persistence.model.datum.correctionnoticebook.CorrectionNoticeBookView;
import com.ht.persistence.model.datum.type.DatumCategory;
import com.ht.persistence.model.system.issue.YearIssue;
import com.ht.service.impl.background.dicdata.constants.BaseDataConstants;
import com.ht.service.inter.background.authority.role.RoleUsersService;
import com.ht.service.inter.background.dicdata.basedata.BaseDataService;
import com.ht.service.inter.background.organization.employee.UserService;
import com.ht.service.inter.complication.formprop.FormValueService;
import com.ht.service.inter.datum.bookinfo.BookFileService;
import com.ht.service.inter.datum.correctionnoticebook.CorrectionNoticeBookService;
import com.ht.service.inter.datum.type.DatumCategoryService;
import com.ht.service.inter.system.issue.YearIssueService;

@SuppressWarnings("serial")
public class CorrectionNoticeBookAction extends BaseAction {
	@Resource
	private YearIssueService yearIssueService;
	@Resource
	private FormValueService formValueService;
	String[] formIdArry ={"01161032213290058","0116104143110089","01161050123140100"};
	/**
	 * 注入海图service
	 */
	@Resource(name="correctionNoticeBookService")
	CorrectionNoticeBookService correctionNoticeBookService;
	
	/**
	 * 注入资料类别Service
	 */
	@Resource(name="datumcategoryService")
	DatumCategoryService datumcategoryService;
	
	/**
	 * 注入图书文件service
	 */
	@Resource(name="bookFileService")
	BookFileService bookFileService;
	
	//必须的参数，参数名与表单名相同
    private File uploadInfo;
    //必须的参数，格式：表单名+FileName，表示上传的文件名  
    private String uploadInfoFileName;
    //必须的参数，格式：表单名+ContentType，表示上传文件类型
    private String uploadInfoContentType;
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

	public File getUploadInfo() {
		return uploadInfo;
	}

	public void setUploadInfo(File uploadInfo) {
		this.uploadInfo = uploadInfo;
	}

	public String getUploadInfoFileName() {
		return uploadInfoFileName;
	}

	public void setUploadInfoFileName(String uploadInfoFileName) {
		this.uploadInfoFileName = uploadInfoFileName;
	}

	public String getUploadInfoContentType() {
		return uploadInfoContentType;
	}

	public void setUploadInfoContentType(String uploadInfoContentType) {
		this.uploadInfoContentType = uploadInfoContentType;
	}
	/**
	 * 注入用户service
	 * */
	@Resource(name = "userService")
	UserService userService;
	
	/**
	 * 注入roleUsersService
	 */
	@Resource(name = "roleUsersService")
	RoleUsersService roleUsersService;
	
	/**
	 * 注入基础数据baseDataService
	 */
	@Resource(name="baseDataService")
	BaseDataService baseDataService;
	
	//文件下载
		private InputStream downloadStream;
		//文件下载名称
		private String fileName;

		public InputStream getDownloadStream() {
			return downloadStream;
		}

		public void setDownloadStream(InputStream downloadStream) {
			this.downloadStream = downloadStream;
		}
		public String getFileName() {
			return fileName;
		}
		public void setFileName(String fileName) throws Exception {
			this.fileName = new String(fileName.getBytes("ISO8859-1"), BaseDataConstants.BASE_CODEING);
		}
		
	
	/**
	 * 初始化海图页面，返回成功列表页面
	 * @return
	 * @throws Exception 
	 */
	public String init() throws Exception{
		// 创建图书资料页面初始化类
		CorrectionNoticeBookPage page = new CorrectionNoticeBookPage();
		String userNo = LoginUtil.getInstance().getLoginNo(request);
		List<User> users = userService.getUserByNo(userNo);
		String userId = null;
		if (users != null)
		{
			userId = users.get(0).getId();
		}
		// 根据当前登录人获取拥有的角色列表
		List<RoleUsers> roleList = roleUsersService.getRoleUsersByUserId(userId);
		List<String> ids =  new ArrayList<String>();
		if (roleList.size()>0)
		{
			for (int i = 0; i < roleList.size(); i++)
			{
				ids.add(roleList.get(i).getRoleId());
			}
		}
		// 将获取的节点字符串返回给前台页面
		request.setAttribute("html", page.getListPage(ids));
		return SUCCESS;
	}
	
	/**
	 * 初始化海图编辑页面
	 * @return 
	 * @throws Exception 
	 */
	public String editInit() throws Exception{
		// 接收前台传来的id
		String id = getParam("id");
		CorrectionNoticeBookPage page = new CorrectionNoticeBookPage();
		// 将获取的节点字符串返回给前台页面
		request.setAttribute("html", page.getEditPage(id, correctionNoticeBookService,baseDataService));
		return SUCCESS;
	}
	
	
	/**
	 * 获取所有图书文件
	 * @return
	 */
	public void getCorrectionNoticeBooks(){
		try {
			// 执行查询操作
			List<CorrectionNoticeBookView> list = correctionNoticeBookService.getCorrectionNoticeBooks();
			// 返回客户端消息
			writeSuccessResult(list);
		} catch (Exception e) {
			// 写入错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 返回客户端错误消息
			writeFailResult(e.getMessage());
		}
	}
	
	public void addCorrectionNoticeBooks(){
		try {
			// 获取参数
			String correctionNoticeBooks = getParam("correctionNoticeBooks");
			// 执行添加
			correctionNoticeBookService.addCorrectionNotice(correctionNoticeBooks);
			// 成功返回消息
			writeSuccessResult(correctionNoticeBooks);
		} catch (Exception e) {
			// 错误返回消息
			writeFailResult(e.getMessage());
		}
	}
	public void changeState(){
		try {
			// 获取参数
			String correctionNoticeBooks = getParam("correctionNoticeBooks");
			String processInstId = getParam("processInstId");
			// 执行添加
			correctionNoticeBookService.changeState(correctionNoticeBooks);
			List<Map<String, Object>> noticeList = getNoticeList(processInstId, "new");
			// 成功返回消息
			writeSuccessResult(DataConverter.convertObject2Json(noticeList));
		} catch (Exception e) {
			// 错误返回消息
			writeFailResult(e.getMessage());
		}
	}
	
	/**
	 * 删除海图
	 */
	public void remove(){
		// 获取bookInfo标识
		String correctionNoticeBooks = getParam("correctionNoticeBooks");
		try {
			correctionNoticeBookService.deleteCorrectionNotice(correctionNoticeBooks);
			writeSuccessResult(correctionNoticeBooks);
		} catch (Exception e) {
			// 写入错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			writeFailResult(e.getMessage());
		}
	}
	
	/**
	 * 导出Excel
	 * @return 返回下载流成功
	 * @throws UnsupportedEncodingException 
	 * @throws Exception
	 */
	public String export() throws UnsupportedEncodingException{
		//表格名称
		SimpleDateFormat sd = new SimpleDateFormat("yyyyMMdd");
		String str = sd.format(new Date());
		this.fileName = "改正通告编辑资料"+str+".xls";
		fileName = new String(fileName.getBytes(), "ISO8859-1");
		try {
			// 执行导出操作
			downloadStream = correctionNoticeBookService.exportExcel();;
		} catch (Exception e) {
			// 写入错误日志
			LogHelper.ERROR.log(e.getMessage(), e);
			// 写失败信息到客户端
			writeFailResult(e.getMessage());
		}
		return SUCCESS;
	}
	/**
	 * 导出模板
	 * @throws Exception 
	 */
	public void exportInfoTemplate() throws Exception{
		correctionNoticeBookService.exportInfoTemplate();
	}
	
	/**
	 * 资料上传
	 */
	public void uploadFile() throws Exception {
		try{
			// 获取文件夹Id
			String booksId = getParam("booksId");
			correctionNoticeBookService.uploadFile(booksId,upload,uploadFileName);
			writeSuccessResult();
		}catch (Exception e){
			LogHelper.ERROR.log(e.getMessage());
			writeFailResult(e.getMessage());
		}
	}
	/**
	 * 初始化查看附件页面，返回成功列表页面
	 * @return
	 */
	public String booksFileInit() throws Exception{
		String booksId = getParam("id");
		String mark = getParam("mark");
		// 创建图书资料页面初始化类
		CorrectionNoticeBookPage ddata = new CorrectionNoticeBookPage();
		// 将获取的节点字符串返回给前台页面
		// 获取一级子类
		String parentId = "201610301150";

		String LoginUser = LoginUtil.getInstance().getLoginNo(request);
		List<User> loginUsers;
		try
		{
			loginUsers = userService.getUserByNo(LoginUser);
			String userId = null;
			if (loginUsers != null)
			{
				if (loginUsers.size() > 0)
				{
					userId = loginUsers.get(0).getId();
				}
			}
			// 判断用户是否是资料管理员
			boolean flag = false;
			List<RoleUsers> role = roleUsersService.getRoleUsersByUserId(userId);
			if (role != null)
			{
				if (role.size() > 0)
				{
					for (int i = 0; i < role.size(); i++)
					{
						String roleId = role.get(i).getRoleId();
						if (roleId.equals("11031915039750121")||roleId.equals("11031912049230118"))
						{
							flag = true;
						}
					}
				}

			}
			List<DatumCategory> list = datumcategoryService.getDatumCategoryByParentId(parentId);
			request.setAttribute("html", ddata.getFileListNode(userService,baseDataService,bookFileService,correctionNoticeBookService,booksId,list,mark,flag));
			return SUCCESS;
		}catch (Exception e)
		{
			return ERROR;
		}
	}
	/**
	 * 图书导入
	 */
	public void uploadInfoFile() throws Exception {
		try{
			String LoginUser = LoginUtil.getInstance().getLoginNo(request);
			String result = correctionNoticeBookService.uploadInfoFile(uploadInfo,uploadInfoFileName,LoginUser);
			writeSuccessResult(result);
		}catch (Exception e){
			LogHelper.ERROR.log(e.getMessage());
			writeFailResult(e.getMessage());
		}
	}
	public List<Map<String, Object>> getNoticeList(String processInstId,String type){
		List<FormValue> formValueList=new ArrayList<>();
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		List<CorrectionNoticeBookView> list = new ArrayList<CorrectionNoticeBookView>();
		try {
			if(formIdArry!=null&&formIdArry.length>0){
				for (String formId : formIdArry) {
					formValueList = formValueService.getFormValueByProcessInstId(processInstId, formId);
					if(formValueList!=null&&formValueList.size()>0){
						break;
					}
				}
			}
			String regx = "^[0-9]{4}年第（[0-9]+）期";
			Pattern pattern = Pattern.compile(regx);
			String value="";
			FormValue formValue=null;
			for (FormValue fV : formValueList) {
				value=fV.getPropValue()==null?"":fV.getPropValue();
				Matcher m = pattern.matcher(value);
				if(m.matches()){
					 value = value.substring(value.lastIndexOf("（")+1,value.lastIndexOf("）")).trim();
					 formValue=fV;
					 break;
				}
			}
			if("new".equals(type)){
				if(formValue!=null){
					List<YearIssue> yearIssueByIssue = yearIssueService.getYearIssueByIssue(formValue.getPropValue());
					if(yearIssueByIssue!=null&&yearIssueByIssue.size()>0){
						list = correctionNoticeBookService.getCorrectionNoticeBooksByCreateDate(yearIssueByIssue.get(0).getBeginDate(), yearIssueByIssue.get(0).getEndDate());
					}
					String propValue = formValue.getPropValue();
					if(Integer.parseInt(value)!=1){
						propValue = propValue.replace("（"+value+"）", "（"+(Integer.parseInt(value)-1)+"）");
						List<YearIssue> yearIssueByIssue2 = yearIssueService.getYearIssueByIssue(propValue);
						if(yearIssueByIssue2!=null&&yearIssueByIssue2.size()>0){
							List<CorrectionNoticeBookView> lastList1 = correctionNoticeBookService.getCorrectionNoticeBooksByCreateDate(yearIssueByIssue2.get(0).getBeginDate(), yearIssueByIssue2.get(0).getEndDate());
							if(lastList1!=null&&lastList1.size()>0){
								list.addAll(lastList1);
							}
						}
					}
				}
			}
			if("his".equals(type)){
				if(formValue!=null){
					String propValue = formValue.getPropValue();
					if(Integer.parseInt(value)!=1){
						propValue = propValue.replace("（"+value+"）", "（"+(Integer.parseInt(value)-1)+"）");
						List<YearIssue> yearIssueByIssue2 = yearIssueService.getYearIssueByIssue(propValue);
						if(yearIssueByIssue2!=null&&yearIssueByIssue2.size()>0){
							List<CorrectionNoticeBookView> lastList1 = correctionNoticeBookService.getCorrectionNoticeBooksByCreateDateAndState(yearIssueByIssue2.get(0).getBeginDate(), yearIssueByIssue2.get(0).getEndDate(),"部分采用");
							if(lastList1!=null&&lastList1.size()>0){
								list.addAll(lastList1);
							}
							List<CorrectionNoticeBookView> lastList2 = correctionNoticeBookService.getCorrectionNoticeBooksByCreateDateAndState(yearIssueByIssue2.get(0).getBeginDate(), yearIssueByIssue2.get(0).getEndDate(),"暂未采用");
							if(lastList2!=null&&lastList2.size()>0){
								list.addAll(lastList2);
							}
						}
					}
				}
			}
			if(list != null){
				if(list.size()>0){
					for (int i = 0; i < list.size(); i++) {
						Map<String, Object> map = DataConverter.convertBean2Map(list.get(i));
						result.add(map);
					}
				}
			}
			return result;
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return result;
	}
}
