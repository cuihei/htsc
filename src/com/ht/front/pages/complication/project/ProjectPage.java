package com.ht.front.pages.complication.project;

import com.ht.front.css.CssClass;
import com.ht.front.css.Prop;
import com.ht.front.model.Base;
import com.ht.front.model.Button;
import com.ht.front.model.Div;
import com.ht.front.model.I;
import com.ht.front.model.Script;
import com.ht.front.util.FrontUtil;


/**
 * 编绘任务清单前台页面初始化类
 * */
public class ProjectPage {
	
	/**
	 * 初始化编绘任务清单海图编绘
	 * @return 节点字符串
	 * */
	public String getListNode() {
		Div div = Div.getInstance(null, null, null);
		//流转状态按钮
		CssClass css = new CssClass("fa fa-arrows-v");
		I i = I.getInstance(css);
		css = new CssClass("btn btn-primary");
		Button tempelate = Button.getButtonWithIcon(null, css, null, i);
		Prop prop = new Prop();
		prop.setPropKey("name");
		prop.setPropValue("status");
		tempelate.addProp(prop);
		Script script = Script.getInstance("status");
		script.addChildNode(tempelate);
		//详情按钮
		CssClass detailCss = new CssClass("fa fa-github-alt");
		I detailI = I.getInstance(detailCss);
		detailCss = new CssClass("btn btn-primary");
		Button detailTempelate = Button.getButtonWithIcon(null, detailCss, null, detailI);
		prop = new Prop();
		prop.setPropKey("name");
		prop.setPropValue("details");
		detailTempelate.addProp(prop);
		prop = new Prop();
		prop.setPropKey("title");
		prop.setPropValue("查看详情");
		detailTempelate.addProp(prop);
		Script detailScript = Script.getInstance("details");
		detailScript.addChildNode(detailTempelate);
		//导出按钮
		CssClass exportCss = new CssClass("fa fa-paste");
		I exportI = I.getInstance(css);
		css = new CssClass("btn btn-primary");
		Button exportTempelate = Button.getButtonWithIcon(null, exportCss, null, exportI);
		prop = new Prop();
		prop.setPropKey("name");
		prop.setPropValue("export");
		tempelate.addProp(prop);
		prop = new Prop();
		prop.setPropKey("title");
		prop.setPropValue("导出");
		tempelate.addProp(prop);
		Script exportScript = Script.getInstance("export");
		exportScript.addChildNode(exportTempelate);
		//返回节点字符串
		return div.getNode()+script.getNode()+detailScript.getNode()+exportScript.getNode();
	}
	
	/**
	 * 初始化流转状态页面
	 * @return 节点字符串
	 * */
	public String getStatusPage() {
		FrontUtil util = FrontUtil.getInstance();
		Base root  = util.createRoot();
		// 创建行
		Base rowGrid = util.createRow();
		// 创建列
		Base columnGrid = util.createColumn(rowGrid, "12","12");
		// 创建Grid
		util.createGrid(columnGrid,"proelectronStatus");
		root.addChildNode(columnGrid);
		root.addChildNode(rowGrid);
		//返回节点字符串
		/*InputHidden process_inst = InputHidden.getInstance("process_inst_id", process_inst_id);
		InputHidden task = InputHidden.getInstance("task_id", task_id);
	    root.addChildNode(process_inst);
	    root.addChildNode(task);*/
		return root.getNode();
	}
	
}
