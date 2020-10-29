package com.ht.front.pages.test;

import com.ht.front.css.CssClass;
import com.ht.front.model.Base;
import com.ht.front.model.Button;
import com.ht.front.model.I;
import com.ht.front.model.InputGroup;
import com.ht.front.util.FrontUtil;

public class TestPage {
	/**
	 * 初始化页面
	 * @return 节点字符串
	 * */
	public String getPage() {
		// 创建前端工具实例
		FrontUtil util = FrontUtil.getInstance();
		// 创建一个容器
		Base root = util.createRoot();
		util.createHeaderBar(root, "测试");
		util.createRowSpace(root);
		/** 创建按钮组行  开始*/
		// 创建行
		Base rowBg = util.createRow(root);
		// 创建按钮组
		Base column = util.createColumn(rowBg, "11", "11", "11", null);
		// 输入框
		InputGroup input = InputGroup.getInGroup("sql：", "sql",null, "请输入sql");
		column.addChildNode(input);
		util.createRowSpace(root);
		// 创建行
		Base rowBg1 = util.createRow(root);
		// 创建按钮组
		Base column1 = util.createColumn(rowBg1, "11", "11", "11", null);
		// 按钮
		CssClass css = new CssClass("fa fa-search");
		I i = I.getInstance(css);
		css = new CssClass("btn btn-success btn-setting search");
		Button button = Button.getButtonWithIcon("search", css, "&nbsp;"+"查询", i);
		column1.addChildNode(button);
		/** 创建按钮组行  结束*/
		// 创建一个行间隔
		Base rowSpace = util.createRowSpace(root);
		/** 创建Grid行  开始*/
		// 创建Grid
		Base rowGrid = util.createGrid(root,"test");
		/** 创建Grid行  结束*/
		//返回节点字符串
		return root.getNode();
	}

}
