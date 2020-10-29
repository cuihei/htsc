package com.ht.action.system.workflow.taskconfig;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import com.ht.action.base.BaseAction;
import com.ht.common.util.DataConverter;
import com.ht.common.util.LogHelper;
import com.ht.front.pages.system.workflow.taskconfig.TaskConfigurationPage;
import com.ht.persistence.model.background.dicdata.basedata.BaseData;
import com.ht.persistence.model.complication.form.Form;
import com.ht.persistence.model.system.workflow.process.ProcessFormProp;
import com.ht.service.impl.background.dicdata.constants.BaseDataConstants;
import com.ht.service.inter.background.dicdata.basedata.BaseDataService;
import com.ht.service.inter.background.dicdata.type.TypeService;
import com.ht.service.inter.complication.form.FormService;
import com.ht.service.inter.system.workflow.process.ProcessFlowService;
import com.ht.service.inter.system.workflow.process.ProcessFormService;
import com.ht.service.inter.system.workflow.process.ProcessLogDetailService;
import com.ht.service.inter.system.workflow.process.ProcessLogService;
import com.ht.service.inter.system.workflow.process.ProcessStatusService;
import com.ht.service.inter.system.workflow.process.ProcessTaskRelationService;
import com.ht.service.inter.system.workflow.taskconfig.TaskConfigurationService;
import com.ht.workflow.common.TaskDef;
import com.ht.workflow.exception.WorkflowException;

/**
 * 流程任务配置外部访问类
 * @author 王有为
 * @date 2016/10/26
 */
public class TaskConfigurationAction extends BaseAction{
	
	/**
	 * 注入service
	 * */
	@Resource(name="processFormService")
	ProcessFormService processFormService;
	
	/**
	 * 注入service
	 */
	@Resource(name="processFlowService")
	ProcessFlowService processFlowService;
	
	/**
	 * 注入service
	 */
	@Resource(name="processLogService")
	ProcessLogService processLogService;
	
	/**
	 * 注入service
	 */
	@Resource(name="processLogDetailService")
	ProcessLogDetailService processLogDetailService;
	
	/**
	 * 注入service
	 * */
	@Resource(name="processStatusService")
	ProcessStatusService processStatusService;
	
	/**
	 * 工作流服务类
	 */
	@Resource(name="taskConfigurationService")
	TaskConfigurationService service;
	
	/**
	 * 工作表单服务类
	 */
	@Resource(name="formService")
	FormService formService;
	
	/**
	 * 注入基础数据baseDataService
	 */
	@Resource(name="baseDataService")
	BaseDataService baseDataService;
	
	/**
	 * 注入数据typeService
	 */
	@Resource(name="typeService")
	TypeService typeService;
	
	@Resource(name="processTaskRelationService")
	ProcessTaskRelationService processTaskRelationService;
	
	/**
	 * 类标识
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 进入配置页面
	 */
	public String init(){
		// 流程定义ID
		String processDefId = getParam("processDefId");
		TaskConfigurationPage page = new TaskConfigurationPage();
		request.setAttribute("html", page.getPage(processDefId));
		return SUCCESS;
	}
	
	/**
	 * 获取任务列表
	 */
	public void getTaskList(){
		// 流程定义ID
		String processDefId = getParam("processDefId");
		try {
			List<TaskDef> taskList = service.getTaskListByProcessDefId(processDefId);
			writeSuccessResult(taskList);
		} catch (WorkflowException e) {
			writeFailResult(e.getMessage());
		}
	}
	
	/**
	 * 根据表单类型获取子表单
	 * @throws Exception 
	 */
	public void getChildeForm () throws Exception{
		respose.setContentType("application/json;charset=utf-8");
		//获取状态父ID
		String baseDataId = getParam("formId");
		List<Form> forms = 
				formService.getFormByBaseDataId(baseDataId);
		String json = DataConverter.convertObject2Json(forms);
		respose.getWriter().write(json);
	}
	
	/**
	 * 根据类型ID获取子状态
	 * @throws IOException 
	 */
	public void getChildeStatus () throws IOException{
		respose.setContentType("application/json;charset=utf-8");
		//获取状态父ID
		String typeId = getParam("typeId");
		//获取所有状态
		List<BaseData> status = 
				baseDataService.getBaseDataByTypeId(typeId);
		String json = DataConverter.convertObject2Json(status);
		respose.getWriter().write(json);
	}
	
