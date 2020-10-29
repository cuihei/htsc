package com.ht.service.inter.system.workflow.taskconfig;

import java.util.List;

import com.ht.workflow.common.TaskDef;
import com.ht.workflow.exception.WorkflowException;

/**
 * 流程任务配置业务处理接口
 * @author 王有为
 * @date 2016/10/26
 */
public interface TaskConfigurationService {

	/**
	 * 获取流程 定义任务列表
	 * @param processDefId 流程定义ID
	 * @return 定义任务列表
	 */
	public List<TaskDef> getTaskListByProcessDefId(String processDefId) throws WorkflowException;
}
