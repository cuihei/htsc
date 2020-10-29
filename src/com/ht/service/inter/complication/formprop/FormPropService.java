package com.ht.service.inter.complication.formprop;

import java.util.List;

import com.ht.persistence.model.complication.formprop.FormProp;
import com.ht.persistence.model.complication.formprop.FormPropFormView;

/**
 * FormPropService接口
 * @author 侯晨
 */
public interface FormPropService {
	/**
	  * 保存
	  * @param formProp
	  * @throws Exception
	  */
	public void addFormProp(String formProp) throws Exception;
	
	/**
	  * 修改
	  * @param formProp
	  * @throws Exception
	  */
	public void modifyFormProp(String formProp) throws Exception;
	
	/**
	  * 删除
	  * @param id
	  * @throws Exception
	  */
	public void delFormProp(String formProp) throws Exception;
	
	/**
	  * 查询所有
	  * @throws Exception
	  */
	public List<FormProp> getFormProp() throws Exception;
	
	/**
	  * 查询一条
	  * @param id
	  * @throws Exception
	  */
	public FormProp getFormProp(String formProp) throws Exception;
	
	/**
	  * 查询多条
	  * @param id
	  * @throws Exception
	  */
	public List<FormProp> getFormPropByFormId(String formId) throws Exception;
	
	/**
	  * 查询所有FormPropFormView
	  * @param formId formId
	  * @throws Exception
	  */
	public List<FormPropFormView> getFormPropList(String formId) throws Exception;
	
}