	/**
	 * 选择表单
	 * @return
	 * @throws Exception 
	 */
	public String initFormEdit () throws Exception{
		// 获取页面返回的参数
		String processDefId = getParam("processDefId");
		String processDefKey = getParam("processDefKey");
		String taskDefId = getParam("taskDefId");
		String taskName = getParam("taskName");
		taskName = new String(taskName.getBytes("ISO8859-1"), BaseDataConstants.BASE_CODEING);
		TaskConfigurationPage page = new TaskConfigurationPage();
		//将获取的节点字符串返回到前台页面
		request.setAttribute("html",page.getFormNode(formService,baseDataService,processFormService,processDefId,processDefKey,taskDefId,taskName));
		return SUCCESS;
	}
	
	/**
	 * 关联表单
	 * @return
	 * @throws Exception
	 */
	public void formEdit () throws Exception{
		try {
			String processForm = getParam("processForm");
			//执行新增方法
			processFormService.addOrUodateProcessForm(processForm);
			writeSuccessResult();
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			//返回错误信息
			writeFailResult(e.getMessage());
		}
	}
	
	/**
	 * 选择状态
	 * @return
	 * @throws Exception 
	 */
	public String initStatusEdit () throws Exception{
		// 获取页面返回的参数
		String processDefId = getParam("processDefId");
		String processDefKey = getParam("processDefKey");
		String taskDefId = getParam("taskDefId");
		TaskConfigurationPage page = new TaskConfigurationPage();
		//将获取的节点字符串返回到前台页面
		request.setAttribute("html",page.getStatusNode(baseDataService ,typeService,processStatusService,
				processDefId,processDefKey,taskDefId));
		return SUCCESS;
	}
	
	/**
	 * 选择状态
	 * @return
	 * @throws Exception 
	 */
	public String initAssociatedEdit() throws Exception{
		// 获取页面返回的参数
		String processDefId = getParam("processDefId");
		String processDefKey = getParam("processDefKey");
		String taskDefId = getParam("taskDefId");
		TaskConfigurationPage page = new TaskConfigurationPage();
		//将获取的节点字符串返回到前台页面
		request.setAttribute("html",page.getAssociatedNode(service,processTaskRelationService,
				processDefId,processDefKey,taskDefId));
		return SUCCESS;
	}
	
	
	/**
	 * 关联状态
	 * @return
	 * @throws Exception
	 */
	public void statusEdit () throws Exception{
		try {
			String processForm = getParam("processStatus");
			//执行新增方法
			processStatusService.addOrUodateProcessStatus(processForm);
			writeSuccessResult();
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			//返回错误信息
			writeFailResult(e.getMessage());
		}
	}
	
	/**
	 * 关联任务
	 * @return
	 * @throws Exception
	 */
	public void associatedEdit () throws Exception{
		try {
			String processTaskRelation = getParam("processTaskRelation");
			//执行新增方法
			processTaskRelationService.addProcessTaskRelation(processTaskRelation);
			writeSuccessResult();
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			//返回错误信息
			writeFailResult(e.getMessage());
		}
	}
	
	/**
	 * 流程表单任务属性页面
	 * @return
	 */
	public String initProcessFormProp(){
		try
		{
			// 获取页面返回的参数
			String processDefKey = getParam("processDefKey");
			String taskDefId = getParam("taskDefId");
			String taskDefName = getParam("taskDefName");
			taskDefName = new String(taskDefName.getBytes("ISO8859-1"), BaseDataConstants.BASE_CODEING);
			TaskConfigurationPage page = new TaskConfigurationPage();
			//将获取的节点字符串返回到前台页面
			request.setAttribute("html",page.getProcessFormPropPage(taskDefName));
			request.setAttribute("taskDefId",taskDefId);
			request.setAttribute("processDefKey",processDefKey);
			return SUCCESS;
		}
		catch (Exception e)
		{
			return SUCCESS;
		}
	}

	/**
	 * 获取流程表单任务属性列表
	 */
	public void getProcessFormProp(){
		String processDefKey = getParam("processDefKey");
		String taskDefId = getParam("taskDefId");
		try
		{
			List<ProcessFormProp> list = processFormService.getProcessFormProp(processDefKey, taskDefId);
			writeSuccessResult(list);
		}
		catch (Exception e)
		{
			writeFailResult(e.getMessage());
		}
	}
	
	/**
	 * 获取流程表单任务属性列表
	 */
	public void saveProcessFormProp(){
		String processFormProps = getParam("processFormProps");
		try
		{
			processFormService.addProcessFormProp(processFormProps);
			writeSuccessResult();
		}
		catch (Exception e)
		{
			writeFailResult(e.getMessage());
		}
	}
}
