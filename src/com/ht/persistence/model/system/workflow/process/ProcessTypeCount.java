package com.ht.persistence.model.system.workflow.process;

import com.ht.persistence.model.base.BaseModel;

/**
 * 
 * @author 王有为
 * @date 2016/10/17
 */
public class ProcessTypeCount extends BaseModel{
	
	/**
	 * 流程定义ID
	 */
	private String processDefId = null;
	
	/**
	 * 流程定义名称
	 */
	private String processDefName = null;
	
	/**
	 * 流程定义名称
	 */
	private String processDefKey = null;
	
	/**
	 * 流程定义名称
	 */
	private Integer cnt = null;
	

	public String getProcessDefId() {
		return processDefId;
	}

	public void setProcessDefId(String processDefId) {
		this.processDefId = processDefId;
	}

	public String getProcessDefName() {
		return processDefName;
	}

	public void setProcessDefName(String processDefName) {
		this.processDefName = processDefName;
	}

	public String getProcessDefKey() {
		return processDefKey;
	}

	public void setProcessDefKey(String processDefKey) {
		this.processDefKey = processDefKey;
	}

	public Integer getCnt() {
		return cnt;
	}

	public void setCnt(Integer cnt) {
		this.cnt = cnt;
	}
	
}
