package com.ht.persistence.dao.impl.background.dicdata.type;

import java.util.List;

import com.ht.persistence.dao.impl.base.BaseDaoImpl;

import com.ht.persistence.dao.inter.background.dicdata.type.TypeDao;
import com.ht.persistence.model.background.dicdata.type.Type;

/**
 * Type 数据映射操作类
 * @author liukai 
 *
 */
public class TypeDaoImpl extends BaseDaoImpl implements TypeDao{

	/**
	 * 增加一个Type
	 * @param type type实体
	 */
	@Override
	public void addType(Type type) {
		//执行保存操作
		this.save(type);
	}

	/**
	 * 更新一个Type
	 * @param type type实体
	 */
	@Override
	public void modifyType(Type type) {
		//执行修改操作
		this.update(type);
	}

	/**
	 * 删除Type
	 * @param type type实体
	 */
	@Override
	public void delType(Type type) {
		//执行删除操作
		this.delete(type);
	}

	/**
	 * 获取所有Type
	 * @return Type列表
	 */
	@Override
	public List<Type> getType() {
		try {
			@SuppressWarnings("unchecked")
			//执行查询操作
			List<Type> result = this.findByNamedQuery("getType");
			return result;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	/**
	 * 获取一条Type
	 * @param type 类别实体
	 * @return Type实体
	 */
	@Override
	public Type getType(Type type) {
		@SuppressWarnings("unchecked")
		//执行查询操作
		List<Type> result = this.findByNamedQueryAndNamedParam("getTypeById", "id", type.getId());
		if(result.size()>0){
			return result.get(0);
		}
		return null;
	}
	
	/**
	 * 移除内存中的实例
	 * @param type
	 */
	@Override
	public void evict (Type type){
		super.evict(type);
	}

	/* 根据type，name获取typelist
	 * @author huodesheng
	 * @date 2017-9-8
	 * @company DaoEasy
	 * (non-Javadoc)
	 * @see com.ht.persistence.dao.inter.background.dicdata.type.TypeDao#getTypeByName()
	 */
	@Override
	public List<Type> getTypeByName(Type type) {
		@SuppressWarnings("unchecked")
		//执行查询操作
		List<Type> result = this.findByNamedQueryAndNamedParam("getTypeByName", "name", type.getName());
		if(null!=result&&result.size()>0){
			return result;
		}
		return null;
	}
   
}
