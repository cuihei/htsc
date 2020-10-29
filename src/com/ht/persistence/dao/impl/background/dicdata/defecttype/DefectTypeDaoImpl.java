package com.ht.persistence.dao.impl.background.dicdata.defecttype;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import com.ht.common.util.LogHelper;
import com.ht.persistence.dao.impl.base.BaseDaoImpl;
import com.ht.persistence.dao.inter.background.dicdata.defecttype.DefectTypeDao;
import com.ht.persistence.model.background.dicdata.defecttype.DefectType;

/** 
* @ClassName: DefectDaoImpl 
* @Description: TODO(缺陷的数据操作) 
* @author penghao
* @date 2016年11月6日 下午2:43:41 
*  
*/
@SuppressWarnings("unchecked")
public class DefectTypeDaoImpl extends BaseDaoImpl implements DefectTypeDao{

	
	/**
	 * 获取缺陷类别列表
	 */
	@Override
	public List<DefectType> getDefectTypeList() {
		List<DefectType> list = null;
		try {
			//获取缺陷列表
			list = this.findByNamedQuery("getDefectTypeList");
			if(CollectionUtils.isEmpty(list)){
				return null;
			}
		} catch (Exception e) {
			LogHelper.ERROR.log(e.getMessage());
		}
		return list;
	}

	/**
	 * 保存
	 */
	@Override
	public void add(DefectType type) {
		this.save(type);
	}

	/**
	 * 更改缺陷类别
	 */
	@Override
	public void modifyDefectType(DefectType type) {
		type = (DefectType) this.merge(type);
		this.update(type);
	}

	/**
	 * 根据id删除缺陷类别
	 */
	@Override
	public void delDefectType(DefectType type) {
		type = (DefectType)this.merge(type);
		this.delete(type);
	}

	/**
	 * 根据类别id获取缺陷类别
	 */
	@Override
	public DefectType getDefectTypeById(DefectType type) {
		List<DefectType> list = null;
		try {
			//根据id获取缺陷
			list = this.findByNamedQueryAndNamedParam("getDefectTypeById", "id", type.getId());
			if(CollectionUtils.isEmpty(list)){
				return null;
			}
		} catch (Exception e) {
		}
		return list.get(0);
	}

	/**
	 * 根据海图类型id获取缺陷类别集合
	 */
	@Override
	public List<DefectType> getDefectTypeListByCharttypeId(DefectType type) {
		List<DefectType> list = null;
		try {
			String id = null;
			if(type.getCharttype() != null && StringUtils.isNotBlank(type.getCharttype().getId())){
				id = type.getCharttype().getId();
			}
			//根据海图类型id获取类别集合
			list = this.findByNamedQueryAndNamedParam("getDefectTypeListByCharttypeId", "id", id);
			if(CollectionUtils.isEmpty(list)){
				return null;
			}
		} catch (Exception e) {
		}
		return list;
	}

	/**
	 * 根据海图类型id和类别判断数据是否已经存在
	 */
	@Override
	public List<DefectType> isExsit(DefectType type) {
		String charttypeId = null;
		if(type.getCharttype() != null && StringUtils.isNotBlank(type.getCharttype().getId())){
			charttypeId = type.getCharttype().getId();
		}
		//组织参数
		String[] names = {"charttypeId","typeName"};
		String[] params = {charttypeId,type.getTypeName()};
		List<DefectType> list = null;
		try {
			// 根据条件获取结果
			 list = this.findByNamedQueryAndNamedParam("getDefectTypeByCtIdAndTypeName",names,params);
		    if (CollectionUtils.isEmpty(list)) {
				return null;
			}
		} catch (Exception e) {
			LogHelper.ERROR.log(e.getMessage());
		}
		return list;
		
	}

}
