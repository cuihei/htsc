package com.ht.front.pages.datum.his;

import com.ht.front.model.Base;
import com.ht.front.util.FrontUtil;

/**
 * 归还记录页面初始化类
 * @author zyd
 *
 */
public class ReturnHisPage {
	
	/**
	 * 
	 * 初始化归还历史记录页面
	 * @param 
	 * @return 节点字符串
	 */
	public String getListNode(){

		// 创建前端工具实例
		FrontUtil util = FrontUtil.getInstance();
		// 创建一个容器
		Base root = util.createRoot();
		util.createHeaderBar(root, "归还历史记录");
		util.createRowSpace(root);
		/** 创建按钮组行  开始*/
		// 创建行
		Base rowBg = util.createRow(root);
		// 创建按钮组
		Base column = util.createColumn(rowBg, "12", "12", "12", null);
		/** 创建按钮组行  结束*/
		// 创建一个行间隔
		Base rowSpace = util.createRowSpace(root);
		/** 创建Grid行  开始*/
		// 创建Grid
		Base rowGrid = util.createGrid(root,"returnHis");
		/** 创建Grid行  结束*/	
		return root.getNode();
	}
	
}
