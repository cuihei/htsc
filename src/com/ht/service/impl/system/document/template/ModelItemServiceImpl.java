package com.ht.service.impl.system.document.template;

import java.util.List;

import javax.annotation.Resource;

import com.ht.common.util.DataConverter;
import com.ht.common.util.LogHelper;
import com.ht.persistence.dao.inter.system.doument.template.ModelItemDao;
import com.ht.persistence.model.system.document.template.Model;
import com.ht.persistence.model.system.document.template.ModelItem;
import com.ht.service.inter.system.document.template.ModelItemService;

public class ModelItemServiceImpl implements ModelItemService{
	
	/**
	 * 注入模板项Dao
	 * */
	@Resource(name = "modelitemDao")
	private ModelItemDao modelitemDao;
	
	@Override
	public void addModelItem(String modelitemParam) throws Exception {
		// 增加ModelItem
		try {
			ModelItem modelitem =(ModelItem)DataConverter.convertJson2Object(modelitemParam, ModelItem.class);
			modelitemDao.addModelItem(modelitem);
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}

	@Override
	public void modifyModelItem(String modelitemParam) throws Exception {
		try {
			ModelItem modelitem = (ModelItem) DataConverter.convertJson2Object(modelitemParam, ModelItem.class);
			// 更新ModelItem
			modelitemDao.modifyModelItem(modelitem);
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}

	@Override
	public void delModelItem(String id) throws Exception {
		try {
			ModelItem modelitem = new ModelItem();
			modelitem.setId(id);
			// 删除ModelItem
			modelitemDao.delModelItem(modelitem);
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}

	@Override
	public List<ModelItem> getModelItem() throws Exception {
		try {
			// 获取所有ModelItem
			return modelitemDao.getModelItem();
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}

	@Override
	public ModelItem getModelItem(String id) throws Exception {
		try {
			ModelItem modelitem = new ModelItem();
			modelitem.setId(id);
			// 根据id获取ModelItem
			return modelitemDao.getModelItem(modelitem);
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}
}
