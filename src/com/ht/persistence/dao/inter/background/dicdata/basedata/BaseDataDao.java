package com.ht.persistence.dao.inter.background.dicdata.basedata;

import java.util.List;

import com.ht.persistence.model.background.dicdata.basedata.BaseData;

/**
 * BaseDataDao
 * @author 刘凯
 */
public interface BaseDataDao {
	/**
	 * 增加一个BaseData
	 * @param baseData baseData实体
	 */
	public void addBaseData(BaseData baseData);

	/**
	 * 更新一个BaseData
	 * @param baseData baseData实体
	 */
	public void modifyBaseData(BaseData baseData);

	/**
	 * 删除BaseData
	 * @param baseData BaseData实体
	 */
	public void delBaseData(BaseData baseData);
	
	/**
	 * 获取所有BaseData
	 * @return BaseData列表
	 */
	public List<BaseData> getBaseData();

	/**
	 * 获取一条BaseData
	 * @param baseData BaseData实体
	 * @return BaseData实体
	 */
	public BaseData getBaseData(BaseData baseData);

	/**
	 * 根据基础数据value获取ID
	 * @param
	 * @return
	 */
	public BaseData getDataByValue(BaseData baseData);

	
	/**
	 * 移除某个对象
	 * @param baseData
	 */
	public void evict(BaseData baseData) ;
	
	/**
	 * 根据类型获取基础数据列表
	 * @param
	 * @return
	 */
	public List<BaseData> getBaseDataByType(BaseData data);
	
	/**
	 * 根据类型ID获取基础数据列表
	 * @param
	 * @return
	 */
	public List<BaseData> getBaseDataByTypeId(BaseData data);
	
	/**
	 * 根据code获取基础数据
	 * @param
	 * @return
	 */
	public List<BaseData> getBaseDataById(BaseData data);

}
