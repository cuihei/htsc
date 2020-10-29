package com.ht.service.impl.experiencebook;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.ht.common.util.DataConverter;
import com.ht.common.util.SpringUtil;
import com.ht.persistence.dao.inter.background.dicdata.defectform.DefectFormDao;
import com.ht.persistence.dao.inter.background.organization.employee.UserDao;
import com.ht.persistence.dao.inter.complication.formprop.FormValueDao;
import com.ht.persistence.dao.inter.system.workflow.process.ProcessFlowDao;
import com.ht.persistence.dao.inter.system.workflow.publish.VProcessDetailDao;
import com.ht.persistence.dao.inter.system.workflow.task.FlowsDao;
import com.ht.persistence.dao.inter.system.workflow.task.TaskDao;
import com.ht.persistence.model.background.dicdata.defectform.DefectForm;
import com.ht.persistence.model.background.organization.employee.User;
import com.ht.persistence.model.complication.formprop.FormValue;
import com.ht.persistence.model.system.workflow.process.ProcessFlow;
import com.ht.persistence.model.system.workflow.publish.VProcessDetail;
import com.ht.persistence.model.system.workflow.task.Flows;
import com.ht.persistence.model.system.workflow.task.HiTask;
import com.ht.service.constant.experiencebook.ChartStatusConstants;
import com.ht.service.constant.experiencebook.electron.EleQualityRecored;
import com.ht.service.constant.experiencebook.electron.EleValidationRecord;
import com.ht.service.constant.experiencebook.paper.PaperQualityRecored;
import com.ht.service.constant.experiencebook.paper.PaperValidationRecord;
//import com.ht.service.constant.experiencebook.sourcedata.ErrorNotes;
import com.ht.service.constant.experiencebook.sourcedata.QualityRecored;
import com.ht.service.constant.experiencebook.sourcedata.ValidationRecord;
import com.ht.service.constant.model.PersonAndDate;
import com.ht.workflow.service.IWorkflowService;
import com.sun.star.util.Date;

public class Base
{
	TaskDao taskDao = null;

	FormValueDao formValueDao = null;
	
	ProcessFlowDao processFlowDao = null;

	DefectFormDao defectFormDao = null;

	FlowsDao flowsDao = null;

	UserDao userDao = null;

	VProcessDetailDao vProcessDetailDao = null;

	public Base()
	{
		taskDao = (TaskDao) SpringUtil.getBean("taskDao");
		formValueDao = (FormValueDao) SpringUtil.getBean("formValueDao");
		processFlowDao = (ProcessFlowDao) SpringUtil.getBean("processFlowDao");
		defectFormDao = (DefectFormDao) SpringUtil.getBean("defectFormDao");
		flowsDao = (FlowsDao) SpringUtil.getBean("flowsDao");
		userDao = (UserDao) SpringUtil.getBean("userDao");
		vProcessDetailDao = (VProcessDetailDao) SpringUtil.getBean("vProcessDetailDao");
	}

	/**
	 * 根据父流程实例ID和流程类型获取第一条历史任务
	 * @param processInstId 父流程实例ID
	 * @param processDefKey 流程定义Key
	 * @return 历史任务
	 */
	public HiTask getFirstHiTask(String processInstId, String processDefKey)
	{
		List<HiTask> hiTaskList = taskDao.getHiTaskByParentProcessIdAndProcessDefKey(processInstId, processDefKey);
		HiTask task = null;
		if (hiTaskList != null)
		{
			if (hiTaskList.size() > 0)
			{
				task = hiTaskList.get(0);
			}
		}
		return task;
	}

	/**
	 * @param parentProcessInstId
	 * @param processInstId
	 * @return
	 */
	public VProcessDetail getDetailRecordId(String parentProcessInstId, String processInstId)
	{
		VProcessDetail detail = null;
		if (StringUtils.isNotEmpty(parentProcessInstId))
		{
			detail = vProcessDetailDao.getProcessDetailByProcessInstId(parentProcessInstId);
		}
		else
		{
			detail = vProcessDetailDao.getProcessDetailByProcessInstId(processInstId);
		}
		return detail;
	}

