package com.ht.persistence.dao.inter.background.dicdata.scalecontrol;

import java.util.List;

import com.ht.persistence.model.background.dicdata.scalecontrol.ScaleControl;
import com.ht.persistence.model.datum.bookinfo.BookInfo;
import com.ht.persistence.model.datum.books.Books;
import com.ht.persistence.model.datum.books.BooksView;

/**
 * 比例尺管理Dao
 * @author houchen
 *
 */
public interface ScaleControlDao {
	
	/**
	 * 增加比例尺
	 * @param ScaleControl ScaleControl实体
	 */
	public void addScaleControl(ScaleControl scaleControl);
	
	/**
	 * 更新比例尺
	 * @param ScaleControl ScaleControl实体
	 */
	public void modifyScaleControl(ScaleControl scaleControl);
	
	/**
	 * 删除比例尺
	 * @param ScaleControl ScaleControl实体
	 */
	public void deleteScaleControl(ScaleControl scaleControl);
	
	/**
	 * 获取所有比例尺
	 * @return ScaleControl列表
	 */
	public List<ScaleControl> getScaleControl();
	
	/**
	 * 获取一条比例尺
	 * @param scaleControl ScaleControl实体
	 * @return ScaleControl实体
	 */
	public ScaleControl getScaleControl(ScaleControl scaleControl);
	
	/**
	 * 根据原比例尺获取标准比例尺
	 * @param scaleControl ScaleControl实体
	 * @return ScaleControl实体
	 */
	public ScaleControl getStandardByOld(ScaleControl scaleControl);
	
}
