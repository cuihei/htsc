package com.ht.front.pages.catalog.history;

import com.ht.front.css.CssClass;
import com.ht.front.model.Base;
import com.ht.front.model.Button;
import com.ht.front.model.I;
import com.ht.front.model.InputHidden;
import com.ht.front.model.TextBox;
import com.ht.front.util.FrontUtil;

public class HistoryCatalogDetailPage
{

	/**
	 * 初始化目录页面
	 * @param
	 * @return 节点字符串
	 */
	public static String getListNode(String type)
	{
		// 创建前端工具实例
		FrontUtil util = FrontUtil.getInstance();
		// 创建一个容器
		Base root = util.createRoot();
		if (type.equals("1"))
		{// 规划目录图管理
			util.createHeaderBar(root, "规划图目录");
		}
		else if (type.equals("2"))
		{// 海军目录图管理
			util.createHeaderBar(root, "海军图目录");
		}
		else if (type.equals("3"))
		{// 港口航道图管理
			util.createHeaderBar(root, "港口航道图目录");
		}
		util.createRowSpace(root);
		/** 创建按钮组行 开始 */
		// 创建行
		Base rowBg = util.createRow(root);
		// 创建按钮组
		Base column = util.createColumn(rowBg, "12", "12", "12", null);
		// 构建创建div
		CssClass css = new CssClass("fa fa-sign-out");
		I i = I.getInstance(css);
		css = new CssClass("btn btn-info btn-setting search");
		Button button = Button.getButtonWithIcon("btn", css, "导出", i);
		column.addChildNode(button);
		/** 创建按钮组行 结束 */

		// 创建隐藏域
		InputHidden hiddenType = InputHidden.getInstance("type", type);
		root.addChildNode(hiddenType);
		// 创建一个行间隔
		Base rowSpace = util.createRowSpace(root);
		/** 创建Grid行 开始 */
		// 创建Grid
		Base rowGrid = util.createGrid(root, "detail");
		/** 创建Grid行 结束 */
		return root.getNode();
	}

	/**
	 * 绘制页面
	 * @return
	 */
	public static String getPage()
	{
		// 获取前端工具实例
		FrontUtil util = FrontUtil.getInstance();
		Base root = util.createRoot();
		// 创建一行
		Base row = util.createRow(root);
		Button btn = Button.getInstance("btn", new CssClass("bk-margin-5 btn btn-primary"), "导出Excel");
		row.addChildNode(btn);

		// 返回整个页面
		return root.getNode();
	}

	/**
	 * 绘制页面
	 * @return
	 */
	public static String getPage1()
	{
		// 获取前端工具实例
		FrontUtil util = FrontUtil.getInstance();
		Base root = util.createRoot();
		// 创建一行
		Base row = util.createRow(root);
		// 创建列
		Base col = util.createColumn(row, "3", "2");
		// 创建一个搜索框
		TextBox text = TextBox.getDefault("id", null, "请输入编号");
		// 把文本框放入列内
		col.addChildNode(text);

		row = util.createRow(root);
		// 创建一个搜索框
		text = TextBox.getDefault("id", null, "请输入名称");
		// 把文本框放入列内
		row.addChildNode(text);
		root.addChildNode(util.createRowSpace());
		// 返回整个页面
		return root.getNode();
	}
}
