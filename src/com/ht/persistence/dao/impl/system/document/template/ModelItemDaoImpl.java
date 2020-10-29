package com.ht.persistence.dao.impl.system.document.template;

import java.util.List;
import com.ht.persistence.dao.impl.base.BaseDaoImpl;
import com.ht.persistence.dao.inter.system.doument.template.ModelItemDao;
import com.ht.persistence.model.system.document.template.ModelItem;

/**
 * ModelItem 数据映射操作类
 * @author 平子金
 *
 */
public class ModelItemDaoImpl extends BaseDaoImpl implements ModelItemDao{

	/**
	 * 增加一个ModelItem
	 * @param ModelItem ModelItem 实体
	 */
	@Override
	public void addModelItem(ModelItem modelitem) {
		this.save(modelitem);
	}

	/**
	 * 更新一个ModelItemItem
	 * @param ModelItem ModelItem实体
	 */
	@Override
	public void modifyModelItem(ModelItem modelitem) {
		this.update(modelitem);
	}
	/**
	 * 删除ModelItem 相关
	 * @param id ModelItem主键
	 */
	@Override
	public void delModelItem(ModelItem modelitem) {
		this.delete(modelitem);
	}

	@Override
	public List<ModelItem> getModelItem() {
		try {

			@SuppressWarnings("unchecked")
			List<ModelItem> result = this.findByNamedQuery("getModelItem");
			return result;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	/**
	 * 获取一条ModelItem
	 * @param id ModelItem主键
	 * @return ModelItem实体
	 */
	@Override
	public ModelItem getModelItem(ModelItem modelitem) {
		@SuppressWarnings("unchecked")
		List<ModelItem> result = this.findByNamedQueryAndNamedParam("getModelItemById", "id", modelitem.getId());
		if(result.size()>0){
			return result.get(0);
		}
		return null;
	}
}
