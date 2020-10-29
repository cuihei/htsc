
package com.ht.persistence.dao.impl.system.workflow.task;

import java.util.*;

import com.ht.common.util.LogHelper;
import com.ht.persistence.dao.impl.base.BaseDaoImpl;
import com.ht.persistence.dao.inter.system.workflow.task.TaskDao;
import com.ht.persistence.model.drawtask.taskbook.book.StatisticsTask;
import com.ht.persistence.model.drawtask.taskbook.book.TaskAnalysis;
import com.ht.persistence.model.drawtask.taskbook.book.TaskPlan;
import com.ht.persistence.model.drawtask.taskbook.book.Taskinfo;
import com.ht.persistence.model.statisticalanalysis.view.CompilationResultSummaryView;
import com.ht.persistence.model.drawtask.taskbook.book.StatisticsPlan;
import com.ht.persistence.model.system.workflow.process.ProcessTypeCount;
import com.ht.persistence.model.system.workflow.task.HiTask;
import com.ht.persistence.model.system.workflow.task.RuTask;
import com.sun.jmx.snmp.Timestamp;
import com.sun.xml.internal.ws.policy.EffectiveAlternativeSelector;
import com.thoughtworks.xstream.alias.ClassMapper.Null;
import com.ht.persistence.model.drawtask.taskbook.book.TaskAnalysis;

import java.io.PrintStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.exception.ViolatedConstraintNameExtracter;
import org.hibernate.transform.Transformers;
import org.hibernate.type.TrueFalseType;
import org.springframework.beans.BeanUtils;
import org.springframework.jdbc.object.UpdatableSqlQuery;

public class TaskDaoImpl extends BaseDaoImpl implements TaskDao {

	/**
	 * @return 任务列表
	 */
	@Override
	public List<RuTask> getAllRuTask() {
		List<RuTask> list = findByNamedQuery("getAllRuTask");
		return list;
	}

	/**
	 * 根据任务受理组取得任务
	 * 
	 * @param groupId
	 *            受理组ID
	 * @return 任务列表
	 */
	@Override
	public List<RuTask> getRuTaskByProcessDefKey(RuTask ruTask) {
		String[] params = { "processDefKey" };
		String[] values = { ruTask.getProcessDefKey() };
		String[] year = { ruTask.getTaskbookNo() };
		List<RuTask> list = findByNamedQueryAndNamedParam("getRuTaskByProcessDefKey", params, values);
		return list;
	}

	/**
	 * 根据任务受理组取得任务
	 * 
	 * @param groupId
	 *            受理组ID
	 * @return 任务列表
	 */
	@Override

	public List<RuTask> getRuTaskByProcessDefKey(RuTask ruTask, String page, String pageSize) {
		String sql = "SELECT PROCESS_INST_ID,PROCESS_DEF_ID,TASK_ID,TASK_DEF_ID,PROCESS_DEF_NAME,TASK_NAME,CREATION_DATE,MAIN_PROCESS_INST_ID,EXCUTION_ID,PARENT_PROCESS_INST_ID,GROUP_ID,GROUP_NAME,ASSIGNEE,ASSIGNEE_NAME,SUSPEND_STATE,PROCESS_DEF_KEY,END_TIME FROM (SELECT ROW_NUMBER() OVER(PARTITION BY PROCESS_INST_ID ORDER BY CREATION_DATE DESC) rn,V_RU_TASK.* FROM  V_RU_TASK where PROCESS_DEF_KEY in  (:processDefKeys)) WHERE rn = 1";
		SQLQuery query = getSession().createSQLQuery(sql);
		List<String> param = new ArrayList();
		param.add(ruTask.getProcessDefKey());
		query.setParameterList("processDefKeys", param);
		query.addEntity(RuTask.class);
		List<RuTask> list = query.setFirstResult((Integer.parseInt(page) - 1) * Integer.parseInt(pageSize))
				.setMaxResults(Integer.parseInt(pageSize)).list();
		return list;
	}

	/**
	 * 根据任务受理组取得任务
	 * 
	 * @param groupId
	 *            受理组ID
	 * @return 任务列表
	 */
	@Override
	public int getRuTaskByProcessDefKeyCount(RuTask ruTask) {
		String sql = "SELECT PROCESS_INST_ID,PROCESS_DEF_ID,TASK_ID,TASK_DEF_ID,PROCESS_DEF_NAME,TASK_NAME,CREATION_DATE,MAIN_PROCESS_INST_ID,EXCUTION_ID,PARENT_PROCESS_INST_ID,GROUP_ID,GROUP_NAME,ASSIGNEE,ASSIGNEE_NAME,SUSPEND_STATE,PROCESS_DEF_KEY,END_TIME FROM (SELECT ROW_NUMBER() OVER(PARTITION BY PROCESS_INST_ID ORDER BY CREATION_DATE DESC) rn,V_RU_TASK.* FROM  V_RU_TASK where PROCESS_DEF_KEY in  (:processDefKeys)) WHERE rn = 1";
		SQLQuery query = getSession().createSQLQuery(sql);
		List<String> param = new ArrayList();
		param.add(ruTask.getProcessDefKey());
		query.setParameterList("processDefKeys", param);
		query.addEntity(RuTask.class);
		int count = query.list().size();
		return count;
	}

	/**
	 * 根据任务受理组取得任务
	 * 
	 * @param groupId
	 *            受理组ID
	 * @return 任务列表
	 */
	@Override
	public List<RuTask> getRuTaskInProcessDefKey(List<String> name) {
	
		List<RuTask> list = findByNamedQueryAndNamedParam("getRuTaskInProcessDefKey", "processDefKey", name);
		return list;
	}

	/**
	 * 根据任务受理组取得任务
	 * 
	 * @param groupId
	 *            受理组ID
	 * @return 任务列表
	 */
	@Override
	public List<RuTask> getRuTaskInProcessDefKey(List<String> name, String page, String pageSize) {
		String hql = "from RuTask where processDefKey in (:processDefKeys) order by createTime desc";
		Query query = getSession().createQuery(hql);
		query.setParameterList("processDefKeys", name);
		List<RuTask> list = query.setFirstResult((Integer.parseInt(page) - 1) * Integer.parseInt(pageSize))
				.setMaxResults(Integer.parseInt(pageSize)).list();
		return list;
	}

	@Override
	public List<RuTask> getRuTaskInProcessDefKeyByYear(List<String> name, String page, String pageSize, String year) {
		String hql = "from RuTask where processDefKey in (:processDefKeys) and (to_char(createTime,'yyyy'))='" + year
				+ "' order by createTime desc";
		Query query = getSession().createQuery(hql);
		query.setParameterList("processDefKeys", name);
		List<RuTask> list = query.setFirstResult((Integer.parseInt(page) - 1) * Integer.parseInt(pageSize))
				.setMaxResults(Integer.parseInt(pageSize)).list();
		return list;
	}

	@Override
	public int getRuTaskInProcessDefKeyCount(List<String> name) {
		String hql = "from RuTask where processDefKey in (:processDefKeys) order by createTime desc";
		Query query = getSession().createQuery(hql);
		query.setParameterList("processDefKeys", name);
		int count = query.list().size();
		return count;
	}

	@Override
	/**
	 * 根据任务受理组取得任务
	 * 
	 * @param groupId
	 *            受理组ID
	 * @return 任务列表
	 */
	public List<RuTask> getRuTaskByGroupIdAndProcessDefId(RuTask ruTask) {
		String[] params = { "groupId", "processDefId" };
		String[] values = { ruTask.getGroupId(), ruTask.getProcessDefId() };
		List<RuTask> list = findByNamedQueryAndNamedParam("getRuTaskByGroupIdAndProcessDefId", params, values);
		return list;
	}

	/**
	 * 根据任务受理组取得任务
	 * 
	 * @param groupId
	 *            受理组ID
	 * @return 任务列表
	 */
	@Override
	public List<RuTask> getRuTaskByGroupId(RuTask ruTask) {
		String[] params = { "groupId" };
		String[] values = { ruTask.getGroupId() };
		List<RuTask> list = findByNamedQueryAndNamedParam("getRuTaskByGroupId", params, values);
		return list;
	}

	/**
	 * 根据任务受理组取得任务
	 * 
	 * @param parentProcessInstId
	 *            父流程实例ID
	 * @return 任务列表
	 */
	@Override
	public List<RuTask> getRuTaskByParentProcessInstId(RuTask ruTask) {
		String[] params = { "parentProcessInstId" };
		String[] values = { ruTask.getParentProcessInstId() };
		List<RuTask> list = findByNamedQueryAndNamedParam("getRuTaskByParentProcessInstId", params, values);
		return list;
	}

	/**
	 * 根据任务受理组取得任务
	 * 
	 * @param parentProcessInstId
	 *            父流程实例ID
	 * @return 任务列表
	 */
	@Override
	public List<RuTask> getRuTaskByProcessInstId(RuTask ruTask) {
		String[] params = { "processInstId" };
		String[] values = { ruTask.getProcessInstId() };
		List<RuTask> list = findByNamedQueryAndNamedParam("getRuTaskByProcessInstId", params, values);
		return list;
	}

	/**
	 * 根据任务受理组取得任务
	 * 
	 * @param parentProcessInstId
	 *            父流程实例ID
	 * @return 任务列表
	 */
	@Override
	public List<RuTask> getRuTaskByAssigneeList(List<String> assigneeList) {
		List<RuTask> list = findByNamedQueryAndNamedParam("getRuTaskByAssigneeList", "assigneeList", assigneeList);
		return list;
	}

	/**
	 * 根据任务受理组取得任务
	 * 
	 * @param parentProcessInstId
	 *            父流程实例ID
	 * @return 任务列表
	 */
	@Override
	public List<ProcessTypeCount> getRuTaskCountGroupByKey(String userId, String groupId) {
		String sql = "select tmp.Process_Def_Id,tmp.PROCESS_DEF_KEY,tmp.PROCESS_DEF_NAME,count(*) as cnt from (";
		sql = sql + "select Process_Def_Id,PROCESS_DEF_KEY,PROCESS_DEF_NAME from V_RU_TASK  where ASSIGNEE = " + userId;
		if (!StringUtils.isEmpty(groupId)) {
			sql = sql
					+ " union all select Process_Def_Id,PROCESS_DEF_KEY,PROCESS_DEF_NAME from V_RU_TASK  where  GROUP_ID in ("
					+ groupId + ") ";
			
		}
		sql = sql + "  ) tmp group by tmp.Process_Def_Id,tmp.PROCESS_DEF_KEY,tmp.PROCESS_DEF_NAME";
		SQLQuery query = getSession().createSQLQuery(sql);
		query.addEntity(ProcessTypeCount.class);
		
		List<ProcessTypeCount> list = query.list();
		
		return list;
	}

	@Override
	public int updateTable(String tableName, String id, String key, String tableStatusName, String status) {
		String sql = "update " + tableName + " set " + tableStatusName + " = '" + status + "' where " + key + " = '"
				+ id + "'";
		return getSession().createSQLQuery(sql).executeUpdate();
	}

	@Override
	public List<HiTask> getHiTaskByParentProcessIdAndProcessDefKey(String parentProcessInstId, String processDefKey) {
		String[] params = { "parentProcessInstId", "processDefKey" };
		String[] values = { parentProcessInstId, processDefKey };
		List<HiTask> list = findByNamedQueryAndNamedParam("getHiTaskByParentProcessIdAndProcessDefKey", params, values);
		return list;
	}

	@Override
	public List<HiTask> getHiTaskByProcessId(String processInstId) {
		String[] params = { "processInstId" };
		String[] values = { processInstId };
		List<HiTask> list = findByNamedQueryAndNamedParam("getHiTaskByProcessId", params, values);
		return list;
	}

