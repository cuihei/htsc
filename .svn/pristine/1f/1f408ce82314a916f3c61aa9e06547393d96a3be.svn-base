package com.ht.service.impl.experiencebook;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.ht.common.exception.CommonException;
import com.ht.common.util.SpringUtil;
import com.ht.persistence.dao.inter.background.dicdata.scalecontrol.ScaleControlDao;
import com.ht.persistence.dao.inter.background.organization.employee.UserDao;
import com.ht.persistence.dao.inter.drawtask.taskbook.book.TaskBookDao;
import com.ht.persistence.dao.inter.drawtask.taskbook.create.CreateTaskDao;
import com.ht.persistence.dao.inter.drawtask.tasksplit.TaskSplitDao;
import com.ht.persistence.dao.inter.system.workflow.task.FlowsDao;
import com.ht.persistence.model.background.dicdata.defectform.DefectForm;
import com.ht.persistence.model.background.dicdata.scalecontrol.ScaleControl;
import com.ht.persistence.model.background.organization.employee.User;
import com.ht.persistence.model.complication.formprop.FormValue;
import com.ht.persistence.model.drawtask.taskbook.book.TaskBook;
import com.ht.persistence.model.drawtask.taskbook.create.CreateTask;
import com.ht.persistence.model.system.workflow.publish.VProcessDetail;
import com.ht.persistence.model.system.workflow.task.Flows;
import com.ht.service.constant.experiencebook.ChartStatusConstants;
import com.ht.service.constant.experiencebook.project.Project;
import com.ht.service.constant.experiencebook.project.ProjectItemNoIssue;
import com.ht.service.constant.experiencebook.project.ProjectPersonAndDate;
import com.ht.service.constant.experiencebook.sourcedatacorrection.SourceDataSamllPersonAndDate;
import com.ht.service.constant.model.PersonAndDate;
import com.ht.service.inter.experiencebook.ProjectMapService;
import com.ht.workflow.service.IWorkflowService;

/**
 * 工程专题图导出经历薄数据类
 * @author wangyouwei
 */
public class ProjectMapServiceImpl implements ProjectMapService
{

	/**
	 * 拆分dao
	 */
	@Resource
	TaskSplitDao taskSplitDao;
	
	@Resource
	FlowsDao flowsDao;
	
	@Resource
	ScaleControlDao scaleControlDao;
	
	@Resource
	UserDao userDao;
	
	@Resource
	CreateTaskDao createTaskDao;
	
	@Resource
	TaskBookDao taskBookDao;

