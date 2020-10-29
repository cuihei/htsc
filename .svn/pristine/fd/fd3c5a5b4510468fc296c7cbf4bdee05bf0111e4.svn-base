package com.ht.persistence.dao.impl.complication.formprop;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;

import com.ht.persistence.dao.impl.base.BaseDaoImpl;
import com.ht.persistence.dao.inter.complication.formprop.ProcessPlanDao;
import com.ht.persistence.model.complication.formprop.ProcessPlan;

import sun.org.mozilla.javascript.internal.Undefined;

/**
 * FormValue 接口的实现类
 * @author 侯晨
 */
public class ProcessPlanDaoImpl extends BaseDaoImpl implements ProcessPlanDao
{

	/**
	 * 增加一个ProcessPlan
	 * @param ProcessPlan ProcessPlan实体
	 */
	@Override
	public void addProcessPlan(ProcessPlan processPlan)
	{
		this.save(processPlan);
	}

	/**
	 * 更新一个ProcessPlan
	 * @param ProcessPlan ProcessPlan实体
	 */
	@Override
	public void modifyProcessPlan(ProcessPlan processPlan)
	{
		processPlan = (ProcessPlan) this.merge(processPlan);
		this.update(processPlan);
	}

	/**
	 * 删除processPlan 相关
	 * @param processPlan processPlan对象
	 */
	@Override
	public void delProcessPlan(ProcessPlan processPlan)
	{
		this.delete(processPlan);
	}


	/**
	 * 获取一条ProcessPlan
	 * @param ProcessPlan ProcessPlan对象getProcessPlan
	 * @return ProcessPlan实体
	 */
	@Override
	public ProcessPlan getProcessPlan(ProcessPlan processPlan)
	{
		@SuppressWarnings("unchecked")
		List<ProcessPlan> result = this.findByNamedQueryAndNamedParam("getById", "id", processPlan.getId());
		if (result.size() > 0) { return result.get(0); }
		return null;
	}

	@Override
	public List<ProcessPlan> getByProcess(ProcessPlan processPlan) {
		String[] params = {"taskDefId","parentProcessInstId"};
		String[] values = {processPlan.getTaskDefId(),processPlan.getParentProcessInstId()};
		List<ProcessPlan> list = this.findByNamedQueryAndNamedParam("getByProcess",params,values);
		return list;
	}
	/*判断是否有退回的流程 2018.9.10*/
	@Override
	public String getProcessByBack(String processinstid, String taskInstId, String userno) {

		  String result2="0";
		  /*			  
		  //获取退回的最大值
			 String sqlcount = "SELECT max(task_id) from PROCESS_FLOW where  TASK_RESULT='退回'  AND  PROCESS_INST_ID ='" +  processinstid + "'";
			 Query query1 = getSession().createSQLQuery(sqlcount);
			 Integer maxtaskid=(Integer.valueOf(((Number)query1.uniqueResult()).intValue())).intValue();
		  	 
	  	 //获取表单中版本值为空的最大值和最小值
		 	 
			 String sqlmax = "SELECT to_number(min(task_inst_id)) from form_value where  PROCESS_INST_ID='"+processinstid+"'  and form_bb is null";
			 Query query2 = getSession().createSQLQuery(sqlmax);
			 Integer maxstr=(Integer.valueOf(((Number)query2.uniqueResult()).intValue())).intValue();
		  	 
			 String sqlmin = "SELECT to_number(min(task_inst_id)) from form_value where  PROCESS_INST_ID='"+processinstid+"'  and form_bb is null";
			 Query query3 = getSession().createSQLQuery(sqlmax);
			 Integer minstr=(Integer.valueOf(((Number)query3.uniqueResult()).intValue())).intValue();
		  	 
		if(maxtaskid>Integer.valueOf(taskInstId))  {
			
			return maxtaskid.toString();
		}
		
		*/
		 
		  String count = "SELECT count(*) from PROCESS_FLOW where TASK_RESULT='退回'  AND PROCESS_INST_ID ='" + processinstid + "'";
		  Query query = getSession().createSQLQuery(count);
		  Integer sqlint =(Integer.valueOf(((Number)query.uniqueResult()).intValue())).intValue();

		  if(sqlint>=1) {
				 String sqlcount = "SELECT max(task_id) from PROCESS_FLOW where  TASK_RESULT='退回'  AND  PROCESS_INST_ID ='" +  processinstid + "'";
				 Query query1 = getSession().createSQLQuery(sqlcount);
				 String str = query1.uniqueResult().toString(); 
				 return String.valueOf(str);
		  }

		  		  
		  
		  
		  
		 return result2;
	}

	/*判断是否有退回的流程 2018.9.10*/
/*		@Override
public String getProcessByBack(String processinstid, String taskInstId, String userno)
{
			
	
 String sqlcount = "SELECT max(task_id) from FORM_VALUE where CREATOR='"+userno+"' and TASK_INST_ID='"+taskInstId+"'  and TASK_RESULT='退回' AND AND  PROCESS_INST_ID ='" +  processinstid + "'";
 
 Query query1 = getSession().createSQLQuery(sqlcount);
 String str = query1.uniqueResult().toString();  
 return String.valueOf(str); 

}
*/


	
	
}
