package com.ht.action.background.dicdata.defect;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;

import com.ht.action.base.BaseAction;
import com.ht.common.util.DataConverter;
import com.ht.common.util.LogHelper;
import com.ht.front.pages.background.dicdata.defect.DefectPage;
import com.ht.persistence.dao.inter.drawtask.taskbook.create.CreateTaskDao;
import com.ht.persistence.model.background.dicdata.defect.Defect;
import com.ht.persistence.model.background.dicdata.defect.ViewDefect;
import com.ht.persistence.model.background.dicdata.defectform.DefectForm;
import com.ht.persistence.model.drawtask.taskbook.create.CreateTask;
import com.ht.persistence.model.system.workflow.process.ProcessForm;
import com.ht.persistence.model.system.workflow.publish.VProcessDetail;
import com.ht.persistence.model.system.workflow.task.HiTask;
import com.ht.service.impl.system.workflow.task.ProcessTypeEnum;
import com.ht.service.impl.system.workflow.util.BusinessUtil;
import com.ht.service.inter.background.dicdata.basedata.BaseDataService;
import com.ht.service.inter.background.dicdata.coefficient.CoefficientService;
import com.ht.service.inter.background.dicdata.defect.DefectService;
import com.ht.service.inter.background.dicdata.defectform.DefectFormService;
import com.ht.service.inter.background.dicdata.defectitem.DefectItemService;
import com.ht.service.inter.background.dicdata.defecttype.DefectTypeService;
import com.ht.service.inter.drawtask.tasksplit.TaskSplitService;
import com.ht.service.inter.system.workflow.process.ProcessFormService;
import com.ht.service.inter.system.workflow.publish.VProcessDetailService;
import com.ht.service.inter.system.workflow.task.TaskService;

/**
 * @ClassName: DefectAction
 * @Description: TODO(缺陷处理)
 * @author penghao
 * @date 2016年11月6日 下午2:32:05
 */
@SuppressWarnings("serial")
public class DefectAction extends BaseAction
{

	/**
	 * 注入缺陷处理类
	 */
	@Resource
	private DefectService defectService;

	/**
	 * 注入基础数据处理
	 */
	@Resource
	private BaseDataService baseDataService;

	/**
	 * 注入缺陷类别
	 */
	@Resource
	private DefectTypeService defectTypeService;

	/**
	 * 注入缺陷项目
	 */
	@Resource
	private DefectItemService defectItemServie;

	/**
	 * 注入缺陷表单
	 */
	@Resource
	private DefectFormService defectFormServie;

	/**
	 * 注入缺陷表单
	 */
	@Resource
	private CoefficientService coefficientService;

	@Resource
	ProcessFormService processFormService;

	/**
	 * 初始化缺陷主页面
	 * @param
	 * @return
	 */
	public String init()
	{
		try
		{
			// 实例化页面对象
			DefectPage page = DefectPage.getInstance();
			// 获取页面节点字符串
			String listPage = page.getListPage();
			// 将获取的节点字符串返回到前台页面
			request.setAttribute("html", listPage);
		}
		catch (Exception e)
		{
			LogHelper.ERROR.log(e.getMessage(), e);
		}
		return SUCCESS;
	}

	/**
	 * 初始化缺陷页面
	 * @return
	 */
	public String initEdit()
	{
		String id = getParam("id");
		try
		{
			// 实例化对象
			DefectPage page = DefectPage.getInstance();
			// 初始化编辑页面
			String editPaeg = page.getEditPage(baseDataService, defectTypeService, defectService, defectItemServie, id);
			// 将获取的节点字符串返回到前台页面
			request.setAttribute("html", editPaeg);
		}
		catch (Exception e)
		{
			LogHelper.ERROR.log(e.getMessage());
		}
		return SUCCESS;

	}

	@Resource
	VProcessDetailService vProcessDetailService;

	@Resource
	TaskSplitService taskSplitService;

	@Resource
	CreateTaskDao createTaskDao;
	
	/**
	 * 注入流程任务service
	 */
	@Resource(name = "taskService")
	TaskService taskService;

