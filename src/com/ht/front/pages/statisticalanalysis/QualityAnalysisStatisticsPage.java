package com.ht.front.pages.statisticalanalysis;

import com.ht.front.css.CssClass;
import com.ht.front.model.Base;
import com.ht.front.model.Button;
import com.ht.front.model.I;
import com.ht.front.model.InputGroup;
import com.ht.front.model.InputHidden;
import com.ht.front.util.FrontUtil;
/**
 * 海图质量分析统计page
 * @author huodesheng
 *
 */
public class QualityAnalysisStatisticsPage {
	/**
	 * 初始化海图质量分析统计数据页面
	 * @return 节点字符串
	 * */
	public String getListPage(String key) {
		// 创建前端工具实例
		FrontUtil util = FrontUtil.getInstance();
		// 创建一个容器
		Base root = util.createRoot();
		util.createHeaderBar(root, "海图质量分析统计");
		util.createRowSpace(root);
		/** 创建按钮组行  开始*/
		// 创建行
		Base rowBg = util.createRow(root);
		// 创建列
		Base col1 = util.createColumn(rowBg,"6","6", "6",null);
		Base col2 = util.createColumn(rowBg,"6", "6","6",null);
		InputGroup ig = (InputGroup) util.createRangeDatePickerGroup("起止时间", "startTime", "请输入开始时间", "endTime", "请输入结束时间");
		col1.addChildNode(ig);
		//创建搜索按钮
		Button searchButton = Button.getButtonWithIcon("search", new CssClass("btn btn-primary search"), "查询", I.getInstance(new CssClass("fa fa-search")));
		col2.addChildNode(searchButton);
		// 创建一个行间隔
		root.addChildNode(util.createRowSpace());
		root.addChildNode(util.createRowSpace());
		// 创建按钮组
		Base column = util.createColumn(rowBg, "12", "12", "12", null);
		// 构建创建div
		CssClass css = new CssClass("fa fa-sign-out");
		I i = I.getInstance(css);
		css = new CssClass("btn btn-info bk-margin-5");
		Button button = Button.getButtonWithIcon("export", css, "&nbsp;"+"导出", i);
		column.addChildNode(button);
		/** 创建按钮组行  结束*/
		// 创建一个行间隔
		util.createRowSpace(root);
		/** 创建Grid行  开始*/
		// 创建Grid
		util.createGrid(root,"qualityAnalysisStatistics");
		/** 创建Grid行  结束*/
		//返回节点字符串
		InputHidden hidden = InputHidden.getInstance("key", key);
		root.addChildNode(hidden);
		return root.getNode();
	}

}
