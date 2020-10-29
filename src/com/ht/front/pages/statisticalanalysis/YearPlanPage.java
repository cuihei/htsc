package com.ht.front.pages.statisticalanalysis;

import com.ht.front.css.CssClass;
import com.ht.front.model.Base;
import com.ht.front.model.Button;
import com.ht.front.model.I;
import com.ht.front.util.FrontUtil;

public class YearPlanPage {
	/**
	 * 初始化港口航道图编绘完成情况数据页面
	 * @return 节点字符串
	 * */
	public String getListPage() {
		// 创建前端工具实例
		FrontUtil util = FrontUtil.getInstance();
		// 创建一个容器
		Base root = util.createRoot();
		util.createHeaderBar(root, "编绘计划（生产管理）");
		util.createRowSpace(root);
		/** 创建按钮组行  开始*/
		// 创建行
		Base rowBg = util.createRow(root);
		// 创建按钮组
		Base column = util.createColumn(rowBg, "12", "12", "12", null);
		// 构建创建div
		CssClass css = new CssClass("fa fa-sign-out");
		I i = I.getInstance(css);
		css = new CssClass("btn btn-info btn-setting search");
		Button button = Button.getButtonWithIcon("export", css, "&nbsp;"+"导出", i);
		column.addChildNode(button);
		/** 创建按钮组行  结束*/
		// 创建一个行间隔
		Base rowSpace = util.createRowSpace(root);
		/** 创建Grid行  开始*/
		// 创建Grid
		Base rowGrid = util.createGrid(root,"year_plan");
		/** 创建Grid行  结束*/
		//返回节点字符串
		return root.getNode();
	}

}