	/**
	 * 获取表单某一个属性的所有数据
	 * @param formId 表单ID
	 * @param processInstId 流程实例ID
	 * @param propKey 表单属性键
	 * @return 表单属性数据
	 */
	public List<FormValue> getFormValues(String formId, String processInstId, String propKey)
	{
		FormValue formValue = new FormValue();
		formValue.setFormId(formId);
		formValue.setProcessInstId(processInstId);
		formValue.setPropKey(propKey);
		List<FormValue> formValues = formValueDao.getFormValueByPidAndFidAndPropKey(formValue);
		return formValues;
	}

	/**
	 * 获取表单多行数据
	 * @param formId 表单ID
	 * @param processInstId 流程实例ID
	 * @return 表单多行数据
	 */
	public List<List<FormValue>> getFormValueByRowFlag(String formId, String processInstId)
	{
		List<List<FormValue>> result = new ArrayList<List<FormValue>>();
		FormValue formValue = new FormValue();
		formValue.setFormId(formId);
		formValue.setProcessInstId(processInstId);
		List<FormValue> formValues = formValueDao.getFormValueByProcessInstId(formValue);
		List<String> rowFlags = new ArrayList<String>();
		List<String> FROM_BBS = new ArrayList<String>();
		int num=0;
		for (int i = 0; i < formValues.size(); i++)
		{
			FormValue formValueI = formValues.get(i);
			
			  if (!rowFlags.contains(formValueI.getRowFlag())) {
			  rowFlags.add(formValueI.getRowFlag());
			  
			  }
			 
			
			///////////////分割提取出来的进行版本比对，取最大一版 2019.11.6//////////////////////////
			/*
			 * String str=formValueI.getFormBb(); String strRowFlag=null;
			 * if(str!=null) { strRowFlag=str.replaceAll("\\D+",""); try{ int
			 * nums = Integer.parseInt(strRowFlag); if(nums>=num) { num=nums;
			 * 
			 * if (!rowFlags.contains(formValueI.getRowFlag())) {
			 * rowFlags.add(formValueI.getRowFlag());
			 * 
			 * }
			 * 
			 * }
			 * 
			 * }catch(Exception e){ System.err.println("转换失败"); }
			 * 
			 * }else if (!rowFlags.contains(formValueI.getRowFlag())) {
			 * rowFlags.add(formValueI.getRowFlag());
			 * 
			 * }
			 */
			/////////////////////////////////////////////////////////////////////////////////
			
			
			
			
			
				
		}

		
		for (int i = 0; i < rowFlags.size(); i++)
		{
			List<FormValue> valueList = new ArrayList<FormValue>();
			String rowFlag = rowFlags.get(i);
			for (int j = 0; j < formValues.size(); j++)
			{
				FormValue formValueJ = formValues.get(j);
				String rowFlagJ = formValueJ.getRowFlag();
				if (rowFlagJ.equals(rowFlag))
				{
					valueList.add(formValueJ);
				}
			}
			result.add(valueList);
		}
		return result;
	}

	
	
	/**
	 * 错误说明获取表单多行数据
	 * @return 表单多行数据
	 */
	public List<ProcessFlow> getProcessFlowByErrlist(String processInstId)
	{
		/*List<List<ProcessFlow>> result = new ArrayList<List<ProcessFlow>>();
		ProcessFlow processFlow = new ProcessFlow();
		processFlow.setProcessInstId(processInstId);
		List<ProcessFlow> processFlows = processFlowDao.getProcessFlowByPid(processFlow);
	
		return processFlows;*/
		
		return null;
	}
	
	
	
	
	/**
	 * 获取当前状态的流转信息
	 * @param status 状态
	 * @param flowList 流转列表
	 * @return
	 */
	public Flows getStatusPersonAndDate(String status, List<Flows> flowList)
	{
		if (flowList != null)
		{

			for (int i = 0; i < flowList.size(); i++)
			{
				Flows flow = flowList.get(i);
				String type = flow.getType();
				if (type.equals(status)) { return flow; }
			}
		}
		return null;
	}

