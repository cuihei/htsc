package com.ht.service.impl.system.workflow.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.ht.common.util.ConfigLookupHelper;
import com.ht.common.util.EnvironmentUtil;

public class BusinessUtil
{

	private static BusinessUtil util = null;

	public static BusinessUtil getInstance()
	{
		if (util == null)
		{
			util = new BusinessUtil();
		}
		return util;
	}

	/**
	 * 获取业务数据的显示列集合
	 * @param processDefId 流程定义ID
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, String>> getBusinessColumns(String processDefKey)
	{
		// 读取xml文件
		String path = "/com/ht/workflow/config/business-configuration.cfg.xml";
		ConfigLookupHelper helper = ConfigLookupHelper.getInstance(EnvironmentUtil.getResourceUrl(path));
		String pathTable = "configuration/tables/table";
		List<Map<String, String>> listTable = (List<Map<String, String>>) helper.getAttributesValue(pathTable);
		// 列集合
		List<Map<String, String>> listColumn = new ArrayList<Map<String, String>>();
		if (listTable != null)
		{
			for (int i = 0; i < listTable.size(); i++)
			{
				if (processDefKey.equals(listTable.get(i).get("process")))
				{
					String pathColumn = "configuration/tables/table[@process='" + processDefKey + "']/columns/column";
					Map<String, String> colmun = null;
					List<String> colmunProps = helper.getAttributeValues(pathColumn, "prop");
					List<String> colmunWidths = helper.getAttributeValues(pathColumn, "width");
					List<String> colmunValues = helper.getValues(pathColumn);
					for (int j = 0; j < colmunProps.size(); j++)
					{
						colmun = new HashMap<String, String>();
						colmun.put("prop", colmunProps.get(j));
						colmun.put("value", colmunValues.get(j));
						colmun.put("width", colmunWidths.get(j));
						listColumn.add(colmun);
					}
				}
			}
		}
		return listColumn;
	}

	/**
	 * 获取业务数据的显示列集合
	 * @param processDefId 流程定义ID
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, String>> getBusinessColumnsByTableName(String tableName)
	{
		// 读取xml文件
		String path = "/com/ht/workflow/config/business-configuration.cfg.xml";
		ConfigLookupHelper helper = ConfigLookupHelper.getInstance(EnvironmentUtil.getResourceUrl(path));
		String pathColumn = "configuration/tables/table[@name='" + tableName + "']/columns/column";
		// 列集合
		List<Map<String, String>> listColumn = new ArrayList<Map<String, String>>();
		Map<String, String> colmun = null;
		List<String> colmunProps = helper.getAttributeValues(pathColumn, "prop");
		List<String> colmunWidths = helper.getAttributeValues(pathColumn, "width");
		List<String> colmunValues = helper.getValues(pathColumn);
		for (int j = 0; j < colmunProps.size(); j++)
		{
			colmun = new HashMap<String, String>();
			colmun.put("prop", colmunProps.get(j));
			colmun.put("value", colmunValues.get(j));
			colmun.put("width", colmunWidths.get(j));
			listColumn.add(colmun);
		}
		return listColumn;
	}
	
	/**
	 * 获取业务对应的表名称的显示列集合
	 * @param processDefId 流程定义ID
	 */
	@SuppressWarnings("unchecked")
	public String getTableNameByProcess(String processDefKey)
	{
		// 读取xml文件
		String path = "/com/ht/workflow/config/business-configuration.cfg.xml";
		ConfigLookupHelper helper = ConfigLookupHelper.getInstance(EnvironmentUtil.getResourceUrl(path));
		String pathTable = "configuration/tables/table[@process='" + processDefKey + "']";
		List<String> tableNames = helper.getAttributeValues(pathTable, "name");
		if (tableNames != null)
		{
			if (tableNames.size() > 0) { return tableNames.get(0); }
		}
		return null;
	}
	
	/**
	 * 获取业务对应的表描述
	 * @param processDefId 流程定义ID
	 */
	@SuppressWarnings("unchecked")
	public String getDecriptionNameByProcess(String processDefKey)
	{
		// 读取xml文件
		String path = "/com/ht/workflow/config/business-configuration.cfg.xml";
		ConfigLookupHelper helper = ConfigLookupHelper.getInstance(EnvironmentUtil.getResourceUrl(path));
		String pathTable = "configuration/tables/table[@process='" + processDefKey + "']";
		List<String> tableNames = helper.getAttributeValues(pathTable, "decription");
		if (tableNames != null)
		{
			if (tableNames.size() > 0) { return tableNames.get(0); }
			else{
				if(processDefKey.equals("PROBLEM_SUBMIT")){
					return "问题汇交待办";
				}else{
					return "任务书待办";
				}
				
			}
		}
		return null;
	}

