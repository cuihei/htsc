package com.ht.front.pages.statisticalanalysis;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.ht.front.css.CssClass;
import com.ht.front.model.Base;
import com.ht.front.model.Button;
import com.ht.front.model.I;
import com.ht.front.model.InputGroup;
import com.ht.front.model.Span;
import com.ht.front.model.TextBox;
import com.ht.front.util.FrontUtil;

public class MonthlyPlanPage {
	/**
	 * 初始化港口航道图编绘完成情况数据页面
	 * @return 节点字符串
	 * */
	public String getListPage() {
		// 创建前端工具实例
		FrontUtil util = FrontUtil.getInstance();
		// 创建一个容器
		Base root = util.createRoot();
		util.createHeaderBar(root, "港口航道图月度编绘计划");
		util.createRowSpace(root);
		/** 创建按钮组行  开始*/
		// 创建行
		Base rowBg = util.createRow(root);
		Base col1 = util.createColumn(rowBg,"6","6", "6",null);
		// 创建列
		Base col2 = util.createColumn(rowBg,"6", "6","6",null);
		InputGroup ig = (InputGroup) util.createRangeDatePickerGroup("汇交时间", "date1", "请输入汇交开始日期", "date2", "请输入汇交结束日期");
		col1.addChildNode(ig);
		//创建搜索按钮
		Button searchButton = Button.getButtonWithIcon("search", new CssClass("btn btn-primary search"), "查询", I.getInstance(new CssClass("fa fa-search")));
		col2.addChildNode(searchButton);
		/** 创建按钮组行  结束*/
		// 创建一个行间隔
		root.addChildNode(util.createRowSpace());
		root.addChildNode(util.createRowSpace());
		

		// 创建按钮组
		Base column = util.createColumn(rowBg, "10", "10", "10", null);
		// 构建创建div
		CssClass css = new CssClass("fa fa-sign-out");
		I i = I.getInstance(css);
		css = new CssClass("btn btn-info bk-margin-5");
		Button button = Button.getButtonWithIcon("export", css, "&nbsp;"+"导出", i);
		column.addChildNode(button);
		Base colInput = util.createColumn(rowBg, "2", "2");
		Span span = Span.getDefault("年份:");
		css = new CssClass("fa fa-calendar");
		i = I.getInstance(css);
		span.addChildNode(i);
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		TextBox text = TextBox.getDefault("year", String.valueOf(year), "年份");
		ig = InputGroup.getInstance(span, text);
		colInput.addChildNode(ig);
		/** 创建按钮组行  结束*/
		// 创建一个行间隔
		Base rowSpace = util.createRowSpace(root);
		/** 创建Grid行  开始*/
		// 创建Grid
		Base rowGrid = util.createGrid(root,"monthly_plan");
		/** 创建Grid行  结束*/
		//返回节点字符串
		return root.getNode();
	}

}