	/**
	 * 缺陷记录表 （质检）
	 * @param workProcessInstId 编绘流程ID
	 * @param compilationPerson 编绘人
	 * @param compilationDate 编绘日期
	 * @param qualityPerson 质检人
	 * @param qualityDate 质检日期
	 * @param notice 通告改正至
	 * @return
	 */
	public Map<String, Object> getQualityDefective(String workProcessInstId, String compilationPerson, String compilationDate, String qualityPerson,
			String qualityDate, String notice)
	{
		QualityRecored qualityRecored = new QualityRecored();
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		List<DefectForm> defectFormList = defectFormDao.getDefectFormListByProcessInstId(workProcessInstId, ChartStatusConstants.QUALITY_LIKE);
		String total = null;
		String coefficient = null;
		String actual = null;
		String grading = null;
		if (defectFormList != null)
		{
			if (defectFormList.size() > 0)
			{
				for (int i = 0; i < defectFormList.size(); i++)
				{
					DefectForm defectForm = defectFormList.get(i);
					total = defectForm.getTotal();
					coefficient = defectForm.getCoefficient();
					actual = defectForm.getActual();
					grading = defectForm.getGrading();
					qualityRecored = new QualityRecored();
					// 设置图层
					qualityRecored.setZJJLB_LAYER(defectForm.getLayerName());
					String problem = null;
					if (StringUtils.isNotEmpty(defectForm.getDefectitem()))
					{
						problem =  defectForm.getDefectitem();
					}
					if (StringUtils.isNotEmpty(problem))
					{
						if (StringUtils.isNotEmpty(defectForm.getOpinion()))
						{
							problem = problem + " "+ defectForm.getOpinion();
						}
					}
					// 设置存在问题
					//qualityRecored.setZJJLB_PROBLEM(problem);
					// 设置处理意见
					 qualityRecored.setZJJLB_OPINION(defectForm.getOpinion());
					// 设置缺陷类别
					qualityRecored.setZJJLB_DEEP(defectForm.getDeep());
					// 设置缺陷个数
					qualityRecored.setZJJLB_NUM(defectForm.getNumber());
					// 设置缺陷扣分
					qualityRecored.setZJJLB_SCORE(defectForm.getScore());
					// 设置备注
					qualityRecored.setZJJLB_REMARKS(defectForm.getRemarks());
					listMap.add(qualityRecored.getQualityRecoredMap());
				}
				qualityRecored = new QualityRecored();
				// 设置缺陷累计扣分
				qualityRecored.setZJJLB_TOTAL(total);
				// 设置调整系数
				qualityRecored.setZJJLB_COEFFICIENT(coefficient);
				// 设置实际扣分
				qualityRecored.setZJJLB_ACTUAL(actual);
				// 设置质量评分
				qualityRecored.setZJJLB_GRADING(grading);

				
			}
		}
		// 设置编绘人
		qualityRecored.setZJJLB_COMPILATION_PERSON(compilationPerson);
		// 设置编绘时间
		qualityRecored.setZJJLB_COMPILATION_DATE(compilationDate);
		// 设置质检人
		qualityRecored.setZJJLB_QUALITY_PERSON(qualityPerson);
		// 设置质检日期
		qualityRecored.setZJJLB_QUALITY_DATE(qualityDate);
		// 设置通告改正至
		qualityRecored.setZJJLB_NOTICE(notice);
		Map<String, Object> result = qualityRecored.getQualityRecoredMap();
		result.put(qualityRecored.MARKER, listMap);
		return result;
	}

