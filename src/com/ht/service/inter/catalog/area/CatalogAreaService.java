package com.ht.service.inter.catalog.area;
import java.io.File;
import java.io.IOException;
import java.util.List;

import com.ht.persistence.model.catalog.area.CatalogArea;

public interface CatalogAreaService {
  /**
   * 查询
   * @return 区域管理list
   * @throws Exception
   */
  public List<CatalogArea> getCatalogAreaList() throws Exception;
  /**
   * 更新所选
   * @param catalogArea catalogArea实体
   * @return 更新结果(成功/已存在)
   * @throws Exception
   */
  public void modify(String categoryId,File myfiles,String myfilesFileName) throws Exception;
  /**
   * 删除所选
   * @param ids 多选id字符串
   * @throws Exception
   */
  public void del(String catalogArea) throws Exception;
  
  /**
   * 获取区域
   * @param id 区域主键
   * @return 区域
   * @throws Exception
   */
  public CatalogArea getCatalogAreaById(String id) throws Exception;
  
  /**
   * 根据目录类型获取目录区域
   * @param
   * @return
   */
  public List<CatalogArea> getCatalogAreaListByCategoryId(String categoryId);
  
  /**
   * 上传图片
   * @param
   * @return
   */
  public String uploadFile(File upload, String uploadFileName) throws IOException;
  
  /**
	* 导出基础数据
	* @param lists
	* @return 
	* @throws Exception
	*/
  void export() throws Exception;
}
