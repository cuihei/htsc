package com.ht.persistence.dao.impl.background.dicdata.defect;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.springframework.util.CollectionUtils;

import com.ht.common.util.LogHelper;
import com.ht.persistence.dao.impl.base.BaseDaoImpl;
import com.ht.persistence.dao.inter.background.dicdata.defect.DefectDao;
import com.ht.persistence.model.background.dicdata.defect.Defect;
import com.ht.persistence.model.background.dicdata.defect.ViewDefect;

/** 
* @ClassName: DefectDaoImpl 
* @Description: TODO(缺陷的数据操作) 
* @author penghao
* @date 2016年11月6日 下午2:43:41 
*  
*/
@SuppressWarnings("unchecked")
public class DefectDaoImpl extends BaseDaoImpl implements DefectDao{

	
	/**
	 * 获取缺陷列表
	 */
	@Override
	public List<Defect> getDefectList(Defect defect) {
		String hql = "from Defect where 1=1";
		if(defect != null && defect.getCharttype()!=null && StringUtils.isNotBlank(defect.getCharttype().getId())){
			hql += " and charttype.id="+defect.getCharttype().getId();
		}
		if(defect != null && StringUtils.isNotBlank(defect.getDiscription())){
			hql += " and discription like '%"+defect.getDiscription()+"%'";
		}
		List<Defect> list = null;
		try {
			//获取缺陷列表
			/*list = this.findByNamedQuery("getDefectList");*/
			list = this.find(hql);
			if(CollectionUtils.isEmpty(list)){
				return null;
			}
		} catch (Exception e) {
			LogHelper.ERROR.log(e.getMessage());
		}
		return list;
	}

	/**
	 * 添加数据
	 */
	@Override
	public void add(Defect defect) {
		try {
			this.save(defect);
		} catch (Exception e) {
			LogHelper.ERROR.log(e.getMessage());
		}
	}

	/**
	 * 更新数据
	 */
	@Override
	public void modifyDefect(Defect defect) {
		defect = (Defect) this.merge(defect);
		this.update(defect);
	}

	/**
	 * 删除数据
	 */
	@Override
	public void delDefect(Defect defect) {
		this.delete(defect);
	}

	/**
	 * 根据id获取缺陷
	 */
	@Override
	public Defect getDefectById(Defect defect) {
		List<Defect> list = null;
		try {
			//根据id获取缺陷
			list = this.findByNamedQueryAndNamedParam("getDefectById", "id", defect.getId());
			if(CollectionUtils.isEmpty(list)){
				return null;
			}
		} catch (Exception e) {
		}
		return list.get(0);
	}
	
	/**
	 * 获取缺陷列表
	 */
	@Override
	public List<ViewDefect> getList() {
		List<ViewDefect> list = null;
		try {
			//根据id获取缺陷
			list = this.findByNamedQuery("getViewDefectList");
		} catch (Exception e) {
			LogHelper.ERROR.log(e.getMessage());
		}
		return list;
	}

	/**
	 * 判断是否存在
	 */
	@Override
	public List<Defect> isExsit(Defect defect) {
		
		String hql = "from Defect where 1=1";
		if(defect.getCharttype() != null && StringUtils.isNotBlank(defect.getCharttype().getId())){
			hql += " and charttype.id =?";
		}
		if(defect.getType() != null && StringUtils.isNotBlank(defect.getType().getId())){
			hql += " and type.id =?";
		}
		if(defect.getItem() != null && StringUtils.isNotBlank(defect.getItem().getId())){
			hql += " and item.id =?";
		}
		if(StringUtils.isNotBlank(defect.getDiscription())){
			hql += " and discription =?";
		}
		List<Defect> list = null;
		try {
			// 根据条件获取结果
			 Query query = this.getSession().createQuery(hql);
			 if(defect.getCharttype() != null && StringUtils.isNotBlank(defect.getCharttype().getId())){
					String charttypeId = defect.getCharttype().getId();
					query.setParameter(0, charttypeId);
				}
				if(defect.getType() != null && StringUtils.isNotBlank(defect.getType().getId())){
					String defecttypeId = defect.getType().getId();
					query.setParameter(1, defecttypeId);
				}
				if(defect.getItem() != null && StringUtils.isNotBlank(defect.getItem().getId())){
					String itemId = defect.getItem().getId();
					query.setParameter(2, itemId);
				}
				if(StringUtils.isNotBlank(defect.getDiscription())){
					String discription = defect.getDiscription();
					query.setParameter(3, discription);
				}
				list = query.list();
		} catch (Exception e) {
			LogHelper.ERROR.log(e.getMessage());
		}
		return list;
	}
	
	
	/**
	 * 根据类别id获取项目
	 */
	@Override
	public List<Defect> getDefectByDefectItemId(Defect defect) {
		List<Defect> list = null;
		try {
			String defectitemId = null;
			if(defect.getItem() != null && StringUtils.isNotBlank(defect.getItem().getId())){
				defectitemId = defect.getItem().getId();
			}
			list = this.findByNamedQueryAndNamedParam("getDefectByDefectItemId","defectitemId",defectitemId);
		} catch (Exception e) {
			LogHelper.ERROR.log(e.getMessage());
		}
		return list;
	}

	/**
	 * 根据3个id获取缺陷列表
	 */
	@Override
	public List<Defect> getDefectListBy3Id(Defect defect) {
		String hql = "from Defect where 1=1";
		if(defect.getCharttype() != null && StringUtils.isNotBlank(defect.getCharttype().getId())){
			hql += " and charttype.id =?";
		}
		if(defect.getType() != null && StringUtils.isNotBlank(defect.getType().getId())){
			hql += " and type.id =?";
		}
		if(defect.getItem() != null && StringUtils.isNotBlank(defect.getItem().getId())){
			hql += " and item.id =?";
		}
		List<Defect> list = null;
		try {
			Query query = this.getSession().createQuery(hql);
			if(defect.getCharttype() != null && StringUtils.isNotBlank(defect.getCharttype().getId())){
				String charttypeId = defect.getCharttype().getId();
				query.setParameter(0, charttypeId);
			}
			if(defect.getType() != null && StringUtils.isNotBlank(defect.getType().getId())){
				String defecttypeId = defect.getType().getId();
				query.setParameter(1, defecttypeId);
			}
			if(defect.getItem() != null && StringUtils.isNotBlank(defect.getItem().getId())){
				String itemId = defect.getItem().getId();
				query.setParameter(2, itemId);
			}
			list = query.list();
		} catch (Exception e) {
			LogHelper.ERROR.log(e.getMessage());
		}
		return list;
	}

}
