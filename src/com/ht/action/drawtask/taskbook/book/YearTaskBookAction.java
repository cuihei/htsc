package com.ht.action.drawtask.taskbook.book;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.ht.action.base.BaseAction;
import com.ht.common.util.LogHelper;
import com.ht.common.util.LoginUtil;
import com.ht.front.pages.drawtask.taskbook.YearTaskBookPage;
import com.ht.persistence.dao.inter.system.workflow.publish.VProcessDetailDao;
import com.ht.persistence.model.drawtask.plan.Plan;
import com.ht.persistence.model.drawtask.taskbill.TaskBill;
import com.ht.persistence.model.drawtask.taskbook.book.TaskBook;
import com.ht.persistence.model.drawtask.taskbook.book.TaskBookList;
import com.ht.service.inter.background.dicdata.basedata.BaseDataService;
import com.ht.service.inter.background.organization.organization.OrganizationService;
import com.ht.service.inter.drawtask.plan.PlanService;
import com.ht.service.inter.drawtask.taskbill.TaskBillService;
import com.ht.service.inter.drawtask.taskbook.book.TaskBookService;
import com.ht.service.inter.drawtask.taskbook.relation.TaskBookPlanRelationService;

/**
 * 处理编绘任务书Action类
 * 
 * @author PeNgHaO
 */

@SuppressWarnings("serial")
public class YearTaskBookAction extends BaseAction {

	@Resource(name = "taskBookService")
	private TaskBookService taskBookService;
	
	@Resource(name = "taskBillService")
	private TaskBillService taskBillService;

	@Resource(name = "organizationService")
	private OrganizationService organizationService;

	@Resource(name = "taskBookPlanRelationService")
	private TaskBookPlanRelationService taskBookPlanRelationService;
	
	@Resource
	private BaseDataService baseDataService;

	@Resource
	VProcessDetailDao vProcessDetailDao;
	
	/**
	 * 注入计划的service
	 */
	@Resource(name = "planService")
	private PlanService planService;
	// 必须的参数，参数名与表单名相同
	private File upload;
	// 必须的参数，格式：表单名+FileName，表示上传的文件名
	private String uploadFileName;
	// 必须的参数，格式：表单名+ContentType，表示上传文件类型
	private String uploadContentType;

	private TaskBook taskBook;

	public TaskBook getTaskBook() {
		return taskBook;
	}

	public void setTaskBook(TaskBook taskBook) {
		this.taskBook = taskBook;
	}

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
	 * 进入编绘任务书首页
	 * 
	 * @return
	 * @throws Exception 
	 */
	public String index() throws Exception {
		YearTaskBookPage page = new YearTaskBookPage();
		String html = page.getListPage(taskBookService,baseDataService);
		request.setAttribute("html", html);
		return SUCCESS;
	}

	/**
	 * 获取目录明细列表
	 */
	public void getTaskBookList() {
		try {
			List<TaskBookList> booklist = taskBookService.findListCorrectionNotice();
			writeSuccessResult(booklist);
		} catch (Exception e) {
			// 写入错误日志
			e.printStackTrace();
			LogHelper.ERROR.log(e.getMessage(), e);
			writeFailResult(e.getMessage());
		}
	}

	/**
	 * 回显任务计划书
	 */
	public void selectPlan() {
		try {
			String taskBookId = this.getParam("taskBookId");
			List<TaskBill> list = taskBillService.findListByTaskBookId(taskBookId);
			List<Plan> planList = new ArrayList<Plan>();
			if(list!=null){
				for (int i = 0; i < list.size(); i++) {
					Plan plan = list.get(i).getPlan();
					plan.setRevision(list.get(i).getRevision());
					planList.add(plan);
				}
			}
			writeSuccessResult(planList);
		} catch (Exception e) {
			// 写入错误日志
			e.printStackTrace();
			LogHelper.ERROR.log(e.getMessage(), e);
			writeFailResult(e.getMessage());
		}
	}

