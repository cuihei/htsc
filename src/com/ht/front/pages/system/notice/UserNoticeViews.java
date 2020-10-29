package com.ht.front.pages.system.notice;

import com.ht.front.css.CssClass;
import com.ht.front.model.Base;
import com.ht.front.model.Button;
import com.ht.front.model.I;
import com.ht.front.model.InputHidden;
import com.ht.front.util.FrontUtil;

public class UserNoticeViews {
	/**
	 * 初始化模板类型数据页面
	 * @return 节点字符串
	 * */
	public String getListNode(String flag){
		// 创建前端工具实例
		FrontUtil util = FrontUtil.getInstance();
		// 创建一个容器
		Base root = util.createRoot();
		util.createHeaderBar(root, "用户通知管理");
		util.createRowSpace(root);
		/** 创建按钮组行  开始*/
		// 创建行
		Base rowBg = util.createRow(root);
		// 创建按钮组
		Base column = util.createColumn(rowBg, "12", "12", "12", null);
		// 添加按钮
		CssClass css = new CssClass("fa fa-mail-reply-all");
		I i = I.getInstance(css);
		css = new CssClass("btn btn-success btn-setting search");
		Button button = Button.getButtonWithIcon("back", css, "返回", i);
		column.addChildNode(button);
		/** 创建按钮组行  结束*/
		// 创建一个行间隔
		Base rowSpace = util.createRowSpace(root);
		/** 创建Grid行  开始*/
		// 创建Grid
		Base rowGrid = util.createGrid(root,"usernoticeview");
		InputHidden hiddenFlag = InputHidden.getInstance("flag",flag);
		root.addChildNode(hiddenFlag);
		/** 创建Grid行  结束*/		
		return root.getNode();
	}
}