	/**
	 * 缺陷记录表 （审定）
	 * @param workProcessInstId 编绘流程ID
	 * @param validationPerson 审定人
	 * @param validationDate 审定日期
	 * @param validationPerson 审定科长
	 * @param validationDate 科长审核日期
	 * @param notice 通告改正至
	 * @return
	 */
	public Map<String, Object> getValidationDefective(String workProcessInstId, String validationPerson, String validationDate, String chiefPerson,
			String chiefDate, String notice)
	{
		ValidationRecord validationRecored = new ValidationRecord();
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		List<DefectForm> defectFormList = defectFormDao.getDefectFormListByProcessInstId(workProcessInstId, ChartStatusConstants.APPROVE_LIKE,ChartStatusConstants.APPROVE2_LIKE);
		String total = null;
		String coefficient = null;
		String actual = null;
		String grading = null;
		if (defectFormList != null)
		{
			if (defectFormList.size() > 0)
			{
				for (int i = 0; i < defectFormList.size(); i++)
				{
					DefectForm defectForm = defectFormList.get(i);
					total = defectForm.getTotal();
					coefficient = defectForm.getCoefficient();
					actual = defectForm.getActual();
					grading = defectForm.getGrading();
					validationRecored = new ValidationRecord();
					// 设置图层
					validationRecored.setSDJLB_LAYER(defectForm.getLayerName());
					// 设置存在问题
					// validationRecored.setSDJLB_PROBLEM(defectForm.getDefectitem() + defectForm.getDiscription());
					// 设置处理意见
					validationRecored.setSDJLB_OPINION(defectForm.getOpinion());
					// 设置缺陷类别
					validationRecored.setSDJLB_DEEP(defectForm.getDeep());
					// 设置缺陷个数
					validationRecored.setSDJLB_NUM(defectForm.getNumber());
					// 设置缺陷扣分
					validationRecored.setSDJLB_SCORE(defectForm.getScore());
					// 设置备注
					validationRecored.setSDJLB_REMARKS(defectForm.getRemarks());
					listMap.add(validationRecored.getValidationRecordMap());
				}
				validationRecored = new ValidationRecord();
				// 设置缺陷累计扣分
				validationRecored.setSDJLB_TOTAL(total);
				// 设置调整系数
				validationRecored.setSDJLB_COEFFICIENT(coefficient);
				// 设置实际扣分
				validationRecored.setSDJLB_ACTUAL(actual);
				// 设置质量评分
				validationRecored.setSDJLB_GRADING(grading);

				
			}
		}
		// 设置审定人
		validationRecored.setVALIDATION(validationPerson);
		// 设置审定时间
		validationRecored.setVALIDATION_DATE(validationDate);
		// 设置科长
		validationRecored.setCHIEF(chiefPerson);
		// 设置科长审核时间
		validationRecored.setCHIEF_DATE(chiefDate);
		// 设置通告改正至
		validationRecored.setNOTICE_TO_CORRECTION(notice);
		Map<String, Object> result = validationRecored.getValidationRecordMap();
		result.put(validationRecored.MARKER, listMap);
		return result;
	}

	/**
	 * 获取各状态操作人和时间
	 * @param workProcessInstId 流程ID
	 * @return 操作人时间
	 */
	public PersonAndDate getPersonAndDate(String workProcessInstId,List<Flows> flowList)
	{
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
			compilationPerson = getUserName(compilationPersonArg.toString());
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
			qualityPerson = getUserName(qualityPersonArg.toString());
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
			valaditionPerson = getUserName(valaditionPersonArg.toString());
		}
		flow = getStatusPersonAndDate(ChartStatusConstants.APPROVE_COMPLETE, flowList);
		if (flow != null)
		{
			if (flow.getStartTime() != null)
			{
				valaditionDate = format.format(flow.getStartTime());
			}
		}
		flow = getStatusPersonAndDate(ChartStatusConstants.APPROVE_APPROVE_COMPLETE, flowList);
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
	
	/**
	 * 获取用户签章
	 * @param userNo 工号
	 * @return 签章地址
	 */
	public String getUserName(String userId)
	{
		User user = new User();
		user.setId(userId);
		user = userDao.getUser(user);
		if (user != null)
		{
			return user.getUserName();
		}
		return null;
	}