	/**
	 * 编辑编绘任务书
	 */
	public void update() {
		try {
			String userNo = LoginUtil.getInstance().getLoginNo(request);
			// 计划ID
			String planId = this.getParam("planId");
			// 版次
			String revision = this.getParam("revision");
			// 流程实例ID
			String processInstId = this.getParam("processInstId");
			// 流程任务ID
			String taskId = this.getParam("processInstId");
			String[] planIds = null;
			String[] revisions = null;
			if (StringUtils.isNotEmpty(planId)) {
				planIds = planId.split(",");
				revisions = revision.split(",");
			}
			taskBookService.modifyTaskBook(taskBook, planIds,revisions,processInstId,taskId,userNo);
			writeSuccessResult();
		} catch (Exception e) {
			writeFailResult(e.getMessage());
		}
	}

	/**
	 * 上传
	 */
	public void uploadFile() {
		try {
			String id = this.getParam("taskbookId");
			taskBookService.uploadFile(upload, uploadFileName, id);
			writeSuccessResult("success");
		} catch (Exception e) {
			writeSuccessResult("error");
			e.printStackTrace();
			// 写入错误日志
			LogHelper.ERROR.log(e.getMessage(), e);
		}
	}

	/**
	 * 前往编辑页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String editPage() throws Exception {
		// getEditPage
		YearTaskBookPage page = new YearTaskBookPage();
		String id = this.getParam("id");
		// 流程实例ID
		String processInstId = getParam("processInstId");
		// 流程任務ID
		String taskId = getParam("taskId");
		// 流程实例ID
		String processDefId = getParam("processDefId");
		// 流程任務ID
		String taskDefId = getParam("taskDefId");
		// 任务书类型
		String taskBookType = this.getParam("taskBookType");
		if (StringUtils.isEmpty(taskBookType)) {
			taskBookType = processDefId.split(":")[0];
		}
		String html = page.getEditPage(taskBookType, taskBookService,
				organizationService, id, processInstId,taskId,processDefId,taskDefId,vProcessDetailDao,taskBookPlanRelationService,baseDataService);
		request.setAttribute("html", html);
		request.setAttribute("processInstId", processInstId);
		request.setAttribute("taskId", taskId);
		return SUCCESS;
	}

	/**
	 * 前往详情页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String detailsPage() throws Exception {// getEditPage
		YearTaskBookPage page = new YearTaskBookPage();
		String id = this.getParam("id");
		TaskBook taskbook = taskBookService.findById(id);
		String html = page.getDetailsPage(taskbook, organizationService);
		request.setAttribute("html", html);
		return SUCCESS;
	}

	/**
	 * 删除任务书
	 */
	public String remove() {
		try {
			String id = "";
			taskBookService.delTaskBook(id);
		} catch (Exception e) {
			// 写入错误日志
			e.printStackTrace();
			LogHelper.ERROR.log(e.getMessage(), e);
			writeFailResult(e.getMessage());
		}
		return "";
	}

	/**
	 * 下载附件
	 */
	public String downLoad() throws Exception {
		try {
			String id = this.getParam("id");
			TaskBook taskbook = taskBookService.findById(id);
			// if(null!=taskbook){
			String enclosure = taskbook.getEnclosure();
			// if(null!=enclosure&&""!=enclosure){
			String[] split = enclosure.split("\\\\");
			String fileName = "";
			// if(split.length!=0){
			fileName = split[split.length - 1];
			// }
			HttpServletResponse response = this.respose;
			// response.setCharacterEncoding("utf-8");
			response.setContentType("multipart/form-data");
			response.setHeader("Content-Disposition", "attachment;fileName="
					+ java.net.URLEncoder.encode(fileName, "UTF-8"));
			// 获取项目在服务器的路径
			String serverPath = request.getServletContext().getRealPath("/");
			// 获取相应文件的流
			File file = new File(serverPath + taskbook.getEnclosure());
			// if(null!=file){
			// 设置文件长度
			response.setHeader("Content-Length", (int) file.length() + "");
			// IO流复制
			InputStream inputStream = new FileInputStream(file);
			OutputStream os = response.getOutputStream();
			int length;
			while ((length = inputStream.read()) != -1) {
				os.write(length);
			}
			// 释放资源
			inputStream.close();
			os.flush();
			os.close();
			return SUCCESS;
			// }
			// }
			// }
		} catch (Exception e) {
			// 写入错误日志
			// writeSuccessResult("暂无附件");
			e.printStackTrace();
			LogHelper.ERROR.log(e.getMessage(), e);
			return "loadError";
		}
	}
}
