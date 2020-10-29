package com.ht.action.drawtask.plan;

import java.io.File;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.ht.action.base.BaseAction;
import com.ht.common.util.DataConverter;
import com.ht.common.util.FileUtil;
import com.ht.common.util.LogHelper;
import com.ht.common.util.LoginUtil;
import com.ht.front.pages.drawtask.plan.PlanPage;
import com.ht.persistence.model.background.authority.role.RoleUsers;
import com.ht.persistence.model.background.dicdata.basedata.BaseData;
import com.ht.persistence.model.background.organization.employee.User;
import com.ht.persistence.model.drawtask.plan.Plan;
import com.ht.persistence.model.drawtask.plan.PlanFile;
import com.ht.persistence.model.drawtask.plan.VPlan;
import com.ht.persistence.model.drawtask.taskbook.relation.TaskBookPlanRelation;
import com.ht.service.impl.background.dicdata.constants.BaseDataConstants;
import com.ht.service.inter.background.authority.role.RoleUsersService;
import com.ht.service.inter.background.dicdata.basedata.BaseDataService;
import com.ht.service.inter.background.organization.employee.UserService;
import com.ht.service.inter.catalog.area.CatalogAreaService;
import com.ht.service.inter.drawtask.plan.PlanService;
import com.ht.service.inter.drawtask.taskbook.relation.TaskBookPlanRelationService;


/** 
* @ClassName: PlanAction 
* @Description: 处理计划 
* @author penghao
* @date 2016年10月19日 下午4:30:01 
*  
*/
@SuppressWarnings("serial")
public class PlanAction extends BaseAction{
	
	/**
	 * 注入用户Service类
	 */
	@Resource(name = "userService")
	private UserService userService;
	@Resource(name = "taskBookPlanRelationService")
	private TaskBookPlanRelationService taskBookPlanRelationService;
	/**
	 * 注入RoleUserService类
	 */
	@Resource(name = "roleUsersService")
	private RoleUsersService roleUsersService;

    private File uploadPlan;
    private String uploadPlanFileName;
    private String uploadPlanContentType;
	private File upload;
	private String uploadFileName;
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
	
	public File getUploadPlan() {
		return uploadPlan;
	}

	public void setUploadPlan(File uploadPlan) {
		this.uploadPlan = uploadPlan;
	}

	public String getUploadPlanFileName() {
		return uploadPlanFileName;
	}

	public void setUploadPlanFileName(String uploadPlanFileName) {
		this.uploadPlanFileName = uploadPlanFileName;
	}

	public String getUploadPlanContentType() {
		return uploadPlanContentType;
	}

