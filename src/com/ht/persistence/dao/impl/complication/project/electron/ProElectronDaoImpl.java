package com.ht.persistence.dao.impl.complication.project.electron;

import java.util.List;

import com.ht.persistence.dao.impl.base.BaseDaoImpl;

import com.ht.persistence.dao.inter.complication.project.electron.ProElectronDao;
import com.ht.persistence.model.complication.project.electron.Project;


/**
 *  目录区域数据映射操作类
 * @author yaoping 
 *
 */
public class ProElectronDaoImpl extends BaseDaoImpl implements ProElectronDao{

	/**
	 * 查询
	 * 获取所有电子海图数据
	 * @return 电子海图list
	 */
	@Override
	public List<Project> findProEleList() {
		try {
			@SuppressWarnings("unchecked")
			List<Project> result = this.findByNamedQuery("getEleList");
			return result;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
}