	/**
	 * 获取业务对应的表名称的显示列集合
	 * @param processDefId 流程定义ID
	 */
	@SuppressWarnings("unchecked")
	public String getIndexByProcess(String processDefKey)
	{
		// 读取xml文件
		String path = "/com/ht/workflow/config/business-configuration.cfg.xml";
		ConfigLookupHelper helper = ConfigLookupHelper.getInstance(EnvironmentUtil.getResourceUrl(path));
		String pathTable = "configuration/tables/table[@process='" + processDefKey + "']";
		List<String> charttypes = helper.getAttributeValues(pathTable, "index");
		if (charttypes != null)
		{
			if (charttypes.size() > 0) { return charttypes.get(0); }
		}
		return null;
	}

	/**
	 * 获取业务对应的表名称的显示列集合
	 * @param processDefKey 流程定义ID
	 */
	@SuppressWarnings("unchecked")
	public String getCharttypeByProcess(String processDefKey)
	{
		// 读取xml文件
		String path = "/com/ht/workflow/config/business-configuration.cfg.xml";
		ConfigLookupHelper helper = ConfigLookupHelper.getInstance(EnvironmentUtil.getResourceUrl(path));
		String pathTable = "configuration/tables/table[@process='" + processDefKey + "']";
		List<String> indexs = helper.getAttributeValues(pathTable, "charttype");
		if (indexs != null)
		{
			if (indexs.size() > 0) { return indexs.get(0); }
		}
		return null;
	}
	
	/**
	 * 获取流程任务流程的需求参数集合
	 * @param processDefId 流程定义ID
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> getProcessNextTaskArgs(String processDefKey,String taskDefId)
	{
		Map<String,Object> result = new HashMap<String, Object>();
		// 读取xml文件
		String path = "/com/ht/workflow/config/business-configuration.cfg.xml";
		ConfigLookupHelper helper = ConfigLookupHelper.getInstance(EnvironmentUtil.getResourceUrl(path));
		String pathTable = "configuration/processes/process[@key='" + processDefKey + "']/tasks/task[@id='" + taskDefId + "']";
		// 获取任务的子节点
		String targets = helper.getAttributeValue(pathTable, "targets");
		if (StringUtils.isNotEmpty(targets))
		{
			String[] targetArray = targets.split(",");
			for (int i = 0; i < targetArray.length; i++)
			{
				pathTable = "configuration/processes/process[@key='" + processDefKey + "']/tasks/task[@id='" + targetArray[i] + "']/args/arg";
				List<Map<String,String>> args = (List<Map<String, String>>) helper.getAttributesValue(pathTable);
				for (int j = 0; j < args.size(); j++)
				{
					if (StringUtils.isNotEmpty(args.get(j).get("value")))
					{
						result.put(args.get(j).get("key"), args.get(j).get("value"));
					}
				}
			}
		}
		return result;
	}
	
	/**
	 * 获取流程当前任务的参数集合
	 * @param processDefId 流程定义ID
	 */
	@SuppressWarnings("unchecked")
	public Map<String, String> getProcessTaskArgkeys(String processDefKey,String taskDefId)
	{
		Map<String,String> result = new HashMap<String, String>();
		// 读取xml文件
		String path = "/com/ht/workflow/config/business-configuration.cfg.xml";
		ConfigLookupHelper helper = ConfigLookupHelper.getInstance(EnvironmentUtil.getResourceUrl(path));
		String pathTable = "configuration/processes/process[@key='" + processDefKey + "']/tasks/task[@id='" + taskDefId + "']/args/arg";
		List<Map<String,String>> args = (List<Map<String, String>>) helper.getAttributesValue(pathTable);
		for (int j = 0; j < args.size(); j++)
		{
			if (StringUtils.isNotEmpty(args.get(j).get("value")))
			{
				result.put(args.get(j).get("key"), args.get(j).get("value"));
			}
		}
		return result;
	}
	
	/**
	 * 获取流程当前任务被分配的参数key
	 * @param processDefId 流程定义ID
	 */
	@SuppressWarnings("unchecked")
	public String getProcessTaskAllotedArgkey(String processDefKey,String taskDefId)
	{
		Map<String,String> result = new HashMap<String, String>();
		// 读取xml文件
		String path = "/com/ht/workflow/config/business-configuration.cfg.xml";
		ConfigLookupHelper helper = ConfigLookupHelper.getInstance(EnvironmentUtil.getResourceUrl(path));
		String pathTable = "configuration/processes/process[@key='" + processDefKey + "']/tasks/task[@id='" + taskDefId + "']/args/arg[@alloted='true']";
		return helper.getAttributeValue(pathTable, "key");
	}
	