	/**
	 * 初始化缺陷表单页面
	 * @return
	 */
	public String initDefectForm()
	{
		// 流程任务定义ID
		String taskDefId = getParam("taskDefId");
		// 流程定义ID
		String processDefId = getParam("processDefId");
		// 任务实例ID
		String taskInstId = getParam("taskId");
		// 流程实例ID
		String processInstId = getParam("processInstId");
		// 流程父实例ID
		String parentProcessInstId = getParam("parentProcessInstId");
		// 标记
		String flag = "source";
		// 海图类型
		String type = null;
		if (StringUtils.isNotEmpty(processDefId))
		{
			type = processDefId.split(":")[0];
		}
		// 初始化编辑页面
		String editPage = null;
		try
		{
			// 获取表单ID
			String formId = null;
			// 查询当前任务所属表单
			ProcessForm pfs = processFormService.getProcessFormByProAndTask(processDefId, taskDefId);
			if (pfs != null)
			{
				formId = pfs.getFormId();
			}
			// 拆分ID
			String splitId = null;
			// 图号
			String mapNo = null;
			// 版次
			String revision = "";
			// 根据processInstId查询和type查询拆分记录ID
			VProcessDetail detail = null;
			if (StringUtils.isNotBlank(parentProcessInstId)&&!parentProcessInstId.equals("null"))
			{
				detail = vProcessDetailService.getProcessDetailByProcessInstId(parentProcessInstId);
			}else{
				detail = vProcessDetailService.getProcessDetailByProcessInstId(processInstId);
			}
			if (detail != null)
			{
				// 查询图号
				CreateTask childTask = createTaskDao.getTaskById(detail.getDetailRecordId());
				splitId = detail.getDetailRecordId();
				if (childTask != null)
				{
					mapNo = childTask.getMapNo();
					revision = childTask.getRevision();
				}
			}
			// 实例化对象
			DefectPage page = DefectPage.getInstance();
			// 纸海图编绘、纸海图小改正、工程/专题图（纸海图）
			if (type.equals(ProcessTypeEnum.SEA_MAP_COMPILATION_PAPER.name()) || type.equals(ProcessTypeEnum.SMALL_CORRECTION_PAPER.name())
					|| type.equals(ProcessTypeEnum.PROJECT_SPECIAL_PAPER.name()))
			{
				String charttype = BusinessUtil.getInstance().getCharttypeByProcess(type);
				editPage = page.getPaperFormPage(type,splitId, formId, taskInstId, processInstId, charttype, mapNo, revision, coefficientService, baseDataService,processDefId,taskDefId,parentProcessInstId);
				flag = "paper";
			}
			else
			// 电子海图编绘、电子海图小改正、工程/专题图（电子）
			if (type.equals(ProcessTypeEnum.SEA_MAP_COMPILATION_ELECTRONIC.name()) || type.equals(ProcessTypeEnum.SMALL_CORRECTION_ELECTRONIC.name())
					|| type.equals(ProcessTypeEnum.PROJECT_SPECIAL_ELECTRONIC.name()))
			{
				String charttype = BusinessUtil.getInstance().getCharttypeByProcess(type);
				editPage = page.getElectFormPage(type,splitId, formId, taskInstId, processInstId, charttype, mapNo, revision, coefficientService, baseDataService,processDefId,taskDefId,parentProcessInstId);
				flag = "electronic";
			}
			else
			// 改正通告编辑、改正通告模板编辑
			if (type.equals(ProcessTypeEnum.CORRECTION_NOTICE_TEMPLATE.name()) || type.equals(ProcessTypeEnum.CORRECTION_NOTICE_TEMPLATE_EDIT.name()))
			{
				String charttype = BusinessUtil.getInstance().getCharttypeByProcess(type);
				editPage = page
						.getNoticeFormPage(splitId, formId, taskInstId, processInstId, charttype, mapNo, revision, coefficientService, baseDataService,processDefId,taskDefId);
				flag = "notice";
			}
			else
			{
				// 源数据编绘、源数据小改正
				String charttype = BusinessUtil.getInstance().getCharttypeByProcess(type);
				editPage = page.getSourceFormPage(splitId, formId, taskInstId, processInstId, charttype, mapNo, revision, coefficientService,
						baseDataService,processDefId,taskDefId,parentProcessInstId);
			}
			// 将获取的节点字符串返回到前台页面
			request.setAttribute("html", editPage);
		}
		catch (Exception e)
		{
			LogHelper.ERROR.log(e.getMessage());
			return ERROR;
		}
		return flag;

	}

	/**
	 * 获取缺陷列表的集合
	 */
	public void getDefectList()
	{
		String defect = getParam("defect");
		try
		{
			// 获取缺陷列表
			List<Defect> list = defectService.getDefectList(defect);
			// 返回客户端成功消息
			writeSuccessResult(list);
		}
		catch (Exception e)
		{
			// 打印日志
			LogHelper.ERROR.log(e.getMessage());
			// 返回客户端失败消息
			writeFailResult(e.getMessage());
		}
	}

	/**
	 * 获取缺陷列表的集合
	 */
	public void getList()
	{
		try
		{
			// 获取缺陷列表
			List<ViewDefect> list = defectService.getList();
			// 返回客户端成功消息
			writeSuccessResult(list);
		}
		catch (Exception e)
		{
			// 打印日志
			LogHelper.ERROR.log(e.getMessage());
			// 返回客户端失败消息
			writeFailResult(e.getMessage());
		}
	}