	/**
	 * 纸海图缺陷记录表 （质检）
	 * @param workProcessInstId 编绘流程ID
	 * @param compilationPerson 编绘人
	 * @param compilationDate 编绘日期
	 * @param qualityPerson 质检人
	 * @param qualityDate 质检日期
	 * @param notice 通告改正至
	 * @return
	 */
	public Map<String, Object> getPaperQualityDefective(String workProcessInstId, String compilationPerson, String compilationDate,
			String qualityPerson, String qualityDate, String notice)
	{
		PaperQualityRecored qualityRecored = new PaperQualityRecored();
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		List<DefectForm> defectFormList = defectFormDao.getDefectFormListByProcessInstId(workProcessInstId, ChartStatusConstants.QUALITY_LIKE);
		String total = null;
		String coefficient = null;
		String actual = null;
		String grading = null;
		if (defectFormList != null)
		{
			if (defectFormList.size() > 0)
			{
				for (int i = 0; i < defectFormList.size(); i++)
				{
					DefectForm defectForm = defectFormList.get(i);
					total = defectForm.getTotal();
					coefficient = defectForm.getCoefficient();
					actual = defectForm.getActual();
					grading = defectForm.getGrading();
					qualityRecored = new PaperQualityRecored();
					// 设置存在问题
					// qualityRecored.setDISCRIPTION(defectForm.getDefectitem() + defectForm.getDiscription());
					// 设置处理意见
					qualityRecored.setOPINION(defectForm.getOpinion());
					// 设置质量扣分
					qualityRecored.setSCORE(defectForm.getScore());
					// 设置备注
					qualityRecored.setREMARKS(defectForm.getRemarks());
					listMap.add(qualityRecored.getQualityRecoredMap());
				}
				qualityRecored = new PaperQualityRecored();
				// 设置质量累计扣分
				qualityRecored.setTOTAL(total);
				// 设置调整系数
				qualityRecored.setCOEFFICIENT(coefficient);
				// 设置实际扣分
				qualityRecored.setACTUAL(actual);
				// 设置质量评分
				qualityRecored.setGRADING(grading);
				
			}
		}
		// 设置编绘人
		qualityRecored.setCOMPILATION_PERSON(compilationPerson);
		// 设置编绘时间
		qualityRecored.setCOMPILATION_DATE(compilationDate);
		// 设置质检人
		qualityRecored.setQUALITY_PERSON(qualityPerson);
		// 设置质检日期
		qualityRecored.setQUALITY_DATE(qualityDate);
		// 设置通告改正至
		qualityRecored.setNOTICE_TO_CORRECTION(notice);
		Map<String, Object> result = qualityRecored.getQualityRecoredMap();
		result.put(qualityRecored.MARKER, listMap);
		return result;
	}

	/**
	 * 纸海图缺陷记录表 （审定）
	 * @param workProcessInstId 编绘流程ID
	 * @param validationPerson 审定人
	 * @param validationDate 审定日期
	 * @param validationPerson 审定科长
	 * @param validationDate 科长审核日期
	 * @param notice 通告改正至
	 * @return
	 */
	public Map<String, Object> getPaperValidationDefective(String workProcessInstId, String validationPerson, String validationDate,
			String chiefPerson, String chiefDate, String notice)
	{
		PaperValidationRecord validationRecored = new PaperValidationRecord();
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		List<DefectForm> defectFormList = defectFormDao.getDefectFormListByProcessInstId(workProcessInstId, ChartStatusConstants.APPROVE_LIKE,ChartStatusConstants.APPROVE2_LIKE);
		String total = null;
		String coefficient = null;
		String actual = null;
		String grading = null;
		if (defectFormList != null)
		{
			if (defectFormList.size() > 0)
			{
				for (int i = 0; i < defectFormList.size(); i++)
				{
					DefectForm defectForm = defectFormList.get(i);
					total = defectForm.getTotal();
					coefficient = defectForm.getCoefficient();
					actual = defectForm.getActual();
					grading = defectForm.getGrading();
					validationRecored = new PaperValidationRecord();
					// 设置存在问题
					// validationRecored.setDISCRIPTION(defectForm.getDefectitem() + defectForm.getDiscription());
					// 设置处理意见
					validationRecored.setOPINION(defectForm.getOpinion());
					// 设置质量扣分
					validationRecored.setSCORE(defectForm.getScore());
					listMap.add(validationRecored.getValidationRecordMap());
				}
				validationRecored = new PaperValidationRecord();
				// 设置质量累计扣分
				validationRecored.setTOTAL(total);
				// 设置调整系数
				validationRecored.setCOEFFICIENT(coefficient);
				// 设置实际扣分
				validationRecored.setACTUAL(actual);
				// 设置质量评分
				validationRecored.setGRADING(grading);
			}
		}
		// 设置审定人
		validationRecored.setVALIDATION(validationPerson);
		// 设置审定时间
		validationRecored.setVALIDATION_DATE(validationDate);
		// 设置科长
		validationRecored.setCHIEF(chiefPerson);
		// 设置科长审核时间
		validationRecored.setCHIEF_DATE(chiefDate);
		// 设置通告改正至
		validationRecored.setNOTICE_TO_CORRECTION(notice);
		
		Map<String, Object> result = validationRecored.getValidationRecordMap();
		result.put(validationRecored.MARKER, listMap);
		return result;
	}

