package com.ht.service.inter.system.document.template;

import java.util.List;
import com.ht.persistence.model.system.document.template.ModelItem;

/**
 * 模板项service
 * @author 平子金
 * */
public interface ModelItemService {

	/**
	 *保存通知
	 * @param ModelItem ModelItem实体
	 */
  public void addModelItem(String modelitem) throws Exception;
  
  	/**
	 *修改通知
	 * @param ModelItem ModelItem实体
	 */
  public void modifyModelItem(String modelitem) throws Exception;
  
  	/**
	 *删除通知
	 * @param ModelItem ModelItem实体
	 */
  public void delModelItem(String id) throws Exception;
  
  	/**
	 *查询所有通知
	 * @param ModelItem ModelItem实体
	 */
  public List<ModelItem> getModelItem() throws Exception;
  
  	/**
	 *查询一条通知
	 * @param ModelItem ModelItem实体
	 */
  public ModelItem getModelItem(String id) throws Exception;
}