	/**
	 * 获取缺陷列表的集合
	 */
	public void getDefect()
	{
		String id = getParam("id");
		try
		{
			// 获取缺陷列表
			Defect defect = defectService.getDefectById(id);
			// 返回客户端成功消息
			writeSuccessResult(defect);
		}
		catch (Exception e)
		{
			// 打印日志
			LogHelper.ERROR.log(e.getMessage());
			// 返回客户端失败消息
			writeFailResult(e.getMessage());
		}
	}

	/**
	 * 根据3种id获取缺陷类表
	 */
	public void getDefectListBy3Id()
	{
		String charttypeId = getParam("charttypeId");
		String defecttypeId = getParam("defecttypeId");
		String defectitemId = getParam("defectitemId");
		try
		{
			// 获取缺陷列表
			List<Defect> list = defectService.getDefectListBy3Id(charttypeId, defecttypeId, defectitemId);
			// 返回客户端成功消息
			writeSuccessResult(list);
		}
		catch (Exception e)
		{
			// 打印日志
			LogHelper.ERROR.log(e.getMessage());
			// 返回客户端失败消息
			writeFailResult(e.getMessage());
		}
	}

	/**
	 * 保存缺陷
	 * @param
	 * @return
	 */
	public void add()
	{
		String params = getParam("defect");
		try
		{
			defectService.add(params);
			// 成功返回客户端消息
			writeSuccessResult();
		}
		catch (Exception e)
		{
			// 打印日志
			LogHelper.ERROR.log(e.getMessage());
			// 返回客户端失败消息
			writeFailResult(e.getMessage());

		}
	}

	/**
	 * 保存缺陷表单
	 * @param
	 * @return
	 */
	public void addForm()
	{
		
		
		String params = getParam("defect");
		String processDefId = getParam("processDefId");
		String taskDefId = getParam("taskDefId");
		String processInstId = getParam("processInstId");
		String type = null;
		if (StringUtils.isNotEmpty(processDefId))
		{
			type = processDefId.split(":")[0];
		}
		// 根据海图编绘类型获取节点相同的taskInstId集合
		String taskInstId = getTaskInstIds(type, taskDefId, processInstId);
		try
		{
			defectFormServie.add(params);
			DefectForm defectForm = (DefectForm) DataConverter.convertJson2Object(params, DefectForm.class);
			defectFormServie.modifyCount(defectForm,taskInstId,processInstId);
			// 成功返回客户端消息
			writeSuccessResult();
		}
		catch (Exception e)
		{
			// 打印日志
			LogHelper.ERROR.log(e.getMessage());
			// 返回客户端失败消息
			writeFailResult(e.getMessage());

		}
	}

	/**
	 * 保存评分
	 * @param
	 * @return
	 */
	public void updateGrading()
	{
		String params = getParam("defect");
		String processDefId = getParam("processDefId");
		String taskDefId = getParam("taskDefId");
		String processInstId = getParam("processInstId");
		String type = null;
		if (StringUtils.isNotEmpty(processDefId))
		{
			type = processDefId.split(":")[0];
		}
		// 根据海图编绘类型获取节点相同的taskInstId集合
		String taskInstId = getTaskInstIds(type, taskDefId, processInstId);
		try
		{
			DefectForm defectForm = (DefectForm) DataConverter.convertJson2Object(params, DefectForm.class);
			defectFormServie.updateGrading(defectForm,taskInstId, processInstId);
			// 成功返回客户端消息
			writeSuccessResult();
		}
		catch (Exception e)
		{
			// 打印日志
			LogHelper.ERROR.log(e.getMessage());
			// 返回客户端失败消息
			writeFailResult(e.getMessage());

		}
	}

	/**
	 * 获取缺陷表单的集合
	 * @param
	 * @return
	 */
	public void getDefectFormList()
	{
		//String splitId = getParam("splitId");
		//String taskInstId = getParam("taskInstId");

		String processInstId = getParam("processInstId");
		String processDefId = getParam("processDefId");
		String taskDefId = getParam("taskDefId");
		String type = null;
		if (StringUtils.isNotEmpty(processDefId))
		{
			type = processDefId.split(":")[0];
		}
		// 根据海图编绘类型获取节点相同的taskInstId集合
		String taskInstId = getTaskInstIds(type, taskDefId, processInstId);
		try
		{
			List<DefectForm> list = defectFormServie.getDefectFormListByTaskInstIds(processInstId, taskInstId);
			// 成功返回客户端消息
			writeSuccessResult(list);
		}
		catch (Exception e)
		{
			// 打印日志
			LogHelper.ERROR.log(e.getMessage());
			// 返回客户端失败消息
			writeFailResult(e.getMessage());

		}
	}

