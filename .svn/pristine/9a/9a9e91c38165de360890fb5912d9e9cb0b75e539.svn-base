package com.ht.service.impl.paper;

import javax.annotation.Resource;

import com.ht.common.util.DataConverter;
import com.ht.persistence.dao.inter.paper.PaperChartDao;
import com.ht.persistence.model.paper.PaperChart;
import com.ht.service.inter.paper.PaperChartService;

public class PaperChartServiceImpl implements PaperChartService{

	/**
	 * 注入PaperChartDao
	 * */
	@Resource(name = "paperChartDao")
	private PaperChartDao paperChartDao;
	

	/**
	 * 根据产品ID查询信息
	 * @param paperChart
	 * @return
	 */
	public PaperChart getPaperChart(String  paperChart) {
		PaperChart pc = new PaperChart();
		pc.setId(paperChart);
		return paperChartDao.getPaperChart(pc);
	}

}