	public void setUploadPlanContentType(String uploadPlanContentType) {
		this.uploadPlanContentType = uploadPlanContentType;
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
	
	/**
	 * 注入计划的service
	 */
	@Resource(name="planService")
	private PlanService planService;
	
	/**
	 * 注入目录区域处理Service类
	 */
	@Resource(name = "catalogAreaService")
	private CatalogAreaService catalogAreaService;
	
	/**
	 * 注入基础数据处理Service类
	 */
	@Resource(name = "baseDataService")
	private BaseDataService baseDataService;
	
	/**
	 * 初始化计划页面
	 * @param
	 * @return
	 */
	public String index(){
		String type = getParam("type");
		String categoryId = getParam("categoryId");
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
			PlanPage page = PlanPage.getInstance();
			//获取页面节点字符串
			String listPage = page.getListPage(baseDataService,type,categoryId,jurisdiction);
			//将获取的节点字符串返回到前台页面
			request.setAttribute("html", listPage);
		} catch (Exception e) {
			LogHelper.ERROR.log(e.getMessage(), e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 初始化计划页面
	 * @return String 返回类型
	 */
	public String initEdit() {
		String id = getParam("id");
		String type = getParam("type");
		String categoryId = getParam("categoryId");
		try {
			//实例化目录页面生成对象
			PlanPage page = PlanPage.getInstance();
			//获取页面节点字符串
			String editPage = page.getEditPage(baseDataService,planService,id,type,categoryId);
			//将获取的节点字符串返回到前台页面
			request.setAttribute("html", editPage);
		} catch (Exception e) {
			LogHelper.ERROR.log(e.getMessage(), e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 初始化年度布局页面
	 * @return String 返回类型
	 */
	public String planAttachmentInit() {
		String year = getParam("year");
		try {
			//实例化目录页面生成对象
			PlanPage page = PlanPage.getInstance();
			//获取页面节点字符串
			String editPage = page.getPlanAttachmentPage(year);
			//将获取的节点字符串返回到前台页面
			request.setAttribute("html", editPage);
		} catch (Exception e) {
			LogHelper.ERROR.log(e.getMessage(), e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 获取计划列表
	 * @param
	 * @return list
	 */
	public void getPlanList(){
		String year = getParam("year");
		String categroryId = getParam("categoryId");
		try {
			//获取计划
			List<VPlan> planList = planService.getPlanList(categroryId,year);
			//成功返回数据客户端
			writeSuccessResult(planList);
		} catch (Exception e) {
			// 写入错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			//返回客户端错误消息
			writeFailResult(e.getMessage());
		}
	}
	
	/**
	 * 获取月计划计划列表
	 * @param
	 * @return list
	 */
	public void monthPlanList(){
		String date = getParam("date");
		String categroryId = getParam("categoryId");
		try {
			//获取计划
			List<VPlan> planList = planService.getMonthPlanList(categroryId,date);
			//成功返回数据客户端
			writeSuccessResult(planList);
		} catch (Exception e) {
			// 写入错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			//返回客户端错误消息
			writeFailResult(e.getMessage());
		}
	}
	
	/**
	 * 获取年度布局计划列表
	 * @param
	 * @return list
	 */
	public void getPlanAttachmentList(){
		String year = getParam("year");
		try {
			//获取计划
			List<PlanFile> planList = planService.getPlanAttachmentList(year);
			//成功返回数据客户端
			writeSuccessResult(planList);
		} catch (Exception e) {
			// 写入错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			//返回客户端错误消息
			writeFailResult(e.getMessage());
		}
	}
	
	/**
	 * 新增计划
	 */
	public void add(){
		String categoryId = getParam("categoryId");
		String type = getParam("type");
		try {
			//获取计划数据
			String param  = getParam("plan");
			planService.addPlan(param,categoryId,type);
			writeSuccessResult();
		} catch (Exception e) {
			// 写入错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			//返回客户端消息
			writeFailResult(e.getMessage());
		}
	}
	
	/**
	 * 删除计划数据
	 */
	@SuppressWarnings("unchecked")
	public void remove(){
		try {
			boolean flag=false;
			String mapNames="";
			//获取计划id。
			String ids = getParam("ids");
			//根据计划id获取任务书和计划关联列表。判断任务计划是否已经使用
			if (StringUtils.isNotEmpty(ids)) {
				// 将用户String类型转成Plan对象
				List<Plan> list = (List<Plan>) DataConverter.convertJson2List(ids,
						Plan.class);
				for (int i = 0; i < list.size(); i++) {
					List<TaskBookPlanRelation> planList = taskBookPlanRelationService.findListByPlanId(list.get(i).getId());
					if(planList!=null&&planList.size()>0){
						flag=true;
						Plan plan = planService.getPlanById(list.get(i).getId());
						if(StringUtils.isEmpty(mapNames)){
							mapNames=plan.getMapName();
						}else{
							mapNames+=","+plan.getMapName();
						}
					}
				}
			}
			if(flag){
				writeSuccessResult("任务计划"+mapNames+"已经被使用，不可删除!");
			}else{
				planService.delPlan(ids);
				// 成功返回客户端消息
				writeSuccessResult("删除成功");
			}
		} catch (Exception e) {
			// 写入错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 异常放回客户端消息
			writeFailResult(e.getMessage());
		}
		
	}
	
	/**
	 * 导入计划数据
	 * @param
	 * @return
	 */
	public void upload(){
		String type = getParam("type");
		String categoryId = getParam("categoryId");
		try {
			//导入目录明细
			String result = planService.addPlanByExcel(upload,type,categoryId);
			writeSuccessResult(result);
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
				this.fileName = baseData.getValue()+"导入模板.xls";
			}
				this.downloadStream = planService.getTemplate(type,fileName);
		} catch (Exception e) {
			// 写入错误日志
			LogHelper.ERROR.log(e.getMessage(), e);
			// 返回客户端错误消息
			writeFailResult(e.getMessage());
		}
		return SUCCESS;
	}
	
	/**
	 * 导出计划数据
	 * @param
	 * @return
	 */
	public String export(){
		String year = getParam("year");
		String type = getParam("type");
		String categoryId = getParam("categoryId");
		SimpleDateFormat sd = new SimpleDateFormat("yyyyMMdd");
		String str = sd.format(new Date());
		try {
			//获取目录名称
			BaseData baseData = baseDataService.getBaseData(categoryId);
			this.fileName = str+".xls";
			if(baseData != null){
				//表格名称
				this.fileName = baseData.getValue()+str+".xls";
			}
			//导出计划
			this.downloadStream = planService.export(year,type,categoryId,fileName);
		} catch (Exception e) {
			// 写入错误日志
			LogHelper.ERROR.log(e.getMessage(), e);
			// 返回客户端错误消息
			writeFailResult(e.getMessage());
		}
		return SUCCESS;
	}
	
	/**
	 * 资料上传
	 */
	public void uploadFile() throws Exception {
		try{
			String year = getParam("year");
			planService.uploadFile(year,uploadPlan,uploadPlanFileName);
			writeSuccessResult();
		}catch (Exception e){
			LogHelper.ERROR.log(e.getMessage());
			writeFailResult(e.getMessage());
		}
	}
	
	/**
	 * 文件下载
	 */
	public String download() throws Exception{
		try{
			// 获取文件Id
			String id = getParam("id");
			planService.downloadFile(id);
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	/**
	 *	检验文件是否存在
	 */
	public void checkFile() throws Exception{
		try{
			PlanFile pf = new PlanFile();
			// 获取文件Id
			String id = getParam("id");
			pf.setId(id);
			pf = planService.getPlanFileById(pf);
			if(pf!=null){
				// 获取项目所在服务器路径，将\\替换为/
				String serverPath = (FileUtil.ROOT_PATH).replaceAll("\\\\", "/");
				// 获取文件路径
				String filePath = (pf.getFilePath()).replaceAll("\\\\", "/");
				// 获取文件名称
				String fileName = pf.getFileName();
				File file = new File(serverPath+filePath+"/"+fileName);
			    if(file.isFile()){
					// 获取文件名称
			    	writeSuccessResult(id);
				 }else{
					writeSuccessResult("");
				 }
			}else{
				writeSuccessResult("");
			}
		}catch(Exception e){
			LogHelper.ERROR.log(e.getMessage(),e);
			writeFailResult(e.getMessage());
		}
	}
	
	/**
	 * 根据id查附件url
	 */
	public void getUrlById () throws Exception{
		try{
			// 获取文件Id
			String id = getParam("id");
			String url = planService.getUrlById(id);
			writeSuccessResult(url);
		}catch(Exception e){
			LogHelper.ERROR.log(e.getMessage(),e);
			writeFailResult(e.getMessage());
		}
	}
	
	
	
	/**
	 * 删除年计划部局
	 */
	public void planFilesRemove(){
		try {
			String ids = this.getParam("ids");
			planService.delPlanFilesRemove(ids);
			writeSuccessResult();
		} catch (Exception e) {
			LogHelper.ERROR.log(e.getMessage(),e);
			writeFailResult(e.getMessage());
		}
	}
	
}
