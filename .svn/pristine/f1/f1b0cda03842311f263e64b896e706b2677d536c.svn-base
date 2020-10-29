package com.ht.service.inter.system.maps;

import java.io.File;
import java.util.List;

import com.ht.persistence.model.system.maps.Maps;

/**
 * 图幅模型service
 * @author yp
 * */
public interface MapsService {
	
  	/**
	 *查询所有图幅
	 * @param Maps Maps实体
	 */
  public List<Maps> getMapsList() throws Exception;
  
  /**
	 *根据id查询
	 * @param Maps Maps实体
	 */
  public Maps getMapsListById(String id) throws Exception;
  
  /**
   * 更新
   * @throws Exception
   */
  public void modify(String id,String mapNo,File myfiles,String myfilesFileName) throws Exception;
  
  /**
   * 删除所选
   * @param ids 多选id字符串
   * @throws Exception
   */
  public void del(String maps) throws Exception;
  
}
