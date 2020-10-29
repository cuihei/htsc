package com.ht.persistence.dao.impl.system.document.type;
import java.util.List;

import com.ht.persistence.dao.impl.base.BaseDaoImpl;

import com.ht.persistence.dao.inter.system.doument.type.ModelTypeDao;
import com.ht.persistence.model.system.document.type.ModelType;


/**
 * ModelType 数据映射操作类
 * @author 平子金
 *
 */
public class ModelTypeDaoImpl extends BaseDaoImpl implements ModelTypeDao{
	/**
	 * 增加一个ModelType
	 * @param ModelTypes ModelType 实体
	 */
	@Override
	public void addModelType(ModelType modeltype) {
		this.save(modeltype);
	}

	/**
	 * 更新一个ModelType
	 * @param ModelTypes ModelType实体
	 */
	@Override
	public void modifyModelType(ModelType modeltype) {
		this.update(modeltype);
	}
	/**
	 * 删除ModelType 相关
	 * @param id ModelType主键
	 */
	@Override
	public void delModelType(ModelType modeltype) {
		this.delete(modeltype);
	}

	@Override
	public List<ModelType> getModelType() {
		try {

			@SuppressWarnings("unchecked")
			List<ModelType> result = this.findByNamedQuery("getModelType");
			return result;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	/**
	 * 获取一条ModelType
	 * @param id ModelType主键
	 * @return ModelType实体
	 */
	@Override
	public ModelType getModelType(ModelType modeltype) {
		@SuppressWarnings("unchecked")
		List<ModelType> result = this.findByNamedQueryAndNamedParam("getModelTypeById", "id", modeltype.getId());
		if(result.size()>0){
			return result.get(0);
		}
		return null;
	}
}
