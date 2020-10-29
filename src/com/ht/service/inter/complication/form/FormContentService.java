package com.ht.service.inter.complication.form;

import java.util.List;

import com.ht.persistence.model.complication.form.FormContent;

/**
 * 表单下的内容类接口
 * @author zqy
 *
 */
public interface FormContentService {
	/**
	 * 增加一个表单内容
	 * @param formContentParam FormContent对象
	 * @param formId FormContent对象的属性
	 * @throws Exception
	 */
	public void addFormContent(String formContentParam,String formId) throws Exception;
	
	/**
	 * 删除一个表单内容
	 * @param formContentParam FormContent对象
	 * @throws Exception
	 */
	public void modifyFormContent(String fromContentParam) throws Exception;
	
	/**
	 * 修改一个表单内容
	 * @param formContentParam FormContent对象
	 * @throws Exception
	 */
	public void delFormContent(String formContentParam) throws Exception;
	
	/**
	 * 查询所有的表单内容
	 * @return List<FormContent> FormContent对象集合
	 * @throws Exception
	 */
	public List<FormContent> getFormContent() throws Exception;
	
	/**
	 * 查询一个表单内容
	 * @param formContentParam FormContent对象的ID属性
	 * @return FormContent FormContent对象
	 * @throws Exception
	 */
	public FormContent getFormContent(String formContentParam) throws Exception;
}
