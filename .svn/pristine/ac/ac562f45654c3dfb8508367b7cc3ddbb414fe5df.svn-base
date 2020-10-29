package com.ht.persistence.dao.inter.system.maps;
import java.util.List;

import com.ht.persistence.model.system.maps.Maps;

/**
 * MapsDao
 * @author yp
*/
public interface MapsDao {
	
	/**
	 * 获取所有maps
	 * @param Maps Maps实体
	 */
	public List<Maps> getMapsList() throws Exception;
	
	/**
	 *根据id查询
	 * @param Maps Maps实体
	 */
	public Maps getMapsListById(Maps maps) throws Exception;
	
	/**
	 *保存
	 * @param Maps Maps实体
	 */
	public void add(Maps maps);
	
	/**
	 *更新
	 * @param Maps Maps实体
	 */
	public void modify(Maps maps);
	
	/**
	 *更新（只更新图号）
	 * @param Maps Maps实体
	 */
	public void modifyWithoutImg(Maps maps);
	
	/**
	 * 删除
	 * @param  maps 实体
	 */
	public void del(Maps maps);
}
