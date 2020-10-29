package com.ht.persistence.dao.inter.catalog.area;

import java.util.List;

import com.ht.persistence.model.catalog.area.CatalogArea;

/**
 * catalogAreaDao
 * @author yp
 */
public interface CatalogAreaDao {
	
	/**
	 * 获取list
	 * @return CatalogArea列表
	 */
	public List<CatalogArea> getCatalogAreaList();
	
	/**
	 * 获取区域
	 */
	public CatalogArea getCatalogAreaById(CatalogArea catalogArea);
	
	/**
	 * 删除一条区域管理
	 * @param  catalogArea 实体
	 */
	public void del(CatalogArea catalogArea);

	/**
	 * 保存一条区域管理
	 * @param  CatalogArea实体
	 */
	public void add(CatalogArea catalogArea);
	
	/**
	 * 校验是否数据已存在
	 * @param  CatalogArea实体
	 */
	public Boolean isExist(CatalogArea catalogArea);
	
	/**
	 * 更新一条区域管理
	 * @param  CatalogArea实体
	 */
	public void modify(CatalogArea catalogArea);
	
	/**
	 * 更新一条区域管理(图片不更新)
	 * @param  CatalogArea实体
	 */
	public void modifyWithoutImg(CatalogArea catalogArea);
	
	/**
	 * 根据名称获取ID
	 * @param String catalogAreaName 区域名称
	 * @return CatalogArea实体
	 */
	public CatalogArea getIdByNameAndCategoryId(CatalogArea catalogArea);

	/**
	 * 根据目录类型获取目录区域
	 * @param
	 * @return
	 */
	public List<CatalogArea> getCatalogAreaListByCategoryId(CatalogArea area);
	
}
