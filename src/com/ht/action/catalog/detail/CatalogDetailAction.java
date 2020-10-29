package com.ht.action.catalog.detail;

import java.io.File;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;

import com.ht.action.base.BaseAction;
import com.ht.common.util.LogHelper;
import com.ht.common.util.LoginUtil;
import com.ht.front.pages.catalog.detail.CatalogDetailPage;
import com.ht.persistence.model.background.authority.role.RoleUsers;
import com.ht.persistence.model.background.dicdata.basedata.BaseData;
import com.ht.persistence.model.background.organization.employee.User;
import com.ht.persistence.model.catalog.detail.CatalogDetail;
import com.ht.service.impl.background.dicdata.constants.BaseDataConstants;
import com.ht.service.inter.background.authority.role.RoleUsersService;
import com.ht.service.inter.background.dicdata.basedata.BaseDataService;
import com.ht.service.inter.background.organization.employee.UserService;
import com.ht.service.inter.catalog.area.CatalogAreaService;
import com.ht.service.inter.catalog.detail.CatalogDetailService;

/**
 * @ClassName: CatalogDetailAction
 * @Description: 目录详情的Action类
 * @author penghao
 * @date 2016年10月14日 下午4:41:02
 * 
 */
@SuppressWarnings("serial")
public class CatalogDetailAction extends BaseAction {
	
	//文件上传
	private File upload;
	//文件上传名称
	private String uploadFileName;
	//文件上传类型
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

