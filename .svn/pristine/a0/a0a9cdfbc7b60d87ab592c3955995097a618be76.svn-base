package com.ht.service.inter.system.workflow.publish;

import java.util.List;
import java.util.Map;

import com.ht.persistence.model.system.workflow.publish.VProcessDetail;

public interface VProcessDetailService
{
	/**
	 * 根据用户userNo获取数据
	 * @param userNo
	 * @return 业务表的表名称， 业务表主键字段，业务表主键数据
	 * @throws Exception
	 */
	public List<VProcessDetail> findListByUserNo(String userNo) throws Exception;

	/**
	 * @param TableName 表名
	 * @param KeyName 主键名
	 * @param KeyValue 主键值
	 * @return
	 * @throws Exception
	 */
	public Map<String, List<Object>> findListByTable(List<VProcessDetail> detailList) throws Exception;

	/**
	 * 根据用户userNo获取表名
	 * @param userNo
	 * @return 业务表的表名称， 业务表主键字段，业务表主键数据
	 * @throws Exception
	 */
	public List<VProcessDetail> findTableNameByUserNo(String userNo) throws Exception;

	public VProcessDetail getProcessDetailByProcessInstIdAndTaskId(String processInstId, String taskId) throws Exception;

	public VProcessDetail getProcessDetailByProcessInstId(String processInstId) throws Exception;

	/**
	 * 通过recordId获取数据
	 * @param recordId
	 * @return
	 */
	public VProcessDetail getByDetailRecordId(String recordId);
}
