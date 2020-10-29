package com.ht.persistence.dao.inter.background.dicdata.type;

import java.util.List;

import com.ht.persistence.model.background.dicdata.type.Type;

/**
 * TypeDao 类别数据Dao
 * @author 刘凯
 */
public interface TypeDao {
	
	/**
	 * 增加一个Type
	 * @param type 类别实体
	 */
	public void addType(Type type);

	/**
	 * 更新一个Type
	 * @param type 类别实体
	 */
	public void modifyType(Type type);

	/**
	 * 删除Type
	 * @param type 类别实体
	 */
	public void delType(Type type);
	
	/**
	 * 获取所有Type
	 * @return Type列表
	 */
	public List<Type> getType();

	/**
	 * 获取一条Type
	 * @param type 类别实体
	 * @return Type实体
	 */
	public Type getType(Type type);
	
	public void evict (Type type) ;
	/**
	 * 根据type，name获取typelist
	 * @author huodesheng
	 * @date 2017-9-8
	 * @company DaoEasy
	 * @return
	 */
	public List<Type> getTypeByName(Type type);
}
