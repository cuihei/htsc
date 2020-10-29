package com.ht.front.pages.datum.his;

import com.ht.front.css.CssClass;
import com.ht.front.css.Prop;
import com.ht.front.model.Base;
import com.ht.front.model.Button;
import com.ht.front.model.I;
import com.ht.front.model.InputHidden;
import com.ht.front.model.Script;
import com.ht.front.util.FrontUtil;

/**
 * 借阅历史记录页面初始化类
 * @author zyd
 *
 */
public class BorrowHisPage {
	
	/**
	 * 
	 * 初始化资料维护页面
	 * @param 
	 * @return 节点字符串
	 */
	public String getListNode(String userName,String type){
		
		// 创建前端工具实例
		FrontUtil util = FrontUtil.getInstance();
		// 创建一个容器
		Base root = util.createRoot();
		util.createHeaderBar(root, "借阅归还记录");
		util.createRowSpace(root);
		/** 创建按钮组行  开始*/
		// 创建行
		Base rowBg = util.createRow(root);
		// 创建按钮组
		Base column = util.createColumn(rowBg, "12", "12", "12", null);
		// 构建创建div
		CssClass css = new CssClass("fa fa-plus-square");
		I i = I.getInstance(css);
		css = new CssClass("btn btn-info bk-margin-4");
		Button button = Button.getButtonWithIcon("export", css, "&nbsp;"+"导出", i);
		if(type.equals("2")){
			column.addChildNode(button);
		}
		/** 创建按钮组行  结束*/
		// 创建一个行间隔
		Base rowSpace = util.createRowSpace(root);
		/** 创建Grid行  开始*/
		// 创建Grid
		Base rowGrid = util.createGrid(root,"borrowHis");
		/** 创建Grid行  结束*/
		CssClass qzcss = new CssClass("fa fa-plus-square");
		I qzi = I.getInstance(qzcss);
		qzcss = new CssClass("btn btn-success bk-margin-4");
		Button qztempelate = Button.getButtonWithIcon(null, qzcss, null, qzi);
		Prop qzprop = new Prop();
		qzprop.setPropKey("name");
		qzprop.setPropValue("return");
		qztempelate.addProp(qzprop);
		qzprop = new Prop();
		qzprop.setPropKey("onclick");
		qzprop.setPropValue("returnPage(this)");
		qztempelate.addProp(qzprop);
		qzprop = new Prop();
		qzprop.setPropKey("title");
		qzprop.setPropValue("归还");
		qztempelate.addProp(qzprop);
		Script qzscript = Script.getInstance("qzTemplate");
		qzscript.addChildNode(qztempelate);
		InputHidden hiddenId = InputHidden.getInstance("userName",userName);
		InputHidden hiddenType = InputHidden.getInstance("type",type);
		root.addChildNode(hiddenId);
		root.addChildNode(hiddenType);
		return root.getNode()+qzscript.getNode();
	}
	
	
	/**
	 * 
	 * 初始化资料维护页面
	 * @param 
	 * @return 节点字符串
	 */
	public String getCurListNode(String userName){
		
		// 创建前端工具实例
		FrontUtil util = FrontUtil.getInstance();
		// 创建一个容器
		Base root = util.createRoot();
		util.createHeaderBar(root, "当前借阅归还记录");
		util.createRowSpace(root);
		/** 创建按钮组行  开始*/
		// 创建行
		Base rowBg = util.createRow(root);
		// 创建按钮组
		Base column = util.createColumn(rowBg, "12", "12", "12", null);
		// 构建创建div
		CssClass css = new CssClass("fa fa-plus-square");
		I i = I.getInstance(css);
		css = new CssClass("btn btn-success bk-margin-4");
		Button button = Button.getButtonWithIcon("batchreturn", css, "&nbsp;"+"归还", i);
		column.addChildNode(button);
		/** 创建按钮组行  结束*/
		// 创建一个行间隔
		Base rowSpace = util.createRowSpace(root);
		/** 创建Grid行  开始*/
		// 创建Grid
		Base rowGrid = util.createGrid(root,"borrowCur");
		/** 创建Grid行  结束*/
		CssClass qzcss = new CssClass("fa fa-plus-square");
		I qzi = I.getInstance(qzcss);
		qzcss = new CssClass("btn btn-success bk-margin-4");
		Button qztempelate = Button.getButtonWithIcon(null, qzcss, null, qzi);
		Prop qzprop = new Prop();
		qzprop.setPropKey("name");
		qzprop.setPropValue("return");
		qztempelate.addProp(qzprop);
		qzprop = new Prop();
		qzprop.setPropKey("onclick");
		qzprop.setPropValue("returnPage(this)");
		qztempelate.addProp(qzprop);
		qzprop = new Prop();
		qzprop.setPropKey("title");
		qzprop.setPropValue("归还");
		qztempelate.addProp(qzprop);
		Script qzscript = Script.getInstance("qzTemplate");
		qzscript.addChildNode(qztempelate);
		InputHidden hiddenId = InputHidden.getInstance("userName",userName);
		root.addChildNode(hiddenId);
		return root.getNode()+qzscript.getNode();
	}
}
