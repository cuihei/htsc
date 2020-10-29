package com.ht.persistence.dao.impl.complication.form;

import java.util.List;

import com.ht.persistence.dao.impl.base.BaseDaoImpl;

import com.ht.persistence.dao.inter.complication.form.FormContentDao;
import com.ht.persistence.model.complication.form.FormContent;

/**
 * FormContent 数据映射操作类
 * @author zhongquanyou 
 *
 */
public class FormContentDaoImpl extends BaseDaoImpl implements FormContentDao {

	/** 
	 *  增加一个表单内容
	 *  @param FormContent FormContent对象
	 */
	public void addFormContent(FormContent formContent) {
		this.save(formContent);
	}

	/** 
	 *  修改一个表单内容
	 *  @param FormContent FormContent对象
	 */
	public void modifyFormContent(FormContent formContent) {
		this.update(formContent);
	}

	/** 
	 *  删除一个表单内容
	 *  @param FormContent FormContent对象
	 */
	public void delFormContent(FormContent formContent) {
		this.delete(formContent);
	}

	/** 
	 *  查询多个表单内容
	 *  @param FormContent FormContent对象
	 */
	public List<FormContent> getFormContent() {
		@SuppressWarnings("unchecked")
		List<FormContent> list = this.findByNamedQuery("getFormContent");
		return list;
	}

	/** 
	 *  查询一个表单内容
	 *  @param FormContent FormContent对象
	 */
	public FormContent getFormContent(FormContent formContent) {
		@SuppressWarnings("unchecked")
		List<FormContent> result = this.findByNamedQueryAndNamedParam("getFormContentByFormId", "formId", formContent.getFormId());
		if(result.size()>0){
			return result.get(0);
		}
		return null;
	}

}
