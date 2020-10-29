package com.ht.service.inter.background.dicdata.type;

import java.util.List;
import java.util.Map;

import com.ht.common.util.DataConverter;
import com.ht.common.util.LogHelper;
import com.ht.persistence.model.background.dicdata.type.Type;
/**
 * 类别数据接口
 */
public interface TypeService {
	
  /**
   * 保存类别数据
   * @param type
   * @throws Exception
   * */
  public Map<String,Object>  addType(String type) throws Exception;
  
  /**
   * 修改类别数据
   * @param type
   * @throws Exception
   * */
  public Map<String,Object>  modifyType(String type) throws Exception;
  
	/**
	 * 修改类别数据
	 * @param typeParam 类别数据
	 */
	public Map<String,Object> modifyTypeById(String typeParam) throws Exception ;
  
  /**
   * 删除一条类别数据
   * @param id
   * @throws Exception
   * */
  public void delType(String id) throws Exception;
  
  	/**
	 * 删除基础数据
	 * @param baseDataParam json对象
	 * @throws Exception
	 */
	public void delTypes(String ids) throws Exception ;
  
  /**
   * 查询所有类别数据
   * @throws Exception
   * */
  public List<Type> getType() throws Exception;
  
  /**
   * 查询一条类别数据
   * @param id
   * */
  public Type getType(String id) throws Exception;
}
