package com.ht.service.impl.system.workflow.publish;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.ht.persistence.dao.inter.system.workflow.publish.VProcessDetailDao;
import com.ht.persistence.model.system.workflow.publish.VProcessDetail;
import com.ht.service.inter.system.workflow.publish.VProcessDetailService;
/**
 * 工作流转日志视图ServiceImpl
 * @author huodesheng
 * @date 2016/10/28
 */
public class VProcessDetailServiceImpl implements VProcessDetailService {
	
	/**
	 * 注入目录Dao
	 */
	@Resource(name="vProcessDetailDao")
	private VProcessDetailDao VProcessDetailDao;
	
	@Override
	public List<VProcessDetail> findListByUserNo(String userNo)
			throws Exception {
		return VProcessDetailDao.findListByUserNo(userNo);
	}

	
	@SuppressWarnings("null")
	@Override
	public Map<String,List<Object>> findListByTable(List<VProcessDetail> detailList) throws Exception {
		 Map<String,List<Object>> tableMap=new HashMap<String, List<Object>>();
		if(null!=detailList&&detailList.size()!=0){
			for (VProcessDetail detail : detailList) {
					List<Object> list = VProcessDetailDao.findListByTable(detail.getTableName(), detail.getTableKeyName(), detail.getDetailRecordId());
					if(list!=null&&list.size()!=0){
						tableMap.put(detail.getTableName(), list);
					}
			}	
		}
			return tableMap;
	}

	@Override
	public List<VProcessDetail> findTableNameByUserNo(String userNo)
			throws Exception {
		return VProcessDetailDao.findTableNameByUserNo(userNo);
	}


	@Override
	public VProcessDetail getProcessDetailByProcessInstIdAndTaskId(String processInstId,String taskId)
			throws Exception {
		return VProcessDetailDao.getProcessDetailByProcessInstIdAndTaskId(processInstId,taskId);
	}
	
	@Override
	public VProcessDetail getProcessDetailByProcessInstId(String processInstId)
			throws Exception {
		return VProcessDetailDao.getProcessDetailByProcessInstId(processInstId);
	}

	/**
	 * 通过recordId获取数据
	 */
	@Override
	public VProcessDetail getByDetailRecordId(String recordId) {
		return VProcessDetailDao.getByDetailRecordId(recordId);
	}
}
