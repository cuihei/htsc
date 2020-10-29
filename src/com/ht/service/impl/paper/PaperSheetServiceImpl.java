package com.ht.service.impl.paper;

import javax.annotation.Resource;

import com.ht.common.util.DataConverter;
import com.ht.persistence.dao.inter.paper.PaperSheetDao;
import com.ht.persistence.model.paper.PaperSheet;
import com.ht.service.inter.paper.PaperSheetService;

public class PaperSheetServiceImpl implements PaperSheetService{

	/**
	 * 注入Dao
	 * */
	@Resource(name = "paperSheetDao")
	private PaperSheetDao paperSheetDao;
	
	/**
	 * 根据产品ID查询信息
	 * @param PaperSheet
	 * @return
	 */
	public PaperSheet getPaperSheet(String paperSheet) {
		PaperSheet ps =new PaperSheet();
		ps.setId(paperSheet);
		return paperSheetDao.getPaperSheet(ps);
	}

}
