/**
 * 
 */
package com.ht.persistence.dao.inter.complication.form;

import java.util.List;

import com.ht.persistence.model.complication.form.FormContent;

/**
 * 表单内容类接口
 * @author zqy
 *
 */
public interface FormContentDao {
	/**
	 * 增加一个FormContent
	 * @param FormContent FormContent对象
	 */
	public void addFormContent(FormContent FormContent);

	/**
	 * 更新一个FormContent
	 * @param FormContent FormContent对象
	 */
	public void modifyFormContent(FormContent formContent);

	/**
	 * 删除FormContent
	 * @param FormContent FormContent对象
	 */
	public void delFormContent(FormContent formContent);
	
	/**
	 * 获取所有FormContent
	 * @return List<FormContent> FormContent对象集合
	 */
	public List<FormContent> getFormContent();

	/**
	 * 获取一条FormContent
	 * @param FormContent FormContent对象
	 * @return FormContent对象
	 */
	public FormContent getFormContent(FormContent formContent);
	
}