	/**
	 * 电子海图缺陷记录表 （质检）
	 * @param workProcessInstId 编绘流程ID
	 * @param compilationPerson 编绘人
	 * @param compilationDate 编绘日期
	 * @param qualityPerson 质检人
	 * @param qualityDate 质检日期
	 * @param notice 通告改正至
	 * @return
	 */
	public Map<String, Object> getEleQualityDefective(String workProcessInstId, String compilationPerson, String compilationDate,
			String qualityPerson, String qualityDate, String notice)
	{
		EleQualityRecored qualityRecored = new EleQualityRecored();
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		List<DefectForm> defectFormList = defectFormDao.getDefectFormListByProcessInstId(workProcessInstId, ChartStatusConstants.QUALITY_LIKE);
		String total = null;
		String coefficient = null;
		String actual = null;
		String grading = null;
		if (defectFormList != null)
		{
			if (defectFormList.size() > 0)
			{
				for (int i = 0; i < defectFormList.size(); i++)
				{
					DefectForm defectForm = defectFormList.get(i);
					total = defectForm.getTotal();
					coefficient = defectForm.getCoefficient();
					actual = defectForm.getActual();
					grading = defectForm.getGrading();
					qualityRecored = new EleQualityRecored();
					// 设置存在问题
					// qualityRecored.setDISCRIPTION(defectForm.getDefectitem() + defectForm.getDiscription());
					// 设置处理意见
					qualityRecored.setOPINION(defectForm.getOpinion());
					// 设置缺陷类别
					qualityRecored.setDEFECT_TYPE(defectForm.getDeep());
					// 设置缺陷个数
					qualityRecored.setNUM(defectForm.getNumber());
					// 设置缺陷扣分
					qualityRecored.setSCORE(defectForm.getScore());
					// 设置备注
					qualityRecored.setREMARKS(defectForm.getRemarks());
					listMap.add(qualityRecored.getQualityRecoredMap());
				}
				qualityRecored = new EleQualityRecored();
				// 设置缺陷累计扣分
				qualityRecored.setTOTAL(total);
				// 设置调整系数
				qualityRecored.setCOEFFICIENT(coefficient);
				// 设置实际扣分
				qualityRecored.setACTUAL(actual);
				// 设置质量评分
				qualityRecored.setGRADING(grading);
			}
		}
		// 设置编绘人
		qualityRecored.setCOMPILATION_PERSON(compilationPerson);
		// 设置编绘时间
		qualityRecored.setCOMPILATION_DATE(compilationDate);
		// 设置质检人
		qualityRecored.setQUALITY_PERSON(qualityPerson);
		// 设置质检日期
		qualityRecored.setQUALITY_DATE(qualityDate);
		// 设置通告改正至
		qualityRecored.setNOTICE_TO_CORRECTION(notice);
		
		Map<String, Object> result = qualityRecored.getQualityRecoredMap();
		result.put(qualityRecored.MARKER, listMap);
		return result;
	}

