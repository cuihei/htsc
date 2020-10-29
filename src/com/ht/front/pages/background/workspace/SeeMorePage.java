package com.ht.front.pages.background.workspace;

import java.util.ArrayList;
import java.util.List;

import com.ht.front.css.CssClass;
import com.ht.front.css.Prop;
import com.ht.front.model.A;
import com.ht.front.model.Base;
import com.ht.front.model.Button;
import com.ht.front.model.I;
import com.ht.front.model.InputHidden;
import com.ht.front.model.Script;
import com.ht.front.util.FrontUtil;
import com.ht.persistence.model.background.dicdata.basedata.BaseData;

/**
 * 通知管理页面初始化类
 */
public class SeeMorePage
{

	/**
	 * 页面实例
	 */
	private static SeeMorePage page = null;

	/**
	 * 获取页面实例
	 * @return
	 */
	public static SeeMorePage getInstance()
	{
		if (page == null)
		{
			page = new SeeMorePage();
		}
		return page;
	}

	/**
	 * 初始化通知数据页面
	 * @param flag
	 * @return 节点字符串
	 */
	public String getListNode(String flag)
	{
		// 创建前端工具实例
		FrontUtil util = FrontUtil.getInstance();
		// 创建一个容器
		Base root = util.createRoot();
		String notice = "已读通知";
		if ("0".equals(flag) || flag == null)
		{
			notice = "未读通知";
		}

		util.createHeaderBar(root, notice);
		util.createRowSpace(root);
		/** 创建按钮组行 开始 */
		// 创建行
		Base rowBg = util.createRow(root);
		// 创建按钮组
		Base column = util.createColumn(rowBg, "10", "10", "10", null);
		// 构建创建div
		CssClass css = new CssClass("fa fa-check");
		I i = I.getInstance(css);
		css = new CssClass("btn btn-success bk-margin-5");
		Button button = null;
		if ("0".equals(flag))
		{
			button = Button.getButtonWithIcon("read", css, "&nbsp;" + "标记为已读", i);
			column.addChildNode(button);
		}
		/** 创建按钮组行 结束 */
		// 创建一个行间隔
		Base rowSpace = util.createRowSpace(root);
		/** 创建Grid行 开始 */
		// 创建Grid
		Base rowGrid = util.createGrid(root, "seeMore");
		/** 创建Grid行 结束 */
		Base a = A.getInstance("", "标记为已读");
		Prop Prop = new Prop();
		Prop.setPropKey("href");
		Prop.setPropValue("javascript:void(0)");
		a.addProp(Prop);
		Prop prop = new Prop();
		prop.setPropKey("name");
		prop.setPropValue("read");
		a.addProp(prop);
		Prop prop1 = new Prop();
		prop1.setPropKey("style");
		prop1.setPropValue("color:grey;");
		a.addProp(prop1);
		css = new CssClass("fa  fa-check-square");
		i = I.getInstance(css);
		a.addChildNode(i);
		Script script = Script.getInstance("a");
		script.addChildNode(a);
		// 设置标识Id 隐藏域
		InputHidden noticeId = InputHidden.getInstance("flag", flag);
		root.addChildNode(noticeId);
		return root.getNode() + script.getNode();
	}

}