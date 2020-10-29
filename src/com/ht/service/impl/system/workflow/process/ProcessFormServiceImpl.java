package com.ht.service.impl.system.workflow.process;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;

import com.ht.common.util.DataConverter;
import com.ht.common.util.GenerateSequenceUtil;
import com.ht.common.util.LogHelper;
import com.ht.exception.DBException;
import com.ht.persistence.dao.inter.complication.formprop.FormPropDao;
import com.ht.persistence.dao.inter.system.workflow.process.ProcessFormDao;
import com.ht.persistence.model.complication.formprop.FormProp;
import com.ht.persistence.model.system.workflow.process.ProcessForm;
import com.ht.persistence.model.system.workflow.process.ProcessFormProp;
import com.ht.service.inter.system.workflow.process.ProcessFormService;

public class ProcessFormServiceImpl implements ProcessFormService
{

	/**
	 * 注入目录Dao
	 */
	@Resource(name = "processFormDao")
	private ProcessFormDao processFormDao;

	@Override
	public ProcessForm getProcessFormById(String id) throws Exception
	{
		ProcessForm processForm = new ProcessForm();
		processForm.setId(id);
		return processFormDao.getProcessForm(processForm);
	}

	@Override
	public List<ProcessForm> getProcessFormList() throws Exception
	{
		try
		{
			// 获取所有列表
			List<ProcessForm> list = processFormDao.getProcessFormList();
			return list;
		}
		catch (Exception e)
		{
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(), e);
			// 抛出异常
			throw e;
		}
	}

	/**
	 * 查找所有实体对象集合
	 * @return 所有实体对象集合
	 */
	@Override
	public ProcessForm getProcessFormByProAndTask(String processDefId, String taskDefId) throws Exception
	{
		try
		{
			ProcessForm processForm = new ProcessForm();
			processForm.setProcessDefId(processDefId);
			processForm.setTaskDefId(taskDefId);
			return processFormDao.getProcessFormByProAndTask(processForm);
		}
		catch (Exception e)
		{
			throw new DBException("数据库操作错误");
		}

	}

	/**
	 * 查找所有实体对象集合
	 * @return 所有实体对象集合
	 */
	@Override
	public List<ProcessForm> getProcessFormByProcessDefId(String processDefId) throws Exception
	{
		try
		{
			ProcessForm processForm = new ProcessForm();
			processForm.setProcessDefId(processDefId);
			return processFormDao.getProcessFormByProcessDefId(processForm);
		}
		catch (Exception e)
		{
			throw new DBException("数据库操作错误," + e.getMessage());
		}
	}

	@Resource
	FormPropDao formPropDao;
	
	@Override
	public void addOrUodateProcessForm(String processForm) throws Exception
	{
		try
		{
			ProcessForm pForm = (ProcessForm) DataConverter.convertJson2Object(processForm, ProcessForm.class);
			ProcessForm proForm = this.getProcessFormByProAndTask(pForm.getProcessDefId(), pForm.getTaskDefId());
			if (proForm != null)
			{
				processFormDao.delProcessForm(proForm);
			}
			if(!pForm.getFormId().equals("0")&&!pForm.getFormId().contains("请选择")){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			if (StringUtils.isBlank(pForm.getId()))
			{
				pForm.setId(GenerateSequenceUtil.generateSequenceNo());
				// 执行添加操作
				processFormDao.addProcessForm(pForm);
			}
			else
			{
				// 执行保存操作
				processFormDao.modifyProcessForm(pForm);
			}
			// 增加processFormProp
			List<ProcessFormProp> pfps = processFormDao.getProcessFormProp(pForm.getProcessDefId(),pForm.getTaskDefId());
			if (pfps != null)
			{
				processFormDao.deleteProcessFormProp(pfps);
				FormProp formProp = new FormProp();
				formProp.setFormId(pForm.getFormId());;
				List<FormProp> propList = formPropDao.getFormPropByFormId(formProp);
				if (propList != null)
				{
					for (int i = 0; i < propList.size(); i++)
					{
						FormProp prop = propList.get(i);
						ProcessFormProp pfp = new ProcessFormProp();
						pfp.setId(GenerateSequenceUtil.generateSequenceNo());
						pfp.setSelectData(prop.getSelectData());
						pfp.setRequired(prop.getRequired());
						pfp.setPropType(prop.getPropType());
						pfp.setPropName(prop.getPropName());
						pfp.setPropKey(prop.getPropKey());
						pfp.setProcessFormId(pForm.getId());
						pfp.setFormPropId(prop.getId());
						pfp.setNum(prop.getNum());
						pfp.setSelectDic(prop.getSelectDic());
						pfp.setEditAble("是");
						pfp.setDefaultValue(prop.getPropDefaultValue());
						processFormDao.saveProcessFormProp(pfp);
					}
				}
			}
			}
		}
		catch (Exception e)
		{
			throw e;
		}
		
	}

	@Override
	public void delProcessForm(String ids) throws Exception
	{
		// 将用户String类型转成ProcessFlow对象
		List<ProcessForm> list = (List<ProcessForm>) DataConverter.convertJson2List(ids, ProcessForm.class);
		for (int i = 0; i < list.size(); i++)
		{
			// 删除对象
			processFormDao.delProcessForm(list.get(i));
		}
	}

	/**
	 * 获取流程表单任务关联的表单属性
	 */
	@Override
	public List<ProcessFormProp> getProcessFormProp(String processDefId, String taskDefId) throws Exception
	{
		try
		{
			return processFormDao.getProcessFormProp(processDefId, taskDefId);
		}
		catch (Exception e)
		{
			throw e;
		}
	}
	
	/**
	 * 获取流程表单任务关联的表单属性
	 */
	@Override
	public List<ProcessFormProp> getProcessFormProp(String formId) throws Exception
	{
		try
		{
			return processFormDao.getProcessFormProp(formId);
		}
		catch (Exception e)
		{
			throw e;
		}
	}


	@Override
	public void addProcessFormProp(String processFormProps) throws Exception
	{
		try
		{
			List<ProcessFormProp> list = (List<ProcessFormProp>) DataConverter.convertJson2List(processFormProps, ProcessFormProp.class);
			for (int i = 0; i < list.size(); i++)
			{
				
				processFormDao.saveProcessFormProp(list.get(i));
			}
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}
