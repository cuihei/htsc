package com.ht.persistence.dao.impl.system.document.template;

import java.util.List;
import com.ht.persistence.dao.impl.base.BaseDaoImpl;
import com.ht.persistence.dao.inter.system.doument.template.ModelDao;
import com.ht.persistence.model.system.document.template.Model;

/**
 * Model 数据映射操作类
 * @author 平子金
 *
 */
public class ModelDaoImpl extends BaseDaoImpl implements ModelDao{
	
	/**
	 * 增加一个Model
	 * @param Model Model 实体
	 */
	@Override
	public void addModel(Model model) {
		this.save(model);
	}

	/**
	 * 更新一个Model
	 * @param Model Model实体
	 */
	@Override
	public void modifyModel(Model model) {
		this.update(model);
	}
	/**
	 * 删除Model 相关
	 * @param id Model主键
	 */
	@Override
	public void delModel(Model model) {
		this.delete(model);
	}

	@Override
	public List<Model> getModel() {
		try {

			@SuppressWarnings("unchecked")
			List<Model> result = this.findByNamedQuery("getModel");
			return result;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	/**
	 * 获取一条Model
	 * @param id Model主键
	 * @return Model实体
	 */
	@Override
	public Model getModel(Model Model) {
		@SuppressWarnings("unchecked")
		List<Model> result = this.findByNamedQueryAndNamedParam("getModelById", "id", Model.getId());
		if(result.size()>0){
			return result.get(0);
		}
		return null;
	}
}
