package com.ht.persistence.dao.inter.paper;

import com.ht.persistence.model.paper.PaperChart;

/**
 * CHART属性信息接口
 * @author zqy
 *
 */
public interface PaperChartDao {

	/**
	 * 根据产品ID查询信息
	 * @param paperChart
	 * @return
	 */
	public PaperChart getPaperChart(PaperChart paperChart);
}