	@Override
	public Map<String, Object> projectPaperMap(String processInstId) throws Exception
	{
		try
		{
			Base base = new Base();
			// 定义导出数据Map
			Map<String, Object> data = new HashMap<String, Object>();
			Map<String, Object> map = new HashMap<String,Object>();
			/**
			 * 开始工程&专题图纸海图计划数据准备
			 */
			String processDefKey ="PROJECT_SPECIAL_PAPER";
			// 获取当前流程实例操作的表数据
			// 编绘作业流程ID
			String workProcessInstId = processInstId;
			// 编绘计划流程实例ID
			String planProcessInstId = processInstId;
			// 拆分主键记录
			VProcessDetail detail = base.getDetailRecordId(null, processInstId);
			CreateTask complicationTask = createTaskDao.getTaskById(detail.getDetailRecordId());
			String taskBookId = complicationTask.getParentTaskbookId();
			TaskBook taskBook = taskBookDao.findById(taskBookId);

			/**
			 * 说明
			 */
			Project project = new Project();
			// 设置海图类型
			project.setGCZTTZTJLB_CHART_TYPE("纸海图");
//			// 设置海图用途
//			project.setGCZTTZTJLB_USE("工程图 &专题图");
//			// 项目名称
//			project.setGCZTTZTJLB_PROJECT_NAME(taskBook.getProjectName());
			// 任务书编号
			project.setGCZTTZTJLB_TASKBOOK_NO(taskBook.getTaskbookNo());
			// 图名
			project.setGCZTTZTJLB_MAP_NAME(complicationTask.getTaskName());
			// 版次
			project.setGCZTTZTJLB_REVISION(complicationTask.getRevision());
			// 比例尺
			project.setGCZTTZTJLB_SCALE(complicationTask.getScale());
			// 编辑比例尺
			project.setGCZTTZTJLB_SCALE_TYPE("编辑比例尺");
			
			//设置项目名称
			ProjectItemNoIssue projectitemNoIssue=new ProjectItemNoIssue();
			List<List<FormValue>> projectitemNoIssueList = base.getFormValueByRowFlag(projectitemNoIssue.ProjectItemNoIssue_Paper_FORM_ID, workProcessInstId);
			
			if (projectitemNoIssueList != null)
			{
				if (projectitemNoIssueList.size() > 0)
				{
					projectitemNoIssue =new ProjectItemNoIssue();
					for (List<FormValue> list : projectitemNoIssueList) {
						projectitemNoIssue.setElectronDataProps(list);
						// 项目名称
						project.setGCZTTZTJLB_PROJECT_NAME(projectitemNoIssue.getXMMC());
						// 设置海图用途
						project.setGCZTTZTJLB_USE(projectitemNoIssue.getHTYT());
						//测量时间
						project.setGCZTTZTJLB_MEASURE_DATE(projectitemNoIssue.getCLRQ());
					}
				}
			}
			//获取整个流程
			Flows flowss = new Flows();
			flowss.setProcessInstId(planProcessInstId);
			List<Flows> flowListAll = new ArrayList<Flows>();
			flowListAll.addAll(flowsDao.getProcessFlows(flowss));
			flowss = new Flows();
			flowss.setProcessInstId(workProcessInstId);
			flowListAll.addAll(flowsDao.getProcessFlows(flowss));
			// 获取流程作业各操作人和状态
			ProjectPersonAndDate pd=new ProjectPersonAndDate(flowListAll);
			/**
			 * 编绘记录表
			 */
			List<List<FormValue>> problemList = base.getFormValueByRowFlag(project.COMPLICATION_PAPER_FORM_ID, workProcessInstId);
			String record = "";
			String measureDate = "";
			for (int i = 0; i < problemList.size(); i++)
			{
				List<FormValue> valueList = problemList.get(i);
				project.setComplicationDataProps(valueList,project.COMPLICATION_PAPER_FORM_ID);
				Map<String,Object> mapP = project.getProjectMap();
				String bhjh = mapP.get("text_gczttztjlb_bhjhjynwt")==null?"":mapP.get("text_gczttztjlb_bhjhjynwt").toString();
				record = bhjh +"\n";
				measureDate = mapP.get("text_gczttztjlb_clrq")==null?"":mapP.get("text_gczttztjlb_clrq").toString();
			}
		
			// 设置编绘计划及疑难问题
			project.setGCZTTZTJLB_PLAN_PROBLEM(record);
			// 设置编绘员
			project.setGCZTTZTJLB_COMPILATION_PERSON(pd.getCompilationPerson());
			// 设置编绘时间
			project.setGCZTTZTJLB_COMPILATION_DATE(pd.getCompilationDate());
			
			/**
			 * 质检记录表
			 */
			map = base.getQualityDefective(workProcessInstId, pd.getCompilationPerson(), pd.getCompilationDate(), pd.getQualityPerson(),
					pd.getQualityDate(), null);
			List<DefectForm> zjlist = (List<DefectForm>) map.get("table_zjjlb");
			String problem  = "";
			if(zjlist!=null){
				if(zjlist.size()>0){
					for (int i = 0; i < zjlist.size(); i++) {
						String zjwt = (String) (((Map<String, Object>) zjlist.get(i)).get("text_zjjlb_problem")==null?"":((Map<String, Object>) zjlist.get(i)).get("text_zjjlb_problem"));
						String cljg =(String) (((Map<String, Object>) zjlist.get(i)).get("text_zjjlb_opinion")==null?"":((Map<String, Object>) zjlist.get(i)).get("text_zjjlb_opinion"));
						problem += zjwt+"  "+cljg +"\n";
					}
					// 质检存在问题（缺陷项目+缺陷内容）和处理结果
					project.setGCZTTZTJLB_QUALITY_PROBLEM(problem);
					
				}
			}
			// 质检员
			project.setGCZTTZTJLB_QUALITY_PERSON(pd.getQualityPerson());
			// 质检时间
			project.setGCZTTZTJLB_QUALITY_DATE(pd.getQualityDate());
			/**
			 * 制图事业部结论
			 */
			// 结论
			project.setGCZTTZTJLB_BD_CONCLUSION(pd.getChiefOpinion());
			// 制图事业部主任
			project.setGCZTTZTJLB_BD_DIRECTOR(pd.getChiefPerson());
			// 制图事业部主任签字日期
			project.setGCZTTZTJLB_BD_DIRECTOR_DATE(pd.getChiefDate());
			
			/**
			 * 审定记录表
			 */
			map = base.getValidationDefective(workProcessInstId, pd.getValidationPerson(), pd.getValidationDate(), pd.getQualityChiefPerson(),pd.getQualityChiefDate(), null);
			List<DefectForm> sdlist = (List<DefectForm>) map.get("table_sdjlb");
			String sdproblem  = "";
			if(sdlist!=null){
				if(sdlist.size()>0){
					for (int i = 0; i < sdlist.size(); i++) {
						String zjwt = (String) (((Map<String, Object>) sdlist.get(i)).get("text_sdjlb_problem")==null?"":((Map<String, Object>) sdlist.get(i)).get("text_sdjlb_problem"));
						String cljg = (String) (((Map<String, Object>) sdlist.get(i)).get("text_sdjlb_opinion")==null?"":((Map<String, Object>) sdlist.get(i)).get("text_sdjlb_opinion"));
						sdproblem += zjwt+"  "+cljg+"\n";
					}
					// 审定存在问题（缺陷项目+缺陷内容）
					project.setGCZTTZTJLB_VALIDATION_PROBLEM(sdproblem);
					
				}
			}
			// 审定员
			project.setGCZTTZTJLB_VALIDATION_PERSON(pd.getValidationPerson());
			// 审定时间
			project.setGCZTTZTJLB_VALIDATION_DATE(pd.getValidationDate());
			
			/**
			 * 质量部门结论  
			 */
			// 结论
			project.setGCZTTZTJLB_QD_CONCLUSION(pd.getQualityChiefchiefOpinion());
			// 制图事业部主任
			project.setGCZTTZTJLB_QD_CHIEF(pd.getQualityChiefPerson());
			// 制图事业部主任签字日期
			project.setGCZTTZTJLB_QD_CHIEF_DATE(pd.getQualityChiefDate());
			
			map = project.getProjectMap();
			data.put(project.MARKER, map);
			
			ProjectItemNoIssue parperData=new ProjectItemNoIssue();
			List<List<FormValue>> parperDataList = base.getFormValueByRowFlag(parperData.ProjectItemNoIssue_Paper_FORM_ID, workProcessInstId);
			if(parperDataList!=null){
				if(parperDataList.size()>0){
					for (List<FormValue> list : parperDataList) {
						parperData.setPaperDataProps(list);
					}
					Map<String,Object> maps=new HashMap<String,Object>();
					maps.putAll(parperData.getMap());
					data.put(parperData.MARKER,maps);
				}
			}
			
			
			return data;
		}
		catch (Exception e)
		{
			throw new CommonException();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> projectElectricMap(String processInstId) throws Exception
	{
		try
		{
			Base base = new Base();
			// 定义导出数据Map
			Map<String, Object> data = new HashMap<String, Object>();
			Map<String, Object> map = new HashMap<String,Object>();
			/**
			 * 开始工程&专题图电子海图计划数据准备
			 */
			String processDefKey ="PROJECT_SPECIAL_ELECTRONIC";
			// 获取当前流程实例操作的表数据
			// 编绘作业流程ID
			String workProcessInstId = processInstId;
			// 编绘计划流程实例ID
			String planProcessInstId = processInstId;
			// 拆分主键记录
			VProcessDetail detail = base.getDetailRecordId(null, processInstId);
			CreateTask complicationTask = createTaskDao.getTaskById(detail.getDetailRecordId());
			String taskBookId = complicationTask.getParentTaskbookId();
			TaskBook taskBook = taskBookDao.findById(taskBookId);

			/**
			 * 说明
			 */
			Project project = new Project();
		
			// 任务书编号
			project.setGCZTTZTJLB_TASKBOOK_NO(taskBook.getTaskbookNo());
			// 设置海图类型
			project.setGCZTTZTJLB_CHART_TYPE("电子海图");
			// 图名
			project.setGCZTTZTJLB_MAP_NAME(complicationTask.getTaskName());
			// 版次
			project.setGCZTTZTJLB_REVISION(complicationTask.getRevision());
			// 编辑比例尺
			project.setGCZTTZTJLB_SCALE_TYPE("标准编辑比例尺");
			// 比例尺
			String scale = complicationTask.getScale();
			ScaleControl scaleControl = new ScaleControl();
			scaleControl.setOldScale(scale);
			scaleControl = scaleControlDao.getStandardByOld(scaleControl);
			if (scaleControl!=null) {
				project.setGCZTTZTJLB_SCALE(scaleControl.getStandardScale()==null?"":scaleControl.getStandardScale());
			}else {
				project.setGCZTTZTJLB_SCALE("");
			}
			//设置项目名称
			ProjectItemNoIssue projectitemNoIssue=new ProjectItemNoIssue();
			List<List<FormValue>> projectitemNoIssueList = base.getFormValueByRowFlag(projectitemNoIssue.ProjectItemNoIssue_Electron_FORM_ID, workProcessInstId);
			
			if (projectitemNoIssueList != null)
			{
				if (projectitemNoIssueList.size() > 0)
				{
					projectitemNoIssue =new ProjectItemNoIssue();
					for (List<FormValue> list : projectitemNoIssueList) {
						projectitemNoIssue.setElectronDataProps(list);
						// 项目名称
						project.setGCZTTZTJLB_PROJECT_NAME(projectitemNoIssue.getXMMC());
						// 设置海图用途
						project.setGCZTTZTJLB_USE(projectitemNoIssue.getHTYT());
						//测量时间
						project.setGCZTTZTJLB_MEASURE_DATE(projectitemNoIssue.getCLRQ());
					}
				}
			}
			//获取整个流程
			Flows flowss = new Flows();
			flowss.setProcessInstId(planProcessInstId);
			List<Flows> flowListAll = new ArrayList<Flows>();
			flowListAll.addAll(flowsDao.getProcessFlows(flowss));
			flowss = new Flows();
			flowss.setProcessInstId(workProcessInstId);
			flowListAll.addAll(flowsDao.getProcessFlows(flowss));
			// 获取流程作业各操作人和状态
			ProjectPersonAndDate pd=new ProjectPersonAndDate(flowListAll);
			/**
			 * 编绘记录表
			 */
			List<List<FormValue>> problemList = base.getFormValueByRowFlag(project.COMPLICATION_ELECTRONIC_FORM_ID, workProcessInstId);
			String record = "";
			String measureDate = "";
			if(problemList!=null){
				if(problemList.size()>0){
					for (int i = 0; i < problemList.size(); i++)
					{
						List<FormValue> valueList = problemList.get(i);
						project.setComplicationDataProps(valueList,project.COMPLICATION_ELECTRONIC_FORM_ID);
						Map<String,Object> mapP = project.getProjectMap();
						String bhjh = mapP.get("text_gczttztjlb_bhjhjynwt")==null?"":mapP.get("text_gczttztjlb_bhjhjynwt").toString();
						record = bhjh +"\n";
						measureDate = mapP.get("text_gczttztjlb_clrq")==null?"":mapP.get("text_gczttztjlb_clrq").toString();
					}
					// 设置编绘计划及疑难问题
					project.setGCZTTZTJLB_PLAN_PROBLEM(record);
					
				}
			}
			// 设置编绘员
			project.setGCZTTZTJLB_COMPILATION_PERSON(pd.getCompilationPerson());
			// 设置编绘时间
			project.setGCZTTZTJLB_COMPILATION_DATE(pd.getCompilationDate());
			
			/**
			 * 质检记录表
			 */
			map = base.getQualityDefective(workProcessInstId, pd.getCompilationPerson(), pd.getCompilationDate(), pd.getQualityPerson(),
					pd.getQualityDate(), null);
			List<DefectForm> zjlist = (List<DefectForm>) map.get("table_zjjlb");
			String problem  = "";
			if(zjlist!=null){
				if(zjlist.size()>0){
					for (int i = 0; i < zjlist.size(); i++) {
						String zjwt = (String) (((Map<String, Object>) zjlist.get(i)).get("text_zjjlb_problem")==null?"":((Map<String, Object>) zjlist.get(i)).get("text_zjjlb_problem"));
						String cljg = (String) (((Map<String, Object>) zjlist.get(i)).get("text_zjjlb_opinion")==null?"":((Map<String, Object>) zjlist.get(i)).get("text_zjjlb_opinion"));
						problem += zjwt+"  "+cljg +"\n";
					}
					// 质检存在问题（缺陷项目+缺陷内容）和处理结果
					project.setGCZTTZTJLB_QUALITY_PROBLEM(problem);
					
				}
			}
			// 质检员
			project.setGCZTTZTJLB_QUALITY_PERSON(pd.getQualityPerson());
			// 质检时间
			project.setGCZTTZTJLB_QUALITY_DATE(pd.getQualityDate());
			
			/**
			 * 制图事业部结论
			 */
			// 结论
			project.setGCZTTZTJLB_BD_CONCLUSION(pd.getChiefOpinion());
			// 制图事业部主任
			project.setGCZTTZTJLB_BD_DIRECTOR(pd.getChiefPerson());
			// 制图事业部主任签字日期
			project.setGCZTTZTJLB_BD_DIRECTOR_DATE(pd.getChiefDate());
			
			/**
			 * 审定记录表
			 */
			map = base.getValidationDefective(workProcessInstId, pd.getValidationPerson(), pd.getValidationDate(), pd.getQualityChiefPerson(),pd.getQualityChiefDate(), null);
			List<DefectForm> sdlist = (List<DefectForm>) map.get("table_sdjlb");
			String sdproblem  = "";
			if(sdlist!=null){
				if(sdlist.size()>0){
					for (int i = 0; i < sdlist.size(); i++) {
						String zjwt = (String) (((Map<String, Object>) sdlist.get(i)).get("text_sdjlb_problem")==null?"":((Map<String, Object>) sdlist.get(i)).get("text_sdjlb_problem"));
						String cljg = (String) (((Map<String, Object>) sdlist.get(i)).get("text_sdjlb_opinion")==null?"":((Map<String, Object>) sdlist.get(i)).get("text_sdjlb_opinion"));
						sdproblem += zjwt+"  "+cljg+"\n";
					}
					// 审定存在问题（缺陷项目+缺陷内容）
					project.setGCZTTZTJLB_VALIDATION_PROBLEM(sdproblem);
				}
			}
			// 审定员
			project.setGCZTTZTJLB_VALIDATION_PERSON(pd.getValidationPerson());
			// 审定时间
			project.setGCZTTZTJLB_VALIDATION_DATE(pd.getValidationDate());
			
			/**
			 * 质量部门结论  
			 */
			// 结论
			project.setGCZTTZTJLB_QD_CONCLUSION(pd.getQualityChiefchiefOpinion());
			// 制图事业部主任
			project.setGCZTTZTJLB_QD_CHIEF(pd.getQualityChiefPerson());
			// 制图事业部主任签字日期
			project.setGCZTTZTJLB_QD_CHIEF_DATE(pd.getQualityChiefDate());
			map = project.getProjectMap();
			data.put(project.MARKER, map);
			return data;
		}
		catch (Exception e)
		{
			throw new CommonException();
		}
	}
	
	/**
	 * 获取各状态操作人和时间
	 * @param workProcessInstId 流程ID
	 * @return 操作人时间
	 */
	public PersonAndDate getPersonAndDate(String workProcessInstId,List<Flows> flowList)
	{
		Base base = new Base();
		// 编绘人
		String compilationPerson = null;
		// 编绘意见
		String compilationAdvice = null;
		// 编绘日期
		String compilationDate = null;
		// 质检人
		String qualityPerson = null;
		// 质检日期
		String qualityDate = null;
		// 质检审核人
		String qualityApprovePerson = null;
		// 质检审核人签章图
		String qualityApproveSignature = null;
		// 质检审核意见
		String qualityApproveAdvice = null;
		// 质检审核日期
		String qualityApproveDate = null;
		// 审定人
		String valaditionPerson = null;
		// 审定日期
		String valaditionDate = null;
		// 审定审核人
		String valaditonApprovePerson = null;
		// 审定审核人签章图片
		String valaditonApproveSignature = null;
		// 审定审核意见
		String valaditonApproveAdvice = null;
		// 审定审核日期
		String valaditonApproveDate = null;
		// 总工程师人
		String enginnerPerson = null;
		// 总工程师时间
		String enginnerDate = null;
		// 总工程师意见
		String enginnerAdvice = null;
		// 总工程师签章图片
		String enginnerSignature = null;
		Flows flow = getStatusPersonAndDate(ChartStatusConstants.COMPILATION_COMPLETE, flowList);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		IWorkflowService service = (IWorkflowService) SpringUtil.getBean("service");
		Object compilationPersonArg = service.getHistProcessArgs(workProcessInstId, "user_bhy");
		if (compilationPersonArg != null)
		{
			compilationPerson = base.getUserName(compilationPersonArg.toString());
		}
		if (flow != null)
		{
			compilationAdvice = !flow.getTaskResult().equals("退回") ? "同意" : null;
			if (flow.getStartTime() != null)
			{
				compilationDate = format.format(flow.getStartTime());
			}
		}
		Object qualityPersonArg = service.getHistProcessArgs(workProcessInstId, "user_zjy");
		if (qualityPersonArg != null)
		{
			qualityPerson =  base.getUserName(qualityPersonArg.toString());
		}
		flow = getStatusPersonAndDate(ChartStatusConstants.QUALITY_COMPLETE, flowList);
		if (flow != null)
		{
			if (flow.getStartTime() != null)
			{
				qualityDate = format.format(flow.getStartTime());
			}
		}
		flow = getStatusPersonAndDate(ChartStatusConstants.QUALITY_APPROVE_COMPLETE, flowList);
		if (flow != null)
		{
			qualityApproveAdvice = !flow.getTaskResult().equals("退回") ? "同意" : null;
			qualityApprovePerson = flow.getUserName();
			qualityApproveSignature = getUserSignature(flow.getUserNo());
			if (flow.getStartTime() != null)
			{
				qualityApproveDate = format.format(flow.getStartTime());
			}
		}
		Object valaditionPersonArg = service.getHistProcessArgs(workProcessInstId, "user_sdy");
		if (valaditionPersonArg != null)
		{
			valaditionPerson =  base.getUserName(valaditionPersonArg.toString());
		}
		flow = getStatusPersonAndDate("审定后改图", flowList);
		if (flow != null)
		{
			if (flow.getStartTime() != null)
			{
				valaditionDate = format.format(flow.getStartTime());
			}
		}
		flow = getStatusPersonAndDate("结束", flowList);
		if (flow != null)
		{
			valaditonApprovePerson = flow.getUserName();
			valaditonApproveAdvice = !flow.getTaskResult().equals("退回") ? "同意" : null;
			valaditonApproveSignature = getUserSignature(flow.getUserNo());
			if (flow.getStartTime() != null)
			{
				valaditonApproveDate = format.format(flow.getStartTime());
			}
		}
		
		flow = getStatusPersonAndDate(ChartStatusConstants.ENGINEER_COMPLETE, flowList);
		if (flow != null)
		{
			enginnerPerson = flow.getUserName();
			enginnerAdvice = !flow.getTaskResult().equals("退回") ? "同意" : null;
			enginnerSignature = getUserSignature(flow.getUserNo());
			if (flow.getStartTime() != null)
			{
				enginnerDate = format.format(flow.getStartTime());
			}
		}
		PersonAndDate pd = new PersonAndDate();
		pd.setCompilationPerson(compilationPerson);
		pd.setCompilationAdvice(compilationAdvice);
		pd.setCompilationDate(compilationDate);
		pd.setQualityPerson(qualityPerson);
		pd.setQualityDate(qualityDate);
		pd.setQualityApprovePerson(qualityApprovePerson);
		pd.setQualityApproveAdvice(qualityApproveAdvice);
		pd.setQualityApproveDate(qualityApproveDate);
		pd.setValaditionPerson(valaditionPerson);
		pd.setValaditionDate(valaditionDate);
		pd.setValaditonApprovePerson(valaditonApprovePerson);
		pd.setValaditonApproveAdvice(valaditonApproveAdvice);
		pd.setValaditonApproveDate(valaditonApproveDate);
		pd.setEnginnerPerson(enginnerPerson);
		pd.setEnginnerAdvice(enginnerAdvice);
		pd.setEnginnerDate(enginnerDate);
		pd.setQualityApproveSignature(qualityApproveSignature);
		pd.setValaditonApproveSignature(valaditonApproveSignature);
		pd.setEnginnerSignature(enginnerSignature);
		return pd;
	}
	
	/**
	 * 获取当前状态的流转信息
	 * @param status 状态
	 * @param flowList 流转列表
	 * @return
	 */
	public Flows getStatusPersonAndDate(String status, List<Flows> flowList)
	{
		for (int i = 0; i < flowList.size(); i++)
		{
			Flows flow = flowList.get(i);
			String type = flow.getType();
			if (type.equals(status)) { return flow; }
		}
		return null;
	}
	
	/**
	 * 获取用户签章
	 * @param userNo 工号
	 * @return 签章地址
	 */
	public String getUserSignature(String userNo)
	{
		User user = new User();
		user.setUserNo(userNo);
		List<User> users = userDao.getUserByNo(user);
		if (users != null)
		{
			if (users.size() > 0) { return users.get(0).getUserImg(); }
		}
		return null;
	}
}
