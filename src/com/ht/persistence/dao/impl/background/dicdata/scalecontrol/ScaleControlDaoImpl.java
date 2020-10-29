package com.ht.persistence.dao.impl.background.dicdata.scalecontrol;

import java.util.List;

import org.hibernate.SQLQuery;

import com.ht.persistence.dao.impl.base.BaseDaoImpl;
import com.ht.persistence.dao.inter.background.dicdata.scalecontrol.ScaleControlDao;
import com.ht.persistence.dao.inter.datum.books.BooksDao;
import com.ht.persistence.model.background.dicdata.scalecontrol.ScaleControl;
import com.ht.persistence.model.datum.books.Books;
import com.ht.persistence.model.datum.books.BooksView;

/**
 * ScaleControl数据映射操作类
 * @author houchen
 *
 */
public class ScaleControlDaoImpl extends BaseDaoImpl implements ScaleControlDao {
	
	/**
	 * 比例尺添加
	 */
	@Override
	public void addScaleControl(ScaleControl scaleControl) {
		this.save(scaleControl);
	}

	/**
	 * 比例尺更新
	 */
	@Override
	public void modifyScaleControl(ScaleControl scaleControl) {
		this.update(scaleControl);
	}

	/**
	 * 删除比例尺
	 */
	@Override
	public void deleteScaleControl(ScaleControl scaleControl) {
		 this.delete(scaleControl);
	}
	
	/**
	 * 获取所有比例尺
	 */
	@Override
	public List<ScaleControl> getScaleControl() {
		try {
			@SuppressWarnings("unchecked")
			// 执行查询
			List<ScaleControl> result = this.findByNamedQuery("getScaleControl");
			if(result.size() > 0){
				return result;
			}
			return null;
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * 获取一条比例尺
	 */
	@Override
	public ScaleControl getScaleControl(ScaleControl scaleControl) {
		@SuppressWarnings("unchecked")
		List<ScaleControl> result = this.findByNamedQueryAndNamedParam("getScaleControlById", "id", scaleControl.getId());
		if(result.size()>0){
			return result.get(0);
		}
		return null;
	}
	
	/**
	 * 获取一条比例尺
	 */
	@Override
	public ScaleControl getStandardByOld(ScaleControl scaleControl) {
		@SuppressWarnings("unchecked")
		List<ScaleControl> result = this.findByNamedQueryAndNamedParam("getStandardByOld", "oldScale", scaleControl.getOldScale());
		if(result.size()>0){
			return result.get(0);
		}
		return null;
	}
	
}
