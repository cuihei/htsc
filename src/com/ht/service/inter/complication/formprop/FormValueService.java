package com.ht.service.inter.complication.formprop;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.ht.persistence.model.complication.formprop.FormValue;

/**
 * FormValueService接口
 * @author 侯晨
 */
public interface FormValueService {
	/**
	  * 保存
	  * @param formValue
	  * @throws Exception
	  */
	public void addFormValue(String formValue) throws Exception;
	
	/**
	  * 修改
	  * @param formValue
	  * @throws Exception
	  */
	public void modifyFormValue(String formValue) throws Exception;
	
	/**
	  * 删除
	  * @param id
	  * @throws Exception
	  */
	public void delFormValue(String formValue) throws Exception;
	
	/**
	  * 查询所有
	  * @throws Exception
	  */
	public List<FormValue> getFormValue() throws Exception;
	
	/**
	  * 查询一条
	  * @param id
	  * @throws Exception
	  */
	public FormValue getFormValue(String formValue) throws Exception;
	
	/**
	  * 根据formid查询
	  * @param formid
	  * @throws Exception
	  */
	public List<FormValue> getFormValueByFromId(String formValue) throws Exception;
	
	/**
	 * 根据formid和propValue 查询
	 * @param fromId
	 * @param propValue
	 * @return
	 * @throws Exception
	 */
	public List<FormValue> getFormValueByFromIdAndPropValue(String fromId,String propValue) throws Exception;
	
	/**
	 * 根据流程实例id，流程任务id查询所有的Formvalue对象
	 * @param processInstId 流程实例ID
	 * @param taskInstId 流程任务ID
	 * @return Exception
	 */
	public List<FormValue> getFormValueByPt(String processInstId,String taskInstId,String formId) throws Exception;
	
	/**
	 * 根据key和rowFlag查询所有的Formvalue对象
	 * @return Exception
	 */
	public FormValue getFormValueByRowFlag(String key,String rowFlag) throws Exception;
	
	/**
	 * 上传文件
	 * @param upload
	 * @param uploadFileName
	 * @throws IOException
	 */
	public String uploadFiles(File[] files, String[] filesFileName) throws IOException;
	
	/**
	 * 上传文件
	 * @param upload
	 * @param uploadFileName
	 * @throws IOException
	 */
	public String uploadFile(File files, String filesFileName) throws IOException;
	
	/**
	 * 文件下载
	 * @return 
	 * @throws Exception 
	 */
	public void downloadFile(FormValueService formValueService,String url) throws Exception;
	
	/**
	 * 多文件下载
	 * @throws Exception 
	 */
	public void downloadFiles(FormValueService formValueService,String url) throws Exception;

	public List<FormValue> getFormValueByProcessInstId(String processInstId, String formId) throws Exception;

	public List<FormValue> getFormValueByPf(String processInstId, String formId);

	public String getTaskIdBypft(String processInstId, String taskInstId, String formId);

	public String uploadImg(File upload, String uploadFileName) throws IOException;

	public String fileIsExist(FormValueService formValueService, String url) throws Exception;
///   向FLORLVALUE添加 提交时的TASKID 2018.9.14
	void addFormValueByTaskId(String processInstId, String loginUser, String taskinstid,String taskDefId);

}