	@Override
	public List<HiTask> getHiTaskByAssignee(String assignee) {
		String[] params = { "assignee" };
		String[] values = { assignee };
		List<HiTask> list = findByNamedQueryAndNamedParam("getHiTaskByAssignee", params, values);
		return list;
	}

	@Override
	public List<HiTask> getHiTaskByAssigneeAndProcessDefKey(String assignee, String processDefKey) {
		String[] params = { "assignee", "processDefKey" };
		String[] values = { assignee, processDefKey };
		List<HiTask> list = findByNamedQueryAndNamedParam("getHiTaskByAssigneeAndProcessDefKey", params, values);
		return list;
	}

	@Override
	public List<HiTask> getHiTaskByAssigneeAndProcessInstId(String assignee, String processDefKey,
			String processInstId) {
		String[] params = { "assignee", "processDefKey", "processInstId" };
		String[] values = { assignee, processDefKey, processInstId };
		List<HiTask> list = findByNamedQueryAndNamedParam("getHiTaskByAssigneeAndProcessInstId", params, values);
		return list;
	}

	@Override
	public List<HiTask> getHiTaskByGroupAndProcessDefKey(String group, String processDefKey) {
		String[] params = { "groupId", "processDefKey" };
		String[] values = { group, processDefKey };
		List<HiTask> list = findByNamedQueryAndNamedParam("getHiTaskByGroupAndProcessDefKey", params, values);
		return list;
	}

	@Override
	public List<HiTask> getHiTaskByGroupAndProcessInstId(String group, String processDefKey, String processInstId) {
		String[] params = { "groupId", "processDefKey", "processInstId" };
		String[] values = { group, processDefKey, processInstId };
		List<HiTask> list = findByNamedQueryAndNamedParam("getHiTaskByGroupAndProcessInstId", params, values);
		return list;
	}

	@Override
	public List<HiTask> getHiTaskByProcessDefKey(List<String> processDefKeys) {
		String[] params = { "processDefKeys" };
		Object[] values = { processDefKeys };
		List<HiTask> list = findByNamedQueryAndNamedParam("getHiTaskByProcessDefKey", params, values);
		return list;
	}

	@Override
	public List<HiTask> getCompleteTask(List<String> processDefKeys, String page, String pageSize) {
		String sql = "SELECT task_id,process_def_id,task_def_id,process_def_name,process_def_key,process_inst_id,execution_id,task_name,assignee,assignee_name,start_time,end_time,claim_time,delete_reason,parent_process_inst_id,group_id,group_name,process_end_time,record_id FROM (SELECT ROW_NUMBER() OVER(PARTITION BY PROCESS_INST_ID ORDER BY PROCESS_END_TIME DESC) rn,V_HI_TASK.* FROM  V_HI_TASK where PROCESS_DEF_KEY in  (:processDefKeys) and PROCESS_END_TIME is not null) WHERE rn = 1";
		SQLQuery query = getSession().createSQLQuery(sql);
		query.setParameterList("processDefKeys", processDefKeys);
		query.addEntity(HiTask.class);
		List<HiTask> list = query.setFirstResult((Integer.parseInt(page) - 1) * Integer.parseInt(pageSize))
				.setMaxResults(Integer.parseInt(pageSize)).list();
		return list;
	}

	@Override
	public List<HiTask> getCompleteTaskByYear(List<String> processDefKeys, String page, String pageSize, String year) {
		String sql = "SELECT b.taskbook_no,e.*,e.record_id FROM (SELECT ROW_NUMBER() OVER(PARTITION BY PROCESS_INST_ID ORDER BY PROCESS_END_TIME DESC) rn,V_HI_TASK.*FROM  V_HI_TASK where PROCESS_DEF_KEY in  (:processDefKeys)  and PROCESS_END_TIME is not null) e,compilation_child_task a,COMPILATION_TASK_BOOK b WHERE rn = 1 and a.id=e.record_id and a.PARENT_TASKBOOK_ID=b.id and substr(b.taskbook_no,0,4)='"
				+ year + "'";
		SQLQuery query = getSession().createSQLQuery(sql);
		query.setParameterList("processDefKeys", processDefKeys);
		query.addEntity(HiTask.class);
		List<HiTask> list = query.setFirstResult((Integer.parseInt(page) - 1) * Integer.parseInt(pageSize))
				.setMaxResults(Integer.parseInt(pageSize)).list();
		return list;
	}

	@Override
	public List<HiTask> getCompleteTask(List<String> processDefKeys) {
		String[] params = { "processDefKeys" };
		Object[] values = { processDefKeys };
		List<HiTask> list = findByNamedQueryAndNamedParam("getCompleteTask", params, values);
		return list;
	}

	@Override
	public int getCompleteTaskCount(List<String> processDefKeys) {
		String sql = "SELECT * FROM (SELECT ROW_NUMBER() OVER(PARTITION BY PROCESS_INST_ID ORDER BY PROCESS_END_TIME DESC) rn,V_HI_TASK.* FROM  V_HI_TASK where PROCESS_DEF_KEY in  (:processDefKeys) and PROCESS_END_TIME is not null) WHERE rn = 1";
		SQLQuery query = getSession().createSQLQuery(sql);
		query.setParameterList("processDefKeys", processDefKeys);
		query.addEntity(HiTask.class);
		int count = query.list().size();
		return count;
	}

	@Override
	public List<HiTask> getCompleteTask(List<String> processDefKeys, Date startTime, Date endTime) {
		String[] params = { "processDefKeys", "startTime", "endTime" };
		Object[] values = { processDefKeys, startTime, endTime };
		List<HiTask> list = findByNamedQueryAndNamedParam("getCompleteTaskByDate", params, values);
		return list;
	}

	@Override
	public List<HiTask> getHiTaskByProcessInstIdAndTaskDefId(String processInstId, String taskDefId) {
		String[] params = { "processInstId", "taskDefId" };
		Object[] values = { processInstId, taskDefId };
		List<HiTask> list = findByNamedQueryAndNamedParam("getHiTaskByProcessInstIdAndTaskDefId", params, values);
		return list;
	}

	@Override
	public List<HiTask> getHiTaskNotInProcessInstId(String processInstId, String parentProcessInstId) {
		String sql = "select * from (select * from v_hi_task WHERE  parent_process_inst_id='" + parentProcessInstId
				+ "'  and process_def_name like '%计划%'  order by end_time desc) where rownum=1";
		SQLQuery query = getSession().createSQLQuery(sql);
		query.addEntity(HiTask.class);
		@SuppressWarnings("unchecked")
		List<HiTask> ht = query.list();
		/// 如果大于0说明该计划已经完成
		if (ht != null && ht.size() > 0) {

			if (ht.get(0).getTaskName().indexOf("计划审定") > 0) {
				return null;
			} else {
				return ht;
			}

		}
		;
		// 如果等于0则说明该计划未完成，并查询出该计划当前状态。
		return null;
	}

	/**
	 * 根据流程分组获取经办
	 * 
	 * @param assignee
	 * @param processDefKey
	 * @return
	 */
	@Override
	public List<HiTask> getHiTaskGroupByProcessInstId(String assignee, String processDefKey) {
		String sql = "select task_id,process_def_id,task_def_id,process_def_name,process_def_key,process_inst_id,execution_id,task_name,assignee,assignee_name,start_time,end_time,claim_time,delete_reason,parent_process_inst_id,group_id,group_name,process_end_time,record_id from (select row_number()over(partition by process_inst_id order by end_time desc nulls last)rn, t.* from(select * from v_hi_task where process_def_key=? and assignee=?) t)t  where t.rn=1";
		SQLQuery query = this.getSession().createSQLQuery(sql);
		query.setParameter(0, processDefKey);
		query.setParameter(1, assignee);
		query.addEntity(HiTask.class);
		List<HiTask> list = query.list();
		return list;
	}

	@Override
	public List<HiTask> getTaskByParentProcessInstId(String parentProcessInstId) {
		String[] params = { "parentProcessInstId" };
		Object[] values = { parentProcessInstId };
		List<HiTask> list = this.findByNamedQueryAndNamedParam("getTaskByParentProcessInstId", params, values);
		return list;
	}

