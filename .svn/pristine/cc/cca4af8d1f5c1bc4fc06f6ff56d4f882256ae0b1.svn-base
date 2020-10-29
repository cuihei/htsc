package com.ht.persistence.dao.inter.complication.formprop;

import java.util.List;

import com.ht.persistence.model.complication.formprop.FormProp;
import com.ht.persistence.model.complication.formprop.FormPropFormView;

/**
 * FormPropDao接口
 * @author houchen
 */
public interface FormPropDao {
	/**
	 * 增加一个FormProp
	 * @param FormProp FormProp实体
	 */
	public void addFormProp(FormProp formProp);

	/**
	 * 更新一个FormProp
	 * @param FormProp FormProp实体
	 */
	public void modifyFormProp(FormProp formProp);

	/**
	 * 删除FormProp 相关
	 * @param FormProp FormProp对象
	 */
	public void delFormProp(FormProp formProp);
	
	/**
	 * 获取所有FormProp对象集合
	 * @return List<FormProp>列表
	 */
	public List<FormProp> getFormProp();

	/**
	 * 获取一条FormProp
	 * @param FormProp FormProp对象
	 * @return FormProp实体
	 */
	public FormProp getFormProp(FormProp formProp);
	
	/**
	 * 根据FormId获取多个FormProm对象
	 */
	public List<FormProp> getFormPropByFormId(FormProp formProp);
	
	/**
	 * 获取所有FormPropFormView对象集合
	 * @param formId formId
	 * @return List<FormProp>列表
	 */
	public List<FormPropFormView> getFormPropList(String formId);
}
