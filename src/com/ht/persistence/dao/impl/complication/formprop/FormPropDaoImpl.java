package com.ht.persistence.dao.impl.complication.formprop;

import java.util.List;

import com.ht.persistence.dao.impl.base.BaseDaoImpl;

import com.ht.common.util.LogHelper;
import com.ht.persistence.dao.inter.complication.formprop.FormPropDao;
import com.ht.persistence.model.complication.formprop.FormProp;
import com.ht.persistence.model.complication.formprop.FormPropFormView;

/**
 * FormProp 接口的实现类
 * @author 侯晨
 *
 */
public class FormPropDaoImpl extends BaseDaoImpl implements FormPropDao{

	/**
	 * 增加一个FormProp
	 * @param FormProp FormProp实体
	 */
	@Override
	public void addFormProp(FormProp formProp) {
		this.save(formProp);
	}

	/**
	 * 更新一个FormProp
	 * @param FormProp FormProp实体
	 */
	@Override
	public void modifyFormProp(FormProp formProp) {
		this.update(formProp);
	}

	/**
	 * 删除FormProp 相关
	 * @param FormProp FormProp对象
	 */
	@Override
	public void delFormProp(FormProp formProp) {
		this.delete(formProp);
	}

	/**
	 * 获取所有FormProp
	 * @return FormProp列表
	 */
	@Override
	public List<FormProp> getFormProp() {
		try {
			@SuppressWarnings("unchecked")
			List<FormProp> result = this.findByNamedQuery("getFormProp");
			return result;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	/**
	 * 获取一条FormProp
	 * @param FormProp FormProp对象
	 * @return FormProp实体
	 */
	@Override
	public FormProp getFormProp(FormProp formProp) {
		@SuppressWarnings("unchecked")
		List<FormProp> result = this.findByNamedQueryAndNamedParam("getFormPropById", "id", formProp.getId());
		if(result.size()>0){
			return result.get(0);
		}
		return null;
	}

	/**
	 * 根据FormId获取多个表单属性列表
	 * @param FormProp FormProp对象
	 * @return List<FormProp> FormProp对象集合
	 */
	public List<FormProp> getFormPropByFormId(FormProp formProp) {
		try{
			@SuppressWarnings("unchecked")
			List<FormProp> formPropList = this.findByNamedQueryAndNamedParam("getFormPropByFormId","formId",formProp.getFormId());
			return formPropList;
		}catch(Exception e){
			// 执行错误日志输出
			LogHelper.ERROR.log(e.getMessage(),e);
		}
		return null;
	}
	
	/**
	 * 获取所有FormPropFormView对象集合
	 * @return List<FormProp>列表
	 */
	@Override
	public List<FormPropFormView> getFormPropList(String formId) {
		@SuppressWarnings("unchecked")
		List<FormPropFormView> result = this.findByNamedQueryAndNamedParam("getFormPropFormView","formId",formId);
		return result;
	}
	

	
	
}
