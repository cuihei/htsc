package com.ht.service.impl.system.workflow.taskconfig;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.activiti.bpmn.model.UserTask;
import org.activiti.engine.repository.ProcessDefinition;

import com.ht.service.inter.system.workflow.taskconfig.TaskConfigurationService;
import com.ht.workflow.common.TaskDef;
import com.ht.workflow.exception.WorkflowException;
import com.ht.workflow.service.IWorkflowService;

public class TaskConfigurationServiceImpl implements TaskConfigurationService{

	@Resource
	IWorkflowService service;
	
	@Override
	/**
	 * 获取流程定义任务列表
	 * @param processDefId 流程定义ID
	 * @return 定义任务列表
	 */
	public List<TaskDef> getTaskListByProcessDefId(String processDefId) throws WorkflowException{
		try {
			// 定义返回结果实例
			List<TaskDef> result = new ArrayList<TaskDef>();
			// 获取流程定义信息
			ProcessDefinition prcessDef = service.getProcessDefinitionId(processDefId);
			// 获取定义任务列表
			List<UserTask> userTaskList = service.getUserTasks(processDefId);
			// 装载自定义流程定义任务对象
			for (int i = 0; i < userTaskList.size(); i++) {
				UserTask userTask = userTaskList.get(i);
				TaskDef task = new TaskDef();
				task.setProcessDefId(processDefId);
				task.setProcessDefName(prcessDef.getName());
				task.setTaskDefId(userTask.getId());
				task.setTaskDefName(userTask.getName());
				task.setGroup(userTask.getCandidateGroups());
				task.setProcessDefKey(prcessDef.getKey());
				result.add(task);
			}
			// 返回结果
			return result;
		} catch (Exception e) {
			throw new WorkflowException("ERROR_WORKFLOW_ENGINE");
		}
	}
}
