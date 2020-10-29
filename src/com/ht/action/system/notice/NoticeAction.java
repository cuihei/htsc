package com.ht.action.system.notice;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.ht.action.base.BaseAction;
import com.ht.common.util.DataConverter;
import com.ht.common.util.ExcelFileUtil;
import com.ht.common.util.ExportExcel;
import com.ht.common.util.FileUtil;
import com.ht.common.util.GenerateSequenceUtil;
import com.ht.common.util.LogHelper;
import com.ht.common.util.LoginUtil;
import com.ht.front.pages.system.notice.Notices;
import com.ht.persistence.model.background.dicdata.basedata.BaseData;
import com.ht.persistence.model.background.organization.employee.User;
import com.ht.persistence.model.background.organization.organization.Organization;
import com.ht.persistence.model.system.notice.Notice;
import com.ht.persistence.model.system.notice.UserNotice;
import com.ht.service.inter.background.dicdata.basedata.BaseDataService;
import com.ht.service.inter.background.organization.employee.UserService;
import com.ht.service.inter.background.organization.organization.OrganizationService;
import com.ht.service.inter.system.notice.NoticeService;
import com.ht.service.inter.system.notice.UserNoticeService;

/**
 * Notice 通知类
 * @author 平子金
 */
@SuppressWarnings("serial")
public class NoticeAction extends BaseAction{
	
	/**
	 * 注入通知service
	 * */
	@Resource(name="noticeService")
	NoticeService noticeService;
	
	/**
	 * 注入组织机构service
	 * */
	@Resource(name="organizationService")
	OrganizationService organizationService;
	
	/**
	 * 注入用户service
	 * */
	@Resource(name="userService")
	UserService userService;

	/**
	 * 注入用户service
	 * */
	@Resource(name="baseDataService")
	BaseDataService baseDataService;
	
	/**
	 * 注入用户通知关系service
	 * */
	@Resource(name="usernoticeService")
	UserNoticeService usernoticeService;
	
	
	/**
	 * 初始化通知数据页面，返回成功列表页面
	 * */
	public String init(){
		List<User> userList = null;
		List<Organization> orgList = null;
		try {
			userList = userService.getUserList();
			orgList = organizationService.getOrganization();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 实例化页面对象
		Notices page = Notices.getInstance();
		String flag = getParam("flag");
		// 获取页面节点字符串
		String listnode = page.getListNode(userService, userList,organizationService, orgList,flag);
		// 将获取的节点字符串返回到前台页面
		request.setAttribute("html", listnode);
		return SUCCESS;
	}
	
	/**
	 * 初始化编辑通知数据页面，返回成功页面
	 * */
	public String editInit() {
		// 接受要修改的通知编号
		String id = getParam("noticeId");
		Notices notices = new Notices();
		// 获取通知类型列表
		String typeId = "10301413441870000";
		List<BaseData> baseList = baseDataService.getBaseDataByTypeId(typeId);
		//将获取的节点字符串返回到前台页面
		request.setAttribute("html", notices.getEditNode(noticeService,id,baseList));
		return SUCCESS;
	}

	/**
	 * 新增通知
	 */
	public void addNotice(){
		try {
			// 获取前台传入参数
			String notice = getParam("notice");
			// 执行保存操作
			noticeService.addNotice(notice);
			writeSuccessResult(notice);
		} catch (Exception e) {
			writeFailResult(e.getMessage());
		}
	}
	
	/**
	 * 更新通知
	 */
	public void modifyNotice(){
		// 获取前台传入数据
		String notice = getParam("notice");
		try {
			noticeService.modifyNotice(notice);
			writeSuccessResult(notice);
		} catch (Exception e) {
			// 写入错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			writeFailResult(e.getMessage());
		}
	}
	
	/**
	 * 删除通知
	 */
	public void removeNotice(){
		// 获取Notice标识
		String notice = getParam("notice");
		try {
			noticeService.delNotice(notice);
			writeSuccessResult();
		} catch (Exception e) {
			// 写入错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			writeFailResult(e.getMessage());
		}
	}
	
	/**
	 * 获取Notice列表
	 */
	public void getNotice(){
		String flag = getParam("flag");
		List<Notice> noticeList = new ArrayList<Notice>();
		String LoginUser = LoginUtil.getInstance().getLoginNo(request);
		try {
			if(flag!=null){
				noticeList = noticeService.getNoticeViewByUser(LoginUser);
			}else{
				noticeList = noticeService.getNotice();
			}
			writeSuccessResult(noticeList);
		} catch (Exception e) {
			// 写入错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			writeFailResult(e.getMessage());
		}
	}
	
	/**
	 * 根据id获取Notice
	 */
	public void getNoticeByKey(){
		try {
			String id = getParam("id");
			Notice notice = noticeService.getNotice(id);
			writeSuccessResult(notice);
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
		String notice = getParam("notice");
		// 获取路径
		String folderPath = FileUtil.ROOT_PATH + "dzy\\export\\" + "modeltype";
		// 判断文件夹是否存在，不存在则创建
		if(!FileUtil.exists(folderPath)){
			FileUtil.CreateFolder(folderPath);
		}
		String path = folderPath + "\\" + ("通知信息") + GenerateSequenceUtil.generateSequenceNo() + ".xls";
		// 将Json转换为list
		List<Notice> list = (List<Notice>) DataConverter.convertJson2List(notice,Notice.class);
		// 数组的形式创建表格标题行
		String[] col = {"标题","描述","通知类型"};
		// 数组的形式创建表格值（对应实体类的字段）
		String[] zd = {"title","description","notice_Type"};
		
		List<Notice> noticeList = new ArrayList<Notice>();
		//基础数据List
		List<BaseData> baseDateList = new ArrayList<BaseData>();
		for (int i = 0; i < list.size(); i++) {
			Notice n = noticeService.getNotice(list.get(i).getId());
			String id = n.getNotice_Type();
			baseDateList = baseDataService.getBaseDataById(id);
			if(baseDateList!=null){
				for(int j = 0;j < baseDateList.size();j++){
					String state = baseDateList.get(j).getValue();
					n.setNotice_Type(state);
				}
			}
			noticeList.add(n);
		}
		if(noticeList.size() > 0){
			ExportExcel ee = new ExportExcel();
			boolean exportResult = ee.exportExcel("通知", col, zd, noticeList,path);
			if(exportResult){
				ExcelFileUtil.download(path, resp); 
			}
		}
	}
	
	/**
	 * 通知是否发布
	 */
	public void isRelease(){
		try {
			String noticeId = getParam("noticeId");
			List<UserNotice> result = usernoticeService.getUserNoticeByNoticeId(noticeId);
//			writeSuccessResult(result);
			respose.getWriter().write(DataConverter.convertObject2Json(result));
		} catch (Exception e) {
			LogHelper.ERROR.log(e.getMessage(),e);
			// 写失败信息到客户端
			writeFailResult(e.getMessage());
		}
	}
	
}