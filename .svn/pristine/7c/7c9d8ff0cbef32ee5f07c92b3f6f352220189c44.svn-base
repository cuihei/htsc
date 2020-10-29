package com.ht.service.impl.paper;

import javax.annotation.Resource;

import com.ht.common.util.DataConverter;
import com.ht.persistence.dao.inter.paper.PaperPanelDao;
import com.ht.persistence.model.paper.PaperPanel;
import com.ht.service.inter.paper.PaperPanelService;

public class PaperPanelServiceImpl implements PaperPanelService{

	/**
	 * 注入Dao
	 * */
	@Resource(name = "paperPanelDao")
	private PaperPanelDao paperPanelDao;
	
	/**
	 * 根据产品ID查询信息
	 * @param PaperPanel
	 * @return
	 */
	public PaperPanel getPaperPanel(String paperPanel) {
		PaperPanel pp = new PaperPanel();
		pp.setId(paperPanel);
		return paperPanelDao.getPaperPanel(pp);
	}

}
