package com.ht.persistence.dao.impl.paper;

import java.util.List;

import com.ht.persistence.dao.impl.base.BaseDaoImpl;
import com.ht.persistence.dao.inter.paper.PaperPanelDao;
import com.ht.persistence.model.paper.PaperPanel;

/**
 * PaperPanel属性信息接口
 * @author hc
 * @date 2016/12/13
 */
public class PaperPanelDaoImpl extends BaseDaoImpl implements PaperPanelDao {

	/**
	 * 根据产品ID查询信息
	 * @param PaperPanel
	 * @return
	 */
	public PaperPanel getPaperPanel(PaperPanel paperPanel) {
	/*	//执行查询方法
		@SuppressWarnings("unchecked")
		List<PaperPanel> result = this.findByNamedQueryAndNamedParam("getPaperPanel", "id", paperPanel.getId());
		if(result.size()>0){
			//获取第一条
			return result.get(0);
		}*/
		return null;
	}

}