	@Override
	public RuTask getRtByParentProcessInstId(String parentProcessInstId) {
		String[] params = { "parentProcessInstId" };
		String[] values = { parentProcessInstId };
		List<RuTask> list = this.findByNamedQueryAndNamedParam("getRtByParentProcessInstId", params, values);

		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	/**
	 * 获取本任务的计划中 分配的人员
	 */
	@Override
	public String getplanName(String taskId, String processInstId, String processDefId, String taskDefId, String userId,
			String parentProcessInstId) {
		String PlanName = null;

		// 质检组长分配人员 u_task_distribution_zhijian
		if (taskDefId.indexOf("u_task_distribution_zhijian") > -1) {
			String sql = "select ASSIGNEE_NAME from v_hi_task where PARENT_PROCESS_INST_ID='" + parentProcessInstId
					+ "' and TASK_NAME='分配任务给质检员' and PROCESS_DEF_NAME  in('纸海图编绘计划','源数据编绘计划','电子海图编绘计划')   and rownum=1 order by claim_time";
			SQLQuery query = this.getSession().createSQLQuery(sql);
			PlanName = query.uniqueResult().toString();
			// 组长分配人员
			return PlanName;
		} else if (taskDefId.indexOf("u_task_distribution_shending") > -1) {// 审定组长分配人员
																			// u_task_distribution_shending
			String sql = "select ASSIGNEE_ID from v_hi_task where PARENT_PROCESS_INST_ID='" + parentProcessInstId
					+ "' and TASK_NAME='分配任务给审定员' and PROCESS_DEF_NAME  in('纸海图编绘计划','源数据编绘计划','电子海图编绘计划')   and rownum=1 order by claim_time";
			SQLQuery query = this.getSession().createSQLQuery(sql);
			PlanName = query.uniqueResult().toString();
			return PlanName;
		}else {
			return PlanName;
		}
		
	}

	/// 获取任务分析表中的年份。

	@Override
	public List<String> getAllTaskYear() {
		String sql = "select substr(taskbook_no,1,4) from COMPILATION_TASK_BOOK where rowid in (select max(rowid) from COMPILATION_TASK_BOOK group by substr(taskbook_no,1,4) ) ";
		SQLQuery query = this.getSession().createSQLQuery(sql);
		List<String> list = query.list();
		return list;
	}

	// 更新表的状态和时间
	public int updateTaskUpTime(String type, String sta) {
		// 获取当前的日期
		Date date = new Date();
		// 设置要获取到什么样的时间
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// 获取String类型的时间
		String createdate = sdf.format(date);
		String sql = "update COMPILATION_TASK_UPTIME Set state=?,up_date=? WHERE ID='" + type + "'";
		SQLQuery query = this.getSession().createSQLQuery(sql);
		query.setString(0, sta);
		query.setString(1, createdate);
		return query.executeUpdate();

	}

	/// 更改更新表中的状态。
	// 先清除表 在新增表 1 正在更新中 0 已经停止更新
	public List<String> taskupTimeStr(String type) {
		String sql = "select state,up_date from COMPILATION_TASK_UPTIME where id ='" + type + "'";
		SQLQuery query = this.getSession().createSQLQuery(sql);
		List<String> list = query.list();
		return list;
	}

	@Override
	public void addTaslAllList(List<Map<String, Object>> listAll) {
		/* 开始循环插入 */
		for (int i = 0; i < listAll.size(); i++) {

			String uid = UUID.randomUUID().toString().replace("-", "").toLowerCase();
			String sqlinsert = "Insert into COMPILATION_TASK_ANALYSIS (ID,APPRVOE_END_TIME,APPRVOE_EXPECTED_TIME,APPRVOE_PERSON,APPRVOE_START_TIME,CHECK_WORKDAYS,COMPILATION_END_TIME,COMPILATION_EXPECTED_TIME,COMPILATION_GROUP,COMPILATION_PERSON,COMPILATION_START_TIME,COMPILATION_WORKDAYS,CREATE_TIME,CURR_USER,END_TIME,EXAMINE_WORKDAYS,EXCUTION_ID,GROUP_,LAST_UPDATE_USER,MAP_NAME,MAP_NO,PARENT_PROCESS_INST_ID,PERFORMER,PROCESS_DEF_ID,PROCESS_DEF_NAME,PROCESS_INST_ID,QUALITY_END_TIME,QUALITY_EXPECTED_TIME,QUALITY_PERSON,QUALITY_SCORE,QUALITY_START_TIME,RECORD_ID,REMARK,SCALE,SCORE,STATUS,SUSPEND_STATE,TASK_BOOK_TIME,TASK_DEF_ID,TASK_INST_ID,TASK_NAME,TASKBOOK_NO,ZL_TIME,ZT_TIME,PROCESS_END_TIME) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			Query query = this.getSession().createSQLQuery(sqlinsert);
			query.setString(0, uid);
			query.setString(1, listAll.get(i).get("apprvoeEndTime") != null
					? listAll.get(i).get("apprvoeEndTime").toString() : null);
			query.setString(2, listAll.get(i).get("apprvoeExpectedTime") != null
					? listAll.get(i).get("apprvoeExpectedTime").toString() : null);
			query.setString(3, listAll.get(i).get("apprvoePerson") != null
					? listAll.get(i).get("apprvoePerson").toString() : null);
			query.setString(4, listAll.get(i).get("apprvoeStartTime") != null
					? listAll.get(i).get("apprvoeStartTime").toString() : null);
			query.setString(5, listAll.get(i).get("checkWorkdays") != null
					? listAll.get(i).get("checkWorkdays").toString() : null);
			query.setString(6, listAll.get(i).get("compilationEndTime") != null
					? listAll.get(i).get("compilationEndTime").toString() : null);
			query.setString(7, listAll.get(i).get("compilationExpectedTime") != null
					? listAll.get(i).get("compilationExpectedTime").toString() : null);
			query.setString(8, listAll.get(i).get("compilationGroup") != null
					? listAll.get(i).get("compilationGroup").toString() : null);
			query.setString(9, listAll.get(i).get("compilationPerson") != null
					? listAll.get(i).get("compilationPerson").toString() : null);
			query.setString(10, listAll.get(i).get("compilationStartTime") != null
					? listAll.get(i).get("compilationStartTime").toString() : null);
			query.setString(11, listAll.get(i).get("compilationWorkdays") != null
					? listAll.get(i).get("compilationWorkdays").toString() : null);
			query.setString(12,
					listAll.get(i).get("taskBookTime") != null ? listAll.get(i).get("taskBookTime").toString() : null);
			query.setString(13,
					listAll.get(i).get("currUser") != null ? listAll.get(i).get("currUser").toString() : null);
			query.setString(14,
					listAll.get(i).get("endTime") != null ? listAll.get(i).get("endTime").toString() : null);
			query.setString(15, listAll.get(i).get("examineWorkdays") != null
					? listAll.get(i).get("examineWorkdays").toString() : null);
			query.setString(16,
					listAll.get(i).get("excutionId") != null ? listAll.get(i).get("excutionId").toString() : null);
			query.setString(17, listAll.get(i).get("group") != null ? listAll.get(i).get("group").toString() : null);
			query.setString(18, listAll.get(i).get("lastUpdateUser") != null
					? listAll.get(i).get("lastUpdateUser").toString() : null);
			query.setString(19,
					listAll.get(i).get("mapName") != null ? listAll.get(i).get("mapName").toString() : null);
			query.setString(20, listAll.get(i).get("mapNo") != null ? listAll.get(i).get("mapNo").toString() : null);
			query.setString(21, listAll.get(i).get("parentProcessInstId") != null
					? listAll.get(i).get("parentProcessInstId").toString() : null);
			query.setString(22,
					listAll.get(i).get("performer") != null ? listAll.get(i).get("performer").toString() : null);
			query.setString(23,
					listAll.get(i).get("processDefId") != null ? listAll.get(i).get("processDefId").toString() : null);
			query.setString(24, listAll.get(i).get("processDefName") != null
					? listAll.get(i).get("processDefName").toString() : null);
			query.setString(25, listAll.get(i).get("processInstId") != null
					? listAll.get(i).get("processInstId").toString() : null);
			query.setString(26, listAll.get(i).get("qualityEndTime") != null
					? listAll.get(i).get("qualityEndTime").toString() : null);
			query.setString(27, listAll.get(i).get("qualityExpectedTime") != null
					? listAll.get(i).get("qualityExpectedTime").toString() : null);
			query.setString(28, listAll.get(i).get("qualityPerson") != null
					? listAll.get(i).get("qualityPerson").toString() : null);
			query.setString(29,
					listAll.get(i).get("qualityScore") != null ? listAll.get(i).get("qualityScore").toString() : null);
			query.setString(30, listAll.get(i).get("qualityStartTime") != null
					? listAll.get(i).get("qualityStartTime").toString() : null);
			query.setString(31,
					listAll.get(i).get("recordId") != null ? listAll.get(i).get("recordId").toString() : null);
			query.setString(32, listAll.get(i).get("remark") != null ? listAll.get(i).get("remark").toString() : null);
			query.setString(33, listAll.get(i).get("scale") != null ? listAll.get(i).get("scale").toString() : null);
			query.setString(34, listAll.get(i).get("score") != null ? listAll.get(i).get("score").toString() : null);
			query.setString(35, listAll.get(i).get("status") != null ? listAll.get(i).get("status").toString() : null);
			query.setString(36,
					listAll.get(i).get("suspendState") != null ? listAll.get(i).get("suspendState").toString() : null);
			query.setString(37,
					listAll.get(i).get("taskBookTime") != null ? listAll.get(i).get("taskBookTime").toString() : null);
			query.setString(38,
					listAll.get(i).get("taskDefId") != null ? listAll.get(i).get("taskDefId").toString() : null);
			query.setString(39,
					listAll.get(i).get("taskInstId") != null ? listAll.get(i).get("taskInstId").toString() : null);
			query.setString(40,
					listAll.get(i).get("taskName") != null ? listAll.get(i).get("taskName").toString() : null);
			query.setString(41,
					listAll.get(i).get("taskbookNo") != null ? listAll.get(i).get("taskbookNo").toString() : null);
			query.setString(42, listAll.get(i).get("zlTime") != null ? listAll.get(i).get("zlTime").toString() : null);
			query.setString(43, listAll.get(i).get("ztTime") != null ? listAll.get(i).get("ztTime").toString() : null);
			query.setString(44, listAll.get(i).get("processEndTime") != null
					? listAll.get(i).get("processEndTime").toString() : null);
			query.executeUpdate();
			//

		}

		updateTaskUpTime("task", "0");
	}

	@Override
	public void addTaslAllListPlan(List<Map<String, Object>> listAll) {
		/// 改变更新表状态

		/* 开始循环插入 */
		for (int i = 0; i < listAll.size(); i++) {
			String uid = UUID.randomUUID().toString().replace("-", "").toLowerCase();
			String sqlinsert = "Insert into COMPILATION_TASK_PLAN (ID,APPRVOE_END_TIME,APPRVOE_EXPECTED_TIME,APPRVOE_PERSON,APPRVOE_START_TIME,CHECK_WORKDAYS,COMPILATION_END_TIME,COMPILATION_EXPECTED_TIME,COMPILATION_GROUP,COMPILATION_PERSON,COMPILATION_START_TIME,COMPILATION_WORKDAYS,CREATE_TIME,CURR_USER,END_TIME,EXAMINE_WORKDAYS,EXCUTION_ID,GROUP_,LAST_UPDATE_USER,MAP_NAME,MAP_NO,PARENT_PROCESS_INST_ID,PERFORMER,PROCESS_DEF_ID,PROCESS_DEF_NAME,PROCESS_INST_ID,QUALITY_END_TIME,QUALITY_EXPECTED_TIME,QUALITY_PERSON,QUALITY_SCORE,QUALITY_START_TIME,RECORD_ID,REMARK,SCALE,SCORE,STATUS,SUSPEND_STATE,TASK_BOOK_TIME,TASK_DEF_ID,TASK_INST_ID,TASK_NAME,TASKBOOK_NO,ZL_TIME,ZT_TIME,PROCESS_END_TIME) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			Query query = getSession().createSQLQuery(sqlinsert);
			query.setString(0, uid);
			query.setString(1, ((Map) listAll.get(i)).get("apprvoeEndTime") != null
					? ((Map) listAll.get(i)).get("apprvoeEndTime").toString() : null);
			query.setString(2, ((Map) listAll.get(i)).get("apprvoeExpectedTime") != null
					? ((Map) listAll.get(i)).get("apprvoeExpectedTime").toString() : null);
			query.setString(3, ((Map) listAll.get(i)).get("apprvoePerson") != null
					? ((Map) listAll.get(i)).get("apprvoePerson").toString() : null);
			query.setString(4, ((Map) listAll.get(i)).get("apprvoeStartTime") != null
					? ((Map) listAll.get(i)).get("apprvoeStartTime").toString() : null);
			query.setString(5, ((Map) listAll.get(i)).get("checkWorkdays") != null
					? ((Map) listAll.get(i)).get("checkWorkdays").toString() : null);
			query.setString(6, ((Map) listAll.get(i)).get("compilationEndTime") != null
					? ((Map) listAll.get(i)).get("compilationEndTime").toString() : null);
			query.setString(7, ((Map) listAll.get(i)).get("compilationExpectedTime") != null
					? ((Map) listAll.get(i)).get("compilationExpectedTime").toString() : null);
			query.setString(8, ((Map) listAll.get(i)).get("compilationGroup") != null
					? ((Map) listAll.get(i)).get("compilationGroup").toString() : null);
			query.setString(9, ((Map) listAll.get(i)).get("compilationPerson") != null
					? ((Map) listAll.get(i)).get("compilationPerson").toString() : null);
			query.setString(10, ((Map) listAll.get(i)).get("compilationStartTime") != null
					? ((Map) listAll.get(i)).get("compilationStartTime").toString() : null);
			query.setString(11, ((Map) listAll.get(i)).get("compilationWorkdays") != null
					? ((Map) listAll.get(i)).get("compilationWorkdays").toString() : null);
			query.setString(12, ((Map) listAll.get(i)).get("taskBookTime") != null
					? ((Map) listAll.get(i)).get("taskBookTime").toString() : null);
			query.setString(13, ((Map) listAll.get(i)).get("currUser") != null
					? ((Map) listAll.get(i)).get("currUser").toString() : null);
			query.setString(14, ((Map) listAll.get(i)).get("endTime") != null
					? ((Map) listAll.get(i)).get("endTime").toString() : null);
			query.setString(15, ((Map) listAll.get(i)).get("examineWorkdays") != null
					? ((Map) listAll.get(i)).get("examineWorkdays").toString() : null);
			query.setString(16, ((Map) listAll.get(i)).get("excutionId") != null
					? ((Map) listAll.get(i)).get("excutionId").toString() : null);
			query.setString(17, ((Map) listAll.get(i)).get("group") != null
					? ((Map) listAll.get(i)).get("group").toString() : null);
			query.setString(18, ((Map) listAll.get(i)).get("lastUpdateUser") != null
					? ((Map) listAll.get(i)).get("lastUpdateUser").toString() : null);
			query.setString(19, ((Map) listAll.get(i)).get("mapName") != null
					? ((Map) listAll.get(i)).get("mapName").toString() : null);
			query.setString(20, ((Map) listAll.get(i)).get("mapNo") != null
					? ((Map) listAll.get(i)).get("mapNo").toString() : null);
			query.setString(21, ((Map) listAll.get(i)).get("parentProcessInstId") != null
					? ((Map) listAll.get(i)).get("parentProcessInstId").toString() : null);
			query.setString(22, ((Map) listAll.get(i)).get("performer") != null
					? ((Map) listAll.get(i)).get("performer").toString() : null);
			query.setString(23, ((Map) listAll.get(i)).get("processDefId") != null
					? ((Map) listAll.get(i)).get("processDefId").toString() : null);
			query.setString(24, ((Map) listAll.get(i)).get("processDefName") != null
					? ((Map) listAll.get(i)).get("processDefName").toString() : null);
			query.setString(25, ((Map) listAll.get(i)).get("processInstId") != null
					? ((Map) listAll.get(i)).get("processInstId").toString() : null);
			query.setString(26, ((Map) listAll.get(i)).get("qualityEndTime") != null
					? ((Map) listAll.get(i)).get("qualityEndTime").toString() : null);
			query.setString(27, ((Map) listAll.get(i)).get("qualityExpectedTime") != null
					? ((Map) listAll.get(i)).get("qualityExpectedTime").toString() : null);
			query.setString(28, ((Map) listAll.get(i)).get("qualityPerson") != null
					? ((Map) listAll.get(i)).get("qualityPerson").toString() : null);
			query.setString(29, ((Map) listAll.get(i)).get("qualityScore") != null
					? ((Map) listAll.get(i)).get("qualityScore").toString() : null);
			query.setString(30, ((Map) listAll.get(i)).get("qualityStartTime") != null
					? ((Map) listAll.get(i)).get("qualityStartTime").toString() : null);
			query.setString(31, ((Map) listAll.get(i)).get("recordId") != null
					? ((Map) listAll.get(i)).get("recordId").toString() : null);
			query.setString(32, ((Map) listAll.get(i)).get("remark") != null
					? ((Map) listAll.get(i)).get("remark").toString() : null);
			query.setString(33, ((Map) listAll.get(i)).get("scale") != null
					? ((Map) listAll.get(i)).get("scale").toString() : null);
			query.setString(34, ((Map) listAll.get(i)).get("score") != null
					? ((Map) listAll.get(i)).get("score").toString() : null);
			query.setString(35, ((Map) listAll.get(i)).get("status") != null
					? ((Map) listAll.get(i)).get("status").toString() : null);
			query.setString(36, ((Map) listAll.get(i)).get("suspendState") != null
					? ((Map) listAll.get(i)).get("suspendState").toString() : null);
			query.setString(37, ((Map) listAll.get(i)).get("taskBookTime") != null
					? ((Map) listAll.get(i)).get("taskBookTime").toString() : null);
			query.setString(38, ((Map) listAll.get(i)).get("taskDefId") != null
					? ((Map) listAll.get(i)).get("taskDefId").toString() : null);
			query.setString(39, ((Map) listAll.get(i)).get("taskInstId") != null
					? ((Map) listAll.get(i)).get("taskInstId").toString() : null);
			query.setString(40, ((Map) listAll.get(i)).get("taskName") != null
					? ((Map) listAll.get(i)).get("taskName").toString() : null);
			query.setString(41, ((Map) listAll.get(i)).get("taskbookNo") != null
					? ((Map) listAll.get(i)).get("taskbookNo").toString() : null);
			query.setString(42, ((Map) listAll.get(i)).get("zlTime") != null
					? ((Map) listAll.get(i)).get("zlTime").toString() : null);
			query.setString(43, ((Map) listAll.get(i)).get("ztTime") != null
					? ((Map) listAll.get(i)).get("ztTime").toString() : null);
			query.setString(44, ((Map) listAll.get(i)).get("processEndTime") != null
					? ((Map) listAll.get(i)).get("processEndTime").toString() : null);
			query.executeUpdate();
		}
		updateTaskUpTime("plan", "0");
	}

	@Override
	public void delAllTempTask() {
		/* 清除临时表中的数据 */

		String sqlcount = "SELECT count(*) FROM COMPILATION_TASK_ANALYSIS";
		Query query1 = this.getSession().createSQLQuery(sqlcount);
		Integer count = ((Number) query1.uniqueResult()).intValue();

		if (count > 0) {
			String sqldel = "DELETE FROM COMPILATION_TASK_ANALYSIS";
			Query querydel = this.getSession().createSQLQuery(sqldel);
			querydel.executeUpdate();
		}

	}

	@Override
	public void delAllTempTaskPlan() {
		/* 清除计划临时表中的数据 */

		String sqlcount = "SELECT count(*) FROM COMPILATION_TASK_PLAN";
		Query query1 = this.getSession().createSQLQuery(sqlcount);
		Integer count = ((Number) query1.uniqueResult()).intValue();

		if (count > 0) {
			String sqldel = "DELETE FROM COMPILATION_TASK_PLAN";
			Query querydel = this.getSession().createSQLQuery(sqldel);
			querydel.executeUpdate();
		}

	}

	/* 获取所有任务 新任务完成情况 */
	// @Override
	// public List<TaskAnalysis> getTaskAll() {
	// String sql = "select * from COMPILATION_TASK_ANALYSIS ORDER BY
	// CREATE_TIME";
	// SQLQuery query = this.getSession().createSQLQuery(sql);
	// @SuppressWarnings("unchecked")
	// List<TaskAnalysis> list = query.list();
	// return list;
	// }
	//
	// 任务完成情况 查询 TASK_PROGRESS
	@Override
	public List<StatisticsTask> getTaskAll(String year, String compType) {
		try {
			String hql0 = "from StatisticsTask where  TASKBOOK_NO like :param  Order By MODIFY_DATE asc";
			String hql1 = "from StatisticsTask where  status ='审定完成' and TASKBOOK_NO like :param  Order By MODIFY_DATE asc";
			String hql2 = "from StatisticsTask where  status !='审定完成' and TASKBOOK_NO like :param  Order By MODIFY_DATE asc";

			// 编绘任务完成情况
			// 全部计划
			if (compType.equals("0")) {
				return this.getSession().createQuery(hql0).setString("param", "%" + year + "%").list();
			}
			;
			// 已完成
			if (compType.equals("1")) {
				return this.getSession().createQuery(hql1).setString("param", "%" + year + "%").list();
			}
			;
			// 未完成
			if (compType.equals("2")) {
				return this.getSession().createQuery(hql2).setString("param", "%" + year + "%").list();
			}
			;

			return null;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	// 计划 编绘计划任务完成情况

	public List<StatisticsPlan> getTaskAllPlan(String year, String compType) {

		try {
			String hql0 = "from StatisticsPlan where PROCESS_DEF_ID like '%_PLAN%' AND  TASKBOOK_NO like :param Order By MODIFY_DATE asc";
			String hql1 = "from StatisticsPlan where PROCESS_DEF_ID like '%_PLAN%' AND  status ='审定完成' and TASKBOOK_NO like :param Order By MODIFY_DATE asc";
			String hql2 = "from StatisticsPlan where PROCESS_DEF_ID like '%_PLAN%' AND  status !='审定完成' and TASKBOOK_NO like :param Order By MODIFY_DATE asc";

			// 编绘任务完成情况
			// 全部计划
			if (compType.equals("0")) {
				return this.getSession().createQuery(hql0).setString("param", "%" + year + "%").list();
			}
			;
			// 已完成
			if (compType.equals("1")) {
				return this.getSession().createQuery(hql1).setString("param", "%" + year + "%").list();
			}
			;
			// 未完成
			if (compType.equals("2")) {
				return this.getSession().createQuery(hql2).setString("param", "%" + year + "%").list();
			}
			;

			return null;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}

	}

	// 计划 临时表编绘任务完成情况
	@Override
	public List<TaskPlan> zjb_getTaskAllPlan(String year, String compType) {
		try {
			/*
			 * String hql0="from TaskPlan where  create_time like :param";
			 * String hql1=
			 * "from TaskPlan where  status ='审定完成' and create_time like :param"
			 * ; String hql2=
			 * "from TaskPlan where  status !='审定完成' and create_time like :param"
			 * ;
			 */

			String hql0 = "from TaskPlan where    taskBookTime  like :param";
			String hql1 = "from TaskPlan where status ='审定完成'    and taskBookTime like :param";
			String hql2 = "from TaskPlan where status !='审定完成'     and taskBookTime like :param";

			// 编绘任务完成情况
			// 全部计划
			if (compType.equals("0")) {
				return this.getSession().createQuery(hql0).setString("param", "%" + year + "%").list();
			}
			;
			// 已完成
			if (compType.equals("1")) {
				return this.getSession().createQuery(hql1).setString("param", "%" + year + "%").list();
			}
			;
			// 未完成
			if (compType.equals("2")) {
				return this.getSession().createQuery(hql2).setString("param", "%" + year + "%").list();
			}
			;

			return null;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	/// 待办获取任务书编号及任务书创建日期
	@Override
	public String talist1(String procinstid, String taskinstid) {
		try {
			String sql = "select taskbookno from V_MAPNO where id='" + taskinstid + "'";
			SQLQuery query = this.getSession().createSQLQuery(sql);
			return query.uniqueResult().toString();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	@Override
	public String talist2(String procinstid, String taskinstid) {
		try {
			String sql = "select taskbooktime from V_MAPNO where id='" + taskinstid + "'";
			SQLQuery query = this.getSession().createSQLQuery(sql);
			return query.uniqueResult().toString();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	// 临时表编绘任务完成情况
	@Override
	public List<TaskAnalysis> zjb_getTaskAll(String year, String compType) {
		try {
			/*
			 * String hql0="from TaskAnalysis where  create_time like :param";
			 * String hql1=
			 * "from TaskAnalysis where  status ='审定完成' and create_time like :param"
			 * ; String hql2=
			 * "from TaskAnalysis where  status !='审定完成' and create_time like :param"
			 * ;
			 */
			// 0 全部任务 1已完成任务 2未完成任务
			String hql0 = "from TaskAnalysis where     taskbookNo  like :param ";
			String hql1 = "from TaskAnalysis where  status ='审定完成'   and taskbookNo like :param";
			String hql2 = "from TaskAnalysis where   status !='审定完成'     and taskbookNo like :param";

			// 编绘任务完成情况
			// 全部计划
			if (compType.equals("0")) {
				return this.getSession().createQuery(hql0).setString("param", "%" + year + "%").list();
			};
			// 已完成
			if (compType.equals("1")) {
				return this.getSession().createQuery(hql1).setString("param", "%" + year + "%").list();
			};
			// 未完成
			if (compType.equals("2")) {
				return this.getSession().createQuery(hql2).setString("param", "%" + year + "%").list();
			};
			return null;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	


	/// 根据ID获取该 ID所有的流程信息

	@Override
	public List<Taskinfo> getinfo(String proc_inst_id) {
		try {
			String hql = "from Taskinfo where    PROC_INST_ID_=" + proc_inst_id + " Order By ID_ asc";
			return this.getSession().createQuery(hql).list();
		}

		catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}

	}

	/// 根据ID获取该 ID所有的流程信息

	@Override
	public List<Taskinfo> getInfos(String proc_inst_ids) {
		try {
			String hql = "from Taskinfo where    PROC_INST_ID_ in (" + proc_inst_ids + ") Order By ID_ asc";
			return this.getSession().createQuery(hql).list();
		}

		catch (Exception e) {
		
			return null;
		}

	}
	/// 根据用户ID获取该 ID所有的流程信息

	@Override
	public String getGroup(String userid) {
		try {

			String sql = "select a.role_name from role a left join ROLE_USERS_RELATION b on b.role_id=a.id left join users c on b.USER_ID=c.ID	where c.id="
					+ userid;
			SQLQuery query = this.getSession().createSQLQuery(sql);
			return query.uniqueResult().toString();
		}

		catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}

	}

	/*
	 * @Override public List<TaskPlan> getTaskAllPlan(String year,String
	 * compType){ try { String hql0=
	 * "from TaskPlan where  create_time like :param"; String hql1=
	 * "from TaskPlan where  status ='审定完成' and create_time like :param"; String
	 * hql2="from TaskPlan where  status !='审定完成' and create_time like :param";
	 * 
	 * // 编绘任务完成情况 //全部计划 if (compType.equals("0")) { return
	 * this.getSession().createQuery(hql0).setString("param",
	 * "%"+year+"%").list();}; // 已完成 if (compType.equals("1")) { return
	 * this.getSession().createQuery(hql1).setString("param",
	 * "%"+year+"%").list();}; // 未完成 if (compType.equals("2")) { return
	 * this.getSession().createQuery(hql2).setString("param",
	 * "%"+year+"%").list();};
	 * 
	 * return null; } catch (Exception e) { System.out.println(e.getMessage());
	 * return null; } }
	 */
	/**
	 * //编绘计划已完成带年份 新
	 */
	public List<HiTask> getCompleteTaskByYear(List<String> processDefKeys, String page, String pageSize) {
		String sql = "SELECT task_id,process_def_id,task_def_id,process_def_name,process_def_key,process_inst_id,execution_id,task_name,assignee,assignee_name,start_time,end_time,claim_time,delete_reason,parent_process_inst_id,group_id,group_name,process_end_time,record_id FROM (SELECT ROW_NUMBER() OVER(PARTITION BY PROCESS_INST_ID ORDER BY PROCESS_END_TIME DESC) rn,V_HI_TASK.* FROM  V_HI_TASK where PROCESS_DEF_KEY in  (:processDefKeys) and PROCESS_END_TIME is not null) WHERE rn = 1";
		SQLQuery query = this.getSession().createSQLQuery(sql);
		query.setParameterList("processDefKeys", processDefKeys);
		query.addEntity(HiTask.class);
		List<HiTask> list = query.setFirstResult((Integer.parseInt(page) - 1) * Integer.parseInt(pageSize))
				.setMaxResults(Integer.parseInt(pageSize)).list();
		return list;
	}

	/*
	 * @Override public void updateTaskplanUser(String taskId, String
	 * processInstId, String processDefId, String taskDefId, String userId,
	 * String parentProcessInstId,String agreeValue,String taskName) {
	 * 
	 * //获取当前的日期 Date date = new Date(); //设置要获取到什么样的时间 SimpleDateFormat sdf =
	 * new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //获取String类型的时间 String
	 * nowTime = sdf.format(date); //当前ID是否存在的条数 int conust=0;
	 * 
	 * String sql=null; //更新的拼串 String update=null;
	 * 
	 * String taskbookno=null; //任务书编号 String mapno=null; //图号 String
	 * mapname=null; //图名 String scale=null; //比例尺 String processdefname=null;
	 * //任务类型 String node=null; //当前节点 String curruser=null; //当前操作人 String
	 * taskbooktime=null; //任务书创建时间 String taskdefkey=null; String
	 * procdefid=null; String creatime=null; String compilationgroup=null;
	 * //编绘员所属工组 String compilationperson=null; //编绘员 String
	 * compilationworkdays=null; //编绘工天 String compilationstarttime=null;
	 * //编绘开始时间 String compilationendtime=null; //编绘结束时间 String
	 * qualityperson=null; //质检人 String checkworkdays=null; //质检工天 String
	 * qualitystarttime=null; //质检开始时间 String qualityendtime=null; //质检结束时间
	 * String qualityscore=null; //质检评分 String zttime=null; //制图事业部科长审核时间 String
	 * apprvoeperson=null; //审定人 String examineworkdays=null; //审定工天 String
	 * apprvoestarttime=null; //审定开始时间 String apprvoeendtime=null; //审定结束时间
	 * String score=null; //审定评分 String zltime=null; //质量检验科科长审核时间 String
	 * state="0"; //状态 0未完成 1已完成 List<Object> list1=new ArrayList<>();
	 * 
	 * //如果是制图科长分配任务给编绘组长，则跳出。
	 * if(taskDefId.indexOf("u_task_distribution_group")>-1) { return; };
	 * //先查询是否存在该数据 Query spquery= this.getSession().createSQLQuery(
	 * "select count(*) from TASK_PROGRESS where SUPERID='"+processInstId+"'");
	 * Integer count=((Number)spquery.uniqueResult()).intValue(); Query prquery=
	 * this.getSession().createSQLQuery(
	 * "select count(*) from TASK_PROGRESS where PROCESSINSTID='"
	 * +processInstId+"'"); Integer
	 * prcount=((Number)spquery.uniqueResult()).intValue(); //如果不存在 根据ID 获取
	 * 任务编号，图号，图名，比例尺，任务类型，当前节点，当前操作人，任务书创建日期 if(count==0&&prcount==0) { sql =
	 * "select e.TASKBOOK_NO as TASKBOOKNO,d.MAP_NO as MAPNO,d.TASK_NAME as MAPNAME,d.SCALE as SCALE,f.name_ as PROCESSDEFNAME,a.NAME_ as NODE,(case when i.first_ is null then h.name_ else i.first_ end) as CURRUSER,to_char(e.ISSUE_TIME,'yyyy-mm-dd') as TASKBOOKTIME,B.PROC_INST_ID_ as PROCESSINSTID, B.SUPER_PROCESS_INSTANCE_ID_ as SUPERID,a.TASK_DEF_KEY_ as TASKDEFKEY,a.PROC_DEF_ID_ as PROCDEFID from ACT_RU_TASK a left JOIN ACT_HI_PROCINST B ON B.PROC_INST_ID_= a.proc_inst_id_ left join PROCESS_LOG_DETAIL c on c.PROCESS_INST_ID=B.SUPER_PROCESS_INSTANCE_ID_ left outer join  COMPILATION_CHILD_TASK d on d.id=c.DETAIL_RECORD_ID left outer join  compilation_task_book e on e.id=d.PARENT_TASKBOOK_ID left outer join  act_re_procdef f on f.id_=a.proc_def_id_ left join act_ru_identitylink g on a.id_=g.task_id_ left join act_id_group h on g.group_id_=h.id_ left join act_id_user i on a.assignee_=i.id_ where B.SUPER_PROCESS_INSTANCE_ID_="
	 * +processInstId; //数据库查询出来的每一行封装成一个Map类型，Key为数据库字段，Value为值，
	 * 然后把这些Map放到了一个List里面， Query
	 * querylist=this.getSession().createSQLQuery(sql).setResultTransformer(
	 * Transformers.ALIAS_TO_ENTITY_MAP); List list=querylist.list();
	 * 
	 * for (int i = 0; i < list.size(); i++) { //UUID String uid =
	 * UUID.randomUUID().toString().replace("-", "").toLowerCase(); Map<String,
	 * Object> map = (Map<String, Object>) list.get(i); String sqlinsert =
	 * "Insert into TASK_PROGRESS (ID,TASKBOOKNO,MAPNO,MAPNAME,SCALE,PROCESSDEFNAME,NODE,CURRUSER,TASKBOOKTIME,PROCESSINSTID,SUPERID,STATE,COMPILATIONPERSON,TASKDEFKEY,PROCDEFID,CREATETIME,COMPILATIONSTARTTIME) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"
	 * ; Query query = getSession().createSQLQuery(sqlinsert);
	 * 
	 * query.setString(0, uid); query.setString(1, map.get("TASKBOOKNO") != null
	 * ? map.get("TASKBOOKNO").toString() : null); query.setString(2,
	 * map.get("MAPNO") != null ? map.get("MAPNO").toString() : null);
	 * query.setString(3, map.get("MAPNAME") != null ?
	 * map.get("MAPNAME").toString() : null); query.setString(4,
	 * map.get("SCALE") != null ? map.get("SCALE").toString() : null);
	 * query.setString(5, map.get("PROCESSDEFNAME") != null ?
	 * map.get("PROCESSDEFNAME").toString() : null); query.setString(6,
	 * map.get("NODE") != null ? map.get("NODE").toString() : null);
	 * query.setString(7, map.get("CURRUSER") != null ?
	 * map.get("CURRUSER").toString() : null); query.setString(8,
	 * map.get("TASKBOOKTIME") != null ? map.get("TASKBOOKTIME").toString() :
	 * null); query.setString(9, map.get("PROCESSINSTID") != null ?
	 * map.get("PROCESSINSTID").toString() : null); query.setString(10,
	 * map.get("SUPERID") != null ? map.get("SUPERID").toString() : null);
	 * query.setString(11, "0"); query.setString(12, map.get("CURRUSER") != null
	 * ? map.get("CURRUSER").toString() : null); query.setString(13,
	 * map.get("TASKDEFKEY") != null ? map.get("TASKDEFKEY").toString() : null);
	 * query.setString(14, map.get("PROCDEFID") != null ?
	 * map.get("PROCDEFID").toString() : null); query.setString(15,
	 * nowTime);//创建时间 query.setString(16, nowTime);//编绘开始时间
	 * query.executeUpdate(); } getSession().getTransaction().commit(); }
	 * //2、获取当前任务的记录 if(taskDefId.indexOf("u_task_distribution_worker")<0)
	 * {///组长分配人员时，ACT_RU_TASK 中还未有记录，所以跳过这 sql =
	 * "select a.NAME_  as NODE,  (case when i.first_ is null then h.name_ else i.first_ end) as CURRUSER,to_char(a.CREATE_TIME_,'yyyy-mm-dd hh24:mi:ss') as CREATETIME, a.PROC_DEF_ID_ as PROCDEFID,a.TASK_DEF_KEY_ as TASKDEFKEY from ACT_RU_TASK a left JOIN ACT_HI_PROCINST B ON B.PROC_INST_ID_= a.proc_inst_id_ left outer join  act_re_procdef f on f.id_=a.proc_def_id_ left join act_ru_identitylink g on a.id_=g.task_id_ left join act_id_group h on g.group_id_=h.id_ left join act_id_user i on a.assignee_=i.id_ where a.PROC_INST_ID_='"
	 * +processInstId+"'"; //数据库查询出来的每一行封装成一个Map类型，Key为数据库字段，Value为值，
	 * 然后把这些Map放到了一个List里面， Query
	 * query1=this.getSession().createSQLQuery(sql).setResultTransformer(
	 * Transformers.ALIAS_TO_ENTITY_MAP); list1=query1.list(); }
	 * if(list1.size()==0) {///如果RU_TASK取不到值，则来HI_TASK中取值，并赋STATE为1 sql =
	 * "select a.NAME_  as NODE,  (case when i.first_ is null then h.name_ else i.first_ end) as CURRUSER,to_char(a.CLAIM_TIME_,'yyyy-mm-dd hh24:mi:ss') as CREATETIME, a.PROC_DEF_ID_ as PROCDEFID,a.TASK_DEF_KEY_ as TASKDEFKEY from ACT_HI_TASKINST a left JOIN ACT_HI_PROCINST B ON B.PROC_INST_ID_= a.proc_inst_id_ left outer join  act_re_procdef f on f.id_=a.proc_def_id_ left join act_ru_identitylink g on a.id_=g.task_id_ left join act_id_group h on g.group_id_=h.id_ left join act_id_user i on a.assignee_=i.id_ where a.PROC_INST_ID_='"
	 * +processInstId+"' and rownum = 1 order by a.CLAIM_TIME_ desc"; Query
	 * query2=this.getSession().createSQLQuery(sql).setResultTransformer(
	 * Transformers.ALIAS_TO_ENTITY_MAP); list1=query2.list(); } Map<String,
	 * Object> map = (Map<String, Object>) list1.get(0);
	 * node=map.get("NODE").toString(); //当前节点
	 * curruser=map.get("CURRUSER").toString(); //当前操作人
	 * taskdefkey=map.get("TASKDEFKEY").toString(); //当前任务ID
	 * procdefid=map.get("PROCDEFID").toString();
	 * 
	 * if(taskDefId.equals("u_task_distribution_worker")){ String sqlstr =
	 * "select a.NAME_ from ACT_ID_GROUP a left join ACT_ID_MEMBERSHIP b on a.ID_=b.GROUP_ID_ left join ACT_ID_USER c on b.USER_ID_=c.ID_ where c.FIRST_ like '%"
	 * +curruser+"%' and a.NAME_ not like '%组长%'"; SQLQuery query =
	 * this.getSession().createSQLQuery(sqlstr); compilationgroup =
	 * query.uniqueResult().toString();//编绘员所属工组 compilationperson=curruser;
	 * //编绘员 compilationstarttime=nowTime; //计划的编绘开始时间 ///清除编绘任务的编绘开始时间和编绘人
	 * String sqlplan =
	 * "update TASK_PROGRESS Set COMPILATIONSTARTTIME=''  WHERE PROCDEFID not like '%_PLAN%' and  SUPERID='"
	 * +processInstId+"'"; SQLQuery queryplan =
	 * this.getSession().createSQLQuery(sqlplan); queryplan.executeUpdate();
	 * 
	 * }
	 * 
	 * if(taskDefId.equals("u_task_zxblc")||taskDefId.equals("u_task_btcxbxbcsb"
	 * )||taskDefId.equals("u_task_work")){ compilationstarttime=nowTime;
	 * //任务的编绘开始时间 }
	 * 
	 * 
	 * if(node.equals("分配任务给质检员")){ String sqlstr =
	 * "select (case when (to_date(substr('"+nowTime+
	 * "', 0,10),'yyyy-mm-dd')-to_date(substr(COMPILATIONSTARTTIME, 0,10),'yyyy-mm-dd'))<=0 then '1' else  to_char((to_date(substr('"
	 * +nowTime+
	 * "', 0,10),'yyyy-mm-dd')-to_date(substr(COMPILATIONSTARTTIME, 0,10),'yyyy-mm-dd'))) end ) COMPILATIONWORKDAYS from TASK_PROGRESS where PROCESSINSTID="
	 * +processInstId; SQLQuery query =
	 * this.getSession().createSQLQuery(sqlstr); compilationendtime=nowTime;
	 * //编绘结束时间 compilationworkdays = query.uniqueResult().toString(); //编绘工天 }
	 * 
	 * 
	 * if(taskdefkey.equals("u_task_plan_zhijian")||
	 * taskdefkey.equals("u_task_zhijian_bhwtcljl")||
	 * taskdefkey.equals("u_task_zhijian_clyj")||taskdefkey.equals(
	 * "u_task_zhijian_bhwtcljl") ) { String sqlstr =
	 * "select b.first_ from ACT_RU_TASK  a left join ACT_ID_USER b on b.id_=a.ASSIGNEE_ where a.PROC_INST_ID_="
	 * +processInstId; SQLQuery query =
	 * this.getSession().createSQLQuery(sqlstr); qualityperson =
	 * query.uniqueResult().toString();//质检人
	 * 
	 * 
	 * 
	 * qualitystarttime=nowTime; //计划质检开始时间 };
	 * 
	 * if(taskdefkey.equals("u_task_plan_zhijian")||
	 * taskdefkey.equals("u_task_zhijian_zjjielunb")||
	 * taskdefkey.equals("u_task_zjjielunb") ) { String sqlstr =
	 * "select (case when (to_date(substr("+nowTime+
	 * ", 0,10),'yyyy-mm-dd')-to_date(substr(COMPILATIONSTARTTIME, 0,10),'yyyy-mm-dd'))<=0 then '1' else  to_char((to_date(substr("
	 * +nowTime+
	 * ", 0,10),'yyyy-mm-dd')-to_date(substr(COMPILATIONSTARTTIME, 0,10),'yyyy-mm-dd'))) end ) COMPILATIONWORKDAYS from TASK_PROGRES where PROCESSINSTID="
	 * +processInstId; SQLQuery query =
	 * this.getSession().createSQLQuery(sqlstr); checkworkdays =
	 * query.uniqueResult().toString(); //质检工天 qualityendtime=nowTime; //质检结束时间
	 * }
	 * 
	 * qualityscore=null; //质检评分
	 * 
	 * if(taskdefkey.equals("u_task_ztkz_shenhe")||
	 * taskdefkey.equals("u_task_zhijian_shenhe")||
	 * taskdefkey.equals("u_task_plan_zhijian_shenhe")) { zttime=nowTime;
	 * //制图事业部科长审核时间 }
	 * 
	 * 
	 * if(node.indexOf("分配任务给审定员")>-1) { String sqlstr =
	 * "select b.first_ from ACT_HI_TASKINST  a left join ACT_ID_USER b on b.id_=a.ASSIGNEE_ where a.PROC_INST_ID_="
	 * +processInstId; SQLQuery query =
	 * this.getSession().createSQLQuery(sqlstr); qualityperson =curruser;//审定人
	 * qualitystarttime=nowTime; //审定开始时间 };
	 * 
	 * if(taskdefkey.equals("u_task_plan_shending")||
	 * taskdefkey.equals("u_task_shending_sdjielunb")) { String sqlstr =
	 * "select (case when (to_date(substr("+nowTime+
	 * ", 0,10),'yyyy-mm-dd')-to_date(substr(COMPILATIONSTARTTIME, 0,10),'yyyy-mm-dd'))<=0 then '1' else  to_char((to_date(substr("
	 * +nowTime+
	 * ", 0,10),'yyyy-mm-dd')-to_date(substr(COMPILATIONSTARTTIME, 0,10),'yyyy-mm-dd'))) end ) COMPILATIONWORKDAYS from TASK_PROGRES where PROCESSINSTID="
	 * +processInstId; SQLQuery query =
	 * this.getSession().createSQLQuery(sqlstr); checkworkdays =
	 * query.uniqueResult().toString(); //质检工天 qualityendtime=nowTime; //质检结束时间
	 * }
	 * 
	 * score=map.get("CURRUSER").toString(); //审定评分
	 * 
	 * if(taskdefkey.equals("u_task_zlkz_shenhe")||
	 * taskdefkey.equals("u_task_shending_shenhe")) { zltime=nowTime;
	 * //质量检验科科长审核时间 }
	 * 
	 * node=node!=null?"NODE='"+node+"',":"";
	 * curruser=curruser!=null?"CURRUSER='"+curruser+"',":"";
	 * taskdefkey=taskdefkey!=null?"TASKDEFKEY='"+taskdefkey+"',":"";
	 * procdefid=procdefid!=null?"PROCDEFID='"+procdefid+"',":"";
	 * compilationgroup=compilationgroup!=null?"COMPILATIONGROUP='"+
	 * compilationgroup+"',":"";
	 * compilationperson=compilationperson!=null?"COMPILATIONPERSON='"+
	 * compilationperson+"',":"";
	 * compilationworkdays=compilationworkdays!=null?"COMPILATIONWORKDAYS='"+
	 * compilationworkdays+"',":"";
	 * compilationstarttime=compilationstarttime!=null?"COMPILATIONSTARTTIME='"+
	 * compilationstarttime+"',":"";
	 * compilationendtime=compilationendtime!=null?"COMPILATIONENDTIME='"+
	 * compilationendtime+"',":"";
	 * qualityperson=qualityperson!=null?"QUALITYPERSON='"+qualityperson+"',":
	 * "";
	 * checkworkdays=checkworkdays!=null?"CHECKWORKDAYS='"+checkworkdays+"',":
	 * ""; qualitystarttime=qualitystarttime!=null?"QUALITYSTARTTIME='"+
	 * qualitystarttime+"',":"";
	 * qualityendtime=qualityendtime!=null?"QUALITYENDTIME='"+qualityendtime+
	 * "',":"";
	 * qualityscore=qualityscore!=null?"QUALITYSCORE='"+qualityscore+"',":"";
	 * zttime=zttime!=null?"ZTTIME='"+zttime+"',":"";
	 * apprvoeperson=apprvoeperson!=null?"APPRVOEPERSON='"+apprvoeperson+"',":
	 * "";
	 * examineworkdays=examineworkdays!=null?"EXAMINEWORKDAYS='"+examineworkdays
	 * +"',":""; apprvoestarttime=apprvoestarttime!=null?"APPRVOESTARTTIME='"+
	 * apprvoestarttime+"',":"";
	 * apprvoeendtime=apprvoeendtime!=null?"APPRVOEENDTIME='"+apprvoeendtime+
	 * "',":""; score=score!=null?"SCORE='"+score+"',":"";
	 * zltime=zltime!=null?"ZLTIME='"+zltime+"',":"";
	 * state="STATE='"+state+"',";
	 * update=node+curruser+taskdefkey+procdefid+compilationgroup+
	 * compilationperson+compilationworkdays+compilationstarttime+
	 * compilationendtime+qualityperson+checkworkdays+qualitystarttime+
	 * qualityendtime+qualityscore+zttime+apprvoeperson+examineworkdays+
	 * apprvoestarttime+apprvoeendtime+score+zltime+state;
	 * 
	 * if(taskDefId.indexOf("u_task_distribution_worker")<0){///组长分配成员的时候，此时
	 * processInstId 为父ID 并且已有数据了无更新内容 直接跳过
	 * 
	 * sql = "update TASK_PROGRESS Set "+update+" MODIFY_DATE='"+nowTime+
	 * "' WHERE  PROCESSINSTID='"+processInstId+"'"; SQLQuery query =
	 * this.getSession().createSQLQuery(sql); query.executeUpdate();
	 * 
	 * };
	 * 
	 * 
	 * }
	 */

	// 更新任务情况
	// 查询是否存在数据
	public int updateTaskProgress_A(String processInstId, String nowTime, String taskDefId) {

		Query taskcount = this.getSession()
				.createSQLQuery("select count(*) from STATISTICS_TASK where PARENT_PROCESS_INST_ID='" + processInstId
						+ "' or  PROCESS_INST_ID='" + processInstId + "'");
		int task = ((Number) taskcount.uniqueResult()).intValue();
		Query playcount = this.getSession()
				.createSQLQuery("select count(*) from STATISTICS_PLAN where PARENT_PROCESS_INST_ID='" + processInstId
						+ "' or  PROCESS_INST_ID='" + processInstId + "'");
		int play = ((Number) playcount.uniqueResult()).intValue();
		if ((task + play) > 0) {// 如果有数据的话，刚按ID更新数据
			String db = null;/// 判断应该更新到哪个数据库
			if (task > 0) {
				db = " STATISTICS_TASK";
			} else {
				db = " STATISTICS_PLAN";
			}
			Map<String, Object> map = tasklist(processInstId);
			String STATUS = map.get("STATUS").toString(); // 当前状态
			String TASK_NAME = map.get("TASK_NAME").toString(); // 当前节点名称
			String CURR_USER = map.get("CURR_USER").toString(); // 当前操作人
			String STATE = map.get("STATE").toString();
			String TASK_DEF_ID = map.get("TASK_DEF_ID").toString(); //
			String PROCESS_DEF_ID = map.get("PROCESS_DEF_ID").toString(); //
			String update = db + " SET STATUS='" + STATUS + "',TASK_NAME='" + TASK_NAME + "',CURR_USER='" + CURR_USER
					+ "',STATE='" + STATE + "',TASK_DEF_ID='" + TASK_DEF_ID + "',";

			// 更新编绘人，编绘开始时间
			if (taskDefId.equals("u_task_zxblc") || taskDefId.equals("u_task_btcxbxbcsb")
					|| (taskDefId.equals("u_task_work") && PROCESS_DEF_ID.indexOf("SOURCE") > 0)) {
				update = update + "COMPILATION_PERSON='" + CURR_USER + "',COMPILATION_START_TIME='" + nowTime + "',";
			}

			// 更新编绘质检人，质检开始时间
			if (taskDefId.equals("u_task_distribution_zhijian")
					|| taskDefId.equals("u_task_plan_distribution_zhijian")) {
				update = update + "QUALITY_PERSON='" + CURR_USER + "',QUALITY_START_TIME='" + nowTime + "',";
			}
			// 更新审定人，审定开始时间
			if (taskDefId.equals("u_task_distribution_shending")
					|| taskDefId.equals("u_task_plan_distribution_shending")) {
				update = update + " APPRVOE_START_TIME='" + nowTime + "',";
			}
			// 更新制图科长审定时间
			if (taskDefId.equals("u_task_ztkz_shenhe") || taskDefId.equals("u_task_zhijian_shenhe")
					|| taskDefId.equals("u_task_plan_zhijian_shenhe")) {
				update = update + "ZT_TIME='" + nowTime + "',";
			}
			// 更新质检科长审定时间
			if (taskDefId.equals("u_task_zlkz_shenhe") || taskDefId.equals("u_task_shending_shenhe")) {
				update = update + "ZL_TIME='" + nowTime + "',";
			}
			// 更新质检员评分
			if (TASK_DEF_ID.equals("u_task_ztkz_shenhe") || TASK_DEF_ID.equals("u_task_zhijian_shenhe")) {
				Query zjquery = this.getSession().createSQLQuery(
						"SELECT a.GRADING FROM FORM_DEFECTIVE a left join ACT_HI_TASKINST b on a.TASK_INST_ID=b.ID_ where a.process_inst_id='"
								+ processInstId + "'  and b.TASK_DEF_KEY_='u_task_shending_sdjilub'");
				String QUALITY_SCORE = (String) zjquery.uniqueResult();
				update = update + "QUALITY_SCORE='" + QUALITY_SCORE + "',";
			}
			// 更新审定员评分
			if (TASK_DEF_ID.equals("u_task_zlkz_shenhe") || TASK_DEF_ID.equals("u_task_shending_shenhe")) {
				Query sdquery = this.getSession().createSQLQuery(
						"SELECT a.GRADING FROM FORM_DEFECTIVE a left join ACT_HI_TASKINST b on a.TASK_INST_ID=b.ID_ where a.process_inst_id='"
								+ processInstId + "'  and b.TASK_DEF_KEY_='u_task_zhijian_zjjilub'");
				String SCORE = (String) sdquery.uniqueResult();
				update = db + " SET STATUS='" + STATUS + "',TASK_NAME='" + TASK_NAME + "',CURR_USER='" + CURR_USER
						+ "',STATE='" + STATE + "',SCORE='" + SCORE + "',";
			}
			updata(update, nowTime, processInstId);
		}
		return task + play;
	};

	// 创建数据
	@SuppressWarnings("unused")
	public List<Map<String, Object>> updateTaskProgress_B(String processInstId, String nowTime, String taskDefId) {
		Date nowDate = new Date(new Date().getTime());
		String sql = null;
		String tab = null;
		// 如果当前不是组长分配人员，说明是先前数据，则去运行的表中查找，如果是的话，则去找任务书相关信息
		if (taskDefId.equals("u_task_distribution_worker")) {
			tab = "B.SUPER_PROCESS_INSTANCE_ID_";
		} else {
			tab = "a.PROC_INST_ID_";
		}
		;

		sql = "select  e.TASKBOOK_NO as TASKBOOK_NO,d.MAP_NO as MAP_NO,d.TASK_NAME as MAP_NAME,d.SCALE as SCALE,f.name_ as PROCESS_DEF_NAME,a.NAME_ as TASK_NAME,d.STATUS,(case when i.first_ is null then h.name_ else i.first_ end) as CURR_USER,to_char(e.ISSUE_TIME,'yyyy-mm-dd') as TASK_BOOK_TIME,B.PROC_INST_ID_ as PROCESS_INST_ID, B.SUPER_PROCESS_INSTANCE_ID_ as PARENT_PROCESS_INST_ID,a.TASK_DEF_KEY_ as TASK_DEF_ID,a.PROC_DEF_ID_ as PROCESS_DEF_ID,a.EXECUTION_ID_  as  EXECUTION_ID,I.first_ AS PERSON,K.NAME_ AS COMPILATION_GROUP from ACT_RU_TASK a left JOIN ACT_HI_PROCINST B ON B.PROC_INST_ID_= a.proc_inst_id_ left join PROCESS_LOG_DETAIL c on c.PROCESS_INST_ID=B.SUPER_PROCESS_INSTANCE_ID_ left outer join  COMPILATION_CHILD_TASK d on d.id=c.DETAIL_RECORD_ID left outer join  compilation_task_book e on e.id=d.PARENT_TASKBOOK_ID left outer join  act_re_procdef f on f.id_=a.proc_def_id_ left join act_ru_identitylink g on a.id_=g.task_id_ left join act_id_group h on g.group_id_=h.id_ left join ACT_ID_MEMBERSHIP j on j.USER_ID_=a.ASSIGNEE_ left join act_ID_GROUP K ON K.ID_=J.GROUP_ID_  left join act_id_user i on a.assignee_=i.id_  where  K.NAME_ not LIKE '%组长%' and  "
				+ tab + "=" + processInstId;
		// 数据库查询出来的每一行封装成一个Map类型，Key为数据库字段，Value为值， 然后把这些Map放到了一个List里面，
		Query querylist = this.getSession().createSQLQuery(sql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		List<Map<String, Object>> list = querylist.list();

		for (int i = 0; i < list.size(); i++) {
			String uid = UUID.randomUUID().toString().replace("-", "").toLowerCase();
			Map<String, Object> map = (Map<String, Object>) list.get(i);
			String pdd = (String) map.get("PROCESS_DEF_ID");
			// 根据pdd来判断要新增到哪一张表中

			if (pdd.indexOf("PLAN") > 0) {
				tab = "STATISTICS_PLAN";
			} else {
				tab = "STATISTICS_TASK";
			}
			;

			String sqlinsert = "Insert into " + tab
					+ "(ID,TASKBOOK_NO,MAP_NO,MAP_NAME,SCALE,PROCESS_DEF_NAME,STATUS,CURR_USER,TASK_BOOK_TIME,PROCESS_INST_ID,PARENT_PROCESS_INST_ID,STATE,COMPILATION_PERSON,COMPILATION_START_TIME,COMPILATION_GROUP,QUALITY_PERSON,QUALITY_START_TIME,APPRVOE_PERSON,APPRVOE_START_TIME,TASK_DEF_ID,PROCESS_DEF_ID,CREATE_TIME,TASK_NAME,EXECUTION_ID,MODIFY_DATE)  values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,to_date('"
					+ nowTime + "','yyyy-MM-dd HH24:mi:ss'))";
			Query query = getSession().createSQLQuery(sqlinsert);
			query.setString(0, uid);
			query.setString(1, map.get("TASKBOOK_NO") != null ? map.get("TASKBOOK_NO").toString() : "");
			query.setString(2, map.get("MAP_NO") != null ? map.get("MAP_NO").toString() : "");
			query.setString(3, map.get("MAP_NAME") != null ? map.get("MAP_NAME").toString() : "");
			query.setString(4, map.get("SCALE") != null ? map.get("SCALE").toString() : "");
			query.setString(5, map.get("PROCESS_DEF_NAME") != null ? map.get("PROCESS_DEF_NAME").toString() : "");
			query.setString(6, map.get("STATUS") != null ? map.get("STATUS").toString() : "");
			query.setString(7, map.get("CURR_USER") != null ? map.get("CURR_USER").toString() : "");
			query.setString(8, map.get("TASK_BOOK_TIME") != null ? map.get("TASK_BOOK_TIME").toString() : "");
			query.setString(9, map.get("PROCESS_INST_ID") != null ? map.get("PROCESS_INST_ID").toString() : "");
			query.setString(10,
					map.get("PARENT_PROCESS_INST_ID") != null ? map.get("PARENT_PROCESS_INST_ID").toString() : "");
			query.setString(11, "0");
			if (taskDefId.equals("u_task_distribution_worker") && tab.equals("STATISTICS_PLAN")) {/// 如果是组长分配人员，则给编绘员赋当前值
				query.setString(12, map.get("PERSON") != null ? map.get("PERSON").toString() : "");
				query.setString(13, nowTime);// 编绘开始时间
				query.setString(14,
						map.get("COMPILATION_GROUP") != null ? map.get("COMPILATION_GROUP").toString() : "");// 所属群组
				query.setString(15, "");
				query.setString(16, "");
				query.setString(17, "");
				query.setString(18, "");
			} else if (taskDefId.equals("u_task_plan_distribution_zhijian")) {// 质检分配人员
																				// 质检开始日期
				query.setString(12, "");
				query.setString(13, "");
				query.setString(14, "");
				query.setString(15, map.get("PERSON") != null ? map.get("PERSON").toString() : "");// QUALITY_PERSON
				query.setString(16, nowTime); // QUALITY_START_TIME
				query.setString(17, "");
				query.setString(18, "");

			} else if (taskDefId.equals("u_task_plan_distribution_shengdin")) {// 审定分配人员
																				// 审定开始日期
				query.setString(12, "");
				query.setString(13, "");
				query.setString(14, "");
				query.setString(15, "");
				query.setString(16, "");
				query.setString(17, map.get("PERSON") != null ? map.get("PERSON").toString() : "");// APPRVOE_PERSON
				query.setString(18, nowTime); // APPRVOE_START_TIME
			} else {
				query.setString(12, "");
				query.setString(13, "");
				query.setString(14, "");
				query.setString(15, "");
				query.setString(16, "");
				query.setString(17, "");
				query.setString(18, "");
			}
			;
			query.setString(19, map.get("TASK_DEF_ID") != null ? map.get("TASK_DEF_ID").toString() : "");
			query.setString(20, pdd); // PROCESS_DEF_ID
			query.setString(21, nowTime);// 创建时间
			query.setString(22, map.get("TASK_NAME") != null ? map.get("TASK_NAME").toString() : "");// 当前任务名称
			query.setString(23, map.get("EXECUTION_ID") != null ? map.get("EXECUTION_ID").toString() : "");// 当前任务名称

			query.executeUpdate();
		}
		return list;

	};

	// 取出数据 封装成MAP
	public Map<String, Object> tasklist(String processInstId) {
		List<Map<String, Object>> list = null;
		Query query = null;
		String sql = null;
		sql = "select  a.NAME_  as TASK_NAME, (case when i.first_ is null then h.name_ else i.first_ end) as CURR_USER,a.TASK_DEF_KEY_ as TASK_DEF_ID,a.PROC_DEF_ID_ as PROCESS_DEF_ID,d.STATUS from ACT_RU_TASK a left JOIN ACT_HI_PROCINST B ON B.PROC_INST_ID_= a.proc_inst_id_ left join PROCESS_LOG_DETAIL c on c.PROCESS_INST_ID=B.SUPER_PROCESS_INSTANCE_ID_ left outer join  COMPILATION_CHILD_TASK d on d.id=c.DETAIL_RECORD_ID left outer join  act_re_procdef f on f.id_=a.proc_def_id_ left join act_ru_identitylink g on a.id_=g.task_id_ left join act_id_group h on g.group_id_=h.id_ left join act_id_user i on a.assignee_=i.id_ where a.PROC_INST_ID_='"
				+ processInstId + "'";
		// 数据库查询出来的每一行封装成一个Map类型，Key为数据库字段，Value为值， 然后把这些Map放到了一个List里面，
		query = this.getSession().createSQLQuery(sql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		list = query.list();
		if (list.size() == 0) {/// 如果RU_TASK取不到值，则来HI_TASK中取值，并赋STATE为1
			sql = "select I.first_ AS PERSON, a.NAME_  as TASK_NAME, (case when i.first_ is null then h.name_ else i.first_ end) as CURR_USER,a.TASK_DEF_KEY_ as TASK_DEF_ID,a.PROC_DEF_ID_ as PROCESS_DEF_ID,d.STATUS from ACT_HI_TASKINST a left JOIN ACT_HI_PROCINST B ON B.PROC_INST_ID_= a.proc_inst_id_ left join PROCESS_LOG_DETAIL c on c.PROCESS_INST_ID=B.SUPER_PROCESS_INSTANCE_ID_ left outer join  COMPILATION_CHILD_TASK d on d.id=c.DETAIL_RECORD_ID left outer join  act_re_procdef f on f.id_=a.proc_def_id_ left join act_ru_identitylink g on a.id_=g.task_id_ left join act_id_group h on g.group_id_=h.id_ left join act_id_user i on a.assignee_=i.id_ where a.PROC_INST_ID_='"
					+ processInstId + "' and rownum = 1 order by a.CLAIM_TIME_ desc";
			query = this.getSession().createSQLQuery(sql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			list = query.list();
		}
		Map<String, Object> map = (Map<String, Object>) list.get(0);
		if (list.size() == 0) {
			map.put("STATE", "1");
		} else {
			map.put("STATE", "0");
		}
		;

		return map;

	};

	/// 更新任务
	public void updata(String update, String nowTime, String processInstId) {
		String sql = "update " + update + " MODIFY_DATE=to_date('" + nowTime
				+ "','yyyy-MM-dd HH24:mi:ss') WHERE  PROCESS_INST_ID='" + processInstId + "'";
		SQLQuery query = this.getSession().createSQLQuery(sql);
		query.executeUpdate();
	};

	@Override
	// 更新编绘结束时间和计算工天
	public void updateTaskProgress_C(String processInstId, String nowTime) {
		Map<String, Object> map = tasklist(processInstId);
		String pdd = (String) map.get("PROCESS_DEF_ID");
		String db = null;
		// 根据pdd来判断要新增到哪一张表中
		if (pdd.indexOf("PLAN") > 0) {
			db = " STATISTICS_PLAN ";
		} else {
			db = " STATISTICS_TASK ";
		}

		Query prquery = this.getSession().createSQLQuery(
				"select (case when COMPILATION_START_TIME is null then ''  when (to_date(substr('" + nowTime
						+ "', 0,10),'yyyy-mm-dd')-to_date(substr(COMPILATION_START_TIME, 0,10),'yyyy-mm-dd'))<=0 then '1' else  to_char((to_date(substr('"
						+ nowTime
						+ "', 0,10),'yyyy-mm-dd')-to_date(substr(COMPILATION_START_TIME, 0,10),'yyyy-mm-dd'))) end ) COMPILATIONWORKDAYS from  "
						+ db + "  where PROCESS_INST_ID='" + processInstId + "'");
		String COMPILATION_WORKDAYS = (String) prquery.uniqueResult();
		String update = db + "  SET COMPILATION_WORKDAYS='" + COMPILATION_WORKDAYS + "',COMPILATION_END_TIME='"
				+ nowTime + "',";
		updata(update, nowTime, processInstId);

	}

	// 更新质检结束时间和计算工天
	@Override
	public void updateTaskProgress_E(String processInstId, String nowTime) {
		Map<String, Object> map = tasklist(processInstId);
		String pdd = (String) map.get("PROCESS_DEF_ID");
		String db = null;
		// 根据pdd来判断要新增到哪一张表中
		if (pdd.indexOf("PLAN") > 0) {
			db = " STATISTICS_PLAN ";
		} else {
			db = " STATISTICS_TASK ";
		}
		Query prquery = this.getSession()
				.createSQLQuery("select (case when QUALITY_START_TIME is null then '' when  (to_date(substr('" + nowTime
						+ "', 0,10),'yyyy-mm-dd')-to_date(substr(QUALITY_START_TIME, 0,10),'yyyy-mm-dd'))<=0 then '1' else  to_char((to_date(substr('"
						+ nowTime
						+ "', 0,10),'yyyy-mm-dd')-to_date(substr(QUALITY_START_TIME, 0,10),'yyyy-mm-dd'))) end ) CHECK_WORKDAYS from "
						+ db + " where PROCESS_INST_ID='" + processInstId + "'");
		String CHECK_WORKDAYS = (String) prquery.uniqueResult();
		String update = db + " SET CHECK_WORKDAYS='" + CHECK_WORKDAYS + "',QUALITY_END_TIME='" + nowTime + "',";
		updata(update, nowTime, processInstId);
	}

	// 更新审定结束时间和计算工天
	@Override
	public void updateTaskProgress_H(String processInstId, String nowTime) {
		Map<String, Object> map = tasklist(processInstId);
		String pdd = (String) map.get("PROCESS_DEF_ID");
		String db = null;
		// 根据pdd来判断要新增到哪一张表中
		if (pdd.indexOf("PLAN") > 0) {
			db = " STATISTICS_PLAN ";
		} else {
			db = " STATISTICS_TASK ";
		}
		Query prquery = this.getSession()
				.createSQLQuery("select (case when APPRVOE_START_TIME is null then '' when  (to_date(substr('" + nowTime
						+ "', 0,10),'yyyy-mm-dd')-to_date(substr(APPRVOE_START_TIME, 0,10),'yyyy-mm-dd'))<=0 then '1' else  to_char((to_date(substr('"
						+ nowTime
						+ "', 0,10),'yyyy-mm-dd')-to_date(substr(APPRVOE_START_TIME, 0,10),'yyyy-mm-dd'))) end ) EXAMINE_WORKDAYS from  "
						+ db + "  where PROCESS_INST_ID='" + processInstId + "'");
		String EXAMINE_WORKDAYS = (String) prquery.uniqueResult();
		String update = db + " SET EXAMINE_WORKDAYS='" + EXAMINE_WORKDAYS + "',APPRVOE_END_TIME='" + nowTime + "',";
		updata(update, nowTime, processInstId);

	}

	@Override
	public void updateTaskplanUser(String taskId, String processInstId, String processDefId, String taskDefId,
			String userId, String parentProcessInstId, String agreeValue, String taskName) {
		// TODO 自动生成的方法存根

	}

	@Override
	public Map<String, Object> getscore(String proc_inst_id, String ry) {
				String sql = "select grading,proc_inst_id_ from (select grading,proc_inst_id_ from V_ACT_HI_INFO where name_ like '%" + ry
				+ "%'   and GRADING is not null  and proc_inst_id_ in (" + proc_inst_id
				+ ") order by start_time_ desc)";
		Query query = this.getSession().createSQLQuery(sql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		List<Map<String, Object>> list = null;
		Map<String, Object>  result =  new HashMap<String, Object>();
		list = query.list();
		if (list != null && list.size() > 0 ) {
			for (int i = 0; i < list.size(); i++) {
				Map<String, Object> map = (Map<String, Object>) list.get(i);
				result.put(map.get("PROC_INST_ID_").toString(), map.get("GRADING").toString());
			}
		}

		return result;

	}
	


	
	/**
	 * 获取编印工作量完成情况数据
	 */
	@Override
	public List<TaskAnalysis> getCompletionFinish(String  id,String  start,String end,String year,String type) {
		
		if(year==null) {
		 year="2020";
		}
		
		try {
		
			// 0 全部任务 1已完成任务 2未完成任务
			
			//and taskFrom like '%部局%'  
			//where   taskbookNo  like :param
		//	String hql1 = "from TaskAnalysis where status ='审定完成'   and taskbookNo like :param";
			//String hql2 = "from TaskAnalysis where status !='审定完成'     and taskbookNo like :param";

			// 编绘任务完成情况
			// 全部计划
			//and  mapNo like '%44123%' 
		//	if (compType.equals("0")) {
			
			if("1".equals(type)&& id==null  ) {
			String	 hql0 = "from TaskAnalysis  where  taskbookNo  like :param and  taskFrom like '%部局%'   and status ='审定完成' and processDefName ='纸海图编绘' ";
					return this.getSession().createQuery(hql0).setString("param", "%" + year + "%").list();
			}
			else if("2".equals(type) && id==null ) {
				String	 hql0 = "from TaskAnalysis  where  taskbookNo  like :param and  taskFrom like '%部局%'   and status ='审定完成' and processDefName ='电子海图编绘' ";
				return this.getSession().createQuery(hql0).setString("param", "%" + year + "%").list();
			}
			else 	if(start != null && end != null){
				List<TaskAnalysis> list = null;
				try {
					String hql = "from TaskAnalysis  where  taskbookNo  like :param and taskFrom like '%部局%'  and status ='审定完成' and processDefName in ('纸海图编绘','电子海图编绘') and sjhjtime>= :beginDate  and sjhjtime<= :endDate   order by  processDefName DESC ";
					
					Query query = this.getSession().createQuery(hql);
					query.setString("beginDate",start);     
					query.setString("endDate",end);  
					list = query.setString("param", "%" + year + "%").list();
				} catch (Exception e) {
					LogHelper.ERROR.log(e.getMessage());
				}
				return list;
			}/*else if(id==null &&  type.equals("1") ) {
				String hql0 = "from TaskAnalysis  where  taskbookNo  like :param  and    taskFrom like '%部局%'   and status ='审定完成' and processDefName ='纸海图编绘' ";
				
				return this.getSession().createQuery(hql0).setString("param", "%" + year + "%").list();
			}*/else if(id!=null   ) {
				String hql0=null;
				if(type.equals("1")) {
				 hql0 = "from TaskAnalysis  where  taskbookNo  like :param and id=:id  and  taskFrom like '%部局%'   and status ='审定完成' and processDefName ='纸海图编绘' ";
				
				}else if(type.equals("2")) {
					  hql0 = "from TaskAnalysis  where  taskbookNo  like :param and id=:id  and  taskFrom like '%部局%'   and status ='审定完成' and processDefName ='电子海图编绘' ";
						
				}
				return this.getSession().createQuery(hql0).setString("param", "%" + year + "%").setString("id", id).list();
			}else {
	            String hql0 = "from TaskAnalysis  where  taskbookNo  like :param and taskFrom like '%部局%'   and status ='审定完成' and processDefName in ('纸海图编绘','电子海图编绘')  order by  processDefName DESC";
				
				return this.getSession().createQuery(hql0).	setString("param", "%" + year + "%").list();
			}
				//
					
			//};
			/*// 已完成
			if (compType.equals("1")) {
				return this.getSession().createQuery(hql1).setString("param", "%" + year + "%").list();
			};
			// 未完成
			if (compType.equals("2")) {
				return this.getSession().createQuery(hql2).setString("param", "%" + year + "%").list();
			};*/
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
		
	}
	
	
	
	/**
	 * 获取一条数据
	 */
	@Override
	public TaskAnalysis getCompletionFinishById(TaskAnalysis crs) {
		try {
			@SuppressWarnings("unchecked")
			// 执行查询
			List<TaskAnalysis> result = this.findByNamedQueryAndNamedParam("getTaskAnalysisById", "id", crs.getId());
			
			if(result.size() > 0){
				return result.get(0);
			}
			return null;
		} catch (Exception e) {
			return null;
		}
	}

}