	public void setUploadContextType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}
	
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
		 try{  
             ServletActionContext.getResponse().setHeader("charset","ISO8859-1");    
             return new String(this.fileName.getBytes(), "ISO8859-1");     
      } catch (Exception e) {   
         return "获取文件名出现了错误!";   
      } 
	}
	
	public void setFileName(String fileName) throws Exception {
		this.fileName = new String(fileName.getBytes("ISO8859-1"), BaseDataConstants.BASE_CODEING);
	}
	
	//导入成功后跳转参数传递
	private String typeName;

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	/**
	 * 注入目录明细处理Service类
	 */
	@Resource(name = "catalogDetailService")
	private CatalogDetailService catalogDetailService;
	
	/**
	 * 注入基础数据处理Service类
	 */
	@Resource(name = "baseDataService")
	private BaseDataService baseDataService;
	
	/**
	 * 注入目录区域处理Service类
	 */
	@Resource(name = "catalogAreaService")
	private CatalogAreaService catalogAreaService;
	
	/**
	 * 注入用户Service类
	 */
	@Resource(name = "userService")
	private UserService userService;
	
	/**
	 * 注入RoleUserService类
	 */
	@Resource(name = "roleUsersService")
	private RoleUsersService roleUsersService;

	/**
	 * 初始化目录明细页面 
	 * @return String 返回类型 
	 */
	public String index() {
		//菜单标记
		String type = getParam("type");
		//菜单类型id
		String categoryId = getParam("categoryId");
		//初始化状态
		String flag = getParam("status");
		try {
			String userNo = LoginUtil.getInstance().getLoginNo(request);
			List<User> users = userService.getUserByNo(userNo);
			String userId = null;
			if (users != null)
			{
				userId = users.get(0).getId();
			}
			// 根据当前登录人获取拥有的角色列表
			List<RoleUsers> roleList = roleUsersService.getRoleUsersByUserId(userId);
			List<String> jurisList=new ArrayList<String>(); 
			jurisList.add("11031903128270101");
			boolean jurisdiction = this.getUserJurisdiction(roleList, jurisList);
			//实例化页面对象
			CatalogDetailPage page = CatalogDetailPage.getInstance();
			//获取页面节点字符串
			String listPage = page.getListPage(flag,type,categoryId,jurisdiction);
			//将获取的节点字符串返回到前台页面
			request.setAttribute("html", listPage);
		} catch (Exception e) {
			LogHelper.ERROR.log(e.getMessage(), e);
			return ERROR;
		}
		return SUCCESS;
	}

	/**
	 * 初始化目录添加页面
	 * @return String 返回类型
	 */
	public String initEdit() {
		String id = getParam("id");
		String type = getParam("type");
		String creator = getParam("creator");
		String creationDate = getParam("creationDate");
		String categoryId = getParam("categoryId");
		String flag = getParam("flag");
		try {
			//实例化目录页面生成对象
			CatalogDetailPage page = CatalogDetailPage.getInstance();
			if(type == null){
				BaseData bd = baseDataService.getBaseData(categoryId);
				if(bd != null){
					if(bd.getValue().equals("海军图目录")){
						type = "2";
					}else if(bd.getValue().equals("规划图目录")){
						type = "1";
					}else if(bd.getValue().equals("港口航道图目录")){
						type = "3";
					}
				}
			}
			String editPage;
			if(type.contains(BaseDataConstants.MLGL_TYPE_GHMLT)){
				editPage = page.getEditPage(catalogAreaService,baseDataService,catalogDetailService,id,categoryId,type,flag);
			}else{
				//获取页面节点字符串
				editPage = page.getEditElsePage(catalogAreaService,baseDataService,catalogDetailService,id,categoryId,type,flag);
			}
			//将获取的节点字符串返回到前台页面
			request.setAttribute("html", editPage);
		} catch (Exception e) {
			LogHelper.ERROR.log(e.getMessage(), e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 初始化区域图片查看
	 * @return String 返回类型
	 */
	public String initViewImg() {
		String year = getParam("year");
		String id = getParam("id");
		String type = getParam("type");
		String categoryId = getParam("categoryId");
		try {
			//实例化目录页面生成对象
			CatalogDetailPage page = CatalogDetailPage.getInstance();
			String editPage = page.getViewPage(catalogDetailService,id,type,categoryId,year);
			//将获取的节点字符串返回到前台页面
			request.setAttribute("html", editPage);
		} catch (Exception e) {
			LogHelper.ERROR.log(e.getMessage(), e);
			return ERROR;
		}
		return SUCCESS;
	}

	/**
	 * 获取目录列表
	 * @return 目录列表
	 */
	public void getDetailList() {
		String year = getParam("year");
		String jsonParam = getParam("catalog");
		try {
			List<CatalogDetail> detailList = null;
			//获得当前用户UserNo
			String userNo = LoginUtil.getInstance().getLoginNo(request);
			List<User> userList = userService.getUserByNo(userNo);
			String userId = userList.get(0).getId();
			List<RoleUsers> roleUsersList = roleUsersService.getRoleUsersByUserId(userId);
			//获得当前用户的角色id
			String roleId = roleUsersList.get(0).getRoleId();
		//	if ("11031903128270101".equals(roleId)) {
				//获取目录列表
				detailList = catalogDetailService.getDetailList(year,jsonParam);
		/*	}else{
				detailList = catalogDetailService.getDetailList(year,jsonParam);
			}*/
			//移除图片文件，不放入列表中
			if(detailList.size()>0){
				for (CatalogDetail cl : detailList) {
					if(cl!=null&&cl.getArea()!=null){
						String areaImg = cl.getArea().getAreaImg();
						if(areaImg!=null){
							cl.getArea().setAreaImg("1");
						}
					}
				}
			}
			
			//返回客户端数据
			writeSuccessResult(detailList);
		} catch (Exception e) {
			// 写入错误日志
			LogHelper.ERROR.log(e.getMessage(), e);
			// 返回客户端错误消息
			writeFailResult(e.getMessage());
		}
	}

	/**
	 * 保存目录数据
	 * @param
	 * @return
	 */
	public void add() {
		try {
			String categoryId = getParam("categoryId");
			//获取目录数据
			String detailParam = getParam("detail");
			
			String str[] = detailParam.split(",");
			//保存目录数据
			catalogDetailService.addDetail(detailParam,categoryId);
			writeSuccessResult();
		} catch (Exception e) {
			// 写入错误日志
			LogHelper.ERROR.log(e.getMessage(), e);
			// 返回客户端错误消息
			writeFailResult(e.getMessage());
		}
	}

	/**
	 * 删除目录明细数据
	 */
	public void remove() {
		//主键id
		String ids = getParam("ids");
		try {
			catalogDetailService.delDetail(ids);
			//返回客户端数据
			writeSuccessResult();
		} catch (Exception e) {
			// 写入错误日志
			LogHelper.ERROR.log(e.getMessage(), e);
			// 返回客户端错误消息
			writeFailResult(e.getMessage());
		}
	}
	
	/**
	 * 导入目录数据
	 * @param
	 * @return
	 */
	public void upload(){
		String type = getParam("type");
		String categoryId = getParam("categoryId");
		try {
			//导入目录明细
			String uploadMsg = catalogDetailService.addDetailByExcel(upload,categoryId,type);
			writeSuccessResult(uploadMsg);
		} catch (Exception e) {
			// 写入错误日志
			LogHelper.ERROR.log(e.getMessage(), e);
			// 返回客户端错误消息
			writeFailResult(e.getMessage());
		}
	}
	
	/**
	 * 下载模板
	 * @param
	 * @return
	 */
	public String getTemplate(){
		String type = getParam("type");
		String categoryId = getParam("categoryId");
		try {
			//获取目录名称
			BaseData baseData = baseDataService.getBaseData(categoryId);
			if(baseData != null){
				//表格名称
				this.fileName = baseData.getValue()+"导入模板"+".xls";
			}
				downloadStream = catalogDetailService.getTemplate(type,fileName);
		} catch (Exception e) {
			// 写入错误日志
			LogHelper.ERROR.log(e.getMessage(), e);
			// 返回客户端错误消息
			writeFailResult(e.getMessage());
		}
		return SUCCESS;
	}
	
	/**
	 * 导出目录数据
	 * @param
	 * @return
	 */
	public String export(){
		String year = getParam("year");
		String type = getParam("type");
		String categoryId = getParam("categoryId");
		try {
			SimpleDateFormat sd = new SimpleDateFormat("yyyyMMddHHmmss");
			String str = sd.format(new Date());
			//获取目录名称
			BaseData baseData = baseDataService.getBaseData(categoryId);
			if(baseData != null){
				//表格名称
				this.fileName = baseData.getValue()+str+".xls";
			}
			//获得当前用户UserNo
			String userNo = LoginUtil.getInstance().getLoginNo(request);
			List<User> userList = userService.getUserByNo(userNo);
			String userId = userList.get(0).getId();
			List<RoleUsers> roleUsersList = roleUsersService.getRoleUsersByUserId(userId);
			//获得当前用户的角色id
			String roleId = roleUsersList.get(0).getRoleId();
			//导入目录明细
			downloadStream = catalogDetailService.export(year,type,categoryId,fileName,roleId,userNo);
		} catch (Exception e) {
			// 写入错误日志
			LogHelper.ERROR.log(e.getMessage(), e);
			// 返回客户端错误消息
			writeFailResult(e.getMessage());
		}
		return SUCCESS;
	}
}
