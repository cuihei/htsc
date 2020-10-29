package com.ht.persistence.dao.inter.paper;

import com.ht.persistence.model.paper.PaperSheet;

/**
 * PaperSheet属性信息接口
 * @author hc
 *
 */
public interface PaperSheetDao {

	/**
	 * 根据产品ID查询信息
	 * @param PaperSheet
	 * @return
	 */
	public PaperSheet getPaperSheet(PaperSheet paperSheet);
}
