package com.ht.service.inter.complication.formprop;

import java.util.List;

import com.ht.persistence.model.complication.formprop.ProcessPlan;

/**
 * ProcessPlanService接口
 */
public interface ProcessPlanService {
	/**
	  * 保存
	  * @param processPlan
	  * @throws Exception
	  */
	public void addProcessPlan(String processPlan) throws Exception;
	
	/**
	  * 修改
	  * @param processPlan
	  * @throws Exception
	  */
	public void modifyProcessPlan(String processPlan) throws Exception;
	
	/**
	  * 删除
	  * @param id
	  * @throws Exception
	  */
	public void delProcessPlan(String processPlan) throws Exception;
	
	
	/**
	  * 查询一条
	  * @param id
	  * @throws Exception
	  */
	public ProcessPlan getProcessPlan(String id) throws Exception;
	
	public List<ProcessPlan> getByProcess(String ProcessPlan);

	/*判断是否有退回的流程*/
	public String getProcessByBack(String processinstid,String taskInstId,String userno);
	

}
