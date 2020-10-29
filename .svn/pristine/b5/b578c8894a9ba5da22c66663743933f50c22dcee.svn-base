package com.ht.persistence.dao.inter.system.workflow.task;

import java.util.List;

import com.ht.persistence.model.system.workflow.task.Flows;

/**
 * 流程流转状态数据访问接口
 * @author 王有为
 * @date 2016/11/1
 */
public interface FlowsDao {

	/**
	 * 获得流转状态
	 * @param flows 流转状态实体
	 * @return 流转状态集合
	 */
	public List<Flows> getProcessFlows(Flows flows);
	
	/**
	 * 获取该月完成数量
	 * @return 完成数量
	 * @throws Exception 
	 */
	public int countCompleteByMonth() throws Exception;

	List<Flows> getProcessFlowsByProcessInstIdAndTypes(String processInstId, List<String> types);
	/**
	 * 根据流程id 和任务id 获取流程
	 * @param processInstId
	 * @param taskId
	 * @return
	 */
	public List<Flows> getProcessFlowsByProcessInstIdAndTaskDefId(String processInstId,List<String> taskDefId);
}
