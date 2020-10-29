package com.ht.persistence.dao.impl.complication.seamap.source;

import java.util.List;

import com.ht.persistence.dao.impl.base.BaseDaoImpl;

import com.ht.persistence.dao.inter.complication.seamap.source.SourceDao;
import com.ht.persistence.model.complication.seamap.source.SourceStatus;
import com.ht.persistence.model.complication.seamap.source.TransferStatus;
import com.ht.persistence.model.drawtask.tasksplit.TaskSplit;


/**
 *  目录区域数据映射操作类
 * @author yaoping 
 *
 */
public class SourceDaoImpl extends BaseDaoImpl implements SourceDao{

	/**
	 * 查询
	 * 获取所有源数据数据
	 * @return 源数据list
	 */
	@Override
	public List<TaskSplit> findSourceList() {
		try {
			@SuppressWarnings("unchecked")
			List<TaskSplit> result = this.findByNamedQuery("getAreaList");
			return result;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	/**
	 * 查询
	 * 源数据一条流转状态
	 * @return 源数据流转状态list
	 */
	@Override
	public List<SourceStatus> findSourceStatus(SourceStatus sourceStatus) {
		try {
			//List<SourceStatus> result = this.findByNamedQueryAndNamedParam("getCatalogAreaById","id",sourceStatus.getId());
			//拼接查询参数
			String[] params={"process_Inst_Id","task_Id"};
            //拼接查询的参数值			
			String[] values={sourceStatus.getProcess_Inst_Id(),sourceStatus.getTask_Id()};
			@SuppressWarnings("unchecked")
			List<SourceStatus> result = this.findByNamedQueryAndNamedParam("getProcessFlowByParam", params, values);
			return result;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	/**
	 * 查询
	 * 源数据采用登记列表
	 * @return 源数据采用登记列表list
	 */
	@Override
	public List<TransferStatus> findSourceAdopt(TransferStatus transferStatus) {
		try {
			@SuppressWarnings("unchecked")
			List<TransferStatus> result = this.findByNamedQueryAndNamedParam("getCatalogAreaById","id",transferStatus.getId());
			return result;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	/**
	 * 查询
	 * 源数据幅索引图数据
	 * @return  源数据幅索引图数据
	 */
	@Override
	public TransferStatus findSourceIndexMap(TransferStatus transferStatus) {
		try {
			@SuppressWarnings("unchecked")
			List<TransferStatus> result = this.findByNamedQueryAndNamedParam("getCatalogAreaById","id",transferStatus.getId());
			TransferStatus info = result.get(0);
			return info;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
}
