package com.ht.service.inter.catalog.detail;

import java.io.File;
import java.io.InputStream;
import java.util.List;

import com.ht.persistence.model.background.dicdata.basedata.BaseData;
import com.ht.persistence.model.catalog.area.CatalogArea;
import com.ht.persistence.model.catalog.detail.CatalogDetail;

/**
* @ClassName: CatalogDetailService 
* @Description: 目录明细接口 
* @author penghao
* @date 2016年10月14日 下午4:46:29 
*
 */
public interface CatalogDetailService{
	
	/**
	 * 查找实体对象
	 * @param id 
	 * @return 实体对象，若不存在则返回null
	 */
	CatalogDetail getDetailById(String id) throws Exception;
	
	/**
	 * 查找所有实体对象集合 
	 * @param jsonParam 
	 * @param jsonParam2 
	 * @param jsonParam2 
	 * @return 所有实体对象集合
	 */
	List<CatalogDetail> getDetailList(String year,String jsonParam) throws Exception;

	/**
	 * 新增实体对象
	 * @param String
	 * @return 
	 */
	void addDetail(String detailParam,String categoryId) throws Exception;

	/**
	 * 删除实体对象
	 * @param id 目录ID
	 */
	void delDetail(String ids) throws Exception;

	/**
	 * 导入目录明细
	 * @param
	 * @return
	 */
	String addDetailByExcel(File file,String categoryId,String type) throws Exception;
	
	/**
	 * 导出目录明细
	 * @param sheetName 表格名称
	 * @return
	 */
	InputStream export(String year,String type,String categoryId,String sheetName,String roleId, String userNo) throws Exception;
	
	/**
	 * 下载模板
	 * @param type 菜单类型
	 * @return InputStream
	 */
	InputStream getTemplate(String type,String sheetName) throws Exception;

	/**
	 * 根据CATEGORY_ID,AREA_ID获取目录
	 * @param entity 实体对象
	 * @return void    返回类型 
	 */
	List<CatalogDetail> getDetailByCategoryId(String categoryId,String areaId) throws Exception;
	
	/**
	 * 获取存在图号的目录类型
	 * @return BaseData    返回类型 
	 */
	List<BaseData> getBaseDataFromDetail() throws Exception;
	
	/**
	 * 根据目录类型获取存在图号的目录区域
	 * @return CatalogArea    返回类型 
	 */
	List<CatalogArea> getCatalogAreaListFromDetail(String baseDataId) throws Exception;
	
	/**
	 * 根据目录类型获取存在图号的目录区域
	 * @return CatalogArea    返回类型 
	 */
	List<CatalogDetail> getDetialListByMapNo(String mapNo,String baseDataId) throws Exception;

	List<CatalogDetail> getOtherDetailList(String year, String jsonParam, String userNo) throws Exception;
	
}