	/**
	 * 删除缺陷
	 */
	public void remove()
	{
		String ids = getParam("ids");
		try
		{
			// 根据id删除缺陷
			defectService.delete(ids);
			// 删除成功后返回客户端消息
			writeSuccessResult();
		}
		catch (Exception e)
		{
			// 打印日志
			LogHelper.ERROR.log(e.getMessage());
			// 返回客户端失败消息
			writeFailResult(e.getMessage());
		}
	}

	/**
	 * 删除缺陷表单数据
	 */
	public void removeForm()
	{
		String id = getParam("id");
		//String taskInstId = getParam("taskInstId");
		String processInstId = getParam("processInstId");
		String processDefId = getParam("processDefId");
		String taskDefId = getParam("taskDefId");
		String type = null;
		if (StringUtils.isNotEmpty(processDefId))
		{
			type = processDefId.split(":")[0];
		}
		// 根据海图编绘类型获取节点相同的taskInstId集合
		String taskInstId = getTaskInstIds(type, taskDefId, processInstId);
		try
		{
			// 根据id删除缺陷表单数据
			defectFormServie.delete(id);
			DefectForm defectForm = new DefectForm();
			//defectForm.setTaskInstId(taskInstId);
			defectForm.setProcessInstId(processInstId);
			defectFormServie.modifyCount(defectForm,taskInstId, processInstId);
			// 删除成功后返回客户端消息
			writeSuccessResult();
		}
		catch (Exception e)
		{
			// 打印日志
			LogHelper.ERROR.log(e.getMessage());
			// 返回客户端失败消息
			writeFailResult(e.getMessage());
		}
	}
	
	
	public String getTaskInstIds(String type,String taskDefId,String processInstId){
		// 根据海图编绘类型获取节点相同的taskInstId集合
		if (type.equals(ProcessTypeEnum.SEA_MAP_COMPILATION_PAPER.name())){// 纸海图编绘
			if(taskDefId.equals("u_task_zhijian_zjjilub")||taskDefId.equals("u_task_zhijian_xgzjjilub")){//质检类型
				taskDefId = "u_task_zhijian_zjjilub,u_task_zhijian_xgzjjilub";
			}else if(taskDefId.equals("u_task_shending_sdjilub")||taskDefId.equals("u_task_sdy_xgsdjilub")){//审定
				taskDefId = "u_task_shending_sdjilub,u_task_sdy_xgsdjilub";
			}
		}else if (type.equals(ProcessTypeEnum.SEA_MAP_COMPILATION_ELECTRONIC.name())){// 电子海图编绘
			if(taskDefId.equals("u_task_zhijian_zjjilub")||taskDefId.equals("u_task_zhijian_xgzjjilub")){//质检类型
				taskDefId = "u_task_zhijian_zjjilub,u_task_zhijian_xgzjjilub";
			}else if(taskDefId.equals("u_task_shending_sdjilub")||taskDefId.equals("u_task_shending_xgsdjilub")){//审定
				taskDefId = "u_task_shending_sdjilub,u_task_shending_xgsdjilub";
			}
		}else if (type.equals(ProcessTypeEnum.SEA_MAP_COMPILATION_SOURCE_DATA.name())){// 源数据编绘
			if(taskDefId.equals("u_task_zhijian_zjjilub")||taskDefId.equals("u_task_zhijian_xgzjjilub")){//质检类型
				taskDefId = "u_task_zhijian_zjjilub,u_task_zhijian_xgzjjilub";
			}else if(taskDefId.equals("u_task_shending_sdjilub")||taskDefId.equals("u_task_xgsdjilub")){//审定
				taskDefId = "u_task_shending_sdjilub,u_task_xgsdjilub";
			}
		}
		String[] taskDefIdArray = taskDefId.split(",");
		String taskInstId = "";
		if(taskDefIdArray.length>1){
			for (int i = taskDefIdArray.length-1; i >=0; i--)
			{
				List<HiTask> hiTaskList = taskService.getHiTaskByProcessInstIdAndTaskDefId(processInstId, taskDefIdArray[i]);
				if (hiTaskList != null)
				{
					if (hiTaskList.size()>0)
					{
						for (int j = 0; j < hiTaskList.size(); j++) {
							taskInstId += "," + hiTaskList.get(j).getTaskInstId();
						}
					}
				}
			}
		}else{
			List<HiTask> hiTaskList = taskService.getHiTaskByProcessInstIdAndTaskDefId(processInstId, taskDefId);
			if (hiTaskList != null)
			{
				if (hiTaskList.size()>0)
				{
					for (int j = 0; j < hiTaskList.size(); j++) {
						taskInstId += "," + hiTaskList.get(j).getTaskInstId();
					}
				}
			}
		}
		return  taskInstId.substring(1);
	}

}
