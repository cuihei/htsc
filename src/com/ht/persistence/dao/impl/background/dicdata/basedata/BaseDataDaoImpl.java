package com.ht.persistence.dao.impl.background.dicdata.basedata;

import java.util.List;

import com.ht.persistence.dao.impl.base.BaseDaoImpl;
import org.springframework.util.CollectionUtils;

import com.ht.common.util.LogHelper;
import com.ht.persistence.dao.inter.background.dicdata.basedata.BaseDataDao;
import com.ht.persistence.model.background.dicdata.basedata.BaseData;

/**
 * BaseData 数据映射操作类
 * @author liukai 
 *
 */
public class BaseDataDaoImpl extends BaseDaoImpl implements BaseDataDao{

	/**
	 * 增加一个BaseData
	 * @param baseData baseData实体
	 */
	@Override
	public void addBaseData(BaseData baseData) {
		//执行保存方法
		this.save(baseData);
	}

	/**
	 * 更新一个BaseData
	 * @param baseData baseData实体
	 */
	@Override
	public void modifyBaseData(BaseData baseData) {
		//执行更新方法
		this.update(baseData);
	}

	/**
	 * 删除BaseData
	 * @param id BaseData主键
	 */
	@Override
	public void delBaseData(BaseData baseData) {
		//执行删除方法
		this.delete(baseData);
	}
	
	/**
	 * 获取所有BaseData
	 * @return BaseData列表
	 */
	@Override
	public List<BaseData> getBaseData() {
		//执行查询方法
		@SuppressWarnings("unchecked")
		List<BaseData> result = this.findByNamedQuery("getBaseData");
		return result;
	}
	
	/**
	 * 获取一条BaseData
	 * @param id BaseData主键
	 * @return BaseData实体
	 */
	@Override
	public BaseData getBaseData(BaseData baseData) {
		//执行查询方法
		@SuppressWarnings("unchecked")
		List<BaseData> result = this.findByNamedQueryAndNamedParam("getBaseDataById", "id", baseData.getId());
		if(result.size()>0){
			//获得第一条
			return result.get(0);
		}
		return null;
	}

	/**
	 * 获取一条BaseData
	 * @param Value 基础数据的值
	 * @return BaseData实体
	 */
	@Override
	public BaseData getDataByValue(BaseData baseData) {
		String [] values={"typeId","value"};
		String [] params={baseData.getTypeId(),baseData.getValue()};
		@SuppressWarnings("unchecked")
		List<BaseData> result = this.findByNamedQueryAndNamedParam("checkPortIfExist", values, params);
		if(result.size()>0){
			return result.get(0);
		}else{
			return null;
		}
	}

	
	/**
	 * 移除某个对象
	 * @param baseData
	 */
	@Override
	public void evict(BaseData baseData){
		try {
			super.evict(baseData);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<BaseData> getBaseDataByType(BaseData data) {
		List<BaseData> list = null;
		try {
			list = this.findByNamedQueryAndNamedParam("getBaseDataByType", "name", data.getTypeName());
			if(CollectionUtils.isEmpty(list)){
				return null;
			}
		} catch (Exception e) {
			LogHelper.ERROR.log(e.getMessage(), e);
			return null;
		}
		return list;
	}
	
	@Override
	public List<BaseData> getBaseDataByTypeId(BaseData data) {
		List<BaseData> list = null;
		try {
			list = this.findByNamedQueryAndNamedParam("getBaseDataByTypeId", "typeId", data.getTypeId());
			if(CollectionUtils.isEmpty(list)){
				return null;
			}
		} catch (Exception e) {
			LogHelper.ERROR.log(e.getMessage(), e);
			return null;
		}
		return list;
	}
	
	/**
	 * 根据code获取基础数据
	 */
	@Override
	public List<BaseData> getBaseDataById(BaseData data) {
		List<BaseData> list = null;
		try {
			list = this.findByNamedQueryAndNamedParam("getBaseDataById", "id", data.getId());
			if(CollectionUtils.isEmpty(list)){
				return null;
			}
		} catch (Exception e) {
			LogHelper.ERROR.log(e.getMessage(), e);
			return null;
		}
		return list;
	}

}
