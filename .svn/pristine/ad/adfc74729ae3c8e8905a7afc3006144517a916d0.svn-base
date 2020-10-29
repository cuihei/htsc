package com.ht.service.impl.system.document.template;
import java.util.List;

import javax.annotation.Resource;

import com.ht.common.util.DataConverter;
import com.ht.common.util.LogHelper;
import com.ht.persistence.dao.inter.system.doument.template.ModelDao;
import com.ht.persistence.model.system.document.template.Model;
import com.ht.service.inter.system.document.template.ModelService;


public class ModelServiceImpl implements ModelService{
	
	/**
	 * 注入模板Dao
	 * */
	@Resource(name = "modelDao")
	private ModelDao modelDao;
	
	@Override
	public void addModel(String modelParam) throws Exception {
		// 增加model
				try {
					Model model =(Model)DataConverter.convertJson2Object(modelParam, Model.class);
					modelDao.addModel(model);
				} catch (Exception e) {
					// 写错误日志
					LogHelper.ERROR.log(e.getMessage(),e);
					// 抛出异常
					throw e;
				}
				
			}
	
	
	@Override
	public void modifyModel(String modelParam) throws Exception {
		try {
			Model model = (Model) DataConverter.convertJson2Object(modelParam, Model.class);
			// 更新Model
			modelDao.modifyModel(model);
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}

	@Override
	public void delModel(String id) throws Exception {
		try {
			Model model = new Model();
			model.setId(id);
			// 删除Model
			modelDao.delModel(model);
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}

	@Override
	public List<Model> getModel() throws Exception {
		try {
			// 获取所有Model
			return modelDao.getModel();
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}

	@Override
	public Model getModel(String id) throws Exception {
		try {
			Model model = new Model();
			model.setId(id);
			// 根据id获取Model
			return modelDao.getModel(model);
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}
}
