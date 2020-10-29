package com.ht.service.inter.datum.type;

import java.util.List;

import com.ht.persistence.model.datum.type.DatumCategory;

/**
 * 资料类别Service
 * @author zyd
 *
 */
public interface DatumCategoryService {
	
	/**
	 * 保存资料类别
	 * @param category_name
	 * @param parent_id
	 * @throws Exception
	 */
	public void addDatumCategory(String datumcategory) throws Exception;
	
	/**
	 * 修改资料类别
	 * @param datumcategory
	 * @throws Exception
	 */
	public void modifyDatumCategory(String datumcategory) throws Exception;
	
	/**
	 * 删除资料类别
	 * @param datumcategory
	 * @throws Exception
	 */
	public void delDatumCategory(String datumcategory) throws Exception;
	
	/**
	 * 查询资料类别
	 * @return
	 * @throws Exception
	 */
	public List<DatumCategory> getDatumCategory() throws Exception;
	
	/**
	 * 查询一条资料类别
	 * @param datumcategory
	 * @return
	 * @throws Exception
	 */
	public DatumCategory getDatumCategory(String datumcategory) throws Exception;
	
	/**
	 * 导出excel
	 * @param datumCategoryList 
	 * @param String sheetName sheet名称
	 * @param String[] title 第一行标题
	 * @param String url 下载后存放的位置
	 * @param String name excel的名称
	 * @param List<DatumCategory> datumCategoryList 对象集合
	 */
	public void exportExcel(String sheetName, String[] title, String url, String name, List<DatumCategory> datumCategoryList);
	
	/**
	 * 获取DatumCategory对象父节点下的所有子节点，形成树结构
	 * @param DatumCategory DatumCategory对象
	 * @return List<DatumCategory> DatumCategory对象的集合
	 * @throws Exception 
	 */
	public String getDatumCategoryTree() throws Exception;
	
	/**
	 * 根据父Id获取部分资料类别
	 * @param parentId
	 * @return
	 */
	public List<DatumCategory> getDatumCategoryByParentId(String parentId);
}
