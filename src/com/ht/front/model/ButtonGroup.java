package com.ht.front.model;

import java.util.List;

import com.ht.front.css.CssClass;

public class ButtonGroup extends Base{

	/**
	 * 构造器 引用父类方法 初始化按钮
	 * @param nodeType 节点类型
	 * @param nodeId 节点ID
	 * @param cssClass css样式类型
	 * @param name 按钮值
	 */
	protected ButtonGroup(String nodeType, String nodeId, CssClass cssClass,
			String name) {
		super(nodeType, nodeId, cssClass, name);
	}

	/**
	 * 构造按钮组
	 * @param buttonList 按钮列表
	 * @return
	 */
	public static ButtonGroup getInstance(List<Button> buttonList){
		// 构建行div
		CssClass css = new CssClass("row");
		ButtonGroup bg = new ButtonGroup("div",null,css,null);
		// 构建按钮div
		for (int i = 0; i < buttonList.size(); i++) {
			bg.addChildNode(createColButton("2","1","3",buttonList.get(i)));
		}
		return bg;
	}
	
	/**
	 * 构造按钮组
	 * @param buttonList 按钮列表
	 * @return
	 */
	public static ButtonGroup getDefaultButtonGroup(String businessName){
		// 构建行div
		CssClass css = new CssClass("row");
		ButtonGroup bg = new ButtonGroup("div",null,css,null);
		css = new CssClass("col-md-12 col-xs-12 col-sm-12");
		Div column = Div.getInstance(null, css, null);
		// 构建创建div
		css = new CssClass("fa fa-plus");
		I i = I.getInstance(css);
		css = new CssClass("btn btn-success search");
		Button button = Button.getButtonWithIcon("add", css, "创建"+businessName, i);
		column.addChildNode(button);
		// 构建删除div
		css = new CssClass("fa fa-times");
		i = I.getInstance(css);
		css = new CssClass("btn btn-danger bk-margin-5 search");
		button = Button.getButtonWithIcon("remove", css, "删除"+businessName, i);
		column.addChildNode(button);
		// 构建导出按钮
		css = new CssClass("fa fa-sign-out");
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
		bg.addChildNode(column);
		return bg;
	}
	
	/**
	 * 创建一个占有指定列的按钮
	 * @param colMdNum 正常屏幕占用列数 
	 * @param colXsNum 大屏幕占用列数
	 * @param colSmNum 小屏幕占用列数
	 * @param btnCssClass 按钮样式
	 * @param btnId 按钮ID
	 * @param btnValue 按钮值
	 * @return
	 */
	public static Div createColButton(String colMdNum,String colXsNum,String colSmNum,CssClass btnCssClass,String btnId,String btnValue,I i){
		CssClass css = new CssClass("col-md-" + colMdNum + " col-xs-" + colXsNum+" col-sm-" + colSmNum);
		Div divCol = Div.getInstance(null, css, null);
		// 构建创建按钮div
		Button btn = Button.getButtonWithIcon(btnId,btnCssClass,btnValue,i);
		divCol.addChildNode(btn);
		return divCol;
	}
	
	/**
	 * 创建一个占有指定列的按钮
	 * @param colNum 占用列数量
	 */
	public static Div createColButton(String colMdNum,String colXsNum,String colSmNum,Button btn){
		CssClass css = new CssClass("col-md-" + colMdNum + " col-xs-" + colXsNum+" col-sm-" + colSmNum);
		Div divCol = Div.getInstance(null, css, null);
		// 构建创建按钮div
		divCol.addChildNode(btn);
		return divCol;
	}
	
	/**
	 * 构造按钮组
	 * @param buttonList 按钮列表
	 * @return
	 */
	public static ButtonGroup getSubmitButtonGroup(String businessName){
		// 构建行div
		CssClass css = new CssClass("row");
		ButtonGroup bg = new ButtonGroup("div",null,css,null);
		css = new CssClass("col-md-12 col-xs-12 col-sm-12");
		Div column = Div.getInstance(null, css, null);
		// 构建创建div
		css = new CssClass("fa fa-check-square");
		I i = I.getInstance(css);
		css = new CssClass("btn btn-success bk-margin-5");
		Button button = Button.getButtonWithIcon("submit", css, "提交"+businessName, i);
		column.addChildNode(button);
		// 构建删除div
		css = new CssClass("btn btn-default bk-margin-5");
		button = Button.getInstance("back", css, "返回"+businessName);
		column.addChildNode(button);
		bg.addChildNode(column);
		return bg;
	}
}
