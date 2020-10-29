package com.ht.service.impl.complication.form;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.ht.common.util.DataConverter;
import com.ht.common.util.GenerateSequenceUtil;
import com.ht.common.util.LogHelper;
import com.ht.persistence.dao.inter.complication.form.FormContentDao;
import com.ht.persistence.model.complication.form.FormContent;
import com.ht.service.inter.complication.form.FormContentService;

/**
 * 表单列表Service类的实现接口类
 * @author zqy
 */
public class FormContentServiceImpl implements FormContentService{
	@Resource
	private FormContentDao formContentDao;

	/**
	 * 增加一个表单内容
	 * @param formContentParam FormContent对象
	 * @throws Exception
	 */
	public void addFormContent(String formContentParam,String formId) throws Exception {
		try{
			
			FormContent fc = new FormContent();
			fc.setFormId(formId);
			fc = formContentDao.getFormContent(fc);
			if(fc != null){
				formContentDao.delFormContent(fc);
			}
			FormContent formContent = new FormContent();
			formContent.setId(GenerateSequenceUtil.generateSequenceNo());
			formContent.setContent(formContentParam);
			formContent.setFormId(formId);
			formContentDao.addFormContent(formContent);
			
		}catch(Exception e){
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(), e);
			// 抛出异常
			throw e;
		}
	}

	/**
	 * 修改一个表单内容
	 * @param formContentParam FormContent对象
	 * @throws Exception
	 */
	public void modifyFormContent(String fromContentParam) throws Exception {
		try {
			FormContent formContent = (FormContent) DataConverter.convertJson2Object(fromContentParam, FormContent.class);
			formContent.setContent(fromContentParam);
			formContentDao.modifyFormContent(formContent);
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(), e);
			// 抛出异常
			throw e;
		}
		
	}

	/**
	 * 删除一个表单内容
	 * @param formContentParam FormContent对象
	 * @throws Exception
	 */
	public void delFormContent(String formContentParam) throws Exception {
		try {
			// 创建Form对象
			FormContent fc = new FormContent();
			fc.setId(formContentParam);
			fc = formContentDao.getFormContent(fc);
			formContentDao.delFormContent(fc);
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
		
	}

	/**
	 * 查找多个表单内容
	 * @param formContentParam FormContent对象
	 * @return List<FormContentContent> Form对象集合
	 * @throws Exception
	 */
	public List<FormContent> getFormContent() throws Exception {
		try {
			// 获取所有Form对象
			return formContentDao.getFormContent();
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(), e);
			// 抛出异常
			throw e;
		}
	}

	/**
	 * 查询一个表单内容
	 * @param formContentParam FormContent对象
	 * @return FormContent FormContent对象
	 * @throws Exception
	 */
	public FormContent getFormContent(String formContentParam) throws Exception {
		try {
			FormContent formContent = new FormContent();
			formContent.setFormId(formContentParam);
			// 获取所有Form对象
			return formContentDao.getFormContent(formContent);
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(), e);
			// 抛出异常
			throw e;
		}
	}

}
