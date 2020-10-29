package com.ht.persistence.dao.inter.paper;

import com.ht.persistence.model.paper.PaperPanel;

/**
 * PaperPanel属性信息接口
 * @author hc
 *
 */
public interface PaperPanelDao {

	/**
	 * 根据产品ID查询信息
	 * @param PaperPanel
	 * @return
	 */
	public PaperPanel getPaperPanel(PaperPanel paperPanel);
}