	/**
	 * 电子海图缺陷记录表 （审定）
	 * @param workProcessInstId 编绘流程ID
	 * @param validationPerson 审定人
	 * @param validationDate 审定日期
	 * @param validationPerson 审定科长
	 * @param validationDate 科长审核日期
	 * @param notice 通告改正至
	 * @return
	 */
	public Map<String, Object> getEleValidationDefective(String workProcessInstId, String validationPerson, String validationDate,
			String chiefPerson, String chiefDate, String notice)
	{
		EleValidationRecord validationRecored = new EleValidationRecord();
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		List<DefectForm> defectFormList = defectFormDao.getDefectFormListByProcessInstId(workProcessInstId, ChartStatusConstants.APPROVE_LIKE,ChartStatusConstants.APPROVE2_LIKE);
		String total = null;
		String coefficient = null;
		String actual = null;
		String grading = null;
		if (defectFormList != null)
		{
			if (defectFormList.size() > 0)
			{
				for (int i = 0; i < defectFormList.size(); i++)
				{
					DefectForm defectForm = defectFormList.get(i);
					total = defectForm.getTotal();
					coefficient = defectForm.getCoefficient();
					actual = defectForm.getActual();
					grading = defectForm.getGrading();
					validationRecored = new EleValidationRecord();
					// 设置存在问题
					// validationRecored.setDISCRIPTION(defectForm.getDefectitem() + defectForm.getDiscription());
					// 设置处理意见
					validationRecored.setOPINION(defectForm.getOpinion());
					// 设置缺陷类别
					validationRecored.setDEFECT_TYPE(defectForm.getDeep());
					// 设置缺陷个数
					validationRecored.setNUM(defectForm.getNumber());
					// 设置缺陷扣分
					validationRecored.setSCORE(defectForm.getScore());
					listMap.add(validationRecored.getValidationRecordMap());
				}
				validationRecored = new EleValidationRecord();
				// 设置缺陷累计扣分
				validationRecored.setTOTAL(total);
				// 设置调整系数
				validationRecored.setCOEFFICIENT(coefficient);
				// 设置实际扣分
				validationRecored.setACTUAL(actual);
				// 设置质量评分
				validationRecored.setGRADING(grading);

			}
		}
		// 设置审定人
		validationRecored.setVALIDATION(validationPerson);
		// 设置审定时间
		validationRecored.setVALIDATION_DATE(validationDate);
		// 设置科长
		validationRecored.setCHIEF(chiefPerson);
		// 设置科长审核时间
		validationRecored.setCHIEF_DATE(chiefDate);
		// 设置通告改正至
		validationRecored.setNOTICE_TO_CORRECTION(notice);
		
		Map<String, Object> result = validationRecored.getValidationRecordMap();
		result.put(validationRecored.MARKER, listMap);
		return result;
	}

	/**
	 * 获取工序流程
	 * @return
	 */
	public String getOrderNameByStatus(String status)
	{
		String orderName = null;
		switch (status)
		{
		case "开始":
			orderName = "工程创建";
			break;
		case "待编绘":
			orderName = "编绘";
			break;
		case "待质检":
			orderName = "部门质检";
			break;
		case "质检后改图":
			orderName = "改图";
			break;
		case "改后质检":
			orderName = "改后确认";
			break;
		case "待审定":
			orderName = "中心审定";
			break;
		case "审定后改图":
			orderName = "改图";
			break;
		case "改后审定":
			orderName = "改后确认";
			break;
		default:
			break;
		}
		return orderName;
	}

	/**
	 * 错误图错误说明列表  2018.11.25
	 * @param creator 创建人
	 * @param creatordate 创建日期
	 * @param taskname  节点名称
	 * @param errtxt1 错误说明一
	 * @param errjpg1 错误图片一
	 * @param errtxt2 错误说明二
	 * @param errjpg2 错误图片二
	 * @param errtxt3 错误说明三
	 * @param errjpg3 错误图片三
	 * @return
	 * 	
	纸海图计划质检 审核质检结果 纸海图计划审定 制图事业部科长审核 制图事业部科长确认 质量检验科长审核 总工程师审核填写质量验收记录
	 */
	public Map<String, Object> getProcessFlowByErrList1(String processInstId)

