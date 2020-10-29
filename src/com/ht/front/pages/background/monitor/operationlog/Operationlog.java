package com.ht.front.pages.background.monitor.operationlog;

import java.util.ArrayList;
import java.util.List;

import com.ht.front.css.CssClass;
import com.ht.front.model.Base;
import com.ht.front.model.Button;
import com.ht.front.model.Div;
import com.ht.front.model.I;
import com.ht.front.model.InputGroup;
import com.ht.front.template.EditPage;
import com.ht.front.util.FrontUtil;


/**
 * 系统操作日志前台页面初始化类
 * */
public class Operationlog {
	
	/**
	 * 初始化日志数据页面
	 * @return 节点字符串
	 * */
	public String getListNode() {
		FrontUtil util = FrontUtil.getInstance();
		// 创建一个容器
		Base root = util.createRoot();
		util.createHeaderBar(root, "系统操作日志管理");
		util.createRowSpace(root);
		/** 创建按钮组行  开始*/
		// 创建行
		Base rowBg = util.createRow(root);
		// 创建按钮组
		Base column = util.createColumn(rowBg, "12", "12", "12", null);
		// 构建创建div
		CssClass css = new CssClass("fa fa-times");
		I i = I.getInstance(css);
		css = new CssClass("btn btn-danger bk-margin-5 search");
		Button button = Button.getButtonWithIcon("remove", css, "删除", i);
		column.addChildNode(button);
		// 构建导出按钮
		css = new CssClass("fa fa-sign-out");
		i = I.getInstance(css);
		css = new CssClass("btn btn-info bk-margin-5 search");
		button = Button.getButtonWithIcon("export", css, "导出", i);
		column.addChildNode(button);
		// 构建刷新按钮
		css = new CssClass("fa fa-refresh");
		i = I.getInstance(css);
		css = new CssClass("btn btn-warning bk-margin-5 search");
		button = Button.getButtonWithIcon("refresh", css, "刷新", i);
		column.addChildNode(button);
		/** 创建按钮组行  结束*/
		// 创建一个行间隔
		Base rowSpace = util.createRowSpace(root);
		/** 创建Grid行  开始*/
		// 创建Grid
		Base rowGrid = util.createGrid(root,"syslogOperation");
		/** 创建Grid行  结束*/
		return root.getNode();
	}
	
	/**
	 * 初始化新增日志信息页面
	 * @return 节点字符串
	 * */
	public String getAddNode() {
		EditPage edit = new EditPage();
		List<Base> param = new ArrayList<Base>();
		InputGroup tb = InputGroup.getInGroup("日志编号", "id", null, "请输入日志编号");
		param.add(tb);
		tb = InputGroup.getInGroup("操作者id", "operatorId", null, "请输入操作者id");
		param.add(tb);
		tb = InputGroup.getInGroup("操作者ip", "operatorIp", null, "请输入操作者ip");
		param.add(tb);
		tb = InputGroup.getInGroup("操作行为", "operationBehavior", null, "请输入操作行为");
		param.add(tb);
		tb = InputGroup.getInGroup("操作结果", "operationResult", null, "请输入操作结果");
		param.add(tb);
		tb = InputGroup.getInGroup("操作对象", "operationObject", null, "请输入操作对象");
		param.add(tb);
		InputGroup ta = InputGroup.getDatePicker("operationTime");
		param.add(ta);
		Base editPage = edit.createEditPage(param);
		FrontUtil util = FrontUtil.getInstance();
		util.createHeaderBar(editPage, "操作日志创建");
		//返回节点字符串
		return editPage.getNode();
	}

	/**
	 * 创建列表页面
	 * @param businessName 您的业务名称
	 * @param gridId 您将要创建的表格的id,此id将会在前端用以绑定数据
	 * @return 列表页面对象
	 */
	public Base createListPage(String businessName,String gridId){
		// 创建前端工具实例
		FrontUtil util = FrontUtil.getInstance();
		// 创建一个容器
		Base root = util.createRoot();
		/** 创建按钮组行  开始*/
		// 创建行
		Base rowBg = util.createRow();
		// 创建列
		Base column = util.createColumn(rowBg, "12","12");
		// 创建按钮组
		createDefaultButtonGroup(column, businessName);
		/** 创建按钮组行  结束*/
		// 创建一个行间隔
		Base rowSpace = util.createRowSpace();
		/** 创建Grid行  开始*/
		// 创建行
		Base rowGrid = util.createRow();
		// 创建列
		Base columnGrid = util.createColumn(rowGrid, "12","12");
		// 创建Grid
		util.createGrid(columnGrid,gridId);
		/** 创建Grid行  结束*/
		// 将行加入到容器
		root.addChildNode(rowBg);
		root.addChildNode(rowSpace);
		root.addChildNode(rowGrid);
		// 返回行
		return root;
	}
	
	/**
	 * 增加默认按钮组
	 * @param parent 父容器
	 * @param businessName 业务名称
	 * @return 按钮组对象
	 */
	public Base createDefaultButtonGroup(Base parent,String businessName){
		// 构建行div
		CssClass css = new CssClass("row");
		css = new CssClass("col-md-12 col-xs-12 col-sm-12");
		Div column = Div.getInstance(null, css, null);
		// 构建删除div
		css = new CssClass("fa fa-times");
		I i = I.getInstance(css);
		css = new CssClass("btn btn-danger bk-margin-5 search");
		Button button = Button.getButtonWithIcon("remove", css, "删除"+businessName, i);
		column.addChildNode(button);
		// 构建导出按钮
		css = new CssClass("fa fa-mail-reply-all");
		i = I.getInstance(css);
		css = new CssClass("btn btn-info bk-margin-5 search");
		button = Button.getButtonWithIcon("export", css, "导出"+businessName, i);
		column.addChildNode(button);
		// 构建刷新按钮
		css = new CssClass("fa fa-refresh");
		i = I.getInstance(css);
		css = new CssClass("btn btn-warning bk-margin-5 search");
		button = Button.getButtonWithIcon("refresh", css, "刷新", i);
		column.addChildNode(button);
		parent.addChildNode(column);
		return parent;
	}
}
