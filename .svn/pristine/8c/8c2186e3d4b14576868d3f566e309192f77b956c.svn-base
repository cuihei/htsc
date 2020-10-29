package com.ht.persistence.dao.impl.background.dicdata.defectitem;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;

import com.ht.common.util.LogHelper;
import com.ht.persistence.dao.impl.base.BaseDaoImpl;
import com.ht.persistence.dao.inter.background.dicdata.defectitem.DefectItemDao;
import com.ht.persistence.model.background.dicdata.defectitem.DefectItem;
import com.ht.persistence.model.background.dicdata.defecttype.DefectType;

/** 
* @ClassName: DefectDaoImpl 
* @Description: TODO(缺陷项目的数据操作) 
* @author penghao
* @date 2016年11月6日 下午2:43:41 
*  
*/
@SuppressWarnings("unchecked")
public class DefectItemDaoImpl extends BaseDaoImpl implements DefectItemDao{

	/**
	 * 获取项目列表
	 */
	@Override
	public List<DefectItem> getDefectItemList(DefectItem item) {
		String hql = "from DefectItem where 1=1";
		if(item !=null && StringUtils.isNotBlank(item.getItem())){
			hql += " and item like '%"+item.getItem()+"%'";
		}
		if(item !=null && item.getCharttype()!=null && StringUtils.isNotBlank(item.getCharttype().getId())){
			hql += " and charttype.id="+item.getCharttype().getId();
		}
		hql  = hql + " order by creationDate desc";
		List<DefectItem> list = null;
		try {
			//获取缺陷列表
			/*list = this.findByNamedQuery("getDefectItemList");*/
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
	 * 保存数据
	 * @param item
	 */
	@Override
	public void add(DefectItem item) {
		this.save(item);
	}

	/**
	 * 更新实体
	 * @param defect
	 */
	@Override
	public void modifyDefectItem(DefectItem item) {
		item = (DefectItem) this.merge(item);
		this.update(item);
	}

	/**
	 * 删除项目
	 */
	@Override
	public void delDefectItem(DefectItem item) {
		item = (DefectItem) this.merge(item);
		this.delete(item);
	}

	/**
	 * 根据id获取项目
	 */
	@Override
	public DefectItem getDefectItemById(DefectItem item) {
		List<DefectItem> list = null;
		try {
			//根据id获取缺陷
			list = this.findByNamedQueryAndNamedParam("getDefectByItemId", "id", item.getId());
			if(CollectionUtils.isEmpty(list)){
				return null;
			}
		} catch (Exception e) {
			LogHelper.ERROR.log(e.getMessage());
		}
		return list.get(0);
	}

	/**
	 * 根据类别id获取项目列表
	 */
	@Override
	public List<DefectItem> getDefectItemListByDefectTypeId(DefectItem item) {
		List<DefectItem> list = null;
		try {
			String id = null;
			if(item.getType() != null && StringUtils.isNotBlank(item.getType().getId())){
				id = item.getType().getId();
			}
			//根据id获取缺陷
			list = this.findByNamedQueryAndNamedParam("getDefectItemListByDefectTypeId", "id", id);
			if(CollectionUtils.isEmpty(list)){
				return null;
			}
		} catch (Exception e) {
			LogHelper.ERROR.log(e.getMessage());
		}
		return list;
	}

	/**
	 * 根据海图类型id，类别id和项目判断数据是否存在
	 */
	@Override
	public List<DefectItem> isExsit(DefectItem item) {
		String charttypeId = null;
		if(item.getCharttype() != null && StringUtils.isNotBlank(item.getCharttype().getId())){
			charttypeId = item.getCharttype().getId();
		}
		String defecttypeId = null;
		if(item.getType() != null && StringUtils.isNotBlank(item.getType().getId())){
			defecttypeId = item.getType().getId();
		}
		//组织参数
		String[] names = {"charttypeId","defecttypeId","item"};
		String[] params = {charttypeId,defecttypeId,item.getItem()};
		List<DefectItem> list = null;
		try {
			// 根据条件获取结果
			 list = this.findByNamedQueryAndNamedParam("getDefectByCtIdAndDtIdAndItem",names,params);
		    if (CollectionUtils.isEmpty(list)) {
				return null;
			}
		} catch (Exception e) {
			LogHelper.ERROR.log(e.getMessage());
		}
		return list;
	}

	/**
	 * 根据类别id获取项目
	 */
	@Override
	public List<DefectItem> getDefectItemByDefectTypeId(DefectItem item) {
		List<DefectItem> list = null;
		try {
			String defecttypeId = null;
			if(item.getType() != null && StringUtils.isNotBlank(item.getType().getId())){
				defecttypeId = item.getType().getId();
			}
			list = this.findByNamedQueryAndNamedParam("getDefectItemByDefectTypeId","defecttypeId",defecttypeId);
			if(CollectionUtils.isEmpty(list)){
				return null;
			}
		} catch (Exception e) {
			LogHelper.ERROR.log(e.getMessage());
		}
		return list;
	}
}