	{
		//ErrorNotes errnote = new ErrorNotes();
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
	//	List<ProcessFlow> processFlows =  processFlowDao.getProcessFlowByErrListId(processInstId);
		 String creator=null;
		 String creatordate=null;
		 String taskname=null;
		 String errtxt1=null;
		 String errjpg1=null;
		 String errtxt2=null;
		 String errjpg2=null;
		 String errtxt3=null;
		 String errjpg3=null;
		 
		 Date currentTime = new Date();
		 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	

return null;
	}
	
	
	
	
	
	
	
	
	
	
	
	public List<List<ProcessFlow>>getProcessFlowByErrList2(String processInstId)
	{
	/*	List<List<ProcessFlow>> result = new ArrayList<List<ProcessFlow>>();
		ProcessFlow processFlow = new ProcessFlow();

		processFlow.setProcessInstId(processInstId);
		List<ProcessFlow> processFlows =  processFlowDao.getProcessFlowByErrListId(processInstId);
		List<String> rowFlags = new ArrayList<String>();
		for (int i = 0; i < processFlows.size(); i++)
		{
			ProcessFlow processFlowI = processFlows.get(i);
			if (!rowFlags.contains(processFlowI.getTaskname()))
			{
				rowFlags.add(processFlowI.getTaskname());
			}
		}
		for (int i = 0; i < rowFlags.size(); i++)
		{
			List<ProcessFlow> valueList = new ArrayList<ProcessFlow>();
			String rowFlag = rowFlags.get(i);
			for (int j = 0; j < processFlows.size(); j++)
			{
				ProcessFlow processFlowJ = processFlows.get(j);
				String rowFlagJ = processFlowJ.getTaskname();
				if (rowFlagJ.equals(rowFlag))
				{
					valueList.add(processFlowJ);
				}
			}
			result.add(valueList);
		}*/
		return null;
	}
	
	
	

	public Map<String, Object>getProcessFlowByErrList(String processInstId)
	{
/*		ErrorNotes errObj = new ErrorNotes();
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		List<ProcessFlow>  processFlowsList =  processFlowDao.getProcessFlowByErrListId(processInstId);
		 String creator=null;
		 java.util.Date creatordate=null;
		 String taskname=null;
		 String errtxt1=null;
		 String errjpg1=null;
		 String errtxt2=null;
		 String errjpg2=null;
		 String errtxt3=null;
		 String errjpg3=null;
		 
		if (processFlowsList != null)
		{
			if (processFlowsList.size() > 0)
			{
				for (int i = 0; i < processFlowsList.size(); i++)
				{
					ProcessFlow errvale = processFlowsList.get(i);
					creator = errvale.getCreator();
					creatordate =errvale.getCreationDate();
					taskname = errvale.getTaskname();
					errtxt1 = errvale.getErrtxt1();
					errtxt2 = errvale.getErrtxt2();
					errtxt3 = errvale.getErrtxt3();
					errjpg1=errvale.getErrjpg1();
					errjpg2=errvale.getErrjpg2();
					errjpg3=errvale.getErrjpg3();
					//声明一个错误备注的对象
					errObj = new ErrorNotes();
					// 设置创建人
					errObj.setSUBMIT(creator);
					// 设置创建日期
					errObj.setDATE(creatordate.toString());
					// 设置节点名称
					errObj.setNAME(taskname);
					// 设置错误文本1
					errObj.setERRTXT1(errtxt1);
					// 设置错误文本2
					errObj.setERRTXT2(errtxt2);
					// 设置错误文本3
					errObj.setERRTXT3(errtxt3);
				    //设置错误图片1
					errObj.setERRJPG1(errjpg1);
				    //设置错误图片2
					errObj.setERRJPG2(errjpg2);
				    //设置错误图片3
					errObj.setERRJPG3(errjpg3);
                listMap.add(errObj.getErrorNotesMap());
				}
			}
		}
		Map<String, Object> result = errObj.getErrorNotesMap();
		result.put(errObj.MARKER, listMap);*/
		return null;
	}

	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
