package com.ht.persistence.dao.impl.catalog.area;

import java.util.List;

import com.ht.persistence.dao.impl.base.BaseDaoImpl;

import org.springframework.util.CollectionUtils;

import com.ht.common.util.LogHelper;
import com.ht.persistence.dao.inter.catalog.area.CatalogAreaDao;
import com.ht.persistence.model.catalog.area.CatalogArea;


/**
 *  目录区域数据映射操作类
 * @author yaoping 
 *
 */
@SuppressWarnings("unchecked")
public class CatalogAreaDaoImpl extends BaseDaoImpl implements CatalogAreaDao{

	
	/**
	 * 查询
	 * 获取所有区域管理数据
	 * @return 区域管理list
	 */
	@Override
	public List<CatalogArea> getCatalogAreaList() {
		try {
			List<CatalogArea> result = this.findByNamedQuery("getAreaList");
			return result;
		} catch (Exception e) {
			LogHelper.ERROR.log(e.getMessage(),e);
			return null;
		}
	}
	
	/**
	 * 删除一条区域管理 相关
	 * @param id CatalogArea主键
	 */
	@Override
	public void del(CatalogArea catalogArea){
		this.delete(this.load(CatalogArea.class, catalogArea.getId()));
	}
	
	
	/**
	 * 增加一个区域管理
	 * @param  CatalogArea实体
	 */
	@Override
	public void add(CatalogArea catalogArea) {
		this.save(catalogArea);
	}

	/**
	 * 更新一个区域管理
	 * @param  CatalogArea实体
	 */
	@Override
	public void modify(CatalogArea catalogArea) {
		this.update(catalogArea);
	}
	
	/**
	 * 更新一个区域管理(图片不更新)
	 * @param  CatalogArea实体
	 */
	@Override
	public void modifyWithoutImg(CatalogArea catalogArea) {
		String sql = "update CatalogArea ca set ca.areaName = '"+catalogArea.getAreaName()+
					 "',ca.baseData.id='"+catalogArea.getBaseData().getId()+
					 "' where ca.id = '"+catalogArea.getId()+"'";
		this.getSession().createQuery(sql).executeUpdate();
	}
	
	/**
	 * 校验数据是否存在
	 * @param  catalogArea实体
	 * @return 存在（true）不存在（false）
	 */
	@Override
	public Boolean isExist(CatalogArea catalogArea) {
		//修改
		if(!"".equals(catalogArea.getId())){
			String[] paramNames={"categoryId","areaName","id"};
			String[] values={catalogArea.getBaseData().getId(),catalogArea.getAreaName(),catalogArea.getId()};
			List<CatalogArea>  result = this.findByNamedQueryAndNamedParam("isExistArea", paramNames, values);
			if(result.size()>0){
				return true;
			}
			return false;
		}else{
			//新增
			String[] paramNames={"categoryId","areaName"};
			String[] values={catalogArea.getBaseData().getId(),catalogArea.getAreaName()};
			List<CatalogArea>  result = this.findByNamedQueryAndNamedParam("isAddExistArea", paramNames, values);
			if(result.size()>0){
				return true;
			}
			return false;
		}
	}

	@Override
	public CatalogArea getCatalogAreaById(CatalogArea catalogArea) {
		try {
			List<CatalogArea> result = this.findByNamedQueryAndNamedParam("getCatalogAreaById","id",catalogArea.getId());
			if (result.size()>0) {
				return result.get(0);
			}
			return null;
		} catch (Exception e) {
			LogHelper.ERROR.log(e.getMessage(),e);
			return null;
		}
	}
	
	/**
	 * 根据名称获取ID
	 * @param SeaMap catalogArea实体
	 * @return catalogArea实体
	 */
	@Override
	public CatalogArea getIdByNameAndCategoryId(CatalogArea catalogArea) {
		String[] paramNames={"categoryId","areaName"};
		String[] values={catalogArea.getBaseData().getId(),catalogArea.getAreaName()};
		List<CatalogArea>  result = this.findByNamedQueryAndNamedParam("isAddExistArea", paramNames, values);
		if (CollectionUtils.isEmpty(result)) {
			return null;
		}
		return result.get(0);
	}

	/**
	 * 根据目录类型获取区域
	 * @param CatalogArea
	 * @return list列表
	 */
	@Override
	public List<CatalogArea> getCatalogAreaListByCategoryId(CatalogArea area) {
		try {
			List<CatalogArea> result = this.findByNamedQueryAndNamedParam("getCatalogAreaListByCategoryId","id",area.getBaseData().getId());
			if (CollectionUtils.isEmpty(result)) {
				return null;
			}
			return result;
		} catch (Exception e) {
			LogHelper.ERROR.log(e.getMessage(),e);
			return null;
		}
	}
}