	/**
	 * 获取流程当前任务流程需要被分配的参数key
	 * @param processDefId 流程定义ID
	 */
	@SuppressWarnings("unchecked")
	public List<String> getProcessNextTasksAllotedArgkey(String processDefKey,String taskDefId)
	{
		List<String> result = new ArrayList<String>();
		// 读取xml文件
		String path = "/com/ht/workflow/config/business-configuration.cfg.xml";
		ConfigLookupHelper helper = ConfigLookupHelper.getInstance(EnvironmentUtil.getResourceUrl(path));
		String pathTable = "configuration/processes/process[@key='" + processDefKey + "']/tasks/task[@id='" + taskDefId + "']";
		// 获取任务的子节点
		String targets = helper.getAttributeValue(pathTable, "targets");
		if (StringUtils.isNotEmpty(targets))
		{
			String[] targetArray = targets.split(",");
			for (int i = 0; i < targetArray.length; i++)
			{
				pathTable = "configuration/processes/process[@key='" + processDefKey + "']/tasks/task[@id='" + targetArray[i] + "']/args/arg[@alloted='true']";
				String key = helper.getAttributeValue(pathTable, "key");
				if (!result.contains(key))
				{
					result.add(key);
				}
			}
		}
		return result;
	}
	

	/**
	 * 获取流程当前任务被分配的组ID
	 * @param processDefId 流程定义ID
	 */
	@SuppressWarnings("unchecked")
	public String getProcessPreTaskAllotGroupId(String processDefKey,String taskDefId)
	{
		Map<String,String> result = new HashMap<String, String>();
		// 读取xml文件
		String path = "/com/ht/workflow/config/business-configuration.cfg.xml";
		ConfigLookupHelper helper = ConfigLookupHelper.getInstance(EnvironmentUtil.getResourceUrl(path));
		String pathTable = "configuration/processes/process[@key='" + processDefKey + "']/tasks/task[@targets='" + taskDefId + "']/args/arg[@allot='true']";
		return helper.getAttributeValue(pathTable, "value");
	}
	
	/**
	 * 获取流程分配组ID
	 * @return
	 */
	public String getProcessAllotGroupId(String processDefKey,String taskDefId){
		// 读取xml文件
		String path = "/com/ht/workflow/config/business-configuration.cfg.xml";
		ConfigLookupHelper helper = ConfigLookupHelper.getInstance(EnvironmentUtil.getResourceUrl(path));
		String pathTable = "configuration/processes/process[@key='" + processDefKey + "']/tasks/task[@id='" + taskDefId + "']/args/arg[@allot='true']";
		return helper.getAttributeValue(pathTable, "value");
	}
	
	/**
	 * 获取流程指定操作的任务定义ID
	 * @return
	 */
	public Map<String,String> getProcessOperationTimes(String processDefKey,String operationType){
		// 读取xml文件
		String path = "/com/ht/workflow/config/business-configuration.cfg.xml";
		ConfigLookupHelper helper = ConfigLookupHelper.getInstance(EnvironmentUtil.getResourceUrl(path));
		String pathTable = "configuration/opration/process[@key='" + processDefKey + "']/type[@key='" + operationType + "']";
		String taskDefId = helper.getAttributeValue(pathTable, "taskDefId");
		String perform = helper.getAttributeValue(pathTable, "perform");
		String name = helper.getAttributeValue(pathTable, "name");
		Map<String,String> map = new HashMap<String, String>();
		map.put("taskDefId", taskDefId);
		map.put("perform", perform);
		map.put("name", name);
		return map;
	}
	
	/**
	 * 获取流程当前任务是否需要校验计划完成
	 * @param processDefId 流程定义ID
	 */
	@SuppressWarnings("unchecked")
	public String getProcessTaskValid(String processDefKey,String taskDefId)
	{
		Map<String,String> result = new HashMap<String, String>();
		// 读取xml文件
		String path = "/com/ht/workflow/config/business-configuration.cfg.xml";
		ConfigLookupHelper helper = ConfigLookupHelper.getInstance(EnvironmentUtil.getResourceUrl(path));
		String pathTable = "configuration/processes/process[@key='" + processDefKey + "']/tasks/task[@id='"+taskDefId+"']";
		return helper.getAttributeValue(pathTable, "valid");
	}
	
	/**
	 * 获取流程当前任务是否需要改正通告
	 * @param processDefId 流程定义ID
	 */
	@SuppressWarnings("unchecked")
	public String getProcessTaskNotice(String processDefKey,String taskDefId)
	{
		Map<String,String> result = new HashMap<String, String>();
		// 读取xml文件
		String path = "/com/ht/workflow/config/business-configuration.cfg.xml";
		ConfigLookupHelper helper = ConfigLookupHelper.getInstance(EnvironmentUtil.getResourceUrl(path));
		String pathTable = "configuration/processes/process[@key='" + processDefKey + "']/tasks/task[@id='"+taskDefId+"']";
		return helper.getAttributeValue(pathTable, "notice");
	}
}
