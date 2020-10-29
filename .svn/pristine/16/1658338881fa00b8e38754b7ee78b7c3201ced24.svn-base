package com.ht.persistence.dao.inter.system.assign;

import java.util.List;

import com.ht.persistence.model.system.workflow.assign.AssignUserRecord;

/**
 * 用户指派记录Dao
 * @author huodesheng
 *
 */
public interface AssignUserRecordDao {
	
	/**
	 * 新增
	 * @param assignUserRecord
	 */
	public void addAssignRecord(AssignUserRecord assignUserRecord)throws Exception;
	/**
	 * 修改
	 * @param assignUserRecord
	 */
	public void updateAssignRecord(AssignUserRecord assignUserRecord)throws Exception;
	/**
	 * 删除
	 * @param assignUserRecord
	 */
	public void deleteAssignRecord(AssignUserRecord assignUserRecord) throws Exception;
	/**
	 * 查询所有
	 * @return
	 */
	public List<AssignUserRecord> getAll() throws Exception;
	/**
	 * 按流程id和历史负责人查询。
	 * @param processInstId
	 * @param hisUser
	 * @return
	 */
	public List<AssignUserRecord> getRecordByProcessInstIdAndHisUser(String processInstId,String hisUser)throws Exception;
	/**
	 * 按流程id和历史负责人查询。
	 * @param processInstId
	 * @param hisUser
	 * @return
	 */
	public List<AssignUserRecord> getRecordByProcessInstIdAndAssignUser(String processInstId,String assignUser)throws Exception;
	/**
	 * 按流程id和类型查询
	 * @param processInstId
	 * @param type
	 * @return
	 */
	public List<AssignUserRecord> getRecordByProcessInstIdAndType(String processInstId,String type)throws Exception;
	
}
