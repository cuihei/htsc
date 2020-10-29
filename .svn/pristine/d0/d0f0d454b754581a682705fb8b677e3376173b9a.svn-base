package com.ht.front.template;

import com.ht.front.model.Base;
import com.ht.front.util.FrontUtil;

/**
 * 列表页面
 * @author 王有为
 * @date 2016/10/14
 */
public class ListPage {
	
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
		util.createDefaultButtonGroup(column, businessName);
		/** 创建按钮组行  结束*/
		// 创建一个行间隔
		Base rowSpace = util.createRowSpace();
		/** 创建Grid行  结束*/
		// 将行加入到容器
		root.addChildNode(rowBg);
		// 创建Grid
		util.createGrid(root,gridId);
		// 返回行
		return root;
	}
	
	/**
	 * 创建列表页面(没有按钮组)
	 * @param gridId 您将要创建的表格的id,此id将会在前端用以绑定数据
	 * @return 列表页面对象
	 */
	public Base createListPage(String gridId){
		// 创建前端工具实例
		FrontUtil util = FrontUtil.getInstance();
		// 创建一个容器
		Base root = util.createRoot();
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
		root.addChildNode(rowSpace);
		root.addChildNode(rowGrid);
		// 返回行
		return root;
	}	
	
	/**
	 * 创建列表页面(底部带有提交返回按钮)
	 * @param gridId 您将要创建的表格的id,此id将会在前端用以绑定数据
	 * @return 列表页面对象
	 */
	public Base createSubmitListPage(String businessName,String gridId){
		// 创建前端工具实例
		FrontUtil util = FrontUtil.getInstance();
		// 创建一个容器
		Base root = util.createRoot();
		// 创建一个行间隔
		Base rowSpace = util.createRowSpace();
		/** 创建Grid行  开始*/
		// 创建行
		Base rowGrid = util.createRow();
		// 创建列
		Base columnGrid = util.createColumn(rowGrid, "12","12");
		// 创建Grid
		util.createGrid(columnGrid,gridId);
		//创建底部按钮
		util.createSubmitButtonGroup(root, businessName);
		/** 创建Grid行  结束*/
		// 将行加入到容器
		root.addChildNode(rowSpace);
		root.addChildNode(rowGrid);
		// 返回行
		return root;
	}	
}
