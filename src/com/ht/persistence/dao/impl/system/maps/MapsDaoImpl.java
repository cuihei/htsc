package com.ht.persistence.dao.impl.system.maps;

import java.util.List;

import org.hibernate.Query;

import com.ht.persistence.dao.impl.base.BaseDaoImpl;
import com.ht.persistence.dao.inter.system.maps.MapsDao;
import com.ht.persistence.model.system.maps.Maps;

/**
 * MapsDao 数据映射操作类
 * @author yp
 *
 */
public class MapsDaoImpl  extends BaseDaoImpl implements MapsDao{
	
	/**
	 * 获取所有Maps
	 * @return Maps列表
	 */
	@Override
	public List<Maps> getMapsList() {
		try {

//			@SuppressWarnings("unchecked")
//			List<Maps> result = this.findByNamedQuery("getMapsList");
			Query createQuery = this.getSession().createQuery("select new Maps(id,mapNo) from Maps where 1=1");
			
			return createQuery.list();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	/**
	 * 获取所有Maps
	 * @return Maps列表
	 */
	@Override
	public Maps getMapsListById(Maps maps) {
		try {

			@SuppressWarnings("unchecked")
			List<Maps> result = this.findByNamedQueryAndNamedParam("getMapsListByMapNo","id",maps.getId());
			return result.get(0);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	@Override
	public void add(Maps maps) {
		this.save(maps);
	}

	@Override
	public void modify(Maps maps) {
    	this.update(maps);
	}

	/**
	 * 删除
	 * @param id maps主键
	 */
	@Override
	public void del(Maps maps){
		this.delete(this.load(Maps.class, maps.getId()));
	}
	
	@Override
	public void modifyWithoutImg(Maps maps) {
    	String sql = "update Maps m set m.mapNo = '"+maps.getMapNo()+"' where m.id = '"+maps.getId()+"'";
		this.getSession().createQuery(sql).executeUpdate();
	}
}
