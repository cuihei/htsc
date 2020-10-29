package com.ht.persistence.dao.impl.system.workflow.process;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.util.CollectionUtils;

import com.ht.persistence.dao.impl.base.BaseDaoImpl;
import com.ht.persistence.dao.inter.system.workflow.process.ProcessFlowDao;
import com.ht.persistence.model.background.application.Application;
import com.ht.persistence.model.complication.formprop.FormPropFormView;
import com.ht.persistence.model.complication.formprop.FormValue;
import com.ht.persistence.model.drawtask.taskbook.book.TaskBookEditHis;
import com.ht.persistence.model.drawtask.taskbook.book.TaskBookList;

import com.ht.persistence.model.system.workflow.process.ProcessFlow;

public class ProcessFlowDaoImpl extends BaseDaoImpl implements ProcessFlowDao
{

	@Override
	public ProcessFlow getProcessFlow(ProcessFlow processFlow) throws Exception
	{
		List<ProcessFlow> result = null;
		try
		{
			// 获取
			result = this.findByNamedQueryAndNamedParam("findProcessFlowById", "id", processFlow.getId());
			if (CollectionUtils.isEmpty(result)) { return null; }
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			return null;
		}
		return result.get(0);
	}

	@Override
	public List<ProcessFlow> getProcessFlowList() throws Exception
	{
		List<ProcessFlow> list = null;
		try
		{
			// 获取目录明细列表
			list = this.findByNamedQuery("findProcessFlowList");
		}
		catch (Exception e)
		{
			return null;
		}
		return list;
	}

	@Override
	public List<ProcessFlow> getProcessFlowByProcessInstId(String processInstId) throws Exception
	{
		List<ProcessFlow> list = null;
		try
		{
			// 获取目录明细列表
			list = this.findByNamedQueryAndNamedParam("getProcessFlowByProcessInstId", "processInstId", processInstId);
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			return null;
		}
		return list;
	}

	@Override
	public void addProcessFlow(ProcessFlow processFlow) throws Exception
	{
		this.save(processFlow);
	}

	@Override
	public void modifyProcessFlow(ProcessFlow processFlow) throws Exception
	{
		this.update(processFlow);
	}

	@Override
	public void delProcessFlow(ProcessFlow processFlow) throws Exception
	{
		this.delete(processFlow);
	}
	
	@Override
	public ProcessFlow getProcessFlowByAdvice(String advice,String processInstId) {
		
		SQLQuery query = this.getSession().createSQLQuery("select * from process_flow t where t.advice like ? and t.process_inst_id = ? order by CREATION_DATE desc");
		query.setString(0,"%"+advice+"%");
		query.setString(1,processInstId);
		query.addEntity(ProcessFlow.class);
		
		@SuppressWarnings("unchecked")
		List<ProcessFlow> appList =  query.list();
		if(appList != null){
			if(appList.size()>0){
				return appList.get(0);
			}
		}
		return null;
	}
	
	@Override
	public ProcessFlow getProcessFlowByAdvice1(String advice,String processInstId) {
		
		SQLQuery query = this.getSession().createSQLQuery("select * from process_flow t where t.advice1 like ? and t.process_inst_id = ? order by CREATION_DATE desc");
		query.setString(0,"%"+advice+"%");
		query.setString(1,processInstId);
		query.addEntity(ProcessFlow.class);
		
		@SuppressWarnings("unchecked")
		List<ProcessFlow> appList =  query.list();
		if(appList != null){
			if(appList.size()>0){
				return appList.get(0);
			}
		}
		return null;
	}

	
	
	
	
	


/*	
	@SuppressWarnings("unchecked")
	@Override
	public List<ProcessFlow> getProcessFlowBytaskResult(String taskResult)
	{				
		String taskname="'纸海图计划质检','审核质检结果','纸海图计划审定','制图事业部科长审核','制图事业部科长确认','质量检验科长审核','总工程师审核填写质量验收记录'";
		
		try {
			SQLQuery query = this.getSession().createSQLQuery("select CREATOR,CREATION_DATE,ERRJPG1,ERRTXT1,ERRJPG2,ERRTXT2,ERRJPG3,ERRTXT3,TASKNAME from process_flow where  Task_Result=? and taskname in(?)");
			query.setString(0,taskResult);
			query.setString(0,taskname);
			query.addEntity(ProcessFlow.class);
			List<ProcessFlow> list = query.list();
			return list;
		} catch (HibernateException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return null;
		
	}*/

	
	/**
	 * 通过流程ID，流程任务ID获取所有FormValue对象
	 * @param FormValue FormValue对象
	 * @return List<FormValue> FormValue实体集合
	 */

	
		
	/**
	 * 获取所有FormPropFormView对象集合
	 * @return List<FormProp>列表
	 */

	
	
	
	
	
	
	
}