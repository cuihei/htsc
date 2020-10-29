package com.ht.service.inter.system.document.type;

import java.util.List;
import com.ht.persistence.model.system.document.type.ModelType;

/**
 * 模板类型service
 * @author 平子金
 * */
public interface ModelTypeService {
	/**
	 *保存通知
	 * @param ModelTypes ModelType实体
	 */
  public void addModelType(String modeltype) throws Exception;
  
  	/**
	 *修改通知
	 * @param ModelTypes ModelType实体
	 */
  public void modifyModelType(String modeltype) throws Exception;
  
  	/**
	 *删除通知
	 * @param ModelTypes ModelType实体
	 */
  public void delModelType(String id) throws Exception;
  
  	/**
	 *查询所有通知
	 * @param ModelTypes ModelType实体
	 */
  public List<ModelType> getModelType() throws Exception;
  
  	/**
	 *查询一条通知
	 * @param ModelTypes ModelType实体
	 */
  public ModelType getModelType(String id) throws Exception;
}
