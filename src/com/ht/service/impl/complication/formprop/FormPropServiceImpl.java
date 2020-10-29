package com.ht.service.impl.complication.formprop;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.ht.common.util.DataConverter;
import com.ht.common.util.GenerateSequenceUtil;
import com.ht.common.util.LogHelper;
import com.ht.persistence.dao.inter.complication.formprop.FormPropDao;
import com.ht.persistence.model.complication.formprop.FormProp;
import com.ht.persistence.model.complication.formprop.FormPropFormView;
import com.ht.service.inter.complication.formprop.FormPropService;

/**
 * FormPropService的实现类
 * @author 侯晨
 */
public class FormPropServiceImpl implements FormPropService {
	/**
	 * 注入formPropDao
	 * */
	@Resource(name = "formPropDao") 
	private FormPropDao formPropDao;
	
	/**
	 * 新增
	 * @param formPropParam 
	 * @throws Exception
	 */
	@Override
	public void addFormProp(String formPropParam) throws Exception {
		try {
			//将用户String类型转成FormProp类型
			FormProp formProp = (FormProp) DataConverter.convertJson2Object(formPropParam, FormProp.class);
			if (formProp.getId()!=null) {
				//执行修改操作
				formPropDao.modifyFormProp(formProp);
			}else{
				String id = GenerateSequenceUtil.generateSequenceNo();
				formProp.setId(id);
				//执行保存操作
				formPropDao.addFormProp(formProp);
			}
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}
	/**
	 * 更新
	 * @param formPropParam 
	 * @throws Exception
	 */
	@Override
	public void modifyFormProp(String formPropParam) throws Exception {
		try {
			//将用户String类型转成FormProp类型
			FormProp formProp = (FormProp) DataConverter.convertJson2Object(formPropParam, FormProp.class);
			formPropDao.modifyFormProp(formProp);
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}

	/**
	 * 删除
	 * @param id 
	 * @throws Exception
	 */
	@Override
	public void delFormProp(String formProp) throws Exception {
		try {
			// 将日志String类型转成FormProp对象
			@SuppressWarnings("unchecked")
			List<FormProp> list = (List<FormProp>) DataConverter.convertJson2List(formProp,FormProp.class);
			for (int i = 0; i < list.size(); i++) {
				// 删除FormProp
				formPropDao.delFormProp(list.get(i));
			}
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}
	
	/**
	 * 获取所有FormProp
	 * @throws Exception
	 */
	@Override
	public List<FormProp> getFormProp() throws Exception {
		try {
			// 获取所有FormProp
			return formPropDao.getFormProp();
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}
	
	/**
	 * 根据id获取FormProp
	 * @param id 
	 * @throws Exception
	 */
	@Override
	public FormProp getFormProp(String id) throws Exception {
		try {
			//创建FormProp对象
			FormProp formProp = new FormProp();
			formProp.setId(id);
			return formPropDao.getFormProp(formProp);
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}
	
	/**
	 * 根据FormId查询多条表单属性信息
	 * @param formId FormProp对象的属性值
	 * @return List<FormProp> FormProp对象的集合
	 * @throws Exception 
	 */
	public List<FormProp> getFormPropByFormId(String formId) throws Exception {
		try {
			// 创建FormProp对象
			FormProp formProp = new FormProp();
			formProp.setFormId(formId);
			// 执行查询操作
			List<FormProp> formPropList = formPropDao.getFormPropByFormId(formProp);
			return formPropList;
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}
	
	/**
	 * 获取所有FormPropFormView
	 * @param formId formId
	 * @throws Exception
	 */
	@Override
	public List<FormPropFormView> getFormPropList(String formId) throws Exception {
		try {
			// 获取所有FormProp
			return formPropDao.getFormPropList(formId);
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}
}
