package com.ht.service.impl.system.document.type;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.ht.common.util.DataConverter;
import com.ht.common.util.GenerateSequenceUtil;
import com.ht.common.util.LogHelper;
import com.ht.persistence.dao.inter.system.doument.type.ModelTypeDao;
import com.ht.persistence.model.background.organization.employee.User;
import com.ht.persistence.model.system.document.template.Model;
import com.ht.persistence.model.system.document.type.ModelType;
import com.ht.service.inter.system.document.type.ModelTypeService;

public class ModelTypeServiceImpl implements ModelTypeService{
	
	/**
	 * 注入模板类别Dao
	 * */
	@Resource(name = "modeltypeDao")
	private ModelTypeDao modeltypeDao;
	
	/**
	 * 新增模板类型 
	 * @param modeltypeParam String类型的模板类型对象
	 * @throws Exception
	 */
	@Override
	public void addModelType(String modeltypeParam) throws Exception {
		// 增加ModelType
		try {
			ModelType modeltype =(ModelType)DataConverter.convertJson2Object(modeltypeParam, ModelType.class);
			if(modeltype.getId()!=null){
				modeltype.setCreator("");
				modeltype.setCreationDate(new Date());
				modeltype.setModifier("");
				modeltype.setModifyDate(new Date());
				modeltypeDao.modifyModelType(modeltype);
			}else{
				modeltype.setId(GenerateSequenceUtil.generateSequenceNo());
				modeltype.setCreator("");
				modeltype.setCreationDate(new Date());
				modeltype.setModifier("");
				modeltype.setModifyDate(new Date());
				modeltypeDao.addModelType(modeltype);
			}
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}

	/**
	 * 修改模板类型
	 * @param modeltypeParam String类型的模板类型对象
	 * @throws Exception
	 */
	@Override
	public void modifyModelType(String modeltypeparam) throws Exception {
		try {
			ModelType modeltype = (ModelType) DataConverter.convertJson2Object(modeltypeparam, ModelType.class);
			modeltype.setCreator("");
			modeltype.setModifier("");
			modeltype.setModifyDate(new Date());
			// 更新ModelType
			modeltypeDao.modifyModelType(modeltype);
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}

	/**
	 * 删除一条模板类型信息
	 * @param id 人员模板类型
	 * @throws Exception
	 */
	@Override
	public void delModelType(String modeltype) throws Exception {
		try {
			List<ModelType> list = (List<ModelType>) DataConverter.convertJson2List(modeltype,ModelType.class);
			for (int i = 0; i < list.size(); i++) {
				// 删除ModelType
				modeltypeDao.delModelType(list.get(i));
			}
			
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}

	/**
	 * 获取模板类型列表
	 * @throws Exception
	 */
	@Override
	public List<ModelType> getModelType() throws Exception {
		try {
			// 获取所有ModelType
			return modeltypeDao.getModelType();
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}
	/**
	 * 根据模板类型编号获取模板类型信息
	 * @param id 模板类型编号
	 * @throws Exception
	 */
	@Override
	public ModelType getModelType(String id) throws Exception {
		try {
			ModelType modeltype = new ModelType();
			modeltype.setId(id);
			// 根据id获取ModelType
			return modeltypeDao.getModelType(modeltype);
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}
}
