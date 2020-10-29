package com.ht.front.pages.complication.correctionnotice.correctionnotice;

import com.ht.front.css.CssClass;
import com.ht.front.css.Prop;
import com.ht.front.model.A;
import com.ht.front.model.Base;
import com.ht.front.model.Button;
import com.ht.front.model.Div;
import com.ht.front.model.I;
import com.ht.front.model.Script;
import com.ht.front.model.Templete;
import com.ht.front.util.FrontUtil;


/**
 * 编绘任务清单前台页面初始化类
 * */
public class CorNoticePage {
	
	/**
	 * 初始化改正通告页面
	 * @return 节点字符串
	 * */
	public String getListNode() {
		FrontUtil util = FrontUtil.getInstance();
		Base root  = util.createRoot();
		util.createHeaderBar(root, "小改正编绘");
		// 创建行
		Base rowGrid = util.createRow();
		// 创建列
		Base columnGrid = util.createColumn(rowGrid, "12","12");
		// 创建Grid
		util.createGrid(columnGrid,"corNotice");
		root.addChildNode(columnGrid);
		root.addChildNode(rowGrid);
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
		root.addChildNode(script);
		//详情按钮
		CssClass detailCss = new CssClass("fa fa-github-alt");
		I detailI = I.getInstance(detailCss);
		detailCss = new CssClass("btn btn-primary");
		Button detailTempelate = Button.getButtonWithIcon(null, detailCss, null, detailI);
		prop = new Prop();
		prop.setPropKey("name");
		prop.setPropValue("details");
		detailTempelate.addProp(prop);
		Script detailScript = Script.getInstance("details");
		detailScript.addChildNode(detailTempelate);
		root.addChildNode(detailScript);
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
		root.addChildNode(exportScript);
		//返回节点字符串
		return root.getNode();
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
		util.createGrid(columnGrid,"corNoticeStatus");
		root.addChildNode(columnGrid);
		root.addChildNode(rowGrid);
		//返回节点字符串
		/*InputHidden hidden = InputHidden.getInstance("statusId", id);
		root.addChildNode(hidden);*/
		return root.getNode();
	}
	
	/**
	 * 初始化详情页面
	 * @return 节点字符串
	 * */
	public String getDetailsPage(String id) {
		CssClass rowCss2 = new CssClass("panel-body bk-bg-white bk-padding-off-top bk-padding-off-bottom");
		Div rowDiv2 = Div.getInstance(null,rowCss2, null);
		CssClass rowCss = new CssClass("row");
		Div rowDiv = Div.getInstance(null,rowCss, null);
		CssClass css = new CssClass("col-xs-9 bk-vcenter");
		Div div = Div.getInstance(null,css, null);
		CssClass pointCss = new CssClass("point point-success point-lg");
		Div pointDiv = Div.getInstance(null,pointCss, null);
		String href="../source/index";
		A a = A.getInstance(href,"编辑资料登记表");
		CssClass h4Css = new CssClass("bk-fg-danger bk-margin-off-bottom");
		CssClass hrCss = new CssClass("bk-margin-off");
		Templete h4 = Templete.getInstance("h4",h4Css);
		Templete hr = Templete.getInstance("hr",hrCss);
		Templete p = Templete.getInstance("p",null);
		h4.addChildNode(pointDiv);
		h4.addChildNode(a);
		div.addChildNode(h4);
		div.addChildNode(p);
		rowDiv.addChildNode(div);
		rowDiv2.addChildNode(rowDiv);
		
		href="../source/initAdopt?id="+id;
		a = A.getInstance(href,"最新改正项号记录表");
		h4 = Templete.getInstance("h4",h4Css);
		h4.addChildNode(pointDiv);
		h4.addChildNode(a);
		div = Div.getInstance(null,css, null);
		div.addChildNode(h4);
		div.addChildNode(p);
		rowDiv = Div.getInstance(null,rowCss, null);
		rowDiv.addChildNode(hr);
		rowDiv.addChildNode(div);
		rowDiv2.addChildNode(rowDiv);
		
		href="../source/initIndexMap?id="+id;
		a = A.getInstance(href,"编辑情况记录表");	
		h4 = Templete.getInstance("h4",h4Css);
		h4.addChildNode(pointDiv);
		h4.addChildNode(a);
		div = Div.getInstance(null,css, null);
		div.addChildNode(h4);
		div.addChildNode(p);
		rowDiv = Div.getInstance(null,rowCss, null);
		rowDiv.addChildNode(hr);
		rowDiv.addChildNode(div);
		rowDiv2.addChildNode(rowDiv);
		
		href="../source/index";
		a = A.getInstance(href,"部门质量检查记录表");	
		h4 = Templete.getInstance("h4",h4Css);
		h4.addChildNode(pointDiv);
		h4.addChildNode(a);
		div = Div.getInstance(null,css, null);
		div.addChildNode(h4);
		div.addChildNode(p);
		rowDiv = Div.getInstance(null,rowCss, null);
		rowDiv.addChildNode(hr);
		rowDiv.addChildNode(div);
		rowDiv2.addChildNode(rowDiv);
		
		href="../source/index";
		a = A.getInstance(href,"中心质量检查记录表");	
		h4 = Templete.getInstance("h4",h4Css);
		h4.addChildNode(pointDiv);
		h4.addChildNode(a);
		div = Div.getInstance(null,css, null);
		div.addChildNode(h4);
		div.addChildNode(p);
		rowDiv = Div.getInstance(null,rowCss, null);
		rowDiv.addChildNode(hr);
		rowDiv.addChildNode(div);
		rowDiv2.addChildNode(rowDiv);
		
		href="../source/index";
		a = A.getInstance(href,"总工程师质量验收记录表");	
		h4 = Templete.getInstance("h4",h4Css);
		h4.addChildNode(pointDiv);
		h4.addChildNode(a);
		div = Div.getInstance(null,css, null);
		div.addChildNode(h4);
		div.addChildNode(p);
		rowDiv = Div.getInstance(null,rowCss, null);
		rowDiv.addChildNode(hr);
		rowDiv.addChildNode(div);
		rowDiv2.addChildNode(rowDiv);
		
		//返回节点字符串
		return rowDiv2.getNode();
	}
}
